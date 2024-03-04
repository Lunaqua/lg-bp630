package android.support.v4.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.support.annotation.av;
import android.support.annotation.k;
import android.support.annotation.p;
import android.support.annotation.v;
import android.support.v4.j.q;
import android.support.v4.view.ab;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class IconCompat extends CustomVersionedParcelable {
    public static final int a = -1;
    static final PorterDuff.Mode i = PorterDuff.Mode.SRC_IN;
    private static final String l = "IconCompat";
    private static final float m = 0.25f;
    private static final float n = 0.6666667f;
    private static final float o = 0.9166667f;
    private static final float p = 0.010416667f;
    private static final float q = 0.020833334f;
    private static final int r = 61;
    private static final int s = 30;
    private static final String t = "type";
    private static final String u = "obj";
    private static final String v = "int1";
    private static final String w = "int2";
    private static final String x = "tint_list";
    private static final String y = "tint_mode";
    @an(a = {an.a.LIBRARY})
    public int b;
    Object c;
    @an(a = {an.a.LIBRARY})
    public byte[] d;
    @an(a = {an.a.LIBRARY})
    public Parcelable e;
    @an(a = {an.a.LIBRARY})
    public int f;
    @an(a = {an.a.LIBRARY})
    public int g;
    @an(a = {an.a.LIBRARY})
    public ColorStateList h = null;
    PorterDuff.Mode j = i;
    @an(a = {an.a.LIBRARY})
    public String k;

    @an(a = {an.a.LIBRARY})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
    }

    @an(a = {an.a.LIBRARY})
    public IconCompat() {
    }

    private IconCompat(int i2) {
        this.b = i2;
    }

    private static Resources a(Context context, String str) {
        if ("android".equals(str)) {
            return Resources.getSystem();
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 8192);
            if (applicationInfo != null) {
                return packageManager.getResourcesForApplication(applicationInfo);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(l, String.format("Unable to find pkg=%s for icon", str), e);
            return null;
        }
    }

    @av
    static Bitmap a(Bitmap bitmap, boolean z) {
        int min = (int) (Math.min(bitmap.getWidth(), bitmap.getHeight()) * n);
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(3);
        float f = min;
        float f2 = 0.5f * f;
        float f3 = o * f2;
        if (z) {
            float f4 = p * f;
            paint.setColor(0);
            paint.setShadowLayer(f4, 0.0f, f * q, 1023410176);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.setShadowLayer(f4, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(ab.s);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - min)) / 2, (-(bitmap.getHeight() - min)) / 2);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f2, f2, f3, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }

    public static IconCompat a(Context context, @p int i2) {
        if (context != null) {
            return a(context.getResources(), context.getPackageName(), i2);
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    @ag
    @ak(a = 23)
    public static IconCompat a(@af Context context, @af Icon icon) {
        q.a(icon);
        int b = b(icon);
        if (b == 2) {
            String c = c(icon);
            try {
                return a(a(context, c), c, d(icon));
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        } else if (b != 4) {
            IconCompat iconCompat = new IconCompat(-1);
            iconCompat.c = icon;
            return iconCompat;
        } else {
            return a(e(icon));
        }
    }

    @an(a = {an.a.LIBRARY})
    public static IconCompat a(Resources resources, String str, @p int i2) {
        if (str != null) {
            if (i2 != 0) {
                IconCompat iconCompat = new IconCompat(2);
                iconCompat.f = i2;
                if (resources != null) {
                    try {
                        iconCompat.c = resources.getResourceName(i2);
                    } catch (Resources.NotFoundException unused) {
                        throw new IllegalArgumentException("Icon resource cannot be found");
                    }
                } else {
                    iconCompat.c = str;
                }
                return iconCompat;
            }
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        throw new IllegalArgumentException("Package must not be null.");
    }

    public static IconCompat a(Bitmap bitmap) {
        if (bitmap != null) {
            IconCompat iconCompat = new IconCompat(1);
            iconCompat.c = bitmap;
            return iconCompat;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    @ag
    @ak(a = 23)
    @an(a = {an.a.LIBRARY_GROUP})
    public static IconCompat a(@af Icon icon) {
        q.a(icon);
        int b = b(icon);
        if (b != 2) {
            if (b != 4) {
                IconCompat iconCompat = new IconCompat(-1);
                iconCompat.c = icon;
                return iconCompat;
            }
            return a(e(icon));
        }
        return a((Resources) null, c(icon), d(icon));
    }

    public static IconCompat a(Uri uri) {
        if (uri != null) {
            return a(uri.toString());
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    @ag
    public static IconCompat a(@af Bundle bundle) {
        Object parcelable;
        int i2 = bundle.getInt(t);
        IconCompat iconCompat = new IconCompat(i2);
        iconCompat.f = bundle.getInt(v);
        iconCompat.g = bundle.getInt(w);
        if (bundle.containsKey(x)) {
            iconCompat.h = (ColorStateList) bundle.getParcelable(x);
        }
        if (bundle.containsKey(y)) {
            iconCompat.j = PorterDuff.Mode.valueOf(bundle.getString(y));
        }
        if (i2 != -1 && i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    iconCompat.c = bundle.getByteArray(u);
                    return iconCompat;
                } else if (i2 != 4) {
                    if (i2 != 5) {
                        Log.w(l, "Unknown type " + i2);
                        return null;
                    }
                }
            }
            parcelable = bundle.getString(u);
            iconCompat.c = parcelable;
            return iconCompat;
        }
        parcelable = bundle.getParcelable(u);
        iconCompat.c = parcelable;
        return iconCompat;
    }

    public static IconCompat a(String str) {
        if (str != null) {
            IconCompat iconCompat = new IconCompat(4);
            iconCompat.c = str;
            return iconCompat;
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    public static IconCompat a(byte[] bArr, int i2, int i3) {
        if (bArr != null) {
            IconCompat iconCompat = new IconCompat(3);
            iconCompat.c = bArr;
            iconCompat.f = i2;
            iconCompat.g = i3;
            return iconCompat;
        }
        throw new IllegalArgumentException("Data must not be null.");
    }

    @ak(a = 23)
    private static int b(@af Icon icon) {
        StringBuilder sb;
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getType();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            e = e;
            sb = new StringBuilder();
            sb.append("Unable to get icon type ");
            sb.append(icon);
            Log.e(l, sb.toString(), e);
            return -1;
        } catch (NoSuchMethodException e2) {
            e = e2;
            sb = new StringBuilder();
            sb.append("Unable to get icon type ");
            sb.append(icon);
            Log.e(l, sb.toString(), e);
            return -1;
        } catch (InvocationTargetException e3) {
            e = e3;
            sb = new StringBuilder();
            sb.append("Unable to get icon type ");
            sb.append(icon);
            Log.e(l, sb.toString(), e);
            return -1;
        }
    }

    public static IconCompat b(Bitmap bitmap) {
        if (bitmap != null) {
            IconCompat iconCompat = new IconCompat(5);
            iconCompat.c = bitmap;
            return iconCompat;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    private static String b(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "UNKNOWN" : "BITMAP_MASKABLE" : "URI" : "DATA" : "RESOURCE" : "BITMAP";
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.graphics.drawable.Drawable c(android.content.Context r8) {
        /*
            r7 = this;
            int r0 = r7.b
            r1 = 1
            if (r0 == r1) goto Ldc
            r2 = 0
            r3 = 0
            r4 = 2
            java.lang.String r5 = "IconCompat"
            if (r0 == r4) goto La6
            r1 = 3
            if (r0 == r1) goto L8e
            r1 = 4
            if (r0 == r1) goto L29
            r1 = 5
            if (r0 == r1) goto L17
            goto Ldb
        L17:
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r8 = r8.getResources()
            java.lang.Object r1 = r7.c
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            android.graphics.Bitmap r1 = a(r1, r2)
            r0.<init>(r8, r1)
            return r0
        L29:
            java.lang.Object r0 = r7.c
            java.lang.String r0 = (java.lang.String) r0
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.String r1 = r0.getScheme()
            java.lang.String r2 = "content"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L5f
            java.lang.String r2 = "file"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L46
            goto L5f
        L46:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.io.FileNotFoundException -> L56
            java.io.File r2 = new java.io.File     // Catch: java.io.FileNotFoundException -> L56
            java.lang.Object r4 = r7.c     // Catch: java.io.FileNotFoundException -> L56
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.io.FileNotFoundException -> L56
            r2.<init>(r4)     // Catch: java.io.FileNotFoundException -> L56
            r1.<init>(r2)     // Catch: java.io.FileNotFoundException -> L56
            r0 = r1
            goto L7e
        L56:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Unable to load image from path: "
            goto L70
        L5f:
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Exception -> L68
            java.io.InputStream r0 = r1.openInputStream(r0)     // Catch: java.lang.Exception -> L68
            goto L7e
        L68:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Unable to load image from URI: "
        L70:
            r2.append(r4)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.w(r5, r0, r1)
            r0 = r3
        L7e:
            if (r0 == 0) goto Ldb
            android.graphics.drawable.BitmapDrawable r1 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r8 = r8.getResources()
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r0)
            r1.<init>(r8, r0)
            return r1
        L8e:
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r8 = r8.getResources()
            java.lang.Object r1 = r7.c
            byte[] r1 = (byte[]) r1
            byte[] r1 = (byte[]) r1
            int r2 = r7.f
            int r3 = r7.g
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeByteArray(r1, r2, r3)
            r0.<init>(r8, r1)
            return r0
        La6:
            java.lang.String r0 = r7.b()
            boolean r6 = android.text.TextUtils.isEmpty(r0)
            if (r6 == 0) goto Lb4
            java.lang.String r0 = r8.getPackageName()
        Lb4:
            android.content.res.Resources r0 = a(r8, r0)
            int r6 = r7.f     // Catch: java.lang.RuntimeException -> Lc3
            android.content.res.Resources$Theme r8 = r8.getTheme()     // Catch: java.lang.RuntimeException -> Lc3
            android.graphics.drawable.Drawable r8 = android.support.v4.content.b.g.a(r0, r6, r8)     // Catch: java.lang.RuntimeException -> Lc3
            return r8
        Lc3:
            r8 = move-exception
            java.lang.Object[] r0 = new java.lang.Object[r4]
            int r4 = r7.f
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r0[r2] = r4
            java.lang.Object r2 = r7.c
            r0[r1] = r2
            java.lang.String r1 = "Unable to load resource 0x%08x from pkg=%s"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            android.util.Log.e(r5, r0, r8)
        Ldb:
            return r3
        Ldc:
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r8 = r8.getResources()
            java.lang.Object r1 = r7.c
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            r0.<init>(r8, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.drawable.IconCompat.c(android.content.Context):android.graphics.drawable.Drawable");
    }

    @ag
    @ak(a = 23)
    private static String c(@af Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e(l, "Unable to get icon package", e);
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e(l, "Unable to get icon package", e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.e(l, "Unable to get icon package", e3);
            return null;
        }
    }

    @ak(a = 23)
    @p
    @v
    private static int d(@af Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.e(l, "Unable to get icon resource", e);
            return 0;
        } catch (NoSuchMethodException e2) {
            Log.e(l, "Unable to get icon resource", e2);
            return 0;
        } catch (InvocationTargetException e3) {
            Log.e(l, "Unable to get icon resource", e3);
            return 0;
        }
    }

    @ag
    @ak(a = 23)
    private static Uri e(@af Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getUri();
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e(l, "Unable to get icon uri", e);
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e(l, "Unable to get icon uri", e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.e(l, "Unable to get icon uri", e3);
            return null;
        }
    }

    public int a() {
        return (this.b != -1 || Build.VERSION.SDK_INT < 23) ? this.b : b((Icon) this.c);
    }

    public IconCompat a(@k int i2) {
        return a(ColorStateList.valueOf(i2));
    }

    public IconCompat a(ColorStateList colorStateList) {
        this.h = colorStateList;
        return this;
    }

    public IconCompat a(PorterDuff.Mode mode) {
        this.j = mode;
        return this;
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public void a(Context context) {
        if (this.b == 2) {
            String str = (String) this.c;
            if (str.contains(":")) {
                String str2 = str.split(":", -1)[1];
                String str3 = str2.split("/", -1)[0];
                String str4 = str2.split("/", -1)[1];
                String str5 = str.split(":", -1)[0];
                int identifier = a(context, str5).getIdentifier(str4, str3, str5);
                if (this.f != identifier) {
                    Log.i(l, "Id has changed for " + str5 + "/" + str4);
                    this.f = identifier;
                }
            }
        }
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public void a(@af Intent intent, @ag Drawable drawable, @af Context context) {
        Bitmap bitmap;
        a(context);
        int i2 = this.b;
        if (i2 == 1) {
            bitmap = (Bitmap) this.c;
            if (drawable != null) {
                bitmap = bitmap.copy(bitmap.getConfig(), true);
            }
        } else if (i2 == 2) {
            try {
                Context createPackageContext = context.createPackageContext(b(), 0);
                if (drawable == null) {
                    intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(createPackageContext, this.f));
                    return;
                }
                Drawable drawable2 = android.support.v4.content.c.getDrawable(createPackageContext, this.f);
                if (drawable2.getIntrinsicWidth() > 0 && drawable2.getIntrinsicHeight() > 0) {
                    bitmap = Bitmap.createBitmap(drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    drawable2.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    drawable2.draw(new Canvas(bitmap));
                }
                int launcherLargeIconSize = ((ActivityManager) createPackageContext.getSystemService("activity")).getLauncherLargeIconSize();
                bitmap = Bitmap.createBitmap(launcherLargeIconSize, launcherLargeIconSize, Bitmap.Config.ARGB_8888);
                drawable2.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                drawable2.draw(new Canvas(bitmap));
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalArgumentException("Can't find package " + this.c, e);
            }
        } else if (i2 != 5) {
            throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
        } else {
            bitmap = a((Bitmap) this.c, true);
        }
        if (drawable != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            drawable.setBounds(width / 2, height / 2, width, height);
            drawable.draw(new Canvas(bitmap));
        }
        intent.putExtra("android.intent.extra.shortcut.ICON", bitmap);
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void a(boolean z) {
        this.k = this.j.name();
        int i2 = this.b;
        if (i2 != -1) {
            if (i2 != 1) {
                if (i2 == 2) {
                    this.d = ((String) this.c).getBytes(Charset.forName("UTF-16"));
                    return;
                } else if (i2 == 3) {
                    this.d = (byte[]) this.c;
                    return;
                } else if (i2 == 4) {
                    this.d = this.c.toString().getBytes(Charset.forName("UTF-16"));
                    return;
                } else if (i2 != 5) {
                    return;
                }
            }
            if (z) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ((Bitmap) this.c).compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.d = byteArrayOutputStream.toByteArray();
                return;
            }
        } else if (z) {
            throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
        }
        this.e = (Parcelable) this.c;
    }

    public Drawable b(Context context) {
        a(context);
        if (Build.VERSION.SDK_INT >= 23) {
            return e().loadDrawable(context);
        }
        Drawable c = c(context);
        if (c != null && (this.h != null || this.j != i)) {
            c.mutate();
            android.support.v4.graphics.drawable.a.a(c, this.h);
            android.support.v4.graphics.drawable.a.a(c, this.j);
        }
        return c;
    }

    @af
    public String b() {
        if (this.b != -1 || Build.VERSION.SDK_INT < 23) {
            if (this.b == 2) {
                return ((String) this.c).split(":", -1)[0];
            }
            throw new IllegalStateException("called getResPackage() on " + this);
        }
        return c((Icon) this.c);
    }

    @v
    public int c() {
        if (this.b != -1 || Build.VERSION.SDK_INT < 23) {
            if (this.b == 2) {
                return this.f;
            }
            throw new IllegalStateException("called getResId() on " + this);
        }
        return d((Icon) this.c);
    }

    @af
    public Uri d() {
        return (this.b != -1 || Build.VERSION.SDK_INT < 23) ? Uri.parse((String) this.c) : e((Icon) this.c);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006d  */
    @android.support.annotation.ak(a = 23)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.drawable.Icon e() {
        /*
            r3 = this;
            int r0 = r3.b
            r1 = -1
            if (r0 == r1) goto L71
            r1 = 1
            if (r0 == r1) goto L58
            r1 = 2
            if (r0 == r1) goto L4d
            r1 = 3
            if (r0 == r1) goto L3e
            r1 = 4
            if (r0 == r1) goto L35
            r1 = 5
            if (r0 != r1) goto L2d
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r0 < r1) goto L23
            java.lang.Object r0 = r3.c
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            android.graphics.drawable.Icon r0 = android.graphics.drawable.Icon.createWithAdaptiveBitmap(r0)
            goto L60
        L23:
            java.lang.Object r0 = r3.c
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            r1 = 0
            android.graphics.Bitmap r0 = a(r0, r1)
            goto L5c
        L2d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unknown type"
            r0.<init>(r1)
            throw r0
        L35:
            java.lang.Object r0 = r3.c
            java.lang.String r0 = (java.lang.String) r0
            android.graphics.drawable.Icon r0 = android.graphics.drawable.Icon.createWithContentUri(r0)
            goto L60
        L3e:
            java.lang.Object r0 = r3.c
            byte[] r0 = (byte[]) r0
            byte[] r0 = (byte[]) r0
            int r1 = r3.f
            int r2 = r3.g
            android.graphics.drawable.Icon r0 = android.graphics.drawable.Icon.createWithData(r0, r1, r2)
            goto L60
        L4d:
            java.lang.String r0 = r3.b()
            int r1 = r3.f
            android.graphics.drawable.Icon r0 = android.graphics.drawable.Icon.createWithResource(r0, r1)
            goto L60
        L58:
            java.lang.Object r0 = r3.c
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
        L5c:
            android.graphics.drawable.Icon r0 = android.graphics.drawable.Icon.createWithBitmap(r0)
        L60:
            android.content.res.ColorStateList r1 = r3.h
            if (r1 == 0) goto L67
            r0.setTintList(r1)
        L67:
            android.graphics.PorterDuff$Mode r1 = r3.j
            android.graphics.PorterDuff$Mode r2 = android.support.v4.graphics.drawable.IconCompat.i
            if (r1 == r2) goto L70
            r0.setTintMode(r1)
        L70:
            return r0
        L71:
            java.lang.Object r0 = r3.c
            android.graphics.drawable.Icon r0 = (android.graphics.drawable.Icon) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.drawable.IconCompat.e():android.graphics.drawable.Icon");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.os.Bundle f() {
        /*
            r4 = this;
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            int r1 = r4.b
            r2 = -1
            java.lang.String r3 = "obj"
            if (r1 == r2) goto L3b
            r2 = 1
            if (r1 == r2) goto L36
            r2 = 2
            if (r1 == r2) goto L2e
            r2 = 3
            if (r1 == r2) goto L24
            r2 = 4
            if (r1 == r2) goto L2e
            r2 = 5
            if (r1 != r2) goto L1c
            goto L36
        L1c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Invalid icon"
            r0.<init>(r1)
            throw r0
        L24:
            java.lang.Object r1 = r4.c
            byte[] r1 = (byte[]) r1
            byte[] r1 = (byte[]) r1
            r0.putByteArray(r3, r1)
            goto L42
        L2e:
            java.lang.Object r1 = r4.c
            java.lang.String r1 = (java.lang.String) r1
            r0.putString(r3, r1)
            goto L42
        L36:
            java.lang.Object r1 = r4.c
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            goto L3f
        L3b:
            java.lang.Object r1 = r4.c
            android.os.Parcelable r1 = (android.os.Parcelable) r1
        L3f:
            r0.putParcelable(r3, r1)
        L42:
            int r1 = r4.b
            java.lang.String r2 = "type"
            r0.putInt(r2, r1)
            int r1 = r4.f
            java.lang.String r2 = "int1"
            r0.putInt(r2, r1)
            int r1 = r4.g
            java.lang.String r2 = "int2"
            r0.putInt(r2, r1)
            android.content.res.ColorStateList r1 = r4.h
            if (r1 == 0) goto L60
            java.lang.String r2 = "tint_list"
            r0.putParcelable(r2, r1)
        L60:
            android.graphics.PorterDuff$Mode r1 = r4.j
            android.graphics.PorterDuff$Mode r2 = android.support.v4.graphics.drawable.IconCompat.i
            if (r1 == r2) goto L6f
            java.lang.String r1 = r1.name()
            java.lang.String r2 = "tint_mode"
            r0.putString(r2, r1)
        L6f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.drawable.IconCompat.f():android.os.Bundle");
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void g() {
        Parcelable parcelable;
        this.j = PorterDuff.Mode.valueOf(this.k);
        int i2 = this.b;
        if (i2 != -1) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        this.c = this.d;
                        return;
                    } else if (i2 != 4) {
                        if (i2 != 5) {
                            return;
                        }
                    }
                }
                this.c = new String(this.d, Charset.forName("UTF-16"));
                return;
            }
            parcelable = this.e;
            if (parcelable == null) {
                byte[] bArr = this.d;
                this.c = bArr;
                this.b = 3;
                this.f = 0;
                this.g = bArr.length;
                return;
            }
        } else {
            parcelable = this.e;
            if (parcelable == null) {
                throw new IllegalArgumentException("Invalid icon");
            }
        }
        this.c = parcelable;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r1 != 5) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r4 = this;
            int r0 = r4.b
            r1 = -1
            if (r0 != r1) goto Lc
            java.lang.Object r0 = r4.c
            java.lang.String r0 = java.lang.String.valueOf(r0)
            return r0
        Lc:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Icon(typ="
            r0.<init>(r1)
            int r1 = r4.b
            java.lang.String r1 = b(r1)
            r0.append(r1)
            int r1 = r4.b
            r2 = 1
            if (r1 == r2) goto L77
            r3 = 2
            if (r1 == r3) goto L4f
            r2 = 3
            if (r1 == r2) goto L39
            r2 = 4
            if (r1 == r2) goto L2e
            r2 = 5
            if (r1 == r2) goto L77
            goto L97
        L2e:
            java.lang.String r1 = " uri="
            r0.append(r1)
            java.lang.Object r1 = r4.c
            r0.append(r1)
            goto L97
        L39:
            java.lang.String r1 = " len="
            r0.append(r1)
            int r1 = r4.f
            r0.append(r1)
            int r1 = r4.g
            if (r1 == 0) goto L97
            java.lang.String r1 = " off="
            r0.append(r1)
            int r1 = r4.g
            goto L94
        L4f:
            java.lang.String r1 = " pkg="
            r0.append(r1)
            java.lang.String r1 = r4.b()
            r0.append(r1)
            java.lang.String r1 = " id="
            r0.append(r1)
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r2 = 0
            int r3 = r4.c()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            java.lang.String r2 = "0x%08x"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r0.append(r1)
            goto L97
        L77:
            java.lang.String r1 = " size="
            r0.append(r1)
            java.lang.Object r1 = r4.c
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getWidth()
            r0.append(r1)
            java.lang.String r1 = "x"
            r0.append(r1)
            java.lang.Object r1 = r4.c
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getHeight()
        L94:
            r0.append(r1)
        L97:
            android.content.res.ColorStateList r1 = r4.h
            if (r1 == 0) goto La5
            java.lang.String r1 = " tint="
            r0.append(r1)
            android.content.res.ColorStateList r1 = r4.h
            r0.append(r1)
        La5:
            android.graphics.PorterDuff$Mode r1 = r4.j
            android.graphics.PorterDuff$Mode r2 = android.support.v4.graphics.drawable.IconCompat.i
            if (r1 == r2) goto Lb5
            java.lang.String r1 = " mode="
            r0.append(r1)
            android.graphics.PorterDuff$Mode r1 = r4.j
            r0.append(r1)
        Lb5:
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.drawable.IconCompat.toString():java.lang.String");
    }
}
