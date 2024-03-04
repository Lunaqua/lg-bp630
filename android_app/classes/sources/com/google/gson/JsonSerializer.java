package com.google.gson;

import java.lang.reflect.Type;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface JsonSerializer<T> {
    JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext);
}
