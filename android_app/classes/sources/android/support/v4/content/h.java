package android.support.v4.content;

import android.support.annotation.af;
import android.support.annotation.ag;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class h {
    private h() {
    }

    @ag
    public static String a(@ag String str, @af String[] strArr) {
        if (str == null) {
            return null;
        }
        String[] split = str.split("/");
        for (String str2 : strArr) {
            if (a(split, str2.split("/"))) {
                return str2;
            }
        }
        return null;
    }

    @ag
    public static String a(@ag String[] strArr, @af String str) {
        if (strArr == null) {
            return null;
        }
        String[] split = str.split("/");
        for (String str2 : strArr) {
            if (a(str2.split("/"), split)) {
                return str2;
            }
        }
        return null;
    }

    public static boolean a(@ag String str, @af String str2) {
        if (str == null) {
            return false;
        }
        return a(str.split("/"), str2.split("/"));
    }

    private static boolean a(@af String[] strArr, @af String[] strArr2) {
        if (strArr2.length == 2) {
            if (strArr2[0].isEmpty() || strArr2[1].isEmpty()) {
                throw new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty.");
            }
            if (strArr.length != 2) {
                return false;
            }
            if ("*".equals(strArr2[0]) || strArr2[0].equals(strArr[0])) {
                return "*".equals(strArr2[1]) || strArr2[1].equals(strArr[1]);
            }
            return false;
        }
        throw new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype.");
    }

    @af
    public static String[] b(@ag String[] strArr, @af String str) {
        if (strArr == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        String[] split = str.split("/");
        for (String str2 : strArr) {
            if (a(str2.split("/"), split)) {
                arrayList.add(str2);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
