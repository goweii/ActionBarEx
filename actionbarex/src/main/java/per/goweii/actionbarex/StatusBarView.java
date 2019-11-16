package per.goweii.actionbarex;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import per.goweii.statusbarcompat.StatusBarCompat;

/**
 * @author CuiZhen
 * @date 2019/11/16
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class StatusBarView extends View {

    public StatusBarView(Context context) {
        super(context);
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = StatusBarCompat.getHeight(getContext());
        setMeasuredDimension(width, height);
    }

    public boolean isVisibility() {
        return getVisibility() == VISIBLE;
    }

    public void setVisibility(boolean visible) {
        setVisibility(visible ? VISIBLE : GONE);
    }
}
