package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.SpinnerAdapter;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface ar extends SpinnerAdapter {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class a {
        private final Context a;
        private final LayoutInflater b;
        private LayoutInflater c;

        public a(@android.support.annotation.af Context context) {
            this.a = context;
            this.b = LayoutInflater.from(context);
        }

        @android.support.annotation.ag
        public Resources.Theme a() {
            LayoutInflater layoutInflater = this.c;
            if (layoutInflater == null) {
                return null;
            }
            return layoutInflater.getContext().getTheme();
        }

        public void a(@android.support.annotation.ag Resources.Theme theme) {
            this.c = theme == null ? null : theme == this.a.getTheme() ? this.b : LayoutInflater.from(new android.support.v7.view.d(this.a, theme));
        }

        @android.support.annotation.af
        public LayoutInflater b() {
            LayoutInflater layoutInflater = this.c;
            return layoutInflater != null ? layoutInflater : this.b;
        }
    }

    @android.support.annotation.ag
    Resources.Theme a();

    void a(@android.support.annotation.ag Resources.Theme theme);
}
