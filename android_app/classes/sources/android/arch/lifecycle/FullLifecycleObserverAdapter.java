package android.arch.lifecycle;

import android.arch.lifecycle.d;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class FullLifecycleObserverAdapter implements GenericLifecycleObserver {
    private final FullLifecycleObserver a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FullLifecycleObserverAdapter(FullLifecycleObserver fullLifecycleObserver) {
        this.a = fullLifecycleObserver;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void a(f fVar, d.a aVar) {
        switch (aVar) {
            case ON_CREATE:
                this.a.a(fVar);
                return;
            case ON_START:
                this.a.b(fVar);
                return;
            case ON_RESUME:
                this.a.c(fVar);
                return;
            case ON_PAUSE:
                this.a.d(fVar);
                return;
            case ON_STOP:
                this.a.e(fVar);
                return;
            case ON_DESTROY:
                this.a.f(fVar);
                return;
            case ON_ANY:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                return;
        }
    }
}
