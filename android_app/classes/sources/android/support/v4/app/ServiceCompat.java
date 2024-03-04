package android.support.v4.app;

import android.app.Service;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.an;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class ServiceCompat {
    public static final int START_STICKY = 1;
    public static final int STOP_FOREGROUND_DETACH = 2;
    public static final int STOP_FOREGROUND_REMOVE = 1;

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface StopForegroundFlags {
    }

    private ServiceCompat() {
    }

    public static void stopForeground(@af Service service, int i) {
        if (Build.VERSION.SDK_INT >= 24) {
            service.stopForeground(i);
        } else {
            service.stopForeground((i & 1) != 0);
        }
    }
}
