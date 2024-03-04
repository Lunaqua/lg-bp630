package android.support.v7.widget;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class aj {
    public static final int a = Integer.MIN_VALUE;
    private int b = 0;
    private int c = 0;
    private int d = Integer.MIN_VALUE;
    private int e = Integer.MIN_VALUE;
    private int f = 0;
    private int g = 0;
    private boolean h = false;
    private boolean i = false;

    public int a() {
        return this.b;
    }

    public void a(int i, int i2) {
        this.d = i;
        this.e = i2;
        this.i = true;
        if (this.h) {
            if (i2 != Integer.MIN_VALUE) {
                this.b = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.c = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.b = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.c = i2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001a, code lost:
        if (r2 != Integer.MIN_VALUE) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0028, code lost:
        if (r2 != Integer.MIN_VALUE) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(boolean r2) {
        /*
            r1 = this;
            boolean r0 = r1.h
            if (r2 != r0) goto L5
            return
        L5:
            r1.h = r2
            boolean r0 = r1.i
            if (r0 == 0) goto L2b
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r2 == 0) goto L1d
            int r2 = r1.e
            if (r2 == r0) goto L14
            goto L16
        L14:
            int r2 = r1.f
        L16:
            r1.b = r2
            int r2 = r1.d
            if (r2 == r0) goto L2f
            goto L31
        L1d:
            int r2 = r1.d
            if (r2 == r0) goto L22
            goto L24
        L22:
            int r2 = r1.f
        L24:
            r1.b = r2
            int r2 = r1.e
            if (r2 == r0) goto L2f
            goto L31
        L2b:
            int r2 = r1.f
            r1.b = r2
        L2f:
            int r2 = r1.g
        L31:
            r1.c = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.aj.a(boolean):void");
    }

    public int b() {
        return this.c;
    }

    public void b(int i, int i2) {
        this.i = false;
        if (i != Integer.MIN_VALUE) {
            this.f = i;
            this.b = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.g = i2;
            this.c = i2;
        }
    }

    public int c() {
        return this.h ? this.c : this.b;
    }

    public int d() {
        return this.h ? this.b : this.c;
    }
}
