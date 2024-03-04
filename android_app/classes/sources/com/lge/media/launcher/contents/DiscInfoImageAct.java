package com.lge.media.launcher.contents;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.e;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class DiscInfoImageAct extends Activity {
    private e a;
    private ImageView b;
    private TextView c;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(b.j.disc_info_image);
        this.a = e.a(this, (Handler) null);
        this.b = (ImageView) findViewById(b.h.img_image);
        this.c = (TextView) findViewById(b.h.text_top_title);
        this.c.setText(this.a.r().c);
        TextView textView = this.c;
        textView.setContentDescription(this.a.r().c + getString(b.m.label_title) + "1");
        if (this.a.r().g != null) {
            this.b.setImageBitmap(this.a.r().g);
            this.b.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        findViewById(b.h.button_back).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.contents.DiscInfoImageAct.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DiscInfoImageAct.this.finish();
            }
        });
    }
}
