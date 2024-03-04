package android.support.v4.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.au;
import android.support.v4.j.p;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.ArrayBlockingQueue;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class c {
    private static final String d = "AsyncLayoutInflater";
    LayoutInflater a;
    private Handler.Callback e = new Handler.Callback() { // from class: android.support.v4.view.c.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            b bVar = (b) message.obj;
            if (bVar.d == null) {
                bVar.d = c.this.a.inflate(bVar.c, bVar.b, false);
            }
            bVar.e.a(bVar.d, bVar.c, bVar.b);
            c.this.c.a(bVar);
            return true;
        }
    };
    Handler b = new Handler(this.e);
    C0034c c = C0034c.a();

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class a extends LayoutInflater {
        private static final String[] a = {"android.widget.", "android.webkit.", "android.app."};

        a(Context context) {
            super(context);
        }

        @Override // android.view.LayoutInflater
        public LayoutInflater cloneInContext(Context context) {
            return new a(context);
        }

        @Override // android.view.LayoutInflater
        protected View onCreateView(String str, AttributeSet attributeSet) {
            View createView;
            for (String str2 : a) {
                try {
                    createView = createView(str, str2, attributeSet);
                } catch (ClassNotFoundException unused) {
                }
                if (createView != null) {
                    return createView;
                }
            }
            return super.onCreateView(str, attributeSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b {
        c a;
        ViewGroup b;
        int c;
        View d;
        d e;

        b() {
        }
    }

    /* renamed from: android.support.v4.view.c$c  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private static class C0034c extends Thread {
        private static final C0034c a = new C0034c();
        private ArrayBlockingQueue<b> b = new ArrayBlockingQueue<>(10);
        private p.c<b> c = new p.c<>(10);

        static {
            a.start();
        }

        private C0034c() {
        }

        public static C0034c a() {
            return a;
        }

        public void a(b bVar) {
            bVar.e = null;
            bVar.a = null;
            bVar.b = null;
            bVar.c = 0;
            bVar.d = null;
            this.c.a(bVar);
        }

        public void b() {
            try {
                b take = this.b.take();
                try {
                    take.d = take.a.a.inflate(take.c, take.b, false);
                } catch (RuntimeException e) {
                    Log.w(c.d, "Failed to inflate resource in the background! Retrying on the UI thread", e);
                }
                Message.obtain(take.a.b, 0, take).sendToTarget();
            } catch (InterruptedException e2) {
                Log.w(c.d, e2);
            }
        }

        public void b(b bVar) {
            try {
                this.b.put(bVar);
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to enqueue async inflate request", e);
            }
        }

        public b c() {
            b a2 = this.c.a();
            return a2 == null ? new b() : a2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                b();
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface d {
        void a(@android.support.annotation.af View view, @android.support.annotation.aa int i, @android.support.annotation.ag ViewGroup viewGroup);
    }

    public c(@android.support.annotation.af Context context) {
        this.a = new a(context);
    }

    @au
    public void a(@android.support.annotation.aa int i, @android.support.annotation.ag ViewGroup viewGroup, @android.support.annotation.af d dVar) {
        if (dVar == null) {
            throw new NullPointerException("callback argument may not be null!");
        }
        b c = this.c.c();
        c.a = this;
        c.c = i;
        c.b = viewGroup;
        c.e = dVar;
        this.c.b(c);
    }
}
