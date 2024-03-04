package android.support.v4.view;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface o {
    boolean dispatchNestedFling(float f, float f2, boolean z);

    boolean dispatchNestedPreFling(float f, float f2);

    boolean dispatchNestedPreScroll(int i, int i2, @android.support.annotation.ag int[] iArr, @android.support.annotation.ag int[] iArr2);

    boolean dispatchNestedScroll(int i, int i2, int i3, int i4, @android.support.annotation.ag int[] iArr);

    boolean hasNestedScrollingParent();

    boolean isNestedScrollingEnabled();

    void setNestedScrollingEnabled(boolean z);

    boolean startNestedScroll(int i);

    void stopNestedScroll();
}
