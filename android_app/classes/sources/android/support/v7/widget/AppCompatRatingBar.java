package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AppCompatRatingBar extends RatingBar {
    private final j a;

    public AppCompatRatingBar(Context context) {
        this(context, null);
    }

    public AppCompatRatingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.ratingBarStyle);
    }

    public AppCompatRatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new j(this);
        this.a.a(attributeSet, i);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Bitmap a = this.a.a();
        if (a != null) {
            setMeasuredDimension(View.resolveSizeAndState(a.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        }
    }
}
