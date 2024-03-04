package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class q {
    private ViewParent a;
    private ViewParent b;
    private final View c;
    private boolean d;
    private int[] e;

    public q(@android.support.annotation.af View view) {
        this.c = view;
    }

    private void a(int i, ViewParent viewParent) {
        if (i == 0) {
            this.a = viewParent;
        } else if (i != 1) {
        } else {
            this.b = viewParent;
        }
    }

    private ViewParent d(int i) {
        if (i != 0) {
            if (i != 1) {
                return null;
            }
            return this.b;
        }
        return this.a;
    }

    public void a(@android.support.annotation.af View view) {
        ab.Y(this.c);
    }

    public void a(boolean z) {
        if (this.d) {
            ab.Y(this.c);
        }
        this.d = z;
    }

    public boolean a() {
        return this.d;
    }

    public boolean a(float f, float f2) {
        ViewParent d;
        if (!a() || (d = d(0)) == null) {
            return false;
        }
        return ae.a(d, this.c, f, f2);
    }

    public boolean a(float f, float f2, boolean z) {
        ViewParent d;
        if (!a() || (d = d(0)) == null) {
            return false;
        }
        return ae.a(d, this.c, f, f2, z);
    }

    public boolean a(int i) {
        return d(i) != null;
    }

    public boolean a(int i, int i2) {
        if (a(i2)) {
            return true;
        }
        if (a()) {
            View view = this.c;
            for (ViewParent parent = this.c.getParent(); parent != null; parent = parent.getParent()) {
                if (ae.a(parent, view, this.c, i, i2)) {
                    a(i2, parent);
                    ae.b(parent, view, this.c, i, i2);
                    return true;
                }
                if (parent instanceof View) {
                    view = (View) parent;
                }
            }
            return false;
        }
        return false;
    }

    public boolean a(int i, int i2, int i3, int i4, @android.support.annotation.ag int[] iArr) {
        return a(i, i2, i3, i4, iArr, 0);
    }

    public boolean a(int i, int i2, int i3, int i4, @android.support.annotation.ag int[] iArr, int i5) {
        ViewParent d;
        int i6;
        int i7;
        if (!a() || (d = d(i5)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.c.getLocationInWindow(iArr);
            i6 = iArr[0];
            i7 = iArr[1];
        } else {
            i6 = 0;
            i7 = 0;
        }
        ae.a(d, this.c, i, i2, i3, i4, i5);
        if (iArr != null) {
            this.c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i6;
            iArr[1] = iArr[1] - i7;
        }
        return true;
    }

    public boolean a(int i, int i2, @android.support.annotation.ag int[] iArr, @android.support.annotation.ag int[] iArr2) {
        return a(i, i2, iArr, iArr2, 0);
    }

    public boolean a(int i, int i2, @android.support.annotation.ag int[] iArr, @android.support.annotation.ag int[] iArr2, int i3) {
        ViewParent d;
        int i4;
        int i5;
        if (!a() || (d = d(i3)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0) {
            if (iArr2 != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
            }
            return false;
        }
        if (iArr2 != null) {
            this.c.getLocationInWindow(iArr2);
            i4 = iArr2[0];
            i5 = iArr2[1];
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (iArr == null) {
            if (this.e == null) {
                this.e = new int[2];
            }
            iArr = this.e;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        ae.a(d, this.c, i, i2, iArr, i3);
        if (iArr2 != null) {
            this.c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i4;
            iArr2[1] = iArr2[1] - i5;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public boolean b() {
        return a(0);
    }

    public boolean b(int i) {
        return a(i, 0);
    }

    public void c() {
        c(0);
    }

    public void c(int i) {
        ViewParent d = d(i);
        if (d != null) {
            ae.a(d, this.c, i);
            a(i, (ViewParent) null);
        }
    }

    public void d() {
        ab.Y(this.c);
    }
}
