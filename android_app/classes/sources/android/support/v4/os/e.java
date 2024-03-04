package android.support.v4.os;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.af;
import android.support.annotation.ag;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class e {
    private e() {
    }

    public static boolean a(@af Handler handler, @af Runnable runnable, @ag Object obj, long j) {
        if (Build.VERSION.SDK_INT >= 28) {
            return handler.postDelayed(runnable, obj, j);
        }
        Message obtain = Message.obtain(handler, runnable);
        obtain.obj = obj;
        return handler.sendMessageDelayed(obtain, j);
    }
}
