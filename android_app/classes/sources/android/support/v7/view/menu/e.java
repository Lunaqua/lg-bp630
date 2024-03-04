package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ar;
import android.support.v7.a.a;
import android.support.v7.view.menu.p;
import android.support.v7.widget.ab;
import android.support.v7.widget.ac;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
final class e extends n implements p, View.OnKeyListener, PopupWindow.OnDismissListener {
    static final int a = 0;
    static final int b = 1;
    static final int c = 200;
    private static final int j = a.i.abc_cascading_menu_item_layout;
    private boolean B;
    private p.a C;
    private PopupWindow.OnDismissListener D;
    final Handler d;
    View g;
    ViewTreeObserver h;
    boolean i;
    private final Context k;
    private final int l;
    private final int m;
    private final int n;
    private final boolean o;
    private View u;
    private boolean w;
    private boolean x;
    private int y;
    private int z;
    private final List<h> p = new ArrayList();
    final List<a> e = new ArrayList();
    final ViewTreeObserver.OnGlobalLayoutListener f = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: android.support.v7.view.menu.e.1
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!e.this.e() || e.this.e.size() <= 0 || e.this.e.get(0).a.h()) {
                return;
            }
            View view = e.this.g;
            if (view == null || !view.isShown()) {
                e.this.d();
                return;
            }
            for (a aVar : e.this.e) {
                aVar.a.a();
            }
        }
    };
    private final View.OnAttachStateChangeListener q = new View.OnAttachStateChangeListener() { // from class: android.support.v7.view.menu.e.2
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (e.this.h != null) {
                if (!e.this.h.isAlive()) {
                    e.this.h = view.getViewTreeObserver();
                }
                e.this.h.removeGlobalOnLayoutListener(e.this.f);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    };
    private final ab r = new ab() { // from class: android.support.v7.view.menu.e.3
        @Override // android.support.v7.widget.ab
        public void a(@af h hVar, @af MenuItem menuItem) {
            e.this.d.removeCallbacksAndMessages(hVar);
        }

        @Override // android.support.v7.widget.ab
        public void b(@af final h hVar, @af final MenuItem menuItem) {
            e.this.d.removeCallbacksAndMessages(null);
            int size = e.this.e.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (hVar == e.this.e.get(i).b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            final a aVar = i2 < e.this.e.size() ? e.this.e.get(i2) : null;
            e.this.d.postAtTime(new Runnable() { // from class: android.support.v7.view.menu.e.3.1
                @Override // java.lang.Runnable
                public void run() {
                    if (aVar != null) {
                        e.this.i = true;
                        aVar.b.b(false);
                        e.this.i = false;
                    }
                    if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                        hVar.a(menuItem, 4);
                    }
                }
            }, hVar, SystemClock.uptimeMillis() + 200);
        }
    };
    private int s = 0;
    private int t = 0;
    private boolean A = false;
    private int v = k();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        public final ac a;
        public final h b;
        public final int c;

        public a(@af ac acVar, @af h hVar, int i) {
            this.a = acVar;
            this.b = hVar;
            this.c = i;
        }

        public ListView a() {
            return this.a.g();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface b {
    }

    public e(@af Context context, @af View view, @android.support.annotation.f int i, @ar int i2, boolean z) {
        this.k = context;
        this.u = view;
        this.m = i;
        this.n = i2;
        this.o = z;
        Resources resources = context.getResources();
        this.l = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(a.e.abc_config_prefDialogWidth));
        this.d = new Handler();
    }

    private MenuItem a(@af h hVar, @af h hVar2) {
        int size = hVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = hVar.getItem(i);
            if (item.hasSubMenu() && hVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    @ag
    private View a(@af a aVar, @af h hVar) {
        g gVar;
        int i;
        int firstVisiblePosition;
        MenuItem a2 = a(aVar.b, hVar);
        if (a2 == null) {
            return null;
        }
        ListView a3 = aVar.a();
        ListAdapter adapter = a3.getAdapter();
        int i2 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            i = headerViewListAdapter.getHeadersCount();
            gVar = (g) headerViewListAdapter.getWrappedAdapter();
        } else {
            gVar = (g) adapter;
            i = 0;
        }
        int count = gVar.getCount();
        while (true) {
            if (i2 >= count) {
                i2 = -1;
                break;
            } else if (a2 == gVar.getItem(i2)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1 && (firstVisiblePosition = (i2 + i) - a3.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < a3.getChildCount()) {
            return a3.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    private void c(@af h hVar) {
        a aVar;
        View view;
        int i;
        int i2;
        int i3;
        LayoutInflater from = LayoutInflater.from(this.k);
        g gVar = new g(hVar, from, this.o, j);
        if (!e() && this.A) {
            gVar.a(true);
        } else if (e()) {
            gVar.a(n.b(hVar));
        }
        int a2 = a(gVar, null, this.k, this.l);
        ac j2 = j();
        j2.a((ListAdapter) gVar);
        j2.h(a2);
        j2.f(this.t);
        if (this.e.size() > 0) {
            List<a> list = this.e;
            aVar = list.get(list.size() - 1);
            view = a(aVar, hVar);
        } else {
            aVar = null;
            view = null;
        }
        if (view != null) {
            j2.e(false);
            j2.a((Object) null);
            int d = d(a2);
            boolean z = d == 1;
            this.v = d;
            if (Build.VERSION.SDK_INT >= 26) {
                j2.b(view);
                i2 = 0;
                i = 0;
            } else {
                int[] iArr = new int[2];
                this.u.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                view.getLocationOnScreen(iArr2);
                if ((this.t & 7) == 5) {
                    iArr[0] = iArr[0] + this.u.getWidth();
                    iArr2[0] = iArr2[0] + view.getWidth();
                }
                i = iArr2[0] - iArr[0];
                i2 = iArr2[1] - iArr[1];
            }
            if ((this.t & 5) == 5) {
                if (!z) {
                    a2 = view.getWidth();
                    i3 = i - a2;
                }
                i3 = i + a2;
            } else {
                if (z) {
                    a2 = view.getWidth();
                    i3 = i + a2;
                }
                i3 = i - a2;
            }
            j2.d(i3);
            j2.d(true);
            j2.e(i2);
        } else {
            if (this.w) {
                j2.d(this.y);
            }
            if (this.x) {
                j2.e(this.z);
            }
            j2.a(i());
        }
        this.e.add(new a(j2, hVar, this.v));
        j2.a();
        ListView g = j2.g();
        g.setOnKeyListener(this);
        if (aVar == null && this.B && hVar.o() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(a.i.abc_popup_menu_header_item_layout, (ViewGroup) g, false);
            frameLayout.setEnabled(false);
            ((TextView) frameLayout.findViewById(16908310)).setText(hVar.o());
            g.addHeaderView(frameLayout, null, false);
            j2.a();
        }
    }

    private int d(int i) {
        List<a> list = this.e;
        ListView a2 = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        a2.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.g.getWindowVisibleDisplayFrame(rect);
        return this.v == 1 ? (iArr[0] + a2.getWidth()) + i > rect.right ? 0 : 1 : iArr[0] - i < 0 ? 1 : 0;
    }

    private int d(@af h hVar) {
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            if (hVar == this.e.get(i).b) {
                return i;
            }
        }
        return -1;
    }

    private ac j() {
        ac acVar = new ac(this.k, null, this.m, this.n);
        acVar.a(this.r);
        acVar.a((AdapterView.OnItemClickListener) this);
        acVar.a((PopupWindow.OnDismissListener) this);
        acVar.b(this.u);
        acVar.f(this.t);
        acVar.a(true);
        acVar.k(2);
        return acVar;
    }

    private int k() {
        return android.support.v4.view.ab.m(this.u) == 1 ? 0 : 1;
    }

    @Override // android.support.v7.view.menu.t
    public void a() {
        if (e()) {
            return;
        }
        for (h hVar : this.p) {
            c(hVar);
        }
        this.p.clear();
        this.g = this.u;
        if (this.g != null) {
            boolean z = this.h == null;
            this.h = this.g.getViewTreeObserver();
            if (z) {
                this.h.addOnGlobalLayoutListener(this.f);
            }
            this.g.addOnAttachStateChangeListener(this.q);
        }
    }

    @Override // android.support.v7.view.menu.n
    public void a(int i) {
        if (this.s != i) {
            this.s = i;
            this.t = android.support.v4.view.f.a(i, android.support.v4.view.ab.m(this.u));
        }
    }

    @Override // android.support.v7.view.menu.p
    public void a(Parcelable parcelable) {
    }

    @Override // android.support.v7.view.menu.n
    public void a(h hVar) {
        hVar.a(this, this.k);
        if (e()) {
            c(hVar);
        } else {
            this.p.add(hVar);
        }
    }

    @Override // android.support.v7.view.menu.p
    public void a(h hVar, boolean z) {
        int d = d(hVar);
        if (d < 0) {
            return;
        }
        int i = d + 1;
        if (i < this.e.size()) {
            this.e.get(i).b.b(false);
        }
        a remove = this.e.remove(d);
        remove.b.b(this);
        if (this.i) {
            remove.a.b((Object) null);
            remove.a.c(0);
        }
        remove.a.d();
        int size = this.e.size();
        this.v = size > 0 ? this.e.get(size - 1).c : k();
        if (size != 0) {
            if (z) {
                this.e.get(0).b.b(false);
                return;
            }
            return;
        }
        d();
        p.a aVar = this.C;
        if (aVar != null) {
            aVar.a(hVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.h;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.h.removeGlobalOnLayoutListener(this.f);
            }
            this.h = null;
        }
        this.g.removeOnAttachStateChangeListener(this.q);
        this.D.onDismiss();
    }

    @Override // android.support.v7.view.menu.p
    public void a(p.a aVar) {
        this.C = aVar;
    }

    @Override // android.support.v7.view.menu.n
    public void a(@af View view) {
        if (this.u != view) {
            this.u = view;
            this.t = android.support.v4.view.f.a(this.s, android.support.v4.view.ab.m(this.u));
        }
    }

    @Override // android.support.v7.view.menu.n
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.D = onDismissListener;
    }

    @Override // android.support.v7.view.menu.n
    public void a(boolean z) {
        this.A = z;
    }

    @Override // android.support.v7.view.menu.p
    public boolean a(v vVar) {
        for (a aVar : this.e) {
            if (vVar == aVar.b) {
                aVar.a().requestFocus();
                return true;
            }
        }
        if (vVar.hasVisibleItems()) {
            a((h) vVar);
            p.a aVar2 = this.C;
            if (aVar2 != null) {
                aVar2.a(vVar);
            }
            return true;
        }
        return false;
    }

    @Override // android.support.v7.view.menu.n
    public void b(int i) {
        this.w = true;
        this.y = i;
    }

    @Override // android.support.v7.view.menu.p
    public void b(boolean z) {
        for (a aVar : this.e) {
            a(aVar.a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.p
    public boolean b() {
        return false;
    }

    @Override // android.support.v7.view.menu.n
    public void c(int i) {
        this.x = true;
        this.z = i;
    }

    @Override // android.support.v7.view.menu.n
    public void c(boolean z) {
        this.B = z;
    }

    @Override // android.support.v7.view.menu.t
    public void d() {
        int size = this.e.size();
        if (size > 0) {
            a[] aVarArr = (a[]) this.e.toArray(new a[size]);
            for (int i = size - 1; i >= 0; i--) {
                a aVar = aVarArr[i];
                if (aVar.a.e()) {
                    aVar.a.d();
                }
            }
        }
    }

    @Override // android.support.v7.view.menu.t
    public boolean e() {
        return this.e.size() > 0 && this.e.get(0).a.e();
    }

    @Override // android.support.v7.view.menu.p
    public Parcelable f() {
        return null;
    }

    @Override // android.support.v7.view.menu.t
    public ListView g() {
        if (this.e.isEmpty()) {
            return null;
        }
        List<a> list = this.e;
        return list.get(list.size() - 1).a();
    }

    @Override // android.support.v7.view.menu.n
    protected boolean h() {
        return false;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        a aVar;
        int size = this.e.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                aVar = null;
                break;
            }
            aVar = this.e.get(i);
            if (!aVar.a.e()) {
                break;
            }
            i++;
        }
        if (aVar != null) {
            aVar.b.b(false);
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
