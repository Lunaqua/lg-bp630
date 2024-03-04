package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class ak {
    private ak() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(RecyclerView.u uVar, ae aeVar, View view, View view2, RecyclerView.i iVar, boolean z) {
        if (iVar.G() == 0 || uVar.i() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (z) {
            return Math.min(aeVar.g(), aeVar.b(view2) - aeVar.a(view));
        }
        return Math.abs(iVar.e(view) - iVar.e(view2)) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(RecyclerView.u uVar, ae aeVar, View view, View view2, RecyclerView.i iVar, boolean z, boolean z2) {
        if (iVar.G() == 0 || uVar.i() == 0 || view == null || view2 == null) {
            return 0;
        }
        int max = z2 ? Math.max(0, (uVar.i() - Math.max(iVar.e(view), iVar.e(view2))) - 1) : Math.max(0, Math.min(iVar.e(view), iVar.e(view2)));
        if (z) {
            return Math.round((max * (Math.abs(aeVar.b(view2) - aeVar.a(view)) / (Math.abs(iVar.e(view) - iVar.e(view2)) + 1))) + (aeVar.d() - aeVar.a(view)));
        }
        return max;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(RecyclerView.u uVar, ae aeVar, View view, View view2, RecyclerView.i iVar, boolean z) {
        if (iVar.G() == 0 || uVar.i() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (z) {
            return (int) (((aeVar.b(view2) - aeVar.a(view)) / (Math.abs(iVar.e(view) - iVar.e(view2)) + 1)) * uVar.i());
        }
        return uVar.i();
    }
}
