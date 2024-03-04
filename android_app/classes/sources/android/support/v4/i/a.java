package android.support.v4.i;

import android.text.SpannableStringBuilder;
import java.util.Locale;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    private static final char d = 8234;
    private static final char e = 8235;
    private static final char f = 8236;
    private static final String k = "";
    private static final int l = 2;
    private static final int m = 2;
    private static final int q = -1;
    private static final int r = 0;
    private static final int s = 1;
    private final boolean n;
    private final int o;
    private final e p;
    static final e a = f.c;
    private static final char g = 8206;
    private static final String i = Character.toString(g);
    private static final char h = 8207;
    private static final String j = Character.toString(h);
    static final a b = new a(false, 2, a);
    static final a c = new a(true, 2, a);

    /* renamed from: android.support.v4.i.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class C0024a {
        private boolean a;
        private int b;
        private e c;

        public C0024a() {
            b(a.b(Locale.getDefault()));
        }

        public C0024a(Locale locale) {
            b(a.b(locale));
        }

        public C0024a(boolean z) {
            b(z);
        }

        private void b(boolean z) {
            this.a = z;
            this.c = a.a;
            this.b = 2;
        }

        private static a c(boolean z) {
            return z ? a.c : a.b;
        }

        public C0024a a(e eVar) {
            this.c = eVar;
            return this;
        }

        public C0024a a(boolean z) {
            this.b = z ? this.b | 2 : this.b & (-3);
            return this;
        }

        public a a() {
            return (this.b == 2 && this.c == a.a) ? c(this.a) : new a(this.a, this.b, this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        private static final int a = 1792;
        private static final byte[] b = new byte[a];
        private final CharSequence c;
        private final boolean d;
        private final int e;
        private int f;
        private char g;

        static {
            for (int i = 0; i < a; i++) {
                b[i] = Character.getDirectionality(i);
            }
        }

        b(CharSequence charSequence, boolean z) {
            this.c = charSequence;
            this.d = z;
            this.e = charSequence.length();
        }

        private static byte a(char c) {
            return c < a ? b[c] : Character.getDirectionality(c);
        }

        private byte e() {
            char charAt;
            int i = this.f;
            while (true) {
                int i2 = this.f;
                if (i2 >= this.e) {
                    this.f = i;
                    this.g = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.c;
                this.f = i2 + 1;
                this.g = charSequence.charAt(i2);
                char c = this.g;
                if (c == '>') {
                    return (byte) 12;
                }
                if (c == '\"' || c == '\'') {
                    char c2 = this.g;
                    do {
                        int i3 = this.f;
                        if (i3 < this.e) {
                            CharSequence charSequence2 = this.c;
                            this.f = i3 + 1;
                            charAt = charSequence2.charAt(i3);
                            this.g = charAt;
                        }
                    } while (charAt != c2);
                }
            }
        }

        private byte f() {
            char charAt;
            int i = this.f;
            while (true) {
                int i2 = this.f;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.c;
                int i3 = i2 - 1;
                this.f = i3;
                this.g = charSequence.charAt(i3);
                char c = this.g;
                if (c == '<') {
                    return (byte) 12;
                }
                if (c == '>') {
                    break;
                } else if (c == '\"' || c == '\'') {
                    char c2 = this.g;
                    do {
                        int i4 = this.f;
                        if (i4 > 0) {
                            CharSequence charSequence2 = this.c;
                            int i5 = i4 - 1;
                            this.f = i5;
                            charAt = charSequence2.charAt(i5);
                            this.g = charAt;
                        }
                    } while (charAt != c2);
                }
            }
            this.f = i;
            this.g = '>';
            return (byte) 13;
        }

        private byte g() {
            char charAt;
            do {
                int i = this.f;
                if (i >= this.e) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.c;
                this.f = i + 1;
                charAt = charSequence.charAt(i);
                this.g = charAt;
            } while (charAt != ';');
            return (byte) 12;
        }

        private byte h() {
            char c;
            int i = this.f;
            do {
                int i2 = this.f;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.c;
                int i3 = i2 - 1;
                this.f = i3;
                this.g = charSequence.charAt(i3);
                c = this.g;
                if (c == '&') {
                    return (byte) 12;
                }
            } while (c != ';');
            this.f = i;
            this.g = ';';
            return (byte) 13;
        }

        int a() {
            this.f = 0;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (this.f < this.e && i == 0) {
                byte c = c();
                if (c != 0) {
                    if (c == 1 || c == 2) {
                        if (i3 == 0) {
                            return 1;
                        }
                    } else if (c != 9) {
                        switch (c) {
                            case 14:
                            case 15:
                                i3++;
                                i2 = -1;
                                break;
                            case 16:
                            case 17:
                                i3++;
                                i2 = 1;
                                break;
                            case 18:
                                i3--;
                                i2 = 0;
                                break;
                        }
                    }
                } else if (i3 == 0) {
                    return -1;
                }
                i = i3;
            }
            if (i == 0) {
                return 0;
            }
            if (i2 != 0) {
                return i2;
            }
            while (this.f > 0) {
                switch (d()) {
                    case 14:
                    case 15:
                        if (i == i3) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i == i3) {
                            return 1;
                        }
                        break;
                    case 18:
                        i3++;
                        continue;
                }
                i3--;
            }
            return 0;
        }

        int b() {
            this.f = this.e;
            int i = 0;
            int i2 = 0;
            while (this.f > 0) {
                byte d = d();
                if (d != 0) {
                    if (d == 1 || d == 2) {
                        if (i2 == 0) {
                            return 1;
                        }
                        if (i == 0) {
                            i = i2;
                        }
                    } else if (d != 9) {
                        switch (d) {
                            case 14:
                            case 15:
                                if (i == i2) {
                                    return -1;
                                }
                                i2--;
                                break;
                            case 16:
                            case 17:
                                if (i == i2) {
                                    return 1;
                                }
                                i2--;
                                break;
                            case 18:
                                i2++;
                                break;
                            default:
                                if (i != 0) {
                                    break;
                                } else {
                                    i = i2;
                                    break;
                                }
                        }
                    } else {
                        continue;
                    }
                } else if (i2 == 0) {
                    return -1;
                } else {
                    if (i == 0) {
                        i = i2;
                    }
                }
            }
            return 0;
        }

        byte c() {
            this.g = this.c.charAt(this.f);
            if (Character.isHighSurrogate(this.g)) {
                int codePointAt = Character.codePointAt(this.c, this.f);
                this.f += Character.charCount(codePointAt);
                return Character.getDirectionality(codePointAt);
            }
            this.f++;
            byte a2 = a(this.g);
            if (this.d) {
                char c = this.g;
                return c == '<' ? e() : c == '&' ? g() : a2;
            }
            return a2;
        }

        byte d() {
            this.g = this.c.charAt(this.f - 1);
            if (Character.isLowSurrogate(this.g)) {
                int codePointBefore = Character.codePointBefore(this.c, this.f);
                this.f -= Character.charCount(codePointBefore);
                return Character.getDirectionality(codePointBefore);
            }
            this.f--;
            byte a2 = a(this.g);
            if (this.d) {
                char c = this.g;
                return c == '>' ? f() : c == ';' ? h() : a2;
            }
            return a2;
        }
    }

    a(boolean z, int i2, e eVar) {
        this.n = z;
        this.o = i2;
        this.p = eVar;
    }

    public static a a() {
        return new C0024a().a();
    }

    public static a a(Locale locale) {
        return new C0024a(locale).a();
    }

    public static a a(boolean z) {
        return new C0024a(z).a();
    }

    private String b(CharSequence charSequence, e eVar) {
        boolean a2 = eVar.a(charSequence, 0, charSequence.length());
        return (this.n || !(a2 || c(charSequence) == 1)) ? this.n ? (!a2 || c(charSequence) == -1) ? j : "" : "" : i;
    }

    static boolean b(Locale locale) {
        return g.a(locale) == 1;
    }

    private static int c(CharSequence charSequence) {
        return new b(charSequence, false).b();
    }

    private String c(CharSequence charSequence, e eVar) {
        boolean a2 = eVar.a(charSequence, 0, charSequence.length());
        return (this.n || !(a2 || d(charSequence) == 1)) ? this.n ? (!a2 || d(charSequence) == -1) ? j : "" : "" : i;
    }

    private static int d(CharSequence charSequence) {
        return new b(charSequence, false).a();
    }

    public CharSequence a(CharSequence charSequence, e eVar) {
        return a(charSequence, eVar, true);
    }

    public CharSequence a(CharSequence charSequence, e eVar, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean a2 = eVar.a(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (c() && z) {
            spannableStringBuilder.append((CharSequence) c(charSequence, a2 ? f.b : f.a));
        }
        if (a2 != this.n) {
            spannableStringBuilder.append(a2 ? e : d);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append(f);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append((CharSequence) b(charSequence, a2 ? f.b : f.a));
        }
        return spannableStringBuilder;
    }

    public CharSequence a(CharSequence charSequence, boolean z) {
        return a(charSequence, this.p, z);
    }

    public String a(String str, e eVar) {
        return a(str, eVar, true);
    }

    public String a(String str, e eVar, boolean z) {
        if (str == null) {
            return null;
        }
        return a((CharSequence) str, eVar, z).toString();
    }

    public String a(String str, boolean z) {
        return a(str, this.p, z);
    }

    public boolean a(CharSequence charSequence) {
        return this.p.a(charSequence, 0, charSequence.length());
    }

    public boolean a(String str) {
        return a((CharSequence) str);
    }

    public CharSequence b(CharSequence charSequence) {
        return a(charSequence, this.p, true);
    }

    public String b(String str) {
        return a(str, this.p, true);
    }

    public boolean b() {
        return this.n;
    }

    public boolean c() {
        return (this.o & 2) != 0;
    }
}
