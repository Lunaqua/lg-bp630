package android.support.v4.i;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.text.Html;
import android.text.Spanned;
@SuppressLint({"InlinedApi"})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class b {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 4;
    public static final int f = 8;
    public static final int g = 16;
    public static final int h = 32;
    public static final int i = 256;
    public static final int j = 0;
    public static final int k = 63;

    private b() {
    }

    @af
    public static Spanned a(@af String str, int i2) {
        return Build.VERSION.SDK_INT >= 24 ? Html.fromHtml(str, i2) : Html.fromHtml(str);
    }

    @af
    public static Spanned a(@af String str, int i2, @ag Html.ImageGetter imageGetter, @ag Html.TagHandler tagHandler) {
        return Build.VERSION.SDK_INT >= 24 ? Html.fromHtml(str, i2, imageGetter, tagHandler) : Html.fromHtml(str, imageGetter, tagHandler);
    }

    @af
    public static String a(@af Spanned spanned, int i2) {
        return Build.VERSION.SDK_INT >= 24 ? Html.toHtml(spanned, i2) : Html.toHtml(spanned);
    }
}
