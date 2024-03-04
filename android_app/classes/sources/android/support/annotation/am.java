package android.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.CLASS)
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public @interface am {

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface a {
        am a() default @am;
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface b {
        am a() default @am;
    }

    String a() default "";

    String[] b() default {};

    String[] c() default {};

    boolean d() default false;
}
