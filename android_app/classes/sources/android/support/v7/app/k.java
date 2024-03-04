package android.support.v7.app;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class k {
    public static final int a = 0;
    public static final int b = 1;
    private static k f = null;
    private static final float g = 0.017453292f;
    private static final float h = 9.0E-4f;
    private static final float i = -0.10471976f;
    private static final float j = 0.0334196f;
    private static final float k = 3.49066E-4f;
    private static final float l = 5.236E-6f;
    private static final float m = 0.4092797f;
    private static final long n = 946728000000L;
    public long c;
    public long d;
    public int e;

    k() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static k a() {
        if (f == null) {
            f = new k();
        }
        return f;
    }

    public void a(long j2, double d, double d2) {
        float f2;
        float f3 = ((float) (j2 - n)) / 8.64E7f;
        double d3 = (0.01720197f * f3) + 6.24006f;
        Double.isNaN(d3);
        double sin = (Math.sin(d3) * 0.03341960161924362d) + d3 + (Math.sin(2.0f * f2) * 3.4906598739326E-4d) + (Math.sin(f2 * 3.0f) * 5.236000106378924E-6d) + 1.796593063d + 3.141592653589793d;
        double d4 = (-d2) / 360.0d;
        double d5 = f3 - h;
        Double.isNaN(d5);
        double round = ((float) Math.round(d5 - d4)) + h;
        Double.isNaN(round);
        double sin2 = round + d4 + (Math.sin(d3) * 0.0053d) + (Math.sin(2.0d * sin) * (-0.0069d));
        double asin = Math.asin(Math.sin(sin) * Math.sin(0.4092797040939331d));
        double d6 = 0.01745329238474369d * d;
        double sin3 = (Math.sin(-0.10471975803375244d) - (Math.sin(d6) * Math.sin(asin))) / (Math.cos(d6) * Math.cos(asin));
        if (sin3 >= 1.0d) {
            this.e = 1;
        } else if (sin3 > -1.0d) {
            double acos = (float) (Math.acos(sin3) / 6.283185307179586d);
            Double.isNaN(acos);
            this.c = Math.round((sin2 + acos) * 8.64E7d) + n;
            Double.isNaN(acos);
            this.d = Math.round((sin2 - acos) * 8.64E7d) + n;
            if (this.d >= j2 || this.c <= j2) {
                this.e = 1;
                return;
            } else {
                this.e = 0;
                return;
            }
        } else {
            this.e = 0;
        }
        this.c = -1L;
        this.d = -1L;
    }
}
