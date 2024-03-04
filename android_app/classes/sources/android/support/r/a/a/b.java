package android.support.r.a.a;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class b {
    public static final int a = 1;
    private static final String b = "android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
    private static final String c = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
    private static final String d = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
    private static final String e = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
    private static final String f = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
    private static final String g = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
    private static final String h = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface a {
        boolean a(c cVar, int i, Bundle bundle);
    }

    @af
    public static InputConnection a(@af InputConnection inputConnection, @af EditorInfo editorInfo, @af final a aVar) {
        if (inputConnection != null) {
            if (editorInfo != null) {
                if (aVar != null) {
                    return Build.VERSION.SDK_INT >= 25 ? new InputConnectionWrapper(inputConnection, false) { // from class: android.support.r.a.a.b.1
                        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
                        public boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
                            if (aVar.a(c.a(inputContentInfo), i, bundle)) {
                                return true;
                            }
                            return super.commitContent(inputContentInfo, i, bundle);
                        }
                    } : android.support.r.a.a.a.a(editorInfo).length == 0 ? inputConnection : new InputConnectionWrapper(inputConnection, false) { // from class: android.support.r.a.a.b.2
                        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
                        public boolean performPrivateCommand(String str, Bundle bundle) {
                            if (b.a(str, bundle, aVar)) {
                                return true;
                            }
                            return super.performPrivateCommand(str, bundle);
                        }
                    };
                }
                throw new IllegalArgumentException("onCommitContentListener must be non-null");
            }
            throw new IllegalArgumentException("editorInfo must be non-null");
        }
        throw new IllegalArgumentException("inputConnection must be non-null");
    }

    public static boolean a(@af InputConnection inputConnection, @af EditorInfo editorInfo, @af c cVar, int i, @ag Bundle bundle) {
        boolean z;
        ClipDescription b2 = cVar.b();
        String[] a2 = android.support.r.a.a.a.a(editorInfo);
        int length = a2.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = false;
                break;
            } else if (b2.hasMimeType(a2[i2])) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            if (Build.VERSION.SDK_INT >= 25) {
                return inputConnection.commitContent((InputContentInfo) cVar.d(), i, bundle);
            }
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable(c, cVar.a());
            bundle2.putParcelable(d, cVar.b());
            bundle2.putParcelable(e, cVar.c());
            bundle2.putInt(g, i);
            bundle2.putParcelable(f, bundle);
            return inputConnection.performPrivateCommand(b, bundle2);
        }
        return false;
    }

    static boolean a(@ag String str, @af Bundle bundle, @af a aVar) {
        ResultReceiver resultReceiver;
        if (!TextUtils.equals(b, str) || bundle == null) {
            return false;
        }
        try {
            resultReceiver = (ResultReceiver) bundle.getParcelable(h);
            try {
                boolean a2 = aVar.a(new c((Uri) bundle.getParcelable(c), (ClipDescription) bundle.getParcelable(d), (Uri) bundle.getParcelable(e)), bundle.getInt(g), (Bundle) bundle.getParcelable(f));
                if (resultReceiver != null) {
                    resultReceiver.send(a2 ? 1 : 0, null);
                }
                return a2;
            } catch (Throwable th) {
                th = th;
                if (resultReceiver != null) {
                    resultReceiver.send(0, null);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            resultReceiver = null;
        }
    }
}
