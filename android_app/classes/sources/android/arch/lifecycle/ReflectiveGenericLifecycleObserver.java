package android.arch.lifecycle;

import android.arch.lifecycle.a;
import android.arch.lifecycle.d;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ReflectiveGenericLifecycleObserver implements GenericLifecycleObserver {
    private final Object a;
    private final a.C0001a b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.a = obj;
        this.b = a.a.b(this.a.getClass());
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void a(f fVar, d.a aVar) {
        this.b.a(fVar, aVar, this.a);
    }
}
