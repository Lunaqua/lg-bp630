package android.support.v7.widget.b;

import android.support.v7.f.g;
import android.support.v7.widget.RecyclerView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class a<T2> extends g.b<T2> {
    final RecyclerView.a a;

    public a(RecyclerView.a aVar) {
        this.a = aVar;
    }

    @Override // android.support.v7.f.e
    public void a(int i, int i2) {
        this.a.c(i, i2);
    }

    @Override // android.support.v7.f.g.b, android.support.v7.f.e
    public void a(int i, int i2, Object obj) {
        this.a.a(i, i2, obj);
    }

    @Override // android.support.v7.f.e
    public void b(int i, int i2) {
        this.a.d(i, i2);
    }

    @Override // android.support.v7.f.e
    public void c(int i, int i2) {
        this.a.b(i, i2);
    }

    @Override // android.support.v7.f.g.b
    public void d(int i, int i2) {
        this.a.a(i, i2);
    }
}
