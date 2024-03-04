package android.support.v4.content.b;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.k;
import android.support.annotation.m;
import android.support.annotation.p;
import android.support.annotation.r;
import android.support.v4.j.q;
import android.util.TypedValue;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class g {
    private static final String a = "ResourcesCompat";

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class a {
        public abstract void a(int i);

        @an(a = {an.a.LIBRARY_GROUP})
        public final void a(final int i, @ag Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() { // from class: android.support.v4.content.b.g.a.2
                @Override // java.lang.Runnable
                public void run() {
                    a.this.a(i);
                }
            });
        }

        public abstract void a(@af Typeface typeface);

        @an(a = {an.a.LIBRARY_GROUP})
        public final void a(final Typeface typeface, @ag Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() { // from class: android.support.v4.content.b.g.a.1
                @Override // java.lang.Runnable
                public void run() {
                    a.this.a(typeface);
                }
            });
        }
    }

    private g() {
    }

    @ag
    public static Typeface a(@af Context context, @r int i) {
        if (context.isRestricted()) {
            return null;
        }
        return a(context, i, new TypedValue(), 0, null, null, false);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public static Typeface a(@af Context context, @r int i, TypedValue typedValue, int i2, @ag a aVar) {
        if (context.isRestricted()) {
            return null;
        }
        return a(context, i, typedValue, i2, aVar, null, true);
    }

    private static Typeface a(@af Context context, int i, TypedValue typedValue, int i2, @ag a aVar, @ag Handler handler, boolean z) {
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        Typeface a2 = a(context, resources, typedValue, i, i2, aVar, handler, z);
        if (a2 == null && aVar == null) {
            throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i) + " could not be retrieved.");
        }
        return a2;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Typeface a(@android.support.annotation.af android.content.Context r15, android.content.res.Resources r16, android.util.TypedValue r17, int r18, int r19, @android.support.annotation.ag android.support.v4.content.b.g.a r20, @android.support.annotation.ag android.os.Handler r21, boolean r22) {
        /*
            r0 = r16
            r1 = r17
            r4 = r18
            r5 = r19
            r9 = r20
            r10 = r21
            java.lang.String r11 = "ResourcesCompat"
            java.lang.CharSequence r2 = r1.string
            if (r2 == 0) goto L9c
            java.lang.CharSequence r1 = r1.string
            java.lang.String r12 = r1.toString()
            java.lang.String r1 = "res/"
            boolean r1 = r12.startsWith(r1)
            r13 = 0
            r14 = -3
            if (r1 != 0) goto L28
            if (r9 == 0) goto L27
            r9.a(r14, r10)
        L27:
            return r13
        L28:
            android.graphics.Typeface r1 = android.support.v4.graphics.g.a(r0, r4, r5)
            if (r1 == 0) goto L34
            if (r9 == 0) goto L33
            r9.a(r1, r10)
        L33:
            return r1
        L34:
            java.lang.String r1 = r12.toLowerCase()     // Catch: java.io.IOException -> L78 org.xmlpull.v1.XmlPullParserException -> L81
            java.lang.String r2 = ".xml"
            boolean r1 = r1.endsWith(r2)     // Catch: java.io.IOException -> L78 org.xmlpull.v1.XmlPullParserException -> L81
            if (r1 == 0) goto L67
            android.content.res.XmlResourceParser r1 = r0.getXml(r4)     // Catch: java.io.IOException -> L78 org.xmlpull.v1.XmlPullParserException -> L81
            android.support.v4.content.b.d$a r2 = android.support.v4.content.b.d.a(r1, r0)     // Catch: java.io.IOException -> L78 org.xmlpull.v1.XmlPullParserException -> L81
            if (r2 != 0) goto L55
            java.lang.String r0 = "Failed to find font-family tag"
            android.util.Log.e(r11, r0)     // Catch: java.io.IOException -> L78 org.xmlpull.v1.XmlPullParserException -> L81
            if (r9 == 0) goto L54
            r9.a(r14, r10)     // Catch: java.io.IOException -> L78 org.xmlpull.v1.XmlPullParserException -> L81
        L54:
            return r13
        L55:
            r1 = r15
            r3 = r16
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r8 = r22
            android.graphics.Typeface r0 = android.support.v4.graphics.g.a(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: java.io.IOException -> L78 org.xmlpull.v1.XmlPullParserException -> L81
            return r0
        L67:
            r1 = r15
            android.graphics.Typeface r0 = android.support.v4.graphics.g.a(r15, r0, r4, r12, r5)     // Catch: java.io.IOException -> L78 org.xmlpull.v1.XmlPullParserException -> L81
            if (r9 == 0) goto L77
            if (r0 == 0) goto L74
            r9.a(r0, r10)     // Catch: java.io.IOException -> L78 org.xmlpull.v1.XmlPullParserException -> L81
            goto L77
        L74:
            r9.a(r14, r10)     // Catch: java.io.IOException -> L78 org.xmlpull.v1.XmlPullParserException -> L81
        L77:
            return r0
        L78:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to read xml resource "
            goto L89
        L81:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to parse xml resource "
        L89:
            r1.append(r2)
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r11, r1, r0)
            if (r9 == 0) goto L9b
            r9.a(r14, r10)
        L9b:
            return r13
        L9c:
            android.content.res.Resources$NotFoundException r2 = new android.content.res.Resources$NotFoundException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "Resource \""
            r3.append(r5)
            java.lang.String r0 = r0.getResourceName(r4)
            r3.append(r0)
            java.lang.String r0 = "\" ("
            r3.append(r0)
            java.lang.String r0 = java.lang.Integer.toHexString(r18)
            r3.append(r0)
            java.lang.String r0 = ") is not a Font: "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.b.g.a(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, android.support.v4.content.b.g$a, android.os.Handler, boolean):android.graphics.Typeface");
    }

    @ag
    public static Drawable a(@af Resources resources, @p int i, int i2, @ag Resources.Theme theme) {
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawableForDensity(i, i2, theme) : Build.VERSION.SDK_INT >= 15 ? resources.getDrawableForDensity(i, i2) : resources.getDrawable(i);
    }

    @ag
    public static Drawable a(@af Resources resources, @p int i, @ag Resources.Theme theme) {
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawable(i, theme) : resources.getDrawable(i);
    }

    public static void a(@af Context context, @r int i, @af a aVar, @ag Handler handler) {
        q.a(aVar);
        if (context.isRestricted()) {
            aVar.a(-4, handler);
        } else {
            a(context, i, new TypedValue(), 0, aVar, handler, false);
        }
    }

    @k
    public static int b(@af Resources resources, @m int i, @ag Resources.Theme theme) {
        return Build.VERSION.SDK_INT >= 23 ? resources.getColor(i, theme) : resources.getColor(i);
    }

    @ag
    public static ColorStateList c(@af Resources resources, @m int i, @ag Resources.Theme theme) {
        return Build.VERSION.SDK_INT >= 23 ? resources.getColorStateList(i, theme) : resources.getColorStateList(i);
    }
}
