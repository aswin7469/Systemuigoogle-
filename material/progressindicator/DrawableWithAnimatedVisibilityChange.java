package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.Property;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.progressindicator.BaseProgressIndicator;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class DrawableWithAnimatedVisibilityChange extends Drawable implements Animatable2Compat {
    public static final AnonymousClass3 GROW_FRACTION = new Property(Float.class, "growFraction");
    public List animationCallbacks;
    public AnimatorDurationScaleProvider animatorDurationScaleProvider;
    public final BaseProgressIndicatorSpec baseSpec;
    public final Context context;
    public float growFraction;
    public ValueAnimator hideAnimator;
    public boolean ignoreCallbacks;
    public float mockGrowFraction;
    public boolean mockHideAnimationRunning;
    public boolean mockShowAnimationRunning;
    public final Paint paint = new Paint();
    public ValueAnimator showAnimator;
    public int totalAlpha;

    /* renamed from: com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange$3  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass3 extends Property {
        public final Object get(Object obj) {
            return Float.valueOf(((DrawableWithAnimatedVisibilityChange) obj).getGrowFraction());
        }

        public final void set(Object obj, Object obj2) {
            DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange = (DrawableWithAnimatedVisibilityChange) obj;
            float floatValue = ((Float) obj2).floatValue();
            if (drawableWithAnimatedVisibilityChange.growFraction != floatValue) {
                drawableWithAnimatedVisibilityChange.growFraction = floatValue;
                drawableWithAnimatedVisibilityChange.invalidateSelf();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [com.google.android.material.progressindicator.AnimatorDurationScaleProvider, java.lang.Object] */
    public DrawableWithAnimatedVisibilityChange(Context context2, BaseProgressIndicatorSpec baseProgressIndicatorSpec) {
        this.context = context2;
        this.baseSpec = baseProgressIndicatorSpec;
        this.animatorDurationScaleProvider = new Object();
        setAlpha(255);
    }

    public final void clearAnimationCallbacks() {
        this.animationCallbacks.clear();
        this.animationCallbacks = null;
    }

    public final int getAlpha() {
        return this.totalAlpha;
    }

    public final float getGrowFraction() {
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.baseSpec;
        if (baseProgressIndicatorSpec.showAnimationBehavior == 0 && baseProgressIndicatorSpec.hideAnimationBehavior == 0) {
            return 1.0f;
        }
        if (this.mockHideAnimationRunning || this.mockShowAnimationRunning) {
            return this.mockGrowFraction;
        }
        return this.growFraction;
    }

    public final int getOpacity() {
        return -3;
    }

    public final boolean isHiding() {
        ValueAnimator valueAnimator = this.hideAnimator;
        if ((valueAnimator == null || !valueAnimator.isRunning()) && !this.mockHideAnimationRunning) {
            return false;
        }
        return true;
    }

    public final boolean isRunning() {
        if (isShowing() || isHiding()) {
            return true;
        }
        return false;
    }

    public final boolean isShowing() {
        ValueAnimator valueAnimator = this.showAnimator;
        if ((valueAnimator == null || !valueAnimator.isRunning()) && !this.mockShowAnimationRunning) {
            return false;
        }
        return true;
    }

    public final void registerAnimationCallback(Animatable2Compat.AnimationCallback animationCallback) {
        if (this.animationCallbacks == null) {
            this.animationCallbacks = new ArrayList();
        }
        if (!this.animationCallbacks.contains(animationCallback)) {
            this.animationCallbacks.add(animationCallback);
        }
    }

    public final void setAlpha(int i) {
        this.totalAlpha = i;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setMockHideAnimationRunning(boolean z, float f) {
        this.mockHideAnimationRunning = z;
        this.mockGrowFraction = f;
    }

    public void setMockShowAnimationRunning(boolean z, float f) {
        this.mockShowAnimationRunning = z;
        this.mockGrowFraction = f;
    }

    public final boolean setVisible(boolean z, boolean z2) {
        return setVisible(z, z2, true);
    }

    public boolean setVisibleInternal(boolean z, boolean z2, boolean z3) {
        ValueAnimator valueAnimator;
        boolean z4;
        if (this.showAnimator == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, GROW_FRACTION, new float[]{0.0f, 1.0f});
            this.showAnimator = ofFloat;
            ofFloat.setDuration(500);
            this.showAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            ValueAnimator valueAnimator2 = this.showAnimator;
            if (valueAnimator2 == null || !valueAnimator2.isRunning()) {
                this.showAnimator = valueAnimator2;
                valueAnimator2.addListener(new AnimatorListenerAdapter(this, 0) {
                    public final /* synthetic */ DrawableWithAnimatedVisibilityChange this$0;

                    {
                        this.this$0 = r1;
                    }

                    public final void onAnimationEnd(Animator animator) {
                        switch (1) {
                            case 1:
                                super.onAnimationEnd(animator);
                                DrawableWithAnimatedVisibilityChange.super.setVisible(false, false);
                                DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange = this.this$0;
                                List<Animatable2Compat.AnimationCallback> list = drawableWithAnimatedVisibilityChange.animationCallbacks;
                                if (list != null && !drawableWithAnimatedVisibilityChange.ignoreCallbacks) {
                                    for (Animatable2Compat.AnimationCallback onAnimationEnd : list) {
                                        onAnimationEnd.onAnimationEnd(drawableWithAnimatedVisibilityChange);
                                    }
                                    return;
                                }
                                return;
                            default:
                                super.onAnimationEnd(animator);
                                return;
                        }
                    }

                    public final void onAnimationStart(Animator animator) {
                        switch (1) {
                            case 0:
                                super.onAnimationStart(animator);
                                DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange = this.this$0;
                                List<Animatable2Compat.AnimationCallback> list = drawableWithAnimatedVisibilityChange.animationCallbacks;
                                if (list != null && !drawableWithAnimatedVisibilityChange.ignoreCallbacks) {
                                    for (Animatable2Compat.AnimationCallback animationCallback : list) {
                                        animationCallback.getClass();
                                    }
                                    return;
                                }
                                return;
                            default:
                                super.onAnimationStart(animator);
                                return;
                        }
                    }
                });
            } else {
                throw new IllegalArgumentException("Cannot set showAnimator while the current showAnimator is running.");
            }
        }
        if (this.hideAnimator == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, GROW_FRACTION, new float[]{1.0f, 0.0f});
            this.hideAnimator = ofFloat2;
            ofFloat2.setDuration(500);
            this.hideAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            ValueAnimator valueAnimator3 = this.hideAnimator;
            if (valueAnimator3 == null || !valueAnimator3.isRunning()) {
                this.hideAnimator = valueAnimator3;
                valueAnimator3.addListener(new AnimatorListenerAdapter(this, 1) {
                    public final /* synthetic */ DrawableWithAnimatedVisibilityChange this$0;

                    {
                        this.this$0 = r1;
                    }

                    public final void onAnimationEnd(Animator animator) {
                        switch (1) {
                            case 1:
                                super.onAnimationEnd(animator);
                                DrawableWithAnimatedVisibilityChange.super.setVisible(false, false);
                                DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange = this.this$0;
                                List<Animatable2Compat.AnimationCallback> list = drawableWithAnimatedVisibilityChange.animationCallbacks;
                                if (list != null && !drawableWithAnimatedVisibilityChange.ignoreCallbacks) {
                                    for (Animatable2Compat.AnimationCallback onAnimationEnd : list) {
                                        onAnimationEnd.onAnimationEnd(drawableWithAnimatedVisibilityChange);
                                    }
                                    return;
                                }
                                return;
                            default:
                                super.onAnimationEnd(animator);
                                return;
                        }
                    }

                    public final void onAnimationStart(Animator animator) {
                        switch (1) {
                            case 0:
                                super.onAnimationStart(animator);
                                DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange = this.this$0;
                                List<Animatable2Compat.AnimationCallback> list = drawableWithAnimatedVisibilityChange.animationCallbacks;
                                if (list != null && !drawableWithAnimatedVisibilityChange.ignoreCallbacks) {
                                    for (Animatable2Compat.AnimationCallback animationCallback : list) {
                                        animationCallback.getClass();
                                    }
                                    return;
                                }
                                return;
                            default:
                                super.onAnimationStart(animator);
                                return;
                        }
                    }
                });
            } else {
                throw new IllegalArgumentException("Cannot set hideAnimator while the current hideAnimator is running.");
            }
        }
        if (!isVisible() && !z) {
            return false;
        }
        if (z) {
            valueAnimator = this.showAnimator;
        } else {
            valueAnimator = this.hideAnimator;
        }
        if (!z3) {
            if (valueAnimator.isRunning()) {
                valueAnimator.end();
            } else {
                boolean z5 = this.ignoreCallbacks;
                this.ignoreCallbacks = true;
                for (ValueAnimator end : new ValueAnimator[]{valueAnimator}) {
                    end.end();
                }
                this.ignoreCallbacks = z5;
            }
            return super.setVisible(z, false);
        } else if (z3 && valueAnimator.isRunning()) {
            return false;
        } else {
            if (!z || super.setVisible(z, false)) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (!z ? this.baseSpec.hideAnimationBehavior == 0 : this.baseSpec.showAnimationBehavior == 0) {
                boolean z6 = this.ignoreCallbacks;
                this.ignoreCallbacks = true;
                for (ValueAnimator end2 : new ValueAnimator[]{valueAnimator}) {
                    end2.end();
                }
                this.ignoreCallbacks = z6;
                return z4;
            }
            if (z2 || !valueAnimator.isPaused()) {
                valueAnimator.start();
            } else {
                valueAnimator.resume();
            }
            return z4;
        }
    }

    public final void start() {
        setVisibleInternal(true, true, false);
    }

    public final void stop() {
        setVisibleInternal(false, true, false);
    }

    public final void unregisterAnimationCallback(BaseProgressIndicator.AnonymousClass3 r2) {
        List list = this.animationCallbacks;
        if (list != null && list.contains(r2)) {
            this.animationCallbacks.remove(r2);
            if (this.animationCallbacks.isEmpty()) {
                this.animationCallbacks = null;
            }
        }
    }

    public final boolean setVisible(boolean z, boolean z2, boolean z3) {
        AnimatorDurationScaleProvider animatorDurationScaleProvider2 = this.animatorDurationScaleProvider;
        ContentResolver contentResolver = this.context.getContentResolver();
        animatorDurationScaleProvider2.getClass();
        return setVisibleInternal(z, z2, z3 && Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) > 0.0f);
    }
}
