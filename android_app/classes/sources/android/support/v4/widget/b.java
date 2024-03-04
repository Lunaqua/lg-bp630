package android.support.v4.widget;

import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.an;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface b {
    @an(a = {an.a.LIBRARY_GROUP})
    public static final boolean a;

    static {
        a = Build.VERSION.SDK_INT >= 27;
    }

    int getAutoSizeMaxTextSize();

    int getAutoSizeMinTextSize();

    int getAutoSizeStepGranularity();

    int[] getAutoSizeTextAvailableSizes();

    int getAutoSizeTextType();

    void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4);

    void setAutoSizeTextTypeUniformWithPresetSizes(@af int[] iArr, int i);

    void setAutoSizeTextTypeWithDefaults(int i);
}
