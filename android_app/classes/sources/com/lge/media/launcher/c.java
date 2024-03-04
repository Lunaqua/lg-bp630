package com.lge.media.launcher;

import android.content.Context;
import android.support.annotation.ak;
import android.support.v4.view.ab;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;
import com.lge.media.launcher.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c {
    public static String a(Context context, int i) {
        int i2 = i / 3600;
        int i3 = (i % 3600) / 60;
        int i4 = i % 60;
        StringBuilder sb = new StringBuilder();
        if (i2 > 0) {
            sb.append(i2);
            sb.append(context.getString(i2 > 1 ? b.m.label_more_than_one_hour : b.m.label_hour));
            if (i3 < 10) {
                sb.append("0");
            }
        }
        if (i3 > 0) {
            sb.append(i3);
            sb.append(context.getString(i3 > 1 ? b.m.label_more_than_one_minute : b.m.label_minute));
        }
        sb.append(i4);
        sb.append(context.getString(i4 > 1 ? b.m.label_more_than_one_second : b.m.label_second));
        return sb.toString();
    }

    public static void a(View view) {
        ab.e(view, 2);
    }

    public static void a(final View view, int i) {
        Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() { // from class: com.lge.media.launcher.c.1
            @Override // java.lang.Runnable
            public void run() {
                view.sendAccessibilityEvent(8);
            }
        }, i, TimeUnit.MILLISECONDS);
    }

    public static void a(View view, final String str) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.c.3
            @Override // android.view.View.AccessibilityDelegate
            @ak(b = 16)
            public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfo);
                ArrayList<View> arrayList = new ArrayList<>();
                view2.addChildrenForAccessibility(arrayList);
                StringBuilder sb = new StringBuilder();
                Iterator<View> it = arrayList.iterator();
                while (it.hasNext()) {
                    View next = it.next();
                    if (next instanceof TextView) {
                        sb.append(((TextView) next).getText());
                        sb.append(",");
                    }
                }
                sb.append(str);
                accessibilityNodeInfo.setContentDescription(sb.toString());
            }
        });
    }

    public static void a(View view, boolean z) {
        ab.e(view, z ? 0 : 4);
    }

    public static void b(View view) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.c.2
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfo);
                accessibilityNodeInfo.setClassName(Button.class.getName());
            }
        });
    }
}
