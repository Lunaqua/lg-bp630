package android.arch.lifecycle;

import android.arch.lifecycle.d;
import android.support.annotation.an;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {
    private final c[] a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.a = cVarArr;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void a(f fVar, d.a aVar) {
        k kVar = new k();
        for (c cVar : this.a) {
            cVar.a(fVar, aVar, false, kVar);
        }
        for (c cVar2 : this.a) {
            cVar2.a(fVar, aVar, true, kVar);
        }
    }
}
