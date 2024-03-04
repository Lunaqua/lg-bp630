package android.support.k.a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.p;
import android.support.k.a.b;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c extends i implements android.support.k.a.b {
    private static final String e = "AnimatedVDCompat";
    private static final String f = "animated-vector";
    private static final String g = "target";
    private static final boolean h = false;
    b a;
    ArrayList<b.a> b;
    final Drawable.Callback c;
    private a i;
    private Context j;
    private ArgbEvaluator k;
    private Animator.AnimatorListener l;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a extends Drawable.ConstantState {
        int a;
        j b;
        AnimatorSet c;
        ArrayList<Animator> d;
        android.support.v4.j.a<Animator, String> e;

        public a(Context context, a aVar, Drawable.Callback callback, Resources resources) {
            if (aVar != null) {
                this.a = aVar.a;
                j jVar = aVar.b;
                if (jVar != null) {
                    Drawable.ConstantState constantState = jVar.getConstantState();
                    this.b = (j) (resources != null ? constantState.newDrawable(resources) : constantState.newDrawable());
                    this.b = (j) this.b.mutate();
                    this.b.setCallback(callback);
                    this.b.setBounds(aVar.b.getBounds());
                    this.b.a(false);
                }
                ArrayList<Animator> arrayList = aVar.d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.d = new ArrayList<>(size);
                    this.e = new android.support.v4.j.a<>(size);
                    for (int i = 0; i < size; i++) {
                        Animator animator = aVar.d.get(i);
                        Animator clone = animator.clone();
                        String str = aVar.e.get(animator);
                        clone.setTarget(this.b.a(str));
                        this.d.add(clone);
                        this.e.put(clone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.c == null) {
                this.c = new AnimatorSet();
            }
            this.c.playTogether(this.d);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }
    }

    @ak(a = 24)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class b extends Drawable.ConstantState {
        private final Drawable.ConstantState a;

        public b(Drawable.ConstantState constantState) {
            this.a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            c cVar = new c();
            cVar.d = this.a.newDrawable();
            cVar.d.setCallback(cVar.c);
            return cVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            c cVar = new c();
            cVar.d = this.a.newDrawable(resources);
            cVar.d.setCallback(cVar.c);
            return cVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            c cVar = new c();
            cVar.d = this.a.newDrawable(resources, theme);
            cVar.d.setCallback(cVar.c);
            return cVar;
        }
    }

    c() {
        this(null, null, null);
    }

    private c(@ag Context context) {
        this(context, null, null);
    }

    private c(@ag Context context, @ag a aVar, @ag Resources resources) {
        this.k = null;
        this.l = null;
        this.b = null;
        this.c = new Drawable.Callback() { // from class: android.support.k.a.c.1
            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable drawable) {
                c.this.invalidateSelf();
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
                c.this.scheduleSelf(runnable, j);
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
                c.this.unscheduleSelf(runnable);
            }
        };
        this.j = context;
        if (aVar != null) {
            this.i = aVar;
        } else {
            this.i = new a(context, aVar, this.c, resources);
        }
    }

    @ag
    public static c a(@af Context context, @p int i) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            c cVar = new c(context);
            cVar.d = android.support.v4.content.b.g.a(context.getResources(), i, context.getTheme());
            cVar.d.setCallback(cVar.c);
            cVar.a = new b(cVar.d.getConstantState());
            return cVar;
        }
        try {
            XmlResourceParser xml = context.getResources().getXml(i);
            AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
            while (true) {
                next = xml.next();
                if (next == 2 || next == 1) {
                    break;
                }
            }
            if (next == 2) {
                return a(context, context.getResources(), xml, asAttributeSet, context.getTheme());
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException | XmlPullParserException e2) {
            Log.e(e, "parser error", e2);
            return null;
        }
    }

    public static c a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        c cVar = new c(context);
        cVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return cVar;
    }

    private void a(Animator animator) {
        ArrayList<Animator> childAnimations;
        if ((animator instanceof AnimatorSet) && (childAnimations = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i = 0; i < childAnimations.size(); i++) {
                a(childAnimations.get(i));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.k == null) {
                    this.k = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.k);
            }
        }
    }

    public static void a(Drawable drawable) {
        if (drawable == null || !(drawable instanceof Animatable)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            ((AnimatedVectorDrawable) drawable).clearAnimationCallbacks();
        } else {
            ((c) drawable).a();
        }
    }

    public static void a(Drawable drawable, b.a aVar) {
        if (drawable == null || aVar == null || !(drawable instanceof Animatable)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            b((AnimatedVectorDrawable) drawable, aVar);
        } else {
            ((c) drawable).a(aVar);
        }
    }

    private void a(String str, Animator animator) {
        animator.setTarget(this.i.b.a(str));
        if (Build.VERSION.SDK_INT < 21) {
            a(animator);
        }
        if (this.i.d == null) {
            this.i.d = new ArrayList<>();
            this.i.e = new android.support.v4.j.a<>();
        }
        this.i.d.add(animator);
        this.i.e.put(animator, str);
    }

    @ak(a = 23)
    private static boolean a(AnimatedVectorDrawable animatedVectorDrawable, b.a aVar) {
        return animatedVectorDrawable.unregisterAnimationCallback(aVar.a());
    }

    private void b() {
        if (this.l != null) {
            this.i.c.removeListener(this.l);
            this.l = null;
        }
    }

    @ak(a = 23)
    private static void b(@af AnimatedVectorDrawable animatedVectorDrawable, @af b.a aVar) {
        animatedVectorDrawable.registerAnimationCallback(aVar.a());
    }

    public static boolean b(Drawable drawable, b.a aVar) {
        if (drawable == null || aVar == null || !(drawable instanceof Animatable)) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 24 ? a((AnimatedVectorDrawable) drawable, aVar) : ((c) drawable).b(aVar);
    }

    @Override // android.support.k.a.b
    public void a() {
        if (this.d != null) {
            ((AnimatedVectorDrawable) this.d).clearAnimationCallbacks();
            return;
        }
        b();
        ArrayList<b.a> arrayList = this.b;
        if (arrayList == null) {
            return;
        }
        arrayList.clear();
    }

    @Override // android.support.k.a.b
    public void a(@af b.a aVar) {
        if (this.d != null) {
            b((AnimatedVectorDrawable) this.d, aVar);
        } else if (aVar == null) {
        } else {
            if (this.b == null) {
                this.b = new ArrayList<>();
            }
            if (this.b.contains(aVar)) {
                return;
            }
            this.b.add(aVar);
            if (this.l == null) {
                this.l = new AnimatorListenerAdapter() { // from class: android.support.k.a.c.2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        ArrayList arrayList = new ArrayList(c.this.b);
                        int size = arrayList.size();
                        for (int i = 0; i < size; i++) {
                            ((b.a) arrayList.get(i)).b(c.this);
                        }
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        ArrayList arrayList = new ArrayList(c.this.b);
                        int size = arrayList.size();
                        for (int i = 0; i < size; i++) {
                            ((b.a) arrayList.get(i)).a(c.this);
                        }
                    }
                };
            }
            this.i.c.addListener(this.l);
        }
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.a(this.d, theme);
        }
    }

    @Override // android.support.k.a.b
    public boolean b(@af b.a aVar) {
        if (this.d != null) {
            a((AnimatedVectorDrawable) this.d, aVar);
        }
        ArrayList<b.a> arrayList = this.b;
        if (arrayList == null || aVar == null) {
            return false;
        }
        boolean remove = arrayList.remove(aVar);
        if (this.b.size() == 0) {
            b();
        }
        return remove;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        if (this.d != null) {
            return android.support.v4.graphics.drawable.a.d(this.d);
        }
        return false;
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.d != null) {
            this.d.draw(canvas);
            return;
        }
        this.i.b.draw(canvas);
        if (this.i.c.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.d != null ? android.support.v4.graphics.drawable.a.c(this.d) : this.i.b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return this.d != null ? this.d.getChangingConfigurations() : super.getChangingConfigurations() | this.i.a;
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.d == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new b(this.d.getConstantState());
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.d != null ? this.d.getIntrinsicHeight() : this.i.b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.d != null ? this.d.getIntrinsicWidth() : this.i.b.getIntrinsicWidth();
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.d != null ? this.d.getOpacity() : this.i.b.getOpacity();
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray obtainAttributes;
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.a(this.d, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (f.equals(name)) {
                    obtainAttributes = android.support.v4.content.b.h.a(resources, theme, attributeSet, android.support.k.a.a.L);
                    int resourceId = obtainAttributes.getResourceId(0, 0);
                    if (resourceId != 0) {
                        j a2 = j.a(resources, resourceId, theme);
                        a2.a(false);
                        a2.setCallback(this.c);
                        if (this.i.b != null) {
                            this.i.b.setCallback(null);
                        }
                        this.i.b = a2;
                    }
                } else if (g.equals(name)) {
                    obtainAttributes = resources.obtainAttributes(attributeSet, android.support.k.a.a.N);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.j;
                        if (context == null) {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                        a(string, e.a(context, resourceId2));
                    }
                } else {
                    continue;
                }
                obtainAttributes.recycle();
            }
            eventType = xmlPullParser.next();
        }
        this.i.a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.d != null ? android.support.v4.graphics.drawable.a.b(this.d) : this.i.b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.d != null ? ((AnimatedVectorDrawable) this.d).isRunning() : this.i.c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.d != null ? this.d.isStateful() : this.i.b.isStateful();
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (this.d != null) {
            this.d.mutate();
        }
        return this;
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        if (this.d != null) {
            this.d.setBounds(rect);
        } else {
            this.i.b.setBounds(rect);
        }
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        return this.d != null ? this.d.setLevel(i) : this.i.b.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        return this.d != null ? this.d.setState(iArr) : this.i.b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.d != null) {
            this.d.setAlpha(i);
        } else {
            this.i.b.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.a(this.d, z);
        } else {
            this.i.b.setAutoMirrored(z);
        }
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.d != null) {
            this.d.setColorFilter(colorFilter);
        } else {
            this.i.b.setColorFilter(colorFilter);
        }
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTint(int i) {
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.a(this.d, i);
        } else {
            this.i.b.setTint(i);
        }
    }

    @Override // android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTintList(ColorStateList colorStateList) {
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.a(this.d, colorStateList);
        } else {
            this.i.b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.a(this.d, mode);
        } else {
            this.i.b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        if (this.d != null) {
            return this.d.setVisible(z, z2);
        }
        this.i.b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        if (this.d != null) {
            ((AnimatedVectorDrawable) this.d).start();
        } else if (this.i.c.isStarted()) {
        } else {
            this.i.c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (this.d != null) {
            ((AnimatedVectorDrawable) this.d).stop();
        } else {
            this.i.c.end();
        }
    }
}
