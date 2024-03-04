package android.support.v7.c.a;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.an;
import android.support.annotation.k;
import android.support.annotation.q;
import android.support.v7.a.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d extends Drawable {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    private static final float f = (float) Math.toRadians(45.0d);
    private float g;
    private float h;
    private float i;
    private float j;
    private boolean k;
    private final int m;
    private float o;
    private float p;
    private final Paint e = new Paint();
    private final Path l = new Path();
    private boolean n = false;
    private int q = 2;

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
    }

    public d(Context context) {
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setStrokeJoin(Paint.Join.MITER);
        this.e.setStrokeCap(Paint.Cap.BUTT);
        this.e.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, a.l.DrawerArrowToggle, a.b.drawerArrowStyle, a.k.Base_Widget_AppCompat_DrawerArrowToggle);
        a(obtainStyledAttributes.getColor(a.l.DrawerArrowToggle_color, 0));
        d(obtainStyledAttributes.getDimension(a.l.DrawerArrowToggle_thickness, 0.0f));
        a(obtainStyledAttributes.getBoolean(a.l.DrawerArrowToggle_spinBars, true));
        e(Math.round(obtainStyledAttributes.getDimension(a.l.DrawerArrowToggle_gapBetweenBars, 0.0f)));
        this.m = obtainStyledAttributes.getDimensionPixelSize(a.l.DrawerArrowToggle_drawableSize, 0);
        this.h = Math.round(obtainStyledAttributes.getDimension(a.l.DrawerArrowToggle_barLength, 0.0f));
        this.g = Math.round(obtainStyledAttributes.getDimension(a.l.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.i = obtainStyledAttributes.getDimension(a.l.DrawerArrowToggle_arrowShaftLength, 0.0f);
        obtainStyledAttributes.recycle();
    }

    private static float a(float f2, float f3, float f4) {
        return f2 + ((f3 - f2) * f4);
    }

    public float a() {
        return this.g;
    }

    public void a(float f2) {
        if (this.g != f2) {
            this.g = f2;
            invalidateSelf();
        }
    }

    public void a(@k int i) {
        if (i != this.e.getColor()) {
            this.e.setColor(i);
            invalidateSelf();
        }
    }

    public void a(boolean z) {
        if (this.k != z) {
            this.k = z;
            invalidateSelf();
        }
    }

    public float b() {
        return this.i;
    }

    public void b(float f2) {
        if (this.i != f2) {
            this.i = f2;
            invalidateSelf();
        }
    }

    public void b(int i) {
        if (i != this.q) {
            this.q = i;
            invalidateSelf();
        }
    }

    public void b(boolean z) {
        if (this.n != z) {
            this.n = z;
            invalidateSelf();
        }
    }

    public float c() {
        return this.h;
    }

    public void c(float f2) {
        if (this.h != f2) {
            this.h = f2;
            invalidateSelf();
        }
    }

    @k
    public int d() {
        return this.e.getColor();
    }

    public void d(float f2) {
        if (this.e.getStrokeWidth() != f2) {
            this.e.setStrokeWidth(f2);
            double d2 = f2 / 2.0f;
            double cos = Math.cos(f);
            Double.isNaN(d2);
            this.p = (float) (d2 * cos);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float f2;
        Rect bounds = getBounds();
        int i = this.q;
        boolean z = false;
        if (i != 0 && (i == 1 || (i == 3 ? android.support.v4.graphics.drawable.a.i(this) == 0 : android.support.v4.graphics.drawable.a.i(this) == 1))) {
            z = true;
        }
        float f3 = this.g;
        float a2 = a(this.h, (float) Math.sqrt(f3 * f3 * 2.0f), this.o);
        float a3 = a(this.h, this.i, this.o);
        float round = Math.round(a(0.0f, this.p, this.o));
        float a4 = a(0.0f, f, this.o);
        float a5 = a(z ? 0.0f : -180.0f, z ? 180.0f : 0.0f, this.o);
        double d2 = a2;
        double d3 = a4;
        double cos = Math.cos(d3);
        Double.isNaN(d2);
        boolean z2 = z;
        float round2 = (float) Math.round(cos * d2);
        double sin = Math.sin(d3);
        Double.isNaN(d2);
        float round3 = (float) Math.round(d2 * sin);
        this.l.rewind();
        float a6 = a(this.j + this.e.getStrokeWidth(), -this.p, this.o);
        float f4 = (-a3) / 2.0f;
        this.l.moveTo(f4 + round, 0.0f);
        this.l.rLineTo(a3 - (round * 2.0f), 0.0f);
        this.l.moveTo(f4, a6);
        this.l.rLineTo(round2, round3);
        this.l.moveTo(f4, -a6);
        this.l.rLineTo(round2, -round3);
        this.l.close();
        canvas.save();
        float strokeWidth = this.e.getStrokeWidth();
        float height = bounds.height() - (3.0f * strokeWidth);
        canvas.translate(bounds.centerX(), ((((int) (height - (2.0f * f2))) / 4) * 2) + (strokeWidth * 1.5f) + this.j);
        if (this.k) {
            canvas.rotate(a5 * (this.n ^ z2 ? -1 : 1));
        } else if (z2) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.l, this.e);
        canvas.restore();
    }

    public float e() {
        return this.e.getStrokeWidth();
    }

    public void e(float f2) {
        if (f2 != this.j) {
            this.j = f2;
            invalidateSelf();
        }
    }

    public float f() {
        return this.j;
    }

    public void f(@q(a = 0.0d, b = 1.0d) float f2) {
        if (this.o != f2) {
            this.o = f2;
            invalidateSelf();
        }
    }

    public boolean g() {
        return this.k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.m;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.m;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public int h() {
        return this.q;
    }

    @q(a = 0.0d, b = 1.0d)
    public float i() {
        return this.o;
    }

    public final Paint j() {
        return this.e;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i != this.e.getAlpha()) {
            this.e.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
