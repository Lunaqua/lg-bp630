package com.lge.media.launcher.YouTubeSearch;

import android.text.TextUtils;
import com.lge.media.launcher.a;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.client.CookieStore;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class YouTubeRequestManager {
    private static final String API_KEY = "AIzaSyBDFtosUSJiagr4PUtdQ9_wGy8zNo7uS6I";
    public static final int DEFAULT_CONNECTION_TIME_OUT = 15000;
    public static final int DEFAULT_SOCKET_TIME_OUT = 20000;
    private static int NETWORK_POOL = 5;
    public static final int PER_PAGE = 20;
    private static final String REQUEST_METHOD_API_KEY = "&key=AIzaSyBDFtosUSJiagr4PUtdQ9_wGy8zNo7uS6I";
    public static final String REQUEST_METHOD_ID = "&id=";
    public static final String REQUEST_METHOD_KEYWORD = "&q=";
    public static final String REQUEST_METHOD_MAX_RESULT = "&maxResults=20";
    public static final String REQUEST_METHOD_ORDER_BY = "&order=";
    public static final String REQUEST_METHOD_PAGE_TOKEN = "&pageToken=";
    public static final String REQUEST_METHOD_TYPE = "&type=video";
    public static final String URL = "https://www.googleapis.com/youtube/v3/search?part=snippet";
    public static final String VIEW_COUNT_URL = "https://www.googleapis.com/youtube/v3/videos?part=snippet,statistics";
    private static YouTubeRequestManager manager;
    private DefaultHttpClient client = null;

    private DefaultHttpClient getHttpClient() {
        CookieStore cookieStore;
        DefaultHttpClient defaultHttpClient = this.client;
        if (defaultHttpClient != null) {
            defaultHttpClient.getConnectionManager().closeExpiredConnections();
            cookieStore = this.client.getCookieStore();
        } else {
            cookieStore = null;
        }
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, DEFAULT_CONNECTION_TIME_OUT);
        HttpConnectionParams.setSoTimeout(basicHttpParams, DEFAULT_SOCKET_TIME_OUT);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(NETWORK_POOL));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, NETWORK_POOL);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, DEFAULT_SOCKET_TIME_OUT);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        this.client = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        if (cookieStore != null) {
            this.client.setCookieStore(cookieStore);
        }
        return this.client;
    }

    public static YouTubeRequestManager getInstance() {
        if (manager == null) {
            manager = new YouTubeRequestManager();
        }
        return manager;
    }

    public void request(String str, RequestCallBack requestCallBack) {
        StringBuilder sb = new StringBuilder(a.d);
        sb.append(VIEW_COUNT_URL);
        sb.append(REQUEST_METHOD_ID + str);
        sb.append(REQUEST_METHOD_API_KEY);
        new HttpAction(getHttpClient(), sb.toString(), requestCallBack).execute(a.d);
    }

    public void request(String str, String str2, String str3, RequestCallBack requestCallBack) {
        try {
            StringBuilder sb = new StringBuilder(a.d);
            sb.append(URL);
            sb.append(REQUEST_METHOD_MAX_RESULT);
            sb.append(REQUEST_METHOD_ORDER_BY + str3);
            if (!TextUtils.isEmpty(str2)) {
                sb.append(REQUEST_METHOD_PAGE_TOKEN + str2);
            }
            sb.append(REQUEST_METHOD_KEYWORD + URLEncoder.encode(str, "utf-8"));
            sb.append(REQUEST_METHOD_TYPE);
            sb.append(REQUEST_METHOD_API_KEY);
            new HttpAction(getHttpClient(), sb.toString(), requestCallBack).execute(a.d);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
