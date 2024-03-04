package android.support.v4.i.a;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.an;
import android.support.v4.j.o;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class b {
    private static final String[] a = new String[0];
    private static final Comparator<a> b = new Comparator<a>() { // from class: android.support.v4.i.a.b.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(a aVar, a aVar2) {
            if (aVar.c < aVar2.c) {
                return -1;
            }
            if (aVar.c <= aVar2.c && aVar.d >= aVar2.d) {
                return aVar.d > aVar2.d ? -1 : 0;
            }
            return 1;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class a {
        URLSpan a;
        String b;
        int c;
        int d;

        a() {
        }
    }

    @an(a = {an.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.i.a.b$b  reason: collision with other inner class name */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public @interface InterfaceC0026b {
    }

    private b() {
    }

    private static String a(String str) {
        return Build.VERSION.SDK_INT >= 28 ? WebView.findAddress(str) : android.support.v4.i.a.a.c(str);
    }

    private static String a(@af String str, @af String[] strArr, Matcher matcher, @ag Linkify.TransformFilter transformFilter) {
        boolean z;
        if (transformFilter != null) {
            str = transformFilter.transformUrl(matcher, str);
        }
        int i = 0;
        while (true) {
            z = true;
            if (i >= strArr.length) {
                z = false;
                break;
            }
            if (str.regionMatches(true, 0, strArr[i], 0, strArr[i].length())) {
                if (!str.regionMatches(false, 0, strArr[i], 0, strArr[i].length())) {
                    str = strArr[i] + str.substring(strArr[i].length());
                }
            } else {
                i++;
            }
        }
        if (z || strArr.length <= 0) {
            return str;
        }
        return strArr[0] + str;
    }

    private static void a(@af TextView textView) {
        MovementMethod movementMethod = textView.getMovementMethod();
        if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public static void a(@af TextView textView, @af Pattern pattern, @ag String str) {
        if (a()) {
            Linkify.addLinks(textView, pattern, str);
        } else {
            a(textView, pattern, str, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
        }
    }

    public static void a(@af TextView textView, @af Pattern pattern, @ag String str, @ag Linkify.MatchFilter matchFilter, @ag Linkify.TransformFilter transformFilter) {
        if (a()) {
            Linkify.addLinks(textView, pattern, str, matchFilter, transformFilter);
        } else {
            a(textView, pattern, str, (String[]) null, matchFilter, transformFilter);
        }
    }

    @SuppressLint({"NewApi"})
    public static void a(@af TextView textView, @af Pattern pattern, @ag String str, @ag String[] strArr, @ag Linkify.MatchFilter matchFilter, @ag Linkify.TransformFilter transformFilter) {
        if (a()) {
            Linkify.addLinks(textView, pattern, str, strArr, matchFilter, transformFilter);
            return;
        }
        SpannableString valueOf = SpannableString.valueOf(textView.getText());
        if (a(valueOf, pattern, str, strArr, matchFilter, transformFilter)) {
            textView.setText(valueOf);
            a(textView);
        }
    }

    private static void a(String str, int i, int i2, Spannable spannable) {
        spannable.setSpan(new URLSpan(str), i, i2, 33);
    }

    private static void a(ArrayList<a> arrayList, Spannable spannable) {
        int indexOf;
        String obj = spannable.toString();
        int i = 0;
        while (true) {
            try {
                String a2 = a(obj);
                if (a2 != null && (indexOf = obj.indexOf(a2)) >= 0) {
                    a aVar = new a();
                    int length = a2.length() + indexOf;
                    aVar.c = indexOf + i;
                    i += length;
                    aVar.d = i;
                    obj = obj.substring(length);
                    try {
                        String encode = URLEncoder.encode(a2, "UTF-8");
                        aVar.b = "geo:0,0?q=" + encode;
                        arrayList.add(aVar);
                    } catch (UnsupportedEncodingException unused) {
                    }
                }
                return;
            } catch (UnsupportedOperationException unused2) {
                return;
            }
        }
    }

    private static void a(ArrayList<a> arrayList, Spannable spannable, Pattern pattern, String[] strArr, Linkify.MatchFilter matchFilter, Linkify.TransformFilter transformFilter) {
        Matcher matcher = pattern.matcher(spannable);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (matchFilter == null || matchFilter.acceptMatch(spannable, start, end)) {
                a aVar = new a();
                aVar.b = a(matcher.group(0), strArr, matcher, transformFilter);
                aVar.c = start;
                aVar.d = end;
                arrayList.add(aVar);
            }
        }
    }

    private static boolean a() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static boolean a(@af Spannable spannable, int i) {
        if (a()) {
            return Linkify.addLinks(spannable, i);
        }
        if (i == 0) {
            return false;
        }
        URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class);
        for (int length = uRLSpanArr.length - 1; length >= 0; length--) {
            spannable.removeSpan(uRLSpanArr[length]);
        }
        if ((i & 4) != 0) {
            Linkify.addLinks(spannable, 4);
        }
        ArrayList arrayList = new ArrayList();
        if ((i & 1) != 0) {
            a(arrayList, spannable, o.e, new String[]{"http://", "https://", "rtsp://"}, Linkify.sUrlMatchFilter, (Linkify.TransformFilter) null);
        }
        if ((i & 2) != 0) {
            a(arrayList, spannable, o.f, new String[]{"mailto:"}, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
        }
        if ((i & 8) != 0) {
            a(arrayList, spannable);
        }
        b(arrayList, spannable);
        if (arrayList.size() == 0) {
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.a == null) {
                a(aVar.b, aVar.c, aVar.d, spannable);
            }
        }
        return true;
    }

    public static boolean a(@af Spannable spannable, @af Pattern pattern, @ag String str) {
        return a() ? Linkify.addLinks(spannable, pattern, str) : a(spannable, pattern, str, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
    }

    public static boolean a(@af Spannable spannable, @af Pattern pattern, @ag String str, @ag Linkify.MatchFilter matchFilter, @ag Linkify.TransformFilter transformFilter) {
        return a() ? Linkify.addLinks(spannable, pattern, str, matchFilter, transformFilter) : a(spannable, pattern, str, (String[]) null, matchFilter, transformFilter);
    }

    @SuppressLint({"NewApi"})
    public static boolean a(@af Spannable spannable, @af Pattern pattern, @ag String str, @ag String[] strArr, @ag Linkify.MatchFilter matchFilter, @ag Linkify.TransformFilter transformFilter) {
        if (a()) {
            return Linkify.addLinks(spannable, pattern, str, strArr, matchFilter, transformFilter);
        }
        if (str == null) {
            str = com.lge.media.launcher.a.d;
        }
        if (strArr == null || strArr.length < 1) {
            strArr = a;
        }
        String[] strArr2 = new String[strArr.length + 1];
        strArr2[0] = str.toLowerCase(Locale.ROOT);
        int i = 0;
        while (i < strArr.length) {
            String str2 = strArr[i];
            i++;
            strArr2[i] = str2 == null ? com.lge.media.launcher.a.d : str2.toLowerCase(Locale.ROOT);
        }
        Matcher matcher = pattern.matcher(spannable);
        boolean z = false;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (matchFilter != null ? matchFilter.acceptMatch(spannable, start, end) : true) {
                a(a(matcher.group(0), strArr2, matcher, transformFilter), start, end, spannable);
                z = true;
            }
        }
        return z;
    }

    public static boolean a(@af TextView textView, int i) {
        if (a()) {
            return Linkify.addLinks(textView, i);
        }
        if (i == 0) {
            return false;
        }
        CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            if (a((Spannable) text, i)) {
                a(textView);
                return true;
            }
            return false;
        }
        SpannableString valueOf = SpannableString.valueOf(text);
        if (a(valueOf, i)) {
            a(textView);
            textView.setText(valueOf);
            return true;
        }
        return false;
    }

    private static void b(ArrayList<a> arrayList, Spannable spannable) {
        int i = 0;
        Object[] objArr = (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class);
        for (int i2 = 0; i2 < objArr.length; i2++) {
            a aVar = new a();
            aVar.a = objArr[i2];
            aVar.c = spannable.getSpanStart(objArr[i2]);
            aVar.d = spannable.getSpanEnd(objArr[i2]);
            arrayList.add(aVar);
        }
        Collections.sort(arrayList, b);
        int size = arrayList.size();
        while (i < size - 1) {
            a aVar2 = arrayList.get(i);
            int i3 = i + 1;
            a aVar3 = arrayList.get(i3);
            if (aVar2.c <= aVar3.c && aVar2.d > aVar3.c) {
                int i4 = (aVar3.d > aVar2.d && aVar2.d - aVar2.c <= aVar3.d - aVar3.c) ? aVar2.d - aVar2.c < aVar3.d - aVar3.c ? i : -1 : i3;
                if (i4 != -1) {
                    Object obj = arrayList.get(i4).a;
                    if (obj != null) {
                        spannable.removeSpan(obj);
                    }
                    arrayList.remove(i4);
                    size--;
                }
            }
            i = i3;
        }
    }
}
