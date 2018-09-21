package per.goweii.actionbarex;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import per.goweii.actionbarex.listener.OnLeftImageClickListener;
import per.goweii.actionbarex.listener.OnLeftTextClickListener;
import per.goweii.actionbarex.listener.OnRightImageClickListener;
import per.goweii.actionbarex.listener.OnRightTextClickListener;

/**
 * @author Cuizhen
 * @date 2018/8/30-上午11:10
 */
public final class SimpleActionBar extends ActionBarEx {

    private String leftText;
    private float leftTextSize;
    private int leftTextColor;
    private int leftTextPaddingLeft;
    private int leftTextPaddingRight;
    private int leftImageRes;
    private int leftImageColor;
    private int leftImagePaddingLeft;
    private int leftImagePaddingRight;
    private int leftImagePaddingTop;
    private int leftImagePaddingBottom;
    private String rightText;
    private float rightTextSize;
    private int rightTextColor;
    private int rightTextPaddingLeft;
    private int rightTextPaddingRight;
    private int rightImageRes;
    private int rightImageColor;
    private int rightImagePaddingLeft;
    private int rightImagePaddingRight;
    private int rightImagePaddingTop;
    private int rightImagePaddingBottom;
    private String titleText;
    private float titleTextSize;
    private int titleTextColor;
    private int titleTextMaxWidth;

    private RelativeLayout titleBarChild;
    private ImageView leftImageView;
    private TextView leftTextView;
    private TextView titleTextView;
    private TextView rightTextView;
    private ImageView rightImageView;

    public SimpleActionBar(Context context) {
        this(context, null);
    }

    public SimpleActionBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public RelativeLayout getTitleBarChild() {
        return titleBarChild;
    }

