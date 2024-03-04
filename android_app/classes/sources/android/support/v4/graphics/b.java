package android.support.v4.graphics;

import android.graphics.Color;
import android.support.annotation.af;
import android.support.annotation.ak;
import android.support.annotation.av;
import android.support.annotation.q;
import android.support.annotation.x;
import android.support.v4.view.ab;
import java.util.Objects;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class b {
    private static final double a = 95.047d;
    private static final double b = 100.0d;
    private static final double c = 108.883d;
    private static final double d = 0.008856d;
    private static final double e = 903.3d;
    private static final int f = 10;
    private static final int g = 1;
    private static final ThreadLocal<double[]> h = new ThreadLocal<>();

    private b() {
    }

    private static double a(double d2) {
        return d2 > d ? Math.pow(d2, 0.3333333333333333d) : ((d2 * e) + 16.0d) / 116.0d;
    }

    @q(a = 0.0d, b = 1.0d)
    public static double a(@android.support.annotation.k int i) {
        double[] a2 = a();
        b(i, a2);
        return a2[1] / b;
    }

    public static double a(@af double[] dArr, @af double[] dArr2) {
        return Math.sqrt(Math.pow(dArr[0] - dArr2[0], 2.0d) + Math.pow(dArr[1] - dArr2[1], 2.0d) + Math.pow(dArr[2] - dArr2[2], 2.0d));
    }

    @av
    static float a(float f2, float f3, float f4) {
        if (Math.abs(f3 - f2) > 180.0f) {
            if (f3 > f2) {
                f2 += 360.0f;
            } else {
                f3 += 360.0f;
            }
        }
        return (f2 + ((f3 - f2) * f4)) % 360.0f;
    }

    @android.support.annotation.k
    public static int a(@q(a = 0.0d, b = 95.047d) double d2, @q(a = 0.0d, b = 100.0d) double d3, @q(a = 0.0d, b = 108.883d) double d4) {
        double d5 = (((3.2406d * d2) + ((-1.5372d) * d3)) + ((-0.4986d) * d4)) / b;
        double d6 = ((((-0.9689d) * d2) + (1.8758d * d3)) + (0.0415d * d4)) / b;
        double d7 = (((0.0557d * d2) + ((-0.204d) * d3)) + (1.057d * d4)) / b;
        return Color.rgb(a((int) Math.round((d5 > 0.0031308d ? (Math.pow(d5, 0.4166666666666667d) * 1.055d) - 0.055d : d5 * 12.92d) * 255.0d), 0, 255), a((int) Math.round((d6 > 0.0031308d ? (Math.pow(d6, 0.4166666666666667d) * 1.055d) - 0.055d : d6 * 12.92d) * 255.0d), 0, 255), a((int) Math.round((d7 > 0.0031308d ? (Math.pow(d7, 0.4166666666666667d) * 1.055d) - 0.055d : d7 * 12.92d) * 255.0d), 0, 255));
    }

    public static int a(@android.support.annotation.k int i, @android.support.annotation.k int i2) {
        int alpha = Color.alpha(i2);
        int alpha2 = Color.alpha(i);
        int d2 = d(alpha2, alpha);
        return Color.argb(d2, a(Color.red(i), alpha2, Color.red(i2), alpha, d2), a(Color.green(i), alpha2, Color.green(i2), alpha, d2), a(Color.blue(i), alpha2, Color.blue(i2), alpha, d2));
    }

    public static int a(@android.support.annotation.k int i, @android.support.annotation.k int i2, float f2) {
        int i3 = 255;
        if (Color.alpha(i2) != 255) {
            throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(i2));
        }
        double d2 = f2;
        if (b(c(i, 255), i2) < d2) {
            return -1;
        }
        int i4 = 0;
        for (int i5 = 0; i5 <= 10 && i3 - i4 > 1; i5++) {
            int i6 = (i4 + i3) / 2;
            if (b(c(i, i6), i2) < d2) {
                i4 = i6;
            } else {
                i3 = i6;
            }
        }
        return i3;
    }

    private static int a(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    private static int a(int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            return 0;
        }
        return (((i * 255) * i2) + ((i3 * i4) * (255 - i2))) / (i5 * 255);
    }

    @android.support.annotation.k
    public static int a(@af float[] fArr) {
        int round;
        int round2;
        int round3;
        float f2 = fArr[0];
        float f3 = fArr[1];
        float f4 = fArr[2];
        float abs = (1.0f - Math.abs((f4 * 2.0f) - 1.0f)) * f3;
        float f5 = f4 - (0.5f * abs);
        float abs2 = (1.0f - Math.abs(((f2 / 60.0f) % 2.0f) - 1.0f)) * abs;
        switch (((int) f2) / 60) {
            case 0:
                round = Math.round((abs + f5) * 255.0f);
                round2 = Math.round((abs2 + f5) * 255.0f);
                round3 = Math.round(f5 * 255.0f);
                break;
            case 1:
                round = Math.round((abs2 + f5) * 255.0f);
                round2 = Math.round((abs + f5) * 255.0f);
                round3 = Math.round(f5 * 255.0f);
                break;
            case 2:
                round = Math.round(f5 * 255.0f);
                round2 = Math.round((abs + f5) * 255.0f);
                round3 = Math.round((abs2 + f5) * 255.0f);
                break;
            case 3:
                round = Math.round(f5 * 255.0f);
                round2 = Math.round((abs2 + f5) * 255.0f);
                round3 = Math.round((abs + f5) * 255.0f);
                break;
            case 4:
                round = Math.round((abs2 + f5) * 255.0f);
                round2 = Math.round(f5 * 255.0f);
                round3 = Math.round((abs + f5) * 255.0f);
                break;
            case 5:
            case 6:
                round = Math.round((abs + f5) * 255.0f);
                round2 = Math.round(f5 * 255.0f);
                round3 = Math.round((abs2 + f5) * 255.0f);
                break;
            default:
                round3 = 0;
                round = 0;
                round2 = 0;
                break;
        }
        return Color.rgb(a(round, 0, 255), a(round2, 0, 255), a(round3, 0, 255));
    }

    @ak(a = 26)
    @af
    public static Color a(@af Color color, @af Color color2) {
        if (!Objects.equals(color.getModel(), color2.getModel())) {
            throw new IllegalArgumentException("Color models must match (" + color.getModel() + " vs. " + color2.getModel() + ")");
        }
        if (!Objects.equals(color2.getColorSpace(), color.getColorSpace())) {
            color = color.convert(color2.getColorSpace());
        }
        float[] components = color.getComponents();
        float[] components2 = color2.getComponents();
        float alpha = color.alpha();
        float alpha2 = color2.alpha() * (1.0f - alpha);
        int componentCount = color2.getComponentCount() - 1;
        components2[componentCount] = alpha + alpha2;
        if (components2[componentCount] > 0.0f) {
            alpha /= components2[componentCount];
            alpha2 /= components2[componentCount];
        }
        for (int i = 0; i < componentCount; i++) {
            components2[i] = (components[i] * alpha) + (components2[i] * alpha2);
        }
        return Color.valueOf(components2, color2.getColorSpace());
    }

    public static void a(@q(a = 0.0d, b = 95.047d) double d2, @q(a = 0.0d, b = 100.0d) double d3, @q(a = 0.0d, b = 108.883d) double d4, @af double[] dArr) {
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outLab must have a length of 3.");
        }
        double a2 = a(d2 / a);
        double a3 = a(d3 / b);
        double a4 = a(d4 / c);
        dArr[0] = Math.max(0.0d, (116.0d * a3) - 16.0d);
        dArr[1] = (a2 - a3) * 500.0d;
        dArr[2] = (a3 - a4) * 200.0d;
    }

    public static void a(@x(a = 0, b = 255) int i, @x(a = 0, b = 255) int i2, @x(a = 0, b = 255) int i3, @af double[] dArr) {
        b(i, i2, i3, dArr);
        a(dArr[0], dArr[1], dArr[2], dArr);
    }

    public static void a(@x(a = 0, b = 255) int i, @x(a = 0, b = 255) int i2, @x(a = 0, b = 255) int i3, @af float[] fArr) {
        float f2;
        float abs;
        float f3 = i / 255.0f;
        float f4 = i2 / 255.0f;
        float f5 = i3 / 255.0f;
        float max = Math.max(f3, Math.max(f4, f5));
        float min = Math.min(f3, Math.min(f4, f5));
        float f6 = max - min;
        float f7 = (max + min) / 2.0f;
        if (max == min) {
            f2 = 0.0f;
            abs = 0.0f;
        } else {
            f2 = max == f3 ? ((f4 - f5) / f6) % 6.0f : max == f4 ? ((f5 - f3) / f6) + 2.0f : ((f3 - f4) / f6) + 4.0f;
            abs = f6 / (1.0f - Math.abs((2.0f * f7) - 1.0f));
        }
        float f8 = (f2 * 60.0f) % 360.0f;
        if (f8 < 0.0f) {
            f8 += 360.0f;
        }
        fArr[0] = b(f8, 0.0f, 360.0f);
        fArr[1] = b(abs, 0.0f, 1.0f);
        fArr[2] = b(f7, 0.0f, 1.0f);
    }

    public static void a(@android.support.annotation.k int i, @af double[] dArr) {
        a(Color.red(i), Color.green(i), Color.blue(i), dArr);
    }

    public static void a(@android.support.annotation.k int i, @af float[] fArr) {
        a(Color.red(i), Color.green(i), Color.blue(i), fArr);
    }

    public static void a(@af double[] dArr, @af double[] dArr2, @q(a = 0.0d, b = 1.0d) double d2, @af double[] dArr3) {
        if (dArr3.length != 3) {
            throw new IllegalArgumentException("outResult must have a length of 3.");
        }
        double d3 = 1.0d - d2;
        dArr3[0] = (dArr[0] * d3) + (dArr2[0] * d2);
        dArr3[1] = (dArr[1] * d3) + (dArr2[1] * d2);
        dArr3[2] = (dArr[2] * d3) + (dArr2[2] * d2);
    }

    public static void a(@af float[] fArr, @af float[] fArr2, @q(a = 0.0d, b = 1.0d) float f2, @af float[] fArr3) {
        if (fArr3.length != 3) {
            throw new IllegalArgumentException("result must have a length of 3.");
        }
        float f3 = 1.0f - f2;
        fArr3[0] = a(fArr[0], fArr2[0], f2);
        fArr3[1] = (fArr[1] * f3) + (fArr2[1] * f2);
        fArr3[2] = (fArr[2] * f3) + (fArr2[2] * f2);
    }

    private static double[] a() {
        double[] dArr = h.get();
        if (dArr == null) {
            double[] dArr2 = new double[3];
            h.set(dArr2);
            return dArr2;
        }
        return dArr;
    }

    public static double b(@android.support.annotation.k int i, @android.support.annotation.k int i2) {
        if (Color.alpha(i2) != 255) {
            throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(i2));
        }
        if (Color.alpha(i) < 255) {
            i = a(i, i2);
        }
        double a2 = a(i) + 0.05d;
        double a3 = a(i2) + 0.05d;
        return Math.max(a2, a3) / Math.min(a2, a3);
    }

    private static float b(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    @android.support.annotation.k
    public static int b(@q(a = 0.0d, b = 100.0d) double d2, @q(a = -128.0d, b = 127.0d) double d3, @q(a = -128.0d, b = 127.0d) double d4) {
        double[] a2 = a();
        b(d2, d3, d4, a2);
        return a(a2[0], a2[1], a2[2]);
    }

    @android.support.annotation.k
    public static int b(@android.support.annotation.k int i, @android.support.annotation.k int i2, @q(a = 0.0d, b = 1.0d) float f2) {
        float f3 = 1.0f - f2;
        return Color.argb((int) ((Color.alpha(i) * f3) + (Color.alpha(i2) * f2)), (int) ((Color.red(i) * f3) + (Color.red(i2) * f2)), (int) ((Color.green(i) * f3) + (Color.green(i2) * f2)), (int) ((Color.blue(i) * f3) + (Color.blue(i2) * f2)));
    }

    public static void b(@q(a = 0.0d, b = 100.0d) double d2, @q(a = -128.0d, b = 127.0d) double d3, @q(a = -128.0d, b = 127.0d) double d4, @af double[] dArr) {
        double d5 = (d2 + 16.0d) / 116.0d;
        double d6 = (d3 / 500.0d) + d5;
        double d7 = d5 - (d4 / 200.0d);
        double pow = Math.pow(d6, 3.0d);
        if (pow <= d) {
            pow = ((d6 * 116.0d) - 16.0d) / e;
        }
        double pow2 = d2 > 7.9996247999999985d ? Math.pow(d5, 3.0d) : d2 / e;
        double pow3 = Math.pow(d7, 3.0d);
        if (pow3 <= d) {
            pow3 = ((d7 * 116.0d) - 16.0d) / e;
        }
        dArr[0] = pow * a;
        dArr[1] = pow2 * b;
        dArr[2] = pow3 * c;
    }

    public static void b(@x(a = 0, b = 255) int i, @x(a = 0, b = 255) int i2, @x(a = 0, b = 255) int i3, @af double[] dArr) {
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d2 = i;
        Double.isNaN(d2);
        double d3 = d2 / 255.0d;
        double pow = d3 < 0.04045d ? d3 / 12.92d : Math.pow((d3 + 0.055d) / 1.055d, 2.4d);
        double d4 = i2;
        Double.isNaN(d4);
        double d5 = d4 / 255.0d;
        double pow2 = d5 < 0.04045d ? d5 / 12.92d : Math.pow((d5 + 0.055d) / 1.055d, 2.4d);
        double d6 = i3;
        Double.isNaN(d6);
        double d7 = d6 / 255.0d;
        double pow3 = d7 < 0.04045d ? d7 / 12.92d : Math.pow((d7 + 0.055d) / 1.055d, 2.4d);
        dArr[0] = ((0.4124d * pow) + (0.3576d * pow2) + (0.1805d * pow3)) * b;
        dArr[1] = ((0.2126d * pow) + (0.7152d * pow2) + (0.0722d * pow3)) * b;
        dArr[2] = ((pow * 0.0193d) + (pow2 * 0.1192d) + (pow3 * 0.9505d)) * b;
    }

    public static void b(@android.support.annotation.k int i, @af double[] dArr) {
        b(Color.red(i), Color.green(i), Color.blue(i), dArr);
    }

    @android.support.annotation.k
    public static int c(@android.support.annotation.k int i, @x(a = 0, b = 255) int i2) {
        if (i2 < 0 || i2 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i & ab.r) | (i2 << 24);
    }

    private static int d(int i, int i2) {
        return 255 - (((255 - i2) * (255 - i)) / 255);
    }
}
