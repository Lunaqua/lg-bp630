package android.support.v4.content.b;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.annotation.k;
import android.support.annotation.m;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParserException;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class b {
    private static final String a = "ComplexColorCompat";
    private final Shader b;
    private final ColorStateList c;
    private int d;

    private b(Shader shader, ColorStateList colorStateList, @k int i) {
        this.b = shader;
        this.c = colorStateList;
        this.d = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a(@k int i) {
        return new b(null, null, i);
    }

    static b a(@af ColorStateList colorStateList) {
        return new b(null, colorStateList, colorStateList.getDefaultColor());
    }

    @ag
    public static b a(@af Resources resources, @m int i, @ag Resources.Theme theme) {
        try {
            return b(resources, i, theme);
        } catch (Exception e) {
            Log.e(a, "Failed to inflate ComplexColor.", e);
            return null;
        }
    }

    static b a(@af Shader shader) {
        return new b(shader, null, 0);
    }

    @af
    private static b b(@af Resources resources, @m int i, @ag Resources.Theme theme) {
        int next;
        XmlResourceParser xml = resources.getXml(i);
        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            String name = xml.getName();
            char c = 65535;
            int hashCode = name.hashCode();
            if (hashCode != 89650992) {
                if (hashCode == 1191572447 && name.equals("selector")) {
                    c = 0;
                }
            } else if (name.equals("gradient")) {
                c = 1;
            }
            if (c != 0) {
                if (c == 1) {
                    return a(e.a(resources, xml, asAttributeSet, theme));
                }
                throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
            }
            return a(a.a(resources, xml, asAttributeSet, theme));
        }
        throw new XmlPullParserException("No start tag found");
    }

    @ag
    public Shader a() {
        return this.b;
    }

    public boolean a(int[] iArr) {
        if (d()) {
            ColorStateList colorStateList = this.c;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.d) {
                this.d = colorForState;
                return true;
            }
        }
        return false;
    }

    @k
    public int b() {
        return this.d;
    }

    public void b(@k int i) {
        this.d = i;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean d() {
        ColorStateList colorStateList;
        return this.b == null && (colorStateList = this.c) != null && colorStateList.isStateful();
    }

    public boolean e() {
        return c() || this.d != 0;
    }
}
