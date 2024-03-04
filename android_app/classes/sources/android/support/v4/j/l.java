package android.support.v4.j;

import android.support.annotation.ag;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class l<K, V> {
    @ag
    l<K, V>.b b;
    @ag
    l<K, V>.c c;
    @ag
    l<K, V>.e d;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    final class a<T> implements Iterator<T> {
        final int a;
        int b;
        int c;
        boolean d = false;

        a(int i) {
            this.a = i;
            this.b = l.this.a();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.c < this.b;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T t = (T) l.this.a(this.c, this.a);
                this.c++;
                this.d = true;
                return t;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.d) {
                throw new IllegalStateException();
            }
            this.c--;
            this.b--;
            this.d = false;
            l.this.a(this.c);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    final class b implements Set<Map.Entry<K, V>> {
        b() {
        }

        @Override // java.util.Set, java.util.Collection
        /* renamed from: a */
        public boolean add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
            int a = l.this.a();
            for (Map.Entry<K, V> entry : collection) {
                l.this.a((l) entry.getKey(), (K) entry.getValue());
            }
            return a != l.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            l.this.c();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                int a = l.this.a(entry.getKey());
                if (a < 0) {
                    return false;
                }
                return g.a(l.this.a(a, 1), entry.getValue());
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return l.a((Set) this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int i = 0;
            for (int a = l.this.a() - 1; a >= 0; a--) {
                Object a2 = l.this.a(a, 0);
                Object a3 = l.this.a(a, 1);
                i += (a2 == null ? 0 : a2.hashCode()) ^ (a3 == null ? 0 : a3.hashCode());
            }
            return i;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return l.this.a() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return l.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    final class c implements Set<K> {
        c() {
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            l.this.c();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            return l.this.a(obj) >= 0;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return l.a((Map) l.this.b(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return l.a((Set) this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int i = 0;
            for (int a = l.this.a() - 1; a >= 0; a--) {
                Object a2 = l.this.a(a, 0);
                i += a2 == null ? 0 : a2.hashCode();
            }
            return i;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return l.this.a() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<K> iterator() {
            return new a(0);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            int a = l.this.a(obj);
            if (a >= 0) {
                l.this.a(a);
                return true;
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            return l.b(l.this.b(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            return l.c(l.this.b(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return l.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            return l.this.b(0);
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) l.this.a(tArr, 0);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    final class d implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
        int a;
        boolean c = false;
        int b = -1;

        d() {
            this.a = l.this.a() - 1;
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public Map.Entry<K, V> next() {
            if (hasNext()) {
                this.b++;
                this.c = true;
                return this;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (this.c) {
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    return g.a(entry.getKey(), l.this.a(this.b, 0)) && g.a(entry.getValue(), l.this.a(this.b, 1));
                }
                return false;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            if (this.c) {
                return (K) l.this.a(this.b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            if (this.c) {
                return (V) l.this.a(this.b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.b < this.a;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            if (this.c) {
                Object a = l.this.a(this.b, 0);
                Object a2 = l.this.a(this.b, 1);
                return (a == null ? 0 : a.hashCode()) ^ (a2 != null ? a2.hashCode() : 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.c) {
                throw new IllegalStateException();
            }
            l.this.a(this.b);
            this.b--;
            this.a--;
            this.c = false;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            if (this.c) {
                return (V) l.this.a(this.b, (int) v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    final class e implements Collection<V> {
        e() {
        }

        @Override // java.util.Collection
        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            l.this.c();
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return l.this.b(obj) >= 0;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return l.this.a() == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new a(1);
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            int b = l.this.b(obj);
            if (b >= 0) {
                l.this.a(b);
                return true;
            }
            return false;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            int a = l.this.a();
            int i = 0;
            boolean z = false;
            while (i < a) {
                if (collection.contains(l.this.a(i, 1))) {
                    l.this.a(i);
                    i--;
                    a--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            int a = l.this.a();
            int i = 0;
            boolean z = false;
            while (i < a) {
                if (!collection.contains(l.this.a(i, 1))) {
                    l.this.a(i);
                    i--;
                    a--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        @Override // java.util.Collection
        public int size() {
            return l.this.a();
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return l.this.b(1);
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) l.this.a(tArr, 1);
        }
    }

    public static <K, V> boolean a(Map<K, V> map, Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!map.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean a(Set<T> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static <K, V> boolean b(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            map.remove(it.next());
        }
        return size != map.size();
    }

    public static <K, V> boolean c(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    protected abstract int a();

    protected abstract int a(Object obj);

    protected abstract Object a(int i, int i2);

    protected abstract V a(int i, V v);

    protected abstract void a(int i);

    protected abstract void a(K k, V v);

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T[] a(T[] tArr, int i) {
        int a2 = a();
        if (tArr.length < a2) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), a2));
        }
        for (int i2 = 0; i2 < a2; i2++) {
            tArr[i2] = a(i2, i);
        }
        if (tArr.length > a2) {
            tArr[a2] = null;
        }
        return tArr;
    }

    protected abstract int b(Object obj);

    protected abstract Map<K, V> b();

    public Object[] b(int i) {
        int a2 = a();
        Object[] objArr = new Object[a2];
        for (int i2 = 0; i2 < a2; i2++) {
            objArr[i2] = a(i2, i);
        }
        return objArr;
    }

    protected abstract void c();

    public Set<Map.Entry<K, V>> d() {
        if (this.b == null) {
            this.b = new b();
        }
        return this.b;
    }

    public Set<K> e() {
        if (this.c == null) {
            this.c = new c();
        }
        return this.c;
    }

    public Collection<V> f() {
        if (this.d == null) {
            this.d = new e();
        }
        return this.d;
    }
}
