package android.support.v7.f;

import android.support.annotation.af;
import android.support.annotation.ag;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class g<T> {
    public static final int a = -1;
    private static final int c = 10;
    private static final int d = 10;
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 4;
    T[] b;
    private T[] h;
    private int i;
    private int j;
    private int k;
    private b l;
    private a m;
    private int n;
    private final Class<T> o;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a<T2> extends b<T2> {
        final b<T2> a;
        private final c b;

        public a(b<T2> bVar) {
            this.a = bVar;
            this.b = new c(this.a);
        }

        public void a() {
            this.b.a();
        }

        @Override // android.support.v7.f.e
        public void a(int i, int i2) {
            this.b.a(i, i2);
        }

        @Override // android.support.v7.f.g.b, android.support.v7.f.e
        public void a(int i, int i2, Object obj) {
            this.b.a(i, i2, obj);
        }

        @Override // android.support.v7.f.g.b
        public boolean a(T2 t2, T2 t22) {
            return this.a.a(t2, t22);
        }

        @Override // android.support.v7.f.e
        public void b(int i, int i2) {
            this.b.b(i, i2);
        }

        @Override // android.support.v7.f.g.b
        public boolean b(T2 t2, T2 t22) {
            return this.a.b(t2, t22);
        }

        @Override // android.support.v7.f.g.b
        @ag
        public Object c(T2 t2, T2 t22) {
            return this.a.c(t2, t22);
        }

        @Override // android.support.v7.f.e
        public void c(int i, int i2) {
            this.b.c(i, i2);
        }

        @Override // android.support.v7.f.g.b, java.util.Comparator
        public int compare(T2 t2, T2 t22) {
            return this.a.compare(t2, t22);
        }

        @Override // android.support.v7.f.g.b
        public void d(int i, int i2) {
            this.b.a(i, i2, null);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class b<T2> implements e, Comparator<T2> {
        @Override // android.support.v7.f.e
        public void a(int i, int i2, Object obj) {
            d(i, i2);
        }

        public abstract boolean a(T2 t2, T2 t22);

        public abstract boolean b(T2 t2, T2 t22);

        @ag
        public Object c(T2 t2, T2 t22) {
            return null;
        }

        public abstract int compare(T2 t2, T2 t22);

        public abstract void d(int i, int i2);
    }

    public g(@af Class<T> cls, @af b<T> bVar) {
        this(cls, bVar, 10);
    }

    public g(@af Class<T> cls, @af b<T> bVar, int i) {
        this.o = cls;
        this.b = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i));
        this.l = bVar;
        this.n = 0;
    }

    private int a(T t, int i, int i2, int i3) {
        T t2;
        for (int i4 = i - 1; i4 >= i2; i4--) {
            T t3 = this.b[i4];
            if (this.l.compare(t3, t) != 0) {
                break;
            } else if (this.l.b(t3, t)) {
                return i4;
            }
        }
        do {
            i++;
            if (i >= i3) {
                return -1;
            }
            t2 = this.b[i];
            if (this.l.compare(t2, t) != 0) {
                return -1;
            }
        } while (!this.l.b(t2, t));
        return i;
    }

    private int a(T t, boolean z) {
        int a2 = a(t, this.b, 0, this.n, 1);
        if (a2 == -1) {
            a2 = 0;
        } else if (a2 < this.n) {
            T t2 = this.b[a2];
            if (this.l.b(t2, t)) {
                if (this.l.a(t2, t)) {
                    this.b[a2] = t;
                    return a2;
                }
                this.b[a2] = t;
                b bVar = this.l;
                bVar.a(a2, 1, bVar.c(t2, t));
                return a2;
            }
        }
        b(a2, (int) t);
        if (z) {
            this.l.a(a2, 1);
        }
        return a2;
    }

    private int a(T t, T[] tArr, int i, int i2) {
        while (i < i2) {
            if (this.l.b(tArr[i], t)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private int a(T t, T[] tArr, int i, int i2, int i3) {
        while (i < i2) {
            int i4 = (i + i2) / 2;
            T t2 = tArr[i4];
            int compare = this.l.compare(t2, t);
            if (compare < 0) {
                i = i4 + 1;
            } else if (compare == 0) {
                if (this.l.b(t2, t)) {
                    return i4;
                }
                int a2 = a((g<T>) t, i4, i, i2);
                return (i3 == 1 && a2 == -1) ? i4 : a2;
            } else {
                i2 = i4;
            }
        }
        if (i3 == 1) {
            return i;
        }
        return -1;
    }

    private void a(int i, boolean z) {
        T[] tArr = this.b;
        System.arraycopy(tArr, i + 1, tArr, i, (this.n - i) - 1);
        this.n--;
        this.b[this.n] = null;
        if (z) {
            this.l.b(i, 1);
        }
    }

    private void a(T[] tArr, int i) {
        int i2 = 0;
        boolean z = !(this.l instanceof a);
        if (z) {
            b();
        }
        this.h = this.b;
        this.i = 0;
        int i3 = this.n;
        this.j = i3;
        this.b = (T[]) ((Object[]) Array.newInstance((Class<?>) this.o, i3 + i + 10));
        this.k = 0;
        while (true) {
            if (this.i >= this.j && i2 >= i) {
                break;
            }
            int i4 = this.i;
            int i5 = this.j;
            if (i4 == i5) {
                int i6 = i - i2;
                System.arraycopy(tArr, i2, this.b, this.k, i6);
                this.k += i6;
                this.n += i6;
                this.l.a(this.k - i6, i6);
                break;
            } else if (i2 == i) {
                int i7 = i5 - i4;
                System.arraycopy(this.h, i4, this.b, this.k, i7);
                this.k += i7;
                break;
            } else {
                T t = this.h[i4];
                T t2 = tArr[i2];
                int compare = this.l.compare(t, t2);
                if (compare > 0) {
                    T[] tArr2 = this.b;
                    int i8 = this.k;
                    this.k = i8 + 1;
                    tArr2[i8] = t2;
                    this.n++;
                    i2++;
                    this.l.a(this.k - 1, 1);
                } else if (compare == 0 && this.l.b(t, t2)) {
                    T[] tArr3 = this.b;
                    int i9 = this.k;
                    this.k = i9 + 1;
                    tArr3[i9] = t2;
                    i2++;
                    this.i++;
                    if (!this.l.a(t, t2)) {
                        b bVar = this.l;
                        bVar.a(this.k - 1, 1, bVar.c(t, t2));
                    }
                } else {
                    T[] tArr4 = this.b;
                    int i10 = this.k;
                    this.k = i10 + 1;
                    tArr4[i10] = t;
                    this.i++;
                }
            }
        }
        this.h = null;
        if (z) {
            c();
        }
    }

    private void b(int i, T t) {
        int i2 = this.n;
        if (i > i2) {
            throw new IndexOutOfBoundsException("cannot add item to " + i + " because size is " + this.n);
        }
        T[] tArr = this.b;
        if (i2 == tArr.length) {
            T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.o, tArr.length + 10));
            System.arraycopy(this.b, 0, tArr2, 0, i);
            tArr2[i] = t;
            System.arraycopy(this.b, i, tArr2, i + 1, this.n - i);
            this.b = tArr2;
        } else {
            System.arraycopy(tArr, i, tArr, i + 1, i2 - i);
            this.b[i] = t;
        }
        this.n++;
    }

    private boolean b(T t, boolean z) {
        int a2 = a(t, this.b, 0, this.n, 2);
        if (a2 == -1) {
            return false;
        }
        a(a2, z);
        return true;
    }

    private void c(T[] tArr) {
        if (tArr.length < 1) {
            return;
        }
        int e2 = e(tArr);
        if (this.n != 0) {
            a(tArr, e2);
            return;
        }
        this.b = tArr;
        this.n = e2;
        this.l.a(0, e2);
    }

    private void d(T t) {
        T[] tArr = this.b;
        int i = this.k;
        tArr[i] = t;
        this.k = i + 1;
        this.n++;
        this.l.a(this.k - 1, 1);
    }

    private void d(@af T[] tArr) {
        boolean z = !(this.l instanceof a);
        if (z) {
            b();
        }
        this.i = 0;
        this.j = this.n;
        this.h = this.b;
        this.k = 0;
        int e2 = e(tArr);
        this.b = (T[]) ((Object[]) Array.newInstance((Class<?>) this.o, e2));
        while (true) {
            if (this.k >= e2 && this.i >= this.j) {
                break;
            }
            int i = this.i;
            int i2 = this.j;
            if (i >= i2) {
                int i3 = this.k;
                int i4 = e2 - i3;
                System.arraycopy(tArr, i3, this.b, i3, i4);
                this.k += i4;
                this.n += i4;
                this.l.a(i3, i4);
                break;
            }
            int i5 = this.k;
            if (i5 >= e2) {
                int i6 = i2 - i;
                this.n -= i6;
                this.l.b(i5, i6);
                break;
            }
            T t = this.h[i];
            T t2 = tArr[i5];
            int compare = this.l.compare(t, t2);
            if (compare < 0) {
                e();
            } else {
                if (compare <= 0) {
                    if (this.l.b(t, t2)) {
                        T[] tArr2 = this.b;
                        int i7 = this.k;
                        tArr2[i7] = t2;
                        this.i++;
                        this.k = i7 + 1;
                        if (!this.l.a(t, t2)) {
                            b bVar = this.l;
                            bVar.a(this.k - 1, 1, bVar.c(t, t2));
                        }
                    } else {
                        e();
                    }
                }
                d((g<T>) t2);
            }
        }
        this.h = null;
        if (z) {
            c();
        }
    }

    private int e(@af T[] tArr) {
        if (tArr.length == 0) {
            return 0;
        }
        Arrays.sort(tArr, this.l);
        int i = 1;
        int i2 = 0;
        for (int i3 = 1; i3 < tArr.length; i3++) {
            T t = tArr[i3];
            if (this.l.compare(tArr[i2], t) == 0) {
                int a2 = a((g<T>) t, (g<T>[]) tArr, i2, i);
                if (a2 != -1) {
                    tArr[a2] = t;
                } else {
                    if (i != i3) {
                        tArr[i] = t;
                    }
                    i++;
                }
            } else {
                if (i != i3) {
                    tArr[i] = t;
                }
                i2 = i;
                i++;
            }
        }
        return i;
    }

    private void e() {
        this.n--;
        this.i++;
        this.l.b(this.k, 1);
    }

    private void f() {
        if (this.h != null) {
            throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
        }
    }

    private T[] f(T[] tArr) {
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.o, tArr.length));
        System.arraycopy(tArr, 0, tArr2, 0, tArr.length);
        return tArr2;
    }

    public int a() {
        return this.n;
    }

    public int a(T t) {
        f();
        return a((g<T>) t, true);
    }

    public T a(int i) {
        f();
        T c2 = c(i);
        a(i, true);
        return c2;
    }

    public void a(int i, T t) {
        f();
        T c2 = c(i);
        boolean z = c2 == t || !this.l.a(c2, t);
        if (c2 != t && this.l.compare(c2, t) == 0) {
            this.b[i] = t;
            if (z) {
                b bVar = this.l;
                bVar.a(i, 1, bVar.c(c2, t));
                return;
            }
            return;
        }
        if (z) {
            b bVar2 = this.l;
            bVar2.a(i, 1, bVar2.c(c2, t));
        }
        a(i, false);
        int a2 = a((g<T>) t, false);
        if (i != a2) {
            this.l.c(i, a2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(@af Collection<T> collection) {
        a(collection.toArray((Object[]) Array.newInstance((Class<?>) this.o, collection.size())), true);
    }

    public void a(@af T... tArr) {
        a((Object[]) tArr, false);
    }

    public void a(@af T[] tArr, boolean z) {
        f();
        if (tArr.length == 0) {
            return;
        }
        if (z) {
            c((Object[]) tArr);
        } else {
            c((Object[]) f(tArr));
        }
    }

    public void b() {
        f();
        b bVar = this.l;
        if (bVar instanceof a) {
            return;
        }
        if (this.m == null) {
            this.m = new a(bVar);
        }
        this.l = this.m;
    }

    public void b(int i) {
        f();
        T c2 = c(i);
        a(i, false);
        int a2 = a((g<T>) c2, false);
        if (i != a2) {
            this.l.c(i, a2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void b(@af Collection<T> collection) {
        b(collection.toArray((Object[]) Array.newInstance((Class<?>) this.o, collection.size())), true);
    }

    public void b(@af T... tArr) {
        b((Object[]) tArr, false);
    }

    public void b(@af T[] tArr, boolean z) {
        f();
        if (z) {
            d((Object[]) tArr);
        } else {
            d((Object[]) f(tArr));
        }
    }

    public boolean b(T t) {
        f();
        return b((g<T>) t, true);
    }

    public int c(T t) {
        if (this.h != null) {
            int a2 = a(t, this.b, 0, this.k, 4);
            if (a2 != -1) {
                return a2;
            }
            int a3 = a(t, this.h, this.i, this.j, 4);
            if (a3 != -1) {
                return (a3 - this.i) + this.k;
            }
            return -1;
        }
        return a(t, this.b, 0, this.n, 4);
    }

    public T c(int i) {
        int i2;
        if (i < this.n && i >= 0) {
            T[] tArr = this.h;
            return (tArr == null || i < (i2 = this.k)) ? this.b[i] : tArr[(i - i2) + this.i];
        }
        throw new IndexOutOfBoundsException("Asked to get item at " + i + " but size is " + this.n);
    }

    public void c() {
        f();
        b bVar = this.l;
        if (bVar instanceof a) {
            ((a) bVar).a();
        }
        b bVar2 = this.l;
        a aVar = this.m;
        if (bVar2 == aVar) {
            this.l = aVar.a;
        }
    }

    public void d() {
        f();
        int i = this.n;
        if (i == 0) {
            return;
        }
        Arrays.fill(this.b, 0, i, (Object) null);
        this.n = 0;
        this.l.b(0, i);
    }
}
