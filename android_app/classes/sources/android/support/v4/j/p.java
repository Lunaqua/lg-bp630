package android.support.v4.j;

import android.support.annotation.af;
import android.support.annotation.ag;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class p {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a<T> {
        @ag
        T a();

        boolean a(@af T t);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b<T> implements a<T> {
        private final Object[] a;
        private int b;

        public b(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.a = new Object[i];
        }

        private boolean b(@af T t) {
            for (int i = 0; i < this.b; i++) {
                if (this.a[i] == t) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.support.v4.j.p.a
        public T a() {
            int i = this.b;
            if (i > 0) {
                int i2 = i - 1;
                Object[] objArr = this.a;
                T t = (T) objArr[i2];
                objArr[i2] = null;
                this.b = i - 1;
                return t;
            }
            return null;
        }

        @Override // android.support.v4.j.p.a
        public boolean a(@af T t) {
            if (b(t)) {
                throw new IllegalStateException("Already in the pool!");
            }
            int i = this.b;
            Object[] objArr = this.a;
            if (i < objArr.length) {
                objArr[i] = t;
                this.b = i + 1;
                return true;
            }
            return false;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class c<T> extends b<T> {
        private final Object a;

        public c(int i) {
            super(i);
            this.a = new Object();
        }

        @Override // android.support.v4.j.p.b, android.support.v4.j.p.a
        public T a() {
            T t;
            synchronized (this.a) {
                t = (T) super.a();
            }
            return t;
        }

        @Override // android.support.v4.j.p.b, android.support.v4.j.p.a
        public boolean a(@af T t) {
            boolean a;
            synchronized (this.a) {
                a = super.a(t);
            }
            return a;
        }
    }

    private p() {
    }
}
