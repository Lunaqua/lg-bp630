package android.support.k.a;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.support.annotation.af;
import android.support.annotation.ak;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface b extends Animatable {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class a {
        Animatable2.AnimationCallback a;

        /* JADX INFO: Access modifiers changed from: package-private */
        @ak(a = 23)
        public Animatable2.AnimationCallback a() {
            if (this.a == null) {
                this.a = new Animatable2.AnimationCallback() { // from class: android.support.k.a.b.a.1
                    @Override // android.graphics.drawable.Animatable2.AnimationCallback
                    public void onAnimationEnd(Drawable drawable) {
                        a.this.b(drawable);
                    }

                    @Override // android.graphics.drawable.Animatable2.AnimationCallback
                    public void onAnimationStart(Drawable drawable) {
                        a.this.a(drawable);
                    }
                };
            }
            return this.a;
        }

        public void a(Drawable drawable) {
        }

        public void b(Drawable drawable) {
        }
    }

    void a();

    void a(@af a aVar);

    boolean b(@af a aVar);
}
