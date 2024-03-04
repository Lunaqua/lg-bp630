package android.support.v7.e;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.an;
import android.text.method.TransformationMethod;
import android.view.View;
import java.util.Locale;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a implements TransformationMethod {
    private Locale a;

    public a(Context context) {
        this.a = context.getResources().getConfiguration().locale;
    }

    @Override // android.text.method.TransformationMethod
    public CharSequence getTransformation(CharSequence charSequence, View view) {
        if (charSequence != null) {
            return charSequence.toString().toUpperCase(this.a);
        }
        return null;
    }

    @Override // android.text.method.TransformationMethod
    public void onFocusChanged(View view, CharSequence charSequence, boolean z, int i, Rect rect) {
    }
}
