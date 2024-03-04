package android.support.v4.j;

import android.support.annotation.af;
import android.support.annotation.ag;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class s<E> implements Cloneable {
    private static final Object a = new Object();
    private boolean b;
    private int[] c;
    private Object[] d;
    private int e;

    public s() {
        this(10);
    }

    public s(int i) {
        this.b = false;
        if (i == 0) {
            this.c = g.a;
            this.d = g.c;
        } else {
            int a2 = g.a(i);
            this.c = new int[a2];
            this.d = new Object[a2];
        }
        this.e = 0;
    }

    private void e() {
        int i = this.e;
        int[] iArr = this.c;
        Object[] objArr = this.d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != a) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.b = false;
        this.e = i2;
    }

    public int a(E e) {
        if (this.b) {
            e();
        }
        for (int i = 0; i < this.e; i++) {
            if (this.d[i] == e) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public s<E> clone() {
        try {
            s<E> sVar = (s) super.clone();
            sVar.c = (int[]) this.c.clone();
            sVar.d = (Object[]) this.d.clone();
            return sVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @ag
    public E a(int i) {
        return a(i, (int) null);
    }

    public E a(int i, E e) {
        int a2 = g.a(this.c, this.e, i);
        if (a2 >= 0) {
            Object[] objArr = this.d;
            if (objArr[a2] != a) {
                return (E) objArr[a2];
            }
        }
        return e;
    }

    public void a(int i, int i2) {
        int min = Math.min(this.e, i2 + i);
        while (i < min) {
            d(i);
            i++;
        }
    }

    public void a(@af s<? extends E> sVar) {
        int b = sVar.b();
        for (int i = 0; i < b; i++) {
            b(sVar.e(i), sVar.f(i));
        }
    }

    public int b() {
        if (this.b) {
            e();
        }
        return this.e;
    }

    public void b(int i) {
        int a2 = g.a(this.c, this.e, i);
        if (a2 >= 0) {
            Object[] objArr = this.d;
            Object obj = objArr[a2];
            Object obj2 = a;
            if (obj != obj2) {
                objArr[a2] = obj2;
                this.b = true;
            }
        }
    }

    public void b(int i, E e) {
        int a2 = g.a(this.c, this.e, i);
        if (a2 >= 0) {
            this.d[a2] = e;
            return;
        }
        int i2 = a2 ^ (-1);
        if (i2 < this.e) {
            Object[] objArr = this.d;
            if (objArr[i2] == a) {
                this.c[i2] = i;
                objArr[i2] = e;
                return;
            }
        }
        if (this.b && this.e >= this.c.length) {
            e();
            i2 = g.a(this.c, this.e, i) ^ (-1);
        }
        int i3 = this.e;
        if (i3 >= this.c.length) {
            int a3 = g.a(i3 + 1);
            int[] iArr = new int[a3];
            Object[] objArr2 = new Object[a3];
            int[] iArr2 = this.c;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.c = iArr;
            this.d = objArr2;
        }
        int i4 = this.e;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.c;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            Object[] objArr4 = this.d;
            System.arraycopy(objArr4, i2, objArr4, i5, this.e - i2);
        }
        this.c[i2] = i;
        this.d[i2] = e;
        this.e++;
    }

    public boolean b(E e) {
        return a((s<E>) e) >= 0;
    }

    public void c(int i) {
        b(i);
    }

    public void c(int i, E e) {
        if (this.b) {
            e();
        }
        this.d[i] = e;
    }

    public boolean c() {
        return b() == 0;
    }

    public void d() {
        int i = this.e;
        Object[] objArr = this.d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.e = 0;
        this.b = false;
    }

    public void d(int i) {
        Object[] objArr = this.d;
        Object obj = objArr[i];
        Object obj2 = a;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.b = true;
        }
    }

    public void d(int i, E e) {
        int i2 = this.e;
        if (i2 != 0 && i <= this.c[i2 - 1]) {
            b(i, e);
            return;
        }
        if (this.b && this.e >= this.c.length) {
            e();
        }
        int i3 = this.e;
        if (i3 >= this.c.length) {
            int a2 = g.a(i3 + 1);
            int[] iArr = new int[a2];
            Object[] objArr = new Object[a2];
            int[] iArr2 = this.c;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.d;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.c = iArr;
            this.d = objArr;
        }
        this.c[i3] = i;
        this.d[i3] = e;
        this.e = i3 + 1;
    }

    public int e(int i) {
        if (this.b) {
            e();
        }
        return this.c[i];
    }

    public E f(int i) {
        if (this.b) {
            e();
        }
        return (E) this.d[i];
    }

    public int g(int i) {
        if (this.b) {
            e();
        }
        return g.a(this.c, this.e, i);
    }

    public boolean h(int i) {
        return g(i) >= 0;
    }

    public String toString() {
        if (b() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.e * 28);
        sb.append('{');
        for (int i = 0; i < this.e; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(e(i));
            sb.append('=');
            E f = f(i);
            if (f != this) {
                sb.append(f);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
