package com.lge.media.launcher.contents;

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
public class c {
    public boolean a;
    public String c;
    public String d;
    public String f;
    public Bitmap g;
    public String h;
    public String i;
    public String n;
    public String o;
    public boolean b = false;
    public String e = null;
    public ArrayList<a> j = new ArrayList<>();
    public ArrayList<String> k = new ArrayList<>();
    public ArrayList<String> l = new ArrayList<>();
    public ArrayList<String> m = new ArrayList<>();
    public ArrayList<d> p = new ArrayList<>();

    public void a(String str) {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        com.lge.media.launcher.control.common.a.a("disc info XML : " + str);
        try {
            XMLReader xMLReader = newInstance.newSAXParser().getXMLReader();
            xMLReader.setContentHandler(new b(this));
            com.lge.media.launcher.control.common.a.b("Disc info parse starts...");
            CharBuffer wrap = CharBuffer.wrap(str);
            String str2 = com.lge.media.launcher.a.d;
            while (wrap.hasRemaining()) {
                char c = wrap.get();
                if (c == '&') {
                    str2 = str2 + "&amp;";
                } else {
                    str2 = str2 + c;
                }
            }
            xMLReader.parse(new InputSource(new StringReader(str2)));
            com.lge.media.launcher.control.common.a.b("Disc info parse ended...");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
        } catch (SAXException e3) {
            e3.printStackTrace();
        }
    }
}
