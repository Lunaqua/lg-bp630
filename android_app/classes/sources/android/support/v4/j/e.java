package android.support.v4.j;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class e {
    private int[] a;
    private int b;
    private int c;
    private int d;

    public e() {
        this(8);
    }

    public e(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (i > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        i = Integer.bitCount(i) != 1 ? Integer.highestOneBit(i - 1) << 1 : i;
        this.d = i - 1;
        this.a = new int[i];
    }

    private void h() {
        int[] iArr = this.a;
        int length = iArr.length;
        int i = this.b;
        int i2 = length - i;
        int i3 = length << 1;
        if (i3 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        int[] iArr2 = new int[i3];
        System.arraycopy(iArr, i, iArr2, 0, i2);
        System.arraycopy(this.a, 0, iArr2, i2, this.b);
        this.a = iArr2;
        this.b = 0;
        this.c = length;
        this.d = i3 - 1;
    }

    public int a() {
        int i = this.b;
        if (i != this.c) {
            int i2 = this.a[i];
            this.b = (i + 1) & this.d;
            return i2;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void a(int i) {
        this.b = (this.b - 1) & this.d;
        int[] iArr = this.a;
        int i2 = this.b;
        iArr[i2] = i;
        if (i2 == this.c) {
            h();
        }
    }

    public int b() {
        int i = this.b;
        int i2 = this.c;
        if (i != i2) {
            int i3 = this.d & (i2 - 1);
            int i4 = this.a[i3];
            this.c = i3;
            return i4;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void b(int i) {
        int[] iArr = this.a;
        int i2 = this.c;
        iArr[i2] = i;
        this.c = this.d & (i2 + 1);
        if (this.c == this.b) {
            h();
        }
    }

    public void c() {
        this.c = this.b;
    }

    public void c(int i) {
        if (i <= 0) {
            return;
        }
        if (i > f()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.b = this.d & (this.b + i);
    }

    public int d() {
        int i = this.b;
        if (i != this.c) {
            return this.a[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void d(int i) {
        if (i <= 0) {
            return;
        }
        if (i > f()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.c = this.d & (this.c - i);
    }

    public int e() {
        int i = this.b;
        int i2 = this.c;
        if (i != i2) {
            return this.a[(i2 - 1) & this.d];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int e(int i) {
        if (i < 0 || i >= f()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.a[this.d & (this.b + i)];
    }

    public int f() {
        return (this.c - this.b) & this.d;
    }

    public boolean g() {
        return this.b == this.c;
    }
}
