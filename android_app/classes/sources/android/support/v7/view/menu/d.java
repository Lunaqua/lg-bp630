package android.support.v7.view.menu;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class d<T> {
    final T b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.b = t;
    }

    public T b() {
        return this.b;
    }
}
