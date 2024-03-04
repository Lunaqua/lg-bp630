package android.support.v7.c.a;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.p;
import android.support.v4.content.b.h;
import android.support.v4.j.j;
import android.support.v4.j.s;
import android.support.v7.a.a;
import android.support.v7.c.a.b;
import android.support.v7.c.a.e;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class a extends android.support.v7.c.a.e {
    private static final String a = "a";
    private static final String b = "transition";
    private static final String c = "item";
    private static final String d = ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable";
    private static final String e = ": <transition> tag requires 'fromId' & 'toId' attributes";
    private static final String f = ": <item> tag requires a 'drawable' attribute or child tag defining a drawable";
    private b g;
    private f h;
    private int i;
    private int j;
    private boolean k;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: android.support.v7.c.a.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class C0040a extends f {
        private final Animatable a;

        C0040a(Animatable animatable) {
            super();
            this.a = animatable;
        }

        @Override // android.support.v7.c.a.a.f
        public void a() {
            this.a.start();
        }

        @Override // android.support.v7.c.a.a.f
        public void b() {
            this.a.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b extends e.a {
        private static final long M = 4294967296L;
        private static final long N = 8589934592L;
        j<Long> a;
        s<Integer> b;

        b(@ag b bVar, @af a aVar, @ag Resources resources) {
            super(bVar, aVar, resources);
            s<Integer> sVar;
            if (bVar != null) {
                this.a = bVar.a;
                sVar = bVar.b;
            } else {
                this.a = new j<>();
                sVar = new s<>();
            }
            this.b = sVar;
        }

        private static long f(int i, int i2) {
            return i2 | (i << 32);
        }

        int a(int i) {
            if (i < 0) {
                return 0;
            }
            return this.b.a(i, 0).intValue();
        }

        int a(int i, int i2) {
            return (int) this.a.a(f(i, i2), -1L).longValue();
        }

        int a(int i, int i2, @af Drawable drawable, boolean z) {
            int a = super.a(drawable);
            long f = f(i, i2);
            long j = z ? N : 0L;
            long j2 = a;
            this.a.c(f, Long.valueOf(j2 | j));
            if (z) {
                this.a.c(f(i2, i), Long.valueOf(M | j2 | j));
            }
            return a;
        }

        int a(@af int[] iArr) {
            int b = super.b(iArr);
            return b >= 0 ? b : super.b(StateSet.WILD_CARD);
        }

        int a(@af int[] iArr, @af Drawable drawable, int i) {
            int a = super.a(iArr, drawable);
            this.b.b(a, Integer.valueOf(i));
            return a;
        }

        @Override // android.support.v7.c.a.e.a, android.support.v7.c.a.b.AbstractC0041b
        void a() {
            this.a = this.a.clone();
            this.b = this.b.clone();
        }

        boolean b(int i, int i2) {
            return (this.a.a(f(i, i2), -1L).longValue() & M) != 0;
        }

        boolean c(int i, int i2) {
            return (this.a.a(f(i, i2), -1L).longValue() & N) != 0;
        }

        @Override // android.support.v7.c.a.e.a, android.graphics.drawable.Drawable.ConstantState
        @af
        public Drawable newDrawable() {
            return new a(this, null);
        }

        @Override // android.support.v7.c.a.e.a, android.graphics.drawable.Drawable.ConstantState
        @af
        public Drawable newDrawable(Resources resources) {
            return new a(this, resources);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class c extends f {
        private final android.support.k.a.c a;

        c(android.support.k.a.c cVar) {
            super();
            this.a = cVar;
        }

        @Override // android.support.v7.c.a.a.f
        public void a() {
            this.a.start();
        }

        @Override // android.support.v7.c.a.a.f
        public void b() {
            this.a.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class d extends f {
        private final ObjectAnimator a;
        private final boolean b;

        d(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i = z ? numberOfFrames - 1 : 0;
            int i2 = z ? 0 : numberOfFrames - 1;
            e eVar = new e(animationDrawable, z);
            ObjectAnimator ofInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i, i2);
            if (Build.VERSION.SDK_INT >= 18) {
                ofInt.setAutoCancel(true);
            }
            ofInt.setDuration(eVar.a());
            ofInt.setInterpolator(eVar);
            this.b = z2;
            this.a = ofInt;
        }

        @Override // android.support.v7.c.a.a.f
        public void a() {
            this.a.start();
        }

        @Override // android.support.v7.c.a.a.f
        public void b() {
            this.a.cancel();
        }

        @Override // android.support.v7.c.a.a.f
        public boolean c() {
            return this.b;
        }

        @Override // android.support.v7.c.a.a.f
        public void d() {
            this.a.reverse();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class e implements TimeInterpolator {
        private int[] a;
        private int b;
        private int c;

        e(AnimationDrawable animationDrawable, boolean z) {
            a(animationDrawable, z);
        }

        int a() {
            return this.c;
        }

        int a(AnimationDrawable animationDrawable, boolean z) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.b = numberOfFrames;
            int[] iArr = this.a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.a = new int[numberOfFrames];
            }
            int[] iArr2 = this.a;
            int i = 0;
            for (int i2 = 0; i2 < numberOfFrames; i2++) {
                int duration = animationDrawable.getDuration(z ? (numberOfFrames - i2) - 1 : i2);
                iArr2[i2] = duration;
                i += duration;
            }
            this.c = i;
            return i;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            int i = (int) ((f * this.c) + 0.5f);
            int i2 = this.b;
            int[] iArr = this.a;
            int i3 = 0;
            while (i3 < i2 && i >= iArr[i3]) {
                i -= iArr[i3];
                i3++;
            }
            return (i3 / i2) + (i3 < i2 ? i / this.c : 0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class f {
        private f() {
        }

        public abstract void a();

        public abstract void b();

        public boolean c() {
            return false;
        }

        public void d() {
        }
    }

    public a() {
        this(null, null);
    }

    a(@ag b bVar, @ag Resources resources) {
        super(null);
        this.i = -1;
        this.j = -1;
        a(new b(bVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    @ag
    public static a a(@af Context context, @p int i, @ag Resources.Theme theme) {
        int next;
        try {
            Resources resources = context.getResources();
            XmlResourceParser xml = resources.getXml(i);
            AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
            while (true) {
                next = xml.next();
                if (next == 2 || next == 1) {
                    break;
                }
            }
            if (next == 2) {
                return a(context, resources, (XmlPullParser) xml, asAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException | XmlPullParserException e2) {
            Log.e(a, "parser error", e2);
            return null;
        }
    }

    public static a a(@af Context context, @af Resources resources, @af XmlPullParser xmlPullParser, @af AttributeSet attributeSet, @ag Resources.Theme theme) {
        String name = xmlPullParser.getName();
        if (name.equals("animated-selector")) {
            a aVar = new a();
            aVar.b(context, resources, xmlPullParser, attributeSet, theme);
            return aVar;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + name);
    }

    private void a(TypedArray typedArray) {
        b bVar = this.g;
        if (Build.VERSION.SDK_INT >= 21) {
            bVar.f |= typedArray.getChangingConfigurations();
        }
        bVar.a(typedArray.getBoolean(a.l.AnimatedStateListDrawableCompat_android_variablePadding, bVar.k));
        bVar.b(typedArray.getBoolean(a.l.AnimatedStateListDrawableCompat_android_constantSize, bVar.n));
        bVar.c(typedArray.getInt(a.l.AnimatedStateListDrawableCompat_android_enterFadeDuration, bVar.C));
        bVar.d(typedArray.getInt(a.l.AnimatedStateListDrawableCompat_android_exitFadeDuration, bVar.D));
        setDither(typedArray.getBoolean(a.l.AnimatedStateListDrawableCompat_android_dither, bVar.z));
    }

    private void c(@af Context context, @af Resources resources, @af XmlPullParser xmlPullParser, @af AttributeSet attributeSet, @ag Resources.Theme theme) {
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            int depth2 = xmlPullParser.getDepth();
            if (depth2 < depth && next == 3) {
                return;
            }
            if (next == 2 && depth2 <= depth) {
                if (xmlPullParser.getName().equals(c)) {
                    e(context, resources, xmlPullParser, attributeSet, theme);
                } else if (xmlPullParser.getName().equals(b)) {
                    d(context, resources, xmlPullParser, attributeSet, theme);
                }
            }
        }
    }

    private int d(@af Context context, @af Resources resources, @af XmlPullParser xmlPullParser, @af AttributeSet attributeSet, @ag Resources.Theme theme) {
        int next;
        TypedArray a2 = h.a(resources, theme, attributeSet, a.l.AnimatedStateListDrawableTransition);
        int resourceId = a2.getResourceId(a.l.AnimatedStateListDrawableTransition_android_fromId, -1);
        int resourceId2 = a2.getResourceId(a.l.AnimatedStateListDrawableTransition_android_toId, -1);
        int resourceId3 = a2.getResourceId(a.l.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable b2 = resourceId3 > 0 ? android.support.v7.b.a.a.b(context, resourceId3) : null;
        boolean z = a2.getBoolean(a.l.AnimatedStateListDrawableTransition_android_reversible, false);
        a2.recycle();
        if (b2 == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + d);
            }
            b2 = xmlPullParser.getName().equals("animated-vector") ? android.support.k.a.c.a(context, resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
        }
        if (b2 == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + d);
        } else if (resourceId == -1 || resourceId2 == -1) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + e);
        } else {
            return this.g.a(resourceId, resourceId2, b2, z);
        }
    }

    private int e(@af Context context, @af Resources resources, @af XmlPullParser xmlPullParser, @af AttributeSet attributeSet, @ag Resources.Theme theme) {
        int next;
        TypedArray a2 = h.a(resources, theme, attributeSet, a.l.AnimatedStateListDrawableItem);
        int resourceId = a2.getResourceId(a.l.AnimatedStateListDrawableItem_android_id, 0);
        int resourceId2 = a2.getResourceId(a.l.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable b2 = resourceId2 > 0 ? android.support.v7.b.a.a.b(context, resourceId2) : null;
        a2.recycle();
        int[] a3 = a(attributeSet);
        if (b2 == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + f);
            }
            b2 = xmlPullParser.getName().equals("vector") ? android.support.k.a.j.a(resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
        }
        if (b2 != null) {
            return this.g.a(a3, b2, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + f);
    }

    private boolean g(int i) {
        int e2;
        int a2;
        f c0040a;
        f fVar = this.h;
        if (fVar == null) {
            e2 = e();
        } else if (i == this.i) {
            return true;
        } else {
            if (i == this.j && fVar.c()) {
                fVar.d();
                this.i = this.j;
                this.j = i;
                return true;
            }
            e2 = this.i;
            fVar.b();
        }
        this.h = null;
        this.j = -1;
        this.i = -1;
        b bVar = this.g;
        int a3 = bVar.a(e2);
        int a4 = bVar.a(i);
        if (a4 == 0 || a3 == 0 || (a2 = bVar.a(a3, a4)) < 0) {
            return false;
        }
        boolean c2 = bVar.c(a3, a4);
        d(a2);
        Drawable current = getCurrent();
        if (current instanceof AnimationDrawable) {
            c0040a = new d((AnimationDrawable) current, bVar.b(a3, a4), c2);
        } else if (!(current instanceof android.support.k.a.c)) {
            if (current instanceof Animatable) {
                c0040a = new C0040a((Animatable) current);
            }
            return false;
        } else {
            c0040a = new c((android.support.k.a.c) current);
        }
        c0040a.a();
        this.h = c0040a;
        this.j = e2;
        this.i = i;
        return true;
    }

    private void h() {
        onStateChange(getState());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.c.a.e, android.support.v7.c.a.b
    /* renamed from: a */
    public b d() {
        return new b(this.g, this, null);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void a(int i) {
        super.a(i);
    }

    public <T extends Drawable & Animatable> void a(int i, int i2, @af T t, boolean z) {
        if (t == null) {
            throw new IllegalArgumentException("Transition drawable must not be null");
        }
        this.g.a(i, i2, t, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.c.a.e, android.support.v7.c.a.b
    public void a(@af b.AbstractC0041b abstractC0041b) {
        super.a(abstractC0041b);
        if (abstractC0041b instanceof b) {
            this.g = (b) abstractC0041b;
        }
    }

    @Override // android.support.v7.c.a.e
    public /* bridge */ /* synthetic */ void a(int[] iArr, Drawable drawable) {
        super.a(iArr, drawable);
    }

    public void a(@af int[] iArr, @af Drawable drawable, int i) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable must not be null");
        }
        this.g.a(iArr, drawable, i);
        onStateChange(getState());
    }

    @Override // android.support.v7.c.a.e, android.support.v7.c.a.b, android.graphics.drawable.Drawable
    @ak(a = 21)
    public /* bridge */ /* synthetic */ void applyTheme(@af Resources.Theme theme) {
        super.applyTheme(theme);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.c.a.e, android.support.v7.c.a.b
    public void b() {
        super.b();
        this.k = false;
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void b(int i) {
        super.b(i);
    }

    @Override // android.support.v7.c.a.e
    public void b(@af Context context, @af Resources resources, @af XmlPullParser xmlPullParser, @af AttributeSet attributeSet, @ag Resources.Theme theme) {
        TypedArray a2 = h.a(resources, theme, attributeSet, a.l.AnimatedStateListDrawableCompat);
        setVisible(a2.getBoolean(a.l.AnimatedStateListDrawableCompat_android_visible, true), true);
        a(a2);
        a(resources);
        a2.recycle();
        c(context, resources, xmlPullParser, attributeSet, theme);
        h();
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    @ak(a = 21)
    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void draw(@af Canvas canvas) {
        super.draw(canvas);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    @af
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void getHotspotBounds(@af Rect rect) {
        super.getHotspotBounds(rect);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    @ak(a = 21)
    public /* bridge */ /* synthetic */ void getOutline(@af Outline outline) {
        super.getOutline(outline);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(@af Rect rect) {
        return super.getPadding(rect);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void invalidateDrawable(@af Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    @Override // android.support.v7.c.a.e, android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        f fVar = this.h;
        if (fVar != null) {
            fVar.b();
            this.h = null;
            d(this.i);
            this.i = -1;
            this.j = -1;
        }
    }

    @Override // android.support.v7.c.a.e, android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.k && super.mutate() == this) {
            this.g.a();
            this.k = true;
        }
        return this;
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i) {
        return super.onLayoutDirectionChanged(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.c.a.e, android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        int a2 = this.g.a(iArr);
        boolean z = a2 != e() && (g(a2) || d(a2));
        Drawable current = getCurrent();
        return current != null ? z | current.setState(iArr) : z;
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void scheduleDrawable(@af Drawable drawable, @af Runnable runnable, long j) {
        super.scheduleDrawable(drawable, runnable, j);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z) {
        super.setAutoMirrored(z);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setDither(boolean z) {
        super.setDither(z);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setTintMode(@af PorterDuff.Mode mode) {
        super.setTintMode(mode);
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (this.h != null && (visible || z2)) {
            if (z) {
                this.h.a();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    @Override // android.support.v7.c.a.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void unscheduleDrawable(@af Drawable drawable, @af Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }
}
