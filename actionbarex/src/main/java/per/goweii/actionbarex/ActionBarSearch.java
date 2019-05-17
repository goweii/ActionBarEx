package per.goweii.actionbarex;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import per.goweii.actionbarex.listener.OnLeftIconClickListener;
import per.goweii.actionbarex.listener.OnLeftTextClickListener;
import per.goweii.actionbarex.listener.OnRightIconClickListener;
import per.goweii.actionbarex.listener.OnRightTextClickListener;

/**
 * @author Cuizhen
 * @date 2018/8/30-上午11:10
 */
public final class ActionBarSearch extends ActionBarEx {

    private String leftText;
    private float leftTextSize;
    private int leftTextColor;
    private int leftTextPaddingLeft;
    private int leftTextPaddingRight;
    private int leftIconRes;
    private int leftIconColor;
    private int leftIconPadding;
    private String rightText;
    private float rightTextSize;
    private int rightTextColor;
    private int rightTextPaddingLeft;
    private int rightTextPaddingRight;
    private int rightIconRes;
    private int rightIconColor;
    private int rightIconPadding;
    private String titleHintText;
    private float titleTextSize;
    private int titleTextColor;
    private int titleHintColor;
    private int titleBgRes;
    private int titlePaddingHorizontal;
    private int titleMarginVertical;
    private boolean leftTextClickToFinish;
    private boolean leftIconClickToFinish;

    private RelativeLayout titleBarChild;
    private ImageView leftIconView;
    private TextView leftTextView;
    private EditText titleEditText;
    private TextView rightTextView;
    private ImageView rightIconView;

    public ActionBarSearch(Context context) {
        this(context, null);
    }

    public ActionBarSearch(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionBarSearch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    public EditText getEditTextView() {
        return titleEditText;
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

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.ActionBarSearch);

        float iconPaddingDef = mContext.getResources().getDimension(R.dimen.title_bar_icon_padding_def);
        float textSizeDef = mContext.getResources().getDimension(R.dimen.title_bar_text_size_def);
        float textPaddingLeftDef = mContext.getResources().getDimension(R.dimen.title_bar_text_padding_left_def);
        float textPaddingRightDef = mContext.getResources().getDimension(R.dimen.title_bar_text_padding_right_def);
        float titleTextSizeDef = mContext.getResources().getDimension(R.dimen.title_bar_title_text_size_def);
        int iconColorDef = ContextCompat.getColor(mContext, R.color.icon_color_def);
        int textColorDef = ContextCompat.getColor(mContext, R.color.text_color_def);
        int titleTextColorDef = ContextCompat.getColor(mContext, R.color.title_text_color_def);
        int titleTextHintColorDef = ContextCompat.getColor(mContext, R.color.title_text_hint_color_def);

        leftTextClickToFinish = typedArray.getBoolean(R.styleable.ActionBarCommon_abc_leftTextClickToFinish, false);
        leftIconClickToFinish = typedArray.getBoolean(R.styleable.ActionBarCommon_abc_leftIconClickToFinish, false);
        leftText = typedArray.getString(R.styleable.ActionBarSearch_abs_leftText);
        leftTextSize = typedArray.getDimension(R.styleable.ActionBarSearch_abs_leftTextSize, textSizeDef);
        leftTextColor = typedArray.getColor(R.styleable.ActionBarSearch_abs_leftTextColor, textColorDef);
        leftTextPaddingLeft = (int) typedArray.getDimension(R.styleable.ActionBarSearch_abs_leftTextPaddingLeft, textPaddingLeftDef);
        leftTextPaddingRight = (int) typedArray.getDimension(R.styleable.ActionBarSearch_abs_leftTextPaddingRight, textPaddingRightDef);
        leftIconRes = typedArray.getResourceId(R.styleable.ActionBarSearch_abs_leftIconRes, 0);
        leftIconColor = typedArray.getColor(R.styleable.ActionBarSearch_abs_leftIconColor, iconColorDef);
        leftIconPadding = (int) typedArray.getDimension(R.styleable.ActionBarSearch_abs_leftIconPadding, iconPaddingDef);

        rightText = typedArray.getString(R.styleable.ActionBarSearch_abs_rightText);
        rightTextSize = typedArray.getDimension(R.styleable.ActionBarSearch_abs_rightTextSize, textSizeDef);
        rightTextColor = typedArray.getColor(R.styleable.ActionBarSearch_abs_rightTextColor, textColorDef);
        rightTextPaddingLeft = (int) typedArray.getDimension(R.styleable.ActionBarSearch_abs_rightTextPaddingLeft, textPaddingLeftDef);
        rightTextPaddingRight = (int) typedArray.getDimension(R.styleable.ActionBarSearch_abs_rightTextPaddingRight, textPaddingRightDef);
        rightIconRes = typedArray.getResourceId(R.styleable.ActionBarSearch_abs_rightIconRes, 0);
        rightIconColor = typedArray.getColor(R.styleable.ActionBarSearch_abs_rightIconColor, iconColorDef);
        rightIconPadding = (int) typedArray.getDimension(R.styleable.ActionBarSearch_abs_rightIconPadding, iconPaddingDef);

        titleBgRes = typedArray.getResourceId(R.styleable.ActionBarSearch_abs_titleBgRes, 0);
        titleHintText = typedArray.getString(R.styleable.ActionBarSearch_abs_titleHintText);
        titleTextSize = typedArray.getDimension(R.styleable.ActionBarSearch_abs_titleTextSize, titleTextSizeDef);
        titleTextColor = typedArray.getColor(R.styleable.ActionBarSearch_abs_titleTextColor, titleTextColorDef);
        titleHintColor = typedArray.getColor(R.styleable.ActionBarSearch_abs_titleHintColor, titleTextHintColorDef);
        titlePaddingHorizontal = (int) typedArray.getDimension(R.styleable.ActionBarSearch_abs_titlePaddingHorizontal, 0);
        titleMarginVertical = (int) typedArray.getDimension(R.styleable.ActionBarSearch_abs_titleMarginVertical, 0);

        typedArray.recycle();
    }

