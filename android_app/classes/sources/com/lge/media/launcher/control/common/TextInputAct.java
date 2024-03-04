package com.lge.media.launcher.control.common;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.lge.media.launcher.b;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class TextInputAct extends Activity {
    public EditText a;
    public e b;
    private com.lge.UDAP.ROAP.b e = null;
    protected boolean c = false;
    private boolean f = false;
    public Handler d = new Handler() { // from class: com.lge.media.launcher.control.common.TextInputAct.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            a.b("tHandler");
            int i = message.what;
            if (i == 25) {
                a.c("Handler msg is SYNC_SS_VIEW_CHANGE");
                TextInputAct.this.b.i = com.lge.media.launcher.a.d;
                TextInputAct.this.b.e = false;
                TextInputAct.this.finish();
            } else if (i == 28) {
                a.c("Handler msg is SYNC_INFORMATION_READY");
            } else if (i != 40) {
                super.handleMessage(message);
            } else {
                a.c("Handler msg is TEXT_INPUT_EVENT_ON");
                TextInputAct.this.a.setText(TextInputAct.this.b.h);
                TextInputAct.this.a.setSelection(TextInputAct.this.a.length());
            }
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a.a("Text Input Create..");
        setContentView(b.j.textinput);
        this.c = getIntent().getBooleanExtra(d.Q, false);
        this.b = e.a(this, this.d);
        this.e = this.b.p();
        ((Button) findViewById(b.h.btn_delete)).setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.control.common.TextInputAct.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.d();
                TextInputAct.this.a.setText(com.lge.media.launcher.a.d);
            }
        });
        this.a = (EditText) findViewById(b.h.text_field);
        if (!this.b.h.equals(this.a.getText().toString())) {
            this.a.setText(this.b.h);
        }
        this.a.addTextChangedListener(new TextWatcher() { // from class: com.lge.media.launcher.control.common.TextInputAct.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                a.b("key down " + TextInputAct.this.a.getText().toString());
                f.d();
                TextInputAct.this.e.b.a(TextInputAct.this.a.getText().toString());
                TextInputAct.this.b.i = TextInputAct.this.a.getText().toString();
                a.c("phoneInputText : " + TextInputAct.this.a.getText().toString());
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.a.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.lge.media.launcher.control.common.TextInputAct.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    a.b("done. " + TextInputAct.this.a.getText().toString());
                    f.d();
                    TextInputAct.this.e.b.b(TextInputAct.this.a.getText().toString());
                    ((InputMethodManager) TextInputAct.this.getSystemService("input_method")).hideSoftInputFromWindow(TextInputAct.this.a.getWindowToken(), 0);
                    TextInputAct.this.b.e = false;
                    TextInputAct.this.finish();
                    return true;
                }
                return false;
            }
        });
        if (bundle != null) {
            this.f = true;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        a.b("TextinputAct onDestroy");
        this.b.g = true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        a.b("Press backbtn");
        if (i == 4) {
            e eVar = this.b;
            eVar.e = false;
            eVar.g = !this.f;
            finish();
        }
        return false;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        a.b("TextinputAct onResume");
    }
}
