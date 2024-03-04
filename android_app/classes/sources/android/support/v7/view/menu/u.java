package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v4.view.ab;
import android.support.v7.a.a;
import android.support.v7.view.menu.p;
import android.support.v7.widget.ac;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
final class u extends n implements p, View.OnKeyListener, AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {
    private static final int e = a.i.abc_popup_menu_item_layout;
    final ac a;
    View c;
    ViewTreeObserver d;
    private final Context f;
    private final h g;
    private final g h;
    private final boolean i;
    private final int j;
    private final int k;
    private final int l;
    private PopupWindow.OnDismissListener n;
    private View o;
    private p.a p;
    private boolean q;
    private boolean r;
    private int s;
    private boolean u;
    final ViewTreeObserver.OnGlobalLayoutListener b = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: android.support.v7.view.menu.u.1
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!u.this.e() || u.this.a.h()) {
                return;
            }
            View view = u.this.c;
            if (view == null || !view.isShown()) {
                u.this.d();
            } else {
                u.this.a.a();
            }
        }
    };
    private final View.OnAttachStateChangeListener m = new View.OnAttachStateChangeListener() { // from class: android.support.v7.view.menu.u.2
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (u.this.d != null) {
                if (!u.this.d.isAlive()) {
                    u.this.d = view.getViewTreeObserver();
                }
                u.this.d.removeGlobalOnLayoutListener(u.this.b);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    };
    private int t = 0;

    public u(Context context, h hVar, View view, int i, int i2, boolean z) {
        this.f = context;
        this.g = hVar;
        this.i = z;
        this.h = new g(hVar, LayoutInflater.from(context), this.i, e);
        this.k = i;
        this.l = i2;
        Resources resources = context.getResources();
        this.j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(a.e.abc_config_prefDialogWidth));
        this.o = view;
        this.a = new ac(this.f, null, this.k, this.l);
        hVar.a(this, context);
    }

    private boolean j() {
        View view;
        if (e()) {
            return true;
        }
        if (this.q || (view = this.o) == null) {
            return false;
        }
        this.c = view;
        this.a.a((PopupWindow.OnDismissListener) this);
        this.a.a((AdapterView.OnItemClickListener) this);
        this.a.a(true);
        View view2 = this.c;
        boolean z = this.d == null;
        this.d = view2.getViewTreeObserver();
        if (z) {
            this.d.addOnGlobalLayoutListener(this.b);
        }
        view2.addOnAttachStateChangeListener(this.m);
        this.a.b(view2);
        this.a.f(this.t);
        if (!this.r) {
            this.s = a(this.h, null, this.f, this.j);
            this.r = true;
        }
        this.a.h(this.s);
        this.a.k(2);
        this.a.a(i());
        this.a.a();
        ListView g = this.a.g();
        g.setOnKeyListener(this);
        if (this.u && this.g.o() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f).inflate(a.i.abc_popup_menu_header_item_layout, (ViewGroup) g, false);
            TextView textView = (TextView) frameLayout.findViewById(16908310);
            if (textView != null) {
                textView.setText(this.g.o());
            }
            frameLayout.setEnabled(false);
            g.addHeaderView(frameLayout, null, false);
        }
        this.a.a((ListAdapter) this.h);
        this.a.a();
        return true;
    }

    @Override // android.support.v7.view.menu.t
    public void a() {
        if (!j()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // android.support.v7.view.menu.n
    public void a(int i) {
        this.t = i;
    }

    @Override // android.support.v7.view.menu.p
    public void a(Parcelable parcelable) {
    }

    @Override // android.support.v7.view.menu.n
    public void a(h hVar) {
    }

    @Override // android.support.v7.view.menu.p
    public void a(h hVar, boolean z) {
        if (hVar != this.g) {
            return;
        }
        d();
        p.a aVar = this.p;
        if (aVar != null) {
            aVar.a(hVar, z);
        }
    }

    @Override // android.support.v7.view.menu.p
    public void a(p.a aVar) {
        this.p = aVar;
    }

    @Override // android.support.v7.view.menu.n
    public void a(View view) {
        this.o = view;
    }

    @Override // android.support.v7.view.menu.n
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.n = onDismissListener;
    }

    @Override // android.support.v7.view.menu.n
    public void a(boolean z) {
        this.h.a(z);
    }

    @Override // android.support.v7.view.menu.p
    public boolean a(v vVar) {
        if (vVar.hasVisibleItems()) {
            o oVar = new o(this.f, vVar, this.c, this.i, this.k, this.l);
            oVar.a(this.p);
            oVar.a(n.b(vVar));
            oVar.a(this.n);
            this.n = null;
            this.g.b(false);
            int n = this.a.n();
            int o = this.a.o();
            if ((Gravity.getAbsoluteGravity(this.t, ab.m(this.o)) & 7) == 5) {
                n += this.o.getWidth();
            }
            if (oVar.b(n, o)) {
                p.a aVar = this.p;
                if (aVar != null) {
                    aVar.a(vVar);
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    @Override // android.support.v7.view.menu.n
    public void b(int i) {
        this.a.d(i);
    }

    @Override // android.support.v7.view.menu.p
    public void b(boolean z) {
        this.r = false;
        g gVar = this.h;
        if (gVar != null) {
            gVar.notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.p
    public boolean b() {
        return false;
    }

    @Override // android.support.v7.view.menu.n
    public void c(int i) {
        this.a.e(i);
    }

    @Override // android.support.v7.view.menu.n
    public void c(boolean z) {
        this.u = z;
    }

    @Override // android.support.v7.view.menu.t
    public void d() {
        if (e()) {
            this.a.d();
        }
    }

    @Override // android.support.v7.view.menu.t
    public boolean e() {
        return !this.q && this.a.e();
    }

    @Override // android.support.v7.view.menu.p
    public Parcelable f() {
        return null;
    }

    @Override // android.support.v7.view.menu.t
    public ListView g() {
        return this.a.g();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.q = true;
        this.g.close();
        ViewTreeObserver viewTreeObserver = this.d;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.d = this.c.getViewTreeObserver();
            }
            this.d.removeGlobalOnLayoutListener(this.b);
            this.d = null;
        }
        this.c.removeOnAttachStateChangeListener(this.m);
        PopupWindow.OnDismissListener onDismissListener = this.n;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && i == 82) {
            d();
            return true;
        }
        return false;
    }
}
