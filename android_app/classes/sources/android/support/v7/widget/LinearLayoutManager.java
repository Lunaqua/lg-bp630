package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.an;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.a.a;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class LinearLayoutManager extends RecyclerView.i implements RecyclerView.t.b, a.e {
    private static final String a = "LinearLayoutManager";
    private static final float b = 0.33333334f;
    static final boolean j = false;
    public static final int k = 0;
    public static final int l = 1;
    public static final int m = Integer.MIN_VALUE;
    private int E;
    private c c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private final b i;
    int n;
    ae o;
    boolean p;
    int q;
    int r;
    SavedState s;
    final a t;

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.support.v7.widget.LinearLayoutManager.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a;
        int b;
        boolean c;

        public SavedState() {
        }

        SavedState(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.a = savedState.a;
            this.b = savedState.b;
            this.c = savedState.c;
        }

        boolean a() {
            return this.a >= 0;
        }

        void b() {
            this.a = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c ? 1 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        ae a;
        int b;
        int c;
        boolean d;
        boolean e;

        a() {
            a();
        }

        void a() {
            this.b = -1;
            this.c = Integer.MIN_VALUE;
            this.d = false;
            this.e = false;
        }

        public void a(View view, int i) {
            int c = this.a.c();
            if (c >= 0) {
                b(view, i);
                return;
            }
            this.b = i;
            if (this.d) {
                int e = (this.a.e() - c) - this.a.b(view);
                this.c = this.a.e() - e;
                if (e > 0) {
                    int e2 = this.c - this.a.e(view);
                    int d = this.a.d();
                    int min = e2 - (d + Math.min(this.a.a(view) - d, 0));
                    if (min < 0) {
                        this.c += Math.min(e, -min);
                        return;
                    }
                    return;
                }
                return;
            }
            int a = this.a.a(view);
            int d2 = a - this.a.d();
            this.c = a;
            if (d2 > 0) {
                int e3 = (this.a.e() - Math.min(0, (this.a.e() - c) - this.a.b(view))) - (a + this.a.e(view));
                if (e3 < 0) {
                    this.c -= Math.min(d2, -e3);
                }
            }
        }

        boolean a(View view, RecyclerView.u uVar) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return !layoutParams.e() && layoutParams.h() >= 0 && layoutParams.h() < uVar.i();
        }

        void b() {
            this.c = this.d ? this.a.e() : this.a.d();
        }

        public void b(View view, int i) {
            this.c = this.d ? this.a.b(view) + this.a.c() : this.a.a(view);
            this.b = i;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.b + ", mCoordinate=" + this.c + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.e + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        public int a;
        public boolean b;
        public boolean c;
        public boolean d;

        protected b() {
        }

        void a() {
            this.a = 0;
            this.b = false;
            this.c = false;
            this.d = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class c {
        static final String a = "LLM#LayoutState";
        static final int b = -1;
        static final int c = 1;
        static final int d = Integer.MIN_VALUE;
        static final int e = -1;
        static final int f = 1;
        static final int g = Integer.MIN_VALUE;
        int i;
        int j;
        int k;
        int l;
        int m;
        int n;
        int q;
        boolean s;
        boolean h = true;
        int o = 0;
        boolean p = false;
        List<RecyclerView.x> r = null;

        c() {
        }

        private View c() {
            int size = this.r.size();
            for (int i = 0; i < size; i++) {
                View view = this.r.get(i).a;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (!layoutParams.e() && this.k == layoutParams.h()) {
                    a(view);
                    return view;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public View a(RecyclerView.p pVar) {
            if (this.r != null) {
                return c();
            }
            View c2 = pVar.c(this.k);
            this.k += this.l;
            return c2;
        }

        public void a() {
            a((View) null);
        }

        public void a(View view) {
            View b2 = b(view);
            this.k = b2 == null ? -1 : ((RecyclerView.LayoutParams) b2.getLayoutParams()).h();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(RecyclerView.u uVar) {
            int i = this.k;
            return i >= 0 && i < uVar.i();
        }

        public View b(View view) {
            int h;
            int size = this.r.size();
            View view2 = null;
            int i = ActivityChooserView.a.a;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.r.get(i2).a;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view3.getLayoutParams();
                if (view3 != view && !layoutParams.e() && (h = (layoutParams.h() - this.k) * this.l) >= 0 && h < i) {
                    if (h == 0) {
                        return view3;
                    }
                    view2 = view3;
                    i = h;
                }
            }
            return view2;
        }

        void b() {
            Log.d(a, "avail:" + this.j + ", ind:" + this.k + ", dir:" + this.l + ", offset:" + this.i + ", layoutDir:" + this.m);
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        this.n = 1;
        this.e = false;
        this.p = false;
        this.f = false;
        this.g = true;
        this.q = -1;
        this.r = Integer.MIN_VALUE;
        this.s = null;
        this.t = new a();
        this.i = new b();
        this.E = 2;
        b(i);
        c(z);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.n = 1;
        this.e = false;
        this.p = false;
        this.f = false;
        this.g = true;
        this.q = -1;
        this.r = Integer.MIN_VALUE;
        this.s = null;
        this.t = new a();
        this.i = new b();
        this.E = 2;
        RecyclerView.i.b a2 = a(context, attributeSet, i, i2);
        b(a2.a);
        c(a2.c);
        a(a2.d);
    }

    private int a(int i, RecyclerView.p pVar, RecyclerView.u uVar, boolean z) {
        int e;
        int e2 = this.o.e() - i;
        if (e2 > 0) {
            int i2 = -c(-e2, pVar, uVar);
            int i3 = i + i2;
            if (!z || (e = this.o.e() - i3) <= 0) {
                return i2;
            }
            this.o.a(e);
            return e + i2;
        }
        return 0;
    }

    private View a(boolean z, boolean z2) {
        int i;
        int G;
        if (this.p) {
            i = G() - 1;
            G = -1;
        } else {
            i = 0;
            G = G();
        }
        return a(i, G, z, z2);
    }

    private void a(int i, int i2) {
        this.c.j = this.o.e() - i2;
        this.c.l = this.p ? -1 : 1;
        c cVar = this.c;
        cVar.k = i;
        cVar.m = 1;
        cVar.i = i2;
        cVar.n = Integer.MIN_VALUE;
    }

    private void a(int i, int i2, boolean z, RecyclerView.u uVar) {
        int d;
        this.c.s = q();
        this.c.o = b(uVar);
        c cVar = this.c;
        cVar.m = i;
        if (i == 1) {
            cVar.o += this.o.h();
            View ab = ab();
            this.c.l = this.p ? -1 : 1;
            this.c.k = e(ab) + this.c.l;
            this.c.i = this.o.b(ab);
            d = this.o.b(ab) - this.o.e();
        } else {
            View c2 = c();
            this.c.o += this.o.d();
            this.c.l = this.p ? 1 : -1;
            this.c.k = e(c2) + this.c.l;
            this.c.i = this.o.a(c2);
            d = (-this.o.a(c2)) + this.o.d();
        }
        c cVar2 = this.c;
        cVar2.j = i2;
        if (z) {
            cVar2.j -= d;
        }
        this.c.n = d;
    }

    private void a(a aVar) {
        a(aVar.b, aVar.c);
    }

    private void a(RecyclerView.p pVar, int i) {
        if (i < 0) {
            return;
        }
        int G = G();
        if (!this.p) {
            for (int i2 = 0; i2 < G; i2++) {
                View j2 = j(i2);
                if (this.o.b(j2) > i || this.o.c(j2) > i) {
                    a(pVar, 0, i2);
                    return;
                }
            }
            return;
        }
        int i3 = G - 1;
        for (int i4 = i3; i4 >= 0; i4--) {
            View j3 = j(i4);
            if (this.o.b(j3) > i || this.o.c(j3) > i) {
                a(pVar, i3, i4);
                return;
            }
        }
    }

    private void a(RecyclerView.p pVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                b(i, pVar);
                i--;
            }
            return;
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            b(i3, pVar);
        }
    }

    private void a(RecyclerView.p pVar, c cVar) {
        if (!cVar.h || cVar.s) {
            return;
        }
        if (cVar.m == -1) {
            b(pVar, cVar.n);
        } else {
            a(pVar, cVar.n);
        }
    }

    private void a(RecyclerView.p pVar, RecyclerView.u uVar, a aVar) {
        if (a(uVar, aVar) || b(pVar, uVar, aVar)) {
            return;
        }
        aVar.b();
        aVar.b = this.f ? uVar.i() - 1 : 0;
    }

    private boolean a(RecyclerView.u uVar, a aVar) {
        int i;
        if (!uVar.c() && (i = this.q) != -1) {
            if (i >= 0 && i < uVar.i()) {
                aVar.b = this.q;
                SavedState savedState = this.s;
                if (savedState != null && savedState.a()) {
                    aVar.d = this.s.c;
                    aVar.c = aVar.d ? this.o.e() - this.s.b : this.o.d() + this.s.b;
                    return true;
                } else if (this.r != Integer.MIN_VALUE) {
                    boolean z = this.p;
                    aVar.d = z;
                    aVar.c = z ? this.o.e() - this.r : this.o.d() + this.r;
                    return true;
                } else {
                    View c2 = c(this.q);
                    if (c2 == null) {
                        if (G() > 0) {
                            aVar.d = (this.q < e(j(0))) == this.p;
                        }
                        aVar.b();
                    } else if (this.o.e(c2) > this.o.g()) {
                        aVar.b();
                        return true;
                    } else if (this.o.a(c2) - this.o.d() < 0) {
                        aVar.c = this.o.d();
                        aVar.d = false;
                        return true;
                    } else if (this.o.e() - this.o.b(c2) < 0) {
                        aVar.c = this.o.e();
                        aVar.d = true;
                        return true;
                    } else {
                        aVar.c = aVar.d ? this.o.b(c2) + this.o.c() : this.o.a(c2);
                    }
                    return true;
                }
            }
            this.q = -1;
            this.r = Integer.MIN_VALUE;
        }
        return false;
    }

    private View ab() {
        return j(this.p ? 0 : G() - 1);
    }

    private void ac() {
        Log.d(a, "internal representation of views on the screen");
        for (int i = 0; i < G(); i++) {
            View j2 = j(i);
            Log.d(a, "item " + e(j2) + ", coord:" + this.o.a(j2));
        }
        Log.d(a, "==============");
    }

    private int b(int i, RecyclerView.p pVar, RecyclerView.u uVar, boolean z) {
        int d;
        int d2 = i - this.o.d();
        if (d2 > 0) {
            int i2 = -c(d2, pVar, uVar);
            int i3 = i + i2;
            if (!z || (d = i3 - this.o.d()) <= 0) {
                return i2;
            }
            this.o.a(-d);
            return i2 - d;
        }
        return 0;
    }

    private View b(boolean z, boolean z2) {
        int G;
        int i;
        if (this.p) {
            G = 0;
            i = G();
        } else {
            G = G() - 1;
            i = -1;
        }
        return a(G, i, z, z2);
    }

    private void b() {
        this.p = (this.n == 1 || !m()) ? this.e : !this.e;
    }

    private void b(a aVar) {
        h(aVar.b, aVar.c);
    }

    private void b(RecyclerView.p pVar, int i) {
        int G = G();
        if (i < 0) {
            return;
        }
        int f = this.o.f() - i;
        if (this.p) {
            for (int i2 = 0; i2 < G; i2++) {
                View j2 = j(i2);
                if (this.o.a(j2) < f || this.o.d(j2) < f) {
                    a(pVar, 0, i2);
                    return;
                }
            }
            return;
        }
        int i3 = G - 1;
        for (int i4 = i3; i4 >= 0; i4--) {
            View j3 = j(i4);
            if (this.o.a(j3) < f || this.o.d(j3) < f) {
                a(pVar, i3, i4);
                return;
            }
        }
    }

    private void b(RecyclerView.p pVar, RecyclerView.u uVar, int i, int i2) {
        if (!uVar.d() || G() == 0 || uVar.c() || !d()) {
            return;
        }
        List<RecyclerView.x> c2 = pVar.c();
        int size = c2.size();
        int e = e(j(0));
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView.x xVar = c2.get(i5);
            if (!xVar.s()) {
                if (((xVar.e() < e) != this.p ? (char) 65535 : (char) 1) == 65535) {
                    i3 += this.o.e(xVar.a);
                } else {
                    i4 += this.o.e(xVar.a);
                }
            }
        }
        this.c.r = c2;
        if (i3 > 0) {
            h(e(c()), i);
            c cVar = this.c;
            cVar.o = i3;
            cVar.j = 0;
            cVar.a();
            a(pVar, this.c, uVar, false);
        }
        if (i4 > 0) {
            a(e(ab()), i2);
            c cVar2 = this.c;
            cVar2.o = i4;
            cVar2.j = 0;
            cVar2.a();
            a(pVar, this.c, uVar, false);
        }
        this.c.r = null;
    }

    private boolean b(RecyclerView.p pVar, RecyclerView.u uVar, a aVar) {
        boolean z = false;
        if (G() == 0) {
            return false;
        }
        View T = T();
        if (T != null && aVar.a(T, uVar)) {
            aVar.a(T, e(T));
            return true;
        } else if (this.d != this.f) {
            return false;
        } else {
            View f = aVar.d ? f(pVar, uVar) : g(pVar, uVar);
            if (f != null) {
                aVar.b(f, e(f));
                if (!uVar.c() && d()) {
                    if ((this.o.a(f) >= this.o.e() || this.o.b(f) < this.o.d()) ? true : true) {
                        aVar.c = aVar.d ? this.o.e() : this.o.d();
                    }
                }
                return true;
            }
            return false;
        }
    }

    private View c() {
        return j(this.p ? G() - 1 : 0);
    }

    private View f(RecyclerView.p pVar, RecyclerView.u uVar) {
        return this.p ? h(pVar, uVar) : i(pVar, uVar);
    }

    private View g(RecyclerView.p pVar, RecyclerView.u uVar) {
        return this.p ? i(pVar, uVar) : h(pVar, uVar);
    }

    private View h(RecyclerView.p pVar, RecyclerView.u uVar) {
        return a(pVar, uVar, 0, G(), uVar.i());
    }

    private void h(int i, int i2) {
        this.c.j = i2 - this.o.d();
        c cVar = this.c;
        cVar.k = i;
        cVar.l = this.p ? 1 : -1;
        c cVar2 = this.c;
        cVar2.m = -1;
        cVar2.i = i2;
        cVar2.n = Integer.MIN_VALUE;
    }

    private int i(RecyclerView.u uVar) {
        if (G() == 0) {
            return 0;
        }
        n();
        return ak.a(uVar, this.o, a(!this.g, true), b(!this.g, true), this, this.g, this.p);
    }

    private View i(RecyclerView.p pVar, RecyclerView.u uVar) {
        return a(pVar, uVar, G() - 1, -1, uVar.i());
    }

    private int j(RecyclerView.u uVar) {
        if (G() == 0) {
            return 0;
        }
        n();
        return ak.a(uVar, this.o, a(!this.g, true), b(!this.g, true), this, this.g);
    }

    private View j(RecyclerView.p pVar, RecyclerView.u uVar) {
        return this.p ? l(pVar, uVar) : m(pVar, uVar);
    }

    private int k(RecyclerView.u uVar) {
        if (G() == 0) {
            return 0;
        }
        n();
        return ak.b(uVar, this.o, a(!this.g, true), b(!this.g, true), this, this.g);
    }

    private View k(RecyclerView.p pVar, RecyclerView.u uVar) {
        return this.p ? m(pVar, uVar) : l(pVar, uVar);
    }

    private View l(RecyclerView.p pVar, RecyclerView.u uVar) {
        return c(0, G());
    }

    private View m(RecyclerView.p pVar, RecyclerView.u uVar) {
        return c(G() - 1, -1);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int a(int i, RecyclerView.p pVar, RecyclerView.u uVar) {
        if (this.n == 1) {
            return 0;
        }
        return c(i, pVar, uVar);
    }

    int a(RecyclerView.p pVar, c cVar, RecyclerView.u uVar, boolean z) {
        int i = cVar.j;
        if (cVar.n != Integer.MIN_VALUE) {
            if (cVar.j < 0) {
                cVar.n += cVar.j;
            }
            a(pVar, cVar);
        }
        int i2 = cVar.j + cVar.o;
        b bVar = this.i;
        while (true) {
            if ((!cVar.s && i2 <= 0) || !cVar.a(uVar)) {
                break;
            }
            bVar.a();
            a(pVar, uVar, cVar, bVar);
            if (!bVar.b) {
                cVar.i += bVar.a * cVar.m;
                if (!bVar.c || this.c.r != null || !uVar.c()) {
                    cVar.j -= bVar.a;
                    i2 -= bVar.a;
                }
                if (cVar.n != Integer.MIN_VALUE) {
                    cVar.n += bVar.a;
                    if (cVar.j < 0) {
                        cVar.n += cVar.j;
                    }
                    a(pVar, cVar);
                }
                if (z && bVar.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.j;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public RecyclerView.LayoutParams a() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    View a(int i, int i2, boolean z, boolean z2) {
        n();
        return (this.n == 0 ? this.w : this.x).a(i, i2, z ? 24579 : 320, z2 ? 320 : 0);
    }

    View a(RecyclerView.p pVar, RecyclerView.u uVar, int i, int i2, int i3) {
        n();
        int d = this.o.d();
        int e = this.o.e();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View j2 = j(i);
            int e2 = e(j2);
            if (e2 >= 0 && e2 < i3) {
                if (((RecyclerView.LayoutParams) j2.getLayoutParams()).e()) {
                    if (view2 == null) {
                        view2 = j2;
                    }
                } else if (this.o.a(j2) < e && this.o.b(j2) >= d) {
                    return j2;
                } else {
                    if (view == null) {
                        view = j2;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public View a(View view, int i, RecyclerView.p pVar, RecyclerView.u uVar) {
        int g;
        b();
        if (G() == 0 || (g = g(i)) == Integer.MIN_VALUE) {
            return null;
        }
        n();
        n();
        a(g, (int) (this.o.g() * b), false, uVar);
        c cVar = this.c;
        cVar.n = Integer.MIN_VALUE;
        cVar.h = false;
        a(pVar, cVar, uVar, true);
        View k2 = g == -1 ? k(pVar, uVar) : j(pVar, uVar);
        View c2 = g == -1 ? c() : ab();
        if (c2.hasFocusable()) {
            if (k2 == null) {
                return null;
            }
            return c2;
        }
        return k2;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(int i, int i2, RecyclerView.u uVar, RecyclerView.i.a aVar) {
        if (this.n != 0) {
            i = i2;
        }
        if (G() == 0 || i == 0) {
            return;
        }
        n();
        a(i > 0 ? 1 : -1, Math.abs(i), true, uVar);
        a(uVar, this.c, aVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(int i, RecyclerView.i.a aVar) {
        boolean z;
        int i2;
        SavedState savedState = this.s;
        if (savedState == null || !savedState.a()) {
            b();
            z = this.p;
            i2 = this.q;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            z = this.s.c;
            i2 = this.s.a;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.E && i2 >= 0 && i2 < i; i4++) {
            aVar.b(i2, 0);
            i2 += i3;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.s = (SavedState) parcelable;
            y();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(RecyclerView.p pVar, RecyclerView.u uVar, a aVar, int i) {
    }

    void a(RecyclerView.p pVar, RecyclerView.u uVar, c cVar, b bVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int f;
        View a2 = cVar.a(pVar);
        if (a2 == null) {
            bVar.b = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) a2.getLayoutParams();
        if (cVar.r == null) {
            if (this.p == (cVar.m == -1)) {
                c(a2);
            } else {
                b(a2, 0);
            }
        } else {
            if (this.p == (cVar.m == -1)) {
                b(a2);
            } else {
                a(a2, 0);
            }
        }
        b(a2, 0, 0);
        bVar.a = this.o.e(a2);
        if (this.n == 1) {
            if (m()) {
                f = J() - N();
                i4 = f - this.o.f(a2);
            } else {
                i4 = L();
                f = this.o.f(a2) + i4;
            }
            if (cVar.m == -1) {
                int i5 = cVar.i;
                i2 = cVar.i - bVar.a;
                i = f;
                i3 = i5;
            } else {
                int i6 = cVar.i;
                i3 = cVar.i + bVar.a;
                i = f;
                i2 = i6;
            }
        } else {
            int M = M();
            int f2 = this.o.f(a2) + M;
            if (cVar.m == -1) {
                i2 = M;
                i = cVar.i;
                i3 = f2;
                i4 = cVar.i - bVar.a;
            } else {
                int i7 = cVar.i;
                i = cVar.i + bVar.a;
                i2 = M;
                i3 = f2;
                i4 = i7;
            }
        }
        b(a2, i4, i2, i, i3);
        if (layoutParams.e() || layoutParams.f()) {
            bVar.c = true;
        }
        bVar.d = a2.hasFocusable();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView.u uVar) {
        super.a(uVar);
        this.s = null;
        this.q = -1;
        this.r = Integer.MIN_VALUE;
        this.t.a();
    }

    void a(RecyclerView.u uVar, c cVar, RecyclerView.i.a aVar) {
        int i = cVar.k;
        if (i < 0 || i >= uVar.i()) {
            return;
        }
        aVar.b(i, Math.max(0, cVar.n));
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView, RecyclerView.p pVar) {
        super.a(recyclerView, pVar);
        if (this.h) {
            c(pVar);
            pVar.a();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView, RecyclerView.u uVar, int i) {
        z zVar = new z(recyclerView.getContext());
        zVar.c(i);
        a(zVar);
    }

    @Override // android.support.v7.widget.a.a.e
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(@android.support.annotation.af View view, @android.support.annotation.af View view2, int i, int i2) {
        int a2;
        a("Cannot drop a view during a scroll or layout calculation");
        n();
        b();
        int e = e(view);
        int e2 = e(view2);
        char c2 = e < e2 ? (char) 1 : (char) 65535;
        if (this.p) {
            if (c2 == 1) {
                b(e2, this.o.e() - (this.o.a(view2) + this.o.e(view)));
                return;
            }
            a2 = this.o.e() - this.o.b(view2);
        } else if (c2 != 65535) {
            b(e2, this.o.b(view2) - this.o.e(view));
            return;
        } else {
            a2 = this.o.a(view2);
        }
        b(e2, a2);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(AccessibilityEvent accessibilityEvent) {
        super.a(accessibilityEvent);
        if (G() > 0) {
            accessibilityEvent.setFromIndex(t());
            accessibilityEvent.setToIndex(v());
        }
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(String str) {
        if (this.s == null) {
            super.a(str);
        }
    }

    public void a(boolean z) {
        a((String) null);
        if (this.f == z) {
            return;
        }
        this.f = z;
        y();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int b(int i, RecyclerView.p pVar, RecyclerView.u uVar) {
        if (this.n == 0) {
            return 0;
        }
        return c(i, pVar, uVar);
    }

    protected int b(RecyclerView.u uVar) {
        if (uVar.g()) {
            return this.o.g();
        }
        return 0;
    }

    public void b(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        a((String) null);
        if (i != this.n || this.o == null) {
            this.o = ae.a(this, i);
            this.t.a = this.o;
            this.n = i;
            y();
        }
    }

    public void b(int i, int i2) {
        this.q = i;
        this.r = i2;
        SavedState savedState = this.s;
        if (savedState != null) {
            savedState.b();
        }
        y();
    }

    public void b(boolean z) {
        this.h = z;
    }

    int c(int i, RecyclerView.p pVar, RecyclerView.u uVar) {
        if (G() == 0 || i == 0) {
            return 0;
        }
        this.c.h = true;
        n();
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        a(i2, abs, true, uVar);
        int a2 = this.c.n + a(pVar, this.c, uVar, false);
        if (a2 < 0) {
            return 0;
        }
        if (abs > a2) {
            i = i2 * a2;
        }
        this.o.a(-i);
        this.c.q = i;
        return i;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int c(RecyclerView.u uVar) {
        return i(uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public View c(int i) {
        int G = G();
        if (G == 0) {
            return null;
        }
        int e = i - e(j(0));
        if (e >= 0 && e < G) {
            View j2 = j(e);
            if (e(j2) == i) {
                return j2;
            }
        }
        return super.c(i);
    }

    View c(int i, int i2) {
        int i3;
        int i4;
        n();
        if ((i2 > i ? (char) 1 : i2 < i ? (char) 65535 : (char) 0) == 0) {
            return j(i);
        }
        if (this.o.a(j(i)) < this.o.d()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        return (this.n == 0 ? this.w : this.x).a(i, i2, i3, i4);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void c(RecyclerView.p pVar, RecyclerView.u uVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int a2;
        int i6;
        View c2;
        int a3;
        int i7;
        int i8 = -1;
        if (!(this.s == null && this.q == -1) && uVar.i() == 0) {
            c(pVar);
            return;
        }
        SavedState savedState = this.s;
        if (savedState != null && savedState.a()) {
            this.q = this.s.a;
        }
        n();
        this.c.h = false;
        b();
        View T = T();
        if (!this.t.e || this.q != -1 || this.s != null) {
            this.t.a();
            a aVar = this.t;
            aVar.d = this.p ^ this.f;
            a(pVar, uVar, aVar);
            this.t.e = true;
        } else if (T != null && (this.o.a(T) >= this.o.e() || this.o.b(T) <= this.o.d())) {
            this.t.a(T, e(T));
        }
        int b2 = b(uVar);
        if (this.c.q >= 0) {
            i = b2;
            b2 = 0;
        } else {
            i = 0;
        }
        int d = b2 + this.o.d();
        int h = i + this.o.h();
        if (uVar.c() && (i6 = this.q) != -1 && this.r != Integer.MIN_VALUE && (c2 = c(i6)) != null) {
            if (this.p) {
                i7 = this.o.e() - this.o.b(c2);
                a3 = this.r;
            } else {
                a3 = this.o.a(c2) - this.o.d();
                i7 = this.r;
            }
            int i9 = i7 - a3;
            if (i9 > 0) {
                d += i9;
            } else {
                h -= i9;
            }
        }
        if (!this.t.d ? !this.p : this.p) {
            i8 = 1;
        }
        a(pVar, uVar, this.t, i8);
        a(pVar);
        this.c.s = q();
        this.c.p = uVar.c();
        if (this.t.d) {
            b(this.t);
            c cVar = this.c;
            cVar.o = d;
            a(pVar, cVar, uVar, false);
            i3 = this.c.i;
            int i10 = this.c.k;
            if (this.c.j > 0) {
                h += this.c.j;
            }
            a(this.t);
            c cVar2 = this.c;
            cVar2.o = h;
            cVar2.k += this.c.l;
            a(pVar, this.c, uVar, false);
            i2 = this.c.i;
            if (this.c.j > 0) {
                int i11 = this.c.j;
                h(i10, i3);
                c cVar3 = this.c;
                cVar3.o = i11;
                a(pVar, cVar3, uVar, false);
                i3 = this.c.i;
            }
        } else {
            a(this.t);
            c cVar4 = this.c;
            cVar4.o = h;
            a(pVar, cVar4, uVar, false);
            i2 = this.c.i;
            int i12 = this.c.k;
            if (this.c.j > 0) {
                d += this.c.j;
            }
            b(this.t);
            c cVar5 = this.c;
            cVar5.o = d;
            cVar5.k += this.c.l;
            a(pVar, this.c, uVar, false);
            i3 = this.c.i;
            if (this.c.j > 0) {
                int i13 = this.c.j;
                a(i12, i2);
                c cVar6 = this.c;
                cVar6.o = i13;
                a(pVar, cVar6, uVar, false);
                i2 = this.c.i;
            }
        }
        if (G() > 0) {
            if (this.p ^ this.f) {
                int a4 = a(i2, pVar, uVar, true);
                i4 = i3 + a4;
                i5 = i2 + a4;
                a2 = b(i4, pVar, uVar, false);
            } else {
                int b3 = b(i3, pVar, uVar, true);
                i4 = i3 + b3;
                i5 = i2 + b3;
                a2 = a(i5, pVar, uVar, false);
            }
            i3 = i4 + a2;
            i2 = i5 + a2;
        }
        b(pVar, uVar, i3, i2);
        if (uVar.c()) {
            this.t.a();
        } else {
            this.o.b();
        }
        this.d = this.f;
    }

    public void c(boolean z) {
        a((String) null);
        if (z == this.e) {
            return;
        }
        this.e = z;
        y();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int d(RecyclerView.u uVar) {
        return i(uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.t.b
    public PointF d(int i) {
        if (G() == 0) {
            return null;
        }
        int i2 = (i < e(j(0))) != this.p ? -1 : 1;
        return this.n == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
    }

    public void d(boolean z) {
        this.g = z;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public boolean d() {
        return this.s == null && this.d == this.f;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int e(RecyclerView.u uVar) {
        return j(uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void e(int i) {
        this.q = i;
        this.r = Integer.MIN_VALUE;
        SavedState savedState = this.s;
        if (savedState != null) {
            savedState.b();
        }
        y();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public boolean e() {
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int f(RecyclerView.u uVar) {
        return j(uVar);
    }

    public void f(int i) {
        this.E = i;
    }

    public boolean f() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.n == 1) ? 1 : Integer.MIN_VALUE : this.n == 0 ? 1 : Integer.MIN_VALUE : this.n == 1 ? -1 : Integer.MIN_VALUE : this.n == 0 ? -1 : Integer.MIN_VALUE : (this.n != 1 && m()) ? -1 : 1 : (this.n != 1 && m()) ? 1 : -1;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int g(RecyclerView.u uVar) {
        return k(uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public Parcelable g() {
        SavedState savedState = this.s;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        if (G() > 0) {
            n();
            boolean z = this.d ^ this.p;
            savedState2.c = z;
            if (z) {
                View ab = ab();
                savedState2.b = this.o.e() - this.o.b(ab);
                savedState2.a = e(ab);
            } else {
                View c2 = c();
                savedState2.a = e(c2);
                savedState2.b = this.o.a(c2) - this.o.d();
            }
        } else {
            savedState2.b();
        }
        return savedState2;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int h(RecyclerView.u uVar) {
        return k(uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public boolean h() {
        return this.n == 0;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public boolean i() {
        return this.n == 1;
    }

    public boolean j() {
        return this.f;
    }

    public int k() {
        return this.n;
    }

    public boolean l() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean m() {
        return D() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n() {
        if (this.c == null) {
            this.c = o();
        }
    }

    c o() {
        return new c();
    }

    public boolean p() {
        return this.g;
    }

    boolean q() {
        return this.o.i() == 0 && this.o.f() == 0;
    }

    public int r() {
        return this.E;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    boolean s() {
        return (I() == 1073741824 || H() == 1073741824 || !aa()) ? false : true;
    }

    public int t() {
        View a2 = a(0, G(), false, true);
        if (a2 == null) {
            return -1;
        }
        return e(a2);
    }

    public int u() {
        View a2 = a(0, G(), true, false);
        if (a2 == null) {
            return -1;
        }
        return e(a2);
    }

    public int v() {
        View a2 = a(G() - 1, -1, false, true);
        if (a2 == null) {
            return -1;
        }
        return e(a2);
    }

    public int w() {
        View a2 = a(G() - 1, -1, true, false);
        if (a2 == null) {
            return -1;
        }
        return e(a2);
    }

    void x() {
        Log.d(a, "validating child count " + G());
        if (G() < 1) {
            return;
        }
        int e = e(j(0));
        int a2 = this.o.a(j(0));
        if (this.p) {
            for (int i = 1; i < G(); i++) {
                View j2 = j(i);
                int e2 = e(j2);
                int a3 = this.o.a(j2);
                if (e2 < e) {
                    ac();
                    StringBuilder sb = new StringBuilder();
                    sb.append("detected invalid position. loc invalid? ");
                    sb.append(a3 < a2);
                    throw new RuntimeException(sb.toString());
                } else if (a3 > a2) {
                    ac();
                    throw new RuntimeException("detected invalid location");
                }
            }
            return;
        }
        for (int i2 = 1; i2 < G(); i2++) {
            View j3 = j(i2);
            int e3 = e(j3);
            int a4 = this.o.a(j3);
            if (e3 < e) {
                ac();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("detected invalid position. loc invalid? ");
                sb2.append(a4 < a2);
                throw new RuntimeException(sb2.toString());
            } else if (a4 < a2) {
                ac();
                throw new RuntimeException("detected invalid location");
            }
        }
    }
}
