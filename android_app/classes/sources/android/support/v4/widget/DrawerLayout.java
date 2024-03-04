package android.support.v4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.a.c;
import android.support.v4.view.ab;
import android.support.v4.widget.x;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class DrawerLayout extends ViewGroup {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 0;
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    static final int[] h;
    static final boolean i;
    private static final String j = "DrawerLayout";
    private static final int[] k;
    private static final int l = 64;
    private static final int m = 10;
    private static final int n = -1728053248;
    private static final int o = 160;
    private static final int p = 400;
    private static final boolean q = false;
    private static final boolean r = true;
    private static final float s = 1.0f;
    private static final boolean t;
    private final x A;
    private final x B;
    private final e C;
    private final e D;
    private int E;
    private boolean F;
    private boolean G;
    private int H;
    private int I;
    private int J;
    private int K;
    private boolean L;
    private boolean M;
    @ag
    private c N;
    private List<c> O;
    private float P;
    private float Q;
    private Drawable R;
    private Drawable S;
    private Drawable T;
    private CharSequence U;
    private CharSequence V;
    private Object W;
    private boolean aa;
    private Drawable ab;
    private Drawable ac;
    private Drawable ad;
    private Drawable ae;
    private final ArrayList<View> af;
    private Rect ag;
    private Matrix ah;
    private final b u;
    private float v;
    private int w;
    private int x;
    private float y;
    private Paint z;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int e = 1;
        private static final int f = 2;
        private static final int g = 4;
        public int a;
        float b;
        boolean c;
        int d;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.a = 0;
        }

        public LayoutParams(int i, int i2, int i3) {
            this(i, i2);
            this.a = i3;
        }

        public LayoutParams(@af Context context, @ag AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.h);
            this.a = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(@af LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.a = 0;
            this.a = layoutParams.a;
        }

        public LayoutParams(@af ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0;
        }

        public LayoutParams(@af ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.a = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.v4.widget.DrawerLayout.SavedState.1
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
        int c;
        int d;
        int e;
        int f;

        public SavedState(@af Parcel parcel, @ag ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = 0;
            this.a = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
        }

        public SavedState(@af Parcelable parcelable) {
            super(parcelable);
            this.a = 0;
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
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
        }

        private void a(android.support.v4.view.a.c cVar, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (DrawerLayout.l(childAt)) {
                    cVar.c(childAt);
                }
            }
        }

        @Override // android.support.v4.view.a
        public void a(View view, android.support.v4.view.a.c cVar) {
            if (DrawerLayout.i) {
                super.a(view, cVar);
            } else {
                android.support.v4.view.a.c a = android.support.v4.view.a.c.a(cVar);
                super.a(view, a);
                cVar.b(view);
                ViewParent n = ab.n(view);
                if (n instanceof View) {
                    cVar.e((View) n);
                }
                a(cVar, a);
                a.z();
                a(cVar, (ViewGroup) view);
            }
            cVar.b((CharSequence) DrawerLayout.class.getName());
            cVar.c(false);
            cVar.d(false);
            cVar.b(c.a.a);
            cVar.b(c.a.b);
        }

        @Override // android.support.v4.view.a
        public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.i || DrawerLayout.l(view)) {
                return super.a(viewGroup, view, accessibilityEvent);
            }
            return false;
        }

        @Override // android.support.v4.view.a
        public boolean b(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() == 32) {
                List<CharSequence> text = accessibilityEvent.getText();
                View c = DrawerLayout.this.c();
                if (c != null) {
                    CharSequence b = DrawerLayout.this.b(DrawerLayout.this.e(c));
                    if (b != null) {
                        text.add(b);
                        return DrawerLayout.r;
                    }
                    return DrawerLayout.r;
                }
                return DrawerLayout.r;
            }
            return super.b(view, accessibilityEvent);
        }

        @Override // android.support.v4.view.a
        public void d(View view, AccessibilityEvent accessibilityEvent) {
            super.d(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static final class b extends android.support.v4.view.a {
        b() {
        }

        @Override // android.support.v4.view.a
        public void a(View view, android.support.v4.view.a.c cVar) {
            super.a(view, cVar);
            if (DrawerLayout.l(view)) {
                return;
            }
            cVar.e((View) null);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface c {
        void onDrawerClosed(@af View view);

        void onDrawerOpened(@af View view);

        void onDrawerSlide(@af View view, float f);

        void onDrawerStateChanged(int i);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class d implements c {
        @Override // android.support.v4.widget.DrawerLayout.c
        public void onDrawerClosed(View view) {
        }

        @Override // android.support.v4.widget.DrawerLayout.c
        public void onDrawerOpened(View view) {
        }

        @Override // android.support.v4.widget.DrawerLayout.c
        public void onDrawerSlide(View view, float f) {
        }

        @Override // android.support.v4.widget.DrawerLayout.c
        public void onDrawerStateChanged(int i) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class e extends x.a {
        private final int b;
        private x c;
        private final Runnable d = new Runnable() { // from class: android.support.v4.widget.DrawerLayout.e.1
            @Override // java.lang.Runnable
            public void run() {
                e.this.b();
            }
        };

        e(int i) {
            this.b = i;
        }

        private void c() {
            View c = DrawerLayout.this.c(this.b == 3 ? 5 : 3);
            if (c != null) {
                DrawerLayout.this.i(c);
            }
        }

        @Override // android.support.v4.widget.x.a
        public int a(View view) {
            if (DrawerLayout.this.g(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // android.support.v4.widget.x.a
        public int a(View view, int i, int i2) {
            int width;
            int width2;
            if (DrawerLayout.this.a(view, 3)) {
                width2 = -view.getWidth();
                width = 0;
            } else {
                width = DrawerLayout.this.getWidth();
                width2 = width - view.getWidth();
            }
            return Math.max(width2, Math.min(i, width));
        }

        public void a() {
            DrawerLayout.this.removeCallbacks(this.d);
        }

        @Override // android.support.v4.widget.x.a
        public void a(int i) {
            DrawerLayout.this.a(this.b, i, this.c.d());
        }

        @Override // android.support.v4.widget.x.a
        public void a(int i, int i2) {
            DrawerLayout.this.postDelayed(this.d, 160L);
        }

        public void a(x xVar) {
            this.c = xVar;
        }

        @Override // android.support.v4.widget.x.a
        public void a(View view, float f, float f2) {
            int i;
            float d = DrawerLayout.this.d(view);
            int width = view.getWidth();
            if (DrawerLayout.this.a(view, 3)) {
                i = (f > 0.0f || (f == 0.0f && d > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f < 0.0f || (f == 0.0f && d > 0.5f)) {
                    width2 -= width;
                }
                i = width2;
            }
            this.c.a(i, view.getTop());
            DrawerLayout.this.invalidate();
        }

        @Override // android.support.v4.widget.x.a
        public void a(View view, int i, int i2, int i3, int i4) {
            int width = view.getWidth();
            float width2 = (DrawerLayout.this.a(view, 3) ? i + width : DrawerLayout.this.getWidth() - i) / width;
            DrawerLayout.this.b(view, width2);
            view.setVisibility(width2 == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        @Override // android.support.v4.widget.x.a
        public boolean a(View view, int i) {
            if (DrawerLayout.this.g(view) && DrawerLayout.this.a(view, this.b) && DrawerLayout.this.a(view) == 0) {
                return DrawerLayout.r;
            }
            return false;
        }

        @Override // android.support.v4.widget.x.a
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        void b() {
            View c;
            int width;
            int c2 = this.c.c();
            boolean z = this.b == 3 ? DrawerLayout.r : false;
            if (z) {
                c = DrawerLayout.this.c(3);
                width = (c != null ? -c.getWidth() : 0) + c2;
            } else {
                c = DrawerLayout.this.c(5);
                width = DrawerLayout.this.getWidth() - c2;
            }
            if (c != null) {
                if (((!z || c.getLeft() >= width) && (z || c.getLeft() <= width)) || DrawerLayout.this.a(c) != 0) {
                    return;
                }
                this.c.a(c, width, c.getTop());
                ((LayoutParams) c.getLayoutParams()).c = DrawerLayout.r;
                DrawerLayout.this.invalidate();
                c();
                DrawerLayout.this.d();
            }
        }

        @Override // android.support.v4.widget.x.a
        public void b(int i, int i2) {
            DrawerLayout drawerLayout;
            int i3;
            if ((i & 1) == 1) {
                drawerLayout = DrawerLayout.this;
                i3 = 3;
            } else {
                drawerLayout = DrawerLayout.this;
                i3 = 5;
            }
            View c = drawerLayout.c(i3);
            if (c == null || DrawerLayout.this.a(c) != 0) {
                return;
            }
            this.c.a(c, i2);
        }

        @Override // android.support.v4.widget.x.a
        public void b(View view, int i) {
            ((LayoutParams) view.getLayoutParams()).c = false;
            c();
        }

        @Override // android.support.v4.widget.x.a
        public boolean b(int i) {
            return false;
        }
    }

    static {
        boolean z = r;
        k = new int[]{16843828};
        h = new int[]{16842931};
        i = Build.VERSION.SDK_INT >= 19 ? r : false;
        if (Build.VERSION.SDK_INT < 21) {
            z = false;
        }
        t = z;
    }

    public DrawerLayout(@af Context context) {
        this(context, null);
    }

    public DrawerLayout(@af Context context, @ag AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawerLayout(@af Context context, @ag AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.u = new b();
        this.x = n;
        this.z = new Paint();
        this.G = r;
        this.H = 3;
        this.I = 3;
        this.J = 3;
        this.K = 3;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        setDescendantFocusability(262144);
        float f2 = getResources().getDisplayMetrics().density;
        this.w = (int) ((64.0f * f2) + 0.5f);
        float f3 = 400.0f * f2;
        this.C = new e(3);
        this.D = new e(5);
        this.A = x.a(this, (float) s, this.C);
        this.A.a(1);
        this.A.a(f3);
        this.C.a(this.A);
        this.B = x.a(this, (float) s, this.D);
        this.B.a(2);
        this.B.a(f3);
        this.D.a(this.B);
        setFocusableInTouchMode(r);
        ab.e((View) this, 1);
        ab.a(this, new a());
        setMotionEventSplittingEnabled(false);
        if (ab.R(this)) {
            if (Build.VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: android.support.v4.widget.DrawerLayout.1
                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                        ((DrawerLayout) view).a(windowInsets, windowInsets.getSystemWindowInsetTop() > 0 ? DrawerLayout.r : false);
                        return windowInsets.consumeSystemWindowInsets();
                    }
                });
                setSystemUiVisibility(1280);
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(k);
                try {
                    this.R = obtainStyledAttributes.getDrawable(0);
                } finally {
                    obtainStyledAttributes.recycle();
                }
            } else {
                this.R = null;
            }
        }
        this.v = f2 * 10.0f;
        this.af = new ArrayList<>();
    }

    private boolean a(float f2, float f3, View view) {
        if (this.ag == null) {
            this.ag = new Rect();
        }
        view.getHitRect(this.ag);
        return this.ag.contains((int) f2, (int) f3);
    }

    private boolean a(MotionEvent motionEvent, View view) {
        if (!view.getMatrix().isIdentity()) {
            MotionEvent b2 = b(motionEvent, view);
            boolean dispatchGenericMotionEvent = view.dispatchGenericMotionEvent(b2);
            b2.recycle();
            return dispatchGenericMotionEvent;
        }
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        motionEvent.offsetLocation(scrollX, scrollY);
        boolean dispatchGenericMotionEvent2 = view.dispatchGenericMotionEvent(motionEvent);
        motionEvent.offsetLocation(-scrollX, -scrollY);
        return dispatchGenericMotionEvent2;
    }

    private MotionEvent b(MotionEvent motionEvent, View view) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation(getScrollX() - view.getLeft(), getScrollY() - view.getTop());
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            if (this.ah == null) {
                this.ah = new Matrix();
            }
            matrix.invert(this.ah);
            obtain.transform(this.ah);
        }
        return obtain;
    }

    private boolean b(Drawable drawable, int i2) {
        if (drawable == null || !android.support.v4.graphics.drawable.a.b(drawable)) {
            return false;
        }
        android.support.v4.graphics.drawable.a.b(drawable, i2);
        return r;
    }

    private void c(View view, boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            ab.e(childAt, ((z || g(childAt)) && !(z && childAt == view)) ? 4 : 1);
        }
    }

    static String d(int i2) {
        return (i2 & 3) == 3 ? "LEFT" : (i2 & 5) == 5 ? "RIGHT" : Integer.toHexString(i2);
    }

    private void e() {
        if (t) {
            return;
        }
        this.S = f();
        this.T = g();
    }

    private Drawable f() {
        int m2 = ab.m(this);
        if (m2 == 0) {
            Drawable drawable = this.ab;
            if (drawable != null) {
                b(drawable, m2);
                return this.ab;
            }
        } else {
            Drawable drawable2 = this.ac;
            if (drawable2 != null) {
                b(drawable2, m2);
                return this.ac;
            }
        }
        return this.ad;
    }

    private Drawable g() {
        int m2 = ab.m(this);
        if (m2 == 0) {
            Drawable drawable = this.ac;
            if (drawable != null) {
                b(drawable, m2);
                return this.ac;
            }
        } else {
            Drawable drawable2 = this.ab;
            if (drawable2 != null) {
                b(drawable2, m2);
                return this.ab;
            }
        }
        return this.ae;
    }

    private boolean h() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (((LayoutParams) getChildAt(i2).getLayoutParams()).c) {
                return r;
            }
        }
        return false;
    }

    private boolean i() {
        if (c() != null) {
            return r;
        }
        return false;
    }

    static boolean l(View view) {
        if (ab.g(view) == 4 || ab.g(view) == 2) {
            return false;
        }
        return r;
    }

    private static boolean m(View view) {
        Drawable background = view.getBackground();
        if (background == null || background.getOpacity() != -1) {
            return false;
        }
        return r;
    }

    public int a(int i2) {
        int m2 = ab.m(this);
        if (i2 == 3) {
            int i3 = this.H;
            if (i3 != 3) {
                return i3;
            }
            int i4 = m2 == 0 ? this.J : this.K;
            if (i4 != 3) {
                return i4;
            }
            return 0;
        } else if (i2 == 5) {
            int i5 = this.I;
            if (i5 != 3) {
                return i5;
            }
            int i6 = m2 == 0 ? this.K : this.J;
            if (i6 != 3) {
                return i6;
            }
            return 0;
        } else if (i2 == 8388611) {
            int i7 = this.J;
            if (i7 != 3) {
                return i7;
            }
            int i8 = m2 == 0 ? this.H : this.I;
            if (i8 != 3) {
                return i8;
            }
            return 0;
        } else if (i2 != 8388613) {
            return 0;
        } else {
            int i9 = this.K;
            if (i9 != 3) {
                return i9;
            }
            int i10 = m2 == 0 ? this.I : this.H;
            if (i10 != 3) {
                return i10;
            }
            return 0;
        }
    }

    public int a(@af View view) {
        if (g(view)) {
            return a(((LayoutParams) view.getLayoutParams()).a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    View a() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((((LayoutParams) childAt.getLayoutParams()).d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    public void a(@android.support.annotation.p int i2, int i3) {
        a(android.support.v4.content.c.getDrawable(getContext(), i2), i3);
    }

    void a(int i2, int i3, View view) {
        int b2 = this.A.b();
        int b3 = this.B.b();
        int i4 = 2;
        if (b2 == 1 || b3 == 1) {
            i4 = 1;
        } else if (b2 != 2 && b3 != 2) {
            i4 = 0;
        }
        if (view != null && i3 == 0) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams.b == 0.0f) {
                b(view);
            } else if (layoutParams.b == s) {
                c(view);
            }
        }
        if (i4 != this.E) {
            this.E = i4;
            List<c> list = this.O;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.O.get(size).onDrawerStateChanged(i4);
                }
            }
        }
    }

    public void a(int i2, @af View view) {
        if (g(view)) {
            b(i2, ((LayoutParams) view.getLayoutParams()).a);
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer with appropriate layout_gravity");
    }

    public void a(int i2, @ag CharSequence charSequence) {
        int a2 = android.support.v4.view.f.a(i2, ab.m(this));
        if (a2 == 3) {
            this.U = charSequence;
        } else if (a2 == 5) {
            this.V = charSequence;
        }
    }

    public void a(int i2, boolean z) {
        View c2 = c(i2);
        if (c2 != null) {
            a(c2, z);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + d(i2));
    }

    public void a(Drawable drawable, int i2) {
        if (t) {
            return;
        }
        if ((i2 & android.support.v4.view.f.b) == 8388611) {
            this.ab = drawable;
        } else if ((i2 & android.support.v4.view.f.c) == 8388613) {
            this.ac = drawable;
        } else if ((i2 & 3) == 3) {
            this.ad = drawable;
        } else if ((i2 & 5) != 5) {
            return;
        } else {
            this.ae = drawable;
        }
        e();
        invalidate();
    }

    public void a(@af c cVar) {
        if (cVar == null) {
            return;
        }
        if (this.O == null) {
            this.O = new ArrayList();
        }
        this.O.add(cVar);
    }

    void a(View view, float f2) {
        List<c> list = this.O;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.O.get(size).onDrawerSlide(view, f2);
            }
        }
    }

    public void a(@af View view, boolean z) {
        if (!g(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.G) {
            layoutParams.b = s;
            layoutParams.d = 1;
            c(view, r);
        } else if (z) {
            layoutParams.d |= 2;
            if (a(view, 3)) {
                this.A.a(view, 0, view.getTop());
            } else {
                this.B.a(view, getWidth() - view.getWidth(), view.getTop());
            }
        } else {
            c(view, s);
            a(layoutParams.a, 0, view);
            view.setVisibility(0);
        }
        invalidate();
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public void a(Object obj, boolean z) {
        this.W = obj;
        this.aa = z;
        setWillNotDraw((z || getBackground() != null) ? false : r);
        requestLayout();
    }

    void a(boolean z) {
        int childCount = getChildCount();
        boolean z2 = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (g(childAt) && (!z || layoutParams.c)) {
                z2 |= a(childAt, 3) ? this.A.a(childAt, -childAt.getWidth(), childAt.getTop()) : this.B.a(childAt, getWidth(), childAt.getTop());
                layoutParams.c = false;
            }
        }
        this.C.a();
        this.D.a();
        if (z2) {
            invalidate();
        }
    }

    boolean a(View view, int i2) {
        if ((e(view) & i2) == i2) {
            return r;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        boolean z = false;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (!g(childAt)) {
                this.af.add(childAt);
            } else if (j(childAt)) {
                childAt.addFocusables(arrayList, i2, i3);
                z = r;
            }
        }
        if (!z) {
            int size = this.af.size();
            for (int i5 = 0; i5 < size; i5++) {
                View view = this.af.get(i5);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i2, i3);
                }
            }
        }
        this.af.clear();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        ab.e(view, (a() != null || g(view)) ? 4 : 1);
        if (i) {
            return;
        }
        ab.a(view, this.u);
    }

    @ag
    public CharSequence b(int i2) {
        int a2 = android.support.v4.view.f.a(i2, ab.m(this));
        if (a2 == 3) {
            return this.U;
        }
        if (a2 == 5) {
            return this.V;
        }
        return null;
    }

    public void b() {
        a(false);
    }

    public void b(int i2, int i3) {
        View c2;
        int a2 = android.support.v4.view.f.a(i3, ab.m(this));
        if (i3 == 3) {
            this.H = i2;
        } else if (i3 == 5) {
            this.I = i2;
        } else if (i3 == 8388611) {
            this.J = i2;
        } else if (i3 == 8388613) {
            this.K = i2;
        }
        if (i2 != 0) {
            (a2 == 3 ? this.A : this.B).g();
        }
        if (i2 != 1) {
            if (i2 == 2 && (c2 = c(a2)) != null) {
                h(c2);
                return;
            }
            return;
        }
        View c3 = c(a2);
        if (c3 != null) {
            i(c3);
        }
    }

    public void b(int i2, boolean z) {
        View c2 = c(i2);
        if (c2 != null) {
            b(c2, z);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + d(i2));
    }

    public void b(@af c cVar) {
        List<c> list;
        if (cVar == null || (list = this.O) == null) {
            return;
        }
        list.remove(cVar);
    }

    void b(View view) {
        View rootView;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if ((layoutParams.d & 1) == 1) {
            layoutParams.d = 0;
            List<c> list = this.O;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.O.get(size).onDrawerClosed(view);
                }
            }
            c(view, false);
            if (!hasWindowFocus() || (rootView = getRootView()) == null) {
                return;
            }
            rootView.sendAccessibilityEvent(32);
        }
    }

    void b(View view, float f2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f2 == layoutParams.b) {
            return;
        }
        layoutParams.b = f2;
        a(view, f2);
    }

    public void b(@af View view, boolean z) {
        x xVar;
        int width;
        if (!g(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.G) {
            layoutParams.b = 0.0f;
            layoutParams.d = 0;
        } else if (z) {
            layoutParams.d |= 4;
            if (a(view, 3)) {
                xVar = this.A;
                width = -view.getWidth();
            } else {
                xVar = this.B;
                width = getWidth();
            }
            xVar.a(view, width, view.getTop());
        } else {
            c(view, 0.0f);
            a(layoutParams.a, 0, view);
            view.setVisibility(4);
        }
        invalidate();
    }

    View c() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (g(childAt) && k(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    View c(int i2) {
        int a2 = android.support.v4.view.f.a(i2, ab.m(this)) & 7;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if ((e(childAt) & 7) == a2) {
                return childAt;
            }
        }
        return null;
    }

    void c(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if ((layoutParams.d & 1) == 0) {
            layoutParams.d = 1;
            List<c> list = this.O;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.O.get(size).onDrawerOpened(view);
                }
            }
            c(view, r);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    void c(View view, float f2) {
        float d2 = d(view);
        float width = view.getWidth();
        int i2 = ((int) (width * f2)) - ((int) (d2 * width));
        if (!a(view, 3)) {
            i2 = -i2;
        }
        view.offsetLeftAndRight(i2);
        b(view, f2);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if ((layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams)) {
            return r;
        }
        return false;
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < childCount; i2++) {
            f2 = Math.max(f2, ((LayoutParams) getChildAt(i2).getLayoutParams()).b);
        }
        this.y = f2;
        boolean a2 = this.A.a(r);
        boolean a3 = this.B.a(r);
        if (a2 || a3) {
            ab.f(this);
        }
    }

    float d(View view) {
        return ((LayoutParams) view.getLayoutParams()).b;
    }

    void d() {
        if (this.M) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).dispatchTouchEvent(obtain);
        }
        obtain.recycle();
        this.M = r;
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.y <= 0.0f) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount != 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            for (int i2 = childCount - 1; i2 >= 0; i2--) {
                View childAt = getChildAt(i2);
                if (a(x, y, childAt) && !f(childAt) && a(motionEvent, childAt)) {
                    return r;
                }
            }
            return false;
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j2) {
        int i2;
        Drawable drawable;
        int height = getHeight();
        boolean f2 = f(view);
        int width = getWidth();
        int save = canvas.save();
        int i3 = 0;
        if (f2) {
            int childCount = getChildCount();
            i2 = width;
            int i4 = 0;
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (childAt != view && childAt.getVisibility() == 0 && m(childAt) && g(childAt) && childAt.getHeight() >= height) {
                    if (a(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i4) {
                            i4 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < i2) {
                            i2 = left;
                        }
                    }
                }
            }
            canvas.clipRect(i4, 0, i2, getHeight());
            i3 = i4;
        } else {
            i2 = width;
        }
        boolean drawChild = super.drawChild(canvas, view, j2);
        canvas.restoreToCount(save);
        float f3 = this.y;
        if (f3 <= 0.0f || !f2) {
            if (this.S != null && a(view, 3)) {
                int intrinsicWidth = this.S.getIntrinsicWidth();
                int right2 = view.getRight();
                float max = Math.max(0.0f, Math.min(right2 / this.A.c(), (float) s));
                this.S.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
                this.S.setAlpha((int) (max * 255.0f));
                drawable = this.S;
            } else if (this.T != null && a(view, 5)) {
                int intrinsicWidth2 = this.T.getIntrinsicWidth();
                int left2 = view.getLeft();
                float max2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.B.c(), (float) s));
                this.T.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
                this.T.setAlpha((int) (max2 * 255.0f));
                drawable = this.T;
            }
            drawable.draw(canvas);
        } else {
            int i6 = this.x;
            this.z.setColor((i6 & ab.r) | (((int) ((((-16777216) & i6) >>> 24) * f3)) << 24));
            canvas.drawRect(i3, 0.0f, i2, getHeight(), this.z);
        }
        return drawChild;
    }

    int e(View view) {
        return android.support.v4.view.f.a(((LayoutParams) view.getLayoutParams()).a, ab.m(this));
    }

    public void e(int i2) {
        a(i2, r);
    }

    public void f(int i2) {
        b(i2, r);
    }

    boolean f(View view) {
        if (((LayoutParams) view.getLayoutParams()).a == 0) {
            return r;
        }
        return false;
    }

    public boolean g(int i2) {
        View c2 = c(i2);
        if (c2 != null) {
            return j(c2);
        }
        return false;
    }

    boolean g(View view) {
        int a2 = android.support.v4.view.f.a(((LayoutParams) view.getLayoutParams()).a, ab.m(view));
        if ((a2 & 3) == 0 && (a2 & 5) == 0) {
            return false;
        }
        return r;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public float getDrawerElevation() {
        if (t) {
            return this.v;
        }
        return 0.0f;
    }

    @ag
    public Drawable getStatusBarBackgroundDrawable() {
        return this.R;
    }

    public void h(@af View view) {
        a(view, r);
    }

    public boolean h(int i2) {
        View c2 = c(i2);
        if (c2 != null) {
            return k(c2);
        }
        return false;
    }

    public void i(@af View view) {
        b(view, r);
    }

    public boolean j(@af View view) {
        if (g(view)) {
            if ((((LayoutParams) view.getLayoutParams()).d & 1) == 1) {
                return r;
            }
            return false;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public boolean k(@af View view) {
        if (g(view)) {
            if (((LayoutParams) view.getLayoutParams()).b > 0.0f) {
                return r;
            }
            return false;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.G = r;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.G = r;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Object obj;
        super.onDraw(canvas);
        if (!this.aa || this.R == null) {
            return;
        }
        int systemWindowInsetTop = (Build.VERSION.SDK_INT < 21 || (obj = this.W) == null) ? 0 : ((WindowInsets) obj).getSystemWindowInsetTop();
        if (systemWindowInsetTop > 0) {
            this.R.setBounds(0, 0, getWidth(), systemWindowInsetTop);
            this.R.draw(canvas);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        if (r0 != 3) goto L7;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = r7.getActionMasked()
            android.support.v4.widget.x r1 = r6.A
            boolean r1 = r1.a(r7)
            android.support.v4.widget.x r2 = r6.B
            boolean r2 = r2.a(r7)
            r1 = r1 | r2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L3a
            if (r0 == r2) goto L31
            r7 = 2
            r4 = 3
            if (r0 == r7) goto L1e
            if (r0 == r4) goto L31
            goto L38
        L1e:
            android.support.v4.widget.x r7 = r6.A
            boolean r7 = r7.d(r4)
            if (r7 == 0) goto L38
            android.support.v4.widget.DrawerLayout$e r7 = r6.C
            r7.a()
            android.support.v4.widget.DrawerLayout$e r7 = r6.D
            r7.a()
            goto L38
        L31:
            r6.a(r2)
            r6.L = r3
            r6.M = r3
        L38:
            r7 = 0
            goto L64
        L3a:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.P = r0
            r6.Q = r7
            float r4 = r6.y
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L5f
            android.support.v4.widget.x r4 = r6.A
            int r0 = (int) r0
            int r7 = (int) r7
            android.view.View r7 = r4.e(r0, r7)
            if (r7 == 0) goto L5f
            boolean r7 = r6.f(r7)
            if (r7 == 0) goto L5f
            r7 = 1
            goto L60
        L5f:
            r7 = 0
        L60:
            r6.L = r3
            r6.M = r3
        L64:
            if (r1 != 0) goto L74
            if (r7 != 0) goto L74
            boolean r7 = r6.h()
            if (r7 != 0) goto L74
            boolean r7 = r6.M
            if (r7 == 0) goto L73
            goto L74
        L73:
            r2 = 0
        L74:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4 && i()) {
            keyEvent.startTracking();
            return r;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            View c2 = c();
            if (c2 != null && a(c2) == 0) {
                b();
            }
            if (c2 != null) {
                return r;
            }
            return false;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        float f2;
        int i7;
        this.F = r;
        int i8 = i4 - i2;
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (f(childAt)) {
                    childAt.layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + childAt.getMeasuredWidth(), layoutParams.topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        float f3 = measuredWidth;
                        i7 = (-measuredWidth) + ((int) (layoutParams.b * f3));
                        f2 = (measuredWidth + i7) / f3;
                    } else {
                        float f4 = measuredWidth;
                        f2 = (i8 - i6) / f4;
                        i7 = i8 - ((int) (layoutParams.b * f4));
                    }
                    boolean z2 = f2 != layoutParams.b ? r : false;
                    int i10 = layoutParams.a & 112;
                    if (i10 == 16) {
                        int i11 = i5 - i3;
                        int i12 = (i11 - measuredHeight) / 2;
                        if (i12 < layoutParams.topMargin) {
                            i12 = layoutParams.topMargin;
                        } else if (i12 + measuredHeight > i11 - layoutParams.bottomMargin) {
                            i12 = (i11 - layoutParams.bottomMargin) - measuredHeight;
                        }
                        childAt.layout(i7, i12, measuredWidth + i7, measuredHeight + i12);
                    } else if (i10 != 80) {
                        childAt.layout(i7, layoutParams.topMargin, measuredWidth + i7, layoutParams.topMargin + measuredHeight);
                    } else {
                        int i13 = i5 - i3;
                        childAt.layout(i7, (i13 - layoutParams.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i7, i13 - layoutParams.bottomMargin);
                    }
                    if (z2) {
                        b(childAt, f2);
                    }
                    int i14 = layoutParams.b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i14) {
                        childAt.setVisibility(i14);
                    }
                }
            }
        }
        this.F = false;
        this.G = false;
    }

    @Override // android.view.View
    @SuppressLint({"WrongConstant"})
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode != 1073741824 || mode2 != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
            if (mode != Integer.MIN_VALUE && mode == 0) {
                size = 300;
            }
            if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                size2 = 300;
            }
        }
        setMeasuredDimension(size, size2);
        int i4 = 0;
        boolean z = (this.W == null || !ab.R(this)) ? false : r;
        int m2 = ab.m(this);
        int childCount = getChildCount();
        int i5 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (z) {
                    int a2 = android.support.v4.view.f.a(layoutParams.a, m2);
                    if (ab.R(childAt)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            WindowInsets windowInsets = (WindowInsets) this.W;
                            if (a2 == 3) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), i4, windowInsets.getSystemWindowInsetBottom());
                            } else if (a2 == 5) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(i4, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsets);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        WindowInsets windowInsets2 = (WindowInsets) this.W;
                        if (a2 == 3) {
                            windowInsets2 = windowInsets2.replaceSystemWindowInsets(windowInsets2.getSystemWindowInsetLeft(), windowInsets2.getSystemWindowInsetTop(), i4, windowInsets2.getSystemWindowInsetBottom());
                        } else if (a2 == 5) {
                            windowInsets2 = windowInsets2.replaceSystemWindowInsets(i4, windowInsets2.getSystemWindowInsetTop(), windowInsets2.getSystemWindowInsetRight(), windowInsets2.getSystemWindowInsetBottom());
                        }
                        layoutParams.leftMargin = windowInsets2.getSystemWindowInsetLeft();
                        layoutParams.topMargin = windowInsets2.getSystemWindowInsetTop();
                        layoutParams.rightMargin = windowInsets2.getSystemWindowInsetRight();
                        layoutParams.bottomMargin = windowInsets2.getSystemWindowInsetBottom();
                    }
                }
                if (f(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - layoutParams.leftMargin) - layoutParams.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec((size2 - layoutParams.topMargin) - layoutParams.bottomMargin, 1073741824));
                } else if (!g(childAt)) {
                    throw new IllegalStateException("Child " + childAt + " at index " + i5 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                } else {
                    if (t) {
                        float M = ab.M(childAt);
                        float f2 = this.v;
                        if (M != f2) {
                            ab.m(childAt, f2);
                        }
                    }
                    int e2 = e(childAt) & 7;
                    boolean z4 = e2 == 3 ? r : false;
                    if ((z4 && z2) || (!z4 && z3)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + d(e2) + " but this " + j + " already has a drawer view along that edge");
                    }
                    if (z4) {
                        z2 = r;
                    } else {
                        z3 = r;
                    }
                    childAt.measure(getChildMeasureSpec(i2, this.w + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), getChildMeasureSpec(i3, layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height));
                    i5++;
                    i4 = 0;
                }
            }
            i5++;
            i4 = 0;
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        View c2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        if (savedState.a != 0 && (c2 = c(savedState.a)) != null) {
            h(c2);
        }
        if (savedState.c != 3) {
            b(savedState.c, 3);
        }
        if (savedState.d != 3) {
            b(savedState.d, 5);
        }
        if (savedState.e != 3) {
            b(savedState.e, android.support.v4.view.f.b);
        }
        if (savedState.f != 3) {
            b(savedState.f, android.support.v4.view.f.c);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        e();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i2).getLayoutParams();
            int i3 = layoutParams.d;
            boolean z = r;
            boolean z2 = i3 == 1 ? r : false;
            if (layoutParams.d != 2) {
                z = false;
            }
            if (z2 || z) {
                savedState.a = layoutParams.a;
                break;
            }
        }
        savedState.c = this.H;
        savedState.d = this.I;
        savedState.e = this.J;
        savedState.f = this.K;
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View a2;
        this.A.b(motionEvent);
        this.B.b(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action == 1) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                View e2 = this.A.e((int) x, (int) y);
                if (e2 != null && f(e2)) {
                    float f2 = x - this.P;
                    float f3 = y - this.Q;
                    int f4 = this.A.f();
                    if ((f2 * f2) + (f3 * f3) < f4 * f4 && (a2 = a()) != null && a(a2) != 2) {
                        z = false;
                        a(z);
                        this.L = false;
                    }
                }
                z = r;
                a(z);
                this.L = false;
            } else if (action == 3) {
                a(r);
            }
            return r;
        }
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        this.P = x2;
        this.Q = y2;
        this.L = false;
        this.M = false;
        return r;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.L = z;
        if (z) {
            a(r);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.F) {
            return;
        }
        super.requestLayout();
    }

    public void setDrawerElevation(float f2) {
        this.v = f2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (g(childAt)) {
                ab.m(childAt, this.v);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(c cVar) {
        c cVar2 = this.N;
        if (cVar2 != null) {
            b(cVar2);
        }
        if (cVar != null) {
            a(cVar);
        }
        this.N = cVar;
    }

    public void setDrawerLockMode(int i2) {
        b(i2, 3);
        b(i2, 5);
    }

    public void setScrimColor(@android.support.annotation.k int i2) {
        this.x = i2;
        invalidate();
    }

    public void setStatusBarBackground(int i2) {
        this.R = i2 != 0 ? android.support.v4.content.c.getDrawable(getContext(), i2) : null;
        invalidate();
    }

    public void setStatusBarBackground(@ag Drawable drawable) {
        this.R = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(@android.support.annotation.k int i2) {
        this.R = new ColorDrawable(i2);
        invalidate();
    }
}
