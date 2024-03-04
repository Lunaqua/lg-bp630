package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.annotation.ag;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface v {
    @ag
    ColorStateList getSupportButtonTintList();

    @ag
    PorterDuff.Mode getSupportButtonTintMode();

    void setSupportButtonTintList(@ag ColorStateList colorStateList);

    void setSupportButtonTintMode(@ag PorterDuff.Mode mode);
}
