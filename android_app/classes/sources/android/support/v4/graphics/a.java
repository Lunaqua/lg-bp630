package android.support.v4.graphics;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.af;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    private a() {
    }

    public static void a(@af Bitmap bitmap, boolean z) {
        if (Build.VERSION.SDK_INT >= 18) {
            bitmap.setHasMipMap(z);
        }
    }

    public static boolean a(@af Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 18) {
            return bitmap.hasMipMap();
        }
        return false;
    }

    public static int b(@af Bitmap bitmap) {
        return Build.VERSION.SDK_INT >= 19 ? bitmap.getAllocationByteCount() : bitmap.getByteCount();
    }
}
