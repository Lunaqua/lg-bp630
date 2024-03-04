package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
/* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
public final class af {
    static final int d = 2113929216;
    private static final String e = "ViewAnimatorCompat";
    Runnable a = null;
    Runnable b = null;
    int c = -1;
    private WeakReference<View> f;

    /* loaded from: /home/navi/Documents/lg_bp630/android_app/classes.dex */
    static class a implements ag {
        af a;
        boolean b;

        a(af afVar) {
            this.a = afVar;
        }

        @Override // android.support.v4.view.ag
        public void a(View view) {
            this.b = false;
            if (this.a.c > -1) {
                view.setLayerType(2, null);
            }
            if (this.a.a != null) {
                Runnable runnable = this.a.a;
                this.a.a = null;
                runnable.run();
            }
            Object tag = view.getTag(af.d);
            ag agVar = tag instanceof ag ? (ag) tag : null;
            if (agVar != null) {
                agVar.a(view);
            }
        }

        @Override // android.support.v4.view.ag
        public void b(View view) {
            if (this.a.c > -1) {
                view.setLayerType(this.a.c, null);
                this.a.c = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.b) {
                if (this.a.b != null) {
                    Runnable runnable = this.a.b;
                    this.a.b = null;
                    runnable.run();
                }
                Object tag = view.getTag(af.d);
                ag agVar = tag instanceof ag ? (ag) tag : null;
                if (agVar != null) {
                    agVar.b(view);
                }
                this.b = true;
            }
        }

        @Override // android.support.v4.view.ag
        public void c(View view) {
            Object tag = view.getTag(af.d);
            ag agVar = tag instanceof ag ? (ag) tag : null;
            if (agVar != null) {
                agVar.c(view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public af(View view) {
        this.f = new WeakReference<>(view);
    }

    private void a(final View view, final ag agVar) {
        if (agVar != null) {
            view.animate().setListener(new AnimatorListenerAdapter() { // from class: android.support.v4.view.af.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    agVar.c(view);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    agVar.b(view);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    agVar.a(view);
                }
            });
        } else {
            view.animate().setListener(null);
        }
    }

    public long a() {
        View view = this.f.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public af a(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public af a(long j) {
        View view = this.f.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public af a(ag agVar) {
        View view = this.f.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT < 16) {
                view.setTag(d, agVar);
                agVar = new a(this);
            }
            a(view, agVar);
        }
        return this;
    }

    public af a(final ai aiVar) {
        final View view = this.f.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(aiVar != null ? new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v4.view.af.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    aiVar.a(view);
                }
            } : null);
        }
        return this;
    }

    public af a(Interpolator interpolator) {
        View view = this.f.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public af a(Runnable runnable) {
        View view = this.f.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                view.animate().withEndAction(runnable);
            } else {
                a(view, new a(this));
                this.b = runnable;
            }
        }
        return this;
    }

    public af b(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().alphaBy(f);
        }
        return this;
    }

    public af b(long j) {
        View view = this.f.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public af b(Runnable runnable) {
        View view = this.f.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                view.animate().withStartAction(runnable);
            } else {
                a(view, new a(this));
                this.a = runnable;
            }
        }
        return this;
    }

    public Interpolator b() {
        View view = this.f.get();
        if (view == null || Build.VERSION.SDK_INT < 18) {
            return null;
        }
        return (Interpolator) view.animate().getInterpolator();
    }

    public long c() {
        View view = this.f.get();
        if (view != null) {
            return view.animate().getStartDelay();
        }
        return 0L;
    }

    public af c(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().translationX(f);
        }
        return this;
    }

    public af d(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }

    public void d() {
        View view = this.f.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public af e(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().rotation(f);
        }
        return this;
    }

    public void e() {
        View view = this.f.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public af f() {
        View view = this.f.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                view.animate().withLayer();
            } else {
                this.c = view.getLayerType();
                a(view, new a(this));
            }
        }
        return this;
    }

    public af f(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().rotationBy(f);
        }
        return this;
    }

    public af g(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().rotationX(f);
        }
        return this;
    }

    public af h(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().rotationXBy(f);
        }
        return this;
    }

    public af i(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().rotationY(f);
        }
        return this;
    }

    public af j(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().rotationYBy(f);
        }
        return this;
    }

    public af k(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().scaleX(f);
        }
        return this;
    }

    public af l(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().scaleXBy(f);
        }
        return this;
    }

    public af m(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().scaleY(f);
        }
        return this;
    }

    public af n(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().scaleYBy(f);
        }
        return this;
    }

    public af o(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().x(f);
        }
        return this;
    }

    public af p(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().xBy(f);
        }
        return this;
    }

    public af q(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().y(f);
        }
        return this;
    }

    public af r(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().yBy(f);
        }
        return this;
    }

    public af s(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().translationXBy(f);
        }
        return this;
    }

    public af t(float f) {
        View view = this.f.get();
        if (view != null) {
            view.animate().translationYBy(f);
        }
        return this;
    }

    public af u(float f) {
        View view = this.f.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            view.animate().translationZBy(f);
        }
        return this;
    }

    public af v(float f) {
        View view = this.f.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            view.animate().translationZ(f);
        }
        return this;
    }

    public af w(float f) {
        View view = this.f.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            view.animate().z(f);
        }
        return this;
    }

    public af x(float f) {
        View view = this.f.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            view.animate().zBy(f);
        }
        return this;
    }
}
