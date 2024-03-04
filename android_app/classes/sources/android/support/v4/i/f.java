package android.support.v4.i;

import java.nio.CharBuffer;
import java.util.Locale;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class f {
    public static final android.support.v4.i.e a = new e(null, false);
    public static final android.support.v4.i.e b = new e(null, true);
    public static final android.support.v4.i.e c = new e(b.a, false);
    public static final android.support.v4.i.e d = new e(b.a, true);
    public static final android.support.v4.i.e e = new e(a.a, false);
    public static final android.support.v4.i.e f = C0028f.a;
    private static final int g = 0;
    private static final int h = 1;
    private static final int i = 2;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class a implements c {
        static final a a = new a(true);
        static final a b = new a(false);
        private final boolean c;

        private a(boolean z) {
            this.c = z;
        }

        @Override // android.support.v4.i.f.c
        public int a(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            boolean z = false;
            while (i < i3) {
                int a2 = f.a(Character.getDirectionality(charSequence.charAt(i)));
                if (a2 != 0) {
                    if (a2 != 1) {
                        continue;
                        i++;
                    } else if (!this.c) {
                        return 1;
                    }
                } else if (this.c) {
                    return 0;
                }
                z = true;
                i++;
            }
            if (z) {
                return this.c ? 1 : 0;
            }
            return 2;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class b implements c {
        static final b a = new b();

        private b() {
        }

        @Override // android.support.v4.i.f.c
        public int a(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 2;
            while (i < i3 && i4 == 2) {
                i4 = f.b(Character.getDirectionality(charSequence.charAt(i)));
                i++;
            }
            return i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface c {
        int a(CharSequence charSequence, int i, int i2);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static abstract class d implements android.support.v4.i.e {
        private final c a;

        d(c cVar) {
            this.a = cVar;
        }

        private boolean b(CharSequence charSequence, int i, int i2) {
            int a = this.a.a(charSequence, i, i2);
            if (a != 0) {
                if (a != 1) {
                    return a();
                }
                return false;
            }
            return true;
        }

        protected abstract boolean a();

        @Override // android.support.v4.i.e
        public boolean a(CharSequence charSequence, int i, int i2) {
            if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
                throw new IllegalArgumentException();
            }
            return this.a == null ? a() : b(charSequence, i, i2);
        }

        @Override // android.support.v4.i.e
        public boolean a(char[] cArr, int i, int i2) {
            return a(CharBuffer.wrap(cArr), i, i2);
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class e extends d {
        private final boolean a;

        e(c cVar, boolean z) {
            super(cVar);
            this.a = z;
        }

        @Override // android.support.v4.i.f.d
        protected boolean a() {
            return this.a;
        }
    }

    /* renamed from: android.support.v4.i.f$f  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class C0028f extends d {
        static final C0028f a = new C0028f();

        C0028f() {
            super(null);
        }

        @Override // android.support.v4.i.f.d
        protected boolean a() {
            return g.a(Locale.getDefault()) == 1;
        }
    }

    private f() {
    }

    static int a(int i2) {
        if (i2 != 0) {
            return (i2 == 1 || i2 == 2) ? 0 : 2;
        }
        return 1;
    }

    static int b(int i2) {
        if (i2 != 0) {
            if (i2 == 1 || i2 == 2) {
                return 0;
            }
            switch (i2) {
                case 14:
                case 15:
                    break;
                case 16:
                case 17:
                    return 0;
                default:
                    return 2;
            }
        }
        return 1;
    }
}
