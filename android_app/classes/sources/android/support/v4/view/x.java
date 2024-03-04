package android.support.v4.view;

import android.os.Build;
import android.view.ScaleGestureDetector;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class x {
    private x() {
    }

    public static void a(ScaleGestureDetector scaleGestureDetector, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            scaleGestureDetector.setQuickScaleEnabled(z);
        }
    }

    @Deprecated
    public static void a(Object obj, boolean z) {
        a((ScaleGestureDetector) obj, z);
    }

    public static boolean a(ScaleGestureDetector scaleGestureDetector) {
        if (Build.VERSION.SDK_INT >= 19) {
            return scaleGestureDetector.isQuickScaleEnabled();
        }
        return false;
    }

    @Deprecated
    public static boolean a(Object obj) {
        return a((ScaleGestureDetector) obj);
    }
}
