package android.support.v4.j;

import android.os.Build;
import android.support.annotation.ag;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class m {
    private m() {
    }

    public static int a(@ag Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static int a(@ag Object... objArr) {
        return Build.VERSION.SDK_INT >= 19 ? Objects.hash(objArr) : Arrays.hashCode(objArr);
    }

    public static boolean a(@ag Object obj, @ag Object obj2) {
        return Build.VERSION.SDK_INT >= 19 ? Objects.equals(obj, obj2) : obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
