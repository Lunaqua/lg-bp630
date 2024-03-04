package com.lge.media.launcher.cplist;

import android.graphics.BitmapFactory;
import android.util.Base64;
import com.lge.UDAP.ROAP.e.b;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c {
    public List<a> a = new ArrayList();

    public synchronized void a(String str) {
        String str2;
        this.a.clear();
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(new StringReader(str.replaceAll("(\\r|\\n|\\t)", com.lge.media.launcher.a.d)));
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType != 0 && eventType != 1) {
                    if (eventType == 2) {
                        if (newPullParser.getName().equalsIgnoreCase("item")) {
                            this.a.add(new a());
                        } else if (newPullParser.getName().equalsIgnoreCase("itemIndex")) {
                            z = true;
                            z2 = false;
                            z3 = false;
                        } else if (newPullParser.getName().equalsIgnoreCase(b.a.b)) {
                            z = false;
                            z2 = true;
                            z3 = false;
                        } else if (newPullParser.getName().equalsIgnoreCase("thumbnail")) {
                            z = false;
                            z2 = false;
                            z3 = true;
                        }
                        z = false;
                        z2 = false;
                        z3 = false;
                    } else if (eventType != 3 && eventType == 4) {
                        com.lge.media.launcher.control.common.a.c("Character : " + newPullParser.getText());
                        if (z) {
                            this.a.get(this.a.size() - 1).a(newPullParser.getText());
                            str2 = "itemIndex : " + this.a.get(this.a.size() - 1).a();
                        } else if (z2) {
                            this.a.get(this.a.size() - 1).b(newPullParser.getText());
                            str2 = "itemName : " + this.a.get(this.a.size() - 1).b();
                        } else if (z3) {
                            try {
                                byte[] decode = Base64.decode(newPullParser.getText(), 0);
                                this.a.get(this.a.size() - 1).a(BitmapFactory.decodeByteArray(decode, 0, decode.length));
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            }
                        }
                        com.lge.media.launcher.control.common.a.c(str2);
                    }
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }
}
