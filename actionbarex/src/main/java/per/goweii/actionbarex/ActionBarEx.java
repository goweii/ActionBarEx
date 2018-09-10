package per.goweii.actionbarex;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import per.goweii.actionbarex.listener.OnTitleBarClickListener;
import per.goweii.actionbarex.statusbar.StatusBarUtils;

/**
 * 高拓展性和定制性的ActionBar
 * 在这里定义几个概念：
 * ActionBar：这里指包含下面三个的整体
 * StatusBar：系统状态栏
 * TitleBar：显示标题和返回按钮等的View，位于StatusBar和BottomLine之间，可自定义布局
 * BottomLine：最下面的一条分割线
 *
 * @author Cuizhen
 * @date 2018/8/30-上午11:10
 */
public class ActionBarEx extends FrameLayout {

    protected final Context context;
    protected final DisplayInfoUtils utils;

    private int actionBarImageRes;
    private float actionBarBlurRadio = 0;
    private boolean statusBarDarkMode;
    private int statusBarColor;
    private int bottomLineColor;
    private int statusBarHeight;
    private int titleBarHeight;
    private int bottomLineHeight;

    private LinearLayout mActionBar;
    private View mStatusBar;
    private FrameLayout mTitleBar;
    private View mBottomLine;

    private int titleBarLayoutRes;
    private View mTitleBarChild;

    private SparseArray<View> views = null;

    public ActionBarEx(Context context) {
        this(context, null);
    }

    public ActionBarEx(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionBarEx(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        utils = DisplayInfoUtils.getInstance(context);
        statusBarHeight = utils.getStatusBarHeight();
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        initAttrs(attrs);
        initView();
    }

    @Override
    public boolean isInEditMode() {
        return true;
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
        return statusBarHeight + titleBarHeight + bottomLineHeight;
    }

    public int getStatusBarHeight() {
        return statusBarHeight;
    }

    public int getTitleBarHeight() {
        return titleBarHeight;
    }

    public int getBottomHeight() {
        return bottomLineHeight;
    }

    public void setOnTitleBarClickListener(final OnTitleBarClickListener onTitleBarClickListener) {
        mTitleBar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTitleBarClickListener != null) {
                    onTitleBarClickListener.onClick();
                }
            }
        });
    }

    /**
     * 初始化布局文件传入的配置
     * 子类可重写并初始化自己定义的属性配置，必须保证在第一行调用super方法
     *
     * @param attrs AttributeSet
     */
    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ActionBarEx);

        actionBarImageRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_action_bar_image_res, 0);
        actionBarBlurRadio = typedArray.getInteger(R.styleable.ActionBarEx_ab_action_bar_blur_radio, 0);
        statusBarDarkMode = typedArray.getInt(R.styleable.ActionBarEx_ab_status_bar_mode, 0) == 1;
        statusBarColor = typedArray.getColor(R.styleable.ActionBarEx_ab_status_bar_color, Config.STATUS_BAR_COLOR_DEF);
        titleBarLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_title_bar_layout, 0);
        titleBarHeight = (int) typedArray.getDimension(R.styleable.ActionBarEx_ab_title_bar_height, utils.dp2px(Config.TITLE_BAR_HEIGHT_DEF));
        bottomLineHeight = (int) typedArray.getDimension(R.styleable.ActionBarEx_ab_bottom_line_height, Config.BOTTOM_LINE_HEIGHT_DEF);
        bottomLineColor = typedArray.getColor(R.styleable.ActionBarEx_ab_bottom_line_color, Config.BOTTOM_LINE_COLOR_DEF);

        typedArray.recycle();
    }

    /**
     * 初始化子TitleBar
     *
     * @return TitleBarChild
     */
    protected View inflateTitleBar() {
        if (titleBarLayoutRes > 0) {
            return inflate(getContext(), titleBarLayoutRes, null);
        }
        return null;
    }

    private void initView() {
        BlurView blurView = null;

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            initWindow(activity);
            if (actionBarBlurRadio > 0) {
                View decorView = activity.getWindow().getDecorView();
                ViewGroup rootView = decorView.findViewById(android.R.id.content);
                Drawable windowBackground = decorView.getBackground();
                blurView = new BlurView(context);
                blurView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                blurView.setupWith(rootView)
                        .blurRadius(actionBarBlurRadio)
                        .setOverlayColor(getDrawingCacheBackgroundColor())
                        .windowBackground(windowBackground)
                        .blurAlgorithm(new RenderScriptBlur(context));
            }
        }

        mActionBar = (LinearLayout) inflate(getContext(), R.layout.action_bar, null);
        mStatusBar = mActionBar.findViewById(R.id.status_bar);
        mTitleBar = mActionBar.findViewById(R.id.title_bar);
        mBottomLine = mActionBar.findViewById(R.id.bottom_line);

        ViewGroup.LayoutParams actionBarParams = mActionBar.getLayoutParams();
        actionBarParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        actionBarParams.height = getActionBarHeight();
        mActionBar.setLayoutParams(actionBarParams);

        ViewGroup.LayoutParams statusBarParams = mStatusBar.getLayoutParams();
        statusBarParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        statusBarParams.height = statusBarHeight;
        mStatusBar.setLayoutParams(statusBarParams);
        mStatusBar.setBackgroundColor(statusBarColor);

        ViewGroup.LayoutParams titleBarParams = mTitleBar.getLayoutParams();
        titleBarParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        titleBarParams.height = (int) titleBarHeight;
        mTitleBar.setLayoutParams(titleBarParams);

        ViewGroup.LayoutParams bottomLineParams = mBottomLine.getLayoutParams();
        bottomLineParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        bottomLineParams.height = (int) bottomLineHeight;
        mBottomLine.setLayoutParams(bottomLineParams);
        mBottomLine.setBackgroundColor(bottomLineColor);

        mTitleBarChild = inflateTitleBar();

        if (mTitleBarChild != null) {
            mTitleBar.addView(mTitleBarChild);
        }
        if (blurView != null) {
            blurView.addView(mActionBar);
            addView(blurView);
        } else {
            if (actionBarImageRes > 0) {
                ImageView actionBarImageView = new ImageView(context);
                actionBarImageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, getActionBarHeight()));
                actionBarImageView.setImageResource(actionBarImageRes);
                actionBarImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                addView(actionBarImageView);
            }
            addView(mActionBar);
        }
    }

    /**
     * 初始化Activity相关
     * 透明状态栏，改变状态栏图标颜色模式， 隐藏默认的ActionBar
     *
     * @param activity Activity
     */
    private void initWindow(Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
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
        StatusBarUtils.transparentStatusBar(activity);
        StatusBarUtils.setStatusBarIconMode(activity, statusBarDarkMode);
    }
}
