package per.goweii.actionbarex;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
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

    private static final int STATUS_BAR_MODE_LIGHT = 0;
    private static final int STATUS_BAR_MODE_DARK = 1;

    private boolean mAutoImmersion;
    private int mBackgroundLayerLayoutRes;
    private int mBackgroundLayerImageRes;
    private boolean mStatusBarVisible;
    private boolean mStatusBarDarkMode;
    private int mStatusBarHeight;
    private int mStatusBarColor;
    private int mTitleBarLayoutRes;
    private int mTitleBarHeight;
    private int mBottomLineColor;
    private int mBottomLineResId;
    private int mBottomLineHeight;
    private int mForegroundLayerLayoutRes;
    private int mClickToFinishViewId;
    private boolean mBottomLineOutside;

    private View mBackgroundLayer;
    private LinearLayout mActionBar;
    private View mStatusBar;
    private FrameLayout mTitleBar;
    private View mTitleBarChild;
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
        mStatusBarHeight = StatusBarCompat.getHeight(context);
        initAttrs(attrs);
        makeImmersion();
        initView();
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

    public View getTitleBarChild() {
        return mTitleBarChild;
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
        return mStatusBarVisible ? mStatusBarHeight : 0;
    }

    public int getTitleBarHeight() {
        return mTitleBarHeight;
    }

    public int getBottomHeight() {
        return mBottomLineHeight;
    }

    /**
     * 初始化布局文件传入的配置
     * 子类可重写并初始化自己定义的属性配置，必须保证在第一行调用super方法
     *
     * @param attrs AttributeSet
     */
    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ActionBarEx);

        float titleBarHeightDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_height_def);
        float bottomLineHeightDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_bottom_line_height_def);
        int bottomLineColorDef = ContextCompat.getColor(getContext(), R.color.actionbarex_common_bottom_line_color_def);
        int statusBarColorDef = ContextCompat.getColor(getContext(), R.color.actionbarex_common_status_bar_color_def);

        mAutoImmersion = typedArray.getBoolean(R.styleable.ActionBarEx_ab_autoImmersion, true);
        mBackgroundLayerLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_backgroundLayerLayout, 0);
        mBackgroundLayerImageRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_backgroundLayerImageRes, 0);
        mStatusBarVisible = typedArray.getBoolean(R.styleable.ActionBarEx_ab_statusBarVisible, true);
        mStatusBarDarkMode = typedArray.getInt(R.styleable.ActionBarEx_ab_statusBarMode, STATUS_BAR_MODE_LIGHT) == STATUS_BAR_MODE_DARK;
        mStatusBarColor = typedArray.getColor(R.styleable.ActionBarEx_ab_statusBarColor, statusBarColorDef);
        mTitleBarLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_titleBarLayout, 0);
        mTitleBarHeight = (int) typedArray.getDimension(R.styleable.ActionBarEx_ab_titleBarHeight, titleBarHeightDef);
        mBottomLineHeight = (int) typedArray.getDimension(R.styleable.ActionBarEx_ab_bottomLineHeight, bottomLineHeightDef);
        mBottomLineColor = typedArray.getColor(R.styleable.ActionBarEx_ab_bottomLineColor, bottomLineColorDef);
        mBottomLineResId = typedArray.getResourceId(R.styleable.ActionBarEx_ab_bottomLineResId, 0);
        mBottomLineOutside = typedArray.getBoolean(R.styleable.ActionBarEx_ab_bottomLineOutside, false);
        mForegroundLayerLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_foregroundLayerLayout, 0);
        mClickToFinishViewId = typedArray.getResourceId(R.styleable.ActionBarEx_ab_clickToFinish, 0);

        typedArray.recycle();
    }

    /**
     * 初始化子TitleBar
     *
     * @return TitleBarChild
     */
    protected View inflateTitleBar() {
        if (mTitleBarLayoutRes > 0) {
            return inflate(getContext(), mTitleBarLayoutRes, null);
        }
        return null;
    }

    private void initView() {
        // 1 初始化BackgroundLayer
        if (mBackgroundLayerLayoutRes > 0) {
            mBackgroundLayer = inflate(getContext(), mBackgroundLayerLayoutRes, null);
            addViewInLayout(mBackgroundLayer, getChildCount(), makeLayerLayoutParams(), true);
        } else {
            if (mBackgroundLayerImageRes > 0) {
                ImageView actionBarImageView = new ImageView(getContext());
                actionBarImageView.setImageResource(mBackgroundLayerImageRes);
                actionBarImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                addViewInLayout(actionBarImageView, getChildCount(), makeLayerLayoutParams(), true);
            }
        }

        // 2 初始ActionBarLayer
        mActionBar = (LinearLayout) inflate(getContext(), R.layout.actionbarex_action_bar, null);
        mActionBar.setLayoutParams(makeLayoutParamsWithHeight(getActionBarHeight()));
        // 2.1 初始StatusBar
        mStatusBar = mActionBar.findViewById(R.id.actionbarex_status_bar);
        mStatusBar.setLayoutParams(makeLayoutParamsWithHeight(mStatusBarHeight));
        mStatusBar.setBackgroundColor(mStatusBarColor);
        mStatusBar.setVisibility(mStatusBarVisible ? VISIBLE : GONE);
        // 2.2 初始TitleBar
        mTitleBar = mActionBar.findViewById(R.id.actionbarex_title_bar);
        mTitleBar.setClickable(true);
        mTitleBar.setFocusable(true);
        mTitleBar.setFocusableInTouchMode(true);
        mTitleBar.setLayoutParams(makeLayoutParamsWithHeight(mTitleBarHeight));
        mTitleBarChild = inflateTitleBar();
        if (mTitleBarChild != null) {
            mTitleBar.addView(mTitleBarChild);
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
        addViewInLayout(mActionBar, getChildCount(), makeLayoutParamsWithHeight(getActionBarHeight()), true);

        // 3 初始ForegroundLayer
        if (mForegroundLayerLayoutRes > 0) {
            mForegroundLayer = inflate(getContext(), mForegroundLayerLayoutRes, null);
            addViewInLayout(mForegroundLayer, getChildCount(), makeLayerLayoutParams(), true);
        }
        performClickToFinish();
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

    private void performClickToFinish() {
        if (mTitleBarChild == null) {
            return;
        }
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

    private LayoutParams makeLayerLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getActionBarHeight());
    }

    private LinearLayout.LayoutParams makeLayoutParamsWithHeight(int height) {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
    }

    /**
     * 设置沉浸模式
     */
    private void makeImmersion() {
        hintSystemActionBar();
        refreshStatusBar();
    }

    /**
     * 透明状态栏，改变状态栏图标颜色模式
     */
    public void refreshStatusBar() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        StatusBarCompat.setIconMode(activity, mStatusBarDarkMode);
        if (mAutoImmersion) {
            StatusBarCompat.transparent(activity);
        } else {
            Window window = activity.getWindow();
            StatusBarCompat.setColor(window, mStatusBarColor);
        }
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
        return Utils.getActivity(getContext());
    }

    public void finishActivity() {
        Utils.finishActivity(getContext());
    }

    @Override
    public boolean isInEditMode() {
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
