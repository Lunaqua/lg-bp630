package com.google.gson;

import com.google.gson.reflect.TypeToken;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface TypeAdapterFactory {
    <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken);
}
