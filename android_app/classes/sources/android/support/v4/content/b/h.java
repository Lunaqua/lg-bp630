package android.support.v4.content.b;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.as;
import android.support.annotation.k;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class h {
    private static final String a = "http://schemas.android.com/apk/res/android";

    private h() {
    }

    public static float a(@af TypedArray typedArray, @af XmlPullParser xmlPullParser, @af String str, @as int i, float f) {
        return !a(xmlPullParser, str) ? f : typedArray.getFloat(i, f);
    }

    public static int a(@af Context context, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId != 0 ? i : i2;
    }

    public static int a(@af TypedArray typedArray, @as int i, @as int i2, int i3) {
        return typedArray.getInt(i, typedArray.getInt(i2, i3));
    }

    public static int a(@af TypedArray typedArray, @af XmlPullParser xmlPullParser, @af String str, @as int i, int i2) {
        return !a(xmlPullParser, str) ? i2 : typedArray.getInt(i, i2);
    }

    @af
    public static TypedArray a(@af Resources resources, @ag Resources.Theme theme, @af AttributeSet attributeSet, @af int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    @ag
    public static Drawable a(@af TypedArray typedArray, @as int i, @as int i2) {
        Drawable drawable = typedArray.getDrawable(i);
        return drawable == null ? typedArray.getDrawable(i2) : drawable;
    }

    public static b a(@af TypedArray typedArray, @af XmlPullParser xmlPullParser, @ag Resources.Theme theme, @af String str, @as int i, @k int i2) {
        if (a(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i, typedValue);
            if (typedValue.type >= 28 && typedValue.type <= 31) {
                return b.a(typedValue.data);
            }
            b a2 = b.a(typedArray.getResources(), typedArray.getResourceId(i, 0), theme);
            if (a2 != null) {
                return a2;
            }
        }
        return b.a(i2);
    }

    @ag
    public static String a(@af TypedArray typedArray, @af XmlPullParser xmlPullParser, @af String str, @as int i) {
        if (a(xmlPullParser, str)) {
            return typedArray.getString(i);
        }
        return null;
    }

    public static boolean a(@af TypedArray typedArray, @as int i, @as int i2, boolean z) {
        return typedArray.getBoolean(i, typedArray.getBoolean(i2, z));
    }

    public static boolean a(@af TypedArray typedArray, @af XmlPullParser xmlPullParser, @af String str, @as int i, boolean z) {
        return !a(xmlPullParser, str) ? z : typedArray.getBoolean(i, z);
    }

    public static boolean a(@af XmlPullParser xmlPullParser, @af String str) {
        return xmlPullParser.getAttributeValue(a, str) != null;
    }

    @android.support.annotation.c
    public static int b(@af TypedArray typedArray, @as int i, @as int i2, @android.support.annotation.c int i3) {
        return typedArray.getResourceId(i, typedArray.getResourceId(i2, i3));
    }

    @k
    public static int b(@af TypedArray typedArray, @af XmlPullParser xmlPullParser, @af String str, @as int i, @k int i2) {
        return !a(xmlPullParser, str) ? i2 : typedArray.getColor(i, i2);
    }

    @ag
    public static TypedValue b(@af TypedArray typedArray, @af XmlPullParser xmlPullParser, @af String str, int i) {
        if (a(xmlPullParser, str)) {
            return typedArray.peekValue(i);
        }
        return null;
    }

    @ag
    public static String b(@af TypedArray typedArray, @as int i, @as int i2) {
        String string = typedArray.getString(i);
        return string == null ? typedArray.getString(i2) : string;
    }

    @android.support.annotation.c
    public static int c(@af TypedArray typedArray, @af XmlPullParser xmlPullParser, @af String str, @as int i, @android.support.annotation.c int i2) {
        return !a(xmlPullParser, str) ? i2 : typedArray.getResourceId(i, i2);
    }

    @ag
    public static CharSequence c(@af TypedArray typedArray, @as int i, @as int i2) {
        CharSequence text = typedArray.getText(i);
        return text == null ? typedArray.getText(i2) : text;
    }

    @ag
    public static CharSequence[] d(@af TypedArray typedArray, @as int i, @as int i2) {
        CharSequence[] textArray = typedArray.getTextArray(i);
        return textArray == null ? typedArray.getTextArray(i2) : textArray;
    }
}
