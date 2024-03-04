package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class b extends Drawable {
    final ActionBarContainer a;

    public b(ActionBarContainer actionBarContainer) {
        this.a = actionBarContainer;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable;
        if (!this.a.d) {
            if (this.a.a != null) {
                this.a.a.draw(canvas);
            }
            if (this.a.b == null || !this.a.e) {
                return;
            }
            drawable = this.a.b;
        } else if (this.a.c == null) {
            return;
        } else {
            drawable = this.a.c;
        }
        drawable.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    @android.support.annotation.ak(a = 21)
    public void getOutline(@android.support.annotation.af Outline outline) {
        Drawable drawable;
        if (this.a.d) {
            if (this.a.c == null) {
                return;
            }
            drawable = this.a.c;
        } else if (this.a.a == null) {
            return;
        } else {
            drawable = this.a.a;
        }
        drawable.getOutline(outline);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
