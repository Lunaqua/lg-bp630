package android.arch.a.b;

import android.arch.a.b.b;
import android.support.annotation.af;
import android.support.annotation.an;
import java.util.HashMap;
import java.util.Map;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a<K, V> extends b<K, V> {
    private HashMap<K, b.c<K, V>> a = new HashMap<>();

    @Override // android.arch.a.b.b
    protected b.c<K, V> a(K k) {
        return this.a.get(k);
    }

    @Override // android.arch.a.b.b
    public V a(@af K k, @af V v) {
        b.c<K, V> a = a((a<K, V>) k);
        if (a != null) {
            return a.b;
        }
        this.a.put(k, b(k, v));
        return null;
    }

    @Override // android.arch.a.b.b
    public V b(@af K k) {
        V v = (V) super.b(k);
        this.a.remove(k);
        return v;
    }

    public boolean c(K k) {
        return this.a.containsKey(k);
    }

    public Map.Entry<K, V> d(K k) {
        if (c(k)) {
            return this.a.get(k).d;
        }
        return null;
    }
}
