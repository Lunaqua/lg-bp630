package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ag;
import android.support.annotation.v;
import android.view.View;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class FragmentContainer {
    public Fragment instantiate(Context context, String str, Bundle bundle) {
        return Fragment.instantiate(context, str, bundle);
    }

    @ag
    public abstract View onFindViewById(@v int i);

    public abstract boolean onHasView();
}
