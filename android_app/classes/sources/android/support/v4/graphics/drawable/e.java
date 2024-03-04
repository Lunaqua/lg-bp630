package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.annotation.an;
import android.support.annotation.k;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface e {
    void setTint(@k int i);

    void setTintList(ColorStateList colorStateList);

    void setTintMode(PorterDuff.Mode mode);
}
