package android.support.v4.content.b;

import java.lang.reflect.Array;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
final class f {
    static final /* synthetic */ boolean a = !f.class.desiredAssertionStatus();

    private f() {
    }

    public static int a(int i) {
        if (i <= 4) {
            return 8;
        }
        return i * 2;
    }

    public static int[] a(int[] iArr, int i, int i2) {
        if (a || i <= iArr.length) {
            if (i + 1 > iArr.length) {
                int[] iArr2 = new int[a(i)];
                System.arraycopy(iArr, 0, iArr2, 0, i);
                iArr = iArr2;
            }
            iArr[i] = i2;
            return iArr;
        }
        throw new AssertionError();
    }

    public static int[] a(int[] iArr, int i, int i2, int i3) {
        if (a || i <= iArr.length) {
            if (i + 1 <= iArr.length) {
                System.arraycopy(iArr, i2, iArr, i2 + 1, i - i2);
                iArr[i2] = i3;
                return iArr;
            }
            int[] iArr2 = new int[a(i)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            iArr2[i2] = i3;
            System.arraycopy(iArr, i2, iArr2, i2 + 1, iArr.length - i2);
            return iArr2;
        }
        throw new AssertionError();
    }

    public static long[] a(long[] jArr, int i, int i2, long j) {
        if (a || i <= jArr.length) {
            if (i + 1 <= jArr.length) {
                System.arraycopy(jArr, i2, jArr, i2 + 1, i - i2);
                jArr[i2] = j;
                return jArr;
            }
            long[] jArr2 = new long[a(i)];
            System.arraycopy(jArr, 0, jArr2, 0, i2);
            jArr2[i2] = j;
            System.arraycopy(jArr, i2, jArr2, i2 + 1, jArr.length - i2);
            return jArr2;
        }
        throw new AssertionError();
    }

    public static long[] a(long[] jArr, int i, long j) {
        if (a || i <= jArr.length) {
            if (i + 1 > jArr.length) {
                long[] jArr2 = new long[a(i)];
                System.arraycopy(jArr, 0, jArr2, 0, i);
                jArr = jArr2;
            }
            jArr[i] = j;
            return jArr;
        }
        throw new AssertionError();
    }

    public static <T> T[] a(T[] tArr, int i, int i2, T t) {
        if (a || i <= tArr.length) {
            if (i + 1 <= tArr.length) {
                System.arraycopy(tArr, i2, tArr, i2 + 1, i - i2);
                tArr[i2] = t;
                return tArr;
            }
            T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), a(i)));
            System.arraycopy(tArr, 0, tArr2, 0, i2);
            tArr2[i2] = t;
            System.arraycopy(tArr, i2, tArr2, i2 + 1, tArr.length - i2);
            return tArr2;
        }
        throw new AssertionError();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object[], java.lang.Object] */
    public static <T> T[] a(T[] tArr, int i, T t) {
        if (a || i <= tArr.length) {
            if (i + 1 > tArr.length) {
                Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), a(i));
                System.arraycopy(tArr, 0, objArr, 0, i);
                tArr = objArr;
            }
            tArr[i] = t;
            return tArr;
        }
        throw new AssertionError();
    }

    public static boolean[] a(boolean[] zArr, int i, int i2, boolean z) {
        if (a || i <= zArr.length) {
            if (i + 1 <= zArr.length) {
                System.arraycopy(zArr, i2, zArr, i2 + 1, i - i2);
                zArr[i2] = z;
                return zArr;
            }
            boolean[] zArr2 = new boolean[a(i)];
            System.arraycopy(zArr, 0, zArr2, 0, i2);
            zArr2[i2] = z;
            System.arraycopy(zArr, i2, zArr2, i2 + 1, zArr.length - i2);
            return zArr2;
        }
        throw new AssertionError();
    }

    public static boolean[] a(boolean[] zArr, int i, boolean z) {
        if (a || i <= zArr.length) {
            if (i + 1 > zArr.length) {
                boolean[] zArr2 = new boolean[a(i)];
                System.arraycopy(zArr, 0, zArr2, 0, i);
                zArr = zArr2;
            }
            zArr[i] = z;
            return zArr;
        }
        throw new AssertionError();
    }
}
