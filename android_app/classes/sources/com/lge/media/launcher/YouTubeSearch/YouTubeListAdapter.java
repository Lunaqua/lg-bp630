package com.lge.media.launcher.YouTubeSearch;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lge.media.launcher.YouTubeSearch.items.Item;
import com.lge.media.launcher.b;
import com.lge.media.launcher.control.common.a;
import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class YouTubeListAdapter extends BaseAdapter {
    private static final int LOW_VERSION_CACHE_SIZE = 90;
    int densityDpi;
    private Context mContext;
    private ArrayList<Item> mData;
    private HashMap<String, Bitmap> mLowVersionMemoryCache;
    private LruCache<String, Bitmap> mMemoryCache;
    private HashMap<String, Boolean> mScheduleMap;
    private final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private final int cacheSize = this.maxMemory / 8;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private String id;
        private int position;
        private String thumbURL;

        public ImageAsyncTask(String str, String str2) {
            this.id = str;
            this.thumbURL = str2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(String... strArr) {
            Bitmap bitmap = null;
            try {
                URLConnection openConnection = new URL(this.thumbURL).openConnection();
                openConnection.connect();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
                bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                bufferedInputStream.close();
                return bitmap;
            } catch (Exception e) {
                a.b("[image] load image fail " + e.getMessage());
                YouTubeListAdapter.this.mScheduleMap.remove(this.id);
                return bitmap;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            super.onPostExecute((ImageAsyncTask) bitmap);
            a.b("[image] load image end" + this.position);
            if (bitmap == null) {
                a.b("[image] load image fail result null ");
                return;
            }
            YouTubeListAdapter.this.addBitmapToMemoryCache(this.id, bitmap);
            YouTubeListAdapter.this.mScheduleMap.remove(this.id);
            YouTubeListAdapter.this.notifyDataSetChanged();
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            a.b("[image] load image " + this.position);
            YouTubeListAdapter.this.mScheduleMap.put(this.id, true);
            super.onPreExecute();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class ViewHolder {
        RelativeLayout mNextLayout;
        ImageView mThumb;
        TextView mTime;
        TextView mTitle;
        TextView mUploader;
        TextView mViewCount;
        LinearLayout mYoutubeLayout;

        private ViewHolder() {
        }
    }

    public YouTubeListAdapter(Context context, ArrayList<Item> arrayList) {
        this.mContext = context;
        this.densityDpi = (int) (context.getResources().getDisplayMetrics().density * 160.0f);
        this.mData = arrayList;
        if (Build.VERSION.SDK_INT >= 12) {
            this.mMemoryCache = new LruCache<String, Bitmap>(this.cacheSize) { // from class: com.lge.media.launcher.YouTubeSearch.YouTubeListAdapter.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.util.LruCache
                @TargetApi(12)
                public int sizeOf(String str, Bitmap bitmap) {
                    return bitmap.getByteCount() / 1024;
                }
            };
        } else {
            this.mLowVersionMemoryCache = new HashMap<>();
        }
        this.mScheduleMap = new HashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBitmapToMemoryCache(String str, Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            if (getBitmapFromMemCache(str) == null) {
                this.mMemoryCache.put(str, bitmap);
                return;
            }
            return;
        }
        if (this.mLowVersionMemoryCache.size() > 90) {
            a.b("[image] low device version cache clear");
            this.mLowVersionMemoryCache.clear();
            notifyDataSetChanged();
        }
        if (getBitmapFromMemCache(str) == null) {
            this.mLowVersionMemoryCache.put(str, bitmap);
        }
    }

    private Bitmap getBitmapFromMemCache(String str) {
        if (str != null) {
            return Build.VERSION.SDK_INT >= 12 ? this.mMemoryCache.get(str) : this.mLowVersionMemoryCache.get(str);
        }
        return null;
    }

    private void setNextItemView(ViewHolder viewHolder, View view) {
        viewHolder.mYoutubeLayout.setVisibility(8);
        viewHolder.mNextLayout.setVisibility(0);
    }

    private void setYouTubeView(ViewHolder viewHolder, int i) {
        LinearLayout linearLayout;
        int i2;
        viewHolder.mYoutubeLayout.setVisibility(0);
        viewHolder.mNextLayout.setVisibility(8);
        viewHolder.mThumb.setImageBitmap(null);
        if (this.mData.get(i) != null) {
            viewHolder.mTitle.setText(this.mData.get(i).snippet.title);
            viewHolder.mUploader.setText(this.mData.get(i).snippet.channelTitle);
            if (!TextUtils.isEmpty(this.mData.get(i).statistics.viewCount) && !"0".equals(this.mData.get(i).statistics.viewCount)) {
                viewHolder.mViewCount.setText(String.format(this.mContext.getResources().getString(b.m.youtube_view_count), this.mData.get(i).statistics.viewCount));
            }
            String str = this.densityDpi >= 320 ? this.mData.get(i).snippet.thumbnails.high.url : this.mData.get(i).snippet.thumbnails.medium.url;
            if (getBitmapFromMemCache(this.mData.get(i).id) != null || this.mScheduleMap.get(this.mData.get(i).id) != null) {
                viewHolder.mThumb.setImageBitmap(getBitmapFromMemCache(this.mData.get(i).id));
            } else if (Build.VERSION.SDK_INT >= 12) {
                new ImageAsyncTask(this.mData.get(i).id, str).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
            } else {
                new ImageAsyncTask(this.mData.get(i).id, str).execute(new String[0]);
            }
            if (i == 0 || i % 2 == 0) {
                linearLayout = viewHolder.mYoutubeLayout;
                i2 = b.g.selector_youtube_list_item;
            } else {
                linearLayout = viewHolder.mYoutubeLayout;
                i2 = b.g.selector_youtube_list_item_2;
            }
            linearLayout.setBackgroundResource(i2);
        }
    }

    public void addData(ArrayList<Item> arrayList) {
        ArrayList<Item> arrayList2 = new ArrayList<>(this.mData);
        arrayList2.remove(this.mData.size() - 1);
        arrayList2.addAll(arrayList);
        this.mData.clear();
        this.mData = arrayList2;
    }

    public void clearData() {
        this.mData.clear();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mData.size();
    }

    public ArrayList<Item> getData() {
        return this.mData;
    }

    @Override // android.widget.Adapter
    public Item getItem(int i) {
        return this.mData.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ArrayList<Item> arrayList = this.mData;
        if (arrayList != null && arrayList.size() != 0) {
            if (view != null) {
                viewHolder = (ViewHolder) view.getTag();
            } else {
                view = LayoutInflater.from(this.mContext).inflate(b.j.youtube_card, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.mTitle = (TextView) view.findViewById(b.h.tv_youtube_title);
                viewHolder.mUploader = (TextView) view.findViewById(b.h.tv_youtube_uploader);
                viewHolder.mTime = (TextView) view.findViewById(b.h.tv_youtube_time);
                viewHolder.mViewCount = (TextView) view.findViewById(b.h.tv_youtube_view_count);
                viewHolder.mThumb = (ImageView) view.findViewById(b.h.iv_youtube_thumb);
                viewHolder.mYoutubeLayout = (LinearLayout) view.findViewById(b.h.layout_youtube_data);
                viewHolder.mNextLayout = (RelativeLayout) view.findViewById(b.h.layout_next_item);
                view.setTag(viewHolder);
            }
            if (this.mData.get(i).isYoutubeData) {
                setYouTubeView(viewHolder, i);
            } else {
                setNextItemView(viewHolder, view);
            }
        }
        return view;
    }

    public void removeProgress() {
        ArrayList<Item> arrayList = this.mData;
        arrayList.remove(arrayList.size() - 1);
    }

    public void setData(ArrayList<Item> arrayList) {
        this.mData.clear();
        this.mData = arrayList;
    }
}
