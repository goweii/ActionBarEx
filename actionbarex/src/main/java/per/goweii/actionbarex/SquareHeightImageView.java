package per.goweii.actionbarex;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public final class SquareHeightImageView extends AppCompatImageView {

    public SquareHeightImageView(Context context) {
        this(context, null);
    }

    public SquareHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(heightSize, heightSize);
    }
}
