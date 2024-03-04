package com.lge.UDAP.ROAP.b;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c {
    private static ThreadLocal<b> a = new ThreadLocal<>();
    private static a b;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void a(b bVar);
    }

    public c(b bVar) {
        a.set(bVar);
    }

    public static b a() {
        return a.get();
    }

    public static void a(b bVar) {
        a.set(bVar);
        b.a(bVar);
    }

    public void a(a aVar) {
        b = aVar;
    }
}
