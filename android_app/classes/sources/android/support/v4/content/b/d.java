package android.support.v4.content.b;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.b.a;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class d {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = -1;
    private static final int d = 400;
    private static final int e = 1;
    private static final int f = 500;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface b {
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class c implements a {
        @af
        private final C0017d[] a;

        public c(@af C0017d[] c0017dArr) {
            this.a = c0017dArr;
        }

        @af
        public C0017d[] a() {
            return this.a;
        }
    }

    /* renamed from: android.support.v4.content.b.d$d  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class C0017d {
        @af
        private final String a;
        private int b;
        private boolean c;
        private String d;
        private int e;
        private int f;

        public C0017d(@af String str, int i, boolean z, @ag String str2, int i2, int i3) {
            this.a = str;
            this.b = i;
            this.c = z;
            this.d = str2;
            this.e = i2;
            this.f = i3;
        }

        @af
        public String a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public boolean c() {
            return this.c;
        }

        @ag
        public String d() {
            return this.d;
        }

        public int e() {
            return this.e;
        }

        public int f() {
            return this.f;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class e implements a {
        @af
        private final android.support.v4.h.c a;
        private final int b;
        private final int c;

        public e(@af android.support.v4.h.c cVar, int i, int i2) {
            this.a = cVar;
            this.c = i;
            this.b = i2;
        }

        @af
        public android.support.v4.h.c a() {
            return this.a;
        }

        public int b() {
            return this.c;
        }

        public int c() {
            return this.b;
        }
    }

    private d() {
    }

    private static int a(TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType(i);
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i, typedValue);
        return typedValue.type;
    }

    @ag
    public static a a(XmlPullParser xmlPullParser, Resources resources) {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return b(xmlPullParser, resources);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static List<List<byte[]>> a(Resources resources, @android.support.annotation.e int i) {
        if (i == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (a(obtainTypedArray, 0) == 1) {
                for (int i2 = 0; i2 < obtainTypedArray.length(); i2++) {
                    int resourceId = obtainTypedArray.getResourceId(i2, 0);
                    if (resourceId != 0) {
                        arrayList.add(a(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(a(resources.getStringArray(i)));
            }
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    private static List<byte[]> a(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }

    private static void a(XmlPullParser xmlPullParser) {
        int i = 1;
        while (i > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }

    @ag
    private static a b(XmlPullParser xmlPullParser, Resources resources) {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return c(xmlPullParser, resources);
        }
        a(xmlPullParser);
        return null;
    }

    @ag
    private static a c(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), a.j.FontFamily);
        String string = obtainAttributes.getString(a.j.FontFamily_fontProviderAuthority);
        String string2 = obtainAttributes.getString(a.j.FontFamily_fontProviderPackage);
        String string3 = obtainAttributes.getString(a.j.FontFamily_fontProviderQuery);
        int resourceId = obtainAttributes.getResourceId(a.j.FontFamily_fontProviderCerts, 0);
        int integer = obtainAttributes.getInteger(a.j.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = obtainAttributes.getInteger(a.j.FontFamily_fontProviderFetchTimeout, f);
        obtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                a(xmlPullParser);
            }
            return new e(new android.support.v4.h.c(string, string2, string3, a(resources, resourceId)), integer, integer2);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("font")) {
                    arrayList.add(d(xmlPullParser, resources));
                } else {
                    a(xmlPullParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new c((C0017d[]) arrayList.toArray(new C0017d[arrayList.size()]));
    }

    private static C0017d d(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), a.j.FontFamilyFont);
        int i = obtainAttributes.getInt(obtainAttributes.hasValue(a.j.FontFamilyFont_fontWeight) ? a.j.FontFamilyFont_fontWeight : a.j.FontFamilyFont_android_fontWeight, d);
        boolean z = 1 == obtainAttributes.getInt(obtainAttributes.hasValue(a.j.FontFamilyFont_fontStyle) ? a.j.FontFamilyFont_fontStyle : a.j.FontFamilyFont_android_fontStyle, 0);
        int i2 = obtainAttributes.hasValue(a.j.FontFamilyFont_ttcIndex) ? a.j.FontFamilyFont_ttcIndex : a.j.FontFamilyFont_android_ttcIndex;
        String string = obtainAttributes.getString(obtainAttributes.hasValue(a.j.FontFamilyFont_fontVariationSettings) ? a.j.FontFamilyFont_fontVariationSettings : a.j.FontFamilyFont_android_fontVariationSettings);
        int i3 = obtainAttributes.getInt(i2, 0);
        int i4 = obtainAttributes.hasValue(a.j.FontFamilyFont_font) ? a.j.FontFamilyFont_font : a.j.FontFamilyFont_android_font;
        int resourceId = obtainAttributes.getResourceId(i4, 0);
        String string2 = obtainAttributes.getString(i4);
        obtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            a(xmlPullParser);
        }
        return new C0017d(string2, i, z, string, i3, resourceId);
    }
}
