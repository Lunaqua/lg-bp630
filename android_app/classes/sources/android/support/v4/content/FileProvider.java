package android.support.v4.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.t;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class FileProvider extends ContentProvider {
    private static final String b = "android.support.FILE_PROVIDER_PATHS";
    private static final String c = "root-path";
    private static final String d = "files-path";
    private static final String e = "cache-path";
    private static final String f = "external-path";
    private static final String g = "external-files-path";
    private static final String h = "external-cache-path";
    private static final String i = "external-media-path";
    private static final String j = "name";
    private static final String k = "path";
    private a n;
    private static final String[] a = {"_display_name", "_size"};
    private static final File l = new File("/");
    @t(a = "sCache")
    private static HashMap<String, a> m = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        Uri a(File file);

        File a(Uri uri);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b implements a {
        private final String a;
        private final HashMap<String, File> b = new HashMap<>();

        b(String str) {
            this.a = str;
        }

        @Override // android.support.v4.content.FileProvider.a
        public Uri a(File file) {
            try {
                String canonicalPath = file.getCanonicalPath();
                Map.Entry<String, File> entry = null;
                for (Map.Entry<String, File> entry2 : this.b.entrySet()) {
                    String path = entry2.getValue().getPath();
                    if (canonicalPath.startsWith(path) && (entry == null || path.length() > entry.getValue().getPath().length())) {
                        entry = entry2;
                    }
                }
                if (entry == null) {
                    throw new IllegalArgumentException("Failed to find configured root that contains " + canonicalPath);
                }
                String path2 = entry.getValue().getPath();
                String substring = canonicalPath.substring(path2.endsWith("/") ? path2.length() : path2.length() + 1);
                return new Uri.Builder().scheme("content").authority(this.a).encodedPath(Uri.encode(entry.getKey()) + '/' + Uri.encode(substring, "/")).build();
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
        }

        @Override // android.support.v4.content.FileProvider.a
        public File a(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = this.b.get(decode);
            if (file == null) {
                throw new IllegalArgumentException("Unable to find configured root for " + uri);
            }
            File file2 = new File(file, decode2);
            try {
                File canonicalFile = file2.getCanonicalFile();
                if (canonicalFile.getPath().startsWith(file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("Resolved path jumped beyond configured root");
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
            }
        }

        void a(String str, File file) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Name must not be empty");
            }
            try {
                this.b.put(str, file.getCanonicalFile());
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file, e);
            }
        }
    }

    private static int a(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + str);
    }

    public static Uri a(@af Context context, @af String str, @af File file) {
        return a(context, str).a(file);
    }

    private static a a(Context context, String str) {
        a aVar;
        synchronized (m) {
            aVar = m.get(str);
            if (aVar == null) {
                try {
                    aVar = b(context, str);
                    m.put(str, aVar);
                } catch (IOException e2) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                } catch (XmlPullParserException e3) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e3);
                }
            }
        }
        return aVar;
    }

    private static File a(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    private static Object[] a(Object[] objArr, int i2) {
        Object[] objArr2 = new Object[i2];
        System.arraycopy(objArr, 0, objArr2, 0, i2);
        return objArr2;
    }

    private static String[] a(String[] strArr, int i2) {
        String[] strArr2 = new String[i2];
        System.arraycopy(strArr, 0, strArr2, 0, i2);
        return strArr2;
    }

    private static a b(Context context, String str) {
        b bVar = new b(str);
        XmlResourceParser loadXmlMetaData = context.getPackageManager().resolveContentProvider(str, 128).loadXmlMetaData(context.getPackageManager(), b);
        if (loadXmlMetaData == null) {
            throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
        }
        while (true) {
            int next = loadXmlMetaData.next();
            if (next == 1) {
                return bVar;
            }
            if (next == 2) {
                String name = loadXmlMetaData.getName();
                File file = null;
                String attributeValue = loadXmlMetaData.getAttributeValue(null, "name");
                String attributeValue2 = loadXmlMetaData.getAttributeValue(null, k);
                if (c.equals(name)) {
                    file = l;
                } else if (d.equals(name)) {
                    file = context.getFilesDir();
                } else if (e.equals(name)) {
                    file = context.getCacheDir();
                } else if (f.equals(name)) {
                    file = Environment.getExternalStorageDirectory();
                } else if (g.equals(name)) {
                    File[] externalFilesDirs = c.getExternalFilesDirs(context, null);
                    if (externalFilesDirs.length > 0) {
                        file = externalFilesDirs[0];
                    }
                } else if (h.equals(name)) {
                    File[] externalCacheDirs = c.getExternalCacheDirs(context);
                    if (externalCacheDirs.length > 0) {
                        file = externalCacheDirs[0];
                    }
                } else if (Build.VERSION.SDK_INT >= 21 && i.equals(name)) {
                    File[] externalMediaDirs = context.getExternalMediaDirs();
                    if (externalMediaDirs.length > 0) {
                        file = externalMediaDirs[0];
                    }
                }
                if (file != null) {
                    bVar.a(attributeValue, a(file, attributeValue2));
                }
            }
        }
    }

    @Override // android.content.ContentProvider
    public void attachInfo(@af Context context, @af ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        }
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
        this.n = a(context, providerInfo.authority);
    }

    @Override // android.content.ContentProvider
    public int delete(@af Uri uri, @ag String str, @ag String[] strArr) {
        return this.n.a(uri).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public String getType(@af Uri uri) {
        File a2 = this.n.a(uri);
        int lastIndexOf = a2.getName().lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(a2.getName().substring(lastIndexOf + 1));
            return mimeTypeFromExtension != null ? mimeTypeFromExtension : "application/octet-stream";
        }
        return "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public Uri insert(@af Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(@af Uri uri, @af String str) {
        return ParcelFileDescriptor.open(this.n.a(uri), a(str));
    }

    @Override // android.content.ContentProvider
    public Cursor query(@af Uri uri, @ag String[] strArr, @ag String str, @ag String[] strArr2, @ag String str2) {
        int i2;
        File a2 = this.n.a(uri);
        if (strArr == null) {
            strArr = a;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i3 = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i3] = "_display_name";
                i2 = i3 + 1;
                objArr[i3] = a2.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i3] = "_size";
                i2 = i3 + 1;
                objArr[i3] = Long.valueOf(a2.length());
            }
            i3 = i2;
        }
        String[] a3 = a(strArr3, i3);
        Object[] a4 = a(objArr, i3);
        MatrixCursor matrixCursor = new MatrixCursor(a3, 1);
        matrixCursor.addRow(a4);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(@af Uri uri, ContentValues contentValues, @ag String str, @ag String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }
}
