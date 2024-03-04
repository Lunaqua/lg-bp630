package android.support.r.a.a;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.view.inputmethod.EditorInfo;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    public static final int a = 16777216;
    public static final int b = Integer.MIN_VALUE;
    private static final String[] c = new String[0];
    private static final String d = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";

    public static void a(@af EditorInfo editorInfo, @ag String[] strArr) {
        if (Build.VERSION.SDK_INT >= 25) {
            editorInfo.contentMimeTypes = strArr;
            return;
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putStringArray(d, strArr);
    }

    @af
    public static String[] a(EditorInfo editorInfo) {
        String[] stringArray;
        if (Build.VERSION.SDK_INT < 25) {
            return (editorInfo.extras == null || (stringArray = editorInfo.extras.getStringArray(d)) == null) ? c : stringArray;
        }
        String[] strArr = editorInfo.contentMimeTypes;
        return strArr != null ? strArr : c;
    }
}
