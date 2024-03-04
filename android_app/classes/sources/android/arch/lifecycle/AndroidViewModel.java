package android.arch.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.annotation.af;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AndroidViewModel extends r {
    @SuppressLint({"StaticFieldLeak"})
    private Application a;

    public AndroidViewModel(@af Application application) {
        this.a = application;
    }

    @af
    public <T extends Application> T a() {
        return (T) this.a;
    }
}
