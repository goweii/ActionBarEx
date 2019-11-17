package per.goweii.actionbarex;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import per.goweii.statusbarcompat.StatusBarCompat;


/**
 * 高拓展性和定制性的ActionBar
 * 整个ActionBar分为3层：
 * ----BackgroundLayer 背景层：可自定义布局
 * ----ActionBar 主体层：改层为垂直线性布局，包含下面三个部分：
 * --------StatusBar：系统状态栏
 * --------TitleBar：位于StatusBar和BottomLine之间，可自定义布局
 * --------BottomLine：分割线
 * ----ForegroundLayer 前景层：可自定义布局
 *
 * @author Cuizhen
 * @date 2018/8/30-上午11:10
 */
public class ActionBarEx extends FrameLayout {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({StatusBarMode.UNCHANGED, StatusBarMode.LIGHT, StatusBarMode.DARK, StatusBarMode.AUTO})
    public @interface StatusBarMode {
        int UNCHANGED = 0;
        int LIGHT = 1;
        int DARK = 2;
        int AUTO = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({StatusBarVisible.AUTO, StatusBarVisible.VISIBLE, StatusBarVisible.GONE})
    public @interface StatusBarVisible {
        int AUTO = 0;
        int VISIBLE = 1;
        int GONE = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Immersion.UNCHANGED, Immersion.ORDINARY, Immersion.IMMERSED})
    public @interface Immersion {
        int UNCHANGED = 0;
        int ORDINARY = 1;
        int IMMERSED = 2;
    }

    @Immersion
    private int mImmersion = Immersion.UNCHANGED;
    @StatusBarVisible
    private int mStatusBarVisible = StatusBarVisible.AUTO;
    @StatusBarMode
    private int mStatusBarMode = StatusBarMode.UNCHANGED;
    @ColorInt
    private int mStatusBarColor = Color.TRANSPARENT;
    private int mTitleBarHeight = -1;
    private int mTitleBarLayoutRes = 0;
    @ColorInt
    private int mBottomLineColor = Color.TRANSPARENT;
    private int mBottomLineResId = 0;
    private int mBottomLineHeight = 0;
    private boolean mBottomLineOutside = false;
    private int mForegroundLayerLayoutRes = 0;
    private int mBackgroundLayerLayoutRes = 0;
    private int mBackgroundLayerImageRes = 0;
    private int mClickToFinishViewId = 0;

    private Activity mActivity = null;

    private LinearLayout mActionBar;
    private StatusBarView mStatusBar;
    private FrameLayout mTitleBar;
    private View mBottomLine;
    private View mForegroundLayer;
    private View mBackgroundLayer;

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

