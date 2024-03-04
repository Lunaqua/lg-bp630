package com.lge.media.launcher.a;

import android.support.v4.app.NotificationCompat;
import com.lge.UDAP.ROAP.e.b;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a {
    public List<b> a = new ArrayList();
    public boolean b = false;

    public void a(String str) {
        String str2;
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(new StringReader(str));
            int eventType = newPullParser.getEventType();
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            boolean z5 = false;
            boolean z6 = false;
            while (true) {
                boolean z7 = true;
                if (eventType == 1) {
                    return;
                }
                if (eventType != 0 && eventType != 1) {
                    if (eventType == 2) {
                        com.lge.media.launcher.control.common.a.c("START_TAG name : " + newPullParser.getName());
                        if (newPullParser.getName().equalsIgnoreCase("item")) {
                            this.a.add(new b());
                        } else if (newPullParser.getName().equalsIgnoreCase("itemIndex")) {
                            z = true;
                            z2 = false;
                            z3 = false;
                            z4 = false;
                            z5 = false;
                            z6 = false;
                        } else if (newPullParser.getName().equalsIgnoreCase(b.a.b)) {
                            z = false;
                            z2 = true;
                            z3 = false;
                            z4 = false;
                            z5 = false;
                            z6 = false;
                        } else if (newPullParser.getName().equalsIgnoreCase(NotificationCompat.CATEGORY_STATUS)) {
                            z = false;
                            z2 = false;
                            z3 = true;
                            z4 = false;
                            z5 = false;
                            z6 = false;
                        } else if (newPullParser.getName().equalsIgnoreCase("mute")) {
                            z = false;
                            z2 = false;
                            z3 = false;
                            z4 = true;
                            z5 = false;
                            z6 = false;
                        } else if (newPullParser.getName().equalsIgnoreCase("mono")) {
                            z = false;
                            z2 = false;
                            z3 = false;
                            z4 = false;
                            z5 = true;
                            z6 = false;
                        } else if (newPullParser.getName().equalsIgnoreCase("channel")) {
                            z = false;
                            z2 = false;
                            z3 = false;
                            z4 = false;
                            z5 = false;
                            z6 = true;
                        }
                        z = false;
                        z2 = false;
                        z3 = false;
                        z4 = false;
                        z5 = false;
                        z6 = false;
                    } else if (eventType == 3) {
                        continue;
                    } else {
                        if (eventType != 4) {
                            str2 = "default. eventType : " + eventType + " , data : " + newPullParser.getText();
                        } else {
                            com.lge.media.launcher.control.common.a.c("Character : " + newPullParser.getText());
                            if (z) {
                                this.a.get(this.a.size() - 1).a(newPullParser.getText());
                                str2 = "itemIndex : " + this.a.get(this.a.size() - 1).a();
                            } else if (z2) {
                                this.a.get(this.a.size() - 1).b(newPullParser.getText());
                                str2 = "itemName : " + this.a.get(this.a.size() - 1).b();
                            } else if (z3) {
                                String text = newPullParser.getText();
                                this.a.get(this.a.size() - 1).a(!text.isEmpty() && Integer.parseInt(text) == 1);
                                str2 = "status : " + this.a.get(this.a.size() - 1).c();
                            } else if (z4) {
                                String text2 = newPullParser.getText();
                                if (text2.isEmpty() || Integer.parseInt(text2) != 1) {
                                    z7 = false;
                                }
                                this.b = z7;
                                str2 = "mute : " + text2;
                            } else if (z5) {
                                String text3 = newPullParser.getText();
                                if ("Y".equals(text3)) {
                                    this.a.get(this.a.size() - 1).b(true);
                                } else {
                                    this.a.get(this.a.size() - 1).b(false);
                                }
                                str2 = "mono : " + text3;
                            } else if (z6) {
                                String text4 = newPullParser.getText();
                                this.a.get(this.a.size() - 1).c(newPullParser.getText());
                                str2 = "channel : " + text4;
                            }
                        }
                        com.lge.media.launcher.control.common.a.c(str2);
                    }
                }
                eventType = newPullParser.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }
}
