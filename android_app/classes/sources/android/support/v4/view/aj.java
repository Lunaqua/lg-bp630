package android.support.v4.view;

import android.os.Build;
import android.view.View;
import android.view.Window;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class aj {
    public static final int a = 8;
    public static final int b = 9;
    public static final int c = 10;

    private aj() {
    }

    @android.support.annotation.af
    public static <T extends View> T a(@android.support.annotation.af Window window, @android.support.annotation.v int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (T) window.requireViewById(i);
        }
        T t = (T) window.findViewById(i);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this Window");
    }
}
