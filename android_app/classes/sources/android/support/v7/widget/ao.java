package android.support.v7.widget;

import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class ao extends RecyclerView.k {
    static final float a = 100.0f;
    RecyclerView b;
    private Scroller c;
    private final RecyclerView.m d = new RecyclerView.m() { // from class: android.support.v7.widget.ao.1
        boolean a = false;

        @Override // android.support.v7.widget.RecyclerView.m
        public void a(RecyclerView recyclerView, int i) {
            super.a(recyclerView, i);
            if (i == 0 && this.a) {
                this.a = false;
                ao.this.a();
            }
        }

        @Override // android.support.v7.widget.RecyclerView.m
        public void a(RecyclerView recyclerView, int i, int i2) {
            if (i == 0 && i2 == 0) {
                return;
            }
            this.a = true;
        }
    };

    private void b() {
        if (this.b.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.b.a(this.d);
        this.b.setOnFlingListener(this);
    }

    private boolean b(@android.support.annotation.af RecyclerView.i iVar, int i, int i2) {
        RecyclerView.t c;
        int a2;
        if (!(iVar instanceof RecyclerView.t.b) || (c = c(iVar)) == null || (a2 = a(iVar, i, i2)) == -1) {
            return false;
        }
        c.c(a2);
        iVar.a(c);
        return true;
    }

    private void c() {
        this.b.b(this.d);
        this.b.setOnFlingListener(null);
    }

    public abstract int a(RecyclerView.i iVar, int i, int i2);

    @android.support.annotation.ag
    public abstract View a(RecyclerView.i iVar);

    void a() {
        RecyclerView.i layoutManager;
        View a2;
        RecyclerView recyclerView = this.b;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (a2 = a(layoutManager)) == null) {
            return;
        }
        int[] a3 = a(layoutManager, a2);
        if (a3[0] == 0 && a3[1] == 0) {
            return;
        }
        this.b.b(a3[0], a3[1]);
    }

    public void a(@android.support.annotation.ag RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.b;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            c();
        }
        this.b = recyclerView;
        if (this.b != null) {
            b();
            this.c = new Scroller(this.b.getContext(), new DecelerateInterpolator());
            a();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.k
    public boolean a(int i, int i2) {
        RecyclerView.i layoutManager = this.b.getLayoutManager();
        if (layoutManager == null || this.b.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.b.getMinFlingVelocity();
        return (Math.abs(i2) > minFlingVelocity || Math.abs(i) > minFlingVelocity) && b(layoutManager, i, i2);
    }

    @android.support.annotation.ag
    public abstract int[] a(@android.support.annotation.af RecyclerView.i iVar, @android.support.annotation.af View view);

    @android.support.annotation.ag
    @Deprecated
    protected z b(RecyclerView.i iVar) {
        if (iVar instanceof RecyclerView.t.b) {
            return new z(this.b.getContext()) { // from class: android.support.v7.widget.ao.2
                @Override // android.support.v7.widget.z
                protected float a(DisplayMetrics displayMetrics) {
                    return ao.a / displayMetrics.densityDpi;
                }

                @Override // android.support.v7.widget.z, android.support.v7.widget.RecyclerView.t
                protected void a(View view, RecyclerView.u uVar, RecyclerView.t.a aVar) {
                    if (ao.this.b == null) {
                        return;
                    }
                    ao aoVar = ao.this;
                    int[] a2 = aoVar.a(aoVar.b.getLayoutManager(), view);
                    int i = a2[0];
                    int i2 = a2[1];
                    int a3 = a(Math.max(Math.abs(i), Math.abs(i2)));
                    if (a3 > 0) {
                        aVar.a(i, i2, a3, this.e);
                    }
                }
            };
        }
        return null;
    }

    public int[] b(int i, int i2) {
        this.c.fling(0, 0, i, i2, Integer.MIN_VALUE, ActivityChooserView.a.a, Integer.MIN_VALUE, ActivityChooserView.a.a);
        return new int[]{this.c.getFinalX(), this.c.getFinalY()};
    }

    @android.support.annotation.ag
    protected RecyclerView.t c(RecyclerView.i iVar) {
        return b(iVar);
    }
}
