package android.support.v4.app;

import android.graphics.Rect;
import android.support.annotation.an;
import android.support.v4.view.ab;
import android.support.v4.view.ad;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@an(a = {an.a.LIBRARY_GROUP})
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public abstract class FragmentTransitionImpl {
    /* JADX INFO: Access modifiers changed from: protected */
    public static void bfsAddViewChildren(List<View> list, View view) {
        int size = list.size();
        if (containedBeforeIndex(list, view, size)) {
            return;
        }
        list.add(view);
        for (int i = size; i < list.size(); i++) {
            View view2 = list.get(i);
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = viewGroup.getChildAt(i2);
                    if (!containedBeforeIndex(list, childAt, size)) {
                        list.add(childAt);
                    }
                }
            }
        }
    }

    private static boolean containedBeforeIndex(List<View> list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    static String findKeyForValue(Map<String, String> map, String str) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (str.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isNullOrEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public abstract void addTarget(Object obj, View view);

    public abstract void addTargets(Object obj, ArrayList<View> arrayList);

    public abstract void beginDelayedTransition(ViewGroup viewGroup, Object obj);

    public abstract boolean canHandle(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() == 0) {
            boolean z = view instanceof ViewGroup;
            ViewGroup viewGroup = view;
            if (z) {
                ViewGroup viewGroup2 = (ViewGroup) view;
                boolean b = ad.b(viewGroup2);
                viewGroup = viewGroup2;
                if (!b) {
                    int childCount = viewGroup2.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        captureTransitioningViews(arrayList, viewGroup2.getChildAt(i));
                    }
                    return;
                }
            }
            arrayList.add(viewGroup);
        }
    }

    public abstract Object cloneTransition(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void findNamedViews(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String O = ab.O(view);
            if (O != null) {
                map.put(O, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    findNamedViews(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void getBoundsOnScreen(View view, Rect rect) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
    }

    public abstract Object mergeTransitionsInSequence(Object obj, Object obj2, Object obj3);

    public abstract Object mergeTransitionsTogether(Object obj, Object obj2, Object obj3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<String> prepareSetNameOverridesReordered(ArrayList<View> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = arrayList.get(i);
            arrayList2.add(ab.O(view));
            ab.a(view, (String) null);
        }
        return arrayList2;
    }

    public abstract void removeTarget(Object obj, View view);

    public abstract void replaceTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract void scheduleHideFragmentView(Object obj, View view, ArrayList<View> arrayList);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scheduleNameReset(ViewGroup viewGroup, final ArrayList<View> arrayList, final Map<String, String> map) {
        OneShotPreDrawListener.add(viewGroup, new Runnable() { // from class: android.support.v4.app.FragmentTransitionImpl.3
            @Override // java.lang.Runnable
            public void run() {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    View view = (View) arrayList.get(i);
                    ab.a(view, (String) map.get(ab.O(view)));
                }
            }
        });
    }

    public abstract void scheduleRemoveTargets(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void setEpicenter(Object obj, Rect rect);

    public abstract void setEpicenter(Object obj, View view);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNameOverridesOrdered(View view, final ArrayList<View> arrayList, final Map<String, String> map) {
        OneShotPreDrawListener.add(view, new Runnable() { // from class: android.support.v4.app.FragmentTransitionImpl.2
            @Override // java.lang.Runnable
            public void run() {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    View view2 = (View) arrayList.get(i);
                    String O = ab.O(view2);
                    if (O != null) {
                        ab.a(view2, FragmentTransitionImpl.findKeyForValue(map, O));
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNameOverridesReordered(View view, final ArrayList<View> arrayList, final ArrayList<View> arrayList2, final ArrayList<String> arrayList3, Map<String, String> map) {
        final int size = arrayList2.size();
        final ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < size; i++) {
            View view2 = arrayList.get(i);
            String O = ab.O(view2);
            arrayList4.add(O);
            if (O != null) {
                ab.a(view2, (String) null);
                String str = map.get(O);
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    } else if (str.equals(arrayList3.get(i2))) {
                        ab.a(arrayList2.get(i2), O);
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        OneShotPreDrawListener.add(view, new Runnable() { // from class: android.support.v4.app.FragmentTransitionImpl.1
            @Override // java.lang.Runnable
            public void run() {
                for (int i3 = 0; i3 < size; i3++) {
                    ab.a((View) arrayList2.get(i3), (String) arrayList3.get(i3));
                    ab.a((View) arrayList.get(i3), (String) arrayList4.get(i3));
                }
            }
        });
    }

    public abstract void setSharedElementTargets(Object obj, View view, ArrayList<View> arrayList);

    public abstract void swapSharedElementTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract Object wrapTransitionInSet(Object obj);
}
