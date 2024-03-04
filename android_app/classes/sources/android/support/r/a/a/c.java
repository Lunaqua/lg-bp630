package android.support.r.a.a;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.view.inputmethod.InputContentInfo;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class c {
    private final InterfaceC0014c a;

    @ak(a = 25)
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static final class a implements InterfaceC0014c {
        @af
        final InputContentInfo a;

        a(@af Uri uri, @af ClipDescription clipDescription, @ag Uri uri2) {
            this.a = new InputContentInfo(uri, clipDescription, uri2);
        }

        a(@af Object obj) {
            this.a = (InputContentInfo) obj;
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        @af
        public Uri a() {
            return this.a.getContentUri();
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        @af
        public ClipDescription b() {
            return this.a.getDescription();
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        @ag
        public Uri c() {
            return this.a.getLinkUri();
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        @ag
        public Object d() {
            return this.a;
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        public void e() {
            this.a.requestPermission();
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        public void f() {
            this.a.releasePermission();
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static final class b implements InterfaceC0014c {
        @af
        private final Uri a;
        @af
        private final ClipDescription b;
        @ag
        private final Uri c;

        b(@af Uri uri, @af ClipDescription clipDescription, @ag Uri uri2) {
            this.a = uri;
            this.b = clipDescription;
            this.c = uri2;
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        @af
        public Uri a() {
            return this.a;
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        @af
        public ClipDescription b() {
            return this.b;
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        @ag
        public Uri c() {
            return this.c;
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        @ag
        public Object d() {
            return null;
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        public void e() {
        }

        @Override // android.support.r.a.a.c.InterfaceC0014c
        public void f() {
        }
    }

    /* renamed from: android.support.r.a.a.c$c  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private interface InterfaceC0014c {
        @af
        Uri a();

        @af
        ClipDescription b();

        @ag
        Uri c();

        @ag
        Object d();

        void e();

        void f();
    }

    public c(@af Uri uri, @af ClipDescription clipDescription, @ag Uri uri2) {
        this.a = Build.VERSION.SDK_INT >= 25 ? new a(uri, clipDescription, uri2) : new b(uri, clipDescription, uri2);
    }

    private c(@af InterfaceC0014c interfaceC0014c) {
        this.a = interfaceC0014c;
    }

    @ag
    public static c a(@ag Object obj) {
        if (obj != null && Build.VERSION.SDK_INT >= 25) {
            return new c(new a(obj));
        }
        return null;
    }

    @af
    public Uri a() {
        return this.a.a();
    }

    @af
    public ClipDescription b() {
        return this.a.b();
    }

    @ag
    public Uri c() {
        return this.a.c();
    }

    @ag
    public Object d() {
        return this.a.d();
    }

    public void e() {
        this.a.e();
    }

    public void f() {
        this.a.f();
    }
}
