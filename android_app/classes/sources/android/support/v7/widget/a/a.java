package android.support.v7.widget.a;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.v4.view.ab;
import android.support.v7.d.a;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a extends RecyclerView.h implements RecyclerView.j {
    private static final String F = "ItemTouchHelper";
    private static final boolean G = false;
    private static final int H = -1;
    private static final int I = 255;
    private static final int J = 1000;
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 4;
    public static final int d = 8;
    public static final int e = 16;
    public static final int f = 32;
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 2;
    public static final int k = 4;
    public static final int l = 8;
    static final int m = 8;
    static final int n = 65280;
    static final int o = 16711680;
    VelocityTracker B;
    android.support.v4.view.e E;
    private float L;
    private float M;
    private float N;
    private float O;
    private int Q;
    private List<RecyclerView.x> R;
    private List<Integer> S;
    private b U;
    private Rect W;
    private long X;
    float r;
    float s;
    float t;
    float u;
    @af
    AbstractC0050a w;
    int x;
    RecyclerView z;
    final List<View> p = new ArrayList();
    private final float[] K = new float[2];
    RecyclerView.x q = null;
    int v = -1;
    private int P = 0;
    List<c> y = new ArrayList();
    final Runnable A = new Runnable() { // from class: android.support.v7.widget.a.a.1
        @Override // java.lang.Runnable
        public void run() {
            if (a.this.q == null || !a.this.b()) {
                return;
            }
            if (a.this.q != null) {
                a aVar = a.this;
                aVar.a(aVar.q);
            }
            a.this.z.removeCallbacks(a.this.A);
            ab.a(a.this.z, this);
        }
    };
    private RecyclerView.d T = null;
    View C = null;
    int D = -1;
    private final RecyclerView.l V = new RecyclerView.l() { // from class: android.support.v7.widget.a.a.2
        @Override // android.support.v7.widget.RecyclerView.l
        public void a(boolean z) {
            if (z) {
                a.this.a((RecyclerView.x) null, 0);
            }
        }

        @Override // android.support.v7.widget.RecyclerView.l
        public boolean a(@af RecyclerView recyclerView, @af MotionEvent motionEvent) {
            int findPointerIndex;
            c b2;
            a.this.E.a(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                a.this.v = motionEvent.getPointerId(0);
                a.this.r = motionEvent.getX();
                a.this.s = motionEvent.getY();
                a.this.c();
                if (a.this.q == null && (b2 = a.this.b(motionEvent)) != null) {
                    a.this.r -= b2.l;
                    a.this.s -= b2.m;
                    a.this.a(b2.h, true);
                    if (a.this.p.remove(b2.h.a)) {
                        a.this.w.e(a.this.z, b2.h);
                    }
                    a.this.a(b2.h, b2.i);
                    a aVar = a.this;
                    aVar.a(motionEvent, aVar.x, 0);
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                a aVar2 = a.this;
                aVar2.v = -1;
                aVar2.a((RecyclerView.x) null, 0);
            } else if (a.this.v != -1 && (findPointerIndex = motionEvent.findPointerIndex(a.this.v)) >= 0) {
                a.this.a(actionMasked, motionEvent, findPointerIndex);
            }
            if (a.this.B != null) {
                a.this.B.addMovement(motionEvent);
            }
            return a.this.q != null;
        }

        @Override // android.support.v7.widget.RecyclerView.l
        public void b(@af RecyclerView recyclerView, @af MotionEvent motionEvent) {
            a.this.E.a(motionEvent);
            if (a.this.B != null) {
                a.this.B.addMovement(motionEvent);
            }
            if (a.this.v == -1) {
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            int findPointerIndex = motionEvent.findPointerIndex(a.this.v);
            if (findPointerIndex >= 0) {
                a.this.a(actionMasked, motionEvent, findPointerIndex);
            }
            RecyclerView.x xVar = a.this.q;
            if (xVar == null) {
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (findPointerIndex >= 0) {
                        a aVar = a.this;
                        aVar.a(motionEvent, aVar.x, findPointerIndex);
                        a.this.a(xVar);
                        a.this.z.removeCallbacks(a.this.A);
                        a.this.A.run();
                        a.this.z.invalidate();
                        return;
                    }
                    return;
                } else if (actionMasked != 3) {
                    if (actionMasked != 6) {
                        return;
                    }
                    int actionIndex = motionEvent.getActionIndex();
                    if (motionEvent.getPointerId(actionIndex) == a.this.v) {
                        a.this.v = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                        a aVar2 = a.this;
                        aVar2.a(motionEvent, aVar2.x, actionIndex);
                        return;
                    }
                    return;
                } else if (a.this.B != null) {
                    a.this.B.clear();
                }
            }
            a.this.a((RecyclerView.x) null, 0);
            a.this.v = -1;
        }
    };

    /* renamed from: android.support.v7.widget.a.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class AbstractC0050a {
        public static final int a = 200;
        public static final int b = 250;
        static final int c = 3158064;
        private static final int d = 789516;
        private static final Interpolator e = new Interpolator() { // from class: android.support.v7.widget.a.a.a.1
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                return f2 * f2 * f2 * f2 * f2;
            }
        };
        private static final Interpolator f = new Interpolator() { // from class: android.support.v7.widget.a.a.a.2
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3 * f3 * f3) + 1.0f;
            }
        };
        private static final long g = 2000;
        private int h = -1;

        public static int a(int i, int i2) {
            int i3;
            int i4 = i & d;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (i4 ^ (-1));
            if (i2 == 0) {
                i3 = i4 << 2;
            } else {
                int i6 = i4 << 1;
                i5 |= (-789517) & i6;
                i3 = (i6 & d) << 2;
            }
            return i5 | i3;
        }

        private int a(RecyclerView recyclerView) {
            if (this.h == -1) {
                this.h = recyclerView.getResources().getDimensionPixelSize(a.c.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.h;
        }

        @af
        public static android.support.v7.widget.a.b a() {
            return android.support.v7.widget.a.c.a;
        }

        public static int b(int i, int i2) {
            int c2 = c(0, i2 | i);
            return c(2, i) | c(1, i2) | c2;
        }

        public static int c(int i, int i2) {
            return i2 << (i * 8);
        }

        public float a(float f2) {
            return f2;
        }

        public float a(@af RecyclerView.x xVar) {
            return 0.5f;
        }

        public int a(@af RecyclerView recyclerView, int i, int i2, int i3, long j) {
            int signum = (int) (((int) (((int) Math.signum(i2)) * a(recyclerView) * f.getInterpolation(Math.min(1.0f, (Math.abs(i2) * 1.0f) / i)))) * e.getInterpolation(j <= g ? ((float) j) / 2000.0f : 1.0f));
            return signum == 0 ? i2 > 0 ? 1 : -1 : signum;
        }

        public abstract int a(@af RecyclerView recyclerView, @af RecyclerView.x xVar);

        public long a(@af RecyclerView recyclerView, int i, float f2, float f3) {
            RecyclerView.f itemAnimator = recyclerView.getItemAnimator();
            return itemAnimator == null ? i == 8 ? 200L : 250L : i == 8 ? itemAnimator.e() : itemAnimator.g();
        }

        public RecyclerView.x a(@af RecyclerView.x xVar, @af List<RecyclerView.x> list, int i, int i2) {
            int i3;
            int bottom;
            int top;
            int abs;
            int left;
            int abs2;
            int right;
            int width = i + xVar.a.getWidth();
            int height = i2 + xVar.a.getHeight();
            int left2 = i - xVar.a.getLeft();
            int top2 = i2 - xVar.a.getTop();
            int size = list.size();
            RecyclerView.x xVar2 = null;
            int i4 = -1;
            for (int i5 = 0; i5 < size; i5++) {
                RecyclerView.x xVar3 = list.get(i5);
                if (left2 <= 0 || (right = xVar3.a.getRight() - width) >= 0 || xVar3.a.getRight() <= xVar.a.getRight() || (i3 = Math.abs(right)) <= i4) {
                    i3 = i4;
                } else {
                    xVar2 = xVar3;
                }
                if (left2 < 0 && (left = xVar3.a.getLeft() - i) > 0 && xVar3.a.getLeft() < xVar.a.getLeft() && (abs2 = Math.abs(left)) > i3) {
                    i3 = abs2;
                    xVar2 = xVar3;
                }
                if (top2 < 0 && (top = xVar3.a.getTop() - i2) > 0 && xVar3.a.getTop() < xVar.a.getTop() && (abs = Math.abs(top)) > i3) {
                    i3 = abs;
                    xVar2 = xVar3;
                }
                if (top2 <= 0 || (bottom = xVar3.a.getBottom() - height) >= 0 || xVar3.a.getBottom() <= xVar.a.getBottom() || (i4 = Math.abs(bottom)) <= i3) {
                    i4 = i3;
                } else {
                    xVar2 = xVar3;
                }
            }
            return xVar2;
        }

        public void a(@af Canvas canvas, @af RecyclerView recyclerView, @af RecyclerView.x xVar, float f2, float f3, int i, boolean z) {
            android.support.v7.widget.a.c.a.a(canvas, recyclerView, xVar.a, f2, f3, i, z);
        }

        void a(Canvas canvas, RecyclerView recyclerView, RecyclerView.x xVar, List<c> list, int i, float f2, float f3) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                c cVar = list.get(i2);
                cVar.c();
                int save = canvas.save();
                a(canvas, recyclerView, cVar.h, cVar.l, cVar.m, cVar.i, false);
                canvas.restoreToCount(save);
            }
            if (xVar != null) {
                int save2 = canvas.save();
                a(canvas, recyclerView, xVar, f2, f3, i, true);
                canvas.restoreToCount(save2);
            }
        }

        public abstract void a(@af RecyclerView.x xVar, int i);

        public void a(@af RecyclerView recyclerView, @af RecyclerView.x xVar, int i, @af RecyclerView.x xVar2, int i2, int i3, int i4) {
            RecyclerView.i layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof e) {
                ((e) layoutManager).a(xVar.a, xVar2.a, i3, i4);
                return;
            }
            if (layoutManager.h()) {
                if (layoutManager.o(xVar2.a) <= recyclerView.getPaddingLeft()) {
                    recyclerView.e(i2);
                }
                if (layoutManager.q(xVar2.a) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.e(i2);
                }
            }
            if (layoutManager.i()) {
                if (layoutManager.p(xVar2.a) <= recyclerView.getPaddingTop()) {
                    recyclerView.e(i2);
                }
                if (layoutManager.r(xVar2.a) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.e(i2);
                }
            }
        }

        public boolean a(@af RecyclerView recyclerView, @af RecyclerView.x xVar, @af RecyclerView.x xVar2) {
            return true;
        }

        public float b(float f2) {
            return f2;
        }

        public float b(@af RecyclerView.x xVar) {
            return 0.5f;
        }

        final int b(RecyclerView recyclerView, RecyclerView.x xVar) {
            return d(a(recyclerView, xVar), ab.m(recyclerView));
        }

        public void b(@af Canvas canvas, @af RecyclerView recyclerView, RecyclerView.x xVar, float f2, float f3, int i, boolean z) {
            android.support.v7.widget.a.c.a.b(canvas, recyclerView, xVar.a, f2, f3, i, z);
        }

        void b(Canvas canvas, RecyclerView recyclerView, RecyclerView.x xVar, List<c> list, int i, float f2, float f3) {
            int size = list.size();
            boolean z = false;
            for (int i2 = 0; i2 < size; i2++) {
                c cVar = list.get(i2);
                int save = canvas.save();
                b(canvas, recyclerView, cVar.h, cVar.l, cVar.m, cVar.i, false);
                canvas.restoreToCount(save);
            }
            if (xVar != null) {
                int save2 = canvas.save();
                b(canvas, recyclerView, xVar, f2, f3, i, true);
                canvas.restoreToCount(save2);
            }
            for (int i3 = size - 1; i3 >= 0; i3--) {
                c cVar2 = list.get(i3);
                if (cVar2.o && !cVar2.k) {
                    list.remove(i3);
                } else if (!cVar2.o) {
                    z = true;
                }
            }
            if (z) {
                recyclerView.invalidate();
            }
        }

        public void b(@ag RecyclerView.x xVar, int i) {
            if (xVar != null) {
                android.support.v7.widget.a.c.a.b(xVar.a);
            }
        }

        public boolean b() {
            return true;
        }

        public abstract boolean b(@af RecyclerView recyclerView, @af RecyclerView.x xVar, @af RecyclerView.x xVar2);

        public boolean c() {
            return true;
        }

        boolean c(RecyclerView recyclerView, RecyclerView.x xVar) {
            return (b(recyclerView, xVar) & a.o) != 0;
        }

        public int d() {
            return 0;
        }

        public int d(int i, int i2) {
            int i3;
            int i4 = i & c;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (i4 ^ (-1));
            if (i2 == 0) {
                i3 = i4 >> 2;
            } else {
                int i6 = i4 >> 1;
                i5 |= (-3158065) & i6;
                i3 = (i6 & c) >> 2;
            }
            return i5 | i3;
        }

        boolean d(RecyclerView recyclerView, RecyclerView.x xVar) {
            return (b(recyclerView, xVar) & 65280) != 0;
        }

        public void e(@af RecyclerView recyclerView, @af RecyclerView.x xVar) {
            android.support.v7.widget.a.c.a.a(xVar.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b extends GestureDetector.SimpleOnGestureListener {
        private boolean b = true;

        b() {
        }

        void a() {
            this.b = false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            View a;
            RecyclerView.x b;
            if (this.b && (a = a.this.a(motionEvent)) != null && (b = a.this.z.b(a)) != null && a.this.w.c(a.this.z, b) && motionEvent.getPointerId(0) == a.this.v) {
                int findPointerIndex = motionEvent.findPointerIndex(a.this.v);
                float x = motionEvent.getX(findPointerIndex);
                float y = motionEvent.getY(findPointerIndex);
                a aVar = a.this;
                aVar.r = x;
                aVar.s = y;
                aVar.u = 0.0f;
                aVar.t = 0.0f;
                if (aVar.w.b()) {
                    a.this.a(b, 2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class c implements Animator.AnimatorListener {
        private float b;
        final float d;
        final float e;
        final float f;
        final float g;
        final RecyclerView.x h;
        final int i;
        final int j;
        boolean k;
        float l;
        float m;
        boolean n = false;
        boolean o = false;
        private final ValueAnimator a = ValueAnimator.ofFloat(0.0f, 1.0f);

        c(RecyclerView.x xVar, int i, int i2, float f, float f2, float f3, float f4) {
            this.i = i2;
            this.j = i;
            this.h = xVar;
            this.d = f;
            this.e = f2;
            this.f = f3;
            this.g = f4;
            this.a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v7.widget.a.a.c.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    c.this.a(valueAnimator.getAnimatedFraction());
                }
            });
            this.a.setTarget(xVar.a);
            this.a.addListener(this);
            a(0.0f);
        }

        public void a() {
            this.h.a(false);
            this.a.start();
        }

        public void a(float f) {
            this.b = f;
        }

        public void a(long j) {
            this.a.setDuration(j);
        }

        public void b() {
            this.a.cancel();
        }

        public void c() {
            float f = this.d;
            float f2 = this.f;
            this.l = f == f2 ? this.h.a.getTranslationX() : f + (this.b * (f2 - f));
            float f3 = this.e;
            float f4 = this.g;
            this.m = f3 == f4 ? this.h.a.getTranslationY() : f3 + (this.b * (f4 - f3));
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            a(1.0f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.o) {
                this.h.a(true);
            }
            this.o = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class d extends AbstractC0050a {
        private int d;
        private int e;

        public d(int i, int i2) {
            this.d = i2;
            this.e = i;
        }

        @Override // android.support.v7.widget.a.a.AbstractC0050a
        public int a(@af RecyclerView recyclerView, @af RecyclerView.x xVar) {
            return b(g(recyclerView, xVar), f(recyclerView, xVar));
        }

        public void a(int i) {
            this.d = i;
        }

        public void b(int i) {
            this.e = i;
        }

        public int f(@af RecyclerView recyclerView, @af RecyclerView.x xVar) {
            return this.d;
        }

        public int g(@af RecyclerView recyclerView, @af RecyclerView.x xVar) {
            return this.e;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface e {
        void a(@af View view, @af View view2, int i, int i2);
    }

    public a(@af AbstractC0050a abstractC0050a) {
        this.w = abstractC0050a;
    }

    private void a(float[] fArr) {
        if ((this.x & 12) != 0) {
            fArr[0] = (this.N + this.t) - this.q.a.getLeft();
        } else {
            fArr[0] = this.q.a.getTranslationX();
        }
        if ((this.x & 3) != 0) {
            fArr[1] = (this.O + this.u) - this.q.a.getTop();
        } else {
            fArr[1] = this.q.a.getTranslationY();
        }
    }

    private static boolean a(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= f4 + ((float) view.getWidth()) && f3 >= f5 && f3 <= f5 + ((float) view.getHeight());
    }

    private int b(RecyclerView.x xVar, int i2) {
        if ((i2 & 12) != 0) {
            int i3 = this.t > 0.0f ? 8 : 4;
            VelocityTracker velocityTracker = this.B;
            if (velocityTracker != null && this.v > -1) {
                velocityTracker.computeCurrentVelocity(1000, this.w.b(this.M));
                float xVelocity = this.B.getXVelocity(this.v);
                float yVelocity = this.B.getYVelocity(this.v);
                int i4 = xVelocity <= 0.0f ? 4 : 8;
                float abs = Math.abs(xVelocity);
                if ((i4 & i2) != 0 && i3 == i4 && abs >= this.w.a(this.L) && abs > Math.abs(yVelocity)) {
                    return i4;
                }
            }
            float width = this.z.getWidth() * this.w.a(xVar);
            if ((i2 & i3) == 0 || Math.abs(this.t) <= width) {
                return 0;
            }
            return i3;
        }
        return 0;
    }

    private int c(RecyclerView.x xVar, int i2) {
        if ((i2 & 3) != 0) {
            int i3 = this.u > 0.0f ? 2 : 1;
            VelocityTracker velocityTracker = this.B;
            if (velocityTracker != null && this.v > -1) {
                velocityTracker.computeCurrentVelocity(1000, this.w.b(this.M));
                float xVelocity = this.B.getXVelocity(this.v);
                float yVelocity = this.B.getYVelocity(this.v);
                int i4 = yVelocity <= 0.0f ? 1 : 2;
                float abs = Math.abs(yVelocity);
                if ((i4 & i2) != 0 && i4 == i3 && abs >= this.w.a(this.L) && abs > Math.abs(xVelocity)) {
                    return i4;
                }
            }
            float height = this.z.getHeight() * this.w.a(xVar);
            if ((i2 & i3) == 0 || Math.abs(this.u) <= height) {
                return 0;
            }
            return i3;
        }
        return 0;
    }

    private RecyclerView.x c(MotionEvent motionEvent) {
        View a2;
        RecyclerView.i layoutManager = this.z.getLayoutManager();
        int i2 = this.v;
        if (i2 == -1) {
            return null;
        }
        int findPointerIndex = motionEvent.findPointerIndex(i2);
        float abs = Math.abs(motionEvent.getX(findPointerIndex) - this.r);
        float abs2 = Math.abs(motionEvent.getY(findPointerIndex) - this.s);
        int i3 = this.Q;
        if (abs >= i3 || abs2 >= i3) {
            if (abs <= abs2 || !layoutManager.h()) {
                if ((abs2 <= abs || !layoutManager.i()) && (a2 = a(motionEvent)) != null) {
                    return this.z.b(a2);
                }
                return null;
            }
            return null;
        }
        return null;
    }

    private List<RecyclerView.x> d(RecyclerView.x xVar) {
        RecyclerView.x xVar2 = xVar;
        List<RecyclerView.x> list = this.R;
        if (list == null) {
            this.R = new ArrayList();
            this.S = new ArrayList();
        } else {
            list.clear();
            this.S.clear();
        }
        int d2 = this.w.d();
        int round = Math.round(this.N + this.t) - d2;
        int round2 = Math.round(this.O + this.u) - d2;
        int i2 = d2 * 2;
        int width = xVar2.a.getWidth() + round + i2;
        int height = xVar2.a.getHeight() + round2 + i2;
        int i3 = (round + width) / 2;
        int i4 = (round2 + height) / 2;
        RecyclerView.i layoutManager = this.z.getLayoutManager();
        int G2 = layoutManager.G();
        int i5 = 0;
        while (i5 < G2) {
            View j2 = layoutManager.j(i5);
            if (j2 != xVar2.a && j2.getBottom() >= round2 && j2.getTop() <= height && j2.getRight() >= round && j2.getLeft() <= width) {
                RecyclerView.x b2 = this.z.b(j2);
                if (this.w.a(this.z, this.q, b2)) {
                    int abs = Math.abs(i3 - ((j2.getLeft() + j2.getRight()) / 2));
                    int abs2 = Math.abs(i4 - ((j2.getTop() + j2.getBottom()) / 2));
                    int i6 = (abs * abs) + (abs2 * abs2);
                    int size = this.R.size();
                    int i7 = 0;
                    for (int i8 = 0; i8 < size && i6 > this.S.get(i8).intValue(); i8++) {
                        i7++;
                    }
                    this.R.add(i7, b2);
                    this.S.add(i7, Integer.valueOf(i6));
                }
            }
            i5++;
            xVar2 = xVar;
        }
        return this.R;
    }

    private void d() {
        this.Q = ViewConfiguration.get(this.z.getContext()).getScaledTouchSlop();
        this.z.a((RecyclerView.h) this);
        this.z.a(this.V);
        this.z.a((RecyclerView.j) this);
        f();
    }

    private int e(RecyclerView.x xVar) {
        if (this.P == 2) {
            return 0;
        }
        int a2 = this.w.a(this.z, xVar);
        int d2 = (this.w.d(a2, ab.m(this.z)) & 65280) >> 8;
        if (d2 == 0) {
            return 0;
        }
        int i2 = (a2 & 65280) >> 8;
        if (Math.abs(this.t) > Math.abs(this.u)) {
            int b2 = b(xVar, d2);
            if (b2 > 0) {
                return (i2 & b2) == 0 ? AbstractC0050a.a(b2, ab.m(this.z)) : b2;
            }
            int c2 = c(xVar, d2);
            if (c2 > 0) {
                return c2;
            }
        } else {
            int c3 = c(xVar, d2);
            if (c3 > 0) {
                return c3;
            }
            int b3 = b(xVar, d2);
            if (b3 > 0) {
                return (i2 & b3) == 0 ? AbstractC0050a.a(b3, ab.m(this.z)) : b3;
            }
        }
        return 0;
    }

    private void e() {
        this.z.b((RecyclerView.h) this);
        this.z.b(this.V);
        this.z.b((RecyclerView.j) this);
        for (int size = this.y.size() - 1; size >= 0; size--) {
            this.w.e(this.z, this.y.get(0).h);
        }
        this.y.clear();
        this.C = null;
        this.D = -1;
        h();
        g();
    }

    private void f() {
        this.U = new b();
        this.E = new android.support.v4.view.e(this.z.getContext(), this.U);
    }

    private void g() {
        b bVar = this.U;
        if (bVar != null) {
            bVar.a();
            this.U = null;
        }
        if (this.E != null) {
            this.E = null;
        }
    }

    private void h() {
        VelocityTracker velocityTracker = this.B;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.B = null;
        }
    }

    private void i() {
        if (Build.VERSION.SDK_INT >= 21) {
            return;
        }
        if (this.T == null) {
            this.T = new RecyclerView.d() { // from class: android.support.v7.widget.a.a.5
                @Override // android.support.v7.widget.RecyclerView.d
                public int a(int i2, int i3) {
                    if (a.this.C == null) {
                        return i3;
                    }
                    int i4 = a.this.D;
                    if (i4 == -1) {
                        i4 = a.this.z.indexOfChild(a.this.C);
                        a.this.D = i4;
                    }
                    return i3 == i2 + (-1) ? i4 : i3 < i4 ? i3 : i3 + 1;
                }
            };
        }
        this.z.setChildDrawingOrderCallback(this.T);
    }

    View a(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.x xVar = this.q;
        if (xVar != null) {
            View view = xVar.a;
            if (a(view, x, y, this.N + this.t, this.O + this.u)) {
                return view;
            }
        }
        for (int size = this.y.size() - 1; size >= 0; size--) {
            c cVar = this.y.get(size);
            View view2 = cVar.h.a;
            if (a(view2, x, y, cVar.l, cVar.m)) {
                return view2;
            }
        }
        return this.z.a(x, y);
    }

    void a(int i2, MotionEvent motionEvent, int i3) {
        RecyclerView.x c2;
        int b2;
        if (this.q != null || i2 != 2 || this.P == 2 || !this.w.c() || this.z.getScrollState() == 1 || (c2 = c(motionEvent)) == null || (b2 = (this.w.b(this.z, c2) & 65280) >> 8) == 0) {
            return;
        }
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        float f2 = x - this.r;
        float f3 = y - this.s;
        float abs = Math.abs(f2);
        float abs2 = Math.abs(f3);
        int i4 = this.Q;
        if (abs >= i4 || abs2 >= i4) {
            if (abs > abs2) {
                if (f2 < 0.0f && (b2 & 4) == 0) {
                    return;
                }
                if (f2 > 0.0f && (b2 & 8) == 0) {
                    return;
                }
            } else if (f3 < 0.0f && (b2 & 1) == 0) {
                return;
            } else {
                if (f3 > 0.0f && (b2 & 2) == 0) {
                    return;
                }
            }
            this.u = 0.0f;
            this.t = 0.0f;
            this.v = motionEvent.getPointerId(0);
            a(c2, 1);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.h
    public void a(Canvas canvas, RecyclerView recyclerView, RecyclerView.u uVar) {
        float f2;
        float f3;
        this.D = -1;
        if (this.q != null) {
            a(this.K);
            float[] fArr = this.K;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.w.a(canvas, recyclerView, this.q, this.y, this.P, f2, f3);
    }

    @Override // android.support.v7.widget.RecyclerView.h
    public void a(Rect rect, View view, RecyclerView recyclerView, RecyclerView.u uVar) {
        rect.setEmpty();
    }

    void a(RecyclerView.x xVar) {
        if (!this.z.isLayoutRequested() && this.P == 2) {
            float b2 = this.w.b(xVar);
            int i2 = (int) (this.N + this.t);
            int i3 = (int) (this.O + this.u);
            if (Math.abs(i3 - xVar.a.getTop()) >= xVar.a.getHeight() * b2 || Math.abs(i2 - xVar.a.getLeft()) >= xVar.a.getWidth() * b2) {
                List<RecyclerView.x> d2 = d(xVar);
                if (d2.size() == 0) {
                    return;
                }
                RecyclerView.x a2 = this.w.a(xVar, d2, i2, i3);
                if (a2 == null) {
                    this.R.clear();
                    this.S.clear();
                    return;
                }
                int f2 = a2.f();
                int f3 = xVar.f();
                if (this.w.b(this.z, xVar, a2)) {
                    this.w.a(this.z, xVar, f3, a2, f2, i2, i3);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(@android.support.annotation.ag android.support.v7.widget.RecyclerView.x r24, int r25) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.a.a.a(android.support.v7.widget.RecyclerView$x, int):void");
    }

    void a(RecyclerView.x xVar, boolean z) {
        for (int size = this.y.size() - 1; size >= 0; size--) {
            c cVar = this.y.get(size);
            if (cVar.h == xVar) {
                cVar.n |= z;
                if (!cVar.o) {
                    cVar.b();
                }
                this.y.remove(size);
                return;
            }
        }
    }

    public void a(@ag RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.z;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            e();
        }
        this.z = recyclerView;
        if (recyclerView != null) {
            Resources resources = recyclerView.getResources();
            this.L = resources.getDimension(a.c.item_touch_helper_swipe_escape_velocity);
            this.M = resources.getDimension(a.c.item_touch_helper_swipe_escape_max_velocity);
            d();
        }
    }

    void a(final c cVar, final int i2) {
        this.z.post(new Runnable() { // from class: android.support.v7.widget.a.a.4
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.z == null || !a.this.z.isAttachedToWindow() || cVar.n || cVar.h.f() == -1) {
                    return;
                }
                RecyclerView.f itemAnimator = a.this.z.getItemAnimator();
                if ((itemAnimator == null || !itemAnimator.a((RecyclerView.f.b) null)) && !a.this.a()) {
                    a.this.w.a(cVar.h, i2);
                } else {
                    a.this.z.post(this);
                }
            }
        });
    }

    void a(MotionEvent motionEvent, int i2, int i3) {
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        this.t = x - this.r;
        this.u = y - this.s;
        if ((i2 & 4) == 0) {
            this.t = Math.max(0.0f, this.t);
        }
        if ((i2 & 8) == 0) {
            this.t = Math.min(0.0f, this.t);
        }
        if ((i2 & 1) == 0) {
            this.u = Math.max(0.0f, this.u);
        }
        if ((i2 & 2) == 0) {
            this.u = Math.min(0.0f, this.u);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.j
    public void a(@af View view) {
    }

    boolean a() {
        int size = this.y.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.y.get(i2).o) {
                return true;
            }
        }
        return false;
    }

    c b(MotionEvent motionEvent) {
        if (this.y.isEmpty()) {
            return null;
        }
        View a2 = a(motionEvent);
        for (int size = this.y.size() - 1; size >= 0; size--) {
            c cVar = this.y.get(size);
            if (cVar.h.a == a2) {
                return cVar;
            }
        }
        return null;
    }

    @Override // android.support.v7.widget.RecyclerView.h
    public void b(Canvas canvas, RecyclerView recyclerView, RecyclerView.u uVar) {
        float f2;
        float f3;
        if (this.q != null) {
            a(this.K);
            float[] fArr = this.K;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.w.b(canvas, recyclerView, this.q, this.y, this.P, f2, f3);
    }

    public void b(@af RecyclerView.x xVar) {
        if (!this.w.c(this.z, xVar)) {
            Log.e(F, "Start drag has been called but dragging is not enabled");
        } else if (xVar.a.getParent() != this.z) {
            Log.e(F, "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
        } else {
            c();
            this.u = 0.0f;
            this.t = 0.0f;
            a(xVar, 2);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.j
    public void b(@af View view) {
        c(view);
        RecyclerView.x b2 = this.z.b(view);
        if (b2 == null) {
            return;
        }
        RecyclerView.x xVar = this.q;
        if (xVar != null && b2 == xVar) {
            a((RecyclerView.x) null, 0);
            return;
        }
        a(b2, false);
        if (this.p.remove(b2.a)) {
            this.w.e(this.z, b2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c5, code lost:
        if (r1 > 0) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0104 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0110  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean b() {
        /*
            Method dump skipped, instructions count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.a.a.b():boolean");
    }

    void c() {
        VelocityTracker velocityTracker = this.B;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.B = VelocityTracker.obtain();
    }

    public void c(@af RecyclerView.x xVar) {
        if (!this.w.d(this.z, xVar)) {
            Log.e(F, "Start swipe has been called but swiping is not enabled");
        } else if (xVar.a.getParent() != this.z) {
            Log.e(F, "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
        } else {
            c();
            this.u = 0.0f;
            this.t = 0.0f;
            a(xVar, 1);
        }
    }

    void c(View view) {
        if (view == this.C) {
            this.C = null;
            if (this.T != null) {
                this.z.setChildDrawingOrderCallback(null);
            }
        }
    }
}
