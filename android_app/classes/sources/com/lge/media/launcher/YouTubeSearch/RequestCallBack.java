package com.lge.media.launcher.YouTubeSearch;

import org.json.JSONObject;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public interface RequestCallBack {
    void onComplete(JSONObject jSONObject);

    void onError(String str, Exception exc);

    void onFail(String str);
}
