package android.support.v4.view;

import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface r {
    int getNestedScrollAxes();

    boolean onNestedFling(@android.support.annotation.af View view, float f, float f2, boolean z);

    boolean onNestedPreFling(@android.support.annotation.af View view, float f, float f2);

    void onNestedPreScroll(@android.support.annotation.af View view, int i, int i2, @android.support.annotation.af int[] iArr);

    void onNestedScroll(@android.support.annotation.af View view, int i, int i2, int i3, int i4);

    void onNestedScrollAccepted(@android.support.annotation.af View view, @android.support.annotation.af View view2, int i);

    boolean onStartNestedScroll(@android.support.annotation.af View view, @android.support.annotation.af View view2, int i);

    void onStopNestedScroll(@android.support.annotation.af View view);
}
