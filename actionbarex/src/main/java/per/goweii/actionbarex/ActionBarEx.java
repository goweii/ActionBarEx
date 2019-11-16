package per.goweii.actionbarex;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import per.goweii.statusbarcompat.StatusBarCompat;

/**
 * 高拓展性和定制性的ActionBar
 * 整个ActionBar分为3层：
 * ----BackgroundLayer 背景层：可自定义布局
 * ----ActionBarLayer 主体层：改层为垂直线性布局，包含下面三个部分：
 * --------StatusBar：系统状态栏
 * --------TitleBar：位于StatusBar和BottomLine之间，可自定义布局
 * --------BottomLine：分割线
 * ----ForegroundLayer 前景层：可自定义布局
 *
 * @author Cuizhen
 * @date 2018/8/30-上午11:10
 */
public class ActionBarEx extends FrameLayout {

    private static final int STATUS_BAR_MODE_UNCHANGED = 0;
    private static final int STATUS_BAR_MODE_LIGHT = 1;
    private static final int STATUS_BAR_MODE_DARK = 2;

    private static final int IMMERSION_UNCHANGED = 0;
    private static final int IMMERSION_ORDINARY = 1;
    private static final int IMMERSION_IMMERSED = 2;

    private Activity mActivity = null;

    private int mImmersion = IMMERSION_UNCHANGED;
    private int mBackgroundLayerLayoutRes = 0;
    private int mBackgroundLayerImageRes = 0;
    private boolean mStatusBarVisible = false;
    private int mStatusBarMode = STATUS_BAR_MODE_UNCHANGED;
    private int mStatusBarColor = Color.TRANSPARENT;
    private int mTitleBarLayoutRes = 0;
    private int mBottomLineColor = Color.TRANSPARENT;
    private int mBottomLineResId = 0;
    private int mBottomLineHeight = 0;
    private int mForegroundLayerLayoutRes = 0;
    private int mClickToFinishViewId = 0;
    private boolean mBottomLineOutside = false;

    private View mBackgroundLayer;
    private LinearLayout mActionBar;
    private View mStatusBar;
    private FrameLayout mTitleBar;
    private View mBottomLine;
    private View mForegroundLayer;

    private SparseArray<View> views = null;

    public ActionBarEx(Context context) {
        this(context, null);
    }

    public ActionBarEx(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionBarEx(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        hintSystemActionBar();
        initAttrs(attrs);
        initView();
        refresh();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mBottomLineOutside) {
            ViewParent parent = getParent();
            if (parent instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) parent;
                viewGroup.setClipChildren(false);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mActionBar.measure(widthMeasureSpec, heightMeasureSpec);
        int width = mActionBar.getMeasuredWidth();
        int height = getActionBarHeight();
        int widthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthSpec, heightSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (child == mForegroundLayer) {
                continue;
            }
            if (child == mActionBar) {
                continue;
            }
            if (child == mBackgroundLayer) {
                continue;
            }
            removeView(child);
            mTitleBar.addView(child, 0);
        }
    }

    public View getBackgroundLayer() {
        return mBackgroundLayer;
    }

    public LinearLayout getActionBar() {
        return mActionBar;
    }

    public View getStatusBar() {
        return mStatusBar;
    }

    public FrameLayout getTitleBar() {
        return mTitleBar;
    }

    public View getBottomLine() {
        return mBottomLine;
    }

    public View getForegroundLayer() {
        return mForegroundLayer;
    }

    /**
     * 获取View并缓存，以便下次获取，避免频繁调用findViewById
     *
     * @param id View的id
     * @return View
     */
    public <V extends View> V getView(@IdRes int id) {
        if (views == null) {
            views = new SparseArray<>();
        }
        View view = views.get(id);
        if (view == null) {
            view = findViewById(id);
            views.put(id, view);
        }
        return (V) view;
    }

    public int getActionBarHeight() {
        if (mBottomLineOutside) {
            return getStatusBarHeight() + getTitleBarHeight();
        } else {
            return getStatusBarHeight() + getTitleBarHeight() + getBottomHeight();
        }
    }

