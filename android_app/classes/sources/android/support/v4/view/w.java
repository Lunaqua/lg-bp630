package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.an;
import android.view.PointerIcon;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class w {
    public static final int a = 0;
    public static final int b = 1000;
    public static final int c = 1001;
    public static final int d = 1002;
    public static final int e = 1003;
    public static final int f = 1004;
    public static final int g = 1006;
    public static final int h = 1007;
    public static final int i = 1008;
    public static final int j = 1009;
    public static final int k = 1010;
    public static final int l = 1011;
    public static final int m = 1012;
    public static final int n = 1013;
    public static final int o = 1014;
    public static final int p = 1015;
    public static final int q = 1016;
    public static final int r = 1017;
    public static final int s = 1018;
    public static final int t = 1019;
    public static final int u = 1020;
    public static final int v = 1021;
    public static final int w = 1000;
    private Object x;

    private w(Object obj) {
        this.x = obj;
    }

    public static w a(Context context, int i2) {
        return Build.VERSION.SDK_INT >= 24 ? new w(PointerIcon.getSystemIcon(context, i2)) : new w(null);
    }

    public static w a(Resources resources, int i2) {
        return Build.VERSION.SDK_INT >= 24 ? new w(PointerIcon.load(resources, i2)) : new w(null);
    }

    public static w a(Bitmap bitmap, float f2, float f3) {
        return Build.VERSION.SDK_INT >= 24 ? new w(PointerIcon.create(bitmap, f2, f3)) : new w(null);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public Object a() {
        return this.x;
    }
}
