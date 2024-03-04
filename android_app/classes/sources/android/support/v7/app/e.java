package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.aa;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.v;
import android.support.v7.app.a;
import android.support.v7.view.b;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ba;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class e {
    static final String a = "AppCompatDelegate";
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 0;
    public static final int e = -1;
    static final int f = -100;
    public static final int g = 108;
    public static final int h = 109;
    public static final int i = 10;
    private static int j = -1;

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
    }

    public static e a(Activity activity, d dVar) {
        return new AppCompatDelegateImpl(activity, activity.getWindow(), dVar);
    }

    public static e a(Dialog dialog, d dVar) {
        return new AppCompatDelegateImpl(dialog.getContext(), dialog.getWindow(), dVar);
    }

    public static e a(Context context, Window window, d dVar) {
        return new AppCompatDelegateImpl(context, window, dVar);
    }

    public static void b(boolean z) {
        ba.a(z);
    }

    public static void f(int i2) {
        if (i2 == -1 || i2 == 0 || i2 == 1 || i2 == 2) {
            j = i2;
        } else {
            Log.d(a, "setDefaultNightMode() called with an unknown mode");
        }
    }

    public static int l() {
        return j;
    }

    public static boolean m() {
        return ba.b();
    }

    @ag
    public abstract ActionBar a();

    @ag
    public abstract android.support.v7.view.b a(@af b.a aVar);

    @ag
    public abstract <T extends View> T a(@v int i2);

    public abstract View a(@ag View view, String str, @af Context context, @af AttributeSet attributeSet);

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    public abstract void a(@ag Toolbar toolbar);

    public abstract void a(View view);

    public abstract void a(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void a(@ag CharSequence charSequence);

    public abstract void a(boolean z);

    public abstract MenuInflater b();

    public abstract void b(@aa int i2);

    public abstract void b(Bundle bundle);

    public abstract void b(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void c();

    public abstract void c(Bundle bundle);

    public abstract boolean c(int i2);

    public abstract void d();

    public abstract boolean d(int i2);

    public abstract void e();

    public abstract void e(int i2);

    public abstract void f();

    public abstract void g();

    @ag
    public abstract a.InterfaceC0038a h();

    public abstract void i();

    public abstract boolean j();

    public abstract boolean k();
}
