package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.support.annotation.an;
import android.support.v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.e;
@an(a = {an.a.LIBRARY})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(e eVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.b = eVar.b(iconCompat.b, 1);
        iconCompat.d = eVar.b(iconCompat.d, 2);
        iconCompat.e = eVar.b((e) iconCompat.e, 3);
        iconCompat.f = eVar.b(iconCompat.f, 4);
        iconCompat.g = eVar.b(iconCompat.g, 5);
        iconCompat.h = (ColorStateList) eVar.b((e) iconCompat.h, 6);
        iconCompat.k = eVar.b(iconCompat.k, 7);
        iconCompat.g();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, e eVar) {
        eVar.a(true, true);
        iconCompat.a(eVar.a());
        eVar.a(iconCompat.b, 1);
        eVar.a(iconCompat.d, 2);
        eVar.a(iconCompat.e, 3);
        eVar.a(iconCompat.f, 4);
        eVar.a(iconCompat.g, 5);
        eVar.a(iconCompat.h, 6);
        eVar.a(iconCompat.k, 7);
    }
}
