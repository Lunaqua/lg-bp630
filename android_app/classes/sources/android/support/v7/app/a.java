package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.aq;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.b;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a implements DrawerLayout.c {
    boolean a;
    View.OnClickListener b;
    private final InterfaceC0038a c;
    private final DrawerLayout d;
    private android.support.v7.c.a.d e;
    private boolean f;
    private Drawable g;
    private boolean h;
    private final int i;
    private final int j;
    private boolean k;

    /* renamed from: android.support.v7.app.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface InterfaceC0038a {
        Drawable a();

        void a(@aq int i);

        void a(Drawable drawable, @aq int i);

        Context b();

        boolean c();
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        @ag
        InterfaceC0038a a();
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class c implements InterfaceC0038a {
        private final Activity a;
        private b.a b;

        c(Activity activity) {
            this.a = activity;
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public Drawable a() {
            if (Build.VERSION.SDK_INT >= 18) {
                TypedArray obtainStyledAttributes = b().obtainStyledAttributes(null, new int[]{16843531}, 16843470, 0);
                Drawable drawable = obtainStyledAttributes.getDrawable(0);
                obtainStyledAttributes.recycle();
                return drawable;
            }
            return android.support.v7.app.b.a(this.a);
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public void a(int i) {
            if (Build.VERSION.SDK_INT < 18) {
                this.b = android.support.v7.app.b.a(this.b, this.a, i);
                return;
            }
            android.app.ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(i);
            }
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public void a(Drawable drawable, int i) {
            android.app.ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    actionBar.setHomeAsUpIndicator(drawable);
                    actionBar.setHomeActionContentDescription(i);
                    return;
                }
                actionBar.setDisplayShowHomeEnabled(true);
                this.b = android.support.v7.app.b.a(this.b, this.a, drawable, i);
                actionBar.setDisplayShowHomeEnabled(false);
            }
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public Context b() {
            android.app.ActionBar actionBar = this.a.getActionBar();
            return actionBar != null ? actionBar.getThemedContext() : this.a;
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public boolean c() {
            android.app.ActionBar actionBar = this.a.getActionBar();
            return (actionBar == null || (actionBar.getDisplayOptions() & 4) == 0) ? false : true;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class d implements InterfaceC0038a {
        final Toolbar a;
        final Drawable b;
        final CharSequence c;

        d(Toolbar toolbar) {
            this.a = toolbar;
            this.b = toolbar.getNavigationIcon();
            this.c = toolbar.getNavigationContentDescription();
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public Drawable a() {
            return this.b;
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public void a(@aq int i) {
            if (i == 0) {
                this.a.setNavigationContentDescription(this.c);
            } else {
                this.a.setNavigationContentDescription(i);
            }
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public void a(Drawable drawable, @aq int i) {
            this.a.setNavigationIcon(drawable);
            a(i);
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public Context b() {
            return this.a.getContext();
        }

        @Override // android.support.v7.app.a.InterfaceC0038a
        public boolean c() {
            return true;
        }
    }

    public a(Activity activity, DrawerLayout drawerLayout, @aq int i, @aq int i2) {
        this(activity, null, drawerLayout, null, i, i2);
    }

    public a(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, @aq int i, @aq int i2) {
        this(activity, toolbar, drawerLayout, null, i, i2);
    }

    a(Activity activity, Toolbar toolbar, DrawerLayout drawerLayout, android.support.v7.c.a.d dVar, @aq int i, @aq int i2) {
        this.f = true;
        this.a = true;
        this.k = false;
        if (toolbar != null) {
            this.c = new d(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: android.support.v7.app.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (a.this.a) {
                        a.this.b();
                    } else if (a.this.b != null) {
                        a.this.b.onClick(view);
                    }
                }
            });
        } else if (activity instanceof b) {
            this.c = ((b) activity).a();
        } else {
            this.c = new c(activity);
        }
        this.d = drawerLayout;
        this.i = i;
        this.j = i2;
        if (dVar == null) {
            this.e = new android.support.v7.c.a.d(this.c.b());
        } else {
            this.e = dVar;
        }
        this.g = g();
    }

    private void a(float f) {
        android.support.v7.c.a.d dVar;
        boolean z;
        if (f != 1.0f) {
            if (f == 0.0f) {
                dVar = this.e;
                z = false;
            }
            this.e.f(f);
        }
        dVar = this.e;
        z = true;
        dVar.b(z);
        this.e.f(f);
    }

    public void a() {
        a(this.d.g(android.support.v4.view.f.b) ? 1.0f : 0.0f);
        if (this.a) {
            a(this.e, this.d.g(android.support.v4.view.f.b) ? this.j : this.i);
        }
    }

    public void a(int i) {
        a(i != 0 ? this.d.getResources().getDrawable(i) : null);
    }

    public void a(Configuration configuration) {
        if (!this.h) {
            this.g = g();
        }
        a();
    }

    public void a(Drawable drawable) {
        if (drawable == null) {
            this.g = g();
            this.h = false;
        } else {
            this.g = drawable;
            this.h = true;
        }
        if (this.a) {
            return;
        }
        a(this.g, 0);
    }

    void a(Drawable drawable, int i) {
        if (!this.k && !this.c.c()) {
            Log.w("ActionBarDrawerToggle", "DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
            this.k = true;
        }
        this.c.a(drawable, i);
    }

    public void a(@af android.support.v7.c.a.d dVar) {
        this.e = dVar;
        a();
    }

    public void a(View.OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    public void a(boolean z) {
        Drawable drawable;
        int i;
        if (z != this.a) {
            if (z) {
                drawable = this.e;
                i = this.d.g(android.support.v4.view.f.b) ? this.j : this.i;
            } else {
                drawable = this.g;
                i = 0;
            }
            a(drawable, i);
            this.a = z;
        }
    }

    public boolean a(MenuItem menuItem) {
        if (menuItem != null && menuItem.getItemId() == 16908332 && this.a) {
            b();
            return true;
        }
        return false;
    }

    void b() {
        int a = this.d.a(android.support.v4.view.f.b);
        if (this.d.h(android.support.v4.view.f.b) && a != 2) {
            this.d.f(android.support.v4.view.f.b);
        } else if (a != 1) {
            this.d.e(android.support.v4.view.f.b);
        }
    }

    void b(int i) {
        this.c.a(i);
    }

    public void b(boolean z) {
        this.f = z;
        if (z) {
            return;
        }
        a(0.0f);
    }

    public boolean c() {
        return this.a;
    }

    @af
    public android.support.v7.c.a.d d() {
        return this.e;
    }

    public boolean e() {
        return this.f;
    }

    public View.OnClickListener f() {
        return this.b;
    }

    Drawable g() {
        return this.c.a();
    }

    @Override // android.support.v4.widget.DrawerLayout.c
    public void onDrawerClosed(View view) {
        a(0.0f);
        if (this.a) {
            b(this.i);
        }
    }

    @Override // android.support.v4.widget.DrawerLayout.c
    public void onDrawerOpened(View view) {
        a(1.0f);
        if (this.a) {
            b(this.j);
        }
    }

    @Override // android.support.v4.widget.DrawerLayout.c
    public void onDrawerSlide(View view, float f) {
        if (this.f) {
            a(Math.min(1.0f, Math.max(0.0f, f)));
        } else {
            a(0.0f);
        }
    }

    @Override // android.support.v4.widget.DrawerLayout.c
    public void onDrawerStateChanged(int i) {
    }
}
