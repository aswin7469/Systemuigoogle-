package com.google.android.material.textfield;

import android.animation.ValueAnimator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class DropdownMenuEndIconDelegate$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ DropdownMenuEndIconDelegate f$0;

    public /* synthetic */ DropdownMenuEndIconDelegate$$ExternalSyntheticLambda0(DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate) {
        this.f$0 = dropdownMenuEndIconDelegate;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate = this.f$0;
        dropdownMenuEndIconDelegate.getClass();
        dropdownMenuEndIconDelegate.endIconView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
