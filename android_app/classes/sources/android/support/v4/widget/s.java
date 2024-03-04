package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
@Deprecated
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class s {
    OverScroller a;

    s(Context context, Interpolator interpolator) {
        this.a = interpolator != null ? new OverScroller(context, interpolator) : new OverScroller(context);
    }

    @Deprecated
    public static s a(Context context) {
        return a(context, null);
    }

    @Deprecated
    public static s a(Context context, Interpolator interpolator) {
        return new s(context, interpolator);
    }

    @Deprecated
    public void a(int i, int i2, int i3) {
        this.a.notifyHorizontalEdgeReached(i, i2, i3);
    }

    @Deprecated
    public void a(int i, int i2, int i3, int i4) {
        this.a.startScroll(i, i2, i3, i4);
    }

    @Deprecated
    public void a(int i, int i2, int i3, int i4, int i5) {
        this.a.startScroll(i, i2, i3, i4, i5);
    }

    @Deprecated
    public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.a.fling(i, i2, i3, i4, i5, i6, i7, i8);
    }

    @Deprecated
    public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.a.fling(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    @Deprecated
    public boolean a() {
        return this.a.isFinished();
    }

    @Deprecated
    public boolean a(int i, int i2, int i3, int i4, int i5, int i6) {
        return this.a.springBack(i, i2, i3, i4, i5, i6);
    }

    @Deprecated
    public int b() {
        return this.a.getCurrX();
    }

    @Deprecated
    public void b(int i, int i2, int i3) {
        this.a.notifyVerticalEdgeReached(i, i2, i3);
    }

    @Deprecated
    public int c() {
        return this.a.getCurrY();
    }

    @Deprecated
    public int d() {
        return this.a.getFinalX();
    }

    @Deprecated
    public int e() {
        return this.a.getFinalY();
    }

    @Deprecated
    public float f() {
        return this.a.getCurrVelocity();
    }

    @Deprecated
    public boolean g() {
        return this.a.computeScrollOffset();
    }

    @Deprecated
    public void h() {
        this.a.abortAnimation();
    }

    @Deprecated
    public boolean i() {
        return this.a.isOverScrolled();
    }
}
