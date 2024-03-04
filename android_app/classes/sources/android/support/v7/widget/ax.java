package android.support.v7.widget;

import android.os.Build;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ax {
    private ax() {
    }

    public static void a(@android.support.annotation.af View view, @android.support.annotation.ag CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
        } else {
            ay.a(view, charSequence);
        }
    }
}
