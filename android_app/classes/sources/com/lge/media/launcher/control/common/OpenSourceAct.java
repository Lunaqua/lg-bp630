package com.lge.media.launcher.control.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.TextView;
import com.lge.media.launcher.b;
import com.lge.media.launcher.ui.ScrollViewFastScroller;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class OpenSourceAct extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(b.j.open_source);
        ((TextView) findViewById(b.h.text_top_title)).setContentDescription(getString(b.m.open_source_licence) + getString(b.m.label_title) + "1");
        findViewById(b.h.button_back).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.OpenSourceAct.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OpenSourceAct.this.finish();
            }
        });
        NestedScrollView nestedScrollView = (NestedScrollView) findViewById(b.h.scroll_view);
        nestedScrollView.setVerticalScrollBarEnabled(false);
        ((ScrollViewFastScroller) findViewById(b.h.fast_scroller)).setScrollView(nestedScrollView);
    }
}
