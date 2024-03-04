package com.lge.UDAP.ROAP.d;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b {
    private static ThreadLocal<com.lge.UDAP.ROAP.d.a> a = new ThreadLocal<>();
    private static a b;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void a(com.lge.UDAP.ROAP.d.a aVar);
    }

    public b(com.lge.UDAP.ROAP.d.a aVar) {
        a.set(aVar);
    }

    public static com.lge.UDAP.ROAP.d.a a() {
        return a.get();
    }

    public static void a(com.lge.UDAP.ROAP.d.a aVar) {
        a.set(aVar);
        b.a(aVar);
    }

    public void a(a aVar) {
        b = aVar;
    }
}
