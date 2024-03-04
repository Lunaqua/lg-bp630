package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.an;
import android.support.v4.view.AbsSavedState;
import android.support.v7.a.a;
import android.support.v7.app.ActionBar;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.p;
import android.support.v7.widget.ActionMenuView;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class Toolbar extends ViewGroup {
    private static final String e = "Toolbar";
    private CharSequence A;
    private int B;
    private int C;
    private boolean D;
    private boolean E;
    private final ArrayList<View> F;
    private final ArrayList<View> G;
    private final int[] H;
    private final ActionMenuView.d I;
    private aw J;
    private ActionMenuPresenter K;
    private a L;
    private p.a M;
    private h.a N;
    private boolean O;
    private final Runnable P;
    ImageButton a;
    View b;
    int c;
    b d;
    private ActionMenuView f;
    private TextView g;
    private TextView h;
    private ImageButton i;
    private ImageView j;
    private Drawable k;
    private CharSequence l;
    private Context m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private aj v;
    private int w;
    private int x;
    private int y;
    private CharSequence z;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LayoutParams extends ActionBar.LayoutParams {
        static final int b = 0;
        static final int c = 1;
        static final int d = 2;
        int e;

        public LayoutParams(int i) {
            this(-2, -1, i);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.e = 0;
            this.a = 8388627;
        }

        public LayoutParams(int i, int i2, int i3) {
            super(i, i2);
            this.e = 0;
            this.a = i3;
        }

        public LayoutParams(@android.support.annotation.af Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.e = 0;
        }

        public LayoutParams(ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
            this.e = 0;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ActionBar.LayoutParams) layoutParams);
            this.e = 0;
            this.e = layoutParams.e;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.e = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.e = 0;
            a(marginLayoutParams);
        }

        void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.leftMargin = marginLayoutParams.leftMargin;
            this.topMargin = marginLayoutParams.topMargin;
            this.rightMargin = marginLayoutParams.rightMargin;
            this.bottomMargin = marginLayoutParams.bottomMargin;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.v7.widget.Toolbar.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a;
        boolean c;

        public SavedState(Parcel parcel) {
            this(parcel, null);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readInt();
            this.c = parcel.readInt() != 0;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeInt(this.c ? 1 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a implements android.support.v7.view.menu.p {
        android.support.v7.view.menu.h a;
        android.support.v7.view.menu.k b;

        a() {
        }

        @Override // android.support.v7.view.menu.p
        public android.support.v7.view.menu.q a(ViewGroup viewGroup) {
            return null;
        }

        @Override // android.support.v7.view.menu.p
        public void a(Context context, android.support.v7.view.menu.h hVar) {
            android.support.v7.view.menu.k kVar;
            android.support.v7.view.menu.h hVar2 = this.a;
            if (hVar2 != null && (kVar = this.b) != null) {
                hVar2.d(kVar);
            }
            this.a = hVar;
        }

        @Override // android.support.v7.view.menu.p
        public void a(Parcelable parcelable) {
        }

        @Override // android.support.v7.view.menu.p
        public void a(android.support.v7.view.menu.h hVar, boolean z) {
        }

        @Override // android.support.v7.view.menu.p
        public void a(p.a aVar) {
        }

        @Override // android.support.v7.view.menu.p
        public boolean a(android.support.v7.view.menu.h hVar, android.support.v7.view.menu.k kVar) {
            Toolbar.this.j();
            ViewParent parent = Toolbar.this.a.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.a);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.a);
            }
            Toolbar.this.b = kVar.getActionView();
            this.b = kVar;
            ViewParent parent2 = Toolbar.this.b.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.b);
                }
                LayoutParams generateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                generateDefaultLayoutParams.a = 8388611 | (Toolbar.this.c & 112);
                generateDefaultLayoutParams.e = 2;
                Toolbar.this.b.setLayoutParams(generateDefaultLayoutParams);
                Toolbar toolbar4 = Toolbar.this;
                toolbar4.addView(toolbar4.b);
            }
            Toolbar.this.l();
            Toolbar.this.requestLayout();
            kVar.e(true);
            if (Toolbar.this.b instanceof android.support.v7.view.c) {
                ((android.support.v7.view.c) Toolbar.this.b).a();
            }
            return true;
        }

        @Override // android.support.v7.view.menu.p
        public boolean a(android.support.v7.view.menu.v vVar) {
            return false;
        }

        @Override // android.support.v7.view.menu.p
        public void b(boolean z) {
            if (this.b != null) {
                android.support.v7.view.menu.h hVar = this.a;
                boolean z2 = false;
                if (hVar != null) {
                    int size = hVar.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        } else if (this.a.getItem(i) == this.b) {
                            z2 = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                if (z2) {
                    return;
                }
                b(this.a, this.b);
            }
        }

        @Override // android.support.v7.view.menu.p
        public boolean b() {
            return false;
        }

        @Override // android.support.v7.view.menu.p
        public boolean b(android.support.v7.view.menu.h hVar, android.support.v7.view.menu.k kVar) {
            if (Toolbar.this.b instanceof android.support.v7.view.c) {
                ((android.support.v7.view.c) Toolbar.this.b).b();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.b);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.a);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.b = null;
            toolbar3.m();
            this.b = null;
            Toolbar.this.requestLayout();
            kVar.e(false);
            return true;
        }

        @Override // android.support.v7.view.menu.p
        public int c() {
            return 0;
        }

        @Override // android.support.v7.view.menu.p
        public Parcelable f() {
            return null;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        boolean a(MenuItem menuItem);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, @android.support.annotation.ag AttributeSet attributeSet) {
        this(context, attributeSet, a.b.toolbarStyle);
    }

    public Toolbar(Context context, @android.support.annotation.ag AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.y = 8388627;
        this.F = new ArrayList<>();
        this.G = new ArrayList<>();
        this.H = new int[2];
        this.I = new ActionMenuView.d() { // from class: android.support.v7.widget.Toolbar.1
            @Override // android.support.v7.widget.ActionMenuView.d
            public boolean a(MenuItem menuItem) {
                if (Toolbar.this.d != null) {
                    return Toolbar.this.d.a(menuItem);
                }
                return false;
            }
        };
        this.P = new Runnable() { // from class: android.support.v7.widget.Toolbar.2
            @Override // java.lang.Runnable
            public void run() {
                Toolbar.this.d();
            }
        };
        av a2 = av.a(getContext(), attributeSet, a.l.Toolbar, i, 0);
        this.o = a2.g(a.l.Toolbar_titleTextAppearance, 0);
        this.p = a2.g(a.l.Toolbar_subtitleTextAppearance, 0);
        this.y = a2.c(a.l.Toolbar_android_gravity, this.y);
        this.c = a2.c(a.l.Toolbar_buttonGravity, 48);
        int d = a2.d(a.l.Toolbar_titleMargin, 0);
        d = a2.j(a.l.Toolbar_titleMargins) ? a2.d(a.l.Toolbar_titleMargins, d) : d;
        this.u = d;
        this.t = d;
        this.s = d;
        this.r = d;
        int d2 = a2.d(a.l.Toolbar_titleMarginStart, -1);
        if (d2 >= 0) {
            this.r = d2;
        }
        int d3 = a2.d(a.l.Toolbar_titleMarginEnd, -1);
        if (d3 >= 0) {
            this.s = d3;
        }
        int d4 = a2.d(a.l.Toolbar_titleMarginTop, -1);
        if (d4 >= 0) {
            this.t = d4;
        }
        int d5 = a2.d(a.l.Toolbar_titleMarginBottom, -1);
        if (d5 >= 0) {
            this.u = d5;
        }
        this.q = a2.e(a.l.Toolbar_maxButtonHeight, -1);
        int d6 = a2.d(a.l.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int d7 = a2.d(a.l.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int e2 = a2.e(a.l.Toolbar_contentInsetLeft, 0);
        int e3 = a2.e(a.l.Toolbar_contentInsetRight, 0);
        t();
        this.v.b(e2, e3);
        if (d6 != Integer.MIN_VALUE || d7 != Integer.MIN_VALUE) {
            this.v.a(d6, d7);
        }
        this.w = a2.d(a.l.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.x = a2.d(a.l.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.k = a2.a(a.l.Toolbar_collapseIcon);
        this.l = a2.d(a.l.Toolbar_collapseContentDescription);
        CharSequence d8 = a2.d(a.l.Toolbar_title);
        if (!TextUtils.isEmpty(d8)) {
            setTitle(d8);
        }
        CharSequence d9 = a2.d(a.l.Toolbar_subtitle);
        if (!TextUtils.isEmpty(d9)) {
            setSubtitle(d9);
        }
        this.m = getContext();
        setPopupTheme(a2.g(a.l.Toolbar_popupTheme, 0));
        Drawable a3 = a2.a(a.l.Toolbar_navigationIcon);
        if (a3 != null) {
            setNavigationIcon(a3);
        }
        CharSequence d10 = a2.d(a.l.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(d10)) {
            setNavigationContentDescription(d10);
        }
        Drawable a4 = a2.a(a.l.Toolbar_logo);
        if (a4 != null) {
            setLogo(a4);
        }
        CharSequence d11 = a2.d(a.l.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(d11)) {
            setLogoDescription(d11);
        }
        if (a2.j(a.l.Toolbar_titleTextColor)) {
            setTitleTextColor(a2.b(a.l.Toolbar_titleTextColor, -1));
        }
        if (a2.j(a.l.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(a2.b(a.l.Toolbar_subtitleTextColor, -1));
        }
        a2.e();
    }

    private int a(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int b2 = b(layoutParams.a);
        if (b2 != 48) {
            if (b2 != 80) {
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int height = getHeight();
                int i3 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
                if (i3 < layoutParams.topMargin) {
                    i3 = layoutParams.topMargin;
                } else {
                    int i4 = (((height - paddingBottom) - measuredHeight) - i3) - paddingTop;
                    if (i4 < layoutParams.bottomMargin) {
                        i3 = Math.max(0, i3 - (layoutParams.bottomMargin - i4));
                    }
                }
                return paddingTop + i3;
            }
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - layoutParams.bottomMargin) - i2;
        }
        return getPaddingTop() - i2;
    }

    private int a(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + max + i2, marginLayoutParams.width), getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private int a(View view, int i, int[] iArr, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = layoutParams.leftMargin - iArr[0];
        int max = i + Math.max(0, i3);
        iArr[0] = Math.max(0, -i3);
        int a2 = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, a2, max + measuredWidth, view.getMeasuredHeight() + a2);
        return max + measuredWidth + layoutParams.rightMargin;
    }

    private int a(List<View> list, int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int size = list.size();
        int i3 = i2;
        int i4 = i;
        int i5 = 0;
        int i6 = 0;
        while (i5 < size) {
            View view = list.get(i5);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int i7 = layoutParams.leftMargin - i4;
            int i8 = layoutParams.rightMargin - i3;
            int max = Math.max(0, i7);
            int max2 = Math.max(0, i8);
            int max3 = Math.max(0, -i7);
            int max4 = Math.max(0, -i8);
            i6 += max + view.getMeasuredWidth() + max2;
            i5++;
            i3 = max4;
            i4 = max3;
        }
        return i6;
    }

    private void a(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private void a(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        LayoutParams generateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (LayoutParams) layoutParams;
        generateDefaultLayoutParams.e = 1;
        if (!z || this.b == null) {
            addView(view, generateDefaultLayoutParams);
            return;
        }
        view.setLayoutParams(generateDefaultLayoutParams);
        this.G.add(view);
    }

    private void a(List<View> list, int i) {
        boolean z = android.support.v4.view.ab.m(this) == 1;
        int childCount = getChildCount();
        int a2 = android.support.v4.view.f.a(i, android.support.v4.view.ab.m(this));
        list.clear();
        if (!z) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.e == 0 && a(childAt) && c(layoutParams.a) == a2) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            View childAt2 = getChildAt(i3);
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            if (layoutParams2.e == 0 && a(childAt2) && c(layoutParams2.a) == a2) {
                list.add(childAt2);
            }
        }
    }

    private boolean a(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private int b(int i) {
        int i2 = i & 112;
        return (i2 == 16 || i2 == 48 || i2 == 80) ? i2 : this.y & 112;
    }

    private int b(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return android.support.v4.view.k.a(marginLayoutParams) + android.support.v4.view.k.b(marginLayoutParams);
    }

    private int b(View view, int i, int[] iArr, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = layoutParams.rightMargin - iArr[1];
        int max = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int a2 = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, a2, max, view.getMeasuredHeight() + a2);
        return max - (measuredWidth + layoutParams.leftMargin);
    }

    private int c(int i) {
        int m = android.support.v4.view.ab.m(this);
        int a2 = android.support.v4.view.f.a(i, m) & 7;
        return (a2 == 1 || a2 == 3 || a2 == 5) ? a2 : m == 1 ? 5 : 3;
    }

    private int c(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private static boolean d(View view) {
        return ((LayoutParams) view.getLayoutParams()).e == 0;
    }

    private boolean e(View view) {
        return view.getParent() == this || this.G.contains(view);
    }

    private MenuInflater getMenuInflater() {
        return new android.support.v7.view.g(getContext());
    }

    private void n() {
        if (this.j == null) {
            this.j = new AppCompatImageView(getContext());
        }
    }

    private void o() {
        p();
        if (this.f.d() == null) {
            android.support.v7.view.menu.h hVar = (android.support.v7.view.menu.h) this.f.getMenu();
            if (this.L == null) {
                this.L = new a();
            }
            this.f.setExpandedActionViewsExclusive(true);
            hVar.a(this.L, this.m);
        }
    }

    private void p() {
        if (this.f == null) {
            this.f = new ActionMenuView(getContext());
            this.f.setPopupTheme(this.n);
            this.f.setOnMenuItemClickListener(this.I);
            this.f.a(this.M, this.N);
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.a = 8388613 | (this.c & 112);
            this.f.setLayoutParams(generateDefaultLayoutParams);
            a((View) this.f, false);
        }
    }

    private void q() {
        if (this.i == null) {
            this.i = new AppCompatImageButton(getContext(), null, a.b.toolbarNavigationButtonStyle);
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.a = 8388611 | (this.c & 112);
            this.i.setLayoutParams(generateDefaultLayoutParams);
        }
    }

    private void r() {
        removeCallbacks(this.P);
        post(this.P);
    }

    private boolean s() {
        if (this.O) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (a(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void t() {
        if (this.v == null) {
            this.v = new aj();
        }
    }

    @Override // android.view.ViewGroup
    /* renamed from: a */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: a */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ActionBar.LayoutParams ? new LayoutParams((ActionBar.LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public void a(@android.support.annotation.ad int i) {
        getMenuInflater().inflate(i, getMenu());
    }

    public void a(int i, int i2) {
        t();
        this.v.a(i, i2);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.r = i;
        this.t = i2;
        this.s = i3;
        this.u = i4;
        requestLayout();
    }

    public void a(Context context, @android.support.annotation.ar int i) {
        this.o = i;
        TextView textView = this.g;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(android.support.v7.view.menu.h hVar, ActionMenuPresenter actionMenuPresenter) {
        if (hVar == null && this.f == null) {
            return;
        }
        p();
        android.support.v7.view.menu.h d = this.f.d();
        if (d == hVar) {
            return;
        }
        if (d != null) {
            d.b(this.K);
            d.b(this.L);
        }
        if (this.L == null) {
            this.L = new a();
        }
        actionMenuPresenter.d(true);
        if (hVar != null) {
            hVar.a(actionMenuPresenter, this.m);
            hVar.a(this.L, this.m);
        } else {
            actionMenuPresenter.a(this.m, (android.support.v7.view.menu.h) null);
            this.L.a(this.m, (android.support.v7.view.menu.h) null);
            actionMenuPresenter.b(true);
            this.L.b(true);
        }
        this.f.setPopupTheme(this.n);
        this.f.setPresenter(actionMenuPresenter);
        this.K = actionMenuPresenter;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void a(p.a aVar, h.a aVar2) {
        this.M = aVar;
        this.N = aVar2;
        ActionMenuView actionMenuView = this.f;
        if (actionMenuView != null) {
            actionMenuView.a(aVar, aVar2);
        }
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public boolean a() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f) != null && actionMenuView.a();
    }

    public void b(int i, int i2) {
        t();
        this.v.b(i, i2);
    }

    public void b(Context context, @android.support.annotation.ar int i) {
        this.p = i;
        TextView textView = this.h;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public boolean b() {
        ActionMenuView actionMenuView = this.f;
        return actionMenuView != null && actionMenuView.g();
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public boolean c() {
        ActionMenuView actionMenuView = this.f;
        return actionMenuView != null && actionMenuView.h();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    public boolean d() {
        ActionMenuView actionMenuView = this.f;
        return actionMenuView != null && actionMenuView.e();
    }

    public boolean e() {
        ActionMenuView actionMenuView = this.f;
        return actionMenuView != null && actionMenuView.f();
    }

    public void f() {
        ActionMenuView actionMenuView = this.f;
        if (actionMenuView != null) {
            actionMenuView.i();
        }
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public boolean g() {
        Layout layout;
        TextView textView = this.g;
        if (textView == null || (layout = textView.getLayout()) == null) {
            return false;
        }
        int lineCount = layout.getLineCount();
        for (int i = 0; i < lineCount; i++) {
            if (layout.getEllipsisCount(i) > 0) {
                return true;
            }
        }
        return false;
    }

    public int getContentInsetEnd() {
        aj ajVar = this.v;
        if (ajVar != null) {
            return ajVar.d();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.x;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        aj ajVar = this.v;
        if (ajVar != null) {
            return ajVar.a();
        }
        return 0;
    }

    public int getContentInsetRight() {
        aj ajVar = this.v;
        if (ajVar != null) {
            return ajVar.b();
        }
        return 0;
    }

    public int getContentInsetStart() {
        aj ajVar = this.v;
        if (ajVar != null) {
            return ajVar.c();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.w;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        android.support.v7.view.menu.h d;
        ActionMenuView actionMenuView = this.f;
        return actionMenuView != null && (d = actionMenuView.d()) != null && d.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.x, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return android.support.v4.view.ab.m(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return android.support.v4.view.ab.m(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.w, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.j;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.j;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        o();
        return this.f.getMenu();
    }

    @android.support.annotation.ag
    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.i;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    @android.support.annotation.ag
    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.i;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.K;
    }

    @android.support.annotation.ag
    public Drawable getOverflowIcon() {
        o();
        return this.f.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.m;
    }

    public int getPopupTheme() {
        return this.n;
    }

    public CharSequence getSubtitle() {
        return this.A;
    }

    public CharSequence getTitle() {
        return this.z;
    }

    public int getTitleMarginBottom() {
        return this.u;
    }

    public int getTitleMarginEnd() {
        return this.s;
    }

    public int getTitleMarginStart() {
        return this.r;
    }

    public int getTitleMarginTop() {
        return this.t;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public p getWrapper() {
        if (this.J == null) {
            this.J = new aw(this, true);
        }
        return this.J;
    }

    public boolean h() {
        a aVar = this.L;
        return (aVar == null || aVar.b == null) ? false : true;
    }

    public void i() {
        a aVar = this.L;
        android.support.v7.view.menu.k kVar = aVar == null ? null : aVar.b;
        if (kVar != null) {
            kVar.collapseActionView();
        }
    }

    void j() {
        if (this.a == null) {
            this.a = new AppCompatImageButton(getContext(), null, a.b.toolbarNavigationButtonStyle);
            this.a.setImageDrawable(this.k);
            this.a.setContentDescription(this.l);
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.a = 8388611 | (this.c & 112);
            generateDefaultLayoutParams.e = 2;
            this.a.setLayoutParams(generateDefaultLayoutParams);
            this.a.setOnClickListener(new View.OnClickListener() { // from class: android.support.v7.widget.Toolbar.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Toolbar.this.i();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: k */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    void l() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((LayoutParams) childAt.getLayoutParams()).e != 2 && childAt != this.f) {
                removeViewAt(childCount);
                this.G.add(childAt);
            }
        }
    }

    void m() {
        for (int size = this.G.size() - 1; size >= 0; size--) {
            addView(this.G.get(size));
        }
        this.G.clear();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.P);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.E = false;
        }
        if (!this.E) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.E = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.E = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x02a5 A[LOOP:0: B:104:0x02a3->B:105:0x02a5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02c7 A[LOOP:1: B:107:0x02c5->B:108:0x02c7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0301 A[LOOP:2: B:116:0x02ff->B:117:0x0301, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x022b  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 790
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        char c;
        char c2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int[] iArr = this.H;
        if (bd.a(this)) {
            c = 1;
            c2 = 0;
        } else {
            c = 0;
            c2 = 1;
        }
        if (a(this.i)) {
            a(this.i, i, 0, i2, 0, this.q);
            i3 = this.i.getMeasuredWidth() + b(this.i);
            i4 = Math.max(0, this.i.getMeasuredHeight() + c(this.i));
            i5 = View.combineMeasuredStates(0, this.i.getMeasuredState());
        } else {
            i3 = 0;
            i4 = 0;
            i5 = 0;
        }
        if (a(this.a)) {
            a(this.a, i, 0, i2, 0, this.q);
            i3 = this.a.getMeasuredWidth() + b(this.a);
            i4 = Math.max(i4, this.a.getMeasuredHeight() + c(this.a));
            i5 = View.combineMeasuredStates(i5, this.a.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max = 0 + Math.max(currentContentInsetStart, i3);
        iArr[c] = Math.max(0, currentContentInsetStart - i3);
        if (a(this.f)) {
            a(this.f, i, max, i2, 0, this.q);
            i6 = this.f.getMeasuredWidth() + b(this.f);
            i4 = Math.max(i4, this.f.getMeasuredHeight() + c(this.f));
            i5 = View.combineMeasuredStates(i5, this.f.getMeasuredState());
        } else {
            i6 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int max2 = max + Math.max(currentContentInsetEnd, i6);
        iArr[c2] = Math.max(0, currentContentInsetEnd - i6);
        if (a(this.b)) {
            max2 += a(this.b, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, this.b.getMeasuredHeight() + c(this.b));
            i5 = View.combineMeasuredStates(i5, this.b.getMeasuredState());
        }
        if (a(this.j)) {
            max2 += a(this.j, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, this.j.getMeasuredHeight() + c(this.j));
            i5 = View.combineMeasuredStates(i5, this.j.getMeasuredState());
        }
        int childCount = getChildCount();
        int i10 = i4;
        int i11 = max2;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (((LayoutParams) childAt.getLayoutParams()).e == 0 && a(childAt)) {
                i11 += a(childAt, i, i11, i2, 0, iArr);
                i10 = Math.max(i10, childAt.getMeasuredHeight() + c(childAt));
                i5 = View.combineMeasuredStates(i5, childAt.getMeasuredState());
            }
        }
        int i13 = this.t + this.u;
        int i14 = this.r + this.s;
        if (a(this.g)) {
            a(this.g, i, i11 + i14, i2, i13, iArr);
            int measuredWidth = this.g.getMeasuredWidth() + b(this.g);
            i9 = this.g.getMeasuredHeight() + c(this.g);
            i7 = View.combineMeasuredStates(i5, this.g.getMeasuredState());
            i8 = measuredWidth;
        } else {
            i7 = i5;
            i8 = 0;
            i9 = 0;
        }
        if (a(this.h)) {
            i8 = Math.max(i8, a(this.h, i, i11 + i14, i2, i9 + i13, iArr));
            i9 += this.h.getMeasuredHeight() + c(this.h);
            i7 = View.combineMeasuredStates(i7, this.h.getMeasuredState());
        }
        int max3 = Math.max(i10, i9);
        int paddingLeft = i11 + i8 + getPaddingLeft() + getPaddingRight();
        int paddingTop = max3 + getPaddingTop() + getPaddingBottom();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i, (-16777216) & i7);
        int resolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i2, i7 << 16);
        if (s()) {
            resolveSizeAndState2 = 0;
        }
        setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        ActionMenuView actionMenuView = this.f;
        android.support.v7.view.menu.h d = actionMenuView != null ? actionMenuView.d() : null;
        if (savedState.a != 0 && this.L != null && d != null && (findItem = d.findItem(savedState.a)) != null) {
            findItem.expandActionView();
        }
        if (savedState.c) {
            r();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i);
        }
        t();
        this.v.a(i == 1);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        a aVar = this.L;
        if (aVar != null && aVar.b != null) {
            savedState.a = this.L.b.getItemId();
        }
        savedState.c = b();
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.D = false;
        }
        if (!this.D) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.D = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.D = false;
        }
        return true;
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setCollapsible(boolean z) {
        this.O = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.x) {
            this.x = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.w) {
            this.w = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(@android.support.annotation.p int i) {
        setLogo(android.support.v7.b.a.a.b(getContext(), i));
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            n();
            if (!e(this.j)) {
                a((View) this.j, true);
            }
        } else {
            ImageView imageView = this.j;
            if (imageView != null && e(imageView)) {
                removeView(this.j);
                this.G.remove(this.j);
            }
        }
        ImageView imageView2 = this.j;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(@android.support.annotation.aq int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            n();
        }
        ImageView imageView = this.j;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(@android.support.annotation.aq int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationContentDescription(@android.support.annotation.ag CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            q();
        }
        ImageButton imageButton = this.i;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(@android.support.annotation.p int i) {
        setNavigationIcon(android.support.v7.b.a.a.b(getContext(), i));
    }

    public void setNavigationIcon(@android.support.annotation.ag Drawable drawable) {
        if (drawable != null) {
            q();
            if (!e(this.i)) {
                a((View) this.i, true);
            }
        } else {
            ImageButton imageButton = this.i;
            if (imageButton != null && e(imageButton)) {
                removeView(this.i);
                this.G.remove(this.i);
            }
        }
        ImageButton imageButton2 = this.i;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        q();
        this.i.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(b bVar) {
        this.d = bVar;
    }

    public void setOverflowIcon(@android.support.annotation.ag Drawable drawable) {
        o();
        this.f.setOverflowIcon(drawable);
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

    public void setSubtitle(@android.support.annotation.aq int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.h;
            if (textView != null && e(textView)) {
                removeView(this.h);
                this.G.remove(this.h);
            }
        } else {
            if (this.h == null) {
                Context context = getContext();
                this.h = new AppCompatTextView(context);
                this.h.setSingleLine();
                this.h.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.p;
                if (i != 0) {
                    this.h.setTextAppearance(context, i);
                }
                int i2 = this.C;
                if (i2 != 0) {
                    this.h.setTextColor(i2);
                }
            }
            if (!e(this.h)) {
                a((View) this.h, true);
            }
        }
        TextView textView2 = this.h;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.A = charSequence;
    }

    public void setSubtitleTextColor(@android.support.annotation.k int i) {
        this.C = i;
        TextView textView = this.h;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public void setTitle(@android.support.annotation.aq int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.g;
            if (textView != null && e(textView)) {
                removeView(this.g);
                this.G.remove(this.g);
            }
        } else {
            if (this.g == null) {
                Context context = getContext();
                this.g = new AppCompatTextView(context);
                this.g.setSingleLine();
                this.g.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.o;
                if (i != 0) {
                    this.g.setTextAppearance(context, i);
                }
                int i2 = this.B;
                if (i2 != 0) {
                    this.g.setTextColor(i2);
                }
            }
            if (!e(this.g)) {
                a((View) this.g, true);
            }
        }
        TextView textView2 = this.g;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.z = charSequence;
    }

    public void setTitleMarginBottom(int i) {
        this.u = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.s = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.r = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.t = i;
        requestLayout();
    }

    public void setTitleTextColor(@android.support.annotation.k int i) {
        this.B = i;
        TextView textView = this.g;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }
}
