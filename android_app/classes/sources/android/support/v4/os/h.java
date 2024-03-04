package android.support.v4.os;

import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.ao;
import android.support.annotation.x;
import android.support.v7.widget.RecyclerView;
import java.util.Locale;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class h {
    static final j a;
    private static final h b = new h();

    @ak(a = 24)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class a implements j {
        private LocaleList a = new LocaleList(new Locale[0]);

        a() {
        }

        @Override // android.support.v4.os.j
        @x(a = RecyclerView.m)
        public int a(Locale locale) {
            return this.a.indexOf(locale);
        }

        @Override // android.support.v4.os.j
        public Object a() {
            return this.a;
        }

        @Override // android.support.v4.os.j
        public Locale a(int i) {
            return this.a.get(i);
        }

        @Override // android.support.v4.os.j
        @ag
        public Locale a(String[] strArr) {
            LocaleList localeList = this.a;
            if (localeList != null) {
                return localeList.getFirstMatch(strArr);
            }
            return null;
        }

        @Override // android.support.v4.os.j
        public void a(@af Locale... localeArr) {
            this.a = new LocaleList(localeArr);
        }

        @Override // android.support.v4.os.j
        public boolean b() {
            return this.a.isEmpty();
        }

        @Override // android.support.v4.os.j
        @x(a = 0)
        public int c() {
            return this.a.size();
        }

        @Override // android.support.v4.os.j
        public String d() {
            return this.a.toLanguageTags();
        }

        @Override // android.support.v4.os.j
        public boolean equals(Object obj) {
            return this.a.equals(((h) obj).a());
        }

        @Override // android.support.v4.os.j
        public int hashCode() {
            return this.a.hashCode();
        }

        @Override // android.support.v4.os.j
        public String toString() {
            return this.a.toString();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class b implements j {
        private i a = new i(new Locale[0]);

        b() {
        }

        @Override // android.support.v4.os.j
        @x(a = RecyclerView.m)
        public int a(Locale locale) {
            return this.a.a(locale);
        }

        @Override // android.support.v4.os.j
        public Object a() {
            return this.a;
        }

        @Override // android.support.v4.os.j
        public Locale a(int i) {
            return this.a.a(i);
        }

        @Override // android.support.v4.os.j
        @ag
        public Locale a(String[] strArr) {
            i iVar = this.a;
            if (iVar != null) {
                return iVar.a(strArr);
            }
            return null;
        }

        @Override // android.support.v4.os.j
        public void a(@af Locale... localeArr) {
            this.a = new i(localeArr);
        }

        @Override // android.support.v4.os.j
        public boolean b() {
            return this.a.a();
        }

        @Override // android.support.v4.os.j
        @x(a = 0)
        public int c() {
            return this.a.b();
        }

        @Override // android.support.v4.os.j
        public String d() {
            return this.a.c();
        }

        @Override // android.support.v4.os.j
        public boolean equals(Object obj) {
            return this.a.equals(((h) obj).a());
        }

        @Override // android.support.v4.os.j
        public int hashCode() {
            return this.a.hashCode();
        }

        @Override // android.support.v4.os.j
        public String toString() {
            return this.a.toString();
        }
    }

    static {
        a = Build.VERSION.SDK_INT >= 24 ? new a() : new b();
    }

    private h() {
    }

    @ak(a = 24)
    public static h a(Object obj) {
        h hVar = new h();
        if (obj instanceof LocaleList) {
            hVar.a((LocaleList) obj);
        }
        return hVar;
    }

    @af
    public static h a(@ag String str) {
        if (str == null || str.isEmpty()) {
            return e();
        }
        String[] split = str.split(",", -1);
        Locale[] localeArr = new Locale[split.length];
        for (int i = 0; i < localeArr.length; i++) {
            localeArr[i] = Build.VERSION.SDK_INT >= 21 ? Locale.forLanguageTag(split[i]) : g.a(split[i]);
        }
        h hVar = new h();
        hVar.b(localeArr);
        return hVar;
    }

    public static h a(@af Locale... localeArr) {
        h hVar = new h();
        hVar.b(localeArr);
        return hVar;
    }

    @ak(a = 24)
    private void a(LocaleList localeList) {
        int size = localeList.size();
        if (size > 0) {
            Locale[] localeArr = new Locale[size];
            for (int i = 0; i < size; i++) {
                localeArr[i] = localeList.get(i);
            }
            a.a(localeArr);
        }
    }

    private void b(Locale... localeArr) {
        a.a(localeArr);
    }

    @af
    public static h e() {
        return b;
    }

    @ao(b = 1)
    @af
    public static h f() {
        return Build.VERSION.SDK_INT >= 24 ? a((Object) LocaleList.getAdjustedDefault()) : a(Locale.getDefault());
    }

    @ao(b = 1)
    @af
    public static h g() {
        return Build.VERSION.SDK_INT >= 24 ? a((Object) LocaleList.getDefault()) : a(Locale.getDefault());
    }

    @x(a = RecyclerView.m)
    public int a(Locale locale) {
        return a.a(locale);
    }

    @ag
    public Object a() {
        return a.a();
    }

    public Locale a(int i) {
        return a.a(i);
    }

    public Locale a(String[] strArr) {
        return a.a(strArr);
    }

    public boolean b() {
        return a.b();
    }

    @x(a = 0)
    public int c() {
        return a.c();
    }

    @af
    public String d() {
        return a.d();
    }

    public boolean equals(Object obj) {
        return a.equals(obj);
    }

    public int hashCode() {
        return a.hashCode();
    }

    public String toString() {
        return a.toString();
    }
}
