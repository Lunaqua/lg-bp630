package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.an;
import android.support.v4.os.f;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new Parcelable.Creator<ResultReceiver>() { // from class: android.support.v4.os.ResultReceiver.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ResultReceiver createFromParcel(Parcel parcel) {
            return new ResultReceiver(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ResultReceiver[] newArray(int i) {
            return new ResultReceiver[i];
        }
    };
    final boolean a;
    final Handler b;
    f c;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    class a extends f.a {
        a() {
        }

        @Override // android.support.v4.os.f
        public void a(int i, Bundle bundle) {
            if (ResultReceiver.this.b != null) {
                ResultReceiver.this.b.post(new b(i, bundle));
            } else {
                ResultReceiver.this.b(i, bundle);
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    class b implements Runnable {
        final int a;
        final Bundle b;

        b(int i, Bundle bundle) {
            this.a = i;
            this.b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.b(this.a, this.b);
        }
    }

    public ResultReceiver(Handler handler) {
        this.a = true;
        this.b = handler;
    }

    ResultReceiver(Parcel parcel) {
        this.a = false;
        this.b = null;
        this.c = f.a.a(parcel.readStrongBinder());
    }

    public void a(int i, Bundle bundle) {
        if (this.a) {
            Handler handler = this.b;
            if (handler != null) {
                handler.post(new b(i, bundle));
                return;
            } else {
                b(i, bundle);
                return;
            }
        }
        f fVar = this.c;
        if (fVar != null) {
            try {
                fVar.a(i, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    protected void b(int i, Bundle bundle) {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.c == null) {
                this.c = new a();
            }
            parcel.writeStrongBinder(this.c.asBinder());
        }
    }
}
