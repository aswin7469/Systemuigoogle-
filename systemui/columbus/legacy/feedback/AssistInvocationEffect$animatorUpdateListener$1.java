package com.google.android.systemui.columbus.legacy.feedback;

import android.animation.ValueAnimator;
import com.google.android.systemui.assist.AssistManagerGoogle;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AssistInvocationEffect$animatorUpdateListener$1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AssistInvocationEffect this$0;

    public AssistInvocationEffect$animatorUpdateListener$1(AssistInvocationEffect assistInvocationEffect) {
        this.this$0 = assistInvocationEffect;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        AssistInvocationEffect assistInvocationEffect = this.this$0;
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        assistInvocationEffect.progress = floatValue;
        AssistManagerGoogle assistManagerGoogle = assistInvocationEffect.assistManager;
        if (assistManagerGoogle != null) {
            assistManagerGoogle.onInvocationProgress(2, floatValue);
        }
    }
}
