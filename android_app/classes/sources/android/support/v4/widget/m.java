package android.support.v4.widget;

import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.view.View;
import android.widget.ListPopupWindow;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class m {
    private m() {
    }

    @ag
    public static View.OnTouchListener a(@af ListPopupWindow listPopupWindow, @af View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return listPopupWindow.createDragToOpenListener(view);
        }
        return null;
    }

    @Deprecated
    public static View.OnTouchListener a(Object obj, View view) {
        return a((ListPopupWindow) obj, view);
    }
}
