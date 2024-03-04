package android.support.v7.f;

import android.support.annotation.af;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c implements e {
    private static final int f = 0;
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 3;
    final e a;
    int b = 0;
    int c = -1;
    int d = -1;
    Object e = null;

    public c(@af e eVar) {
        this.a = eVar;
    }

    public void a() {
        int i2 = this.b;
        if (i2 == 0) {
            return;
        }
        if (i2 == 1) {
            this.a.a(this.c, this.d);
        } else if (i2 == 2) {
            this.a.b(this.c, this.d);
        } else if (i2 == 3) {
            this.a.a(this.c, this.d, this.e);
        }
        this.e = null;
        this.b = 0;
    }

    @Override // android.support.v7.f.e
    public void a(int i2, int i3) {
        int i4;
        if (this.b == 1 && i2 >= (i4 = this.c)) {
            int i5 = this.d;
            if (i2 <= i4 + i5) {
                this.d = i5 + i3;
                this.c = Math.min(i2, i4);
                return;
            }
        }
        a();
        this.c = i2;
        this.d = i3;
        this.b = 1;
    }

    @Override // android.support.v7.f.e
    public void a(int i2, int i3, Object obj) {
        int i4;
        if (this.b == 3) {
            int i5 = this.c;
            int i6 = this.d;
            if (i2 <= i5 + i6 && (i4 = i2 + i3) >= i5 && this.e == obj) {
                this.c = Math.min(i2, i5);
                this.d = Math.max(i6 + i5, i4) - this.c;
                return;
            }
        }
        a();
        this.c = i2;
        this.d = i3;
        this.e = obj;
        this.b = 3;
    }

    @Override // android.support.v7.f.e
    public void b(int i2, int i3) {
        int i4;
        if (this.b == 2 && (i4 = this.c) >= i2 && i4 <= i2 + i3) {
            this.d += i3;
            this.c = i2;
            return;
        }
        a();
        this.c = i2;
        this.d = i3;
        this.b = 2;
    }

    @Override // android.support.v7.f.e
    public void c(int i2, int i3) {
        a();
        this.a.c(i2, i3);
    }
}
