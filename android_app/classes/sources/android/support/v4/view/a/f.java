package android.support.v4.view.a;

import android.graphics.Rect;
import android.os.Build;
import android.view.accessibility.AccessibilityWindowInfo;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class f {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    private static final int g = -1;
    private Object f;

    private f(Object obj) {
        this.f = obj;
    }

    public static f a(f fVar) {
        if (Build.VERSION.SDK_INT < 21 || fVar == null) {
            return null;
        }
        return a(AccessibilityWindowInfo.obtain((AccessibilityWindowInfo) fVar.f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static f a(Object obj) {
        if (obj != null) {
            return new f(obj);
        }
        return null;
    }

    private static String b(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "<UNKNOWN>" : "TYPE_ACCESSIBILITY_OVERLAY" : "TYPE_SYSTEM" : "TYPE_INPUT_METHOD" : "TYPE_APPLICATION";
    }

    public static f l() {
        if (Build.VERSION.SDK_INT >= 21) {
            return a(AccessibilityWindowInfo.obtain());
        }
        return null;
    }

    public int a() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.f).getType();
        }
        return -1;
    }

    public f a(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return a(((AccessibilityWindowInfo) this.f).getChild(i));
        }
        return null;
    }

    public void a(Rect rect) {
        if (Build.VERSION.SDK_INT >= 21) {
            ((AccessibilityWindowInfo) this.f).getBoundsInScreen(rect);
        }
    }

    public int b() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.f).getLayer();
        }
        return -1;
    }

    public c c() {
        if (Build.VERSION.SDK_INT >= 21) {
            return c.a((Object) ((AccessibilityWindowInfo) this.f).getRoot());
        }
        return null;
    }

    public f d() {
        if (Build.VERSION.SDK_INT >= 21) {
            return a(((AccessibilityWindowInfo) this.f).getParent());
        }
        return null;
    }

    public int e() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.f).getId();
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            f fVar = (f) obj;
            Object obj2 = this.f;
            if (obj2 == null) {
                if (fVar.f != null) {
                    return false;
                }
            } else if (!obj2.equals(fVar.f)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean f() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.f).isActive();
        }
        return true;
    }

    public boolean g() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.f).isFocused();
        }
        return true;
    }

    public boolean h() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.f).isAccessibilityFocused();
        }
        return true;
    }

    public int hashCode() {
        Object obj = this.f;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public int i() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.f).getChildCount();
        }
        return 0;
    }

    public CharSequence j() {
        if (Build.VERSION.SDK_INT >= 24) {
            return ((AccessibilityWindowInfo) this.f).getTitle();
        }
        return null;
    }

    public c k() {
        if (Build.VERSION.SDK_INT >= 24) {
            return c.a((Object) ((AccessibilityWindowInfo) this.f).getAnchor());
        }
        return null;
    }

    public void m() {
        if (Build.VERSION.SDK_INT >= 21) {
            ((AccessibilityWindowInfo) this.f).recycle();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Rect rect = new Rect();
        a(rect);
        sb.append("AccessibilityWindowInfo[");
        sb.append("id=");
        sb.append(e());
        sb.append(", type=");
        sb.append(b(a()));
        sb.append(", layer=");
        sb.append(b());
        sb.append(", bounds=");
        sb.append(rect);
        sb.append(", focused=");
        sb.append(g());
        sb.append(", active=");
        sb.append(f());
        sb.append(", hasParent=");
        sb.append(d() != null);
        sb.append(", hasChildren=");
        sb.append(i() > 0);
        sb.append(']');
        return sb.toString();
    }
}
