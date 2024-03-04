package android.support.v4.os;

import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class d {
    public static final String a = "unknown";
    private static final String b = "EnvironmentCompat";

    private d() {
    }

    public static String a(File file) {
        if (Build.VERSION.SDK_INT >= 19) {
            return Environment.getStorageState(file);
        }
        try {
            return file.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath()) ? Environment.getExternalStorageState() : a;
        } catch (IOException e) {
            Log.w(b, "Failed to resolve canonical path: " + e);
            return a;
        }
    }
}
