package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class d {
    private final Object a;

    public d(Rect rect, List<Rect> list) {
        this(Build.VERSION.SDK_INT >= 28 ? new DisplayCutout(rect, list) : null);
    }

    private d(Object obj) {
        this.a = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new d(obj);
    }

    public int a() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.a).getSafeInsetTop();
        }
        return 0;
    }

    public int b() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.a).getSafeInsetBottom();
        }
        return 0;
    }

    public int c() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.a).getSafeInsetLeft();
        }
        return 0;
    }

    public int d() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.a).getSafeInsetRight();
        }
        return 0;
    }

    public List<Rect> e() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.a).getBoundingRects();
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        Object obj2 = this.a;
        return obj2 == null ? dVar.a == null : obj2.equals(dVar.a);
    }

    public int hashCode() {
        Object obj = this.a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.a + "}";
    }
}
