package com.google.android.systemui.columbus.legacy.feedback;

import android.animation.ValueAnimator;
import com.google.android.systemui.assist.AssistManagerGoogle;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
