package per.goweii.actionbarex.common;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public final class ActionBarIconView extends AppCompatImageView {

    public ActionBarIconView(Context context) {
        this(context, null);
    }

    public ActionBarIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(heightSize, heightSize);
    }
}
