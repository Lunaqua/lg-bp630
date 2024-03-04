package android.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public @interface w {
    int[] a() default {};

    boolean b() default false;
}
