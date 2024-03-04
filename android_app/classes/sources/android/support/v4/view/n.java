package android.support.v4.view;

import android.view.MotionEvent;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class n {
    @Deprecated
    public static final int A = 17;
    @Deprecated
    public static final int B = 18;
    @Deprecated
    public static final int C = 19;
    @Deprecated
    public static final int D = 20;
    @Deprecated
    public static final int E = 21;
    @Deprecated
    public static final int F = 22;
    @Deprecated
    public static final int G = 23;
    @Deprecated
    public static final int H = 24;
    @Deprecated
    public static final int I = 25;
    public static final int J = 26;
    public static final int K = 27;
    public static final int L = 28;
    @Deprecated
    public static final int M = 32;
    @Deprecated
    public static final int N = 33;
    @Deprecated
    public static final int O = 34;
    @Deprecated
    public static final int P = 35;
    @Deprecated
    public static final int Q = 36;
    @Deprecated
    public static final int R = 37;
    @Deprecated
    public static final int S = 38;
    @Deprecated
    public static final int T = 39;
    @Deprecated
    public static final int U = 40;
    @Deprecated
    public static final int V = 41;
    @Deprecated
    public static final int W = 42;
    @Deprecated
    public static final int X = 43;
    @Deprecated
    public static final int Y = 44;
    @Deprecated
    public static final int Z = 45;
    @Deprecated
    public static final int a = 255;
    @Deprecated
    public static final int aa = 46;
    @Deprecated
    public static final int ab = 47;
    @Deprecated
    public static final int ac = 1;
    @Deprecated
    public static final int b = 5;
    @Deprecated
    public static final int c = 6;
    @Deprecated
    public static final int d = 7;
    @Deprecated
    public static final int e = 8;
    @Deprecated
    public static final int f = 65280;
    @Deprecated
    public static final int g = 8;
    @Deprecated
    public static final int h = 9;
    @Deprecated
    public static final int i = 10;
    @Deprecated
    public static final int j = 0;
    @Deprecated
    public static final int k = 1;
    @Deprecated
    public static final int l = 2;
    @Deprecated
    public static final int m = 3;
    @Deprecated
    public static final int n = 4;
    @Deprecated
    public static final int o = 5;
    @Deprecated
    public static final int p = 6;
    @Deprecated
    public static final int q = 7;
    @Deprecated
    public static final int r = 8;
    @Deprecated
    public static final int s = 9;
    @Deprecated
    public static final int t = 10;
    @Deprecated
    public static final int u = 11;
    @Deprecated
    public static final int v = 12;
    @Deprecated
    public static final int w = 13;
    @Deprecated
    public static final int x = 14;
    @Deprecated
    public static final int y = 15;
    @Deprecated
    public static final int z = 16;

    private n() {
    }

    @Deprecated
    public static float a(MotionEvent motionEvent, int i2, int i3) {
        return motionEvent.getAxisValue(i2, i3);
    }

    @Deprecated
    public static int a(MotionEvent motionEvent) {
        return motionEvent.getActionMasked();
    }

    @Deprecated
    public static int a(MotionEvent motionEvent, int i2) {
        return motionEvent.findPointerIndex(i2);
    }

    @Deprecated
    public static int b(MotionEvent motionEvent) {
        return motionEvent.getActionIndex();
    }

    @Deprecated
    public static int b(MotionEvent motionEvent, int i2) {
        return motionEvent.getPointerId(i2);
    }

    @Deprecated
    public static float c(MotionEvent motionEvent, int i2) {
        return motionEvent.getX(i2);
    }

    @Deprecated
    public static int c(MotionEvent motionEvent) {
        return motionEvent.getPointerCount();
    }

    @Deprecated
    public static float d(MotionEvent motionEvent, int i2) {
        return motionEvent.getY(i2);
    }

    @Deprecated
    public static int d(MotionEvent motionEvent) {
        return motionEvent.getSource();
    }

    @Deprecated
    public static int e(MotionEvent motionEvent) {
        return motionEvent.getButtonState();
    }

    public static boolean e(MotionEvent motionEvent, int i2) {
        return (motionEvent.getSource() & i2) == i2;
    }

    @Deprecated
    public static float f(MotionEvent motionEvent, int i2) {
        return motionEvent.getAxisValue(i2);
    }
}
