package android.support.v4.app;

import android.arch.lifecycle.u;
import android.os.Bundle;
import android.support.annotation.ac;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.v4.content.f;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class LoaderManager {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface LoaderCallbacks<D> {
        @ac
        @af
        f<D> onCreateLoader(int i, @ag Bundle bundle);

        @ac
        void onLoadFinished(@af f<D> fVar, D d);

        @ac
        void onLoaderReset(@af f<D> fVar);
    }

    public static void enableDebugLogging(boolean z) {
        LoaderManagerImpl.DEBUG = z;
    }

    @af
    public static <T extends android.arch.lifecycle.f & u> LoaderManager getInstance(@af T t) {
        return new LoaderManagerImpl(t, t.getViewModelStore());
    }

    @ac
    public abstract void destroyLoader(int i);

    @Deprecated
    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @ag
    public abstract <D> f<D> getLoader(int i);

    public boolean hasRunningLoaders() {
        return false;
    }

    @ac
    @af
    public abstract <D> f<D> initLoader(int i, @ag Bundle bundle, @af LoaderCallbacks<D> loaderCallbacks);

    public abstract void markForRedelivery();

    @ac
    @af
    public abstract <D> f<D> restartLoader(int i, @ag Bundle bundle, @af LoaderCallbacks<D> loaderCallbacks);
}
