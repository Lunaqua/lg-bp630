package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.k;
import android.util.AttributeSet;
import android.util.Log;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    private static final String a = "DrawableCompat";
    private static Method b;
    private static boolean c;
    private static Method d;
    private static boolean e;

    private a() {
    }

    @Deprecated
    public static void a(@af Drawable drawable) {
        drawable.jumpToCurrentState();
    }

    public static void a(@af Drawable drawable, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setHotspot(f, f2);
        }
    }

    public static void a(@af Drawable drawable, @k int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setTint(i);
        } else if (drawable instanceof e) {
            ((e) drawable).setTint(i);
        }
    }

    public static void a(@af Drawable drawable, int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setHotspotBounds(i, i2, i3, i4);
        }
    }

    public static void a(@af Drawable drawable, @ag ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setTintList(colorStateList);
        } else if (drawable instanceof e) {
            ((e) drawable).setTintList(colorStateList);
        }
    }

    public static void a(@af Drawable drawable, @af Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.applyTheme(theme);
        }
    }

    public static void a(@af Drawable drawable, @af Resources resources, @af XmlPullParser xmlPullParser, @af AttributeSet attributeSet, @ag Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
        } else {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        }
    }

    public static void a(@af Drawable drawable, @af PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setTintMode(mode);
        } else if (drawable instanceof e) {
            ((e) drawable).setTintMode(mode);
        }
    }

    public static void a(@af Drawable drawable, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            drawable.setAutoMirrored(z);
        }
    }

    public static boolean b(@af Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 19) {
            return drawable.isAutoMirrored();
        }
        return false;
    }

    public static boolean b(@af Drawable drawable, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return drawable.setLayoutDirection(i);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (!c) {
                try {
                    b = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE);
                    b.setAccessible(true);
                } catch (NoSuchMethodException e2) {
                    Log.i(a, "Failed to retrieve setLayoutDirection(int) method", e2);
                }
                c = true;
            }
            Method method = b;
            if (method != null) {
                try {
                    method.invoke(drawable, Integer.valueOf(i));
                    return true;
                } catch (Exception e3) {
                    Log.i(a, "Failed to invoke setLayoutDirection(int) via reflection", e3);
                    b = null;
                }
            }
        }
        return false;
    }

    public static int c(@af Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 19) {
            return drawable.getAlpha();
        }
        return 0;
    }

    public static boolean d(@af Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 21) {
            return drawable.canApplyTheme();
        }
        return false;
    }

    public static ColorFilter e(@af Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 21) {
            return drawable.getColorFilter();
        }
        return null;
    }

    public static void f(@af Drawable drawable) {
        DrawableContainer.DrawableContainerState drawableContainerState;
        Drawable a2;
        if (Build.VERSION.SDK_INT >= 23 || Build.VERSION.SDK_INT < 21) {
            drawable.clearColorFilter();
            return;
        }
        drawable.clearColorFilter();
        if (drawable instanceof InsetDrawable) {
            a2 = ((InsetDrawable) drawable).getDrawable();
        } else if (!(drawable instanceof f)) {
            if (!(drawable instanceof DrawableContainer) || (drawableContainerState = (DrawableContainer.DrawableContainerState) ((DrawableContainer) drawable).getConstantState()) == null) {
                return;
            }
            int childCount = drawableContainerState.getChildCount();
            for (int i = 0; i < childCount; i++) {
                Drawable child = drawableContainerState.getChild(i);
                if (child != null) {
                    f(child);
                }
            }
            return;
        } else {
            a2 = ((f) drawable).a();
        }
        f(a2);
    }

    public static Drawable g(@af Drawable drawable) {
        return Build.VERSION.SDK_INT >= 23 ? drawable : Build.VERSION.SDK_INT >= 21 ? !(drawable instanceof e) ? new h(drawable) : drawable : !(drawable instanceof e) ? new g(drawable) : drawable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Drawable> T h(@af Drawable drawable) {
        return drawable instanceof f ? (T) ((f) drawable).a() : drawable;
    }

    public static int i(@af Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 23) {
            return drawable.getLayoutDirection();
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (!e) {
                try {
                    d = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
                    d.setAccessible(true);
                } catch (NoSuchMethodException e2) {
                    Log.i(a, "Failed to retrieve getLayoutDirection() method", e2);
                }
                e = true;
            }
            Method method = d;
            if (method != null) {
                try {
                    return ((Integer) method.invoke(drawable, new Object[0])).intValue();
                } catch (Exception e3) {
                    Log.i(a, "Failed to invoke getLayoutDirection() via reflection", e3);
                    d = null;
                }
            }
        }
        return 0;
    }
}
