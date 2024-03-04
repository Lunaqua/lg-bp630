package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class ac {
    private static final String a = "ViewConfigCompat";
    private static Method b;

    static {
        if (Build.VERSION.SDK_INT == 25) {
            try {
                b = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor", new Class[0]);
            } catch (Exception unused) {
                Log.i(a, "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
    }

    private ac() {
    }

    public static float a(@android.support.annotation.af ViewConfiguration viewConfiguration, @android.support.annotation.af Context context) {
        return Build.VERSION.SDK_INT >= 26 ? viewConfiguration.getScaledHorizontalScrollFactor() : d(viewConfiguration, context);
    }

    @Deprecated
    public static int a(ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledPagingTouchSlop();
    }

    public static float b(@android.support.annotation.af ViewConfiguration viewConfiguration, @android.support.annotation.af Context context) {
        return Build.VERSION.SDK_INT >= 26 ? viewConfiguration.getScaledVerticalScrollFactor() : d(viewConfiguration, context);
    }

    @Deprecated
    public static boolean b(ViewConfiguration viewConfiguration) {
        return viewConfiguration.hasPermanentMenuKey();
    }

    public static int c(ViewConfiguration viewConfiguration) {
        return Build.VERSION.SDK_INT >= 28 ? viewConfiguration.getScaledHoverSlop() : viewConfiguration.getScaledTouchSlop() / 2;
    }

    public static boolean c(ViewConfiguration viewConfiguration, @android.support.annotation.af Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
        return identifier != 0 && resources.getBoolean(identifier);
    }

    private static float d(ViewConfiguration viewConfiguration, Context context) {
        Method method;
        if (Build.VERSION.SDK_INT >= 25 && (method = b) != null) {
            try {
                return ((Integer) method.invoke(viewConfiguration, new Object[0])).intValue();
            } catch (Exception unused) {
                Log.i(a, "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16842829, typedValue, true)) {
            return typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return 0.0f;
    }
}
