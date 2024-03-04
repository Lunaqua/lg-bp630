package android.support.v7.c.a;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.support.v4.content.b.h;
import android.support.v7.a.a;
import android.support.v7.c.a.b;
import android.util.AttributeSet;
import android.util.StateSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class e extends b {
    private static final String a = "StateListDrawable";
    private static final boolean b = false;
    private a c;
    private boolean d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a extends b.AbstractC0041b {
        int[][] L;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(a aVar, e eVar, Resources resources) {
            super(aVar, eVar, resources);
            if (aVar != null) {
                this.L = aVar.L;
            } else {
                this.L = new int[c()];
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int a(int[] iArr, Drawable drawable) {
            int a = a(drawable);
            this.L[a] = iArr;
            return a;
        }

        @Override // android.support.v7.c.a.b.AbstractC0041b
        void a() {
            int[][] iArr = this.L;
            int[][] iArr2 = new int[iArr.length];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[][] iArr3 = this.L;
                iArr2[length] = iArr3[length] != null ? (int[]) iArr3[length].clone() : null;
            }
            this.L = iArr2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int b(int[] iArr) {
            int[][] iArr2 = this.L;
            int d = d();
            for (int i = 0; i < d; i++) {
                if (StateSet.stateSetMatches(iArr2[i], iArr)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // android.support.v7.c.a.b.AbstractC0041b
        public void e(int i, int i2) {
            super.e(i, i2);
            int[][] iArr = new int[i2];
            System.arraycopy(this.L, 0, iArr, 0, i);
            this.L = iArr;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @af
        public Drawable newDrawable() {
            return new e(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @af
        public Drawable newDrawable(Resources resources) {
            return new e(this, resources);
        }
    }

    e() {
        this(null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(@ag a aVar) {
        if (aVar != null) {
            a(aVar);
        }
    }

    e(a aVar, Resources resources) {
        a(new a(aVar, this, resources));
        onStateChange(getState());
    }

    private void a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int next;
        a aVar = this.c;
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next2 = xmlPullParser.next();
            if (next2 == 1) {
                return;
            }
            int depth2 = xmlPullParser.getDepth();
            if (depth2 < depth && next2 == 3) {
                return;
            }
            if (next2 == 2 && depth2 <= depth && xmlPullParser.getName().equals("item")) {
                TypedArray a2 = h.a(resources, theme, attributeSet, a.l.StateListDrawableItem);
                int resourceId = a2.getResourceId(a.l.StateListDrawableItem_android_drawable, -1);
                Drawable b2 = resourceId > 0 ? android.support.v7.b.a.a.b(context, resourceId) : null;
                a2.recycle();
                int[] a3 = a(attributeSet);
                if (b2 == null) {
                    do {
                        next = xmlPullParser.next();
                    } while (next == 4);
                    if (next != 2) {
                        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
                    }
                    b2 = Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
                }
                aVar.a(a3, b2);
            }
        }
    }

    private void a(TypedArray typedArray) {
        a aVar = this.c;
        if (Build.VERSION.SDK_INT >= 21) {
            aVar.f |= typedArray.getChangingConfigurations();
        }
        aVar.k = typedArray.getBoolean(a.l.StateListDrawable_android_variablePadding, aVar.k);
        aVar.n = typedArray.getBoolean(a.l.StateListDrawable_android_constantSize, aVar.n);
        aVar.C = typedArray.getInt(a.l.StateListDrawable_android_enterFadeDuration, aVar.C);
        aVar.D = typedArray.getInt(a.l.StateListDrawable_android_exitFadeDuration, aVar.D);
        aVar.z = typedArray.getBoolean(a.l.StateListDrawable_android_dither, aVar.z);
    }

    int a(int[] iArr) {
        return this.c.b(iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.c.a.b
    public void a(@af b.AbstractC0041b abstractC0041b) {
        super.a(abstractC0041b);
        if (abstractC0041b instanceof a) {
            this.c = (a) abstractC0041b;
        }
    }

    public void a(int[] iArr, Drawable drawable) {
        if (drawable != null) {
            this.c.a(iArr, drawable);
            onStateChange(getState());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] a(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i = 0;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i2);
            if (attributeNameResource != 0 && attributeNameResource != 16842960 && attributeNameResource != 16843161) {
                int i3 = i + 1;
                if (!attributeSet.getAttributeBooleanValue(i2, false)) {
                    attributeNameResource = -attributeNameResource;
                }
                iArr[i] = attributeNameResource;
                i = i3;
            }
        }
        return StateSet.trimStateSet(iArr, i);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    @ak(a = 21)
    public void applyTheme(@af Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.c.a.b
    public void b() {
        super.b();
        this.d = false;
    }

    public void b(@af Context context, @af Resources resources, @af XmlPullParser xmlPullParser, @af AttributeSet attributeSet, @ag Resources.Theme theme) {
        TypedArray a2 = h.a(resources, theme, attributeSet, a.l.StateListDrawable);
        setVisible(a2.getBoolean(a.l.StateListDrawable_android_visible, true), true);
        a(a2);
        a(resources);
        a2.recycle();
        a(context, resources, xmlPullParser, attributeSet, theme);
        onStateChange(getState());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.c.a.b
    /* renamed from: c */
    public a d() {
        return new a(this.c, this, null);
    }

    int[] e(int i) {
        return this.c.L[i];
    }

    Drawable f(int i) {
        return this.c.b(i);
    }

    a f() {
        return this.c;
    }

    int g() {
        return this.c.d();
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    @af
    public Drawable mutate() {
        if (!this.d && super.mutate() == this) {
            this.c.a();
            this.d = true;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        int b2 = this.c.b(iArr);
        if (b2 < 0) {
            b2 = this.c.b(StateSet.WILD_CARD);
        }
        return d(b2) || onStateChange;
    }
}
