package android.arch.lifecycle;

import android.support.annotation.ac;
import android.support.annotation.af;
import android.support.annotation.ag;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class q {
    private q() {
    }

    @ac
    public static <X, Y> LiveData<Y> a(@af LiveData<X> liveData, @af final android.arch.a.c.a<X, Y> aVar) {
        final j jVar = new j();
        jVar.a(liveData, new m<X>() { // from class: android.arch.lifecycle.q.1
            @Override // android.arch.lifecycle.m
            public void onChanged(@ag X x) {
                j.this.setValue(aVar.a(x));
            }
        });
        return jVar;
    }

    @ac
    public static <X, Y> LiveData<Y> b(@af LiveData<X> liveData, @af final android.arch.a.c.a<X, LiveData<Y>> aVar) {
        final j jVar = new j();
        jVar.a(liveData, new m<X>() { // from class: android.arch.lifecycle.q.2
            LiveData<Y> a;

            @Override // android.arch.lifecycle.m
            public void onChanged(@ag X x) {
                LiveData<Y> liveData2 = (LiveData) android.arch.a.c.a.this.a(x);
                Object obj = this.a;
                if (obj == liveData2) {
                    return;
                }
                if (obj != null) {
                    jVar.a(obj);
                }
                this.a = liveData2;
                Object obj2 = this.a;
                if (obj2 != null) {
                    jVar.a(obj2, new m<Y>() { // from class: android.arch.lifecycle.q.2.1
                        @Override // android.arch.lifecycle.m
                        public void onChanged(@ag Y y) {
                            jVar.setValue(y);
                        }
                    });
                }
            }
        });
        return jVar;
    }
}
