package android.support.v4.j;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class d<E> {
    private E[] a;
    private int b;
    private int c;
    private int d;

    public d() {
        this(8);
    }

    public d(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (i > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        i = Integer.bitCount(i) != 1 ? Integer.highestOneBit(i - 1) << 1 : i;
        this.d = i - 1;
        this.a = (E[]) new Object[i];
    }

    private void h() {
        E[] eArr = this.a;
        int length = eArr.length;
        int i = this.b;
        int i2 = length - i;
        int i3 = length << 1;
        if (i3 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        Object[] objArr = new Object[i3];
        System.arraycopy(eArr, i, objArr, 0, i2);
        System.arraycopy(this.a, 0, objArr, i2, this.b);
        this.a = (E[]) objArr;
        this.b = 0;
        this.c = length;
        this.d = i3 - 1;
    }

    public E a() {
        int i = this.b;
        if (i != this.c) {
            E[] eArr = this.a;
            E e = eArr[i];
            eArr[i] = null;
            this.b = (i + 1) & this.d;
            return e;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void a(int i) {
        if (i <= 0) {
            return;
        }
        if (i > f()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int length = this.a.length;
        int i2 = this.b;
        if (i < length - i2) {
            length = i2 + i;
        }
        for (int i3 = this.b; i3 < length; i3++) {
            this.a[i3] = null;
        }
        int i4 = this.b;
        int i5 = length - i4;
        int i6 = i - i5;
        this.b = this.d & (i4 + i5);
        if (i6 > 0) {
            for (int i7 = 0; i7 < i6; i7++) {
                this.a[i7] = null;
            }
            this.b = i6;
        }
    }

    public void a(E e) {
        this.b = (this.b - 1) & this.d;
        E[] eArr = this.a;
        int i = this.b;
        eArr[i] = e;
        if (i == this.c) {
            h();
        }
    }

    public E b() {
        int i = this.b;
        int i2 = this.c;
        if (i != i2) {
            int i3 = this.d & (i2 - 1);
            E[] eArr = this.a;
            E e = eArr[i3];
            eArr[i3] = null;
            this.c = i3;
            return e;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void b(int i) {
        int i2;
        if (i <= 0) {
            return;
        }
        if (i > f()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = this.c;
        int i4 = i < i3 ? i3 - i : 0;
        int i5 = i4;
        while (true) {
            i2 = this.c;
            if (i5 >= i2) {
                break;
            }
            this.a[i5] = null;
            i5++;
        }
        int i6 = i2 - i4;
        int i7 = i - i6;
        this.c = i2 - i6;
        if (i7 > 0) {
            this.c = this.a.length;
            int i8 = this.c - i7;
            for (int i9 = i8; i9 < this.c; i9++) {
                this.a[i9] = null;
            }
            this.c = i8;
        }
    }

    public void b(E e) {
        E[] eArr = this.a;
        int i = this.c;
        eArr[i] = e;
        this.c = this.d & (i + 1);
        if (this.c == this.b) {
            h();
        }
    }

    public E c(int i) {
        if (i < 0 || i >= f()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.a[this.d & (this.b + i)];
    }

    public void c() {
        a(f());
    }

    public E d() {
        int i = this.b;
        if (i != this.c) {
            return this.a[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E e() {
        int i = this.b;
        int i2 = this.c;
        if (i != i2) {
            return this.a[(i2 - 1) & this.d];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int f() {
        return (this.c - this.b) & this.d;
    }

    public boolean g() {
        return this.b == this.c;
    }
}
