package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class aq {
    private static final ThreadLocal<TypedValue> i = new ThreadLocal<>();
    static final int[] a = {-16842910};
    static final int[] b = {16842908};
    static final int[] c = {16843518};
    static final int[] d = {16842919};
    static final int[] e = {16842912};
    static final int[] f = {16842913};
    static final int[] g = {-16842919, -16842908};
    static final int[] h = new int[0];
    private static final int[] j = new int[1];

    private aq() {
    }

    public static int a(Context context, int i2) {
        int[] iArr = j;
        iArr[0] = i2;
        av a2 = av.a(context, (AttributeSet) null, iArr);
        try {
            return a2.b(0, 0);
        } finally {
            a2.e();
        }
    }

    static int a(Context context, int i2, float f2) {
        int a2 = a(context, i2);
        return android.support.v4.graphics.b.c(a2, Math.round(Color.alpha(a2) * f2));
    }

    public static ColorStateList a(int i2, int i3) {
        return new ColorStateList(new int[][]{a, h}, new int[]{i3, i2});
    }

    private static TypedValue a() {
        TypedValue typedValue = i.get();
        if (typedValue == null) {
            TypedValue typedValue2 = new TypedValue();
            i.set(typedValue2);
            return typedValue2;
        }
        return typedValue;
    }

    public static ColorStateList b(Context context, int i2) {
        int[] iArr = j;
        iArr[0] = i2;
        av a2 = av.a(context, (AttributeSet) null, iArr);
        try {
            return a2.g(0);
        } finally {
            a2.e();
        }
    }

    public static int c(Context context, int i2) {
        ColorStateList b2 = b(context, i2);
        if (b2 == null || !b2.isStateful()) {
            TypedValue a2 = a();
            context.getTheme().resolveAttribute(16842803, a2, true);
            return a(context, i2, a2.getFloat());
        }
        return b2.getColorForState(a, b2.getDefaultColor());
    }
}
