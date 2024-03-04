package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ai;
import android.support.annotation.av;
import android.support.v4.view.ab;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements android.support.v4.view.o, android.support.v4.view.r {
    private static final int A = 300;
    private static final int B = 200;
    private static final int C = 200;
    private static final int D = -328966;
    private static final int E = 64;
    private static final int[] V = {16842766};
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = -1;
    @av
    static final int d = 40;
    @av
    static final int e = 56;
    private static final String s = "SwipeRefreshLayout";
    private static final int t = 255;
    private static final int u = 76;
    private static final float v = 2.0f;
    private static final int w = -1;
    private static final float x = 0.5f;
    private static final float y = 0.8f;
    private static final int z = 150;
    private View F;
    private int G;
    private float H;
    private float I;
    private final android.support.v4.view.t J;
    private final android.support.v4.view.q K;
    private final int[] L;
    private final int[] M;
    private boolean N;
    private int O;
    private float P;
    private float Q;
    private boolean R;
    private int S;
    private boolean T;
    private final DecelerateInterpolator U;
    private int W;
    private Animation aa;
    private Animation ab;
    private Animation ac;
    private Animation ad;
    private Animation ae;
    private int af;
    private a ag;
    private Animation.AnimationListener ah;
    private final Animation ai;
    private final Animation aj;
    b f;
    boolean g;
    int h;
    boolean i;
    c j;
    protected int k;
    float l;
    protected int m;
    int n;
    int o;
    d p;
    boolean q;
    boolean r;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        boolean a(@af SwipeRefreshLayout swipeRefreshLayout, @ag View view);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        void a();
    }

    public SwipeRefreshLayout(@af Context context) {
        this(context, null);
    }

    public SwipeRefreshLayout(@af Context context, @ag AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = false;
        this.H = -1.0f;
        this.L = new int[2];
        this.M = new int[2];
        this.S = -1;
        this.W = -1;
        this.ah = new Animation.AnimationListener() { // from class: android.support.v4.widget.SwipeRefreshLayout.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (!SwipeRefreshLayout.this.g) {
                    SwipeRefreshLayout.this.a();
                    return;
                }
                SwipeRefreshLayout.this.p.setAlpha(255);
                SwipeRefreshLayout.this.p.start();
                if (SwipeRefreshLayout.this.q && SwipeRefreshLayout.this.f != null) {
                    SwipeRefreshLayout.this.f.a();
                }
                SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
                swipeRefreshLayout.h = swipeRefreshLayout.j.getTop();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        };
        this.ai = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.6
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.setTargetOffsetTopAndBottom((SwipeRefreshLayout.this.k + ((int) (((!SwipeRefreshLayout.this.r ? SwipeRefreshLayout.this.n - Math.abs(SwipeRefreshLayout.this.m) : SwipeRefreshLayout.this.n) - SwipeRefreshLayout.this.k) * f))) - SwipeRefreshLayout.this.j.getTop());
                SwipeRefreshLayout.this.p.c(1.0f - f);
            }
        };
        this.aj = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.7
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.a(f);
            }
        };
        this.G = ViewConfiguration.get(context).getScaledTouchSlop();
        this.O = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.U = new DecelerateInterpolator(v);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.af = (int) (displayMetrics.density * 40.0f);
        d();
        setChildrenDrawingOrderEnabled(true);
        this.n = (int) (displayMetrics.density * 64.0f);
        this.H = this.n;
        this.J = new android.support.v4.view.t(this);
        this.K = new android.support.v4.view.q(this);
        setNestedScrollingEnabled(true);
        int i = -this.af;
        this.h = i;
        this.m = i;
        a(1.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, V);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
    }

    private Animation a(final int i, final int i2) {
        Animation animation = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.4
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                d dVar = SwipeRefreshLayout.this.p;
                int i3 = i;
                dVar.setAlpha((int) (i3 + ((i2 - i3) * f)));
            }
        };
        animation.setDuration(300L);
        this.j.a((Animation.AnimationListener) null);
        this.j.clearAnimation();
        this.j.startAnimation(animation);
        return animation;
    }

    private void a(int i, Animation.AnimationListener animationListener) {
        this.k = i;
        this.ai.reset();
        this.ai.setDuration(200L);
        this.ai.setInterpolator(this.U);
        if (animationListener != null) {
            this.j.a(animationListener);
        }
        this.j.clearAnimation();
        this.j.startAnimation(this.ai);
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.S) {
            this.S = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }

    private void a(boolean z2, boolean z3) {
        if (this.g != z2) {
            this.q = z3;
            g();
            this.g = z2;
            if (this.g) {
                a(this.h, this.ah);
            } else {
                a(this.ah);
            }
        }
    }

    private boolean a(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    private void b(float f) {
        this.p.a(true);
        float min = Math.min(1.0f, Math.abs(f / this.H));
        double d2 = min;
        Double.isNaN(d2);
        float max = (((float) Math.max(d2 - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float abs = Math.abs(f) - this.H;
        int i = this.o;
        if (i <= 0) {
            i = this.r ? this.n - this.m : this.n;
        }
        float f2 = i;
        double max2 = Math.max(0.0f, Math.min(abs, f2 * v) / f2) / 4.0f;
        double pow = Math.pow(max2, 2.0d);
        Double.isNaN(max2);
        float f3 = ((float) (max2 - pow)) * v;
        int i2 = this.m + ((int) ((f2 * min) + (f2 * f3 * v)));
        if (this.j.getVisibility() != 0) {
            this.j.setVisibility(0);
        }
        if (!this.i) {
            this.j.setScaleX(1.0f);
            this.j.setScaleY(1.0f);
        }
        if (this.i) {
            setAnimationProgress(Math.min(1.0f, f / this.H));
        }
        if (f < this.H) {
            if (this.p.getAlpha() > 76 && !a(this.ac)) {
                e();
            }
        } else if (this.p.getAlpha() < 255 && !a(this.ad)) {
            f();
        }
        this.p.b(0.0f, Math.min((float) y, max * y));
        this.p.c(Math.min(1.0f, max));
        this.p.d((((max * 0.4f) - 0.25f) + (f3 * v)) * x);
        setTargetOffsetTopAndBottom(i2 - this.h);
    }

    private void b(int i, Animation.AnimationListener animationListener) {
        if (this.i) {
            c(i, animationListener);
            return;
        }
        this.k = i;
        this.aj.reset();
        this.aj.setDuration(200L);
        this.aj.setInterpolator(this.U);
        if (animationListener != null) {
            this.j.a(animationListener);
        }
        this.j.clearAnimation();
        this.j.startAnimation(this.aj);
    }

    private void b(Animation.AnimationListener animationListener) {
        this.j.setVisibility(0);
        this.p.setAlpha(255);
        this.aa = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.2
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(f);
            }
        };
        this.aa.setDuration(this.O);
        if (animationListener != null) {
            this.j.a(animationListener);
        }
        this.j.clearAnimation();
        this.j.startAnimation(this.aa);
    }

    private void c(float f) {
        if (f > this.H) {
            a(true, true);
            return;
        }
        this.g = false;
        this.p.b(0.0f, 0.0f);
        b(this.h, this.i ? null : new Animation.AnimationListener() { // from class: android.support.v4.widget.SwipeRefreshLayout.5
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (SwipeRefreshLayout.this.i) {
                    return;
                }
                SwipeRefreshLayout.this.a((Animation.AnimationListener) null);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.p.a(false);
    }

    private void c(int i, Animation.AnimationListener animationListener) {
        this.k = i;
        this.l = this.j.getScaleX();
        this.ae = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.8
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(SwipeRefreshLayout.this.l + ((-SwipeRefreshLayout.this.l) * f));
                SwipeRefreshLayout.this.a(f);
            }
        };
        this.ae.setDuration(150L);
        if (animationListener != null) {
            this.j.a(animationListener);
        }
        this.j.clearAnimation();
        this.j.startAnimation(this.ae);
    }

    private void d() {
        this.j = new c(getContext(), D);
        this.p = new d(getContext());
        this.p.a(1);
        this.j.setImageDrawable(this.p);
        this.j.setVisibility(8);
        addView(this.j);
    }

    private void d(float f) {
        float f2 = this.Q;
        int i = this.G;
        if (f - f2 <= i || this.R) {
            return;
        }
        this.P = f2 + i;
        this.R = true;
        this.p.setAlpha(76);
    }

    private void e() {
        this.ac = a(this.p.getAlpha(), 76);
    }

    private void f() {
        this.ad = a(this.p.getAlpha(), 255);
    }

    private void g() {
        if (this.F == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (!childAt.equals(this.j)) {
                    this.F = childAt;
                    return;
                }
            }
        }
    }

    private void setColorViewAlpha(int i) {
        this.j.getBackground().setAlpha(i);
        this.p.setAlpha(i);
    }

    void a() {
        this.j.clearAnimation();
        this.p.stop();
        this.j.setVisibility(8);
        setColorViewAlpha(255);
        if (this.i) {
            setAnimationProgress(0.0f);
        } else {
            setTargetOffsetTopAndBottom(this.m - this.h);
        }
        this.h = this.j.getTop();
    }

    void a(float f) {
        int i = this.k;
        setTargetOffsetTopAndBottom((i + ((int) ((this.m - i) * f))) - this.j.getTop());
    }

    void a(Animation.AnimationListener animationListener) {
        this.ab = new Animation() { // from class: android.support.v4.widget.SwipeRefreshLayout.3
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(1.0f - f);
            }
        };
        this.ab.setDuration(150L);
        this.j.a(animationListener);
        this.j.clearAnimation();
        this.j.startAnimation(this.ab);
    }

    public void a(boolean z2, int i) {
        this.n = i;
        this.i = z2;
        this.j.invalidate();
    }

    public void a(boolean z2, int i, int i2) {
        this.i = z2;
        this.m = i;
        this.n = i2;
        this.r = true;
        a();
        this.g = false;
    }

    public boolean b() {
        return this.g;
    }

    public boolean c() {
        a aVar = this.ag;
        if (aVar != null) {
            return aVar.a(this, this.F);
        }
        View view = this.F;
        return view instanceof ListView ? o.b((ListView) view, -1) : view.canScrollVertically(-1);
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean dispatchNestedFling(float f, float f2, boolean z2) {
        return this.K.a(f, f2, z2);
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.K.a(f, f2);
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.K.a(i, i2, iArr, iArr2);
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.K.a(i, i2, i3, i4, iArr);
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i, int i2) {
        int i3 = this.W;
        return i3 < 0 ? i2 : i2 == i + (-1) ? i3 : i2 >= i3 ? i2 + 1 : i2;
    }

    @Override // android.view.ViewGroup, android.support.v4.view.r
    public int getNestedScrollAxes() {
        return this.J.a();
    }

    public int getProgressCircleDiameter() {
        return this.af;
    }

    public int getProgressViewEndOffset() {
        return this.n;
    }

    public int getProgressViewStartOffset() {
        return this.m;
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean hasNestedScrollingParent() {
        return this.K.b();
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean isNestedScrollingEnabled() {
        return this.K.a();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        g();
        int actionMasked = motionEvent.getActionMasked();
        if (this.T && actionMasked == 0) {
            this.T = false;
        }
        if (!isEnabled() || this.T || c() || this.g || this.N) {
            return false;
        }
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int i = this.S;
                    if (i == -1) {
                        Log.e(s, "Got ACTION_MOVE event but don't have an active pointer id.");
                        return false;
                    }
                    int findPointerIndex = motionEvent.findPointerIndex(i);
                    if (findPointerIndex < 0) {
                        return false;
                    }
                    d(motionEvent.getY(findPointerIndex));
                } else if (actionMasked != 3) {
                    if (actionMasked == 6) {
                        a(motionEvent);
                    }
                }
            }
            this.R = false;
            this.S = -1;
        } else {
            setTargetOffsetTopAndBottom(this.m - this.j.getTop());
            this.S = motionEvent.getPointerId(0);
            this.R = false;
            int findPointerIndex2 = motionEvent.findPointerIndex(this.S);
            if (findPointerIndex2 < 0) {
                return false;
            }
            this.Q = motionEvent.getY(findPointerIndex2);
        }
        return this.R;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z2, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.F == null) {
            g();
        }
        View view = this.F;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.j.getMeasuredWidth();
        int measuredHeight2 = this.j.getMeasuredHeight();
        int i5 = measuredWidth / 2;
        int i6 = measuredWidth2 / 2;
        int i7 = this.h;
        this.j.layout(i5 - i6, i7, i5 + i6, measuredHeight2 + i7);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.F == null) {
            g();
        }
        View view = this.F;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        this.j.measure(View.MeasureSpec.makeMeasureSpec(this.af, 1073741824), View.MeasureSpec.makeMeasureSpec(this.af, 1073741824));
        this.W = -1;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == this.j) {
                this.W = i3;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public boolean onNestedFling(View view, float f, float f2, boolean z2) {
        return dispatchNestedFling(f, f2, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0) {
            float f = this.I;
            if (f > 0.0f) {
                float f2 = i2;
                if (f2 > f) {
                    iArr[1] = i2 - ((int) f);
                    this.I = 0.0f;
                } else {
                    this.I = f - f2;
                    iArr[1] = i2;
                }
                b(this.I);
            }
        }
        if (this.r && i2 > 0 && this.I == 0.0f && Math.abs(i2 - iArr[1]) > 0) {
            this.j.setVisibility(8);
        }
        int[] iArr2 = this.L;
        if (dispatchNestedPreScroll(i - iArr[0], i2 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5;
        dispatchNestedScroll(i, i2, i3, i4, this.M);
        if (i4 + this.M[1] >= 0 || c()) {
            return;
        }
        this.I += Math.abs(i5);
        b(this.I);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.J.a(view, view2, i);
        startNestedScroll(i & 2);
        this.I = 0.0f;
        this.N = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (!isEnabled() || this.T || this.g || (i & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.r
    public void onStopNestedScroll(View view) {
        this.J.a(view);
        this.N = false;
        float f = this.I;
        if (f > 0.0f) {
            c(f);
            this.I = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.T && actionMasked == 0) {
            this.T = false;
        }
        if (!isEnabled() || this.T || c() || this.g || this.N) {
            return false;
        }
        if (actionMasked == 0) {
            this.S = motionEvent.getPointerId(0);
            this.R = false;
        } else if (actionMasked == 1) {
            int findPointerIndex = motionEvent.findPointerIndex(this.S);
            if (findPointerIndex < 0) {
                Log.e(s, "Got ACTION_UP event but don't have an active pointer id.");
                return false;
            }
            if (this.R) {
                float y2 = (motionEvent.getY(findPointerIndex) - this.P) * x;
                this.R = false;
                c(y2);
            }
            this.S = -1;
            return false;
        } else if (actionMasked == 2) {
            int findPointerIndex2 = motionEvent.findPointerIndex(this.S);
            if (findPointerIndex2 < 0) {
                Log.e(s, "Got ACTION_MOVE event but have an invalid active pointer id.");
                return false;
            }
            float y3 = motionEvent.getY(findPointerIndex2);
            d(y3);
            if (this.R) {
                float f = (y3 - this.P) * x;
                if (f <= 0.0f) {
                    return false;
                }
                b(f);
            }
        } else if (actionMasked == 3) {
            return false;
        } else {
            if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                if (actionIndex < 0) {
                    Log.e(s, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                    return false;
                }
                this.S = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                a(motionEvent);
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
        if (Build.VERSION.SDK_INT >= 21 || !(this.F instanceof AbsListView)) {
            View view = this.F;
            if (view == null || ab.X(view)) {
                super.requestDisallowInterceptTouchEvent(z2);
            }
        }
    }

    void setAnimationProgress(float f) {
        this.j.setScaleX(f);
        this.j.setScaleY(f);
    }

    @Deprecated
    public void setColorScheme(@android.support.annotation.m int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(@android.support.annotation.k int... iArr) {
        g();
        this.p.a(iArr);
    }

    public void setColorSchemeResources(@android.support.annotation.m int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = android.support.v4.content.c.getColor(context, iArr[i]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i) {
        this.H = i;
    }

    @Override // android.view.View
    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        if (z2) {
            return;
        }
        a();
    }

    @Override // android.view.View, android.support.v4.view.o
    public void setNestedScrollingEnabled(boolean z2) {
        this.K.a(z2);
    }

    public void setOnChildScrollUpCallback(@ag a aVar) {
        this.ag = aVar;
    }

    public void setOnRefreshListener(@ag b bVar) {
        this.f = bVar;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i) {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeColor(@android.support.annotation.k int i) {
        this.j.setBackgroundColor(i);
    }

    public void setProgressBackgroundColorSchemeResource(@android.support.annotation.m int i) {
        setProgressBackgroundColorSchemeColor(android.support.v4.content.c.getColor(getContext(), i));
    }

    public void setRefreshing(boolean z2) {
        if (!z2 || this.g == z2) {
            a(z2, false);
            return;
        }
        this.g = z2;
        setTargetOffsetTopAndBottom((!this.r ? this.n + this.m : this.n) - this.h);
        this.q = false;
        b(this.ah);
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            this.af = (int) (getResources().getDisplayMetrics().density * (i == 0 ? 56.0f : 40.0f));
            this.j.setImageDrawable(null);
            this.p.a(i);
            this.j.setImageDrawable(this.p);
        }
    }

    public void setSlingshotDistance(@ai int i) {
        this.o = i;
    }

    void setTargetOffsetTopAndBottom(int i) {
        this.j.bringToFront();
        ab.m((View) this.j, i);
        this.h = this.j.getTop();
    }

    @Override // android.view.View, android.support.v4.view.o
    public boolean startNestedScroll(int i) {
        return this.K.b(i);
    }

    @Override // android.view.View, android.support.v4.view.o
    public void stopNestedScroll() {
        this.K.c();
    }
}
