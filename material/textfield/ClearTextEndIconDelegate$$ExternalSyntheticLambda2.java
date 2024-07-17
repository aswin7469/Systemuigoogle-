package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import com.google.android.material.internal.CheckableImageButton;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ClearTextEndIconDelegate$$ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ClearTextEndIconDelegate f$0;

    public /* synthetic */ ClearTextEndIconDelegate$$ExternalSyntheticLambda2(ClearTextEndIconDelegate clearTextEndIconDelegate, int i) {
        this.$r8$classId = i;
        this.f$0 = clearTextEndIconDelegate;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.$r8$classId;
        ClearTextEndIconDelegate clearTextEndIconDelegate = this.f$0;
        clearTextEndIconDelegate.getClass();
        switch (i) {
            case 0:
                clearTextEndIconDelegate.endIconView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                return;
            default:
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CheckableImageButton checkableImageButton = clearTextEndIconDelegate.endIconView;
                checkableImageButton.setScaleX(floatValue);
                checkableImageButton.setScaleY(floatValue);
                return;
        }
    }
}
