package com.lge.media.launcher.sp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.lge.UDAP.ROAP.b;
import com.lge.media.launcher.control.common.e;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class WatchSoundPrivacy extends BroadcastReceiver {
    public e c;
    final int a = 0;
    final int b = 1;
    public b d = null;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int i = intent.getExtras().getInt("state");
        this.c = e.a(context, (Handler) null);
        this.d = this.c.p();
        if (1 == i) {
            com.lge.media.launcher.control.common.a.c("headset connected");
        }
    }
}
