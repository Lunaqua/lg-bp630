package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.WindowInsets;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ak {
    private final Object a;

    public ak(ak akVar) {
        WindowInsets windowInsets = null;
        if (Build.VERSION.SDK_INT >= 20 && akVar != null) {
            windowInsets = new WindowInsets((WindowInsets) akVar.a);
        }
        this.a = windowInsets;
    }

    private ak(Object obj) {
        this.a = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ak a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new ak(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object a(ak akVar) {
        if (akVar == null) {
            return null;
        }
        return akVar.a;
    }

    public int a() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.a).getSystemWindowInsetLeft();
        }
        return 0;
    }

    public ak a(int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 20) {
            return new ak(((WindowInsets) this.a).replaceSystemWindowInsets(i, i2, i3, i4));
        }
        return null;
    }

    public ak a(Rect rect) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ak(((WindowInsets) this.a).replaceSystemWindowInsets(rect));
        }
        return null;
    }

    public int b() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.a).getSystemWindowInsetTop();
        }
        return 0;
    }

    public int c() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.a).getSystemWindowInsetRight();
        }
        return 0;
    }

    public int d() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.a).getSystemWindowInsetBottom();
        }
        return 0;
    }

    public boolean e() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.a).hasSystemWindowInsets();
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ak akVar = (ak) obj;
        Object obj2 = this.a;
        return obj2 == null ? akVar.a == null : obj2.equals(akVar.a);
    }

    public boolean f() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.a).hasInsets();
        }
        return false;
    }

    public boolean g() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.a).isConsumed();
        }
        return false;
    }

    public boolean h() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.a).isRound();
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public ak i() {
        if (Build.VERSION.SDK_INT >= 20) {
            return new ak(((WindowInsets) this.a).consumeSystemWindowInsets());
        }
        return null;
    }

    public int j() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.a).getStableInsetTop();
        }
        return 0;
    }

    public int k() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.a).getStableInsetLeft();
        }
        return 0;
    }

    public int l() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.a).getStableInsetRight();
        }
        return 0;
    }

    public int m() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.a).getStableInsetBottom();
        }
        return 0;
    }

    public boolean n() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.a).hasStableInsets();
        }
        return false;
    }

    public ak o() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ak(((WindowInsets) this.a).consumeStableInsets());
        }
        return null;
    }

    @android.support.annotation.ag
    public d p() {
        if (Build.VERSION.SDK_INT >= 28) {
            return d.a(((WindowInsets) this.a).getDisplayCutout());
        }
        return null;
    }

    public ak q() {
        return Build.VERSION.SDK_INT >= 28 ? new ak(((WindowInsets) this.a).consumeDisplayCutout()) : this;
    }
}
