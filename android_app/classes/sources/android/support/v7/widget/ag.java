package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.support.v7.view.menu.h;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ag {
    final android.support.v7.view.menu.o a;
    b b;
    a c;
    private final Context d;
    private final android.support.v7.view.menu.h e;
    private final View f;
    private View.OnTouchListener g;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void a(ag agVar);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        boolean a(MenuItem menuItem);
    }

    public ag(@android.support.annotation.af Context context, @android.support.annotation.af View view) {
        this(context, view, 0);
    }

    public ag(@android.support.annotation.af Context context, @android.support.annotation.af View view, int i) {
        this(context, view, i, a.b.popupMenuStyle, 0);
    }

    public ag(@android.support.annotation.af Context context, @android.support.annotation.af View view, int i, @android.support.annotation.f int i2, @android.support.annotation.ar int i3) {
        this.d = context;
        this.f = view;
        this.e = new android.support.v7.view.menu.h(context);
        this.e.a(new h.a() { // from class: android.support.v7.widget.ag.1
            @Override // android.support.v7.view.menu.h.a
            public void a(android.support.v7.view.menu.h hVar) {
            }

            @Override // android.support.v7.view.menu.h.a
            public boolean a(android.support.v7.view.menu.h hVar, MenuItem menuItem) {
                if (ag.this.b != null) {
                    return ag.this.b.a(menuItem);
                }
                return false;
            }
        });
        this.a = new android.support.v7.view.menu.o(context, this.e, view, false, i2, i3);
        this.a.a(i);
        this.a.a(new PopupWindow.OnDismissListener() { // from class: android.support.v7.widget.ag.2
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (ag.this.c != null) {
                    ag.this.c.a(ag.this);
                }
            }
        });
    }

    public int a() {
        return this.a.b();
    }

    public void a(int i) {
        this.a.a(i);
    }

    public void a(@android.support.annotation.ag a aVar) {
        this.c = aVar;
    }

    public void a(@android.support.annotation.ag b bVar) {
        this.b = bVar;
    }

    @android.support.annotation.af
    public View.OnTouchListener b() {
        if (this.g == null) {
            this.g = new w(this.f) { // from class: android.support.v7.widget.ag.3
                @Override // android.support.v7.widget.w
                public android.support.v7.view.menu.t a() {
                    return ag.this.a.d();
                }

                @Override // android.support.v7.widget.w
                protected boolean b() {
                    ag.this.e();
                    return true;
                }

                @Override // android.support.v7.widget.w
                protected boolean c() {
                    ag.this.f();
                    return true;
                }
            };
        }
        return this.g;
    }

    public void b(@android.support.annotation.ad int i) {
        d().inflate(i, this.e);
    }

    @android.support.annotation.af
    public Menu c() {
        return this.e;
    }

    @android.support.annotation.af
    public MenuInflater d() {
        return new android.support.v7.view.g(this.d);
    }

    public void e() {
        this.a.c();
    }

    public void f() {
        this.a.a();
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    ListView g() {
        if (this.a.g()) {
            return this.a.h();
        }
        return null;
    }
}
