package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.an;
import android.support.v7.view.menu.p;
import android.util.SparseArray;
import android.view.Menu;
import android.view.Window;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface o {
    void a(int i);

    void a(SparseArray<Parcelable> sparseArray);

    void a(Menu menu, p.a aVar);

    void b(SparseArray<Parcelable> sparseArray);

    boolean f();

    boolean g();

    CharSequence getTitle();

    boolean h();

    boolean i();

    boolean j();

    boolean k();

    boolean l();

    void m();

    void n();

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setLogo(int i);

    void setUiOptions(int i);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);
}
