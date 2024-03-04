package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class AbsSavedState implements Parcelable {
    private final Parcelable a;
    public static final AbsSavedState b = new AbsSavedState() { // from class: android.support.v4.view.AbsSavedState.1
    };
    public static final Parcelable.Creator<AbsSavedState> CREATOR = new Parcelable.ClassLoaderCreator<AbsSavedState>() { // from class: android.support.v4.view.AbsSavedState.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public AbsSavedState createFromParcel(Parcel parcel) {
            return createFromParcel(parcel, null);
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: a */
        public AbsSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel.readParcelable(classLoader) == null) {
                return AbsSavedState.b;
            }
            throw new IllegalStateException("superState must be null");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public AbsSavedState[] newArray(int i) {
            return new AbsSavedState[i];
        }
    };

    private AbsSavedState() {
        this.a = null;
    }

    protected AbsSavedState(@android.support.annotation.af Parcel parcel) {
        this(parcel, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbsSavedState(@android.support.annotation.af Parcel parcel, @android.support.annotation.ag ClassLoader classLoader) {
        Parcelable readParcelable = parcel.readParcelable(classLoader);
        this.a = readParcelable == null ? b : readParcelable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbsSavedState(@android.support.annotation.af Parcelable parcelable) {
        if (parcelable == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        this.a = parcelable == b ? null : parcelable;
    }

    @android.support.annotation.ag
    public final Parcelable a() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
    }
}
