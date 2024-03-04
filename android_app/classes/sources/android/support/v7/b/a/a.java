package android.support.v7.b.a;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.m;
import android.support.annotation.p;
import android.support.v4.content.c;
import android.support.v7.widget.g;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    private static final String a = "AppCompatResources";
    private static final ThreadLocal<TypedValue> b = new ThreadLocal<>();
    private static final WeakHashMap<Context, SparseArray<C0039a>> c = new WeakHashMap<>(0);
    private static final Object d = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: android.support.v7.b.a.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class C0039a {
        final ColorStateList a;
        final Configuration b;

        C0039a(@af ColorStateList colorStateList, @af Configuration configuration) {
            this.a = colorStateList;
            this.b = configuration;
        }
    }

    private a() {
    }

    public static ColorStateList a(@af Context context, @m int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        ColorStateList d2 = d(context, i);
        if (d2 != null) {
            return d2;
        }
        ColorStateList c2 = c(context, i);
        if (c2 != null) {
            a(context, i, c2);
            return c2;
        }
        return c.getColorStateList(context, i);
    }

    @af
    private static TypedValue a() {
        TypedValue typedValue = b.get();
        if (typedValue == null) {
            TypedValue typedValue2 = new TypedValue();
            b.set(typedValue2);
            return typedValue2;
        }
        return typedValue;
    }

    private static void a(@af Context context, @m int i, @af ColorStateList colorStateList) {
        synchronized (d) {
            SparseArray<C0039a> sparseArray = c.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                c.put(context, sparseArray);
            }
            sparseArray.append(i, new C0039a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    @ag
    public static Drawable b(@af Context context, @p int i) {
        return g.a().a(context, i);
    }

    @ag
    private static ColorStateList c(Context context, int i) {
        if (e(context, i)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return android.support.v4.content.b.a.a(resources, resources.getXml(i), context.getTheme());
        } catch (Exception e) {
            Log.e(a, "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    @ag
    private static ColorStateList d(@af Context context, @m int i) {
        C0039a c0039a;
        synchronized (d) {
            SparseArray<C0039a> sparseArray = c.get(context);
            if (sparseArray != null && sparseArray.size() > 0 && (c0039a = sparseArray.get(i)) != null) {
                if (c0039a.b.equals(context.getResources().getConfiguration())) {
                    return c0039a.a;
                }
                sparseArray.remove(i);
            }
            return null;
        }
    }

    private static boolean e(@af Context context, @m int i) {
        Resources resources = context.getResources();
        TypedValue a2 = a();
        resources.getValue(i, a2, true);
        return a2.type >= 28 && a2.type <= 31;
    }
}
