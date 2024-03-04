package android.support.v4.view;

import android.content.Context;
import android.support.annotation.an;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class b {
    private static final String a = "ActionProvider(support)";
    private final Context b;
    private a c;
    private InterfaceC0033b d;

    @an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void a(boolean z);
    }

    /* renamed from: android.support.v4.view.b$b  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface InterfaceC0033b {
        void a(boolean z);
    }

    public b(Context context) {
        this.b = context;
    }

    public Context a() {
        return this.b;
    }

    public View a(MenuItem menuItem) {
        return b();
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(InterfaceC0033b interfaceC0033b) {
        if (this.d != null && interfaceC0033b != null) {
            Log.w(a, "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.d = interfaceC0033b;
    }

    public void a(SubMenu subMenu) {
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public void a(boolean z) {
        a aVar = this.c;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    public abstract View b();

    public boolean c() {
        return false;
    }

    public boolean d() {
        return true;
    }

    public void e() {
        if (this.d == null || !c()) {
            return;
        }
        this.d.a(d());
    }

    public boolean f() {
        return false;
    }

    public boolean g() {
        return false;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public void h() {
        this.d = null;
        this.c = null;
    }
}
