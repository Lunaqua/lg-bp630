package android.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
@Retention(RetentionPolicy.CLASS)
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public @interface an {

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public enum a {
        LIBRARY,
        LIBRARY_GROUP,
        GROUP_ID,
        TESTS,
        SUBCLASSES
    }

    a[] a();
}
