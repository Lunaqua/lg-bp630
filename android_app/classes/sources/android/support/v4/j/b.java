package android.support.v4.j;

import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class b<E> implements Collection<E>, Set<E> {
    private static final boolean c = false;
    private static final String d = "ArraySet";
    private static final int[] e = new int[0];
    private static final Object[] f = new Object[0];
    private static final int g = 4;
    private static final int h = 10;
    @ag
    private static Object[] i;
    private static int j;
    @ag
    private static Object[] k;
    private static int l;
    Object[] a;
    int b;
    private int[] m;
    private l<E, E> n;

    public b() {
        this(0);
    }

    public b(int i2) {
        if (i2 == 0) {
            this.m = e;
            this.a = f;
        } else {
            d(i2);
        }
        this.b = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(@ag b<E> bVar) {
        this();
        if (bVar != 0) {
            a((b) bVar);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(@ag Collection<E> collection) {
        this();
        if (collection != 0) {
            addAll(collection);
        }
    }

    private int a() {
        int i2 = this.b;
        if (i2 == 0) {
            return -1;
        }
        int a = g.a(this.m, i2, 0);
        if (a >= 0 && this.a[a] != null) {
            int i3 = a + 1;
            while (i3 < i2 && this.m[i3] == 0) {
                if (this.a[i3] == null) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = a - 1; i4 >= 0 && this.m[i4] == 0; i4--) {
                if (this.a[i4] == null) {
                    return i4;
                }
            }
            return i3 ^ (-1);
        }
        return a;
    }

    private int a(Object obj, int i2) {
        int i3 = this.b;
        if (i3 == 0) {
            return -1;
        }
        int a = g.a(this.m, i3, i2);
        if (a >= 0 && !obj.equals(this.a[a])) {
            int i4 = a + 1;
            while (i4 < i3 && this.m[i4] == i2) {
                if (obj.equals(this.a[i4])) {
                    return i4;
                }
                i4++;
            }
            for (int i5 = a - 1; i5 >= 0 && this.m[i5] == i2; i5--) {
                if (obj.equals(this.a[i5])) {
                    return i5;
                }
            }
            return i4 ^ (-1);
        }
        return a;
    }

    private static void a(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (b.class) {
                if (l < 10) {
                    objArr[0] = k;
                    objArr[1] = iArr;
                    for (int i3 = i2 - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    k = objArr;
                    l++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (b.class) {
                if (j < 10) {
                    objArr[0] = i;
                    objArr[1] = iArr;
                    for (int i4 = i2 - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    i = objArr;
                    j++;
                }
            }
        }
    }

    private l<E, E> b() {
        if (this.n == null) {
            this.n = new l<E, E>() { // from class: android.support.v4.j.b.1
                @Override // android.support.v4.j.l
                protected int a() {
                    return b.this.b;
                }

                @Override // android.support.v4.j.l
                protected int a(Object obj) {
                    return b.this.a(obj);
                }

                @Override // android.support.v4.j.l
                protected Object a(int i2, int i3) {
                    return b.this.a[i2];
                }

                @Override // android.support.v4.j.l
                protected E a(int i2, E e2) {
                    throw new UnsupportedOperationException("not a map");
                }

                @Override // android.support.v4.j.l
                protected void a(int i2) {
                    b.this.c(i2);
                }

                @Override // android.support.v4.j.l
                protected void a(E e2, E e3) {
                    b.this.add(e2);
                }

                @Override // android.support.v4.j.l
                protected int b(Object obj) {
                    return b.this.a(obj);
                }

                @Override // android.support.v4.j.l
                protected Map<E, E> b() {
                    throw new UnsupportedOperationException("not a map");
                }

                @Override // android.support.v4.j.l
                protected void c() {
                    b.this.clear();
                }
            };
        }
        return this.n;
    }

    private void d(int i2) {
        if (i2 == 8) {
            synchronized (b.class) {
                if (k != null) {
                    Object[] objArr = k;
                    this.a = objArr;
                    k = (Object[]) objArr[0];
                    this.m = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    l--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (b.class) {
                if (i != null) {
                    Object[] objArr2 = i;
                    this.a = objArr2;
                    i = (Object[]) objArr2[0];
                    this.m = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    j--;
                    return;
                }
            }
        }
        this.m = new int[i2];
        this.a = new Object[i2];
    }

    public int a(@ag Object obj) {
        return obj == null ? a() : a(obj, obj.hashCode());
    }

    public void a(int i2) {
        int[] iArr = this.m;
        if (iArr.length < i2) {
            Object[] objArr = this.a;
            d(i2);
            int i3 = this.b;
            if (i3 > 0) {
                System.arraycopy(iArr, 0, this.m, 0, i3);
                System.arraycopy(objArr, 0, this.a, 0, this.b);
            }
            a(iArr, objArr, this.b);
        }
    }

    public void a(@af b<? extends E> bVar) {
        int i2 = bVar.b;
        a(this.b + i2);
        if (this.b != 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                add(bVar.b(i3));
            }
        } else if (i2 > 0) {
            System.arraycopy(bVar.m, 0, this.m, 0, i2);
            System.arraycopy(bVar.a, 0, this.a, 0, i2);
            this.b = i2;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(@ag E e2) {
        int i2;
        int a;
        if (e2 == null) {
            a = a();
            i2 = 0;
        } else {
            int hashCode = e2.hashCode();
            i2 = hashCode;
            a = a(e2, hashCode);
        }
        if (a >= 0) {
            return false;
        }
        int i3 = a ^ (-1);
        int i4 = this.b;
        if (i4 >= this.m.length) {
            int i5 = 4;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.m;
            Object[] objArr = this.a;
            d(i5);
            int[] iArr2 = this.m;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.a, 0, objArr.length);
            }
            a(iArr, objArr, this.b);
        }
        int i6 = this.b;
        if (i3 < i6) {
            int[] iArr3 = this.m;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr2 = this.a;
            System.arraycopy(objArr2, i3, objArr2, i7, this.b - i3);
        }
        this.m[i3] = i2;
        this.a[i3] = e2;
        this.b++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(@af Collection<? extends E> collection) {
        a(this.b + collection.size());
        boolean z = false;
        for (E e2 : collection) {
            z |= add(e2);
        }
        return z;
    }

    @ag
    public E b(int i2) {
        return (E) this.a[i2];
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public void b(E e2) {
        int i2 = this.b;
        int hashCode = e2 == null ? 0 : e2.hashCode();
        int[] iArr = this.m;
        if (i2 >= iArr.length) {
            throw new IllegalStateException("Array is full");
        }
        if (i2 > 0 && iArr[i2 - 1] > hashCode) {
            add(e2);
            return;
        }
        this.b = i2 + 1;
        this.m[i2] = hashCode;
        this.a[i2] = e2;
    }

    public boolean b(@af b<? extends E> bVar) {
        int i2 = bVar.b;
        int i3 = this.b;
        for (int i4 = 0; i4 < i2; i4++) {
            remove(bVar.b(i4));
        }
        return i3 != this.b;
    }

    public E c(int i2) {
        Object[] objArr = this.a;
        E e2 = (E) objArr[i2];
        int i3 = this.b;
        if (i3 <= 1) {
            a(this.m, objArr, i3);
            this.m = e;
            this.a = f;
            this.b = 0;
        } else {
            int[] iArr = this.m;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                this.b--;
                int i4 = this.b;
                if (i2 < i4) {
                    int[] iArr2 = this.m;
                    int i5 = i2 + 1;
                    System.arraycopy(iArr2, i5, iArr2, i2, i4 - i2);
                    Object[] objArr2 = this.a;
                    System.arraycopy(objArr2, i5, objArr2, i2, this.b - i2);
                }
                this.a[this.b] = null;
            } else {
                int i6 = i3 > 8 ? i3 + (i3 >> 1) : 8;
                int[] iArr3 = this.m;
                Object[] objArr3 = this.a;
                d(i6);
                this.b--;
                if (i2 > 0) {
                    System.arraycopy(iArr3, 0, this.m, 0, i2);
                    System.arraycopy(objArr3, 0, this.a, 0, i2);
                }
                int i7 = this.b;
                if (i2 < i7) {
                    int i8 = i2 + 1;
                    System.arraycopy(iArr3, i8, this.m, i2, i7 - i2);
                    System.arraycopy(objArr3, i8, this.a, i2, this.b - i2);
                }
            }
        }
        return e2;
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i2 = this.b;
        if (i2 != 0) {
            a(this.m, this.a, i2);
            this.m = e;
            this.a = f;
            this.b = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(@ag Object obj) {
        return a(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(@af Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.b; i2++) {
                try {
                    if (!set.contains(b(i2))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.m;
        int i2 = this.b;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += iArr[i4];
        }
        return i3;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.b <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return b().e().iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(@ag Object obj) {
        int a = a(obj);
        if (a >= 0) {
            c(a);
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(@af Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= remove(it.next());
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(@af Collection<?> collection) {
        boolean z = false;
        for (int i2 = this.b - 1; i2 >= 0; i2--) {
            if (!collection.contains(this.a[i2])) {
                c(i2);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.b;
    }

    @Override // java.util.Collection, java.util.Set
    @af
    public Object[] toArray() {
        int i2 = this.b;
        Object[] objArr = new Object[i2];
        System.arraycopy(this.a, 0, objArr, 0, i2);
        return objArr;
    }

    @Override // java.util.Collection, java.util.Set
    @af
    public <T> T[] toArray(@af T[] tArr) {
        if (tArr.length < this.b) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.b));
        }
        System.arraycopy(this.a, 0, tArr, 0, this.b);
        int length = tArr.length;
        int i2 = this.b;
        if (length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.b * 14);
        sb.append('{');
        for (int i2 = 0; i2 < this.b; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            E b = b(i2);
            if (b != this) {
                sb.append(b);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
