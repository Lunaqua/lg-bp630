package android.support.v4.content.b;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.k;
import android.support.b.a;
import android.util.AttributeSet;
import android.util.Xml;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* JADX INFO: Access modifiers changed from: package-private */
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class e {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class a {
        final int[] a;
        final float[] b;

        a(@k int i, @k int i2) {
            this.a = new int[]{i, i2};
            this.b = new float[]{0.0f, 1.0f};
        }

        a(@k int i, @k int i2, @k int i3) {
            this.a = new int[]{i, i2, i3};
            this.b = new float[]{0.0f, 0.5f, 1.0f};
        }

        a(@af List<Integer> list, @af List<Float> list2) {
            int size = list.size();
            this.a = new int[size];
            this.b = new float[size];
            for (int i = 0; i < size; i++) {
                this.a[i] = list.get(i).intValue();
                this.b[i] = list2.get(i).floatValue();
            }
        }
    }

    private e() {
    }

    private static Shader.TileMode a(int i) {
        return i != 1 ? i != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR : Shader.TileMode.REPEAT;
    }

    static Shader a(@af Resources resources, @af XmlPullParser xmlPullParser, @ag Resources.Theme theme) {
        int next;
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return a(resources, xmlPullParser, asAttributeSet, theme);
        }
        throw new XmlPullParserException("No start tag found");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Shader a(@af Resources resources, @af XmlPullParser xmlPullParser, @af AttributeSet attributeSet, @ag Resources.Theme theme) {
        String name = xmlPullParser.getName();
        if (!name.equals("gradient")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
        TypedArray a2 = h.a(resources, theme, attributeSet, a.j.GradientColor);
        float a3 = h.a(a2, xmlPullParser, "startX", a.j.GradientColor_android_startX, 0.0f);
        float a4 = h.a(a2, xmlPullParser, "startY", a.j.GradientColor_android_startY, 0.0f);
        float a5 = h.a(a2, xmlPullParser, "endX", a.j.GradientColor_android_endX, 0.0f);
        float a6 = h.a(a2, xmlPullParser, "endY", a.j.GradientColor_android_endY, 0.0f);
        float a7 = h.a(a2, xmlPullParser, "centerX", a.j.GradientColor_android_centerX, 0.0f);
        float a8 = h.a(a2, xmlPullParser, "centerY", a.j.GradientColor_android_centerY, 0.0f);
        int a9 = h.a(a2, xmlPullParser, "type", a.j.GradientColor_android_type, 0);
        int b2 = h.b(a2, xmlPullParser, "startColor", a.j.GradientColor_android_startColor, 0);
        boolean a10 = h.a(xmlPullParser, "centerColor");
        int b3 = h.b(a2, xmlPullParser, "centerColor", a.j.GradientColor_android_centerColor, 0);
        int b4 = h.b(a2, xmlPullParser, "endColor", a.j.GradientColor_android_endColor, 0);
        int a11 = h.a(a2, xmlPullParser, "tileMode", a.j.GradientColor_android_tileMode, 0);
        float a12 = h.a(a2, xmlPullParser, "gradientRadius", a.j.GradientColor_android_gradientRadius, 0.0f);
        a2.recycle();
        a a13 = a(b(resources, xmlPullParser, attributeSet, theme), b2, b4, a10, b3);
        if (a9 != 1) {
            return a9 != 2 ? new LinearGradient(a3, a4, a5, a6, a13.a, a13.b, a(a11)) : new SweepGradient(a7, a8, a13.a, a13.b);
        } else if (a12 > 0.0f) {
            return new RadialGradient(a7, a8, a12, a13.a, a13.b, a(a11));
        } else {
            throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
        }
    }

    private static a a(@ag a aVar, @k int i, @k int i2, boolean z, @k int i3) {
        return aVar != null ? aVar : z ? new a(i, i3, i2) : new a(i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0089, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r9.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.support.v4.content.b.e.a b(@android.support.annotation.af android.content.res.Resources r8, @android.support.annotation.af org.xmlpull.v1.XmlPullParser r9, @android.support.annotation.af android.util.AttributeSet r10, @android.support.annotation.ag android.content.res.Resources.Theme r11) {
        /*
            int r0 = r9.getDepth()
            r1 = 1
            int r0 = r0 + r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 20
            r2.<init>(r3)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
        L12:
            int r3 = r9.next()
            if (r3 == r1) goto L8a
            int r5 = r9.getDepth()
            if (r5 >= r0) goto L21
            r6 = 3
            if (r3 == r6) goto L8a
        L21:
            r6 = 2
            if (r3 == r6) goto L25
            goto L12
        L25:
            if (r5 > r0) goto L12
            java.lang.String r3 = r9.getName()
            java.lang.String r5 = "item"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L34
            goto L12
        L34:
            int[] r3 = android.support.b.a.j.GradientColorItem
            android.content.res.TypedArray r3 = android.support.v4.content.b.h.a(r8, r11, r10, r3)
            int r5 = android.support.b.a.j.GradientColorItem_android_color
            boolean r5 = r3.hasValue(r5)
            int r6 = android.support.b.a.j.GradientColorItem_android_offset
            boolean r6 = r3.hasValue(r6)
            if (r5 == 0) goto L6a
            if (r6 == 0) goto L6a
            int r5 = android.support.b.a.j.GradientColorItem_android_color
            r6 = 0
            int r5 = r3.getColor(r5, r6)
            int r6 = android.support.b.a.j.GradientColorItem_android_offset
            r7 = 0
            float r6 = r3.getFloat(r6, r7)
            r3.recycle()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r4.add(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r6)
            r2.add(r3)
            goto L12
        L6a:
            org.xmlpull.v1.XmlPullParserException r8 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r9 = r9.getPositionDescription()
            r10.append(r9)
            java.lang.String r9 = ": <item> tag requires a 'color' attribute and a 'offset' "
            r10.append(r9)
            java.lang.String r9 = "attribute!"
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            throw r8
        L8a:
            int r8 = r4.size()
            if (r8 <= 0) goto L96
            android.support.v4.content.b.e$a r8 = new android.support.v4.content.b.e$a
            r8.<init>(r4, r2)
            return r8
        L96:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.b.e.b(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.support.v4.content.b.e$a");
    }
}
