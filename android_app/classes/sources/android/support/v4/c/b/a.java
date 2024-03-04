package android.support.v4.c.b;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.am;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class a {
    private final Context a;

    /* renamed from: android.support.v4.c.b.a$a  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class AbstractC0015a {
        public void a() {
        }

        public void a(int i, CharSequence charSequence) {
        }

        public void a(b bVar) {
        }

        public void b(int i, CharSequence charSequence) {
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class b {
        private final c a;

        public b(c cVar) {
            this.a = cVar;
        }

        public c a() {
            return this.a;
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class c {
        private final Signature a;
        private final Cipher b;
        private final Mac c;

        public c(@af Signature signature) {
            this.a = signature;
            this.b = null;
            this.c = null;
        }

        public c(@af Cipher cipher) {
            this.b = cipher;
            this.a = null;
            this.c = null;
        }

        public c(@af Mac mac) {
            this.c = mac;
            this.b = null;
            this.a = null;
        }

        @ag
        public Signature a() {
            return this.a;
        }

        @ag
        public Cipher b() {
            return this.b;
        }

        @ag
        public Mac c() {
            return this.c;
        }
    }

    private a(Context context) {
        this.a = context;
    }

    @ak(a = 23)
    private static FingerprintManager.AuthenticationCallback a(final AbstractC0015a abstractC0015a) {
        return new FingerprintManager.AuthenticationCallback() { // from class: android.support.v4.c.b.a.1
            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public void onAuthenticationError(int i, CharSequence charSequence) {
                AbstractC0015a.this.a(i, charSequence);
            }

            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public void onAuthenticationFailed() {
                AbstractC0015a.this.a();
            }

            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public void onAuthenticationHelp(int i, CharSequence charSequence) {
                AbstractC0015a.this.b(i, charSequence);
            }

            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
                AbstractC0015a.this.a(new b(a.a(authenticationResult.getCryptoObject())));
            }
        };
    }

    @ak(a = 23)
    private static FingerprintManager.CryptoObject a(c cVar) {
        if (cVar == null) {
            return null;
        }
        if (cVar.b() != null) {
            return new FingerprintManager.CryptoObject(cVar.b());
        }
        if (cVar.a() != null) {
            return new FingerprintManager.CryptoObject(cVar.a());
        }
        if (cVar.c() != null) {
            return new FingerprintManager.CryptoObject(cVar.c());
        }
        return null;
    }

    @ak(a = 23)
    static c a(FingerprintManager.CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        if (cryptoObject.getCipher() != null) {
            return new c(cryptoObject.getCipher());
        }
        if (cryptoObject.getSignature() != null) {
            return new c(cryptoObject.getSignature());
        }
        if (cryptoObject.getMac() != null) {
            return new c(cryptoObject.getMac());
        }
        return null;
    }

    @af
    public static a a(@af Context context) {
        return new a(context);
    }

    @ag
    @ak(a = 23)
    private static FingerprintManager b(@af Context context) {
        if (context.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
            return (FingerprintManager) context.getSystemService(FingerprintManager.class);
        }
        return null;
    }

    @am(a = "android.permission.USE_FINGERPRINT")
    public void a(@ag c cVar, int i, @ag android.support.v4.os.b bVar, @af AbstractC0015a abstractC0015a, @ag Handler handler) {
        FingerprintManager b2;
        if (Build.VERSION.SDK_INT < 23 || (b2 = b(this.a)) == null) {
            return;
        }
        b2.authenticate(a(cVar), bVar != null ? (CancellationSignal) bVar.d() : null, i, a(abstractC0015a), handler);
    }

    @am(a = "android.permission.USE_FINGERPRINT")
    public boolean a() {
        FingerprintManager b2;
        return Build.VERSION.SDK_INT >= 23 && (b2 = b(this.a)) != null && b2.hasEnrolledFingerprints();
    }

    @am(a = "android.permission.USE_FINGERPRINT")
    public boolean b() {
        FingerprintManager b2;
        return Build.VERSION.SDK_INT >= 23 && (b2 = b(this.a)) != null && b2.isHardwareDetected();
    }
}
