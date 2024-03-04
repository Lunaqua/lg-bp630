package android.support.v4.f;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.am;
import android.support.annotation.an;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.f.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface InterfaceC0019a {
    }

    private a() {
    }

    @ag
    @am(a = "android.permission.ACCESS_NETWORK_STATE")
    public static NetworkInfo a(@af ConnectivityManager connectivityManager, @af Intent intent) {
        NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
        if (networkInfo != null) {
            return connectivityManager.getNetworkInfo(networkInfo.getType());
        }
        return null;
    }

    @am(a = "android.permission.ACCESS_NETWORK_STATE")
    public static boolean a(@af ConnectivityManager connectivityManager) {
        if (Build.VERSION.SDK_INT >= 16) {
            return connectivityManager.isActiveNetworkMetered();
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return true;
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            case 1:
            case 7:
            case 9:
                return false;
            case 8:
            default:
                return true;
        }
    }

    public static int b(@af ConnectivityManager connectivityManager) {
        if (Build.VERSION.SDK_INT >= 24) {
            return connectivityManager.getRestrictBackgroundStatus();
        }
        return 3;
    }
}
