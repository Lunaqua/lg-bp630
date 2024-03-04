package android.support.v7.widget;

import android.content.Context;
import android.os.Build;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class AppCompatPopupWindow extends PopupWindow {
    private static final boolean a;
    private boolean b;

    static {
        a = Build.VERSION.SDK_INT < 21;
    }

    public AppCompatPopupWindow(@android.support.annotation.af Context context, @android.support.annotation.ag AttributeSet attributeSet, @android.support.annotation.f int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i, 0);
    }

    public AppCompatPopupWindow(@android.support.annotation.af Context context, @android.support.annotation.ag AttributeSet attributeSet, @android.support.annotation.f int i, @android.support.annotation.ar int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    private void a(Context context, AttributeSet attributeSet, int i, int i2) {
        av a2 = av.a(context, attributeSet, a.l.PopupWindow, i, i2);
        if (a2.j(a.l.PopupWindow_overlapAnchor)) {
            a(a2.a(a.l.PopupWindow_overlapAnchor, false));
        }
        setBackgroundDrawable(a2.a(a.l.PopupWindow_android_popupBackground));
        a2.e();
    }

    private void a(boolean z) {
        if (a) {
            this.b = z;
        } else {
            android.support.v4.widget.q.a(this, z);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        if (a && this.b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (a && this.b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        if (a && this.b) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }
}
