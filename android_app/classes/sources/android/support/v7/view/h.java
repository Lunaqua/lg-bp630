package android.support.v7.view;

import android.support.annotation.an;
import android.support.v4.view.af;
import android.support.v4.view.ag;
import android.support.v4.view.ah;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class h {
    ag b;
    private Interpolator d;
    private boolean e;
    private long c = -1;
    private final ah f = new ah() { // from class: android.support.v7.view.h.1
        private boolean b = false;
        private int c = 0;

        void a() {
            this.c = 0;
            this.b = false;
            h.this.b();
        }

        @Override // android.support.v4.view.ah, android.support.v4.view.ag
        public void a(View view) {
            if (this.b) {
                return;
            }
            this.b = true;
            if (h.this.b != null) {
                h.this.b.a(null);
            }
        }

        @Override // android.support.v4.view.ah, android.support.v4.view.ag
        public void b(View view) {
            int i = this.c + 1;
            this.c = i;
            if (i == h.this.a.size()) {
                if (h.this.b != null) {
                    h.this.b.b(null);
                }
                a();
            }
        }
    };
    final ArrayList<af> a = new ArrayList<>();

    public h a(long j) {
        if (!this.e) {
            this.c = j;
        }
        return this;
    }

    public h a(af afVar) {
        if (!this.e) {
            this.a.add(afVar);
        }
        return this;
    }

    public h a(af afVar, af afVar2) {
        this.a.add(afVar);
        afVar2.b(afVar.a());
        this.a.add(afVar2);
        return this;
    }

    public h a(ag agVar) {
        if (!this.e) {
            this.b = agVar;
        }
        return this;
    }

    public h a(Interpolator interpolator) {
        if (!this.e) {
            this.d = interpolator;
        }
        return this;
    }

    public void a() {
        if (this.e) {
            return;
        }
        Iterator<af> it = this.a.iterator();
        while (it.hasNext()) {
            af next = it.next();
            long j = this.c;
            if (j >= 0) {
                next.a(j);
            }
            Interpolator interpolator = this.d;
            if (interpolator != null) {
                next.a(interpolator);
            }
            if (this.b != null) {
                next.a(this.f);
            }
            next.e();
        }
        this.e = true;
    }

    void b() {
        this.e = false;
    }

    public void c() {
        if (this.e) {
            Iterator<af> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().d();
            }
            this.e = false;
        }
    }
}
