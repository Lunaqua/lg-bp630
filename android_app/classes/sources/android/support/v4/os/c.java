package android.support.v4.os;

import android.content.res.Configuration;
import android.os.Build;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class c {
    private c() {
    }

    public static h a(Configuration configuration) {
        return Build.VERSION.SDK_INT >= 24 ? h.a((Object) configuration.getLocales()) : h.a(configuration.locale);
    }
}
