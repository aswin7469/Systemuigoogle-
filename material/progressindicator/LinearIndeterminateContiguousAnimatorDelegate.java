package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Property;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.progressindicator.BaseProgressIndicator;
import java.util.Arrays;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LinearIndeterminateContiguousAnimatorDelegate extends IndeterminateAnimatorDelegate {
    public static final AnonymousClass2 ANIMATION_FRACTION = new Property(Float.class, "animationFraction");
    public float animationFraction;
    public ObjectAnimator animator;
    public final LinearProgressIndicatorSpec baseSpec;
    public boolean dirtyColors;
    public final FastOutSlowInInterpolator interpolator;
    public int newIndicatorColorIndex = 1;

    /* renamed from: com.google.android.material.progressindicator.LinearIndeterminateContiguousAnimatorDelegate$2  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass2 extends Property {
        public final Object get(Object obj) {
            return Float.valueOf(((LinearIndeterminateContiguousAnimatorDelegate) obj).animationFraction);
        }

        public final void set(Object obj, Object obj2) {
            ((LinearIndeterminateContiguousAnimatorDelegate) obj).setAnimationFraction(((Float) obj2).floatValue());
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [androidx.interpolator.view.animation.FastOutSlowInInterpolator, java.lang.Object] */
    public LinearIndeterminateContiguousAnimatorDelegate(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(3);
        this.baseSpec = linearProgressIndicatorSpec;
        this.interpolator = new Object();
    }

    public final void cancelAnimatorImmediately() {
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    public void resetPropertiesForNewStart() {
        this.dirtyColors = true;
        this.newIndicatorColorIndex = 1;
        Arrays.fill(this.segmentColors, MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[0], this.drawable.totalAlpha));
    }

    public void setAnimationFraction(float f) {
        this.animationFraction = f;
        float[] fArr = this.segmentPositions;
        fArr[0] = 0.0f;
        float f2 = ((float) ((int) (f * 333.0f))) / ((float) 667);
        FastOutSlowInInterpolator fastOutSlowInInterpolator = this.interpolator;
        float interpolation = fastOutSlowInInterpolator.getInterpolation(f2);
        fArr[2] = interpolation;
        fArr[1] = interpolation;
        float interpolation2 = fastOutSlowInInterpolator.getInterpolation(f2 + 0.49925038f);
        fArr[4] = interpolation2;
        fArr[3] = interpolation2;
        fArr[5] = 1.0f;
        if (this.dirtyColors && interpolation2 < 1.0f) {
            int[] iArr = this.segmentColors;
            iArr[2] = iArr[1];
            iArr[1] = iArr[0];
            iArr[0] = MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[this.newIndicatorColorIndex], this.drawable.totalAlpha);
            this.dirtyColors = false;
        }
        this.drawable.invalidateSelf();
    }

    public final void startAnimator() {
        if (this.animator == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, new float[]{0.0f, 1.0f});
            this.animator = ofFloat;
            ofFloat.setDuration(333);
            this.animator.setInterpolator((TimeInterpolator) null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter() {
                public final void onAnimationRepeat(Animator animator) {
                    super.onAnimationRepeat(animator);
                    LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate = LinearIndeterminateContiguousAnimatorDelegate.this;
                    linearIndeterminateContiguousAnimatorDelegate.newIndicatorColorIndex = (linearIndeterminateContiguousAnimatorDelegate.newIndicatorColorIndex + 1) % linearIndeterminateContiguousAnimatorDelegate.baseSpec.indicatorColors.length;
                    linearIndeterminateContiguousAnimatorDelegate.dirtyColors = true;
                }
            });
        }
        resetPropertiesForNewStart();
        this.animator.start();
    }

    public final void registerAnimatorsCompleteCallback(BaseProgressIndicator.AnonymousClass3 r1) {
    }

    public final void requestCancelAnimatorAfterCurrentCycle() {
    }

    public final void unregisterAnimatorsCompleteCallback() {
    }
}
