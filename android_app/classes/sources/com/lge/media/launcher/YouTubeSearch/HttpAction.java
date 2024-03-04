package com.lge.media.launcher.YouTubeSearch;

import android.os.AsyncTask;
import com.lge.media.launcher.control.common.a;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class HttpAction extends AsyncTask<String, Integer, String> {
    private RequestCallBack callBack;
    private HttpClient client;
    private String url;

    public HttpAction(DefaultHttpClient defaultHttpClient, String str, RequestCallBack requestCallBack) {
        this.client = defaultHttpClient;
        this.callBack = requestCallBack;
        this.url = str;
    }

    private String getStringFromHttpResponse(HttpResponse httpResponse) {
        if (httpResponse == null || httpResponse.getEntity() == null) {
            a.b("[youtube] response.getEntity() is null" + httpResponse);
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "utf-8"), 2048);
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return stringBuffer.toString();
            }
            stringBuffer.append(readLine);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        a.b("[youtube] reqeust  : " + this.url);
        try {
            HttpResponse execute = this.client.execute(new HttpGet(this.url));
            if (execute == null) {
                a.b("[youtube] response is null");
                throw new Exception("response is null");
            }
            String stringFromHttpResponse = getStringFromHttpResponse(execute);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return stringFromHttpResponse;
            }
            throw new HttpResponseException(execute.getStatusLine().getStatusCode(), stringFromHttpResponse);
        } catch (Exception e) {
            a.b("[youtube] error  : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((HttpAction) str);
        try {
            a.b("[youtube] result  : " + str);
            if (str == null) {
                a.b("[youtube] result : connection failed ");
                this.callBack.onError("connection failed", new Exception("connection failed"));
            } else {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObject2 = jSONObject.getJSONObject("pageInfo");
                if (jSONObject.getJSONArray("items").length() != 0 && jSONObject2.getInt("totalResults") != 0) {
                    a.b("[youtube] result : success ");
                    this.callBack.onComplete(jSONObject);
                }
                a.b("[youtube] result : No results found ");
                this.callBack.onFail("No results found");
            }
        } catch (Exception e) {
            if ((e instanceof JSONException) && e.getMessage() != null && e.getMessage().contains("entry")) {
                a.b("[youtube] result. exception : No results found ");
                this.callBack.onFail("No results found");
                return;
            }
            a.b("[youtube] result. exception : error");
            this.callBack.onError(e.getMessage(), e);
        }
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
