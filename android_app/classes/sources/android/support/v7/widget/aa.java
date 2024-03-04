package android.support.v7.widget;

import android.graphics.PointF;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class aa extends ao {
    private static final float c = 1.0f;
    @android.support.annotation.ag
    private ae d;
    @android.support.annotation.ag
    private ae e;

    private int a(RecyclerView.i iVar, ae aeVar, int i, int i2) {
        int[] b = b(i, i2);
        float b2 = b(iVar, aeVar);
        if (b2 <= 0.0f) {
            return 0;
        }
        return Math.round((Math.abs(b[0]) > Math.abs(b[1]) ? b[0] : b[1]) / b2);
    }

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

    private float b(RecyclerView.i iVar, ae aeVar) {
        int G = iVar.G();
        if (G == 0) {
            return c;
        }
        View view = null;
        View view2 = null;
        int i = ActivityChooserView.a.a;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < G; i3++) {
            View j = iVar.j(i3);
            int e = iVar.e(j);
            if (e != -1) {
                if (e < i) {
                    view = j;
                    i = e;
                }
                if (e > i2) {
                    view2 = j;
                    i2 = e;
                }
            }
        }
        if (view == null || view2 == null) {
            return c;
        }
        int max = Math.max(aeVar.b(view), aeVar.b(view2)) - Math.min(aeVar.a(view), aeVar.a(view2));
        return max == 0 ? c : (max * c) / ((i2 - i) + 1);
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

    @Override // android.support.v7.widget.ao
    public int a(RecyclerView.i iVar, int i, int i2) {
        int U;
        View a;
        int e;
        int i3;
        PointF d;
        int i4;
        int i5;
        if (!(iVar instanceof RecyclerView.t.b) || (U = iVar.U()) == 0 || (a = a(iVar)) == null || (e = iVar.e(a)) == -1 || (d = ((RecyclerView.t.b) iVar).d(U - 1)) == null) {
            return -1;
        }
        if (iVar.h()) {
            i4 = a(iVar, e(iVar), i, 0);
            if (d.x < 0.0f) {
                i4 = -i4;
            }
        } else {
            i4 = 0;
        }
        if (iVar.i()) {
            i5 = a(iVar, d(iVar), 0, i2);
            if (d.y < 0.0f) {
                i5 = -i5;
            }
        } else {
            i5 = 0;
        }
        if (iVar.i()) {
            i4 = i5;
        }
        if (i4 == 0) {
            return -1;
        }
        int i6 = e + i4;
        if (i6 < 0) {
            i6 = 0;
        }
        return i6 >= U ? i3 : i6;
    }

    @Override // android.support.v7.widget.ao
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
}
