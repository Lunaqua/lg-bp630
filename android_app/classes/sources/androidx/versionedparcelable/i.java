package androidx.versionedparcelable;

import android.support.annotation.an;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
@an(a = {an.a.LIBRARY_GROUP})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public @interface i {
    boolean a() default false;

    boolean b() default false;

    boolean c() default false;

    int[] d() default {};

    String e() default "";
}
