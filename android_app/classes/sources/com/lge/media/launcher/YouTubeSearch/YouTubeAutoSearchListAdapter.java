package com.lge.media.launcher.YouTubeSearch;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lge.media.launcher.a;
import com.lge.media.launcher.b;
import java.util.ArrayList;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class YouTubeAutoSearchListAdapter extends BaseAdapter {
    private static final String KEY_SAVE_YOUTUBE_LIST = "youtube_list";
    private static final int MAX_SAVE_SIZE = 6;
    private static final String PREFERENCE_NAME = "com.lge.media.launcher";
    private OnAutoCommitCallback callback;
    private Context mContext;
    private ArrayList<String> mData;
    private View.OnClickListener onclickListener = new AutoOnClickListener();

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class AutoOnClickListener implements View.OnClickListener {
        private AutoOnClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str = (String) view.getTag(b.h.layout_auto_commit);
            if (YouTubeAutoSearchListAdapter.this.callback != null) {
                YouTubeAutoSearchListAdapter.this.callback.onAutoCommitClicked(str);
            }
        }
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface OnAutoCommitCallback {
        void onAutoCommitClicked(String str);
    }

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    private class ViewHolder {
        LinearLayout mAutoCommit;
        TextView mTitle;

        private ViewHolder() {
        }
    }

    public YouTubeAutoSearchListAdapter(Context context, ArrayList<String> arrayList, OnAutoCommitCallback onAutoCommitCallback) {
        this.mContext = context;
        this.mData = arrayList;
        this.callback = onAutoCommitCallback;
    }

    public void clearData() {
        this.mData.clear();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mData.size();
    }

    @Override // android.widget.Adapter
    public String getItem(int i) {
        return this.mData.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(this.mContext).inflate(b.j.youtube_auto_search_card, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.mTitle = (TextView) view.findViewById(b.h.tv_youtube_title);
            viewHolder.mAutoCommit = (LinearLayout) view.findViewById(b.h.layout_auto_commit);
            view.setTag(viewHolder);
        }
        viewHolder.mTitle.setText(this.mData.get(i));
        if (viewHolder.mAutoCommit != null) {
            viewHolder.mAutoCommit.setTag(b.h.layout_auto_commit, this.mData.get(i));
            viewHolder.mAutoCommit.setOnClickListener(this.onclickListener);
        }
        return view;
    }

    public void loadSavedSearchList() {
        String string = this.mContext.getSharedPreferences("com.lge.media.launcher", 0).getString(KEY_SAVE_YOUTUBE_LIST, a.d);
        ArrayList<String> arrayList = new ArrayList<>();
        String[] split = string.split("\n");
        int length = split.length;
        for (int i = 0; i < length; i++) {
            if (!a.d.equals(split[i]) && !"\n".equals(split[i]) && split[i] != null) {
                arrayList.add(split[i]);
                com.lge.media.launcher.control.common.a.b("searchedListString : " + split[i]);
            }
        }
        setData(arrayList);
        notifyDataSetChanged();
    }

    public void saveSearchList(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mData);
        String str2 = new String();
        int i = 0;
        while (true) {
            if (i >= arrayList.size()) {
                break;
            }
            if (arrayList.get(i) != null && ((String) arrayList.get(i)).equals(str)) {
                arrayList.remove(i);
                break;
            }
            i++;
        }
        arrayList.add(0, str);
        int size = arrayList.size() <= 6 ? arrayList.size() : 6;
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 != 0) {
                str2 = str2 + "\n";
            }
            str2 = str2 + ((String) arrayList.get(i2));
            com.lge.media.launcher.control.common.a.b("search save : " + ((String) arrayList.get(i2)));
        }
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences("com.lge.media.launcher", 0).edit();
        edit.putString(KEY_SAVE_YOUTUBE_LIST, str2);
        edit.commit();
    }

    public void setData(ArrayList<String> arrayList) {
        this.mData.clear();
        this.mData = arrayList;
    }
}
