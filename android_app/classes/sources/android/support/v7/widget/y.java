package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class y {
    static final String a = "LayoutState";
    static final int b = -1;
    static final int c = 1;
    static final int d = Integer.MIN_VALUE;
    static final int e = -1;
    static final int f = 1;
    int h;
    int i;
    int j;
    int k;
    boolean n;
    boolean o;
    boolean g = true;
    int l = 0;
    int m = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View a(RecyclerView.p pVar) {
        View c2 = pVar.c(this.i);
        this.i += this.j;
        return c2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(RecyclerView.u uVar) {
        int i = this.i;
        return i >= 0 && i < uVar.i();
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.h + ", mCurrentPosition=" + this.i + ", mItemDirection=" + this.j + ", mLayoutDirection=" + this.k + ", mStartLine=" + this.l + ", mEndLine=" + this.m + '}';
    }
}