    public ImageView getLeftImageView() {
        return leftImageView;
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

    public ImageView getRightImageView() {
        return rightImageView;
    }

    @Override
    protected void initAttrs(AttributeSet attrs) {
        super.initAttrs(attrs);

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.SimpleActionBar);

        leftText = typedArray.getString(R.styleable.SimpleActionBar_simple_left_text);
        leftTextSize = mDisplayInfoUtils.px2sp(typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_text_size, mDisplayInfoUtils.sp2px(Config.TEXT_SIZE_DEF)));
        leftTextColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_left_text_color, Config.TEXT_COLOR_DEF);
        leftTextPaddingLeft = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_text_padding_left, mDisplayInfoUtils.dp2px(Config.TEXT_PADDING_DEF));
        leftTextPaddingRight = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_text_padding_right, mDisplayInfoUtils.dp2px(Config.TEXT_PADDING_DEF));
        leftImageRes = typedArray.getResourceId(R.styleable.SimpleActionBar_simple_left_image_res, Config.IMAGE_RES_DEF);
        leftImageColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_left_image_color, Config.IMAGE_COLOR_DEF);
        int leftImagePadding = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_image_padding, mDisplayInfoUtils.dp2px(Config.IMAGE_PADDING_DEF));
        leftImagePaddingLeft = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_image_padding_left, leftImagePadding);
        leftImagePaddingRight = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_image_padding_right, leftImagePadding);
        leftImagePaddingTop = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_image_padding_top, leftImagePadding);
        leftImagePaddingBottom = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_image_padding_bottom, leftImagePadding);

        rightText = typedArray.getString(R.styleable.SimpleActionBar_simple_right_text);
        rightTextSize = mDisplayInfoUtils.px2sp(typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_text_size, mDisplayInfoUtils.sp2px(Config.TEXT_SIZE_DEF)));
        rightTextColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_right_text_color, Config.TEXT_COLOR_DEF);
        rightTextPaddingLeft = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_text_padding_left, mDisplayInfoUtils.dp2px(Config.TEXT_PADDING_DEF));
        rightTextPaddingRight = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_text_padding_right, mDisplayInfoUtils.dp2px(Config.TEXT_PADDING_DEF));
        rightImageRes = typedArray.getResourceId(R.styleable.SimpleActionBar_simple_right_image_res, Config.IMAGE_RES_DEF);
        rightImageColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_right_image_color, Config.IMAGE_COLOR_DEF);
        int rightImagePadding = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_image_padding, mDisplayInfoUtils.dp2px(Config.IMAGE_PADDING_DEF));
        rightImagePaddingLeft = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_image_padding_left, rightImagePadding);
        rightImagePaddingRight = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_image_padding_right, rightImagePadding);
        rightImagePaddingTop = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_image_padding_top, rightImagePadding);
        rightImagePaddingBottom = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_image_padding_bottom, rightImagePadding);

        titleText = typedArray.getString(R.styleable.SimpleActionBar_simple_title_text);
        titleTextSize = mDisplayInfoUtils.px2sp(typedArray.getDimension(R.styleable.SimpleActionBar_simple_title_text_size, mDisplayInfoUtils.sp2px(Config.TITLE_TEXT_SIZE_DEF)));
        titleTextColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_title_text_color, Config.TITLE_TEXT_COLOR_DEF);
        titleTextMaxWidth = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_title_text_max_width, mDisplayInfoUtils.dp2px(Config.TITLE_TEXT_MAX_WIDTH_DEF));

        typedArray.recycle();
    }

    @Override
    protected View inflateTitleBar() {

        titleBarChild = (RelativeLayout) inflate(getContext(), R.layout.title_bar_simple, null);

        leftImageView = titleBarChild.findViewById(R.id.iv_left);
        leftTextView = titleBarChild.findViewById(R.id.tv_left);
        titleTextView = titleBarChild.findViewById(R.id.tv_title);
        rightTextView = titleBarChild.findViewById(R.id.tv_right);
        rightImageView = titleBarChild.findViewById(R.id.iv_right);

        if (leftImageRes > 0) {
            leftImageView.setVisibility(VISIBLE);
            leftImageView.setPadding(leftImagePaddingLeft, leftImagePaddingTop, leftImagePaddingRight, leftImagePaddingBottom);
            leftImageView.setImageResource(leftImageRes);
            leftImageView.setColorFilter(leftImageColor);
        } else {
            leftImageView.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(leftText)) {
            leftTextView.setVisibility(VISIBLE);
            leftTextView.setText(leftText);
            leftTextView.setTextColor(leftTextColor);
            leftTextView.setTextSize(leftTextSize);
            leftTextView.setPadding(leftTextPaddingLeft, 0, leftTextPaddingRight, 0);
        } else {
            leftTextView.setVisibility(GONE);
        }

        titleTextView.setVisibility(VISIBLE);
        titleTextView.setText(titleText);
        titleTextView.setTextColor(titleTextColor);
        titleTextView.setTextSize(titleTextSize);
        titleTextView.setMaxWidth(titleTextMaxWidth);

        if (rightImageRes > 0) {
            rightImageView.setVisibility(VISIBLE);
            leftImageView.setPadding(rightImagePaddingLeft, rightImagePaddingTop, rightImagePaddingRight, rightImagePaddingBottom);
            rightImageView.setImageResource(rightImageRes);
            rightImageView.setColorFilter(rightImageColor);
        } else {
            rightImageView.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(rightText)) {
            rightTextView.setVisibility(VISIBLE);
            rightTextView.setText(rightText);
            rightTextView.setTextColor(rightTextColor);
            rightTextView.setTextSize(rightTextSize);
            rightTextView.setPadding(rightTextPaddingLeft, 0, rightTextPaddingRight, 0);
        } else {
            rightTextView.setVisibility(GONE);
        }
        return titleBarChild;
    }

    public void setOnLeftImageClickListener(final OnLeftImageClickListener onLeftImageClickListener) {
        leftImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftImageClickListener != null) {
                    onLeftImageClickListener.onClick();
                }
            }
        });
    }

    public void setOnLeftTextClickListener(final OnLeftTextClickListener onLeftTextClickListener) {
        leftTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftTextClickListener != null) {
                    onLeftTextClickListener.onClick();
                }
            }
        });
    }

    public void setOnRightTextClickListener(final OnRightTextClickListener onRightTextClickListener) {
        rightTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightTextClickListener != null) {
                    onRightTextClickListener.onClick();
                }
            }
        });
    }

    public void setOnRightImageClickListener(final OnRightImageClickListener onRightImageClickListener) {
        rightImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightImageClickListener != null) {
                    onRightImageClickListener.onClick();
                }
            }
        });
    }
}
