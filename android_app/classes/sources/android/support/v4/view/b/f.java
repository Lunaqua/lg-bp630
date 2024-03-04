package android.support.v4.view.b;

import android.graphics.Path;
import android.os.Build;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class f {
    private f() {
    }

    public static Interpolator a(float f, float f2) {
        return Build.VERSION.SDK_INT >= 21 ? new PathInterpolator(f, f2) : new e(f, f2);
    }

    public static Interpolator a(float f, float f2, float f3, float f4) {
        return Build.VERSION.SDK_INT >= 21 ? new PathInterpolator(f, f2, f3, f4) : new e(f, f2, f3, f4);
    }

    public static Interpolator a(Path path) {
        return Build.VERSION.SDK_INT >= 21 ? new PathInterpolator(path) : new e(path);
    }
}
