package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class t {
    private final ViewGroup a;
    private int b;

    public t(@android.support.annotation.af ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    public int a() {
        return this.b;
    }

    public void a(@android.support.annotation.af View view) {
        a(view, 0);
    }

    public void a(@android.support.annotation.af View view, int i) {
        this.b = 0;
    }

    public void a(@android.support.annotation.af View view, @android.support.annotation.af View view2, int i) {
        a(view, view2, i, 0);
    }

    public void a(@android.support.annotation.af View view, @android.support.annotation.af View view2, int i, int i2) {
        this.b = i;
    }
}
