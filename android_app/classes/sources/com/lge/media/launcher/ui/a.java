package com.lge.media.launcher.ui;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a {
    private com.lge.UDAP.a.c a;
    private boolean b;
    private String c;
    private String d;
    private String e;

    public a(com.lge.UDAP.a.c cVar) {
        this.a = cVar;
        this.b = true;
        this.c = cVar.g();
        this.d = cVar.d();
        this.e = cVar.k();
    }

    public a(String str, String str2) {
        this.c = str;
        this.d = str2;
        this.b = false;
        this.a = null;
    }

    public a(String str, String str2, String str3) {
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.b = false;
        this.a = null;
    }

    public com.lge.UDAP.a.c a() {
        return this.a;
    }

    public void a(com.lge.UDAP.a.c cVar) {
        this.a = cVar;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public void b(String str) {
        this.d = str;
    }

    public boolean b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.e = str;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public void f() {
        this.b = false;
        this.a = null;
    }
}
