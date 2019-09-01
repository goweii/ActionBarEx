package per.goweii.actionbarex.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import per.goweii.actionbarex.ActionBarEx;

/**
 * @author Cuizhen
 * @date 2018/8/30-上午11:10
 */
public final class ActionBarCommon extends ActionBarEx {

    private String leftText;
    private float leftTextSize;
    private int leftTextColor;
    private int leftTextPaddingLeft;
    private int leftTextPaddingRight;
    private int leftIconRes;
    private int leftIconColor;
    private int leftIconPadding;
    private int leftIconMarginLeft;
    private String rightText;
    private float rightTextSize;
    private int rightTextColor;
    private int rightTextPaddingLeft;
    private int rightTextPaddingRight;
    private int rightIconRes;
    private int rightIconColor;
    private int rightIconPadding;
    private int rightIconMarginRight;
    private String titleText;
    private float titleTextSize;
    private int titleTextColor;
    private int titleTextMaxWidth;
    private boolean leftTextClickToFinish;
    private boolean leftIconClickToFinish;

    private RelativeLayout titleBarChild;
    private ImageView leftIconView;
    private TextView leftTextView;
    private TextView titleTextView;
    private TextView rightTextView;
    private ImageView rightIconView;

    public ActionBarCommon(Context context) {
        this(context, null);
    }

