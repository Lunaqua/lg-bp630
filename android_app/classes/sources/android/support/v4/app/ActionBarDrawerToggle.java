package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.aq;
import android.support.annotation.p;
import android.support.v4.content.c;
import android.support.v4.view.ab;
import android.support.v4.view.f;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;
@Deprecated
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public class ActionBarDrawerToggle implements DrawerLayout.c {
    private static final int ID_HOME = 16908332;
    private static final String TAG = "ActionBarDrawerToggle";
    private static final int[] THEME_ATTRS = {16843531};
    private static final float TOGGLE_DRAWABLE_OFFSET = 0.33333334f;
    final Activity mActivity;
    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private Drawable mDrawerImage;
    private final int mDrawerImageResource;
    private boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private SetIndicatorInfo mSetIndicatorInfo;
    private SlideDrawable mSlider;

    @Deprecated
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface Delegate {
        @ag
        Drawable getThemeUpIndicator();

        void setActionBarDescription(@aq int i);

        void setActionBarUpIndicator(Drawable drawable, @aq int i);
    }

    @Deprecated
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public interface DelegateProvider {
        @ag
        Delegate getDrawerToggleDelegate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public static class SetIndicatorInfo {
        Method mSetHomeActionContentDescription;
        Method mSetHomeAsUpIndicator;
        ImageView mUpIndicatorView;

        SetIndicatorInfo(Activity activity) {
            try {
                this.mSetHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.mSetHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
            } catch (NoSuchMethodException unused) {
                View findViewById = activity.findViewById(ActionBarDrawerToggle.ID_HOME);
                if (findViewById == null) {
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
                if (viewGroup.getChildCount() != 2) {
                    return;
                }
                View childAt = viewGroup.getChildAt(0);
                View childAt2 = childAt.getId() != ActionBarDrawerToggle.ID_HOME ? childAt : viewGroup.getChildAt(1);
                if (childAt2 instanceof ImageView) {
                    this.mUpIndicatorView = (ImageView) childAt2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    public class SlideDrawable extends InsetDrawable implements Drawable.Callback {
        private final boolean mHasMirroring;
        private float mOffset;
        private float mPosition;
        private final Rect mTmpRect;

        SlideDrawable(Drawable drawable) {
            super(drawable, 0);
            this.mHasMirroring = Build.VERSION.SDK_INT > 18;
            this.mTmpRect = new Rect();
        }

        @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public void draw(@af Canvas canvas) {
            copyBounds(this.mTmpRect);
            canvas.save();
            boolean z = ab.m(ActionBarDrawerToggle.this.mActivity.getWindow().getDecorView()) == 1;
            int i = z ? -1 : 1;
            float width = this.mTmpRect.width();
            canvas.translate((-this.mOffset) * width * this.mPosition * i, 0.0f);
            if (z && !this.mHasMirroring) {
                canvas.translate(width, 0.0f);
                canvas.scale(-1.0f, 1.0f);
            }
            super.draw(canvas);
            canvas.restore();
        }

        public float getPosition() {
            return this.mPosition;
        }

        public void setOffset(float f) {
            this.mOffset = f;
            invalidateSelf();
        }

        public void setPosition(float f) {
            this.mPosition = f;
            invalidateSelf();
        }
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, @p int i, @aq int i2, @aq int i3) {
        this(activity, drawerLayout, !assumeMaterial(activity), i, i2, i3);
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, boolean z, @p int i, @aq int i2, @aq int i3) {
        this.mDrawerIndicatorEnabled = true;
        this.mActivity = activity;
        this.mActivityImpl = activity instanceof DelegateProvider ? ((DelegateProvider) activity).getDrawerToggleDelegate() : null;
        this.mDrawerLayout = drawerLayout;
        this.mDrawerImageResource = i;
        this.mOpenDrawerContentDescRes = i2;
        this.mCloseDrawerContentDescRes = i3;
        this.mHomeAsUpIndicator = getThemeUpIndicator();
        this.mDrawerImage = c.getDrawable(activity, i);
        this.mSlider = new SlideDrawable(this.mDrawerImage);
        this.mSlider.setOffset(z ? TOGGLE_DRAWABLE_OFFSET : 0.0f);
    }

    private static boolean assumeMaterial(Context context) {
        return context.getApplicationInfo().targetSdkVersion >= 21 && Build.VERSION.SDK_INT >= 21;
    }

    private Drawable getThemeUpIndicator() {
        TypedArray obtainStyledAttributes;
        Delegate delegate = this.mActivityImpl;
        if (delegate != null) {
            return delegate.getThemeUpIndicator();
        }
        if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.mActivity.getActionBar();
            obtainStyledAttributes = (actionBar != null ? actionBar.getThemedContext() : this.mActivity).obtainStyledAttributes(null, THEME_ATTRS, 16843470, 0);
        } else {
            obtainStyledAttributes = this.mActivity.obtainStyledAttributes(THEME_ATTRS);
        }
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    private void setActionBarDescription(int i) {
        Delegate delegate = this.mActivityImpl;
        if (delegate != null) {
            delegate.setActionBarDescription(i);
        } else if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(i);
            }
        } else {
            if (this.mSetIndicatorInfo == null) {
                this.mSetIndicatorInfo = new SetIndicatorInfo(this.mActivity);
            }
            if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
                try {
                    ActionBar actionBar2 = this.mActivity.getActionBar();
                    this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(actionBar2, Integer.valueOf(i));
                    actionBar2.setSubtitle(actionBar2.getSubtitle());
                } catch (Exception e) {
                    Log.w(TAG, "Couldn't set content description via JB-MR2 API", e);
                }
            }
        }
    }

    private void setActionBarUpIndicator(Drawable drawable, int i) {
        Delegate delegate = this.mActivityImpl;
        if (delegate != null) {
            delegate.setActionBarUpIndicator(drawable, i);
        } else if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(drawable);
                actionBar.setHomeActionContentDescription(i);
            }
        } else {
            if (this.mSetIndicatorInfo == null) {
                this.mSetIndicatorInfo = new SetIndicatorInfo(this.mActivity);
            }
            if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator == null) {
                if (this.mSetIndicatorInfo.mUpIndicatorView != null) {
                    this.mSetIndicatorInfo.mUpIndicatorView.setImageDrawable(drawable);
                    return;
                } else {
                    Log.w(TAG, "Couldn't set home-as-up indicator");
                    return;
                }
            }
            try {
                ActionBar actionBar2 = this.mActivity.getActionBar();
                this.mSetIndicatorInfo.mSetHomeAsUpIndicator.invoke(actionBar2, drawable);
                this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(actionBar2, Integer.valueOf(i));
            } catch (Exception e) {
                Log.w(TAG, "Couldn't set home-as-up indicator via JB-MR2 API", e);
            }
        }
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.mDrawerIndicatorEnabled;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.mHasCustomUpIndicator) {
            this.mHomeAsUpIndicator = getThemeUpIndicator();
        }
        this.mDrawerImage = c.getDrawable(this.mActivity, this.mDrawerImageResource);
        syncState();
    }

    @Override // android.support.v4.widget.DrawerLayout.c
    public void onDrawerClosed(View view) {
        this.mSlider.setPosition(0.0f);
        if (this.mDrawerIndicatorEnabled) {
            setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }

    @Override // android.support.v4.widget.DrawerLayout.c
    public void onDrawerOpened(View view) {
        this.mSlider.setPosition(1.0f);
        if (this.mDrawerIndicatorEnabled) {
            setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }

    @Override // android.support.v4.widget.DrawerLayout.c
    public void onDrawerSlide(View view, float f) {
        float position = this.mSlider.getPosition();
        this.mSlider.setPosition(f > 0.5f ? Math.max(position, Math.max(0.0f, f - 0.5f) * 2.0f) : Math.min(position, f * 2.0f));
    }

    @Override // android.support.v4.widget.DrawerLayout.c
    public void onDrawerStateChanged(int i) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem != null && menuItem.getItemId() == ID_HOME && this.mDrawerIndicatorEnabled) {
            if (this.mDrawerLayout.h(f.b)) {
                this.mDrawerLayout.f(f.b);
                return true;
            }
            this.mDrawerLayout.e(f.b);
            return true;
        }
        return false;
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        Drawable drawable;
        int i;
        if (z != this.mDrawerIndicatorEnabled) {
            if (z) {
                drawable = this.mSlider;
                i = this.mDrawerLayout.g(f.b) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes;
            } else {
                drawable = this.mHomeAsUpIndicator;
                i = 0;
            }
            setActionBarUpIndicator(drawable, i);
            this.mDrawerIndicatorEnabled = z;
        }
    }

    public void setHomeAsUpIndicator(int i) {
        setHomeAsUpIndicator(i != 0 ? c.getDrawable(this.mActivity, i) : null);
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        if (drawable == null) {
            this.mHomeAsUpIndicator = getThemeUpIndicator();
            this.mHasCustomUpIndicator = false;
        } else {
            this.mHomeAsUpIndicator = drawable;
            this.mHasCustomUpIndicator = true;
        }
        if (this.mDrawerIndicatorEnabled) {
            return;
        }
        setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
    }

    public void syncState() {
        SlideDrawable slideDrawable;
        float f;
        if (this.mDrawerLayout.g(f.b)) {
            slideDrawable = this.mSlider;
            f = 1.0f;
        } else {
            slideDrawable = this.mSlider;
            f = 0.0f;
        }
        slideDrawable.setPosition(f);
        if (this.mDrawerIndicatorEnabled) {
            setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.g(f.b) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
        }
    }
}
