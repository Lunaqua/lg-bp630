package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.v4.content.b.d;
import android.support.v4.content.b.g;
import android.support.v4.h.d;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class g {
    private static final String a = "TypefaceCompat";
    private static final l b;
    private static final android.support.v4.j.k<String, Typeface> c;

    static {
        b = Build.VERSION.SDK_INT >= 28 ? new k() : Build.VERSION.SDK_INT >= 26 ? new j() : (Build.VERSION.SDK_INT < 24 || !i.a()) ? Build.VERSION.SDK_INT >= 21 ? new h() : new l() : new i();
        c = new android.support.v4.j.k<>(16);
    }

    private g() {
    }

    @ag
    public static Typeface a(@af Context context, @af Resources resources, int i, String str, int i2) {
        Typeface a2 = b.a(context, resources, i, str, i2);
        if (a2 != null) {
            c.a(b(resources, i, i2), a2);
        }
        return a2;
    }

    @ag
    public static Typeface a(@af Context context, @ag CancellationSignal cancellationSignal, @af d.c[] cVarArr, int i) {
        return b.a(context, cancellationSignal, cVarArr, i);
    }

    @ag
    public static Typeface a(@af Context context, @af d.a aVar, @af Resources resources, int i, int i2, @ag g.a aVar2, @ag Handler handler, boolean z) {
        Typeface a2;
        if (aVar instanceof d.e) {
            d.e eVar = (d.e) aVar;
            boolean z2 = false;
            if (!z ? aVar2 == null : eVar.b() == 0) {
                z2 = true;
            }
            a2 = android.support.v4.h.d.a(context, eVar.a(), aVar2, handler, z2, z ? eVar.c() : -1, i2);
        } else {
            a2 = b.a(context, (d.c) aVar, resources, i2);
            if (aVar2 != null) {
                if (a2 != null) {
                    aVar2.a(a2, handler);
                } else {
                    aVar2.a(-3, handler);
                }
            }
        }
        if (a2 != null) {
            c.a(b(resources, i, i2), a2);
        }
        return a2;
    }

    @ag
    public static Typeface a(@af Resources resources, int i, int i2) {
        return c.a((android.support.v4.j.k<String, Typeface>) b(resources, i, i2));
    }

    private static String b(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }
}
