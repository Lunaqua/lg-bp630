package android.support.v7.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class ae {
    public static final int b = 0;
    public static final int c = 1;
    private static final int e = Integer.MIN_VALUE;
    protected final RecyclerView.i a;
    final Rect d;
    private int f;

    private ae(RecyclerView.i iVar) {
        this.f = Integer.MIN_VALUE;
        this.d = new Rect();
        this.a = iVar;
    }

    public static ae a(RecyclerView.i iVar) {
        return new ae(iVar) { // from class: android.support.v7.widget.ae.1
            @Override // android.support.v7.widget.ae
            public int a(View view) {
                return this.a.o(view) - ((RecyclerView.LayoutParams) view.getLayoutParams()).leftMargin;
            }

            @Override // android.support.v7.widget.ae
            public void a(int i) {
                this.a.k(i);
            }

            @Override // android.support.v7.widget.ae
            public void a(View view, int i) {
                view.offsetLeftAndRight(i);
            }

            @Override // android.support.v7.widget.ae
            public int b(View view) {
                return this.a.q(view) + ((RecyclerView.LayoutParams) view.getLayoutParams()).rightMargin;
            }

            @Override // android.support.v7.widget.ae
            public int c(View view) {
                this.a.a(view, true, this.d);
                return this.d.right;
            }

            @Override // android.support.v7.widget.ae
            public int d() {
                return this.a.L();
            }

            @Override // android.support.v7.widget.ae
            public int d(View view) {
                this.a.a(view, true, this.d);
                return this.d.left;
            }

            @Override // android.support.v7.widget.ae
            public int e() {
                return this.a.J() - this.a.N();
            }

            @Override // android.support.v7.widget.ae
            public int e(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return this.a.m(view) + layoutParams.leftMargin + layoutParams.rightMargin;
            }

            @Override // android.support.v7.widget.ae
            public int f() {
                return this.a.J();
            }

            @Override // android.support.v7.widget.ae
            public int f(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return this.a.n(view) + layoutParams.topMargin + layoutParams.bottomMargin;
            }

            @Override // android.support.v7.widget.ae
            public int g() {
                return (this.a.J() - this.a.L()) - this.a.N();
            }

            @Override // android.support.v7.widget.ae
            public int h() {
                return this.a.N();
            }

            @Override // android.support.v7.widget.ae
            public int i() {
                return this.a.H();
            }

            @Override // android.support.v7.widget.ae
            public int j() {
                return this.a.I();
            }
        };
    }

    public static ae a(RecyclerView.i iVar, int i) {
        if (i != 0) {
            if (i == 1) {
                return b(iVar);
            }
            throw new IllegalArgumentException("invalid orientation");
        }
        return a(iVar);
    }

    public static ae b(RecyclerView.i iVar) {
        return new ae(iVar) { // from class: android.support.v7.widget.ae.2
            @Override // android.support.v7.widget.ae
            public int a(View view) {
                return this.a.p(view) - ((RecyclerView.LayoutParams) view.getLayoutParams()).topMargin;
            }

            @Override // android.support.v7.widget.ae
            public void a(int i) {
                this.a.l(i);
            }

            @Override // android.support.v7.widget.ae
            public void a(View view, int i) {
                view.offsetTopAndBottom(i);
            }

            @Override // android.support.v7.widget.ae
            public int b(View view) {
                return this.a.r(view) + ((RecyclerView.LayoutParams) view.getLayoutParams()).bottomMargin;
            }

            @Override // android.support.v7.widget.ae
            public int c(View view) {
                this.a.a(view, true, this.d);
                return this.d.bottom;
            }

            @Override // android.support.v7.widget.ae
            public int d() {
                return this.a.M();
            }

            @Override // android.support.v7.widget.ae
            public int d(View view) {
                this.a.a(view, true, this.d);
                return this.d.top;
            }

            @Override // android.support.v7.widget.ae
            public int e() {
                return this.a.K() - this.a.O();
            }

            @Override // android.support.v7.widget.ae
            public int e(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return this.a.n(view) + layoutParams.topMargin + layoutParams.bottomMargin;
            }

            @Override // android.support.v7.widget.ae
            public int f() {
                return this.a.K();
            }

            @Override // android.support.v7.widget.ae
            public int f(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return this.a.m(view) + layoutParams.leftMargin + layoutParams.rightMargin;
            }

            @Override // android.support.v7.widget.ae
            public int g() {
                return (this.a.K() - this.a.M()) - this.a.O();
            }

            @Override // android.support.v7.widget.ae
            public int h() {
                return this.a.O();
            }

            @Override // android.support.v7.widget.ae
            public int i() {
                return this.a.I();
            }

            @Override // android.support.v7.widget.ae
            public int j() {
                return this.a.H();
            }
        };
    }

    public abstract int a(View view);

    public RecyclerView.i a() {
        return this.a;
    }

    public abstract void a(int i);

    public abstract void a(View view, int i);

    public abstract int b(View view);

    public void b() {
        this.f = g();
    }

    public int c() {
        if (Integer.MIN_VALUE == this.f) {
            return 0;
        }
        return g() - this.f;
    }

    public abstract int c(View view);

    public abstract int d();

    public abstract int d(View view);

    public abstract int e();

    public abstract int e(View view);

    public abstract int f();

    public abstract int f(View view);

    public abstract int g();

    public abstract int h();

    public abstract int i();

    public abstract int j();
}
