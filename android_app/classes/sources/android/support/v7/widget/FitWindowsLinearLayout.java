package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.an;
import android.support.v7.widget.v;
import android.util.AttributeSet;
import android.widget.LinearLayout;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class FitWindowsLinearLayout extends LinearLayout implements v {
    private v.a a;

    public FitWindowsLinearLayout(Context context) {
        super(context);
    }

    public FitWindowsLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        v.a aVar = this.a;
        if (aVar != null) {
            aVar.a(rect);
        }
        return super.fitSystemWindows(rect);
    }

    @Override // android.support.v7.widget.v
    public void setOnFitSystemWindowsListener(v.a aVar) {
        this.a = aVar;
    }
}
