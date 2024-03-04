package android.arch.a.a;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ag;
import android.support.annotation.an;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b extends c {
    private final Object a = new Object();
    private ExecutorService b = Executors.newFixedThreadPool(2);
    @ag
    private volatile Handler c;

    @Override // android.arch.a.a.c
    public void a(Runnable runnable) {
        this.b.execute(runnable);
    }

    @Override // android.arch.a.a.c
    public void b(Runnable runnable) {
        if (this.c == null) {
            synchronized (this.a) {
                if (this.c == null) {
                    this.c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.c.post(runnable);
    }

    @Override // android.arch.a.a.c
    public boolean d() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
