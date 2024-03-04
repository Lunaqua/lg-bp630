package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.an;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.p;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements h.b, android.support.v7.view.menu.q {
    static final int a = 56;
    static final int b = 4;
    private static final String k = "ActionMenuView";
    h.a c;
    d d;
    private android.support.v7.view.menu.h l;
    private Context m;
    private int n;
    private boolean o;
    private ActionMenuPresenter p;
    private p.a q;
    private boolean r;
    private int s;
    private int t;
    private int u;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LayoutParams extends LinearLayoutCompat.LayoutParams {
        @ViewDebug.ExportedProperty
        public boolean a;
        @ViewDebug.ExportedProperty
        public int b;
        @ViewDebug.ExportedProperty
        public int c;
        @ViewDebug.ExportedProperty
        public boolean d;
        @ViewDebug.ExportedProperty
        public boolean e;
        boolean f;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.a = false;
        }

        LayoutParams(int i, int i2, boolean z) {
            super(i, i2);
            this.a = z;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.a = layoutParams.a;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        boolean d();

        boolean e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b implements p.a {
        b() {
        }

        @Override // android.support.v7.view.menu.p.a
        public void a(android.support.v7.view.menu.h hVar, boolean z) {
        }

        @Override // android.support.v7.view.menu.p.a
        public boolean a(android.support.v7.view.menu.h hVar) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class c implements h.a {
        c() {
        }

        @Override // android.support.v7.view.menu.h.a
        public void a(android.support.v7.view.menu.h hVar) {
            if (ActionMenuView.this.c != null) {
                ActionMenuView.this.c.a(hVar);
            }
        }

        @Override // android.support.v7.view.menu.h.a
        public boolean a(android.support.v7.view.menu.h hVar, MenuItem menuItem) {
            return ActionMenuView.this.d != null && ActionMenuView.this.d.a(menuItem);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface d {
        boolean a(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.t = (int) (56.0f * f);
        this.u = (int) (f * 4.0f);
        this.m = context;
        this.n = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(View view, int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3) - i4, View.MeasureSpec.getMode(i3));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z = true;
        boolean z2 = actionMenuItemView != null && actionMenuItemView.b();
        int i5 = 2;
        if (i2 <= 0 || (z2 && i2 < 2)) {
            i5 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i, Integer.MIN_VALUE), makeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i6 = measuredWidth / i;
            if (measuredWidth % i != 0) {
                i6++;
            }
            if (!z2 || i6 >= 2) {
                i5 = i6;
            }
        }
        layoutParams.d = (layoutParams.a || !z2) ? false : false;
        layoutParams.b = i5;
        view.measure(View.MeasureSpec.makeMeasureSpec(i * i5, 1073741824), makeMeasureSpec);
        return i5;
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x023e A[LOOP:5: B:139:0x023e->B:144:0x025d, LOOP_START, PHI: r13 
      PHI: (r13v4 int) = (r13v3 int), (r13v5 int) binds: [B:138:0x023c, B:144:0x025d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0265  */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v18 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(int r30, int r31) {
        /*
            Method dump skipped, instructions count: 621
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionMenuView.c(int, int):void");
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    /* renamed from: a */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    /* renamed from: a */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams != null) {
            LayoutParams layoutParams2 = layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : new LayoutParams(layoutParams);
            if (layoutParams2.h <= 0) {
                layoutParams2.h = 16;
            }
            return layoutParams2;
        }
        return j();
    }

    @Override // android.support.v7.view.menu.q
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(android.support.v7.view.menu.h hVar) {
        this.l = hVar;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(p.a aVar, h.a aVar2) {
        this.q = aVar;
        this.c = aVar2;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public boolean a() {
        return this.o;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    protected boolean a(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof a)) {
            z = false | ((a) childAt).e();
        }
        return (i <= 0 || !(childAt2 instanceof a)) ? z : z | ((a) childAt2).d();
    }

    @Override // android.support.v7.view.menu.h.b
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public boolean a(android.support.v7.view.menu.k kVar) {
        return this.l.a(kVar, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.LinearLayoutCompat
    /* renamed from: b */
    public LayoutParams j() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.h = 16;
        return layoutParams;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public LayoutParams c() {
        LayoutParams j = j();
        j.a = true;
        return j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof LayoutParams);
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public android.support.v7.view.menu.h d() {
        return this.l;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public boolean e() {
        ActionMenuPresenter actionMenuPresenter = this.p;
        return actionMenuPresenter != null && actionMenuPresenter.e();
    }

    public boolean f() {
        ActionMenuPresenter actionMenuPresenter = this.p;
        return actionMenuPresenter != null && actionMenuPresenter.g();
    }

    public boolean g() {
        ActionMenuPresenter actionMenuPresenter = this.p;
        return actionMenuPresenter != null && actionMenuPresenter.j();
    }

    public Menu getMenu() {
        if (this.l == null) {
            Context context = getContext();
            this.l = new android.support.v7.view.menu.h(context);
            this.l.a(new c());
            this.p = new ActionMenuPresenter(context);
            this.p.c(true);
            ActionMenuPresenter actionMenuPresenter = this.p;
            p.a aVar = this.q;
            if (aVar == null) {
                aVar = new b();
            }
            actionMenuPresenter.a(aVar);
            this.l.a(this.p, this.m);
            this.p.a(this);
        }
        return this.l;
    }

    @android.support.annotation.ag
    public Drawable getOverflowIcon() {
        getMenu();
        return this.p.d();
    }

    public int getPopupTheme() {
        return this.n;
    }

    @Override // android.support.v7.view.menu.q
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int getWindowAnimations() {
        return 0;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public boolean h() {
        ActionMenuPresenter actionMenuPresenter = this.p;
        return actionMenuPresenter != null && actionMenuPresenter.k();
    }

    public void i() {
        ActionMenuPresenter actionMenuPresenter = this.p;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.h();
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.p;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.b(false);
            if (this.p.j()) {
                this.p.g();
                this.p.e();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int width;
        int i7;
        if (!this.r) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i8 = (i4 - i2) / 2;
        int dividerWidth = getDividerWidth();
        int i9 = i3 - i;
        int paddingRight = (i9 - getPaddingRight()) - getPaddingLeft();
        boolean a2 = bd.a(this);
        int i10 = paddingRight;
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (a(i13)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a2) {
                        i7 = getPaddingLeft() + layoutParams.leftMargin;
                        width = i7 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - layoutParams.rightMargin;
                        i7 = width - measuredWidth;
                    }
                    int i14 = i8 - (measuredHeight / 2);
                    childAt.layout(i7, i14, width, measuredHeight + i14);
                    i10 -= measuredWidth;
                    i11 = 1;
                } else {
                    i10 -= (childAt.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin;
                    a(i13);
                    i12++;
                }
            }
        }
        if (childCount == 1 && i11 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i15 = (i9 / 2) - (measuredWidth2 / 2);
            int i16 = i8 - (measuredHeight2 / 2);
            childAt2.layout(i15, i16, measuredWidth2 + i15, measuredHeight2 + i16);
            return;
        }
        int i17 = i12 - (i11 ^ 1);
        if (i17 > 0) {
            i6 = i10 / i17;
            i5 = 0;
        } else {
            i5 = 0;
            i6 = 0;
        }
        int max = Math.max(i5, i6);
        if (a2) {
            int width2 = getWidth() - getPaddingRight();
            while (i5 < childCount) {
                View childAt3 = getChildAt(i5);
                LayoutParams layoutParams2 = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !layoutParams2.a) {
                    int i18 = width2 - layoutParams2.rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i19 = i8 - (measuredHeight3 / 2);
                    childAt3.layout(i18 - measuredWidth3, i19, i18, measuredHeight3 + i19);
                    width2 = i18 - ((measuredWidth3 + layoutParams2.leftMargin) + max);
                }
                i5++;
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        while (i5 < childCount) {
            View childAt4 = getChildAt(i5);
            LayoutParams layoutParams3 = (LayoutParams) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !layoutParams3.a) {
                int i20 = paddingLeft + layoutParams3.leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i21 = i8 - (measuredHeight4 / 2);
                childAt4.layout(i20, i21, i20 + measuredWidth4, measuredHeight4 + i21);
                paddingLeft = i20 + measuredWidth4 + layoutParams3.rightMargin + max;
            }
            i5++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i, int i2) {
        android.support.v7.view.menu.h hVar;
        boolean z = this.r;
        this.r = View.MeasureSpec.getMode(i) == 1073741824;
        if (z != this.r) {
            this.s = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.r && (hVar = this.l) != null && size != this.s) {
            this.s = size;
            hVar.c(true);
        }
        int childCount = getChildCount();
        if (this.r && childCount > 0) {
            c(i, i2);
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i3).getLayoutParams();
            layoutParams.rightMargin = 0;
            layoutParams.leftMargin = 0;
        }
        super.onMeasure(i, i2);
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setExpandedActionViewsExclusive(boolean z) {
        this.p.d(z);
    }

    public void setOnMenuItemClickListener(d dVar) {
        this.d = dVar;
    }

    public void setOverflowIcon(@android.support.annotation.ag Drawable drawable) {
        getMenu();
        this.p.a(drawable);
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setOverflowReserved(boolean z) {
        this.o = z;
    }

    public void setPopupTheme(@android.support.annotation.ar int i) {
        if (this.n != i) {
            this.n = i;
            if (i == 0) {
                this.m = getContext();
            } else {
                this.m = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.p = actionMenuPresenter;
        this.p.a(this);
    }
}
