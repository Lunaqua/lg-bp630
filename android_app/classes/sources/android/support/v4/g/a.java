package android.support.v4.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    static final boolean a;
    static final boolean b;
    public static final int c = 1;
    public static final int d = 2;
    @SuppressLint({"InlinedApi"})
    public static final int e = 1;
    @SuppressLint({"InlinedApi"})
    public static final int f = 2;
    public static final int g = 1;
    public static final int h = 2;
    private static final String o = "PrintHelper";
    private static final int p = 3500;
    final Context i;
    BitmapFactory.Options j = null;
    final Object k = new Object();
    int l = 2;
    int m = 2;
    int n = 1;

    /* renamed from: android.support.v4.g.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface InterfaceC0020a {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ak(a = 19)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class b extends PrintDocumentAdapter {
        private final String b;
        private final int c;
        private final Bitmap d;
        private final InterfaceC0020a e;
        private PrintAttributes f;

        b(String str, int i, Bitmap bitmap, InterfaceC0020a interfaceC0020a) {
            this.b = str;
            this.c = i;
            this.d = bitmap;
            this.e = interfaceC0020a;
        }

        @Override // android.print.PrintDocumentAdapter
        public void onFinish() {
            InterfaceC0020a interfaceC0020a = this.e;
            if (interfaceC0020a != null) {
                interfaceC0020a.a();
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            this.f = printAttributes2;
            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.b).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
        }

        @Override // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            a.this.a(this.f, this.c, this.d, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ak(a = 19)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class c extends PrintDocumentAdapter {
        final String a;
        final Uri b;
        final InterfaceC0020a c;
        final int d;
        PrintAttributes e;
        AsyncTask<Uri, Boolean, Bitmap> f;
        Bitmap g = null;

        c(String str, Uri uri, InterfaceC0020a interfaceC0020a, int i) {
            this.a = str;
            this.b = uri;
            this.c = interfaceC0020a;
            this.d = i;
        }

        void a() {
            synchronized (a.this.k) {
                if (a.this.j != null) {
                    if (Build.VERSION.SDK_INT < 24) {
                        a.this.j.requestCancelDecode();
                    }
                    a.this.j = null;
                }
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onFinish() {
            super.onFinish();
            a();
            AsyncTask<Uri, Boolean, Bitmap> asyncTask = this.f;
            if (asyncTask != null) {
                asyncTask.cancel(true);
            }
            InterfaceC0020a interfaceC0020a = this.c;
            if (interfaceC0020a != null) {
                interfaceC0020a.a();
            }
            Bitmap bitmap = this.g;
            if (bitmap != null) {
                bitmap.recycle();
                this.g = null;
            }
        }

        /* JADX WARN: Type inference failed for: r11v3, types: [android.support.v4.g.a$c$1] */
        @Override // android.print.PrintDocumentAdapter
        public void onLayout(final PrintAttributes printAttributes, final PrintAttributes printAttributes2, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            synchronized (this) {
                this.e = printAttributes2;
            }
            if (cancellationSignal.isCanceled()) {
                layoutResultCallback.onLayoutCancelled();
            } else if (this.g != null) {
                layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.a).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
            } else {
                this.f = new AsyncTask<Uri, Boolean, Bitmap>() { // from class: android.support.v4.g.a.c.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    /* renamed from: a */
                    public Bitmap doInBackground(Uri... uriArr) {
                        try {
                            return a.this.a(c.this.b);
                        } catch (FileNotFoundException unused) {
                            return null;
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    /* renamed from: a */
                    public void onPostExecute(Bitmap bitmap) {
                        PrintAttributes.MediaSize mediaSize;
                        super.onPostExecute(bitmap);
                        if (bitmap != null && (!a.a || a.this.n == 0)) {
                            synchronized (this) {
                                mediaSize = c.this.e.getMediaSize();
                            }
                            if (mediaSize != null && mediaSize.isPortrait() != a.a(bitmap)) {
                                Matrix matrix = new Matrix();
                                matrix.postRotate(90.0f);
                                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                            }
                        }
                        c cVar = c.this;
                        cVar.g = bitmap;
                        if (bitmap != null) {
                            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(cVar.a).setContentType(1).setPageCount(1).build(), true ^ printAttributes2.equals(printAttributes));
                        } else {
                            layoutResultCallback.onLayoutFailed(null);
                        }
                        c.this.f = null;
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    /* renamed from: b */
                    public void onCancelled(Bitmap bitmap) {
                        layoutResultCallback.onLayoutCancelled();
                        c.this.f = null;
                    }

                    @Override // android.os.AsyncTask
                    protected void onPreExecute() {
                        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.support.v4.g.a.c.1.1
                            @Override // android.os.CancellationSignal.OnCancelListener
                            public void onCancel() {
                                c.this.a();
                                cancel(false);
                            }
                        });
                    }
                }.execute(new Uri[0]);
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            a.this.a(this.e, this.d, this.g, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    static {
        a = Build.VERSION.SDK_INT < 20 || Build.VERSION.SDK_INT > 23;
        b = Build.VERSION.SDK_INT != 23;
    }

    public a(@af Context context) {
        this.i = context;
    }

    static Bitmap a(Bitmap bitmap, int i) {
        if (i != 1) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }

    private Bitmap a(Uri uri, BitmapFactory.Options options) {
        Context context;
        if (uri == null || (context = this.i) == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        InputStream inputStream = null;
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream, null, options);
                if (openInputStream != null) {
                    try {
                        openInputStream.close();
                    } catch (IOException e2) {
                        Log.w(o, "close fail ", e2);
                    }
                }
                return decodeStream;
            } catch (Throwable th) {
                th = th;
                inputStream = openInputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        Log.w(o, "close fail ", e3);
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    static Matrix a(int i, int i2, RectF rectF, int i3) {
        Matrix matrix = new Matrix();
        float f2 = i;
        float width = rectF.width() / f2;
        float max = i3 == 2 ? Math.max(width, rectF.height() / i2) : Math.min(width, rectF.height() / i2);
        matrix.postScale(max, max);
        matrix.postTranslate((rectF.width() - (f2 * max)) / 2.0f, (rectF.height() - (i2 * max)) / 2.0f);
        return matrix;
    }

    @ak(a = 19)
    private static PrintAttributes.Builder a(PrintAttributes printAttributes) {
        PrintAttributes.Builder minMargins = new PrintAttributes.Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
        if (printAttributes.getColorMode() != 0) {
            minMargins.setColorMode(printAttributes.getColorMode());
        }
        if (Build.VERSION.SDK_INT >= 23 && printAttributes.getDuplexMode() != 0) {
            minMargins.setDuplexMode(printAttributes.getDuplexMode());
        }
        return minMargins;
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 19;
    }

    static boolean a(Bitmap bitmap) {
        return bitmap.getWidth() <= bitmap.getHeight();
    }

    Bitmap a(Uri uri) {
        BitmapFactory.Options options;
        if (uri == null || this.i == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inJustDecodeBounds = true;
        a(uri, options2);
        int i = options2.outWidth;
        int i2 = options2.outHeight;
        if (i > 0 && i2 > 0) {
            int max = Math.max(i, i2);
            int i3 = 1;
            while (max > p) {
                max >>>= 1;
                i3 <<= 1;
            }
            if (i3 > 0 && Math.min(i, i2) / i3 > 0) {
                synchronized (this.k) {
                    this.j = new BitmapFactory.Options();
                    this.j.inMutable = true;
                    this.j.inSampleSize = i3;
                    options = this.j;
                }
                try {
                    Bitmap a2 = a(uri, options);
                    synchronized (this.k) {
                        this.j = null;
                    }
                    return a2;
                } catch (Throwable th) {
                    synchronized (this.k) {
                        this.j = null;
                        throw th;
                    }
                }
            }
        }
        return null;
    }

    public void a(int i) {
        this.l = i;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [android.support.v4.g.a$1] */
    @ak(a = 19)
    void a(final PrintAttributes printAttributes, final int i, final Bitmap bitmap, final ParcelFileDescriptor parcelFileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        final PrintAttributes build = b ? printAttributes : a(printAttributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
        new AsyncTask<Void, Void, Throwable>() { // from class: android.support.v4.g.a.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* renamed from: a */
            public Throwable doInBackground(Void... voidArr) {
                RectF rectF;
                try {
                    if (cancellationSignal.isCanceled()) {
                        return null;
                    }
                    PrintedPdfDocument printedPdfDocument = new PrintedPdfDocument(a.this.i, build);
                    Bitmap a2 = a.a(bitmap, build.getColorMode());
                    if (cancellationSignal.isCanceled()) {
                        return null;
                    }
                    PdfDocument.Page startPage = printedPdfDocument.startPage(1);
                    if (a.b) {
                        rectF = new RectF(startPage.getInfo().getContentRect());
                    } else {
                        PrintedPdfDocument printedPdfDocument2 = new PrintedPdfDocument(a.this.i, printAttributes);
                        PdfDocument.Page startPage2 = printedPdfDocument2.startPage(1);
                        RectF rectF2 = new RectF(startPage2.getInfo().getContentRect());
                        printedPdfDocument2.finishPage(startPage2);
                        printedPdfDocument2.close();
                        rectF = rectF2;
                    }
                    Matrix a3 = a.a(a2.getWidth(), a2.getHeight(), rectF, i);
                    if (!a.b) {
                        a3.postTranslate(rectF.left, rectF.top);
                        startPage.getCanvas().clipRect(rectF);
                    }
                    startPage.getCanvas().drawBitmap(a2, a3, null);
                    printedPdfDocument.finishPage(startPage);
                    if (cancellationSignal.isCanceled()) {
                        printedPdfDocument.close();
                        if (parcelFileDescriptor != null) {
                            try {
                                parcelFileDescriptor.close();
                            } catch (IOException unused) {
                            }
                        }
                        if (a2 != bitmap) {
                            a2.recycle();
                        }
                        return null;
                    }
                    printedPdfDocument.writeTo(new FileOutputStream(parcelFileDescriptor.getFileDescriptor()));
                    printedPdfDocument.close();
                    if (parcelFileDescriptor != null) {
                        try {
                            parcelFileDescriptor.close();
                        } catch (IOException unused2) {
                        }
                    }
                    if (a2 != bitmap) {
                        a2.recycle();
                    }
                    return null;
                } catch (Throwable th) {
                    return th;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* renamed from: a */
            public void onPostExecute(Throwable th) {
                if (cancellationSignal.isCanceled()) {
                    writeResultCallback.onWriteCancelled();
                } else if (th == null) {
                    writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                } else {
                    Log.e(a.o, "Error writing printed content", th);
                    writeResultCallback.onWriteFailed(null);
                }
            }
        }.execute(new Void[0]);
    }

    public void a(@af String str, @af Bitmap bitmap) {
        a(str, bitmap, (InterfaceC0020a) null);
    }

    public void a(@af String str, @af Bitmap bitmap, @ag InterfaceC0020a interfaceC0020a) {
        if (Build.VERSION.SDK_INT < 19 || bitmap == null) {
            return;
        }
        ((PrintManager) this.i.getSystemService("print")).print(str, new b(str, this.l, bitmap, interfaceC0020a), new PrintAttributes.Builder().setMediaSize(a(bitmap) ? PrintAttributes.MediaSize.UNKNOWN_PORTRAIT : PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE).setColorMode(this.m).build());
    }

    public void a(@af String str, @af Uri uri) {
        a(str, uri, (InterfaceC0020a) null);
    }

    public void a(@af String str, @af Uri uri, @ag InterfaceC0020a interfaceC0020a) {
        PrintAttributes.MediaSize mediaSize;
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        c cVar = new c(str, uri, interfaceC0020a, this.l);
        PrintManager printManager = (PrintManager) this.i.getSystemService("print");
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(this.m);
        int i = this.n;
        if (i != 1 && i != 0) {
            if (i == 2) {
                mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
            }
            printManager.print(str, cVar, builder.build());
        }
        mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
        builder.setMediaSize(mediaSize);
        printManager.print(str, cVar, builder.build());
    }

    public int b() {
        return this.l;
    }

    public void b(int i) {
        this.m = i;
    }

    public int c() {
        return this.m;
    }

    public void c(int i) {
        this.n = i;
    }

    public int d() {
        if (Build.VERSION.SDK_INT < 19 || this.n != 0) {
            return this.n;
        }
        return 1;
    }
}
