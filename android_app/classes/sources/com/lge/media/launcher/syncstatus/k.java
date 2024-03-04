package com.lge.media.launcher.syncstatus;

import android.graphics.Bitmap;
import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class k {
    public Bitmap o;
    public String a = com.lge.media.launcher.a.d;
    public String b = com.lge.media.launcher.a.d;
    public String c = com.lge.media.launcher.a.d;
    public String d = com.lge.media.launcher.a.d;
    public String e = com.lge.media.launcher.a.d;
    public String f = com.lge.media.launcher.a.d;
    public String g = com.lge.media.launcher.a.d;
    public String h = com.lge.media.launcher.a.d;
    public String i = com.lge.media.launcher.a.d;
    public String j = com.lge.media.launcher.a.d;
    public String k = com.lge.media.launcher.a.d;
    public String l = com.lge.media.launcher.a.d;
    public String m = com.lge.media.launcher.a.d;
    public String n = com.lge.media.launcher.a.d;
    public ArrayList<d> p = new ArrayList<>();
    public String q = com.lge.media.launcher.a.d;
    public String r = com.lge.media.launcher.a.d;
    public String s = com.lge.media.launcher.a.d;
    public String t = com.lge.media.launcher.a.d;
    public String u = com.lge.media.launcher.a.d;

    public void a(String str, String str2) {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        if (str != null && !str.contains("15y")) {
            str2 = str2.replaceAll("&", "&amp;");
        }
        com.lge.media.launcher.control.common.a.a("sync info XML : " + str2);
        try {
            XMLReader xMLReader = newInstance.newSAXParser().getXMLReader();
            xMLReader.setContentHandler(new j(this));
            com.lge.media.launcher.control.common.a.b("Sync info parse starts...");
            CharBuffer wrap = CharBuffer.wrap(str2);
            String str3 = com.lge.media.launcher.a.d;
            while (wrap.hasRemaining()) {
                str3 = str3 + wrap.get();
            }
            xMLReader.parse(new InputSource(new StringReader(str3)));
            com.lge.media.launcher.control.common.a.b("Sync info parse ended...");
        } catch (IOException e) {
            e.printStackTrace();
            com.lge.media.launcher.control.common.a.b("Sync info parse error...");
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
        } catch (SAXException e3) {
            e3.printStackTrace();
        }
    }
}
