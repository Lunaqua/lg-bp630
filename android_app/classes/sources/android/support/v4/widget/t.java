package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.an;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class t extends r {
    @an(a = {an.a.LIBRARY_GROUP})
    protected int[] l;
    @an(a = {an.a.LIBRARY_GROUP})
    protected int[] m;
    String[] n;
    private int o;
    private a p;
    private b q;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        CharSequence a(Cursor cursor);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface b {
        boolean a(View view, Cursor cursor, int i);
    }

    @Deprecated
    public t(Context context, int i, Cursor cursor, String[] strArr, int[] iArr) {
        super(context, i, cursor);
        this.o = -1;
        this.m = iArr;
        this.n = strArr;
        a(cursor, strArr);
    }

    public t(Context context, int i, Cursor cursor, String[] strArr, int[] iArr, int i2) {
        super(context, i, cursor, i2);
        this.o = -1;
        this.m = iArr;
        this.n = strArr;
        a(cursor, strArr);
    }

    private void a(Cursor cursor, String[] strArr) {
        if (cursor == null) {
            this.l = null;
            return;
        }
        int length = strArr.length;
        int[] iArr = this.l;
        if (iArr == null || iArr.length != length) {
            this.l = new int[length];
        }
        for (int i = 0; i < length; i++) {
            this.l[i] = cursor.getColumnIndexOrThrow(strArr[i]);
        }
    }

    public void a(Cursor cursor, String[] strArr, int[] iArr) {
        this.n = strArr;
        this.m = iArr;
        a(cursor, this.n);
        super.a(cursor);
    }

    public void a(a aVar) {
        this.p = aVar;
    }

    public void a(b bVar) {
        this.q = bVar;
    }

    @Override // android.support.v4.widget.f
    public void a(View view, Context context, Cursor cursor) {
        b bVar = this.q;
        int[] iArr = this.m;
        int length = iArr.length;
        int[] iArr2 = this.l;
        for (int i = 0; i < length; i++) {
            View findViewById = view.findViewById(iArr[i]);
            if (findViewById != null) {
                if (bVar != null ? bVar.a(findViewById, cursor, iArr2[i]) : false) {
                    continue;
                } else {
                    String string = cursor.getString(iArr2[i]);
                    if (string == null) {
                        string = com.lge.media.launcher.a.d;
                    }
                    if (findViewById instanceof TextView) {
                        a((TextView) findViewById, string);
                    } else if (!(findViewById instanceof ImageView)) {
                        throw new IllegalStateException(findViewById.getClass().getName() + " is not a  view that can be bounds by this SimpleCursorAdapter");
                    } else {
                        a((ImageView) findViewById, string);
                    }
                }
            }
        }
    }

    public void a(ImageView imageView, String str) {
        try {
            imageView.setImageResource(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            imageView.setImageURI(Uri.parse(str));
        }
    }

    public void a(TextView textView, String str) {
        textView.setText(str);
    }

    @Override // android.support.v4.widget.f
    public Cursor b(Cursor cursor) {
        a(cursor, this.n);
        return super.b(cursor);
    }

    @Override // android.support.v4.widget.f, android.support.v4.widget.g.a
    public CharSequence c(Cursor cursor) {
        a aVar = this.p;
        if (aVar != null) {
            return aVar.a(cursor);
        }
        int i = this.o;
        return i > -1 ? cursor.getString(i) : super.c(cursor);
    }

    public void c(int i) {
        this.o = i;
    }

    public b d() {
        return this.q;
    }

    public int e() {
        return this.o;
    }

    public a f() {
        return this.p;
    }
}
