package android.support.v4.j;

import android.support.annotation.af;
import android.support.annotation.an;
import android.support.annotation.x;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Locale;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class q {
    private q() {
    }

    public static float a(float f, float f2, float f3, String str) {
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException(str + " must not be NaN");
        } else if (f >= f2) {
            if (f <= f3) {
                return f;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", str, Float.valueOf(f2), Float.valueOf(f3)));
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", str, Float.valueOf(f2), Float.valueOf(f3)));
        }
    }

    public static float a(float f, String str) {
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException(str + " must not be NaN");
        } else if (Float.isInfinite(f)) {
            throw new IllegalArgumentException(str + " must not be infinite");
        } else {
            return f;
        }
    }

    @x(a = 0)
    public static int a(int i) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException();
    }

    public static int a(int i, int i2) {
        if ((i & i2) == i) {
            return i;
        }
        throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i) + ", but only 0x" + Integer.toHexString(i2) + " are allowed");
    }

    public static int a(int i, int i2, int i3, String str) {
        if (i >= i2) {
            if (i <= i3) {
                return i;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", str, Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", str, Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    @x(a = 0)
    public static int a(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str);
    }

    public static long a(long j) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException();
    }

    public static long a(long j, long j2, long j3, String str) {
        if (j >= j2) {
            if (j <= j3) {
                return j;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", str, Long.valueOf(j2), Long.valueOf(j3)));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", str, Long.valueOf(j2), Long.valueOf(j3)));
    }

    public static long a(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str);
    }

    @af
    public static <T extends CharSequence> T a(T t) {
        if (TextUtils.isEmpty(t)) {
            throw new IllegalArgumentException();
        }
        return t;
    }

    @af
    public static <T extends CharSequence> T a(T t, Object obj) {
        if (TextUtils.isEmpty(t)) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
        return t;
    }

    @af
    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    @af
    public static <T> T a(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    @af
    public static <C extends Collection<T>, T> C a(C c, String str) {
        if (c == null) {
            throw new NullPointerException(str + " must not be null");
        }
        long j = 0;
        for (Object obj : c) {
            if (obj == null) {
                throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", str, Long.valueOf(j)));
            }
            j++;
        }
        return c;
    }

    public static void a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void a(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static float[] a(float[] fArr, float f, float f2, String str) {
        a(fArr, str + " must not be null");
        for (int i = 0; i < fArr.length; i++) {
            float f3 = fArr[i];
            if (Float.isNaN(f3)) {
                throw new IllegalArgumentException(str + "[" + i + "] must not be NaN");
            } else if (f3 < f) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too low)", str, Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f2)));
            } else {
                if (f3 > f2) {
                    throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too high)", str, Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f2)));
                }
            }
        }
        return fArr;
    }

    public static <T> T[] a(T[] tArr, String str) {
        if (tArr == null) {
            throw new NullPointerException(str + " must not be null");
        }
        for (int i = 0; i < tArr.length; i++) {
            if (tArr[i] == null) {
                throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", str, Integer.valueOf(i)));
            }
        }
        return tArr;
    }

    public static int b(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str);
    }

    public static <T> Collection<T> b(Collection<T> collection, String str) {
        if (collection == null) {
            throw new NullPointerException(str + " must not be null");
        } else if (collection.isEmpty()) {
            throw new IllegalArgumentException(str + " is empty");
        } else {
            return collection;
        }
    }

    public static void b(boolean z) {
        a(z, (String) null);
    }
}
