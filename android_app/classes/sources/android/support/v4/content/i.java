package android.support.v4.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.an;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
abstract class i<Params, Progress, Result> {
    private static final String a = "AsyncTask";
    private static final int b = 5;
    private static final int f = 128;
    private static final int g = 1;
    private static final int j = 1;
    private static final int k = 2;
    private static b l;
    private static final ThreadFactory h = new ThreadFactory() { // from class: android.support.v4.content.i.1
        private final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "ModernAsyncTask #" + this.a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> i = new LinkedBlockingQueue(10);
    public static final Executor c = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, i, h);
    private static volatile Executor m = c;
    private volatile c p = c.PENDING;
    final AtomicBoolean d = new AtomicBoolean();
    final AtomicBoolean e = new AtomicBoolean();
    private final d<Params, Result> n = new d<Params, Result>() { // from class: android.support.v4.content.i.2
        @Override // java.util.concurrent.Callable
        public Result call() {
            i.this.e.set(true);
            Result result = null;
            try {
                Process.setThreadPriority(10);
                result = (Result) i.this.a((Object[]) this.b);
                Binder.flushPendingCommands();
                return result;
            } finally {
            }
        }
    };
    private final FutureTask<Result> o = new FutureTask<Result>(this.n) { // from class: android.support.v4.content.i.3
        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                i.this.c((i) get());
            } catch (InterruptedException e) {
                Log.w(i.a, e);
            } catch (CancellationException unused) {
                i.this.c((i) null);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
            } catch (Throwable th) {
                throw new RuntimeException("An error occurred while executing doInBackground()", th);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.support.v4.content.i$4  reason: invalid class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[c.values().length];

        static {
            try {
                a[c.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[c.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a<Data> {
        final i a;
        final Data[] b;

        a(i iVar, Data... dataArr) {
            this.a = iVar;
            this.b = dataArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class b extends Handler {
        b() {
            super(Looper.getMainLooper());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            a aVar = (a) message.obj;
            int i = message.what;
            if (i == 1) {
                aVar.a.e(aVar.b[0]);
            } else if (i != 2) {
            } else {
                aVar.a.b((Object[]) aVar.b);
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum c {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class d<Params, Result> implements Callable<Result> {
        Params[] b;

        d() {
        }
    }

    private static Handler a() {
        b bVar;
        synchronized (i.class) {
            if (l == null) {
                l = new b();
            }
            bVar = l;
        }
        return bVar;
    }

    public static void a(Runnable runnable) {
        m.execute(runnable);
    }

    @an(a = {an.a.LIBRARY_GROUP})
    public static void a(Executor executor) {
        m = executor;
    }

    public final i<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.p == c.PENDING) {
            this.p = c.RUNNING;
            c();
            this.n.b = paramsArr;
            executor.execute(this.o);
            return this;
        }
        int i2 = AnonymousClass4.a[this.p.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                throw new IllegalStateException("We should never reach this state");
            }
            throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
        }
        throw new IllegalStateException("Cannot execute task: the task is already running.");
    }

    public final Result a(long j2, TimeUnit timeUnit) {
        return this.o.get(j2, timeUnit);
    }

    protected abstract Result a(Params... paramsArr);

    protected void a(Result result) {
    }

    public final boolean a(boolean z) {
        this.d.set(true);
        return this.o.cancel(z);
    }

    public final c b() {
        return this.p;
    }

    protected void b(Result result) {
        d();
    }

    protected void b(Progress... progressArr) {
    }

    public final i<Params, Progress, Result> c(Params... paramsArr) {
        return a(m, paramsArr);
    }

    protected void c() {
    }

    void c(Result result) {
        if (this.e.get()) {
            return;
        }
        d((i<Params, Progress, Result>) result);
    }

    Result d(Result result) {
        a().obtainMessage(1, new a(this, result)).sendToTarget();
        return result;
    }

    protected void d() {
    }

    protected final void d(Progress... progressArr) {
        if (e()) {
            return;
        }
        a().obtainMessage(2, new a(this, progressArr)).sendToTarget();
    }

    void e(Result result) {
        if (e()) {
            b((i<Params, Progress, Result>) result);
        } else {
            a((i<Params, Progress, Result>) result);
        }
        this.p = c.FINISHED;
    }

    public final boolean e() {
        return this.d.get();
    }

    public final Result f() {
        return this.o.get();
    }
}
