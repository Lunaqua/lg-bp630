package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.widget.ImageView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class l {
    private l() {
    }

    @ag
    public static ColorStateList a(@af ImageView imageView) {
        if (Build.VERSION.SDK_INT >= 21) {
            return imageView.getImageTintList();
        }
        if (imageView instanceof w) {
            return ((w) imageView).getSupportImageTintList();
        }
        return null;
    }

    public static void a(@af ImageView imageView, @ag ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT < 21) {
            if (imageView instanceof w) {
                ((w) imageView).setSupportImageTintList(colorStateList);
                return;
            }
            return;
        }
        imageView.setImageTintList(colorStateList);
        if (Build.VERSION.SDK_INT == 21) {
            Drawable drawable = imageView.getDrawable();
            boolean z = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? false : true;
            if (drawable == null || !z) {
                return;
            }
            if (drawable.isStateful()) {
                drawable.setState(imageView.getDrawableState());
            }
            imageView.setImageDrawable(drawable);
        }
    }

    public static void a(@af ImageView imageView, @ag PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT < 21) {
            if (imageView instanceof w) {
                ((w) imageView).setSupportImageTintMode(mode);
                return;
            }
            return;
        }
        imageView.setImageTintMode(mode);
        if (Build.VERSION.SDK_INT == 21) {
            Drawable drawable = imageView.getDrawable();
            boolean z = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? false : true;
            if (drawable == null || !z) {
                return;
            }
            if (drawable.isStateful()) {
                drawable.setState(imageView.getDrawableState());
            }
            imageView.setImageDrawable(drawable);
        }
    }

    @ag
    public static PorterDuff.Mode b(@af ImageView imageView) {
        if (Build.VERSION.SDK_INT >= 21) {
            return imageView.getImageTintMode();
        }
        if (imageView instanceof w) {
            return ((w) imageView).getSupportImageTintMode();
        }
        return null;
    }
}
