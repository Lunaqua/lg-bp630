package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.p;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class aw implements p {
    private static final String e = "ToolbarWidgetWrapper";
    private static final int f = 3;
    private static final long g = 200;
    Toolbar a;
    CharSequence b;
    Window.Callback c;
    boolean d;
    private int h;
    private View i;
    private Spinner j;
    private View k;
    private Drawable l;
    private Drawable m;
    private Drawable n;
    private boolean o;
    private CharSequence p;
    private CharSequence q;
    private ActionMenuPresenter r;
    private int s;
    private int t;
    private Drawable u;

    public aw(Toolbar toolbar, boolean z) {
        this(toolbar, z, a.j.abc_action_bar_up_description, a.f.abc_ic_ab_back_material);
    }

    public aw(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.s = 0;
        this.t = 0;
        this.a = toolbar;
        this.b = toolbar.getTitle();
        this.p = toolbar.getSubtitle();
        this.o = this.b != null;
        this.n = toolbar.getNavigationIcon();
        av a = av.a(toolbar.getContext(), null, a.l.ActionBar, a.b.actionBarStyle, 0);
        this.u = a.a(a.l.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence d = a.d(a.l.ActionBar_title);
            if (!TextUtils.isEmpty(d)) {
                b(d);
            }
            CharSequence d2 = a.d(a.l.ActionBar_subtitle);
            if (!TextUtils.isEmpty(d2)) {
                c(d2);
            }
            Drawable a2 = a.a(a.l.ActionBar_logo);
            if (a2 != null) {
                b(a2);
            }
            Drawable a3 = a.a(a.l.ActionBar_icon);
            if (a3 != null) {
                a(a3);
            }
            if (this.n == null && (drawable = this.u) != null) {
                c(drawable);
            }
            c(a.a(a.l.ActionBar_displayOptions, 0));
            int g2 = a.g(a.l.ActionBar_customNavigationLayout, 0);
            if (g2 != 0) {
                a(LayoutInflater.from(this.a.getContext()).inflate(g2, (ViewGroup) this.a, false));
                c(this.h | 16);
            }
            int f2 = a.f(a.l.ActionBar_height, 0);
            if (f2 > 0) {
                ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
                layoutParams.height = f2;
                this.a.setLayoutParams(layoutParams);
            }
            int d3 = a.d(a.l.ActionBar_contentInsetStart, -1);
            int d4 = a.d(a.l.ActionBar_contentInsetEnd, -1);
            if (d3 >= 0 || d4 >= 0) {
                this.a.a(Math.max(d3, 0), Math.max(d4, 0));
            }
            int g3 = a.g(a.l.ActionBar_titleTextStyle, 0);
            if (g3 != 0) {
                Toolbar toolbar2 = this.a;
                toolbar2.a(toolbar2.getContext(), g3);
            }
            int g4 = a.g(a.l.ActionBar_subtitleTextStyle, 0);
            if (g4 != 0) {
                Toolbar toolbar3 = this.a;
                toolbar3.b(toolbar3.getContext(), g4);
            }
            int g5 = a.g(a.l.ActionBar_popupTheme, 0);
            if (g5 != 0) {
                this.a.setPopupTheme(g5);
            }
        } else {
            this.h = B();
        }
        a.e();
        i(i);
        this.q = this.a.getNavigationContentDescription();
        this.a.setNavigationOnClickListener(new View.OnClickListener() { // from class: android.support.v7.widget.aw.1
            final android.support.v7.view.menu.a a;

            {
                this.a = new android.support.v7.view.menu.a(aw.this.a.getContext(), 0, 16908332, 0, 0, aw.this.b);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (aw.this.c == null || !aw.this.d) {
                    return;
                }
                aw.this.c.onMenuItemSelected(0, this.a);
            }
        });
    }

    private int B() {
        if (this.a.getNavigationIcon() != null) {
            this.u = this.a.getNavigationIcon();
            return 15;
        }
        return 11;
    }

    private void C() {
        Drawable drawable;
        int i = this.h;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.m) == null) {
            drawable = this.l;
        }
        this.a.setLogo(drawable);
    }

    private void D() {
        if (this.j == null) {
            this.j = new AppCompatSpinner(b(), null, a.b.actionDropDownStyle);
            this.j.setLayoutParams(new Toolbar.LayoutParams(-2, -2, 8388627));
        }
    }

    private void E() {
        Toolbar toolbar;
        Drawable drawable;
        if ((this.h & 4) != 0) {
            toolbar = this.a;
            drawable = this.n;
            if (drawable == null) {
                drawable = this.u;
            }
        } else {
            toolbar = this.a;
            drawable = null;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void F() {
        if ((this.h & 4) != 0) {
            if (TextUtils.isEmpty(this.q)) {
                this.a.setNavigationContentDescription(this.t);
            } else {
                this.a.setNavigationContentDescription(this.q);
            }
        }
    }

    private void e(CharSequence charSequence) {
        this.b = charSequence;
        if ((this.h & 8) != 0) {
            this.a.setTitle(charSequence);
        }
    }

    @Override // android.support.v7.widget.p
    public Menu A() {
        return this.a.getMenu();
    }

    @Override // android.support.v7.widget.p
    public android.support.v4.view.af a(final int i, long j) {
        return android.support.v4.view.ab.C(this.a).a(i == 0 ? 1.0f : 0.0f).a(j).a(new android.support.v4.view.ah() { // from class: android.support.v7.widget.aw.2
            private boolean c = false;

            @Override // android.support.v4.view.ah, android.support.v4.view.ag
            public void a(View view) {
                aw.this.a.setVisibility(0);
            }

            @Override // android.support.v4.view.ah, android.support.v4.view.ag
            public void b(View view) {
                if (this.c) {
                    return;
                }
                aw.this.a.setVisibility(i);
            }

            @Override // android.support.v4.view.ah, android.support.v4.view.ag
            public void c(View view) {
                this.c = true;
            }
        });
    }

    @Override // android.support.v7.widget.p
    public ViewGroup a() {
        return this.a;
    }

    @Override // android.support.v7.widget.p
    public void a(int i) {
        a(i != 0 ? android.support.v7.b.a.a.b(b(), i) : null);
    }

    @Override // android.support.v7.widget.p
    public void a(Drawable drawable) {
        this.l = drawable;
        C();
    }

    @Override // android.support.v7.widget.p
    public void a(p.a aVar, h.a aVar2) {
        this.a.a(aVar, aVar2);
    }

    @Override // android.support.v7.widget.p
    public void a(al alVar) {
        View view = this.i;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.a;
            if (parent == toolbar) {
                toolbar.removeView(this.i);
            }
        }
        this.i = alVar;
        if (alVar == null || this.s != 2) {
            return;
        }
        this.a.addView(this.i, 0);
        Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) this.i.getLayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.a = 8388691;
        alVar.setAllowCollapse(true);
    }

    @Override // android.support.v7.widget.p
    public void a(SparseArray<Parcelable> sparseArray) {
        this.a.saveHierarchyState(sparseArray);
    }

    @Override // android.support.v7.widget.p
    public void a(Menu menu, p.a aVar) {
        if (this.r == null) {
            this.r = new ActionMenuPresenter(this.a.getContext());
            this.r.a(a.g.action_menu_presenter);
        }
        this.r.a(aVar);
        this.a.a((android.support.v7.view.menu.h) menu, this.r);
    }

    @Override // android.support.v7.widget.p
    public void a(View view) {
        View view2 = this.k;
        if (view2 != null && (this.h & 16) != 0) {
            this.a.removeView(view2);
        }
        this.k = view;
        if (view == null || (this.h & 16) == 0) {
            return;
        }
        this.a.addView(this.k);
    }

    @Override // android.support.v7.widget.p
    public void a(Window.Callback callback) {
        this.c = callback;
    }

    @Override // android.support.v7.widget.p
    public void a(SpinnerAdapter spinnerAdapter, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        D();
        this.j.setAdapter(spinnerAdapter);
        this.j.setOnItemSelectedListener(onItemSelectedListener);
    }

    @Override // android.support.v7.widget.p
    public void a(CharSequence charSequence) {
        if (this.o) {
            return;
        }
        e(charSequence);
    }

    @Override // android.support.v7.widget.p
    public void a(boolean z) {
        this.a.setCollapsible(z);
    }

    @Override // android.support.v7.widget.p
    public Context b() {
        return this.a.getContext();
    }

    @Override // android.support.v7.widget.p
    public void b(int i) {
        b(i != 0 ? android.support.v7.b.a.a.b(b(), i) : null);
    }

    @Override // android.support.v7.widget.p
    public void b(Drawable drawable) {
        this.m = drawable;
        C();
    }

    @Override // android.support.v7.widget.p
    public void b(SparseArray<Parcelable> sparseArray) {
        this.a.restoreHierarchyState(sparseArray);
    }

    @Override // android.support.v7.widget.p
    public void b(CharSequence charSequence) {
        this.o = true;
        e(charSequence);
    }

    @Override // android.support.v7.widget.p
    public void b(boolean z) {
    }

    @Override // android.support.v7.widget.p
    public void c(int i) {
        View view;
        CharSequence charSequence;
        Toolbar toolbar;
        int i2 = this.h ^ i;
        this.h = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    F();
                }
                E();
            }
            if ((i2 & 3) != 0) {
                C();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.a.setTitle(this.b);
                    toolbar = this.a;
                    charSequence = this.p;
                } else {
                    charSequence = null;
                    this.a.setTitle((CharSequence) null);
                    toolbar = this.a;
                }
                toolbar.setSubtitle(charSequence);
            }
            if ((i2 & 16) == 0 || (view = this.k) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.a.addView(view);
            } else {
                this.a.removeView(view);
            }
        }
    }

    @Override // android.support.v7.widget.p
    public void c(Drawable drawable) {
        this.n = drawable;
        E();
    }

    @Override // android.support.v7.widget.p
    public void c(CharSequence charSequence) {
        this.p = charSequence;
        if ((this.h & 8) != 0) {
            this.a.setSubtitle(charSequence);
        }
    }

    @Override // android.support.v7.widget.p
    public boolean c() {
        return this.a.h();
    }

    @Override // android.support.v7.widget.p
    public void d() {
        this.a.i();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    @Override // android.support.v7.widget.p
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(int r5) {
        /*
            r4 = this;
            int r0 = r4.s
            if (r5 == r0) goto L71
            r1 = 2
            r2 = 1
            if (r0 == r2) goto L1a
            if (r0 == r1) goto Lb
            goto L2b
        Lb:
            android.view.View r0 = r4.i
            if (r0 == 0) goto L2b
            android.view.ViewParent r0 = r0.getParent()
            android.support.v7.widget.Toolbar r3 = r4.a
            if (r0 != r3) goto L2b
            android.view.View r0 = r4.i
            goto L28
        L1a:
            android.widget.Spinner r0 = r4.j
            if (r0 == 0) goto L2b
            android.view.ViewParent r0 = r0.getParent()
            android.support.v7.widget.Toolbar r3 = r4.a
            if (r0 != r3) goto L2b
            android.widget.Spinner r0 = r4.j
        L28:
            r3.removeView(r0)
        L2b:
            r4.s = r5
            if (r5 == 0) goto L71
            r0 = 0
            if (r5 == r2) goto L67
            if (r5 != r1) goto L50
            android.view.View r5 = r4.i
            if (r5 == 0) goto L71
            android.support.v7.widget.Toolbar r1 = r4.a
            r1.addView(r5, r0)
            android.view.View r5 = r4.i
            android.view.ViewGroup$LayoutParams r5 = r5.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r5 = (android.support.v7.widget.Toolbar.LayoutParams) r5
            r0 = -2
            r5.width = r0
            r5.height = r0
            r0 = 8388691(0x800053, float:1.175506E-38)
            r5.a = r0
            goto L71
        L50:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid navigation mode "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        L67:
            r4.D()
            android.support.v7.widget.Toolbar r5 = r4.a
            android.widget.Spinner r1 = r4.j
            r5.addView(r1, r0)
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.aw.d(int):void");
    }

    @Override // android.support.v7.widget.p
    public void d(Drawable drawable) {
        if (this.u != drawable) {
            this.u = drawable;
            E();
        }
    }

    @Override // android.support.v7.widget.p
    public void d(CharSequence charSequence) {
        this.q = charSequence;
        F();
    }

    @Override // android.support.v7.widget.p
    public CharSequence e() {
        return this.a.getTitle();
    }

    @Override // android.support.v7.widget.p
    public void e(int i) {
        Spinner spinner = this.j;
        if (spinner == null) {
            throw new IllegalStateException("Can't set dropdown selected position without an adapter");
        }
        spinner.setSelection(i);
    }

    @Override // android.support.v7.widget.p
    public void e(Drawable drawable) {
        android.support.v4.view.ab.a(this.a, drawable);
    }

    @Override // android.support.v7.widget.p
    public CharSequence f() {
        return this.a.getSubtitle();
    }

    @Override // android.support.v7.widget.p
    public void f(int i) {
        android.support.v4.view.af a = a(i, g);
        if (a != null) {
            a.e();
        }
    }

    @Override // android.support.v7.widget.p
    public void g() {
        Log.i(e, "Progress display unsupported");
    }

    @Override // android.support.v7.widget.p
    public void g(int i) {
        c(i != 0 ? android.support.v7.b.a.a.b(b(), i) : null);
    }

    @Override // android.support.v7.widget.p
    public void h() {
        Log.i(e, "Progress display unsupported");
    }

    @Override // android.support.v7.widget.p
    public void h(int i) {
        d(i == 0 ? null : b().getString(i));
    }

    @Override // android.support.v7.widget.p
    public void i(int i) {
        if (i == this.t) {
            return;
        }
        this.t = i;
        if (TextUtils.isEmpty(this.a.getNavigationContentDescription())) {
            h(this.t);
        }
    }

    @Override // android.support.v7.widget.p
    public boolean i() {
        return this.l != null;
    }

    @Override // android.support.v7.widget.p
    public void j(int i) {
        this.a.setVisibility(i);
    }

    @Override // android.support.v7.widget.p
    public boolean j() {
        return this.m != null;
    }

    @Override // android.support.v7.widget.p
    public boolean k() {
        return this.a.a();
    }

    @Override // android.support.v7.widget.p
    public boolean l() {
        return this.a.b();
    }

    @Override // android.support.v7.widget.p
    public boolean m() {
        return this.a.c();
    }

    @Override // android.support.v7.widget.p
    public boolean n() {
        return this.a.d();
    }

    @Override // android.support.v7.widget.p
    public boolean o() {
        return this.a.e();
    }

    @Override // android.support.v7.widget.p
    public void p() {
        this.d = true;
    }

    @Override // android.support.v7.widget.p
    public void q() {
        this.a.f();
    }

    @Override // android.support.v7.widget.p
    public int r() {
        return this.h;
    }

    @Override // android.support.v7.widget.p
    public boolean s() {
        return this.i != null;
    }

    @Override // android.support.v7.widget.p
    public boolean t() {
        return this.a.g();
    }

    @Override // android.support.v7.widget.p
    public int u() {
        return this.s;
    }

    @Override // android.support.v7.widget.p
    public int v() {
        Spinner spinner = this.j;
        if (spinner != null) {
            return spinner.getSelectedItemPosition();
        }
        return 0;
    }

    @Override // android.support.v7.widget.p
    public int w() {
        Spinner spinner = this.j;
        if (spinner != null) {
            return spinner.getCount();
        }
        return 0;
    }

    @Override // android.support.v7.widget.p
    public View x() {
        return this.k;
    }

    @Override // android.support.v7.widget.p
    public int y() {
        return this.a.getHeight();
    }

    @Override // android.support.v7.widget.p
    public int z() {
        return this.a.getVisibility();
    }
}
