package com.lge.UDAP.ROAP.b;

import com.lge.UDAP.ROAP.a;
import java.io.PrintStream;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a {

    /* renamed from: com.lge.UDAP.ROAP.b.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum EnumC0054a {
        BGM_INFO_UNKNOWN(0),
        BGM_INFO_OK(1),
        BGM_INFO_FAIL(2),
        BGM_INFO_LOADING(3),
        BGM_INFO_CANCEL(4);
        
        private final int f;

        EnumC0054a(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum b {
        CP_UNKNOWN(0),
        CP_LIST_CHANGE(1),
        CP_LOADING_START(2),
        CP_LOADING_END(3);
        
        private final int e;

        b(int i) {
            this.e = i;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum c {
        EXTSPK_UNKNOWN(0),
        EXTSPK_LIST_CHANGE(1);
        
        private final int c;

        c(int i) {
            this.c = i;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum d {
        NETWORK_UNKNOWN(0),
        NETWORK_WIRED_CONNECTED(1),
        NETWORK_WIRED_DISCONNECTED(2),
        NETWORK_WIRELESS_CONNECTED(3),
        NETWORK_WIRELESS_DISCONNECTED(4);
        
        private final int f;

        d(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum e {
        PLAYBACK_UNKNOWN(0),
        PLAYBACK_ERROR(1),
        PLAYBACK_PLAY(2),
        PLAYBACK_STOP(3),
        PLAYBACK_PAUSE(4),
        PLAYBACK_FAST_FWD(5),
        PLAYBACK_FAST_BWD(6),
        PLAYBACK_SLOW_FWD(7),
        PLAYBACK_NOW_PLAYING(8);
        
        private final int j;

        e(int i) {
            this.j = i;
        }

        public int a() {
            return this.j;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum f {
        SP_UNKNOWN(0),
        SP_SOUND_ON(1),
        SP_SOUND_OFF(2),
        SP_SAMPLERATE_CHANGE(3),
        SP_STOP(4),
        SP_NOT_SUPPORT(5),
        SP_ALIVE(6);
        
        private final int h;

        f(int i2) {
            this.h = i2;
        }

        public int a() {
            return this.h;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum g {
        SS_UNKNOWN(0),
        SS_VIEW_CHANGE(1),
        SS_ENABLED(2),
        SS_DISABLED(3);
        
        private final int e;

        g(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum h {
        USB_UNKNOWN(0),
        USB_CONNECTED(1),
        USB_DISCONNECTED(2);
        
        private final int d;

        h(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum i {
        VIEW_TOUCHPAD(1),
        VIEW_TEXTINPUT(2);
        
        private final int c;

        i(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }
    }

    public void a(a.f fVar) {
        PrintStream printStream;
        String str;
        System.out.println("Create setBGMInfoStatusEvent");
        com.lge.UDAP.ROAP.b.b a = com.lge.UDAP.ROAP.b.c.a();
        a.a(fVar);
        com.lge.UDAP.ROAP.b.c.a(a);
        if (a == null) {
            printStream = System.out;
            str = "roapEventInterface is null";
        } else {
            printStream = System.out;
            str = "roapEventInterface is not null";
        }
        printStream.println(str);
    }

    public void a(a.g gVar) {
        PrintStream printStream;
        String str;
        System.out.println("Create setCPStatusEvent");
        com.lge.UDAP.ROAP.b.b a = com.lge.UDAP.ROAP.b.c.a();
        a.a(gVar);
        com.lge.UDAP.ROAP.b.c.a(a);
        if (a == null) {
            printStream = System.out;
            str = "roapEventInterface is null";
        } else {
            printStream = System.out;
            str = "roapEventInterface is not null";
        }
        printStream.println(str);
    }

    public void a(a.h hVar) {
        PrintStream printStream;
        String str;
        System.out.println("Create setExtSpkStatusEvent");
        com.lge.UDAP.ROAP.b.b a = com.lge.UDAP.ROAP.b.c.a();
        a.a(hVar);
        com.lge.UDAP.ROAP.b.c.a(a);
        if (a == null) {
            printStream = System.out;
            str = "roapEventInterface is null";
        } else {
            printStream = System.out;
            str = "roapEventInterface is not null";
        }
        printStream.println(str);
    }

    public void a(a.i iVar) {
        com.lge.UDAP.ROAP.b.b a = com.lge.UDAP.ROAP.b.c.a();
        a.a(iVar);
        com.lge.UDAP.ROAP.b.c.a(a);
    }

    public void a(a.j jVar) {
        com.lge.UDAP.ROAP.b.b a = com.lge.UDAP.ROAP.b.c.a();
        a.a(jVar);
        com.lge.UDAP.ROAP.b.c.a(a);
    }

    public void a(a.k kVar) {
        com.lge.UDAP.ROAP.b.b a = com.lge.UDAP.ROAP.b.c.a();
        a.a(kVar);
        com.lge.UDAP.ROAP.b.c.a(a);
    }

    public void a(a.l lVar) {
        PrintStream printStream;
        String str;
        System.out.println("Create setSyncStatusEvent");
        com.lge.UDAP.ROAP.b.b a = com.lge.UDAP.ROAP.b.c.a();
        a.a(lVar);
        com.lge.UDAP.ROAP.b.c.a(a);
        if (a == null) {
            printStream = System.out;
            str = "roapEventInterface is null";
        } else {
            printStream = System.out;
            str = "roapEventInterface is not null";
        }
        printStream.println(str);
    }

    public void a(a.m mVar) {
        com.lge.UDAP.ROAP.b.b a = com.lge.UDAP.ROAP.b.c.a();
        a.a(mVar);
        com.lge.UDAP.ROAP.b.c.a(a);
    }

    public void a(a.n nVar) {
        com.lge.UDAP.ROAP.b.b a = com.lge.UDAP.ROAP.b.c.a();
        a.a(nVar);
        com.lge.UDAP.ROAP.b.c.a(a);
    }
}
