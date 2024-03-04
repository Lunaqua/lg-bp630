package android.support.v4.os;

import android.os.Build;
import android.os.Trace;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class o {
    private o() {
    }

    public static void a() {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }

    public static void a(String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.beginSection(str);
        }
    }
}
