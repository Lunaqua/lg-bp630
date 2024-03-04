package android.support.v4.j;

import android.support.annotation.an;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class h {
    private h() {
    }

    public static void a(Object obj, StringBuilder sb) {
        int lastIndexOf;
        String hexString;
        if (obj == null) {
            hexString = "null";
        } else {
            String simpleName = obj.getClass().getSimpleName();
            if ((simpleName == null || simpleName.length() <= 0) && (lastIndexOf = (simpleName = obj.getClass().getName()).lastIndexOf(46)) > 0) {
                simpleName = simpleName.substring(lastIndexOf + 1);
            }
            sb.append(simpleName);
            sb.append('{');
            hexString = Integer.toHexString(System.identityHashCode(obj));
        }
        sb.append(hexString);
    }
}
