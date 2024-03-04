package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v4.view.ab;
import android.view.animation.Animation;
import android.widget.ImageView;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c extends ImageView {
    private static final int b = 503316480;
    private static final int c = 1023410176;
    private static final float d = 0.0f;
    private static final float e = 1.75f;
    private static final float f = 3.5f;
    private static final int g = 4;
    int a;
    private Animation.AnimationListener h;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class a extends OvalShape {
        private RadialGradient b;
        private Paint c = new Paint();

        a(int i) {
            c.this.a = i;
            a((int) rect().width());
        }

        private void a(int i) {
            float f = i / 2;
            this.b = new RadialGradient(f, f, c.this.a, new int[]{c.c, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.c.setShader(this.b);
        }

        @Override // android.graphics.drawable.shapes.OvalShape, android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void draw(Canvas canvas, Paint paint) {
            int width;
            float width2 = c.this.getWidth() / 2;
            float height = c.this.getHeight() / 2;
            canvas.drawCircle(width2, height, width2, this.c);
            canvas.drawCircle(width2, height, width - c.this.a, paint);
        }

        @Override // android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        protected void onResize(float f, float f2) {
            super.onResize(f, f2);
            a((int) f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context, int i) {
        super(context);
        ShapeDrawable shapeDrawable;
        float f2 = getContext().getResources().getDisplayMetrics().density;
        int i2 = (int) (e * f2);
        int i3 = (int) (0.0f * f2);
        this.a = (int) (f * f2);
        if (a()) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
            ab.m(this, f2 * 4.0f);
        } else {
            ShapeDrawable shapeDrawable2 = new ShapeDrawable(new a(this.a));
            setLayerType(1, shapeDrawable2.getPaint());
            shapeDrawable2.getPaint().setShadowLayer(this.a, i3, i2, b);
            int i4 = this.a;
            setPadding(i4, i4, i4, i4);
            shapeDrawable = shapeDrawable2;
        }
        shapeDrawable.getPaint().setColor(i);
        ab.a(this, shapeDrawable);
    }

    private boolean a() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public void a(int i) {
        setBackgroundColor(android.support.v4.content.c.getColor(getContext(), i));
    }

    public void a(Animation.AnimationListener animationListener) {
        this.h = animationListener;
    }

    @Override // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.h;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.h;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (a()) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth() + (this.a * 2), getMeasuredHeight() + (this.a * 2));
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i);
        }
    }
}
