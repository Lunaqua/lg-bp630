package android.support.r.a;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    private Object a;

    private a(Object obj) {
        this.a = obj;
    }

    @ag
    @an(a = {an.a.LIBRARY_GROUP})
    public static a a(Activity activity, DragEvent dragEvent) {
        DragAndDropPermissions requestDragAndDropPermissions;
        if (Build.VERSION.SDK_INT < 24 || (requestDragAndDropPermissions = activity.requestDragAndDropPermissions(dragEvent)) == null) {
            return null;
        }
        return new a(requestDragAndDropPermissions);
    }

    public void a() {
        if (Build.VERSION.SDK_INT >= 24) {
            ((DragAndDropPermissions) this.a).release();
        }
    }
}
