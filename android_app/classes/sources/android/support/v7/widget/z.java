package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class z extends RecyclerView.t {
    public static final int a = -1;
    public static final int b = 1;
    public static final int c = 0;
    private static final String i = "LinearSmoothScroller";
    private static final boolean j = false;
    private static final float k = 25.0f;
    private static final int l = 10000;
    private static final float m = 1.2f;
    protected PointF f;
    private final float n;
    protected final LinearInterpolator d = new LinearInterpolator();
    protected final DecelerateInterpolator e = new DecelerateInterpolator();
    protected int g = 0;
    protected int h = 0;

    public z(Context context) {
        this.n = a(context.getResources().getDisplayMetrics());
    }

    private int b(int i2, int i3) {
        int i4 = i2 - i3;
        if (i2 * i4 <= 0) {
            return 0;
        }
        return i4;
    }

    protected float a(DisplayMetrics displayMetrics) {
        return k / displayMetrics.densityDpi;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(int i2) {
        double b2 = b(i2);
        Double.isNaN(b2);
        return (int) Math.ceil(b2 / 0.3356d);
    }

    public int a(int i2, int i3, int i4, int i5, int i6) {
        if (i6 != -1) {
            if (i6 != 0) {
                if (i6 == 1) {
                    return i5 - i3;
                }
                throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
            }
            int i7 = i4 - i2;
            if (i7 > 0) {
                return i7;
            }
            int i8 = i5 - i3;
            if (i8 < 0) {
                return i8;
            }
            return 0;
        }
        return i4 - i2;
    }

    public int a(View view, int i2) {
        RecyclerView.i e = e();
        if (e == null || !e.i()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return a(e.p(view) - layoutParams.topMargin, e.r(view) + layoutParams.bottomMargin, e.M(), e.K() - e.O(), i2);
    }

    @Override // android.support.v7.widget.RecyclerView.t
    protected void a() {
    }

    @Override // android.support.v7.widget.RecyclerView.t
    protected void a(int i2, int i3, RecyclerView.u uVar, RecyclerView.t.a aVar) {
        if (j() == 0) {
            f();
            return;
        }
        this.g = b(this.g, i2);
        this.h = b(this.h, i3);
        if (this.g == 0 && this.h == 0) {
            a(aVar);
        }
    }

    protected void a(RecyclerView.t.a aVar) {
        PointF d = d(i());
        if (d == null || (d.x == 0.0f && d.y == 0.0f)) {
            aVar.a(i());
            f();
            return;
        }
        a(d);
        this.f = d;
        this.g = (int) (d.x * 10000.0f);
        this.h = (int) (d.y * 10000.0f);
        aVar.a((int) (this.g * m), (int) (this.h * m), (int) (b(l) * m), this.d);
    }

    @Override // android.support.v7.widget.RecyclerView.t
    protected void a(View view, RecyclerView.u uVar, RecyclerView.t.a aVar) {
        int b2 = b(view, c());
        int a2 = a(view, d());
        int a3 = a((int) Math.sqrt((b2 * b2) + (a2 * a2)));
        if (a3 > 0) {
            aVar.a(-b2, -a2, a3, this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int b(int i2) {
        return (int) Math.ceil(Math.abs(i2) * this.n);
    }

    public int b(View view, int i2) {
        RecyclerView.i e = e();
        if (e == null || !e.h()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return a(e.o(view) - layoutParams.leftMargin, e.q(view) + layoutParams.rightMargin, e.L(), e.J() - e.N(), i2);
    }

    @Override // android.support.v7.widget.RecyclerView.t
    protected void b() {
        this.h = 0;
        this.g = 0;
        this.f = null;
    }

    protected int c() {
        PointF pointF = this.f;
        if (pointF == null || pointF.x == 0.0f) {
            return 0;
        }
        return this.f.x > 0.0f ? 1 : -1;
    }

    protected int d() {
        PointF pointF = this.f;
        if (pointF == null || pointF.y == 0.0f) {
            return 0;
        }
        return this.f.y > 0.0f ? 1 : -1;
    }
}
