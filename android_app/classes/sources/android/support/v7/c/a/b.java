package android.support.v7.c.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.util.SparseArray;
/* JADX INFO: Access modifiers changed from: package-private */
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b extends Drawable implements Drawable.Callback {
    private static final boolean a = false;
    private static final String b = "DrawableContainer";
    private static final boolean c = true;
    private AbstractC0041b d;
    private Rect e;
    private Drawable f;
    private Drawable g;
    private boolean i;
    private boolean l;
    private Runnable m;
    private long n;
    private long o;
    private a p;
    private int h = 255;
    private int j = -1;
    private int k = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a implements Drawable.Callback {
        private Drawable.Callback a;

        a() {
        }

        public Drawable.Callback a() {
            Drawable.Callback callback = this.a;
            this.a = null;
            return callback;
        }

        public a a(Drawable.Callback callback) {
            this.a = callback;
            return this;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(@af Drawable drawable) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(@af Drawable drawable, @af Runnable runnable, long j) {
            Drawable.Callback callback = this.a;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(@af Drawable drawable, @af Runnable runnable) {
            Drawable.Callback callback = this.a;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.support.v7.c.a.b$b  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class AbstractC0041b extends Drawable.ConstantState {
        boolean A;
        int B;
        int C;
        int D;
        boolean E;
        ColorFilter F;
        boolean G;
        ColorStateList H;
        PorterDuff.Mode I;
        boolean J;
        boolean K;
        final b c;
        Resources d;
        int e;
        int f;
        int g;
        SparseArray<Drawable.ConstantState> h;
        Drawable[] i;
        int j;
        boolean k;
        boolean l;
        Rect m;
        boolean n;
        boolean o;
        int p;
        int q;
        int r;
        int s;
        boolean t;
        int u;
        boolean v;
        boolean w;
        boolean x;
        boolean y;
        boolean z;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AbstractC0041b(AbstractC0041b abstractC0041b, b bVar, Resources resources) {
            this.e = 160;
            this.k = false;
            this.n = false;
            this.z = b.c;
            this.C = 0;
            this.D = 0;
            this.c = bVar;
            this.d = resources != null ? resources : abstractC0041b != null ? abstractC0041b.d : null;
            this.e = b.a(resources, abstractC0041b != null ? abstractC0041b.e : 0);
            if (abstractC0041b == null) {
                this.i = new Drawable[10];
                this.j = 0;
                return;
            }
            this.f = abstractC0041b.f;
            this.g = abstractC0041b.g;
            this.x = b.c;
            this.y = b.c;
            this.k = abstractC0041b.k;
            this.n = abstractC0041b.n;
            this.z = abstractC0041b.z;
            this.A = abstractC0041b.A;
            this.B = abstractC0041b.B;
            this.C = abstractC0041b.C;
            this.D = abstractC0041b.D;
            this.E = abstractC0041b.E;
            this.F = abstractC0041b.F;
            this.G = abstractC0041b.G;
            this.H = abstractC0041b.H;
            this.I = abstractC0041b.I;
            this.J = abstractC0041b.J;
            this.K = abstractC0041b.K;
            if (abstractC0041b.e == this.e) {
                if (abstractC0041b.l) {
                    this.m = new Rect(abstractC0041b.m);
                    this.l = b.c;
                }
                if (abstractC0041b.o) {
                    this.p = abstractC0041b.p;
                    this.q = abstractC0041b.q;
                    this.r = abstractC0041b.r;
                    this.s = abstractC0041b.s;
                    this.o = b.c;
                }
            }
            if (abstractC0041b.t) {
                this.u = abstractC0041b.u;
                this.t = b.c;
            }
            if (abstractC0041b.v) {
                this.w = abstractC0041b.w;
                this.v = b.c;
            }
            Drawable[] drawableArr = abstractC0041b.i;
            this.i = new Drawable[drawableArr.length];
            this.j = abstractC0041b.j;
            SparseArray<Drawable.ConstantState> sparseArray = abstractC0041b.h;
            this.h = sparseArray != null ? sparseArray.clone() : new SparseArray<>(this.j);
            int i = this.j;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2] != null) {
                    Drawable.ConstantState constantState = drawableArr[i2].getConstantState();
                    if (constantState != null) {
                        this.h.put(i2, constantState);
                    } else {
                        this.i[i2] = drawableArr[i2];
                    }
                }
            }
        }

        private Drawable b(Drawable drawable) {
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(this.B);
            }
            Drawable mutate = drawable.mutate();
            mutate.setCallback(this.c);
            return mutate;
        }

        private void r() {
            SparseArray<Drawable.ConstantState> sparseArray = this.h;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    this.i[this.h.keyAt(i)] = b(this.h.valueAt(i).newDrawable(this.d));
                }
                this.h = null;
            }
        }

        public final int a(Drawable drawable) {
            int i = this.j;
            if (i >= this.i.length) {
                e(i, i + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, b.c);
            drawable.setCallback(this.c);
            this.i[i] = drawable;
            this.j++;
            this.g = drawable.getChangingConfigurations() | this.g;
            b();
            this.m = null;
            this.l = false;
            this.o = false;
            this.x = false;
            return i;
        }

        void a() {
            int i = this.j;
            Drawable[] drawableArr = this.i;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2] != null) {
                    drawableArr[i2].mutate();
                }
            }
            this.A = b.c;
        }

        @ak(a = 21)
        final void a(Resources.Theme theme) {
            if (theme != null) {
                r();
                int i = this.j;
                Drawable[] drawableArr = this.i;
                for (int i2 = 0; i2 < i; i2++) {
                    if (drawableArr[i2] != null && drawableArr[i2].canApplyTheme()) {
                        drawableArr[i2].applyTheme(theme);
                        this.g |= drawableArr[i2].getChangingConfigurations();
                    }
                }
                a(theme.getResources());
            }
        }

        final void a(Resources resources) {
            if (resources != null) {
                this.d = resources;
                int a = b.a(resources, this.e);
                int i = this.e;
                this.e = a;
                if (i != a) {
                    this.o = false;
                    this.l = false;
                }
            }
        }

        public final void a(boolean z) {
            this.k = z;
        }

        public final Drawable b(int i) {
            int indexOfKey;
            Drawable drawable = this.i[i];
            if (drawable != null) {
                return drawable;
            }
            SparseArray<Drawable.ConstantState> sparseArray = this.h;
            if (sparseArray == null || (indexOfKey = sparseArray.indexOfKey(i)) < 0) {
                return null;
            }
            Drawable b = b(this.h.valueAt(indexOfKey).newDrawable(this.d));
            this.i[i] = b;
            this.h.removeAt(indexOfKey);
            if (this.h.size() == 0) {
                this.h = null;
            }
            return b;
        }

        void b() {
            this.t = false;
            this.v = false;
        }

        public final void b(boolean z) {
            this.n = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final int c() {
            return this.i.length;
        }

        public final void c(int i) {
            this.C = i;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @ak(a = 21)
        public boolean canApplyTheme() {
            int i = this.j;
            Drawable[] drawableArr = this.i;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                if (drawable == null) {
                    Drawable.ConstantState constantState = this.h.get(i2);
                    if (constantState != null && constantState.canApplyTheme()) {
                        return b.c;
                    }
                } else if (drawable.canApplyTheme()) {
                    return b.c;
                }
            }
            return false;
        }

        public final int d() {
            return this.j;
        }

        public final void d(int i) {
            this.D = i;
        }

        final boolean d(int i, int i2) {
            int i3 = this.j;
            Drawable[] drawableArr = this.i;
            boolean z = false;
            for (int i4 = 0; i4 < i3; i4++) {
                if (drawableArr[i4] != null) {
                    boolean layoutDirection = Build.VERSION.SDK_INT >= 23 ? drawableArr[i4].setLayoutDirection(i) : false;
                    if (i4 == i2) {
                        z = layoutDirection;
                    }
                }
            }
            this.B = i;
            return z;
        }

        final void e() {
            this.A = false;
        }

        public void e(int i, int i2) {
            Drawable[] drawableArr = new Drawable[i2];
            System.arraycopy(this.i, 0, drawableArr, 0, i);
            this.i = drawableArr;
        }

        public final Rect f() {
            if (this.k) {
                return null;
            }
            if (this.m != null || this.l) {
                return this.m;
            }
            r();
            Rect rect = new Rect();
            int i = this.j;
            Drawable[] drawableArr = this.i;
            Rect rect2 = null;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2].getPadding(rect)) {
                    if (rect2 == null) {
                        rect2 = new Rect(0, 0, 0, 0);
                    }
                    if (rect.left > rect2.left) {
                        rect2.left = rect.left;
                    }
                    if (rect.top > rect2.top) {
                        rect2.top = rect.top;
                    }
                    if (rect.right > rect2.right) {
                        rect2.right = rect.right;
                    }
                    if (rect.bottom > rect2.bottom) {
                        rect2.bottom = rect.bottom;
                    }
                }
            }
            this.l = b.c;
            this.m = rect2;
            return rect2;
        }

        public final boolean g() {
            return this.n;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f | this.g;
        }

        public final int h() {
            if (!this.o) {
                l();
            }
            return this.p;
        }

        public final int i() {
            if (!this.o) {
                l();
            }
            return this.q;
        }

        public final int j() {
            if (!this.o) {
                l();
            }
            return this.r;
        }

        public final int k() {
            if (!this.o) {
                l();
            }
            return this.s;
        }

        protected void l() {
            this.o = b.c;
            r();
            int i = this.j;
            Drawable[] drawableArr = this.i;
            this.q = -1;
            this.p = -1;
            this.s = 0;
            this.r = 0;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.p) {
                    this.p = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.q) {
                    this.q = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.r) {
                    this.r = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.s) {
                    this.s = minimumHeight;
                }
            }
        }

        public final int m() {
            return this.C;
        }

        public final int n() {
            return this.D;
        }

        public final int o() {
            if (this.t) {
                return this.u;
            }
            r();
            int i = this.j;
            Drawable[] drawableArr = this.i;
            int opacity = i > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i2 = 1; i2 < i; i2++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i2].getOpacity());
            }
            this.u = opacity;
            this.t = b.c;
            return opacity;
        }

        public final boolean p() {
            if (this.v) {
                return this.w;
            }
            r();
            int i = this.j;
            Drawable[] drawableArr = this.i;
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    break;
                } else if (drawableArr[i2].isStateful()) {
                    z = b.c;
                    break;
                } else {
                    i2++;
                }
            }
            this.w = z;
            this.v = b.c;
            return z;
        }

        public synchronized boolean q() {
            if (this.x) {
                return this.y;
            }
            r();
            this.x = b.c;
            int i = this.j;
            Drawable[] drawableArr = this.i;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2].getConstantState() == null) {
                    this.y = false;
                    return false;
                }
            }
            this.y = b.c;
            return b.c;
        }
    }

    static int a(@ag Resources resources, int i) {
        if (resources != null) {
            i = resources.getDisplayMetrics().densityDpi;
        }
        if (i == 0) {
            return 160;
        }
        return i;
    }

    private void a(Drawable drawable) {
        if (this.p == null) {
            this.p = new a();
        }
        drawable.setCallback(this.p.a(drawable.getCallback()));
        try {
            if (this.d.C <= 0 && this.i) {
                drawable.setAlpha(this.h);
            }
            if (this.d.G) {
                drawable.setColorFilter(this.d.F);
            } else {
                if (this.d.J) {
                    android.support.v4.graphics.drawable.a.a(drawable, this.d.H);
                }
                if (this.d.K) {
                    android.support.v4.graphics.drawable.a.a(drawable, this.d.I);
                }
            }
            drawable.setVisible(isVisible(), c);
            drawable.setDither(this.d.z);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            if (Build.VERSION.SDK_INT >= 19) {
                drawable.setAutoMirrored(this.d.E);
            }
            Rect rect = this.e;
            if (Build.VERSION.SDK_INT >= 21 && rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.p.a());
        }
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(23)
    private boolean a() {
        if (isAutoMirrored() && getLayoutDirection() == 1) {
            return c;
        }
        return false;
    }

    public void a(int i) {
        this.d.D = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Resources resources) {
        this.d.a(resources);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(AbstractC0041b abstractC0041b) {
        this.d = abstractC0041b;
        int i = this.j;
        if (i >= 0) {
            this.f = abstractC0041b.b(i);
            Drawable drawable = this.f;
            if (drawable != null) {
                a(drawable);
            }
        }
        this.k = -1;
        this.g = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(boolean r14) {
        /*
            r13 = this;
            r0 = 1
            r13.i = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.f
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L38
            long r9 = r13.n
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L3a
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L20
            int r9 = r13.h
            r3.setAlpha(r9)
            goto L38
        L20:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r3 = (int) r9
            android.support.v7.c.a.b$b r9 = r13.d
            int r9 = r9.C
            int r3 = r3 / r9
            android.graphics.drawable.Drawable r9 = r13.f
            int r3 = 255 - r3
            int r10 = r13.h
            int r3 = r3 * r10
            int r3 = r3 / 255
            r9.setAlpha(r3)
            r3 = 1
            goto L3b
        L38:
            r13.n = r7
        L3a:
            r3 = 0
        L3b:
            android.graphics.drawable.Drawable r9 = r13.g
            if (r9 == 0) goto L68
            long r10 = r13.o
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 == 0) goto L6a
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L53
            r9.setVisible(r6, r6)
            r0 = 0
            r13.g = r0
            r0 = -1
            r13.k = r0
            goto L68
        L53:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            android.support.v7.c.a.b$b r4 = r13.d
            int r4 = r4.D
            int r3 = r3 / r4
            android.graphics.drawable.Drawable r4 = r13.g
            int r5 = r13.h
            int r3 = r3 * r5
            int r3 = r3 / 255
            r4.setAlpha(r3)
            goto L6b
        L68:
            r13.o = r7
        L6a:
            r0 = r3
        L6b:
            if (r14 == 0) goto L77
            if (r0 == 0) goto L77
            java.lang.Runnable r14 = r13.m
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L77:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.c.a.b.a(boolean):void");
    }

    @Override // android.graphics.drawable.Drawable
    @ak(a = 21)
    public void applyTheme(@af Resources.Theme theme) {
        this.d.a(theme);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        this.d.e();
        this.l = false;
    }

    public void b(int i) {
        this.d.C = i;
    }

    void c(int i) {
        d(i);
    }

    @Override // android.graphics.drawable.Drawable
    @ak(a = 21)
    public boolean canApplyTheme() {
        return this.d.canApplyTheme();
    }

    AbstractC0041b d() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d(int i) {
        if (i == this.j) {
            return false;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.d.D > 0) {
            Drawable drawable = this.g;
            if (drawable != null) {
                drawable.setVisible(false, false);
            }
            Drawable drawable2 = this.f;
            if (drawable2 != null) {
                this.g = drawable2;
                this.k = this.j;
                this.o = this.d.D + uptimeMillis;
            } else {
                this.g = null;
                this.k = -1;
                this.o = 0L;
            }
        } else {
            Drawable drawable3 = this.f;
            if (drawable3 != null) {
                drawable3.setVisible(false, false);
            }
        }
        if (i < 0 || i >= this.d.j) {
            this.f = null;
            this.j = -1;
        } else {
            Drawable b2 = this.d.b(i);
            this.f = b2;
            this.j = i;
            if (b2 != null) {
                if (this.d.C > 0) {
                    this.n = uptimeMillis + this.d.C;
                }
                a(b2);
            }
        }
        if (this.n != 0 || this.o != 0) {
            Runnable runnable = this.m;
            if (runnable == null) {
                this.m = new Runnable() { // from class: android.support.v7.c.a.b.1
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.a(b.c);
                        b.this.invalidateSelf();
                    }
                };
            } else {
                unscheduleSelf(runnable);
            }
            a(c);
        }
        invalidateSelf();
        return c;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@af Canvas canvas) {
        Drawable drawable = this.f;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.g;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.d.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.d.q()) {
            this.d.f = getChangingConfigurations();
            return this.d;
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    @af
    public Drawable getCurrent() {
        return this.f;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(@af Rect rect) {
        Rect rect2 = this.e;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.d.g()) {
            return this.d.i();
        }
        Drawable drawable = this.f;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.d.g()) {
            return this.d.h();
        }
        Drawable drawable = this.f;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        if (this.d.g()) {
            return this.d.k();
        }
        Drawable drawable = this.f;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        if (this.d.g()) {
            return this.d.j();
        }
        Drawable drawable = this.f;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.d.o();
    }

    @Override // android.graphics.drawable.Drawable
    @ak(a = 21)
    public void getOutline(@af Outline outline) {
        Drawable drawable = this.f;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(@af Rect rect) {
        boolean padding;
        Rect f = this.d.f();
        if (f != null) {
            rect.set(f);
            padding = (f.right | ((f.left | f.top) | f.bottom)) != 0 ? c : false;
        } else {
            Drawable drawable = this.f;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (a()) {
            int i = rect.left;
            rect.left = rect.right;
            rect.right = i;
        }
        return padding;
    }

    public void invalidateDrawable(@af Drawable drawable) {
        AbstractC0041b abstractC0041b = this.d;
        if (abstractC0041b != null) {
            abstractC0041b.b();
        }
        if (drawable != this.f || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.d.E;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.d.p();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.g;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.g = null;
            this.k = -1;
            z = c;
        } else {
            z = false;
        }
        Drawable drawable2 = this.f;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.i) {
                this.f.setAlpha(this.h);
            }
        }
        if (this.o != 0) {
            this.o = 0L;
            z = c;
        }
        if (this.n != 0) {
            this.n = 0L;
            z = c;
        }
        if (z) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    @af
    public Drawable mutate() {
        if (!this.l && super.mutate() == this) {
            AbstractC0041b d = d();
            d.a();
            a(d);
            this.l = c;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.g;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        return this.d.d(i, e());
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        Drawable drawable = this.g;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        Drawable drawable2 = this.f;
        if (drawable2 != null) {
            return drawable2.setLevel(i);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.g;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.f;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    public void scheduleDrawable(@af Drawable drawable, @af Runnable runnable, long j) {
        if (drawable != this.f || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.i && this.h == i) {
            return;
        }
        this.i = c;
        this.h = i;
        Drawable drawable = this.f;
        if (drawable != null) {
            if (this.n == 0) {
                drawable.setAlpha(i);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        if (this.d.E != z) {
            AbstractC0041b abstractC0041b = this.d;
            abstractC0041b.E = z;
            Drawable drawable = this.f;
            if (drawable != null) {
                android.support.v4.graphics.drawable.a.a(drawable, abstractC0041b.E);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        AbstractC0041b abstractC0041b = this.d;
        abstractC0041b.G = c;
        if (abstractC0041b.F != colorFilter) {
            this.d.F = colorFilter;
            Drawable drawable = this.f;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        if (this.d.z != z) {
            AbstractC0041b abstractC0041b = this.d;
            abstractC0041b.z = z;
            Drawable drawable = this.f;
            if (drawable != null) {
                drawable.setDither(abstractC0041b.z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f2) {
        Drawable drawable = this.f;
        if (drawable != null) {
            android.support.v4.graphics.drawable.a.a(drawable, f, f2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect rect = this.e;
        if (rect == null) {
            this.e = new Rect(i, i2, i3, i4);
        } else {
            rect.set(i, i2, i3, i4);
        }
        Drawable drawable = this.f;
        if (drawable != null) {
            android.support.v4.graphics.drawable.a.a(drawable, i, i2, i3, i4);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        AbstractC0041b abstractC0041b = this.d;
        abstractC0041b.J = c;
        if (abstractC0041b.H != colorStateList) {
            this.d.H = colorStateList;
            android.support.v4.graphics.drawable.a.a(this.f, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(@af PorterDuff.Mode mode) {
        AbstractC0041b abstractC0041b = this.d;
        abstractC0041b.K = c;
        if (abstractC0041b.I != mode) {
            this.d.I = mode;
            android.support.v4.graphics.drawable.a.a(this.f, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.g;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.f;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    public void unscheduleDrawable(@af Drawable drawable, @af Runnable runnable) {
        if (drawable != this.f || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }
}
