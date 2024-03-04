package android.support.v4.widget;

import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.view.View;
import android.widget.PopupMenu;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class p {
    private p() {
    }

    @ag
    public static View.OnTouchListener a(@af Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            return ((PopupMenu) obj).getDragToOpenListener();
        }
        return null;
    }
}
