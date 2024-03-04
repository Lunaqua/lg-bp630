package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.support.annotation.ak;
import android.view.Gravity;
@ak(a = 21)
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class c extends b {
    /* JADX INFO: Access modifiers changed from: protected */
    public c(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    @Override // android.support.v4.graphics.drawable.b
    void a(int i, int i2, int i3, Rect rect, Rect rect2) {
        Gravity.apply(i, i2, i3, rect, rect2, 0);
    }

    @Override // android.support.v4.graphics.drawable.b
    public void a(boolean z) {
        if (this.a != null) {
            this.a.setHasMipMap(z);
            invalidateSelf();
        }
    }

    @Override // android.support.v4.graphics.drawable.b
    public boolean d() {
        return this.a != null && this.a.hasMipMap();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        f();
        outline.setRoundRect(this.b, h());
    }
}
