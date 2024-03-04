package android.support.v4.j;

import android.support.annotation.af;
import android.support.annotation.ag;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class j<E> implements Cloneable {
    private static final Object a = new Object();
    private boolean b;
    private long[] c;
    private Object[] d;
    private int e;

    public j() {
        this(10);
    }

    public j(int i) {
        this.b = false;
        if (i == 0) {
            this.c = g.b;
            this.d = g.c;
        } else {
            int b = g.b(i);
            this.c = new long[b];
            this.d = new Object[b];
        }
        this.e = 0;
    }

    private void e() {
        int i = this.e;
        long[] jArr = this.c;
        Object[] objArr = this.d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != a) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
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
    public j<E> clone() {
        try {
            j<E> jVar = (j) super.clone();
            jVar.c = (long[]) this.c.clone();
            jVar.d = (Object[]) this.d.clone();
            return jVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @ag
    public E a(long j) {
        return a(j, (long) null);
    }

    public E a(long j, E e) {
        int a2 = g.a(this.c, this.e, j);
        if (a2 >= 0) {
            Object[] objArr = this.d;
            if (objArr[a2] != a) {
                return (E) objArr[a2];
            }
        }
        return e;
    }

    public void a(int i) {
        Object[] objArr = this.d;
        Object obj = objArr[i];
        Object obj2 = a;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.b = true;
        }
    }

    public void a(int i, E e) {
        if (this.b) {
            e();
        }
        this.d[i] = e;
    }

    public void a(@af j<? extends E> jVar) {
        int b = jVar.b();
        for (int i = 0; i < b; i++) {
            b(jVar.b(i), jVar.c(i));
        }
    }

    public int b() {
        if (this.b) {
            e();
        }
        return this.e;
    }

    public long b(int i) {
        if (this.b) {
            e();
        }
        return this.c[i];
    }

    public void b(long j) {
        int a2 = g.a(this.c, this.e, j);
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

    public void b(long j, E e) {
        int a2 = g.a(this.c, this.e, j);
        if (a2 >= 0) {
            this.d[a2] = e;
            return;
        }
        int i = a2 ^ (-1);
        if (i < this.e) {
            Object[] objArr = this.d;
            if (objArr[i] == a) {
                this.c[i] = j;
                objArr[i] = e;
                return;
            }
        }
        if (this.b && this.e >= this.c.length) {
            e();
            i = g.a(this.c, this.e, j) ^ (-1);
        }
        int i2 = this.e;
        if (i2 >= this.c.length) {
            int b = g.b(i2 + 1);
            long[] jArr = new long[b];
            Object[] objArr2 = new Object[b];
            long[] jArr2 = this.c;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.c = jArr;
            this.d = objArr2;
        }
        int i3 = this.e;
        if (i3 - i != 0) {
            long[] jArr3 = this.c;
            int i4 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i4, i3 - i);
            Object[] objArr4 = this.d;
            System.arraycopy(objArr4, i, objArr4, i4, this.e - i);
        }
        this.c[i] = j;
        this.d[i] = e;
        this.e++;
    }

    public boolean b(E e) {
        return a((j<E>) e) >= 0;
    }

    public E c(int i) {
        if (this.b) {
            e();
        }
        return (E) this.d[i];
    }

    public void c(long j) {
        b(j);
    }

    public void c(long j, E e) {
        int i = this.e;
        if (i != 0 && j <= this.c[i - 1]) {
            b(j, e);
            return;
        }
        if (this.b && this.e >= this.c.length) {
            e();
        }
        int i2 = this.e;
        if (i2 >= this.c.length) {
            int b = g.b(i2 + 1);
            long[] jArr = new long[b];
            Object[] objArr = new Object[b];
            long[] jArr2 = this.c;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.d;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.c = jArr;
            this.d = objArr;
        }
        this.c[i2] = j;
        this.d[i2] = e;
        this.e = i2 + 1;
    }

    public boolean c() {
        return b() == 0;
    }

    public int d(long j) {
        if (this.b) {
            e();
        }
        return g.a(this.c, this.e, j);
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

    public boolean e(long j) {
        return d(j) >= 0;
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
            sb.append(b(i));
            sb.append('=');
            E c = c(i);
            if (c != this) {
                sb.append(c);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
