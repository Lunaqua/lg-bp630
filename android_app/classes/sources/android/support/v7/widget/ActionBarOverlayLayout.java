package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.support.v7.view.menu.p;
import android.support.v7.widget.ActivityChooserView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.OverScroller;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ActionBarOverlayLayout extends ViewGroup implements android.support.v4.view.r, o {
    static final int[] e = {a.b.actionBarSize, 16842841};
    private static final String f = "ActionBarOverlayLayout";
    private static final int z = 600;
    private OverScroller A;
    private final Runnable B;
    private final Runnable C;
    private final android.support.v4.view.t D;
    ActionBarContainer a;
    boolean b;
    ViewPropertyAnimator c;
    final AnimatorListenerAdapter d;
    private int g;
    private int h;
    private ContentFrameLayout i;
    private p j;
    private Drawable k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    private final Rect r;
    private final Rect s;
    private final Rect t;
    private final Rect u;
    private final Rect v;
    private final Rect w;
    private final Rect x;
    private a y;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void B();

        void C();

        void D();

        void E();

        void k(boolean z);

        void n(int i);
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 0;
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.u = new Rect();
        this.v = new Rect();
        this.w = new Rect();
        this.x = new Rect();
        this.d = new AnimatorListenerAdapter() { // from class: android.support.v7.widget.ActionBarOverlayLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.c = null;
                actionBarOverlayLayout.b = false;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.c = null;
                actionBarOverlayLayout.b = false;
            }
        };
        this.B = new Runnable() { // from class: android.support.v7.widget.ActionBarOverlayLayout.2
            @Override // java.lang.Runnable
            public void run() {
                ActionBarOverlayLayout.this.e();
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.c = actionBarOverlayLayout.a.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.d);
            }
        };
        this.C = new Runnable() { // from class: android.support.v7.widget.ActionBarOverlayLayout.3
            @Override // java.lang.Runnable
            public void run() {
                ActionBarOverlayLayout.this.e();
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.c = actionBarOverlayLayout.a.animate().translationY(-ActionBarOverlayLayout.this.a.getHeight()).setListener(ActionBarOverlayLayout.this.d);
            }
        };
        a(context);
        this.D = new android.support.v4.view.t(this);
    }

    private p a(View view) {
        if (view instanceof p) {
            return (p) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    private void a(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(e);
        this.g = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.k = obtainStyledAttributes.getDrawable(1);
        setWillNotDraw(this.k == null);
        obtainStyledAttributes.recycle();
        this.l = context.getApplicationInfo().targetSdkVersion < 19;
        this.A = new OverScroller(context);
    }

    private boolean a(float f2, float f3) {
        this.A.fling(0, 0, 0, (int) f3, 0, 0, Integer.MIN_VALUE, ActivityChooserView.a.a);
        return this.A.getFinalY() > this.a.getHeight();
    }

    private boolean a(View view, Rect rect, boolean z2, boolean z3, boolean z4, boolean z5) {
        boolean z6;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!z2 || layoutParams.leftMargin == rect.left) {
            z6 = false;
        } else {
            layoutParams.leftMargin = rect.left;
            z6 = true;
        }
        if (z3 && layoutParams.topMargin != rect.top) {
            layoutParams.topMargin = rect.top;
            z6 = true;
        }
        if (z5 && layoutParams.rightMargin != rect.right) {
            layoutParams.rightMargin = rect.right;
            z6 = true;
        }
        if (!z4 || layoutParams.bottomMargin == rect.bottom) {
            return z6;
        }
        layoutParams.bottomMargin = rect.bottom;
        return true;
    }

    private void o() {
        e();
        postDelayed(this.B, 600L);
    }

    private void p() {
        e();
        postDelayed(this.C, 600L);
    }

    private void q() {
        e();
        this.B.run();
    }

    private void r() {
        e();
        this.C.run();
    }

    @Override // android.view.ViewGroup
    /* renamed from: a */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.support.v7.widget.o
    public void a(int i) {
        c();
        if (i == 2) {
            this.j.g();
        } else if (i == 5) {
            this.j.h();
        } else if (i != 109) {
        } else {
            setOverlayMode(true);
        }
    }

    @Override // android.support.v7.widget.o
    public void a(SparseArray<Parcelable> sparseArray) {
        c();
        this.j.a(sparseArray);
    }

    @Override // android.support.v7.widget.o
    public void a(Menu menu, p.a aVar) {
        c();
        this.j.a(menu, aVar);
    }

    public boolean a() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: b */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.support.v7.widget.o
    public void b(SparseArray<Parcelable> sparseArray) {
        c();
        this.j.b(sparseArray);
    }

    void c() {
        if (this.i == null) {
            this.i = (ContentFrameLayout) findViewById(a.g.action_bar_activity_content);
            this.a = (ActionBarContainer) findViewById(a.g.action_bar_container);
            this.j = a(findViewById(a.g.action_bar));
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public boolean d() {
        return this.o;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.k == null || this.l) {
            return;
        }
        int bottom = this.a.getVisibility() == 0 ? (int) (this.a.getBottom() + this.a.getTranslationY() + 0.5f) : 0;
        this.k.setBounds(0, bottom, getWidth(), this.k.getIntrinsicHeight() + bottom);
        this.k.draw(canvas);
    }

    void e() {
        removeCallbacks(this.B);
        removeCallbacks(this.C);
        ViewPropertyAnimator viewPropertyAnimator = this.c;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    @Override // android.support.v7.widget.o
    public boolean f() {
        c();
        return this.j.i();
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        c();
        int P = android.support.v4.view.ab.P(this) & 256;
        boolean a2 = a(this.a, rect, true, true, false, true);
        this.u.set(rect);
        bd.a(this, this.u, this.r);
        if (!this.v.equals(this.u)) {
            this.v.set(this.u);
            a2 = true;
        }
        if (!this.s.equals(this.r)) {
            this.s.set(this.r);
            a2 = true;
        }
        if (a2) {
            requestLayout();
        }
        return true;
    }

    @Override // android.support.v7.widget.o
    public boolean g() {
        c();
        return this.j.j();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.a;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup, android.support.v4.view.r
    public int getNestedScrollAxes() {
        return this.D.a();
    }

    @Override // android.support.v7.widget.o
    public CharSequence getTitle() {
        c();
        return this.j.e();
    }

    @Override // android.support.v7.widget.o
    public boolean h() {
        c();
        return this.j.k();
    }

    @Override // android.support.v7.widget.o
    public boolean i() {
        c();
        return this.j.l();
    }

    @Override // android.support.v7.widget.o
    public boolean j() {
        c();
        return this.j.m();
    }

    @Override // android.support.v7.widget.o
    public boolean k() {
        c();
        return this.j.n();
    }

    @Override // android.support.v7.widget.o
    public boolean l() {
        c();
        return this.j.o();
    }

    @Override // android.support.v7.widget.o
    public void m() {
        c();
        this.j.p();
    }

    @Override // android.support.v7.widget.o
    public void n() {
        c();
        this.j.q();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(getContext());
        android.support.v4.view.ab.Q(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        e();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z2, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        getPaddingRight();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = layoutParams.leftMargin + paddingLeft;
                int i7 = layoutParams.topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredHeight;
        Rect rect;
        c();
        measureChildWithMargins(this.a, i, 0, i2, 0);
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        int max = Math.max(0, this.a.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
        int max2 = Math.max(0, this.a.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
        int combineMeasuredStates = View.combineMeasuredStates(0, this.a.getMeasuredState());
        boolean z2 = (android.support.v4.view.ab.P(this) & 256) != 0;
        if (z2) {
            measuredHeight = this.g;
            if (this.n && this.a.getTabContainer() != null) {
                measuredHeight += this.g;
            }
        } else {
            measuredHeight = this.a.getVisibility() != 8 ? this.a.getMeasuredHeight() : 0;
        }
        this.t.set(this.r);
        this.w.set(this.u);
        if (this.m || z2) {
            this.w.top += measuredHeight;
            rect = this.w;
        } else {
            this.t.top += measuredHeight;
            rect = this.t;
        }
        rect.bottom += 0;
        a(this.i, this.t, true, true, true, true);
        if (!this.x.equals(this.w)) {
            this.x.set(this.w);
            this.i.a(this.w);
        }
        measureChildWithMargins(this.i, i, 0, i2, 0);
        LayoutParams layoutParams2 = (LayoutParams) this.i.getLayoutParams();
        int max3 = Math.max(max, this.i.getMeasuredWidth() + layoutParams2.leftMargin + layoutParams2.rightMargin);
        int max4 = Math.max(max2, this.i.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin);
        int combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, this.i.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(max3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, combineMeasuredStates2), View.resolveSizeAndState(Math.max(max4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, combineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        if (this.o && z2) {
            if (a(f2, f3)) {
                r();
            } else {
                q();
            }
            this.b = true;
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        this.p += i2;
        setActionBarHideOffset(this.p);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.D.a(view, view2, i);
        this.p = getActionBarHideOffset();
        e();
        a aVar = this.y;
        if (aVar != null) {
            aVar.D();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.a.getVisibility() != 0) {
            return false;
        }
        return this.o;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onStopNestedScroll(View view) {
        if (this.o && !this.b) {
            if (this.p <= this.a.getHeight()) {
                o();
            } else {
                p();
            }
        }
        a aVar = this.y;
        if (aVar != null) {
            aVar.E();
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i);
        }
        c();
        int i2 = this.q ^ i;
        this.q = i;
        boolean z2 = (i & 4) == 0;
        boolean z3 = (i & 256) != 0;
        a aVar = this.y;
        if (aVar != null) {
            aVar.k(!z3);
            if (z2 || !z3) {
                this.y.B();
            } else {
                this.y.C();
            }
        }
        if ((i2 & 256) == 0 || this.y == null) {
            return;
        }
        android.support.v4.view.ab.Q(this);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.h = i;
        a aVar = this.y;
        if (aVar != null) {
            aVar.n(i);
        }
    }

    public void setActionBarHideOffset(int i) {
        e();
        this.a.setTranslationY(-Math.max(0, Math.min(i, this.a.getHeight())));
    }

    public void setActionBarVisibilityCallback(a aVar) {
        this.y = aVar;
        if (getWindowToken() != null) {
            this.y.n(this.h);
            int i = this.q;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                android.support.v4.view.ab.Q(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z2) {
        this.n = z2;
    }

    public void setHideOnContentScrollEnabled(boolean z2) {
        if (z2 != this.o) {
            this.o = z2;
            if (z2) {
                return;
            }
            e();
            setActionBarHideOffset(0);
        }
    }

    @Override // android.support.v7.widget.o
    public void setIcon(int i) {
        c();
        this.j.a(i);
    }

    @Override // android.support.v7.widget.o
    public void setIcon(Drawable drawable) {
        c();
        this.j.a(drawable);
    }

    @Override // android.support.v7.widget.o
    public void setLogo(int i) {
        c();
        this.j.b(i);
    }

    public void setOverlayMode(boolean z2) {
        this.m = z2;
        this.l = z2 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z2) {
    }

    @Override // android.support.v7.widget.o
    public void setUiOptions(int i) {
    }

    @Override // android.support.v7.widget.o
    public void setWindowCallback(Window.Callback callback) {
        c();
        this.j.a(callback);
    }

    @Override // android.support.v7.widget.o
    public void setWindowTitle(CharSequence charSequence) {
        c();
        this.j.a(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
