package android.arch.a.a;

import android.support.annotation.af;
import android.support.annotation.an;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class c {
    public abstract void a(@af Runnable runnable);

    public abstract void b(@af Runnable runnable);

    public void c(@af Runnable runnable) {
        if (d()) {
            runnable.run();
        } else {
            b(runnable);
        }
    }

    public abstract boolean d();
}
