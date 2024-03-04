package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.widget.SeekBar;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class k extends j {
    private final SeekBar a;
    private Drawable b;
    private ColorStateList c;
    private PorterDuff.Mode d;
    private boolean e;
    private boolean f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(SeekBar seekBar) {
        super(seekBar);
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.a = seekBar;
    }

    private void g() {
        if (this.b != null) {
            if (this.e || this.f) {
                this.b = android.support.v4.graphics.drawable.a.g(this.b.mutate());
                if (this.e) {
                    android.support.v4.graphics.drawable.a.a(this.b, this.c);
                }
                if (this.f) {
                    android.support.v4.graphics.drawable.a.a(this.b, this.d);
                }
                if (this.b.isStateful()) {
                    this.b.setState(this.a.getDrawableState());
                }
            }
        }
    }

    void a(@android.support.annotation.ag ColorStateList colorStateList) {
        this.c = colorStateList;
        this.e = true;
        g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Canvas canvas) {
        if (this.b != null) {
            int max = this.a.getMax();
            if (max > 1) {
                int intrinsicWidth = this.b.getIntrinsicWidth();
                int intrinsicHeight = this.b.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.b.setBounds(-i, -i2, i, i2);
                float width = ((this.a.getWidth() - this.a.getPaddingLeft()) - this.a.getPaddingRight()) / max;
                int save = canvas.save();
                canvas.translate(this.a.getPaddingLeft(), this.a.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.b.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }

    void a(@android.support.annotation.ag PorterDuff.Mode mode) {
        this.d = mode;
        this.f = true;
        g();
    }

    void a(@android.support.annotation.ag Drawable drawable) {
        Drawable drawable2 = this.b;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.b = drawable;
        if (drawable != null) {
            drawable.setCallback(this.a);
            android.support.v4.graphics.drawable.a.b(drawable, android.support.v4.view.ab.m(this.a));
            if (drawable.isStateful()) {
                drawable.setState(this.a.getDrawableState());
            }
            g();
        }
        this.a.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.widget.j
    public void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        av a = av.a(this.a.getContext(), attributeSet, a.l.AppCompatSeekBar, i, 0);
        Drawable b = a.b(a.l.AppCompatSeekBar_android_thumb);
        if (b != null) {
            this.a.setThumb(b);
        }
        a(a.a(a.l.AppCompatSeekBar_tickMark));
        if (a.j(a.l.AppCompatSeekBar_tickMarkTintMode)) {
            this.d = s.a(a.a(a.l.AppCompatSeekBar_tickMarkTintMode, -1), this.d);
            this.f = true;
        }
        if (a.j(a.l.AppCompatSeekBar_tickMarkTint)) {
            this.c = a.g(a.l.AppCompatSeekBar_tickMarkTint);
            this.e = true;
        }
        a.e();
        g();
    }

    @android.support.annotation.ag
    Drawable b() {
        return this.b;
    }

    @android.support.annotation.ag
    ColorStateList c() {
        return this.c;
    }

    @android.support.annotation.ag
    PorterDuff.Mode d() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        Drawable drawable = this.b;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.a.getDrawableState())) {
            this.a.invalidateDrawable(drawable);
        }
    }
}
