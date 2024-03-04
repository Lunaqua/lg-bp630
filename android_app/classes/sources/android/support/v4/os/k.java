package android.support.v4.os;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class k extends RuntimeException {
    public k() {
        this(null);
    }

    public k(String str) {
        super(str == null ? "The operation has been canceled." : str);
    }
}
