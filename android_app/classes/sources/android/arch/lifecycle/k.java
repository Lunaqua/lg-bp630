package android.arch.lifecycle;

import android.support.annotation.an;
import java.util.HashMap;
import java.util.Map;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class k {
    private Map<String, Integer> a = new HashMap();

    @an(a = {an.a.LIBRARY_GROUP})
    public boolean a(String str, int i) {
        Integer num = this.a.get(str);
        int intValue = num != null ? num.intValue() : 0;
        boolean z = (intValue & i) != 0;
        this.a.put(str, Integer.valueOf(i | intValue));
        return !z;
    }
}
