package android.support.v4.graphics;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.af;
import android.support.annotation.ak;
import android.support.annotation.q;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class f {
    private f() {
    }

    @ak(a = 26)
    @af
    public static Collection<e> a(@af Path path) {
        return a(path, 0.5f);
    }

    @ak(a = 26)
    @af
    public static Collection<e> a(@af Path path, @q(a = 0.0d) float f) {
        float[] approximate = path.approximate(f);
        int length = approximate.length / 3;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 1; i < length; i++) {
            int i2 = i * 3;
            int i3 = (i - 1) * 3;
            float f2 = approximate[i2];
            float f3 = approximate[i2 + 1];
            float f4 = approximate[i2 + 2];
            float f5 = approximate[i3];
            float f6 = approximate[i3 + 1];
            float f7 = approximate[i3 + 2];
            if (f2 != f5 && (f3 != f6 || f4 != f7)) {
                arrayList.add(new e(new PointF(f6, f7), f5, new PointF(f3, f4), f2));
            }
        }
        return arrayList;
    }
}
