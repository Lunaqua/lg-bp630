package android.support.v4.j;

import android.support.annotation.af;
import android.support.annotation.ag;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class n<F, S> {
    @ag
    public final F a;
    @ag
    public final S b;

    public n(@ag F f, @ag S s) {
        this.a = f;
        this.b = s;
    }

    @af
    public static <A, B> n<A, B> a(@ag A a, @ag B b) {
        return new n<>(a, b);
    }

    public boolean equals(Object obj) {
        if (obj instanceof n) {
            n nVar = (n) obj;
            return m.a(nVar.a, this.a) && m.a(nVar.b, this.b);
        }
        return false;
    }

    public int hashCode() {
        F f = this.a;
        int hashCode = f == null ? 0 : f.hashCode();
        S s = this.b;
        return hashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.a) + " " + String.valueOf(this.b) + "}";
    }
}
