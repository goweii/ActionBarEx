package per.goweii.actionbarex;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
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
public final class SearchActionBar extends ActionBarEx {

    private String leftText;
    private float leftTextSize;
    private int leftTextColor;
    private int leftImageRes;
    private int leftImageColor;
    private int leftImagePadding;
    private String rightText;
    private float rightTextSize;
    private int rightTextColor;
    private int rightImageRes;
    private int rightImageColor;
    private int rightImagePadding;
    private String titleHintText;
    private float titleTextSize;
    private int titleTextColor;
    private int titleHintColor;

    private RelativeLayout titleBarChild;
    private ImageView leftImageView;
    private TextView leftTextView;
    private EditText titleEditText;
    private TextView rightTextView;
    private ImageView rightImageView;

    public SearchActionBar(Context context) {
        this(context, null);
    }

    public SearchActionBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RelativeLayout getTitleBarChild() {
        return titleBarChild;
    }

    public ImageView getLeftImageView() {
        return leftImageView;
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

    public ImageView getRightImageView() {
        return rightImageView;
    }

    @Override
    protected void initAttrs(AttributeSet attrs) {
        super.initAttrs(attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchActionBar);

        leftText = typedArray.getString(R.styleable.SearchActionBar_search_left_text);
        leftTextSize = typedArray.getDimension(R.styleable.SearchActionBar_search_left_text_size, Config.TEXT_SIZE_DEF);
        leftTextColor = typedArray.getColor(R.styleable.SearchActionBar_search_left_text_color, Config.TEXT_COLOR_DEF);
        leftImageRes = typedArray.getResourceId(R.styleable.SearchActionBar_search_left_image_res, Config.IMAGE_RES_DEF);
        leftImageColor = typedArray.getColor(R.styleable.SearchActionBar_search_left_image_color, Config.IMAGE_COLOR_DEF);
        leftImagePadding = (int) typedArray.getDimension(R.styleable.SearchActionBar_search_left_image_padding, utils.dp2px(Config.IMAGE_PADDING_DEF));

        rightText = typedArray.getString(R.styleable.SearchActionBar_search_right_text);
        rightTextSize = typedArray.getDimension(R.styleable.SearchActionBar_search_right_text_size, Config.TEXT_SIZE_DEF);
        rightTextColor = typedArray.getColor(R.styleable.SearchActionBar_search_right_text_color, Config.TEXT_COLOR_DEF);
        rightImageRes = typedArray.getResourceId(R.styleable.SearchActionBar_search_right_image_res, Config.IMAGE_RES_DEF);
        rightImageColor = typedArray.getColor(R.styleable.SearchActionBar_search_right_image_color, Config.IMAGE_COLOR_DEF);
        rightImagePadding = (int) typedArray.getDimension(R.styleable.SearchActionBar_search_right_image_padding, utils.dp2px(Config.IMAGE_PADDING_DEF));

        titleHintText = typedArray.getString(R.styleable.SearchActionBar_search_title_hint_text);
        titleTextSize = typedArray.getDimension(R.styleable.SearchActionBar_search_title_text_size, Config.TITLE_TEXT_SIZE_DEF);
        titleTextColor = typedArray.getColor(R.styleable.SearchActionBar_search_title_text_color, Config.TITLE_TEXT_COLOR_DEF);
        titleHintColor = typedArray.getColor(R.styleable.SearchActionBar_search_title_hint_color, Config.TITLE_TEXT_HINT_COLOR_DEF);

        typedArray.recycle();
    }

    @Override
    protected View inflateTitleBar() {

        titleBarChild = (RelativeLayout) inflate(getContext(), R.layout.title_bar_search, null);

        leftImageView = titleBarChild.findViewById(R.id.iv_left);
        leftTextView = titleBarChild.findViewById(R.id.tv_left);
        titleEditText = titleBarChild.findViewById(R.id.et_title);
        rightTextView = titleBarChild.findViewById(R.id.tv_right);
        rightImageView = titleBarChild.findViewById(R.id.iv_right);

        if (leftImageRes > 0) {
            leftImageView.setVisibility(VISIBLE);
            leftImageView.setPadding(leftImagePadding, leftImagePadding, leftImagePadding, leftImagePadding);
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
        } else {
            leftTextView.setVisibility(GONE);
        }

        titleEditText.setVisibility(VISIBLE);
        titleEditText.setHint(titleHintText);
        titleEditText.setTextColor(titleTextColor);
        titleEditText.setTextSize(titleTextSize);
        titleEditText.setHintTextColor(titleHintColor);

        if (!TextUtils.isEmpty(rightText)) {
            rightTextView.setVisibility(VISIBLE);
            rightTextView.setText(rightText);
            rightTextView.setTextColor(rightTextColor);
            rightTextView.setTextSize(rightTextSize);
        } else {
            rightTextView.setVisibility(GONE);
        }

        if (rightImageRes > 0) {
            rightImageView.setVisibility(VISIBLE);
            rightImageView.setPadding(rightImagePadding, rightImagePadding, rightImagePadding, rightImagePadding);
            rightImageView.setImageResource(rightImageRes);
            rightImageView.setColorFilter(rightImageColor);
        } else {
            rightImageView.setVisibility(GONE);
        }
        return titleBarChild;
    }

    public void setOnLeftImageClickListener(final OnLeftImageClickListener onLeftImageClickListener) {
        leftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftImageClickListener != null) {
                    onLeftImageClickListener.onClick();
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

    public void setOnRightImageClickListener(final OnRightImageClickListener onRightImageClickListener) {
        rightImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightImageClickListener != null) {
                    onRightImageClickListener.onClick();
                }
            }
        });
    }
}
