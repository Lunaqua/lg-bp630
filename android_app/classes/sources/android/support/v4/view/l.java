package android.support.v4.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class l {
    private l() {
    }

    @SuppressLint({"NewApi"})
    public static void a(Menu menu, boolean z) {
        if (menu instanceof android.support.v4.d.a.a) {
            ((android.support.v4.d.a.a) menu).setGroupDividerEnabled(z);
        } else if (Build.VERSION.SDK_INT >= 28) {
            menu.setGroupDividerEnabled(z);
        }
    }

    @Deprecated
    public static void a(MenuItem menuItem, int i) {
        menuItem.setShowAsAction(i);
    }
}
