package android.support.v4.b.a;

import android.database.sqlite.SQLiteCursor;
import android.os.Build;
import android.support.annotation.af;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    private a() {
    }

    public static void a(@af SQLiteCursor sQLiteCursor, boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            sQLiteCursor.setFillWindowForwardOnly(z);
        }
    }
}
