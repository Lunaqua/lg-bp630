package android.support.v4.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface f extends IInterface {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static abstract class a extends Binder implements f {
        static final int a = 1;
        private static final String b = "android.support.v4.os.IResultReceiver";

        /* renamed from: android.support.v4.os.f$a$a  reason: collision with other inner class name */
        /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
        private static class C0029a implements f {
            private IBinder a;

            C0029a(IBinder iBinder) {
                this.a = iBinder;
            }

            public String a() {
                return a.b;
            }

            @Override // android.support.v4.os.f
            public void a(int i, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(a.b);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }
        }

        public a() {
            attachInterface(this, b);
        }

        public static f a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(b);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof f)) ? new C0029a(iBinder) : (f) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface(b);
                a(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(b);
                return true;
            }
        }
    }

    void a(int i, Bundle bundle);
}
