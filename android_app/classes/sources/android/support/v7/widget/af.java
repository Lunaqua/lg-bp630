package android.support.v7.widget;

import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class af extends ao {
    private static final int c = 100;
    @android.support.annotation.ag
    private ae d;
    @android.support.annotation.ag
    private ae e;

    private int a(@android.support.annotation.af RecyclerView.i iVar, @android.support.annotation.af View view, ae aeVar) {
        return (aeVar.a(view) + (aeVar.e(view) / 2)) - (iVar.B() ? aeVar.d() + (aeVar.g() / 2) : aeVar.f() / 2);
    }

    @android.support.annotation.ag
    private View a(RecyclerView.i iVar, ae aeVar) {
        int G = iVar.G();
        View view = null;
        if (G == 0) {
            return null;
        }
        int d = iVar.B() ? aeVar.d() + (aeVar.g() / 2) : aeVar.f() / 2;
        int i = ActivityChooserView.a.a;
        for (int i2 = 0; i2 < G; i2++) {
            View j = iVar.j(i2);
            int abs = Math.abs((aeVar.a(j) + (aeVar.e(j) / 2)) - d);
            if (abs < i) {
                view = j;
                i = abs;
            }
        }
        return view;
    }

    @android.support.annotation.ag
    private View b(RecyclerView.i iVar, ae aeVar) {
        int G = iVar.G();
        View view = null;
        if (G == 0) {
            return null;
        }
        int i = ActivityChooserView.a.a;
        for (int i2 = 0; i2 < G; i2++) {
            View j = iVar.j(i2);
            int a = aeVar.a(j);
            if (a < i) {
                view = j;
                i = a;
            }
        }
        return view;
    }

    @android.support.annotation.af
    private ae d(@android.support.annotation.af RecyclerView.i iVar) {
        ae aeVar = this.d;
        if (aeVar == null || aeVar.a != iVar) {
            this.d = ae.b(iVar);
        }
        return this.d;
    }

    @android.support.annotation.af
    private ae e(@android.support.annotation.af RecyclerView.i iVar) {
        ae aeVar = this.e;
        if (aeVar == null || aeVar.a != iVar) {
            this.e = ae.a(iVar);
        }
        return this.e;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0060  */
    @Override // android.support.v7.widget.ao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a(android.support.v7.widget.RecyclerView.i r6, int r7, int r8) {
        /*
            r5 = this;
            int r0 = r6.U()
            r1 = -1
            if (r0 != 0) goto L8
            return r1
        L8:
            r2 = 0
            boolean r3 = r6.i()
            if (r3 == 0) goto L18
            android.support.v7.widget.ae r2 = r5.d(r6)
        L13:
            android.view.View r2 = r5.b(r6, r2)
            goto L23
        L18:
            boolean r3 = r6.h()
            if (r3 == 0) goto L23
            android.support.v7.widget.ae r2 = r5.e(r6)
            goto L13
        L23:
            if (r2 != 0) goto L26
            return r1
        L26:
            int r2 = r6.e(r2)
            if (r2 != r1) goto L2d
            return r1
        L2d:
            boolean r1 = r6.h()
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L3b
            if (r7 <= 0) goto L39
        L37:
            r7 = 1
            goto L3e
        L39:
            r7 = 0
            goto L3e
        L3b:
            if (r8 <= 0) goto L39
            goto L37
        L3e:
            boolean r8 = r6 instanceof android.support.v7.widget.RecyclerView.t.b
            if (r8 == 0) goto L59
            android.support.v7.widget.RecyclerView$t$b r6 = (android.support.v7.widget.RecyclerView.t.b) r6
            int r0 = r0 - r4
            android.graphics.PointF r6 = r6.d(r0)
            if (r6 == 0) goto L59
            float r8 = r6.x
            r0 = 0
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 < 0) goto L58
            float r6 = r6.y
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 >= 0) goto L59
        L58:
            r3 = 1
        L59:
            if (r3 == 0) goto L60
            if (r7 == 0) goto L64
            int r2 = r2 + (-1)
            goto L64
        L60:
            if (r7 == 0) goto L64
            int r2 = r2 + 1
        L64:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.af.a(android.support.v7.widget.RecyclerView$i, int, int):int");
    }

    @Override // android.support.v7.widget.ao
    @android.support.annotation.ag
    public View a(RecyclerView.i iVar) {
        ae e;
        if (iVar.i()) {
            e = d(iVar);
        } else if (!iVar.h()) {
            return null;
        } else {
            e = e(iVar);
        }
        return a(iVar, e);
    }

    @Override // android.support.v7.widget.ao
    @android.support.annotation.ag
    public int[] a(@android.support.annotation.af RecyclerView.i iVar, @android.support.annotation.af View view) {
        int[] iArr = new int[2];
        if (iVar.h()) {
            iArr[0] = a(iVar, view, e(iVar));
        } else {
            iArr[0] = 0;
        }
        if (iVar.i()) {
            iArr[1] = a(iVar, view, d(iVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // android.support.v7.widget.ao
    protected z b(RecyclerView.i iVar) {
        if (iVar instanceof RecyclerView.t.b) {
            return new z(this.b.getContext()) { // from class: android.support.v7.widget.af.1
                @Override // android.support.v7.widget.z
                protected float a(DisplayMetrics displayMetrics) {
                    return 100.0f / displayMetrics.densityDpi;
                }

                @Override // android.support.v7.widget.z, android.support.v7.widget.RecyclerView.t
                protected void a(View view, RecyclerView.u uVar, RecyclerView.t.a aVar) {
                    af afVar = af.this;
                    int[] a = afVar.a(afVar.b.getLayoutManager(), view);
                    int i = a[0];
                    int i2 = a[1];
                    int a2 = a(Math.max(Math.abs(i), Math.abs(i2)));
                    if (a2 > 0) {
                        aVar.a(i, i2, a2, this.e);
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.support.v7.widget.z
                public int b(int i) {
                    return Math.min(100, super.b(i));
                }
            };
        }
        return null;
    }
}
