package android.support.v4.widget;

import android.content.Context;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.util.AttributeSet;
import android.widget.ProgressBar;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {
    private static final int e = 500;
    private static final int f = 500;
    long a;
    boolean b;
    boolean c;
    boolean d;
    private final Runnable g;
    private final Runnable h;

    public ContentLoadingProgressBar(@af Context context) {
        this(context, null);
    }

    public ContentLoadingProgressBar(@af Context context, @ag AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.a = -1L;
        this.b = false;
        this.c = false;
        this.d = false;
        this.g = new Runnable() { // from class: android.support.v4.widget.ContentLoadingProgressBar.1
            @Override // java.lang.Runnable
            public void run() {
                ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
                contentLoadingProgressBar.b = false;
                contentLoadingProgressBar.a = -1L;
                contentLoadingProgressBar.setVisibility(8);
            }
        };
        this.h = new Runnable() { // from class: android.support.v4.widget.ContentLoadingProgressBar.2
            @Override // java.lang.Runnable
            public void run() {
                ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
                contentLoadingProgressBar.c = false;
                if (contentLoadingProgressBar.d) {
                    return;
                }
                ContentLoadingProgressBar.this.a = System.currentTimeMillis();
                ContentLoadingProgressBar.this.setVisibility(0);
            }
        };
    }

    private void c() {
        removeCallbacks(this.g);
        removeCallbacks(this.h);
    }

    public synchronized void a() {
        this.d = true;
        removeCallbacks(this.h);
        this.c = false;
        long currentTimeMillis = System.currentTimeMillis() - this.a;
        if (currentTimeMillis < 500 && this.a != -1) {
            if (!this.b) {
                postDelayed(this.g, 500 - currentTimeMillis);
                this.b = true;
            }
        }
        setVisibility(8);
    }

    public synchronized void b() {
        this.a = -1L;
        this.d = false;
        removeCallbacks(this.g);
        this.b = false;
        if (!this.c) {
            postDelayed(this.h, 500L);
            this.c = true;
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        c();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }
}
