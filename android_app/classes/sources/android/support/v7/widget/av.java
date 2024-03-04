package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.an;
import android.support.v4.content.b.g;
import android.util.AttributeSet;
import android.util.TypedValue;
@android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class av {
    private final Context a;
    private final TypedArray b;
    private TypedValue c;

    private av(Context context, TypedArray typedArray) {
        this.a = context;
        this.b = typedArray;
    }

    public static av a(Context context, int i, int[] iArr) {
        return new av(context, context.obtainStyledAttributes(i, iArr));
    }

    public static av a(Context context, AttributeSet attributeSet, int[] iArr) {
        return new av(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static av a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new av(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public float a(int i, float f) {
        return this.b.getFloat(i, f);
    }

    public float a(int i, int i2, int i3, float f) {
        return this.b.getFraction(i, i2, i3, f);
    }

    public int a() {
        return this.b.length();
    }

    public int a(int i, int i2) {
        return this.b.getInt(i, i2);
    }

    public int a(int i, String str) {
        return this.b.getLayoutDimension(i, str);
    }

    @android.support.annotation.ag
    public Typeface a(@android.support.annotation.as int i, int i2, @android.support.annotation.ag g.a aVar) {
        int resourceId = this.b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.c == null) {
            this.c = new TypedValue();
        }
        return android.support.v4.content.b.g.a(this.a, resourceId, this.c, i2, aVar);
    }

    public Drawable a(int i) {
        int resourceId;
        return (!this.b.hasValue(i) || (resourceId = this.b.getResourceId(i, 0)) == 0) ? this.b.getDrawable(i) : android.support.v7.b.a.a.b(this.a, resourceId);
    }

    public boolean a(int i, TypedValue typedValue) {
        return this.b.getValue(i, typedValue);
    }

    public boolean a(int i, boolean z) {
        return this.b.getBoolean(i, z);
    }

    public float b(int i, float f) {
        return this.b.getDimension(i, f);
    }

    public int b() {
        return this.b.getIndexCount();
    }

    public int b(int i, int i2) {
        return this.b.getColor(i, i2);
    }

    public Drawable b(int i) {
        int resourceId;
        if (!this.b.hasValue(i) || (resourceId = this.b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return g.a().a(this.a, resourceId, true);
    }

    public int c(int i) {
        return this.b.getIndex(i);
    }

    public int c(int i, int i2) {
        return this.b.getInteger(i, i2);
    }

    public Resources c() {
        return this.b.getResources();
    }

    public int d(int i, int i2) {
        return this.b.getDimensionPixelOffset(i, i2);
    }

    public CharSequence d(int i) {
        return this.b.getText(i);
    }

    public String d() {
        return this.b.getPositionDescription();
    }

    public int e(int i, int i2) {
        return this.b.getDimensionPixelSize(i, i2);
    }

    public String e(int i) {
        return this.b.getString(i);
    }

    public void e() {
        this.b.recycle();
    }

    @android.support.annotation.ak(a = 21)
    public int f() {
        return this.b.getChangingConfigurations();
    }

    public int f(int i, int i2) {
        return this.b.getLayoutDimension(i, i2);
    }

    public String f(int i) {
        return this.b.getNonResourceString(i);
    }

    public int g(int i, int i2) {
        return this.b.getResourceId(i, i2);
    }

    public ColorStateList g(int i) {
        int resourceId;
        ColorStateList a;
        return (!this.b.hasValue(i) || (resourceId = this.b.getResourceId(i, 0)) == 0 || (a = android.support.v7.b.a.a.a(this.a, resourceId)) == null) ? this.b.getColorStateList(i) : a;
    }

    public CharSequence[] h(int i) {
        return this.b.getTextArray(i);
    }

    public int i(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.b.getType(i);
        }
        if (this.c == null) {
            this.c = new TypedValue();
        }
        this.b.getValue(i, this.c);
        return this.c.type;
    }

    public boolean j(int i) {
        return this.b.hasValue(i);
    }

    public TypedValue k(int i) {
        return this.b.peekValue(i);
    }
}
