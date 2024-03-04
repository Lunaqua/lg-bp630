package com.lge.UDAP.ROAP.c;

import com.lge.UDAP.ROAP.a;
import java.io.PipedOutputStream;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d {
    private com.lge.UDAP.ROAP.c.a a;
    private com.lge.UDAP.ROAP.c.b b;
    private com.lge.UDAP.ROAP.c.c c;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum a {
        AUTH_REQUST_KEY_OK(0),
        AUTH_REQUST_KEY_FAIL(1),
        AUTH_SEND_KEY_OK(2),
        AUTH_SEND_KEY_FAIL(3),
        AUTH_SESSION_FAIL(4),
        AUTH_CANCEL_OK(5),
        AUTH_CANCEL_FAIL(6);
        
        private final int h;

        a(int i2) {
            this.h = i2;
        }

        public int a() {
            return this.h;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum b {
        CP_LIST_OK(0),
        CP_LIST_FAIL(1);
        
        private final int c;

        b(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum c {
        CAP_OK(0),
        CAP_FAIL(1);
        
        private final int c;

        c(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }
    }

    /* renamed from: com.lge.UDAP.ROAP.c.d$d  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum EnumC0056d {
        TEXT_DATA(0),
        THUMBNAIL_DATA(1);
        
        private final int c;

        EnumC0056d(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum e {
        BGM_INFO(0),
        DISC_INFO(1),
        MEDIALINK_INFO(2);
        
        private final int d;

        e(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum f {
        HDD(0),
        USB(1),
        DISC(2),
        PLEX(3),
        DLNA(4);
        
        private final int f;

        f(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum g {
        CONTENTS_LIST_OK(0),
        CONTENTS_LIST_FAIL(1);
        
        private final int c;

        g(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum h {
        ALL_MEDIA(0),
        MOVIE_MEDIA(1),
        MUSIC_MEDIA(2),
        PHOTO_MEDIA(3);
        
        private final int e;

        h(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum i {
        EXTSPK_LIST_OK(0),
        EXTSPK_LIST_FAIL(1);
        
        private final int c;

        i(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum j {
        INFO_OK(0),
        INFO_FAIL(1),
        INFO_INVALID_STATUS(2),
        INFO_IN_PROGRESS(3);
        
        private final int e;

        j(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum k {
        UNKNOWN(0),
        PLAY_OK(1),
        PLAY_FAIL(2),
        PLAY_UNSUPPORTED_VIDEOCODEC(3),
        PLAY_UNSUPPORTED_AUDIOCODEC(4),
        PLAY_UNKNOWN_VIDEO(5);
        
        private final int g;

        k(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum l {
        SP_INFO_OK(0),
        SP_INFO_FAIL(1);
        
        private final int c;

        l(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum m {
        SYNC_STATUS_INFO_OK(0),
        SYNC_STATUS_INFO_FAIL(1),
        SYNC_STATUS_INFO_TEXT(2),
        SYNC_STATUS_INFO_TOUCH(3);
        
        private final int e;

        m(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }
    }

    public d(PipedOutputStream pipedOutputStream) {
        this.a = new com.lge.UDAP.ROAP.c.a(pipedOutputStream);
        this.b = new com.lge.UDAP.ROAP.c.b(pipedOutputStream);
        this.c = new com.lge.UDAP.ROAP.c.c(pipedOutputStream);
    }

    public void a(a.aa aaVar) {
        this.c.a(aaVar);
    }

    public void a(a.o oVar) {
        this.a.a(oVar);
    }

    public void a(a.p pVar) {
        this.b.a(pVar);
    }

    public void a(a.q qVar) {
        this.b.a(qVar);
    }

    public void a(a.r rVar) {
        this.b.a(rVar);
    }

    public void a(a.s sVar) {
        this.b.a(sVar);
    }

    public void a(a.t tVar) {
        this.c.a(tVar);
    }

    public void a(a.u uVar) {
        this.c.a(uVar);
    }

    public void a(a.v vVar) {
        System.out.println("create setResponseContentsInfo");
        this.c.a(vVar);
    }

    public void a(a.x xVar) {
        this.c.a(xVar);
    }

    public void a(a.y yVar) {
        this.c.a(yVar);
    }

    public void a(a.z zVar) {
        this.c.a(zVar);
    }

    public void a(h hVar, String str) {
        this.c.a(f.USB.name(), EnumC0056d.TEXT_DATA.name(), hVar.name(), str);
    }

    public void a(String str) {
        this.a.a(str);
    }

    public boolean a() {
        return (this.a == null || this.b == null || this.c == null) ? false : true;
    }

    public void b() {
        this.a.a();
    }

    public void b(h hVar, String str) {
        this.c.a(f.USB.name(), EnumC0056d.THUMBNAIL_DATA.name(), hVar.name(), str);
    }

    public void b(String str) {
        this.c.b(str);
    }

    public void c() {
        this.c.a();
    }

    public void c(String str) {
        this.c.a(str);
    }

    public void d() {
        this.c.a(e.BGM_INFO.name(), EnumC0056d.TEXT_DATA.name(), new String[0]);
    }

    public void e() {
        this.c.c();
    }

    public void f() {
        this.c.a(e.BGM_INFO.name(), EnumC0056d.THUMBNAIL_DATA.name(), com.lge.media.launcher.a.d);
    }

    public void g() {
        this.c.a(e.DISC_INFO.name(), EnumC0056d.TEXT_DATA.name(), new String[0]);
    }

    public void h() {
        this.c.a(e.DISC_INFO.name(), EnumC0056d.THUMBNAIL_DATA.name(), com.lge.media.launcher.a.d);
    }

    public void i() {
        this.c.d();
    }

    public void j() {
        this.c.e();
    }

    public void k() {
        this.c.f();
    }

    public void l() {
        this.b.a();
    }

    public void m() {
        this.b.b();
    }

    public void n() {
        this.b.c();
    }

    public void o() {
        this.b.d();
    }

    public void p() {
        this.c.g();
    }

    public void q() {
        this.c.h();
    }
}
