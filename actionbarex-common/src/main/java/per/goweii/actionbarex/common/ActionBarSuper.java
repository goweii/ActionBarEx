package per.goweii.actionbarex.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import per.goweii.actionbarex.ActionBarEx;

/**
 * @author Cuizhen
 * @date 2018/8/30-上午11:10
 */
public final class ActionBarSuper extends ActionBarEx {

    @IntDef({TextStyle.NORMAL, TextStyle.BOLD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextStyle {
        int NORMAL = 0;
        int BOLD = 1;
    }

    @IntDef({TitleGravity.CENTER, TitleGravity.LEFT, TitleGravity.RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TitleGravity {
        int CENTER = 0;
        int LEFT = 1;
        int RIGHT = 2;
    }

    @TitleGravity
    private int titleGravity;
    @TextStyle
    private int titleTextStyle;
    private String titleText;
    private float titleTextSize;
    private int titleTextColor;
    private int titleTextMaxWidth;
    private int[] titlePadding;
    private int[] titleMargin;
    @TextStyle
    private int subtitleTextStyle;
    private String subtitleText;
    private float subtitleTextSize;
    private int subtitleTextColor;
    private int subtitleTextMaxWidth;
    private int[] subtitlePadding;
    private int[] subtitleMargin;

    @TextStyle
    private int textStyle;
    private int textColor;
    private float textSize;
    private int[] textPadding;
    private int[] textMargin;
    private int iconColor;
    private int[] iconPadding;
    private int[] iconMargin;

    @TextStyle
    private int leftTextStyle;
    @TextStyle
    private int[] leftTextStyles;
    private boolean[] leftTextClickToFinish;
    private boolean[] leftIconClickToFinish;
    private int[] leftPadding;
    private String[] leftTexts;
    private int leftTextColor;
    private int[] leftTextColors;
    private float leftTextSize;
    private float[] leftTextSizes;
    private int[] leftTextPadding;
    private int[][] leftTextPaddings;
    private int[] leftTextMargin;
    private int[][] leftTextMargins;
    private int[] leftIcons;
    private int leftIconColor;
    private int[] leftIconColors;
    private int[] leftIconPadding;
    private int[][] leftIconPaddings;
    private int[] leftIconMargin;
    private int[][] leftIconMargins;

    @TextStyle
    private int rightTextStyle;
    @TextStyle
    private int[] rightTextStyles;
    private boolean[] rightTextClickToFinish;
    private boolean[] rightIconClickToFinish;
    private int[] rightPadding;
    private String[] rightTexts;
    private int rightTextColor;
    private int[] rightTextColors;
    private float rightTextSize;
    private float[] rightTextSizes;
    private int[] rightTextPadding;
    private int[][] rightTextPaddings;
    private int[] rightTextMargin;
    private int[][] rightTextMargins;
    private int[] rightIcons;
    private int rightIconColor;
    private int[] rightIconColors;
    private int[] rightIconPadding;
    private int[][] rightIconPaddings;
    private int[] rightIconMargin;
    private int[][] rightIconMargins;

    private FrameLayout titleBarChild;
    private TextView titleTextView;
    private TextView subtitleTextView;
    private ActionView[] leftActionViews;
    private ActionView[] rightActionViews;

    public ActionBarSuper(Context context) {
        this(context, null);
    }

    public ActionBarSuper(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionBarSuper(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public FrameLayout getTitleBarChild() {
        return titleBarChild;
    }

    @Override
    protected void initAttrs(AttributeSet attrs) {
        super.initAttrs(attrs);

        float titleTextSizeDef = getContext().getResources().getDimension(R.dimen.title_bar_title_text_size_def);
        float subtitleTextSizeDef = getContext().getResources().getDimension(R.dimen.title_bar_subtitle_text_size_def);
        int titleTextColorDef = ContextCompat.getColor(getContext(), R.color.title_bar_title_text_color_def);
        int subtitleTextColorDef = ContextCompat.getColor(getContext(), R.color.title_bar_subtitle_text_color_def);
        float titleTextMaxWidthDef = getContext().getResources().getDimension(R.dimen.title_bar_title_text_max_width_def);
        float subtitleTextMaxWidthDef = getContext().getResources().getDimension(R.dimen.title_bar_subtitle_text_max_width_def);
        int textColorDef = ContextCompat.getColor(getContext(), R.color.title_bar_text_color_def);
        float textSizeDef = getContext().getResources().getDimension(R.dimen.title_bar_text_size_def);
        float textPaddingLeftDef = getContext().getResources().getDimension(R.dimen.title_bar_text_padding_left_def);
        float textPaddingRightDef = getContext().getResources().getDimension(R.dimen.title_bar_text_padding_right_def);
        int iconColorDef = ContextCompat.getColor(getContext(), R.color.title_bar_icon_color_def);
        float iconPaddingDef = getContext().getResources().getDimension(R.dimen.title_bar_icon_padding_def);

        titlePadding = new int[4];
        titleMargin = new int[4];
        subtitlePadding = new int[4];
        subtitleMargin = new int[4];

        textPadding = new int[4];
        textMargin = new int[4];
        iconPadding = new int[4];
        iconMargin = new int[4];

        leftPadding = new int[4];

        leftTextPadding = new int[4];
        leftTextMargin = new int[4];
        leftIconPadding = new int[4];
        leftIconMargin = new int[4];

        leftTextStyles = new int[5];
        leftTextClickToFinish = new boolean[5];
        leftIconClickToFinish = new boolean[5];
        leftTexts = new String[5];
        leftTextColors = new int[5];
        leftTextSizes = new float[5];
        leftTextPaddings = new int[5][4];
        leftTextMargins = new int[5][4];
        leftIcons = new int[5];
        leftIconColors = new int[5];
        leftIconPaddings = new int[5][4];
        leftIconMargins = new int[5][4];

        rightPadding = new int[4];

        rightTextPadding = new int[4];
        rightTextMargin = new int[4];
        rightIconPadding = new int[4];
        rightIconMargin = new int[4];

        rightTextStyles = new int[5];
        rightTextClickToFinish = new boolean[5];
        rightIconClickToFinish = new boolean[5];
        rightTexts = new String[5];
        rightTextColors = new int[5];
        rightTextSizes = new float[5];
        rightTextPaddings = new int[5][4];
        rightTextMargins = new int[5][4];
        rightIcons = new int[5];
        rightIconColors = new int[5];
        rightIconPaddings = new int[5][4];
        rightIconMargins = new int[5][4];

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ActionBarSuper);
        titleGravity = typedArray.getInt(R.styleable.ActionBarSuper_absuper_titleGravity, TitleGravity.CENTER);
        titleTextStyle = typedArray.getInt(R.styleable.ActionBarSuper_absuper_titleTextStyle, TextStyle.NORMAL);
        titleText = typedArray.getString(R.styleable.ActionBarSuper_absuper_titleText);
        titleTextSize = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titleTextSize, titleTextSizeDef);
        titleTextColor = typedArray.getColor(R.styleable.ActionBarSuper_absuper_titleTextColor, titleTextColorDef);
        titleTextMaxWidth = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titleTextMaxWidth, titleTextMaxWidthDef);
        int titlePaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titlePadding, -1);
        titlePadding[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titlePaddingLeft, titlePaddingTemp >= 0 ? titlePaddingTemp : 0);
        titlePadding[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titlePaddingTop, titlePaddingTemp >= 0 ? titlePaddingTemp : 0);
        titlePadding[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titlePaddingRight, titlePaddingTemp >= 0 ? titlePaddingTemp : 0);
        titlePadding[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titlePaddingBottom, titlePaddingTemp >= 0 ? titlePaddingTemp : 0);
        int titleMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titleMargin, -1);
        titleMargin[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titleMarginLeft, titleMarginTemp >= 0 ? titleMarginTemp : 0);
        titleMargin[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titleMarginTop, titleMarginTemp >= 0 ? titleMarginTemp : 0);
        titleMargin[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titleMarginRight, titleMarginTemp >= 0 ? titleMarginTemp : 0);
        titleMargin[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_titleMarginBottom, titleMarginTemp >= 0 ? titleMarginTemp : 0);
        subtitleTextStyle = typedArray.getInt(R.styleable.ActionBarSuper_absuper_subtitleTextStyle, TextStyle.NORMAL);
        subtitleText = typedArray.getString(R.styleable.ActionBarSuper_absuper_subtitleText);
        subtitleTextSize = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitleTextSize, subtitleTextSizeDef);
        subtitleTextColor = typedArray.getColor(R.styleable.ActionBarSuper_absuper_subtitleTextColor, subtitleTextColorDef);
        subtitleTextMaxWidth = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitleTextMaxWidth, subtitleTextMaxWidthDef);
        int subtitlePaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitlePadding, -1);
        subtitlePadding[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitlePaddingLeft, subtitlePaddingTemp >= 0 ? subtitlePaddingTemp : 0);
        subtitlePadding[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitlePaddingTop, subtitlePaddingTemp >= 0 ? subtitlePaddingTemp : 0);
        subtitlePadding[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitlePaddingRight, subtitlePaddingTemp >= 0 ? subtitlePaddingTemp : 0);
        subtitlePadding[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitlePaddingBottom, subtitlePaddingTemp >= 0 ? subtitlePaddingTemp : 0);
        int subtitleMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitleMargin, -1);
        subtitleMargin[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitleMarginLeft, subtitleMarginTemp >= 0 ? subtitleMarginTemp : 0);
        subtitleMargin[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitleMarginTop, subtitleMarginTemp >= 0 ? subtitleMarginTemp : 0);
        subtitleMargin[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitleMarginRight, subtitleMarginTemp >= 0 ? subtitleMarginTemp : 0);
        subtitleMargin[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_subtitleMarginBottom, subtitleMarginTemp >= 0 ? subtitleMarginTemp : 0);

        textStyle = typedArray.getInt(R.styleable.ActionBarSuper_absuper_textStyle, TextStyle.NORMAL);
        textColor = typedArray.getColor(R.styleable.ActionBarSuper_absuper_textColor, textColorDef);
        textSize = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textSize, textSizeDef);
        int textPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textPadding, -1);
        textPadding[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textPaddingLeft, textPaddingTemp >= 0 ? textPaddingTemp : textPaddingLeftDef);
        textPadding[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textPaddingTop, textPaddingTemp >= 0 ? textPaddingTemp : 0);
        textPadding[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textPaddingRight, textPaddingTemp >= 0 ? textPaddingTemp : textPaddingRightDef);
        textPadding[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textPaddingBottom, textPaddingTemp >= 0 ? textPaddingTemp : 0);
        int textMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        textMargin[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMarginLeft, textMarginTemp >= 0 ? textMarginTemp : 0);
        textMargin[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMarginTop, textMarginTemp >= 0 ? textMarginTemp : 0);
        textMargin[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMarginRight, textMarginTemp >= 0 ? textMarginTemp : 0);
        textMargin[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMarginBottom, textMarginTemp >= 0 ? textMarginTemp : 0);
        iconColor = typedArray.getColor(R.styleable.ActionBarSuper_absuper_iconColor, iconColorDef);
        int iconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_iconPadding, -1);
        iconPadding[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_iconPaddingLeft, iconPaddingTemp >= 0 ? iconPaddingTemp : iconPaddingDef);
        iconPadding[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_iconPaddingTop, iconPaddingTemp >= 0 ? iconPaddingTemp : iconPaddingDef);
        iconPadding[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_iconPaddingRight, iconPaddingTemp >= 0 ? iconPaddingTemp : iconPaddingDef);
        iconPadding[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_iconPaddingBottom, iconPaddingTemp >= 0 ? iconPaddingTemp : iconPaddingDef);
        int iconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_iconMargin, -1);
        iconMargin[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_iconMarginLeft, iconMarginTemp >= 0 ? iconMarginTemp : 0);
        iconMargin[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_iconMarginTop, iconMarginTemp >= 0 ? iconMarginTemp : 0);
        iconMargin[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_iconMarginRight, iconMarginTemp >= 0 ? iconMarginTemp : 0);
        iconMargin[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_iconMarginBottom, iconMarginTemp >= 0 ? iconMarginTemp : 0);

        int leftPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftPadding, -1);
        leftPadding[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftPaddingLeft, leftPaddingTemp >= 0 ? leftPaddingTemp : 0);
        leftPadding[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftPaddingTop, leftPaddingTemp >= 0 ? leftPaddingTemp : 0);
        leftPadding[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftPaddingRight, leftPaddingTemp >= 0 ? leftPaddingTemp : 0);
        leftPadding[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftPaddingBottom, leftPaddingTemp >= 0 ? leftPaddingTemp : 0);

        leftTextStyle = typedArray.getInt(R.styleable.ActionBarSuper_absuper_leftTextStyle, textStyle);
        leftTextColor = typedArray.getColor(R.styleable.ActionBarSuper_absuper_leftTextColor, textColor);
        leftTextSize = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftTextSize, textSize);
        int leftTextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftTextPadding, -1);
        leftTextPadding[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftTextPaddingLeft, leftTextPaddingTemp >= 0 ? leftTextPaddingTemp : textPadding[0]);
        leftTextPadding[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftTextPaddingTop, leftTextPaddingTemp >= 0 ? leftTextPaddingTemp : textPadding[1]);
        leftTextPadding[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftTextPaddingRight, leftTextPaddingTemp >= 0 ? leftTextPaddingTemp : textPadding[2]);
        leftTextPadding[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftTextPaddingBottom, leftTextPaddingTemp >= 0 ? leftTextPaddingTemp : textPadding[3]);
        int leftTextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        leftTextMargin[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftTextMarginLeft, leftTextMarginTemp >= 0 ? leftTextMarginTemp : textMargin[0]);
        leftTextMargin[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftTextMarginTop, leftTextMarginTemp >= 0 ? leftTextMarginTemp : textMargin[1]);
        leftTextMargin[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftTextMarginRight, leftTextMarginTemp >= 0 ? leftTextMarginTemp : textMargin[2]);
        leftTextMargin[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftTextMarginBottom, leftTextMarginTemp >= 0 ? leftTextMarginTemp : textMargin[3]);
        leftIconColor = typedArray.getColor(R.styleable.ActionBarSuper_absuper_leftIconColor, iconColor);
        int leftIconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftIconPadding, -1);
        leftIconPadding[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftIconPaddingLeft, leftIconPaddingTemp >= 0 ? leftIconPaddingTemp : iconPadding[0]);
        leftIconPadding[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftIconPaddingTop, leftIconPaddingTemp >= 0 ? leftIconPaddingTemp : iconPadding[1]);
        leftIconPadding[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftIconPaddingRight, leftIconPaddingTemp >= 0 ? leftIconPaddingTemp : iconPadding[2]);
        leftIconPadding[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftIconPaddingBottom, leftIconPaddingTemp >= 0 ? leftIconPaddingTemp : iconPadding[3]);
        int leftIconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftIconMargin, -1);
        leftIconMargin[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftIconMarginLeft, leftIconMarginTemp >= 0 ? leftIconMarginTemp : iconMargin[0]);
        leftIconMargin[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftIconMarginTop, leftIconMarginTemp >= 0 ? leftIconMarginTemp : iconMargin[1]);
        leftIconMargin[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftIconMarginRight, leftIconMarginTemp >= 0 ? leftIconMarginTemp : iconMargin[2]);
        leftIconMargin[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_leftIconMarginBottom, leftIconMarginTemp >= 0 ? leftIconMarginTemp : iconMargin[3]);

        leftTextStyles[0] = typedArray.getInt(R.styleable.ActionBarSuper_absuper_left1TextStyle, leftTextStyle);
        leftTextClickToFinish[0] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_left1TextClickToFinish, false);
        leftIconClickToFinish[0] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_left1IconClickToFinish, false);
        leftTexts[0] = typedArray.getString(R.styleable.ActionBarSuper_absuper_left1Text);
        leftTextColors[0] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_left1TextColor, leftTextColor);
        leftTextSizes[0] = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1TextSize, leftTextSize);
        int left1TextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1TextPadding, -1);
        leftTextPaddings[0][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1TextPaddingLeft, left1TextPaddingTemp >= 0 ? left1TextPaddingTemp : leftTextPadding[0]);
        leftTextPaddings[0][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1TextPaddingTop, left1TextPaddingTemp >= 0 ? left1TextPaddingTemp : leftTextPadding[1]);
        leftTextPaddings[0][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1TextPaddingRight, left1TextPaddingTemp >= 0 ? left1TextPaddingTemp : leftTextPadding[2]);
        leftTextPaddings[0][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1TextPaddingBottom, left1TextPaddingTemp >= 0 ? left1TextPaddingTemp : leftTextPadding[3]);
        int left1TextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        leftTextMargins[0][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1TextMarginLeft, left1TextMarginTemp >= 0 ? left1TextMarginTemp : leftTextMargin[0]);
        leftTextMargins[0][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1TextMarginTop, left1TextMarginTemp >= 0 ? left1TextMarginTemp : leftTextMargin[1]);
        leftTextMargins[0][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1TextMarginRight, left1TextMarginTemp >= 0 ? left1TextMarginTemp : leftTextMargin[2]);
        leftTextMargins[0][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1TextMarginBottom, left1TextMarginTemp >= 0 ? left1TextMarginTemp : leftTextMargin[3]);
        leftIcons[0] = typedArray.getResourceId(R.styleable.ActionBarSuper_absuper_left1Icon, 0);
        leftIconColors[0] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_left1IconColor, leftIconColor);
        int left1IconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1IconPadding, -1);
        leftIconPaddings[0][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1IconPaddingLeft, left1IconPaddingTemp >= 0 ? left1IconPaddingTemp : leftIconPadding[0]);
        leftIconPaddings[0][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1IconPaddingTop, left1IconPaddingTemp >= 0 ? left1IconPaddingTemp : leftIconPadding[1]);
        leftIconPaddings[0][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1IconPaddingRight, left1IconPaddingTemp >= 0 ? left1IconPaddingTemp : leftIconPadding[2]);
        leftIconPaddings[0][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1IconPaddingBottom, left1IconPaddingTemp >= 0 ? left1IconPaddingTemp : leftIconPadding[3]);
        int left1IconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1IconMargin, -1);
        leftIconMargins[0][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1IconMarginLeft, left1IconMarginTemp >= 0 ? left1IconMarginTemp : leftIconMargin[0]);
        leftIconMargins[0][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1IconMarginTop, left1IconMarginTemp >= 0 ? left1IconMarginTemp : leftIconMargin[1]);
        leftIconMargins[0][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1IconMarginRight, left1IconMarginTemp >= 0 ? left1IconMarginTemp : leftIconMargin[2]);
        leftIconMargins[0][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left1IconMarginBottom, left1IconMarginTemp >= 0 ? left1IconMarginTemp : leftIconMargin[3]);

        leftTextStyles[1] = typedArray.getInt(R.styleable.ActionBarSuper_absuper_left2TextStyle, leftTextStyle);
        leftTextClickToFinish[1] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_left2TextClickToFinish, false);
        leftIconClickToFinish[1] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_left2IconClickToFinish, false);
        leftTexts[1] = typedArray.getString(R.styleable.ActionBarSuper_absuper_left2Text);
        leftTextColors[1] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_left2TextColor, leftTextColor);
        leftTextSizes[1] = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2TextSize, leftTextSize);
        int left2TextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2TextPadding, -1);
        leftTextPaddings[1][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2TextPaddingLeft, left2TextPaddingTemp >= 0 ? left2TextPaddingTemp : leftTextPadding[0]);
        leftTextPaddings[1][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2TextPaddingTop, left2TextPaddingTemp >= 0 ? left2TextPaddingTemp : leftTextPadding[1]);
        leftTextPaddings[1][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2TextPaddingRight, left2TextPaddingTemp >= 0 ? left2TextPaddingTemp : leftTextPadding[2]);
        leftTextPaddings[1][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2TextPaddingBottom, left2TextPaddingTemp >= 0 ? left2TextPaddingTemp : leftTextPadding[3]);
        int left2TextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        leftTextMargins[1][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2TextMarginLeft, left2TextMarginTemp >= 0 ? left2TextMarginTemp : leftTextMargin[0]);
        leftTextMargins[1][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2TextMarginTop, left2TextMarginTemp >= 0 ? left2TextMarginTemp : leftTextMargin[1]);
        leftTextMargins[1][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2TextMarginRight, left2TextMarginTemp >= 0 ? left2TextMarginTemp : leftTextMargin[2]);
        leftTextMargins[1][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2TextMarginBottom, left2TextMarginTemp >= 0 ? left2TextMarginTemp : leftTextMargin[3]);
        leftIcons[1] = typedArray.getResourceId(R.styleable.ActionBarSuper_absuper_left2Icon, 0);
        leftIconColors[1] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_left2IconColor, leftIconColor);
        int left2IconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2IconPadding, -1);
        leftIconPaddings[1][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2IconPaddingLeft, left2IconPaddingTemp >= 0 ? left2IconPaddingTemp : leftIconPadding[0]);
        leftIconPaddings[1][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2IconPaddingTop, left2IconPaddingTemp >= 0 ? left2IconPaddingTemp : leftIconPadding[1]);
        leftIconPaddings[1][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2IconPaddingRight, left2IconPaddingTemp >= 0 ? left2IconPaddingTemp : leftIconPadding[2]);
        leftIconPaddings[1][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2IconPaddingBottom, left2IconPaddingTemp >= 0 ? left2IconPaddingTemp : leftIconPadding[3]);
        int left2IconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2IconMargin, -1);
        leftIconMargins[1][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2IconMarginLeft, left2IconMarginTemp >= 0 ? left2IconMarginTemp : leftIconMargin[0]);
        leftIconMargins[1][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2IconMarginTop, left2IconMarginTemp >= 0 ? left2IconMarginTemp : leftIconMargin[1]);
        leftIconMargins[1][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2IconMarginRight, left2IconMarginTemp >= 0 ? left2IconMarginTemp : leftIconMargin[2]);
        leftIconMargins[1][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left2IconMarginBottom, left2IconMarginTemp >= 0 ? left2IconMarginTemp : leftIconMargin[3]);

        leftTextStyles[2] = typedArray.getInt(R.styleable.ActionBarSuper_absuper_left3TextStyle, leftTextStyle);
        leftTextClickToFinish[2] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_left3TextClickToFinish, false);
        leftIconClickToFinish[2] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_left3IconClickToFinish, false);
        leftTexts[2] = typedArray.getString(R.styleable.ActionBarSuper_absuper_left3Text);
        leftTextColors[2] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_left3TextColor, leftTextColor);
        leftTextSizes[2] = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3TextSize, leftTextSize);
        int left3TextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3TextPadding, -1);
        leftTextPaddings[2][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3TextPaddingLeft, left3TextPaddingTemp >= 0 ? left3TextPaddingTemp : leftTextPadding[0]);
        leftTextPaddings[2][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3TextPaddingTop, left3TextPaddingTemp >= 0 ? left3TextPaddingTemp : leftTextPadding[1]);
        leftTextPaddings[2][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3TextPaddingRight, left3TextPaddingTemp >= 0 ? left3TextPaddingTemp : leftTextPadding[2]);
        leftTextPaddings[2][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3TextPaddingBottom, left3TextPaddingTemp >= 0 ? left3TextPaddingTemp : leftTextPadding[3]);
        int left3TextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        leftTextMargins[2][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3TextMarginLeft, left3TextMarginTemp >= 0 ? left3TextMarginTemp : leftTextMargin[0]);
        leftTextMargins[2][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3TextMarginTop, left3TextMarginTemp >= 0 ? left3TextMarginTemp : leftTextMargin[1]);
        leftTextMargins[2][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3TextMarginRight, left3TextMarginTemp >= 0 ? left3TextMarginTemp : leftTextMargin[2]);
        leftTextMargins[2][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3TextMarginBottom, left3TextMarginTemp >= 0 ? left3TextMarginTemp : leftTextMargin[3]);
        leftIcons[2] = typedArray.getResourceId(R.styleable.ActionBarSuper_absuper_left3Icon, 0);
        leftIconColors[2] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_left3IconColor, leftIconColor);
        int left3IconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3IconPadding, -1);
        leftIconPaddings[2][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3IconPaddingLeft, left3IconPaddingTemp >= 0 ? left3IconPaddingTemp : leftIconPadding[0]);
        leftIconPaddings[2][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3IconPaddingTop, left3IconPaddingTemp >= 0 ? left3IconPaddingTemp : leftIconPadding[1]);
        leftIconPaddings[2][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3IconPaddingRight, left3IconPaddingTemp >= 0 ? left3IconPaddingTemp : leftIconPadding[2]);
        leftIconPaddings[2][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3IconPaddingBottom, left3IconPaddingTemp >= 0 ? left3IconPaddingTemp : leftIconPadding[3]);
        int left3IconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3IconMargin, -1);
        leftIconMargins[2][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3IconMarginLeft, left3IconMarginTemp >= 0 ? left3IconMarginTemp : leftIconMargin[0]);
        leftIconMargins[2][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3IconMarginTop, left3IconMarginTemp >= 0 ? left3IconMarginTemp : leftIconMargin[1]);
        leftIconMargins[2][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3IconMarginRight, left3IconMarginTemp >= 0 ? left3IconMarginTemp : leftIconMargin[2]);
        leftIconMargins[2][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left3IconMarginBottom, left3IconMarginTemp >= 0 ? left3IconMarginTemp : leftIconMargin[3]);

        leftTextStyles[3] = typedArray.getInt(R.styleable.ActionBarSuper_absuper_left4TextStyle, leftTextStyle);
        leftTextClickToFinish[3] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_left4TextClickToFinish, false);
        leftIconClickToFinish[3] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_left4IconClickToFinish, false);
        leftTexts[3] = typedArray.getString(R.styleable.ActionBarSuper_absuper_left4Text);
        leftTextColors[3] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_left4TextColor, leftTextColor);
        leftTextSizes[3] = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4TextSize, leftTextSize);
        int left4TextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4TextPadding, -1);
        leftTextPaddings[3][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4TextPaddingLeft, left4TextPaddingTemp >= 0 ? left4TextPaddingTemp : leftTextPadding[0]);
        leftTextPaddings[3][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4TextPaddingTop, left4TextPaddingTemp >= 0 ? left4TextPaddingTemp : leftTextPadding[1]);
        leftTextPaddings[3][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4TextPaddingRight, left4TextPaddingTemp >= 0 ? left4TextPaddingTemp : leftTextPadding[2]);
        leftTextPaddings[3][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4TextPaddingBottom, left4TextPaddingTemp >= 0 ? left4TextPaddingTemp : leftTextPadding[3]);
        int left4TextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        leftTextMargins[3][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4TextMarginLeft, left4TextMarginTemp >= 0 ? left4TextMarginTemp : leftTextMargin[0]);
        leftTextMargins[3][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4TextMarginTop, left4TextMarginTemp >= 0 ? left4TextMarginTemp : leftTextMargin[1]);
        leftTextMargins[3][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4TextMarginRight, left4TextMarginTemp >= 0 ? left4TextMarginTemp : leftTextMargin[2]);
        leftTextMargins[3][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4TextMarginBottom, left4TextMarginTemp >= 0 ? left4TextMarginTemp : leftTextMargin[3]);
        leftIcons[3] = typedArray.getResourceId(R.styleable.ActionBarSuper_absuper_left4Icon, 0);
        leftIconColors[3] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_left4IconColor, leftIconColor);
        int left4IconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4IconPadding, -1);
        leftIconPaddings[3][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4IconPaddingLeft, left4IconPaddingTemp >= 0 ? left4IconPaddingTemp : leftIconPadding[0]);
        leftIconPaddings[3][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4IconPaddingTop, left4IconPaddingTemp >= 0 ? left4IconPaddingTemp : leftIconPadding[1]);
        leftIconPaddings[3][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4IconPaddingRight, left4IconPaddingTemp >= 0 ? left4IconPaddingTemp : leftIconPadding[2]);
        leftIconPaddings[3][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4IconPaddingBottom, left4IconPaddingTemp >= 0 ? left4IconPaddingTemp : leftIconPadding[3]);
        int left4IconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4IconMargin, -1);
        leftIconMargins[3][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4IconMarginLeft, left4IconMarginTemp >= 0 ? left4IconMarginTemp : leftIconMargin[0]);
        leftIconMargins[3][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4IconMarginTop, left4IconMarginTemp >= 0 ? left4IconMarginTemp : leftIconMargin[1]);
        leftIconMargins[3][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4IconMarginRight, left4IconMarginTemp >= 0 ? left4IconMarginTemp : leftIconMargin[2]);
        leftIconMargins[3][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left4IconMarginBottom, left4IconMarginTemp >= 0 ? left4IconMarginTemp : leftIconMargin[3]);

        leftTextStyles[4] = typedArray.getInt(R.styleable.ActionBarSuper_absuper_left5TextStyle, leftTextStyle);
        leftTextClickToFinish[4] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_left5TextClickToFinish, false);
        leftIconClickToFinish[4] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_left5IconClickToFinish, false);
        leftTexts[4] = typedArray.getString(R.styleable.ActionBarSuper_absuper_left5Text);
        leftTextColors[4] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_left5TextColor, leftTextColor);
        leftTextSizes[4] = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5TextSize, leftTextSize);
        int left5TextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5TextPadding, -1);
        leftTextPaddings[4][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5TextPaddingLeft, left5TextPaddingTemp >= 0 ? left5TextPaddingTemp : leftTextPadding[0]);
        leftTextPaddings[4][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5TextPaddingTop, left5TextPaddingTemp >= 0 ? left5TextPaddingTemp : leftTextPadding[1]);
        leftTextPaddings[4][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5TextPaddingRight, left5TextPaddingTemp >= 0 ? left5TextPaddingTemp : leftTextPadding[2]);
        leftTextPaddings[4][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5TextPaddingBottom, left5TextPaddingTemp >= 0 ? left5TextPaddingTemp : leftTextPadding[3]);
        int left5TextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        leftTextMargins[4][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5TextMarginLeft, left5TextMarginTemp >= 0 ? left5TextMarginTemp : leftTextMargin[0]);
        leftTextMargins[4][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5TextMarginTop, left5TextMarginTemp >= 0 ? left5TextMarginTemp : leftTextMargin[1]);
        leftTextMargins[4][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5TextMarginRight, left5TextMarginTemp >= 0 ? left5TextMarginTemp : leftTextMargin[2]);
        leftTextMargins[4][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5TextMarginBottom, left5TextMarginTemp >= 0 ? left5TextMarginTemp : leftTextMargin[3]);
        leftIcons[4] = typedArray.getResourceId(R.styleable.ActionBarSuper_absuper_left5Icon, 0);
        leftIconColors[4] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_left5IconColor, leftIconColor);
        int left5IconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5IconPadding, -1);
        leftIconPaddings[4][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5IconPaddingLeft, left5IconPaddingTemp >= 0 ? left5IconPaddingTemp : leftIconPadding[0]);
        leftIconPaddings[4][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5IconPaddingTop, left5IconPaddingTemp >= 0 ? left5IconPaddingTemp : leftIconPadding[1]);
        leftIconPaddings[4][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5IconPaddingRight, left5IconPaddingTemp >= 0 ? left5IconPaddingTemp : leftIconPadding[2]);
        leftIconPaddings[4][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5IconPaddingBottom, left5IconPaddingTemp >= 0 ? left5IconPaddingTemp : leftIconPadding[3]);
        int left5IconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5IconMargin, -1);
        leftIconMargins[4][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5IconMarginLeft, left5IconMarginTemp >= 0 ? left5IconMarginTemp : leftIconMargin[0]);
        leftIconMargins[4][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5IconMarginTop, left5IconMarginTemp >= 0 ? left5IconMarginTemp : leftIconMargin[1]);
        leftIconMargins[4][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5IconMarginRight, left5IconMarginTemp >= 0 ? left5IconMarginTemp : leftIconMargin[2]);
        leftIconMargins[4][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_left5IconMarginBottom, left5IconMarginTemp >= 0 ? left5IconMarginTemp : leftIconMargin[3]);

        int rightPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightPadding, -1);
        rightPadding[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightPaddingLeft, rightPaddingTemp >= 0 ? rightPaddingTemp : 0);
        rightPadding[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightPaddingTop, rightPaddingTemp >= 0 ? rightPaddingTemp : 0);
        rightPadding[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightPaddingRight, rightPaddingTemp >= 0 ? rightPaddingTemp : 0);
        rightPadding[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightPaddingBottom, rightPaddingTemp >= 0 ? rightPaddingTemp : 0);

        rightTextStyle = typedArray.getInt(R.styleable.ActionBarSuper_absuper_rightTextStyle, textStyle);
        rightTextColor = typedArray.getColor(R.styleable.ActionBarSuper_absuper_rightTextColor, textColor);
        rightTextSize = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightTextSize, textSize);
        int rightTextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightTextPadding, -1);
        rightTextPadding[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightTextPaddingLeft, rightTextPaddingTemp >= 0 ? rightTextPaddingTemp : textPadding[0]);
        rightTextPadding[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightTextPaddingTop, rightTextPaddingTemp >= 0 ? rightTextPaddingTemp : textPadding[1]);
        rightTextPadding[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightTextPaddingRight, rightTextPaddingTemp >= 0 ? rightTextPaddingTemp : textPadding[2]);
        rightTextPadding[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightTextPaddingBottom, rightTextPaddingTemp >= 0 ? rightTextPaddingTemp : textPadding[3]);
        int rightTextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        rightTextMargin[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightTextMarginLeft, rightTextMarginTemp >= 0 ? rightTextMarginTemp : textMargin[0]);
        rightTextMargin[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightTextMarginTop, rightTextMarginTemp >= 0 ? rightTextMarginTemp : textMargin[1]);
        rightTextMargin[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightTextMarginRight, rightTextMarginTemp >= 0 ? rightTextMarginTemp : textMargin[2]);
        rightTextMargin[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightTextMarginBottom, rightTextMarginTemp >= 0 ? rightTextMarginTemp : textMargin[3]);
        rightIconColor = typedArray.getColor(R.styleable.ActionBarSuper_absuper_rightIconColor, iconColor);
        int rightIconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightIconPadding, -1);
        rightIconPadding[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightIconPaddingLeft, rightIconPaddingTemp >= 0 ? rightIconPaddingTemp : iconPadding[0]);
        rightIconPadding[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightIconPaddingTop, rightIconPaddingTemp >= 0 ? rightIconPaddingTemp : iconPadding[1]);
        rightIconPadding[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightIconPaddingRight, rightIconPaddingTemp >= 0 ? rightIconPaddingTemp : iconPadding[2]);
        rightIconPadding[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightIconPaddingBottom, rightIconPaddingTemp >= 0 ? rightIconPaddingTemp : iconPadding[3]);
        int rightIconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightIconMargin, -1);
        rightIconMargin[0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightIconMarginLeft, rightIconMarginTemp >= 0 ? rightIconMarginTemp : iconMargin[0]);
        rightIconMargin[1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightIconMarginTop, rightIconMarginTemp >= 0 ? rightIconMarginTemp : iconMargin[1]);
        rightIconMargin[2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightIconMarginRight, rightIconMarginTemp >= 0 ? rightIconMarginTemp : iconMargin[2]);
        rightIconMargin[3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_rightIconMarginBottom, rightIconMarginTemp >= 0 ? rightIconMarginTemp : iconMargin[3]);

        rightTextStyles[0] = typedArray.getInt(R.styleable.ActionBarSuper_absuper_right1TextStyle, rightTextStyle);
        rightTextClickToFinish[0] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_right1TextClickToFinish, false);
        rightIconClickToFinish[0] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_right1IconClickToFinish, false);
        rightTexts[0] = typedArray.getString(R.styleable.ActionBarSuper_absuper_right1Text);
        rightTextColors[0] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_right1TextColor, rightTextColor);
        rightTextSizes[0] = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1TextSize, rightTextSize);
        int right1TextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1TextPadding, -1);
        rightTextPaddings[0][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1TextPaddingRight, right1TextPaddingTemp >= 0 ? right1TextPaddingTemp : rightTextPadding[0]);
        rightTextPaddings[0][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1TextPaddingTop, right1TextPaddingTemp >= 0 ? right1TextPaddingTemp : rightTextPadding[1]);
        rightTextPaddings[0][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1TextPaddingRight, right1TextPaddingTemp >= 0 ? right1TextPaddingTemp : rightTextPadding[2]);
        rightTextPaddings[0][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1TextPaddingBottom, right1TextPaddingTemp >= 0 ? right1TextPaddingTemp : rightTextPadding[3]);
        int right1TextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        rightTextMargins[0][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1TextMarginRight, right1TextMarginTemp >= 0 ? right1TextMarginTemp : rightTextMargin[0]);
        rightTextMargins[0][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1TextMarginTop, right1TextMarginTemp >= 0 ? right1TextMarginTemp : rightTextMargin[1]);
        rightTextMargins[0][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1TextMarginRight, right1TextMarginTemp >= 0 ? right1TextMarginTemp : rightTextMargin[2]);
        rightTextMargins[0][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1TextMarginBottom, right1TextMarginTemp >= 0 ? right1TextMarginTemp : rightTextMargin[3]);
        rightIcons[0] = typedArray.getResourceId(R.styleable.ActionBarSuper_absuper_right1Icon, 0);
        rightIconColors[0] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_right1IconColor, rightIconColor);
        int right1IconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1IconPadding, -1);
        rightIconPaddings[0][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1IconPaddingRight, right1IconPaddingTemp >= 0 ? right1IconPaddingTemp : rightIconPadding[0]);
        rightIconPaddings[0][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1IconPaddingTop, right1IconPaddingTemp >= 0 ? right1IconPaddingTemp : rightIconPadding[1]);
        rightIconPaddings[0][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1IconPaddingRight, right1IconPaddingTemp >= 0 ? right1IconPaddingTemp : rightIconPadding[2]);
        rightIconPaddings[0][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1IconPaddingBottom, right1IconPaddingTemp >= 0 ? right1IconPaddingTemp : rightIconPadding[3]);
        int right1IconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1IconMargin, -1);
        rightIconMargins[0][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1IconMarginRight, right1IconMarginTemp >= 0 ? right1IconMarginTemp : rightIconMargin[0]);
        rightIconMargins[0][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1IconMarginTop, right1IconMarginTemp >= 0 ? right1IconMarginTemp : rightIconMargin[1]);
        rightIconMargins[0][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1IconMarginRight, right1IconMarginTemp >= 0 ? right1IconMarginTemp : rightIconMargin[2]);
        rightIconMargins[0][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right1IconMarginBottom, right1IconMarginTemp >= 0 ? right1IconMarginTemp : rightIconMargin[3]);

        rightTextStyles[1] = typedArray.getInt(R.styleable.ActionBarSuper_absuper_right2TextStyle, rightTextStyle);
        rightTextClickToFinish[1] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_right2TextClickToFinish, false);
        rightIconClickToFinish[1] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_right2IconClickToFinish, false);
        rightTexts[1] = typedArray.getString(R.styleable.ActionBarSuper_absuper_right2Text);
        rightTextColors[1] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_right2TextColor, rightTextColor);
        rightTextSizes[1] = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2TextSize, rightTextSize);
        int right2TextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2TextPadding, -1);
        rightTextPaddings[1][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2TextPaddingRight, right2TextPaddingTemp >= 0 ? right2TextPaddingTemp : rightTextPadding[0]);
        rightTextPaddings[1][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2TextPaddingTop, right2TextPaddingTemp >= 0 ? right2TextPaddingTemp : rightTextPadding[1]);
        rightTextPaddings[1][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2TextPaddingRight, right2TextPaddingTemp >= 0 ? right2TextPaddingTemp : rightTextPadding[2]);
        rightTextPaddings[1][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2TextPaddingBottom, right2TextPaddingTemp >= 0 ? right2TextPaddingTemp : rightTextPadding[3]);
        int right2TextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        rightTextMargins[1][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2TextMarginRight, right2TextMarginTemp >= 0 ? right2TextMarginTemp : rightTextMargin[0]);
        rightTextMargins[1][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2TextMarginTop, right2TextMarginTemp >= 0 ? right2TextMarginTemp : rightTextMargin[1]);
        rightTextMargins[1][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2TextMarginRight, right2TextMarginTemp >= 0 ? right2TextMarginTemp : rightTextMargin[2]);
        rightTextMargins[1][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2TextMarginBottom, right2TextMarginTemp >= 0 ? right2TextMarginTemp : rightTextMargin[3]);
        rightIcons[1] = typedArray.getResourceId(R.styleable.ActionBarSuper_absuper_right2Icon, 0);
        rightIconColors[1] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_right2IconColor, rightIconColor);
        int right2IconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2IconPadding, -1);
        rightIconPaddings[1][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2IconPaddingRight, right2IconPaddingTemp >= 0 ? right2IconPaddingTemp : rightIconPadding[0]);
        rightIconPaddings[1][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2IconPaddingTop, right2IconPaddingTemp >= 0 ? right2IconPaddingTemp : rightIconPadding[1]);
        rightIconPaddings[1][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2IconPaddingRight, right2IconPaddingTemp >= 0 ? right2IconPaddingTemp : rightIconPadding[2]);
        rightIconPaddings[1][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2IconPaddingBottom, right2IconPaddingTemp >= 0 ? right2IconPaddingTemp : rightIconPadding[3]);
        int right2IconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2IconMargin, -1);
        rightIconMargins[1][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2IconMarginRight, right2IconMarginTemp >= 0 ? right2IconMarginTemp : rightIconMargin[0]);
        rightIconMargins[1][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2IconMarginTop, right2IconMarginTemp >= 0 ? right2IconMarginTemp : rightIconMargin[1]);
        rightIconMargins[1][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2IconMarginRight, right2IconMarginTemp >= 0 ? right2IconMarginTemp : rightIconMargin[2]);
        rightIconMargins[1][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right2IconMarginBottom, right2IconMarginTemp >= 0 ? right2IconMarginTemp : rightIconMargin[3]);

        rightTextStyles[2] = typedArray.getInt(R.styleable.ActionBarSuper_absuper_right3TextStyle, rightTextStyle);
        rightTextClickToFinish[2] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_right3TextClickToFinish, false);
        rightIconClickToFinish[2] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_right3IconClickToFinish, false);
        rightTexts[2] = typedArray.getString(R.styleable.ActionBarSuper_absuper_right3Text);
        rightTextColors[2] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_right3TextColor, rightTextColor);
        rightTextSizes[2] = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3TextSize, rightTextSize);
        int right3TextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3TextPadding, -1);
        rightTextPaddings[2][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3TextPaddingRight, right3TextPaddingTemp >= 0 ? right3TextPaddingTemp : rightTextPadding[0]);
        rightTextPaddings[2][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3TextPaddingTop, right3TextPaddingTemp >= 0 ? right3TextPaddingTemp : rightTextPadding[1]);
        rightTextPaddings[2][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3TextPaddingRight, right3TextPaddingTemp >= 0 ? right3TextPaddingTemp : rightTextPadding[2]);
        rightTextPaddings[2][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3TextPaddingBottom, right3TextPaddingTemp >= 0 ? right3TextPaddingTemp : rightTextPadding[3]);
        int right3TextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        rightTextMargins[2][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3TextMarginRight, right3TextMarginTemp >= 0 ? right3TextMarginTemp : rightTextMargin[0]);
        rightTextMargins[2][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3TextMarginTop, right3TextMarginTemp >= 0 ? right3TextMarginTemp : rightTextMargin[1]);
        rightTextMargins[2][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3TextMarginRight, right3TextMarginTemp >= 0 ? right3TextMarginTemp : rightTextMargin[2]);
        rightTextMargins[2][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3TextMarginBottom, right3TextMarginTemp >= 0 ? right3TextMarginTemp : rightTextMargin[3]);
        rightIcons[2] = typedArray.getResourceId(R.styleable.ActionBarSuper_absuper_right3Icon, 0);
        rightIconColors[2] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_right3IconColor, rightIconColor);
        int right3IconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3IconPadding, -1);
        rightIconPaddings[2][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3IconPaddingRight, right3IconPaddingTemp >= 0 ? right3IconPaddingTemp : rightIconPadding[0]);
        rightIconPaddings[2][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3IconPaddingTop, right3IconPaddingTemp >= 0 ? right3IconPaddingTemp : rightIconPadding[1]);
        rightIconPaddings[2][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3IconPaddingRight, right3IconPaddingTemp >= 0 ? right3IconPaddingTemp : rightIconPadding[2]);
        rightIconPaddings[2][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3IconPaddingBottom, right3IconPaddingTemp >= 0 ? right3IconPaddingTemp : rightIconPadding[3]);
        int right3IconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3IconMargin, -1);
        rightIconMargins[2][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3IconMarginRight, right3IconMarginTemp >= 0 ? right3IconMarginTemp : rightIconMargin[0]);
        rightIconMargins[2][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3IconMarginTop, right3IconMarginTemp >= 0 ? right3IconMarginTemp : rightIconMargin[1]);
        rightIconMargins[2][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3IconMarginRight, right3IconMarginTemp >= 0 ? right3IconMarginTemp : rightIconMargin[2]);
        rightIconMargins[2][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right3IconMarginBottom, right3IconMarginTemp >= 0 ? right3IconMarginTemp : rightIconMargin[3]);

        rightTextStyles[3] = typedArray.getInt(R.styleable.ActionBarSuper_absuper_right4TextStyle, rightTextStyle);
        rightTextClickToFinish[3] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_right4TextClickToFinish, false);
        rightIconClickToFinish[3] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_right4IconClickToFinish, false);
        rightTexts[3] = typedArray.getString(R.styleable.ActionBarSuper_absuper_right4Text);
        rightTextColors[3] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_right4TextColor, rightTextColor);
        rightTextSizes[3] = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4TextSize, rightTextSize);
        int right4TextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4TextPadding, -1);
        rightTextPaddings[3][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4TextPaddingRight, right4TextPaddingTemp >= 0 ? right4TextPaddingTemp : rightTextPadding[0]);
        rightTextPaddings[3][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4TextPaddingTop, right4TextPaddingTemp >= 0 ? right4TextPaddingTemp : rightTextPadding[1]);
        rightTextPaddings[3][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4TextPaddingRight, right4TextPaddingTemp >= 0 ? right4TextPaddingTemp : rightTextPadding[2]);
        rightTextPaddings[3][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4TextPaddingBottom, right4TextPaddingTemp >= 0 ? right4TextPaddingTemp : rightTextPadding[3]);
        int right4TextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        rightTextMargins[3][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4TextMarginRight, right4TextMarginTemp >= 0 ? right4TextMarginTemp : rightTextMargin[0]);
        rightTextMargins[3][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4TextMarginTop, right4TextMarginTemp >= 0 ? right4TextMarginTemp : rightTextMargin[1]);
        rightTextMargins[3][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4TextMarginRight, right4TextMarginTemp >= 0 ? right4TextMarginTemp : rightTextMargin[2]);
        rightTextMargins[3][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4TextMarginBottom, right4TextMarginTemp >= 0 ? right4TextMarginTemp : rightTextMargin[3]);
        rightIcons[3] = typedArray.getResourceId(R.styleable.ActionBarSuper_absuper_right4Icon, 0);
        rightIconColors[3] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_right4IconColor, rightIconColor);
        int right4IconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4IconPadding, -1);
        rightIconPaddings[3][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4IconPaddingRight, right4IconPaddingTemp >= 0 ? right4IconPaddingTemp : rightIconPadding[0]);
        rightIconPaddings[3][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4IconPaddingTop, right4IconPaddingTemp >= 0 ? right4IconPaddingTemp : rightIconPadding[1]);
        rightIconPaddings[3][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4IconPaddingRight, right4IconPaddingTemp >= 0 ? right4IconPaddingTemp : rightIconPadding[2]);
        rightIconPaddings[3][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4IconPaddingBottom, right4IconPaddingTemp >= 0 ? right4IconPaddingTemp : rightIconPadding[3]);
        int right4IconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4IconMargin, -1);
        rightIconMargins[3][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4IconMarginRight, right4IconMarginTemp >= 0 ? right4IconMarginTemp : rightIconMargin[0]);
        rightIconMargins[3][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4IconMarginTop, right4IconMarginTemp >= 0 ? right4IconMarginTemp : rightIconMargin[1]);
        rightIconMargins[3][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4IconMarginRight, right4IconMarginTemp >= 0 ? right4IconMarginTemp : rightIconMargin[2]);
        rightIconMargins[3][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right4IconMarginBottom, right4IconMarginTemp >= 0 ? right4IconMarginTemp : rightIconMargin[3]);

        rightTextStyles[4] = typedArray.getInt(R.styleable.ActionBarSuper_absuper_right5TextStyle, rightTextStyle);
        rightTextClickToFinish[4] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_right5TextClickToFinish, false);
        rightIconClickToFinish[4] = typedArray.getBoolean(R.styleable.ActionBarSuper_absuper_right5IconClickToFinish, false);
        rightTexts[4] = typedArray.getString(R.styleable.ActionBarSuper_absuper_right5Text);
        rightTextColors[4] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_right5TextColor, rightTextColor);
        rightTextSizes[4] = typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5TextSize, rightTextSize);
        int right5TextPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5TextPadding, -1);
        rightTextPaddings[4][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5TextPaddingRight, right5TextPaddingTemp >= 0 ? right5TextPaddingTemp : rightTextPadding[0]);
        rightTextPaddings[4][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5TextPaddingTop, right5TextPaddingTemp >= 0 ? right5TextPaddingTemp : rightTextPadding[1]);
        rightTextPaddings[4][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5TextPaddingRight, right5TextPaddingTemp >= 0 ? right5TextPaddingTemp : rightTextPadding[2]);
        rightTextPaddings[4][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5TextPaddingBottom, right5TextPaddingTemp >= 0 ? right5TextPaddingTemp : rightTextPadding[3]);
        int right5TextMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_textMargin, -1);
        rightTextMargins[4][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5TextMarginRight, right5TextMarginTemp >= 0 ? right5TextMarginTemp : rightTextMargin[0]);
        rightTextMargins[4][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5TextMarginTop, right5TextMarginTemp >= 0 ? right5TextMarginTemp : rightTextMargin[1]);
        rightTextMargins[4][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5TextMarginRight, right5TextMarginTemp >= 0 ? right5TextMarginTemp : rightTextMargin[2]);
        rightTextMargins[4][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5TextMarginBottom, right5TextMarginTemp >= 0 ? right5TextMarginTemp : rightTextMargin[3]);
        rightIcons[4] = typedArray.getResourceId(R.styleable.ActionBarSuper_absuper_right5Icon, 0);
        rightIconColors[4] = typedArray.getColor(R.styleable.ActionBarSuper_absuper_right5IconColor, rightIconColor);
        int right5IconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5IconPadding, -1);
        rightIconPaddings[4][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5IconPaddingRight, right5IconPaddingTemp >= 0 ? right5IconPaddingTemp : rightIconPadding[0]);
        rightIconPaddings[4][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5IconPaddingTop, right5IconPaddingTemp >= 0 ? right5IconPaddingTemp : rightIconPadding[1]);
        rightIconPaddings[4][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5IconPaddingRight, right5IconPaddingTemp >= 0 ? right5IconPaddingTemp : rightIconPadding[2]);
        rightIconPaddings[4][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5IconPaddingBottom, right5IconPaddingTemp >= 0 ? right5IconPaddingTemp : rightIconPadding[3]);
        int right5IconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5IconMargin, -1);
        rightIconMargins[4][0] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5IconMarginRight, right5IconMarginTemp >= 0 ? right5IconMarginTemp : rightIconMargin[0]);
        rightIconMargins[4][1] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5IconMarginTop, right5IconMarginTemp >= 0 ? right5IconMarginTemp : rightIconMargin[1]);
        rightIconMargins[4][2] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5IconMarginRight, right5IconMarginTemp >= 0 ? right5IconMarginTemp : rightIconMargin[2]);
        rightIconMargins[4][3] = (int) typedArray.getDimension(R.styleable.ActionBarSuper_absuper_right5IconMarginBottom, right5IconMarginTemp >= 0 ? right5IconMarginTemp : rightIconMargin[3]);

        typedArray.recycle();
    }

    @Override
    protected View inflateTitleBar() {
        titleBarChild = (FrameLayout) inflate(getContext(), R.layout.action_bar_title_bar_suoer, null);
        initTitleTextView();
        initLeftActionViews();
        initRightActionViews();
        return titleBarChild;
    }

    public void initTitleTextView() {
        titleTextView = titleBarChild.findViewById(R.id.tv_title);
        titleTextView.setVisibility(VISIBLE);
        setTextStyle(titleTextView, titleTextStyle);
        titleTextView.setText(titleText);
        titleTextView.setTextColor(titleTextColor);
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        titleTextView.setMaxWidth(titleTextMaxWidth);
        titleTextView.setPadding(titlePadding[0], titlePadding[1], titlePadding[2], titlePadding[3]);
        LinearLayout.LayoutParams titleParams = (LinearLayout.LayoutParams) titleTextView.getLayoutParams();
        titleParams.leftMargin = titleMargin[0];
        titleParams.topMargin = titleMargin[1];
        titleParams.rightMargin = titleMargin[2];
        titleParams.bottomMargin = titleMargin[3];
        subtitleTextView = titleBarChild.findViewById(R.id.tv_subtitle);
        if (TextUtils.isEmpty(subtitleText)) {
            subtitleTextView.setVisibility(GONE);
        } else {
            subtitleTextView.setVisibility(VISIBLE);
        }
        setTextStyle(subtitleTextView, subtitleTextStyle);
        subtitleTextView.setText(subtitleText);
        subtitleTextView.setTextColor(subtitleTextColor);
        subtitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, subtitleTextSize);
        subtitleTextView.setMaxWidth(subtitleTextMaxWidth);
        subtitleTextView.setPadding(subtitlePadding[0], subtitlePadding[1], subtitlePadding[2], subtitlePadding[3]);
        LinearLayout.LayoutParams subtitleParams = (LinearLayout.LayoutParams) subtitleTextView.getLayoutParams();
        subtitleParams.leftMargin = subtitleMargin[0];
        subtitleParams.topMargin = subtitleMargin[1];
        subtitleParams.rightMargin = subtitleMargin[2];
        subtitleParams.bottomMargin = subtitleMargin[3];
        LinearLayout titleLayout = titleBarChild.findViewById(R.id.ll_title);
        LayoutParams titleLayoutParams = (LayoutParams) titleLayout.getLayoutParams();
        if (titleGravity == TitleGravity.CENTER) {
            titleLayout.setGravity(Gravity.CENTER);
            titleLayoutParams.gravity = Gravity.CENTER;
        } else if (titleGravity == TitleGravity.LEFT) {
            titleLayout.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            titleLayoutParams.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
        } else if (titleGravity == TitleGravity.RIGHT) {
            titleLayout.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            titleLayoutParams.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        } else {
            titleLayout.setGravity(Gravity.CENTER);
            titleLayoutParams.gravity = Gravity.CENTER;
        }
    }

    private void initLeftActionViews() {
        LinearLayout leftActionLayout = titleBarChild.findViewById(R.id.ll_left);
        leftActionLayout.setPadding(leftPadding[0], leftPadding[1], leftPadding[2], leftPadding[3]);
        leftActionViews = new ActionView[5];
        leftActionViews[0] = titleBarChild.findViewById(R.id.av_left1);
        leftActionViews[1] = titleBarChild.findViewById(R.id.av_left2);
        leftActionViews[2] = titleBarChild.findViewById(R.id.av_left3);
        leftActionViews[3] = titleBarChild.findViewById(R.id.av_left4);
        leftActionViews[4] = titleBarChild.findViewById(R.id.av_left5);
        for (int i = 0; i < leftActionViews.length; i++) {
            setTextStyle(leftActionViews[i].getTextView(), leftTextStyles[i]);
            leftActionViews[i].setTextPadding(leftTextPaddings[i][0], leftTextPaddings[i][1], leftTextPaddings[i][2], leftTextPaddings[i][3]);
            leftActionViews[i].setTextMargin(leftTextMargins[i][0], leftTextMargins[i][1], leftTextMargins[i][2], leftTextMargins[i][3]);
            leftActionViews[i].setTextColor(leftTextColors[i]);
            leftActionViews[i].setTextSizePx(leftTextSizes[i]);
            leftActionViews[i].setIconPadding(leftIconPaddings[i][0], leftIconPaddings[i][1], leftIconPaddings[i][2], leftIconPaddings[i][3]);
            leftActionViews[i].setIconMargin(leftIconMargins[i][0], leftIconMargins[i][1], leftIconMargins[i][2], leftIconMargins[i][3]);
            leftActionViews[i].setIconColorInt(leftIconColors[i]);
            if (leftIcons[i] > 0) {
                leftActionViews[i].setIcon(leftIcons[i]);
            } else if (!TextUtils.isEmpty(leftTexts[i])) {
                leftActionViews[i].setText(leftTexts[i]);
            } else {
                leftActionViews[i].toggleToGone();
            }
            if (leftTextClickToFinish[i]) {
                leftActionViews[i].getTextView().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishActivity();
                    }
                });
            }
            if (leftIconClickToFinish[i]) {
                leftActionViews[i].getIconView().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishActivity();
                    }
                });
            }
        }
    }

    private void initRightActionViews() {
        LinearLayout rightActionLayout = titleBarChild.findViewById(R.id.ll_right);
        rightActionLayout.setPadding(rightPadding[0], rightPadding[1], rightPadding[2], rightPadding[3]);
        rightActionViews = new ActionView[5];
        rightActionViews[0] = titleBarChild.findViewById(R.id.av_right1);
        rightActionViews[1] = titleBarChild.findViewById(R.id.av_right2);
        rightActionViews[2] = titleBarChild.findViewById(R.id.av_right3);
        rightActionViews[3] = titleBarChild.findViewById(R.id.av_right4);
        rightActionViews[4] = titleBarChild.findViewById(R.id.av_right5);
        for (int i = 0; i < rightActionViews.length; i++) {
            setTextStyle(rightActionViews[i].getTextView(), rightTextStyles[i]);
            rightActionViews[i].setTextPadding(rightTextPaddings[i][0], rightTextPaddings[i][1], rightTextPaddings[i][2], rightTextPaddings[i][3]);
            rightActionViews[i].setTextMargin(rightTextMargins[i][0], rightTextMargins[i][1], rightTextMargins[i][2], rightTextMargins[i][3]);
            rightActionViews[i].setTextColor(rightTextColors[i]);
            rightActionViews[i].setTextSizePx(rightTextSizes[i]);
            rightActionViews[i].setIconPadding(rightIconPaddings[i][0], rightIconPaddings[i][1], rightIconPaddings[i][2], rightIconPaddings[i][3]);
            rightActionViews[i].setIconMargin(rightIconMargins[i][0], rightIconMargins[i][1], rightIconMargins[i][2], rightIconMargins[i][3]);
            rightActionViews[i].setIconColorInt(rightIconColors[i]);
            if (rightIcons[i] > 0) {
                rightActionViews[i].setIcon(rightIcons[i]);
            } else if (!TextUtils.isEmpty(rightTexts[i])) {
                rightActionViews[i].setText(rightTexts[i]);
            } else {
                rightActionViews[i].toggleToGone();
            }
            if (rightTextClickToFinish[i]) {
                rightActionViews[i].getTextView().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishActivity();
                    }
                });
            }
            if (rightIconClickToFinish[i]) {
                rightActionViews[i].getIconView().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishActivity();
                    }
                });
            }
        }
    }

    private void setTextStyle(TextView textView, @TextStyle int textStyle) {
        if (textStyle == TextStyle.BOLD) {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else if (textStyle == TextStyle.NORMAL) {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        } else {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public ActionView[] getLeftActionViews() {
        return leftActionViews;
    }

    public ActionView[] getRightActionViews() {
        return rightActionViews;
    }

    public ActionView getLeftActionView(int index) {
        if (index < 0 || index >= leftActionViews.length) {
            return null;
        }
        return leftActionViews[index];
    }

    public ActionView getRightActionView(int index) {
        if (index < 0 || index >= rightActionViews.length) {
            return null;
        }
        return rightActionViews[index];
    }
}
