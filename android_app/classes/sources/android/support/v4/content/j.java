package android.support.v4.content;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.v4.app.AppOpsManagerCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class j {
    public static final int a = 0;
    public static final int b = -1;
    public static final int c = -2;

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
    }

    private j() {
    }

    public static int a(@af Context context, @af String str) {
        return a(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }

    public static int a(@af Context context, @af String str, int i, int i2, @ag String str2) {
        if (context.checkPermission(str, i, i2) == -1) {
            return -1;
        }
        String permissionToOp = AppOpsManagerCompat.permissionToOp(str);
        if (permissionToOp == null) {
            return 0;
        }
        if (str2 == null) {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i2);
            if (packagesForUid == null || packagesForUid.length <= 0) {
                return -1;
            }
            str2 = packagesForUid[0];
        }
        return AppOpsManagerCompat.noteProxyOpNoThrow(context, permissionToOp, str2) != 0 ? -2 : 0;
    }

    public static int a(@af Context context, @af String str, @ag String str2) {
        if (Binder.getCallingPid() == Process.myPid()) {
            return -1;
        }
        return a(context, str, Binder.getCallingPid(), Binder.getCallingUid(), str2);
    }

    public static int b(@af Context context, @af String str) {
        return a(context, str, Binder.getCallingPid(), Binder.getCallingUid(), Binder.getCallingPid() == Process.myPid() ? context.getPackageName() : null);
    }
}
