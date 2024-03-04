package android.support.v7.app;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
class h implements AdapterView.OnItemSelectedListener {
    private final ActionBar.d a;

    public h(ActionBar.d dVar) {
        this.a = dVar;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ActionBar.d dVar = this.a;
        if (dVar != null) {
            dVar.a(i, j);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
