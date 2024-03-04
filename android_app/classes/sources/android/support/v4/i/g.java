package android.support.v4.i;

import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.text.TextUtils;
import java.util.Locale;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class g {
    private static final Locale a = new Locale(com.lge.media.launcher.a.d, com.lge.media.launcher.a.d);
    private static final String b = "Arab";
    private static final String c = "Hebr";

    private g() {
    }

    public static int a(@ag Locale locale) {
        if (Build.VERSION.SDK_INT >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(locale);
        }
        if (locale == null || locale.equals(a)) {
            return 0;
        }
        String a2 = c.a(locale);
        return a2 == null ? b(locale) : (a2.equalsIgnoreCase(b) || a2.equalsIgnoreCase(c)) ? 1 : 0;
    }

    @af
    public static String a(@af String str) {
        String str2;
        if (Build.VERSION.SDK_INT >= 17) {
            return TextUtils.htmlEncode(str);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                str2 = "&quot;";
            } else if (charAt == '<') {
                str2 = "&lt;";
            } else if (charAt == '>') {
                str2 = "&gt;";
            } else if (charAt == '&') {
                str2 = "&amp;";
            } else if (charAt != '\'') {
                sb.append(charAt);
            } else {
                str2 = "&#39;";
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    private static int b(@af Locale locale) {
        byte directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        return (directionality == 1 || directionality == 2) ? 1 : 0;
    }
}
