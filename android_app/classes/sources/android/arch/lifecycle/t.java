package android.arch.lifecycle;

import java.util.HashMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class t {
    private final HashMap<String, r> a = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final r a(String str) {
        return this.a.get(str);
    }

    public final void a() {
        for (r rVar : this.a.values()) {
            rVar.onCleared();
        }
        this.a.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, r rVar) {
        r put = this.a.put(str, rVar);
        if (put != null) {
            put.onCleared();
        }
    }
}
