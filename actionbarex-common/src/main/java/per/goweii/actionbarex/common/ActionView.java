package per.goweii.actionbarex.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

public final class ActionView extends FrameLayout {

    private String text;
    private float textSize;
    private int textColor;
    private int[] textPadding = {0, 0, 0, 0};
    private int[] textMargin = {0, 0, 0, 0};
    private int iconRes;
    private int iconColor;
    private int[] iconPadding = {0, 0, 0, 0};
    private int[] iconMargin = {0, 0, 0, 0};

    private ActionIconView iconView;
    private ActionTextView textView;

    public ActionView(Context context) {
        this(context, null);
    }

    public ActionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
    }

    private void initAttrs(AttributeSet attrs) {
        final float textSizeDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_text_size_def);
        final int textColorDef = ContextCompat.getColor(getContext(), R.color.actionbarex_common_title_bar_text_color_def);
        final float textPaddingLeftDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_text_padding_left_def);
        final float textPaddingRightDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_text_padding_right_def);
        final int iconColorDef = ContextCompat.getColor(getContext(), R.color.actionbarex_common_title_bar_icon_color_def);
        final float iconPaddingDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_icon_padding_def);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ActionView);
        text = typedArray.getString(R.styleable.ActionView_av_text);
        textSize = typedArray.getDimension(R.styleable.ActionView_av_textSize, textSizeDef);
        textColor = typedArray.getColor(R.styleable.ActionView_av_textSize, textColorDef);
        int textPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionView_av_textPadding, -1);
        textPadding[0] = (int) typedArray.getDimension(R.styleable.ActionView_av_textPaddingLeft, textPaddingTemp >= 0 ? textPaddingTemp : textPaddingLeftDef);
        textPadding[1] = (int) typedArray.getDimension(R.styleable.ActionView_av_textPaddingTop, textPaddingTemp >= 0 ? textPaddingTemp : 0);
        textPadding[2] = (int) typedArray.getDimension(R.styleable.ActionView_av_textPaddingRight, textPaddingTemp >= 0 ? textPaddingTemp : textPaddingRightDef);
        textPadding[3] = (int) typedArray.getDimension(R.styleable.ActionView_av_textPaddingBottom, textPaddingTemp >= 0 ? textPaddingTemp : 0);
        int textMarginTemp = (int) typedArray.getDimension(R.styleable.ActionView_av_textMargin, -1);
        textMargin[0] = (int) typedArray.getDimension(R.styleable.ActionView_av_textMarginLeft, textMarginTemp >= 0 ? textMarginTemp : 0);
        textMargin[1] = (int) typedArray.getDimension(R.styleable.ActionView_av_textMarginTop, textMarginTemp >= 0 ? textMarginTemp : 0);
        textMargin[2] = (int) typedArray.getDimension(R.styleable.ActionView_av_textMarginRight, textMarginTemp >= 0 ? textMarginTemp : 0);
        textMargin[3] = (int) typedArray.getDimension(R.styleable.ActionView_av_textMarginBottom, textMarginTemp >= 0 ? textMarginTemp : 0);
        iconRes = typedArray.getResourceId(R.styleable.ActionView_av_icon, 0);
        iconColor = typedArray.getColor(R.styleable.ActionView_av_iconColor, iconColorDef);
        int iconPaddingTemp = (int) typedArray.getDimension(R.styleable.ActionView_av_iconPadding, -1);
        iconPadding[0] = (int) typedArray.getDimension(R.styleable.ActionView_av_iconPaddingLeft, iconPaddingTemp >= 0 ? iconPaddingTemp : iconPaddingDef);
        iconPadding[1] = (int) typedArray.getDimension(R.styleable.ActionView_av_iconPaddingTop, iconPaddingTemp >= 0 ? iconPaddingTemp : iconPaddingDef);
        iconPadding[2] = (int) typedArray.getDimension(R.styleable.ActionView_av_iconPaddingRight, iconPaddingTemp >= 0 ? iconPaddingTemp : iconPaddingDef);
        iconPadding[3] = (int) typedArray.getDimension(R.styleable.ActionView_av_iconPaddingBottom, iconPaddingTemp >= 0 ? iconPaddingTemp : iconPaddingDef);
        int iconMarginTemp = (int) typedArray.getDimension(R.styleable.ActionView_av_iconMargin, -1);
        iconMargin[0] = (int) typedArray.getDimension(R.styleable.ActionView_av_iconMarginLeft, iconMarginTemp >= 0 ? iconMarginTemp : 0);
        iconMargin[1] = (int) typedArray.getDimension(R.styleable.ActionView_av_iconMarginTop, iconMarginTemp >= 0 ? iconMarginTemp : 0);
        iconMargin[2] = (int) typedArray.getDimension(R.styleable.ActionView_av_iconMarginRight, iconMarginTemp >= 0 ? iconMarginTemp : 0);
        iconMargin[3] = (int) typedArray.getDimension(R.styleable.ActionView_av_iconMarginBottom, iconMarginTemp >= 0 ? iconMarginTemp : 0);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView() {
        iconView = createIconView();
        textView = createTextView();
        addViewInLayout(iconView, getChildCount(), iconView.getLayoutParams());
        addViewInLayout(textView, getChildCount(), textView.getLayoutParams());
        setTextColor(textColor);
        setTextSizePx(textSize);
        setIconColorInt(iconColor);
        if (iconRes > 0) {
            setIcon(iconRes);
        } else if (!TextUtils.isEmpty(text)) {
            setText(text);
        } else {
            toggleToGone();
        }
    }

    private ActionIconView createIconView() {
        ActionIconView iconView = new ActionIconView(getContext());
        iconView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        layoutParams.leftMargin = iconMargin[0];
        layoutParams.topMargin = iconMargin[1];
        layoutParams.rightMargin = iconMargin[2];
        layoutParams.bottomMargin = iconMargin[3];
        iconView.setLayoutParams(layoutParams);
        iconView.setPadding(iconPadding[0], iconPadding[1], iconPadding[2], iconPadding[3]);
        iconView.setVisibility(GONE);
        return iconView;
    }

    private ActionTextView createTextView() {
        ActionTextView textView = new ActionTextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setSingleLine(true);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        layoutParams.leftMargin = textMargin[0];
        layoutParams.topMargin = textMargin[1];
        layoutParams.rightMargin = textMargin[2];
        layoutParams.bottomMargin = textMargin[3];
        textView.setLayoutParams(layoutParams);
        textView.setPadding(textPadding[0], textPadding[1], textPadding[2], textPadding[3]);
        textView.setVisibility(GONE);
        return textView;
    }

    public ActionTextView getTextView() {
        return textView;
    }

    public ActionIconView getIconView() {
        return iconView;
    }

    public void toggleToText() {
        iconView.setVisibility(GONE);
        textView.setVisibility(VISIBLE);
        setVisibility(VISIBLE);
    }

    public void toggleToIcon() {
        textView.setVisibility(GONE);
        iconView.setVisibility(VISIBLE);
        setVisibility(VISIBLE);
    }

    public void toggleToGone() {
        textView.setVisibility(GONE);
        iconView.setVisibility(GONE);
        setVisibility(GONE);
    }

    public void setText(@NonNull CharSequence text) {
        textView.setText(text);
        toggleToText();
    }

    public void setText(@StringRes int textId) {
        textView.setText(textId);
        toggleToText();
    }

    public void setTextSize(float sp) {
        textView.setTextSize(sp);
    }

    public void setTextSizePx(float px) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, px);
    }

    public void setTextColor(@ColorInt int colorInt) {
        textView.setTextColor(colorInt);
    }

    public void setTextColorRes(@ColorRes int colorRes) {
        textView.setTextColor(ContextCompat.getColor(getContext(), colorRes));
    }

    public void setTextPadding(int left, int top, int right, int bottom){
        textView.setPadding(left, top, right, bottom);
    }

    public void setTextMargin(int left, int top, int right, int bottom){
        LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        layoutParams.leftMargin = left;
        layoutParams.topMargin = top;
        layoutParams.rightMargin = right;
        layoutParams.bottomMargin = bottom;
        textView.setLayoutParams(layoutParams);
    }

    public void setIcon(@DrawableRes int iconId) {
        iconView.setImageResource(iconId);
        toggleToIcon();
    }

    public void setIcon(Drawable icon) {
        iconView.setImageDrawable(icon);
        toggleToIcon();
    }

    public void setIcon(Bitmap icon) {
        iconView.setImageBitmap(icon);
        toggleToIcon();
    }

    public void setIconColorInt(@ColorInt int colorInt) {
        iconView.setColorFilter(colorInt);
    }

    public void setIconColorRes(@ColorRes int colorRes) {
        iconView.setColorFilter(ContextCompat.getColor(getContext(), colorRes));
    }

    public void setIconPadding(int left, int top, int right, int bottom){
        iconView.setPadding(left, top, right, bottom);
    }

    public void setIconMargin(int left, int top, int right, int bottom){
        LayoutParams layoutParams = (LayoutParams) iconView.getLayoutParams();
        layoutParams.leftMargin = left;
        layoutParams.topMargin = top;
        layoutParams.rightMargin = right;
        layoutParams.bottomMargin = bottom;
        iconView.setLayoutParams(layoutParams);
    }

}
