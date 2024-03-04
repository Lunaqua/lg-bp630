package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.an;
import android.support.v7.view.menu.h;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class v extends h implements SubMenu {
    private h j;
    private k k;

    public v(Context context, h hVar, k kVar) {
        super(context);
        this.j = hVar;
        this.k = kVar;
    }

    @Override // android.support.v7.view.menu.h
    public String a() {
        k kVar = this.k;
        int itemId = kVar != null ? kVar.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.a() + ":" + itemId;
    }

    @Override // android.support.v7.view.menu.h
    public void a(h.a aVar) {
        this.j.a(aVar);
    }

    @Override // android.support.v7.view.menu.h
    public void a(boolean z) {
        this.j.a(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.view.menu.h
    public boolean a(h hVar, MenuItem menuItem) {
        return super.a(hVar, menuItem) || this.j.a(hVar, menuItem);
    }

    @Override // android.support.v7.view.menu.h
    public boolean b() {
        return this.j.b();
    }

    @Override // android.support.v7.view.menu.h
    public boolean c(k kVar) {
        return this.j.c(kVar);
    }

    @Override // android.support.v7.view.menu.h
    public boolean d() {
        return this.j.d();
    }

    @Override // android.support.v7.view.menu.h
    public boolean d(k kVar) {
        return this.j.d(kVar);
    }

    @Override // android.support.v7.view.menu.h
    public boolean e() {
        return this.j.e();
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.k;
    }

    @Override // android.support.v7.view.menu.h
    public h r() {
        return this.j.r();
    }

    @Override // android.support.v7.view.menu.h, android.support.v4.d.a.a, android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.j.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        return (SubMenu) super.f(i);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        return (SubMenu) super.a(drawable);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        return (SubMenu) super.e(i);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        return (SubMenu) super.a(charSequence);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        return (SubMenu) super.a(view);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i) {
        this.k.setIcon(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.k.setIcon(drawable);
        return this;
    }

    @Override // android.support.v7.view.menu.h, android.view.Menu
    public void setQwertyMode(boolean z) {
        this.j.setQwertyMode(z);
    }

    public Menu u() {
        return this.j;
    }
}
