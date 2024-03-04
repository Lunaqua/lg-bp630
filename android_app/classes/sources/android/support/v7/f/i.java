package android.support.v7.f;

import android.util.SparseArray;
import java.lang.reflect.Array;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class i<T> {
    final int a;
    a<T> b;
    private final SparseArray<a<T>> c = new SparseArray<>(10);

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a<T> {
        public final T[] a;
        public int b;
        public int c;
        a<T> d;

        public a(Class<T> cls, int i) {
            this.a = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i));
        }

        boolean a(int i) {
            int i2 = this.b;
            return i2 <= i && i < i2 + this.c;
        }

        T b(int i) {
            return this.a[i - this.b];
        }
    }

    public i(int i) {
        this.a = i;
    }

    public int a() {
        return this.c.size();
    }

    public a<T> a(a<T> aVar) {
        int indexOfKey = this.c.indexOfKey(aVar.b);
        if (indexOfKey < 0) {
            this.c.put(aVar.b, aVar);
            return null;
        }
        a<T> valueAt = this.c.valueAt(indexOfKey);
        this.c.setValueAt(indexOfKey, aVar);
        if (this.b == valueAt) {
            this.b = aVar;
        }
        return valueAt;
    }

    public T a(int i) {
        a<T> aVar = this.b;
        if (aVar == null || !aVar.a(i)) {
            int indexOfKey = this.c.indexOfKey(i - (i % this.a));
            if (indexOfKey < 0) {
                return null;
            }
            this.b = this.c.valueAt(indexOfKey);
        }
        return this.b.b(i);
    }

    public a<T> b(int i) {
        return this.c.valueAt(i);
    }

    public void b() {
        this.c.clear();
    }

    public a<T> c(int i) {
        a<T> aVar = this.c.get(i);
        if (this.b == aVar) {
            this.b = null;
        }
        this.c.delete(i);
        return aVar;
    }
}
