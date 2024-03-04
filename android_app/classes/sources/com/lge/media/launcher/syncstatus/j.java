package com.lge.media.launcher.syncstatus;

import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Base64;
import com.lge.UDAP.ROAP.e.b;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class j extends DefaultHandler {
    private k e;
    private StringBuffer a = new StringBuffer();
    private StringBuffer b = new StringBuffer();
    private StringBuffer c = new StringBuffer();
    private d d = new d();
    private String f = com.lge.media.launcher.a.d;
    private String g = com.lge.media.launcher.a.d;
    private String h = com.lge.media.launcher.a.d;
    private String i = com.lge.media.launcher.a.d;
    private String j = com.lge.media.launcher.a.d;
    private String k = com.lge.media.launcher.a.d;
    private boolean l = false;

    public j(k kVar) {
        this.e = kVar;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) {
        StringBuilder sb;
        String str;
        if (!this.f.equalsIgnoreCase(b.a.b) && !this.f.equalsIgnoreCase("now") && !this.f.equalsIgnoreCase("title") && !this.f.equalsIgnoreCase("album") && !this.f.equalsIgnoreCase("artist")) {
            StringBuffer stringBuffer = this.b;
            stringBuffer.delete(0, stringBuffer.length());
        }
        this.b.append(cArr, i, i2);
        com.lge.media.launcher.control.common.a.c("Character : " + ((Object) this.b));
        this.f = this.a.toString();
        if (this.f.equalsIgnoreCase("Type")) {
            this.e.a = this.b.toString();
            sb = new StringBuilder();
            sb.append("type : ");
            str = this.e.a;
        } else if (this.f.equalsIgnoreCase("linkedMediaType")) {
            this.e.b = this.b.toString();
            sb = new StringBuilder();
            sb.append("linkedMediaTypes : ");
            str = this.e.b;
        } else if (this.f.equalsIgnoreCase("linkedMediaName")) {
            this.e.c = this.b.toString();
            sb = new StringBuilder();
            sb.append("linkedMediaName : ");
            str = this.e.c;
        } else if (this.f.equalsIgnoreCase("mediaType")) {
            this.e.d = this.b.toString();
            sb = new StringBuilder();
            sb.append("mediaTypes : ");
            str = this.e.d;
        } else if (this.f.equalsIgnoreCase("path")) {
            this.e.h = this.b.toString();
            sb = new StringBuilder();
            sb.append("path : ");
            str = this.e.h;
        } else if (this.f.equalsIgnoreCase("numOfContents")) {
            this.e.e = this.b.toString();
            sb = new StringBuilder();
            sb.append("numOfContents : ");
            str = this.e.e;
        } else if (this.f.equalsIgnoreCase("offsetIndex")) {
            this.e.f = this.b.toString();
            sb = new StringBuilder();
            sb.append("offsetIndex : ");
            str = this.e.f;
        } else if (this.f.equalsIgnoreCase("endIndex")) {
            this.e.g = this.b.toString();
            sb = new StringBuilder();
            sb.append("endIndex : ");
            str = this.e.g;
        } else if (this.f.equalsIgnoreCase("now")) {
            this.h = this.b.toString();
            return;
        } else if (this.f.equalsIgnoreCase("title")) {
            this.i = this.b.toString();
            return;
        } else if (this.f.equalsIgnoreCase("artist")) {
            this.k = this.b.toString();
            return;
        } else if (this.f.equalsIgnoreCase("album")) {
            this.j = this.b.toString();
            return;
        } else if (this.f.equalsIgnoreCase("initTime")) {
            this.e.m = this.b.toString();
            sb = new StringBuilder();
            sb.append("initTime : ");
            str = this.e.m;
        } else if (!this.f.equalsIgnoreCase("totalTime")) {
            if (this.f.equalsIgnoreCase("coverArt")) {
                this.l = true;
                this.c.append(this.b.toString());
                com.lge.media.launcher.control.common.a.b("length : " + this.c.length());
                return;
            }
            if (this.f.equalsIgnoreCase("freq")) {
                this.e.q = this.b.toString();
            } else if (this.f.equalsIgnoreCase("band")) {
                this.e.r = this.b.toString();
            } else if (this.f.equalsIgnoreCase("preset")) {
                this.e.s = this.b.toString();
            } else if (this.f.equalsIgnoreCase("stereo")) {
                this.e.t = this.b.toString();
            } else if (this.f.equalsIgnoreCase("station")) {
                this.e.u = this.b.toString();
            } else if (this.f.equalsIgnoreCase("itemIndex")) {
                this.e.p.get(this.e.p.size() - 1).a = this.b.toString();
                sb = new StringBuilder();
                sb.append("itemIndex : ");
                str = this.d.a;
            } else if (this.f.equalsIgnoreCase(b.a.b)) {
                this.g = this.b.toString();
                return;
            } else if (this.f.equalsIgnoreCase(NotificationCompat.CATEGORY_STATUS)) {
                this.e.p.get(this.e.p.size() - 1).c = this.b.toString();
                sb = new StringBuilder();
                sb.append("itemStatus : ");
                str = this.d.c;
            } else if (this.f.equalsIgnoreCase("enable")) {
                this.e.p.get(this.e.p.size() - 1).f = this.b.toString();
                sb = new StringBuilder();
                sb.append("enable : ");
                str = this.d.f;
            } else if (this.f.equalsIgnoreCase("mdType")) {
                this.e.p.get(this.e.p.size() - 1).e = this.b.toString();
                sb = new StringBuilder();
                sb.append("mediaType : ");
                str = this.d.e;
            } else if (!this.f.equalsIgnoreCase("itemType")) {
                return;
            } else {
                this.e.p.get(this.e.p.size() - 1).d = this.b.toString();
                sb = new StringBuilder();
                sb.append("itemType : ");
                str = this.d.d;
            }
            StringBuffer stringBuffer2 = this.a;
            stringBuffer2.delete(0, stringBuffer2.length());
        } else {
            this.e.n = this.b.toString();
            sb = new StringBuilder();
            sb.append("totalTime : ");
            str = this.e.n;
        }
        sb.append(str);
        com.lge.media.launcher.control.common.a.c(sb.toString());
        StringBuffer stringBuffer22 = this.a;
        stringBuffer22.delete(0, stringBuffer22.length());
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endDocument() {
        super.endDocument();
        if (!this.l) {
            com.lge.media.launcher.control.common.a.a("parer coverArtImg is null");
            return;
        }
        this.l = false;
        com.lge.media.launcher.control.common.a.a("parer coverArtImg is not null");
        byte[] decode = Base64.decode(this.c.toString(), 0);
        this.e.o = BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) {
        if (this.f.equalsIgnoreCase(b.a.b)) {
            this.e.p.get(this.e.p.size() - 1).b = this.g;
            com.lge.media.launcher.control.common.a.c("itemIndex : " + this.d.b);
            StringBuffer stringBuffer = this.b;
            stringBuffer.delete(0, stringBuffer.length());
            this.g = com.lge.media.launcher.a.d;
        }
        if (str2.equals("now")) {
            this.e.i = this.h;
            com.lge.media.launcher.control.common.a.c("now length: " + this.h.length());
            com.lge.media.launcher.control.common.a.c("now : " + this.e.i);
            this.h = com.lge.media.launcher.a.d;
            StringBuffer stringBuffer2 = this.b;
            stringBuffer2.delete(0, stringBuffer2.length());
        }
        if (str2.equals("title")) {
            this.e.j = this.i;
            com.lge.media.launcher.control.common.a.c("title length: " + this.i.length());
            com.lge.media.launcher.control.common.a.c("title : " + this.e.j);
            this.i = com.lge.media.launcher.a.d;
            StringBuffer stringBuffer3 = this.b;
            stringBuffer3.delete(0, stringBuffer3.length());
        }
        if (str2.equals("artist")) {
            this.e.k = this.k;
            com.lge.media.launcher.control.common.a.c("artist length: " + this.k.length());
            com.lge.media.launcher.control.common.a.c("artist : " + this.e.k);
            this.k = com.lge.media.launcher.a.d;
            StringBuffer stringBuffer4 = this.b;
            stringBuffer4.delete(0, stringBuffer4.length());
        }
        if (str2.equals("album")) {
            this.e.l = this.j;
            com.lge.media.launcher.control.common.a.c("album length: " + this.j.length());
            com.lge.media.launcher.control.common.a.c("album : " + this.e.l);
            StringBuffer stringBuffer5 = this.b;
            stringBuffer5.delete(0, stringBuffer5.length());
            this.j = com.lge.media.launcher.a.d;
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) {
        StringBuffer stringBuffer = this.a;
        stringBuffer.delete(0, stringBuffer.length());
        com.lge.media.launcher.control.common.a.c("Current LocalName Start: " + str2);
        this.a.append(str2);
        if (str2.equalsIgnoreCase("item")) {
            this.e.p.add(new d());
        }
        StringBuffer stringBuffer2 = this.b;
        stringBuffer2.delete(0, stringBuffer2.length());
    }
}
