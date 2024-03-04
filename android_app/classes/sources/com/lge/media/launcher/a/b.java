package com.lge.media.launcher.a;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b {
    public static final String a = "Mono";
    public static final String b = "Stereo";
    public static final String c = "L";
    public static final String d = "R";
    public boolean g;
    public boolean i;
    public String e = com.lge.media.launcher.a.d;
    public String f = com.lge.media.launcher.a.d;
    public String h = com.lge.media.launcher.a.d;

    public String a() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public String b() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public void c(String str) {
        this.h = str;
    }

    public boolean c() {
        return this.g;
    }

    public String d() {
        return this.h;
    }

    public boolean e() {
        return this.i;
    }

    public void f() {
        boolean equals = a.equals(this.h);
        String str = c;
        if (!equals && !b.equals(this.h)) {
            boolean equals2 = c.equals(this.h);
            str = d;
            if (!equals2) {
                if (d.equals(this.h)) {
                    if (this.i) {
                        c(a);
                        return;
                    } else {
                        c(b);
                        return;
                    }
                }
                return;
            }
        }
        c(str);
    }

    public int g() {
        if (a.equals(this.h) || b.equals(this.h)) {
            return 0;
        }
        if (c.equals(this.h)) {
            return 1;
        }
        return d.equals(this.h) ? 2 : 0;
    }
}
