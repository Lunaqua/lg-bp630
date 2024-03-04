package android.support.v4.app;

import android.arch.lifecycle.f;
import android.arch.lifecycle.l;
import android.arch.lifecycle.m;
import android.arch.lifecycle.r;
import android.arch.lifecycle.s;
import android.arch.lifecycle.t;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.ac;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.f;
import android.support.v4.j.h;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class LoaderManagerImpl extends LoaderManager {
    static boolean DEBUG = false;
    static final String TAG = "LoaderManager";
    @af
    private final f mLifecycleOwner;
    @af
    private final LoaderViewModel mLoaderViewModel;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LoaderInfo<D> extends l<D> implements f.c<D> {
        @ag
        private final Bundle mArgs;
        private final int mId;
        private android.arch.lifecycle.f mLifecycleOwner;
        @af
        private final android.support.v4.content.f<D> mLoader;
        private LoaderObserver<D> mObserver;
        private android.support.v4.content.f<D> mPriorLoader;

        LoaderInfo(int i, @ag Bundle bundle, @af android.support.v4.content.f<D> fVar, @ag android.support.v4.content.f<D> fVar2) {
            this.mId = i;
            this.mArgs = bundle;
            this.mLoader = fVar;
            this.mPriorLoader = fVar2;
            this.mLoader.a(i, this);
        }

        @ac
        android.support.v4.content.f<D> destroy(boolean z) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v(LoaderManagerImpl.TAG, "  Destroying: " + this);
            }
            this.mLoader.y();
            this.mLoader.B();
            LoaderObserver<D> loaderObserver = this.mObserver;
            if (loaderObserver != null) {
                removeObserver(loaderObserver);
                if (z) {
                    loaderObserver.reset();
                }
            }
            this.mLoader.a(this);
            if ((loaderObserver == null || loaderObserver.hasDeliveredData()) && !z) {
                return this.mLoader;
            }
            this.mLoader.D();
            return this.mPriorLoader;
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.mId);
            printWriter.print(" mArgs=");
            printWriter.println(this.mArgs);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.mLoader);
            android.support.v4.content.f<D> fVar = this.mLoader;
            fVar.a(str + "  ", fileDescriptor, printWriter, strArr);
            if (this.mObserver != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.mObserver);
                LoaderObserver<D> loaderObserver = this.mObserver;
                loaderObserver.dump(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(getLoader().c(getValue()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(hasActiveObservers());
        }

        @af
        android.support.v4.content.f<D> getLoader() {
            return this.mLoader;
        }

        boolean isCallbackWaitingForData() {
            LoaderObserver<D> loaderObserver;
            return (!hasActiveObservers() || (loaderObserver = this.mObserver) == null || loaderObserver.hasDeliveredData()) ? false : true;
        }

        void markForRedelivery() {
            android.arch.lifecycle.f fVar = this.mLifecycleOwner;
            LoaderObserver<D> loaderObserver = this.mObserver;
            if (fVar == null || loaderObserver == null) {
                return;
            }
            super.removeObserver(loaderObserver);
            observe(fVar, loaderObserver);
        }

        @Override // android.arch.lifecycle.LiveData
        protected void onActive() {
            if (LoaderManagerImpl.DEBUG) {
                Log.v(LoaderManagerImpl.TAG, "  Starting: " + this);
            }
            this.mLoader.x();
        }

        @Override // android.arch.lifecycle.LiveData
        protected void onInactive() {
            if (LoaderManagerImpl.DEBUG) {
                Log.v(LoaderManagerImpl.TAG, "  Stopping: " + this);
            }
            this.mLoader.A();
        }

        @Override // android.support.v4.content.f.c
        public void onLoadComplete(@af android.support.v4.content.f<D> fVar, @ag D d) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v(LoaderManagerImpl.TAG, "onLoadComplete: " + this);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                setValue(d);
                return;
            }
            if (LoaderManagerImpl.DEBUG) {
                Log.w(LoaderManagerImpl.TAG, "onLoadComplete was incorrectly called on a background thread");
            }
            postValue(d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.arch.lifecycle.LiveData
        public void removeObserver(@af m<? super D> mVar) {
            super.removeObserver(mVar);
            this.mLifecycleOwner = null;
            this.mObserver = null;
        }

        @ac
        @af
        android.support.v4.content.f<D> setCallback(@af android.arch.lifecycle.f fVar, @af LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
            LoaderObserver<D> loaderObserver = new LoaderObserver<>(this.mLoader, loaderCallbacks);
            observe(fVar, loaderObserver);
            LoaderObserver<D> loaderObserver2 = this.mObserver;
            if (loaderObserver2 != null) {
                removeObserver(loaderObserver2);
            }
            this.mLifecycleOwner = fVar;
            this.mObserver = loaderObserver;
            return this.mLoader;
        }

        @Override // android.arch.lifecycle.l, android.arch.lifecycle.LiveData
        public void setValue(D d) {
            super.setValue(d);
            android.support.v4.content.f<D> fVar = this.mPriorLoader;
            if (fVar != null) {
                fVar.D();
                this.mPriorLoader = null;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.mId);
            sb.append(" : ");
            h.a(this.mLoader, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LoaderObserver<D> implements m<D> {
        @af
        private final LoaderManager.LoaderCallbacks<D> mCallback;
        private boolean mDeliveredData = false;
        @af
        private final android.support.v4.content.f<D> mLoader;

        LoaderObserver(@af android.support.v4.content.f<D> fVar, @af LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
            this.mLoader = fVar;
            this.mCallback = loaderCallbacks;
        }

        public void dump(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.mDeliveredData);
        }

        boolean hasDeliveredData() {
            return this.mDeliveredData;
        }

        @Override // android.arch.lifecycle.m
        public void onChanged(@ag D d) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v(LoaderManagerImpl.TAG, "  onLoadFinished in " + this.mLoader + ": " + this.mLoader.c(d));
            }
            this.mCallback.onLoadFinished(this.mLoader, d);
            this.mDeliveredData = true;
        }

        @ac
        void reset() {
            if (this.mDeliveredData) {
                if (LoaderManagerImpl.DEBUG) {
                    Log.v(LoaderManagerImpl.TAG, "  Resetting: " + this.mLoader);
                }
                this.mCallback.onLoaderReset(this.mLoader);
            }
        }

        public String toString() {
            return this.mCallback.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class LoaderViewModel extends r {
        private static final s.b FACTORY = new s.b() { // from class: android.support.v4.app.LoaderManagerImpl.LoaderViewModel.1
            @Override // android.arch.lifecycle.s.b
            @af
            public <T extends r> T create(@af Class<T> cls) {
                return new LoaderViewModel();
            }
        };
        private android.support.v4.j.s<LoaderInfo> mLoaders = new android.support.v4.j.s<>();
        private boolean mCreatingLoader = false;

        LoaderViewModel() {
        }

        @af
        static LoaderViewModel getInstance(t tVar) {
            return (LoaderViewModel) new s(tVar, FACTORY).a(LoaderViewModel.class);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.mLoaders.b() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.mLoaders.b(); i++) {
                    LoaderInfo f = this.mLoaders.f(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.mLoaders.e(i));
                    printWriter.print(": ");
                    printWriter.println(f.toString());
                    f.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void finishCreatingLoader() {
            this.mCreatingLoader = false;
        }

        <D> LoaderInfo<D> getLoader(int i) {
            return this.mLoaders.a(i);
        }

        boolean hasRunningLoaders() {
            int b = this.mLoaders.b();
            for (int i = 0; i < b; i++) {
                if (this.mLoaders.f(i).isCallbackWaitingForData()) {
                    return true;
                }
            }
            return false;
        }

        boolean isCreatingLoader() {
            return this.mCreatingLoader;
        }

        void markForRedelivery() {
            int b = this.mLoaders.b();
            for (int i = 0; i < b; i++) {
                this.mLoaders.f(i).markForRedelivery();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.arch.lifecycle.r
        public void onCleared() {
            super.onCleared();
            int b = this.mLoaders.b();
            for (int i = 0; i < b; i++) {
                this.mLoaders.f(i).destroy(true);
            }
            this.mLoaders.d();
        }

        void putLoader(int i, @af LoaderInfo loaderInfo) {
            this.mLoaders.b(i, loaderInfo);
        }

        void removeLoader(int i) {
            this.mLoaders.c(i);
        }

        void startCreatingLoader() {
            this.mCreatingLoader = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoaderManagerImpl(@af android.arch.lifecycle.f fVar, @af t tVar) {
        this.mLifecycleOwner = fVar;
        this.mLoaderViewModel = LoaderViewModel.getInstance(tVar);
    }

    @ac
    @af
    private <D> android.support.v4.content.f<D> createAndInstallLoader(int i, @ag Bundle bundle, @af LoaderManager.LoaderCallbacks<D> loaderCallbacks, @ag android.support.v4.content.f<D> fVar) {
        try {
            this.mLoaderViewModel.startCreatingLoader();
            android.support.v4.content.f<D> onCreateLoader = loaderCallbacks.onCreateLoader(i, bundle);
            if (onCreateLoader != null) {
                if (onCreateLoader.getClass().isMemberClass() && !Modifier.isStatic(onCreateLoader.getClass().getModifiers())) {
                    throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + onCreateLoader);
                }
                LoaderInfo loaderInfo = new LoaderInfo(i, bundle, onCreateLoader, fVar);
                if (DEBUG) {
                    Log.v(TAG, "  Created new loader " + loaderInfo);
                }
                this.mLoaderViewModel.putLoader(i, loaderInfo);
                this.mLoaderViewModel.finishCreatingLoader();
                return loaderInfo.setCallback(this.mLifecycleOwner, loaderCallbacks);
            }
            throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
        } catch (Throwable th) {
            this.mLoaderViewModel.finishCreatingLoader();
            throw th;
        }
    }

    @Override // android.support.v4.app.LoaderManager
    @ac
    public void destroyLoader(int i) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
        if (DEBUG) {
            Log.v(TAG, "destroyLoader in " + this + " of " + i);
        }
        LoaderInfo loader = this.mLoaderViewModel.getLoader(i);
        if (loader != null) {
            loader.destroy(true);
            this.mLoaderViewModel.removeLoader(i);
        }
    }

    @Override // android.support.v4.app.LoaderManager
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mLoaderViewModel.dump(str, fileDescriptor, printWriter, strArr);
    }

    @Override // android.support.v4.app.LoaderManager
    @ag
    public <D> android.support.v4.content.f<D> getLoader(int i) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        LoaderInfo<D> loader = this.mLoaderViewModel.getLoader(i);
        if (loader != null) {
            return loader.getLoader();
        }
        return null;
    }

    @Override // android.support.v4.app.LoaderManager
    public boolean hasRunningLoaders() {
        return this.mLoaderViewModel.hasRunningLoaders();
    }

    @Override // android.support.v4.app.LoaderManager
    @ac
    @af
    public <D> android.support.v4.content.f<D> initLoader(int i, @ag Bundle bundle, @af LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            LoaderInfo<D> loader = this.mLoaderViewModel.getLoader(i);
            if (DEBUG) {
                Log.v(TAG, "initLoader in " + this + ": args=" + bundle);
            }
            if (loader == null) {
                return createAndInstallLoader(i, bundle, loaderCallbacks, null);
            }
            if (DEBUG) {
                Log.v(TAG, "  Re-using existing loader " + loader);
            }
            return loader.setCallback(this.mLifecycleOwner, loaderCallbacks);
        }
        throw new IllegalStateException("initLoader must be called on the main thread");
    }

    @Override // android.support.v4.app.LoaderManager
    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    @Override // android.support.v4.app.LoaderManager
    @ac
    @af
    public <D> android.support.v4.content.f<D> restartLoader(int i, @ag Bundle bundle, @af LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            if (DEBUG) {
                Log.v(TAG, "restartLoader in " + this + ": args=" + bundle);
            }
            LoaderInfo<D> loader = this.mLoaderViewModel.getLoader(i);
            return createAndInstallLoader(i, bundle, loaderCallbacks, loader != null ? loader.destroy(false) : null);
        }
        throw new IllegalStateException("restartLoader must be called on the main thread");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        h.a(this.mLifecycleOwner, sb);
        sb.append("}}");
        return sb.toString();
    }
}