    public ActionBarCommon(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionBarCommon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public RelativeLayout getTitleBarChild() {
        return titleBarChild;
    }

    public ImageView getLeftIconView() {
        return leftIconView;
    }

    public TextView getLeftTextView() {
        return leftTextView;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public TextView getRightTextView() {
        return rightTextView;
    }

    public ImageView getRightIconView() {
        return rightIconView;
    }

    @Override
    protected void initAttrs(AttributeSet attrs) {
        super.initAttrs(attrs);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ActionBarCommon);

        float titleTextMaxWidthDef = getContext().getResources().getDimension(R.dimen.title_bar_title_text_max_width_def);
        float iconPaddingDef = getContext().getResources().getDimension(R.dimen.title_bar_icon_padding_def);
        float textSizeDef = getContext().getResources().getDimension(R.dimen.title_bar_text_size_def);
        float textPaddingLeftDef = getContext().getResources().getDimension(R.dimen.title_bar_text_padding_left_def);
        float textPaddingRightDef = getContext().getResources().getDimension(R.dimen.title_bar_text_padding_right_def);
        float titleTextSizeDef = getContext().getResources().getDimension(R.dimen.title_bar_title_text_size_def);
        int iconColorDef = ContextCompat.getColor(getContext(), R.color.title_bar_icon_color_def);
        int textColorDef = ContextCompat.getColor(getContext(), R.color.title_bar_text_color_def);
        int titleTextColorDef = ContextCompat.getColor(getContext(), R.color.title_bar_title_text_color_def);

        leftTextClickToFinish = typedArray.getBoolean(R.styleable.ActionBarCommon_abc_leftTextClickToFinish, false);
        leftIconClickToFinish = typedArray.getBoolean(R.styleable.ActionBarCommon_abc_leftIconClickToFinish, false);
        leftText = typedArray.getString(R.styleable.ActionBarCommon_abc_leftText);
        leftTextSize = typedArray.getDimension(R.styleable.ActionBarCommon_abc_leftTextSize, textSizeDef);
        leftTextColor = typedArray.getColor(R.styleable.ActionBarCommon_abc_leftTextColor, textColorDef);
        leftTextPaddingLeft = (int) typedArray.getDimension(R.styleable.ActionBarCommon_abc_leftTextPaddingLeft, textPaddingLeftDef);
        leftTextPaddingRight = (int) typedArray.getDimension(R.styleable.ActionBarCommon_abc_leftTextPaddingRight, textPaddingRightDef);
        leftIconRes = typedArray.getResourceId(R.styleable.ActionBarCommon_abc_leftIconRes, 0);
        leftIconColor = typedArray.getColor(R.styleable.ActionBarCommon_abc_leftIconColor, iconColorDef);
        leftIconPadding = (int) typedArray.getDimension(R.styleable.ActionBarCommon_abc_leftIconPadding, iconPaddingDef);
        leftIconMarginLeft = (int) typedArray.getDimension(R.styleable.ActionBarCommon_abc_leftIconMarginLeft, 0);

        rightText = typedArray.getString(R.styleable.ActionBarCommon_abc_rightText);
        rightTextSize = typedArray.getDimension(R.styleable.ActionBarCommon_abc_rightTextSize, textSizeDef);
        rightTextColor = typedArray.getColor(R.styleable.ActionBarCommon_abc_rightTextColor, textColorDef);
        rightTextPaddingLeft = (int) typedArray.getDimension(R.styleable.ActionBarCommon_abc_rightTextPaddingLeft, textPaddingLeftDef);
        rightTextPaddingRight = (int) typedArray.getDimension(R.styleable.ActionBarCommon_abc_rightTextPaddingRight, textPaddingRightDef);
        rightIconRes = typedArray.getResourceId(R.styleable.ActionBarCommon_abc_rightIconRes, 0);
        rightIconColor = typedArray.getColor(R.styleable.ActionBarCommon_abc_rightIconColor, iconColorDef);
        rightIconPadding = (int) typedArray.getDimension(R.styleable.ActionBarCommon_abc_rightIconPadding, iconPaddingDef);
        rightIconMarginRight = (int) typedArray.getDimension(R.styleable.ActionBarCommon_abc_rightIconMarginRight, 0);

        titleText = typedArray.getString(R.styleable.ActionBarCommon_abc_titleText);
        titleTextSize = typedArray.getDimension(R.styleable.ActionBarCommon_abc_titleTextSize, titleTextSizeDef);
        titleTextColor = typedArray.getColor(R.styleable.ActionBarCommon_abc_titleTextColor, titleTextColorDef);
        titleTextMaxWidth = (int) typedArray.getDimension(R.styleable.ActionBarCommon_abc_titleTextMaxWidth, titleTextMaxWidthDef);

        typedArray.recycle();
    }

    @Override
    protected View inflateTitleBar() {
        titleBarChild = (RelativeLayout) inflate(getContext(), R.layout.action_bar_title_bar_common, null);

        leftIconView = titleBarChild.findViewById(R.id.iv_left);
        leftTextView = titleBarChild.findViewById(R.id.tv_left);
        titleTextView = titleBarChild.findViewById(R.id.tv_title);
        rightTextView = titleBarChild.findViewById(R.id.tv_right);
        rightIconView = titleBarChild.findViewById(R.id.iv_right);

        LinearLayout.LayoutParams leftIconViewParams = (LinearLayout.LayoutParams) leftIconView.getLayoutParams();
        leftIconViewParams.leftMargin = leftIconMarginLeft;
        leftIconView.setLayoutParams(leftIconViewParams);
        if (leftIconRes > 0) {
            leftIconView.setVisibility(VISIBLE);
            leftIconView.setPadding(leftIconPadding, leftIconPadding, leftIconPadding, leftIconPadding);
            leftIconView.setImageResource(leftIconRes);
            leftIconView.setColorFilter(leftIconColor);
            if (leftIconClickToFinish) {
                leftIconView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishActivity();
                    }
                });
            }
        } else {
            leftIconView.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(leftText)) {
            leftTextView.setVisibility(VISIBLE);
            leftTextView.setText(leftText);
            leftTextView.setTextColor(leftTextColor);
            leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
            leftTextView.setPadding(leftTextPaddingLeft, 0, leftTextPaddingRight, 0);
            if (leftTextClickToFinish) {
                leftTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishActivity();
                    }
                });
            }
        } else {
            leftTextView.setVisibility(GONE);
        }

        titleTextView.setVisibility(VISIBLE);
        titleTextView.setText(titleText);
        titleTextView.setTextColor(titleTextColor);
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        titleTextView.setMaxWidth(titleTextMaxWidth);

        LinearLayout.LayoutParams rightIconViewParams = (LinearLayout.LayoutParams) rightIconView.getLayoutParams();
        rightIconViewParams.rightMargin = rightIconMarginRight;
        rightIconView.setLayoutParams(rightIconViewParams);
        if (rightIconRes > 0) {
            rightIconView.setVisibility(VISIBLE);
            rightIconView.setPadding(rightIconPadding, rightIconPadding, rightIconPadding, rightIconPadding);
            rightIconView.setImageResource(rightIconRes);
            rightIconView.setColorFilter(rightIconColor);
        } else {
            rightIconView.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(rightText)) {
            rightTextView.setVisibility(VISIBLE);
            rightTextView.setText(rightText);
            rightTextView.setTextColor(rightTextColor);
            rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
            rightTextView.setPadding(rightTextPaddingLeft, 0, rightTextPaddingRight, 0);
        } else {
            rightTextView.setVisibility(GONE);
        }
        return titleBarChild;
    }

    public void setOnLeftIconClickListener(final OnActionBarChildClickListener onLeftIconClickListener) {
        leftIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftIconClickListener != null) {
                    onLeftIconClickListener.onClick(v);
                }
            }
        });
    }

    public void setOnLeftTextClickListener(final OnActionBarChildClickListener onLeftTextClickListener) {
        leftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftTextClickListener != null) {
                    onLeftTextClickListener.onClick(v);
                }
            }
        });
    }

    public void setOnRightTextClickListener(final OnActionBarChildClickListener onRightTextClickListener) {
        rightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightTextClickListener != null) {
                    onRightTextClickListener.onClick(v);
                }
            }
        });
    }

    public void setOnRightIconClickListener(final OnActionBarChildClickListener onRightIconClickListener) {
        rightIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightIconClickListener != null) {
                    onRightIconClickListener.onClick(v);
                }
            }
        });
    }
}
