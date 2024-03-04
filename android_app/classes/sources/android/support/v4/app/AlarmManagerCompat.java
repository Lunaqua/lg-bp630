package android.support.v4.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build;
import android.support.annotation.af;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class AlarmManagerCompat {
    private AlarmManagerCompat() {
    }

    public static void setAlarmClock(@af AlarmManager alarmManager, long j, @af PendingIntent pendingIntent, @af PendingIntent pendingIntent2) {
        if (Build.VERSION.SDK_INT >= 21) {
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(j, pendingIntent), pendingIntent2);
        } else {
            setExact(alarmManager, 0, j, pendingIntent2);
        }
    }

    public static void setAndAllowWhileIdle(@af AlarmManager alarmManager, int i, long j, @af PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setAndAllowWhileIdle(i, j, pendingIntent);
        } else {
            alarmManager.set(i, j, pendingIntent);
        }
    }

    public static void setExact(@af AlarmManager alarmManager, int i, long j, @af PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(i, j, pendingIntent);
        } else {
            alarmManager.set(i, j, pendingIntent);
        }
    }

    public static void setExactAndAllowWhileIdle(@af AlarmManager alarmManager, int i, long j, @af PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(i, j, pendingIntent);
        } else {
            setExact(alarmManager, i, j, pendingIntent);
        }
    }
}
