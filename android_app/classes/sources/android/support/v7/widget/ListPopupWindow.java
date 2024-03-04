package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.support.v7.widget.ActivityChooserView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Method;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ListPopupWindow implements android.support.v7.view.menu.t {
    private static final String a = "ListPopupWindow";
    private static final boolean b = false;
    static final int c = 250;
    public static final int i = 0;
    public static final int j = 1;
    public static final int k = -1;
    public static final int l = -2;
    public static final int m = 0;
    public static final int n = 1;
    public static final int o = 2;
    private static Method p;
    private static Method q;
    private static Method r;
    private boolean A;
    private boolean B;
    private boolean C;
    private int D;
    private boolean E;
    private boolean F;
    private View G;
    private int H;
    private DataSetObserver I;
    private View J;
    private Drawable K;
    private AdapterView.OnItemClickListener L;
    private AdapterView.OnItemSelectedListener M;
    private final d N;
    private final c O;
    private final a P;
    private Runnable Q;
    private final Rect R;
    private Rect S;
    private boolean T;
    t d;
    int e;
    final e f;
    final Handler g;
    PopupWindow h;
    private Context s;
    private ListAdapter t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private boolean z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListPopupWindow.this.t();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class b extends DataSetObserver {
        b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (ListPopupWindow.this.e()) {
                ListPopupWindow.this.a();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ListPopupWindow.this.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class c implements AbsListView.OnScrollListener {
        c() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i != 1 || ListPopupWindow.this.u() || ListPopupWindow.this.h.getContentView() == null) {
                return;
            }
            ListPopupWindow.this.g.removeCallbacks(ListPopupWindow.this.f);
            ListPopupWindow.this.f.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class d implements View.OnTouchListener {
        d() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && ListPopupWindow.this.h != null && ListPopupWindow.this.h.isShowing() && x >= 0 && x < ListPopupWindow.this.h.getWidth() && y >= 0 && y < ListPopupWindow.this.h.getHeight()) {
                ListPopupWindow.this.g.postDelayed(ListPopupWindow.this.f, 250L);
                return false;
            } else if (action == 1) {
                ListPopupWindow.this.g.removeCallbacks(ListPopupWindow.this.f);
                return false;
            } else {
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ListPopupWindow.this.d == null || !android.support.v4.view.ab.af(ListPopupWindow.this.d) || ListPopupWindow.this.d.getCount() <= ListPopupWindow.this.d.getChildCount() || ListPopupWindow.this.d.getChildCount() > ListPopupWindow.this.e) {
                return;
            }
            ListPopupWindow.this.h.setInputMethodMode(2);
            ListPopupWindow.this.a();
        }
    }

    static {
        try {
            p = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
        } catch (NoSuchMethodException unused) {
            Log.i(a, "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        try {
            q = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
        } catch (NoSuchMethodException unused2) {
            Log.i(a, "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
            r = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
        } catch (NoSuchMethodException unused3) {
            Log.i(a, "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public ListPopupWindow(@android.support.annotation.af Context context) {
        this(context, null, a.b.listPopupWindowStyle);
    }

    public ListPopupWindow(@android.support.annotation.af Context context, @android.support.annotation.ag AttributeSet attributeSet) {
        this(context, attributeSet, a.b.listPopupWindowStyle);
    }

    public ListPopupWindow(@android.support.annotation.af Context context, @android.support.annotation.ag AttributeSet attributeSet, @android.support.annotation.f int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListPopupWindow(@android.support.annotation.af Context context, @android.support.annotation.ag AttributeSet attributeSet, @android.support.annotation.f int i2, @android.support.annotation.ar int i3) {
        this.u = -2;
        this.v = -2;
        this.y = android.support.v4.view.w.d;
        this.A = true;
        this.D = 0;
        this.E = false;
        this.F = false;
        this.e = ActivityChooserView.a.a;
        this.H = 0;
        this.f = new e();
        this.N = new d();
        this.O = new c();
        this.P = new a();
        this.R = new Rect();
        this.s = context;
        this.g = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.l.ListPopupWindow, i2, i3);
        this.w = obtainStyledAttributes.getDimensionPixelOffset(a.l.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.x = obtainStyledAttributes.getDimensionPixelOffset(a.l.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.x != 0) {
            this.z = true;
        }
        obtainStyledAttributes.recycle();
        this.h = new AppCompatPopupWindow(context, attributeSet, i2, i3);
        this.h.setInputMethodMode(1);
    }

    private int a(View view, int i2, boolean z) {
        Method method = q;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.h, view, Integer.valueOf(i2), Boolean.valueOf(z))).intValue();
            } catch (Exception unused) {
                Log.i(a, "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.h.getMaxAvailableHeight(view, i2);
    }

    private void b() {
        View view = this.G;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.G);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x015c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int c() {
        /*
            Method dump skipped, instructions count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ListPopupWindow.c():int");
    }

    private void e(boolean z) {
        Method method = p;
        if (method != null) {
            try {
                method.invoke(this.h, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i(a, "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    private static boolean o(int i2) {
        return i2 == 66 || i2 == 23;
    }

    @android.support.annotation.af
    t a(Context context, boolean z) {
        return new t(context, z);
    }

    @Override // android.support.v7.view.menu.t
    public void a() {
        int c2 = c();
        boolean u = u();
        android.support.v4.widget.q.a(this.h, this.y);
        boolean z = true;
        if (this.h.isShowing()) {
            if (android.support.v4.view.ab.af(m())) {
                int i2 = this.v;
                if (i2 == -1) {
                    i2 = -1;
                } else if (i2 == -2) {
                    i2 = m().getWidth();
                }
                int i3 = this.u;
                if (i3 == -1) {
                    if (!u) {
                        c2 = -1;
                    }
                    if (u) {
                        this.h.setWidth(this.v == -1 ? -1 : 0);
                        this.h.setHeight(0);
                    } else {
                        this.h.setWidth(this.v == -1 ? -1 : 0);
                        this.h.setHeight(-1);
                    }
                } else if (i3 != -2) {
                    c2 = i3;
                }
                this.h.setOutsideTouchable((this.F || this.E) ? false : false);
                this.h.update(m(), this.w, this.x, i2 < 0 ? -1 : i2, c2 < 0 ? -1 : c2);
                return;
            }
            return;
        }
        int i4 = this.v;
        if (i4 == -1) {
            i4 = -1;
        } else if (i4 == -2) {
            i4 = m().getWidth();
        }
        int i5 = this.u;
        if (i5 == -1) {
            c2 = -1;
        } else if (i5 != -2) {
            c2 = i5;
        }
        this.h.setWidth(i4);
        this.h.setHeight(c2);
        e(true);
        this.h.setOutsideTouchable((this.F || this.E) ? false : true);
        this.h.setTouchInterceptor(this.N);
        if (this.C) {
            android.support.v4.widget.q.a(this.h, this.B);
        }
        Method method = r;
        if (method != null) {
            try {
                method.invoke(this.h, this.S);
            } catch (Exception e2) {
                Log.e(a, "Could not invoke setEpicenterBounds on PopupWindow", e2);
            }
        }
        android.support.v4.widget.q.a(this.h, m(), this.w, this.x, this.D);
        this.d.setSelection(-1);
        if (!this.T || this.d.isInTouchMode()) {
            t();
        }
        if (this.T) {
            return;
        }
        this.g.post(this.P);
    }

    public void a(int i2) {
        this.H = i2;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(Rect rect) {
        this.S = rect;
    }

    public void a(Drawable drawable) {
        this.K = drawable;
    }

    public void a(@android.support.annotation.ag AdapterView.OnItemClickListener onItemClickListener) {
        this.L = onItemClickListener;
    }

    public void a(@android.support.annotation.ag AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.M = onItemSelectedListener;
    }

    public void a(@android.support.annotation.ag ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.I;
        if (dataSetObserver == null) {
            this.I = new b();
        } else {
            ListAdapter listAdapter2 = this.t;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.t = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.I);
        }
        t tVar = this.d;
        if (tVar != null) {
            tVar.setAdapter(this.t);
        }
    }

    public void a(@android.support.annotation.ag PopupWindow.OnDismissListener onDismissListener) {
        this.h.setOnDismissListener(onDismissListener);
    }

    public void a(boolean z) {
        this.T = z;
        this.h.setFocusable(z);
    }

    public boolean a(int i2, @android.support.annotation.af KeyEvent keyEvent) {
        int i3;
        if (e() && i2 != 62 && (this.d.getSelectedItemPosition() >= 0 || !o(i2))) {
            int selectedItemPosition = this.d.getSelectedItemPosition();
            boolean z = !this.h.isAboveAnchor();
            ListAdapter listAdapter = this.t;
            int i4 = ActivityChooserView.a.a;
            if (listAdapter != null) {
                boolean areAllItemsEnabled = listAdapter.areAllItemsEnabled();
                int a2 = areAllItemsEnabled ? 0 : this.d.a(0, true);
                i3 = areAllItemsEnabled ? listAdapter.getCount() - 1 : this.d.a(listAdapter.getCount() - 1, false);
                i4 = a2;
            } else {
                i3 = Integer.MIN_VALUE;
            }
            if ((z && i2 == 19 && selectedItemPosition <= i4) || (!z && i2 == 20 && selectedItemPosition >= i3)) {
                t();
                this.h.setInputMethodMode(1);
                a();
                return true;
            }
            this.d.setListSelectionHidden(false);
            if (this.d.onKeyDown(i2, keyEvent)) {
                this.h.setInputMethodMode(2);
                this.d.requestFocusFromTouch();
                a();
                if (i2 == 19 || i2 == 20 || i2 == 23 || i2 == 66) {
                    return true;
                }
            } else if (z && i2 == 20) {
                if (selectedItemPosition == i3) {
                    return true;
                }
            } else if (!z && i2 == 19 && selectedItemPosition == i4) {
                return true;
            }
        }
        return false;
    }

    public void b(int i2) {
        this.h.setSoftInputMode(i2);
    }

    public void b(@android.support.annotation.ag Drawable drawable) {
        this.h.setBackgroundDrawable(drawable);
    }

    public void b(@android.support.annotation.ag View view) {
        this.J = view;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void b(boolean z) {
        this.F = z;
    }

    public boolean b(int i2, @android.support.annotation.af KeyEvent keyEvent) {
        if (!e() || this.d.getSelectedItemPosition() < 0) {
            return false;
        }
        boolean onKeyUp = this.d.onKeyUp(i2, keyEvent);
        if (onKeyUp && o(i2)) {
            d();
        }
        return onKeyUp;
    }

    public void c(@android.support.annotation.ar int i2) {
        this.h.setAnimationStyle(i2);
    }

    public void c(@android.support.annotation.ag View view) {
        boolean e2 = e();
        if (e2) {
            b();
        }
        this.G = view;
        if (e2) {
            a();
        }
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void c(boolean z) {
        this.E = z;
    }

    public boolean c(int i2, @android.support.annotation.af KeyEvent keyEvent) {
        if (i2 == 4 && e()) {
            View view = this.J;
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState = view.getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.startTracking(keyEvent, this);
                }
                return true;
            } else if (keyEvent.getAction() == 1) {
                KeyEvent.DispatcherState keyDispatcherState2 = view.getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.handleUpEvent(keyEvent);
                }
                if (!keyEvent.isTracking() || keyEvent.isCanceled()) {
                    return false;
                }
                d();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public View.OnTouchListener d(View view) {
        return new w(view) { // from class: android.support.v7.widget.ListPopupWindow.1
            @Override // android.support.v7.widget.w
            /* renamed from: e */
            public ListPopupWindow a() {
                return ListPopupWindow.this;
            }
        };
    }

    @Override // android.support.v7.view.menu.t
    public void d() {
        this.h.dismiss();
        b();
        this.h.setContentView(null);
        this.d = null;
        this.g.removeCallbacks(this.f);
    }

    public void d(int i2) {
        this.w = i2;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void d(boolean z) {
        this.C = true;
        this.B = z;
    }

    public void e(int i2) {
        this.x = i2;
        this.z = true;
    }

    @Override // android.support.v7.view.menu.t
    public boolean e() {
        return this.h.isShowing();
    }

    public int f() {
        return this.H;
    }

    public void f(int i2) {
        this.D = i2;
    }

    @Override // android.support.v7.view.menu.t
    @android.support.annotation.ag
    public ListView g() {
        return this.d;
    }

    public void g(int i2) {
        this.v = i2;
    }

    public void h(int i2) {
        Drawable background = this.h.getBackground();
        if (background == null) {
            g(i2);
            return;
        }
        background.getPadding(this.R);
        this.v = this.R.left + this.R.right + i2;
    }

    public boolean h() {
        return this.T;
    }

    public void i(int i2) {
        if (i2 < 0 && -2 != i2 && -1 != i2) {
            throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
        }
        this.u = i2;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public boolean i() {
        return this.E;
    }

    public int j() {
        return this.h.getSoftInputMode();
    }

    public void j(int i2) {
        this.y = i2;
    }

    @android.support.annotation.ag
    public Drawable k() {
        return this.h.getBackground();
    }

    public void k(int i2) {
        this.h.setInputMethodMode(i2);
    }

    @android.support.annotation.ar
    public int l() {
        return this.h.getAnimationStyle();
    }

    public void l(int i2) {
        t tVar = this.d;
        if (!e() || tVar == null) {
            return;
        }
        tVar.setListSelectionHidden(false);
        tVar.setSelection(i2);
        if (tVar.getChoiceMode() != 0) {
            tVar.setItemChecked(i2, true);
        }
    }

    @android.support.annotation.ag
    public View m() {
        return this.J;
    }

    public boolean m(int i2) {
        if (e()) {
            if (this.L != null) {
                t tVar = this.d;
                this.L.onItemClick(tVar, tVar.getChildAt(i2 - tVar.getFirstVisiblePosition()), i2, tVar.getAdapter().getItemId(i2));
                return true;
            }
            return true;
        }
        return false;
    }

    public int n() {
        return this.w;
    }

    void n(int i2) {
        this.e = i2;
    }

    public int o() {
        if (this.z) {
            return this.x;
        }
        return 0;
    }

    public int p() {
        return this.v;
    }

    public int q() {
        return this.u;
    }

    public void r() {
        this.g.post(this.Q);
    }

    public int s() {
        return this.h.getInputMethodMode();
    }

    public void t() {
        t tVar = this.d;
        if (tVar != null) {
            tVar.setListSelectionHidden(true);
            tVar.requestLayout();
        }
    }

    public boolean u() {
        return this.h.getInputMethodMode() == 2;
    }

    @android.support.annotation.ag
    public Object v() {
        if (e()) {
            return this.d.getSelectedItem();
        }
        return null;
    }

    public int w() {
        if (e()) {
            return this.d.getSelectedItemPosition();
        }
        return -1;
    }

    public long x() {
        if (e()) {
            return this.d.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    @android.support.annotation.ag
    public View y() {
        if (e()) {
            return this.d.getSelectedView();
        }
        return null;
    }
}
