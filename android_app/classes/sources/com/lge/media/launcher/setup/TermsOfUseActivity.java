package com.lge.media.launcher.setup;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.d;
import java.util.Locale;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class TermsOfUseActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(b.j.activity_terms_of_use);
        final SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", 0);
        TextView textView = (TextView) findViewById(16908310);
        textView.setText(b.m.setup_terms_of_use_title);
        textView.setContentDescription(getString(b.m.setup_terms_of_use_title) + getString(b.m.label_title) + "1");
        Button button = (Button) findViewById(16908313);
        button.setText(b.m.setup_terms_of_use_decline);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.setup.TermsOfUseActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                sharedPreferences.edit().putBoolean(d.bs, false).apply();
                TermsOfUseActivity.this.setResult(0);
                TermsOfUseActivity.this.finish();
            }
        });
        Button button2 = (Button) findViewById(16908314);
        button2.setText(b.m.setup_terms_of_use_accept);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.setup.TermsOfUseActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                sharedPreferences.edit().putBoolean(d.bs, true).apply();
                TermsOfUseActivity.this.setResult(-1);
                TermsOfUseActivity.this.finish();
            }
        });
        TextView textView2 = (TextView) findViewById(b.h.tou_13);
        TextView textView3 = (TextView) findViewById(b.h.tou_14);
        TextView textView4 = (TextView) findViewById(b.h.tou_15);
        TextView textView5 = (TextView) findViewById(b.h.tou_16);
        TextView textView6 = (TextView) findViewById(b.h.tou_17);
        int i = Locale.getDefault().getLanguage().equalsIgnoreCase("ko") ? 8 : 0;
        textView2.setVisibility(i);
        textView3.setVisibility(i);
        textView4.setVisibility(i);
        textView5.setVisibility(i);
        textView6.setVisibility(i);
    }
}
