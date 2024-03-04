package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class e {
    private static final String a = "CompoundButtonCompat";
    private static Field b;
    private static boolean c;

    private e() {
    }

    @ag
    public static ColorStateList a(@af CompoundButton compoundButton) {
        if (Build.VERSION.SDK_INT >= 21) {
            return compoundButton.getButtonTintList();
        }
        if (compoundButton instanceof v) {
            return ((v) compoundButton).getSupportButtonTintList();
        }
        return null;
    }

    public static void a(@af CompoundButton compoundButton, @ag ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= 21) {
            compoundButton.setButtonTintList(colorStateList);
        } else if (compoundButton instanceof v) {
            ((v) compoundButton).setSupportButtonTintList(colorStateList);
        }
    }

    public static void a(@af CompoundButton compoundButton, @ag PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT >= 21) {
            compoundButton.setButtonTintMode(mode);
        } else if (compoundButton instanceof v) {
            ((v) compoundButton).setSupportButtonTintMode(mode);
        }
    }

    @ag
    public static PorterDuff.Mode b(@af CompoundButton compoundButton) {
        if (Build.VERSION.SDK_INT >= 21) {
            return compoundButton.getButtonTintMode();
        }
        if (compoundButton instanceof v) {
            return ((v) compoundButton).getSupportButtonTintMode();
        }
        return null;
    }

    @ag
    public static Drawable c(@af CompoundButton compoundButton) {
        if (Build.VERSION.SDK_INT >= 23) {
            return compoundButton.getButtonDrawable();
        }
        if (!c) {
            try {
                b = CompoundButton.class.getDeclaredField("mButtonDrawable");
                b.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.i(a, "Failed to retrieve mButtonDrawable field", e);
            }
            c = true;
        }
        Field field = b;
        if (field != null) {
            try {
                return (Drawable) field.get(compoundButton);
            } catch (IllegalAccessException e2) {
                Log.i(a, "Failed to get button drawable via reflection", e2);
                b = null;
            }
        }
        return null;
    }
}
