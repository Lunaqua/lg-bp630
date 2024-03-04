package android.support.v7.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class bb {
    static final int a = 1;
    static final int b = 2;
    static final int c = 4;
    static final int d = 0;
    static final int e = 1;
    static final int f = 2;
    static final int g = 4;
    static final int h = 4;
    static final int i = 16;
    static final int j = 32;
    static final int k = 64;
    static final int l = 8;
    static final int m = 256;
    static final int n = 512;
    static final int o = 1024;
    static final int p = 12;
    static final int q = 4096;
    static final int r = 8192;
    static final int s = 16384;
    static final int t = 7;
    final b u;
    a v = new a();

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class a {
        int a = 0;
        int b;
        int c;
        int d;
        int e;

        a() {
        }

        void a() {
            this.a = 0;
        }

        void a(int i) {
            this.a = i | this.a;
        }

        void a(int i, int i2) {
            this.a = (i & i2) | (this.a & (i2 ^ (-1)));
        }

        void a(int i, int i2, int i3, int i4) {
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }

        int b(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        boolean b() {
            int i = this.a;
            if ((i & 7) == 0 || (i & (b(this.d, this.b) << 0)) != 0) {
                int i2 = this.a;
                if ((i2 & 112) == 0 || (i2 & (b(this.d, this.c) << 4)) != 0) {
                    int i3 = this.a;
                    if ((i3 & 1792) == 0 || (i3 & (b(this.e, this.b) << 8)) != 0) {
                        int i4 = this.a;
                        return (i4 & 28672) == 0 || (i4 & (b(this.e, this.c) << 12)) != 0;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    interface b {
        int a();

        int a(View view);

        View a(int i);

        int b(View view);

        View b();

        int c();

        int d();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface c {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public bb(b bVar) {
        this.u = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View a(int i2, int i3, int i4, int i5) {
        int c2 = this.u.c();
        int d2 = this.u.d();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        while (i2 != i3) {
            View a2 = this.u.a(i2);
            this.v.a(c2, d2, this.u.a(a2), this.u.b(a2));
            if (i4 != 0) {
                this.v.a();
                this.v.a(i4);
                if (this.v.b()) {
                    return a2;
                }
            }
            if (i5 != 0) {
                this.v.a();
                this.v.a(i5);
                if (this.v.b()) {
                    view = a2;
                }
            }
            i2 += i6;
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(View view, int i2) {
        this.v.a(this.u.c(), this.u.d(), this.u.a(view), this.u.b(view));
        if (i2 != 0) {
            this.v.a();
            this.v.a(i2);
            return this.v.b();
        }
        return false;
    }
}
