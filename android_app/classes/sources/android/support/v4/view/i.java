package android.support.v4.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.Field;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class i {
    private static final String a = "LayoutInflaterCompatHC";
    private static Field b;
    private static boolean c;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class a implements LayoutInflater.Factory2 {
        final j a;

        a(j jVar) {
            this.a = jVar;
        }

        @Override // android.view.LayoutInflater.Factory2
        public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
            return this.a.a(view, str, context, attributeSet);
        }

        @Override // android.view.LayoutInflater.Factory
        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            return this.a.a(null, str, context, attributeSet);
        }

        public String toString() {
            return getClass().getName() + "{" + this.a + "}";
        }
    }

    private i() {
    }

    @Deprecated
    public static j a(LayoutInflater layoutInflater) {
        LayoutInflater.Factory factory = layoutInflater.getFactory();
        if (factory instanceof a) {
            return ((a) factory).a;
        }
        return null;
    }

    @Deprecated
    public static void a(@android.support.annotation.af LayoutInflater layoutInflater, @android.support.annotation.af j jVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            layoutInflater.setFactory2(jVar != null ? new a(jVar) : null);
            return;
        }
        a aVar = jVar != null ? new a(jVar) : null;
        layoutInflater.setFactory2(aVar);
        LayoutInflater.Factory factory = layoutInflater.getFactory();
        if (factory instanceof LayoutInflater.Factory2) {
            b(layoutInflater, (LayoutInflater.Factory2) factory);
        } else {
            b(layoutInflater, aVar);
        }
    }

    public static void a(@android.support.annotation.af LayoutInflater layoutInflater, @android.support.annotation.af LayoutInflater.Factory2 factory2) {
        layoutInflater.setFactory2(factory2);
        if (Build.VERSION.SDK_INT < 21) {
            LayoutInflater.Factory factory = layoutInflater.getFactory();
            if (factory instanceof LayoutInflater.Factory2) {
                b(layoutInflater, (LayoutInflater.Factory2) factory);
            } else {
                b(layoutInflater, factory2);
            }
        }
    }

    private static void b(LayoutInflater layoutInflater, LayoutInflater.Factory2 factory2) {
        if (!c) {
            try {
                b = LayoutInflater.class.getDeclaredField("mFactory2");
                b.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e(a, "forceSetFactory2 Could not find field 'mFactory2' on class " + LayoutInflater.class.getName() + "; inflation may have unexpected results.", e);
            }
            c = true;
        }
        Field field = b;
        if (field != null) {
            try {
                field.set(layoutInflater, factory2);
            } catch (IllegalAccessException e2) {
                Log.e(a, "forceSetFactory2 could not set the Factory2 on LayoutInflater " + layoutInflater + "; inflation may have unexpected results.", e2);
            }
        }
    }
}
