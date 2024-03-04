package com.google.gson.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public @interface JsonAdapter {
    Class<?> value();
}
