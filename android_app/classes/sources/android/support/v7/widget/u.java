package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
/* JADX INFO: Access modifiers changed from: package-private */
@android.support.annotation.av
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class u extends RecyclerView.h implements RecyclerView.l {
    private static final int k = 0;
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 0;
    private static final int o = 1;
    private static final int p = 2;
    private static final int q = 0;
    private static final int r = 1;
    private static final int s = 2;
    private static final int t = 3;
    private static final int u = 500;
    private static final int v = 1500;
    private static final int w = 1200;
    private static final int x = 500;
    private static final int y = 255;
    private final int B;
    private final int C;
    private final int D;
    private final int E;
    private final StateListDrawable F;
    private final Drawable G;
    private final int H;
    private final int I;
    private RecyclerView L;
    final StateListDrawable a;
    final Drawable b;
    @android.support.annotation.av
    int c;
    @android.support.annotation.av
    int d;
    @android.support.annotation.av
    float e;
    @android.support.annotation.av
    int f;
    @android.support.annotation.av
    int g;
    @android.support.annotation.av
    float h;
    private static final int[] z = {16842919};
    private static final int[] A = new int[0];
    private int J = 0;
    private int K = 0;
    private boolean M = false;
    private boolean N = false;
    private int O = 0;
    private int P = 0;
    private final int[] Q = new int[2];
    private final int[] R = new int[2];
    final ValueAnimator i = ValueAnimator.ofFloat(0.0f, 1.0f);
    int j = 0;
    private final Runnable S = new Runnable() { // from class: android.support.v7.widget.u.1
        @Override // java.lang.Runnable
        public void run() {
            u.this.b(500);
        }
    };
    private final RecyclerView.m T = new RecyclerView.m() { // from class: android.support.v7.widget.u.2
        @Override // android.support.v7.widget.RecyclerView.m
        public void a(RecyclerView recyclerView, int i, int i2) {
            u.this.a(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    };

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class a extends AnimatorListenerAdapter {
        private boolean b = false;

        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.b = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.b) {
                this.b = false;
            } else if (((Float) u.this.i.getAnimatedValue()).floatValue() == 0.0f) {
                u uVar = u.this;
                uVar.j = 0;
                uVar.a(0);
            } else {
                u uVar2 = u.this;
                uVar2.j = 2;
                uVar2.a();
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            u.this.a.setAlpha(floatValue);
            u.this.b.setAlpha(floatValue);
            u.this.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        this.a = stateListDrawable;
        this.b = drawable;
        this.F = stateListDrawable2;
        this.G = drawable2;
        this.D = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.E = Math.max(i, drawable.getIntrinsicWidth());
        this.H = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.I = Math.max(i, drawable2.getIntrinsicWidth());
        this.B = i2;
        this.C = i3;
        this.a.setAlpha(255);
        this.b.setAlpha(255);
        this.i.addListener(new a());
        this.i.addUpdateListener(new b());
        a(recyclerView);
    }

    private int a(float f, float f2, int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[1] - iArr[0];
        if (i4 == 0) {
            return 0;
        }
        int i5 = i - i3;
        int i6 = (int) (((f2 - f) / i4) * i5);
        int i7 = i2 + i6;
        if (i7 >= i5 || i7 < 0) {
            return 0;
        }
        return i6;
    }

    private void a(float f) {
        int[] o2 = o();
        float max = Math.max(o2[0], Math.min(o2[1], f));
        if (Math.abs(this.d - max) < 2.0f) {
            return;
        }
        int a2 = a(this.e, max, o2, this.L.computeVerticalScrollRange(), this.L.computeVerticalScrollOffset(), this.K);
        if (a2 != 0) {
            this.L.scrollBy(0, a2);
        }
        this.e = max;
    }

    private void a(Canvas canvas) {
        int i = this.J;
        int i2 = this.D;
        int i3 = i - i2;
        int i4 = this.d;
        int i5 = this.c;
        int i6 = i4 - (i5 / 2);
        this.a.setBounds(0, 0, i2, i5);
        this.b.setBounds(0, 0, this.E, this.K);
        if (m()) {
            this.b.draw(canvas);
            canvas.translate(this.D, i6);
            canvas.scale(-1.0f, 1.0f);
            this.a.draw(canvas);
            canvas.scale(1.0f, 1.0f);
            i3 = this.D;
        } else {
            canvas.translate(i3, 0.0f);
            this.b.draw(canvas);
            canvas.translate(0.0f, i6);
            this.a.draw(canvas);
        }
        canvas.translate(-i3, -i6);
    }

    private void b(float f) {
        int[] p2 = p();
        float max = Math.max(p2[0], Math.min(p2[1], f));
        if (Math.abs(this.g - max) < 2.0f) {
            return;
        }
        int a2 = a(this.h, max, p2, this.L.computeHorizontalScrollRange(), this.L.computeHorizontalScrollOffset(), this.J);
        if (a2 != 0) {
            this.L.scrollBy(a2, 0);
        }
        this.h = max;
    }

    private void b(Canvas canvas) {
        int i = this.K;
        int i2 = this.H;
        int i3 = i - i2;
        int i4 = this.g;
        int i5 = this.f;
        int i6 = i4 - (i5 / 2);
        this.F.setBounds(0, 0, i5, i2);
        this.G.setBounds(0, 0, this.J, this.I);
        canvas.translate(0.0f, i3);
        this.G.draw(canvas);
        canvas.translate(i6, 0.0f);
        this.F.draw(canvas);
        canvas.translate(-i6, -i3);
    }

    private void c(int i) {
        n();
        this.L.postDelayed(this.S, i);
    }

    private void k() {
        this.L.a((RecyclerView.h) this);
        this.L.a((RecyclerView.l) this);
        this.L.a(this.T);
    }

    private void l() {
        this.L.b((RecyclerView.h) this);
        this.L.b((RecyclerView.l) this);
        this.L.b(this.T);
        n();
    }

    private boolean m() {
        return android.support.v4.view.ab.m(this.L) == 1;
    }

    private void n() {
        this.L.removeCallbacks(this.S);
    }

    private int[] o() {
        int[] iArr = this.Q;
        int i = this.C;
        iArr[0] = i;
        iArr[1] = this.K - i;
        return iArr;
    }

    private int[] p() {
        int[] iArr = this.R;
        int i = this.C;
        iArr[0] = i;
        iArr[1] = this.J - i;
        return iArr;
    }

    void a() {
        this.L.invalidate();
    }

    void a(int i) {
        int i2;
        if (i == 2 && this.O != 2) {
            this.a.setState(z);
            n();
        }
        if (i == 0) {
            a();
        } else {
            e();
        }
        if (this.O != 2 || i == 2) {
            if (i == 1) {
                i2 = v;
            }
            this.O = i;
        }
        this.a.setState(A);
        i2 = w;
        c(i2);
        this.O = i;
    }

    void a(int i, int i2) {
        int computeVerticalScrollRange = this.L.computeVerticalScrollRange();
        int i3 = this.K;
        this.M = computeVerticalScrollRange - i3 > 0 && i3 >= this.B;
        int computeHorizontalScrollRange = this.L.computeHorizontalScrollRange();
        int i4 = this.J;
        this.N = computeHorizontalScrollRange - i4 > 0 && i4 >= this.B;
        if (!this.M && !this.N) {
            if (this.O != 0) {
                a(0);
                return;
            }
            return;
        }
        if (this.M) {
            float f = i3;
            this.d = (int) ((f * (i2 + (f / 2.0f))) / computeVerticalScrollRange);
            this.c = Math.min(i3, (i3 * i3) / computeVerticalScrollRange);
        }
        if (this.N) {
            float f2 = i4;
            this.g = (int) ((f2 * (i + (f2 / 2.0f))) / computeHorizontalScrollRange);
            this.f = Math.min(i4, (i4 * i4) / computeHorizontalScrollRange);
        }
        int i5 = this.O;
        if (i5 == 0 || i5 == 1) {
            a(1);
        }
    }

    public void a(@android.support.annotation.ag RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.L;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            l();
        }
        this.L = recyclerView;
        if (this.L != null) {
            k();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.l
    public void a(boolean z2) {
    }

    @android.support.annotation.av
    boolean a(float f, float f2) {
        if (!m() ? f >= this.J - this.D : f <= this.D / 2) {
            int i = this.d;
            int i2 = this.c;
            if (f2 >= i - (i2 / 2) && f2 <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.l
    public boolean a(@android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af MotionEvent motionEvent) {
        int i = this.O;
        if (i == 1) {
            boolean a2 = a(motionEvent.getX(), motionEvent.getY());
            boolean b2 = b(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!a2 && !b2) {
                return false;
            }
            if (b2) {
                this.P = 1;
                this.h = (int) motionEvent.getX();
            } else if (a2) {
                this.P = 2;
                this.e = (int) motionEvent.getY();
            }
            a(2);
        } else if (i != 2) {
            return false;
        }
        return true;
    }

    @android.support.annotation.av
    void b(int i) {
        int i2 = this.j;
        if (i2 == 1) {
            this.i.cancel();
        } else if (i2 != 2) {
            return;
        }
        this.j = 3;
        ValueAnimator valueAnimator = this.i;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.i.setDuration(i);
        this.i.start();
    }

    @Override // android.support.v7.widget.RecyclerView.h
    public void b(Canvas canvas, RecyclerView recyclerView, RecyclerView.u uVar) {
        if (this.J != this.L.getWidth() || this.K != this.L.getHeight()) {
            this.J = this.L.getWidth();
            this.K = this.L.getHeight();
            a(0);
        } else if (this.j != 0) {
            if (this.M) {
                a(canvas);
            }
            if (this.N) {
                b(canvas);
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.l
    public void b(@android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af MotionEvent motionEvent) {
        if (this.O == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean a2 = a(motionEvent.getX(), motionEvent.getY());
            boolean b2 = b(motionEvent.getX(), motionEvent.getY());
            if (a2 || b2) {
                if (b2) {
                    this.P = 1;
                    this.h = (int) motionEvent.getX();
                } else if (a2) {
                    this.P = 2;
                    this.e = (int) motionEvent.getY();
                }
                a(2);
            }
        } else if (motionEvent.getAction() == 1 && this.O == 2) {
            this.e = 0.0f;
            this.h = 0.0f;
            a(1);
            this.P = 0;
        } else if (motionEvent.getAction() == 2 && this.O == 2) {
            e();
            if (this.P == 1) {
                b(motionEvent.getX());
            }
            if (this.P == 2) {
                a(motionEvent.getY());
            }
        }
    }

    public boolean b() {
        return this.O == 2;
    }

    @android.support.annotation.av
    boolean b(float f, float f2) {
        if (f2 >= this.K - this.H) {
            int i = this.g;
            int i2 = this.f;
            if (f >= i - (i2 / 2) && f <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }

    @android.support.annotation.av
    boolean c() {
        return this.O == 1;
    }

    @android.support.annotation.av
    boolean d() {
        return this.O == 0;
    }

    public void e() {
        int i = this.j;
        if (i != 0) {
            if (i != 3) {
                return;
            }
            this.i.cancel();
        }
        this.j = 1;
        ValueAnimator valueAnimator = this.i;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.i.setDuration(500L);
        this.i.setStartDelay(0L);
        this.i.start();
    }

    public void f() {
        b(0);
    }

    @android.support.annotation.av
    Drawable g() {
        return this.G;
    }

    @android.support.annotation.av
    Drawable h() {
        return this.F;
    }

    @android.support.annotation.av
    Drawable i() {
        return this.b;
    }

    @android.support.annotation.av
    Drawable j() {
        return this.a;
    }
}
