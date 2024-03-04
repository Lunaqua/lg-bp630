package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.an;
import android.util.AttributeSet;
import android.widget.ImageView;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class AppCompatImageView extends ImageView implements android.support.v4.view.z, android.support.v4.widget.w {
    private final e a;
    private final i b;

    public AppCompatImageView(Context context) {
        this(context, null);
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        this.a = new e(this);
        this.a.a(attributeSet, i);
        this.b = new i(this);
        this.b.a(attributeSet, i);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.a;
        if (eVar != null) {
            eVar.c();
        }
        i iVar = this.b;
        if (iVar != null) {
            iVar.d();
        }
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.a;
        if (eVar != null) {
            return eVar.a();
        }
        return null;
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.a;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    @Override // android.support.v4.widget.w
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public ColorStateList getSupportImageTintList() {
        i iVar = this.b;
        if (iVar != null) {
            return iVar.b();
        }
        return null;
    }

    @Override // android.support.v4.widget.w
    @android.support.annotation.ag
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportImageTintMode() {
        i iVar = this.b;
        if (iVar != null) {
            return iVar.c();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.b.a() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.a;
        if (eVar != null) {
            eVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@android.support.annotation.p int i) {
        super.setBackgroundResource(i);
        e eVar = this.a;
        if (eVar != null) {
            eVar.a(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        i iVar = this.b;
        if (iVar != null) {
            iVar.d();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(@android.support.annotation.ag Drawable drawable) {
        super.setImageDrawable(drawable);
        i iVar = this.b;
        if (iVar != null) {
            iVar.d();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(@android.support.annotation.p int i) {
        i iVar = this.b;
        if (iVar != null) {
            iVar.a(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(@android.support.annotation.ag Uri uri) {
        super.setImageURI(uri);
        i iVar = this.b;
        if (iVar != null) {
            iVar.d();
        }
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@android.support.annotation.ag ColorStateList colorStateList) {
        e eVar = this.a;
        if (eVar != null) {
            eVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.view.z
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@android.support.annotation.ag PorterDuff.Mode mode) {
        e eVar = this.a;
        if (eVar != null) {
            eVar.a(mode);
        }
    }

    @Override // android.support.v4.widget.w
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportImageTintList(@android.support.annotation.ag ColorStateList colorStateList) {
        i iVar = this.b;
        if (iVar != null) {
            iVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.widget.w
    @android.support.annotation.an(a = {an.a.LIBRARY_GROUP})
    public void setSupportImageTintMode(@android.support.annotation.ag PorterDuff.Mode mode) {
        i iVar = this.b;
        if (iVar != null) {
            iVar.a(mode);
        }
    }
}
