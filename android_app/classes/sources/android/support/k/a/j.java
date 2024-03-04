package android.support.k.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.support.annotation.k;
import android.support.annotation.p;
import android.support.v4.graphics.d;
import android.support.v4.view.ab;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class j extends i {
    static final String a = "VectorDrawableCompat";
    static final PorterDuff.Mode b = PorterDuff.Mode.SRC_IN;
    private static final String c = "clip-path";
    private static final String e = "group";
    private static final String f = "path";
    private static final String g = "vector";
    private static final int h = 0;
    private static final int i = 1;
    private static final int j = 2;
    private static final int k = 0;
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 2048;
    private static final boolean o = false;
    private g p;
    private PorterDuffColorFilter q;
    private ColorFilter r;
    private boolean s;
    private boolean t;
    private Drawable.ConstantState u;
    private final float[] v;
    private final Matrix w;
    private final Rect x;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a extends e {
        public a() {
        }

        public a(a aVar) {
            super(aVar);
        }

        private void a(TypedArray typedArray) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.n = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.m = android.support.v4.graphics.d.b(string2);
            }
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (android.support.v4.content.b.h.a(xmlPullParser, "pathData")) {
                TypedArray a = android.support.v4.content.b.h.a(resources, theme, attributeSet, android.support.k.a.a.I);
                a(a);
                a.recycle();
            }
        }

        @Override // android.support.k.a.j.e
        public boolean a() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b extends e {
        private static final int q = 0;
        android.support.v4.content.b.b a;
        float b;
        android.support.v4.content.b.b c;
        float d;
        int e;
        float f;
        float g;
        float h;
        float i;
        Paint.Cap j;
        Paint.Join k;
        float l;
        private int[] p;

        public b() {
            this.b = 0.0f;
            this.d = 1.0f;
            this.e = 0;
            this.f = 1.0f;
            this.g = 0.0f;
            this.h = 1.0f;
            this.i = 0.0f;
            this.j = Paint.Cap.BUTT;
            this.k = Paint.Join.MITER;
            this.l = 4.0f;
        }

        public b(b bVar) {
            super(bVar);
            this.b = 0.0f;
            this.d = 1.0f;
            this.e = 0;
            this.f = 1.0f;
            this.g = 0.0f;
            this.h = 1.0f;
            this.i = 0.0f;
            this.j = Paint.Cap.BUTT;
            this.k = Paint.Join.MITER;
            this.l = 4.0f;
            this.p = bVar.p;
            this.a = bVar.a;
            this.b = bVar.b;
            this.d = bVar.d;
            this.c = bVar.c;
            this.e = bVar.e;
            this.f = bVar.f;
            this.g = bVar.g;
            this.h = bVar.h;
            this.i = bVar.i;
            this.j = bVar.j;
            this.k = bVar.k;
            this.l = bVar.l;
        }

        private Paint.Cap a(int i, Paint.Cap cap) {
            return i != 0 ? i != 1 ? i != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        private Paint.Join a(int i, Paint.Join join) {
            return i != 0 ? i != 1 ? i != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.p = null;
            if (android.support.v4.content.b.h.a(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.n = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.m = android.support.v4.graphics.d.b(string2);
                }
                this.c = android.support.v4.content.b.h.a(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.f = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "fillAlpha", 12, this.f);
                this.j = a(android.support.v4.content.b.h.a(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.j);
                this.k = a(android.support.v4.content.b.h.a(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.k);
                this.l = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.l);
                this.a = android.support.v4.content.b.h.a(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.d = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "strokeAlpha", 11, this.d);
                this.b = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "strokeWidth", 4, this.b);
                this.h = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "trimPathEnd", 6, this.h);
                this.i = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "trimPathOffset", 7, this.i);
                this.g = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "trimPathStart", 5, this.g);
                this.e = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "fillType", 13, this.e);
            }
        }

        @Override // android.support.k.a.j.e
        public void a(Resources.Theme theme) {
            if (this.p == null) {
            }
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray a = android.support.v4.content.b.h.a(resources, theme, attributeSet, android.support.k.a.a.t);
            a(a, xmlPullParser, theme);
            a.recycle();
        }

        @Override // android.support.k.a.j.d
        public boolean a(int[] iArr) {
            return this.a.a(iArr) | this.c.a(iArr);
        }

        @Override // android.support.k.a.j.e
        public boolean b() {
            return this.p != null;
        }

        @Override // android.support.k.a.j.d
        public boolean c() {
            return this.c.d() || this.a.d();
        }

        float getFillAlpha() {
            return this.f;
        }

        @k
        int getFillColor() {
            return this.c.b();
        }

        float getStrokeAlpha() {
            return this.d;
        }

        @k
        int getStrokeColor() {
            return this.a.b();
        }

        float getStrokeWidth() {
            return this.b;
        }

        float getTrimPathEnd() {
            return this.h;
        }

        float getTrimPathOffset() {
            return this.i;
        }

        float getTrimPathStart() {
            return this.g;
        }

        void setFillAlpha(float f) {
            this.f = f;
        }

        void setFillColor(int i) {
            this.c.b(i);
        }

        void setStrokeAlpha(float f) {
            this.d = f;
        }

        void setStrokeColor(int i) {
            this.a.b(i);
        }

        void setStrokeWidth(float f) {
            this.b = f;
        }

        void setTrimPathEnd(float f) {
            this.h = f;
        }

        void setTrimPathOffset(float f) {
            this.i = f;
        }

        void setTrimPathStart(float f) {
            this.g = f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class c extends d {
        final Matrix a;
        final ArrayList<d> b;
        float c;
        final Matrix d;
        int e;
        private float f;
        private float g;
        private float h;
        private float i;
        private float j;
        private float k;
        private int[] l;
        private String m;

        public c() {
            super();
            this.a = new Matrix();
            this.b = new ArrayList<>();
            this.c = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 1.0f;
            this.i = 1.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.d = new Matrix();
            this.m = null;
        }

        public c(c cVar, android.support.v4.j.a<String, Object> aVar) {
            super();
            e aVar2;
            this.a = new Matrix();
            this.b = new ArrayList<>();
            this.c = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 1.0f;
            this.i = 1.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.d = new Matrix();
            this.m = null;
            this.c = cVar.c;
            this.f = cVar.f;
            this.g = cVar.g;
            this.h = cVar.h;
            this.i = cVar.i;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.e = cVar.e;
            String str = this.m;
            if (str != null) {
                aVar.put(str, this);
            }
            this.d.set(cVar.d);
            ArrayList<d> arrayList = cVar.b;
            for (int i = 0; i < arrayList.size(); i++) {
                d dVar = arrayList.get(i);
                if (dVar instanceof c) {
                    this.b.add(new c((c) dVar, aVar));
                } else {
                    if (dVar instanceof b) {
                        aVar2 = new b((b) dVar);
                    } else if (!(dVar instanceof a)) {
                        throw new IllegalStateException("Unknown object in the tree!");
                    } else {
                        aVar2 = new a((a) dVar);
                    }
                    this.b.add(aVar2);
                    if (aVar2.n != null) {
                        aVar.put(aVar2.n, aVar2);
                    }
                }
            }
        }

        private void a() {
            this.d.reset();
            this.d.postTranslate(-this.f, -this.g);
            this.d.postScale(this.h, this.i);
            this.d.postRotate(this.c, 0.0f, 0.0f);
            this.d.postTranslate(this.j + this.f, this.k + this.g);
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.c = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "rotation", 5, this.c);
            this.f = typedArray.getFloat(1, this.f);
            this.g = typedArray.getFloat(2, this.g);
            this.h = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "scaleX", 3, this.h);
            this.i = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "scaleY", 4, this.i);
            this.j = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "translateX", 6, this.j);
            this.k = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "translateY", 7, this.k);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            a();
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray a = android.support.v4.content.b.h.a(resources, theme, attributeSet, android.support.k.a.a.k);
            a(a, xmlPullParser);
            a.recycle();
        }

        @Override // android.support.k.a.j.d
        public boolean a(int[] iArr) {
            boolean z = false;
            for (int i = 0; i < this.b.size(); i++) {
                z |= this.b.get(i).a(iArr);
            }
            return z;
        }

        @Override // android.support.k.a.j.d
        public boolean c() {
            for (int i = 0; i < this.b.size(); i++) {
                if (this.b.get(i).c()) {
                    return true;
                }
            }
            return false;
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.d;
        }

        public float getPivotX() {
            return this.f;
        }

        public float getPivotY() {
            return this.g;
        }

        public float getRotation() {
            return this.c;
        }

        public float getScaleX() {
            return this.h;
        }

        public float getScaleY() {
            return this.i;
        }

        public float getTranslateX() {
            return this.j;
        }

        public float getTranslateY() {
            return this.k;
        }

        public void setPivotX(float f) {
            if (f != this.f) {
                this.f = f;
                a();
            }
        }

        public void setPivotY(float f) {
            if (f != this.g) {
                this.g = f;
                a();
            }
        }

        public void setRotation(float f) {
            if (f != this.c) {
                this.c = f;
                a();
            }
        }

        public void setScaleX(float f) {
            if (f != this.h) {
                this.h = f;
                a();
            }
        }

        public void setScaleY(float f) {
            if (f != this.i) {
                this.i = f;
                a();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.j) {
                this.j = f;
                a();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.k) {
                this.k = f;
                a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class d {
        private d() {
        }

        public boolean a(int[] iArr) {
            return false;
        }

        public boolean c() {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class e extends d {
        protected d.b[] m;
        String n;
        int o;

        public e() {
            super();
            this.m = null;
        }

        public e(e eVar) {
            super();
            this.m = null;
            this.n = eVar.n;
            this.o = eVar.o;
            this.m = android.support.v4.graphics.d.a(eVar.m);
        }

        public String a(d.b[] bVarArr) {
            float[] fArr;
            String str = " ";
            int i = 0;
            while (i < bVarArr.length) {
                String str2 = str + bVarArr[i].a + ":";
                for (int i2 = 0; i2 < bVarArr[i].b.length; i2++) {
                    str2 = str2 + fArr[i2] + ",";
                }
                i++;
                str = str2;
            }
            return str;
        }

        public void a(int i) {
            String str = com.lge.media.launcher.a.d;
            for (int i2 = 0; i2 < i; i2++) {
                str = str + "    ";
            }
            Log.v(j.a, str + "current path is :" + this.n + " pathData is " + a(this.m));
        }

        public void a(Resources.Theme theme) {
        }

        public void a(Path path) {
            path.reset();
            d.b[] bVarArr = this.m;
            if (bVarArr != null) {
                d.b.a(bVarArr, path);
            }
        }

        public boolean a() {
            return false;
        }

        public boolean b() {
            return false;
        }

        public d.b[] getPathData() {
            return this.m;
        }

        public String getPathName() {
            return this.n;
        }

        public void setPathData(d.b[] bVarArr) {
            if (android.support.v4.graphics.d.a(this.m, bVarArr)) {
                android.support.v4.graphics.d.b(this.m, bVarArr);
            } else {
                this.m = android.support.v4.graphics.d.a(bVarArr);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class f {
        private static final Matrix n = new Matrix();
        Paint a;
        Paint b;
        final c c;
        float d;
        float e;
        float f;
        float g;
        int h;
        String i;
        Boolean j;
        final android.support.v4.j.a<String, Object> k;
        private final Path l;
        private final Path m;
        private final Matrix o;
        private PathMeasure p;
        private int q;

        public f() {
            this.o = new Matrix();
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 255;
            this.i = null;
            this.j = null;
            this.k = new android.support.v4.j.a<>();
            this.c = new c();
            this.l = new Path();
            this.m = new Path();
        }

        public f(f fVar) {
            this.o = new Matrix();
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 255;
            this.i = null;
            this.j = null;
            this.k = new android.support.v4.j.a<>();
            this.c = new c(fVar.c, this.k);
            this.l = new Path(fVar.l);
            this.m = new Path(fVar.m);
            this.d = fVar.d;
            this.e = fVar.e;
            this.f = fVar.f;
            this.g = fVar.g;
            this.q = fVar.q;
            this.h = fVar.h;
            this.i = fVar.i;
            String str = fVar.i;
            if (str != null) {
                this.k.put(str, this);
            }
            this.j = fVar.j;
        }

        private static float a(float f, float f2, float f3, float f4) {
            return (f * f4) - (f2 * f3);
        }

        private float a(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float a = a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float max = Math.max((float) Math.hypot(fArr[0], fArr[1]), (float) Math.hypot(fArr[2], fArr[3]));
            if (max > 0.0f) {
                return Math.abs(a) / max;
            }
            return 0.0f;
        }

        private void a(c cVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            cVar.a.set(matrix);
            cVar.a.preConcat(cVar.d);
            canvas.save();
            for (int i3 = 0; i3 < cVar.b.size(); i3++) {
                d dVar = cVar.b.get(i3);
                if (dVar instanceof c) {
                    a((c) dVar, cVar.a, canvas, i, i2, colorFilter);
                } else if (dVar instanceof e) {
                    a(cVar, (e) dVar, canvas, i, i2, colorFilter);
                }
            }
            canvas.restore();
        }

        private void a(c cVar, e eVar, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            float f = i / this.f;
            float f2 = i2 / this.g;
            float min = Math.min(f, f2);
            Matrix matrix = cVar.a;
            this.o.set(matrix);
            this.o.postScale(f, f2);
            float a = a(matrix);
            if (a == 0.0f) {
                return;
            }
            eVar.a(this.l);
            Path path = this.l;
            this.m.reset();
            if (eVar.a()) {
                this.m.addPath(path, this.o);
                canvas.clipPath(this.m);
                return;
            }
            b bVar = (b) eVar;
            if (bVar.g != 0.0f || bVar.h != 1.0f) {
                float f3 = (bVar.g + bVar.i) % 1.0f;
                float f4 = (bVar.h + bVar.i) % 1.0f;
                if (this.p == null) {
                    this.p = new PathMeasure();
                }
                this.p.setPath(this.l, false);
                float length = this.p.getLength();
                float f5 = f3 * length;
                float f6 = f4 * length;
                path.reset();
                if (f5 > f6) {
                    this.p.getSegment(f5, length, path, true);
                    this.p.getSegment(0.0f, f6, path, true);
                } else {
                    this.p.getSegment(f5, f6, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.m.addPath(path, this.o);
            if (bVar.c.e()) {
                android.support.v4.content.b.b bVar2 = bVar.c;
                if (this.b == null) {
                    this.b = new Paint(1);
                    this.b.setStyle(Paint.Style.FILL);
                }
                Paint paint = this.b;
                if (bVar2.c()) {
                    Shader a2 = bVar2.a();
                    a2.setLocalMatrix(this.o);
                    paint.setShader(a2);
                    paint.setAlpha(Math.round(bVar.f * 255.0f));
                } else {
                    paint.setColor(j.a(bVar2.b(), bVar.f));
                }
                paint.setColorFilter(colorFilter);
                this.m.setFillType(bVar.e == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.m, paint);
            }
            if (bVar.a.e()) {
                android.support.v4.content.b.b bVar3 = bVar.a;
                if (this.a == null) {
                    this.a = new Paint(1);
                    this.a.setStyle(Paint.Style.STROKE);
                }
                Paint paint2 = this.a;
                if (bVar.k != null) {
                    paint2.setStrokeJoin(bVar.k);
                }
                if (bVar.j != null) {
                    paint2.setStrokeCap(bVar.j);
                }
                paint2.setStrokeMiter(bVar.l);
                if (bVar3.c()) {
                    Shader a3 = bVar3.a();
                    a3.setLocalMatrix(this.o);
                    paint2.setShader(a3);
                    paint2.setAlpha(Math.round(bVar.d * 255.0f));
                } else {
                    paint2.setColor(j.a(bVar3.b(), bVar.d));
                }
                paint2.setColorFilter(colorFilter);
                paint2.setStrokeWidth(bVar.b * min * a);
                canvas.drawPath(this.m, paint2);
            }
        }

        public void a(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            a(this.c, n, canvas, i, i2, colorFilter);
        }

        public boolean a() {
            if (this.j == null) {
                this.j = Boolean.valueOf(this.c.c());
            }
            return this.j.booleanValue();
        }

        public boolean a(int[] iArr) {
            return this.c.a(iArr);
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.h;
        }

        public void setAlpha(float f) {
            setRootAlpha((int) (f * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.h = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class g extends Drawable.ConstantState {
        int a;
        f b;
        ColorStateList c;
        PorterDuff.Mode d;
        boolean e;
        Bitmap f;
        int[] g;
        ColorStateList h;
        PorterDuff.Mode i;
        int j;
        boolean k;
        boolean l;
        Paint m;

        public g() {
            this.c = null;
            this.d = j.b;
            this.b = new f();
        }

        public g(g gVar) {
            this.c = null;
            this.d = j.b;
            if (gVar != null) {
                this.a = gVar.a;
                this.b = new f(gVar.b);
                if (gVar.b.b != null) {
                    this.b.b = new Paint(gVar.b.b);
                }
                if (gVar.b.a != null) {
                    this.b.a = new Paint(gVar.b.a);
                }
                this.c = gVar.c;
                this.d = gVar.d;
                this.e = gVar.e;
            }
        }

        public Paint a(ColorFilter colorFilter) {
            if (a() || colorFilter != null) {
                if (this.m == null) {
                    this.m = new Paint();
                    this.m.setFilterBitmap(true);
                }
                this.m.setAlpha(this.b.getRootAlpha());
                this.m.setColorFilter(colorFilter);
                return this.m;
            }
            return null;
        }

        public void a(int i, int i2) {
            this.f.eraseColor(0);
            this.b.a(new Canvas(this.f), i, i2, (ColorFilter) null);
        }

        public void a(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f, (Rect) null, rect, a(colorFilter));
        }

        public boolean a() {
            return this.b.getRootAlpha() < 255;
        }

        public boolean a(int[] iArr) {
            boolean a = this.b.a(iArr);
            this.l |= a;
            return a;
        }

        public void b(int i, int i2) {
            if (this.f == null || !c(i, i2)) {
                this.f = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.l = true;
            }
        }

        public boolean b() {
            return !this.l && this.h == this.c && this.i == this.d && this.k == this.e && this.j == this.b.getRootAlpha();
        }

        public void c() {
            this.h = this.c;
            this.i = this.d;
            this.j = this.b.getRootAlpha();
            this.k = this.e;
            this.l = false;
        }

        public boolean c(int i, int i2) {
            return i == this.f.getWidth() && i2 == this.f.getHeight();
        }

        public boolean d() {
            return this.b.a();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @af
        public Drawable newDrawable() {
            return new j(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @af
        public Drawable newDrawable(Resources resources) {
            return new j(this);
        }
    }

    @ak(a = 24)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class h extends Drawable.ConstantState {
        private final Drawable.ConstantState a;

        public h(Drawable.ConstantState constantState) {
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
            j jVar = new j();
            jVar.d = (VectorDrawable) this.a.newDrawable();
            return jVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            j jVar = new j();
            jVar.d = (VectorDrawable) this.a.newDrawable(resources);
            return jVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            j jVar = new j();
            jVar.d = (VectorDrawable) this.a.newDrawable(resources, theme);
            return jVar;
        }
    }

    j() {
        this.t = true;
        this.v = new float[9];
        this.w = new Matrix();
        this.x = new Rect();
        this.p = new g();
    }

    j(@af g gVar) {
        this.t = true;
        this.v = new float[9];
        this.w = new Matrix();
        this.x = new Rect();
        this.p = gVar;
        this.q = a(this.q, gVar.c, gVar.d);
    }

    static int a(int i2, float f2) {
        return (i2 & ab.r) | (((int) (Color.alpha(i2) * f2)) << 24);
    }

    private static PorterDuff.Mode a(int i2, PorterDuff.Mode mode) {
        if (i2 != 3) {
            if (i2 != 5) {
                if (i2 != 9) {
                    switch (i2) {
                        case 14:
                            return PorterDuff.Mode.MULTIPLY;
                        case 15:
                            return PorterDuff.Mode.SCREEN;
                        case 16:
                            return PorterDuff.Mode.ADD;
                        default:
                            return mode;
                    }
                }
                return PorterDuff.Mode.SRC_ATOP;
            }
            return PorterDuff.Mode.SRC_IN;
        }
        return PorterDuff.Mode.SRC_OVER;
    }

    @ag
    public static j a(@af Resources resources, @p int i2, @ag Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            j jVar = new j();
            jVar.d = android.support.v4.content.b.g.a(resources, i2, theme);
            jVar.u = new h(jVar.d.getConstantState());
            return jVar;
        }
        try {
            XmlResourceParser xml = resources.getXml(i2);
            AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
            while (true) {
                next = xml.next();
                if (next == 2 || next == 1) {
                    break;
                }
            }
            if (next == 2) {
                return a(resources, xml, asAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException | XmlPullParserException e2) {
            Log.e(a, "parser error", e2);
            return null;
        }
    }

    public static j a(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        j jVar = new j();
        jVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return jVar;
    }

    private void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
        g gVar = this.p;
        f fVar = gVar.b;
        gVar.d = a(android.support.v4.content.b.h.a(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateList = typedArray.getColorStateList(1);
        if (colorStateList != null) {
            gVar.c = colorStateList;
        }
        gVar.e = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "autoMirrored", 5, gVar.e);
        fVar.f = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "viewportWidth", 7, fVar.f);
        fVar.g = android.support.v4.content.b.h.a(typedArray, xmlPullParser, "viewportHeight", 8, fVar.g);
        if (fVar.f <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        } else if (fVar.g <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        } else {
            fVar.d = typedArray.getDimension(3, fVar.d);
            fVar.e = typedArray.getDimension(2, fVar.e);
            if (fVar.d <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
            } else if (fVar.e <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
            } else {
                fVar.setAlpha(android.support.v4.content.b.h.a(typedArray, xmlPullParser, "alpha", 4, fVar.getAlpha()));
                String string = typedArray.getString(0);
                if (string != null) {
                    fVar.i = string;
                    fVar.k.put(string, fVar);
                }
            }
        }
    }

    private void a(c cVar, int i2) {
        String str = com.lge.media.launcher.a.d;
        for (int i3 = 0; i3 < i2; i3++) {
            str = str + "    ";
        }
        Log.v(a, str + "current group is :" + cVar.getGroupName() + " rotation is " + cVar.c);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("matrix is :");
        sb.append(cVar.getLocalMatrix().toString());
        Log.v(a, sb.toString());
        for (int i4 = 0; i4 < cVar.b.size(); i4++) {
            d dVar = cVar.b.get(i4);
            if (dVar instanceof c) {
                a((c) dVar, i2 + 1);
            } else {
                ((e) dVar).a(i2 + 1);
            }
        }
    }

    private void b(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int i2;
        int i3;
        g gVar = this.p;
        f fVar = gVar.b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(fVar.c);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                c cVar = (c) arrayDeque.peek();
                if (f.equals(name)) {
                    b bVar = new b();
                    bVar.a(resources, attributeSet, theme, xmlPullParser);
                    cVar.b.add(bVar);
                    if (bVar.getPathName() != null) {
                        fVar.k.put(bVar.getPathName(), bVar);
                    }
                    z = false;
                    i2 = gVar.a;
                    i3 = bVar.o;
                } else if (c.equals(name)) {
                    a aVar = new a();
                    aVar.a(resources, attributeSet, theme, xmlPullParser);
                    cVar.b.add(aVar);
                    if (aVar.getPathName() != null) {
                        fVar.k.put(aVar.getPathName(), aVar);
                    }
                    i2 = gVar.a;
                    i3 = aVar.o;
                } else if (e.equals(name)) {
                    c cVar2 = new c();
                    cVar2.a(resources, attributeSet, theme, xmlPullParser);
                    cVar.b.add(cVar2);
                    arrayDeque.push(cVar2);
                    if (cVar2.getGroupName() != null) {
                        fVar.k.put(cVar2.getGroupName(), cVar2);
                    }
                    i2 = gVar.a;
                    i3 = cVar2.e;
                }
                gVar.a = i3 | i2;
            } else if (eventType == 3 && e.equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    private boolean b() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && android.support.v4.graphics.drawable.a.i(this) == 1;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public float a() {
        g gVar = this.p;
        if (gVar == null || gVar.b == null || this.p.b.d == 0.0f || this.p.b.e == 0.0f || this.p.b.g == 0.0f || this.p.b.f == 0.0f) {
            return 1.0f;
        }
        float f2 = this.p.b.d;
        float f3 = this.p.b.e;
        return Math.min(this.p.b.f / f2, this.p.b.g / f3);
    }

    PorterDuffColorFilter a(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object a(String str) {
        return this.p.b.k.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.t = z;
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.d(this.d);
            return false;
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
        copyBounds(this.x);
        if (this.x.width() <= 0 || this.x.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.r;
        if (colorFilter == null) {
            colorFilter = this.q;
        }
        canvas.getMatrix(this.w);
        this.w.getValues(this.v);
        float abs = Math.abs(this.v[0]);
        float abs2 = Math.abs(this.v[4]);
        float abs3 = Math.abs(this.v[1]);
        float abs4 = Math.abs(this.v[3]);
        if (abs3 != 0.0f || abs4 != 0.0f) {
            abs = 1.0f;
            abs2 = 1.0f;
        }
        int min = Math.min(2048, (int) (this.x.width() * abs));
        int min2 = Math.min(2048, (int) (this.x.height() * abs2));
        if (min <= 0 || min2 <= 0) {
            return;
        }
        int save = canvas.save();
        canvas.translate(this.x.left, this.x.top);
        if (b()) {
            canvas.translate(this.x.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.x.offsetTo(0, 0);
        this.p.b(min, min2);
        if (!this.t) {
            this.p.a(min, min2);
        } else if (!this.p.b()) {
            this.p.a(min, min2);
            this.p.c();
        }
        this.p.a(canvas, colorFilter, this.x);
        canvas.restoreToCount(save);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.d != null ? android.support.v4.graphics.drawable.a.c(this.d) : this.p.b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return this.d != null ? this.d.getChangingConfigurations() : super.getChangingConfigurations() | this.p.getChangingConfigurations();
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.d == null || Build.VERSION.SDK_INT < 24) {
            this.p.a = getChangingConfigurations();
            return this.p;
        }
        return new h(this.d.getConstantState());
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.d != null ? this.d.getIntrinsicHeight() : (int) this.p.b.e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.d != null ? this.d.getIntrinsicWidth() : (int) this.p.b.d;
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
        if (this.d != null) {
            return this.d.getOpacity();
        }
        return -3;
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
        if (this.d != null) {
            this.d.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.a(this.d, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        g gVar = this.p;
        gVar.b = new f();
        TypedArray a2 = android.support.v4.content.b.h.a(resources, theme, attributeSet, android.support.k.a.a.a);
        a(a2, xmlPullParser);
        a2.recycle();
        gVar.a = getChangingConfigurations();
        gVar.l = true;
        b(resources, xmlPullParser, attributeSet, theme);
        this.q = a(this.q, gVar.c, gVar.d);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if (this.d != null) {
            this.d.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.d != null ? android.support.v4.graphics.drawable.a.b(this.d) : this.p.e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        g gVar;
        return this.d != null ? this.d.isStateful() : super.isStateful() || ((gVar = this.p) != null && (gVar.d() || (this.p.c != null && this.p.c.isStateful())));
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (this.d != null) {
            this.d.mutate();
            return this;
        }
        if (!this.s && super.mutate() == this) {
            this.p = new g(this.p);
            this.s = true;
        }
        return this;
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        if (this.d != null) {
            this.d.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        if (this.d != null) {
            return this.d.setState(iArr);
        }
        boolean z = false;
        g gVar = this.p;
        if (gVar.c != null && gVar.d != null) {
            this.q = a(this.q, gVar.c, gVar.d);
            invalidateSelf();
            z = true;
        }
        if (gVar.d() && gVar.a(iArr)) {
            invalidateSelf();
            return true;
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j2) {
        if (this.d != null) {
            this.d.scheduleSelf(runnable, j2);
        } else {
            super.scheduleSelf(runnable, j2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.d != null) {
            this.d.setAlpha(i2);
        } else if (this.p.b.getRootAlpha() != i2) {
            this.p.b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.a(this.d, z);
        } else {
            this.p.e = z;
        }
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.d != null) {
            this.d.setColorFilter(colorFilter);
            return;
        }
        this.r = colorFilter;
        invalidateSelf();
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
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // android.support.k.a.i, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTint(int i2) {
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.a(this.d, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTintList(ColorStateList colorStateList) {
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.a(this.d, colorStateList);
            return;
        }
        g gVar = this.p;
        if (gVar.c != colorStateList) {
            gVar.c = colorStateList;
            this.q = a(this.q, colorStateList, gVar.d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, android.support.v4.graphics.drawable.e
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.d != null) {
            android.support.v4.graphics.drawable.a.a(this.d, mode);
            return;
        }
        g gVar = this.p;
        if (gVar.d != mode) {
            gVar.d = mode;
            this.q = a(this.q, gVar.c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return this.d != null ? this.d.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        if (this.d != null) {
            this.d.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }
}