    public int getStatusBarHeight() {
        return mStatusBarVisible ? StatusBarCompat.getHeight(getContext()) : 0;
    }

    public int getTitleBarHeight() {
        return mTitleBar.getMeasuredHeight();
    }

    public int getBottomHeight() {
        return mBottomLineHeight;
    }

    /**
     * 设置沉浸模式
     */
    public void refresh() {
        refreshImmersion();
        refreshStatusBarMode();
        refreshStatusBarColor();
    }

    /**
     * 透明状态栏
     */
    public void refreshImmersion() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        switch (mImmersion) {
            case IMMERSION_ORDINARY:
                StatusBarCompat.unTransparent(activity);
                break;
            case IMMERSION_IMMERSED:
                StatusBarCompat.transparent(activity);
                break;
            default:
                break;
        }
    }

    /**
     * 改变状态栏图标颜色模式
     */
    public void refreshStatusBarMode() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        switch (mStatusBarMode) {
            case STATUS_BAR_MODE_LIGHT:
                StatusBarCompat.setIconMode(activity, false);
                break;
            case STATUS_BAR_MODE_DARK:
                StatusBarCompat.setIconMode(activity, true);
                break;
            default:
                break;
        }
    }

    public void refreshStatusBarColor() {
        switch (mImmersion) {
            case IMMERSION_ORDINARY:
                Activity activity = getActivity();
                if (activity != null) {
                    StatusBarCompat.setColor(activity.getWindow(), mStatusBarColor);
                }
                break;
            case IMMERSION_IMMERSED:
                mStatusBar.setBackgroundColor(mStatusBarColor);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化布局文件传入的配置
     * 子类可重写并初始化自己定义的属性配置，必须保证在第一行调用super方法
     *
     * @param attrs AttributeSet
     */
    @CallSuper
    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ActionBarEx);
        mImmersion = typedArray.getInt(R.styleable.ActionBarEx_ab_immersion, mImmersion);
        mBackgroundLayerLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_backgroundLayerLayout, mBackgroundLayerLayoutRes);
        mBackgroundLayerImageRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_backgroundLayerImageRes, mBackgroundLayerLayoutRes);
        mStatusBarVisible = typedArray.getBoolean(R.styleable.ActionBarEx_ab_statusBarVisible, mStatusBarVisible);
        mStatusBarMode = typedArray.getInt(R.styleable.ActionBarEx_ab_statusBarMode, mStatusBarMode);
        mStatusBarColor = typedArray.getColor(R.styleable.ActionBarEx_ab_statusBarColor, mStatusBarColor);
        mTitleBarLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_titleBarLayout, mTitleBarLayoutRes);
        mBottomLineHeight = (int) typedArray.getDimension(R.styleable.ActionBarEx_ab_bottomLineHeight, mBottomLineHeight);
        mBottomLineColor = typedArray.getColor(R.styleable.ActionBarEx_ab_bottomLineColor, mBottomLineColor);
        mBottomLineResId = typedArray.getResourceId(R.styleable.ActionBarEx_ab_bottomLineResId, mBottomLineResId);
        mBottomLineOutside = typedArray.getBoolean(R.styleable.ActionBarEx_ab_bottomLineOutside, mBottomLineOutside);
        mForegroundLayerLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_foregroundLayerLayout, mForegroundLayerLayoutRes);
        mClickToFinishViewId = typedArray.getResourceId(R.styleable.ActionBarEx_ab_clickToFinish, mClickToFinishViewId);
        typedArray.recycle();
    }

    /**
     * 初始化子TitleBar
     *
     * @return TitleBarChild
     */
    protected View inflateTitleBar() {
        if (getTitleBarRes() > 0) {
            return LayoutInflater.from(getContext()).inflate(getTitleBarRes(), mTitleBar, false);
        }
        return null;
    }

    protected int getTitleBarRes() {
        return mTitleBarLayoutRes;
    }

    @CallSuper
    protected void initView() {
        // 1 初始化BackgroundLayer
        if (mBackgroundLayerLayoutRes > 0) {
            mBackgroundLayer = inflate(getContext(), mBackgroundLayerLayoutRes, null);
            addViewInLayout(mBackgroundLayer, getChildCount(), makeLayerLayoutParamsMatch(), true);
        } else {
            if (mBackgroundLayerImageRes > 0) {
                ImageView actionBarImageView = new ImageView(getContext());
                mBackgroundLayer = actionBarImageView;
                actionBarImageView.setImageResource(mBackgroundLayerImageRes);
                actionBarImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                addViewInLayout(actionBarImageView, getChildCount(), makeLayerLayoutParamsMatch(), true);
            }
        }
        // 2 初始ActionBarLayer
        mActionBar = (LinearLayout) inflate(getContext(), R.layout.actionbarex_action_bar, null);
        // 2.1 初始StatusBar
        mStatusBar = mActionBar.findViewById(R.id.actionbarex_status_bar);
        mStatusBar.setLayoutParams(makeLayoutParamsWithHeight(StatusBarCompat.getHeight(getContext())));
        mStatusBar.setVisibility(mStatusBarVisible ? VISIBLE : GONE);
        // 2.2 初始TitleBar
        mTitleBar = mActionBar.findViewById(R.id.actionbarex_title_bar);
        mTitleBar.setClickable(true);
        mTitleBar.setFocusable(true);
        mTitleBar.setFocusableInTouchMode(true);
        mTitleBar.setLayoutParams(makeLayoutParamsWithHeight(ViewGroup.LayoutParams.WRAP_CONTENT));
        View titleBarChild = inflateTitleBar();
        if (titleBarChild != null) {
            mTitleBar.addView(titleBarChild);
        }
        // 2.3 初始BottomLine
        mBottomLine = mActionBar.findViewById(R.id.actionbarex_bottom_line);
        mBottomLine.setLayoutParams(makeLayoutParamsWithHeight(mBottomLineHeight));
        if (mBottomLineResId > 0) {
            mBottomLine.setBackgroundResource(mBottomLineResId);
        } else {
            mBottomLine.setBackgroundColor(mBottomLineColor);
        }
        if (mBottomLineOutside) {
            mActionBar.setClipChildren(false);
            setClipChildren(false);
        }
        addViewInLayout(mActionBar, getChildCount(), makeLayerLayoutParamsWrap(), true);
        // 3 初始ForegroundLayer
        if (mForegroundLayerLayoutRes > 0) {
            mForegroundLayer = inflate(getContext(), mForegroundLayerLayoutRes, null);
            addViewInLayout(mForegroundLayer, getChildCount(), makeLayerLayoutParamsMatch(), true);
        }
        performClickToFinish();
    }

    private void performClickToFinish() {
        View clickToFinishView = getView(mClickToFinishViewId);
        if (clickToFinishView == null) {
            return;
        }
        clickToFinishView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }

    private LayoutParams makeLayerLayoutParamsWrap() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    private LayoutParams makeLayerLayoutParamsMatch() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    private LinearLayout.LayoutParams makeLayoutParamsWithHeight(int height) {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
    }

    /**
     * 隐藏默认的ActionBar
     */
    private void hintSystemActionBar() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (activity.getActionBar() != null) {
            activity.getActionBar().hide();
        }
        if (activity instanceof AppCompatActivity) {
            AppCompatActivity compatActivity = (AppCompatActivity) activity;
            if (compatActivity.getSupportActionBar() != null) {
                compatActivity.getSupportActionBar().hide();
            }
        }
    }

    /**
     * 从当前上下文获取Activity
     */
    @Nullable
    private Activity getActivity() {
        if (mActivity != null) {
            return mActivity;
        }
        Context context = getContext();
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            if (baseContext instanceof Activity) {
                mActivity = (Activity) baseContext;
            }
        }
        return mActivity;
    }

    public void finishActivity() {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }
}
