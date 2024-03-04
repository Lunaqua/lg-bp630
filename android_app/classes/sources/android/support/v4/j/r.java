package android.support.v4.j;

import android.support.annotation.af;
import android.support.annotation.ag;
import java.util.ConcurrentModificationException;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class r<K, V> {
    private static final boolean a = false;
    @ag
    static Object[] b = null;
    static int c = 0;
    @ag
    static Object[] d = null;
    static int e = 0;
    private static final String i = "ArrayMap";
    private static final boolean j = true;
    private static final int k = 4;
    private static final int l = 10;
    int[] f;
    Object[] g;
    int h;

    public r() {
        this.f = g.a;
        this.g = g.c;
        this.h = 0;
    }

    public r(int i2) {
        if (i2 == 0) {
            this.f = g.a;
            this.g = g.c;
        } else {
            e(i2);
        }
        this.h = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public r(r<K, V> rVar) {
        this();
        if (rVar != 0) {
            a((r) rVar);
        }
    }

    private static int a(int[] iArr, int i2, int i3) {
        try {
            return g.a(iArr, i2, i3);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private static void a(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (a.class) {
                if (e < 10) {
                    objArr[0] = d;
                    objArr[1] = iArr;
                    for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    d = objArr;
                    e++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (a.class) {
                if (c < 10) {
                    objArr[0] = b;
                    objArr[1] = iArr;
                    for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    b = objArr;
                    c++;
                }
            }
        }
    }

    private void e(int i2) {
        if (i2 == 8) {
            synchronized (a.class) {
                if (d != null) {
                    Object[] objArr = d;
                    this.g = objArr;
                    d = (Object[]) objArr[0];
                    this.f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    e--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (a.class) {
                if (b != null) {
                    Object[] objArr2 = b;
                    this.g = objArr2;
                    b = (Object[]) objArr2[0];
                    this.f = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    c--;
                    return;
                }
            }
        }
        this.f = new int[i2];
        this.g = new Object[i2 << 1];
    }

    int a() {
        int i2 = this.h;
        if (i2 == 0) {
            return -1;
        }
        int a2 = a(this.f, i2, 0);
        if (a2 >= 0 && this.g[a2 << 1] != null) {
            int i3 = a2 + 1;
            while (i3 < i2 && this.f[i3] == 0) {
                if (this.g[i3 << 1] == null) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = a2 - 1; i4 >= 0 && this.f[i4] == 0; i4--) {
                if (this.g[i4 << 1] == null) {
                    return i4;
                }
            }
            return i3 ^ (-1);
        }
        return a2;
    }

    public int a(@ag Object obj) {
        return obj == null ? a() : a(obj, obj.hashCode());
    }

    int a(Object obj, int i2) {
        int i3 = this.h;
        if (i3 == 0) {
            return -1;
        }
        int a2 = a(this.f, i3, i2);
        if (a2 >= 0 && !obj.equals(this.g[a2 << 1])) {
            int i4 = a2 + 1;
            while (i4 < i3 && this.f[i4] == i2) {
                if (obj.equals(this.g[i4 << 1])) {
                    return i4;
                }
                i4++;
            }
            for (int i5 = a2 - 1; i5 >= 0 && this.f[i5] == i2; i5--) {
                if (obj.equals(this.g[i5 << 1])) {
                    return i5;
                }
            }
            return i4 ^ (-1);
        }
        return a2;
    }

    public V a(int i2, V v) {
        int i3 = (i2 << 1) + 1;
        Object[] objArr = this.g;
        V v2 = (V) objArr[i3];
        objArr[i3] = v;
        return v2;
    }

    public void a(int i2) {
        int i3 = this.h;
        int[] iArr = this.f;
        if (iArr.length < i2) {
            Object[] objArr = this.g;
            e(i2);
            if (this.h > 0) {
                System.arraycopy(iArr, 0, this.f, 0, i3);
                System.arraycopy(objArr, 0, this.g, 0, i3 << 1);
            }
            a(iArr, objArr, i3);
        }
        if (this.h != i3) {
            throw new ConcurrentModificationException();
        }
    }

    public void a(@af r<? extends K, ? extends V> rVar) {
        int i2 = rVar.h;
        a(this.h + i2);
        if (this.h != 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                put(rVar.b(i3), rVar.c(i3));
            }
        } else if (i2 > 0) {
            System.arraycopy(rVar.f, 0, this.f, 0, i2);
            System.arraycopy(rVar.g, 0, this.g, 0, i2 << 1);
            this.h = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(Object obj) {
        int i2 = this.h * 2;
        Object[] objArr = this.g;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (obj.equals(objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    public K b(int i2) {
        return (K) this.g[i2 << 1];
    }

    public V c(int i2) {
        return (V) this.g[(i2 << 1) + 1];
    }

    public void clear() {
        int i2 = this.h;
        if (i2 > 0) {
            int[] iArr = this.f;
            Object[] objArr = this.g;
            this.f = g.a;
            this.g = g.c;
            this.h = 0;
            a(iArr, objArr, i2);
        }
        if (this.h > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(@ag Object obj) {
        if (a(obj) >= 0) {
            return j;
        }
        return false;
    }

    public boolean containsValue(Object obj) {
        if (b(obj) >= 0) {
            return j;
        }
        return false;
    }

    public V d(int i2) {
        int i3;
        Object[] objArr = this.g;
        int i4 = i2 << 1;
        V v = (V) objArr[i4 + 1];
        int i5 = this.h;
        if (i5 <= 1) {
            a(this.f, objArr, i5);
            this.f = g.a;
            this.g = g.c;
            i3 = 0;
        } else {
            i3 = i5 - 1;
            int[] iArr = this.f;
            if (iArr.length <= 8 || i5 >= iArr.length / 3) {
                if (i2 < i3) {
                    int[] iArr2 = this.f;
                    int i6 = i2 + 1;
                    int i7 = i3 - i2;
                    System.arraycopy(iArr2, i6, iArr2, i2, i7);
                    Object[] objArr2 = this.g;
                    System.arraycopy(objArr2, i6 << 1, objArr2, i4, i7 << 1);
                }
                Object[] objArr3 = this.g;
                int i8 = i3 << 1;
                objArr3[i8] = null;
                objArr3[i8 + 1] = null;
            } else {
                int i9 = i5 > 8 ? i5 + (i5 >> 1) : 8;
                int[] iArr3 = this.f;
                Object[] objArr4 = this.g;
                e(i9);
                if (i5 != this.h) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    System.arraycopy(iArr3, 0, this.f, 0, i2);
                    System.arraycopy(objArr4, 0, this.g, 0, i4);
                }
                if (i2 < i3) {
                    int i10 = i2 + 1;
                    int i11 = i3 - i2;
                    System.arraycopy(iArr3, i10, this.f, i2, i11);
                    System.arraycopy(objArr4, i10 << 1, this.g, i4, i11 << 1);
                }
            }
        }
        if (i5 == this.h) {
            this.h = i3;
            return v;
        }
        throw new ConcurrentModificationException();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return j;
        }
        if (obj instanceof r) {
            r rVar = (r) obj;
            if (size() != rVar.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.h; i2++) {
                try {
                    K b2 = b(i2);
                    V c2 = c(i2);
                    Object obj2 = rVar.get(b2);
                    if (c2 == null) {
                        if (obj2 != null || !rVar.containsKey(b2)) {
                            return false;
                        }
                    } else if (!c2.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return j;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i3 = 0; i3 < this.h; i3++) {
                try {
                    K b3 = b(i3);
                    V c3 = c(i3);
                    Object obj3 = map.get(b3);
                    if (c3 == null) {
                        if (obj3 != null || !map.containsKey(b3)) {
                            return false;
                        }
                    } else if (!c3.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return j;
        }
        return false;
    }

    @ag
    public V get(Object obj) {
        int a2 = a(obj);
        if (a2 >= 0) {
            return (V) this.g[(a2 << 1) + 1];
        }
        return null;
    }

    public int hashCode() {
        int[] iArr = this.f;
        Object[] objArr = this.g;
        int i2 = this.h;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        while (i3 < i2) {
            Object obj = objArr[i5];
            i4 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i5 += 2;
        }
        return i4;
    }

    public boolean isEmpty() {
        if (this.h <= 0) {
            return j;
        }
        return false;
    }

    @ag
    public V put(K k2, V v) {
        int i2;
        int a2;
        int i3 = this.h;
        if (k2 == null) {
            a2 = a();
            i2 = 0;
        } else {
            int hashCode = k2.hashCode();
            i2 = hashCode;
            a2 = a(k2, hashCode);
        }
        if (a2 >= 0) {
            int i4 = (a2 << 1) + 1;
            Object[] objArr = this.g;
            V v2 = (V) objArr[i4];
            objArr[i4] = v;
            return v2;
        }
        int i5 = a2 ^ (-1);
        if (i3 >= this.f.length) {
            int i6 = 4;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i6 = 8;
            }
            int[] iArr = this.f;
            Object[] objArr2 = this.g;
            e(i6);
            if (i3 != this.h) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.g, 0, objArr2.length);
            }
            a(iArr, objArr2, i3);
        }
        if (i5 < i3) {
            int[] iArr3 = this.f;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.g;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.h - i5) << 1);
        }
        int i8 = this.h;
        if (i3 == i8) {
            int[] iArr4 = this.f;
            if (i5 < iArr4.length) {
                iArr4[i5] = i2;
                Object[] objArr4 = this.g;
                int i9 = i5 << 1;
                objArr4[i9] = k2;
                objArr4[i9 + 1] = v;
                this.h = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    @ag
    public V remove(Object obj) {
        int a2 = a(obj);
        if (a2 >= 0) {
            return d(a2);
        }
        return null;
    }

    public int size() {
        return this.h;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.h * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.h; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            K b2 = b(i2);
            if (b2 != this) {
                sb.append(b2);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V c2 = c(i2);
            if (c2 != this) {
                sb.append(c2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
