package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.util.Log;
import java.io.InputStream;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class d {
    private static final String a = "RoundedBitmapDrawableFa";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a extends b {
        a(Resources resources, Bitmap bitmap) {
            super(resources, bitmap);
        }

        @Override // android.support.v4.graphics.drawable.b
        void a(int i, int i2, int i3, Rect rect, Rect rect2) {
            android.support.v4.view.f.a(i, i2, i3, rect, rect2, 0);
        }

        @Override // android.support.v4.graphics.drawable.b
        public void a(boolean z) {
            if (this.a != null) {
                android.support.v4.graphics.a.a(this.a, z);
                invalidateSelf();
            }
        }

        @Override // android.support.v4.graphics.drawable.b
        public boolean d() {
            return this.a != null && android.support.v4.graphics.a.a(this.a);
        }
    }

    private d() {
    }

    @af
    public static b a(@af Resources resources, @ag Bitmap bitmap) {
        return Build.VERSION.SDK_INT >= 21 ? new c(resources, bitmap) : new a(resources, bitmap);
    }

    @af
    public static b a(@af Resources resources, @af InputStream inputStream) {
        b a2 = a(resources, BitmapFactory.decodeStream(inputStream));
        if (a2.b() == null) {
            Log.w(a, "RoundedBitmapDrawable cannot decode " + inputStream);
        }
        return a2;
    }

    @af
    public static b a(@af Resources resources, @af String str) {
        b a2 = a(resources, BitmapFactory.decodeFile(str));
        if (a2.b() == null) {
            Log.w(a, "RoundedBitmapDrawable cannot decode " + str);
        }
        return a2;
    }
}
