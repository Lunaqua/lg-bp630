package android.support.v7.widget;

import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.an;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class bd {
    private static final String a = "ViewUtils";
    private static Method b;

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                b = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
                if (b.isAccessible()) {
                    return;
                }
                b.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                Log.d(a, "Could not find method computeFitSystemWindows. Oh well.");
            }
        }
    }

    private bd() {
    }

    public static void a(View view, Rect rect, Rect rect2) {
        Method method = b;
        if (method != null) {
            try {
                method.invoke(view, rect, rect2);
            } catch (Exception e) {
                Log.d(a, "Could not invoke computeFitSystemWindows", e);
            }
        }
    }

    public static boolean a(View view) {
        return android.support.v4.view.ab.m(view) == 1;
    }

    public static void b(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                Method method = view.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                method.invoke(view, new Object[0]);
            } catch (IllegalAccessException e) {
                e = e;
                Log.d(a, "Could not invoke makeOptionalFitsSystemWindows", e);
            } catch (NoSuchMethodException unused) {
                Log.d(a, "Could not find method makeOptionalFitsSystemWindows. Oh well...");
            } catch (InvocationTargetException e2) {
                e = e2;
                Log.d(a, "Could not invoke makeOptionalFitsSystemWindows", e);
            }
        }
    }
}
