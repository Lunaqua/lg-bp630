package android.support.v7.f;

import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.au;
import android.support.annotation.aw;
import android.support.v7.f.h;
import android.support.v7.f.i;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b<T> {
    static final String a = "AsyncListUtil";
    static final boolean b = false;
    final Class<T> c;
    final int d;
    final a<T> e;
    final AbstractC0045b f;
    final i<T> g;
    final h.b<T> h;
    final h.a<T> i;
    boolean m;
    final int[] j = new int[2];
    final int[] k = new int[2];
    final int[] l = new int[2];
    private int r = 0;
    int n = 0;
    int o = 0;
    int p = this.o;
    final SparseIntArray q = new SparseIntArray();
    private final h.b<T> s = new h.b<T>() { // from class: android.support.v7.f.b.1
        private void a() {
            for (int i = 0; i < b.this.g.a(); i++) {
                b.this.i.a(b.this.g.b(i));
            }
            b.this.g.b();
        }

        private boolean a(int i) {
            return i == b.this.p;
        }

        @Override // android.support.v7.f.h.b
        public void a(int i, int i2) {
            if (a(i)) {
                b bVar = b.this;
                bVar.n = i2;
                bVar.f.a();
                b bVar2 = b.this;
                bVar2.o = bVar2.p;
                a();
                b bVar3 = b.this;
                bVar3.m = false;
                bVar3.d();
            }
        }

        @Override // android.support.v7.f.h.b
        public void a(int i, i.a<T> aVar) {
            if (!a(i)) {
                b.this.i.a(aVar);
                return;
            }
            i.a<T> a2 = b.this.g.a(aVar);
            if (a2 != null) {
                Log.e(b.a, "duplicate tile @" + a2.b);
                b.this.i.a(a2);
            }
            int i2 = aVar.b + aVar.c;
            int i3 = 0;
            while (i3 < b.this.q.size()) {
                int keyAt = b.this.q.keyAt(i3);
                if (aVar.b > keyAt || keyAt >= i2) {
                    i3++;
                } else {
                    b.this.q.removeAt(i3);
                    b.this.f.a(keyAt);
                }
            }
        }

        @Override // android.support.v7.f.h.b
        public void b(int i, int i2) {
            if (a(i)) {
                i.a<T> c = b.this.g.c(i2);
                if (c != null) {
                    b.this.i.a(c);
                    return;
                }
                Log.e(b.a, "tile not found @" + i2);
            }
        }
    };
    private final h.a<T> t = new h.a<T>() { // from class: android.support.v7.f.b.2
        final SparseBooleanArray a = new SparseBooleanArray();
        private i.a<T> c;
        private int d;
        private int e;
        private int f;
        private int g;

        private i.a<T> a() {
            i.a<T> aVar = this.c;
            if (aVar != null) {
                this.c = aVar.d;
                return aVar;
            }
            return new i.a<>(b.this.c, b.this.d);
        }

        private void a(int i, int i2, int i3, boolean z) {
            int i4 = i;
            while (i4 <= i2) {
                b.this.i.a(z ? (i2 + i) - i4 : i4, i3);
                i4 += b.this.d;
            }
        }

        private void a(String str, Object... objArr) {
            Log.d(b.a, "[BKGR] " + String.format(str, objArr));
        }

        private int b(int i) {
            return i - (i % b.this.d);
        }

        private void b(i.a<T> aVar) {
            this.a.put(aVar.b, true);
            b.this.h.a(this.d, aVar);
        }

        private boolean c(int i) {
            return this.a.get(i);
        }

        private void d(int i) {
            this.a.delete(i);
            b.this.h.b(this.d, i);
        }

        private void e(int i) {
            int b2 = b.this.e.b();
            while (this.a.size() >= b2) {
                int keyAt = this.a.keyAt(0);
                SparseBooleanArray sparseBooleanArray = this.a;
                int keyAt2 = sparseBooleanArray.keyAt(sparseBooleanArray.size() - 1);
                int i2 = this.f - keyAt;
                int i3 = keyAt2 - this.g;
                if (i2 > 0 && (i2 >= i3 || i == 2)) {
                    d(keyAt);
                } else if (i3 <= 0) {
                    return;
                } else {
                    if (i2 >= i3 && i != 1) {
                        return;
                    }
                    d(keyAt2);
                }
            }
        }

        @Override // android.support.v7.f.h.a
        public void a(int i) {
            this.d = i;
            this.a.clear();
            this.e = b.this.e.a();
            b.this.h.a(this.d, this.e);
        }

        @Override // android.support.v7.f.h.a
        public void a(int i, int i2) {
            if (c(i)) {
                return;
            }
            i.a<T> a2 = a();
            a2.b = i;
            a2.c = Math.min(b.this.d, this.e - a2.b);
            b.this.e.a(a2.a, a2.b, a2.c);
            e(i2);
            b(a2);
        }

        @Override // android.support.v7.f.h.a
        public void a(int i, int i2, int i3, int i4, int i5) {
            if (i > i2) {
                return;
            }
            int b2 = b(i);
            int b3 = b(i2);
            this.f = b(i3);
            this.g = b(i4);
            if (i5 == 1) {
                a(this.f, b3, i5, true);
                a(b3 + b.this.d, this.g, i5, false);
                return;
            }
            a(b2, this.g, i5, false);
            a(this.f, b2 - b.this.d, i5, true);
        }

        @Override // android.support.v7.f.h.a
        public void a(i.a<T> aVar) {
            b.this.e.a(aVar.a, aVar.c);
            aVar.d = this.c;
            this.c = aVar;
        }
    };

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class a<T> {
        @aw
        public abstract int a();

        @aw
        public void a(@af T[] tArr, int i) {
        }

        @aw
        public abstract void a(@af T[] tArr, int i, int i2);

        @aw
        public int b() {
            return 10;
        }
    }

    /* renamed from: android.support.v7.f.b$b  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class AbstractC0045b {
        public static final int a = 0;
        public static final int b = 1;
        public static final int c = 2;

        @au
        public abstract void a();

        @au
        public abstract void a(int i);

        @au
        public abstract void a(@af int[] iArr);

        @au
        public void a(@af int[] iArr, @af int[] iArr2, int i) {
            int i2 = (iArr[1] - iArr[0]) + 1;
            int i3 = i2 / 2;
            iArr2[0] = iArr[0] - (i == 1 ? i2 : i3);
            int i4 = iArr[1];
            if (i != 2) {
                i2 = i3;
            }
            iArr2[1] = i4 + i2;
        }
    }

    public b(@af Class<T> cls, int i, @af a<T> aVar, @af AbstractC0045b abstractC0045b) {
        this.c = cls;
        this.d = i;
        this.e = aVar;
        this.f = abstractC0045b;
        this.g = new i<>(this.d);
        f fVar = new f();
        this.h = fVar.a(this.s);
        this.i = fVar.a(this.t);
        b();
    }

    private boolean e() {
        return this.p != this.o;
    }

    @ag
    public T a(int i) {
        if (i < 0 || i >= this.n) {
            throw new IndexOutOfBoundsException(i + " is not within 0 and " + this.n);
        }
        T a2 = this.g.a(i);
        if (a2 == null && !e()) {
            this.q.put(i, 0);
        }
        return a2;
    }

    public void a() {
        if (e()) {
            return;
        }
        d();
        this.m = true;
    }

    void a(String str, Object... objArr) {
        Log.d(a, "[MAIN] " + String.format(str, objArr));
    }

    public void b() {
        this.q.clear();
        h.a<T> aVar = this.i;
        int i = this.p + 1;
        this.p = i;
        aVar.a(i);
    }

    public int c() {
        return this.n;
    }

    void d() {
        this.f.a(this.j);
        int[] iArr = this.j;
        if (iArr[0] > iArr[1] || iArr[0] < 0 || iArr[1] >= this.n) {
            return;
        }
        if (this.m) {
            int i = iArr[0];
            int[] iArr2 = this.k;
            if (i <= iArr2[1] && iArr2[0] <= iArr[1]) {
                if (iArr[0] < iArr2[0]) {
                    this.r = 1;
                } else if (iArr[0] > iArr2[0]) {
                    this.r = 2;
                }
                int[] iArr3 = this.k;
                int[] iArr4 = this.j;
                iArr3[0] = iArr4[0];
                iArr3[1] = iArr4[1];
                this.f.a(iArr4, this.l, this.r);
                int[] iArr5 = this.l;
                iArr5[0] = Math.min(this.j[0], Math.max(iArr5[0], 0));
                int[] iArr6 = this.l;
                iArr6[1] = Math.max(this.j[1], Math.min(iArr6[1], this.n - 1));
                h.a<T> aVar = this.i;
                int[] iArr7 = this.j;
                int i2 = iArr7[0];
                int i3 = iArr7[1];
                int[] iArr8 = this.l;
                aVar.a(i2, i3, iArr8[0], iArr8[1], this.r);
            }
        }
        this.r = 0;
        int[] iArr32 = this.k;
        int[] iArr42 = this.j;
        iArr32[0] = iArr42[0];
        iArr32[1] = iArr42[1];
        this.f.a(iArr42, this.l, this.r);
        int[] iArr52 = this.l;
        iArr52[0] = Math.min(this.j[0], Math.max(iArr52[0], 0));
        int[] iArr62 = this.l;
        iArr62[1] = Math.max(this.j[1], Math.min(iArr62[1], this.n - 1));
        h.a<T> aVar2 = this.i;
        int[] iArr72 = this.j;
        int i22 = iArr72[0];
        int i32 = iArr72[1];
        int[] iArr82 = this.l;
        aVar2.a(i22, i32, iArr82[0], iArr82[1], this.r);
    }
}
