package android.support.v4.graphics;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.af;
import android.support.v4.j.n;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class c {
    private static final String a = "\udfffd";
    private static final String b = "m";
    private static final ThreadLocal<n<Rect, Rect>> c = new ThreadLocal<>();

    private c() {
    }

    private static n<Rect, Rect> a() {
        n<Rect, Rect> nVar = c.get();
        if (nVar == null) {
            n<Rect, Rect> nVar2 = new n<>(new Rect(), new Rect());
            c.set(nVar2);
            return nVar2;
        }
        nVar.a.setEmpty();
        nVar.b.setEmpty();
        return nVar;
    }

    public static boolean a(@af Paint paint, @af String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return paint.hasGlyph(str);
        }
        int length = str.length();
        if (length == 1 && Character.isWhitespace(str.charAt(0))) {
            return true;
        }
        float measureText = paint.measureText(a);
        float measureText2 = paint.measureText(b);
        float measureText3 = paint.measureText(str);
        float f = 0.0f;
        if (measureText3 == 0.0f) {
            return false;
        }
        if (str.codePointCount(0, str.length()) > 1) {
            if (measureText3 > measureText2 * 2.0f) {
                return false;
            }
            int i = 0;
            while (i < length) {
                int charCount = Character.charCount(str.codePointAt(i)) + i;
                f += paint.measureText(str, i, charCount);
                i = charCount;
            }
            if (measureText3 >= f) {
                return false;
            }
        }
        if (measureText3 != measureText) {
            return true;
        }
        n<Rect, Rect> a2 = a();
        paint.getTextBounds(a, 0, 2, a2.a);
        paint.getTextBounds(str, 0, length, a2.b);
        return !a2.a.equals(a2.b);
    }
}
