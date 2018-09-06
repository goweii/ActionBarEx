package per.goweii.actionbarex;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
    private int leftImageDrawableRes;
    private String rightText;
    private float rightTextSize;
    private int rightTextColor;
    private int rightImageDrawableRes;
    private String titleText;
    private float titleTextSize;
    private int titleTextColor;

    private RelativeLayout rl_title_bar;
    private ImageView iv_left;
    private TextView tv_left;
    private TextView tv_title;
    private TextView tv_right;
    private ImageView iv_right;

    public SimpleActionBar(Context context) {
        this(context, null);
    }

    public SimpleActionBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RelativeLayout getTitleBarLayout() {
        return rl_title_bar;
    }

    public ImageView getLeftImageView() {
        return iv_left;
    }

    public TextView getLeftTextView() {
        return tv_left;
    }

    public TextView getTitleTextView() {
        return tv_title;
    }

    public TextView getRightTextView() {
        return tv_right;
    }

    public ImageView getRightImageView() {
        return iv_right;
    }

    @Override
    protected void initAttrs(AttributeSet attrs) {
        super.initAttrs(attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleActionBar);

        int textSizeDef = 15;
        int titleTextSizeDef = 17;
        int textColorDef = ContextCompat.getColor(context, R.color.text_black);

        leftText = typedArray.getString(R.styleable.SimpleActionBar_simple_left_text);
        leftTextSize = typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_text_size, textSizeDef);
        leftTextColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_left_text_color, textColorDef);
        leftImageDrawableRes = typedArray.getResourceId(R.styleable.SimpleActionBar_simple_left_image_res, 0);

        rightText = typedArray.getString(R.styleable.SimpleActionBar_simple_right_text);
        rightTextSize = typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_text_size, textSizeDef);
        rightTextColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_right_text_color, textColorDef);
        rightImageDrawableRes = typedArray.getResourceId(R.styleable.SimpleActionBar_simple_right_image_res, 0);

        titleText = typedArray.getString(R.styleable.SimpleActionBar_simple_title_text);
        titleTextSize = typedArray.getDimension(R.styleable.SimpleActionBar_simple_title_text_size, titleTextSizeDef);
        titleTextColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_title_text_color, textColorDef);

        typedArray.recycle();
    }

    @Override
    protected View inflateTitleBar() {

        rl_title_bar = (RelativeLayout) inflate(getContext(), R.layout.title_bar_simple, null);

        iv_left = rl_title_bar.findViewById(R.id.iv_left);
        tv_left = rl_title_bar.findViewById(R.id.tv_left);
        tv_title = rl_title_bar.findViewById(R.id.tv_title);
        tv_right = rl_title_bar.findViewById(R.id.tv_right);
        iv_right = rl_title_bar.findViewById(R.id.iv_right);

        if (leftImageDrawableRes > 0) {
            iv_left.setVisibility(VISIBLE);
            iv_left.setImageResource(leftImageDrawableRes);
        } else {
            iv_left.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(leftText)) {
            tv_left.setVisibility(VISIBLE);
            tv_left.setText(leftText);
            tv_left.setTextColor(leftTextColor);
            tv_left.setTextSize(leftTextSize);
        } else {
            tv_left.setVisibility(GONE);
        }

        tv_title.setVisibility(VISIBLE);
        tv_title.setText(titleText);
        tv_title.setTextColor(titleTextColor);
        tv_title.setTextSize(titleTextSize);

        if (!TextUtils.isEmpty(rightText)) {
            tv_right.setVisibility(VISIBLE);
            tv_right.setText(rightText);
            tv_right.setTextColor(rightTextColor);
            tv_right.setTextSize(rightTextSize);
        } else {
            tv_right.setVisibility(GONE);
        }

        if (rightImageDrawableRes > 0) {
            iv_right.setVisibility(VISIBLE);
            iv_right.setImageResource(rightImageDrawableRes);
        } else {
            iv_right.setVisibility(GONE);
        }
        return rl_title_bar;
    }

    public void setOnLeftImageClickListener(final OnLeftImageClickListener onLeftImageClickListener) {
        iv_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftImageClickListener != null){
                    onLeftImageClickListener.onClick();
                }
            }
        });
    }

    public void setOnLeftTextClickListener(final OnLeftTextClickListener onLeftTextClickListener) {
        tv_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftTextClickListener != null){
                    onLeftTextClickListener.onClick();
                }
            }
        });
    }

    public void setOnRightTextClickListener(final OnRightTextClickListener onRightTextClickListener) {
        tv_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightTextClickListener != null){
                    onRightTextClickListener.onClick();
                }
            }
        });
    }

    public void setOnRightImageClickListener(final OnRightImageClickListener onRightImageClickListener) {
        iv_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightImageClickListener != null){
                    onRightImageClickListener.onClick();
                }
            }
        });
    }
}
