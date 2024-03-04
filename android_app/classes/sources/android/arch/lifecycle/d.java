package android.arch.lifecycle;

import android.support.annotation.ac;
import android.support.annotation.af;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class d {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum a {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum b {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;

        public boolean a(@af b bVar) {
            return compareTo(bVar) >= 0;
        }
    }

    @ac
    @af
    public abstract b a();

    @ac
    public abstract void a(@af e eVar);

    @ac
    public abstract void b(@af e eVar);
}
