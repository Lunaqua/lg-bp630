package android.support.v4.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.af;
import android.support.annotation.an;
import android.support.v4.view.ab;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d extends Drawable implements Animatable {
    public static final int a = 0;
    public static final int b = 1;
    private static final float g = 11.0f;
    private static final float h = 3.0f;
    private static final int i = 12;
    private static final int j = 6;
    private static final float k = 7.5f;
    private static final float l = 2.5f;
    private static final int m = 10;
    private static final int n = 5;
    private static final float p = 0.75f;
    private static final float q = 0.5f;
    private static final int r = 1332;
    private static final float s = 216.0f;
    private static final float v = 0.8f;
    private static final float w = 0.01f;
    private static final float x = 0.20999998f;
    float c;
    boolean d;
    private final b t = new b();
    private float u;
    private Resources y;
    private Animator z;
    private static final Interpolator e = new LinearInterpolator();
    private static final Interpolator f = new android.support.v4.view.b.b();
    private static final int[] o = {ab.s};

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        int[] i;
        int j;
        float k;
        float l;
        float m;
        boolean n;
        Path o;
        float q;
        int r;
        int s;
        int u;
        final RectF a = new RectF();
        final Paint b = new Paint();
        final Paint c = new Paint();
        final Paint d = new Paint();
        float e = 0.0f;
        float f = 0.0f;
        float g = 0.0f;
        float h = 5.0f;
        float p = 1.0f;
        int t = 255;

        b() {
            this.b.setStrokeCap(Paint.Cap.SQUARE);
            this.b.setAntiAlias(true);
            this.b.setStyle(Paint.Style.STROKE);
            this.c.setStyle(Paint.Style.FILL);
            this.c.setAntiAlias(true);
            this.d.setColor(0);
        }

        Paint.Cap a() {
            return this.b.getStrokeCap();
        }

        void a(float f) {
            this.h = f;
            this.b.setStrokeWidth(f);
        }

        void a(float f, float f2) {
            this.r = (int) f;
            this.s = (int) f2;
        }

        void a(int i) {
            this.u = i;
        }

        void a(Canvas canvas, float f, float f2, RectF rectF) {
            if (this.n) {
                Path path = this.o;
                if (path == null) {
                    this.o = new Path();
                    this.o.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                this.o.moveTo(0.0f, 0.0f);
                this.o.lineTo(this.r * this.p, 0.0f);
                Path path2 = this.o;
                float f3 = this.p;
                path2.lineTo((this.r * f3) / 2.0f, this.s * f3);
                this.o.offset(((Math.min(rectF.width(), rectF.height()) / 2.0f) + rectF.centerX()) - ((this.r * this.p) / 2.0f), rectF.centerY() + (this.h / 2.0f));
                this.o.close();
                this.c.setColor(this.u);
                this.c.setAlpha(this.t);
                canvas.save();
                canvas.rotate(f + f2, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.o, this.c);
                canvas.restore();
            }
        }

        void a(Canvas canvas, Rect rect) {
            RectF rectF = this.a;
            float f = this.q;
            float f2 = (this.h / 2.0f) + f;
            if (f <= 0.0f) {
                f2 = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.r * this.p) / 2.0f, this.h / 2.0f);
            }
            rectF.set(rect.centerX() - f2, rect.centerY() - f2, rect.centerX() + f2, rect.centerY() + f2);
            float f3 = this.e;
            float f4 = this.g;
            float f5 = (f3 + f4) * 360.0f;
            float f6 = ((this.f + f4) * 360.0f) - f5;
            this.b.setColor(this.u);
            this.b.setAlpha(this.t);
            float f7 = this.h / 2.0f;
            rectF.inset(f7, f7);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.d);
            float f8 = -f7;
            rectF.inset(f8, f8);
            canvas.drawArc(rectF, f5, f6, false, this.b);
            a(canvas, f5, f6, rectF);
        }

        void a(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }

        void a(Paint.Cap cap) {
            this.b.setStrokeCap(cap);
        }

        void a(boolean z) {
            if (this.n != z) {
                this.n = z;
            }
        }

        void a(@af int[] iArr) {
            this.i = iArr;
            c(0);
        }

        float b() {
            return this.r;
        }

        void b(float f) {
            this.e = f;
        }

        void b(int i) {
            this.d.setColor(i);
        }

        float c() {
            return this.s;
        }

        void c(float f) {
            this.f = f;
        }

        void c(int i) {
            this.j = i;
            this.u = this.i[this.j];
        }

        void d(float f) {
            this.g = f;
        }

        void d(int i) {
            this.t = i;
        }

        int[] d() {
            return this.i;
        }

        int e() {
            return this.d.getColor();
        }

        void e(float f) {
            this.q = f;
        }

        int f() {
            return this.i[g()];
        }

        void f(float f) {
            if (f != this.p) {
                this.p = f;
            }
        }

        int g() {
            return (this.j + 1) % this.i.length;
        }

        void h() {
            c(g());
        }

        int i() {
            return this.t;
        }

        float j() {
            return this.h;
        }

        float k() {
            return this.e;
        }

        float l() {
            return this.k;
        }

        float m() {
            return this.l;
        }

        int n() {
            return this.i[this.j];
        }

        float o() {
            return this.f;
        }

        float p() {
            return this.g;
        }

        float q() {
            return this.q;
        }

        boolean r() {
            return this.n;
        }

        float s() {
            return this.p;
        }

        float t() {
            return this.m;
        }

        void u() {
            this.k = this.e;
            this.l = this.f;
            this.m = this.g;
        }

        void v() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            b(0.0f);
            c(0.0f);
            d(0.0f);
        }
    }

    public d(@af Context context) {
        this.y = ((Context) android.support.v4.j.q.a(context)).getResources();
        this.t.a(o);
        a(l);
        n();
    }

    private int a(float f2, int i2, int i3) {
        int i4 = (i2 >> 24) & 255;
        int i5 = (i2 >> 16) & 255;
        int i6 = (i2 >> 8) & 255;
        int i7 = i2 & 255;
        return ((i4 + ((int) ((((i3 >> 24) & 255) - i4) * f2))) << 24) | ((i5 + ((int) ((((i3 >> 16) & 255) - i5) * f2))) << 16) | ((i6 + ((int) ((((i3 >> 8) & 255) - i6) * f2))) << 8) | (i7 + ((int) (f2 * ((i3 & 255) - i7))));
    }

    private void a(float f2, float f3, float f4, float f5) {
        b bVar = this.t;
        float f6 = this.y.getDisplayMetrics().density;
        bVar.a(f3 * f6);
        bVar.e(f2 * f6);
        bVar.c(0);
        bVar.a(f4 * f6, f5 * f6);
    }

    private void b(float f2, b bVar) {
        a(f2, bVar);
        bVar.b(bVar.l() + (((bVar.m() - w) - bVar.l()) * f2));
        bVar.c(bVar.m());
        bVar.d(bVar.t() + ((((float) (Math.floor(bVar.t() / v) + 1.0d)) - bVar.t()) * f2));
    }

    private void e(float f2) {
        this.u = f2;
    }

    private float m() {
        return this.u;
    }

    private void n() {
        final b bVar = this.t;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v4.widget.d.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                d.this.a(floatValue, bVar);
                d.this.a(floatValue, bVar, false);
                d.this.invalidateSelf();
            }
        });
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(e);
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: android.support.v4.widget.d.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                d.this.a(1.0f, bVar, true);
                bVar.u();
                bVar.h();
                if (!d.this.d) {
                    d.this.c += 1.0f;
                    return;
                }
                d.this.d = false;
                animator.cancel();
                animator.setDuration(1332L);
                animator.start();
                bVar.a(false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                d.this.c = 0.0f;
            }
        });
        this.z = ofFloat;
    }

    public float a() {
        return this.t.j();
    }

    public void a(float f2) {
        this.t.a(f2);
        invalidateSelf();
    }

    public void a(float f2, float f3) {
        this.t.a(f2, f3);
        invalidateSelf();
    }

    void a(float f2, b bVar) {
        bVar.a(f2 > p ? a((f2 - p) / 0.25f, bVar.n(), bVar.f()) : bVar.n());
    }

    void a(float f2, b bVar, boolean z) {
        float l2;
        float interpolation;
        if (this.d) {
            b(f2, bVar);
        } else if (f2 != 1.0f || z) {
            float t = bVar.t();
            if (f2 < q) {
                float f3 = f2 / q;
                float l3 = bVar.l();
                l2 = (f.getInterpolation(f3) * 0.79f) + w + l3;
                interpolation = l3;
            } else {
                float f4 = (f2 - q) / q;
                l2 = bVar.l() + 0.79f;
                interpolation = l2 - (((1.0f - f.getInterpolation(f4)) * 0.79f) + w);
            }
            float f5 = (f2 + this.c) * s;
            bVar.b(interpolation);
            bVar.c(l2);
            bVar.d(t + (x * f2));
            e(f5);
        }
    }

    public void a(int i2) {
        float f2;
        float f3;
        float f4;
        float f5;
        if (i2 == 0) {
            f2 = g;
            f3 = h;
            f4 = 12.0f;
            f5 = 6.0f;
        } else {
            f2 = k;
            f3 = l;
            f4 = 10.0f;
            f5 = 5.0f;
        }
        a(f2, f3, f4, f5);
        invalidateSelf();
    }

    public void a(@af Paint.Cap cap) {
        this.t.a(cap);
        invalidateSelf();
    }

    public void a(boolean z) {
        this.t.a(z);
        invalidateSelf();
    }

    public void a(@af int... iArr) {
        this.t.a(iArr);
        this.t.c(0);
        invalidateSelf();
    }

    public float b() {
        return this.t.q();
    }

    public void b(float f2) {
        this.t.e(f2);
        invalidateSelf();
    }

    public void b(float f2, float f3) {
        this.t.b(f2);
        this.t.c(f3);
        invalidateSelf();
    }

    public void b(int i2) {
        this.t.b(i2);
        invalidateSelf();
    }

    @af
    public Paint.Cap c() {
        return this.t.a();
    }

    public void c(float f2) {
        this.t.f(f2);
        invalidateSelf();
    }

    public float d() {
        return this.t.b();
    }

    public void d(float f2) {
        this.t.d(f2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.u, bounds.exactCenterX(), bounds.exactCenterY());
        this.t.a(canvas, bounds);
        canvas.restore();
    }

    public float e() {
        return this.t.c();
    }

    public boolean f() {
        return this.t.r();
    }

    public float g() {
        return this.t.s();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.t.i();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public float h() {
        return this.t.k();
    }

    public float i() {
        return this.t.o();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.z.isRunning();
    }

    public float j() {
        return this.t.p();
    }

    public int k() {
        return this.t.e();
    }

    @af
    public int[] l() {
        return this.t.d();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.t.d(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.t.a(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Animator animator;
        long j2;
        this.z.cancel();
        this.t.u();
        if (this.t.o() != this.t.k()) {
            this.d = true;
            animator = this.z;
            j2 = 666;
        } else {
            this.t.c(0);
            this.t.v();
            animator = this.z;
            j2 = 1332;
        }
        animator.setDuration(j2);
        this.z.start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.z.cancel();
        e(0.0f);
        this.t.a(false);
        this.t.c(0);
        this.t.v();
        invalidateSelf();
    }
}
