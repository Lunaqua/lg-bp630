package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.a.c;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    private static final boolean E = false;
    private static final String F = "GridLayoutManager";
    public static final int a = -1;
    boolean b;
    int c;
    int[] d;
    View[] e;
    final SparseIntArray f;
    final SparseIntArray g;
    b h;
    final Rect i;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int a = -1;
        int b;
        int c;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.b = -1;
            this.c = 0;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = -1;
            this.c = 0;
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = -1;
            this.c = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = -1;
            this.c = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.b = -1;
            this.c = 0;
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.c;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class a extends b {
        @Override // android.support.v7.widget.GridLayoutManager.b
        public int a(int i) {
            return 1;
        }

        @Override // android.support.v7.widget.GridLayoutManager.b
        public int a(int i, int i2) {
            return i % i2;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class b {
        final SparseIntArray a = new SparseIntArray();
        private boolean b = false;

        public abstract int a(int i);

        /* JADX WARN: Removed duplicated region for block: B:14:0x002a  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0039  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0031 -> B:19:0x0036). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0033 -> B:19:0x0036). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0035 -> B:19:0x0036). Please submit an issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int a(int r6, int r7) {
            /*
                r5 = this;
                int r0 = r5.a(r6)
                r1 = 0
                if (r0 != r7) goto L8
                return r1
            L8:
                boolean r2 = r5.b
                if (r2 == 0) goto L26
                android.util.SparseIntArray r2 = r5.a
                int r2 = r2.size()
                if (r2 <= 0) goto L26
                int r2 = r5.b(r6)
                if (r2 < 0) goto L26
                android.util.SparseIntArray r3 = r5.a
                int r3 = r3.get(r2)
                int r4 = r5.a(r2)
                int r3 = r3 + r4
                goto L36
            L26:
                r2 = 0
                r3 = 0
            L28:
                if (r2 >= r6) goto L39
                int r4 = r5.a(r2)
                int r3 = r3 + r4
                if (r3 != r7) goto L33
                r3 = 0
                goto L36
            L33:
                if (r3 <= r7) goto L36
                r3 = r4
            L36:
                int r2 = r2 + 1
                goto L28
            L39:
                int r0 = r0 + r3
                if (r0 > r7) goto L3d
                return r3
            L3d:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.b.a(int, int):int");
        }

        public void a() {
            this.a.clear();
        }

        public void a(boolean z) {
            this.b = z;
        }

        int b(int i) {
            int size = this.a.size() - 1;
            int i2 = 0;
            while (i2 <= size) {
                int i3 = (i2 + size) >>> 1;
                if (this.a.keyAt(i3) < i) {
                    i2 = i3 + 1;
                } else {
                    size = i3 - 1;
                }
            }
            int i4 = i2 - 1;
            if (i4 < 0 || i4 >= this.a.size()) {
                return -1;
            }
            return this.a.keyAt(i4);
        }

        int b(int i, int i2) {
            if (this.b) {
                int i3 = this.a.get(i, -1);
                if (i3 != -1) {
                    return i3;
                }
                int a = a(i, i2);
                this.a.put(i, a);
                return a;
            }
            return a(i, i2);
        }

        public boolean b() {
            return this.b;
        }

        public int c(int i, int i2) {
            int a = a(i);
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int a2 = a(i5);
                i3 += a2;
                if (i3 == i2) {
                    i4++;
                    i3 = 0;
                } else if (i3 > i2) {
                    i4++;
                    i3 = a2;
                }
            }
            return i3 + a > i2 ? i4 + 1 : i4;
        }
    }

    public GridLayoutManager(Context context, int i) {
        super(context);
        this.b = false;
        this.c = -1;
        this.f = new SparseIntArray();
        this.g = new SparseIntArray();
        this.h = new a();
        this.i = new Rect();
        a(i);
    }

    public GridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i2, z);
        this.b = false;
        this.c = -1;
        this.f = new SparseIntArray();
        this.g = new SparseIntArray();
        this.h = new a();
        this.i = new Rect();
        a(i);
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.b = false;
        this.c = -1;
        this.f = new SparseIntArray();
        this.g = new SparseIntArray();
        this.h = new a();
        this.i = new Rect();
        a(a(context, attributeSet, i, i2).b);
    }

    private int a(RecyclerView.p pVar, RecyclerView.u uVar, int i) {
        if (uVar.c()) {
            int b2 = pVar.b(i);
            if (b2 == -1) {
                Log.w(F, "Cannot find span size for pre layout position. " + i);
                return 0;
            }
            return this.h.c(b2, this.c);
        }
        return this.h.c(i, this.c);
    }

    private void a(float f, int i) {
        n(Math.max(Math.round(f * this.c), i));
    }

    private void a(RecyclerView.p pVar, RecyclerView.u uVar, int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5 = 0;
        int i6 = -1;
        if (z) {
            i6 = i;
            i3 = 0;
            i4 = 1;
        } else {
            i3 = i - 1;
            i4 = -1;
        }
        while (i3 != i6) {
            View view = this.e[i3];
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.c = c(pVar, uVar, e(view));
            layoutParams.b = i5;
            i5 += layoutParams.c;
            i3 += i4;
        }
    }

    private void a(View view, int i, int i2, boolean z) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (z ? a(view, i, i2, layoutParams) : b(view, i, i2, layoutParams)) {
            view.measure(i, i2);
        }
    }

    private void a(View view, int i, boolean z) {
        int i2;
        int i3;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.e;
        int i4 = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
        int i5 = rect.left + rect.right + layoutParams.leftMargin + layoutParams.rightMargin;
        int a2 = a(layoutParams.b, layoutParams.c);
        if (this.n == 1) {
            i3 = a(a2, i, i5, layoutParams.width, false);
            i2 = a(this.o.g(), I(), i4, layoutParams.height, true);
        } else {
            int a3 = a(a2, i, i4, layoutParams.height, false);
            int a4 = a(this.o.g(), H(), i5, layoutParams.width, true);
            i2 = a3;
            i3 = a4;
        }
        a(view, i3, i2, z);
    }

    static int[] a(int[] iArr, int i, int i2) {
        int i3;
        if (iArr == null || iArr.length != i + 1 || iArr[iArr.length - 1] != i2) {
            iArr = new int[i + 1];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i2 / i;
        int i6 = i2 % i;
        int i7 = 0;
        for (int i8 = 1; i8 <= i; i8++) {
            i4 += i6;
            if (i4 <= 0 || i - i4 >= i6) {
                i3 = i5;
            } else {
                i3 = i5 + 1;
                i4 -= i;
            }
            i7 += i3;
            iArr[i8] = i7;
        }
        return iArr;
    }

    private void ab() {
        this.f.clear();
        this.g.clear();
    }

    private void ac() {
        int G = G();
        for (int i = 0; i < G; i++) {
            LayoutParams layoutParams = (LayoutParams) j(i).getLayoutParams();
            int h = layoutParams.h();
            this.f.put(h, layoutParams.b());
            this.g.put(h, layoutParams.a());
        }
    }

    private void ad() {
        int K;
        int M;
        if (k() == 1) {
            K = J() - N();
            M = L();
        } else {
            K = K() - O();
            M = M();
        }
        n(K - M);
    }

    private void ae() {
        View[] viewArr = this.e;
        if (viewArr == null || viewArr.length != this.c) {
            this.e = new View[this.c];
        }
    }

    private int b(RecyclerView.p pVar, RecyclerView.u uVar, int i) {
        if (uVar.c()) {
            int i2 = this.g.get(i, -1);
            if (i2 != -1) {
                return i2;
            }
            int b2 = pVar.b(i);
            if (b2 == -1) {
                Log.w(F, "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
                return 0;
            }
            return this.h.b(b2, this.c);
        }
        return this.h.b(i, this.c);
    }

    private void b(RecyclerView.p pVar, RecyclerView.u uVar, LinearLayoutManager.a aVar, int i) {
        boolean z = i == 1;
        int b2 = b(pVar, uVar, aVar.b);
        if (z) {
            while (b2 > 0 && aVar.b > 0) {
                aVar.b--;
                b2 = b(pVar, uVar, aVar.b);
            }
            return;
        }
        int i2 = uVar.i() - 1;
        int i3 = aVar.b;
        while (i3 < i2) {
            int i4 = i3 + 1;
            int b3 = b(pVar, uVar, i4);
            if (b3 <= b2) {
                break;
            }
            i3 = i4;
            b2 = b3;
        }
        aVar.b = i3;
    }

    private int c(RecyclerView.p pVar, RecyclerView.u uVar, int i) {
        if (uVar.c()) {
            int i2 = this.f.get(i, -1);
            if (i2 != -1) {
                return i2;
            }
            int b2 = pVar.b(i);
            if (b2 == -1) {
                Log.w(F, "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
                return 1;
            }
            return this.h.a(b2);
        }
        return this.h.a(i);
    }

    private void n(int i) {
        this.d = a(this.d, this.c, i);
    }

    int a(int i, int i2) {
        if (this.n != 1 || !m()) {
            int[] iArr = this.d;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.d;
        int i3 = this.c;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.i
    public int a(int i, RecyclerView.p pVar, RecyclerView.u uVar) {
        ad();
        ae();
        return super.a(i, pVar, uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int a(RecyclerView.p pVar, RecyclerView.u uVar) {
        if (this.n == 0) {
            return this.c;
        }
        if (uVar.i() < 1) {
            return 0;
        }
        return a(pVar, uVar, uVar.i() - 1) + 1;
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.i
    public RecyclerView.LayoutParams a() {
        return this.n == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public RecyclerView.LayoutParams a(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public RecyclerView.LayoutParams a(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    View a(RecyclerView.p pVar, RecyclerView.u uVar, int i, int i2, int i3) {
        n();
        int d = this.o.d();
        int e = this.o.e();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View j = j(i);
            int e2 = e(j);
            if (e2 >= 0 && e2 < i3 && b(pVar, uVar, e2) == 0) {
                if (((RecyclerView.LayoutParams) j.getLayoutParams()).e()) {
                    if (view2 == null) {
                        view2 = j;
                    }
                } else if (this.o.a(j) < e && this.o.b(j) >= d) {
                    return j;
                } else {
                    if (view == null) {
                        view = j;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d7, code lost:
        if (r13 == (r2 > r8)) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00f7, code lost:
        if (r13 == (r2 > r11)) goto L51;
     */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0105  */
    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View a(android.view.View r23, int r24, android.support.v7.widget.RecyclerView.p r25, android.support.v7.widget.RecyclerView.u r26) {
        /*
            Method dump skipped, instructions count: 333
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.a(android.view.View, int, android.support.v7.widget.RecyclerView$p, android.support.v7.widget.RecyclerView$u):android.view.View");
    }

    public void a(int i) {
        if (i == this.c) {
            return;
        }
        this.b = true;
        if (i >= 1) {
            this.c = i;
            this.h.a();
            y();
            return;
        }
        throw new IllegalArgumentException("Span count should be at least 1. Provided " + i);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(Rect rect, int i, int i2) {
        int a2;
        int a3;
        if (this.d == null) {
            super.a(rect, i, i2);
        }
        int L = L() + N();
        int M = M() + O();
        if (this.n == 1) {
            a3 = a(i2, rect.height() + M, X());
            int[] iArr = this.d;
            a2 = a(i, iArr[iArr.length - 1] + L, W());
        } else {
            a2 = a(i, rect.width() + L, W());
            int[] iArr2 = this.d;
            a3 = a(i2, iArr2[iArr2.length - 1] + M, X());
        }
        g(a2, a3);
    }

    public void a(b bVar) {
        this.h = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.widget.LinearLayoutManager
    public void a(RecyclerView.p pVar, RecyclerView.u uVar, LinearLayoutManager.a aVar, int i) {
        super.a(pVar, uVar, aVar, i);
        ad();
        if (uVar.i() > 0 && !uVar.c()) {
            b(pVar, uVar, aVar, i);
        }
        ae();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a1, code lost:
        r22.b = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a3, code lost:
        return;
     */
    @Override // android.support.v7.widget.LinearLayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(android.support.v7.widget.RecyclerView.p r19, android.support.v7.widget.RecyclerView.u r20, android.support.v7.widget.LinearLayoutManager.c r21, android.support.v7.widget.LinearLayoutManager.b r22) {
        /*
            Method dump skipped, instructions count: 574
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.a(android.support.v7.widget.RecyclerView$p, android.support.v7.widget.RecyclerView$u, android.support.v7.widget.LinearLayoutManager$c, android.support.v7.widget.LinearLayoutManager$b):void");
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView.p pVar, RecyclerView.u uVar, View view, android.support.v4.view.a.c cVar) {
        int i;
        int a2;
        int b2;
        boolean z;
        boolean z2;
        int i2;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.a(view, cVar);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int a3 = a(pVar, uVar, layoutParams2.h());
        if (this.n == 0) {
            int a4 = layoutParams2.a();
            i = layoutParams2.b();
            b2 = 1;
            z = this.c > 1 && layoutParams2.b() == this.c;
            z2 = false;
            i2 = a4;
            a2 = a3;
        } else {
            i = 1;
            a2 = layoutParams2.a();
            b2 = layoutParams2.b();
            z = this.c > 1 && layoutParams2.b() == this.c;
            z2 = false;
            i2 = a3;
        }
        cVar.c(c.C0032c.a(i2, i, a2, b2, z, z2));
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView.u uVar) {
        super.a(uVar);
        this.b = false;
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    void a(RecyclerView.u uVar, LinearLayoutManager.c cVar, RecyclerView.i.a aVar) {
        int i = this.c;
        for (int i2 = 0; i2 < this.c && cVar.a(uVar) && i > 0; i2++) {
            int i3 = cVar.k;
            aVar.b(i3, Math.max(0, cVar.n));
            i -= this.h.a(i3);
            cVar.k += cVar.l;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView) {
        this.h.a();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView, int i, int i2) {
        this.h.a();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView, int i, int i2, int i3) {
        this.h.a();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.h.a();
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public void a(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.a(false);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public boolean a(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.i
    public int b(int i, RecyclerView.p pVar, RecyclerView.u uVar) {
        ad();
        ae();
        return super.b(i, pVar, uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int b(RecyclerView.p pVar, RecyclerView.u uVar) {
        if (this.n == 1) {
            return this.c;
        }
        if (uVar.i() < 1) {
            return 0;
        }
        return a(pVar, uVar, uVar.i() - 1) + 1;
    }

    public b b() {
        return this.h;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void b(RecyclerView recyclerView, int i, int i2) {
        this.h.a();
    }

    public int c() {
        return this.c;
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.i
    public void c(RecyclerView.p pVar, RecyclerView.u uVar) {
        if (uVar.c()) {
            ac();
        }
        super.c(pVar, uVar);
        ab();
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.i
    public boolean d() {
        return this.s == null && !this.b;
    }
}
