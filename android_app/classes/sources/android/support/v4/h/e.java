package android.support.v4.h;

import android.net.Uri;
import android.support.annotation.ag;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class e extends a {
    private File b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(@ag a aVar, File file) {
        super(aVar);
        this.b = file;
    }

    private static boolean b(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            boolean z = true;
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    z &= b(file2);
                }
                if (!file2.delete()) {
                    Log.w("DocumentFile", "Failed to delete " + file2);
                    z = false;
                }
            }
            return z;
        }
        return true;
    }

    private static String d(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(lastIndexOf + 1).toLowerCase());
            return mimeTypeFromExtension != null ? mimeTypeFromExtension : "application/octet-stream";
        }
        return "application/octet-stream";
    }

    @Override // android.support.v4.h.a
    public Uri a() {
        return Uri.fromFile(this.b);
    }

    @Override // android.support.v4.h.a
    @ag
    public a a(String str) {
        File file = new File(this.b, str);
        if (file.isDirectory() || file.mkdir()) {
            return new e(this, file);
        }
        return null;
    }

    @Override // android.support.v4.h.a
    @ag
    public a a(String str, String str2) {
        String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
        if (extensionFromMimeType != null) {
            str2 = str2 + "." + extensionFromMimeType;
        }
        File file = new File(this.b, str2);
        try {
            file.createNewFile();
            return new e(this, file);
        } catch (IOException e) {
            Log.w("DocumentFile", "Failed to createFile: " + e);
            return null;
        }
    }

    @Override // android.support.v4.h.a
    public String b() {
        return this.b.getName();
    }

    @Override // android.support.v4.h.a
    @ag
    public String c() {
        if (this.b.isDirectory()) {
            return null;
        }
        return d(this.b.getName());
    }

    @Override // android.support.v4.h.a
    public boolean c(String str) {
        File file = new File(this.b.getParentFile(), str);
        if (this.b.renameTo(file)) {
            this.b = file;
            return true;
        }
        return false;
    }

    @Override // android.support.v4.h.a
    public boolean e() {
        return this.b.isDirectory();
    }

    @Override // android.support.v4.h.a
    public boolean f() {
        return this.b.isFile();
    }

    @Override // android.support.v4.h.a
    public boolean g() {
        return false;
    }

    @Override // android.support.v4.h.a
    public long h() {
        return this.b.lastModified();
    }

    @Override // android.support.v4.h.a
    public long i() {
        return this.b.length();
    }

    @Override // android.support.v4.h.a
    public boolean j() {
        return this.b.canRead();
    }

    @Override // android.support.v4.h.a
    public boolean k() {
        return this.b.canWrite();
    }

    @Override // android.support.v4.h.a
    public boolean l() {
        b(this.b);
        return this.b.delete();
    }

    @Override // android.support.v4.h.a
    public boolean m() {
        return this.b.exists();
    }

    @Override // android.support.v4.h.a
    public a[] n() {
        ArrayList arrayList = new ArrayList();
        File[] listFiles = this.b.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                arrayList.add(new e(this, file));
            }
        }
        return (a[]) arrayList.toArray(new a[arrayList.size()]);
    }
}
