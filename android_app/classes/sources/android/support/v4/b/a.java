package android.support.v4.b;

import android.database.CursorWindow;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    private a() {
    }

    @af
    public static CursorWindow a(@ag String str, long j) {
        return Build.VERSION.SDK_INT >= 28 ? new CursorWindow(str, j) : Build.VERSION.SDK_INT >= 15 ? new CursorWindow(str) : new CursorWindow(false);
    }
}
