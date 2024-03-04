package android.support.v4.content.a;

import android.content.pm.PackageInfo;
import android.os.Build;
import android.support.annotation.af;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class b {
    private b() {
    }

    public static long a(@af PackageInfo packageInfo) {
        return Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
    }
}
