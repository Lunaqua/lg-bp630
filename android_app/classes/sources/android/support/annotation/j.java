package android.support.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public @interface j {
    String a() default "";
}
