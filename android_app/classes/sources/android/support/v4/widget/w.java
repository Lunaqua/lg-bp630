package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.annotation.ag;
import android.support.annotation.an;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface w {
    @ag
    ColorStateList getSupportImageTintList();

    @ag
    PorterDuff.Mode getSupportImageTintMode();

    void setSupportImageTintList(@ag ColorStateList colorStateList);

    void setSupportImageTintMode(@ag PorterDuff.Mode mode);
}
