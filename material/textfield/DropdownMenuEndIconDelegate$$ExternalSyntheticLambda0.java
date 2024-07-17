package com.google.android.material.textfield;

import android.animation.ValueAnimator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
