package android.support.v4.content.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.v4.graphics.drawable.IconCompat;
import android.text.TextUtils;
import java.util.Arrays;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d {
    Context a;
    String b;
    Intent[] c;
    ComponentName d;
    CharSequence e;
    CharSequence f;
    CharSequence g;
    IconCompat h;
    boolean i;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        private final d a = new d();

        public a(@af Context context, @af String str) {
            d dVar = this.a;
            dVar.a = context;
            dVar.b = str;
        }

        public a a() {
            this.a.i = true;
            return this;
        }

        @af
        public a a(@af ComponentName componentName) {
            this.a.d = componentName;
            return this;
        }

        @af
        public a a(@af Intent intent) {
            return a(new Intent[]{intent});
        }

        @af
        public a a(IconCompat iconCompat) {
            this.a.h = iconCompat;
            return this;
        }

        @af
        public a a(@af CharSequence charSequence) {
            this.a.e = charSequence;
            return this;
        }

        @af
        public a a(@af Intent[] intentArr) {
            this.a.c = intentArr;
            return this;
        }

        @af
        public a b(@af CharSequence charSequence) {
            this.a.f = charSequence;
            return this;
        }

        @af
        public d b() {
            if (TextUtils.isEmpty(this.a.e)) {
                throw new IllegalArgumentException("Shortcut must have a non-empty label");
            }
            if (this.a.c == null || this.a.c.length == 0) {
                throw new IllegalArgumentException("Shortcut must have an intent");
            }
            return this.a;
        }

        @af
        public a c(@af CharSequence charSequence) {
            this.a.g = charSequence;
            return this;
        }
    }

    d() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Intent a(Intent intent) {
        Intent[] intentArr = this.c;
        intent.putExtra("android.intent.extra.shortcut.INTENT", intentArr[intentArr.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.e.toString());
        if (this.h != null) {
            Drawable drawable = null;
            if (this.i) {
                PackageManager packageManager = this.a.getPackageManager();
                ComponentName componentName = this.d;
                if (componentName != null) {
                    try {
                        drawable = packageManager.getActivityIcon(componentName);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                if (drawable == null) {
                    drawable = this.a.getApplicationInfo().loadIcon(packageManager);
                }
            }
            this.h.a(intent, drawable, this.a);
        }
        return intent;
    }

    @ak(a = 25)
    public ShortcutInfo a() {
        ShortcutInfo.Builder intents = new ShortcutInfo.Builder(this.a, this.b).setShortLabel(this.e).setIntents(this.c);
        IconCompat iconCompat = this.h;
        if (iconCompat != null) {
            intents.setIcon(iconCompat.e());
        }
        if (!TextUtils.isEmpty(this.f)) {
            intents.setLongLabel(this.f);
        }
        if (!TextUtils.isEmpty(this.g)) {
            intents.setDisabledMessage(this.g);
        }
        ComponentName componentName = this.d;
        if (componentName != null) {
            intents.setActivity(componentName);
        }
        return intents.build();
    }

    @af
    public String b() {
        return this.b;
    }

    @ag
    public ComponentName c() {
        return this.d;
    }

    @af
    public CharSequence d() {
        return this.e;
    }

    @ag
    public CharSequence e() {
        return this.f;
    }

    @ag
    public CharSequence f() {
        return this.g;
    }

    @af
    public Intent g() {
        Intent[] intentArr = this.c;
        return intentArr[intentArr.length - 1];
    }

    @af
    public Intent[] h() {
        Intent[] intentArr = this.c;
        return (Intent[]) Arrays.copyOf(intentArr, intentArr.length);
    }
}
