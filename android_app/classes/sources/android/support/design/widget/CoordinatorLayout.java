package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.av;
import android.support.annotation.k;
import android.support.annotation.q;
import android.support.annotation.v;
import android.support.c.a;
import android.support.v4.j.m;
import android.support.v4.j.p;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ab;
import android.support.v4.view.ak;
import android.support.v4.view.s;
import android.support.v4.view.t;
import android.support.v4.view.u;
import android.support.v4.widget.h;
import android.support.v4.widget.y;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class CoordinatorLayout extends ViewGroup implements s {
    static final String a = "CoordinatorLayout";
    static final String b;
    static final Class<?>[] c;
    static final ThreadLocal<Map<String, Constructor<Behavior>>> d;
    static final int e = 0;
    static final int f = 1;
    static final int g = 2;
    static final Comparator<View> h;
    private static final int j = 0;
    private static final int k = 1;
    private static final p.a<Rect> l;
    private boolean A;
    private Drawable B;
    private u C;
    private final t D;
    ViewGroup.OnHierarchyChangeListener i;
    private final List<View> m;
    private final h<View> n;
    private final List<View> o;
    private final List<View> p;
    private final int[] q;
    private Paint r;
    private boolean s;
    private boolean t;
    private int[] u;
    private View v;
    private View w;
    private f x;
    private boolean y;
    private ak z;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class Behavior<V extends View> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
        }

        @ag
        public static Object a(@af View view) {
            return ((e) view.getLayoutParams()).n;
        }

        public static void a(@af View view, @ag Object obj) {
            ((e) view.getLayoutParams()).n = obj;
        }

        @k
        public int a(@af CoordinatorLayout coordinatorLayout, @af V v) {
            return ab.s;
        }

        @af
        public ak a(@af CoordinatorLayout coordinatorLayout, @af V v, @af ak akVar) {
            return akVar;
        }

        public void a() {
        }

        public void a(@af e eVar) {
        }

        public void a(@af CoordinatorLayout coordinatorLayout, @af V v, @af Parcelable parcelable) {
        }

        public void a(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view, int i) {
            if (i == 0) {
                d(coordinatorLayout, v, view);
            }
        }

        @Deprecated
        public void a(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view, int i, int i2, int i3, int i4) {
        }

        public void a(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view, int i, int i2, int i3, int i4, int i5) {
            if (i5 == 0) {
                a(coordinatorLayout, (CoordinatorLayout) v, view, i, i2, i3, i4);
            }
        }

        @Deprecated
        public void a(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view, int i, int i2, @af int[] iArr) {
        }

        public void a(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view, int i, int i2, @af int[] iArr, int i3) {
            if (i3 == 0) {
                a(coordinatorLayout, (CoordinatorLayout) v, view, i, i2, iArr);
            }
        }

        public boolean a(@af CoordinatorLayout coordinatorLayout, @af V v, int i) {
            return false;
        }

        public boolean a(@af CoordinatorLayout coordinatorLayout, @af V v, int i, int i2, int i3, int i4) {
            return false;
        }

        public boolean a(@af CoordinatorLayout coordinatorLayout, @af V v, @af Rect rect) {
            return false;
        }

        public boolean a(@af CoordinatorLayout coordinatorLayout, @af V v, @af Rect rect, boolean z) {
            return false;
        }

        public boolean a(@af CoordinatorLayout coordinatorLayout, @af V v, @af MotionEvent motionEvent) {
            return false;
        }

        public boolean a(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view) {
            return false;
        }

        public boolean a(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view, float f, float f2) {
            return false;
        }

        public boolean a(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view, float f, float f2, boolean z) {
            return false;
        }

        @Deprecated
        public boolean a(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view, @af View view2, int i) {
            return false;
        }

        public boolean a(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view, @af View view2, int i, int i2) {
            if (i2 == 0) {
                return a(coordinatorLayout, (CoordinatorLayout) v, view, view2, i);
            }
            return false;
        }

        @q(a = 0.0d, b = 1.0d)
        public float b(@af CoordinatorLayout coordinatorLayout, @af V v) {
            return 0.0f;
        }

        @Deprecated
        public void b(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view, @af View view2, int i) {
        }

        public void b(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view, @af View view2, int i, int i2) {
            if (i2 == 0) {
                b(coordinatorLayout, v, view, view2, i);
            }
        }

        public boolean b(@af CoordinatorLayout coordinatorLayout, @af V v, @af MotionEvent motionEvent) {
            return false;
        }

        public boolean b(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view) {
            return false;
        }

        public void c(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view) {
        }

        public boolean c(@af CoordinatorLayout coordinatorLayout, @af V v) {
            return b(coordinatorLayout, v) > 0.0f;
        }

        @ag
        public Parcelable d(@af CoordinatorLayout coordinatorLayout, @af V v) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public void d(@af CoordinatorLayout coordinatorLayout, @af V v, @af View view) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.design.widget.CoordinatorLayout.SavedState.1
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
        SparseArray<Parcelable> a;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int readInt = parcel.readInt();
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
            this.a = new SparseArray<>(readInt);
            for (int i = 0; i < readInt; i++) {
                this.a.append(iArr[i], readParcelableArray[i]);
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            SparseArray<Parcelable> sparseArray = this.a;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = this.a.keyAt(i2);
                parcelableArr[i2] = this.a.valueAt(i2);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        @af
        Behavior a();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Deprecated
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface b {
        Class<? extends Behavior> a();
    }

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface c {
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class d implements ViewGroup.OnHierarchyChangeListener {
        d() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            if (CoordinatorLayout.this.i != null) {
                CoordinatorLayout.this.i.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.a(2);
            if (CoordinatorLayout.this.i != null) {
                CoordinatorLayout.this.i.onChildViewRemoved(view, view2);
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class e extends ViewGroup.MarginLayoutParams {
        Behavior a;
        boolean b;
        public int c;
        public int d;
        public int e;
        int f;
        public int g;
        public int h;
        int i;
        int j;
        View k;
        View l;
        final Rect m;
        Object n;
        private boolean o;
        private boolean p;
        private boolean q;
        private boolean r;

        public e(int i, int i2) {
            super(i, i2);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.m = new Rect();
        }

        e(@af Context context, @ag AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.m = new Rect();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.j.CoordinatorLayout_Layout);
            this.c = obtainStyledAttributes.getInteger(a.j.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f = obtainStyledAttributes.getResourceId(a.j.CoordinatorLayout_Layout_layout_anchor, -1);
            this.d = obtainStyledAttributes.getInteger(a.j.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.e = obtainStyledAttributes.getInteger(a.j.CoordinatorLayout_Layout_layout_keyline, -1);
            this.g = obtainStyledAttributes.getInt(a.j.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.h = obtainStyledAttributes.getInt(a.j.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            this.b = obtainStyledAttributes.hasValue(a.j.CoordinatorLayout_Layout_layout_behavior);
            if (this.b) {
                this.a = CoordinatorLayout.a(context, attributeSet, obtainStyledAttributes.getString(a.j.CoordinatorLayout_Layout_layout_behavior));
            }
            obtainStyledAttributes.recycle();
            Behavior behavior = this.a;
            if (behavior != null) {
                behavior.a(this);
            }
        }

        public e(e eVar) {
            super((ViewGroup.MarginLayoutParams) eVar);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.m = new Rect();
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.m = new Rect();
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.m = new Rect();
        }

        private void a(View view, CoordinatorLayout coordinatorLayout) {
            this.k = coordinatorLayout.findViewById(this.f);
            View view2 = this.k;
            if (view2 != null) {
                if (view2 != coordinatorLayout) {
                    for (ViewParent parent = view2.getParent(); parent != coordinatorLayout && parent != null; parent = parent.getParent()) {
                        if (parent != view) {
                            if (parent instanceof View) {
                                view2 = (View) parent;
                            }
                        } else if (!coordinatorLayout.isInEditMode()) {
                            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                        }
                    }
                    this.l = view2;
                    return;
                } else if (!coordinatorLayout.isInEditMode()) {
                    throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                }
            } else if (!coordinatorLayout.isInEditMode()) {
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.f) + " to anchor view " + view);
            }
            this.l = null;
            this.k = null;
        }

        private boolean a(View view, int i) {
            int a = android.support.v4.view.f.a(((e) view.getLayoutParams()).g, i);
            return a != 0 && (android.support.v4.view.f.a(this.h, i) & a) == a;
        }

        private boolean b(View view, CoordinatorLayout coordinatorLayout) {
            if (this.k.getId() != this.f) {
                return false;
            }
            View view2 = this.k;
            for (ViewParent parent = view2.getParent(); parent != coordinatorLayout; parent = parent.getParent()) {
                if (parent == null || parent == view) {
                    this.l = null;
                    this.k = null;
                    return false;
                }
                if (parent instanceof View) {
                    view2 = (View) parent;
                }
            }
            this.l = view2;
            return true;
        }

        @v
        public int a() {
            return this.f;
        }

        public void a(@v int i) {
            i();
            this.f = i;
        }

        void a(int i, boolean z) {
            if (i == 0) {
                this.p = z;
            } else if (i != 1) {
            } else {
                this.q = z;
            }
        }

        void a(Rect rect) {
            this.m.set(rect);
        }

        public void a(@ag Behavior behavior) {
            Behavior behavior2 = this.a;
            if (behavior2 != behavior) {
                if (behavior2 != null) {
                    behavior2.a();
                }
                this.a = behavior;
                this.n = null;
                this.b = true;
                if (behavior != null) {
                    behavior.a(this);
                }
            }
        }

        void a(boolean z) {
            this.r = z;
        }

        boolean a(CoordinatorLayout coordinatorLayout, View view) {
            boolean z = this.o;
            if (z) {
                return true;
            }
            Behavior behavior = this.a;
            boolean c = (behavior != null ? behavior.c(coordinatorLayout, view) : false) | z;
            this.o = c;
            return c;
        }

        boolean a(CoordinatorLayout coordinatorLayout, View view, View view2) {
            Behavior behavior;
            return view2 == this.l || a(view2, ab.m(coordinatorLayout)) || ((behavior = this.a) != null && behavior.a(coordinatorLayout, (CoordinatorLayout) view, view2));
        }

        @ag
        public Behavior b() {
            return this.a;
        }

        View b(CoordinatorLayout coordinatorLayout, View view) {
            if (this.f == -1) {
                this.l = null;
                this.k = null;
                return null;
            }
            if (this.k == null || !b(view, coordinatorLayout)) {
                a(view, coordinatorLayout);
            }
            return this.k;
        }

        void b(int i) {
            a(i, false);
        }

        Rect c() {
            return this.m;
        }

        boolean c(int i) {
            if (i != 0) {
                if (i != 1) {
                    return false;
                }
                return this.q;
            }
            return this.p;
        }

        boolean d() {
            return this.k == null && this.f != -1;
        }

        boolean e() {
            if (this.a == null) {
                this.o = false;
            }
            return this.o;
        }

        void f() {
            this.o = false;
        }

        boolean g() {
            return this.r;
        }

        void h() {
            this.r = false;
        }

        void i() {
            this.l = null;
            this.k = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class f implements ViewTreeObserver.OnPreDrawListener {
        f() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            CoordinatorLayout.this.a(0);
            return true;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class g implements Comparator<View> {
        g() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(View view, View view2) {
            float ad = ab.ad(view);
            float ad2 = ab.ad(view2);
            if (ad > ad2) {
                return -1;
            }
            return ad < ad2 ? 1 : 0;
        }
    }

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        b = r0 != null ? r0.getName() : null;
        if (Build.VERSION.SDK_INT >= 21) {
            h = new g();
        } else {
            h = null;
        }
        c = new Class[]{Context.class, AttributeSet.class};
        d = new ThreadLocal<>();
        l = new p.c(12);
    }

    public CoordinatorLayout(@af Context context) {
        this(context, null);
    }

    public CoordinatorLayout(@af Context context, @ag AttributeSet attributeSet) {
        this(context, attributeSet, a.C0004a.coordinatorLayoutStyle);
    }

    public CoordinatorLayout(@af Context context, @ag AttributeSet attributeSet, @android.support.annotation.f int i) {
        super(context, attributeSet, i);
        this.m = new ArrayList();
        this.n = new h<>();
        this.o = new ArrayList();
        this.p = new ArrayList();
        this.q = new int[2];
        this.D = new t(this);
        TypedArray obtainStyledAttributes = i == 0 ? context.obtainStyledAttributes(attributeSet, a.j.CoordinatorLayout, 0, a.i.Widget_Support_CoordinatorLayout) : context.obtainStyledAttributes(attributeSet, a.j.CoordinatorLayout, i, 0);
        int resourceId = obtainStyledAttributes.getResourceId(a.j.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.u = resources.getIntArray(resourceId);
            float f2 = resources.getDisplayMetrics().density;
            int length = this.u.length;
            for (int i2 = 0; i2 < length; i2++) {
                int[] iArr = this.u;
                iArr[i2] = (int) (iArr[i2] * f2);
            }
        }
        this.B = obtainStyledAttributes.getDrawable(a.j.CoordinatorLayout_statusBarBackground);
        obtainStyledAttributes.recycle();
        g();
        super.setOnHierarchyChangeListener(new d());
    }

    private static int a(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    static Behavior a(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0 && !TextUtils.isEmpty(b)) {
            str = b + '.' + str;
        }
        try {
            Map<String, Constructor<Behavior>> map = d.get();
            if (map == null) {
                map = new HashMap<>();
                d.set(map);
            }
            Constructor<Behavior> constructor = map.get(str);
            if (constructor == null) {
                constructor = context.getClassLoader().loadClass(str).getConstructor(c);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return (Behavior) constructor.newInstance(context, attributeSet);
        } catch (Exception e2) {
            throw new RuntimeException("Could not inflate Behavior subclass " + str, e2);
        }
    }

    private static void a(@af Rect rect) {
        rect.setEmpty();
        l.a(rect);
    }

    private void a(e eVar, Rect rect, int i, int i2) {
        int width = getWidth();
        int height = getHeight();
        int max = Math.max(getPaddingLeft() + eVar.leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i) - eVar.rightMargin));
        int max2 = Math.max(getPaddingTop() + eVar.topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i2) - eVar.bottomMargin));
        rect.set(max, max2, i + max, i2 + max2);
    }

    private void a(View view, int i, Rect rect, Rect rect2, e eVar, int i2, int i3) {
        int a2 = android.support.v4.view.f.a(e(eVar.c), i);
        int a3 = android.support.v4.view.f.a(c(eVar.d), i);
        int i4 = a2 & 7;
        int i5 = a2 & 112;
        int i6 = a3 & 7;
        int i7 = a3 & 112;
        int width = i6 != 1 ? i6 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int height = i7 != 16 ? i7 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i4 == 1) {
            width -= i2 / 2;
        } else if (i4 != 5) {
            width -= i2;
        }
        if (i5 == 16) {
            height -= i3 / 2;
        } else if (i5 != 80) {
            height -= i3;
        }
        rect2.set(width, height, i2 + width, i3 + height);
    }

    private void a(View view, Rect rect, int i) {
        boolean z;
        boolean z2;
        int width;
        int i2;
        int height;
        int i3;
        if (ab.ab(view) && view.getWidth() > 0 && view.getHeight() > 0) {
            e eVar = (e) view.getLayoutParams();
            Behavior b2 = eVar.b();
            Rect e2 = e();
            Rect e3 = e();
            e3.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            if (b2 == null || !b2.a(this, (CoordinatorLayout) view, e2)) {
                e2.set(e3);
            } else if (!e3.contains(e2)) {
                throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + e2.toShortString() + " | Bounds:" + e3.toShortString());
            }
            a(e3);
            if (e2.isEmpty()) {
                a(e2);
                return;
            }
            int a2 = android.support.v4.view.f.a(eVar.h, i);
            if ((a2 & 48) != 48 || (i3 = (e2.top - eVar.topMargin) - eVar.j) >= rect.top) {
                z = false;
            } else {
                f(view, rect.top - i3);
                z = true;
            }
            if ((a2 & 80) == 80 && (height = ((getHeight() - e2.bottom) - eVar.bottomMargin) + eVar.j) < rect.bottom) {
                f(view, height - rect.bottom);
                z = true;
            }
            if (!z) {
                f(view, 0);
            }
            if ((a2 & 3) != 3 || (i2 = (e2.left - eVar.leftMargin) - eVar.i) >= rect.left) {
                z2 = false;
            } else {
                e(view, rect.left - i2);
                z2 = true;
            }
            if ((a2 & 5) == 5 && (width = ((getWidth() - e2.right) - eVar.rightMargin) + eVar.i) < rect.right) {
                e(view, width - rect.right);
                z2 = true;
            }
            if (!z2) {
                e(view, 0);
            }
            a(e2);
        }
    }

    private void a(View view, View view2, int i) {
        Rect e2 = e();
        Rect e3 = e();
        try {
            a(view2, e2);
            a(view, i, e2, e3);
            view.layout(e3.left, e3.top, e3.right, e3.bottom);
        } finally {
            a(e2);
            a(e3);
        }
    }

    private void a(List<View> list) {
        list.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            list.add(getChildAt(isChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i) : i));
        }
        Comparator<View> comparator = h;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    private void a(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            Behavior b2 = ((e) childAt.getLayoutParams()).b();
            if (b2 != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z) {
                    b2.a(this, (CoordinatorLayout) childAt, obtain);
                } else {
                    b2.b(this, (CoordinatorLayout) childAt, obtain);
                }
                obtain.recycle();
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            ((e) getChildAt(i2).getLayoutParams()).f();
        }
        this.v = null;
        this.s = false;
    }

    private boolean a(MotionEvent motionEvent, int i) {
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.o;
        a(list);
        int size = list.size();
        MotionEvent motionEvent2 = null;
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            View view = list.get(i2);
            e eVar = (e) view.getLayoutParams();
            Behavior b2 = eVar.b();
            boolean z3 = true;
            if (!(z || z2) || actionMasked == 0) {
                if (!z && b2 != null) {
                    if (i == 0) {
                        z = b2.a(this, (CoordinatorLayout) view, motionEvent);
                    } else if (i == 1) {
                        z = b2.b(this, (CoordinatorLayout) view, motionEvent);
                    }
                    if (z) {
                        this.v = view;
                    }
                }
                boolean e2 = eVar.e();
                boolean a2 = eVar.a(this, view);
                z3 = (!a2 || e2) ? false : false;
                if (a2 && !z3) {
                    break;
                }
                z2 = z3;
            } else if (b2 != null) {
                if (motionEvent2 == null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    motionEvent2 = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                if (i == 0) {
                    b2.a(this, (CoordinatorLayout) view, motionEvent2);
                } else if (i == 1) {
                    b2.b(this, (CoordinatorLayout) view, motionEvent2);
                }
            }
        }
        list.clear();
        return z;
    }

    private int b(int i) {
        StringBuilder sb;
        int[] iArr = this.u;
        if (iArr == null) {
            sb = new StringBuilder();
            sb.append("No keylines defined for ");
            sb.append(this);
            sb.append(" - attempted index lookup ");
            sb.append(i);
        } else if (i >= 0 && i < iArr.length) {
            return iArr[i];
        } else {
            sb = new StringBuilder();
            sb.append("Keyline index ");
            sb.append(i);
            sb.append(" out of range for ");
            sb.append(this);
        }
        Log.e(a, sb.toString());
        return 0;
    }

    private ak b(ak akVar) {
        Behavior b2;
        if (akVar.g()) {
            return akVar;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (ab.R(childAt) && (b2 = ((e) childAt.getLayoutParams()).b()) != null) {
                akVar = b2.a(this, (CoordinatorLayout) childAt, akVar);
                if (akVar.g()) {
                    break;
                }
            }
        }
        return akVar;
    }

    private void b(View view, int i, int i2) {
        e eVar = (e) view.getLayoutParams();
        int a2 = android.support.v4.view.f.a(d(eVar.c), i2);
        int i3 = a2 & 7;
        int i4 = a2 & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (i2 == 1) {
            i = width - i;
        }
        int b2 = b(i) - measuredWidth;
        int i5 = 0;
        if (i3 == 1) {
            b2 += measuredWidth / 2;
        } else if (i3 == 5) {
            b2 += measuredWidth;
        }
        if (i4 == 16) {
            i5 = 0 + (measuredHeight / 2);
        } else if (i4 == 80) {
            i5 = measuredHeight + 0;
        }
        int max = Math.max(getPaddingLeft() + eVar.leftMargin, Math.min(b2, ((width - getPaddingRight()) - measuredWidth) - eVar.rightMargin));
        int max2 = Math.max(getPaddingTop() + eVar.topMargin, Math.min(i5, ((height - getPaddingBottom()) - measuredHeight) - eVar.bottomMargin));
        view.layout(max, max2, measuredWidth + max, measuredHeight + max2);
    }

    private static int c(int i) {
        if ((i & 7) == 0) {
            i |= android.support.v4.view.f.b;
        }
        return (i & 112) == 0 ? i | 48 : i;
    }

    private static int d(int i) {
        if (i == 0) {
            return 8388661;
        }
        return i;
    }

    private void d(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        Rect e2 = e();
        e2.set(getPaddingLeft() + eVar.leftMargin, getPaddingTop() + eVar.topMargin, (getWidth() - getPaddingRight()) - eVar.rightMargin, (getHeight() - getPaddingBottom()) - eVar.bottomMargin);
        if (this.z != null && ab.R(this) && !ab.R(view)) {
            e2.left += this.z.a();
            e2.top += this.z.b();
            e2.right -= this.z.c();
            e2.bottom -= this.z.d();
        }
        Rect e3 = e();
        android.support.v4.view.f.a(c(eVar.c), view.getMeasuredWidth(), view.getMeasuredHeight(), e2, e3, i);
        view.layout(e3.left, e3.top, e3.right, e3.bottom);
        a(e2);
        a(e3);
    }

    private static int e(int i) {
        if (i == 0) {
            return 17;
        }
        return i;
    }

    @af
    private static Rect e() {
        Rect a2 = l.a();
        return a2 == null ? new Rect() : a2;
    }

    private void e(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        if (eVar.i != i) {
            ab.n(view, i - eVar.i);
            eVar.i = i;
        }
    }

    private boolean e(View view) {
        return this.n.e(view);
    }

    private void f() {
        this.m.clear();
        this.n.a();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            e a2 = a(childAt);
            a2.b(this, childAt);
            this.n.a((h<View>) childAt);
            for (int i2 = 0; i2 < childCount; i2++) {
                if (i2 != i) {
                    View childAt2 = getChildAt(i2);
                    if (a2.a(this, childAt, childAt2)) {
                        if (!this.n.b(childAt2)) {
                            this.n.a((h<View>) childAt2);
                        }
                        this.n.a(childAt2, childAt);
                    }
                }
            }
        }
        this.m.addAll(this.n.b());
        Collections.reverse(this.m);
    }

    private void f(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        if (eVar.j != i) {
            ab.m(view, i - eVar.j);
            eVar.j = i;
        }
    }

    private void g() {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (!ab.R(this)) {
            ab.a(this, (u) null);
            return;
        }
        if (this.C == null) {
            this.C = new u() { // from class: android.support.design.widget.CoordinatorLayout.1
                @Override // android.support.v4.view.u
                public ak a(View view, ak akVar) {
                    return CoordinatorLayout.this.a(akVar);
                }
            };
        }
        ab.a(this, this.C);
        setSystemUiVisibility(1280);
    }

    @Override // android.view.ViewGroup
    /* renamed from: a */
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    e a(View view) {
        e eVar = (e) view.getLayoutParams();
        if (!eVar.b) {
            if (view instanceof a) {
                Behavior a2 = ((a) view).a();
                if (a2 == null) {
                    Log.e(a, "Attached behavior class is null");
                }
                eVar.a(a2);
            } else {
                b bVar = null;
                for (Class<?> cls = view.getClass(); cls != null; cls = cls.getSuperclass()) {
                    bVar = (b) cls.getAnnotation(b.class);
                    if (bVar != null) {
                        break;
                    }
                }
                if (bVar != null) {
                    try {
                        eVar.a(bVar.a().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception e2) {
                        Log.e(a, "Default behavior class " + bVar.a().getName() + " could not be instantiated. Did you forget a default constructor?", e2);
                    }
                }
            }
            eVar.b = true;
        }
        return eVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: a */
    public e generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    final ak a(ak akVar) {
        if (m.a(this.z, akVar)) {
            return akVar;
        }
        this.z = akVar;
        boolean z = true;
        this.A = akVar != null && akVar.b() > 0;
        setWillNotDraw((this.A || getBackground() != null) ? false : false);
        ak b2 = b(akVar);
        requestLayout();
        return b2;
    }

    void a() {
        int childCount = getChildCount();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            } else if (e(getChildAt(i))) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z != this.y) {
            if (z) {
                b();
            } else {
                c();
            }
        }
    }

    final void a(int i) {
        boolean z;
        int m = ab.m(this);
        int size = this.m.size();
        Rect e2 = e();
        Rect e3 = e();
        Rect e4 = e();
        for (int i2 = 0; i2 < size; i2++) {
            View view = this.m.get(i2);
            e eVar = (e) view.getLayoutParams();
            if (i != 0 || view.getVisibility() != 8) {
                for (int i3 = 0; i3 < i2; i3++) {
                    if (eVar.l == this.m.get(i3)) {
                        b(view, m);
                    }
                }
                a(view, true, e3);
                if (eVar.g != 0 && !e3.isEmpty()) {
                    int a2 = android.support.v4.view.f.a(eVar.g, m);
                    int i4 = a2 & 112;
                    if (i4 == 48) {
                        e2.top = Math.max(e2.top, e3.bottom);
                    } else if (i4 == 80) {
                        e2.bottom = Math.max(e2.bottom, getHeight() - e3.top);
                    }
                    int i5 = a2 & 7;
                    if (i5 == 3) {
                        e2.left = Math.max(e2.left, e3.right);
                    } else if (i5 == 5) {
                        e2.right = Math.max(e2.right, getWidth() - e3.left);
                    }
                }
                if (eVar.h != 0 && view.getVisibility() == 0) {
                    a(view, e2, m);
                }
                if (i != 2) {
                    c(view, e4);
                    if (!e4.equals(e3)) {
                        b(view, e3);
                    }
                }
                for (int i6 = i2 + 1; i6 < size; i6++) {
                    View view2 = this.m.get(i6);
                    e eVar2 = (e) view2.getLayoutParams();
                    Behavior b2 = eVar2.b();
                    if (b2 != null && b2.a(this, (CoordinatorLayout) view2, view)) {
                        if (i == 0 && eVar2.g()) {
                            eVar2.h();
                        } else {
                            if (i != 2) {
                                z = b2.b(this, (CoordinatorLayout) view2, view);
                            } else {
                                b2.c(this, view2, view);
                                z = true;
                            }
                            if (i == 1) {
                                eVar2.a(z);
                            }
                        }
                    }
                }
            }
        }
        a(e2);
        a(e3);
        a(e4);
    }

    public void a(@af View view, int i) {
        e eVar = (e) view.getLayoutParams();
        if (eVar.d()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        if (eVar.k != null) {
            a(view, eVar.k, i);
        } else if (eVar.e >= 0) {
            b(view, eVar.e, i);
        } else {
            d(view, i);
        }
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        measureChildWithMargins(view, i, i2, i3, i4);
    }

    @Override // android.support.v4.view.s
    public void a(View view, int i, int i2, int i3, int i4, int i5) {
        Behavior b2;
        int childCount = getChildCount();
        boolean z = false;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.c(i5) && (b2 = eVar.b()) != null) {
                    b2.a(this, childAt, view, i, i2, i3, i4, i5);
                    z = true;
                }
            }
        }
        if (z) {
            a(1);
        }
    }

    @Override // android.support.v4.view.s
    public void a(View view, int i, int i2, int[] iArr, int i3) {
        Behavior b2;
        int childCount = getChildCount();
        boolean z = false;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.c(i3) && (b2 = eVar.b()) != null) {
                    int[] iArr2 = this.q;
                    iArr2[1] = 0;
                    iArr2[0] = 0;
                    b2.a(this, (CoordinatorLayout) childAt, view, i, i2, iArr2, i3);
                    i4 = i > 0 ? Math.max(i4, this.q[0]) : Math.min(i4, this.q[0]);
                    i5 = i2 > 0 ? Math.max(i5, this.q[1]) : Math.min(i5, this.q[1]);
                    z = true;
                }
            }
        }
        iArr[0] = i4;
        iArr[1] = i5;
        if (z) {
            a(1);
        }
    }

    void a(View view, int i, Rect rect, Rect rect2) {
        e eVar = (e) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        a(view, i, rect, rect2, eVar, measuredWidth, measuredHeight);
        a(eVar, rect2, measuredWidth, measuredHeight);
    }

    void a(View view, Rect rect) {
        y.b(this, view, rect);
    }

    void a(View view, boolean z, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z) {
            a(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public boolean a(@af View view, int i, int i2) {
        Rect e2 = e();
        a(view, e2);
        try {
            return e2.contains(i, i2);
        } finally {
            a(e2);
        }
    }

    public boolean a(@af View view, @af View view2) {
        boolean z = false;
        if (view.getVisibility() == 0 && view2.getVisibility() == 0) {
            Rect e2 = e();
            a(view, view.getParent() != this, e2);
            Rect e3 = e();
            a(view2, view2.getParent() != this, e3);
            try {
                if (e2.left <= e3.right && e2.top <= e3.bottom && e2.right >= e3.left) {
                    if (e2.bottom >= e3.top) {
                        z = true;
                    }
                }
                return z;
            } finally {
                a(e2);
                a(e3);
            }
        }
        return false;
    }

    @Override // android.support.v4.view.s
    public boolean a(View view, View view2, int i, int i2) {
        int childCount = getChildCount();
        boolean z = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                Behavior b2 = eVar.b();
                if (b2 != null) {
                    boolean a2 = b2.a(this, (CoordinatorLayout) childAt, view, view2, i, i2);
                    eVar.a(i2, a2);
                    z |= a2;
                } else {
                    eVar.a(i2, false);
                }
            }
        }
        return z;
    }

    void b() {
        if (this.t) {
            if (this.x == null) {
                this.x = new f();
            }
            getViewTreeObserver().addOnPreDrawListener(this.x);
        }
        this.y = true;
    }

    public void b(@af View view) {
        List c2 = this.n.c(view);
        if (c2 == null || c2.isEmpty()) {
            return;
        }
        for (int i = 0; i < c2.size(); i++) {
            View view2 = (View) c2.get(i);
            Behavior b2 = ((e) view2.getLayoutParams()).b();
            if (b2 != null) {
                b2.b(this, (CoordinatorLayout) view2, view);
            }
        }
    }

    void b(View view, int i) {
        Behavior b2;
        e eVar = (e) view.getLayoutParams();
        if (eVar.k != null) {
            Rect e2 = e();
            Rect e3 = e();
            Rect e4 = e();
            a(eVar.k, e2);
            boolean z = false;
            a(view, false, e3);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            a(view, i, e2, e4, eVar, measuredWidth, measuredHeight);
            z = (e4.left == e3.left && e4.top == e3.top) ? true : true;
            a(eVar, e4, measuredWidth, measuredHeight);
            int i2 = e4.left - e3.left;
            int i3 = e4.top - e3.top;
            if (i2 != 0) {
                ab.n(view, i2);
            }
            if (i3 != 0) {
                ab.m(view, i3);
            }
            if (z && (b2 = eVar.b()) != null) {
                b2.b(this, (CoordinatorLayout) view, eVar.k);
            }
            a(e2);
            a(e3);
            a(e4);
        }
    }

    void b(View view, Rect rect) {
        ((e) view.getLayoutParams()).a(rect);
    }

    @Override // android.support.v4.view.s
    public void b(View view, View view2, int i, int i2) {
        Behavior b2;
        this.D.a(view, view2, i, i2);
        this.w = view2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            e eVar = (e) childAt.getLayoutParams();
            if (eVar.c(i2) && (b2 = eVar.b()) != null) {
                b2.b(this, childAt, view, view2, i, i2);
            }
        }
    }

    @af
    public List<View> c(@af View view) {
        List<View> d2 = this.n.d(view);
        this.p.clear();
        if (d2 != null) {
            this.p.addAll(d2);
        }
        return this.p;
    }

    void c() {
        if (this.t && this.x != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.x);
        }
        this.y = false;
    }

    @Override // android.support.v4.view.s
    public void c(View view, int i) {
        this.D.a(view, i);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            e eVar = (e) childAt.getLayoutParams();
            if (eVar.c(i)) {
                Behavior b2 = eVar.b();
                if (b2 != null) {
                    b2.a(this, (CoordinatorLayout) childAt, view, i);
                }
                eVar.b(i);
                eVar.h();
            }
        }
        this.w = null;
    }

    void c(View view, Rect rect) {
        rect.set(((e) view.getLayoutParams()).c());
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof e) && super.checkLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: d */
    public e generateDefaultLayoutParams() {
        return new e(-2, -2);
    }

    @af
    public List<View> d(@af View view) {
        List c2 = this.n.c(view);
        this.p.clear();
        if (c2 != null) {
            this.p.addAll(c2);
        }
        return this.p;
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j2) {
        e eVar = (e) view.getLayoutParams();
        if (eVar.a != null) {
            float b2 = eVar.a.b(this, view);
            if (b2 > 0.0f) {
                if (this.r == null) {
                    this.r = new Paint();
                }
                this.r.setColor(eVar.a.a(this, (CoordinatorLayout) view));
                this.r.setAlpha(a(Math.round(b2 * 255.0f), 0, 255));
                int save = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.r);
                canvas.restoreToCount(save);
            }
        }
        return super.drawChild(canvas, view, j2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.B;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = false | drawable.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    @av
    final List<View> getDependencySortedChildren() {
        f();
        return Collections.unmodifiableList(this.m);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public final ak getLastWindowInsets() {
        return this.z;
    }

    @Override // android.view.ViewGroup, android.support.v4.view.r
    public int getNestedScrollAxes() {
        return this.D.a();
    }

    @ag
    public Drawable getStatusBarBackground() {
        return this.B;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a(false);
        if (this.y) {
            if (this.x == null) {
                this.x = new f();
            }
            getViewTreeObserver().addOnPreDrawListener(this.x);
        }
        if (this.z == null && ab.R(this)) {
            ab.Q(this);
        }
        this.t = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a(false);
        if (this.y && this.x != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.x);
        }
        View view = this.w;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.t = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.A || this.B == null) {
            return;
        }
        ak akVar = this.z;
        int b2 = akVar != null ? akVar.b() : 0;
        if (b2 > 0) {
            this.B.setBounds(0, 0, getWidth(), b2);
            this.B.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            a(true);
        }
        boolean a2 = a(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            a(true);
        }
        return a2;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Behavior b2;
        int m = ab.m(this);
        int size = this.m.size();
        for (int i5 = 0; i5 < size; i5++) {
            View view = this.m.get(i5);
            if (view.getVisibility() != 8 && ((b2 = ((e) view.getLayoutParams()).b()) == null || !b2.a(this, (CoordinatorLayout) view, m))) {
                a(view, m);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0119, code lost:
        if (r0.a(r30, (android.support.design.widget.CoordinatorLayout) r20, r11, r21, r23, 0) == false) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x011c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r31, int r32) {
        /*
            Method dump skipped, instructions count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        Behavior b2;
        int childCount = getChildCount();
        boolean z2 = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.c(0) && (b2 = eVar.b()) != null) {
                    z2 |= b2.a(this, (CoordinatorLayout) childAt, view, f2, f3, z);
                }
            }
        }
        if (z2) {
            a(1);
        }
        return z2;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public boolean onNestedPreFling(View view, float f2, float f3) {
        Behavior b2;
        int childCount = getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.c(0) && (b2 = eVar.b()) != null) {
                    z |= b2.a(this, (CoordinatorLayout) childAt, view, f2, f3);
                }
            }
        }
        return z;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        a(view, i, i2, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        a(view, i, i2, i3, i4, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onNestedScrollAccepted(View view, View view2, int i) {
        b(view, view2, i, 0);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        SparseArray<Parcelable> sparseArray = savedState.a;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            Behavior b2 = a(childAt).b();
            if (id != -1 && b2 != null && (parcelable2 = sparseArray.get(id)) != null) {
                b2.a(this, (CoordinatorLayout) childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable d2;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            Behavior b2 = ((e) childAt.getLayoutParams()).b();
            if (id != -1 && b2 != null && (d2 = b2.d(this, childAt)) != null) {
                sparseArray.append(id, d2);
            }
        }
        savedState.a = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return a(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onStopNestedScroll(View view) {
        c(view, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        if (r3 != false) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r18.getActionMasked()
            android.view.View r3 = r0.v
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L15
            boolean r3 = r0.a(r1, r4)
            if (r3 == 0) goto L2b
            goto L16
        L15:
            r3 = 0
        L16:
            android.view.View r6 = r0.v
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            android.support.design.widget.CoordinatorLayout$e r6 = (android.support.design.widget.CoordinatorLayout.e) r6
            android.support.design.widget.CoordinatorLayout$Behavior r6 = r6.b()
            if (r6 == 0) goto L2b
            android.view.View r7 = r0.v
            boolean r6 = r6.b(r0, r7, r1)
            goto L2c
        L2b:
            r6 = 0
        L2c:
            android.view.View r7 = r0.v
            r8 = 0
            if (r7 != 0) goto L37
            boolean r1 = super.onTouchEvent(r18)
            r6 = r6 | r1
            goto L4a
        L37:
            if (r3 == 0) goto L4a
            long r11 = android.os.SystemClock.uptimeMillis()
            r13 = 3
            r14 = 0
            r15 = 0
            r16 = 0
            r9 = r11
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16)
            super.onTouchEvent(r8)
        L4a:
            if (r8 == 0) goto L4f
            r8.recycle()
        L4f:
            if (r2 == r4) goto L54
            r1 = 3
            if (r2 != r1) goto L57
        L54:
            r0.a(r5)
        L57:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        Behavior b2 = ((e) view.getLayoutParams()).b();
        if (b2 == null || !b2.a(this, (CoordinatorLayout) view, rect, z)) {
            return super.requestChildRectangleOnScreen(view, rect, z);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (!z || this.s) {
            return;
        }
        a(false);
        this.s = true;
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        g();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.i = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(@ag Drawable drawable) {
        Drawable drawable2 = this.B;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            this.B = drawable != null ? drawable.mutate() : null;
            Drawable drawable3 = this.B;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.B.setState(getDrawableState());
                }
                android.support.v4.graphics.drawable.a.b(this.B, ab.m(this));
                this.B.setVisible(getVisibility() == 0, false);
                this.B.setCallback(this);
            }
            ab.f(this);
        }
    }

    public void setStatusBarBackgroundColor(@k int i) {
        setStatusBarBackground(new ColorDrawable(i));
    }

    public void setStatusBarBackgroundResource(@android.support.annotation.p int i) {
        setStatusBarBackground(i != 0 ? android.support.v4.content.c.getDrawable(getContext(), i) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.B;
        if (drawable == null || drawable.isVisible() == z) {
            return;
        }
        this.B.setVisible(z, false);
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.B;
    }
}
