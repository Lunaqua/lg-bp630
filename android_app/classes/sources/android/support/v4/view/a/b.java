package android.support.v4.view.a;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ak;
import android.view.accessibility.AccessibilityManager;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class b {

    @Deprecated
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        @Deprecated
        void a(boolean z);
    }

    @Deprecated
    /* renamed from: android.support.v4.view.a.b$b  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class AbstractC0031b implements a {
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class c implements AccessibilityManager.AccessibilityStateChangeListener {
        a a;

        c(@af a aVar) {
            this.a = aVar;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.a.equals(((c) obj).a);
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public void onAccessibilityStateChanged(boolean z) {
            this.a.a(z);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface d {
        void a(boolean z);
    }

    @ak(a = 19)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class e implements AccessibilityManager.TouchExplorationStateChangeListener {
        final d a;

        e(@af d dVar) {
            this.a = dVar;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.a.equals(((e) obj).a);
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            this.a.a(z);
        }
    }

    private b() {
    }

    @Deprecated
    public static List<AccessibilityServiceInfo> a(AccessibilityManager accessibilityManager) {
        return accessibilityManager.getInstalledAccessibilityServiceList();
    }

    @Deprecated
    public static List<AccessibilityServiceInfo> a(AccessibilityManager accessibilityManager, int i) {
        return accessibilityManager.getEnabledAccessibilityServiceList(i);
    }

    @Deprecated
    public static boolean a(AccessibilityManager accessibilityManager, a aVar) {
        if (aVar == null) {
            return false;
        }
        return accessibilityManager.addAccessibilityStateChangeListener(new c(aVar));
    }

    public static boolean a(AccessibilityManager accessibilityManager, d dVar) {
        if (Build.VERSION.SDK_INT < 19 || dVar == null) {
            return false;
        }
        return accessibilityManager.addTouchExplorationStateChangeListener(new e(dVar));
    }

    @Deprecated
    public static boolean b(AccessibilityManager accessibilityManager) {
        return accessibilityManager.isTouchExplorationEnabled();
    }

    @Deprecated
    public static boolean b(AccessibilityManager accessibilityManager, a aVar) {
        if (aVar == null) {
            return false;
        }
        return accessibilityManager.removeAccessibilityStateChangeListener(new c(aVar));
    }

    public static boolean b(AccessibilityManager accessibilityManager, d dVar) {
        if (Build.VERSION.SDK_INT < 19 || dVar == null) {
            return false;
        }
        return accessibilityManager.removeTouchExplorationStateChangeListener(new e(dVar));
    }
}
