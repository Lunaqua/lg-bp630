package com.lge.media.launcher.contents;

import android.support.v4.app.NotificationCompat;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class b extends DefaultHandler {
    private c c;
    private StringBuffer a = new StringBuffer();
    private StringBuffer b = new StringBuffer();
    private StringBuffer d = new StringBuffer();
    private String e = new String();

    public b(c cVar) {
        this.c = cVar;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) {
        ArrayList<String> arrayList;
        String str;
        if (!this.e.equalsIgnoreCase("track_name")) {
            StringBuffer stringBuffer = this.b;
            stringBuffer.delete(0, stringBuffer.length());
        }
        this.b.append(cArr, i, i2);
        com.lge.media.launcher.control.common.a.c("Character : " + ((Object) this.b));
        this.e = this.a.toString();
        if (this.e.equalsIgnoreCase("title")) {
            this.c.c = new String(this.b.toString());
        } else if (this.e.equalsIgnoreCase("genre")) {
            this.c.d = new String(this.b.toString());
        } else if (this.e.equalsIgnoreCase("cover_art")) {
            this.d.append(this.b.toString());
            com.lge.media.launcher.control.common.a.b("length : " + this.d.length());
        } else if (this.e.equalsIgnoreCase("release_date")) {
            this.c.f = new String(this.b.toString());
        } else if (this.e.equalsIgnoreCase("duration")) {
            this.c.h = new String(this.b.toString());
        } else if (this.e.equalsIgnoreCase("rating")) {
            this.c.i = new String(this.b.toString());
        } else if (this.e.equalsIgnoreCase("actor")) {
            this.c.j.get(this.c.j.size() - 1).a = new String(this.b.toString());
        } else if (this.e.equalsIgnoreCase("character")) {
            this.c.j.get(this.c.j.size() - 1).b = new String(this.b.toString());
        } else {
            if (this.e.equalsIgnoreCase("producer")) {
                arrayList = this.c.k;
                str = new String(this.b.toString());
            } else if (this.e.equalsIgnoreCase("director")) {
                arrayList = this.c.l;
                str = new String(this.b.toString());
            } else if (!this.e.equalsIgnoreCase("writer")) {
                if (this.e.equalsIgnoreCase("synopsis")) {
                    this.c.n = new String(this.b.toString());
                    return;
                } else if (this.e.equalsIgnoreCase("artist")) {
                    this.c.o = new String(this.b.toString());
                    return;
                } else if (this.e.equalsIgnoreCase("artist_name")) {
                    this.c.p.get(this.c.p.size() - 1).b = new String(this.b.toString());
                    return;
                } else if (this.e.equalsIgnoreCase("track_name")) {
                    this.c.p.get(this.c.p.size() - 1).c = new String(this.b.toString());
                    return;
                } else if (this.e.equalsIgnoreCase("track_num")) {
                    this.c.p.get(this.c.p.size() - 1).a = new String(this.b.toString());
                    return;
                } else if (this.e.equalsIgnoreCase(NotificationCompat.CATEGORY_STATUS)) {
                    this.c.p.get(this.c.p.size() - 1).d = new String(this.b.toString());
                    return;
                } else {
                    return;
                }
            } else {
                arrayList = this.c.m;
                str = new String(this.b.toString());
            }
            arrayList.add(str);
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endDocument() {
        super.endDocument();
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) {
        if (this.e.equalsIgnoreCase("track_name")) {
            StringBuffer stringBuffer = this.b;
            stringBuffer.delete(0, stringBuffer.length());
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) {
        ArrayList arrayList;
        Object dVar;
        StringBuffer stringBuffer = this.a;
        stringBuffer.delete(0, stringBuffer.length());
        com.lge.media.launcher.control.common.a.c("Current LocalName Start: " + str2);
        this.a.append(str2);
        if (str2.equals("contentsInformation")) {
            if (attributes.getValue("media_type").equals("video")) {
                com.lge.media.launcher.control.common.a.b("This is BD Info");
                this.c.a = true;
                return;
            } else if (attributes.getValue("media_type").equals("audio")) {
                com.lge.media.launcher.control.common.a.b("This is CD Info");
                this.c.a = false;
                return;
            } else {
                return;
            }
        }
        if (str2.equalsIgnoreCase("cast")) {
            arrayList = this.c.j;
            dVar = new a();
        } else if (!str2.equalsIgnoreCase("track")) {
            if (str2.equalsIgnoreCase("bgm")) {
                this.c.b = true;
                return;
            }
            return;
        } else {
            arrayList = this.c.p;
            dVar = new d();
        }
        arrayList.add(dVar);
    }
}
