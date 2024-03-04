package android.support.v4.os;

import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.x;
import android.support.v7.widget.RecyclerView;
import java.util.Locale;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
interface j {
    @x(a = RecyclerView.m)
    int a(Locale locale);

    Object a();

    Locale a(int i);

    @ag
    Locale a(String[] strArr);

    void a(@af Locale... localeArr);

    boolean b();

    @x(a = 0)
    int c();

    String d();

    boolean equals(Object obj);

    int hashCode();

    String toString();
}
