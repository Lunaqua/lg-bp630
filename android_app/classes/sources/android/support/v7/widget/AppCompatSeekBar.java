package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.widget.SeekBar;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AppCompatSeekBar extends SeekBar {
    private final k a;

    public AppCompatSeekBar(Context context) {
        this(context, null);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.seekBarStyle);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new k(this);
        this.a.a(attributeSet, i);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.a.f();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.a.e();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.a.a(canvas);
    }
}
