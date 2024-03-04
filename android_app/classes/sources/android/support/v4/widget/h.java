package android.support.v4.widget;

import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.v4.j.p;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@an(a = {an.a.LIBRARY})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class h<T> {
    private final p.a<ArrayList<T>> a = new p.b(10);
    private final android.support.v4.j.r<T, ArrayList<T>> b = new android.support.v4.j.r<>();
    private final ArrayList<T> c = new ArrayList<>();
    private final HashSet<T> d = new HashSet<>();

    private void a(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (arrayList.contains(t)) {
            return;
        }
        if (hashSet.contains(t)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(t);
        ArrayList<T> arrayList2 = this.b.get(t);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                a(arrayList2.get(i), arrayList, hashSet);
            }
        }
        hashSet.remove(t);
        arrayList.add(t);
    }

    private void a(@af ArrayList<T> arrayList) {
        arrayList.clear();
        this.a.a(arrayList);
    }

    @af
    private ArrayList<T> d() {
        ArrayList<T> a = this.a.a();
        return a == null ? new ArrayList<>() : a;
    }

    public void a() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ArrayList<T> c = this.b.c(i);
            if (c != null) {
                a((ArrayList) c);
            }
        }
        this.b.clear();
    }

    public void a(@af T t) {
        if (this.b.containsKey(t)) {
            return;
        }
        this.b.put(t, null);
    }

    public void a(@af T t, @af T t2) {
        if (!this.b.containsKey(t) || !this.b.containsKey(t2)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList<T> arrayList = this.b.get(t);
        if (arrayList == null) {
            arrayList = d();
            this.b.put(t, arrayList);
        }
        arrayList.add(t2);
    }

    @af
    public ArrayList<T> b() {
        this.c.clear();
        this.d.clear();
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            a(this.b.b(i), this.c, this.d);
        }
        return this.c;
    }

    public boolean b(@af T t) {
        return this.b.containsKey(t);
    }

    int c() {
        return this.b.size();
    }

    @ag
    public List c(@af T t) {
        return this.b.get(t);
    }

    @ag
    public List<T> d(@af T t) {
        int size = this.b.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            ArrayList<T> c = this.b.c(i);
            if (c != null && c.contains(t)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.b.b(i));
            }
        }
        return arrayList;
    }

    public boolean e(@af T t) {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ArrayList<T> c = this.b.c(i);
            if (c != null && c.contains(t)) {
                return true;
            }
        }
        return false;
    }
}
