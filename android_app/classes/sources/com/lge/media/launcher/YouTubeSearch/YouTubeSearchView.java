package com.lge.media.launcher.YouTubeSearch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.gson.Gson;
import com.lge.media.launcher.YouTubeSearch.YouTubeAutoSearchListAdapter;
import com.lge.media.launcher.YouTubeSearch.YoutubeSearchOptionDialog;
import com.lge.media.launcher.YouTubeSearch.items.Item;
import com.lge.media.launcher.YouTubeSearch.items.Items;
import com.lge.media.launcher.a;
import com.lge.media.launcher.b;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class YouTubeSearchView extends LinearLayout {
    private Button backButton;
    private Button btnClear;
    private Button btnMenu;
    private YoutubeCallback callback;
    private Context context;
    InputMethodManager inputMethod;
    private boolean isLoading;
    private Gson mGson;
    private String mNextPageToken;
    private YoutubeSearchOptionDialog optionDialog;
    private ProgressBar progressBar;
    private String searchText;
    private EditText searchView;
    private int sortOption;
    private ListView youTubeAutoSearchList;
    private YouTubeAutoSearchListAdapter youTubeAutoSearchListAdapter;
    private ListView youTubeList;
    private YouTubeListAdapter youTubeListAdapter;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface YoutubeCallback {
        void backButtonClickListener();

        void onNetworkError();

        void onNoResultFound();
    }

    public YouTubeSearchView(Context context) {
        super(context);
        this.searchText = a.d;
        this.mNextPageToken = a.d;
        this.isLoading = false;
        this.sortOption = 0;
        this.mGson = new Gson();
        this.context = context;
        initView();
    }

    public YouTubeSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.searchText = a.d;
        this.mNextPageToken = a.d;
        this.isLoading = false;
        this.sortOption = 0;
        this.mGson = new Gson();
        this.context = context;
        initView();
    }

    @TargetApi(11)
    public YouTubeSearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.searchText = a.d;
        this.mNextPageToken = a.d;
        this.isLoading = false;
        this.sortOption = 0;
        this.mGson = new Gson();
        this.context = context;
        initView();
    }

    private void clearList() {
        this.youTubeListAdapter.clearData();
        this.youTubeAutoSearchListAdapter.clearData();
        this.searchView.setText(a.d);
        this.sortOption = 0;
        showAutoSearchList();
        YoutubeSearchOptionDialog youtubeSearchOptionDialog = this.optionDialog;
        if (youtubeSearchOptionDialog != null) {
            youtubeSearchOptionDialog.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSortType(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "relevance" : "rating" : "viewCount" : "date" : "relevance";
    }

    private void initAutoSearchListView() {
        this.youTubeAutoSearchListAdapter = new YouTubeAutoSearchListAdapter(this.context, new ArrayList(), new YouTubeAutoSearchListAdapter.OnAutoCommitCallback() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.1
            @Override // com.lge.media.launcher.YouTubeSearch.YouTubeAutoSearchListAdapter.OnAutoCommitCallback
            public void onAutoCommitClicked(String str) {
                if (YouTubeSearchView.this.searchView != null) {
                    YouTubeSearchView.this.searchView.setText(str);
                    YouTubeSearchView.this.searchView.setSelection(YouTubeSearchView.this.searchView.length());
                }
            }
        });
        this.youTubeAutoSearchList.setAdapter((ListAdapter) this.youTubeAutoSearchListAdapter);
        this.youTubeAutoSearchList.setOnTouchListener(new View.OnTouchListener() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (YouTubeSearchView.this.inputMethod.isActive()) {
                    YouTubeSearchView.this.inputMethod.hideSoftInputFromWindow(YouTubeSearchView.this.searchView.getWindowToken(), 0);
                }
                return false;
            }
        });
        this.youTubeAutoSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.3
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String item = YouTubeSearchView.this.youTubeAutoSearchListAdapter.getItem(i);
                if (a.d.equals(item)) {
                    return;
                }
                YouTubeSearchView.this.searchText = item;
                YouTubeSearchView.this.searchView.setText(item);
                YouTubeSearchView.this.searchView.setSelection(YouTubeSearchView.this.searchView.length());
                if (YouTubeSearchView.this.inputMethod.isActive()) {
                    YouTubeSearchView.this.inputMethod.hideSoftInputFromWindow(YouTubeSearchView.this.searchView.getWindowToken(), 0);
                }
                YouTubeSearchView.this.sortOption = 0;
                if (YouTubeSearchView.this.optionDialog != null) {
                    YouTubeSearchView.this.optionDialog.clear();
                }
                YouTubeSearchView.this.youTubeAutoSearchListAdapter.saveSearchList(YouTubeSearchView.this.searchText);
                YouTubeSearchView youTubeSearchView = YouTubeSearchView.this;
                String str = youTubeSearchView.searchText;
                YouTubeSearchView youTubeSearchView2 = YouTubeSearchView.this;
                youTubeSearchView.requestSearch(str, a.d, youTubeSearchView2.getSortType(youTubeSearchView2.sortOption));
            }
        });
    }

    private void initResultListView() {
        this.youTubeListAdapter = new YouTubeListAdapter(this.context, new ArrayList());
        this.youTubeList.setAdapter((ListAdapter) this.youTubeListAdapter);
        this.youTubeList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.4
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Item item = YouTubeSearchView.this.youTubeListAdapter.getItem(i);
                if (!item.isYoutubeData || item == null || item.id == null) {
                    return;
                }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setFlags(268435456);
                intent.setData(Uri.parse("http://www.youtube.com/watch?v=" + item.id));
                YouTubeSearchView.this.context.startActivity(intent);
            }
        });
        this.youTubeList.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.5
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i3 <= 0 || i + i2 != i3) {
                    return;
                }
                com.lge.media.launcher.control.common.a.b("youtube load more pre");
                if (YouTubeSearchView.this.isLoading || TextUtils.isEmpty(YouTubeSearchView.this.mNextPageToken)) {
                    return;
                }
                com.lge.media.launcher.control.common.a.b("youtube load more!!");
                YouTubeSearchView youTubeSearchView = YouTubeSearchView.this;
                String str = youTubeSearchView.searchText;
                String str2 = YouTubeSearchView.this.mNextPageToken;
                YouTubeSearchView youTubeSearchView2 = YouTubeSearchView.this;
                youTubeSearchView.requestLoadMore(str, str2, youTubeSearchView2.getSortType(youTubeSearchView2.sortOption));
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }
        });
        this.youTubeList.setOnTouchListener(new View.OnTouchListener() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (YouTubeSearchView.this.inputMethod.isActive()) {
                    YouTubeSearchView.this.inputMethod.hideSoftInputFromWindow(YouTubeSearchView.this.searchView.getWindowToken(), 0);
                }
                return false;
            }
        });
    }

    private void initSearchHeaderView() {
        Button button;
        int i;
        this.searchView.setOnTouchListener(new View.OnTouchListener() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.7
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                com.lge.media.launcher.control.common.a.b("youtube searchView onTouch " + motionEvent.getAction());
                if (motionEvent.getAction() == 0) {
                    if (YouTubeSearchView.this.youTubeAutoSearchListAdapter != null) {
                        YouTubeSearchView.this.youTubeAutoSearchListAdapter.loadSavedSearchList();
                    }
                    YouTubeSearchView.this.showAutoSearchList();
                    return false;
                }
                return false;
            }
        });
        this.searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.8
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                com.lge.media.launcher.control.common.a.b("youtube searchView onEditorAction " + i2);
                if (i2 == 3 || i2 == 2) {
                    String charSequence = textView.getText().toString();
                    if (!a.d.equals(charSequence)) {
                        YouTubeSearchView.this.searchText = charSequence;
                        if (YouTubeSearchView.this.inputMethod.isActive()) {
                            YouTubeSearchView.this.inputMethod.hideSoftInputFromWindow(YouTubeSearchView.this.searchView.getWindowToken(), 0);
                        }
                        YouTubeSearchView.this.sortOption = 0;
                        if (YouTubeSearchView.this.optionDialog != null) {
                            YouTubeSearchView.this.optionDialog.clear();
                        }
                        YouTubeSearchView.this.youTubeAutoSearchListAdapter.saveSearchList(YouTubeSearchView.this.searchText);
                        YouTubeSearchView youTubeSearchView = YouTubeSearchView.this;
                        String str = youTubeSearchView.searchText;
                        YouTubeSearchView youTubeSearchView2 = YouTubeSearchView.this;
                        youTubeSearchView.requestSearch(str, a.d, youTubeSearchView2.getSortType(youTubeSearchView2.sortOption));
                    }
                }
                return false;
            }
        });
        this.searchView.addTextChangedListener(new TextWatcher() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.9
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                Button button2;
                int i5;
                if (charSequence == null || charSequence.length() <= 0) {
                    button2 = YouTubeSearchView.this.btnClear;
                    i5 = 8;
                } else {
                    button2 = YouTubeSearchView.this.btnClear;
                    i5 = 0;
                }
                button2.setVisibility(i5);
            }
        });
        this.searchView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.10
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                StringBuilder sb;
                CharSequence hint;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                if (YouTubeSearchView.this.searchView.getText().length() > 0) {
                    sb = new StringBuilder();
                    sb.append(YouTubeSearchView.this.context.getString(b.m.label_youtube_search));
                    sb.append(", ");
                    hint = YouTubeSearchView.this.searchView.getText();
                } else {
                    sb = new StringBuilder();
                    sb.append(YouTubeSearchView.this.context.getString(b.m.label_youtube_search));
                    sb.append(", ");
                    hint = YouTubeSearchView.this.searchView.getHint();
                }
                sb.append((Object) hint);
                accessibilityNodeInfo.setText(sb.toString());
            }
        });
        this.backButton.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (YouTubeSearchView.this.inputMethod.isActive()) {
                    YouTubeSearchView.this.inputMethod.hideSoftInputFromWindow(YouTubeSearchView.this.searchView.getWindowToken(), 0);
                }
                if (YouTubeSearchView.this.callback != null) {
                    YouTubeSearchView.this.callback.backButtonClickListener();
                }
            }
        });
        EditText editText = this.searchView;
        if (editText == null || editText.getText().length() <= 0) {
            button = this.btnClear;
            i = 8;
        } else {
            button = this.btnClear;
            i = 0;
        }
        button.setVisibility(i);
        this.btnClear.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (YouTubeSearchView.this.searchView != null) {
                    YouTubeSearchView.this.searchView.setText(a.d);
                    YouTubeSearchView.this.showAutoSearchList();
                }
            }
        });
        this.btnMenu.setOnClickListener(new View.OnClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                YouTubeSearchView.this.showOptionDialog();
            }
        });
    }

    private void initView() {
        this.inputMethod = (InputMethodManager) this.context.getSystemService("input_method");
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(b.j.youtube_search, (ViewGroup) null);
        inflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        addView(inflate);
        this.youTubeAutoSearchList = (ListView) findViewById(b.h.list_youtube_auto_search);
        this.youTubeList = (ListView) findViewById(b.h.list_youtube);
        this.progressBar = (ProgressBar) findViewById(b.h.loading_progress);
        this.searchView = (EditText) findViewById(b.h.et_search);
        this.backButton = (Button) findViewById(b.h.btn_youtube_back);
        this.btnClear = (Button) findViewById(b.h.btn_et_clear);
        this.btnMenu = (Button) findViewById(b.h.btn_youtube_detail_search);
        initAutoSearchListView();
        initResultListView();
        initSearchHeaderView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isShowAutoSearchList() {
        return this.youTubeAutoSearchList.getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestLoadMore(String str, String str2, String str3) {
        this.isLoading = true;
        YouTubeRequestManager.getInstance().request(str, str2, str3, new RequestCallBack() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.15
            @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
            public void onComplete(JSONObject jSONObject) {
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray("items");
                    if (jSONArray.length() == 0) {
                        onFail("totalItems is 0");
                        return;
                    }
                    YouTubeSearchView.this.mNextPageToken = jSONObject.getString("nextPageToken");
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i).getJSONObject("id");
                        sb.append(jSONObject2.getString("videoId") + ",");
                    }
                    YouTubeRequestManager.getInstance().request(sb.toString(), new RequestCallBack() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.15.1
                        @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
                        public void onComplete(JSONObject jSONObject3) {
                            try {
                                Items items = (Items) YouTubeSearchView.this.mGson.fromJson(jSONObject3.toString(), (Class<Object>) Items.class);
                                if (!TextUtils.isEmpty(YouTubeSearchView.this.mNextPageToken)) {
                                    Item item = new Item();
                                    item.isYoutubeData = false;
                                    items.items.add(item);
                                }
                                YouTubeSearchView.this.youTubeListAdapter.addData(items.items);
                                YouTubeSearchView.this.youTubeListAdapter.notifyDataSetChanged();
                                if (!YouTubeSearchView.this.isShowAutoSearchList()) {
                                    YouTubeSearchView.this.showList();
                                }
                                YouTubeSearchView.this.isLoading = false;
                            } catch (Exception e) {
                                e.printStackTrace();
                                YouTubeSearchView.this.showNoData();
                            }
                        }

                        @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
                        public void onError(String str4, Exception exc) {
                            com.lge.media.launcher.control.common.a.b("requestSearch error : " + str4);
                            YouTubeSearchView.this.showNetworkError();
                        }

                        @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
                        public void onFail(String str4) {
                            com.lge.media.launcher.control.common.a.b("requestSearch onFail : " + str4);
                            YouTubeSearchView.this.youTubeListAdapter.removeProgress();
                            YouTubeSearchView.this.youTubeListAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    YouTubeSearchView.this.showNoData();
                }
            }

            @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
            public void onError(String str4, Exception exc) {
                YouTubeSearchView.this.showNetworkError();
            }

            @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
            public void onFail(String str4) {
                com.lge.media.launcher.control.common.a.b("requestSearch onFail : " + str4);
                YouTubeSearchView.this.youTubeListAdapter.removeProgress();
                YouTubeSearchView.this.youTubeListAdapter.notifyDataSetChanged();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestSearch(String str, String str2, String str3) {
        this.isLoading = true;
        showProgress();
        YouTubeRequestManager.getInstance().request(str, str2, str3, new RequestCallBack() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.14
            @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
            public void onComplete(JSONObject jSONObject) {
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray("items");
                    if (jSONArray.length() == 0) {
                        onFail("totalItems is 0");
                        return;
                    }
                    YouTubeSearchView.this.mNextPageToken = jSONObject.getString("nextPageToken");
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i).getJSONObject("id");
                        sb.append(jSONObject2.getString("videoId") + ",");
                    }
                    YouTubeRequestManager.getInstance().request(sb.toString(), new RequestCallBack() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.14.1
                        @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
                        public void onComplete(JSONObject jSONObject3) {
                            try {
                                Items items = (Items) YouTubeSearchView.this.mGson.fromJson(jSONObject3.toString(), (Class<Object>) Items.class);
                                if (!TextUtils.isEmpty(YouTubeSearchView.this.mNextPageToken)) {
                                    Item item = new Item();
                                    item.isYoutubeData = false;
                                    items.items.add(item);
                                }
                                YouTubeSearchView.this.youTubeList.requestFocusFromTouch();
                                YouTubeSearchView.this.youTubeListAdapter.setData(items.items);
                                YouTubeSearchView.this.youTubeListAdapter.notifyDataSetChanged();
                                YouTubeSearchView.this.youTubeList.setSelection(0);
                                if (!YouTubeSearchView.this.isShowAutoSearchList()) {
                                    YouTubeSearchView.this.showList();
                                }
                                YouTubeSearchView.this.isLoading = false;
                            } catch (Exception e) {
                                e.printStackTrace();
                                YouTubeSearchView.this.youTubeListAdapter.clearData();
                                YouTubeSearchView.this.youTubeListAdapter.notifyDataSetChanged();
                                YouTubeSearchView.this.showNoData();
                            }
                        }

                        @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
                        public void onError(String str4, Exception exc) {
                            com.lge.media.launcher.control.common.a.b("requestSearch error : " + str4);
                            YouTubeSearchView.this.showNetworkError();
                        }

                        @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
                        public void onFail(String str4) {
                            com.lge.media.launcher.control.common.a.b("requestSearch onFail : " + str4);
                            YouTubeSearchView.this.showNoResultError();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    YouTubeSearchView.this.youTubeListAdapter.clearData();
                    YouTubeSearchView.this.youTubeListAdapter.notifyDataSetChanged();
                    YouTubeSearchView.this.showNoData();
                }
            }

            @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
            public void onError(String str4, Exception exc) {
                com.lge.media.launcher.control.common.a.b("requestSearch error : " + str4);
                YouTubeSearchView.this.showNetworkError();
            }

            @Override // com.lge.media.launcher.YouTubeSearch.RequestCallBack
            public void onFail(String str4) {
                com.lge.media.launcher.control.common.a.b("requestSearch onFail : " + str4);
                YouTubeSearchView.this.showNoResultError();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAutoSearchList() {
        this.progressBar.setVisibility(8);
        this.youTubeList.setVisibility(8);
        this.youTubeAutoSearchList.setVisibility(0);
        this.btnMenu.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showList() {
        this.progressBar.setVisibility(8);
        this.youTubeList.setVisibility(0);
        this.youTubeAutoSearchList.setVisibility(8);
        this.btnMenu.setVisibility(0);
        this.searchView.setText(this.searchText);
        EditText editText = this.searchView;
        editText.setSelection(editText.length());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNetworkError() {
        YoutubeCallback youtubeCallback = this.callback;
        if (youtubeCallback != null) {
            youtubeCallback.onNetworkError();
        }
        this.youTubeListAdapter.clearData();
        this.youTubeListAdapter.notifyDataSetChanged();
        showNoData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoData() {
        this.progressBar.setVisibility(8);
        this.youTubeList.setVisibility(8);
        this.youTubeAutoSearchList.setVisibility(8);
        this.btnMenu.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoResultError() {
        YoutubeCallback youtubeCallback = this.callback;
        if (youtubeCallback != null) {
            youtubeCallback.onNoResultFound();
        }
        this.youTubeListAdapter.clearData();
        this.youTubeListAdapter.notifyDataSetChanged();
        showNoData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOptionDialog() {
        if (this.optionDialog == null) {
            this.optionDialog = new YoutubeSearchOptionDialog(this.context, 16973840);
            this.optionDialog.setListener(new YoutubeSearchOptionDialog.DialogOnClickListener() { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeSearchView.16
                @Override // com.lge.media.launcher.YouTubeSearch.YoutubeSearchOptionDialog.DialogOnClickListener
                public void negativeButtonOnClickListener(int i) {
                }

                @Override // com.lge.media.launcher.YouTubeSearch.YoutubeSearchOptionDialog.DialogOnClickListener
                public void positiveButtonOnClickListener(int i) {
                    if (a.d.equals(YouTubeSearchView.this.searchText)) {
                        return;
                    }
                    YouTubeSearchView.this.sortOption = i;
                    if (YouTubeSearchView.this.inputMethod.isActive()) {
                        YouTubeSearchView.this.inputMethod.hideSoftInputFromWindow(YouTubeSearchView.this.searchView.getWindowToken(), 0);
                    }
                    YouTubeSearchView youTubeSearchView = YouTubeSearchView.this;
                    String str = youTubeSearchView.searchText;
                    YouTubeSearchView youTubeSearchView2 = YouTubeSearchView.this;
                    youTubeSearchView.requestSearch(str, a.d, youTubeSearchView2.getSortType(youTubeSearchView2.sortOption));
                }
            });
        }
        this.optionDialog.show(this.sortOption);
    }

    private void showProgress() {
        this.progressBar.setVisibility(0);
        this.youTubeList.setVisibility(8);
        this.youTubeAutoSearchList.setVisibility(8);
        this.btnMenu.setVisibility(8);
    }

    public void bindView() {
        clearList();
        YouTubeAutoSearchListAdapter youTubeAutoSearchListAdapter = this.youTubeAutoSearchListAdapter;
        if (youTubeAutoSearchListAdapter != null) {
            youTubeAutoSearchListAdapter.loadSavedSearchList();
        }
        showAutoSearchList();
    }

    public boolean onBackPressed() {
        boolean isShowAutoSearchList = isShowAutoSearchList();
        YouTubeListAdapter youTubeListAdapter = this.youTubeListAdapter;
        if ((youTubeListAdapter == null || youTubeListAdapter.getCount() != 0) && isShowAutoSearchList) {
            showList();
            return true;
        }
        return false;
    }

    public void setCallback(YoutubeCallback youtubeCallback) {
        this.callback = youtubeCallback;
    }
}
