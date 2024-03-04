package android.support.v4.os;

import android.os.Build;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a {
    private a() {
    }

    @Deprecated
    public static boolean a() {
        return Build.VERSION.SDK_INT >= 24;
    }

    @Deprecated
    public static boolean b() {
        return Build.VERSION.SDK_INT >= 25;
    }

    @Deprecated
    public static boolean c() {
        return Build.VERSION.SDK_INT >= 26;
    }

    @Deprecated
    public static boolean d() {
        return Build.VERSION.SDK_INT >= 27;
    }

    @Deprecated
    public static boolean e() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static boolean f() {
        return Build.VERSION.CODENAME.length() == 1 && Build.VERSION.CODENAME.charAt(0) >= 'Q' && Build.VERSION.CODENAME.charAt(0) <= 'Z';
    }
}
