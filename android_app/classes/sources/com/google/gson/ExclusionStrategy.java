package com.google.gson;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface ExclusionStrategy {
    boolean shouldSkipClass(Class<?> cls);

    boolean shouldSkipField(FieldAttributes fieldAttributes);
}
