package com.lge.media.launcher.YouTubeSearch;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import com.lge.media.launcher.b;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class YoutubeSearchOptionDialog extends Dialog {
    public static final int OPTION_COUNT = 4;
    Button btnCancel;
    Button btnOk;
    CheckBox[] cbOptions;
    private int checkedSortOption;
    private DialogOnClickListener listener;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface DialogOnClickListener {
        void negativeButtonOnClickListener(int i);

        void positiveButtonOnClickListener(int i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public YoutubeSearchOptionDialog(Context context, int i) {
        super(context, i);
        initView(context);
    }

    private void initView(Context context) {
        setContentView(b.j.youtube_menu_popup);
        this.cbOptions = new CheckBox[4];
        this.cbOptions[0] = (CheckBox) findViewById(b.h.cb_youtube_sort_1);
        this.cbOptions[1] = (CheckBox) findViewById(b.h.cb_youtube_sort_2);
        this.cbOptions[2] = (CheckBox) findViewById(b.h.cb_youtube_sort_3);
        this.cbOptions[3] = (CheckBox) findViewById(b.h.cb_youtube_sort_4);
        this.btnOk = (Button) findViewById(b.h.btn_popup_one);
        this.btnCancel = (Button) findViewById(b.h.btn_popup_two);
        setOptionCheck(0);
        this.cbOptions[0].setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YoutubeSearchOptionDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                YoutubeSearchOptionDialog.this.setOptionCheck(0);
            }
        });
        this.cbOptions[1].setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YoutubeSearchOptionDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                YoutubeSearchOptionDialog.this.setOptionCheck(1);
            }
        });
        this.cbOptions[2].setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YoutubeSearchOptionDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                YoutubeSearchOptionDialog.this.setOptionCheck(2);
            }
        });
        this.cbOptions[3].setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YoutubeSearchOptionDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                YoutubeSearchOptionDialog.this.setOptionCheck(3);
            }
        });
        this.btnOk.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YoutubeSearchOptionDialog.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (YoutubeSearchOptionDialog.this.listener != null) {
                    YoutubeSearchOptionDialog.this.listener.positiveButtonOnClickListener(YoutubeSearchOptionDialog.this.checkedSortOption);
                    YoutubeSearchOptionDialog.this.dismiss();
                }
            }
        });
        this.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YoutubeSearchOptionDialog.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                YoutubeSearchOptionDialog.this.cancel();
                if (YoutubeSearchOptionDialog.this.listener != null) {
                    YoutubeSearchOptionDialog.this.listener.negativeButtonOnClickListener(YoutubeSearchOptionDialog.this.checkedSortOption);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOptionCheck(int i) {
        int i2 = 0;
        while (true) {
            CheckBox[] checkBoxArr = this.cbOptions;
            if (i2 >= checkBoxArr.length) {
                return;
            }
            if (i2 == i) {
                checkBoxArr[i2].setChecked(true);
                this.checkedSortOption = i2;
            } else {
                checkBoxArr[i2].setChecked(false);
            }
            i2++;
        }
    }

    public void clear() {
        setOptionCheck(0);
    }

    public void setListener(DialogOnClickListener dialogOnClickListener) {
        this.listener = dialogOnClickListener;
    }

    public void show(int i) {
        setOptionCheck(i);
        show();
    }
}
