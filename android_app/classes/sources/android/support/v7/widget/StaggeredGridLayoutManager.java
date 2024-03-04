package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.an;
import android.support.v4.view.a.c;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.i implements RecyclerView.t.b {
    static final boolean a = false;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 0;
    @Deprecated
    public static final int e = 1;
    public static final int f = 2;
    static final int g = Integer.MIN_VALUE;
    private static final String p = "StaggeredGridLManager";
    private static final float q = 0.33333334f;
    @android.support.annotation.af
    private final y E;
    private BitSet F;
    private boolean H;
    private boolean I;
    private SavedState J;
    private int K;
    private int[] P;
    b[] h;
    @android.support.annotation.af
    ae i;
    @android.support.annotation.af
    ae j;
    private int s;
    private int t;
    private int r = -1;
    boolean k = false;
    boolean l = false;
    int m = -1;
    int n = Integer.MIN_VALUE;
    LazySpanLookup o = new LazySpanLookup();
    private int G = 2;
    private final Rect L = new Rect();
    private final a M = new a();
    private boolean N = false;
    private boolean O = true;
    private final Runnable Q = new Runnable() { // from class: android.support.v7.widget.StaggeredGridLayoutManager.1
        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.b();
        }
    };

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int a = -1;
        b b;
        boolean c;

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public void a(boolean z) {
            this.c = z;
        }

        public boolean a() {
            return this.c;
        }

        public final int b() {
            b bVar = this.b;
            if (bVar == null) {
                return -1;
            }
            return bVar.f;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LazySpanLookup {
        private static final int c = 10;
        int[] a;
        List<FullSpanItem> b;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new Parcelable.Creator<FullSpanItem>() { // from class: android.support.v7.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem.1
                @Override // android.os.Parcelable.Creator
                /* renamed from: a */
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: a */
                public FullSpanItem[] newArray(int i) {
                    return new FullSpanItem[i];
                }
            };
            int a;
            int b;
            int[] c;
            boolean d;

            FullSpanItem() {
            }

            FullSpanItem(Parcel parcel) {
                this.a = parcel.readInt();
                this.b = parcel.readInt();
                this.d = parcel.readInt() == 1;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    this.c = new int[readInt];
                    parcel.readIntArray(this.c);
                }
            }

            int a(int i) {
                int[] iArr = this.c;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.a + ", mGapDir=" + this.b + ", mHasUnwantedGapAfter=" + this.d + ", mGapPerSpan=" + Arrays.toString(this.c) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b);
                parcel.writeInt(this.d ? 1 : 0);
                int[] iArr = this.c;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                    return;
                }
                parcel.writeInt(iArr.length);
                parcel.writeIntArray(this.c);
            }
        }

        LazySpanLookup() {
        }

        private void c(int i, int i2) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return;
            }
            int i3 = i + i2;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                if (fullSpanItem.a >= i) {
                    if (fullSpanItem.a < i3) {
                        this.b.remove(size);
                    } else {
                        fullSpanItem.a -= i2;
                    }
                }
            }
        }

        private void d(int i, int i2) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                if (fullSpanItem.a >= i) {
                    fullSpanItem.a += i2;
                }
            }
        }

        private int g(int i) {
            if (this.b == null) {
                return -1;
            }
            FullSpanItem f = f(i);
            if (f != null) {
                this.b.remove(f);
            }
            int size = this.b.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                } else if (this.b.get(i2).a >= i) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 != -1) {
                this.b.remove(i2);
                return this.b.get(i2).a;
            }
            return -1;
        }

        int a(int i) {
            List<FullSpanItem> list = this.b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.b.get(size).a >= i) {
                        this.b.remove(size);
                    }
                }
            }
            return b(i);
        }

        public FullSpanItem a(int i, int i2, int i3, boolean z) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                FullSpanItem fullSpanItem = this.b.get(i4);
                if (fullSpanItem.a >= i2) {
                    return null;
                }
                if (fullSpanItem.a >= i && (i3 == 0 || fullSpanItem.b == i3 || (z && fullSpanItem.d))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        void a() {
            int[] iArr = this.a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.b = null;
        }

        void a(int i, int i2) {
            int[] iArr = this.a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            e(i3);
            int[] iArr2 = this.a;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.a;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            c(i, i2);
        }

        void a(int i, b bVar) {
            e(i);
            this.a[i] = bVar.f;
        }

        public void a(FullSpanItem fullSpanItem) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem fullSpanItem2 = this.b.get(i);
                if (fullSpanItem2.a == fullSpanItem.a) {
                    this.b.remove(i);
                }
                if (fullSpanItem2.a >= fullSpanItem.a) {
                    this.b.add(i, fullSpanItem);
                    return;
                }
            }
            this.b.add(fullSpanItem);
        }

        int b(int i) {
            int[] iArr = this.a;
            if (iArr != null && i < iArr.length) {
                int g = g(i);
                if (g == -1) {
                    int[] iArr2 = this.a;
                    Arrays.fill(iArr2, i, iArr2.length, -1);
                    return this.a.length;
                }
                int i2 = g + 1;
                Arrays.fill(this.a, i, i2, -1);
                return i2;
            }
            return -1;
        }

        void b(int i, int i2) {
            int[] iArr = this.a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            e(i3);
            int[] iArr2 = this.a;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.a, i, i3, -1);
            d(i, i2);
        }

        int c(int i) {
            int[] iArr = this.a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            return iArr[i];
        }

        int d(int i) {
            int length = this.a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        void e(int i) {
            int[] iArr = this.a;
            if (iArr == null) {
                this.a = new int[Math.max(i, 10) + 1];
                Arrays.fill(this.a, -1);
            } else if (i >= iArr.length) {
                this.a = new int[d(i)];
                System.arraycopy(iArr, 0, this.a, 0, iArr.length);
                int[] iArr2 = this.a;
                Arrays.fill(iArr2, iArr.length, iArr2.length, -1);
            }
        }

        public FullSpanItem f(int i) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                if (fullSpanItem.a == i) {
                    return fullSpanItem;
                }
            }
            return null;
        }
    }

    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.support.v7.widget.StaggeredGridLayoutManager.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a;
        int b;
        int c;
        int[] d;
        int e;
        int[] f;
        List<LazySpanLookup.FullSpanItem> g;
        boolean h;
        boolean i;
        boolean j;

        public SavedState() {
        }

        SavedState(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            int i = this.c;
            if (i > 0) {
                this.d = new int[i];
                parcel.readIntArray(this.d);
            }
            this.e = parcel.readInt();
            int i2 = this.e;
            if (i2 > 0) {
                this.f = new int[i2];
                parcel.readIntArray(this.f);
            }
            this.h = parcel.readInt() == 1;
            this.i = parcel.readInt() == 1;
            this.j = parcel.readInt() == 1;
            this.g = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.c = savedState.c;
            this.a = savedState.a;
            this.b = savedState.b;
            this.d = savedState.d;
            this.e = savedState.e;
            this.f = savedState.f;
            this.h = savedState.h;
            this.i = savedState.i;
            this.j = savedState.j;
            this.g = savedState.g;
        }

        void a() {
            this.d = null;
            this.c = 0;
            this.e = 0;
            this.f = null;
            this.g = null;
        }

        void b() {
            this.d = null;
            this.c = 0;
            this.a = -1;
            this.b = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            if (this.c > 0) {
                parcel.writeIntArray(this.d);
            }
            parcel.writeInt(this.e);
            if (this.e > 0) {
                parcel.writeIntArray(this.f);
            }
            parcel.writeInt(this.h ? 1 : 0);
            parcel.writeInt(this.i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeList(this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class a {
        int a;
        int b;
        boolean c;
        boolean d;
        boolean e;
        int[] f;

        a() {
            a();
        }

        void a() {
            this.a = -1;
            this.b = Integer.MIN_VALUE;
            this.c = false;
            this.d = false;
            this.e = false;
            int[] iArr = this.f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        void a(int i) {
            this.b = this.c ? StaggeredGridLayoutManager.this.i.e() - i : StaggeredGridLayoutManager.this.i.d() + i;
        }

        void a(b[] bVarArr) {
            int length = bVarArr.length;
            int[] iArr = this.f;
            if (iArr == null || iArr.length < length) {
                this.f = new int[StaggeredGridLayoutManager.this.h.length];
            }
            for (int i = 0; i < length; i++) {
                this.f[i] = bVarArr[i].a(Integer.MIN_VALUE);
            }
        }

        void b() {
            this.b = this.c ? StaggeredGridLayoutManager.this.i.e() : StaggeredGridLayoutManager.this.i.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b {
        static final int a = Integer.MIN_VALUE;
        ArrayList<View> b = new ArrayList<>();
        int c = Integer.MIN_VALUE;
        int d = Integer.MIN_VALUE;
        int e = 0;
        final int f;

        b(int i) {
            this.f = i;
        }

        int a(int i) {
            int i2 = this.c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.b.size() == 0) {
                return i;
            }
            a();
            return this.c;
        }

        int a(int i, int i2, boolean z) {
            return a(i, i2, z, true, false);
        }

        int a(int i, int i2, boolean z, boolean z2, boolean z3) {
            int d = StaggeredGridLayoutManager.this.i.d();
            int e = StaggeredGridLayoutManager.this.i.e();
            int i3 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = this.b.get(i);
                int a2 = StaggeredGridLayoutManager.this.i.a(view);
                int b = StaggeredGridLayoutManager.this.i.b(view);
                boolean z4 = false;
                boolean z5 = !z3 ? a2 >= e : a2 > e;
                if (!z3 ? b > d : b >= d) {
                    z4 = true;
                }
                if (z5 && z4) {
                    if (!z || !z2) {
                        if (!z2 && a2 >= d && b <= e) {
                        }
                        return StaggeredGridLayoutManager.this.e(view);
                    } else if (a2 >= d && b <= e) {
                        return StaggeredGridLayoutManager.this.e(view);
                    }
                }
                i += i3;
            }
            return -1;
        }

        public View a(int i, int i2) {
            View view = null;
            if (i2 != -1) {
                int size = this.b.size() - 1;
                while (size >= 0) {
                    View view2 = this.b.get(size);
                    if ((StaggeredGridLayoutManager.this.k && StaggeredGridLayoutManager.this.e(view2) >= i) || ((!StaggeredGridLayoutManager.this.k && StaggeredGridLayoutManager.this.e(view2) <= i) || !view2.hasFocusable())) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.b.size();
                int i3 = 0;
                while (i3 < size2) {
                    View view3 = this.b.get(i3);
                    if ((StaggeredGridLayoutManager.this.k && StaggeredGridLayoutManager.this.e(view3) <= i) || ((!StaggeredGridLayoutManager.this.k && StaggeredGridLayoutManager.this.e(view3) >= i) || !view3.hasFocusable())) {
                        break;
                    }
                    i3++;
                    view = view3;
                }
            }
            return view;
        }

        void a() {
            LazySpanLookup.FullSpanItem f;
            View view = this.b.get(0);
            LayoutParams c = c(view);
            this.c = StaggeredGridLayoutManager.this.i.a(view);
            if (c.c && (f = StaggeredGridLayoutManager.this.o.f(c.h())) != null && f.b == -1) {
                this.c -= f.a(this.f);
            }
        }

        void a(View view) {
            LayoutParams c = c(view);
            c.b = this;
            this.b.add(0, view);
            this.c = Integer.MIN_VALUE;
            if (this.b.size() == 1) {
                this.d = Integer.MIN_VALUE;
            }
            if (c.e() || c.f()) {
                this.e += StaggeredGridLayoutManager.this.i.e(view);
            }
        }

        void a(boolean z, int i) {
            int b = z ? b(Integer.MIN_VALUE) : a(Integer.MIN_VALUE);
            e();
            if (b == Integer.MIN_VALUE) {
                return;
            }
            if (!z || b >= StaggeredGridLayoutManager.this.i.e()) {
                if (z || b <= StaggeredGridLayoutManager.this.i.d()) {
                    if (i != Integer.MIN_VALUE) {
                        b += i;
                    }
                    this.d = b;
                    this.c = b;
                }
            }
        }

        int b() {
            int i = this.c;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            a();
            return this.c;
        }

        int b(int i) {
            int i2 = this.d;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.b.size() == 0) {
                return i;
            }
            c();
            return this.d;
        }

        int b(int i, int i2, boolean z) {
            return a(i, i2, false, false, z);
        }

        void b(View view) {
            LayoutParams c = c(view);
            c.b = this;
            this.b.add(view);
            this.d = Integer.MIN_VALUE;
            if (this.b.size() == 1) {
                this.c = Integer.MIN_VALUE;
            }
            if (c.e() || c.f()) {
                this.e += StaggeredGridLayoutManager.this.i.e(view);
            }
        }

        LayoutParams c(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        void c() {
            LazySpanLookup.FullSpanItem f;
            ArrayList<View> arrayList = this.b;
            View view = arrayList.get(arrayList.size() - 1);
            LayoutParams c = c(view);
            this.d = StaggeredGridLayoutManager.this.i.b(view);
            if (c.c && (f = StaggeredGridLayoutManager.this.o.f(c.h())) != null && f.b == 1) {
                this.d += f.a(this.f);
            }
        }

        void c(int i) {
            this.c = i;
            this.d = i;
        }

        int d() {
            int i = this.d;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            c();
            return this.d;
        }

        void d(int i) {
            int i2 = this.c;
            if (i2 != Integer.MIN_VALUE) {
                this.c = i2 + i;
            }
            int i3 = this.d;
            if (i3 != Integer.MIN_VALUE) {
                this.d = i3 + i;
            }
        }

        void e() {
            this.b.clear();
            f();
            this.e = 0;
        }

        void f() {
            this.c = Integer.MIN_VALUE;
            this.d = Integer.MIN_VALUE;
        }

        void g() {
            int size = this.b.size();
            View remove = this.b.remove(size - 1);
            LayoutParams c = c(remove);
            c.b = null;
            if (c.e() || c.f()) {
                this.e -= StaggeredGridLayoutManager.this.i.e(remove);
            }
            if (size == 1) {
                this.c = Integer.MIN_VALUE;
            }
            this.d = Integer.MIN_VALUE;
        }

        void h() {
            View remove = this.b.remove(0);
            LayoutParams c = c(remove);
            c.b = null;
            if (this.b.size() == 0) {
                this.d = Integer.MIN_VALUE;
            }
            if (c.e() || c.f()) {
                this.e -= StaggeredGridLayoutManager.this.i.e(remove);
            }
            this.c = Integer.MIN_VALUE;
        }

        public int i() {
            return this.e;
        }

        public int j() {
            return StaggeredGridLayoutManager.this.k ? a(this.b.size() - 1, -1, false) : a(0, this.b.size(), false);
        }

        public int k() {
            int i;
            int size;
            if (StaggeredGridLayoutManager.this.k) {
                i = this.b.size() - 1;
                size = -1;
            } else {
                i = 0;
                size = this.b.size();
            }
            return b(i, size, true);
        }

        public int l() {
            int i;
            int size;
            if (StaggeredGridLayoutManager.this.k) {
                i = this.b.size() - 1;
                size = -1;
            } else {
                i = 0;
                size = this.b.size();
            }
            return a(i, size, true);
        }

        public int m() {
            return StaggeredGridLayoutManager.this.k ? a(0, this.b.size(), false) : a(this.b.size() - 1, -1, false);
        }

        public int n() {
            int size;
            int i;
            if (StaggeredGridLayoutManager.this.k) {
                size = 0;
                i = this.b.size();
            } else {
                size = this.b.size() - 1;
                i = -1;
            }
            return b(size, i, true);
        }

        public int o() {
            int size;
            int i;
            if (StaggeredGridLayoutManager.this.k) {
                size = 0;
                i = this.b.size();
            } else {
                size = this.b.size() - 1;
                i = -1;
            }
            return a(size, i, true);
        }
    }

    public StaggeredGridLayoutManager(int i, int i2) {
        this.s = i2;
        a(i);
        this.E = new y();
        u();
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.i.b a2 = a(context, attributeSet, i, i2);
        b(a2.a);
        a(a2.b);
        a(a2.c);
        this.E = new y();
        u();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v6 */
    private int a(RecyclerView.p pVar, y yVar, RecyclerView.u uVar) {
        int i;
        b bVar;
        int e2;
        int i2;
        int i3;
        int e3;
        RecyclerView.i iVar;
        View view;
        int i4;
        int i5;
        ?? r9 = 0;
        this.F.set(0, this.r, true);
        if (this.E.o) {
            i = yVar.k == 1 ? ActivityChooserView.a.a : Integer.MIN_VALUE;
        } else {
            i = yVar.k == 1 ? yVar.m + yVar.h : yVar.l - yVar.h;
        }
        b(yVar.k, i);
        int e4 = this.l ? this.i.e() : this.i.d();
        boolean z = false;
        while (yVar.a(uVar) && (this.E.o || !this.F.isEmpty())) {
            View a2 = yVar.a(pVar);
            LayoutParams layoutParams = (LayoutParams) a2.getLayoutParams();
            int h = layoutParams.h();
            int c2 = this.o.c(h);
            boolean z2 = c2 == -1;
            if (z2) {
                bVar = layoutParams.c ? this.h[r9] : a(yVar);
                this.o.a(h, bVar);
            } else {
                bVar = this.h[c2];
            }
            b bVar2 = bVar;
            layoutParams.b = bVar2;
            if (yVar.k == 1) {
                c(a2);
            } else {
                b(a2, (int) r9);
            }
            a(a2, layoutParams, (boolean) r9);
            if (yVar.k == 1) {
                int s = layoutParams.c ? s(e4) : bVar2.b(e4);
                int e5 = this.i.e(a2) + s;
                if (z2 && layoutParams.c) {
                    LazySpanLookup.FullSpanItem o = o(s);
                    o.b = -1;
                    o.a = h;
                    this.o.a(o);
                }
                i2 = e5;
                e2 = s;
            } else {
                int r = layoutParams.c ? r(e4) : bVar2.a(e4);
                e2 = r - this.i.e(a2);
                if (z2 && layoutParams.c) {
                    LazySpanLookup.FullSpanItem p2 = p(r);
                    p2.b = 1;
                    p2.a = h;
                    this.o.a(p2);
                }
                i2 = r;
            }
            if (layoutParams.c && yVar.j == -1) {
                if (!z2) {
                    if (!(yVar.k == 1 ? o() : p())) {
                        LazySpanLookup.FullSpanItem f2 = this.o.f(h);
                        if (f2 != null) {
                            f2.d = true;
                        }
                    }
                }
                this.N = true;
            }
            a(a2, layoutParams, yVar);
            if (l() && this.s == 1) {
                int e6 = layoutParams.c ? this.j.e() : this.j.e() - (((this.r - 1) - bVar2.f) * this.t);
                e3 = e6;
                i3 = e6 - this.j.e(a2);
            } else {
                int d2 = layoutParams.c ? this.j.d() : (bVar2.f * this.t) + this.j.d();
                i3 = d2;
                e3 = this.j.e(a2) + d2;
            }
            if (this.s == 1) {
                iVar = this;
                view = a2;
                i4 = i3;
                i3 = e2;
                i5 = e3;
            } else {
                iVar = this;
                view = a2;
                i4 = e2;
                i5 = i2;
                i2 = e3;
            }
            iVar.b(view, i4, i3, i5, i2);
            if (layoutParams.c) {
                b(this.E.k, i);
            } else {
                a(bVar2, this.E.k, i);
            }
            a(pVar, this.E);
            if (this.E.n && a2.hasFocusable()) {
                if (layoutParams.c) {
                    this.F.clear();
                } else {
                    this.F.set(bVar2.f, false);
                    z = true;
                    r9 = 0;
                }
            }
            z = true;
            r9 = 0;
        }
        if (!z) {
            a(pVar, this.E);
        }
        int d3 = this.E.k == -1 ? this.i.d() - r(this.i.d()) : s(this.i.e()) - this.i.e();
        if (d3 > 0) {
            return Math.min(yVar.h, d3);
        }
        return 0;
    }

    private b a(y yVar) {
        int i;
        int i2;
        int i3 = -1;
        if (u(yVar.k)) {
            i = this.r - 1;
            i2 = -1;
        } else {
            i = 0;
            i3 = this.r;
            i2 = 1;
        }
        b bVar = null;
        if (yVar.k == 1) {
            int i4 = ActivityChooserView.a.a;
            int d2 = this.i.d();
            while (i != i3) {
                b bVar2 = this.h[i];
                int b2 = bVar2.b(d2);
                if (b2 < i4) {
                    bVar = bVar2;
                    i4 = b2;
                }
                i += i2;
            }
            return bVar;
        }
        int i5 = Integer.MIN_VALUE;
        int e2 = this.i.e();
        while (i != i3) {
            b bVar3 = this.h[i];
            int a2 = bVar3.a(e2);
            if (a2 > i5) {
                bVar = bVar3;
                i5 = a2;
            }
            i += i2;
        }
        return bVar;
    }

    private void a(RecyclerView.p pVar, int i) {
        while (G() > 0) {
            View j = j(0);
            if (this.i.b(j) > i || this.i.c(j) > i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) j.getLayoutParams();
            if (layoutParams.c) {
                for (int i2 = 0; i2 < this.r; i2++) {
                    if (this.h[i2].b.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.r; i3++) {
                    this.h[i3].h();
                }
            } else if (layoutParams.b.b.size() == 1) {
                return;
            } else {
                layoutParams.b.h();
            }
            b(j, pVar);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x0151, code lost:
        if (b() != false) goto L83;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(android.support.v7.widget.RecyclerView.p r9, android.support.v7.widget.RecyclerView.u r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 373
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.a(android.support.v7.widget.RecyclerView$p, android.support.v7.widget.RecyclerView$u, boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0010, code lost:
        if (r4.k == (-1)) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(android.support.v7.widget.RecyclerView.p r3, android.support.v7.widget.y r4) {
        /*
            r2 = this;
            boolean r0 = r4.g
            if (r0 == 0) goto L4f
            boolean r0 = r4.o
            if (r0 == 0) goto L9
            goto L4f
        L9:
            int r0 = r4.h
            r1 = -1
            if (r0 != 0) goto L1e
            int r0 = r4.k
            if (r0 != r1) goto L18
        L12:
            int r4 = r4.m
        L14:
            r2.b(r3, r4)
            goto L4f
        L18:
            int r4 = r4.l
        L1a:
            r2.a(r3, r4)
            goto L4f
        L1e:
            int r0 = r4.k
            if (r0 != r1) goto L39
            int r0 = r4.l
            int r1 = r4.l
            int r1 = r2.q(r1)
            int r0 = r0 - r1
            if (r0 >= 0) goto L2e
            goto L12
        L2e:
            int r1 = r4.m
            int r4 = r4.h
            int r4 = java.lang.Math.min(r0, r4)
            int r4 = r1 - r4
            goto L14
        L39:
            int r0 = r4.m
            int r0 = r2.t(r0)
            int r1 = r4.m
            int r0 = r0 - r1
            if (r0 >= 0) goto L45
            goto L18
        L45:
            int r1 = r4.l
            int r4 = r4.h
            int r4 = java.lang.Math.min(r0, r4)
            int r4 = r4 + r1
            goto L1a
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.a(android.support.v7.widget.RecyclerView$p, android.support.v7.widget.y):void");
    }

    private void a(a aVar) {
        boolean z;
        if (this.J.c > 0) {
            if (this.J.c == this.r) {
                for (int i = 0; i < this.r; i++) {
                    this.h[i].e();
                    int i2 = this.J.d[i];
                    if (i2 != Integer.MIN_VALUE) {
                        i2 += this.J.i ? this.i.e() : this.i.d();
                    }
                    this.h[i].c(i2);
                }
            } else {
                this.J.a();
                SavedState savedState = this.J;
                savedState.a = savedState.b;
            }
        }
        this.I = this.J.j;
        a(this.J.h);
        v();
        if (this.J.a != -1) {
            this.m = this.J.a;
            z = this.J.i;
        } else {
            z = this.l;
        }
        aVar.c = z;
        if (this.J.e > 1) {
            this.o.a = this.J.f;
            this.o.b = this.J.g;
        }
    }

    private void a(b bVar, int i, int i2) {
        int i3 = bVar.i();
        if (i == -1) {
            if (bVar.b() + i3 > i2) {
                return;
            }
        } else if (bVar.d() - i3 < i2) {
            return;
        }
        this.F.set(bVar.f, false);
    }

    private void a(View view, int i, int i2, boolean z) {
        b(view, this.L);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int b2 = b(i, layoutParams.leftMargin + this.L.left, layoutParams.rightMargin + this.L.right);
        int b3 = b(i2, layoutParams.topMargin + this.L.top, layoutParams.bottomMargin + this.L.bottom);
        if (z ? a(view, b2, b3, layoutParams) : b(view, b2, b3, layoutParams)) {
            view.measure(b2, b3);
        }
    }

    private void a(View view, LayoutParams layoutParams, y yVar) {
        if (yVar.k == 1) {
            if (layoutParams.c) {
                w(view);
            } else {
                layoutParams.b.b(view);
            }
        } else if (layoutParams.c) {
            x(view);
        } else {
            layoutParams.b.a(view);
        }
    }

    private void a(View view, LayoutParams layoutParams, boolean z) {
        int a2;
        int a3;
        if (layoutParams.c) {
            if (this.s != 1) {
                a(view, a(J(), H(), L() + N(), layoutParams.width, true), this.K, z);
                return;
            }
            a2 = this.K;
        } else if (this.s != 1) {
            a2 = a(J(), H(), L() + N(), layoutParams.width, true);
            a3 = a(this.t, I(), 0, layoutParams.height, false);
            a(view, a2, a3, z);
        } else {
            a2 = a(this.t, H(), 0, layoutParams.width, false);
        }
        a3 = a(K(), I(), M() + O(), layoutParams.height, true);
        a(view, a2, a3, z);
    }

    private boolean a(b bVar) {
        if (this.l) {
            if (bVar.d() < this.i.e()) {
                return !bVar.c(bVar.b.get(bVar.b.size() - 1)).c;
            }
        } else if (bVar.b() > this.i.d()) {
            return !bVar.c(bVar.b.get(0)).c;
        }
        return false;
    }

    private int b(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    private int b(RecyclerView.u uVar) {
        if (G() == 0) {
            return 0;
        }
        return ak.a(uVar, this.i, b(!this.O), c(!this.O), this, this.O, this.l);
    }

    private void b(int i, int i2) {
        for (int i3 = 0; i3 < this.r; i3++) {
            if (!this.h[i3].b.isEmpty()) {
                a(this.h[i3], i, i2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(int r5, android.support.v7.widget.RecyclerView.u r6) {
        /*
            r4 = this;
            android.support.v7.widget.y r0 = r4.E
            r1 = 0
            r0.h = r1
            r0.i = r5
            boolean r0 = r4.C()
            r2 = 1
            if (r0 == 0) goto L2e
            int r6 = r6.f()
            r0 = -1
            if (r6 == r0) goto L2e
            boolean r0 = r4.l
            if (r6 >= r5) goto L1b
            r5 = 1
            goto L1c
        L1b:
            r5 = 0
        L1c:
            if (r0 != r5) goto L25
            android.support.v7.widget.ae r5 = r4.i
            int r5 = r5.g()
            goto L2f
        L25:
            android.support.v7.widget.ae r5 = r4.i
            int r5 = r5.g()
            r6 = r5
            r5 = 0
            goto L30
        L2e:
            r5 = 0
        L2f:
            r6 = 0
        L30:
            boolean r0 = r4.B()
            if (r0 == 0) goto L4d
            android.support.v7.widget.y r0 = r4.E
            android.support.v7.widget.ae r3 = r4.i
            int r3 = r3.d()
            int r3 = r3 - r6
            r0.l = r3
            android.support.v7.widget.y r6 = r4.E
            android.support.v7.widget.ae r0 = r4.i
            int r0 = r0.e()
            int r0 = r0 + r5
            r6.m = r0
            goto L5d
        L4d:
            android.support.v7.widget.y r0 = r4.E
            android.support.v7.widget.ae r3 = r4.i
            int r3 = r3.f()
            int r3 = r3 + r5
            r0.m = r3
            android.support.v7.widget.y r5 = r4.E
            int r6 = -r6
            r5.l = r6
        L5d:
            android.support.v7.widget.y r5 = r4.E
            r5.n = r1
            r5.g = r2
            android.support.v7.widget.ae r6 = r4.i
            int r6 = r6.i()
            if (r6 != 0) goto L74
            android.support.v7.widget.ae r6 = r4.i
            int r6 = r6.f()
            if (r6 != 0) goto L74
            r1 = 1
        L74:
            r5.o = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.b(int, android.support.v7.widget.RecyclerView$u):void");
    }

    private void b(RecyclerView.p pVar, int i) {
        for (int G = G() - 1; G >= 0; G--) {
            View j = j(G);
            if (this.i.a(j) < i || this.i.d(j) < i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) j.getLayoutParams();
            if (layoutParams.c) {
                for (int i2 = 0; i2 < this.r; i2++) {
                    if (this.h[i2].b.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.r; i3++) {
                    this.h[i3].g();
                }
            } else if (layoutParams.b.b.size() == 1) {
                return;
            } else {
                layoutParams.b.g();
            }
            b(j, pVar);
        }
    }

    private void b(RecyclerView.p pVar, RecyclerView.u uVar, boolean z) {
        int e2;
        int s = s(Integer.MIN_VALUE);
        if (s != Integer.MIN_VALUE && (e2 = this.i.e() - s) > 0) {
            int i = e2 - (-c(-e2, pVar, uVar));
            if (!z || i <= 0) {
                return;
            }
            this.i.a(i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.l
            if (r0 == 0) goto L9
            int r0 = r6.q()
            goto Ld
        L9:
            int r0 = r6.r()
        Ld:
            r1 = 8
            if (r9 != r1) goto L1b
            if (r7 >= r8) goto L16
            int r2 = r8 + 1
            goto L1d
        L16:
            int r2 = r7 + 1
            r3 = r2
            r2 = r8
            goto L1f
        L1b:
            int r2 = r7 + r8
        L1d:
            r3 = r2
            r2 = r7
        L1f:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r6.o
            r4.b(r2)
            r4 = 1
            if (r9 == r4) goto L3e
            r5 = 2
            if (r9 == r5) goto L38
            if (r9 == r1) goto L2d
            goto L43
        L2d:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.o
            r9.a(r7, r4)
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r6.o
            r7.b(r8, r4)
            goto L43
        L38:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.o
            r9.a(r7, r8)
            goto L43
        L3e:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.o
            r9.b(r7, r8)
        L43:
            if (r3 > r0) goto L46
            return
        L46:
            boolean r7 = r6.l
            if (r7 == 0) goto L4f
            int r7 = r6.r()
            goto L53
        L4f:
            int r7 = r6.q()
        L53:
            if (r2 > r7) goto L58
            r6.y()
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.c(int, int, int):void");
    }

    private void c(RecyclerView.p pVar, RecyclerView.u uVar, boolean z) {
        int d2;
        int r = r(ActivityChooserView.a.a);
        if (r != Integer.MAX_VALUE && (d2 = r - this.i.d()) > 0) {
            int c2 = d2 - c(d2, pVar, uVar);
            if (!z || c2 <= 0) {
                return;
            }
            this.i.a(-c2);
        }
    }

    private boolean c(RecyclerView.u uVar, a aVar) {
        aVar.a = this.H ? x(uVar.i()) : w(uVar.i());
        aVar.b = Integer.MIN_VALUE;
        return true;
    }

    private int i(RecyclerView.u uVar) {
        if (G() == 0) {
            return 0;
        }
        return ak.a(uVar, this.i, b(!this.O), c(!this.O), this, this.O);
    }

    private int j(RecyclerView.u uVar) {
        if (G() == 0) {
            return 0;
        }
        return ak.b(uVar, this.i, b(!this.O), c(!this.O), this, this.O);
    }

    private void n(int i) {
        y yVar = this.E;
        yVar.k = i;
        yVar.j = this.l != (i == -1) ? -1 : 1;
    }

    private LazySpanLookup.FullSpanItem o(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.c = new int[this.r];
        for (int i2 = 0; i2 < this.r; i2++) {
            fullSpanItem.c[i2] = i - this.h[i2].b(i);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem p(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.c = new int[this.r];
        for (int i2 = 0; i2 < this.r; i2++) {
            fullSpanItem.c[i2] = this.h[i2].a(i) - i;
        }
        return fullSpanItem;
    }

    private int q(int i) {
        int a2 = this.h[0].a(i);
        for (int i2 = 1; i2 < this.r; i2++) {
            int a3 = this.h[i2].a(i);
            if (a3 > a2) {
                a2 = a3;
            }
        }
        return a2;
    }

    private int r(int i) {
        int a2 = this.h[0].a(i);
        for (int i2 = 1; i2 < this.r; i2++) {
            int a3 = this.h[i2].a(i);
            if (a3 < a2) {
                a2 = a3;
            }
        }
        return a2;
    }

    private int s(int i) {
        int b2 = this.h[0].b(i);
        for (int i2 = 1; i2 < this.r; i2++) {
            int b3 = this.h[i2].b(i);
            if (b3 > b2) {
                b2 = b3;
            }
        }
        return b2;
    }

    private int t(int i) {
        int b2 = this.h[0].b(i);
        for (int i2 = 1; i2 < this.r; i2++) {
            int b3 = this.h[i2].b(i);
            if (b3 < b2) {
                b2 = b3;
            }
        }
        return b2;
    }

    private void u() {
        this.i = ae.a(this, this.s);
        this.j = ae.a(this, 1 - this.s);
    }

    private boolean u(int i) {
        if (this.s == 0) {
            return (i == -1) != this.l;
        }
        return ((i == -1) == this.l) == l();
    }

    private int v(int i) {
        if (G() == 0) {
            return this.l ? 1 : -1;
        }
        return (i < r()) != this.l ? -1 : 1;
    }

    private void v() {
        this.l = (this.s == 1 || !l()) ? this.k : !this.k;
    }

    private int w(int i) {
        int G = G();
        for (int i2 = 0; i2 < G; i2++) {
            int e2 = e(j(i2));
            if (e2 >= 0 && e2 < i) {
                return e2;
            }
        }
        return 0;
    }

    private void w() {
        int i;
        int i2;
        if (this.j.i() == 1073741824) {
            return;
        }
        int G = G();
        float f2 = 0.0f;
        for (int i3 = 0; i3 < G; i3++) {
            View j = j(i3);
            float e2 = this.j.e(j);
            if (e2 >= f2) {
                if (((LayoutParams) j.getLayoutParams()).a()) {
                    e2 = (e2 * 1.0f) / this.r;
                }
                f2 = Math.max(f2, e2);
            }
        }
        int i4 = this.t;
        int round = Math.round(f2 * this.r);
        if (this.j.i() == Integer.MIN_VALUE) {
            round = Math.min(round, this.j.g());
        }
        g(round);
        if (this.t == i4) {
            return;
        }
        for (int i5 = 0; i5 < G; i5++) {
            View j2 = j(i5);
            LayoutParams layoutParams = (LayoutParams) j2.getLayoutParams();
            if (!layoutParams.c) {
                if (l() && this.s == 1) {
                    i = (-((this.r - 1) - layoutParams.b.f)) * this.t;
                    i2 = (-((this.r - 1) - layoutParams.b.f)) * i4;
                } else {
                    i = layoutParams.b.f * this.t;
                    i2 = layoutParams.b.f * i4;
                    if (this.s != 1) {
                        j2.offsetTopAndBottom(i - i2);
                    }
                }
                j2.offsetLeftAndRight(i - i2);
            }
        }
    }

    private void w(View view) {
        for (int i = this.r - 1; i >= 0; i--) {
            this.h[i].b(view);
        }
    }

    private int x(int i) {
        for (int G = G() - 1; G >= 0; G--) {
            int e2 = e(j(G));
            if (e2 >= 0 && e2 < i) {
                return e2;
            }
        }
        return 0;
    }

    private void x(View view) {
        for (int i = this.r - 1; i >= 0; i--) {
            this.h[i].a(view);
        }
    }

    private int y(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.s == 1) ? 1 : Integer.MIN_VALUE : this.s == 0 ? 1 : Integer.MIN_VALUE : this.s == 1 ? -1 : Integer.MIN_VALUE : this.s == 0 ? -1 : Integer.MIN_VALUE : (this.s != 1 && l()) ? -1 : 1 : (this.s != 1 && l()) ? 1 : -1;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int a(int i, RecyclerView.p pVar, RecyclerView.u uVar) {
        return c(i, pVar, uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int a(RecyclerView.p pVar, RecyclerView.u uVar) {
        return this.s == 0 ? this.r : super.a(pVar, uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public RecyclerView.LayoutParams a() {
        return this.s == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public RecyclerView.LayoutParams a(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public RecyclerView.LayoutParams a(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    @android.support.annotation.ag
    public View a(View view, int i, RecyclerView.p pVar, RecyclerView.u uVar) {
        View g2;
        View a2;
        if (G() == 0 || (g2 = g(view)) == null) {
            return null;
        }
        v();
        int y = y(i);
        if (y == Integer.MIN_VALUE) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) g2.getLayoutParams();
        boolean z = layoutParams.c;
        b bVar = layoutParams.b;
        int q2 = y == 1 ? q() : r();
        b(q2, uVar);
        n(y);
        y yVar = this.E;
        yVar.i = yVar.j + q2;
        this.E.h = (int) (this.i.g() * q);
        y yVar2 = this.E;
        yVar2.n = true;
        yVar2.g = false;
        a(pVar, yVar2, uVar);
        this.H = this.l;
        if (z || (a2 = bVar.a(q2, y)) == null || a2 == g2) {
            if (u(y)) {
                for (int i2 = this.r - 1; i2 >= 0; i2--) {
                    View a3 = this.h[i2].a(q2, y);
                    if (a3 != null && a3 != g2) {
                        return a3;
                    }
                }
            } else {
                for (int i3 = 0; i3 < this.r; i3++) {
                    View a4 = this.h[i3].a(q2, y);
                    if (a4 != null && a4 != g2) {
                        return a4;
                    }
                }
            }
            boolean z2 = (this.k ^ true) == (y == -1);
            if (!z) {
                View c2 = c(z2 ? bVar.k() : bVar.n());
                if (c2 != null && c2 != g2) {
                    return c2;
                }
            }
            if (u(y)) {
                for (int i4 = this.r - 1; i4 >= 0; i4--) {
                    if (i4 != bVar.f) {
                        View c3 = c(z2 ? this.h[i4].k() : this.h[i4].n());
                        if (c3 != null && c3 != g2) {
                            return c3;
                        }
                    }
                }
            } else {
                for (int i5 = 0; i5 < this.r; i5++) {
                    View c4 = c(z2 ? this.h[i5].k() : this.h[i5].n());
                    if (c4 != null && c4 != g2) {
                        return c4;
                    }
                }
            }
            return null;
        }
        return a2;
    }

    public void a(int i) {
        a((String) null);
        if (i != this.r) {
            k();
            this.r = i;
            this.F = new BitSet(this.r);
            this.h = new b[this.r];
            for (int i2 = 0; i2 < this.r; i2++) {
                this.h[i2] = new b(i2);
            }
            y();
        }
    }

    public void a(int i, int i2) {
        SavedState savedState = this.J;
        if (savedState != null) {
            savedState.b();
        }
        this.m = i;
        this.n = i2;
        y();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    @android.support.annotation.an(a = {an.a.LIBRARY})
    public void a(int i, int i2, RecyclerView.u uVar, RecyclerView.i.a aVar) {
        int b2;
        int i3;
        if (this.s != 0) {
            i = i2;
        }
        if (G() == 0 || i == 0) {
            return;
        }
        a(i, uVar);
        int[] iArr = this.P;
        if (iArr == null || iArr.length < this.r) {
            this.P = new int[this.r];
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.r; i5++) {
            if (this.E.j == -1) {
                b2 = this.E.l;
                i3 = this.h[i5].a(this.E.l);
            } else {
                b2 = this.h[i5].b(this.E.m);
                i3 = this.E.m;
            }
            int i6 = b2 - i3;
            if (i6 >= 0) {
                this.P[i4] = i6;
                i4++;
            }
        }
        Arrays.sort(this.P, 0, i4);
        for (int i7 = 0; i7 < i4 && this.E.a(uVar); i7++) {
            aVar.b(this.E.i, this.P[i7]);
            this.E.i += this.E.j;
        }
    }

    void a(int i, RecyclerView.u uVar) {
        int r;
        int i2;
        if (i > 0) {
            r = q();
            i2 = 1;
        } else {
            r = r();
            i2 = -1;
        }
        this.E.g = true;
        b(r, uVar);
        n(i2);
        y yVar = this.E;
        yVar.i = r + yVar.j;
        this.E.h = Math.abs(i);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(Rect rect, int i, int i2) {
        int a2;
        int a3;
        int L = L() + N();
        int M = M() + O();
        if (this.s == 1) {
            a3 = a(i2, rect.height() + M, X());
            a2 = a(i, (this.t * this.r) + L, W());
        } else {
            a2 = a(i, rect.width() + L, W());
            a3 = a(i2, (this.t * this.r) + M, X());
        }
        g(a2, a3);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.J = (SavedState) parcelable;
            y();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView.p pVar, RecyclerView.u uVar, View view, android.support.v4.view.a.c cVar) {
        int i;
        int i2;
        int b2;
        int i3;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.a(view, cVar);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (this.s == 0) {
            i = layoutParams2.b();
            i2 = layoutParams2.c ? this.r : 1;
            b2 = -1;
            i3 = -1;
        } else {
            i = -1;
            i2 = -1;
            b2 = layoutParams2.b();
            i3 = layoutParams2.c ? this.r : 1;
        }
        cVar.c(c.C0032c.a(i, i2, b2, i3, layoutParams2.c, false));
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView.u uVar) {
        super.a(uVar);
        this.m = -1;
        this.n = Integer.MIN_VALUE;
        this.J = null;
        this.M.a();
    }

    void a(RecyclerView.u uVar, a aVar) {
        if (b(uVar, aVar) || c(uVar, aVar)) {
            return;
        }
        aVar.b();
        aVar.a = 0;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView) {
        this.o.a();
        y();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView, int i, int i2) {
        c(i, i2, 1);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView, int i, int i2, int i3) {
        c(i, i2, 8);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView, int i, int i2, Object obj) {
        c(i, i2, 4);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView, RecyclerView.p pVar) {
        super.a(recyclerView, pVar);
        b(this.Q);
        for (int i = 0; i < this.r; i++) {
            this.h[i].e();
        }
        recyclerView.requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(RecyclerView recyclerView, RecyclerView.u uVar, int i) {
        z zVar = new z(recyclerView.getContext());
        zVar.c(i);
        a(zVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(AccessibilityEvent accessibilityEvent) {
        super.a(accessibilityEvent);
        if (G() > 0) {
            View b2 = b(false);
            View c2 = c(false);
            if (b2 == null || c2 == null) {
                return;
            }
            int e2 = e(b2);
            int e3 = e(c2);
            if (e2 < e3) {
                accessibilityEvent.setFromIndex(e2);
                accessibilityEvent.setToIndex(e3);
                return;
            }
            accessibilityEvent.setFromIndex(e3);
            accessibilityEvent.setToIndex(e2);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void a(String str) {
        if (this.J == null) {
            super.a(str);
        }
    }

    public void a(boolean z) {
        a((String) null);
        SavedState savedState = this.J;
        if (savedState != null && savedState.h != z) {
            this.J.h = z;
        }
        this.k = z;
        y();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public boolean a(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public int[] a(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.r];
        } else if (iArr.length < this.r) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.r + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.r; i++) {
            iArr[i] = this.h[i].j();
        }
        return iArr;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int b(int i, RecyclerView.p pVar, RecyclerView.u uVar) {
        return c(i, pVar, uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int b(RecyclerView.p pVar, RecyclerView.u uVar) {
        return this.s == 1 ? this.r : super.b(pVar, uVar);
    }

    View b(boolean z) {
        int d2 = this.i.d();
        int e2 = this.i.e();
        int G = G();
        View view = null;
        for (int i = 0; i < G; i++) {
            View j = j(i);
            int a2 = this.i.a(j);
            if (this.i.b(j) > d2 && a2 < e2) {
                if (a2 >= d2 || !z) {
                    return j;
                }
                if (view == null) {
                    view = j;
                }
            }
        }
        return view;
    }

    public void b(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        a((String) null);
        if (i == this.s) {
            return;
        }
        this.s = i;
        ae aeVar = this.i;
        this.i = this.j;
        this.j = aeVar;
        y();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void b(RecyclerView recyclerView, int i, int i2) {
        c(i, i2, 2);
    }

    boolean b() {
        int r;
        int q2;
        if (G() == 0 || this.G == 0 || !A()) {
            return false;
        }
        if (this.l) {
            r = q();
            q2 = r();
        } else {
            r = r();
            q2 = q();
        }
        if (r == 0 && c() != null) {
            this.o.a();
        } else if (!this.N) {
            return false;
        } else {
            int i = this.l ? -1 : 1;
            int i2 = q2 + 1;
            LazySpanLookup.FullSpanItem a2 = this.o.a(r, i2, i, true);
            if (a2 == null) {
                this.N = false;
                this.o.a(i2);
                return false;
            }
            LazySpanLookup.FullSpanItem a3 = this.o.a(r, a2.a, i * (-1), true);
            if (a3 == null) {
                this.o.a(a2.a);
            } else {
                this.o.a(a3.a + 1);
            }
        }
        Z();
        y();
        return true;
    }

    boolean b(RecyclerView.u uVar, a aVar) {
        int i;
        int d2;
        int a2;
        if (!uVar.c() && (i = this.m) != -1) {
            if (i >= 0 && i < uVar.i()) {
                SavedState savedState = this.J;
                if (savedState == null || savedState.a == -1 || this.J.c < 1) {
                    View c2 = c(this.m);
                    if (c2 != null) {
                        aVar.a = this.l ? q() : r();
                        if (this.n != Integer.MIN_VALUE) {
                            if (aVar.c) {
                                d2 = this.i.e() - this.n;
                                a2 = this.i.b(c2);
                            } else {
                                d2 = this.i.d() + this.n;
                                a2 = this.i.a(c2);
                            }
                            aVar.b = d2 - a2;
                            return true;
                        } else if (this.i.e(c2) > this.i.g()) {
                            aVar.b = aVar.c ? this.i.e() : this.i.d();
                            return true;
                        } else {
                            int a3 = this.i.a(c2) - this.i.d();
                            if (a3 < 0) {
                                aVar.b = -a3;
                                return true;
                            }
                            int e2 = this.i.e() - this.i.b(c2);
                            if (e2 < 0) {
                                aVar.b = e2;
                                return true;
                            }
                            aVar.b = Integer.MIN_VALUE;
                        }
                    } else {
                        aVar.a = this.m;
                        int i2 = this.n;
                        if (i2 == Integer.MIN_VALUE) {
                            aVar.c = v(aVar.a) == 1;
                            aVar.b();
                        } else {
                            aVar.a(i2);
                        }
                        aVar.d = true;
                    }
                } else {
                    aVar.b = Integer.MIN_VALUE;
                    aVar.a = this.m;
                }
                return true;
            }
            this.m = -1;
            this.n = Integer.MIN_VALUE;
        }
        return false;
    }

    public int[] b(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.r];
        } else if (iArr.length < this.r) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.r + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.r; i++) {
            iArr[i] = this.h[i].l();
        }
        return iArr;
    }

    int c(int i, RecyclerView.p pVar, RecyclerView.u uVar) {
        if (G() == 0 || i == 0) {
            return 0;
        }
        a(i, uVar);
        int a2 = a(pVar, this.E, uVar);
        if (this.E.h >= a2) {
            i = i < 0 ? -a2 : a2;
        }
        this.i.a(-i);
        this.H = this.l;
        y yVar = this.E;
        yVar.h = 0;
        a(pVar, yVar);
        return i;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int c(RecyclerView.u uVar) {
        return b(uVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
        if (r10 == r11) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
        if (r10 == r11) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0088, code lost:
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008a, code lost:
        r10 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    android.view.View c() {
        /*
            r12 = this;
            int r0 = r12.G()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.r
            r2.<init>(r3)
            int r3 = r12.r
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.s
            r5 = -1
            if (r3 != r1) goto L20
            boolean r3 = r12.l()
            if (r3 == 0) goto L20
            r3 = 1
            goto L21
        L20:
            r3 = -1
        L21:
            boolean r6 = r12.l
            if (r6 == 0) goto L27
            r6 = -1
            goto L2b
        L27:
            int r0 = r0 + 1
            r6 = r0
            r0 = 0
        L2b:
            if (r0 >= r6) goto L2e
            r5 = 1
        L2e:
            if (r0 == r6) goto Lab
            android.view.View r7 = r12.j(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r8 = (android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams) r8
            android.support.v7.widget.StaggeredGridLayoutManager$b r9 = r8.b
            int r9 = r9.f
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            android.support.v7.widget.StaggeredGridLayoutManager$b r9 = r8.b
            boolean r9 = r12.a(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            android.support.v7.widget.StaggeredGridLayoutManager$b r9 = r8.b
            int r9 = r9.f
            r2.clear(r9)
        L54:
            boolean r9 = r8.c
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.j(r9)
            boolean r10 = r12.l
            if (r10 == 0) goto L77
            android.support.v7.widget.ae r10 = r12.i
            int r10 = r10.b(r7)
            android.support.v7.widget.ae r11 = r12.i
            int r11 = r11.b(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            android.support.v7.widget.ae r10 = r12.i
            int r10 = r10.a(r7)
            android.support.v7.widget.ae r11 = r12.i
            int r11 = r11.a(r9)
            if (r10 <= r11) goto L86
            return r7
        L86:
            if (r10 != r11) goto L8a
        L88:
            r10 = 1
            goto L8b
        L8a:
            r10 = 0
        L8b:
            if (r10 == 0) goto La9
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r9 = (android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams) r9
            android.support.v7.widget.StaggeredGridLayoutManager$b r8 = r8.b
            int r8 = r8.f
            android.support.v7.widget.StaggeredGridLayoutManager$b r9 = r9.b
            int r9 = r9.f
            int r8 = r8 - r9
            if (r8 >= 0) goto La0
            r8 = 1
            goto La1
        La0:
            r8 = 0
        La1:
            if (r3 >= 0) goto La5
            r9 = 1
            goto La6
        La5:
            r9 = 0
        La6:
            if (r8 == r9) goto La9
            return r7
        La9:
            int r0 = r0 + r5
            goto L2e
        Lab:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.c():android.view.View");
    }

    View c(boolean z) {
        int d2 = this.i.d();
        int e2 = this.i.e();
        View view = null;
        for (int G = G() - 1; G >= 0; G--) {
            View j = j(G);
            int a2 = this.i.a(j);
            int b2 = this.i.b(j);
            if (b2 > d2 && a2 < e2) {
                if (b2 <= e2 || !z) {
                    return j;
                }
                if (view == null) {
                    view = j;
                }
            }
        }
        return view;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void c(RecyclerView.p pVar, RecyclerView.u uVar) {
        a(pVar, uVar, true);
    }

    public int[] c(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.r];
        } else if (iArr.length < this.r) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.r + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.r; i++) {
            iArr[i] = this.h[i].m();
        }
        return iArr;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int d(RecyclerView.u uVar) {
        return b(uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.t.b
    public PointF d(int i) {
        int v = v(i);
        PointF pointF = new PointF();
        if (v == 0) {
            return null;
        }
        if (this.s == 0) {
            pointF.x = v;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = v;
        }
        return pointF;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public boolean d() {
        return this.J == null;
    }

    public int[] d(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.r];
        } else if (iArr.length < this.r) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.r + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.r; i++) {
            iArr[i] = this.h[i].o();
        }
        return iArr;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int e(RecyclerView.u uVar) {
        return i(uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void e(int i) {
        SavedState savedState = this.J;
        if (savedState != null && savedState.a != i) {
            this.J.b();
        }
        this.m = i;
        this.n = Integer.MIN_VALUE;
        y();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public boolean e() {
        return this.G != 0;
    }

    public int f() {
        return this.G;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int f(RecyclerView.u uVar) {
        return i(uVar);
    }

    public void f(int i) {
        a((String) null);
        if (i == this.G) {
            return;
        }
        if (i != 0 && i != 2) {
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
        this.G = i;
        y();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int g(RecyclerView.u uVar) {
        return j(uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public Parcelable g() {
        int a2;
        int d2;
        SavedState savedState = this.J;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        savedState2.h = this.k;
        savedState2.i = this.H;
        savedState2.j = this.I;
        LazySpanLookup lazySpanLookup = this.o;
        if (lazySpanLookup == null || lazySpanLookup.a == null) {
            savedState2.e = 0;
        } else {
            savedState2.f = this.o.a;
            savedState2.e = savedState2.f.length;
            savedState2.g = this.o.b;
        }
        if (G() > 0) {
            savedState2.a = this.H ? q() : r();
            savedState2.b = n();
            int i = this.r;
            savedState2.c = i;
            savedState2.d = new int[i];
            for (int i2 = 0; i2 < this.r; i2++) {
                if (this.H) {
                    a2 = this.h[i2].b(Integer.MIN_VALUE);
                    if (a2 != Integer.MIN_VALUE) {
                        d2 = this.i.e();
                        a2 -= d2;
                        savedState2.d[i2] = a2;
                    } else {
                        savedState2.d[i2] = a2;
                    }
                } else {
                    a2 = this.h[i2].a(Integer.MIN_VALUE);
                    if (a2 != Integer.MIN_VALUE) {
                        d2 = this.i.d();
                        a2 -= d2;
                        savedState2.d[i2] = a2;
                    } else {
                        savedState2.d[i2] = a2;
                    }
                }
            }
        } else {
            savedState2.a = -1;
            savedState2.b = -1;
            savedState2.c = 0;
        }
        return savedState2;
    }

    void g(int i) {
        this.t = i / this.r;
        this.K = View.MeasureSpec.makeMeasureSpec(i, this.j.i());
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public int h(RecyclerView.u uVar) {
        return j(uVar);
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public boolean h() {
        return this.s == 0;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public boolean i() {
        return this.s == 1;
    }

    public int j() {
        return this.r;
    }

    public void k() {
        this.o.a();
        y();
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void k(int i) {
        super.k(i);
        for (int i2 = 0; i2 < this.r; i2++) {
            this.h[i2].d(i);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void l(int i) {
        super.l(i);
        for (int i2 = 0; i2 < this.r; i2++) {
            this.h[i2].d(i);
        }
    }

    boolean l() {
        return D() == 1;
    }

    @Override // android.support.v7.widget.RecyclerView.i
    public void m(int i) {
        if (i == 0) {
            b();
        }
    }

    public boolean m() {
        return this.k;
    }

    int n() {
        View c2 = this.l ? c(true) : b(true);
        if (c2 == null) {
            return -1;
        }
        return e(c2);
    }

    boolean o() {
        int b2 = this.h[0].b(Integer.MIN_VALUE);
        for (int i = 1; i < this.r; i++) {
            if (this.h[i].b(Integer.MIN_VALUE) != b2) {
                return false;
            }
        }
        return true;
    }

    boolean p() {
        int a2 = this.h[0].a(Integer.MIN_VALUE);
        for (int i = 1; i < this.r; i++) {
            if (this.h[i].a(Integer.MIN_VALUE) != a2) {
                return false;
            }
        }
        return true;
    }

    int q() {
        int G = G();
        if (G == 0) {
            return 0;
        }
        return e(j(G - 1));
    }

    int r() {
        if (G() == 0) {
            return 0;
        }
        return e(j(0));
    }

    public int t() {
        return this.s;
    }
}
