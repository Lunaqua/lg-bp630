package android.support.v4.os;

import android.content.Context;
import android.os.Build;
import android.os.UserManager;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class p {
    private p() {
    }

    public static boolean a(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
        }
        return true;
    }
}
