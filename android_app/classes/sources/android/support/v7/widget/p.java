package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.an;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.p;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface p {
    Menu A();

    android.support.v4.view.af a(int i, long j);

    ViewGroup a();

    void a(int i);

    void a(Drawable drawable);

    void a(p.a aVar, h.a aVar2);

    void a(al alVar);

    void a(SparseArray<Parcelable> sparseArray);

    void a(Menu menu, p.a aVar);

    void a(View view);

    void a(Window.Callback callback);

    void a(SpinnerAdapter spinnerAdapter, AdapterView.OnItemSelectedListener onItemSelectedListener);

    void a(CharSequence charSequence);

    void a(boolean z);

    Context b();

    void b(int i);

    void b(Drawable drawable);

    void b(SparseArray<Parcelable> sparseArray);

    void b(CharSequence charSequence);

    void b(boolean z);

    void c(int i);

    void c(Drawable drawable);

    void c(CharSequence charSequence);

    boolean c();

    void d();

    void d(int i);

    void d(Drawable drawable);

    void d(CharSequence charSequence);

    CharSequence e();

    void e(int i);

    void e(Drawable drawable);

    CharSequence f();

    void f(int i);

    void g();

    void g(int i);

    void h();

    void h(int i);

    void i(int i);

    boolean i();

    void j(int i);

    boolean j();

    boolean k();

    boolean l();

    boolean m();

    boolean n();

    boolean o();

    void p();

    void q();

    int r();

    boolean s();

    boolean t();

    int u();

    int v();

    int w();

    View x();

    int y();

    int z();
}
