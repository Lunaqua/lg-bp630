package android.support.v4.widget;

import android.content.Context;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ai;
import android.support.v4.view.ab;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class x {
    private static final Interpolator L = new Interpolator() { // from class: android.support.v4.widget.x.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    };
    public static final int a = -1;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 4;
    public static final int h = 8;
    public static final int i = 15;
    public static final int j = 1;
    public static final int k = 2;
    public static final int l = 3;
    private static final String m = "ViewDragHelper";
    private static final int n = 20;
    private static final int o = 256;
    private static final int p = 600;
    private int A;
    private VelocityTracker B;
    private float C;
    private float D;
    private int E;
    private int F;
    private OverScroller G;
    private final a H;
    private View I;
    private boolean J;
    private final ViewGroup K;
    private int q;
    private int r;
    private float[] t;
    private float[] u;
    private float[] v;
    private float[] w;
    private int[] x;
    private int[] y;
    private int[] z;
    private int s = -1;
    private final Runnable M = new Runnable() { // from class: android.support.v4.widget.x.2
        @Override // java.lang.Runnable
        public void run() {
            x.this.c(0);
        }
    };

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class a {
        public int a(@af View view) {
            return 0;
        }

        public int a(@af View view, int i, int i2) {
            return 0;
        }

        public void a(int i) {
        }

        public void a(int i, int i2) {
        }

        public void a(@af View view, float f, float f2) {
        }

        public void a(@af View view, int i, int i2, @ai int i3, @ai int i4) {
        }

        public abstract boolean a(@af View view, int i);

        public int b(@af View view) {
            return 0;
        }

        public int b(@af View view, int i, int i2) {
            return 0;
        }

        public void b(int i, int i2) {
        }

        public void b(@af View view, int i) {
        }

        public boolean b(int i) {
            return false;
        }

        public int c(int i) {
            return i;
        }
    }

    private x(@af Context context, @af ViewGroup viewGroup, @af a aVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (aVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.K = viewGroup;
        this.H = aVar;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.E = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.r = viewConfiguration.getScaledTouchSlop();
        this.C = viewConfiguration.getScaledMaximumFlingVelocity();
        this.D = viewConfiguration.getScaledMinimumFlingVelocity();
        this.G = new OverScroller(context, L);
    }

    private float a(float f2, float f3, float f4) {
        float abs = Math.abs(f2);
        if (abs < f3) {
            return 0.0f;
        }
        return abs > f4 ? f2 > 0.0f ? f4 : -f4 : f2;
    }

    private int a(int i2, int i3, int i4) {
        if (i2 == 0) {
            return 0;
        }
        int width = this.K.getWidth();
        float f2 = width / 2;
        float b2 = f2 + (b(Math.min(1.0f, Math.abs(i2) / width)) * f2);
        int abs = Math.abs(i3);
        return Math.min(abs > 0 ? Math.round(Math.abs(b2 / abs) * 1000.0f) * 4 : (int) (((Math.abs(i2) / i4) + 1.0f) * 256.0f), (int) p);
    }

    private int a(View view, int i2, int i3, int i4, int i5) {
        float f2;
        float f3;
        float f4;
        float f5;
        int b2 = b(i4, (int) this.D, (int) this.C);
        int b3 = b(i5, (int) this.D, (int) this.C);
        int abs = Math.abs(i2);
        int abs2 = Math.abs(i3);
        int abs3 = Math.abs(b2);
        int abs4 = Math.abs(b3);
        int i6 = abs3 + abs4;
        int i7 = abs + abs2;
        if (b2 != 0) {
            f2 = abs3;
            f3 = i6;
        } else {
            f2 = abs;
            f3 = i7;
        }
        float f6 = f2 / f3;
        if (b3 != 0) {
            f4 = abs4;
            f5 = i6;
        } else {
            f4 = abs2;
            f5 = i7;
        }
        return (int) ((a(i2, b2, this.H.a(view)) * f6) + (a(i3, b3, this.H.b(view)) * (f4 / f5)));
    }

    public static x a(@af ViewGroup viewGroup, float f2, @af a aVar) {
        x a2 = a(viewGroup, aVar);
        a2.r = (int) (a2.r * (1.0f / f2));
        return a2;
    }

    public static x a(@af ViewGroup viewGroup, @af a aVar) {
        return new x(viewGroup.getContext(), viewGroup, aVar);
    }

    private void a(float f2, float f3) {
        this.J = true;
        this.H.a(this.I, f2, f3);
        this.J = false;
        if (this.q == 1) {
            c(0);
        }
    }

    private void a(float f2, float f3, int i2) {
        g(i2);
        float[] fArr = this.t;
        this.v[i2] = f2;
        fArr[i2] = f2;
        float[] fArr2 = this.u;
        this.w[i2] = f3;
        fArr2[i2] = f3;
        this.x[i2] = f((int) f2, (int) f3);
        this.A |= 1 << i2;
    }

    private boolean a(float f2, float f3, int i2, int i3) {
        float abs = Math.abs(f2);
        float abs2 = Math.abs(f3);
        if ((this.x[i2] & i3) != i3 || (this.F & i3) == 0 || (this.z[i2] & i3) == i3 || (this.y[i2] & i3) == i3) {
            return false;
        }
        int i4 = this.r;
        if (abs > i4 || abs2 > i4) {
            if (abs >= abs2 * 0.5f || !this.H.b(i3)) {
                return (this.y[i2] & i3) == 0 && abs > ((float) this.r);
            }
            int[] iArr = this.z;
            iArr[i2] = iArr[i2] | i3;
            return false;
        }
        return false;
    }

    private boolean a(View view, float f2, float f3) {
        if (view == null) {
            return false;
        }
        boolean z = this.H.a(view) > 0;
        boolean z2 = this.H.b(view) > 0;
        if (!z || !z2) {
            return z ? Math.abs(f2) > ((float) this.r) : z2 && Math.abs(f3) > ((float) this.r);
        }
        int i2 = this.r;
        return (f2 * f2) + (f3 * f3) > ((float) (i2 * i2));
    }

    private float b(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    private int b(int i2, int i3, int i4) {
        int abs = Math.abs(i2);
        if (abs < i3) {
            return 0;
        }
        return abs > i4 ? i2 > 0 ? i4 : -i4 : i2;
    }

    private void b(float f2, float f3, int i2) {
        int i3 = a(f2, f3, i2, 1) ? 1 : 0;
        if (a(f3, f2, i2, 4)) {
            i3 |= 4;
        }
        if (a(f2, f3, i2, 2)) {
            i3 |= 2;
        }
        if (a(f3, f2, i2, 8)) {
            i3 |= 8;
        }
        if (i3 != 0) {
            int[] iArr = this.y;
            iArr[i2] = iArr[i2] | i3;
            this.H.b(i3, i2);
        }
    }

    private boolean b(int i2, int i3, int i4, int i5) {
        int left = this.I.getLeft();
        int top = this.I.getTop();
        int i6 = i2 - left;
        int i7 = i3 - top;
        if (i6 == 0 && i7 == 0) {
            this.G.abortAnimation();
            c(0);
            return false;
        }
        this.G.startScroll(left, top, i6, i7, a(this.I, i6, i7, i4, i5));
        c(2);
        return true;
    }

    private void c(int i2, int i3, int i4, int i5) {
        int left = this.I.getLeft();
        int top = this.I.getTop();
        if (i4 != 0) {
            i2 = this.H.a(this.I, i2, i4);
            ab.n(this.I, i2 - left);
        }
        int i6 = i2;
        if (i5 != 0) {
            i3 = this.H.b(this.I, i3, i5);
            ab.m(this.I, i3 - top);
        }
        int i7 = i3;
        if (i4 == 0 && i5 == 0) {
            return;
        }
        this.H.a(this.I, i6, i7, i6 - left, i7 - top);
    }

    private void c(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i2 = 0; i2 < pointerCount; i2++) {
            int pointerId = motionEvent.getPointerId(i2);
            if (h(pointerId)) {
                float x = motionEvent.getX(i2);
                float y = motionEvent.getY(i2);
                this.v[pointerId] = x;
                this.w[pointerId] = y;
            }
        }
    }

    private int f(int i2, int i3) {
        int i4 = i2 < this.K.getLeft() + this.E ? 1 : 0;
        if (i3 < this.K.getTop() + this.E) {
            i4 |= 4;
        }
        if (i2 > this.K.getRight() - this.E) {
            i4 |= 2;
        }
        return i3 > this.K.getBottom() - this.E ? i4 | 8 : i4;
    }

    private void f(int i2) {
        if (this.t == null || !b(i2)) {
            return;
        }
        this.t[i2] = 0.0f;
        this.u[i2] = 0.0f;
        this.v[i2] = 0.0f;
        this.w[i2] = 0.0f;
        this.x[i2] = 0;
        this.y[i2] = 0;
        this.z[i2] = 0;
        this.A = ((1 << i2) ^ (-1)) & this.A;
    }

    private void g(int i2) {
        float[] fArr = this.t;
        if (fArr == null || fArr.length <= i2) {
            int i3 = i2 + 1;
            float[] fArr2 = new float[i3];
            float[] fArr3 = new float[i3];
            float[] fArr4 = new float[i3];
            float[] fArr5 = new float[i3];
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            float[] fArr6 = this.t;
            if (fArr6 != null) {
                System.arraycopy(fArr6, 0, fArr2, 0, fArr6.length);
                float[] fArr7 = this.u;
                System.arraycopy(fArr7, 0, fArr3, 0, fArr7.length);
                float[] fArr8 = this.v;
                System.arraycopy(fArr8, 0, fArr4, 0, fArr8.length);
                float[] fArr9 = this.w;
                System.arraycopy(fArr9, 0, fArr5, 0, fArr9.length);
                int[] iArr4 = this.x;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.y;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.z;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.t = fArr2;
            this.u = fArr3;
            this.v = fArr4;
            this.w = fArr5;
            this.x = iArr;
            this.y = iArr2;
            this.z = iArr3;
        }
    }

    private boolean h(int i2) {
        if (b(i2)) {
            return true;
        }
        Log.e(m, "Ignoring pointerId=" + i2 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    private void i() {
        float[] fArr = this.t;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.u, 0.0f);
        Arrays.fill(this.v, 0.0f);
        Arrays.fill(this.w, 0.0f);
        Arrays.fill(this.x, 0);
        Arrays.fill(this.y, 0);
        Arrays.fill(this.z, 0);
        this.A = 0;
    }

    private void j() {
        this.B.computeCurrentVelocity(1000, this.C);
        a(a(this.B.getXVelocity(this.s), this.D, this.C), a(this.B.getYVelocity(this.s), this.D, this.C));
    }

    public float a() {
        return this.D;
    }

    public void a(float f2) {
        this.D = f2;
    }

    public void a(int i2) {
        this.F = i2;
    }

    public void a(int i2, int i3, int i4, int i5) {
        if (!this.J) {
            throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
        }
        this.G.fling(this.I.getLeft(), this.I.getTop(), (int) this.B.getXVelocity(this.s), (int) this.B.getYVelocity(this.s), i2, i4, i3, i5);
        c(2);
    }

    public void a(@af View view, int i2) {
        if (view.getParent() == this.K) {
            this.I = view;
            this.s = i2;
            this.H.b(view, i2);
            c(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.K + ")");
    }

    public boolean a(int i2, int i3) {
        if (this.J) {
            return b(i2, i3, (int) this.B.getXVelocity(this.s), (int) this.B.getYVelocity(this.s));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00dd, code lost:
        if (r12 != r11) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(@android.support.annotation.af android.view.MotionEvent r17) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.x.a(android.view.MotionEvent):boolean");
    }

    public boolean a(@af View view, int i2, int i3) {
        this.I = view;
        this.s = -1;
        boolean b2 = b(i2, i3, 0, 0);
        if (!b2 && this.q == 0 && this.I != null) {
            this.I = null;
        }
        return b2;
    }

    protected boolean a(@af View view, boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i7 = i4 + scrollX;
                if (i7 >= childAt.getLeft() && i7 < childAt.getRight() && (i6 = i5 + scrollY) >= childAt.getTop() && i6 < childAt.getBottom() && a(childAt, true, i2, i3, i7 - childAt.getLeft(), i6 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && (view.canScrollHorizontally(-i2) || view.canScrollVertically(-i3));
    }

    public boolean a(boolean z) {
        if (this.q == 2) {
            boolean computeScrollOffset = this.G.computeScrollOffset();
            int currX = this.G.getCurrX();
            int currY = this.G.getCurrY();
            int left = currX - this.I.getLeft();
            int top = currY - this.I.getTop();
            if (left != 0) {
                ab.n(this.I, left);
            }
            if (top != 0) {
                ab.m(this.I, top);
            }
            if (left != 0 || top != 0) {
                this.H.a(this.I, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.G.getFinalX() && currY == this.G.getFinalY()) {
                this.G.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z) {
                    this.K.post(this.M);
                } else {
                    c(0);
                }
            }
        }
        return this.q == 2;
    }

    public int b() {
        return this.q;
    }

    public void b(@af MotionEvent motionEvent) {
        int i2;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            g();
        }
        if (this.B == null) {
            this.B = VelocityTracker.obtain();
        }
        this.B.addMovement(motionEvent);
        int i3 = 0;
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View e2 = e((int) x, (int) y);
            a(x, y, pointerId);
            b(e2, pointerId);
            int i4 = this.x[pointerId];
            int i5 = this.F;
            if ((i4 & i5) != 0) {
                this.H.a(i4 & i5, pointerId);
                return;
            }
            return;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                if (this.q != 1) {
                    int pointerCount = motionEvent.getPointerCount();
                    while (i3 < pointerCount) {
                        int pointerId2 = motionEvent.getPointerId(i3);
                        if (h(pointerId2)) {
                            float x2 = motionEvent.getX(i3);
                            float y2 = motionEvent.getY(i3);
                            float f2 = x2 - this.t[pointerId2];
                            float f3 = y2 - this.u[pointerId2];
                            b(f2, f3, pointerId2);
                            if (this.q != 1) {
                                View e3 = e((int) x2, (int) y2);
                                if (a(e3, f2, f3) && b(e3, pointerId2)) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        i3++;
                    }
                } else if (!h(this.s)) {
                    return;
                } else {
                    int findPointerIndex = motionEvent.findPointerIndex(this.s);
                    float x3 = motionEvent.getX(findPointerIndex);
                    float y3 = motionEvent.getY(findPointerIndex);
                    float[] fArr = this.v;
                    int i6 = this.s;
                    int i7 = (int) (x3 - fArr[i6]);
                    int i8 = (int) (y3 - this.w[i6]);
                    c(this.I.getLeft() + i7, this.I.getTop() + i8, i7, i8);
                }
                c(motionEvent);
                return;
            } else if (actionMasked != 3) {
                if (actionMasked == 5) {
                    int pointerId3 = motionEvent.getPointerId(actionIndex);
                    float x4 = motionEvent.getX(actionIndex);
                    float y4 = motionEvent.getY(actionIndex);
                    a(x4, y4, pointerId3);
                    if (this.q != 0) {
                        if (d((int) x4, (int) y4)) {
                            b(this.I, pointerId3);
                            return;
                        }
                        return;
                    }
                    b(e((int) x4, (int) y4), pointerId3);
                    int i9 = this.x[pointerId3];
                    int i10 = this.F;
                    if ((i9 & i10) != 0) {
                        this.H.a(i9 & i10, pointerId3);
                        return;
                    }
                    return;
                } else if (actionMasked != 6) {
                    return;
                } else {
                    int pointerId4 = motionEvent.getPointerId(actionIndex);
                    if (this.q == 1 && pointerId4 == this.s) {
                        int pointerCount2 = motionEvent.getPointerCount();
                        while (true) {
                            if (i3 >= pointerCount2) {
                                i2 = -1;
                                break;
                            }
                            int pointerId5 = motionEvent.getPointerId(i3);
                            if (pointerId5 != this.s) {
                                View e4 = e((int) motionEvent.getX(i3), (int) motionEvent.getY(i3));
                                View view = this.I;
                                if (e4 == view && b(view, pointerId5)) {
                                    i2 = this.s;
                                    break;
                                }
                            }
                            i3++;
                        }
                        if (i2 == -1) {
                            j();
                        }
                    }
                    f(pointerId4);
                    return;
                }
            } else if (this.q == 1) {
                a(0.0f, 0.0f);
            }
        } else if (this.q == 1) {
            j();
        }
        g();
    }

    public boolean b(int i2) {
        return ((1 << i2) & this.A) != 0;
    }

    public boolean b(int i2, int i3) {
        if (b(i3)) {
            boolean z = (i2 & 1) == 1;
            boolean z2 = (i2 & 2) == 2;
            float f2 = this.v[i3] - this.t[i3];
            float f3 = this.w[i3] - this.u[i3];
            if (!z || !z2) {
                return z ? Math.abs(f2) > ((float) this.r) : z2 && Math.abs(f3) > ((float) this.r);
            }
            int i4 = this.r;
            return (f2 * f2) + (f3 * f3) > ((float) (i4 * i4));
        }
        return false;
    }

    boolean b(View view, int i2) {
        if (view == this.I && this.s == i2) {
            return true;
        }
        if (view == null || !this.H.a(view, i2)) {
            return false;
        }
        this.s = i2;
        a(view, i2);
        return true;
    }

    public boolean b(@ag View view, int i2, int i3) {
        return view != null && i2 >= view.getLeft() && i2 < view.getRight() && i3 >= view.getTop() && i3 < view.getBottom();
    }

    @ai
    public int c() {
        return this.E;
    }

    void c(int i2) {
        this.K.removeCallbacks(this.M);
        if (this.q != i2) {
            this.q = i2;
            this.H.a(i2);
            if (this.q == 0) {
                this.I = null;
            }
        }
    }

    public boolean c(int i2, int i3) {
        return b(i3) && (i2 & this.x[i3]) != 0;
    }

    @ag
    public View d() {
        return this.I;
    }

    public boolean d(int i2) {
        int length = this.t.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (b(i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public boolean d(int i2, int i3) {
        return b(this.I, i2, i3);
    }

    public int e() {
        return this.s;
    }

    @ag
    public View e(int i2, int i3) {
        for (int childCount = this.K.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.K.getChildAt(this.H.c(childCount));
            if (i2 >= childAt.getLeft() && i2 < childAt.getRight() && i3 >= childAt.getTop() && i3 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public boolean e(int i2) {
        int length = this.x.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (c(i2, i3)) {
                return true;
            }
        }
        return false;
    }

    @ai
    public int f() {
        return this.r;
    }

    public void g() {
        this.s = -1;
        i();
        VelocityTracker velocityTracker = this.B;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.B = null;
        }
    }

    public void h() {
        g();
        if (this.q == 2) {
            int currX = this.G.getCurrX();
            int currY = this.G.getCurrY();
            this.G.abortAnimation();
            int currX2 = this.G.getCurrX();
            int currY2 = this.G.getCurrY();
            this.H.a(this.I, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        c(0);
    }
}
