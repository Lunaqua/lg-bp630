package android.support.v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.support.annotation.af;
import android.support.v4.view.ab;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class a implements View.OnTouchListener {
    private static final int A = 315;
    private static final int B = 1575;
    private static final float C = Float.MAX_VALUE;
    private static final float D = 0.2f;
    private static final float E = 1.0f;
    private static final int F = ViewConfiguration.getTapTimeout();
    private static final int G = 500;
    private static final int H = 500;
    public static final float a = 0.0f;
    public static final float b = Float.MAX_VALUE;
    public static final float c = 0.0f;
    public static final int d = 0;
    public static final int e = 1;
    public static final int f = 2;
    private static final int l = 0;
    private static final int m = 1;
    private static final int z = 1;
    final View h;
    boolean i;
    boolean j;
    boolean k;
    private Runnable o;
    private int r;
    private int s;
    private boolean w;
    private boolean x;
    private boolean y;
    final C0035a g = new C0035a();
    private final Interpolator n = new AccelerateInterpolator();
    private float[] p = {0.0f, 0.0f};
    private float[] q = {Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] t = {0.0f, 0.0f};
    private float[] u = {0.0f, 0.0f};
    private float[] v = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: android.support.v4.widget.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class C0035a {
        private int a;
        private int b;
        private float c;
        private float d;
        private float j;
        private int k;
        private long e = Long.MIN_VALUE;
        private long i = -1;
        private long f = 0;
        private int g = 0;
        private int h = 0;

        C0035a() {
        }

        private float a(float f) {
            return ((-4.0f) * f * f) + (f * 4.0f);
        }

        private float a(long j) {
            if (j < this.e) {
                return 0.0f;
            }
            long j2 = this.i;
            if (j2 < 0 || j < j2) {
                return a.a(((float) (j - this.e)) / this.a, 0.0f, (float) a.E) * 0.5f;
            }
            long j3 = j - j2;
            float f = this.j;
            return (a.E - f) + (f * a.a(((float) j3) / this.k, 0.0f, (float) a.E));
        }

        public void a() {
            this.e = AnimationUtils.currentAnimationTimeMillis();
            this.i = -1L;
            this.f = this.e;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }

        public void a(float f, float f2) {
            this.c = f;
            this.d = f2;
        }

        public void a(int i) {
            this.a = i;
        }

        public void b() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.k = a.a((int) (currentAnimationTimeMillis - this.e), 0, this.b);
            this.j = a(currentAnimationTimeMillis);
            this.i = currentAnimationTimeMillis;
        }

        public void b(int i) {
            this.b = i;
        }

        public boolean c() {
            return this.i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.i + ((long) this.k);
        }

        public void d() {
            if (this.f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float a = a(a(currentAnimationTimeMillis));
            this.f = currentAnimationTimeMillis;
            float f = ((float) (currentAnimationTimeMillis - this.f)) * a;
            this.g = (int) (this.c * f);
            this.h = (int) (f * this.d);
        }

        public int e() {
            float f = this.c;
            return (int) (f / Math.abs(f));
        }

        public int f() {
            float f = this.d;
            return (int) (f / Math.abs(f));
        }

        public int g() {
            return this.g;
        }

        public int h() {
            return this.h;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.k) {
                if (a.this.i) {
                    a aVar = a.this;
                    aVar.i = false;
                    aVar.g.a();
                }
                C0035a c0035a = a.this.g;
                if (c0035a.c() || !a.this.c()) {
                    a.this.k = false;
                    return;
                }
                if (a.this.j) {
                    a aVar2 = a.this;
                    aVar2.j = false;
                    aVar2.d();
                }
                c0035a.d();
                a.this.a(c0035a.g(), c0035a.h());
                ab.a(a.this.h, this);
            }
        }
    }

    public a(@af View view) {
        this.h = view;
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        float f2 = (int) ((displayMetrics.density * 1575.0f) + 0.5f);
        a(f2, f2);
        float f3 = (int) ((displayMetrics.density * 315.0f) + 0.5f);
        b(f3, f3);
        a(1);
        e(Float.MAX_VALUE, Float.MAX_VALUE);
        d(D, D);
        c(E, E);
        b(F);
        c(500);
        d(500);
    }

    static float a(float f2, float f3, float f4) {
        return f2 > f4 ? f4 : f2 < f3 ? f3 : f2;
    }

    private float a(float f2, float f3, float f4, float f5) {
        float interpolation;
        float a2 = a(f2 * f3, 0.0f, f4);
        float f6 = f(f3 - f5, a2) - f(f5, a2);
        if (f6 < 0.0f) {
            interpolation = -this.n.getInterpolation(-f6);
        } else if (f6 <= 0.0f) {
            return 0.0f;
        } else {
            interpolation = this.n.getInterpolation(f6);
        }
        return a(interpolation, -1.0f, (float) E);
    }

    private float a(int i, float f2, float f3, float f4) {
        float a2 = a(this.p[i], f3, this.q[i], f2);
        if (a2 == 0.0f) {
            return 0.0f;
        }
        float f5 = this.t[i];
        float f6 = this.u[i];
        float f7 = this.v[i];
        float f8 = f5 * f4;
        return a2 > 0.0f ? a(a2 * f8, f6, f7) : -a((-a2) * f8, f6, f7);
    }

    static int a(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    private void e() {
        int i;
        if (this.o == null) {
            this.o = new b();
        }
        this.k = true;
        this.i = true;
        if (this.w || (i = this.s) <= 0) {
            this.o.run();
        } else {
            ab.a(this.h, this.o, i);
        }
        this.w = true;
    }

    private float f(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        int i = this.r;
        if (i == 0 || i == 1) {
            if (f2 < f3) {
                if (f2 >= 0.0f) {
                    return E - (f2 / f3);
                }
                if (this.k && this.r == 1) {
                    return E;
                }
            }
        } else if (i == 2 && f2 < 0.0f) {
            return f2 / (-f3);
        }
        return 0.0f;
    }

    private void f() {
        if (this.i) {
            this.k = false;
        } else {
            this.g.b();
        }
    }

    @af
    public a a(float f2, float f3) {
        float[] fArr = this.v;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    @af
    public a a(int i) {
        this.r = i;
        return this;
    }

    public a a(boolean z2) {
        if (this.x && !z2) {
            f();
        }
        this.x = z2;
        return this;
    }

    public abstract void a(int i, int i2);

    public boolean a() {
        return this.x;
    }

    @af
    public a b(float f2, float f3) {
        float[] fArr = this.u;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    @af
    public a b(int i) {
        this.s = i;
        return this;
    }

    public a b(boolean z2) {
        this.y = z2;
        return this;
    }

    public boolean b() {
        return this.y;
    }

    @af
    public a c(float f2, float f3) {
        float[] fArr = this.t;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    @af
    public a c(int i) {
        this.g.a(i);
        return this;
    }

    boolean c() {
        C0035a c0035a = this.g;
        int f2 = c0035a.f();
        int e2 = c0035a.e();
        return (f2 != 0 && f(f2)) || (e2 != 0 && e(e2));
    }

    @af
    public a d(float f2, float f3) {
        float[] fArr = this.p;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    @af
    public a d(int i) {
        this.g.b(i);
        return this;
    }

    void d() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.h.onTouchEvent(obtain);
        obtain.recycle();
    }

    @af
    public a e(float f2, float f3) {
        float[] fArr = this.q;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    public abstract boolean e(int i);

    public abstract boolean f(int i);

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
        if (r0 != 3) goto L12;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.x
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r6 = 3
            if (r0 == r6) goto L16
            goto L58
        L16:
            r5.f()
            goto L58
        L1a:
            r5.j = r2
            r5.w = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.h
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.a(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.h
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.a(r2, r7, r6, r3)
            android.support.v4.widget.a$a r7 = r5.g
            r7.a(r0, r6)
            boolean r6 = r5.k
            if (r6 != 0) goto L58
            boolean r6 = r5.c()
            if (r6 == 0) goto L58
            r5.e()
        L58:
            boolean r6 = r5.y
            if (r6 == 0) goto L61
            boolean r6 = r5.k
            if (r6 == 0) goto L61
            r1 = 1
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
