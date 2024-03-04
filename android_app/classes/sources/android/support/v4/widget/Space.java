package android.support.v4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.util.AttributeSet;
import android.view.View;
@Deprecated
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class Space extends View {
    @Deprecated
    public Space(@af Context context) {
        this(context, null);
    }

    @Deprecated
    public Space(@af Context context, @ag AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    public Space(@af Context context, @ag AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getVisibility() == 0) {
            setVisibility(4);
        }
    }

    private static int a(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        return mode != Integer.MIN_VALUE ? (mode == 0 || mode != 1073741824) ? i : size : Math.min(i, size);
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    @Deprecated
    public void draw(Canvas canvas) {
    }

    @Override // android.view.View
    @Deprecated
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(a(getSuggestedMinimumWidth(), i), a(getSuggestedMinimumHeight(), i2));
    }
}
