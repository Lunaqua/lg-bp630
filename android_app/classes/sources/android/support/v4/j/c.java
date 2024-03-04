package android.support.v4.j;

import android.support.annotation.af;
import android.support.annotation.ag;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class c {
    private final File a;
    private final File b;

    public c(@af File file) {
        this.a = file;
        this.b = new File(file.getPath() + ".bak");
    }

    private static boolean c(@af FileOutputStream fileOutputStream) {
        try {
            fileOutputStream.getFD().sync();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    @af
    public File a() {
        return this.a;
    }

    public void a(@ag FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            c(fileOutputStream);
            try {
                fileOutputStream.close();
                this.b.delete();
            } catch (IOException e) {
                Log.w("AtomicFile", "finishWrite: Got exception:", e);
            }
        }
    }

    public void b() {
        this.a.delete();
        this.b.delete();
    }

    public void b(@ag FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            c(fileOutputStream);
            try {
                fileOutputStream.close();
                this.a.delete();
                this.b.renameTo(this.a);
            } catch (IOException e) {
                Log.w("AtomicFile", "failWrite: Got exception:", e);
            }
        }
    }

    @af
    public FileOutputStream c() {
        if (this.a.exists()) {
            if (this.b.exists()) {
                this.a.delete();
            } else if (!this.a.renameTo(this.b)) {
                Log.w("AtomicFile", "Couldn't rename file " + this.a + " to backup file " + this.b);
            }
        }
        try {
            return new FileOutputStream(this.a);
        } catch (FileNotFoundException unused) {
            if (!this.a.getParentFile().mkdirs()) {
                throw new IOException("Couldn't create directory " + this.a);
            }
            try {
                return new FileOutputStream(this.a);
            } catch (FileNotFoundException unused2) {
                throw new IOException("Couldn't create " + this.a);
            }
        }
    }

    @af
    public FileInputStream d() {
        if (this.b.exists()) {
            this.a.delete();
            this.b.renameTo(this.a);
        }
        return new FileInputStream(this.a);
    }

    @af
    public byte[] e() {
        FileInputStream d = d();
        try {
            byte[] bArr = new byte[d.available()];
            int i = 0;
            while (true) {
                int read = d.read(bArr, i, bArr.length - i);
                if (read <= 0) {
                    return bArr;
                }
                i += read;
                int available = d.available();
                if (available > bArr.length - i) {
                    byte[] bArr2 = new byte[available + i];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    bArr = bArr2;
                }
            }
        } finally {
            d.close();
        }
    }
}
