package com.lge.UDAP.ROAP;

import com.lge.UDAP.ROAP.c.d;
import com.lge.UDAP.a.c;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b extends c {
    public d a;
    public com.lge.UDAP.ROAP.a.b b;
    public com.lge.UDAP.ROAP.b.a c;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 4;
        private String d;
        private int e;
        private int f;
        private String g;

        public a(com.lge.UDAP.a.c cVar) {
            this.d = cVar.k();
            PrintStream printStream = System.out;
            printStream.println("ROAPNetwork created with param ip is: " + this.d + " port:" + this.e + "capadataheaerStr: " + this.g);
            Iterator<c.a> it = cVar.j().iterator();
            while (it.hasNext()) {
                c.a next = it.next();
                byte a2 = next.a();
                if (cVar.l() == c.b.ROAP_TYPE_HDCP) {
                    if (a2 == 2) {
                        this.e = next.b();
                        this.f = 4;
                    }
                } else if (cVar.l() == c.b.ROAP_TYPE_PROTOBUFFER && a2 == 0) {
                    this.e = next.b();
                    this.f = 2;
                }
            }
        }

        public String a() {
            return this.d;
        }

        public int b() {
            return this.e;
        }

        public int c() {
            return this.f;
        }

        public String d() {
            return this.g;
        }
    }

    public b(a aVar) {
        super(aVar);
        System.out.println("create ROAPManager");
        this.a = new d(c());
        this.b = new com.lge.UDAP.ROAP.a.b(c());
        this.c = new com.lge.UDAP.ROAP.b.a();
    }

    public boolean a() {
        d();
        return true;
    }

    public boolean a(a aVar) {
        b(aVar);
        return true;
    }

    public boolean b() {
        try {
            e();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("ROAPManager stop error:" + e);
            return true;
        }
    }
}
