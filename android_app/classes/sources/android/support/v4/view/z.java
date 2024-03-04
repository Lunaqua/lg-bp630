package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface z {
    @android.support.annotation.ag
    ColorStateList getSupportBackgroundTintList();

    @android.support.annotation.ag
    PorterDuff.Mode getSupportBackgroundTintMode();

    void setSupportBackgroundTintList(@android.support.annotation.ag ColorStateList colorStateList);

    void setSupportBackgroundTintMode(@android.support.annotation.ag PorterDuff.Mode mode);
}
