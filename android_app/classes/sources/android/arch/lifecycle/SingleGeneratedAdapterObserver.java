package android.arch.lifecycle;

import android.arch.lifecycle.d;
import android.support.annotation.an;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {
    private final c a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SingleGeneratedAdapterObserver(c cVar) {
        this.a = cVar;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void a(f fVar, d.a aVar) {
        this.a.a(fVar, aVar, false, null);
        this.a.a(fVar, aVar, true, null);
    }
}
