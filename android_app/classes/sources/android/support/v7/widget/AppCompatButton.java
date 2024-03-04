package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.an;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AppCompatButton extends Button implements android.support.v4.view.z, android.support.v4.widget.b {
    private final e b;
    private final l c;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        this.b = new e(this);
        this.b.a(attributeSet, i);
        this.c = new l(this);
        this.c.a(attributeSet, i);
        this.c.a();
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.b;
        if (eVar != null) {
            eVar.c();
        }
        l lVar = this.c;
        if (lVar != null) {
            lVar.a();
        }
    }

    @Override // android.widget.TextView, android.support.v4.widget.b
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int getAutoSizeMaxTextSize() {
        if (a) {
            return super.getAutoSizeMaxTextSize();
        }
        l lVar = this.c;
        if (lVar != null) {
            return lVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView, android.support.v4.widget.b
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int getAutoSizeMinTextSize() {
        if (a) {
            return super.getAutoSizeMinTextSize();
        }
        l lVar = this.c;
        if (lVar != null) {
            return lVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView, android.support.v4.widget.b
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int getAutoSizeStepGranularity() {
        if (a) {
            return super.getAutoSizeStepGranularity();
        }
        l lVar = this.c;
        if (lVar != null) {
            return lVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView, android.support.v4.widget.b
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int[] getAutoSizeTextAvailableSizes() {
        if (a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        l lVar = this.c;
        return lVar != null ? lVar.h() : new int[0];
    }

    @Override // android.widget.TextView, android.support.v4.widget.b
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public int getAutoSizeTextType() {
        if (a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        l lVar = this.c;
        if (lVar != null) {
            return lVar.d();
        }
        return 0;
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.b;
        if (eVar != null) {
            return eVar.a();
        }
        return null;
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.b;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (this.c == null || a || !this.c.c()) {
            return;
        }
        this.c.b();
    }

    @Override // android.widget.TextView, android.support.v4.widget.b
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView, android.support.v4.widget.b
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setAutoSizeTextTypeUniformWithPresetSizes(@android.support.annotation.af int[] iArr, int i) {
        if (a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(iArr, i);
        }
    }

    @Override // android.widget.TextView, android.support.v4.widget.b
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@android.support.annotation.p int i) {
        super.setBackgroundResource(i);
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(android.support.v4.widget.u.b(this, callback));
    }

    public void setSupportAllCaps(boolean z) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(z);
        }
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@android.support.annotation.ag ColorStateList colorStateList) {
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@android.support.annotation.ag PorterDuff.Mode mode) {
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        if (a) {
            super.setTextSize(i, f);
            return;
        }
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(i, f);
        }
    }
}
