package android.support.v7.view.menu;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.an;
import android.view.ViewGroup;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface p {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void a(h hVar, boolean z);

        boolean a(h hVar);
    }

    q a(ViewGroup viewGroup);

    void a(Context context, h hVar);

    void a(Parcelable parcelable);

    void a(h hVar, boolean z);

    void a(a aVar);

    boolean a(h hVar, k kVar);

    boolean a(v vVar);

    void b(boolean z);

    boolean b();

    boolean b(h hVar, k kVar);

    int c();

    Parcelable f();
}
