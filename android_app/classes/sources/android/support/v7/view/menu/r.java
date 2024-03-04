package android.support.v7.view.menu;

import android.content.Context;
import android.os.Build;
import android.support.annotation.an;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class r {
    private r() {
    }

    public static Menu a(Context context, android.support.v4.d.a.a aVar) {
        return new s(context, aVar);
    }

    public static MenuItem a(Context context, android.support.v4.d.a.b bVar) {
        return Build.VERSION.SDK_INT >= 16 ? new m(context, bVar) : new l(context, bVar);
    }

    public static SubMenu a(Context context, android.support.v4.d.a.c cVar) {
        return new w(context, cVar);
    }
}
