package android.support.v4.widget;

import android.os.Build;
import android.support.annotation.af;
import android.support.v4.view.ab;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class q {
    private static final String a = "PopupWindowCompatApi21";
    private static Method b;
    private static boolean c;
    private static Method d;
    private static boolean e;
    private static Field f;
    private static boolean g;

    private q() {
    }

    public static void a(@af PopupWindow popupWindow, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setWindowLayoutType(i);
            return;
        }
        if (!c) {
            try {
                b = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                b.setAccessible(true);
            } catch (Exception unused) {
            }
            c = true;
        }
        Method method = b;
        if (method != null) {
            try {
                method.invoke(popupWindow, Integer.valueOf(i));
            } catch (Exception unused2) {
            }
        }
    }

    public static void a(@af PopupWindow popupWindow, @af View view, int i, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 19) {
            popupWindow.showAsDropDown(view, i, i2, i3);
            return;
        }
        if ((android.support.v4.view.f.a(i3, ab.m(view)) & 7) == 5) {
            i -= popupWindow.getWidth() - view.getWidth();
        }
        popupWindow.showAsDropDown(view, i, i2);
    }

    public static void a(@af PopupWindow popupWindow, boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setOverlapAnchor(z);
        } else if (Build.VERSION.SDK_INT >= 21) {
            if (!g) {
                try {
                    f = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    f.setAccessible(true);
                } catch (NoSuchFieldException e2) {
                    Log.i(a, "Could not fetch mOverlapAnchor field from PopupWindow", e2);
                }
                g = true;
            }
            Field field = f;
            if (field != null) {
                try {
                    field.set(popupWindow, Boolean.valueOf(z));
                } catch (IllegalAccessException e3) {
                    Log.i(a, "Could not set overlap anchor field in PopupWindow", e3);
                }
            }
        }
    }

    public static boolean a(@af PopupWindow popupWindow) {
        if (Build.VERSION.SDK_INT >= 23) {
            return popupWindow.getOverlapAnchor();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (!g) {
                try {
                    f = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    f.setAccessible(true);
                } catch (NoSuchFieldException e2) {
                    Log.i(a, "Could not fetch mOverlapAnchor field from PopupWindow", e2);
                }
                g = true;
            }
            Field field = f;
            if (field != null) {
                try {
                    return ((Boolean) field.get(popupWindow)).booleanValue();
                } catch (IllegalAccessException e3) {
                    Log.i(a, "Could not get overlap anchor field in PopupWindow", e3);
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    public static int b(@af PopupWindow popupWindow) {
        if (Build.VERSION.SDK_INT >= 23) {
            return popupWindow.getWindowLayoutType();
        }
        if (!e) {
            try {
                d = PopupWindow.class.getDeclaredMethod("getWindowLayoutType", new Class[0]);
                d.setAccessible(true);
            } catch (Exception unused) {
            }
            e = true;
        }
        Method method = d;
        if (method != null) {
            try {
                return ((Integer) method.invoke(popupWindow, new Object[0])).intValue();
            } catch (Exception unused2) {
            }
        }
        return 0;
    }
}
