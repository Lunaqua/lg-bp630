package android.support.v4.content;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.af;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class e {
    public static final String a = "android.intent.extra.HTML_TEXT";
    public static final String b = "android.intent.extra.START_PLAYBACK";
    public static final String c = "android.intent.category.LEANBACK_LAUNCHER";

    private e() {
    }

    @af
    public static Intent a(@af String str, @af String str2) {
        if (Build.VERSION.SDK_INT >= 15) {
            return Intent.makeMainSelectorActivity(str, str2);
        }
        Intent intent = new Intent(str);
        intent.addCategory(str2);
        return intent;
    }
}
