package per.goweii.actionbarex;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import per.goweii.actionbarex.statusbar.StatusBarUtils;

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

    protected final Context mContext;
    protected final DisplayInfoUtils mDisplayInfoUtils;

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
    private int mBottomLineHeight;
    private int mForegroundLayerLayoutRes;

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
        this.mContext = context;
        mDisplayInfoUtils = DisplayInfoUtils.getInstance(context);
        mStatusBarHeight = mDisplayInfoUtils.getStatusBarHeight();
        initAttrs(attrs);
        makeImmersion();
        initView();
    }

    @Override
    public boolean isInEditMode() {
        return true;
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
     * 获取一个TitleBar中的View并缓存，以便下次获取，避免频繁调用findViewById
     *
     * @param id View的id
     * @return View
     */
    public <V extends View> V getView(@IdRes int id) {
        if (mTitleBarChild == null) {
            return null;
        }
        if (views == null) {
            views = new SparseArray<>();
        }
        View view = views.get(id);
        if (view == null) {
            view = mTitleBarChild.findViewById(id);
            views.put(id, view);
        }
        return (V) view;
    }

    public int getActionBarHeight() {
        return mStatusBarHeight + mTitleBarHeight + mBottomLineHeight;
    }

    public int getStatusBarHeight() {
        return mStatusBarHeight;
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
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.ActionBarEx);

        mAutoImmersion = typedArray.getBoolean(R.styleable.ActionBarEx_ab_auto_immersion, Config.AUTO_IMMERSION);
        mBackgroundLayerLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_background_layer_layout, 0);
        mBackgroundLayerImageRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_background_layer_image_res, 0);
        mStatusBarVisible = typedArray.getBoolean(R.styleable.ActionBarEx_ab_status_bar_visible, true);
        mStatusBarDarkMode = typedArray.getInt(R.styleable.ActionBarEx_ab_status_bar_mode, 0) == 1;
        mStatusBarColor = typedArray.getColor(R.styleable.ActionBarEx_ab_status_bar_color, Config.STATUS_BAR_COLOR_DEF);
        mTitleBarLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_title_bar_layout, 0);
        mTitleBarHeight = (int) typedArray.getDimension(R.styleable.ActionBarEx_ab_title_bar_height, mDisplayInfoUtils.dp2px(Config.TITLE_BAR_HEIGHT_DEF));
        mBottomLineHeight = (int) typedArray.getDimension(R.styleable.ActionBarEx_ab_bottom_line_height, Config.BOTTOM_LINE_HEIGHT_DEF);
        mBottomLineColor = typedArray.getColor(R.styleable.ActionBarEx_ab_bottom_line_color, Config.BOTTOM_LINE_COLOR_DEF);
        mForegroundLayerLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_foreground_layer_layout, 0);

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
        // 1 初始化背景图
        if (mBackgroundLayerLayoutRes > 0){
            mBackgroundLayer = inflate(getContext(), mBackgroundLayerLayoutRes, null);
            mBackgroundLayer.setLayoutParams(makeLayerLayoutParams());
            addView(mBackgroundLayer);
        } else {
            if (mBackgroundLayerImageRes > 0) {
                ImageView actionBarImageView = new ImageView(mContext);
                actionBarImageView.setLayoutParams(makeLayerLayoutParams());
                actionBarImageView.setImageResource(mBackgroundLayerImageRes);
                actionBarImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                addView(actionBarImageView);
            }
        }

        // 2 初始ContentLayer（ActionBar）
        mActionBar = (LinearLayout) inflate(getContext(), R.layout.action_bar, null);
        mActionBar.setLayoutParams(makeLayoutParamsWithHeight(getActionBarHeight()));
        // 2.1 初始StatusBar
        mStatusBar = mActionBar.findViewById(R.id.status_bar);
        mStatusBar.setLayoutParams(makeLayoutParamsWithHeight(mStatusBarHeight));
        mStatusBar.setBackgroundColor(mStatusBarColor);
        mStatusBar.setVisibility(mStatusBarVisible ? VISIBLE : GONE);
        // 2.2 初始TitleBar
        mTitleBar = mActionBar.findViewById(R.id.title_bar);
        mTitleBar.setClickable(true);
        mTitleBar.setFocusable(true);
        mTitleBar.setFocusableInTouchMode(true);
        mTitleBar.setLayoutParams(makeLayoutParamsWithHeight(mTitleBarHeight));
        mTitleBarChild = inflateTitleBar();
        if (mTitleBarChild != null) {
            mTitleBar.addView(mTitleBarChild);
        }
        // 2.3 初始BottomLine
        mBottomLine = mActionBar.findViewById(R.id.bottom_line);
        mBottomLine.setLayoutParams(makeLayoutParamsWithHeight(mBottomLineHeight));
        mBottomLine.setBackgroundColor(mBottomLineColor);
        addView(mActionBar);

        // 3 初始ForegroundLayer
        if (mForegroundLayerLayoutRes > 0) {
            mForegroundLayer = inflate(getContext(), mForegroundLayerLayoutRes, null);
            mForegroundLayer.setLayoutParams(makeLayerLayoutParams());
            addView(mForegroundLayer);
        }
    }

    private LayoutParams makeLayerLayoutParams(){
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getActionBarHeight());
    }

    private LinearLayout.LayoutParams makeLayoutParamsWithHeight(int height) {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
    }

    /**
     * 设置沉浸模式
     */
    private void makeImmersion() {
        if (!mAutoImmersion) {
            return;
        }
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        setSystemStatusBar(activity);
        hintSystemActionBar(activity);
    }

    /**
     * 透明状态栏，改变状态栏图标颜色模式
     */
    private void setSystemStatusBar(@NonNull Activity activity) {
        StatusBarUtils.transparentStatusBar(activity);
        StatusBarUtils.setStatusBarIconMode(activity, mStatusBarDarkMode);
    }

    /**
     * 隐藏默认的ActionBar
     */
    private void hintSystemActionBar(@NonNull Activity activity) {
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
        Context context = getContext();
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            if (baseContext instanceof Activity) {
                return (Activity) baseContext;
            }
        }
        return null;
    }
}
