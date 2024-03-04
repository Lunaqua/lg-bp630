package android.support.v7.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
abstract class c<T> extends d<T> {
    final Context a;
    private Map<android.support.v4.d.a.b, MenuItem> c;
    private Map<android.support.v4.d.a.c, SubMenu> d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context, T t) {
        super(t);
        this.a = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final MenuItem a(MenuItem menuItem) {
        if (menuItem instanceof android.support.v4.d.a.b) {
            android.support.v4.d.a.b bVar = (android.support.v4.d.a.b) menuItem;
            if (this.c == null) {
                this.c = new android.support.v4.j.a();
            }
            MenuItem menuItem2 = this.c.get(menuItem);
            if (menuItem2 == null) {
                MenuItem a = r.a(this.a, bVar);
                this.c.put(bVar, a);
                return a;
            }
            return menuItem2;
        }
        return menuItem;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SubMenu a(SubMenu subMenu) {
        if (subMenu instanceof android.support.v4.d.a.c) {
            android.support.v4.d.a.c cVar = (android.support.v4.d.a.c) subMenu;
            if (this.d == null) {
                this.d = new android.support.v4.j.a();
            }
            SubMenu subMenu2 = this.d.get(cVar);
            if (subMenu2 == null) {
                SubMenu a = r.a(this.a, cVar);
                this.d.put(cVar, a);
                return a;
            }
            return subMenu2;
        }
        return subMenu;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        Map<android.support.v4.d.a.b, MenuItem> map = this.c;
        if (map != null) {
            map.clear();
        }
        Map<android.support.v4.d.a.c, SubMenu> map2 = this.d;
        if (map2 != null) {
            map2.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        Map<android.support.v4.d.a.b, MenuItem> map = this.c;
        if (map == null) {
            return;
        }
        Iterator<android.support.v4.d.a.b> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i == it.next().getGroupId()) {
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(int i) {
        Map<android.support.v4.d.a.b, MenuItem> map = this.c;
        if (map == null) {
            return;
        }
        Iterator<android.support.v4.d.a.b> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i == it.next().getItemId()) {
                it.remove();
                return;
            }
        }
    }
}