    @Override
    protected View inflateTitleBar() {
        titleBarChild = (RelativeLayout) inflate(getContext(), R.layout.action_bar_title_bar_search, null);

        leftIconView = titleBarChild.findViewById(R.id.iv_left);
        leftTextView = titleBarChild.findViewById(R.id.tv_left);
        titleEditText = titleBarChild.findViewById(R.id.et_title);
        rightTextView = titleBarChild.findViewById(R.id.tv_right);
        rightIconView = titleBarChild.findViewById(R.id.iv_right);

        if (leftIconRes > 0) {
            leftIconView.setVisibility(VISIBLE);
            leftIconView.setPadding(leftIconPadding, leftIconPadding, leftIconPadding, leftIconPadding);
            leftIconView.setImageResource(leftIconRes);
            leftIconView.setColorFilter(leftIconColor);
            if (leftIconClickToFinish) {
                leftIconView.setOnClickListener(new OnClickListener() {
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
                leftTextView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishActivity();
                    }
                });
            }
        } else {
            leftTextView.setVisibility(GONE);
        }

        titleEditText.setVisibility(VISIBLE);
        titleEditText.setHint(titleHintText);
        titleEditText.setTextColor(titleTextColor);
        titleEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        titleEditText.setHintTextColor(titleHintColor);
        if (titleBgRes > 0) {
            titleEditText.setBackgroundResource(titleBgRes);
        }
        titleEditText.setPadding(titlePaddingHorizontal, 0, titlePaddingHorizontal, 0);
        RelativeLayout.LayoutParams titleParams = (RelativeLayout.LayoutParams) titleEditText.getLayoutParams();
        titleParams.topMargin = titleMarginVertical;
        titleParams.bottomMargin = titleMarginVertical;
        titleEditText.setLayoutParams(titleParams);

        if (rightIconRes > 0) {
            rightIconView.setVisibility(VISIBLE);
            leftIconView.setPadding(rightIconPadding, rightIconPadding, rightIconPadding, rightIconPadding);
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

    public void setOnLeftImageClickListener(final OnLeftIconClickListener onLeftIconClickListener) {
        leftIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftIconClickListener != null) {
                    onLeftIconClickListener.onClick();
                }
            }
        });
    }

    public void setOnLeftTextClickListener(final OnLeftTextClickListener onLeftTextClickListener) {
        leftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftTextClickListener != null) {
                    onLeftTextClickListener.onClick();
                }
            }
        });
    }

    public void setOnRightTextClickListener(final OnRightTextClickListener onRightTextClickListener) {
        rightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightTextClickListener != null) {
                    onRightTextClickListener.onClick();
                }
            }
        });
    }

    public void setOnRightImageClickListener(final OnRightIconClickListener onRightIconClickListener) {
        rightIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightIconClickListener != null) {
                    onRightIconClickListener.onClick();
                }
            }
        });
    }
}
