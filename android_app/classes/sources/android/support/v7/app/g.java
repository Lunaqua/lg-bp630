package android.support.v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.an;
import android.support.v4.app.DialogFragment;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class g extends DialogFragment {
    @Override // android.support.v4.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return new f(getContext(), getTheme());
    }

    @Override // android.support.v4.app.DialogFragment
    @an(a = {an.a.LIBRARY_GROUP})
    public void setupDialog(Dialog dialog, int i) {
        if (!(dialog instanceof f)) {
            super.setupDialog(dialog, i);
            return;
        }
        f fVar = (f) dialog;
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            }
            dialog.getWindow().addFlags(24);
        }
        fVar.e(1);
    }
}
