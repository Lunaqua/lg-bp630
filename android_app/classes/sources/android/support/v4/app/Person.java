package android.support.v4.app;

import android.app.Person;
import android.os.Bundle;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.ak;
import android.support.annotation.an;
import android.support.v4.graphics.drawable.IconCompat;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class Person {
    private static final String ICON_KEY = "icon";
    private static final String IS_BOT_KEY = "isBot";
    private static final String IS_IMPORTANT_KEY = "isImportant";
    private static final String KEY_KEY = "key";
    private static final String NAME_KEY = "name";
    private static final String URI_KEY = "uri";
    @ag
    IconCompat mIcon;
    boolean mIsBot;
    boolean mIsImportant;
    @ag
    String mKey;
    @ag
    CharSequence mName;
    @ag
    String mUri;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class Builder {
        @ag
        IconCompat mIcon;
        boolean mIsBot;
        boolean mIsImportant;
        @ag
        String mKey;
        @ag
        CharSequence mName;
        @ag
        String mUri;

        public Builder() {
        }

        Builder(Person person) {
            this.mName = person.mName;
            this.mIcon = person.mIcon;
            this.mUri = person.mUri;
            this.mKey = person.mKey;
            this.mIsBot = person.mIsBot;
            this.mIsImportant = person.mIsImportant;
        }

        @af
        public Person build() {
            return new Person(this);
        }

        @af
        public Builder setBot(boolean z) {
            this.mIsBot = z;
            return this;
        }

        @af
        public Builder setIcon(@ag IconCompat iconCompat) {
            this.mIcon = iconCompat;
            return this;
        }

        @af
        public Builder setImportant(boolean z) {
            this.mIsImportant = z;
            return this;
        }

        @af
        public Builder setKey(@ag String str) {
            this.mKey = str;
            return this;
        }

        @af
        public Builder setName(@ag CharSequence charSequence) {
            this.mName = charSequence;
            return this;
        }

        @af
        public Builder setUri(@ag String str) {
            this.mUri = str;
            return this;
        }
    }

    Person(Builder builder) {
        this.mName = builder.mName;
        this.mIcon = builder.mIcon;
        this.mUri = builder.mUri;
        this.mKey = builder.mKey;
        this.mIsBot = builder.mIsBot;
        this.mIsImportant = builder.mIsImportant;
    }

    @ak(a = 28)
    @af
    @an(a = {an.a.LIBRARY_GROUP})
    public static Person fromAndroidPerson(@af android.app.Person person) {
        return new Builder().setName(person.getName()).setIcon(person.getIcon() != null ? IconCompat.a(person.getIcon()) : null).setUri(person.getUri()).setKey(person.getKey()).setBot(person.isBot()).setImportant(person.isImportant()).build();
    }

    @af
    public static Person fromBundle(@af Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(ICON_KEY);
        return new Builder().setName(bundle.getCharSequence("name")).setIcon(bundle2 != null ? IconCompat.a(bundle2) : null).setUri(bundle.getString(URI_KEY)).setKey(bundle.getString(KEY_KEY)).setBot(bundle.getBoolean(IS_BOT_KEY)).setImportant(bundle.getBoolean(IS_IMPORTANT_KEY)).build();
    }

    @ag
    public IconCompat getIcon() {
        return this.mIcon;
    }

    @ag
    public String getKey() {
        return this.mKey;
    }

    @ag
    public CharSequence getName() {
        return this.mName;
    }

    @ag
    public String getUri() {
        return this.mUri;
    }

    public boolean isBot() {
        return this.mIsBot;
    }

    public boolean isImportant() {
        return this.mIsImportant;
    }

    @ak(a = 28)
    @af
    @an(a = {an.a.LIBRARY_GROUP})
    public android.app.Person toAndroidPerson() {
        return new Person.Builder().setName(getName()).setIcon(getIcon() != null ? getIcon().e() : null).setUri(getUri()).setKey(getKey()).setBot(isBot()).setImportant(isImportant()).build();
    }

    @af
    public Builder toBuilder() {
        return new Builder(this);
    }

    @af
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("name", this.mName);
        IconCompat iconCompat = this.mIcon;
        bundle.putBundle(ICON_KEY, iconCompat != null ? iconCompat.f() : null);
        bundle.putString(URI_KEY, this.mUri);
        bundle.putString(KEY_KEY, this.mKey);
        bundle.putBoolean(IS_BOT_KEY, this.mIsBot);
        bundle.putBoolean(IS_IMPORTANT_KEY, this.mIsImportant);
        return bundle;
    }
}