    @CallSuper
    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ActionBarEx);
        mImmersion = typedArray.getInt(R.styleable.ActionBarEx_ab_immersion, mImmersion);
        mBackgroundLayerLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_backgroundLayerLayout, mBackgroundLayerLayoutRes);
        mBackgroundLayerImageRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_backgroundLayerImageRes, mBackgroundLayerLayoutRes);
        mStatusBarVisible = typedArray.getInt(R.styleable.ActionBarEx_ab_statusBarVisible, mStatusBarVisible);
        mStatusBarMode = typedArray.getInt(R.styleable.ActionBarEx_ab_statusBarMode, mStatusBarMode);
        mStatusBarColor = typedArray.getColor(R.styleable.ActionBarEx_ab_statusBarColor, mStatusBarColor);
        mTitleBarHeight = (int) typedArray.getDimension(R.styleable.ActionBarEx_ab_titleBarHeight, mTitleBarHeight);
        mTitleBarLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_titleBarLayout, mTitleBarLayoutRes);
        mBottomLineHeight = (int) typedArray.getDimension(R.styleable.ActionBarEx_ab_bottomLineHeight, mBottomLineHeight);
        mBottomLineColor = typedArray.getColor(R.styleable.ActionBarEx_ab_bottomLineColor, mBottomLineColor);
        mBottomLineResId = typedArray.getResourceId(R.styleable.ActionBarEx_ab_bottomLineResId, mBottomLineResId);
        mBottomLineOutside = typedArray.getBoolean(R.styleable.ActionBarEx_ab_bottomLineOutside, mBottomLineOutside);
        mForegroundLayerLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_foregroundLayerLayout, mForegroundLayerLayoutRes);
        mClickToFinishViewId = typedArray.getResourceId(R.styleable.ActionBarEx_ab_clickToFinish, mClickToFinishViewId);
        typedArray.recycle();
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
                addViewInLayout(mBackgroundLayer, getChildCount(), makeLayerLayoutParamsMatch(), true);
                actionBarImageView.setImageResource(mBackgroundLayerImageRes);
                actionBarImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }
        // 2 初始ActionBarLayer
        mActionBar = (LinearLayout) inflate(getContext(), R.layout.actionbarex_action_bar, null);
        addViewInLayout(mActionBar, getChildCount(), makeLayerLayoutParamsWrap(), true);
        // 2.1 初始StatusBar
        mStatusBar = mActionBar.findViewById(R.id.actionbarex_status_bar);
        // 2.2 初始TitleBar
        mTitleBar = mActionBar.findViewById(R.id.actionbarex_title_bar);
        mTitleBar.setClickable(true);
        mTitleBar.setFocusable(true);
        mTitleBar.setFocusableInTouchMode(true);
        if (mTitleBarHeight >= 0) {
            mTitleBar.getLayoutParams().height = mTitleBarHeight;
        }
        setTitleBarChild(inflateTitleBar());
        // 2.3 初始BottomLine
        mBottomLine = mActionBar.findViewById(R.id.actionbarex_bottom_line);
        mBottomLine.getLayoutParams().height = mBottomLineHeight;
        if (mBottomLineResId > 0) {
            mBottomLine.setBackgroundResource(mBottomLineResId);
        } else {
            mBottomLine.setBackgroundColor(mBottomLineColor);
        }
        if (mBottomLineOutside) {
            mActionBar.setClipChildren(false);
            setClipChildren(false);
        }
        // 3 初始ForegroundLayer
        if (mForegroundLayerLayoutRes > 0) {
            mForegroundLayer = inflate(getContext(), mForegroundLayerLayoutRes, null);
            addViewInLayout(mForegroundLayer, getChildCount(), makeLayerLayoutParamsMatch(), true);
        }
        performClickToFinish();
    }

    protected View inflateTitleBar() {
        if (getTitleBarRes() > 0) {
            return LayoutInflater.from(getContext()).inflate(getTitleBarRes(), mTitleBar, false);
        }
        return null;
    }

    protected int getTitleBarRes() {
        return mTitleBarLayoutRes;
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mActionBar.measure(widthMeasureSpec, heightMeasureSpec);
        int width = mActionBar.getMeasuredWidth();
        int height;
        if (mBottomLineOutside) {
            height = mStatusBar.getMeasuredHeight() + mTitleBar.getMeasuredHeight();
        } else {
            height = mActionBar.getMeasuredHeight();
        }
        int widthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthSpec, heightSpec);
    }

    public View getBackgroundLayer() {
        return mBackgroundLayer;
    }

    public LinearLayout getActionBar() {
        return mActionBar;
    }

    public StatusBarView getStatusBar() {
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

    public void setTitleBarChild(View titleBarChild) {
        mTitleBar.removeAllViewsInLayout();
        if (titleBarChild != null) {
            ViewGroup.LayoutParams titleBarChildParams = titleBarChild.getLayoutParams();
            if (titleBarChildParams == null) {
                titleBarChildParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
            if (mTitleBarHeight >= 0) {
                titleBarChildParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            }
            mTitleBar.addView(titleBarChild, titleBarChildParams);
        }
    }

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

    public void setImmersion(@Immersion int immersion) {
        mImmersion = immersion;
    }

    public void setStatusBarVisible(@StatusBarVisible int statusBarVisible) {
        mStatusBarVisible = statusBarVisible;
    }

    public void setStatusBarMode(@StatusBarMode int statusBarMode) {
        mStatusBarMode = statusBarMode;
    }

    public void setStatusBarColor(@ColorInt int statusBarColor) {
        mStatusBarColor = statusBarColor;
    }

    public void refresh() {
        refreshImmersion();
        refreshStatusBarVisible();
        refreshStatusBarMode();
        refreshStatusBarColor();
    }

    public void refreshImmersion() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        switch (mImmersion) {
            case Immersion.ORDINARY:
                StatusBarCompat.unTransparent(activity);
                break;
            case Immersion.IMMERSED:
                StatusBarCompat.transparent(activity);
                break;
            case Immersion.UNCHANGED:
            default:
                break;
        }
    }

    public void refreshStatusBarVisible() {
        switch (mStatusBarVisible) {
            case StatusBarVisible.AUTO:
                Activity activity = getActivity();
                if (activity != null) {
                    mStatusBar.setVisibility(StatusBarCompat.isTransparent(activity));
                }
                break;
            case StatusBarVisible.VISIBLE:
                mStatusBar.setVisibility(true);
                break;
            case StatusBarVisible.GONE:
                mStatusBar.setVisibility(false);
                break;
            default:
                break;
        }
    }

    public void refreshStatusBarMode() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        switch (mStatusBarMode) {
            case StatusBarMode.LIGHT:
                StatusBarCompat.setIconMode(activity, false);
                break;
            case StatusBarMode.DARK:
                StatusBarCompat.setIconMode(activity, true);
                break;
            case StatusBarMode.AUTO:
                refreshStatusBarModeAuto();
                break;
            case StatusBarMode.UNCHANGED:
            default:
                break;
        }
    }

    private void refreshStatusBarModeAuto() {
        post(new Runnable() {
            @Override
            public void run() {
                Activity activity = getActivity();
                if (activity != null) {
                    StatusBarCompat.setIconMode(activity, isStatusBarBgLight());
                }
            }
        });
    }

    public void refreshStatusBarColor() {
        mStatusBar.setBackgroundColor(mStatusBarColor);
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (StatusBarCompat.isTransparent(activity) && mStatusBar.isVisibility()) {
            StatusBarCompat.setColor(activity.getWindow(), Color.TRANSPARENT);
        } else {
            StatusBarCompat.setColor(activity.getWindow(), mStatusBarColor);
        }
    }

    public boolean isStatusBarIconDark() {
        Activity activity = getActivity();
        if (activity == null) {
            return false;
        }
        return StatusBarCompat.isIconDark(activity);
    }

    public boolean isStatusBarBgLight() {
        return LuminanceUtils.isLight(calculateStatusBarBgLuminance());
    }

    public double calculateStatusBarBgLuminance() {
        return LuminanceUtils.calcStatusBarLuminance(getActivity());
    }

    public void finishActivity() {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
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
}
