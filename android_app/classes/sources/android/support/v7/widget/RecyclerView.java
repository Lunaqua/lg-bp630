package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.an;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.a.c;
import android.support.v7.d.a;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.bb;
import android.support.v7.widget.bc;
import android.support.v7.widget.d;
import android.support.v7.widget.n;
import android.support.v7.widget.x;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class RecyclerView extends ViewGroup implements android.support.v4.view.p, android.support.v4.view.y {
    public static final int S = 0;
    public static final int T = 1;
    public static final int U = 2;
    static final long V = Long.MAX_VALUE;
    static final String a = "RecyclerView";
    private static final int aK = -1;
    static final Interpolator ak;
    private static final int[] al = {16843830};
    private static final int[] am = {16842987};
    private static final boolean an;
    private static final boolean ao;
    private static final String ap = "RV OnLayout";
    private static final String aq = "RV FullInvalidate";
    private static final String ar = "RV PartialInvalidate";
    private static final Class<?>[] as;
    static final boolean b = false;
    static final boolean c = false;
    static final boolean d;
    static final boolean e;
    static final boolean f;
    static final boolean g;
    static final boolean h = false;
    public static final int i = 0;
    public static final int j = 1;
    static final int k = 1;
    public static final int l = -1;
    public static final long m = -1;
    public static final int n = -1;
    public static final int o = 0;
    public static final int p = 1;
    static final int q = 2000;
    static final String r = "RV Scroll";
    static final String s = "RV OnBindView";
    static final String t = "RV Prefetch";
    static final String u = "RV Nested Prefetch";
    static final String v = "RV CreateView";
    boolean A;
    final Runnable B;
    final Rect C;
    final RectF D;
    a E;
    @android.support.annotation.av
    i F;
    q G;
    final ArrayList<h> H;
    boolean I;
    boolean J;
    boolean K;
    @android.support.annotation.av
    boolean L;
    boolean M;
    boolean N;
    boolean O;
    boolean P;
    boolean Q;
    f R;
    final w W;
    private int aA;
    private final AccessibilityManager aB;
    private List<j> aC;
    private int aD;
    private int aE;
    @android.support.annotation.af
    private e aF;
    private EdgeEffect aG;
    private EdgeEffect aH;
    private EdgeEffect aI;
    private EdgeEffect aJ;
    private int aL;
    private int aM;
    private VelocityTracker aN;
    private int aO;
    private int aP;
    private int aQ;
    private int aR;
    private int aS;
    private k aT;
    private final int aU;
    private final int aV;
    private float aW;
    private float aX;
    private boolean aY;
    private m aZ;
    android.support.v7.widget.x aa;
    x.a ab;
    final u ac;
    boolean ad;
    boolean ae;
    boolean af;
    ah ag;
    final int[] ah;
    final int[] ai;
    @android.support.annotation.av
    final List<x> aj;
    private final r at;
    private SavedState au;
    private final Rect av;
    private final ArrayList<l> aw;
    private l ax;
    private int ay;
    private boolean az;
    private List<m> ba;
    private f.c bb;
    private d bc;
    private final int[] bd;
    private android.support.v4.view.q be;
    private final int[] bf;
    private final int[] bg;
    private Runnable bh;
    private final bc.b bi;
    final p w;
    android.support.v7.widget.d x;
    android.support.v7.widget.n y;
    final bc z;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        x d;
        final Rect e;
        boolean f;
        boolean g;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.e = new Rect();
            this.f = true;
            this.g = false;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.e = new Rect();
            this.f = true;
            this.g = false;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.e = new Rect();
            this.f = true;
            this.g = false;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.e = new Rect();
            this.f = true;
            this.g = false;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.e = new Rect();
            this.f = true;
            this.g = false;
        }

        public boolean c() {
            return this.d.q();
        }

        public boolean d() {
            return this.d.p();
        }

        public boolean e() {
            return this.d.s();
        }

        public boolean f() {
            return this.d.B();
        }

        @Deprecated
        public int g() {
            return this.d.d();
        }

        public int h() {
            return this.d.e();
        }

        public int i() {
            return this.d.f();
        }
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.v7.widget.RecyclerView.SavedState.1
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
        Parcelable a;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readParcelable(classLoader == null ? i.class.getClassLoader() : classLoader);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        void a(SavedState savedState) {
            this.a = savedState.a;
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.a, 0);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class a<VH extends x> {
        private final b a = new b();
        private boolean b = false;

        public abstract int a();

        @android.support.annotation.af
        public abstract VH a(@android.support.annotation.af ViewGroup viewGroup, int i);

        public final void a(int i, int i2) {
            this.a.a(i, i2);
        }

        public final void a(int i, int i2, @android.support.annotation.ag Object obj) {
            this.a.a(i, i2, obj);
        }

        public final void a(int i, @android.support.annotation.ag Object obj) {
            this.a.a(i, 1, obj);
        }

        public void a(@android.support.annotation.af c cVar) {
            this.a.registerObserver(cVar);
        }

        public void a(@android.support.annotation.af VH vh) {
        }

        public abstract void a(@android.support.annotation.af VH vh, int i);

        public void a(@android.support.annotation.af VH vh, int i, @android.support.annotation.af List<Object> list) {
            a((a<VH>) vh, i);
        }

        public void a(@android.support.annotation.af RecyclerView recyclerView) {
        }

        public void a(boolean z) {
            if (c()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.b = z;
        }

        public int b(int i) {
            return 0;
        }

        @android.support.annotation.af
        public final VH b(@android.support.annotation.af ViewGroup viewGroup, int i) {
            try {
                android.support.v4.os.o.a(RecyclerView.v);
                VH a = a(viewGroup, i);
                if (a.a.getParent() == null) {
                    a.f = i;
                    return a;
                }
                throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
            } finally {
                android.support.v4.os.o.a();
            }
        }

        public final void b(int i, int i2) {
            this.a.d(i, i2);
        }

        public void b(@android.support.annotation.af c cVar) {
            this.a.unregisterObserver(cVar);
        }

        public final void b(@android.support.annotation.af VH vh, int i) {
            vh.c = i;
            if (b()) {
                vh.e = c(i);
            }
            vh.a(1, 519);
            android.support.v4.os.o.a(RecyclerView.s);
            a((a<VH>) vh, i, vh.w());
            vh.v();
            ViewGroup.LayoutParams layoutParams = vh.a.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                ((LayoutParams) layoutParams).f = true;
            }
            android.support.v4.os.o.a();
        }

        public void b(@android.support.annotation.af RecyclerView recyclerView) {
        }

        public final boolean b() {
            return this.b;
        }

        public boolean b(@android.support.annotation.af VH vh) {
            return false;
        }

        public long c(int i) {
            return -1L;
        }

        public final void c(int i, int i2) {
            this.a.b(i, i2);
        }

        public void c(@android.support.annotation.af VH vh) {
        }

        public final boolean c() {
            return this.a.a();
        }

        public final void d() {
            this.a.b();
        }

        public final void d(int i) {
            this.a.a(i, 1);
        }

        public final void d(int i, int i2) {
            this.a.c(i, i2);
        }

        public void d(@android.support.annotation.af VH vh) {
        }

        public final void e(int i) {
            this.a.b(i, 1);
        }

        public final void f(int i) {
            this.a.c(i, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b extends Observable<c> {
        b() {
        }

        public void a(int i, int i2) {
            a(i, i2, null);
        }

        public void a(int i, int i2, @android.support.annotation.ag Object obj) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a(i, i2, obj);
            }
        }

        public boolean a() {
            return !this.mObservers.isEmpty();
        }

        public void b() {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a();
            }
        }

        public void b(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).b(i, i2);
            }
        }

        public void c(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).c(i, i2);
            }
        }

        public void d(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a(i, i2, 1);
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class c {
        public void a() {
        }

        public void a(int i, int i2) {
        }

        public void a(int i, int i2, int i3) {
        }

        public void a(int i, int i2, @android.support.annotation.ag Object obj) {
            a(i, i2);
        }

        public void b(int i, int i2) {
        }

        public void c(int i, int i2) {
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface d {
        int a(int i, int i2);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class e {
        public static final int a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public @interface a {
        }

        @android.support.annotation.af
        protected EdgeEffect a(@android.support.annotation.af RecyclerView recyclerView, int i) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class f {
        public static final int h = 2;
        public static final int i = 8;
        public static final int j = 4;
        public static final int k = 2048;
        public static final int l = 4096;
        private c a = null;
        private ArrayList<b> b = new ArrayList<>();
        private long c = 120;
        private long d = 120;
        private long e = 250;
        private long f = 250;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public @interface a {
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public interface b {
            void a();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public interface c {
            void a(@android.support.annotation.af x xVar);
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static class d {
            public int a;
            public int b;
            public int c;
            public int d;
            public int e;

            @android.support.annotation.af
            public d a(@android.support.annotation.af x xVar) {
                return a(xVar, 0);
            }

            @android.support.annotation.af
            public d a(@android.support.annotation.af x xVar, int i) {
                View view = xVar.a;
                this.a = view.getLeft();
                this.b = view.getTop();
                this.c = view.getRight();
                this.d = view.getBottom();
                return this;
            }
        }

        static int e(x xVar) {
            int i2 = xVar.y & 14;
            if (xVar.p()) {
                return 4;
            }
            if ((i2 & 4) == 0) {
                int g = xVar.g();
                int f = xVar.f();
                return (g == -1 || f == -1 || g == f) ? i2 : i2 | 2048;
            }
            return i2;
        }

        @android.support.annotation.af
        public d a(@android.support.annotation.af u uVar, @android.support.annotation.af x xVar) {
            return j().a(xVar);
        }

        @android.support.annotation.af
        public d a(@android.support.annotation.af u uVar, @android.support.annotation.af x xVar, int i2, @android.support.annotation.af List<Object> list) {
            return j().a(xVar);
        }

        public abstract void a();

        public void a(long j2) {
            this.e = j2;
        }

        void a(c cVar) {
            this.a = cVar;
        }

        public final boolean a(@android.support.annotation.ag b bVar) {
            boolean b2 = b();
            if (bVar != null) {
                if (b2) {
                    this.b.add(bVar);
                } else {
                    bVar.a();
                }
            }
            return b2;
        }

        public abstract boolean a(@android.support.annotation.af x xVar, @android.support.annotation.af d dVar, @android.support.annotation.ag d dVar2);

        public abstract boolean a(@android.support.annotation.af x xVar, @android.support.annotation.af x xVar2, @android.support.annotation.af d dVar, @android.support.annotation.af d dVar2);

        public boolean a(@android.support.annotation.af x xVar, @android.support.annotation.af List<Object> list) {
            return j(xVar);
        }

        public void b(long j2) {
            this.c = j2;
        }

        public abstract boolean b();

        public abstract boolean b(@android.support.annotation.af x xVar, @android.support.annotation.ag d dVar, @android.support.annotation.af d dVar2);

        public void c(long j2) {
            this.d = j2;
        }

        public abstract boolean c(@android.support.annotation.af x xVar, @android.support.annotation.af d dVar, @android.support.annotation.af d dVar2);

        public abstract void d();

        public void d(long j2) {
            this.f = j2;
        }

        public abstract void d(@android.support.annotation.af x xVar);

        public long e() {
            return this.e;
        }

        public long f() {
            return this.c;
        }

        public final void f(@android.support.annotation.af x xVar) {
            g(xVar);
            c cVar = this.a;
            if (cVar != null) {
                cVar.a(xVar);
            }
        }

        public long g() {
            return this.d;
        }

        public void g(@android.support.annotation.af x xVar) {
        }

        public long h() {
            return this.f;
        }

        public final void h(@android.support.annotation.af x xVar) {
            i(xVar);
        }

        public final void i() {
            int size = this.b.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.b.get(i2).a();
            }
            this.b.clear();
        }

        public void i(@android.support.annotation.af x xVar) {
        }

        @android.support.annotation.af
        public d j() {
            return new d();
        }

        public boolean j(@android.support.annotation.af x xVar) {
            return true;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class g implements f.c {
        g() {
        }

        @Override // android.support.v7.widget.RecyclerView.f.c
        public void a(x xVar) {
            xVar.a(true);
            if (xVar.h != null && xVar.i == null) {
                xVar.h = null;
            }
            xVar.i = null;
            if (xVar.z() || RecyclerView.this.a(xVar.a) || !xVar.t()) {
                return;
            }
            RecyclerView.this.removeDetachedView(xVar.a, false);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class h {
        @Deprecated
        public void a(@android.support.annotation.af Canvas canvas, @android.support.annotation.af RecyclerView recyclerView) {
        }

        public void a(@android.support.annotation.af Canvas canvas, @android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af u uVar) {
            a(canvas, recyclerView);
        }

        @Deprecated
        public void a(@android.support.annotation.af Rect rect, int i, @android.support.annotation.af RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        public void a(@android.support.annotation.af Rect rect, @android.support.annotation.af View view, @android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af u uVar) {
            a(rect, ((LayoutParams) view.getLayoutParams()).h(), recyclerView);
        }

        @Deprecated
        public void b(@android.support.annotation.af Canvas canvas, @android.support.annotation.af RecyclerView recyclerView) {
        }

        public void b(@android.support.annotation.af Canvas canvas, @android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af u uVar) {
            b(canvas, recyclerView);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class i {
        int C;
        boolean D;
        private int e;
        private int f;
        private int g;
        private int h;
        android.support.v7.widget.n u;
        RecyclerView v;
        @android.support.annotation.ag
        t y;
        private final bb.b a = new bb.b() { // from class: android.support.v7.widget.RecyclerView.i.1
            @Override // android.support.v7.widget.bb.b
            public int a() {
                return i.this.G();
            }

            @Override // android.support.v7.widget.bb.b
            public int a(View view) {
                return i.this.o(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
            }

            @Override // android.support.v7.widget.bb.b
            public View a(int i) {
                return i.this.j(i);
            }

            @Override // android.support.v7.widget.bb.b
            public int b(View view) {
                return i.this.q(view) + ((LayoutParams) view.getLayoutParams()).rightMargin;
            }

            @Override // android.support.v7.widget.bb.b
            public View b() {
                return i.this.v;
            }

            @Override // android.support.v7.widget.bb.b
            public int c() {
                return i.this.L();
            }

            @Override // android.support.v7.widget.bb.b
            public int d() {
                return i.this.J() - i.this.N();
            }
        };
        private final bb.b b = new bb.b() { // from class: android.support.v7.widget.RecyclerView.i.2
            @Override // android.support.v7.widget.bb.b
            public int a() {
                return i.this.G();
            }

            @Override // android.support.v7.widget.bb.b
            public int a(View view) {
                return i.this.p(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
            }

            @Override // android.support.v7.widget.bb.b
            public View a(int i) {
                return i.this.j(i);
            }

            @Override // android.support.v7.widget.bb.b
            public int b(View view) {
                return i.this.r(view) + ((LayoutParams) view.getLayoutParams()).bottomMargin;
            }

            @Override // android.support.v7.widget.bb.b
            public View b() {
                return i.this.v;
            }

            @Override // android.support.v7.widget.bb.b
            public int c() {
                return i.this.M();
            }

            @Override // android.support.v7.widget.bb.b
            public int d() {
                return i.this.K() - i.this.O();
            }
        };
        bb w = new bb(this.a);
        bb x = new bb(this.b);
        boolean z = false;
        boolean A = false;
        boolean B = false;
        private boolean c = true;
        private boolean d = true;

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public interface a {
            void b(int i, int i2);
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static class b {
            public int a;
            public int b;
            public boolean c;
            public boolean d;
        }

        public static int a(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i2, i3) : size : Math.min(size, Math.max(i2, i3));
        }

        public static int a(int i, int i2, int i3, int i4, boolean z) {
            int i5;
            int i6 = i - i3;
            int i7 = 0;
            int max = Math.max(0, i6);
            if (z) {
                if (i4 < 0) {
                    if (i4 == -1) {
                        if (i2 == Integer.MIN_VALUE || (i2 != 0 && i2 == 1073741824)) {
                            i5 = max;
                        } else {
                            i2 = 0;
                            i5 = 0;
                        }
                        i7 = i2;
                        max = i5;
                    }
                    max = 0;
                }
                max = i4;
                i7 = 1073741824;
            } else {
                if (i4 < 0) {
                    if (i4 == -1) {
                        i7 = i2;
                    } else {
                        if (i4 == -2) {
                            if (i2 == Integer.MIN_VALUE || i2 == 1073741824) {
                                i7 = Integer.MIN_VALUE;
                            }
                        }
                        max = 0;
                    }
                }
                max = i4;
                i7 = 1073741824;
            }
            return View.MeasureSpec.makeMeasureSpec(max, i7);
        }

        /* JADX WARN: Code restructure failed: missing block: B:4:0x000a, code lost:
            if (r3 >= 0) goto L8;
         */
        @java.lang.Deprecated
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static int a(int r1, int r2, int r3, boolean r4) {
            /*
                int r1 = r1 - r2
                r2 = 0
                int r1 = java.lang.Math.max(r2, r1)
                r0 = 1073741824(0x40000000, float:2.0)
                if (r4 == 0) goto Lf
                if (r3 < 0) goto Ld
                goto L11
            Ld:
                r1 = 0
                goto L1e
            Lf:
                if (r3 < 0) goto L15
            L11:
                r1 = r3
            L12:
                r2 = 1073741824(0x40000000, float:2.0)
                goto L1e
            L15:
                r4 = -1
                if (r3 != r4) goto L19
                goto L12
            L19:
                r4 = -2
                if (r3 != r4) goto Ld
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
            L1e:
                int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.i.a(int, int, int, boolean):int");
        }

        public static b a(@android.support.annotation.af Context context, @android.support.annotation.ag AttributeSet attributeSet, int i, int i2) {
            b bVar = new b();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.j.RecyclerView, i, i2);
            bVar.a = obtainStyledAttributes.getInt(a.j.RecyclerView_android_orientation, 1);
            bVar.b = obtainStyledAttributes.getInt(a.j.RecyclerView_spanCount, 1);
            bVar.c = obtainStyledAttributes.getBoolean(a.j.RecyclerView_reverseLayout, false);
            bVar.d = obtainStyledAttributes.getBoolean(a.j.RecyclerView_stackFromEnd, false);
            obtainStyledAttributes.recycle();
            return bVar;
        }

        private void a(int i, @android.support.annotation.af View view) {
            this.u.e(i);
        }

        private void a(p pVar, int i, View view) {
            x e = RecyclerView.e(view);
            if (e.c()) {
                return;
            }
            if (e.p() && !e.s() && !this.v.E.b()) {
                h(i);
                pVar.b(e);
                return;
            }
            i(i);
            pVar.d(view);
            this.v.z.h(e);
        }

        private void a(View view, int i, boolean z) {
            x e = RecyclerView.e(view);
            if (z || e.s()) {
                this.v.z.e(e);
            } else {
                this.v.z.f(e);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (e.l() || e.j()) {
                if (e.j()) {
                    e.k();
                } else {
                    e.m();
                }
                this.u.a(view, i, view.getLayoutParams(), false);
            } else if (view.getParent() == this.v) {
                int b2 = this.u.b(view);
                if (i == -1) {
                    i = this.u.b();
                }
                if (b2 == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.v.indexOfChild(view) + this.v.a());
                } else if (b2 != i) {
                    this.v.F.f(b2, i);
                }
            } else {
                this.u.a(view, i, false);
                layoutParams.f = true;
                t tVar = this.y;
                if (tVar != null && tVar.h()) {
                    this.y.b(view);
                }
            }
            if (layoutParams.g) {
                e.a.invalidate();
                layoutParams.g = false;
            }
        }

        private static boolean b(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (i3 <= 0 || i == i3) {
                if (mode == Integer.MIN_VALUE) {
                    return size >= i;
                } else if (mode != 0) {
                    return mode == 1073741824 && size == i;
                } else {
                    return true;
                }
            }
            return false;
        }

        private int[] b(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            int[] iArr = new int[2];
            int L = L();
            int M = M();
            int J = J() - N();
            int K = K() - O();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int width = rect.width() + left;
            int height = rect.height() + top;
            int i = left - L;
            int min = Math.min(0, i);
            int i2 = top - M;
            int min2 = Math.min(0, i2);
            int i3 = width - J;
            int max = Math.max(0, i3);
            int max2 = Math.max(0, height - K);
            if (D() != 1) {
                if (min == 0) {
                    min = Math.min(i, max);
                }
                max = min;
            } else if (max == 0) {
                max = Math.max(min, i3);
            }
            if (min2 == 0) {
                min2 = Math.min(i2, max2);
            }
            iArr[0] = max;
            iArr[1] = min2;
            return iArr;
        }

        private boolean d(RecyclerView recyclerView, int i, int i2) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int L = L();
            int M = M();
            int J = J() - N();
            int K = K() - O();
            Rect rect = this.v.C;
            a(focusedChild, rect);
            return rect.left - i < J && rect.right - i > L && rect.top - i2 < K && rect.bottom - i2 > M;
        }

        public boolean A() {
            return this.A;
        }

        public boolean B() {
            RecyclerView recyclerView = this.v;
            return recyclerView != null && recyclerView.A;
        }

        public boolean C() {
            t tVar = this.y;
            return tVar != null && tVar.h();
        }

        public int D() {
            return android.support.v4.view.ab.m(this.v);
        }

        public void E() {
            for (int G = G() - 1; G >= 0; G--) {
                this.u.a(G);
            }
        }

        public int F() {
            return -1;
        }

        public int G() {
            android.support.v7.widget.n nVar = this.u;
            if (nVar != null) {
                return nVar.b();
            }
            return 0;
        }

        public int H() {
            return this.e;
        }

        public int I() {
            return this.f;
        }

        @android.support.annotation.ai
        public int J() {
            return this.g;
        }

        @android.support.annotation.ai
        public int K() {
            return this.h;
        }

        @android.support.annotation.ai
        public int L() {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        @android.support.annotation.ai
        public int M() {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        @android.support.annotation.ai
        public int N() {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        @android.support.annotation.ai
        public int O() {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        @android.support.annotation.ai
        public int P() {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                return android.support.v4.view.ab.t(recyclerView);
            }
            return 0;
        }

        @android.support.annotation.ai
        public int Q() {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                return android.support.v4.view.ab.u(recyclerView);
            }
            return 0;
        }

        public boolean R() {
            RecyclerView recyclerView = this.v;
            return recyclerView != null && recyclerView.isFocused();
        }

        public boolean S() {
            RecyclerView recyclerView = this.v;
            return recyclerView != null && recyclerView.hasFocus();
        }

        @android.support.annotation.ag
        public View T() {
            View focusedChild;
            RecyclerView recyclerView = this.v;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.u.c(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public int U() {
            RecyclerView recyclerView = this.v;
            a adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.a();
            }
            return 0;
        }

        public boolean V() {
            return this.c;
        }

        @android.support.annotation.ai
        public int W() {
            return android.support.v4.view.ab.A(this.v);
        }

        @android.support.annotation.ai
        public int X() {
            return android.support.v4.view.ab.B(this.v);
        }

        void Y() {
            t tVar = this.y;
            if (tVar != null) {
                tVar.f();
            }
        }

        public void Z() {
            this.z = true;
        }

        public int a(int i, p pVar, u uVar) {
            return 0;
        }

        public int a(@android.support.annotation.af p pVar, @android.support.annotation.af u uVar) {
            RecyclerView recyclerView = this.v;
            if (recyclerView == null || recyclerView.E == null || !i()) {
                return 1;
            }
            return this.v.E.a();
        }

        public abstract LayoutParams a();

        public LayoutParams a(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public LayoutParams a(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
        }

        @android.support.annotation.ag
        public View a(@android.support.annotation.af View view, int i, @android.support.annotation.af p pVar, @android.support.annotation.af u uVar) {
            return null;
        }

        public void a(int i, int i2, u uVar, a aVar) {
        }

        public void a(int i, a aVar) {
        }

        public void a(int i, @android.support.annotation.af p pVar) {
            a(pVar, i, j(i));
        }

        public void a(Rect rect, int i, int i2) {
            g(a(i, rect.width() + L() + N(), W()), a(i2, rect.height() + M() + O(), X()));
        }

        public void a(Parcelable parcelable) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(android.support.v4.view.a.c cVar) {
            a(this.v.w, this.v.ac, cVar);
        }

        public void a(@android.support.annotation.ag a aVar, @android.support.annotation.ag a aVar2) {
        }

        public void a(@android.support.annotation.af p pVar) {
            for (int G = G() - 1; G >= 0; G--) {
                a(pVar, G, j(G));
            }
        }

        public void a(@android.support.annotation.af p pVar, @android.support.annotation.af u uVar, int i, int i2) {
            this.v.f(i, i2);
        }

        public void a(@android.support.annotation.af p pVar, @android.support.annotation.af u uVar, @android.support.annotation.af android.support.v4.view.a.c cVar) {
            if (this.v.canScrollVertically(-1) || this.v.canScrollHorizontally(-1)) {
                cVar.d(8192);
                cVar.l(true);
            }
            if (this.v.canScrollVertically(1) || this.v.canScrollHorizontally(1)) {
                cVar.d(4096);
                cVar.l(true);
            }
            cVar.b(c.b.a(a(pVar, uVar), b(pVar, uVar), e(pVar, uVar), d(pVar, uVar)));
        }

        public void a(@android.support.annotation.af p pVar, @android.support.annotation.af u uVar, @android.support.annotation.af View view, @android.support.annotation.af android.support.v4.view.a.c cVar) {
            cVar.c(c.C0032c.a(i() ? e(view) : 0, 1, h() ? e(view) : 0, 1, false, false));
        }

        public void a(@android.support.annotation.af p pVar, @android.support.annotation.af u uVar, @android.support.annotation.af AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.v;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.v.canScrollVertically(-1) && !this.v.canScrollHorizontally(-1) && !this.v.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            if (this.v.E != null) {
                accessibilityEvent.setItemCount(this.v.E.a());
            }
        }

        public void a(t tVar) {
            t tVar2 = this.y;
            if (tVar2 != null && tVar != tVar2 && tVar2.h()) {
                this.y.f();
            }
            this.y = tVar;
            this.y.a(this.v, this);
        }

        public void a(u uVar) {
        }

        public void a(@android.support.annotation.af RecyclerView recyclerView) {
        }

        public void a(@android.support.annotation.af RecyclerView recyclerView, int i, int i2) {
        }

        public void a(@android.support.annotation.af RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public void a(@android.support.annotation.af RecyclerView recyclerView, int i, int i2, @android.support.annotation.ag Object obj) {
            c(recyclerView, i, i2);
        }

        @android.support.annotation.i
        public void a(RecyclerView recyclerView, p pVar) {
            e(recyclerView);
        }

        public void a(RecyclerView recyclerView, u uVar, int i) {
            Log.e(RecyclerView.a, "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void a(View view) {
            if (this.v.R != null) {
                this.v.R.d(RecyclerView.e(view));
            }
        }

        public void a(View view, int i) {
            a(view, i, true);
        }

        public void a(@android.support.annotation.af View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect l = this.v.l(view);
            int i3 = i + l.left + l.right;
            int i4 = i2 + l.top + l.bottom;
            int a2 = a(J(), H(), L() + N() + i3, layoutParams.width, h());
            int a3 = a(K(), I(), M() + O() + i4, layoutParams.height, i());
            if (b(view, a2, a3, layoutParams)) {
                view.measure(a2, a3);
            }
        }

        public void a(@android.support.annotation.af View view, int i, int i2, int i3, int i4) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).e;
            view.layout(i + rect.left, i2 + rect.top, i3 - rect.right, i4 - rect.bottom);
        }

        public void a(@android.support.annotation.af View view, int i, LayoutParams layoutParams) {
            x e = RecyclerView.e(view);
            if (e.s()) {
                this.v.z.e(e);
            } else {
                this.v.z.f(e);
            }
            this.u.a(view, i, layoutParams, e.s());
        }

        public void a(@android.support.annotation.af View view, @android.support.annotation.af Rect rect) {
            RecyclerView.b(view, rect);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(View view, android.support.v4.view.a.c cVar) {
            x e = RecyclerView.e(view);
            if (e == null || e.s() || this.u.c(e.a)) {
                return;
            }
            a(this.v.w, this.v.ac, view, cVar);
        }

        public void a(@android.support.annotation.af View view, @android.support.annotation.af p pVar) {
            a(pVar, this.u.b(view), view);
        }

        public void a(@android.support.annotation.af View view, boolean z, @android.support.annotation.af Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((LayoutParams) view.getLayoutParams()).e;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.v != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.v.D;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public void a(@android.support.annotation.af AccessibilityEvent accessibilityEvent) {
            a(this.v.w, this.v.ac, accessibilityEvent);
        }

        public void a(Runnable runnable) {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                android.support.v4.view.ab.a(recyclerView, runnable);
            }
        }

        public void a(String str) {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                recyclerView.b(str);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(int i, @android.support.annotation.ag Bundle bundle) {
            return a(this.v.w, this.v.ac, i, bundle);
        }

        public boolean a(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0070 A[ADDED_TO_REGION] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean a(@android.support.annotation.af android.support.v7.widget.RecyclerView.p r2, @android.support.annotation.af android.support.v7.widget.RecyclerView.u r3, int r4, @android.support.annotation.ag android.os.Bundle r5) {
            /*
                r1 = this;
                android.support.v7.widget.RecyclerView r2 = r1.v
                r3 = 0
                if (r2 != 0) goto L6
                return r3
            L6:
                r5 = 4096(0x1000, float:5.74E-42)
                r0 = 1
                if (r4 == r5) goto L42
                r5 = 8192(0x2000, float:1.148E-41)
                if (r4 == r5) goto L12
                r2 = 0
            L10:
                r4 = 0
                goto L6e
            L12:
                r4 = -1
                boolean r2 = r2.canScrollVertically(r4)
                if (r2 == 0) goto L29
                int r2 = r1.K()
                int r5 = r1.M()
                int r2 = r2 - r5
                int r5 = r1.O()
                int r2 = r2 - r5
                int r2 = -r2
                goto L2a
            L29:
                r2 = 0
            L2a:
                android.support.v7.widget.RecyclerView r5 = r1.v
                boolean r4 = r5.canScrollHorizontally(r4)
                if (r4 == 0) goto L10
                int r4 = r1.J()
                int r5 = r1.L()
                int r4 = r4 - r5
                int r5 = r1.N()
                int r4 = r4 - r5
                int r4 = -r4
                goto L6e
            L42:
                boolean r2 = r2.canScrollVertically(r0)
                if (r2 == 0) goto L57
                int r2 = r1.K()
                int r4 = r1.M()
                int r2 = r2 - r4
                int r4 = r1.O()
                int r2 = r2 - r4
                goto L58
            L57:
                r2 = 0
            L58:
                android.support.v7.widget.RecyclerView r4 = r1.v
                boolean r4 = r4.canScrollHorizontally(r0)
                if (r4 == 0) goto L10
                int r4 = r1.J()
                int r5 = r1.L()
                int r4 = r4 - r5
                int r5 = r1.N()
                int r4 = r4 - r5
            L6e:
                if (r2 != 0) goto L73
                if (r4 != 0) goto L73
                return r3
            L73:
                android.support.v7.widget.RecyclerView r3 = r1.v
                r3.b(r4, r2)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.i.a(android.support.v7.widget.RecyclerView$p, android.support.v7.widget.RecyclerView$u, int, android.os.Bundle):boolean");
        }

        public boolean a(@android.support.annotation.af p pVar, @android.support.annotation.af u uVar, @android.support.annotation.af View view, int i, @android.support.annotation.ag Bundle bundle) {
            return false;
        }

        public boolean a(@android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af u uVar, @android.support.annotation.af View view, @android.support.annotation.ag View view2) {
            return a(recyclerView, view, view2);
        }

        public boolean a(@android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af View view, @android.support.annotation.af Rect rect, boolean z) {
            return a(recyclerView, view, rect, z, false);
        }

        public boolean a(@android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af View view, @android.support.annotation.af Rect rect, boolean z, boolean z2) {
            int[] b2 = b(recyclerView, view, rect, z);
            int i = b2[0];
            int i2 = b2[1];
            if ((!z2 || d(recyclerView, i, i2)) && !(i == 0 && i2 == 0)) {
                if (z) {
                    recyclerView.scrollBy(i, i2);
                } else {
                    recyclerView.b(i, i2);
                }
                return true;
            }
            return false;
        }

        @Deprecated
        public boolean a(@android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af View view, @android.support.annotation.ag View view2) {
            return C() || recyclerView.s();
        }

        public boolean a(@android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af ArrayList<View> arrayList, int i, int i2) {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(View view, int i, int i2, LayoutParams layoutParams) {
            return (this.c && b(view.getMeasuredWidth(), i, layoutParams.width) && b(view.getMeasuredHeight(), i2, layoutParams.height)) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(@android.support.annotation.af View view, int i, @android.support.annotation.ag Bundle bundle) {
            return a(this.v.w, this.v.ac, view, i, bundle);
        }

        public boolean a(@android.support.annotation.af View view, boolean z, boolean z2) {
            boolean z3 = this.w.a(view, 24579) && this.x.a(view, 24579);
            return z ? z3 : !z3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean aa() {
            int G = G();
            for (int i = 0; i < G; i++) {
                ViewGroup.LayoutParams layoutParams = j(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public int b(int i, p pVar, u uVar) {
            return 0;
        }

        public int b(@android.support.annotation.af p pVar, @android.support.annotation.af u uVar) {
            RecyclerView recyclerView = this.v;
            if (recyclerView == null || recyclerView.E == null || !h()) {
                return 1;
            }
            return this.v.E.a();
        }

        public void b(int i, @android.support.annotation.af p pVar) {
            View j = j(i);
            h(i);
            pVar.a(j);
        }

        void b(p pVar) {
            int e = pVar.e();
            for (int i = e - 1; i >= 0; i--) {
                View e2 = pVar.e(i);
                x e3 = RecyclerView.e(e2);
                if (!e3.c()) {
                    e3.a(false);
                    if (e3.t()) {
                        this.v.removeDetachedView(e2, false);
                    }
                    if (this.v.R != null) {
                        this.v.R.d(e3);
                    }
                    e3.a(true);
                    pVar.c(e2);
                }
            }
            pVar.f();
            if (e > 0) {
                this.v.invalidate();
            }
        }

        void b(t tVar) {
            if (this.y == tVar) {
                this.y = null;
            }
        }

        void b(RecyclerView recyclerView) {
            int height;
            if (recyclerView == null) {
                this.v = null;
                this.u = null;
                height = 0;
                this.g = 0;
            } else {
                this.v = recyclerView;
                this.u = recyclerView.y;
                this.g = recyclerView.getWidth();
                height = recyclerView.getHeight();
            }
            this.h = height;
            this.e = 1073741824;
            this.f = 1073741824;
        }

        public void b(@android.support.annotation.af RecyclerView recyclerView, int i, int i2) {
        }

        void b(RecyclerView recyclerView, p pVar) {
            this.A = false;
            a(recyclerView, pVar);
        }

        public void b(View view) {
            a(view, -1);
        }

        public void b(View view, int i) {
            a(view, i, false);
        }

        public void b(@android.support.annotation.af View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect l = this.v.l(view);
            int i3 = i + l.left + l.right;
            int i4 = i2 + l.top + l.bottom;
            int a2 = a(J(), H(), L() + N() + layoutParams.leftMargin + layoutParams.rightMargin + i3, layoutParams.width, h());
            int a3 = a(K(), I(), M() + O() + layoutParams.topMargin + layoutParams.bottomMargin + i4, layoutParams.height, i());
            if (b(view, a2, a3, layoutParams)) {
                view.measure(a2, a3);
            }
        }

        public void b(@android.support.annotation.af View view, int i, int i2, int i3, int i4) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.e;
            view.layout(i + rect.left + layoutParams.leftMargin, i2 + rect.top + layoutParams.topMargin, (i3 - rect.right) - layoutParams.rightMargin, (i4 - rect.bottom) - layoutParams.bottomMargin);
        }

        public void b(@android.support.annotation.af View view, @android.support.annotation.af Rect rect) {
            RecyclerView recyclerView = this.v;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.l(view));
            }
        }

        public void b(@android.support.annotation.af View view, @android.support.annotation.af p pVar) {
            d(view);
            pVar.a(view);
        }

        public void b(String str) {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                recyclerView.a(str);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean b(View view, int i, int i2, LayoutParams layoutParams) {
            return (!view.isLayoutRequested() && this.c && b(view.getWidth(), i, layoutParams.width) && b(view.getHeight(), i2, layoutParams.height)) ? false : true;
        }

        public boolean b(Runnable runnable) {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public int c(@android.support.annotation.af u uVar) {
            return 0;
        }

        @android.support.annotation.ag
        public View c(int i) {
            int G = G();
            for (int i2 = 0; i2 < G; i2++) {
                View j = j(i2);
                x e = RecyclerView.e(j);
                if (e != null && e.e() == i && !e.c() && (this.v.ac.c() || !e.s())) {
                    return j;
                }
            }
            return null;
        }

        public void c(@android.support.annotation.af p pVar) {
            for (int G = G() - 1; G >= 0; G--) {
                if (!RecyclerView.e(j(G)).c()) {
                    b(G, pVar);
                }
            }
        }

        public void c(p pVar, u uVar) {
            Log.e(RecyclerView.a, "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        void c(RecyclerView recyclerView) {
            this.A = true;
            d(recyclerView);
        }

        public void c(@android.support.annotation.af RecyclerView recyclerView, int i, int i2) {
        }

        public void c(View view) {
            b(view, -1);
        }

        public void c(@android.support.annotation.af View view, int i) {
            a(view, i, (LayoutParams) view.getLayoutParams());
        }

        public int d(@android.support.annotation.af p pVar, @android.support.annotation.af u uVar) {
            return 0;
        }

        public int d(@android.support.annotation.af u uVar) {
            return 0;
        }

        @android.support.annotation.ag
        public View d(@android.support.annotation.af View view, int i) {
            return null;
        }

        void d(int i, int i2) {
            this.g = View.MeasureSpec.getSize(i);
            this.e = View.MeasureSpec.getMode(i);
            if (this.e == 0 && !RecyclerView.e) {
                this.g = 0;
            }
            this.h = View.MeasureSpec.getSize(i2);
            this.f = View.MeasureSpec.getMode(i2);
            if (this.f != 0 || RecyclerView.e) {
                return;
            }
            this.h = 0;
        }

        @android.support.annotation.i
        public void d(RecyclerView recyclerView) {
        }

        public void d(View view) {
            this.u.a(view);
        }

        public boolean d() {
            return false;
        }

        public int e(@android.support.annotation.af u uVar) {
            return 0;
        }

        public int e(@android.support.annotation.af View view) {
            return ((LayoutParams) view.getLayoutParams()).h();
        }

        public void e(int i) {
        }

        void e(int i, int i2) {
            int G = G();
            if (G == 0) {
                this.v.f(i, i2);
                return;
            }
            int i3 = ActivityChooserView.a.a;
            int i4 = ActivityChooserView.a.a;
            int i5 = Integer.MIN_VALUE;
            int i6 = Integer.MIN_VALUE;
            for (int i7 = 0; i7 < G; i7++) {
                View j = j(i7);
                Rect rect = this.v.C;
                a(j, rect);
                if (rect.left < i3) {
                    i3 = rect.left;
                }
                if (rect.right > i5) {
                    i5 = rect.right;
                }
                if (rect.top < i4) {
                    i4 = rect.top;
                }
                if (rect.bottom > i6) {
                    i6 = rect.bottom;
                }
            }
            this.v.C.set(i3, i4, i5, i6);
            a(this.v.C, i, i2);
        }

        @Deprecated
        public void e(RecyclerView recyclerView) {
        }

        @Deprecated
        public void e(boolean z) {
            this.B = z;
        }

        public boolean e() {
            return this.B;
        }

        public boolean e(@android.support.annotation.af p pVar, @android.support.annotation.af u uVar) {
            return false;
        }

        public int f(@android.support.annotation.af u uVar) {
            return 0;
        }

        public int f(@android.support.annotation.af View view) {
            return RecyclerView.e(view).i();
        }

        public void f(int i, int i2) {
            View j = j(i);
            if (j != null) {
                i(i);
                c(j, i2);
                return;
            }
            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i + this.v.toString());
        }

        void f(RecyclerView recyclerView) {
            d(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        public final void f(boolean z) {
            if (z != this.d) {
                this.d = z;
                this.C = 0;
                RecyclerView recyclerView = this.v;
                if (recyclerView != null) {
                    recyclerView.w.b();
                }
            }
        }

        public int g(@android.support.annotation.af u uVar) {
            return 0;
        }

        @android.support.annotation.ag
        public Parcelable g() {
            return null;
        }

        @android.support.annotation.ag
        public View g(@android.support.annotation.af View view) {
            View c;
            RecyclerView recyclerView = this.v;
            if (recyclerView == null || (c = recyclerView.c(view)) == null || this.u.c(c)) {
                return null;
            }
            return c;
        }

        public void g(int i, int i2) {
            this.v.setMeasuredDimension(i, i2);
        }

        public void g(boolean z) {
            this.c = z;
        }

        public int h(@android.support.annotation.af u uVar) {
            return 0;
        }

        public void h(int i) {
            if (j(i) != null) {
                this.u.a(i);
            }
        }

        public void h(@android.support.annotation.af View view) {
            int b2 = this.u.b(view);
            if (b2 >= 0) {
                a(b2, view);
            }
        }

        public boolean h() {
            return false;
        }

        public void i(int i) {
            a(i, j(i));
        }

        public void i(@android.support.annotation.af View view) {
            c(view, -1);
        }

        public boolean i() {
            return false;
        }

        @android.support.annotation.ag
        public View j(int i) {
            android.support.v7.widget.n nVar = this.u;
            if (nVar != null) {
                return nVar.b(i);
            }
            return null;
        }

        public void j(@android.support.annotation.af View view) {
            this.v.removeDetachedView(view, false);
        }

        public void k(@android.support.annotation.ai int i) {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                recyclerView.l(i);
            }
        }

        public void k(@android.support.annotation.af View view) {
            ViewParent parent = view.getParent();
            RecyclerView recyclerView = this.v;
            if (parent != recyclerView || recyclerView.indexOfChild(view) == -1) {
                throw new IllegalArgumentException("View should be fully attached to be ignored" + this.v.a());
            }
            x e = RecyclerView.e(view);
            e.b(128);
            this.v.z.g(e);
        }

        public void l(@android.support.annotation.ai int i) {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                recyclerView.k(i);
            }
        }

        public void l(@android.support.annotation.af View view) {
            x e = RecyclerView.e(view);
            e.o();
            e.x();
            e.b(4);
        }

        public int m(@android.support.annotation.af View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).e;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public void m(int i) {
        }

        public int n(@android.support.annotation.af View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).e;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public int o(@android.support.annotation.af View view) {
            return view.getLeft() - u(view);
        }

        public int p(@android.support.annotation.af View view) {
            return view.getTop() - s(view);
        }

        public int q(@android.support.annotation.af View view) {
            return view.getRight() + v(view);
        }

        public int r(@android.support.annotation.af View view) {
            return view.getBottom() + t(view);
        }

        public int s(@android.support.annotation.af View view) {
            return ((LayoutParams) view.getLayoutParams()).e.top;
        }

        boolean s() {
            return false;
        }

        public int t(@android.support.annotation.af View view) {
            return ((LayoutParams) view.getLayoutParams()).e.bottom;
        }

        public int u(@android.support.annotation.af View view) {
            return ((LayoutParams) view.getLayoutParams()).e.left;
        }

        public int v(@android.support.annotation.af View view) {
            return ((LayoutParams) view.getLayoutParams()).e.right;
        }

        public void y() {
            RecyclerView recyclerView = this.v;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public final boolean z() {
            return this.d;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface j {
        void a(@android.support.annotation.af View view);

        void b(@android.support.annotation.af View view);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class k {
        public abstract boolean a(int i, int i2);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface l {
        void a(boolean z);

        boolean a(@android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af MotionEvent motionEvent);

        void b(@android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af MotionEvent motionEvent);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class m {
        public void a(@android.support.annotation.af RecyclerView recyclerView, int i) {
        }

        public void a(@android.support.annotation.af RecyclerView recyclerView, int i, int i2) {
        }
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface n {
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class o {
        private static final int b = 5;
        SparseArray<a> a = new SparseArray<>();
        private int c = 0;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static class a {
            final ArrayList<x> a = new ArrayList<>();
            int b = 5;
            long c = 0;
            long d = 0;

            a() {
            }
        }

        private a c(int i) {
            a aVar = this.a.get(i);
            if (aVar == null) {
                a aVar2 = new a();
                this.a.put(i, aVar2);
                return aVar2;
            }
            return aVar;
        }

        public int a(int i) {
            return c(i).a.size();
        }

        long a(long j, long j2) {
            return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
        }

        public void a() {
            for (int i = 0; i < this.a.size(); i++) {
                this.a.valueAt(i).a.clear();
            }
        }

        public void a(int i, int i2) {
            a c = c(i);
            c.b = i2;
            ArrayList<x> arrayList = c.a;
            while (arrayList.size() > i2) {
                arrayList.remove(arrayList.size() - 1);
            }
        }

        void a(int i, long j) {
            a c = c(i);
            c.c = a(c.c, j);
        }

        void a(a aVar, a aVar2, boolean z) {
            if (aVar != null) {
                d();
            }
            if (!z && this.c == 0) {
                a();
            }
            if (aVar2 != null) {
                c();
            }
        }

        public void a(x xVar) {
            int i = xVar.i();
            ArrayList<x> arrayList = c(i).a;
            if (this.a.get(i).b <= arrayList.size()) {
                return;
            }
            xVar.x();
            arrayList.add(xVar);
        }

        boolean a(int i, long j, long j2) {
            long j3 = c(i).c;
            return j3 == 0 || j + j3 < j2;
        }

        int b() {
            int i = 0;
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                ArrayList<x> arrayList = this.a.valueAt(i2).a;
                if (arrayList != null) {
                    i += arrayList.size();
                }
            }
            return i;
        }

        @android.support.annotation.ag
        public x b(int i) {
            a aVar = this.a.get(i);
            if (aVar == null || aVar.a.isEmpty()) {
                return null;
            }
            ArrayList<x> arrayList = aVar.a;
            return arrayList.remove(arrayList.size() - 1);
        }

        void b(int i, long j) {
            a c = c(i);
            c.d = a(c.d, j);
        }

        boolean b(int i, long j, long j2) {
            long j3 = c(i).d;
            return j3 == 0 || j + j3 < j2;
        }

        void c() {
            this.c++;
        }

        void d() {
            this.c--;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public final class p {
        static final int f = 2;
        o e;
        private v j;
        final ArrayList<x> a = new ArrayList<>();
        ArrayList<x> b = null;
        final ArrayList<x> c = new ArrayList<>();
        private final List<x> h = Collections.unmodifiableList(this.a);
        private int i = 2;
        int d = 2;

        public p() {
        }

        private void a(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, true);
                }
            }
            if (z) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                    return;
                }
                int visibility = viewGroup.getVisibility();
                viewGroup.setVisibility(4);
                viewGroup.setVisibility(visibility);
            }
        }

        private boolean a(@android.support.annotation.af x xVar, int i, int i2, long j) {
            xVar.E = RecyclerView.this;
            int i3 = xVar.i();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j == RecyclerView.V || this.e.b(i3, nanoTime, j)) {
                RecyclerView.this.E.b((a) xVar, i);
                this.e.b(xVar.i(), RecyclerView.this.getNanoTime() - nanoTime);
                e(xVar);
                if (RecyclerView.this.ac.c()) {
                    xVar.g = i2;
                    return true;
                }
                return true;
            }
            return false;
        }

        private void e(x xVar) {
            if (RecyclerView.this.r()) {
                View view = xVar.a;
                if (android.support.v4.view.ab.g(view) == 0) {
                    android.support.v4.view.ab.e(view, 1);
                }
                if (android.support.v4.view.ab.d(view)) {
                    return;
                }
                xVar.b(16384);
                android.support.v4.view.ab.a(view, RecyclerView.this.ag.c());
            }
        }

        private void f(x xVar) {
            if (xVar.a instanceof ViewGroup) {
                a((ViewGroup) xVar.a, false);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Removed duplicated region for block: B:100:0x0214  */
        /* JADX WARN: Removed duplicated region for block: B:106:0x0229 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x018b  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x01a8  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x01cd  */
        /* JADX WARN: Removed duplicated region for block: B:89:0x01dc  */
        /* JADX WARN: Removed duplicated region for block: B:98:0x0206  */
        @android.support.annotation.ag
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public android.support.v7.widget.RecyclerView.x a(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instructions count: 618
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.p.a(int, boolean, long):android.support.v7.widget.RecyclerView$x");
        }

        x a(long j, int i, boolean z) {
            for (int size = this.a.size() - 1; size >= 0; size--) {
                x xVar = this.a.get(size);
                if (xVar.h() == j && !xVar.l()) {
                    if (i == xVar.i()) {
                        xVar.b(32);
                        if (xVar.s() && !RecyclerView.this.ac.c()) {
                            xVar.a(2, 14);
                        }
                        return xVar;
                    } else if (!z) {
                        this.a.remove(size);
                        RecyclerView.this.removeDetachedView(xVar.a, false);
                        c(xVar.a);
                    }
                }
            }
            int size2 = this.c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                x xVar2 = this.c.get(size2);
                if (xVar2.h() == j) {
                    if (i == xVar2.i()) {
                        if (!z) {
                            this.c.remove(size2);
                        }
                        return xVar2;
                    } else if (!z) {
                        d(size2);
                        return null;
                    }
                }
            }
        }

        View a(int i, boolean z) {
            return a(i, z, RecyclerView.V).a;
        }

        public void a() {
            this.a.clear();
            d();
        }

        public void a(int i) {
            this.i = i;
            b();
        }

        void a(int i, int i2) {
            int i3;
            int i4;
            int i5;
            if (i < i2) {
                i4 = i;
                i3 = i2;
                i5 = -1;
            } else {
                i3 = i;
                i4 = i2;
                i5 = 1;
            }
            int size = this.c.size();
            for (int i6 = 0; i6 < size; i6++) {
                x xVar = this.c.get(i6);
                if (xVar != null && xVar.c >= i4 && xVar.c <= i3) {
                    if (xVar.c == i) {
                        xVar.a(i2 - i, false);
                    } else {
                        xVar.a(i5, false);
                    }
                }
            }
        }

        void a(int i, int i2, boolean z) {
            int i3 = i + i2;
            for (int size = this.c.size() - 1; size >= 0; size--) {
                x xVar = this.c.get(size);
                if (xVar != null) {
                    if (xVar.c >= i3) {
                        xVar.a(-i2, z);
                    } else if (xVar.c >= i) {
                        xVar.b(8);
                        d(size);
                    }
                }
            }
        }

        void a(a aVar, a aVar2, boolean z) {
            a();
            g().a(aVar, aVar2, z);
        }

        void a(o oVar) {
            o oVar2 = this.e;
            if (oVar2 != null) {
                oVar2.d();
            }
            this.e = oVar;
            if (this.e == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.e.c();
        }

        void a(v vVar) {
            this.j = vVar;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(@android.support.annotation.af x xVar, boolean z) {
            RecyclerView.c(xVar);
            if (xVar.a(16384)) {
                xVar.a(0, 16384);
                android.support.v4.view.ab.a(xVar.a, (android.support.v4.view.a) null);
            }
            if (z) {
                d(xVar);
            }
            xVar.E = null;
            g().a(xVar);
        }

        public void a(@android.support.annotation.af View view) {
            x e = RecyclerView.e(view);
            if (e.t()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (e.j()) {
                e.k();
            } else if (e.l()) {
                e.m();
            }
            b(e);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x005a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(@android.support.annotation.af android.view.View r7, int r8) {
            /*
                r6 = this;
                android.support.v7.widget.RecyclerView$x r7 = android.support.v7.widget.RecyclerView.e(r7)
                if (r7 == 0) goto L9b
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.d r0 = r0.x
                int r2 = r0.b(r8)
                if (r2 < 0) goto L5e
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$a r0 = r0.E
                int r0 = r0.a()
                if (r2 >= r0) goto L5e
                r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r0 = r6
                r1 = r7
                r3 = r8
                r0.a(r1, r2, r3, r4)
                android.view.View r8 = r7.a
                android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
                if (r8 != 0) goto L3b
                android.support.v7.widget.RecyclerView r8 = android.support.v7.widget.RecyclerView.this
                android.view.ViewGroup$LayoutParams r8 = r8.generateDefaultLayoutParams()
            L33:
                android.support.v7.widget.RecyclerView$LayoutParams r8 = (android.support.v7.widget.RecyclerView.LayoutParams) r8
                android.view.View r0 = r7.a
                r0.setLayoutParams(r8)
                goto L4c
            L3b:
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                boolean r0 = r0.checkLayoutParams(r8)
                if (r0 != 0) goto L4a
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.view.ViewGroup$LayoutParams r8 = r0.generateLayoutParams(r8)
                goto L33
            L4a:
                android.support.v7.widget.RecyclerView$LayoutParams r8 = (android.support.v7.widget.RecyclerView.LayoutParams) r8
            L4c:
                r0 = 1
                r8.f = r0
                r8.d = r7
                android.view.View r7 = r7.a
                android.view.ViewParent r7 = r7.getParent()
                if (r7 != 0) goto L5a
                goto L5b
            L5a:
                r0 = 0
            L5b:
                r8.g = r0
                return
            L5e:
                java.lang.IndexOutOfBoundsException r7 = new java.lang.IndexOutOfBoundsException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Inconsistency detected. Invalid item position "
                r0.append(r1)
                r0.append(r8)
                java.lang.String r8 = "(offset:"
                r0.append(r8)
                r0.append(r2)
                java.lang.String r8 = ")."
                r0.append(r8)
                java.lang.String r8 = "state:"
                r0.append(r8)
                android.support.v7.widget.RecyclerView r8 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$u r8 = r8.ac
                int r8 = r8.i()
                r0.append(r8)
                android.support.v7.widget.RecyclerView r8 = android.support.v7.widget.RecyclerView.this
                java.lang.String r8 = r8.a()
                r0.append(r8)
                java.lang.String r8 = r0.toString()
                r7.<init>(r8)
                throw r7
            L9b:
                java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                r8.<init>()
                java.lang.String r0 = "The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter"
                r8.append(r0)
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                java.lang.String r0 = r0.a()
                r8.append(r0)
                java.lang.String r8 = r8.toString()
                r7.<init>(r8)
                goto Lb9
            Lb8:
                throw r7
            Lb9:
                goto Lb8
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.p.a(android.view.View, int):void");
        }

        boolean a(x xVar) {
            if (xVar.s()) {
                return RecyclerView.this.ac.c();
            }
            if (xVar.c >= 0 && xVar.c < RecyclerView.this.E.a()) {
                if (RecyclerView.this.ac.c() || RecyclerView.this.E.b(xVar.c) == xVar.i()) {
                    return !RecyclerView.this.E.b() || xVar.h() == RecyclerView.this.E.c(xVar.c);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + xVar + RecyclerView.this.a());
        }

        public int b(int i) {
            if (i >= 0 && i < RecyclerView.this.ac.i()) {
                return !RecyclerView.this.ac.c() ? i : RecyclerView.this.x.b(i);
            }
            throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + RecyclerView.this.ac.i() + RecyclerView.this.a());
        }

        x b(int i, boolean z) {
            View c;
            int size = this.a.size();
            for (int i2 = 0; i2 < size; i2++) {
                x xVar = this.a.get(i2);
                if (!xVar.l() && xVar.e() == i && !xVar.p() && (RecyclerView.this.ac.j || !xVar.s())) {
                    xVar.b(32);
                    return xVar;
                }
            }
            if (z || (c = RecyclerView.this.y.c(i)) == null) {
                int size2 = this.c.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    x xVar2 = this.c.get(i3);
                    if (!xVar2.p() && xVar2.e() == i) {
                        if (!z) {
                            this.c.remove(i3);
                        }
                        return xVar2;
                    }
                }
                return null;
            }
            x e = RecyclerView.e(c);
            RecyclerView.this.y.e(c);
            int b = RecyclerView.this.y.b(c);
            if (b != -1) {
                RecyclerView.this.y.e(b);
                d(c);
                e.b(8224);
                return e;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + e + RecyclerView.this.a());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void b() {
            this.d = this.i + (RecyclerView.this.F != null ? RecyclerView.this.F.C : 0);
            for (int size = this.c.size() - 1; size >= 0 && this.c.size() > this.d; size--) {
                d(size);
            }
        }

        void b(int i, int i2) {
            int size = this.c.size();
            for (int i3 = 0; i3 < size; i3++) {
                x xVar = this.c.get(i3);
                if (xVar != null && xVar.c >= i) {
                    xVar.a(i2, true);
                }
            }
        }

        void b(x xVar) {
            boolean z;
            if (xVar.j() || xVar.a.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(xVar.j());
                sb.append(" isAttached:");
                sb.append(xVar.a.getParent() != null);
                sb.append(RecyclerView.this.a());
                throw new IllegalArgumentException(sb.toString());
            } else if (xVar.t()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + xVar + RecyclerView.this.a());
            } else if (xVar.c()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.a());
            } else {
                boolean A = xVar.A();
                if ((RecyclerView.this.E != null && A && RecyclerView.this.E.b((a) xVar)) || xVar.y()) {
                    if (this.d <= 0 || xVar.a(526)) {
                        z = false;
                    } else {
                        int size = this.c.size();
                        if (size >= this.d && size > 0) {
                            d(0);
                            size--;
                        }
                        if (RecyclerView.g && size > 0 && !RecyclerView.this.ab.a(xVar.c)) {
                            int i = size - 1;
                            while (i >= 0) {
                                if (!RecyclerView.this.ab.a(this.c.get(i).c)) {
                                    break;
                                }
                                i--;
                            }
                            size = i + 1;
                        }
                        this.c.add(size, xVar);
                        z = true;
                    }
                    if (!z) {
                        a(xVar, true);
                        r1 = true;
                    }
                } else {
                    z = false;
                }
                RecyclerView.this.z.g(xVar);
                if (z || r1 || !A) {
                    return;
                }
                xVar.E = null;
            }
        }

        void b(View view) {
            b(RecyclerView.e(view));
        }

        @android.support.annotation.af
        public View c(int i) {
            return a(i, false);
        }

        @android.support.annotation.af
        public List<x> c() {
            return this.h;
        }

        void c(int i, int i2) {
            int i3;
            int i4 = i2 + i;
            for (int size = this.c.size() - 1; size >= 0; size--) {
                x xVar = this.c.get(size);
                if (xVar != null && (i3 = xVar.c) >= i && i3 < i4) {
                    xVar.b(2);
                    d(size);
                }
            }
        }

        void c(x xVar) {
            (xVar.C ? this.b : this.a).remove(xVar);
            xVar.B = null;
            xVar.C = false;
            xVar.m();
        }

        void c(View view) {
            x e = RecyclerView.e(view);
            e.B = null;
            e.C = false;
            e.m();
            b(e);
        }

        void d() {
            for (int size = this.c.size() - 1; size >= 0; size--) {
                d(size);
            }
            this.c.clear();
            if (RecyclerView.g) {
                RecyclerView.this.ab.a();
            }
        }

        void d(int i) {
            a(this.c.get(i), true);
            this.c.remove(i);
        }

        void d(@android.support.annotation.af x xVar) {
            if (RecyclerView.this.G != null) {
                RecyclerView.this.G.a(xVar);
            }
            if (RecyclerView.this.E != null) {
                RecyclerView.this.E.a((a) xVar);
            }
            if (RecyclerView.this.ac != null) {
                RecyclerView.this.z.g(xVar);
            }
        }

        void d(View view) {
            ArrayList<x> arrayList;
            x e = RecyclerView.e(view);
            if (!e.a(12) && e.B() && !RecyclerView.this.b(e)) {
                if (this.b == null) {
                    this.b = new ArrayList<>();
                }
                e.a(this, true);
                arrayList = this.b;
            } else if (e.p() && !e.s() && !RecyclerView.this.E.b()) {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.a());
            } else {
                e.a(this, false);
                arrayList = this.a;
            }
            arrayList.add(e);
        }

        int e() {
            return this.a.size();
        }

        View e(int i) {
            return this.a.get(i).a;
        }

        x f(int i) {
            int size;
            int b;
            ArrayList<x> arrayList = this.b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    x xVar = this.b.get(i2);
                    if (!xVar.l() && xVar.e() == i) {
                        xVar.b(32);
                        return xVar;
                    }
                }
                if (RecyclerView.this.E.b() && (b = RecyclerView.this.x.b(i)) > 0 && b < RecyclerView.this.E.a()) {
                    long c = RecyclerView.this.E.c(b);
                    for (int i3 = 0; i3 < size; i3++) {
                        x xVar2 = this.b.get(i3);
                        if (!xVar2.l() && xVar2.h() == c) {
                            xVar2.b(32);
                            return xVar2;
                        }
                    }
                }
            }
            return null;
        }

        void f() {
            this.a.clear();
            ArrayList<x> arrayList = this.b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        o g() {
            if (this.e == null) {
                this.e = new o();
            }
            return this.e;
        }

        void h() {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                x xVar = this.c.get(i);
                if (xVar != null) {
                    xVar.b(6);
                    xVar.a((Object) null);
                }
            }
            if (RecyclerView.this.E == null || !RecyclerView.this.E.b()) {
                d();
            }
        }

        void i() {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                this.c.get(i).a();
            }
            int size2 = this.a.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.a.get(i2).a();
            }
            ArrayList<x> arrayList = this.b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    this.b.get(i3).a();
                }
            }
        }

        void j() {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                LayoutParams layoutParams = (LayoutParams) this.c.get(i).a.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.f = true;
                }
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface q {
        void a(@android.support.annotation.af x xVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class r extends c {
        r() {
        }

        @Override // android.support.v7.widget.RecyclerView.c
        public void a() {
            RecyclerView.this.b((String) null);
            RecyclerView.this.ac.i = true;
            RecyclerView.this.c(true);
            if (RecyclerView.this.x.d()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // android.support.v7.widget.RecyclerView.c
        public void a(int i, int i2, int i3) {
            RecyclerView.this.b((String) null);
            if (RecyclerView.this.x.a(i, i2, i3)) {
                b();
            }
        }

        @Override // android.support.v7.widget.RecyclerView.c
        public void a(int i, int i2, Object obj) {
            RecyclerView.this.b((String) null);
            if (RecyclerView.this.x.a(i, i2, obj)) {
                b();
            }
        }

        void b() {
            if (RecyclerView.f && RecyclerView.this.J && RecyclerView.this.I) {
                RecyclerView recyclerView = RecyclerView.this;
                android.support.v4.view.ab.a(recyclerView, recyclerView.B);
                return;
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.O = true;
            recyclerView2.requestLayout();
        }

        @Override // android.support.v7.widget.RecyclerView.c
        public void b(int i, int i2) {
            RecyclerView.this.b((String) null);
            if (RecyclerView.this.x.b(i, i2)) {
                b();
            }
        }

        @Override // android.support.v7.widget.RecyclerView.c
        public void c(int i, int i2) {
            RecyclerView.this.b((String) null);
            if (RecyclerView.this.x.c(i, i2)) {
                b();
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class s implements l {
        @Override // android.support.v7.widget.RecyclerView.l
        public void a(boolean z) {
        }

        @Override // android.support.v7.widget.RecyclerView.l
        public boolean a(@android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af MotionEvent motionEvent) {
            return false;
        }

        @Override // android.support.v7.widget.RecyclerView.l
        public void b(@android.support.annotation.af RecyclerView recyclerView, @android.support.annotation.af MotionEvent motionEvent) {
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class t {
        private RecyclerView b;
        private i c;
        private boolean d;
        private boolean e;
        private View f;
        private boolean h;
        private int a = -1;
        private final a g = new a(0, 0);

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static class a {
            public static final int a = Integer.MIN_VALUE;
            private int b;
            private int c;
            private int d;
            private int e;
            private Interpolator f;
            private boolean g;
            private int h;

            public a(@android.support.annotation.ai int i, @android.support.annotation.ai int i2) {
                this(i, i2, Integer.MIN_VALUE, null);
            }

            public a(@android.support.annotation.ai int i, @android.support.annotation.ai int i2, int i3) {
                this(i, i2, i3, null);
            }

            public a(@android.support.annotation.ai int i, @android.support.annotation.ai int i2, int i3, @android.support.annotation.ag Interpolator interpolator) {
                this.e = -1;
                this.g = false;
                this.h = 0;
                this.b = i;
                this.c = i2;
                this.d = i3;
                this.f = interpolator;
            }

            private void f() {
                if (this.f != null && this.d < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.d < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            public void a(int i) {
                this.e = i;
            }

            public void a(@android.support.annotation.ai int i, @android.support.annotation.ai int i2, int i3, @android.support.annotation.ag Interpolator interpolator) {
                this.b = i;
                this.c = i2;
                this.d = i3;
                this.f = interpolator;
                this.g = true;
            }

            void a(RecyclerView recyclerView) {
                int i = this.e;
                if (i >= 0) {
                    this.e = -1;
                    recyclerView.f(i);
                    this.g = false;
                } else if (!this.g) {
                    this.h = 0;
                } else {
                    f();
                    if (this.f != null) {
                        recyclerView.W.a(this.b, this.c, this.d, this.f);
                    } else if (this.d == Integer.MIN_VALUE) {
                        recyclerView.W.b(this.b, this.c);
                    } else {
                        recyclerView.W.a(this.b, this.c, this.d);
                    }
                    this.h++;
                    if (this.h > 10) {
                        Log.e(RecyclerView.a, "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.g = false;
                }
            }

            public void a(@android.support.annotation.ag Interpolator interpolator) {
                this.g = true;
                this.f = interpolator;
            }

            boolean a() {
                return this.e >= 0;
            }

            @android.support.annotation.ai
            public int b() {
                return this.b;
            }

            public void b(@android.support.annotation.ai int i) {
                this.g = true;
                this.b = i;
            }

            @android.support.annotation.ai
            public int c() {
                return this.c;
            }

            public void c(@android.support.annotation.ai int i) {
                this.g = true;
                this.c = i;
            }

            public int d() {
                return this.d;
            }

            public void d(int i) {
                this.g = true;
                this.d = i;
            }

            @android.support.annotation.ag
            public Interpolator e() {
                return this.f;
            }
        }

        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public interface b {
            @android.support.annotation.ag
            PointF d(int i);
        }

        public int a(View view) {
            return this.b.h(view);
        }

        protected abstract void a();

        void a(int i, int i2) {
            PointF d;
            RecyclerView recyclerView = this.b;
            if (!this.e || this.a == -1 || recyclerView == null) {
                f();
            }
            if (this.d && this.f == null && this.c != null && (d = d(this.a)) != null && (d.x != 0.0f || d.y != 0.0f)) {
                recyclerView.a((int) Math.signum(d.x), (int) Math.signum(d.y), (int[]) null);
            }
            this.d = false;
            View view = this.f;
            if (view != null) {
                if (a(view) == this.a) {
                    a(this.f, recyclerView.ac, this.g);
                    this.g.a(recyclerView);
                    f();
                } else {
                    Log.e(RecyclerView.a, "Passed over target position while smooth scrolling.");
                    this.f = null;
                }
            }
            if (this.e) {
                a(i, i2, recyclerView.ac, this.g);
                boolean a2 = this.g.a();
                this.g.a(recyclerView);
                if (a2) {
                    if (!this.e) {
                        f();
                        return;
                    }
                    this.d = true;
                    recyclerView.W.a();
                }
            }
        }

        protected abstract void a(@android.support.annotation.ai int i, @android.support.annotation.ai int i2, @android.support.annotation.af u uVar, @android.support.annotation.af a aVar);

        /* JADX INFO: Access modifiers changed from: protected */
        public void a(@android.support.annotation.af PointF pointF) {
            float sqrt = (float) Math.sqrt((pointF.x * pointF.x) + (pointF.y * pointF.y));
            pointF.x /= sqrt;
            pointF.y /= sqrt;
        }

        void a(RecyclerView recyclerView, i iVar) {
            if (this.h) {
                Log.w(RecyclerView.a, "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.b = recyclerView;
            this.c = iVar;
            if (this.a == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            this.b.ac.d = this.a;
            this.e = true;
            this.d = true;
            this.f = e(i());
            a();
            this.b.W.a();
            this.h = true;
        }

        protected abstract void a(@android.support.annotation.af View view, @android.support.annotation.af u uVar, @android.support.annotation.af a aVar);

        protected abstract void b();

        protected void b(View view) {
            if (a(view) == i()) {
                this.f = view;
            }
        }

        public void c(int i) {
            this.a = i;
        }

        @android.support.annotation.ag
        public PointF d(int i) {
            i e = e();
            if (e instanceof b) {
                return ((b) e).d(i);
            }
            Log.w(RecyclerView.a, "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + b.class.getCanonicalName());
            return null;
        }

        @android.support.annotation.ag
        public i e() {
            return this.c;
        }

        public View e(int i) {
            return this.b.F.c(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void f() {
            if (this.e) {
                this.e = false;
                b();
                this.b.ac.d = -1;
                this.f = null;
                this.a = -1;
                this.d = false;
                this.c.b(this);
                this.c = null;
                this.b = null;
            }
        }

        @Deprecated
        public void f(int i) {
            this.b.e(i);
        }

        public boolean g() {
            return this.d;
        }

        public boolean h() {
            return this.e;
        }

        public int i() {
            return this.a;
        }

        public int j() {
            return this.b.F.G();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class u {
        static final int a = 1;
        static final int b = 2;
        static final int c = 4;
        int d = -1;
        int e = 0;
        int f = 0;
        int g = 1;
        int h = 0;
        boolean i = false;
        boolean j = false;
        boolean k = false;
        boolean l = false;
        boolean m = false;
        boolean n = false;
        int o;
        long p;
        int q;
        int r;
        int s;
        private SparseArray<Object> t;

        u a() {
            this.d = -1;
            SparseArray<Object> sparseArray = this.t;
            if (sparseArray != null) {
                sparseArray.clear();
            }
            this.h = 0;
            this.i = false;
            this.l = false;
            return this;
        }

        void a(int i) {
            if ((this.g & i) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.g));
        }

        public void a(int i, Object obj) {
            if (this.t == null) {
                this.t = new SparseArray<>();
            }
            this.t.put(i, obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(a aVar) {
            this.g = 1;
            this.h = aVar.a();
            this.j = false;
            this.k = false;
            this.l = false;
        }

        public void b(int i) {
            SparseArray<Object> sparseArray = this.t;
            if (sparseArray == null) {
                return;
            }
            sparseArray.remove(i);
        }

        public boolean b() {
            return this.l;
        }

        public <T> T c(int i) {
            SparseArray<Object> sparseArray = this.t;
            if (sparseArray == null) {
                return null;
            }
            return (T) sparseArray.get(i);
        }

        public boolean c() {
            return this.j;
        }

        public boolean d() {
            return this.n;
        }

        public boolean e() {
            return this.m;
        }

        public int f() {
            return this.d;
        }

        public boolean g() {
            return this.d != -1;
        }

        public boolean h() {
            return this.i;
        }

        public int i() {
            return this.j ? this.e - this.f : this.h;
        }

        public int j() {
            return this.r;
        }

        public int k() {
            return this.s;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.d + ", mData=" + this.t + ", mItemCount=" + this.h + ", mIsMeasuring=" + this.l + ", mPreviousLayoutItemCount=" + this.e + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f + ", mStructureChanged=" + this.i + ", mInPreLayout=" + this.j + ", mRunSimpleAnimations=" + this.m + ", mRunPredictiveAnimations=" + this.n + '}';
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class v {
        @android.support.annotation.ag
        public abstract View a(@android.support.annotation.af p pVar, int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class w implements Runnable {
        OverScroller a;
        private int d;
        private int e;
        Interpolator b = RecyclerView.ak;
        private boolean f = false;
        private boolean g = false;

        w() {
            this.a = new OverScroller(RecyclerView.this.getContext(), RecyclerView.ak);
        }

        private float a(float f) {
            return (float) Math.sin((f - 0.5f) * 0.47123894f);
        }

        private int b(int i, int i2, int i3, int i4) {
            int i5;
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            boolean z = abs > abs2;
            int sqrt = (int) Math.sqrt((i3 * i3) + (i4 * i4));
            int sqrt2 = (int) Math.sqrt((i * i) + (i2 * i2));
            int width = z ? RecyclerView.this.getWidth() : RecyclerView.this.getHeight();
            int i6 = width / 2;
            float f = width;
            float f2 = i6;
            float a = f2 + (a(Math.min(1.0f, (sqrt2 * 1.0f) / f)) * f2);
            if (sqrt > 0) {
                i5 = Math.round(Math.abs(a / sqrt) * 1000.0f) * 4;
            } else {
                if (!z) {
                    abs = abs2;
                }
                i5 = (int) (((abs / f) + 1.0f) * 300.0f);
            }
            return Math.min(i5, (int) RecyclerView.q);
        }

        private void c() {
            this.g = false;
            this.f = true;
        }

        private void d() {
            this.f = false;
            if (this.g) {
                a();
            }
        }

        void a() {
            if (this.f) {
                this.g = true;
                return;
            }
            RecyclerView.this.removeCallbacks(this);
            android.support.v4.view.ab.a(RecyclerView.this, this);
        }

        public void a(int i, int i2) {
            RecyclerView.this.setScrollState(2);
            this.e = 0;
            this.d = 0;
            this.a.fling(0, 0, i, i2, Integer.MIN_VALUE, ActivityChooserView.a.a, Integer.MIN_VALUE, ActivityChooserView.a.a);
            a();
        }

        public void a(int i, int i2, int i3) {
            a(i, i2, i3, RecyclerView.ak);
        }

        public void a(int i, int i2, int i3, int i4) {
            a(i, i2, b(i, i2, i3, i4));
        }

        public void a(int i, int i2, int i3, Interpolator interpolator) {
            if (this.b != interpolator) {
                this.b = interpolator;
                this.a = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            RecyclerView.this.setScrollState(2);
            this.e = 0;
            this.d = 0;
            this.a.startScroll(0, 0, i, i2, i3);
            if (Build.VERSION.SDK_INT < 23) {
                this.a.computeScrollOffset();
            }
            a();
        }

        public void a(int i, int i2, Interpolator interpolator) {
            int b = b(i, i2, 0, 0);
            if (interpolator == null) {
                interpolator = RecyclerView.ak;
            }
            a(i, i2, b, interpolator);
        }

        public void b() {
            RecyclerView.this.removeCallbacks(this);
            this.a.abortAnimation();
        }

        public void b(int i, int i2) {
            a(i, i2, 0, 0);
        }

        /* JADX WARN: Code restructure failed: missing block: B:47:0x00e9, code lost:
            if (r8 > 0) goto L43;
         */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00e5  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00f5  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00fc A[ADDED_TO_REGION] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 422
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.w.run():void");
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class x {
        private static final List<Object> F = Collections.emptyList();
        static final int j = 1;
        static final int k = 2;
        static final int l = 4;
        static final int m = 8;
        static final int n = 16;
        static final int o = 32;
        static final int p = 128;
        static final int q = 256;
        static final int r = 512;
        static final int s = 1024;
        static final int t = 2048;
        static final int u = 4096;
        static final int v = -1;
        static final int w = 8192;
        static final int x = 16384;
        RecyclerView E;
        @android.support.annotation.af
        public final View a;
        WeakReference<RecyclerView> b;
        int y;
        int c = -1;
        int d = -1;
        long e = -1;
        int f = -1;
        int g = -1;
        x h = null;
        x i = null;
        List<Object> z = null;
        List<Object> A = null;
        private int G = 0;
        p B = null;
        boolean C = false;
        private int H = 0;
        @android.support.annotation.av
        int D = -1;

        public x(@android.support.annotation.af View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.a = view;
        }

        private void C() {
            if (this.z == null) {
                this.z = new ArrayList();
                this.A = Collections.unmodifiableList(this.z);
            }
        }

        boolean A() {
            return (this.y & 16) == 0 && android.support.v4.view.ab.e(this.a);
        }

        boolean B() {
            return (this.y & 2) != 0;
        }

        void a() {
            this.d = -1;
            this.g = -1;
        }

        void a(int i, int i2) {
            this.y = (i & i2) | (this.y & (i2 ^ (-1)));
        }

        void a(int i, int i2, boolean z) {
            b(8);
            a(i2, z);
            this.c = i;
        }

        void a(int i, boolean z) {
            if (this.d == -1) {
                this.d = this.c;
            }
            if (this.g == -1) {
                this.g = this.c;
            }
            if (z) {
                this.g += i;
            }
            this.c += i;
            if (this.a.getLayoutParams() != null) {
                ((LayoutParams) this.a.getLayoutParams()).f = true;
            }
        }

        void a(p pVar, boolean z) {
            this.B = pVar;
            this.C = z;
        }

        void a(RecyclerView recyclerView) {
            int i = this.D;
            if (i == -1) {
                i = android.support.v4.view.ab.g(this.a);
            }
            this.H = i;
            recyclerView.a(this, 4);
        }

        void a(Object obj) {
            if (obj == null) {
                b(1024);
            } else if ((1024 & this.y) == 0) {
                C();
                this.z.add(obj);
            }
        }

        public final void a(boolean z) {
            int i;
            this.G = z ? this.G - 1 : this.G + 1;
            int i2 = this.G;
            if (i2 < 0) {
                this.G = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z && i2 == 1) {
                i = this.y | 16;
            } else if (!z || this.G != 0) {
                return;
            } else {
                i = this.y & (-17);
            }
            this.y = i;
        }

        boolean a(int i) {
            return (i & this.y) != 0;
        }

        void b() {
            if (this.d == -1) {
                this.d = this.c;
            }
        }

        void b(int i) {
            this.y = i | this.y;
        }

        void b(RecyclerView recyclerView) {
            recyclerView.a(this, this.H);
            this.H = 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean c() {
            return (this.y & 128) != 0;
        }

        @Deprecated
        public final int d() {
            int i = this.g;
            return i == -1 ? this.c : i;
        }

        public final int e() {
            int i = this.g;
            return i == -1 ? this.c : i;
        }

        public final int f() {
            RecyclerView recyclerView = this.E;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.d(this);
        }

        public final int g() {
            return this.d;
        }

        public final long h() {
            return this.e;
        }

        public final int i() {
            return this.f;
        }

        boolean j() {
            return this.B != null;
        }

        void k() {
            this.B.c(this);
        }

        boolean l() {
            return (this.y & 32) != 0;
        }

        void m() {
            this.y &= -33;
        }

        void n() {
            this.y &= -257;
        }

        void o() {
            this.y &= -129;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean p() {
            return (this.y & 4) != 0;
        }

        boolean q() {
            return (this.y & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean r() {
            return (this.y & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean s() {
            return (this.y & 8) != 0;
        }

        boolean t() {
            return (this.y & 256) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + this.c + " id=" + this.e + ", oldPos=" + this.d + ", pLpos:" + this.g);
            if (j()) {
                sb.append(" scrap ");
                sb.append(this.C ? "[changeScrap]" : "[attachedScrap]");
            }
            if (p()) {
                sb.append(" invalid");
            }
            if (!r()) {
                sb.append(" unbound");
            }
            if (q()) {
                sb.append(" update");
            }
            if (s()) {
                sb.append(" removed");
            }
            if (c()) {
                sb.append(" ignored");
            }
            if (t()) {
                sb.append(" tmpDetached");
            }
            if (!y()) {
                sb.append(" not recyclable(" + this.G + ")");
            }
            if (u()) {
                sb.append(" undefined adapter position");
            }
            if (this.a.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        boolean u() {
            return (this.y & 512) != 0 || p();
        }

        void v() {
            List<Object> list = this.z;
            if (list != null) {
                list.clear();
            }
            this.y &= -1025;
        }

        List<Object> w() {
            if ((this.y & 1024) == 0) {
                List<Object> list = this.z;
                return (list == null || list.size() == 0) ? F : this.A;
            }
            return F;
        }

        void x() {
            this.y = 0;
            this.c = -1;
            this.d = -1;
            this.e = -1L;
            this.g = -1;
            this.G = 0;
            this.h = null;
            this.i = null;
            v();
            this.H = 0;
            this.D = -1;
            RecyclerView.c(this);
        }

        public final boolean y() {
            return (this.y & 16) == 0 && !android.support.v4.view.ab.e(this.a);
        }

        boolean z() {
            return (this.y & 16) != 0;
        }
    }

    static {
        d = Build.VERSION.SDK_INT == 18 || Build.VERSION.SDK_INT == 19 || Build.VERSION.SDK_INT == 20;
        e = Build.VERSION.SDK_INT >= 23;
        f = Build.VERSION.SDK_INT >= 16;
        g = Build.VERSION.SDK_INT >= 21;
        an = Build.VERSION.SDK_INT <= 15;
        ao = Build.VERSION.SDK_INT <= 15;
        as = new Class[]{Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
        ak = new Interpolator() { // from class: android.support.v7.widget.RecyclerView.3
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3 * f3 * f3) + 1.0f;
            }
        };
    }

    public RecyclerView(@android.support.annotation.af Context context) {
        this(context, null);
    }

    public RecyclerView(@android.support.annotation.af Context context, @android.support.annotation.ag AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecyclerView(@android.support.annotation.af Context context, @android.support.annotation.ag AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.at = new r();
        this.w = new p();
        this.z = new bc();
        this.B = new Runnable() { // from class: android.support.v7.widget.RecyclerView.1
            @Override // java.lang.Runnable
            public void run() {
                if (!RecyclerView.this.L || RecyclerView.this.isLayoutRequested()) {
                    return;
                }
                if (!RecyclerView.this.I) {
                    RecyclerView.this.requestLayout();
                } else if (RecyclerView.this.N) {
                    RecyclerView.this.M = true;
                } else {
                    RecyclerView.this.g();
                }
            }
        };
        this.C = new Rect();
        this.av = new Rect();
        this.D = new RectF();
        this.H = new ArrayList<>();
        this.aw = new ArrayList<>();
        this.ay = 0;
        this.P = false;
        this.Q = false;
        this.aD = 0;
        this.aE = 0;
        this.aF = new e();
        this.R = new android.support.v7.widget.q();
        this.aL = 0;
        this.aM = -1;
        this.aW = Float.MIN_VALUE;
        this.aX = Float.MIN_VALUE;
        boolean z = true;
        this.aY = true;
        this.W = new w();
        this.ab = g ? new x.a() : null;
        this.ac = new u();
        this.ad = false;
        this.ae = false;
        this.bb = new g();
        this.af = false;
        this.bd = new int[2];
        this.bf = new int[2];
        this.ah = new int[2];
        this.bg = new int[2];
        this.ai = new int[2];
        this.aj = new ArrayList();
        this.bh = new Runnable() { // from class: android.support.v7.widget.RecyclerView.2
            @Override // java.lang.Runnable
            public void run() {
                if (RecyclerView.this.R != null) {
                    RecyclerView.this.R.a();
                }
                RecyclerView.this.af = false;
            }
        };
        this.bi = new bc.b() { // from class: android.support.v7.widget.RecyclerView.4
            @Override // android.support.v7.widget.bc.b
            public void a(x xVar) {
                RecyclerView.this.F.b(xVar.a, RecyclerView.this.w);
            }

            @Override // android.support.v7.widget.bc.b
            public void a(x xVar, @android.support.annotation.af f.d dVar, @android.support.annotation.ag f.d dVar2) {
                RecyclerView.this.w.c(xVar);
                RecyclerView.this.b(xVar, dVar, dVar2);
            }

            @Override // android.support.v7.widget.bc.b
            public void b(x xVar, f.d dVar, f.d dVar2) {
                RecyclerView.this.a(xVar, dVar, dVar2);
            }

            @Override // android.support.v7.widget.bc.b
            public void c(x xVar, @android.support.annotation.af f.d dVar, @android.support.annotation.af f.d dVar2) {
                xVar.a(false);
                if (RecyclerView.this.P) {
                    if (!RecyclerView.this.R.a(xVar, xVar, dVar, dVar2)) {
                        return;
                    }
                } else if (!RecyclerView.this.R.c(xVar, dVar, dVar2)) {
                    return;
                }
                RecyclerView.this.t();
            }
        };
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, am, i2, 0);
            this.A = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
        } else {
            this.A = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.aS = viewConfiguration.getScaledTouchSlop();
        this.aW = android.support.v4.view.ac.a(viewConfiguration, context);
        this.aX = android.support.v4.view.ac.b(viewConfiguration, context);
        this.aU = viewConfiguration.getScaledMinimumFlingVelocity();
        this.aV = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.R.a(this.bb);
        b();
        F();
        E();
        if (android.support.v4.view.ab.g(this) == 0) {
            android.support.v4.view.ab.e((View) this, 1);
        }
        this.aB = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new ah(this));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, a.j.RecyclerView, i2, 0);
            String string = obtainStyledAttributes2.getString(a.j.RecyclerView_layoutManager);
            if (obtainStyledAttributes2.getInt(a.j.RecyclerView_android_descendantFocusability, -1) == -1) {
                setDescendantFocusability(262144);
            }
            this.K = obtainStyledAttributes2.getBoolean(a.j.RecyclerView_fastScrollEnabled, false);
            if (this.K) {
                a((StateListDrawable) obtainStyledAttributes2.getDrawable(a.j.RecyclerView_fastScrollVerticalThumbDrawable), obtainStyledAttributes2.getDrawable(a.j.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) obtainStyledAttributes2.getDrawable(a.j.RecyclerView_fastScrollHorizontalThumbDrawable), obtainStyledAttributes2.getDrawable(a.j.RecyclerView_fastScrollHorizontalTrackDrawable));
            }
            obtainStyledAttributes2.recycle();
            a(context, string, attributeSet, i2, 0);
            if (Build.VERSION.SDK_INT >= 21) {
                TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, al, i2, 0);
                boolean z2 = obtainStyledAttributes3.getBoolean(0, true);
                obtainStyledAttributes3.recycle();
                z = z2;
            }
        } else {
            setDescendantFocusability(262144);
        }
        setNestedScrollingEnabled(z);
    }

    @SuppressLint({"InlinedApi"})
    private void E() {
        if (android.support.v4.view.ab.b(this) == 0) {
            android.support.v4.view.ab.d((View) this, 8);
        }
    }

    private void F() {
        this.y = new android.support.v7.widget.n(new n.b() { // from class: android.support.v7.widget.RecyclerView.5
            @Override // android.support.v7.widget.n.b
            public int a() {
                return RecyclerView.this.getChildCount();
            }

            @Override // android.support.v7.widget.n.b
            public int a(View view) {
                return RecyclerView.this.indexOfChild(view);
            }

            @Override // android.support.v7.widget.n.b
            public void a(int i2) {
                View childAt = RecyclerView.this.getChildAt(i2);
                if (childAt != null) {
                    RecyclerView.this.n(childAt);
                    childAt.clearAnimation();
                }
                RecyclerView.this.removeViewAt(i2);
            }

            @Override // android.support.v7.widget.n.b
            public void a(View view, int i2) {
                RecyclerView.this.addView(view, i2);
                RecyclerView.this.o(view);
            }

            @Override // android.support.v7.widget.n.b
            public void a(View view, int i2, ViewGroup.LayoutParams layoutParams) {
                x e2 = RecyclerView.e(view);
                if (e2 != null) {
                    if (!e2.t() && !e2.c()) {
                        throw new IllegalArgumentException("Called attach on a child which is not detached: " + e2 + RecyclerView.this.a());
                    }
                    e2.n();
                }
                RecyclerView.this.attachViewToParent(view, i2, layoutParams);
            }

            @Override // android.support.v7.widget.n.b
            public x b(View view) {
                return RecyclerView.e(view);
            }

            @Override // android.support.v7.widget.n.b
            public View b(int i2) {
                return RecyclerView.this.getChildAt(i2);
            }

            @Override // android.support.v7.widget.n.b
            public void b() {
                int a2 = a();
                for (int i2 = 0; i2 < a2; i2++) {
                    View b2 = b(i2);
                    RecyclerView.this.n(b2);
                    b2.clearAnimation();
                }
                RecyclerView.this.removeAllViews();
            }

            @Override // android.support.v7.widget.n.b
            public void c(int i2) {
                x e2;
                View b2 = b(i2);
                if (b2 != null && (e2 = RecyclerView.e(b2)) != null) {
                    if (e2.t() && !e2.c()) {
                        throw new IllegalArgumentException("called detach on an already detached child " + e2 + RecyclerView.this.a());
                    }
                    e2.b(256);
                }
                RecyclerView.this.detachViewFromParent(i2);
            }

            @Override // android.support.v7.widget.n.b
            public void c(View view) {
                x e2 = RecyclerView.e(view);
                if (e2 != null) {
                    e2.a(RecyclerView.this);
                }
            }

            @Override // android.support.v7.widget.n.b
            public void d(View view) {
                x e2 = RecyclerView.e(view);
                if (e2 != null) {
                    e2.b(RecyclerView.this);
                }
            }
        });
    }

    private boolean G() {
        int b2 = this.y.b();
        for (int i2 = 0; i2 < b2; i2++) {
            x e2 = e(this.y.b(i2));
            if (e2 != null && !e2.c() && e2.B()) {
                return true;
            }
        }
        return false;
    }

    private void H() {
        this.W.b();
        i iVar = this.F;
        if (iVar != null) {
            iVar.Y();
        }
    }

    private void I() {
        boolean z;
        EdgeEffect edgeEffect = this.aG;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.aG.isFinished();
        } else {
            z = false;
        }
        EdgeEffect edgeEffect2 = this.aH;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.aH.isFinished();
        }
        EdgeEffect edgeEffect3 = this.aI;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.aI.isFinished();
        }
        EdgeEffect edgeEffect4 = this.aJ;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.aJ.isFinished();
        }
        if (z) {
            android.support.v4.view.ab.f(this);
        }
    }

    private void J() {
        VelocityTracker velocityTracker = this.aN;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        a(0);
        I();
    }

    private void K() {
        J();
        setScrollState(0);
    }

    private void L() {
        int i2 = this.aA;
        this.aA = 0;
        if (i2 == 0 || !r()) {
            return;
        }
        AccessibilityEvent obtain = AccessibilityEvent.obtain();
        obtain.setEventType(2048);
        android.support.v4.view.a.a.b(obtain, i2);
        sendAccessibilityEventUnchecked(obtain);
    }

    private boolean M() {
        return this.R != null && this.F.d();
    }

    private void N() {
        if (this.P) {
            this.x.a();
            if (this.Q) {
                this.F.a(this);
            }
        }
        if (M()) {
            this.x.b();
        } else {
            this.x.e();
        }
        boolean z = false;
        boolean z2 = this.ad || this.ae;
        this.ac.m = this.L && this.R != null && (this.P || z2 || this.F.z) && (!this.P || this.E.b());
        u uVar = this.ac;
        if (uVar.m && z2 && !this.P && M()) {
            z = true;
        }
        uVar.n = z;
    }

    private void O() {
        View focusedChild = (this.aY && hasFocus() && this.E != null) ? getFocusedChild() : null;
        x d2 = focusedChild != null ? d(focusedChild) : null;
        if (d2 == null) {
            P();
            return;
        }
        this.ac.p = this.E.b() ? d2.h() : -1L;
        this.ac.o = this.P ? -1 : d2.s() ? d2.d : d2.f();
        this.ac.q = p(d2.a);
    }

    private void P() {
        u uVar = this.ac;
        uVar.p = -1L;
        uVar.o = -1;
        uVar.q = -1;
    }

    @android.support.annotation.ag
    private View Q() {
        x j2;
        int i2 = this.ac.o != -1 ? this.ac.o : 0;
        int i3 = this.ac.i();
        for (int i4 = i2; i4 < i3; i4++) {
            x j3 = j(i4);
            if (j3 == null) {
                break;
            } else if (j3.a.hasFocusable()) {
                return j3.a;
            }
        }
        int min = Math.min(i3, i2);
        while (true) {
            min--;
            if (min < 0 || (j2 = j(min)) == null) {
                return null;
            }
            if (j2.a.hasFocusable()) {
                return j2.a;
            }
        }
    }

    private void R() {
        View view;
        if (!this.aY || this.E == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!ao || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                if (!this.y.c(focusedChild)) {
                    return;
                }
            } else if (this.y.b() == 0) {
                requestFocus();
                return;
            }
        }
        View view2 = null;
        x a2 = (this.ac.p == -1 || !this.E.b()) ? null : a(this.ac.p);
        if (a2 != null && !this.y.c(a2.a) && a2.a.hasFocusable()) {
            view2 = a2.a;
        } else if (this.y.b() > 0) {
            view2 = Q();
        }
        if (view2 != null) {
            if (this.ac.q == -1 || (view = view2.findViewById(this.ac.q)) == null || !view.isFocusable()) {
                view = view2;
            }
            view.requestFocus();
        }
    }

    private void S() {
        boolean z = true;
        this.ac.a(1);
        a(this.ac);
        this.ac.l = false;
        h();
        this.z.a();
        p();
        N();
        O();
        u uVar = this.ac;
        uVar.k = (uVar.m && this.ae) ? false : false;
        this.ae = false;
        this.ad = false;
        u uVar2 = this.ac;
        uVar2.j = uVar2.n;
        this.ac.h = this.E.a();
        a(this.bd);
        if (this.ac.m) {
            int b2 = this.y.b();
            for (int i2 = 0; i2 < b2; i2++) {
                x e2 = e(this.y.b(i2));
                if (!e2.c() && (!e2.p() || this.E.b())) {
                    this.z.a(e2, this.R.a(this.ac, e2, f.e(e2), e2.w()));
                    if (this.ac.k && e2.B() && !e2.s() && !e2.c() && !e2.p()) {
                        this.z.a(a(e2), e2);
                    }
                }
            }
        }
        if (this.ac.n) {
            x();
            boolean z2 = this.ac.i;
            u uVar3 = this.ac;
            uVar3.i = false;
            this.F.c(this.w, uVar3);
            this.ac.i = z2;
            for (int i3 = 0; i3 < this.y.b(); i3++) {
                x e3 = e(this.y.b(i3));
                if (!e3.c() && !this.z.d(e3)) {
                    int e4 = f.e(e3);
                    boolean a2 = e3.a(8192);
                    if (!a2) {
                        e4 |= 4096;
                    }
                    f.d a3 = this.R.a(this.ac, e3, e4, e3.w());
                    if (a2) {
                        a(e3, a3);
                    } else {
                        this.z.b(e3, a3);
                    }
                }
            }
        }
        y();
        q();
        a(false);
        this.ac.g = 2;
    }

    private void T() {
        h();
        p();
        this.ac.a(6);
        this.x.e();
        this.ac.h = this.E.a();
        u uVar = this.ac;
        uVar.f = 0;
        uVar.j = false;
        this.F.c(this.w, uVar);
        u uVar2 = this.ac;
        uVar2.i = false;
        this.au = null;
        uVar2.m = uVar2.m && this.R != null;
        this.ac.g = 4;
        q();
        a(false);
    }

    private void U() {
        this.ac.a(4);
        h();
        p();
        u uVar = this.ac;
        uVar.g = 1;
        if (uVar.m) {
            for (int b2 = this.y.b() - 1; b2 >= 0; b2--) {
                x e2 = e(this.y.b(b2));
                if (!e2.c()) {
                    long a2 = a(e2);
                    f.d a3 = this.R.a(this.ac, e2);
                    x a4 = this.z.a(a2);
                    if (a4 != null && !a4.c()) {
                        boolean a5 = this.z.a(a4);
                        boolean a6 = this.z.a(e2);
                        if (!a5 || a4 != e2) {
                            f.d b3 = this.z.b(a4);
                            this.z.c(e2, a3);
                            f.d c2 = this.z.c(e2);
                            if (b3 == null) {
                                a(a2, e2, a4);
                            } else {
                                a(a4, e2, b3, c2, a5, a6);
                            }
                        }
                    }
                    this.z.c(e2, a3);
                }
            }
            this.z.a(this.bi);
        }
        this.F.b(this.w);
        u uVar2 = this.ac;
        uVar2.e = uVar2.h;
        this.P = false;
        this.Q = false;
        u uVar3 = this.ac;
        uVar3.m = false;
        uVar3.n = false;
        this.F.z = false;
        if (this.w.b != null) {
            this.w.b.clear();
        }
        if (this.F.D) {
            i iVar = this.F;
            iVar.C = 0;
            iVar.D = false;
            this.w.b();
        }
        this.F.a(this.ac);
        q();
        a(false);
        this.z.a();
        int[] iArr = this.bd;
        if (k(iArr[0], iArr[1])) {
            j(0, 0);
        }
        R();
        P();
    }

    private String a(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        } else if (str.contains(".")) {
            return str;
        } else {
            return RecyclerView.class.getPackage().getName() + '.' + str;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(float r7, float r8, float r9, float r10) {
        /*
            r6 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            r1 = 1
            r2 = 0
            int r3 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r3 >= 0) goto L21
            r6.k()
            android.widget.EdgeEffect r3 = r6.aG
            float r4 = -r8
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            float r9 = r0 - r9
        L1c:
            android.support.v4.widget.i.a(r3, r4, r9)
            r9 = 1
            goto L39
        L21:
            int r3 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r3 <= 0) goto L38
            r6.l()
            android.widget.EdgeEffect r3 = r6.aI
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r4 = r8 / r4
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            goto L1c
        L38:
            r9 = 0
        L39:
            int r3 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r3 >= 0) goto L53
            r6.m()
            android.widget.EdgeEffect r9 = r6.aH
            float r0 = -r10
            int r3 = r6.getHeight()
            float r3 = (float) r3
            float r0 = r0 / r3
            int r3 = r6.getWidth()
            float r3 = (float) r3
            float r7 = r7 / r3
            android.support.v4.widget.i.a(r9, r0, r7)
            goto L6f
        L53:
            int r3 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r3 <= 0) goto L6e
            r6.n()
            android.widget.EdgeEffect r9 = r6.aJ
            int r3 = r6.getHeight()
            float r3 = (float) r3
            float r3 = r10 / r3
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r7 = r7 / r4
            float r0 = r0 - r7
            android.support.v4.widget.i.a(r9, r3, r0)
            goto L6f
        L6e:
            r1 = r9
        L6f:
            if (r1 != 0) goto L79
            int r7 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r7 != 0) goto L79
            int r7 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r7 == 0) goto L7c
        L79:
            android.support.v4.view.ab.f(r6)
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.a(float, float, float, float):void");
    }

    private void a(long j2, x xVar, x xVar2) {
        int b2 = this.y.b();
        for (int i2 = 0; i2 < b2; i2++) {
            x e2 = e(this.y.b(i2));
            if (e2 != xVar && a(e2) == j2) {
                a aVar = this.E;
                if (aVar == null || !aVar.b()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + e2 + " \n View Holder 2:" + xVar + a());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + e2 + " \n View Holder 2:" + xVar + a());
            }
        }
        Log.e(a, "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + xVar2 + " cannot be found but it is necessary for " + xVar + a());
    }

    private void a(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
        Constructor constructor;
        if (str != null) {
            String trim = str.trim();
            if (trim.isEmpty()) {
                return;
            }
            String a2 = a(context, trim);
            try {
                Class<? extends U> asSubclass = (isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).loadClass(a2).asSubclass(i.class);
                Object[] objArr = null;
                try {
                    constructor = asSubclass.getConstructor(as);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i3)};
                } catch (NoSuchMethodException e2) {
                    try {
                        constructor = asSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e3) {
                        e3.initCause(e2);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + a2, e3);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((i) constructor.newInstance(objArr));
            } catch (ClassCastException e4) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + a2, e4);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + a2, e5);
            } catch (IllegalAccessException e6) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + a2, e6);
            } catch (InstantiationException e7) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + a2, e7);
            } catch (InvocationTargetException e8) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + a2, e8);
            }
        }
    }

    private void a(@android.support.annotation.ag a aVar, boolean z, boolean z2) {
        a aVar2 = this.E;
        if (aVar2 != null) {
            aVar2.b(this.at);
            this.E.b(this);
        }
        if (!z || z2) {
            d();
        }
        this.x.a();
        a aVar3 = this.E;
        this.E = aVar;
        if (aVar != null) {
            aVar.a(this.at);
            aVar.a(this);
        }
        i iVar = this.F;
        if (iVar != null) {
            iVar.a(aVar3, this.E);
        }
        this.w.a(aVar3, this.E, z);
        this.ac.i = true;
    }

    private void a(@android.support.annotation.af x xVar, @android.support.annotation.af x xVar2, @android.support.annotation.af f.d dVar, @android.support.annotation.af f.d dVar2, boolean z, boolean z2) {
        xVar.a(false);
        if (z) {
            e(xVar);
        }
        if (xVar != xVar2) {
            if (z2) {
                e(xVar2);
            }
            xVar.h = xVar2;
            e(xVar);
            this.w.c(xVar);
            xVar2.a(false);
            xVar2.i = xVar;
        }
        if (this.R.a(xVar, xVar2, dVar, dVar2)) {
            t();
        }
    }

    private void a(@android.support.annotation.af View view, @android.support.annotation.ag View view2) {
        View view3 = view2 != null ? view2 : view;
        this.C.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.f) {
                Rect rect = layoutParams2.e;
                this.C.left -= rect.left;
                this.C.right += rect.right;
                this.C.top -= rect.top;
                this.C.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.C);
            offsetRectIntoDescendantCoords(view, this.C);
        }
        this.F.a(this, view, this.C, !this.L, view2 == null);
    }

    private void a(int[] iArr) {
        int b2 = this.y.b();
        if (b2 == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = ActivityChooserView.a.a;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < b2; i4++) {
            x e2 = e(this.y.b(i4));
            if (!e2.c()) {
                int e3 = e2.e();
                if (e3 < i2) {
                    i2 = e3;
                }
                if (e3 > i3) {
                    i3 = e3;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    private boolean a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.ax = null;
        }
        int size = this.aw.size();
        for (int i2 = 0; i2 < size; i2++) {
            l lVar = this.aw.get(i2);
            if (lVar.a(this, motionEvent) && action != 3) {
                this.ax = lVar;
                return true;
            }
        }
        return false;
    }

    private boolean a(View view, View view2, int i2) {
        if (view2 == null || view2 == this || c(view2) == null) {
            return false;
        }
        if (view == null || c(view) == null) {
            return true;
        }
        this.C.set(0, 0, view.getWidth(), view.getHeight());
        this.av.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.C);
        offsetDescendantRectToMyCoords(view2, this.av);
        char c2 = 65535;
        int i3 = this.F.D() == 1 ? -1 : 1;
        int i4 = ((this.C.left < this.av.left || this.C.right <= this.av.left) && this.C.right < this.av.right) ? 1 : ((this.C.right > this.av.right || this.C.left >= this.av.right) && this.C.left > this.av.left) ? -1 : 0;
        if ((this.C.top < this.av.top || this.C.bottom <= this.av.top) && this.C.bottom < this.av.bottom) {
            c2 = 1;
        } else if ((this.C.bottom <= this.av.bottom && this.C.top < this.av.bottom) || this.C.top <= this.av.top) {
            c2 = 0;
        }
        if (i2 == 1) {
            return c2 < 0 || (c2 == 0 && i4 * i3 <= 0);
        } else if (i2 == 2) {
            return c2 > 0 || (c2 == 0 && i4 * i3 >= 0);
        } else if (i2 == 17) {
            return i4 < 0;
        } else if (i2 == 33) {
            return c2 < 0;
        } else if (i2 == 66) {
            return i4 > 0;
        } else if (i2 == 130) {
            return c2 > 0;
        } else {
            throw new IllegalArgumentException("Invalid direction: " + i2 + a());
        }
    }

    static void b(View view, Rect rect) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.e;
        rect.set((view.getLeft() - rect2.left) - layoutParams.leftMargin, (view.getTop() - rect2.top) - layoutParams.topMargin, view.getRight() + rect2.right + layoutParams.rightMargin, view.getBottom() + rect2.bottom + layoutParams.bottomMargin);
    }

    private boolean b(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        l lVar = this.ax;
        if (lVar != null) {
            if (action != 0) {
                lVar.b(this, motionEvent);
                if (action == 3 || action == 1) {
                    this.ax = null;
                }
                return true;
            }
            this.ax = null;
        }
        if (action != 0) {
            int size = this.aw.size();
            for (int i2 = 0; i2 < size; i2++) {
                l lVar2 = this.aw.get(i2);
                if (lVar2.a(this, motionEvent)) {
                    this.ax = lVar2;
                    return true;
                }
            }
        }
        return false;
    }

    static void c(@android.support.annotation.af x xVar) {
        if (xVar.b != null) {
            ViewParent viewParent = xVar.b.get();
            while (true) {
                for (View view = (View) viewParent; view != null; view = null) {
                    if (view == xVar.a) {
                        return;
                    }
                    viewParent = view.getParent();
                    if (viewParent instanceof View) {
                        break;
                    }
                }
                xVar.b = null;
                return;
            }
        }
    }

    private void c(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.aM) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.aM = motionEvent.getPointerId(i2);
            int x2 = (int) (motionEvent.getX(i2) + 0.5f);
            this.aQ = x2;
            this.aO = x2;
            int y = (int) (motionEvent.getY(i2) + 0.5f);
            this.aR = y;
            this.aP = y;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static x e(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).d;
    }

    private void e(x xVar) {
        View view = xVar.a;
        boolean z = view.getParent() == this;
        this.w.c(b(view));
        if (xVar.t()) {
            this.y.a(view, -1, view.getLayoutParams(), true);
        } else if (z) {
            this.y.d(view);
        } else {
            this.y.a(view, true);
        }
    }

    private android.support.v4.view.q getScrollingChildHelper() {
        if (this.be == null) {
            this.be = new android.support.v4.view.q(this);
        }
        return this.be;
    }

    private boolean k(int i2, int i3) {
        a(this.bd);
        int[] iArr = this.bd;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    @android.support.annotation.ag
    static RecyclerView m(@android.support.annotation.af View view) {
        if (view instanceof ViewGroup) {
            if (view instanceof RecyclerView) {
                return (RecyclerView) view;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                RecyclerView m2 = m(viewGroup.getChildAt(i2));
                if (m2 != null) {
                    return m2;
                }
            }
            return null;
        }
        return null;
    }

    private int p(View view) {
        int id;
        loop0: while (true) {
            id = view.getId();
            while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
                view = ((ViewGroup) view).getFocusedChild();
                if (view.getId() != -1) {
                    break;
                }
            }
        }
        return id;
    }

    public void A() {
        if (this.H.size() == 0) {
            return;
        }
        i iVar = this.F;
        if (iVar != null) {
            iVar.a("Cannot invalidate item decorations during a scroll or layout");
        }
        v();
        requestLayout();
    }

    public boolean B() {
        return !this.L || this.P || this.x.d();
    }

    void C() {
        int b2 = this.y.b();
        for (int i2 = 0; i2 < b2; i2++) {
            View b3 = this.y.b(i2);
            x b4 = b(b3);
            if (b4 != null && b4.i != null) {
                View view = b4.i.a;
                int left = b3.getLeft();
                int top = b3.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    void D() {
        int i2;
        for (int size = this.aj.size() - 1; size >= 0; size--) {
            x xVar = this.aj.get(size);
            if (xVar.a.getParent() == this && !xVar.c() && (i2 = xVar.D) != -1) {
                android.support.v4.view.ab.e(xVar.a, i2);
                xVar.D = -1;
            }
        }
        this.aj.clear();
    }

    long a(x xVar) {
        return this.E.b() ? xVar.h() : xVar.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0036 A[SYNTHETIC] */
    @android.support.annotation.ag
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    android.support.v7.widget.RecyclerView.x a(int r6, boolean r7) {
        /*
            r5 = this;
            android.support.v7.widget.n r0 = r5.y
            int r0 = r0.c()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            android.support.v7.widget.n r3 = r5.y
            android.view.View r3 = r3.d(r2)
            android.support.v7.widget.RecyclerView$x r3 = e(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.s()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.c
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.e()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            android.support.v7.widget.n r1 = r5.y
            android.view.View r4 = r3.a
            boolean r1 = r1.c(r4)
            if (r1 == 0) goto L36
            r1 = r3
            goto L37
        L36:
            return r3
        L37:
            int r2 = r2 + 1
            goto L8
        L3a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.a(int, boolean):android.support.v7.widget.RecyclerView$x");
    }

    public x a(long j2) {
        a aVar = this.E;
        x xVar = null;
        if (aVar != null && aVar.b()) {
            int c2 = this.y.c();
            for (int i2 = 0; i2 < c2; i2++) {
                x e2 = e(this.y.d(i2));
                if (e2 != null && !e2.s() && e2.h() == j2) {
                    if (!this.y.c(e2.a)) {
                        return e2;
                    }
                    xVar = e2;
                }
            }
        }
        return xVar;
    }

    @android.support.annotation.ag
    public View a(float f2, float f3) {
        for (int b2 = this.y.b() - 1; b2 >= 0; b2--) {
            View b3 = this.y.b(b2);
            float translationX = b3.getTranslationX();
            float translationY = b3.getTranslationY();
            if (f2 >= b3.getLeft() + translationX && f2 <= b3.getRight() + translationX && f3 >= b3.getTop() + translationY && f3 <= b3.getBottom() + translationY) {
                return b3;
            }
        }
        return null;
    }

    String a() {
        return " " + super.toString() + ", adapter:" + this.E + ", layout:" + this.F + ", context:" + getContext();
    }

    @Override // android.support.v4.view.p
    public void a(int i2) {
        getScrollingChildHelper().c(i2);
    }

    public void a(@android.support.annotation.ai int i2, @android.support.annotation.ai int i3, @android.support.annotation.ag Interpolator interpolator) {
        i iVar = this.F;
        if (iVar == null) {
            Log.e(a, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (this.N) {
        } else {
            if (!iVar.h()) {
                i2 = 0;
            }
            if (!this.F.i()) {
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return;
            }
            this.W.a(i2, i3, interpolator);
        }
    }

    void a(int i2, int i3, Object obj) {
        int c2 = this.y.c();
        int i4 = i2 + i3;
        for (int i5 = 0; i5 < c2; i5++) {
            View d2 = this.y.d(i5);
            x e2 = e(d2);
            if (e2 != null && !e2.c() && e2.c >= i2 && e2.c < i4) {
                e2.b(2);
                e2.a(obj);
                ((LayoutParams) d2.getLayoutParams()).f = true;
            }
        }
        this.w.c(i2, i3);
    }

    void a(int i2, int i3, boolean z) {
        int i4 = i2 + i3;
        int c2 = this.y.c();
        for (int i5 = 0; i5 < c2; i5++) {
            x e2 = e(this.y.d(i5));
            if (e2 != null && !e2.c()) {
                if (e2.c >= i4) {
                    e2.a(-i3, z);
                } else if (e2.c >= i2) {
                    e2.a(i2 - 1, -i3, z);
                }
                this.ac.i = true;
            }
        }
        this.w.a(i2, i3, z);
        requestLayout();
    }

    void a(int i2, int i3, @android.support.annotation.ag int[] iArr) {
        h();
        p();
        android.support.v4.os.o.a(r);
        a(this.ac);
        int a2 = i2 != 0 ? this.F.a(i2, this.w, this.ac) : 0;
        int b2 = i3 != 0 ? this.F.b(i3, this.w, this.ac) : 0;
        android.support.v4.os.o.a();
        C();
        q();
        a(false);
        if (iArr != null) {
            iArr[0] = a2;
            iArr[1] = b2;
        }
    }

    @android.support.annotation.av
    void a(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new android.support.v7.widget.u(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(a.c.fastscroll_default_thickness), resources.getDimensionPixelSize(a.c.fastscroll_minimum_range), resources.getDimensionPixelOffset(a.c.fastscroll_margin));
            return;
        }
        throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + a());
    }

    public void a(@android.support.annotation.ag a aVar, boolean z) {
        setLayoutFrozen(false);
        a(aVar, true, z);
        c(true);
        requestLayout();
    }

    public void a(@android.support.annotation.af h hVar) {
        a(hVar, -1);
    }

    public void a(@android.support.annotation.af h hVar, int i2) {
        i iVar = this.F;
        if (iVar != null) {
            iVar.a("Cannot add item decoration during a scroll  or layout");
        }
        if (this.H.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.H.add(hVar);
        } else {
            this.H.add(i2, hVar);
        }
        v();
        requestLayout();
    }

    public void a(@android.support.annotation.af j jVar) {
        if (this.aC == null) {
            this.aC = new ArrayList();
        }
        this.aC.add(jVar);
    }

    public void a(@android.support.annotation.af l lVar) {
        this.aw.add(lVar);
    }

    public void a(@android.support.annotation.af m mVar) {
        if (this.ba == null) {
            this.ba = new ArrayList();
        }
        this.ba.add(mVar);
    }

    final void a(u uVar) {
        if (getScrollState() != 2) {
            uVar.r = 0;
            uVar.s = 0;
            return;
        }
        OverScroller overScroller = this.W.a;
        uVar.r = overScroller.getFinalX() - overScroller.getCurrX();
        uVar.s = overScroller.getFinalY() - overScroller.getCurrY();
    }

    void a(x xVar, f.d dVar) {
        xVar.a(0, 8192);
        if (this.ac.k && xVar.B() && !xVar.s() && !xVar.c()) {
            this.z.a(a(xVar), xVar);
        }
        this.z.a(xVar, dVar);
    }

    void a(@android.support.annotation.af x xVar, @android.support.annotation.ag f.d dVar, @android.support.annotation.af f.d dVar2) {
        xVar.a(false);
        if (this.R.b(xVar, dVar, dVar2)) {
            t();
        }
    }

    public void a(@android.support.annotation.af View view, @android.support.annotation.af Rect rect) {
        b(view, rect);
    }

    void a(String str) {
        if (s()) {
            return;
        }
        if (str == null) {
            throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling" + a());
        }
        throw new IllegalStateException(str + a());
    }

    void a(boolean z) {
        if (this.ay < 1) {
            this.ay = 1;
        }
        if (!z && !this.N) {
            this.M = false;
        }
        if (this.ay == 1) {
            if (z && this.M && !this.N && this.F != null && this.E != null) {
                u();
            }
            if (!this.N) {
                this.M = false;
            }
        }
        this.ay--;
    }

    @Override // android.support.v4.view.p
    public boolean a(int i2, int i3) {
        return getScrollingChildHelper().a(i2, i3);
    }

    @Override // android.support.v4.view.p
    public boolean a(int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        return getScrollingChildHelper().a(i2, i3, i4, i5, iArr, i6);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x009b, code lost:
        if (r0 != 0) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean a(int r19, int r20, android.view.MotionEvent r21) {
        /*
            r18 = this;
            r7 = r18
            r8 = r19
            r9 = r20
            r10 = r21
            r18.g()
            android.support.v7.widget.RecyclerView$a r0 = r7.E
            r11 = 1
            r12 = 0
            if (r0 == 0) goto L25
            int[] r0 = r7.ai
            r7.a(r8, r9, r0)
            int[] r0 = r7.ai
            r1 = r0[r12]
            r0 = r0[r11]
            int r2 = r8 - r1
            int r3 = r9 - r0
            r6 = r0
            r15 = r1
            r13 = r2
            r14 = r3
            goto L29
        L25:
            r6 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L29:
            java.util.ArrayList<android.support.v7.widget.RecyclerView$h> r0 = r7.H
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L34
            r18.invalidate()
        L34:
            int[] r5 = r7.bf
            r16 = 0
            r0 = r18
            r1 = r15
            r2 = r6
            r3 = r13
            r4 = r14
            r17 = r6
            r6 = r16
            boolean r0 = r0.a(r1, r2, r3, r4, r5, r6)
            if (r0 == 0) goto L76
            int r0 = r7.aQ
            int[] r1 = r7.bf
            r2 = r1[r12]
            int r0 = r0 - r2
            r7.aQ = r0
            int r0 = r7.aR
            r2 = r1[r11]
            int r0 = r0 - r2
            r7.aR = r0
            if (r10 == 0) goto L63
            r0 = r1[r12]
            float r0 = (float) r0
            r1 = r1[r11]
            float r1 = (float) r1
            r10.offsetLocation(r0, r1)
        L63:
            int[] r0 = r7.bg
            r1 = r0[r12]
            int[] r2 = r7.bf
            r3 = r2[r12]
            int r1 = r1 + r3
            r0[r12] = r1
            r1 = r0[r11]
            r2 = r2[r11]
            int r1 = r1 + r2
            r0[r11] = r1
            goto L97
        L76:
            int r0 = r18.getOverScrollMode()
            r1 = 2
            if (r0 == r1) goto L97
            if (r10 == 0) goto L94
            r0 = 8194(0x2002, float:1.1482E-41)
            boolean r0 = android.support.v4.view.n.e(r10, r0)
            if (r0 != 0) goto L94
            float r0 = r21.getX()
            float r1 = (float) r13
            float r2 = r21.getY()
            float r3 = (float) r14
            r7.a(r0, r1, r2, r3)
        L94:
            r18.d(r19, r20)
        L97:
            if (r15 != 0) goto L9e
            r0 = r17
            if (r0 == 0) goto La3
            goto La0
        L9e:
            r0 = r17
        La0:
            r7.j(r15, r0)
        La3:
            boolean r1 = r18.awakenScrollBars()
            if (r1 != 0) goto Lac
            r18.invalidate()
        Lac:
            if (r15 != 0) goto Lb0
            if (r0 == 0) goto Lb1
        Lb0:
            r12 = 1
        Lb1:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.a(int, int, android.view.MotionEvent):boolean");
    }

    @Override // android.support.v4.view.p
    public boolean a(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().a(i2, i3, iArr, iArr2, i4);
    }

    @android.support.annotation.av
    boolean a(x xVar, int i2) {
        if (!s()) {
            android.support.v4.view.ab.e(xVar.a, i2);
            return true;
        }
        xVar.D = i2;
        this.aj.add(xVar);
        return false;
    }

    boolean a(View view) {
        h();
        boolean f2 = this.y.f(view);
        if (f2) {
            x e2 = e(view);
            this.w.c(e2);
            this.w.b(e2);
        }
        a(!f2);
        return f2;
    }

    boolean a(AccessibilityEvent accessibilityEvent) {
        if (s()) {
            int c2 = accessibilityEvent != null ? android.support.v4.view.a.a.c(accessibilityEvent) : 0;
            if (c2 == 0) {
                c2 = 0;
            }
            this.aA = c2 | this.aA;
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        i iVar = this.F;
        if (iVar == null || !iVar.a(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    public x b(@android.support.annotation.af View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return e(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    void b() {
        this.x = new android.support.v7.widget.d(new d.a() { // from class: android.support.v7.widget.RecyclerView.6
            @Override // android.support.v7.widget.d.a
            public x a(int i2) {
                x a2 = RecyclerView.this.a(i2, true);
                if (a2 == null || RecyclerView.this.y.c(a2.a)) {
                    return null;
                }
                return a2;
            }

            @Override // android.support.v7.widget.d.a
            public void a(int i2, int i3) {
                RecyclerView.this.a(i2, i3, true);
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.ad = true;
                recyclerView.ac.f += i3;
            }

            @Override // android.support.v7.widget.d.a
            public void a(int i2, int i3, Object obj) {
                RecyclerView.this.a(i2, i3, obj);
                RecyclerView.this.ae = true;
            }

            @Override // android.support.v7.widget.d.a
            public void a(d.b bVar) {
                c(bVar);
            }

            @Override // android.support.v7.widget.d.a
            public void b(int i2, int i3) {
                RecyclerView.this.a(i2, i3, false);
                RecyclerView.this.ad = true;
            }

            @Override // android.support.v7.widget.d.a
            public void b(d.b bVar) {
                c(bVar);
            }

            @Override // android.support.v7.widget.d.a
            public void c(int i2, int i3) {
                RecyclerView.this.h(i2, i3);
                RecyclerView.this.ad = true;
            }

            void c(d.b bVar) {
                int i2 = bVar.f;
                if (i2 == 1) {
                    RecyclerView.this.F.a(RecyclerView.this, bVar.g, bVar.i);
                } else if (i2 == 2) {
                    RecyclerView.this.F.b(RecyclerView.this, bVar.g, bVar.i);
                } else if (i2 == 4) {
                    RecyclerView.this.F.a(RecyclerView.this, bVar.g, bVar.i, bVar.h);
                } else if (i2 != 8) {
                } else {
                    RecyclerView.this.F.a(RecyclerView.this, bVar.g, bVar.i, 1);
                }
            }

            @Override // android.support.v7.widget.d.a
            public void d(int i2, int i3) {
                RecyclerView.this.g(i2, i3);
                RecyclerView.this.ad = true;
            }
        });
    }

    public void b(@android.support.annotation.ai int i2, @android.support.annotation.ai int i3) {
        a(i2, i3, (Interpolator) null);
    }

    public void b(@android.support.annotation.af h hVar) {
        i iVar = this.F;
        if (iVar != null) {
            iVar.a("Cannot remove item decoration during a scroll  or layout");
        }
        this.H.remove(hVar);
        if (this.H.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        v();
        requestLayout();
    }

    public void b(@android.support.annotation.af j jVar) {
        List<j> list = this.aC;
        if (list == null) {
            return;
        }
        list.remove(jVar);
    }

    public void b(@android.support.annotation.af l lVar) {
        this.aw.remove(lVar);
        if (this.ax == lVar) {
            this.ax = null;
        }
    }

    public void b(@android.support.annotation.af m mVar) {
        List<m> list = this.ba;
        if (list != null) {
            list.remove(mVar);
        }
    }

    void b(@android.support.annotation.af x xVar, @android.support.annotation.af f.d dVar, @android.support.annotation.ag f.d dVar2) {
        e(xVar);
        xVar.a(false);
        if (this.R.a(xVar, dVar, dVar2)) {
            t();
        }
    }

    void b(String str) {
        if (s()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + a());
        } else if (this.aE > 0) {
            Log.w(a, "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(com.lge.media.launcher.a.d + a()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(boolean z) {
        this.aD--;
        if (this.aD < 1) {
            this.aD = 0;
            if (z) {
                L();
                D();
            }
        }
    }

    @Override // android.support.v4.view.p
    public boolean b(int i2) {
        return getScrollingChildHelper().a(i2);
    }

    boolean b(x xVar) {
        f fVar = this.R;
        return fVar == null || fVar.a(xVar, xVar.w());
    }

    @android.support.annotation.af
    public h c(int i2) {
        int itemDecorationCount = getItemDecorationCount();
        if (i2 < 0 || i2 >= itemDecorationCount) {
            throw new IndexOutOfBoundsException(i2 + " is an invalid index for size " + itemDecorationCount);
        }
        return this.H.get(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:?, code lost:
        return r3;
     */
    @android.support.annotation.ag
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View c(@android.support.annotation.af android.view.View r3) {
        /*
            r2 = this;
        L0:
            android.view.ViewParent r0 = r3.getParent()
            if (r0 == 0) goto L10
            if (r0 == r2) goto L10
            boolean r1 = r0 instanceof android.view.View
            if (r1 == 0) goto L10
            r3 = r0
            android.view.View r3 = (android.view.View) r3
            goto L0
        L10:
            if (r0 != r2) goto L13
            goto L14
        L13:
            r3 = 0
        L14:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.c(android.view.View):android.view.View");
    }

    void c(boolean z) {
        this.Q = z | this.Q;
        this.P = true;
        z();
    }

    public boolean c() {
        return this.J;
    }

    public boolean c(int i2, int i3) {
        i iVar = this.F;
        if (iVar == null) {
            Log.e(a, "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.N) {
            return false;
        } else {
            boolean h2 = iVar.h();
            boolean i4 = this.F.i();
            i2 = (!h2 || Math.abs(i2) < this.aU) ? 0 : 0;
            i3 = (!i4 || Math.abs(i3) < this.aU) ? 0 : 0;
            if (i2 == 0 && i3 == 0) {
                return false;
            }
            float f2 = i2;
            float f3 = i3;
            if (!dispatchNestedPreFling(f2, f3)) {
                boolean z = h2 || i4;
                dispatchNestedFling(f2, f3, z);
                k kVar = this.aT;
                if (kVar != null && kVar.a(i2, i3)) {
                    return true;
                }
                if (z) {
                    int i5 = h2 ? 1 : 0;
                    if (i4) {
                        i5 |= 2;
                    }
                    a(i5, 1);
                    int i6 = this.aV;
                    int max = Math.max(-i6, Math.min(i2, i6));
                    int i7 = this.aV;
                    this.W.a(max, Math.max(-i7, Math.min(i3, i7)));
                    return true;
                }
            }
            return false;
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.F.a((LayoutParams) layoutParams);
    }

    @Override // android.view.View, android.support.v4.view.y
    public int computeHorizontalScrollExtent() {
        i iVar = this.F;
        if (iVar != null && iVar.h()) {
            return this.F.e(this.ac);
        }
        return 0;
    }

    @Override // android.view.View, android.support.v4.view.y
    public int computeHorizontalScrollOffset() {
        i iVar = this.F;
        if (iVar != null && iVar.h()) {
            return this.F.c(this.ac);
        }
        return 0;
    }

    @Override // android.view.View, android.support.v4.view.y
    public int computeHorizontalScrollRange() {
        i iVar = this.F;
        if (iVar != null && iVar.h()) {
            return this.F.g(this.ac);
        }
        return 0;
    }

    @Override // android.view.View, android.support.v4.view.y
    public int computeVerticalScrollExtent() {
        i iVar = this.F;
        if (iVar != null && iVar.i()) {
            return this.F.f(this.ac);
        }
        return 0;
    }

    @Override // android.view.View, android.support.v4.view.y
    public int computeVerticalScrollOffset() {
        i iVar = this.F;
        if (iVar != null && iVar.i()) {
            return this.F.d(this.ac);
        }
        return 0;
    }

    @Override // android.view.View, android.support.v4.view.y
    public int computeVerticalScrollRange() {
        i iVar = this.F;
        if (iVar != null && iVar.i()) {
            return this.F.h(this.ac);
        }
        return 0;
    }

    int d(x xVar) {
        if (xVar.a(524) || !xVar.r()) {
            return -1;
        }
        return this.x.c(xVar.c);
    }

    @android.support.annotation.ag
    public x d(@android.support.annotation.af View view) {
        View c2 = c(view);
        if (c2 == null) {
            return null;
        }
        return b(c2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        f fVar = this.R;
        if (fVar != null) {
            fVar.d();
        }
        i iVar = this.F;
        if (iVar != null) {
            iVar.c(this.w);
            this.F.b(this.w);
        }
        this.w.a();
    }

    public void d(int i2) {
        int itemDecorationCount = getItemDecorationCount();
        if (i2 >= 0 && i2 < itemDecorationCount) {
            b(c(i2));
            return;
        }
        throw new IndexOutOfBoundsException(i2 + " is an invalid index for size " + itemDecorationCount);
    }

    void d(int i2, int i3) {
        boolean z;
        EdgeEffect edgeEffect = this.aG;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            z = false;
        } else {
            this.aG.onRelease();
            z = this.aG.isFinished();
        }
        EdgeEffect edgeEffect2 = this.aI;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.aI.onRelease();
            z |= this.aI.isFinished();
        }
        EdgeEffect edgeEffect3 = this.aH;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i3 > 0) {
            this.aH.onRelease();
            z |= this.aH.isFinished();
        }
        EdgeEffect edgeEffect4 = this.aJ;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i3 < 0) {
            this.aJ.onRelease();
            z |= this.aJ.isFinished();
        }
        if (z) {
            android.support.v4.view.ab.f(this);
        }
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return getScrollingChildHelper().a(f2, f3, z);
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return getScrollingChildHelper().a(f2, f3);
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().a(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return getScrollingChildHelper().a(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        boolean z2;
        float f2;
        int i2;
        super.draw(canvas);
        int size = this.H.size();
        boolean z3 = false;
        for (int i3 = 0; i3 < size; i3++) {
            this.H.get(i3).b(canvas, this, this.ac);
        }
        EdgeEffect edgeEffect = this.aG;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.A ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.aG;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.aH;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.A) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.aH;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.aI;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.A ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.aI;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.aJ;
        if (edgeEffect7 == null || edgeEffect7.isFinished()) {
            z2 = z;
        } else {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.A) {
                f2 = (-getWidth()) + getPaddingRight();
                i2 = (-getHeight()) + getPaddingBottom();
            } else {
                f2 = -getWidth();
                i2 = -getHeight();
            }
            canvas.translate(f2, i2);
            EdgeEffect edgeEffect8 = this.aJ;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z3 = true;
            }
            z2 = z3 | z;
            canvas.restoreToCount(save4);
        }
        if (!z2 && this.R != null && this.H.size() > 0 && this.R.b()) {
            z2 = true;
        }
        if (z2) {
            android.support.v4.view.ab.f(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    public void e() {
        List<j> list = this.aC;
        if (list != null) {
            list.clear();
        }
    }

    public void e(int i2) {
        if (this.N) {
            return;
        }
        j();
        i iVar = this.F;
        if (iVar == null) {
            Log.e(a, "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        iVar.e(i2);
        awakenScrollBars();
    }

    void e(int i2, int i3) {
        if (i2 < 0) {
            k();
            this.aG.onAbsorb(-i2);
        } else if (i2 > 0) {
            l();
            this.aI.onAbsorb(i2);
        }
        if (i3 < 0) {
            m();
            this.aH.onAbsorb(-i3);
        } else if (i3 > 0) {
            n();
            this.aJ.onAbsorb(i3);
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        android.support.v4.view.ab.f(this);
    }

    @Deprecated
    public int f(@android.support.annotation.af View view) {
        return g(view);
    }

    public void f() {
        List<m> list = this.ba;
        if (list != null) {
            list.clear();
        }
    }

    void f(int i2) {
        i iVar = this.F;
        if (iVar == null) {
            return;
        }
        iVar.e(i2);
        awakenScrollBars();
    }

    void f(int i2, int i3) {
        setMeasuredDimension(i.a(i2, getPaddingLeft() + getPaddingRight(), android.support.v4.view.ab.A(this)), i.a(i3, getPaddingTop() + getPaddingBottom(), android.support.v4.view.ab.B(this)));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i2) {
        View view2;
        boolean z;
        View d2 = this.F.d(view, i2);
        if (d2 != null) {
            return d2;
        }
        boolean z2 = (this.E == null || this.F == null || s() || this.N) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z2 && (i2 == 2 || i2 == 1)) {
            if (this.F.i()) {
                int i3 = i2 == 2 ? 130 : 33;
                z = focusFinder.findNextFocus(this, view, i3) == null;
                if (an) {
                    i2 = i3;
                }
            } else {
                z = false;
            }
            if (!z && this.F.h()) {
                int i4 = (this.F.D() == 1) ^ (i2 == 2) ? 66 : 17;
                z = focusFinder.findNextFocus(this, view, i4) == null;
                if (an) {
                    i2 = i4;
                }
            }
            if (z) {
                g();
                if (c(view) == null) {
                    return null;
                }
                h();
                this.F.a(view, i2, this.w, this.ac);
                a(false);
            }
            view2 = focusFinder.findNextFocus(this, view, i2);
        } else {
            View findNextFocus = focusFinder.findNextFocus(this, view, i2);
            if (findNextFocus == null && z2) {
                g();
                if (c(view) == null) {
                    return null;
                }
                h();
                view2 = this.F.a(view, i2, this.w, this.ac);
                a(false);
            } else {
                view2 = findNextFocus;
            }
        }
        if (view2 == null || view2.hasFocusable()) {
            return a(view, view2, i2) ? view2 : super.focusSearch(view, i2);
        } else if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        } else {
            a(view2, (View) null);
            return view;
        }
    }

    public int g(@android.support.annotation.af View view) {
        x e2 = e(view);
        if (e2 != null) {
            return e2.f();
        }
        return -1;
    }

    void g() {
        if (!this.L || this.P) {
            android.support.v4.os.o.a(aq);
            u();
            android.support.v4.os.o.a();
        } else if (this.x.d()) {
            if (this.x.a(4) && !this.x.a(11)) {
                android.support.v4.os.o.a(ar);
                h();
                p();
                this.x.b();
                if (!this.M) {
                    if (G()) {
                        u();
                    } else {
                        this.x.c();
                    }
                }
                a(true);
                q();
            } else if (!this.x.d()) {
                return;
            } else {
                android.support.v4.os.o.a(aq);
                u();
            }
            android.support.v4.os.o.a();
        }
    }

    public void g(int i2) {
        if (this.N) {
            return;
        }
        i iVar = this.F;
        if (iVar == null) {
            Log.e(a, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            iVar.a(this, this.ac, i2);
        }
    }

    void g(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int c2 = this.y.c();
        if (i2 < i3) {
            i5 = i2;
            i4 = i3;
            i6 = -1;
        } else {
            i4 = i2;
            i5 = i3;
            i6 = 1;
        }
        for (int i7 = 0; i7 < c2; i7++) {
            x e2 = e(this.y.d(i7));
            if (e2 != null && e2.c >= i5 && e2.c <= i4) {
                if (e2.c == i2) {
                    e2.a(i3 - i2, false);
                } else {
                    e2.a(i6, false);
                }
                this.ac.i = true;
            }
        }
        this.w.a(i2, i3);
        requestLayout();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        i iVar = this.F;
        if (iVar != null) {
            return iVar.a();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        i iVar = this.F;
        if (iVar != null) {
            return iVar.a(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        i iVar = this.F;
        if (iVar != null) {
            return iVar.a(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    @android.support.annotation.ag
    public a getAdapter() {
        return this.E;
    }

    @Override // android.view.View
    public int getBaseline() {
        i iVar = this.F;
        return iVar != null ? iVar.F() : super.getBaseline();
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        d dVar = this.bc;
        return dVar == null ? super.getChildDrawingOrder(i2, i3) : dVar.a(i2, i3);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.A;
    }

    @android.support.annotation.ag
    public ah getCompatAccessibilityDelegate() {
        return this.ag;
    }

    @android.support.annotation.af
    public e getEdgeEffectFactory() {
        return this.aF;
    }

    @android.support.annotation.ag
    public f getItemAnimator() {
        return this.R;
    }

    public int getItemDecorationCount() {
        return this.H.size();
    }

    @android.support.annotation.ag
    public i getLayoutManager() {
        return this.F;
    }

    public int getMaxFlingVelocity() {
        return this.aV;
    }

    public int getMinFlingVelocity() {
        return this.aU;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getNanoTime() {
        if (g) {
            return System.nanoTime();
        }
        return 0L;
    }

    @android.support.annotation.ag
    public k getOnFlingListener() {
        return this.aT;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.aY;
    }

    @android.support.annotation.af
    public o getRecycledViewPool() {
        return this.w.g();
    }

    public int getScrollState() {
        return this.aL;
    }

    public int h(@android.support.annotation.af View view) {
        x e2 = e(view);
        if (e2 != null) {
            return e2.e();
        }
        return -1;
    }

    @android.support.annotation.ag
    @Deprecated
    public x h(int i2) {
        return a(i2, false);
    }

    void h() {
        this.ay++;
        if (this.ay != 1 || this.N) {
            return;
        }
        this.M = false;
    }

    void h(int i2, int i3) {
        int c2 = this.y.c();
        for (int i4 = 0; i4 < c2; i4++) {
            x e2 = e(this.y.d(i4));
            if (e2 != null && !e2.c() && e2.c >= i2) {
                e2.a(i3, false);
                this.ac.i = true;
            }
        }
        this.w.b(i2, i3);
        requestLayout();
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().b();
    }

    public long i(@android.support.annotation.af View view) {
        x e2;
        a aVar = this.E;
        if (aVar == null || !aVar.b() || (e2 = e(view)) == null) {
            return -1L;
        }
        return e2.h();
    }

    @android.support.annotation.ag
    public x i(int i2) {
        return a(i2, false);
    }

    public void i(@android.support.annotation.ai int i2, @android.support.annotation.ai int i3) {
    }

    public boolean i() {
        return this.N;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.I;
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().a();
    }

    @android.support.annotation.ag
    public x j(int i2) {
        x xVar = null;
        if (this.P) {
            return null;
        }
        int c2 = this.y.c();
        for (int i3 = 0; i3 < c2; i3++) {
            x e2 = e(this.y.d(i3));
            if (e2 != null && !e2.s() && d(e2) == i2) {
                if (!this.y.c(e2.a)) {
                    return e2;
                }
                xVar = e2;
            }
        }
        return xVar;
    }

    public void j() {
        setScrollState(0);
        H();
    }

    void j(int i2, int i3) {
        this.aE++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        i(i2, i3);
        m mVar = this.aZ;
        if (mVar != null) {
            mVar.a(this, i2, i3);
        }
        List<m> list = this.ba;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.ba.get(size).a(this, i2, i3);
            }
        }
        this.aE--;
    }

    public void j(@android.support.annotation.af View view) {
    }

    void k() {
        EdgeEffect edgeEffect;
        int measuredHeight;
        int measuredWidth;
        if (this.aG != null) {
            return;
        }
        this.aG = this.aF.a(this, 0);
        if (this.A) {
            edgeEffect = this.aG;
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        } else {
            edgeEffect = this.aG;
            measuredHeight = getMeasuredHeight();
            measuredWidth = getMeasuredWidth();
        }
        edgeEffect.setSize(measuredHeight, measuredWidth);
    }

    public void k(@android.support.annotation.ai int i2) {
        int b2 = this.y.b();
        for (int i3 = 0; i3 < b2; i3++) {
            this.y.b(i3).offsetTopAndBottom(i2);
        }
    }

    public void k(@android.support.annotation.af View view) {
    }

    Rect l(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.f) {
            if (this.ac.c() && (layoutParams.f() || layoutParams.d())) {
                return layoutParams.e;
            }
            Rect rect = layoutParams.e;
            rect.set(0, 0, 0, 0);
            int size = this.H.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.C.set(0, 0, 0, 0);
                this.H.get(i2).a(this.C, view, this, this.ac);
                rect.left += this.C.left;
                rect.top += this.C.top;
                rect.right += this.C.right;
                rect.bottom += this.C.bottom;
            }
            layoutParams.f = false;
            return rect;
        }
        return layoutParams.e;
    }

    void l() {
        EdgeEffect edgeEffect;
        int measuredHeight;
        int measuredWidth;
        if (this.aI != null) {
            return;
        }
        this.aI = this.aF.a(this, 2);
        if (this.A) {
            edgeEffect = this.aI;
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        } else {
            edgeEffect = this.aI;
            measuredHeight = getMeasuredHeight();
            measuredWidth = getMeasuredWidth();
        }
        edgeEffect.setSize(measuredHeight, measuredWidth);
    }

    public void l(@android.support.annotation.ai int i2) {
        int b2 = this.y.b();
        for (int i3 = 0; i3 < b2; i3++) {
            this.y.b(i3).offsetLeftAndRight(i2);
        }
    }

    void m() {
        EdgeEffect edgeEffect;
        int measuredWidth;
        int measuredHeight;
        if (this.aH != null) {
            return;
        }
        this.aH = this.aF.a(this, 1);
        if (this.A) {
            edgeEffect = this.aH;
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        } else {
            edgeEffect = this.aH;
            measuredWidth = getMeasuredWidth();
            measuredHeight = getMeasuredHeight();
        }
        edgeEffect.setSize(measuredWidth, measuredHeight);
    }

    public void m(int i2) {
    }

    void n() {
        EdgeEffect edgeEffect;
        int measuredWidth;
        int measuredHeight;
        if (this.aJ != null) {
            return;
        }
        this.aJ = this.aF.a(this, 3);
        if (this.A) {
            edgeEffect = this.aJ;
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        } else {
            edgeEffect = this.aJ;
            measuredWidth = getMeasuredWidth();
            measuredHeight = getMeasuredHeight();
        }
        edgeEffect.setSize(measuredWidth, measuredHeight);
    }

    void n(int i2) {
        i iVar = this.F;
        if (iVar != null) {
            iVar.m(i2);
        }
        m(i2);
        m mVar = this.aZ;
        if (mVar != null) {
            mVar.a(this, i2);
        }
        List<m> list = this.ba;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.ba.get(size).a(this, i2);
            }
        }
    }

    void n(View view) {
        x e2 = e(view);
        k(view);
        a aVar = this.E;
        if (aVar != null && e2 != null) {
            aVar.d((a) e2);
        }
        List<j> list = this.aC;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.aC.get(size).b(view);
            }
        }
    }

    void o() {
        this.aJ = null;
        this.aH = null;
        this.aI = null;
        this.aG = null;
    }

    void o(View view) {
        x e2 = e(view);
        j(view);
        a aVar = this.E;
        if (aVar != null && e2 != null) {
            aVar.c((a) e2);
        }
        List<j> list = this.aC;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.aC.get(size).a(view);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004f, code lost:
        if (r0 >= 30.0f) goto L17;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onAttachedToWindow() {
        /*
            r4 = this;
            super.onAttachedToWindow()
            r0 = 0
            r4.aD = r0
            r1 = 1
            r4.I = r1
            boolean r2 = r4.L
            if (r2 == 0) goto L14
            boolean r2 = r4.isLayoutRequested()
            if (r2 != 0) goto L14
            goto L15
        L14:
            r1 = 0
        L15:
            r4.L = r1
            android.support.v7.widget.RecyclerView$i r1 = r4.F
            if (r1 == 0) goto L1e
            r1.c(r4)
        L1e:
            r4.af = r0
            boolean r0 = android.support.v7.widget.RecyclerView.g
            if (r0 == 0) goto L69
            java.lang.ThreadLocal<android.support.v7.widget.x> r0 = android.support.v7.widget.x.a
            java.lang.Object r0 = r0.get()
            android.support.v7.widget.x r0 = (android.support.v7.widget.x) r0
            r4.aa = r0
            android.support.v7.widget.x r0 = r4.aa
            if (r0 != 0) goto L64
            android.support.v7.widget.x r0 = new android.support.v7.widget.x
            r0.<init>()
            r4.aa = r0
            android.view.Display r0 = android.support.v4.view.ab.ai(r4)
            r1 = 1114636288(0x42700000, float:60.0)
            boolean r2 = r4.isInEditMode()
            if (r2 != 0) goto L52
            if (r0 == 0) goto L52
            float r0 = r0.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 < 0) goto L52
            goto L54
        L52:
            r0 = 1114636288(0x42700000, float:60.0)
        L54:
            android.support.v7.widget.x r1 = r4.aa
            r2 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r2 = r2 / r0
            long r2 = (long) r2
            r1.d = r2
            java.lang.ThreadLocal<android.support.v7.widget.x> r0 = android.support.v7.widget.x.a
            android.support.v7.widget.x r1 = r4.aa
            r0.set(r1)
        L64:
            android.support.v7.widget.x r0 = r4.aa
            r0.a(r4)
        L69:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onAttachedToWindow():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        android.support.v7.widget.x xVar;
        super.onDetachedFromWindow();
        f fVar = this.R;
        if (fVar != null) {
            fVar.d();
        }
        j();
        this.I = false;
        i iVar = this.F;
        if (iVar != null) {
            iVar.b(this, this.w);
        }
        this.aj.clear();
        removeCallbacks(this.bh);
        this.z.b();
        if (!g || (xVar = this.aa) == null) {
            return;
        }
        xVar.b(this);
        this.aa = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.H.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.H.get(i2).a(canvas, this, this.ac);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0066  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.support.v7.widget.RecyclerView$i r0 = r5.F
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r5.N
            if (r0 == 0) goto Lb
            return r1
        Lb:
            int r0 = r6.getAction()
            r2 = 8
            if (r0 != r2) goto L77
            int r0 = r6.getSource()
            r0 = r0 & 2
            r2 = 0
            if (r0 == 0) goto L3c
            android.support.v7.widget.RecyclerView$i r0 = r5.F
            boolean r0 = r0.i()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = 0
        L2d:
            android.support.v7.widget.RecyclerView$i r3 = r5.F
            boolean r3 = r3.h()
            if (r3 == 0) goto L61
            r3 = 10
            float r3 = r6.getAxisValue(r3)
            goto L62
        L3c:
            int r0 = r6.getSource()
            r3 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r3
            if (r0 == 0) goto L60
            r0 = 26
            float r0 = r6.getAxisValue(r0)
            android.support.v7.widget.RecyclerView$i r3 = r5.F
            boolean r3 = r3.i()
            if (r3 == 0) goto L55
            float r0 = -r0
            goto L61
        L55:
            android.support.v7.widget.RecyclerView$i r3 = r5.F
            boolean r3 = r3.h()
            if (r3 == 0) goto L60
            r3 = r0
            r0 = 0
            goto L62
        L60:
            r0 = 0
        L61:
            r3 = 0
        L62:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L6a
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 == 0) goto L77
        L6a:
            float r2 = r5.aW
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.aX
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.a(r2, r0, r6)
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (this.N) {
            return false;
        }
        if (a(motionEvent)) {
            K();
            return true;
        }
        i iVar = this.F;
        if (iVar == null) {
            return false;
        }
        boolean h2 = iVar.h();
        boolean i2 = this.F.i();
        if (this.aN == null) {
            this.aN = VelocityTracker.obtain();
        }
        this.aN.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.az) {
                this.az = false;
            }
            this.aM = motionEvent.getPointerId(0);
            int x2 = (int) (motionEvent.getX() + 0.5f);
            this.aQ = x2;
            this.aO = x2;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.aR = y;
            this.aP = y;
            if (this.aL == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
            }
            int[] iArr = this.bg;
            iArr[1] = 0;
            iArr[0] = 0;
            int i3 = h2 ? 1 : 0;
            if (i2) {
                i3 |= 2;
            }
            a(i3, 0);
        } else if (actionMasked == 1) {
            this.aN.clear();
            a(0);
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.aM);
            if (findPointerIndex < 0) {
                Log.e(a, "Error processing scroll; pointer index for id " + this.aM + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x3 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
            if (this.aL != 1) {
                int i4 = x3 - this.aO;
                int i5 = y2 - this.aP;
                if (!h2 || Math.abs(i4) <= this.aS) {
                    z = false;
                } else {
                    this.aQ = x3;
                    z = true;
                }
                if (i2 && Math.abs(i5) > this.aS) {
                    this.aR = y2;
                    z = true;
                }
                if (z) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            K();
        } else if (actionMasked == 5) {
            this.aM = motionEvent.getPointerId(actionIndex);
            int x4 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.aQ = x4;
            this.aO = x4;
            int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.aR = y3;
            this.aP = y3;
        } else if (actionMasked == 6) {
            c(motionEvent);
        }
        return this.aL == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        android.support.v4.os.o.a(ap);
        u();
        android.support.v4.os.o.a();
        this.L = true;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        i iVar = this.F;
        if (iVar == null) {
            f(i2, i3);
            return;
        }
        boolean z = false;
        if (iVar.e()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.F.a(this.w, this.ac, i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            if (z || this.E == null) {
                return;
            }
            if (this.ac.g == 1) {
                S();
            }
            this.F.d(i2, i3);
            this.ac.l = true;
            T();
            this.F.e(i2, i3);
            if (this.F.s()) {
                this.F.d(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.ac.l = true;
                T();
                this.F.e(i2, i3);
            }
        } else if (this.J) {
            this.F.a(this.w, this.ac, i2, i3);
        } else {
            if (this.O) {
                h();
                p();
                N();
                q();
                if (this.ac.n) {
                    this.ac.j = true;
                } else {
                    this.x.e();
                    this.ac.j = false;
                }
                this.O = false;
                a(false);
            } else if (this.ac.n) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            a aVar = this.E;
            if (aVar != null) {
                this.ac.h = aVar.a();
            } else {
                this.ac.h = 0;
            }
            h();
            this.F.a(this.w, this.ac, i2, i3);
            a(false);
            this.ac.j = false;
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (s()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        this.au = (SavedState) parcelable;
        super.onRestoreInstanceState(this.au.a());
        if (this.F == null || this.au.a == null) {
            return;
        }
        this.F.a(this.au.a);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.au;
        if (savedState2 != null) {
            savedState.a(savedState2);
        } else {
            i iVar = this.F;
            savedState.a = iVar != null ? iVar.g() : null;
        }
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        o();
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x011c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r15) {
        /*
            Method dump skipped, instructions count: 456
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p() {
        this.aD++;
    }

    void q() {
        b(true);
    }

    boolean r() {
        AccessibilityManager accessibilityManager = this.aB;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    @Override // android.view.ViewGroup
    protected void removeDetachedView(View view, boolean z) {
        x e2 = e(view);
        if (e2 != null) {
            if (e2.t()) {
                e2.n();
            } else if (!e2.c()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + e2 + a());
            }
        }
        view.clearAnimation();
        n(view);
        super.removeDetachedView(view, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.F.a(this, this.ac, view, view2) && view2 != null) {
            a(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.F.a(this, view, rect, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.aw.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.aw.get(i2).a(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ay != 0 || this.N) {
            this.M = true;
        } else {
            super.requestLayout();
        }
    }

    public boolean s() {
        return this.aD > 0;
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        i iVar = this.F;
        if (iVar == null) {
            Log.e(a, "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (this.N) {
        } else {
            boolean h2 = iVar.h();
            boolean i4 = this.F.i();
            if (h2 || i4) {
                if (!h2) {
                    i2 = 0;
                }
                if (!i4) {
                    i3 = 0;
                }
                a(i2, i3, (MotionEvent) null);
            }
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        Log.w(a, "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (a(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(@android.support.annotation.ag ah ahVar) {
        this.ag = ahVar;
        android.support.v4.view.ab.a(this, this.ag);
    }

    public void setAdapter(@android.support.annotation.ag a aVar) {
        setLayoutFrozen(false);
        a(aVar, false, true);
        c(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(@android.support.annotation.ag d dVar) {
        if (dVar == this.bc) {
            return;
        }
        this.bc = dVar;
        setChildrenDrawingOrderEnabled(this.bc != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if (z != this.A) {
            o();
        }
        this.A = z;
        super.setClipToPadding(z);
        if (this.L) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(@android.support.annotation.af e eVar) {
        android.support.v4.j.q.a(eVar);
        this.aF = eVar;
        o();
    }

    public void setHasFixedSize(boolean z) {
        this.J = z;
    }

    public void setItemAnimator(@android.support.annotation.ag f fVar) {
        f fVar2 = this.R;
        if (fVar2 != null) {
            fVar2.d();
            this.R.a((f.c) null);
        }
        this.R = fVar;
        f fVar3 = this.R;
        if (fVar3 != null) {
            fVar3.a(this.bb);
        }
    }

    public void setItemViewCacheSize(int i2) {
        this.w.a(i2);
    }

    public void setLayoutFrozen(boolean z) {
        if (z != this.N) {
            b("Do not setLayoutFrozen in layout or scroll");
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
                this.N = true;
                this.az = true;
                j();
                return;
            }
            this.N = false;
            if (this.M && this.F != null && this.E != null) {
                requestLayout();
            }
            this.M = false;
        }
    }

    public void setLayoutManager(@android.support.annotation.ag i iVar) {
        if (iVar == this.F) {
            return;
        }
        j();
        if (this.F != null) {
            f fVar = this.R;
            if (fVar != null) {
                fVar.d();
            }
            this.F.c(this.w);
            this.F.b(this.w);
            this.w.a();
            if (this.I) {
                this.F.b(this, this.w);
            }
            this.F.b((RecyclerView) null);
            this.F = null;
        } else {
            this.w.a();
        }
        this.y.a();
        this.F = iVar;
        if (iVar != null) {
            if (iVar.v != null) {
                throw new IllegalArgumentException("LayoutManager " + iVar + " is already attached to a RecyclerView:" + iVar.v.a());
            }
            this.F.b(this);
            if (this.I) {
                this.F.c(this);
            }
        }
        this.w.b();
        requestLayout();
    }

    @Override // android.view.View, android.support.v4.view.o
    public void setNestedScrollingEnabled(boolean z) {
        getScrollingChildHelper().a(z);
    }

    public void setOnFlingListener(@android.support.annotation.ag k kVar) {
        this.aT = kVar;
    }

    @Deprecated
    public void setOnScrollListener(@android.support.annotation.ag m mVar) {
        this.aZ = mVar;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.aY = z;
    }

    public void setRecycledViewPool(@android.support.annotation.ag o oVar) {
        this.w.a(oVar);
    }

    public void setRecyclerListener(@android.support.annotation.ag q qVar) {
        this.G = qVar;
    }

    void setScrollState(int i2) {
        if (i2 == this.aL) {
            return;
        }
        this.aL = i2;
        if (i2 != 2) {
            H();
        }
        n(i2);
    }

    public void setScrollingTouchSlop(int i2) {
        int scaledTouchSlop;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i2 != 0) {
            if (i2 == 1) {
                scaledTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
                this.aS = scaledTouchSlop;
            }
            Log.w(a, "setScrollingTouchSlop(): bad argument constant " + i2 + "; using default value");
        }
        scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.aS = scaledTouchSlop;
    }

    public void setViewCacheExtension(@android.support.annotation.ag v vVar) {
        this.w.a(vVar);
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().b(i2);
    }

    @Override // android.view.View, android.support.v4.view.o
    public void stopNestedScroll() {
        getScrollingChildHelper().c();
    }

    void t() {
        if (this.af || !this.I) {
            return;
        }
        android.support.v4.view.ab.a(this, this.bh);
        this.af = true;
    }

    void u() {
        String str;
        if (this.E == null) {
            str = "No adapter attached; skipping layout";
        } else if (this.F != null) {
            u uVar = this.ac;
            uVar.l = false;
            if (uVar.g == 1) {
                S();
            } else if (!this.x.f() && this.F.J() == getWidth() && this.F.K() == getHeight()) {
                this.F.f(this);
                U();
                return;
            }
            this.F.f(this);
            T();
            U();
            return;
        } else {
            str = "No layout manager attached; skipping layout";
        }
        Log.e(a, str);
    }

    void v() {
        int c2 = this.y.c();
        for (int i2 = 0; i2 < c2; i2++) {
            ((LayoutParams) this.y.d(i2).getLayoutParams()).f = true;
        }
        this.w.j();
    }

    public boolean w() {
        f fVar = this.R;
        return fVar != null && fVar.b();
    }

    void x() {
        int c2 = this.y.c();
        for (int i2 = 0; i2 < c2; i2++) {
            x e2 = e(this.y.d(i2));
            if (!e2.c()) {
                e2.b();
            }
        }
    }

    void y() {
        int c2 = this.y.c();
        for (int i2 = 0; i2 < c2; i2++) {
            x e2 = e(this.y.d(i2));
            if (!e2.c()) {
                e2.a();
            }
        }
        this.w.i();
    }

    void z() {
        int c2 = this.y.c();
        for (int i2 = 0; i2 < c2; i2++) {
            x e2 = e(this.y.d(i2));
            if (e2 != null && !e2.c()) {
                e2.b(6);
            }
        }
        v();
        this.w.h();
    }
}
