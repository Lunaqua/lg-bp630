package android.support.v4.j;

import android.support.annotation.an;
import android.util.Log;
import java.io.Writer;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class i extends Writer {
    private final String a;
    private StringBuilder b = new StringBuilder(128);

    public i(String str) {
        this.a = str;
    }

    private void a() {
        if (this.b.length() > 0) {
            Log.d(this.a, this.b.toString());
            StringBuilder sb = this.b;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        a();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                a();
            } else {
                this.b.append(c);
            }
        }
    }
}
