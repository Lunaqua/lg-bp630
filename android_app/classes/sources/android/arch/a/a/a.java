package android.arch.a.a;

import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import java.util.concurrent.Executor;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a extends c {
    private static volatile a a;
    @af
    private static final Executor d = new Executor() { // from class: android.arch.a.a.a.1
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.a().b(runnable);
        }
    };
    @af
    private static final Executor e = new Executor() { // from class: android.arch.a.a.a.2
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.a().a(runnable);
        }
    };
    @af
    private c c = new b();
    @af
    private c b = this.c;

    private a() {
    }

    @af
    public static a a() {
        if (a != null) {
            return a;
        }
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
        }
        return a;
    }

    @af
    public static Executor b() {
        return d;
    }

    @af
    public static Executor c() {
        return e;
    }

    public void a(@ag c cVar) {
        if (cVar == null) {
            cVar = this.c;
        }
        this.b = cVar;
    }

    @Override // android.arch.a.a.c
    public void a(Runnable runnable) {
        this.b.a(runnable);
    }

    @Override // android.arch.a.a.c
    public void b(Runnable runnable) {
        this.b.b(runnable);
    }

    @Override // android.arch.a.a.c
    public boolean d() {
        return this.b.d();
    }
}
