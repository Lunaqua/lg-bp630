package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ak;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class i {
    private static final String a = "ResourcesFlusher";
    private static Field b;
    private static boolean c;
    private static Class d;
    private static boolean e;
    private static Field f;
    private static boolean g;
    private static Field h;
    private static boolean i;

    private i() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@af Resources resources) {
        if (Build.VERSION.SDK_INT >= 28) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            d(resources);
        } else if (Build.VERSION.SDK_INT >= 23) {
            c(resources);
        } else if (Build.VERSION.SDK_INT >= 21) {
            b(resources);
        }
    }

    @ak(a = 16)
    private static void a(@af Object obj) {
        LongSparseArray longSparseArray;
        if (!e) {
            try {
                d = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e2) {
                Log.e(a, "Could not find ThemedResourceCache class", e2);
            }
            e = true;
        }
        Class cls = d;
        if (cls == null) {
            return;
        }
        if (!g) {
            try {
                f = cls.getDeclaredField("mUnthemedEntries");
                f.setAccessible(true);
            } catch (NoSuchFieldException e3) {
                Log.e(a, "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e3);
            }
            g = true;
        }
        Field field = f;
        if (field == null) {
            return;
        }
        try {
            longSparseArray = (LongSparseArray) field.get(obj);
        } catch (IllegalAccessException e4) {
            Log.e(a, "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e4);
            longSparseArray = null;
        }
        if (longSparseArray != null) {
            longSparseArray.clear();
        }
    }

    @ak(a = 21)
    private static void b(@af Resources resources) {
        Map map;
        if (!c) {
            try {
                b = Resources.class.getDeclaredField("mDrawableCache");
                b.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.e(a, "Could not retrieve Resources#mDrawableCache field", e2);
            }
            c = true;
        }
        Field field = b;
        if (field != null) {
            try {
                map = (Map) field.get(resources);
            } catch (IllegalAccessException e3) {
                Log.e(a, "Could not retrieve value from Resources#mDrawableCache", e3);
                map = null;
            }
            if (map != null) {
                map.clear();
            }
        }
    }

    @ak(a = 23)
    private static void c(@af Resources resources) {
        if (!c) {
            try {
                b = Resources.class.getDeclaredField("mDrawableCache");
                b.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.e(a, "Could not retrieve Resources#mDrawableCache field", e2);
            }
            c = true;
        }
        Object obj = null;
        Field field = b;
        if (field != null) {
            try {
                obj = field.get(resources);
            } catch (IllegalAccessException e3) {
                Log.e(a, "Could not retrieve value from Resources#mDrawableCache", e3);
            }
        }
        if (obj == null) {
            return;
        }
        a(obj);
    }

    @ak(a = 24)
    private static void d(@af Resources resources) {
        Object obj;
        if (!i) {
            try {
                h = Resources.class.getDeclaredField("mResourcesImpl");
                h.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.e(a, "Could not retrieve Resources#mResourcesImpl field", e2);
            }
            i = true;
        }
        Field field = h;
        if (field == null) {
            return;
        }
        Object obj2 = null;
        try {
            obj = field.get(resources);
        } catch (IllegalAccessException e3) {
            Log.e(a, "Could not retrieve value from Resources#mResourcesImpl", e3);
            obj = null;
        }
        if (obj == null) {
            return;
        }
        if (!c) {
            try {
                b = obj.getClass().getDeclaredField("mDrawableCache");
                b.setAccessible(true);
            } catch (NoSuchFieldException e4) {
                Log.e(a, "Could not retrieve ResourcesImpl#mDrawableCache field", e4);
            }
            c = true;
        }
        Field field2 = b;
        if (field2 != null) {
            try {
                obj2 = field2.get(obj);
            } catch (IllegalAccessException e5) {
                Log.e(a, "Could not retrieve value from ResourcesImpl#mDrawableCache", e5);
            }
        }
        if (obj2 != null) {
            a(obj2);
        }
    }
}
