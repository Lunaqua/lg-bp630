package android.support.v4.os;

import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.ao;
import android.support.annotation.t;
import android.support.annotation.x;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
final class i {
    private static final String e = "en-XA";
    private static final String f = "ar-XB";
    private static final int i = 2;
    private final Locale[] a;
    @af
    private final String b;
    private static final Locale[] c = new Locale[0];
    private static final i d = new i(new Locale[0]);
    private static final Locale g = new Locale("en", "XA");
    private static final Locale h = new Locale("ar", "XB");
    private static final Locale j = g.a("en-Latn");
    private static final Object k = new Object();
    @t(a = "sLock")
    private static i l = null;
    @t(a = "sLock")
    private static i m = null;
    @t(a = "sLock")
    private static i n = null;
    @t(a = "sLock")
    private static Locale o = null;

    @an(a = {an.a.LIBRARY_GROUP})
    i(@af Locale locale, i iVar) {
        if (locale == null) {
            throw new NullPointerException("topLocale is null");
        }
        int length = iVar == null ? 0 : iVar.a.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            } else if (locale.equals(iVar.a[i2])) {
                break;
            } else {
                i2++;
            }
        }
        int i3 = (i2 == -1 ? 1 : 0) + length;
        Locale[] localeArr = new Locale[i3];
        localeArr[0] = (Locale) locale.clone();
        if (i2 == -1) {
            int i4 = 0;
            while (i4 < length) {
                int i5 = i4 + 1;
                localeArr[i5] = (Locale) iVar.a[i4].clone();
                i4 = i5;
            }
        } else {
            int i6 = 0;
            while (i6 < i2) {
                int i7 = i6 + 1;
                localeArr[i7] = (Locale) iVar.a[i6].clone();
                i6 = i7;
            }
            for (int i8 = i2 + 1; i8 < length; i8++) {
                localeArr[i8] = (Locale) iVar.a[i8].clone();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i9 = 0; i9 < i3; i9++) {
            sb.append(g.a(localeArr[i9]));
            if (i9 < i3 - 1) {
                sb.append(',');
            }
        }
        this.a = localeArr;
        this.b = sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @an(a = {an.a.LIBRARY_GROUP})
    public i(@af Locale... localeArr) {
        String sb;
        if (localeArr.length == 0) {
            this.a = c;
            sb = com.lge.media.launcher.a.d;
        } else {
            Locale[] localeArr2 = new Locale[localeArr.length];
            HashSet hashSet = new HashSet();
            StringBuilder sb2 = new StringBuilder();
            for (int i2 = 0; i2 < localeArr.length; i2++) {
                Locale locale = localeArr[i2];
                if (locale == null) {
                    throw new NullPointerException("list[" + i2 + "] is null");
                } else if (hashSet.contains(locale)) {
                    throw new IllegalArgumentException("list[" + i2 + "] is a repetition");
                } else {
                    Locale locale2 = (Locale) locale.clone();
                    localeArr2[i2] = locale2;
                    sb2.append(g.a(locale2));
                    if (i2 < localeArr.length - 1) {
                        sb2.append(',');
                    }
                    hashSet.add(locale2);
                }
            }
            this.a = localeArr2;
            sb = sb2.toString();
        }
        this.b = sb;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001b, code lost:
        if (r6 < Integer.MAX_VALUE) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int a(java.util.Collection<java.lang.String> r5, boolean r6) {
        /*
            r4 = this;
            java.util.Locale[] r0 = r4.a
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 != r3) goto L8
            return r2
        L8:
            int r0 = r0.length
            if (r0 != 0) goto Ld
            r5 = -1
            return r5
        Ld:
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r6 == 0) goto L1e
            java.util.Locale r6 = android.support.v4.os.i.j
            int r6 = r4.d(r6)
            if (r6 != 0) goto L1b
            return r2
        L1b:
            if (r6 >= r0) goto L1e
            goto L21
        L1e:
            r6 = 2147483647(0x7fffffff, float:NaN)
        L21:
            java.util.Iterator r5 = r5.iterator()
        L25:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L40
            java.lang.Object r1 = r5.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.Locale r1 = android.support.v4.os.g.a(r1)
            int r1 = r4.d(r1)
            if (r1 != 0) goto L3c
            return r2
        L3c:
            if (r1 >= r6) goto L25
            r6 = r1
            goto L25
        L40:
            if (r6 != r0) goto L43
            return r2
        L43:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.os.i.a(java.util.Collection, boolean):int");
    }

    @x(a = 0, b = 1)
    private static int a(Locale locale, Locale locale2) {
        if (locale.equals(locale2)) {
            return 1;
        }
        if (!locale.getLanguage().equals(locale2.getLanguage()) || c(locale) || c(locale2)) {
            return 0;
        }
        String b = b(locale);
        if (b.isEmpty()) {
            String country = locale.getCountry();
            return (country.isEmpty() || country.equals(locale2.getCountry())) ? 1 : 0;
        }
        return b.equals(b(locale2)) ? 1 : 0;
    }

    @af
    @an(a = {an.a.LIBRARY_GROUP})
    static i a(@ag String str) {
        if (str == null || str.isEmpty()) {
            return d();
        }
        String[] split = str.split(",", -1);
        Locale[] localeArr = new Locale[split.length];
        for (int i2 = 0; i2 < localeArr.length; i2++) {
            localeArr[i2] = g.a(split[i2]);
        }
        return new i(localeArr);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    static void a(@ao(b = 1) @af i iVar) {
        a(iVar, 0);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    static void a(@ao(b = 1) @af i iVar, int i2) {
        if (iVar == null) {
            throw new NullPointerException("locales is null");
        }
        if (iVar.a()) {
            throw new IllegalArgumentException("locales is empty");
        }
        synchronized (k) {
            o = iVar.a(i2);
            Locale.setDefault(o);
            l = iVar;
            m = iVar;
            n = i2 == 0 ? m : new i(o, m);
        }
    }

    private static String b(Locale locale) {
        if (Build.VERSION.SDK_INT >= 21) {
            String script = locale.getScript();
            if (!script.isEmpty()) {
                return script;
            }
        }
        return com.lge.media.launcher.a.d;
    }

    private Locale b(Collection<String> collection, boolean z) {
        int a = a(collection, z);
        if (a == -1) {
            return null;
        }
        return this.a[a];
    }

    private static boolean b(String str) {
        return e.equals(str) || f.equals(str);
    }

    private static boolean c(Locale locale) {
        return g.equals(locale) || h.equals(locale);
    }

    private int d(Locale locale) {
        int i2 = 0;
        while (true) {
            Locale[] localeArr = this.a;
            if (i2 >= localeArr.length) {
                return ActivityChooserView.a.a;
            }
            if (a(locale, localeArr[i2]) > 0) {
                return i2;
            }
            i2++;
        }
    }

    @af
    @an(a = {an.a.LIBRARY_GROUP})
    static i d() {
        return d;
    }

    @ao(b = 1)
    @af
    @an(a = {an.a.LIBRARY_GROUP})
    static i e() {
        Locale locale = Locale.getDefault();
        synchronized (k) {
            if (!locale.equals(o)) {
                o = locale;
                if (m != null && locale.equals(m.a(0))) {
                    return m;
                }
                m = new i(locale, l);
                n = m;
            }
            return m;
        }
    }

    @an(a = {an.a.LIBRARY_GROUP})
    static boolean e(@ag String[] strArr) {
        if (strArr == null) {
            return true;
        }
        if (strArr.length > 3) {
            return false;
        }
        for (String str : strArr) {
            if (!str.isEmpty() && !b(str)) {
                return false;
            }
        }
        return true;
    }

    @ao(b = 1)
    @af
    static i f() {
        i iVar;
        e();
        synchronized (k) {
            iVar = n;
        }
        return iVar;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    int a(Collection<String> collection) {
        return a(collection, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @x(a = RecyclerView.m)
    @an(a = {an.a.LIBRARY_GROUP})
    public int a(Locale locale) {
        int i2 = 0;
        while (true) {
            Locale[] localeArr = this.a;
            if (i2 >= localeArr.length) {
                return -1;
            }
            if (localeArr[i2].equals(locale)) {
                return i2;
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @an(a = {an.a.LIBRARY_GROUP})
    public Locale a(int i2) {
        if (i2 >= 0) {
            Locale[] localeArr = this.a;
            if (i2 < localeArr.length) {
                return localeArr[i2];
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ag
    @an(a = {an.a.LIBRARY_GROUP})
    public Locale a(String[] strArr) {
        return b(Arrays.asList(strArr), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @an(a = {an.a.LIBRARY_GROUP})
    public boolean a() {
        return this.a.length == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @x(a = 0)
    @an(a = {an.a.LIBRARY_GROUP})
    public int b() {
        return this.a.length;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    int b(String[] strArr) {
        return a((Collection<String>) Arrays.asList(strArr), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @af
    @an(a = {an.a.LIBRARY_GROUP})
    public String c() {
        return this.b;
    }

    @ag
    @an(a = {an.a.LIBRARY_GROUP})
    Locale c(String[] strArr) {
        return b(Arrays.asList(strArr), true);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    int d(String[] strArr) {
        return a(Arrays.asList(strArr));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        Locale[] localeArr = ((i) obj).a;
        if (this.a.length != localeArr.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            Locale[] localeArr2 = this.a;
            if (i2 >= localeArr2.length) {
                return true;
            }
            if (!localeArr2[i2].equals(localeArr[i2])) {
                return false;
            }
            i2++;
        }
    }

    public int hashCode() {
        int i2 = 1;
        int i3 = 0;
        while (true) {
            Locale[] localeArr = this.a;
            if (i3 >= localeArr.length) {
                return i2;
            }
            i2 = (i2 * 31) + localeArr[i3].hashCode();
            i3++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i2 = 0;
        while (true) {
            Locale[] localeArr = this.a;
            if (i2 >= localeArr.length) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(localeArr[i2]);
            if (i2 < this.a.length - 1) {
                sb.append(',');
            }
            i2++;
        }
    }
}
