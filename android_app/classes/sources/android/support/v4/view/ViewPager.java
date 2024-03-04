package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ViewPager extends ViewGroup {
    private static final int W = -1;
    private static final int af = 2;
    private static final int au = 0;
    private static final int av = 1;
    private static final int aw = 2;
    public static final int d = 0;
    public static final int e = 1;
    public static final int f = 2;
    private static final String g = "ViewPager";
    private static final boolean h = false;
    private static final boolean i = false;
    private static final int j = 1;
    private static final int k = 600;
    private static final int l = 25;
    private static final int m = 16;
    private static final int n = 400;
    private int A;
    private Drawable B;
    private int C;
    private int D;
    private float E;
    private float F;
    private int G;
    private int H;
    private boolean I;
    private boolean J;
    private boolean K;
    private int L;
    private boolean M;
    private boolean N;
    private int O;
    private int P;
    private int Q;
    private float R;
    private float S;
    private float T;
    private float U;
    private int V;
    private final Runnable aA;
    private int aB;
    private VelocityTracker aa;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private boolean ag;
    private long ah;
    private EdgeEffect ai;
    private EdgeEffect aj;
    private boolean ak;
    private boolean al;
    private boolean am;
    private int an;
    private List<e> ao;
    private e ap;
    private e aq;
    private List<d> ar;
    private f as;
    private int at;
    private int ax;
    private ArrayList<View> ay;
    v b;
    int c;
    private int o;
    private final ArrayList<b> r;
    private final b s;
    private final Rect t;
    private int u;
    private Parcelable v;
    private ClassLoader w;
    private Scroller x;
    private boolean y;
    private g z;
    static final int[] a = {16842931};
    private static final Comparator<b> p = new Comparator<b>() { // from class: android.support.v4.view.ViewPager.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(b bVar, b bVar2) {
            return bVar.b - bVar2.b;
        }
    };
    private static final Interpolator q = new Interpolator() { // from class: android.support.v4.view.ViewPager.2
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    };
    private static final i az = new i();

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LayoutParams extends ViewGroup.LayoutParams {
        public boolean a;
        public int b;
        float c;
        boolean d;
        int e;
        int f;

        public LayoutParams() {
            super(-1, -1);
            this.c = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.c = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.a);
            this.b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.v4.view.ViewPager.SavedState.1
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
        Parcelable c;
        ClassLoader d;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.a = parcel.readInt();
            this.c = parcel.readParcelable(classLoader);
            this.d = classLoader;
        }

        public SavedState(@android.support.annotation.af Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.a + "}";
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeParcelable(this.c, i);
        }
    }

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        Object a;
        int b;
        boolean c;
        float d;
        float e;

        b() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class c extends android.support.v4.view.a {
        c() {
        }

        private boolean b() {
            return ViewPager.this.b != null && ViewPager.this.b.getCount() > 1;
        }

        @Override // android.support.v4.view.a
        public void a(View view, android.support.v4.view.a.c cVar) {
            super.a(view, cVar);
            cVar.b((CharSequence) ViewPager.class.getName());
            cVar.l(b());
            if (ViewPager.this.canScrollHorizontally(1)) {
                cVar.d(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                cVar.d(8192);
            }
        }

        @Override // android.support.v4.view.a
        public boolean a(View view, int i, Bundle bundle) {
            ViewPager viewPager;
            int i2;
            if (super.a(view, i, bundle)) {
                return true;
            }
            if (i != 4096) {
                if (i != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                    return false;
                }
                viewPager = ViewPager.this;
                i2 = viewPager.c - 1;
            } else if (!ViewPager.this.canScrollHorizontally(1)) {
                return false;
            } else {
                viewPager = ViewPager.this;
                i2 = viewPager.c + 1;
            }
            viewPager.setCurrentItem(i2);
            return true;
        }

        @Override // android.support.v4.view.a
        public void d(View view, AccessibilityEvent accessibilityEvent) {
            super.d(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(b());
            if (accessibilityEvent.getEventType() != 4096 || ViewPager.this.b == null) {
                return;
            }
            accessibilityEvent.setItemCount(ViewPager.this.b.getCount());
            accessibilityEvent.setFromIndex(ViewPager.this.c);
            accessibilityEvent.setToIndex(ViewPager.this.c);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface d {
        void a(@android.support.annotation.af ViewPager viewPager, @android.support.annotation.ag v vVar, @android.support.annotation.ag v vVar2);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface e {
        void a(int i);

        void a(int i, float f, @android.support.annotation.ai int i2);

        void b(int i);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface f {
        void a(@android.support.annotation.af View view, float f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class g extends DataSetObserver {
        g() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            ViewPager.this.c();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ViewPager.this.c();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class h implements e {
        @Override // android.support.v4.view.ViewPager.e
        public void a(int i) {
        }

        @Override // android.support.v4.view.ViewPager.e
        public void a(int i, float f, int i2) {
        }

        @Override // android.support.v4.view.ViewPager.e
        public void b(int i) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class i implements Comparator<View> {
        i() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(View view, View view2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
            return layoutParams.a != layoutParams2.a ? layoutParams.a ? 1 : -1 : layoutParams.e - layoutParams2.e;
        }
    }

    public ViewPager(@android.support.annotation.af Context context) {
        super(context);
        this.r = new ArrayList<>();
        this.s = new b();
        this.t = new Rect();
        this.u = -1;
        this.v = null;
        this.w = null;
        this.E = -3.4028235E38f;
        this.F = Float.MAX_VALUE;
        this.L = 1;
        this.V = -1;
        this.ak = true;
        this.al = false;
        this.aA = new Runnable() { // from class: android.support.v4.view.ViewPager.3
            @Override // java.lang.Runnable
            public void run() {
                ViewPager.this.setScrollState(0);
                ViewPager.this.d();
            }
        };
        this.aB = 0;
        a();
    }

    public ViewPager(@android.support.annotation.af Context context, @android.support.annotation.ag AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = new ArrayList<>();
        this.s = new b();
        this.t = new Rect();
        this.u = -1;
        this.v = null;
        this.w = null;
        this.E = -3.4028235E38f;
        this.F = Float.MAX_VALUE;
        this.L = 1;
        this.V = -1;
        this.ak = true;
        this.al = false;
        this.aA = new Runnable() { // from class: android.support.v4.view.ViewPager.3
            @Override // java.lang.Runnable
            public void run() {
                ViewPager.this.setScrollState(0);
                ViewPager.this.d();
            }
        };
        this.aB = 0;
        a();
    }

    private int a(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.ad || Math.abs(i3) <= this.ab) {
            i2 += (int) (f2 + (i2 >= this.c ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        if (this.r.size() > 0) {
            ArrayList<b> arrayList = this.r;
            return Math.max(this.r.get(0).b, Math.min(i2, arrayList.get(arrayList.size() - 1).b));
        }
        return i2;
    }

    private Rect a(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    private void a(int i2, int i3, int i4, int i5) {
        int min;
        if (i3 <= 0 || this.r.isEmpty()) {
            b b2 = b(this.c);
            min = (int) ((b2 != null ? Math.min(b2.e, this.F) : 0.0f) * ((i2 - getPaddingLeft()) - getPaddingRight()));
            if (min == getScrollX()) {
                return;
            }
            a(false);
        } else if (!this.x.isFinished()) {
            this.x.setFinalX(getCurrentItem() * getClientWidth());
            return;
        } else {
            min = (int) ((getScrollX() / (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)) * (((i2 - getPaddingLeft()) - getPaddingRight()) + i4));
        }
        scrollTo(min, getScrollY());
    }

    private void a(int i2, boolean z, int i3, boolean z2) {
        b b2 = b(i2);
        int clientWidth = b2 != null ? (int) (getClientWidth() * Math.max(this.E, Math.min(b2.e, this.F))) : 0;
        if (z) {
            a(clientWidth, 0, i3);
            if (z2) {
                e(i2);
                return;
            }
            return;
        }
        if (z2) {
            e(i2);
        }
        a(false);
        scrollTo(clientWidth, 0);
        d(clientWidth);
    }

    private void a(b bVar, int i2, b bVar2) {
        b bVar3;
        b bVar4;
        int count = this.b.getCount();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? this.A / clientWidth : 0.0f;
        if (bVar2 != null) {
            int i3 = bVar2.b;
            if (i3 < bVar.b) {
                float f3 = bVar2.e + bVar2.d + f2;
                int i4 = i3 + 1;
                int i5 = 0;
                while (i4 <= bVar.b && i5 < this.r.size()) {
                    while (true) {
                        bVar4 = this.r.get(i5);
                        if (i4 <= bVar4.b || i5 >= this.r.size() - 1) {
                            break;
                        }
                        i5++;
                    }
                    while (i4 < bVar4.b) {
                        f3 += this.b.getPageWidth(i4) + f2;
                        i4++;
                    }
                    bVar4.e = f3;
                    f3 += bVar4.d + f2;
                    i4++;
                }
            } else if (i3 > bVar.b) {
                int size = this.r.size() - 1;
                float f4 = bVar2.e;
                while (true) {
                    i3--;
                    if (i3 < bVar.b || size < 0) {
                        break;
                    }
                    while (true) {
                        bVar3 = this.r.get(size);
                        if (i3 >= bVar3.b || size <= 0) {
                            break;
                        }
                        size--;
                    }
                    while (i3 > bVar3.b) {
                        f4 -= this.b.getPageWidth(i3) + f2;
                        i3--;
                    }
                    f4 -= bVar3.d + f2;
                    bVar3.e = f4;
                }
            }
        }
        int size2 = this.r.size();
        float f5 = bVar.e;
        int i6 = bVar.b - 1;
        this.E = bVar.b == 0 ? bVar.e : -3.4028235E38f;
        int i7 = count - 1;
        this.F = bVar.b == i7 ? (bVar.e + bVar.d) - 1.0f : Float.MAX_VALUE;
        int i8 = i2 - 1;
        while (i8 >= 0) {
            b bVar5 = this.r.get(i8);
            while (i6 > bVar5.b) {
                f5 -= this.b.getPageWidth(i6) + f2;
                i6--;
            }
            f5 -= bVar5.d + f2;
            bVar5.e = f5;
            if (bVar5.b == 0) {
                this.E = f5;
            }
            i8--;
            i6--;
        }
        float f6 = bVar.e + bVar.d + f2;
        int i9 = bVar.b + 1;
        int i10 = i2 + 1;
        while (i10 < size2) {
            b bVar6 = this.r.get(i10);
            while (i9 < bVar6.b) {
                f6 += this.b.getPageWidth(i9) + f2;
                i9++;
            }
            if (bVar6.b == i7) {
                this.F = (bVar6.d + f6) - 1.0f;
            }
            bVar6.e = f6;
            f6 += bVar6.d + f2;
            i10++;
            i9++;
        }
        this.al = false;
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.V) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.R = motionEvent.getX(i2);
            this.V = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.aa;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void a(boolean z) {
        boolean z2 = this.aB == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (!this.x.isFinished()) {
                this.x.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.x.getCurrX();
                int currY = this.x.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        d(currX);
                    }
                }
            }
        }
        this.K = false;
        boolean z3 = z2;
        for (int i2 = 0; i2 < this.r.size(); i2++) {
            b bVar = this.r.get(i2);
            if (bVar.c) {
                bVar.c = false;
                z3 = true;
            }
        }
        if (z3) {
            if (z) {
                ab.a(this, this.aA);
            } else {
                this.aA.run();
            }
        }
    }

    private boolean a(float f2, float f3) {
        return (f2 < ((float) this.P) && f3 > 0.0f) || (f2 > ((float) (getWidth() - this.P)) && f3 < 0.0f);
    }

    private void b(int i2, float f2, int i3) {
        e eVar = this.ap;
        if (eVar != null) {
            eVar.a(i2, f2, i3);
        }
        List<e> list = this.ao;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                e eVar2 = this.ao.get(i4);
                if (eVar2 != null) {
                    eVar2.a(i2, f2, i3);
                }
            }
        }
        e eVar3 = this.aq;
        if (eVar3 != null) {
            eVar3.a(i2, f2, i3);
        }
    }

    private void b(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).setLayerType(z ? this.at : 0, null);
        }
    }

    private void c(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean c(float f2) {
        boolean z;
        boolean z2;
        float f3 = this.R - f2;
        this.R = f2;
        float scrollX = getScrollX() + f3;
        float clientWidth = getClientWidth();
        float f4 = this.E * clientWidth;
        float f5 = this.F * clientWidth;
        boolean z3 = false;
        b bVar = this.r.get(0);
        ArrayList<b> arrayList = this.r;
        b bVar2 = arrayList.get(arrayList.size() - 1);
        if (bVar.b != 0) {
            f4 = bVar.e * clientWidth;
            z = false;
        } else {
            z = true;
        }
        if (bVar2.b != this.b.getCount() - 1) {
            f5 = bVar2.e * clientWidth;
            z2 = false;
        } else {
            z2 = true;
        }
        if (scrollX < f4) {
            if (z) {
                this.ai.onPull(Math.abs(f4 - scrollX) / clientWidth);
                z3 = true;
            }
            scrollX = f4;
        } else if (scrollX > f5) {
            if (z2) {
                this.aj.onPull(Math.abs(scrollX - f5) / clientWidth);
                z3 = true;
            }
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.R += scrollX - i2;
        scrollTo(i2, getScrollY());
        d(i2);
        return z3;
    }

    private static boolean c(@android.support.annotation.af View view) {
        return view.getClass().getAnnotation(a.class) != null;
    }

    private boolean d(int i2) {
        if (this.r.size() == 0) {
            if (this.ak) {
                return false;
            }
            this.am = false;
            a(0, 0.0f, 0);
            if (this.am) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        b m2 = m();
        int clientWidth = getClientWidth();
        int i3 = this.A;
        int i4 = clientWidth + i3;
        float f2 = clientWidth;
        int i5 = m2.b;
        float f3 = ((i2 / f2) - m2.e) / (m2.d + (i3 / f2));
        this.am = false;
        a(i5, f3, (int) (i4 * f3));
        if (this.am) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private void e(int i2) {
        e eVar = this.ap;
        if (eVar != null) {
            eVar.a(i2);
        }
        List<e> list = this.ao;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                e eVar2 = this.ao.get(i3);
                if (eVar2 != null) {
                    eVar2.a(i2);
                }
            }
        }
        e eVar3 = this.aq;
        if (eVar3 != null) {
            eVar3.a(i2);
        }
    }

    private void f(int i2) {
        e eVar = this.ap;
        if (eVar != null) {
            eVar.b(i2);
        }
        List<e> list = this.ao;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                e eVar2 = this.ao.get(i3);
                if (eVar2 != null) {
                    eVar2.b(i2);
                }
            }
        }
        e eVar3 = this.aq;
        if (eVar3 != null) {
            eVar3.b(i2);
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void j() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((LayoutParams) getChildAt(i2).getLayoutParams()).a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    private void k() {
        if (this.ax != 0) {
            ArrayList<View> arrayList = this.ay;
            if (arrayList == null) {
                this.ay = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.ay.add(getChildAt(i2));
            }
            Collections.sort(this.ay, az);
        }
    }

    private boolean l() {
        this.V = -1;
        n();
        this.ai.onRelease();
        this.aj.onRelease();
        return this.ai.isFinished() || this.aj.isFinished();
    }

    private b m() {
        int i2;
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f2 = clientWidth > 0 ? this.A / clientWidth : 0.0f;
        b bVar = null;
        int i3 = 0;
        boolean z = true;
        int i4 = -1;
        float f3 = 0.0f;
        float f4 = 0.0f;
        while (i3 < this.r.size()) {
            b bVar2 = this.r.get(i3);
            if (!z && bVar2.b != (i2 = i4 + 1)) {
                bVar2 = this.s;
                bVar2.e = f3 + f4 + f2;
                bVar2.b = i2;
                bVar2.d = this.b.getPageWidth(bVar2.b);
                i3--;
            }
            f3 = bVar2.e;
            float f5 = bVar2.d + f3 + f2;
            if (!z && scrollX < f3) {
                return bVar;
            }
            if (scrollX < f5 || i3 == this.r.size() - 1) {
                return bVar2;
            }
            i4 = bVar2.b;
            f4 = bVar2.d;
            i3++;
            bVar = bVar2;
            z = false;
        }
        return bVar;
    }

    private void n() {
        this.M = false;
        this.N = false;
        VelocityTracker velocityTracker = this.aa;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.aa = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.J != z) {
            this.J = z;
        }
    }

    float a(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    b a(View view) {
        for (int i2 = 0; i2 < this.r.size(); i2++) {
            b bVar = this.r.get(i2);
            if (this.b.isViewFromObject(view, bVar.a)) {
                return bVar;
            }
        }
        return null;
    }

    void a() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.x = new Scroller(context, q);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.Q = viewConfiguration.getScaledPagingTouchSlop();
        this.ab = (int) (400.0f * f2);
        this.ac = viewConfiguration.getScaledMaximumFlingVelocity();
        this.ai = new EdgeEffect(context);
        this.aj = new EdgeEffect(context);
        this.ad = (int) (25.0f * f2);
        this.ae = (int) (2.0f * f2);
        this.O = (int) (f2 * 16.0f);
        ab.a(this, new c());
        if (ab.g(this) == 0) {
            ab.e((View) this, 1);
        }
        ab.a(this, new u() { // from class: android.support.v4.view.ViewPager.4
            private final Rect b = new Rect();

            @Override // android.support.v4.view.u
            public ak a(View view, ak akVar) {
                ak a2 = ab.a(view, akVar);
                if (a2.g()) {
                    return a2;
                }
                Rect rect = this.b;
                rect.left = a2.a();
                rect.top = a2.b();
                rect.right = a2.c();
                rect.bottom = a2.d();
                int childCount = ViewPager.this.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    ak b2 = ab.b(ViewPager.this.getChildAt(i2), a2);
                    rect.left = Math.min(b2.a(), rect.left);
                    rect.top = Math.min(b2.b(), rect.top);
                    rect.right = Math.min(b2.c(), rect.right);
                    rect.bottom = Math.min(b2.d(), rect.bottom);
                }
                return a2.a(rect.left, rect.top, rect.right, rect.bottom);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0064, code lost:
        if (r8.b == r17.c) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006a, code lost:
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00c7, code lost:
        if (r15 >= 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00d5, code lost:
        if (r15 >= 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00e3, code lost:
        if (r15 >= 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00e5, code lost:
        r5 = r17.r.get(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00ee, code lost:
        r5 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(int r18) {
        /*
            Method dump skipped, instructions count: 592
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.a(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0066  */
    @android.support.annotation.i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.an
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L6d
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = r4
            r4 = r3
            r3 = 0
        L1d:
            if (r3 >= r6) goto L6d
            android.view.View r8 = r12.getChildAt(r3)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            android.support.v4.view.ViewPager$LayoutParams r9 = (android.support.v4.view.ViewPager.LayoutParams) r9
            boolean r10 = r9.a
            if (r10 != 0) goto L2e
            goto L6a
        L2e:
            int r9 = r9.b
            r9 = r9 & 7
            if (r9 == r2) goto L4f
            r10 = 3
            if (r9 == r10) goto L49
            r10 = 5
            if (r9 == r10) goto L3c
            r9 = r4
            goto L5e
        L3c:
            int r9 = r5 - r7
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r7 = r7 + r10
            goto L5b
        L49:
            int r9 = r8.getWidth()
            int r9 = r9 + r4
            goto L5e
        L4f:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r4)
        L5b:
            r11 = r9
            r9 = r4
            r4 = r11
        L5e:
            int r4 = r4 + r0
            int r10 = r8.getLeft()
            int r4 = r4 - r10
            if (r4 == 0) goto L69
            r8.offsetLeftAndRight(r4)
        L69:
            r4 = r9
        L6a:
            int r3 = r3 + 1
            goto L1d
        L6d:
            r12.b(r13, r14, r15)
            android.support.v4.view.ViewPager$f r13 = r12.as
            if (r13 == 0) goto La1
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7c:
            if (r1 >= r14) goto La1
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            android.support.v4.view.ViewPager$LayoutParams r0 = (android.support.v4.view.ViewPager.LayoutParams) r0
            boolean r0 = r0.a
            if (r0 == 0) goto L8d
            goto L9e
        L8d:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            android.support.v4.view.ViewPager$f r3 = r12.as
            r3.a(r15, r0)
        L9e:
            int r1 = r1 + 1
            goto L7c
        La1:
            r12.am = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.a(int, float, int):void");
    }

    void a(int i2, int i3) {
        a(i2, i3, 0);
    }

    void a(int i2, int i3, int i4) {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.x;
        if ((scroller == null || scroller.isFinished()) ? false : true) {
            scrollX = this.y ? this.x.getCurrX() : this.x.getStartX();
            this.x.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            scrollX = getScrollX();
        }
        int i5 = scrollX;
        int scrollY = getScrollY();
        int i6 = i2 - i5;
        int i7 = i3 - scrollY;
        if (i6 == 0 && i7 == 0) {
            a(false);
            d();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i8 = clientWidth / 2;
        float f2 = clientWidth;
        float f3 = i8;
        float a2 = f3 + (a(Math.min(1.0f, (Math.abs(i6) * 1.0f) / f2)) * f3);
        int abs = Math.abs(i4);
        int min = Math.min(abs > 0 ? Math.round(Math.abs(a2 / abs) * 1000.0f) * 4 : (int) (((Math.abs(i6) / ((f2 * this.b.getPageWidth(this.c)) + this.A)) + 1.0f) * 100.0f), (int) k);
        this.y = false;
        this.x.startScroll(i5, scrollY, i6, i7, min);
        ab.f(this);
    }

    public void a(int i2, boolean z) {
        this.K = false;
        a(i2, z, false);
    }

    void a(int i2, boolean z, boolean z2) {
        a(i2, z, z2, 0);
    }

    void a(int i2, boolean z, boolean z2, int i3) {
        v vVar = this.b;
        if (vVar == null || vVar.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z2 || this.c != i2 || this.r.size() == 0) {
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= this.b.getCount()) {
                i2 = this.b.getCount() - 1;
            }
            int i4 = this.L;
            int i5 = this.c;
            if (i2 > i5 + i4 || i2 < i5 - i4) {
                for (int i6 = 0; i6 < this.r.size(); i6++) {
                    this.r.get(i6).c = true;
                }
            }
            boolean z3 = this.c != i2;
            if (!this.ak) {
                a(i2);
                a(i2, z, i3, z3);
                return;
            }
            this.c = i2;
            if (z3) {
                e(i2);
            }
            requestLayout();
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    public void a(@android.support.annotation.af d dVar) {
        if (this.ar == null) {
            this.ar = new ArrayList();
        }
        this.ar.add(dVar);
    }

    public void a(@android.support.annotation.af e eVar) {
        if (this.ao == null) {
            this.ao = new ArrayList();
        }
        this.ao.add(eVar);
    }

    public void a(boolean z, @android.support.annotation.ag f fVar) {
        a(z, fVar, 2);
    }

    public void a(boolean z, @android.support.annotation.ag f fVar, int i2) {
        boolean z2 = fVar != null;
        boolean z3 = z2 != (this.as != null);
        this.as = fVar;
        setChildrenDrawingOrderEnabled(z2);
        if (z2) {
            this.ax = z ? 2 : 1;
            this.at = i2;
        } else {
            this.ax = 0;
        }
        if (z3) {
            d();
        }
    }

    public boolean a(@android.support.annotation.af KeyEvent keyEvent) {
        int i2;
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 21) {
                if (keyCode != 22) {
                    if (keyCode == 61) {
                        if (keyEvent.hasNoModifiers()) {
                            return c(2);
                        }
                        if (keyEvent.hasModifiers(1)) {
                            return c(1);
                        }
                    }
                } else if (keyEvent.hasModifiers(2)) {
                    return i();
                } else {
                    i2 = 66;
                }
            } else if (keyEvent.hasModifiers(2)) {
                return h();
            } else {
                i2 = 17;
            }
            return c(i2);
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
        return z && view.canScrollHorizontally(-i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        b a2;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.c) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if (((i3 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) || arrayList == null) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        b a2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.c) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        layoutParams2.a |= c(view);
        if (!this.I) {
            super.addView(view, i2, layoutParams);
        } else if (layoutParams2 != null && layoutParams2.a) {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        } else {
            layoutParams2.d = true;
            addViewInLayout(view, i2, layoutParams);
        }
    }

    b b(int i2) {
        for (int i3 = 0; i3 < this.r.size(); i3++) {
            b bVar = this.r.get(i3);
            if (bVar.b == i2) {
                return bVar;
            }
        }
        return null;
    }

    b b(int i2, int i3) {
        b bVar = new b();
        bVar.b = i2;
        bVar.a = this.b.instantiateItem((ViewGroup) this, i2);
        bVar.d = this.b.getPageWidth(i2);
        if (i3 < 0 || i3 >= this.r.size()) {
            this.r.add(bVar);
        } else {
            this.r.add(i3, bVar);
        }
        return bVar;
    }

    b b(View view) {
        while (true) {
            ViewParent parent = view.getParent();
            if (parent == this) {
                return a(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    public void b() {
        List<e> list = this.ao;
        if (list != null) {
            list.clear();
        }
    }

    public void b(float f2) {
        ArrayList<b> arrayList;
        if (!this.ag) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.b == null) {
            return;
        }
        this.R += f2;
        float scrollX = getScrollX() - f2;
        float clientWidth = getClientWidth();
        float f3 = this.E * clientWidth;
        float f4 = this.F * clientWidth;
        b bVar = this.r.get(0);
        b bVar2 = this.r.get(arrayList.size() - 1);
        if (bVar.b != 0) {
            f3 = bVar.e * clientWidth;
        }
        if (bVar2.b != this.b.getCount() - 1) {
            f4 = bVar2.e * clientWidth;
        }
        if (scrollX < f3) {
            scrollX = f3;
        } else if (scrollX > f4) {
            scrollX = f4;
        }
        int i2 = (int) scrollX;
        this.R += scrollX - i2;
        scrollTo(i2, getScrollY());
        d(i2);
        MotionEvent obtain = MotionEvent.obtain(this.ah, SystemClock.uptimeMillis(), 2, this.R, 0.0f, 0);
        this.aa.addMovement(obtain);
        obtain.recycle();
    }

    public void b(@android.support.annotation.af d dVar) {
        List<d> list = this.ar;
        if (list != null) {
            list.remove(dVar);
        }
    }

    public void b(@android.support.annotation.af e eVar) {
        List<e> list = this.ao;
        if (list != null) {
            list.remove(eVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e c(e eVar) {
        e eVar2 = this.aq;
        this.aq = eVar;
        return eVar2;
    }

    void c() {
        int count = this.b.getCount();
        this.o = count;
        boolean z = this.r.size() < (this.L * 2) + 1 && this.r.size() < count;
        int i2 = this.c;
        int i3 = 0;
        boolean z2 = false;
        while (i3 < this.r.size()) {
            b bVar = this.r.get(i3);
            int itemPosition = this.b.getItemPosition(bVar.a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.r.remove(i3);
                    i3--;
                    if (!z2) {
                        this.b.startUpdate((ViewGroup) this);
                        z2 = true;
                    }
                    this.b.destroyItem((ViewGroup) this, bVar.b, bVar.a);
                    if (this.c == bVar.b) {
                        i2 = Math.max(0, Math.min(this.c, count - 1));
                    }
                } else if (bVar.b != itemPosition) {
                    if (bVar.b == this.c) {
                        i2 = itemPosition;
                    }
                    bVar.b = itemPosition;
                }
                z = true;
            }
            i3++;
        }
        if (z2) {
            this.b.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.r, p);
        if (z) {
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i4).getLayoutParams();
                if (!layoutParams.a) {
                    layoutParams.c = 0.0f;
                }
            }
            a(i2, false, true);
            requestLayout();
        }
    }

    public boolean c(int i2) {
        boolean i3;
        boolean z;
        View findFocus = findFocus();
        boolean z2 = false;
        View view = null;
        if (findFocus != this) {
            if (findFocus != null) {
                ViewParent parent = findFocus.getParent();
                while (true) {
                    if (!(parent instanceof ViewGroup)) {
                        z = false;
                        break;
                    } else if (parent == this) {
                        z = true;
                        break;
                    } else {
                        parent = parent.getParent();
                    }
                }
                if (!z) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(findFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        sb.append(" => ");
                        sb.append(parent2.getClass().getSimpleName());
                    }
                    Log.e(g, "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i2);
        if (findNextFocus == null || findNextFocus == view) {
            if (i2 == 17 || i2 == 1) {
                z2 = h();
            } else if (i2 == 66 || i2 == 2) {
                z2 = i();
            }
        } else if (i2 == 17) {
            int i4 = a(this.t, findNextFocus).left;
            int i5 = a(this.t, view).left;
            if (view != null && i4 >= i5) {
                i3 = h();
                z2 = i3;
            }
            i3 = findNextFocus.requestFocus();
            z2 = i3;
        } else if (i2 == 66) {
            int i6 = a(this.t, findNextFocus).left;
            int i7 = a(this.t, view).left;
            if (view != null && i6 <= i7) {
                i3 = i();
                z2 = i3;
            }
            i3 = findNextFocus.requestFocus();
            z2 = i3;
        }
        if (z2) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i2));
        }
        return z2;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        if (this.b == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i2 < 0 ? scrollX > ((int) (((float) clientWidth) * this.E)) : i2 > 0 && scrollX < ((int) (((float) clientWidth) * this.F));
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.y = true;
        if (this.x.isFinished() || !this.x.computeScrollOffset()) {
            a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.x.getCurrX();
        int currY = this.x.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!d(currX)) {
                this.x.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ab.f(this);
    }

    void d() {
        a(this.c);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        b a2;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.c && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        v vVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean z = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (vVar = this.b) != null && vVar.getCount() > 1)) {
            if (!this.ai.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.E * width);
                this.ai.setSize(height, width);
                z = false | this.ai.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.aj.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.F + 1.0f)) * width2);
                this.aj.setSize(height2, width2);
                z |= this.aj.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.ai.finish();
            this.aj.finish();
        }
        if (z) {
            ab.f(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.B;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public boolean e() {
        if (this.M) {
            return false;
        }
        this.ag = true;
        setScrollState(1);
        this.R = 0.0f;
        this.T = 0.0f;
        VelocityTracker velocityTracker = this.aa;
        if (velocityTracker == null) {
            this.aa = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.aa.addMovement(obtain);
        obtain.recycle();
        this.ah = uptimeMillis;
        return true;
    }

    public void f() {
        if (!this.ag) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.b != null) {
            VelocityTracker velocityTracker = this.aa;
            velocityTracker.computeCurrentVelocity(1000, this.ac);
            int xVelocity = (int) velocityTracker.getXVelocity(this.V);
            this.K = true;
            int clientWidth = getClientWidth();
            int scrollX = getScrollX();
            b m2 = m();
            a(a(m2.b, ((scrollX / clientWidth) - m2.e) / m2.d, xVelocity, (int) (this.R - this.T)), true, true, xVelocity);
        }
        n();
        this.ag = false;
    }

    public boolean g() {
        return this.ag;
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
        return generateDefaultLayoutParams();
    }

    @android.support.annotation.ag
    public v getAdapter() {
        return this.b;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        if (this.ax == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((LayoutParams) this.ay.get(i3).getLayoutParams()).f;
    }

    public int getCurrentItem() {
        return this.c;
    }

    public int getOffscreenPageLimit() {
        return this.L;
    }

    public int getPageMargin() {
        return this.A;
    }

    boolean h() {
        int i2 = this.c;
        if (i2 > 0) {
            a(i2 - 1, true);
            return true;
        }
        return false;
    }

    boolean i() {
        v vVar = this.b;
        if (vVar == null || this.c >= vVar.getCount() - 1) {
            return false;
        }
        a(this.c + 1, true);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.ak = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.aA);
        Scroller scroller = this.x;
        if (scroller != null && !scroller.isFinished()) {
            this.x.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int width;
        float f2;
        float f3;
        float f4;
        super.onDraw(canvas);
        if (this.A <= 0 || this.B == null || this.r.size() <= 0 || this.b == null) {
            return;
        }
        int scrollX = getScrollX();
        float width2 = getWidth();
        float f5 = this.A / width2;
        int i2 = 0;
        b bVar = this.r.get(0);
        float f6 = bVar.e;
        int size = this.r.size();
        int i3 = bVar.b;
        int i4 = this.r.get(size - 1).b;
        while (i3 < i4) {
            while (i3 > bVar.b && i2 < size) {
                i2++;
                bVar = this.r.get(i2);
            }
            if (i3 == bVar.b) {
                f3 = (bVar.e + bVar.d) * width2;
                f2 = bVar.e + bVar.d + f5;
            } else {
                float pageWidth = this.b.getPageWidth(i3);
                f2 = f6 + pageWidth + f5;
                f3 = (f6 + pageWidth) * width2;
            }
            if (this.A + f3 > scrollX) {
                f4 = f5;
                this.B.setBounds(Math.round(f3), this.C, Math.round(this.A + f3), this.D);
                this.B.draw(canvas);
            } else {
                f4 = f5;
            }
            if (f3 > scrollX + width) {
                return;
            }
            i3++;
            f6 = f2;
            f5 = f4;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            l();
            return false;
        }
        if (action != 0) {
            if (this.M) {
                return true;
            }
            if (this.N) {
                return false;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            this.T = x;
            this.R = x;
            float y = motionEvent.getY();
            this.U = y;
            this.S = y;
            this.V = motionEvent.getPointerId(0);
            this.N = false;
            this.y = true;
            this.x.computeScrollOffset();
            if (this.aB != 2 || Math.abs(this.x.getFinalX() - this.x.getCurrX()) <= this.ae) {
                a(false);
                this.M = false;
            } else {
                this.x.abortAnimation();
                this.K = false;
                d();
                this.M = true;
                c(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i2 = this.V;
            if (i2 != -1) {
                int findPointerIndex = motionEvent.findPointerIndex(i2);
                float x2 = motionEvent.getX(findPointerIndex);
                float f2 = x2 - this.R;
                float abs = Math.abs(f2);
                float y2 = motionEvent.getY(findPointerIndex);
                float abs2 = Math.abs(y2 - this.U);
                if (f2 != 0.0f && !a(this.R, f2) && a(this, false, (int) f2, (int) x2, (int) y2)) {
                    this.R = x2;
                    this.S = y2;
                    this.N = true;
                    return false;
                }
                if (abs > this.Q && abs * 0.5f > abs2) {
                    this.M = true;
                    c(true);
                    setScrollState(1);
                    this.R = f2 > 0.0f ? this.T + this.Q : this.T - this.Q;
                    this.S = y2;
                    setScrollingCacheEnabled(true);
                } else if (abs2 > this.Q) {
                    this.N = true;
                }
                if (this.M && c(x2)) {
                    ab.f(this);
                }
            }
        } else if (action == 6) {
            a(motionEvent);
        }
        if (this.aa == null) {
            this.aa = VelocityTracker.obtain();
        }
        this.aa.addMovement(motionEvent);
        return this.M;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        boolean z2;
        b a2;
        int max;
        int max2;
        int childCount = getChildCount();
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i8 = paddingBottom;
        int i9 = 0;
        int i10 = paddingTop;
        int i11 = paddingLeft;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.a) {
                    int i13 = layoutParams.b & 7;
                    int i14 = layoutParams.b & 112;
                    if (i13 == 1) {
                        max = Math.max((i6 - childAt.getMeasuredWidth()) / 2, i11);
                    } else if (i13 == 3) {
                        max = i11;
                        i11 = childAt.getMeasuredWidth() + i11;
                    } else if (i13 != 5) {
                        max = i11;
                    } else {
                        max = (i6 - paddingRight) - childAt.getMeasuredWidth();
                        paddingRight += childAt.getMeasuredWidth();
                    }
                    if (i14 == 16) {
                        max2 = Math.max((i7 - childAt.getMeasuredHeight()) / 2, i10);
                    } else if (i14 == 48) {
                        max2 = i10;
                        i10 = childAt.getMeasuredHeight() + i10;
                    } else if (i14 != 80) {
                        max2 = i10;
                    } else {
                        max2 = (i7 - i8) - childAt.getMeasuredHeight();
                        i8 += childAt.getMeasuredHeight();
                    }
                    int i15 = max + scrollX;
                    childAt.layout(i15, max2, childAt.getMeasuredWidth() + i15, max2 + childAt.getMeasuredHeight());
                    i9++;
                }
            }
        }
        int i16 = (i6 - i11) - paddingRight;
        for (int i17 = 0; i17 < childCount; i17++) {
            View childAt2 = getChildAt(i17);
            if (childAt2.getVisibility() != 8) {
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                if (!layoutParams2.a && (a2 = a(childAt2)) != null) {
                    float f2 = i16;
                    int i18 = ((int) (a2.e * f2)) + i11;
                    if (layoutParams2.d) {
                        layoutParams2.d = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (f2 * layoutParams2.c), 1073741824), View.MeasureSpec.makeMeasureSpec((i7 - i10) - i8, 1073741824));
                    }
                    childAt2.layout(i18, i10, childAt2.getMeasuredWidth() + i18, childAt2.getMeasuredHeight() + i10);
                }
            }
        }
        this.C = i10;
        this.D = i7 - i8;
        this.an = i9;
        if (this.ak) {
            z2 = false;
            a(this.c, false, 0, false);
        } else {
            z2 = false;
        }
        this.ak = z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bb  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r17, int r18) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        b a2;
        int childCount = getChildCount();
        int i5 = -1;
        if ((i2 & 2) != 0) {
            i5 = childCount;
            i3 = 0;
            i4 = 1;
        } else {
            i3 = childCount - 1;
            i4 = -1;
        }
        while (i3 != i5) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.c && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i3 += i4;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        v vVar = this.b;
        if (vVar != null) {
            vVar.restoreState(savedState.c, savedState.d);
            a(savedState.a, false, true);
            return;
        }
        this.u = savedState.a;
        this.v = savedState.c;
        this.w = savedState.d;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.c;
        v vVar = this.b;
        if (vVar != null) {
            savedState.c = vVar.saveState();
        }
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.A;
            a(i2, i4, i6, i6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0151  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instructions count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.I) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(@android.support.annotation.ag v vVar) {
        v vVar2 = this.b;
        if (vVar2 != null) {
            vVar2.setViewPagerObserver(null);
            this.b.startUpdate((ViewGroup) this);
            for (int i2 = 0; i2 < this.r.size(); i2++) {
                b bVar = this.r.get(i2);
                this.b.destroyItem((ViewGroup) this, bVar.b, bVar.a);
            }
            this.b.finishUpdate((ViewGroup) this);
            this.r.clear();
            j();
            this.c = 0;
            scrollTo(0, 0);
        }
        v vVar3 = this.b;
        this.b = vVar;
        this.o = 0;
        if (this.b != null) {
            if (this.z == null) {
                this.z = new g();
            }
            this.b.setViewPagerObserver(this.z);
            this.K = false;
            boolean z = this.ak;
            this.ak = true;
            this.o = this.b.getCount();
            if (this.u >= 0) {
                this.b.restoreState(this.v, this.w);
                a(this.u, false, true);
                this.u = -1;
                this.v = null;
                this.w = null;
            } else if (z) {
                requestLayout();
            } else {
                d();
            }
        }
        List<d> list = this.ar;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.ar.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.ar.get(i3).a(this, vVar3, vVar);
        }
    }

    public void setCurrentItem(int i2) {
        this.K = false;
        a(i2, !this.ak, false);
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w(g, "Requested offscreen page limit " + i2 + " too small; defaulting to 1");
            i2 = 1;
        }
        if (i2 != this.L) {
            this.L = i2;
            d();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(e eVar) {
        this.ap = eVar;
    }

    public void setPageMargin(int i2) {
        int i3 = this.A;
        this.A = i2;
        int width = getWidth();
        a(width, width, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(@android.support.annotation.p int i2) {
        setPageMarginDrawable(android.support.v4.content.c.getDrawable(getContext(), i2));
    }

    public void setPageMarginDrawable(@android.support.annotation.ag Drawable drawable) {
        this.B = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    void setScrollState(int i2) {
        if (this.aB == i2) {
            return;
        }
        this.aB = i2;
        if (this.as != null) {
            b(i2 != 0);
        }
        f(i2);
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.B;
    }
}
