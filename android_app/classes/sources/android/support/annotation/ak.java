package android.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
@Retention(RetentionPolicy.CLASS)
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public @interface ak {
    @x(a = 1)
    int a() default 1;

    @x(a = 1)
    int b() default 1;
}
