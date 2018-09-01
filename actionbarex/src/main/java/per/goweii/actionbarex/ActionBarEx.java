package per.goweii.actionbarex;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

/**
 * @author Cuizhen
 * @date 2018/8/30-上午11:10
 */
public class ActionBarEx extends FrameLayout {

    protected final Context context;
    protected final DisplayInfoUtils utils;

    protected int actionBarImageRes;
    protected float actionBarBlurRadio = 0;
    protected boolean statusBarDarkMode;
    protected int statusBarColor;
    protected int bottomLineColor;
    protected float bottomLineHeight;
    protected float titleBarHeight;

    protected LinearLayout root;
    protected View view_status_bar;
    protected FrameLayout fl_title_bar;
    protected View view_line;

    protected int titleBarLayoutRes;
    protected View view_title_bar;

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
        initAttrs(attrs);
        initView();
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    public LinearLayout getActionBar() {
        return root;
    }

    public View getStatusBar() {
        return view_status_bar;
    }

    public FrameLayout getTitleBar() {
        return fl_title_bar;
    }

    public View getBottomLine() {
        return view_line;
    }

    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ActionBarEx);

        int statusBarColorDef = ContextCompat.getColor(context, R.color.view_status_bar);
        int titleBarHeightDef = utils.dp2px(48);
        int bottomLineHeightDef = utils.dp2px(1);
        int bottomLineColorDef = ContextCompat.getColor(context, R.color.line);

        actionBarImageRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_action_bar_image_res, 0);
        actionBarBlurRadio = typedArray.getInteger(R.styleable.ActionBarEx_ab_action_bar_blur_radio, 0);
        statusBarDarkMode = typedArray.getInt(R.styleable.ActionBarEx_ab_status_bar_mode, 0) == 1;
        statusBarColor = typedArray.getColor(R.styleable.ActionBarEx_ab_status_bar_color, statusBarColorDef);
        titleBarLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_title_bar_layout, 0);
        titleBarHeight = typedArray.getDimension(R.styleable.ActionBarEx_ab_title_bar_height, titleBarHeightDef);
        bottomLineHeight = typedArray.getDimension(R.styleable.ActionBarEx_ab_bottom_line_height, bottomLineHeightDef);
        bottomLineColor = typedArray.getColor(R.styleable.ActionBarEx_ab_bottom_line_color, bottomLineColorDef);

        typedArray.recycle();
    }

    protected void initView() {
        BlurView blurView = null;

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
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
            StatusBarUtils.translucentStatusBar(activity);
            StatusBarUtils.setStatusBarMode(activity, statusBarDarkMode);

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

        root = (LinearLayout) inflate(getContext(), R.layout.action_bar, null);
        view_status_bar = root.findViewById(R.id.view_status_bar);
        view_line = root.findViewById(R.id.view_line);
        fl_title_bar = root.findViewById(R.id.fl_title_bar);

        if (titleBarLayoutRes > 0) {
            view_title_bar = inflate(getContext(), titleBarLayoutRes, null);
        }

        ViewGroup.LayoutParams view_status_bar_params = view_status_bar.getLayoutParams();
        view_status_bar_params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        view_status_bar_params.height = utils.getStatusBarHeight();
        view_status_bar.setLayoutParams(view_status_bar_params);
        view_status_bar.setBackgroundColor(statusBarColor);

        ViewGroup.LayoutParams view_line_params = view_line.getLayoutParams();
        view_line_params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        view_line_params.height = (int) bottomLineHeight;
        view_line.setLayoutParams(view_line_params);
        view_line.setBackgroundColor(bottomLineColor);

        ViewGroup.LayoutParams fl_title_bar_params = fl_title_bar.getLayoutParams();
        fl_title_bar_params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        fl_title_bar_params.height = (int) titleBarHeight;
        fl_title_bar.setLayoutParams(fl_title_bar_params);

        if (view_title_bar != null) {
            initTitleBar();
            fl_title_bar.addView(view_title_bar);
        }
        if (blurView != null) {
            blurView.addView(root);
            addView(blurView);
        } else {
            if (actionBarImageRes > 0) {
                ImageView actionBarImageView = new ImageView(context);
                actionBarImageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, getActionBarHeight()));
                actionBarImageView.setImageResource(actionBarImageRes);
                actionBarImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                addView(actionBarImageView);
            }
            addView(root);
        }
    }

    protected void initTitleBar() {
    }

    public <V extends View> V getView(@IdRes int id) {
        if (view_title_bar == null){
            return null;
        }
        if (views == null) {
            views = new SparseArray<>();
        }
        View view = views.get(id);
        if (view == null) {
            view = view_title_bar.findViewById(id);
            views.put(id, view);
        }
        return (V) view;
    }

    public int getActionBarHeight() {
        return (int) (utils.getStatusBarHeight() + titleBarHeight + bottomLineHeight);
    }

    public int getStatusBarHeight() {
        return utils.getStatusBarHeight();
    }

    public int getTitleBarHeight() {
        return (int) titleBarHeight;
    }

    public int getBottomHeight() {
        return (int) bottomLineHeight;
    }

    public void setOnTitleBarClickListener(final OnTitleBarClickListener onTitleBarClickListener) {
        fl_title_bar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTitleBarClickListener != null) {
                    onTitleBarClickListener.onClick();
                }
            }
        });
    }
}
