package android.support.v4.content;

import android.content.SharedPreferences;
import android.support.annotation.af;
@Deprecated
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class k {

    @Deprecated
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class a {
        private static a a;
        private final C0018a b = new C0018a();

        /* renamed from: android.support.v4.content.k$a$a  reason: collision with other inner class name */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        private static class C0018a {
            C0018a() {
            }

            public void a(@af SharedPreferences.Editor editor) {
                try {
                    editor.apply();
                } catch (AbstractMethodError unused) {
                    editor.commit();
                }
            }
        }

        private a() {
        }

        @Deprecated
        public static a a() {
            if (a == null) {
                a = new a();
            }
            return a;
        }

        @Deprecated
        public void a(@af SharedPreferences.Editor editor) {
            this.b.a(editor);
        }
    }

    private k() {
    }
}
