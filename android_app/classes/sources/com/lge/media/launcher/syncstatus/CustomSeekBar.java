package com.lge.media.launcher.syncstatus;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class CustomSeekBar extends AppCompatSeekBar {
    public CustomSeekBar(Context context) {
        super(context);
    }

    public CustomSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEvent(int i) {
        if (i != 4) {
            super.sendAccessibilityEvent(i);
        }
    }
}
