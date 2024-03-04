package android.support.v4.content.a;

import android.annotation.SuppressLint;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.an;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class c {

    @an(a = {an.a.LIBRARY})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
    }

    @an(a = {an.a.LIBRARY})
    @SuppressLint({"UniqueConstants"})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface b {
    }

    private c() {
    }

    @SuppressLint({"WrongConstant"})
    public static int a(@af PermissionInfo permissionInfo) {
        return Build.VERSION.SDK_INT >= 28 ? permissionInfo.getProtection() : permissionInfo.protectionLevel & 15;
    }

    @SuppressLint({"WrongConstant"})
    public static int b(@af PermissionInfo permissionInfo) {
        return Build.VERSION.SDK_INT >= 28 ? permissionInfo.getProtectionFlags() : permissionInfo.protectionLevel & (-16);
    }
}
