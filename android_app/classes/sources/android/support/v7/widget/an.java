package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class an extends RecyclerView.f {
    private static final boolean a = false;
    private static final String b = "SimpleItemAnimator";
    boolean m = true;

    public final void a(RecyclerView.x xVar, boolean z) {
        d(xVar, z);
        f(xVar);
    }

    public void a(boolean z) {
        this.m = z;
    }

    public abstract boolean a(RecyclerView.x xVar);

    public abstract boolean a(RecyclerView.x xVar, int i, int i2, int i3, int i4);

    @Override // android.support.v7.widget.RecyclerView.f
    public boolean a(@android.support.annotation.af RecyclerView.x xVar, @android.support.annotation.af RecyclerView.f.d dVar, @android.support.annotation.ag RecyclerView.f.d dVar2) {
        int i = dVar.a;
        int i2 = dVar.b;
        View view = xVar.a;
        int left = dVar2 == null ? view.getLeft() : dVar2.a;
        int top = dVar2 == null ? view.getTop() : dVar2.b;
        if (xVar.s() || (i == left && i2 == top)) {
            return a(xVar);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return a(xVar, i, i2, left, top);
    }

    public abstract boolean a(RecyclerView.x xVar, RecyclerView.x xVar2, int i, int i2, int i3, int i4);

    @Override // android.support.v7.widget.RecyclerView.f
    public boolean a(@android.support.annotation.af RecyclerView.x xVar, @android.support.annotation.af RecyclerView.x xVar2, @android.support.annotation.af RecyclerView.f.d dVar, @android.support.annotation.af RecyclerView.f.d dVar2) {
        int i;
        int i2;
        int i3 = dVar.a;
        int i4 = dVar.b;
        if (xVar2.c()) {
            int i5 = dVar.a;
            i2 = dVar.b;
            i = i5;
        } else {
            i = dVar2.a;
            i2 = dVar2.b;
        }
        return a(xVar, xVar2, i3, i4, i, i2);
    }

    public final void b(RecyclerView.x xVar, boolean z) {
        c(xVar, z);
    }

    public abstract boolean b(RecyclerView.x xVar);

    @Override // android.support.v7.widget.RecyclerView.f
    public boolean b(@android.support.annotation.af RecyclerView.x xVar, @android.support.annotation.ag RecyclerView.f.d dVar, @android.support.annotation.af RecyclerView.f.d dVar2) {
        return (dVar == null || (dVar.a == dVar2.a && dVar.b == dVar2.b)) ? b(xVar) : a(xVar, dVar.a, dVar.b, dVar2.a, dVar2.b);
    }

    public void c(RecyclerView.x xVar, boolean z) {
    }

    @Override // android.support.v7.widget.RecyclerView.f
    public boolean c(@android.support.annotation.af RecyclerView.x xVar, @android.support.annotation.af RecyclerView.f.d dVar, @android.support.annotation.af RecyclerView.f.d dVar2) {
        if (dVar.a == dVar2.a && dVar.b == dVar2.b) {
            l(xVar);
            return false;
        }
        return a(xVar, dVar.a, dVar.b, dVar2.a, dVar2.b);
    }

    public void d(RecyclerView.x xVar, boolean z) {
    }

    @Override // android.support.v7.widget.RecyclerView.f
    public boolean j(@android.support.annotation.af RecyclerView.x xVar) {
        return !this.m || xVar.p();
    }

    public final void k(RecyclerView.x xVar) {
        r(xVar);
        f(xVar);
    }

    public boolean k() {
        return this.m;
    }

    public final void l(RecyclerView.x xVar) {
        v(xVar);
        f(xVar);
    }

    public final void m(RecyclerView.x xVar) {
        t(xVar);
        f(xVar);
    }

    public final void n(RecyclerView.x xVar) {
        q(xVar);
    }

    public final void o(RecyclerView.x xVar) {
        u(xVar);
    }

    public final void p(RecyclerView.x xVar) {
        s(xVar);
    }

    public void q(RecyclerView.x xVar) {
    }

    public void r(RecyclerView.x xVar) {
    }

    public void s(RecyclerView.x xVar) {
    }

    public void t(RecyclerView.x xVar) {
    }

    public void u(RecyclerView.x xVar) {
    }

    public void v(RecyclerView.x xVar) {
    }
}
