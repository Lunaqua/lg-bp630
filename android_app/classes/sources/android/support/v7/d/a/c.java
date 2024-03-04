package android.support.v7.d.a;

import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.v7.d.a.a;
import android.support.v7.f.d;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.x;
import java.util.List;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class c<T, VH extends RecyclerView.x> extends RecyclerView.a<VH> {
    private final b<T> a;

    protected c(@af a<T> aVar) {
        this.a = new b<>(new android.support.v7.f.a(this), aVar);
    }

    protected c(@af d.c<T> cVar) {
        this.a = new b<>(new android.support.v7.f.a(this), new a.C0043a(cVar).a());
    }

    @Override // android.support.v7.widget.RecyclerView.a
    public int a() {
        return this.a.a().size();
    }

    protected T a(int i) {
        return this.a.a().get(i);
    }

    public void a(@ag List<T> list) {
        this.a.a(list);
    }
}
