package android.support.v7.view.menu;

import android.graphics.drawable.Drawable;
import android.support.annotation.an;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface q {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        void a(k kVar, int i);

        void a(boolean z, char c);

        boolean a();

        boolean c();

        k getItemData();

        void setCheckable(boolean z);

        void setChecked(boolean z);

        void setEnabled(boolean z);

        void setIcon(Drawable drawable);

        void setTitle(CharSequence charSequence);
    }

    void a(h hVar);

    int getWindowAnimations();
}
