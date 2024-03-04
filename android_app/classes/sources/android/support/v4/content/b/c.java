package android.support.v4.content.b;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.af;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class c {
    private c() {
    }

    public static int a(@af Resources resources) {
        return Build.VERSION.SDK_INT >= 17 ? resources.getConfiguration().densityDpi : resources.getDisplayMetrics().densityDpi;
    }
}
