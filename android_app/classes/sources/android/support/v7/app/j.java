package android.support.v7.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.ag;
import android.support.v4.view.ab;
import android.support.v7.app.ActionBar;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.p;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.aw;
import android.support.v7.widget.p;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SpinnerAdapter;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class j extends ActionBar {
    p i;
    boolean j;
    Window.Callback k;
    private boolean l;
    private boolean m;
    private ArrayList<ActionBar.c> n = new ArrayList<>();
    private final Runnable o = new Runnable() { // from class: android.support.v7.app.j.1
        @Override // java.lang.Runnable
        public void run() {
            j.this.B();
        }
    };
    private final Toolbar.b p = new Toolbar.b() { // from class: android.support.v7.app.j.2
        @Override // android.support.v7.widget.Toolbar.b
        public boolean a(MenuItem menuItem) {
            return j.this.k.onMenuItemSelected(0, menuItem);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public final class a implements p.a {
        private boolean b;

        a() {
        }

        @Override // android.support.v7.view.menu.p.a
        public void a(android.support.v7.view.menu.h hVar, boolean z) {
            if (this.b) {
                return;
            }
            this.b = true;
            j.this.i.q();
            if (j.this.k != null) {
                j.this.k.onPanelClosed(108, hVar);
            }
            this.b = false;
        }

        @Override // android.support.v7.view.menu.p.a
        public boolean a(android.support.v7.view.menu.h hVar) {
            if (j.this.k != null) {
                j.this.k.onMenuOpened(108, hVar);
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public final class b implements h.a {
        b() {
        }

        @Override // android.support.v7.view.menu.h.a
        public void a(android.support.v7.view.menu.h hVar) {
            if (j.this.k != null) {
                if (j.this.i.l()) {
                    j.this.k.onPanelClosed(108, hVar);
                } else if (j.this.k.onPreparePanel(0, null, hVar)) {
                    j.this.k.onMenuOpened(108, hVar);
                }
            }
        }

        @Override // android.support.v7.view.menu.h.a
        public boolean a(android.support.v7.view.menu.h hVar, MenuItem menuItem) {
            return false;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class c extends android.support.v7.view.i {
        public c(Window.Callback callback) {
            super(callback);
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        public View onCreatePanelView(int i) {
            return i == 0 ? new View(j.this.i.b()) : super.onCreatePanelView(i);
        }

        @Override // android.support.v7.view.i, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (onPreparePanel && !j.this.j) {
                j.this.i.p();
                j.this.j = true;
            }
            return onPreparePanel;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        this.i = new aw(toolbar, false);
        this.k = new c(callback);
        this.i.a(this.k);
        toolbar.setOnMenuItemClickListener(this.p);
        this.i.a(charSequence);
    }

    private Menu C() {
        if (!this.l) {
            this.i.a(new a(), new b());
            this.l = true;
        }
        return this.i.A();
    }

    public Window.Callback A() {
        return this.k;
    }

    void B() {
        Menu C = C();
        android.support.v7.view.menu.h hVar = C instanceof android.support.v7.view.menu.h ? (android.support.v7.view.menu.h) C : null;
        if (hVar != null) {
            hVar.i();
        }
        try {
            C.clear();
            if (!this.k.onCreatePanelMenu(0, C) || !this.k.onPreparePanel(0, null, C)) {
                C.clear();
            }
        } finally {
            if (hVar != null) {
                hVar.j();
            }
        }
    }

    @Override // android.support.v7.app.ActionBar
    public int a() {
        return -1;
    }

    @Override // android.support.v7.app.ActionBar
    public void a(float f) {
        ab.m(this.i.a(), f);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(int i) {
        a(LayoutInflater.from(this.i.b()).inflate(i, this.i.a(), false));
    }

    @Override // android.support.v7.app.ActionBar
    public void a(int i, int i2) {
        this.i.c((i & i2) | ((i2 ^ (-1)) & this.i.r()));
    }

    @Override // android.support.v7.app.ActionBar
    public void a(Configuration configuration) {
        super.a(configuration);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(Drawable drawable) {
        this.i.a(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(ActionBar.c cVar) {
        this.n.add(cVar);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(ActionBar.e eVar) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.v7.app.ActionBar
    public void a(ActionBar.e eVar, int i) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.v7.app.ActionBar
    public void a(ActionBar.e eVar, int i, boolean z) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.v7.app.ActionBar
    public void a(ActionBar.e eVar, boolean z) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.v7.app.ActionBar
    public void a(View view) {
        a(view, new ActionBar.LayoutParams(-2, -2));
    }

    @Override // android.support.v7.app.ActionBar
    public void a(View view, ActionBar.LayoutParams layoutParams) {
        if (view != null) {
            view.setLayoutParams(layoutParams);
        }
        this.i.a(view);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(SpinnerAdapter spinnerAdapter, ActionBar.d dVar) {
        this.i.a(spinnerAdapter, new h(dVar));
    }

    @Override // android.support.v7.app.ActionBar
    public void a(CharSequence charSequence) {
        this.i.b(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(boolean z) {
        a(z ? 1 : 0, 1);
    }

    @Override // android.support.v7.app.ActionBar
    public boolean a(int i, KeyEvent keyEvent) {
        Menu C = C();
        if (C != null) {
            C.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
            return C.performShortcut(i, keyEvent, 0);
        }
        return false;
    }

    @Override // android.support.v7.app.ActionBar
    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            u();
        }
        return true;
    }

    @Override // android.support.v7.app.ActionBar
    public int b() {
        return 0;
    }

    @Override // android.support.v7.app.ActionBar
    public void b(int i) {
        this.i.a(i);
    }

    @Override // android.support.v7.app.ActionBar
    public void b(Drawable drawable) {
        this.i.b(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void b(ActionBar.c cVar) {
        this.n.remove(cVar);
    }

    @Override // android.support.v7.app.ActionBar
    public void b(ActionBar.e eVar) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.v7.app.ActionBar
    public void b(CharSequence charSequence) {
        this.i.c(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void b(boolean z) {
        a(z ? 2 : 0, 2);
    }

    @Override // android.support.v7.app.ActionBar
    public View c() {
        return this.i.x();
    }

    @Override // android.support.v7.app.ActionBar
    public void c(int i) {
        this.i.b(i);
    }

    @Override // android.support.v7.app.ActionBar
    public void c(@ag Drawable drawable) {
        this.i.e(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void c(ActionBar.e eVar) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.v7.app.ActionBar
    public void c(CharSequence charSequence) {
        this.i.d(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void c(boolean z) {
        a(z ? 4 : 0, 4);
    }

    @Override // android.support.v7.app.ActionBar
    public CharSequence d() {
        return this.i.e();
    }

    @Override // android.support.v7.app.ActionBar
    public void d(int i) {
        if (this.i.u() != 1) {
            throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
        }
        this.i.e(i);
    }

    @Override // android.support.v7.app.ActionBar
    public void d(Drawable drawable) {
    }

    @Override // android.support.v7.app.ActionBar
    public void d(CharSequence charSequence) {
        this.i.a(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void d(boolean z) {
        a(z ? 8 : 0, 8);
    }

    @Override // android.support.v7.app.ActionBar
    public CharSequence e() {
        return this.i.f();
    }

    @Override // android.support.v7.app.ActionBar
    public void e(int i) {
        android.support.v7.widget.p pVar = this.i;
        pVar.b(i != 0 ? pVar.b().getText(i) : null);
    }

    @Override // android.support.v7.app.ActionBar
    public void e(Drawable drawable) {
    }

    @Override // android.support.v7.app.ActionBar
    public void e(boolean z) {
        a(z ? 16 : 0, 16);
    }

    @Override // android.support.v7.app.ActionBar
    public int f() {
        return 0;
    }

    @Override // android.support.v7.app.ActionBar
    public void f(int i) {
        android.support.v7.widget.p pVar = this.i;
        pVar.c(i != 0 ? pVar.b().getText(i) : null);
    }

    @Override // android.support.v7.app.ActionBar
    public void f(Drawable drawable) {
        this.i.c(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void f(boolean z) {
    }

    @Override // android.support.v7.app.ActionBar
    public int g() {
        return this.i.r();
    }

    @Override // android.support.v7.app.ActionBar
    @SuppressLint({"WrongConstant"})
    public void g(int i) {
        a(i, -1);
    }

    @Override // android.support.v7.app.ActionBar
    public ActionBar.e h() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.v7.app.ActionBar
    public void h(int i) {
        if (i == 2) {
            throw new IllegalArgumentException("Tabs not supported in this configuration");
        }
        this.i.d(i);
    }

    @Override // android.support.v7.app.ActionBar
    public void h(boolean z) {
    }

    @Override // android.support.v7.app.ActionBar
    public void i() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.v7.app.ActionBar
    public void i(int i) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.v7.app.ActionBar
    public void i(boolean z) {
    }

    @Override // android.support.v7.app.ActionBar
    public ActionBar.e j() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.v7.app.ActionBar
    public ActionBar.e j(int i) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.v7.app.ActionBar
    public void j(boolean z) {
        if (z == this.m) {
            return;
        }
        this.m = z;
        int size = this.n.size();
        for (int i = 0; i < size; i++) {
            this.n.get(i).a(z);
        }
    }

    @Override // android.support.v7.app.ActionBar
    public int k() {
        return 0;
    }

    @Override // android.support.v7.app.ActionBar
    public void k(int i) {
        this.i.g(i);
    }

    @Override // android.support.v7.app.ActionBar
    public int l() {
        return this.i.y();
    }

    @Override // android.support.v7.app.ActionBar
    public void l(int i) {
        this.i.h(i);
    }

    @Override // android.support.v7.app.ActionBar
    public void m() {
        this.i.j(0);
    }

    @Override // android.support.v7.app.ActionBar
    public void n() {
        this.i.j(8);
    }

    @Override // android.support.v7.app.ActionBar
    public boolean o() {
        return this.i.z() == 0;
    }

    @Override // android.support.v7.app.ActionBar
    public Context p() {
        return this.i.b();
    }

    @Override // android.support.v7.app.ActionBar
    public boolean q() {
        return super.q();
    }

    @Override // android.support.v7.app.ActionBar
    public float t() {
        return ab.M(this.i.a());
    }

    @Override // android.support.v7.app.ActionBar
    public boolean u() {
        return this.i.n();
    }

    @Override // android.support.v7.app.ActionBar
    public boolean v() {
        return this.i.o();
    }

    @Override // android.support.v7.app.ActionBar
    public boolean w() {
        this.i.a().removeCallbacks(this.o);
        ab.a(this.i.a(), this.o);
        return true;
    }

    @Override // android.support.v7.app.ActionBar
    public boolean x() {
        if (this.i.c()) {
            this.i.d();
            return true;
        }
        return false;
    }

    @Override // android.support.v7.app.ActionBar
    public boolean y() {
        ViewGroup a2 = this.i.a();
        if (a2 == null || a2.hasFocus()) {
            return false;
        }
        a2.requestFocus();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.app.ActionBar
    public void z() {
        this.i.a().removeCallbacks(this.o);
    }
}
