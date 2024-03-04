package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ai;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ab;
import android.support.v4.widget.x;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class SlidingPaneLayout extends ViewGroup {
    private static final String h = "SlidingPaneLayout";
    private static final int i = 32;
    private static final int j = -858993460;
    private static final int l = 400;
    private boolean A;
    View a;
    float b;
    int c;
    boolean d;
    final x e;
    boolean f;
    final ArrayList<b> g;
    private int k;
    private int m;
    private Drawable n;
    private Drawable o;
    private final int p;
    private boolean q;
    private float r;
    private int s;
    private float t;
    private float u;
    private d v;
    private boolean w;
    private final Rect x;
    private Method y;
    private Field z;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int[] e = {16843137};
        public float a;
        boolean b;
        boolean c;
        Paint d;

        public LayoutParams() {
            super(-1, -1);
            this.a = 0.0f;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.a = 0.0f;
        }

        public LayoutParams(@af Context context, @ag AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e);
            this.a = obtainStyledAttributes.getFloat(0, 0.0f);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(@af LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.a = 0.0f;
            this.a = layoutParams.a;
        }

        public LayoutParams(@af ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0.0f;
        }

        public LayoutParams(@af ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.a = 0.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.v4.widget.SlidingPaneLayout.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        boolean a;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readInt() != 0;
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a ? 1 : 0);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    class a extends android.support.v4.view.a {
        private final Rect b = new Rect();

        a() {
        }

        private void a(android.support.v4.view.a.c cVar, android.support.v4.view.a.c cVar2) {
            Rect rect = this.b;
            cVar2.a(rect);
            cVar.b(rect);
            cVar2.c(rect);
            cVar.d(rect);
            cVar.e(cVar2.m());
            cVar.a(cVar2.v());
            cVar.b(cVar2.w());
            cVar.d(cVar2.y());
            cVar.j(cVar2.r());
            cVar.h(cVar2.p());
            cVar.c(cVar2.k());
            cVar.d(cVar2.l());
            cVar.f(cVar2.n());
            cVar.g(cVar2.o());
            cVar.i(cVar2.q());
            cVar.d(cVar2.f());
            cVar.f(cVar2.g());
        }

        @Override // android.support.v4.view.a
        public void a(View view, android.support.v4.view.a.c cVar) {
            android.support.v4.view.a.c a = android.support.v4.view.a.c.a(cVar);
            super.a(view, a);
            a(cVar, a);
            a.z();
            cVar.b((CharSequence) SlidingPaneLayout.class.getName());
            cVar.b(view);
            ViewParent n = ab.n(view);
            if (n instanceof View) {
                cVar.e((View) n);
            }
            int childCount = SlidingPaneLayout.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = SlidingPaneLayout.this.getChildAt(i);
                if (!b(childAt) && childAt.getVisibility() == 0) {
                    ab.e(childAt, 1);
                    cVar.c(childAt);
                }
            }
        }

        @Override // android.support.v4.view.a
        public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (b(view)) {
                return false;
            }
            return super.a(viewGroup, view, accessibilityEvent);
        }

        public boolean b(View view) {
            return SlidingPaneLayout.this.f(view);
        }

        @Override // android.support.v4.view.a
        public void d(View view, AccessibilityEvent accessibilityEvent) {
            super.d(view, accessibilityEvent);
            accessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b implements Runnable {
        final View a;

        b(View view) {
            this.a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a.getParent() == SlidingPaneLayout.this) {
                this.a.setLayerType(0, null);
                SlidingPaneLayout.this.e(this.a);
            }
            SlidingPaneLayout.this.g.remove(this);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class c extends x.a {
        c() {
        }

        @Override // android.support.v4.widget.x.a
        public int a(View view) {
            return SlidingPaneLayout.this.c;
        }

        @Override // android.support.v4.widget.x.a
        public int a(View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) SlidingPaneLayout.this.a.getLayoutParams();
            if (SlidingPaneLayout.this.i()) {
                int width = SlidingPaneLayout.this.getWidth() - ((SlidingPaneLayout.this.getPaddingRight() + layoutParams.rightMargin) + SlidingPaneLayout.this.a.getWidth());
                return Math.max(Math.min(i, width), width - SlidingPaneLayout.this.c);
            }
            int paddingLeft = SlidingPaneLayout.this.getPaddingLeft() + layoutParams.leftMargin;
            return Math.min(Math.max(i, paddingLeft), SlidingPaneLayout.this.c + paddingLeft);
        }

        @Override // android.support.v4.widget.x.a
        public void a(int i) {
            SlidingPaneLayout slidingPaneLayout;
            boolean z;
            if (SlidingPaneLayout.this.e.b() == 0) {
                if (SlidingPaneLayout.this.b == 0.0f) {
                    SlidingPaneLayout slidingPaneLayout2 = SlidingPaneLayout.this;
                    slidingPaneLayout2.d(slidingPaneLayout2.a);
                    SlidingPaneLayout slidingPaneLayout3 = SlidingPaneLayout.this;
                    slidingPaneLayout3.c(slidingPaneLayout3.a);
                    slidingPaneLayout = SlidingPaneLayout.this;
                    z = false;
                } else {
                    SlidingPaneLayout slidingPaneLayout4 = SlidingPaneLayout.this;
                    slidingPaneLayout4.b(slidingPaneLayout4.a);
                    slidingPaneLayout = SlidingPaneLayout.this;
                    z = true;
                }
                slidingPaneLayout.f = z;
            }
        }

        @Override // android.support.v4.widget.x.a
        public void a(View view, float f, float f2) {
            int paddingLeft;
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (SlidingPaneLayout.this.i()) {
                int paddingRight = SlidingPaneLayout.this.getPaddingRight() + layoutParams.rightMargin;
                if (f < 0.0f || (f == 0.0f && SlidingPaneLayout.this.b > 0.5f)) {
                    paddingRight += SlidingPaneLayout.this.c;
                }
                paddingLeft = (SlidingPaneLayout.this.getWidth() - paddingRight) - SlidingPaneLayout.this.a.getWidth();
            } else {
                paddingLeft = layoutParams.leftMargin + SlidingPaneLayout.this.getPaddingLeft();
                if (f > 0.0f || (f == 0.0f && SlidingPaneLayout.this.b > 0.5f)) {
                    paddingLeft += SlidingPaneLayout.this.c;
                }
            }
            SlidingPaneLayout.this.e.a(paddingLeft, view.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        @Override // android.support.v4.widget.x.a
        public void a(View view, int i, int i2, int i3, int i4) {
            SlidingPaneLayout.this.a(i);
            SlidingPaneLayout.this.invalidate();
        }

        @Override // android.support.v4.widget.x.a
        public boolean a(View view, int i) {
            if (SlidingPaneLayout.this.d) {
                return false;
            }
            return ((LayoutParams) view.getLayoutParams()).b;
        }

        @Override // android.support.v4.widget.x.a
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // android.support.v4.widget.x.a
        public void b(int i, int i2) {
            SlidingPaneLayout.this.e.a(SlidingPaneLayout.this.a, i2);
        }

        @Override // android.support.v4.widget.x.a
        public void b(View view, int i) {
            SlidingPaneLayout.this.a();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface d {
        void a(@af View view);

        void a(@af View view, float f);

        void b(@af View view);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class e implements d {
        @Override // android.support.v4.widget.SlidingPaneLayout.d
        public void a(View view) {
        }

        @Override // android.support.v4.widget.SlidingPaneLayout.d
        public void a(View view, float f) {
        }

        @Override // android.support.v4.widget.SlidingPaneLayout.d
        public void b(View view) {
        }
    }

    public SlidingPaneLayout(@af Context context) {
        this(context, null);
    }

    public SlidingPaneLayout(@af Context context, @ag AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingPaneLayout(@af Context context, @ag AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.k = j;
        this.w = true;
        this.x = new Rect();
        this.g = new ArrayList<>();
        float f = context.getResources().getDisplayMetrics().density;
        this.p = (int) ((32.0f * f) + 0.5f);
        setWillNotDraw(false);
        ab.a(this, new a());
        ab.e((View) this, 1);
        this.e = x.a(this, 0.5f, new c());
        this.e.a(f * 400.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(float r10) {
        /*
            r9 = this;
            boolean r0 = r9.i()
            android.view.View r1 = r9.a
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v4.widget.SlidingPaneLayout$LayoutParams r1 = (android.support.v4.widget.SlidingPaneLayout.LayoutParams) r1
            boolean r2 = r1.c
            r3 = 0
            if (r2 == 0) goto L1c
            if (r0 == 0) goto L16
            int r1 = r1.rightMargin
            goto L18
        L16:
            int r1 = r1.leftMargin
        L18:
            if (r1 > 0) goto L1c
            r1 = 1
            goto L1d
        L1c:
            r1 = 0
        L1d:
            int r2 = r9.getChildCount()
        L21:
            if (r3 >= r2) goto L5b
            android.view.View r4 = r9.getChildAt(r3)
            android.view.View r5 = r9.a
            if (r4 != r5) goto L2c
            goto L58
        L2c:
            float r5 = r9.r
            r6 = 1065353216(0x3f800000, float:1.0)
            float r5 = r6 - r5
            int r7 = r9.s
            float r8 = (float) r7
            float r5 = r5 * r8
            int r5 = (int) r5
            r9.r = r10
            float r8 = r6 - r10
            float r7 = (float) r7
            float r8 = r8 * r7
            int r7 = (int) r8
            int r5 = r5 - r7
            if (r0 == 0) goto L44
            int r5 = -r5
        L44:
            r4.offsetLeftAndRight(r5)
            if (r1 == 0) goto L58
            if (r0 == 0) goto L4f
            float r5 = r9.r
            float r5 = r5 - r6
            goto L53
        L4f:
            float r5 = r9.r
            float r5 = r6 - r5
        L53:
            int r6 = r9.m
            r9.a(r4, r5, r6)
        L58:
            int r3 = r3 + 1
            goto L21
        L5b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.SlidingPaneLayout.a(float):void");
    }

    private void a(View view, float f, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f <= 0.0f || i2 == 0) {
            if (view.getLayerType() != 0) {
                if (layoutParams.d != null) {
                    layoutParams.d.setColorFilter(null);
                }
                b bVar = new b(view);
                this.g.add(bVar);
                ab.a(this, bVar);
                return;
            }
            return;
        }
        int i3 = (((int) ((((-16777216) & i2) >>> 24) * f)) << 24) | (i2 & ab.r);
        if (layoutParams.d == null) {
            layoutParams.d = new Paint();
        }
        layoutParams.d.setColorFilter(new PorterDuffColorFilter(i3, PorterDuff.Mode.SRC_OVER));
        if (view.getLayerType() != 2) {
            view.setLayerType(2, layoutParams.d);
        }
        e(view);
    }

    private boolean a(View view, int i2) {
        if (this.w || a(0.0f, i2)) {
            this.f = false;
            return true;
        }
        return false;
    }

    private boolean b(View view, int i2) {
        if (this.w || a(1.0f, i2)) {
            this.f = true;
            return true;
        }
        return false;
    }

    private static boolean g(View view) {
        Drawable background;
        if (view.isOpaque()) {
            return true;
        }
        return Build.VERSION.SDK_INT < 18 && (background = view.getBackground()) != null && background.getOpacity() == -1;
    }

    void a() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    void a(int i2) {
        if (this.a == null) {
            this.b = 0.0f;
            return;
        }
        boolean i3 = i();
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        int width = this.a.getWidth();
        if (i3) {
            i2 = (getWidth() - i2) - width;
        }
        this.b = (i2 - ((i3 ? getPaddingRight() : getPaddingLeft()) + (i3 ? layoutParams.rightMargin : layoutParams.leftMargin))) / this.c;
        if (this.s != 0) {
            a(this.b);
        }
        if (layoutParams.c) {
            a(this.a, this.b, this.k);
        }
        a(this.a);
    }

    void a(View view) {
        d dVar = this.v;
        if (dVar != null) {
            dVar.a(view, this.b);
        }
    }

    boolean a(float f, int i2) {
        int paddingLeft;
        if (this.q) {
            boolean i3 = i();
            LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
            if (i3) {
                paddingLeft = (int) (getWidth() - (((getPaddingRight() + layoutParams.rightMargin) + (f * this.c)) + this.a.getWidth()));
            } else {
                paddingLeft = (int) (getPaddingLeft() + layoutParams.leftMargin + (f * this.c));
            }
            x xVar = this.e;
            View view = this.a;
            if (xVar.a(view, paddingLeft, view.getTop())) {
                a();
                ab.f(this);
                return true;
            }
            return false;
        }
        return false;
    }

    protected boolean a(View view, boolean z, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && a(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                    return true;
                }
            }
        }
        if (z) {
            if (view.canScrollHorizontally(i() ? i2 : -i2)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public void b() {
        c();
    }

    void b(View view) {
        d dVar = this.v;
        if (dVar != null) {
            dVar.a(view);
        }
        sendAccessibilityEvent(32);
    }

    void c(View view) {
        d dVar = this.v;
        if (dVar != null) {
            dVar.b(view);
        }
        sendAccessibilityEvent(32);
    }

    public boolean c() {
        return b(this.a, 0);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.e.a(true)) {
            if (this.q) {
                ab.f(this);
            } else {
                this.e.h();
            }
        }
    }

    @Deprecated
    public void d() {
        e();
    }

    void d(View view) {
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z;
        int i6;
        View view2 = view;
        boolean i7 = i();
        int width = i7 ? getWidth() - getPaddingRight() : getPaddingLeft();
        int paddingLeft = i7 ? getPaddingLeft() : getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        if (view2 == null || !g(view)) {
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
        } else {
            i2 = view.getLeft();
            i3 = view.getRight();
            i4 = view.getTop();
            i5 = view.getBottom();
        }
        int childCount = getChildCount();
        int i8 = 0;
        while (i8 < childCount) {
            View childAt = getChildAt(i8);
            if (childAt == view2) {
                return;
            }
            if (childAt.getVisibility() == 8) {
                z = i7;
            } else {
                int max = Math.max(i7 ? paddingLeft : width, childAt.getLeft());
                int max2 = Math.max(paddingTop, childAt.getTop());
                if (i7) {
                    z = i7;
                    i6 = width;
                } else {
                    z = i7;
                    i6 = paddingLeft;
                }
                childAt.setVisibility((max < i2 || max2 < i4 || Math.min(i6, childAt.getRight()) > i3 || Math.min(height, childAt.getBottom()) > i5) ? 0 : 4);
            }
            i8++;
            view2 = view;
            i7 = z;
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i2;
        int i3;
        super.draw(canvas);
        Drawable drawable = i() ? this.o : this.n;
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt == null || drawable == null) {
            return;
        }
        int top = childAt.getTop();
        int bottom = childAt.getBottom();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (i()) {
            i3 = childAt.getRight();
            i2 = intrinsicWidth + i3;
        } else {
            int left = childAt.getLeft();
            int i4 = left - intrinsicWidth;
            i2 = left;
            i3 = i4;
        }
        drawable.setBounds(i3, top, i2, bottom);
        drawable.draw(canvas);
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int save = canvas.save();
        if (this.q && !layoutParams.b && this.a != null) {
            canvas.getClipBounds(this.x);
            if (i()) {
                Rect rect = this.x;
                rect.left = Math.max(rect.left, this.a.getRight());
            } else {
                Rect rect2 = this.x;
                rect2.right = Math.min(rect2.right, this.a.getLeft());
            }
            canvas.clipRect(this.x);
        }
        boolean drawChild = super.drawChild(canvas, view, j2);
        canvas.restoreToCount(save);
        return drawChild;
    }

    void e(View view) {
        Field field;
        if (Build.VERSION.SDK_INT >= 17) {
            ab.a(view, ((LayoutParams) view.getLayoutParams()).d);
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            if (!this.A) {
                try {
                    this.y = View.class.getDeclaredMethod("getDisplayList", null);
                } catch (NoSuchMethodException e2) {
                    Log.e(h, "Couldn't fetch getDisplayList method; dimming won't work right.", e2);
                }
                try {
                    this.z = View.class.getDeclaredField("mRecreateDisplayList");
                    this.z.setAccessible(true);
                } catch (NoSuchFieldException e3) {
                    Log.e(h, "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e3);
                }
                this.A = true;
            }
            if (this.y == null || (field = this.z) == null) {
                view.invalidate();
                return;
            }
            try {
                field.setBoolean(view, true);
                this.y.invoke(view, null);
            } catch (Exception e4) {
                Log.e(h, "Error refreshing display list state", e4);
            }
        }
        ab.a(this, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    public boolean e() {
        return a(this.a, 0);
    }

    public boolean f() {
        return !this.q || this.b == 1.0f;
    }

    boolean f(View view) {
        if (view == null) {
            return false;
        }
        return this.q && ((LayoutParams) view.getLayoutParams()).c && this.b > 0.0f;
    }

    @Deprecated
    public boolean g() {
        return this.q;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @android.support.annotation.k
    public int getCoveredFadeColor() {
        return this.m;
    }

    @ai
    public int getParallaxDistance() {
        return this.s;
    }

    @android.support.annotation.k
    public int getSliderFadeColor() {
        return this.k;
    }

    public boolean h() {
        return this.q;
    }

    boolean i() {
        return ab.m(this) == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.w = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.w = true;
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.g.get(i2).run();
        }
        this.g.clear();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View childAt;
        int actionMasked = motionEvent.getActionMasked();
        if (!this.q && actionMasked == 0 && getChildCount() > 1 && (childAt = getChildAt(1)) != null) {
            this.f = !this.e.b(childAt, (int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (!this.q || (this.d && actionMasked != 0)) {
            this.e.g();
            return super.onInterceptTouchEvent(motionEvent);
        } else if (actionMasked == 3 || actionMasked == 1) {
            this.e.g();
            return false;
        } else {
            if (actionMasked == 0) {
                this.d = false;
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.t = x;
                this.u = y;
                if (this.e.b(this.a, (int) x, (int) y) && f(this.a)) {
                    z = true;
                    return this.e.a(motionEvent) || z;
                }
            } else if (actionMasked == 2) {
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                float abs = Math.abs(x2 - this.t);
                float abs2 = Math.abs(y2 - this.u);
                if (abs > this.e.f() && abs2 > abs) {
                    this.e.g();
                    this.d = true;
                    return false;
                }
            }
            z = false;
            if (this.e.a(motionEvent)) {
                return true;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean i11 = i();
        if (i11) {
            this.e.a(2);
        } else {
            this.e.a(1);
        }
        int i12 = i4 - i2;
        int paddingRight = i11 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = i11 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.w) {
            this.b = (this.q && this.f) ? 1.0f : 0.0f;
        }
        int i13 = paddingRight;
        int i14 = i13;
        for (int i15 = 0; i15 < childCount; i15++) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (layoutParams.b) {
                    int i16 = i12 - paddingLeft;
                    int min = (Math.min(i13, i16 - this.p) - i14) - (layoutParams.leftMargin + layoutParams.rightMargin);
                    this.c = min;
                    int i17 = i11 ? layoutParams.rightMargin : layoutParams.leftMargin;
                    layoutParams.c = ((i14 + i17) + min) + (measuredWidth / 2) > i16;
                    int i18 = (int) (min * this.b);
                    i6 = i17 + i18 + i14;
                    this.b = i18 / this.c;
                    i7 = 0;
                } else if (!this.q || (i8 = this.s) == 0) {
                    i6 = i13;
                    i7 = 0;
                } else {
                    i7 = (int) ((1.0f - this.b) * i8);
                    i6 = i13;
                }
                if (i11) {
                    i10 = (i12 - i6) + i7;
                    i9 = i10 - measuredWidth;
                } else {
                    i9 = i6 - i7;
                    i10 = i9 + measuredWidth;
                }
                childAt.layout(i9, paddingTop, i10, childAt.getMeasuredHeight() + paddingTop);
                i13 += childAt.getWidth();
                i14 = i6;
            }
        }
        if (this.w) {
            if (this.q) {
                if (this.s != 0) {
                    a(this.b);
                }
                if (((LayoutParams) this.a.getLayoutParams()).c) {
                    a(this.a, this.b, this.k);
                }
            } else {
                for (int i19 = 0; i19 < childCount; i19++) {
                    a(getChildAt(i19), 0.0f, this.k);
                }
            }
            d(this.a);
        }
        this.w = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0118  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r21, int r22) {
        /*
            Method dump skipped, instructions count: 571
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.SlidingPaneLayout.onMeasure(int, int):void");
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        if (savedState.a) {
            c();
        } else {
            e();
        }
        this.f = savedState.a;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = h() ? f() : this.f;
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            this.w = true;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.q) {
            this.e.b(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.t = x;
                this.u = y;
            } else if (actionMasked == 1 && f(this.a)) {
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                float f = x2 - this.t;
                float f2 = y2 - this.u;
                int f3 = this.e.f();
                if ((f * f) + (f2 * f2) < f3 * f3 && this.e.b(this.a, (int) x2, (int) y2)) {
                    a(this.a, 0);
                }
            }
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (isInTouchMode() || this.q) {
            return;
        }
        this.f = view == this.a;
    }

    public void setCoveredFadeColor(@android.support.annotation.k int i2) {
        this.m = i2;
    }

    public void setPanelSlideListener(@ag d dVar) {
        this.v = dVar;
    }

    public void setParallaxDistance(@ai int i2) {
        this.s = i2;
        requestLayout();
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(@ag Drawable drawable) {
        this.n = drawable;
    }

    public void setShadowDrawableRight(@ag Drawable drawable) {
        this.o = drawable;
    }

    @Deprecated
    public void setShadowResource(@android.support.annotation.p int i2) {
        setShadowDrawable(getResources().getDrawable(i2));
    }

    public void setShadowResourceLeft(int i2) {
        setShadowDrawableLeft(android.support.v4.content.c.getDrawable(getContext(), i2));
    }

    public void setShadowResourceRight(int i2) {
        setShadowDrawableRight(android.support.v4.content.c.getDrawable(getContext(), i2));
    }

    public void setSliderFadeColor(@android.support.annotation.k int i2) {
        this.k = i2;
    }
}
