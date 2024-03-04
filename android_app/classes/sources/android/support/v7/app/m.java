package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.an;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ab;
import android.support.v4.view.af;
import android.support.v4.view.ag;
import android.support.v4.view.ah;
import android.support.v4.view.ai;
import android.support.v7.a.a;
import android.support.v7.app.ActionBar;
import android.support.v7.view.b;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.v;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.al;
import android.support.v7.widget.p;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.SpinnerAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class m extends ActionBar implements ActionBarOverlayLayout.a {
    private static final String B = "WindowDecorActionBar";
    private static final int N = -1;
    private static final long O = 100;
    private static final long P = 200;
    private Context E;
    private Activity F;
    private Dialog G;
    private b I;
    private boolean K;
    private boolean L;
    private boolean Q;
    private boolean S;
    private boolean U;
    Context i;
    ActionBarOverlayLayout j;
    ActionBarContainer k;
    p l;
    ActionBarContextView m;
    View n;
    al o;
    a p;
    android.support.v7.view.b q;
    b.a r;
    boolean t;
    boolean u;
    android.support.v7.view.h v;
    boolean w;
    static final /* synthetic */ boolean A = !m.class.desiredAssertionStatus();
    private static final Interpolator C = new AccelerateInterpolator();
    private static final Interpolator D = new DecelerateInterpolator();
    private ArrayList<b> H = new ArrayList<>();
    private int J = -1;
    private ArrayList<ActionBar.c> M = new ArrayList<>();
    private int R = 0;
    boolean s = true;
    private boolean T = true;
    final ag x = new ah() { // from class: android.support.v7.app.m.1
        @Override // android.support.v4.view.ah, android.support.v4.view.ag
        public void b(View view) {
            if (m.this.s && m.this.n != null) {
                m.this.n.setTranslationY(0.0f);
                m.this.k.setTranslationY(0.0f);
            }
            m.this.k.setVisibility(8);
            m.this.k.setTransitioning(false);
            m mVar = m.this;
            mVar.v = null;
            mVar.A();
            if (m.this.j != null) {
                ab.Q(m.this.j);
            }
        }
    };
    final ag y = new ah() { // from class: android.support.v7.app.m.2
        @Override // android.support.v4.view.ah, android.support.v4.view.ag
        public void b(View view) {
            m mVar = m.this;
            mVar.v = null;
            mVar.k.requestLayout();
        }
    };
    final ai z = new ai() { // from class: android.support.v7.app.m.3
        @Override // android.support.v4.view.ai
        public void a(View view) {
            ((View) m.this.k.getParent()).invalidate();
        }
    };

    @an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a extends android.support.v7.view.b implements h.a {
        private final Context b;
        private final android.support.v7.view.menu.h c;
        private b.a d;
        private WeakReference<View> e;

        public a(Context context, b.a aVar) {
            this.b = context;
            this.d = aVar;
            this.c = new android.support.v7.view.menu.h(context).a(1);
            this.c.a(this);
        }

        @Override // android.support.v7.view.b
        public MenuInflater a() {
            return new android.support.v7.view.g(this.b);
        }

        @Override // android.support.v7.view.b
        public void a(int i) {
            b(m.this.i.getResources().getString(i));
        }

        @Override // android.support.v7.view.menu.h.a
        public void a(android.support.v7.view.menu.h hVar) {
            if (this.d == null) {
                return;
            }
            d();
            m.this.m.a();
        }

        public void a(android.support.v7.view.menu.h hVar, boolean z) {
        }

        @Override // android.support.v7.view.b
        public void a(View view) {
            m.this.m.setCustomView(view);
            this.e = new WeakReference<>(view);
        }

        @Override // android.support.v7.view.b
        public void a(CharSequence charSequence) {
            m.this.m.setSubtitle(charSequence);
        }

        @Override // android.support.v7.view.b
        public void a(boolean z) {
            super.a(z);
            m.this.m.setTitleOptional(z);
        }

        @Override // android.support.v7.view.menu.h.a
        public boolean a(android.support.v7.view.menu.h hVar, MenuItem menuItem) {
            b.a aVar = this.d;
            if (aVar != null) {
                return aVar.a(this, menuItem);
            }
            return false;
        }

        public boolean a(v vVar) {
            if (this.d == null) {
                return false;
            }
            if (vVar.hasVisibleItems()) {
                new o(m.this.p(), vVar).c();
                return true;
            }
            return true;
        }

        @Override // android.support.v7.view.b
        public Menu b() {
            return this.c;
        }

        @Override // android.support.v7.view.b
        public void b(int i) {
            a((CharSequence) m.this.i.getResources().getString(i));
        }

        public void b(v vVar) {
        }

        @Override // android.support.v7.view.b
        public void b(CharSequence charSequence) {
            m.this.m.setTitle(charSequence);
        }

        @Override // android.support.v7.view.b
        public void c() {
            if (m.this.p != this) {
                return;
            }
            if (m.a(m.this.t, m.this.u, false)) {
                this.d.a(this);
            } else {
                m mVar = m.this;
                mVar.q = this;
                mVar.r = this.d;
            }
            this.d = null;
            m.this.n(false);
            m.this.m.i();
            m.this.l.a().sendAccessibilityEvent(32);
            m.this.j.setHideOnContentScrollEnabled(m.this.w);
            m.this.p = null;
        }

        @Override // android.support.v7.view.b
        public void d() {
            if (m.this.p != this) {
                return;
            }
            this.c.i();
            try {
                this.d.b(this, this.c);
            } finally {
                this.c.j();
            }
        }

        public boolean e() {
            this.c.i();
            try {
                return this.d.a(this, this.c);
            } finally {
                this.c.j();
            }
        }

        @Override // android.support.v7.view.b
        public CharSequence f() {
            return m.this.m.getTitle();
        }

        @Override // android.support.v7.view.b
        public CharSequence g() {
            return m.this.m.getSubtitle();
        }

        @Override // android.support.v7.view.b
        public boolean h() {
            return m.this.m.k();
        }

        @Override // android.support.v7.view.b
        public View i() {
            WeakReference<View> weakReference = this.e;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }
    }

    @an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b extends ActionBar.e {
        private ActionBar.f c;
        private Object d;
        private Drawable e;
        private CharSequence f;
        private CharSequence g;
        private int h = -1;
        private View i;

        public b() {
        }

        @Override // android.support.v7.app.ActionBar.e
        public int a() {
            return this.h;
        }

        @Override // android.support.v7.app.ActionBar.e
        public ActionBar.e a(int i) {
            return a(android.support.v7.b.a.a.b(m.this.i, i));
        }

        @Override // android.support.v7.app.ActionBar.e
        public ActionBar.e a(Drawable drawable) {
            this.e = drawable;
            if (this.h >= 0) {
                m.this.o.c(this.h);
            }
            return this;
        }

        @Override // android.support.v7.app.ActionBar.e
        public ActionBar.e a(ActionBar.f fVar) {
            this.c = fVar;
            return this;
        }

        @Override // android.support.v7.app.ActionBar.e
        public ActionBar.e a(View view) {
            this.i = view;
            if (this.h >= 0) {
                m.this.o.c(this.h);
            }
            return this;
        }

        @Override // android.support.v7.app.ActionBar.e
        public ActionBar.e a(CharSequence charSequence) {
            this.f = charSequence;
            if (this.h >= 0) {
                m.this.o.c(this.h);
            }
            return this;
        }

        @Override // android.support.v7.app.ActionBar.e
        public ActionBar.e a(Object obj) {
            this.d = obj;
            return this;
        }

        @Override // android.support.v7.app.ActionBar.e
        public Drawable b() {
            return this.e;
        }

        @Override // android.support.v7.app.ActionBar.e
        public ActionBar.e b(int i) {
            return a(m.this.i.getResources().getText(i));
        }

        @Override // android.support.v7.app.ActionBar.e
        public ActionBar.e b(CharSequence charSequence) {
            this.g = charSequence;
            if (this.h >= 0) {
                m.this.o.c(this.h);
            }
            return this;
        }

        @Override // android.support.v7.app.ActionBar.e
        public ActionBar.e c(int i) {
            return a(LayoutInflater.from(m.this.p()).inflate(i, (ViewGroup) null));
        }

        @Override // android.support.v7.app.ActionBar.e
        public CharSequence c() {
            return this.f;
        }

        @Override // android.support.v7.app.ActionBar.e
        public ActionBar.e d(int i) {
            return b(m.this.i.getResources().getText(i));
        }

        @Override // android.support.v7.app.ActionBar.e
        public View d() {
            return this.i;
        }

        @Override // android.support.v7.app.ActionBar.e
        public Object e() {
            return this.d;
        }

        public void e(int i) {
            this.h = i;
        }

        @Override // android.support.v7.app.ActionBar.e
        public void f() {
            m.this.c(this);
        }

        @Override // android.support.v7.app.ActionBar.e
        public CharSequence g() {
            return this.g;
        }

        public ActionBar.f h() {
            return this.c;
        }
    }

    public m(Activity activity, boolean z) {
        this.F = activity;
        View decorView = activity.getWindow().getDecorView();
        b(decorView);
        if (z) {
            return;
        }
        this.n = decorView.findViewById(16908290);
    }

    public m(Dialog dialog) {
        this.G = dialog;
        b(dialog.getWindow().getDecorView());
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public m(View view) {
        if (!A && !view.isInEditMode()) {
            throw new AssertionError();
        }
        b(view);
    }

    private void H() {
        if (this.o != null) {
            return;
        }
        al alVar = new al(this.i);
        if (this.Q) {
            alVar.setVisibility(0);
            this.l.a(alVar);
        } else {
            if (f() == 2) {
                alVar.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.j;
                if (actionBarOverlayLayout != null) {
                    ab.Q(actionBarOverlayLayout);
                }
            } else {
                alVar.setVisibility(8);
            }
            this.k.setTabContainer(alVar);
        }
        this.o = alVar;
    }

    private void I() {
        if (this.I != null) {
            c((ActionBar.e) null);
        }
        this.H.clear();
        al alVar = this.o;
        if (alVar != null) {
            alVar.a();
        }
        this.J = -1;
    }

    private void J() {
        if (this.S) {
            return;
        }
        this.S = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.j;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        p(false);
    }

    private void K() {
        if (this.S) {
            this.S = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.j;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            p(false);
        }
    }

    private boolean L() {
        return ab.ab(this.k);
    }

    static boolean a(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    private void b(ActionBar.e eVar, int i) {
        b bVar = (b) eVar;
        if (bVar.h() == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
        bVar.e(i);
        this.H.add(i, bVar);
        int size = this.H.size();
        while (true) {
            i++;
            if (i >= size) {
                return;
            }
            this.H.get(i).e(i);
        }
    }

    private void b(View view) {
        this.j = (ActionBarOverlayLayout) view.findViewById(a.g.decor_content_parent);
        ActionBarOverlayLayout actionBarOverlayLayout = this.j;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.l = c(view.findViewById(a.g.action_bar));
        this.m = (ActionBarContextView) view.findViewById(a.g.action_context_bar);
        this.k = (ActionBarContainer) view.findViewById(a.g.action_bar_container);
        p pVar = this.l;
        if (pVar == null || this.m == null || this.k == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.i = pVar.b();
        boolean z = (this.l.r() & 4) != 0;
        if (z) {
            this.K = true;
        }
        android.support.v7.view.a a2 = android.support.v7.view.a.a(this.i);
        f(a2.f() || z);
        o(a2.d());
        TypedArray obtainStyledAttributes = this.i.obtainStyledAttributes(null, a.l.ActionBar, a.b.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(a.l.ActionBar_hideOnContentScroll, false)) {
            g(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(a.l.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            a(dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    private p c(View view) {
        if (view instanceof p) {
            return (p) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != null ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    private void o(boolean z) {
        this.Q = z;
        if (this.Q) {
            this.k.setTabContainer(null);
            this.l.a(this.o);
        } else {
            this.l.a((al) null);
            this.k.setTabContainer(this.o);
        }
        boolean z2 = true;
        boolean z3 = f() == 2;
        al alVar = this.o;
        if (alVar != null) {
            if (z3) {
                alVar.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.j;
                if (actionBarOverlayLayout != null) {
                    ab.Q(actionBarOverlayLayout);
                }
            } else {
                alVar.setVisibility(8);
            }
        }
        this.l.a(!this.Q && z3);
        this.j.setHasNonEmbeddedTabs((this.Q || !z3) ? false : false);
    }

    private void p(boolean z) {
        if (a(this.t, this.u, this.S)) {
            if (this.T) {
                return;
            }
            this.T = true;
            l(z);
        } else if (this.T) {
            this.T = false;
            m(z);
        }
    }

    void A() {
        b.a aVar = this.r;
        if (aVar != null) {
            aVar.a(this.q);
            this.q = null;
            this.r = null;
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void B() {
        if (this.u) {
            this.u = false;
            p(true);
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void C() {
        if (this.u) {
            return;
        }
        this.u = true;
        p(true);
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void D() {
        android.support.v7.view.h hVar = this.v;
        if (hVar != null) {
            hVar.c();
            this.v = null;
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void E() {
    }

    public boolean F() {
        return this.l.i();
    }

    public boolean G() {
        return this.l.j();
    }

    @Override // android.support.v7.app.ActionBar
    public int a() {
        b bVar;
        int u = this.l.u();
        if (u != 1) {
            if (u == 2 && (bVar = this.I) != null) {
                return bVar.a();
            }
            return -1;
        }
        return this.l.v();
    }

    @Override // android.support.v7.app.ActionBar
    public android.support.v7.view.b a(b.a aVar) {
        a aVar2 = this.p;
        if (aVar2 != null) {
            aVar2.c();
        }
        this.j.setHideOnContentScrollEnabled(false);
        this.m.j();
        a aVar3 = new a(this.m.getContext(), aVar);
        if (aVar3.e()) {
            this.p = aVar3;
            aVar3.d();
            this.m.a(aVar3);
            n(true);
            this.m.sendAccessibilityEvent(32);
            return aVar3;
        }
        return null;
    }

    @Override // android.support.v7.app.ActionBar
    public void a(float f) {
        ab.m(this.k, f);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(int i) {
        a(LayoutInflater.from(p()).inflate(i, this.l.a(), false));
    }

    @Override // android.support.v7.app.ActionBar
    public void a(int i, int i2) {
        int r = this.l.r();
        if ((i2 & 4) != 0) {
            this.K = true;
        }
        this.l.c((i & i2) | ((i2 ^ (-1)) & r));
    }

    @Override // android.support.v7.app.ActionBar
    public void a(Configuration configuration) {
        o(android.support.v7.view.a.a(this.i).d());
    }

    @Override // android.support.v7.app.ActionBar
    public void a(Drawable drawable) {
        this.l.a(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(ActionBar.c cVar) {
        this.M.add(cVar);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(ActionBar.e eVar) {
        a(eVar, this.H.isEmpty());
    }

    @Override // android.support.v7.app.ActionBar
    public void a(ActionBar.e eVar, int i) {
        a(eVar, i, this.H.isEmpty());
    }

    @Override // android.support.v7.app.ActionBar
    public void a(ActionBar.e eVar, int i, boolean z) {
        H();
        this.o.a(eVar, i, z);
        b(eVar, i);
        if (z) {
            c(eVar);
        }
    }

    @Override // android.support.v7.app.ActionBar
    public void a(ActionBar.e eVar, boolean z) {
        H();
        this.o.b(eVar, z);
        b(eVar, this.H.size());
        if (z) {
            c(eVar);
        }
    }

    @Override // android.support.v7.app.ActionBar
    public void a(View view) {
        this.l.a(view);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(View view, ActionBar.LayoutParams layoutParams) {
        view.setLayoutParams(layoutParams);
        this.l.a(view);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(SpinnerAdapter spinnerAdapter, ActionBar.d dVar) {
        this.l.a(spinnerAdapter, new h(dVar));
    }

    @Override // android.support.v7.app.ActionBar
    public void a(CharSequence charSequence) {
        this.l.b(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void a(boolean z) {
        a(z ? 1 : 0, 1);
    }

    @Override // android.support.v7.app.ActionBar
    public boolean a(int i, KeyEvent keyEvent) {
        Menu b2;
        a aVar = this.p;
        if (aVar == null || (b2 = aVar.b()) == null) {
            return false;
        }
        b2.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return b2.performShortcut(i, keyEvent, 0);
    }

    @Override // android.support.v7.app.ActionBar
    public int b() {
        int u = this.l.u();
        if (u != 1) {
            if (u != 2) {
                return 0;
            }
            return this.H.size();
        }
        return this.l.w();
    }

    @Override // android.support.v7.app.ActionBar
    public void b(int i) {
        this.l.a(i);
    }

    @Override // android.support.v7.app.ActionBar
    public void b(Drawable drawable) {
        this.l.b(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void b(ActionBar.c cVar) {
        this.M.remove(cVar);
    }

    @Override // android.support.v7.app.ActionBar
    public void b(ActionBar.e eVar) {
        i(eVar.a());
    }

    @Override // android.support.v7.app.ActionBar
    public void b(CharSequence charSequence) {
        this.l.c(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void b(boolean z) {
        a(z ? 2 : 0, 2);
    }

    @Override // android.support.v7.app.ActionBar
    public View c() {
        return this.l.x();
    }

    @Override // android.support.v7.app.ActionBar
    public void c(int i) {
        this.l.b(i);
    }

    @Override // android.support.v7.app.ActionBar
    public void c(Drawable drawable) {
        this.k.setPrimaryBackground(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void c(ActionBar.e eVar) {
        if (f() != 2) {
            this.J = eVar != null ? eVar.a() : -1;
            return;
        }
        FragmentTransaction disallowAddToBackStack = (!(this.F instanceof FragmentActivity) || this.l.a().isInEditMode()) ? null : ((FragmentActivity) this.F).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        b bVar = this.I;
        if (bVar != eVar) {
            this.o.setTabSelected(eVar != null ? eVar.a() : -1);
            b bVar2 = this.I;
            if (bVar2 != null) {
                bVar2.h().b(this.I, disallowAddToBackStack);
            }
            this.I = (b) eVar;
            b bVar3 = this.I;
            if (bVar3 != null) {
                bVar3.h().a(this.I, disallowAddToBackStack);
            }
        } else if (bVar != null) {
            bVar.h().c(this.I, disallowAddToBackStack);
            this.o.b(eVar.a());
        }
        if (disallowAddToBackStack == null || disallowAddToBackStack.isEmpty()) {
            return;
        }
        disallowAddToBackStack.commit();
    }

    @Override // android.support.v7.app.ActionBar
    public void c(CharSequence charSequence) {
        this.l.d(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void c(boolean z) {
        a(z ? 4 : 0, 4);
    }

    @Override // android.support.v7.app.ActionBar
    public CharSequence d() {
        return this.l.e();
    }

    @Override // android.support.v7.app.ActionBar
    public void d(int i) {
        int u = this.l.u();
        if (u == 1) {
            this.l.e(i);
        } else if (u != 2) {
            throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
        } else {
            c(this.H.get(i));
        }
    }

    @Override // android.support.v7.app.ActionBar
    public void d(Drawable drawable) {
        this.k.setStackedBackground(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void d(CharSequence charSequence) {
        this.l.a(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void d(boolean z) {
        a(z ? 8 : 0, 8);
    }

    @Override // android.support.v7.app.ActionBar
    public CharSequence e() {
        return this.l.f();
    }

    @Override // android.support.v7.app.ActionBar
    public void e(int i) {
        a(this.i.getString(i));
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
        return this.l.u();
    }

    @Override // android.support.v7.app.ActionBar
    public void f(int i) {
        b(this.i.getString(i));
    }

    @Override // android.support.v7.app.ActionBar
    public void f(Drawable drawable) {
        this.l.c(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void f(boolean z) {
        this.l.b(z);
    }

    @Override // android.support.v7.app.ActionBar
    public int g() {
        return this.l.r();
    }

    @Override // android.support.v7.app.ActionBar
    public void g(int i) {
        if ((i & 4) != 0) {
            this.K = true;
        }
        this.l.c(i);
    }

    @Override // android.support.v7.app.ActionBar
    public void g(boolean z) {
        if (z && !this.j.a()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.w = z;
        this.j.setHideOnContentScrollEnabled(z);
    }

    @Override // android.support.v7.app.ActionBar
    public ActionBar.e h() {
        return new b();
    }

    @Override // android.support.v7.app.ActionBar
    public void h(int i) {
        ActionBarOverlayLayout actionBarOverlayLayout;
        int u = this.l.u();
        if (u == 2) {
            this.J = a();
            c((ActionBar.e) null);
            this.o.setVisibility(8);
        }
        if (u != i && !this.Q && (actionBarOverlayLayout = this.j) != null) {
            ab.Q(actionBarOverlayLayout);
        }
        this.l.d(i);
        boolean z = false;
        if (i == 2) {
            H();
            this.o.setVisibility(0);
            int i2 = this.J;
            if (i2 != -1) {
                d(i2);
                this.J = -1;
            }
        }
        this.l.a(i == 2 && !this.Q);
        ActionBarOverlayLayout actionBarOverlayLayout2 = this.j;
        if (i == 2 && !this.Q) {
            z = true;
        }
        actionBarOverlayLayout2.setHasNonEmbeddedTabs(z);
    }

    @Override // android.support.v7.app.ActionBar
    public void h(boolean z) {
        if (this.K) {
            return;
        }
        c(z);
    }

    @Override // android.support.v7.app.ActionBar
    public void i() {
        I();
    }

    @Override // android.support.v7.app.ActionBar
    public void i(int i) {
        if (this.o == null) {
            return;
        }
        b bVar = this.I;
        int a2 = bVar != null ? bVar.a() : this.J;
        this.o.d(i);
        b remove = this.H.remove(i);
        if (remove != null) {
            remove.e(-1);
        }
        int size = this.H.size();
        for (int i2 = i; i2 < size; i2++) {
            this.H.get(i2).e(i2);
        }
        if (a2 == i) {
            c(this.H.isEmpty() ? null : this.H.get(Math.max(0, i - 1)));
        }
    }

    @Override // android.support.v7.app.ActionBar
    public void i(boolean z) {
        android.support.v7.view.h hVar;
        this.U = z;
        if (z || (hVar = this.v) == null) {
            return;
        }
        hVar.c();
    }

    @Override // android.support.v7.app.ActionBar
    public ActionBar.e j() {
        return this.I;
    }

    @Override // android.support.v7.app.ActionBar
    public ActionBar.e j(int i) {
        return this.H.get(i);
    }

    @Override // android.support.v7.app.ActionBar
    public void j(boolean z) {
        if (z == this.L) {
            return;
        }
        this.L = z;
        int size = this.M.size();
        for (int i = 0; i < size; i++) {
            this.M.get(i).a(z);
        }
    }

    @Override // android.support.v7.app.ActionBar
    public int k() {
        return this.H.size();
    }

    @Override // android.support.v7.app.ActionBar
    public void k(int i) {
        this.l.g(i);
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void k(boolean z) {
        this.s = z;
    }

    @Override // android.support.v7.app.ActionBar
    public int l() {
        return this.k.getHeight();
    }

    @Override // android.support.v7.app.ActionBar
    public void l(int i) {
        this.l.h(i);
    }

    public void l(boolean z) {
        View view;
        View view2;
        int[] iArr;
        android.support.v7.view.h hVar = this.v;
        if (hVar != null) {
            hVar.c();
        }
        this.k.setVisibility(0);
        if (this.R == 0 && (this.U || z)) {
            this.k.setTranslationY(0.0f);
            float f = -this.k.getHeight();
            if (z) {
                this.k.getLocationInWindow(new int[]{0, 0});
                f -= iArr[1];
            }
            this.k.setTranslationY(f);
            android.support.v7.view.h hVar2 = new android.support.v7.view.h();
            af d = ab.C(this.k).d(0.0f);
            d.a(this.z);
            hVar2.a(d);
            if (this.s && (view2 = this.n) != null) {
                view2.setTranslationY(f);
                hVar2.a(ab.C(this.n).d(0.0f));
            }
            hVar2.a(D);
            hVar2.a(250L);
            hVar2.a(this.y);
            this.v = hVar2;
            hVar2.a();
        } else {
            this.k.setAlpha(1.0f);
            this.k.setTranslationY(0.0f);
            if (this.s && (view = this.n) != null) {
                view.setTranslationY(0.0f);
            }
            this.y.b(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.j;
        if (actionBarOverlayLayout != null) {
            ab.Q(actionBarOverlayLayout);
        }
    }

    @Override // android.support.v7.app.ActionBar
    public void m() {
        if (this.t) {
            this.t = false;
            p(false);
        }
    }

    @Override // android.support.v7.app.ActionBar
    public void m(int i) {
        if (i != 0 && !this.j.a()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
        }
        this.j.setActionBarHideOffset(i);
    }

    public void m(boolean z) {
        View view;
        int[] iArr;
        android.support.v7.view.h hVar = this.v;
        if (hVar != null) {
            hVar.c();
        }
        if (this.R != 0 || (!this.U && !z)) {
            this.x.b(null);
            return;
        }
        this.k.setAlpha(1.0f);
        this.k.setTransitioning(true);
        android.support.v7.view.h hVar2 = new android.support.v7.view.h();
        float f = -this.k.getHeight();
        if (z) {
            this.k.getLocationInWindow(new int[]{0, 0});
            f -= iArr[1];
        }
        af d = ab.C(this.k).d(f);
        d.a(this.z);
        hVar2.a(d);
        if (this.s && (view = this.n) != null) {
            hVar2.a(ab.C(view).d(f));
        }
        hVar2.a(C);
        hVar2.a(250L);
        hVar2.a(this.x);
        this.v = hVar2;
        hVar2.a();
    }

    @Override // android.support.v7.app.ActionBar
    public void n() {
        if (this.t) {
            return;
        }
        this.t = true;
        p(false);
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.a
    public void n(int i) {
        this.R = i;
    }

    public void n(boolean z) {
        af a2;
        af a3;
        if (z) {
            J();
        } else {
            K();
        }
        if (!L()) {
            if (z) {
                this.l.j(4);
                this.m.setVisibility(0);
                return;
            }
            this.l.j(0);
            this.m.setVisibility(8);
            return;
        }
        if (z) {
            a3 = this.l.a(4, O);
            a2 = this.m.a(0, P);
        } else {
            a2 = this.l.a(0, P);
            a3 = this.m.a(8, O);
        }
        android.support.v7.view.h hVar = new android.support.v7.view.h();
        hVar.a(a3, a2);
        hVar.a();
    }

    @Override // android.support.v7.app.ActionBar
    public boolean o() {
        int l = l();
        return this.T && (l == 0 || s() < l);
    }

    @Override // android.support.v7.app.ActionBar
    public Context p() {
        if (this.E == null) {
            TypedValue typedValue = new TypedValue();
            this.i.getTheme().resolveAttribute(a.b.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.E = new ContextThemeWrapper(this.i, i);
            } else {
                this.E = this.i;
            }
        }
        return this.E;
    }

    @Override // android.support.v7.app.ActionBar
    public boolean q() {
        p pVar = this.l;
        return pVar != null && pVar.t();
    }

    @Override // android.support.v7.app.ActionBar
    public boolean r() {
        return this.j.d();
    }

    @Override // android.support.v7.app.ActionBar
    public int s() {
        return this.j.getActionBarHideOffset();
    }

    @Override // android.support.v7.app.ActionBar
    public float t() {
        return ab.M(this.k);
    }

    @Override // android.support.v7.app.ActionBar
    public boolean x() {
        p pVar = this.l;
        if (pVar == null || !pVar.c()) {
            return false;
        }
        this.l.d();
        return true;
    }

    @Override // android.support.v7.app.ActionBar
    public boolean y() {
        ViewGroup a2 = this.l.a();
        if (a2 == null || a2.hasFocus()) {
            return false;
        }
        a2.requestFocus();
        return true;
    }
}
