package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.af;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class g {
    static final int a = 1;
    private static final String b = "LocalBroadcastManager";
    private static final boolean c = false;
    private static final Object i = new Object();
    private static g j;
    private final Context d;
    private final HashMap<BroadcastReceiver, ArrayList<b>> e = new HashMap<>();
    private final HashMap<String, ArrayList<b>> f = new HashMap<>();
    private final ArrayList<a> g = new ArrayList<>();
    private final Handler h;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class a {
        final Intent a;
        final ArrayList<b> b;

        a(Intent intent, ArrayList<b> arrayList) {
            this.a = intent;
            this.b = arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static final class b {
        final IntentFilter a;
        final BroadcastReceiver b;
        boolean c;
        boolean d;

        b(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.a = intentFilter;
            this.b = broadcastReceiver;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.b);
            sb.append(" filter=");
            sb.append(this.a);
            if (this.d) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    private g(Context context) {
        this.d = context;
        this.h = new Handler(context.getMainLooper()) { // from class: android.support.v4.content.g.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    super.handleMessage(message);
                } else {
                    g.this.a();
                }
            }
        };
    }

    @af
    public static g a(@af Context context) {
        g gVar;
        synchronized (i) {
            if (j == null) {
                j = new g(context.getApplicationContext());
            }
            gVar = j;
        }
        return gVar;
    }

    void a() {
        a[] aVarArr;
        while (true) {
            synchronized (this.e) {
                int size = this.g.size();
                if (size <= 0) {
                    return;
                }
                aVarArr = new a[size];
                this.g.toArray(aVarArr);
                this.g.clear();
            }
            for (a aVar : aVarArr) {
                int size2 = aVar.b.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    b bVar = aVar.b.get(i2);
                    if (!bVar.d) {
                        bVar.b.onReceive(this.d, aVar.a);
                    }
                }
            }
        }
    }

    public void a(@af BroadcastReceiver broadcastReceiver) {
        synchronized (this.e) {
            ArrayList<b> remove = this.e.remove(broadcastReceiver);
            if (remove == null) {
                return;
            }
            for (int size = remove.size() - 1; size >= 0; size--) {
                b bVar = remove.get(size);
                bVar.d = true;
                for (int i2 = 0; i2 < bVar.a.countActions(); i2++) {
                    String action = bVar.a.getAction(i2);
                    ArrayList<b> arrayList = this.f.get(action);
                    if (arrayList != null) {
                        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                            b bVar2 = arrayList.get(size2);
                            if (bVar2.b == broadcastReceiver) {
                                bVar2.d = true;
                                arrayList.remove(size2);
                            }
                        }
                        if (arrayList.size() <= 0) {
                            this.f.remove(action);
                        }
                    }
                }
            }
        }
    }

    public void a(@af BroadcastReceiver broadcastReceiver, @af IntentFilter intentFilter) {
        synchronized (this.e) {
            b bVar = new b(intentFilter, broadcastReceiver);
            ArrayList<b> arrayList = this.e.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.e.put(broadcastReceiver, arrayList);
            }
            arrayList.add(bVar);
            for (int i2 = 0; i2 < intentFilter.countActions(); i2++) {
                String action = intentFilter.getAction(i2);
                ArrayList<b> arrayList2 = this.f.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.f.put(action, arrayList2);
                }
                arrayList2.add(bVar);
            }
        }
    }

    public boolean a(@af Intent intent) {
        String str;
        ArrayList arrayList;
        int i2;
        ArrayList<b> arrayList2;
        String str2;
        synchronized (this.e) {
            String action = intent.getAction();
            String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.d.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z = (intent.getFlags() & 8) != 0;
            if (z) {
                Log.v(b, "Resolving type " + resolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
            }
            ArrayList<b> arrayList3 = this.f.get(intent.getAction());
            if (arrayList3 != null) {
                if (z) {
                    Log.v(b, "Action list: " + arrayList3);
                }
                ArrayList arrayList4 = null;
                int i3 = 0;
                while (i3 < arrayList3.size()) {
                    b bVar = arrayList3.get(i3);
                    if (z) {
                        Log.v(b, "Matching against filter " + bVar.a);
                    }
                    if (bVar.c) {
                        if (z) {
                            Log.v(b, "  Filter's target already added");
                        }
                        i2 = i3;
                        arrayList2 = arrayList3;
                        str = action;
                        str2 = resolveTypeIfNeeded;
                        arrayList = arrayList4;
                    } else {
                        str = action;
                        arrayList = arrayList4;
                        i2 = i3;
                        arrayList2 = arrayList3;
                        str2 = resolveTypeIfNeeded;
                        int match = bVar.a.match(action, resolveTypeIfNeeded, scheme, data, categories, b);
                        if (match >= 0) {
                            if (z) {
                                Log.v(b, "  Filter matched!  match=0x" + Integer.toHexString(match));
                            }
                            arrayList4 = arrayList == null ? new ArrayList() : arrayList;
                            arrayList4.add(bVar);
                            bVar.c = true;
                            i3 = i2 + 1;
                            action = str;
                            arrayList3 = arrayList2;
                            resolveTypeIfNeeded = str2;
                        } else if (z) {
                            Log.v(b, "  Filter did not match: " + (match != -4 ? match != -3 ? match != -2 ? match != -1 ? "unknown reason" : "type" : "data" : "action" : "category"));
                        }
                    }
                    arrayList4 = arrayList;
                    i3 = i2 + 1;
                    action = str;
                    arrayList3 = arrayList2;
                    resolveTypeIfNeeded = str2;
                }
                ArrayList arrayList5 = arrayList4;
                if (arrayList5 != null) {
                    for (int i4 = 0; i4 < arrayList5.size(); i4++) {
                        ((b) arrayList5.get(i4)).c = false;
                    }
                    this.g.add(new a(intent, arrayList5));
                    if (!this.h.hasMessages(1)) {
                        this.h.sendEmptyMessage(1);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public void b(@af Intent intent) {
        if (a(intent)) {
            a();
        }
    }
}
