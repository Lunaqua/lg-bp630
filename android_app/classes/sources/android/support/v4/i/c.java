package android.support.v4.i;

import android.os.Build;
import android.support.annotation.ag;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class c {
    private static final String a = "ICUCompat";
    private static Method b;
    private static Method c;

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                c = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
                return;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        try {
            Class<?> cls = Class.forName("libcore.icu.ICU");
            if (cls != null) {
                b = cls.getMethod("getScript", String.class);
                c = cls.getMethod("addLikelySubtags", String.class);
            }
        } catch (Exception e2) {
            b = null;
            c = null;
            Log.w(a, e2);
        }
    }

    private c() {
    }

    private static String a(String str) {
        try {
            if (b != null) {
                return (String) b.invoke(null, str);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.w(a, e);
        }
        return null;
    }

    @ag
    public static String a(Locale locale) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return ((Locale) c.invoke(null, locale)).getScript();
            } catch (IllegalAccessException | InvocationTargetException e) {
                Log.w(a, e);
                return locale.getScript();
            }
        }
        String b2 = b(locale);
        if (b2 != null) {
            return a(b2);
        }
        return null;
    }

    private static String b(Locale locale) {
        String locale2 = locale.toString();
        try {
            if (c != null) {
                return (String) c.invoke(null, locale2);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.w(a, e);
        }
        return locale2;
    }
}
