package com.google.android.systemui.columbus.legacy.feedback;

import android.animation.Animator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AssistInvocationEffect$animatorListener$1 implements Animator.AnimatorListener {
    public final /* synthetic */ AssistInvocationEffect this$0;

    public AssistInvocationEffect$animatorListener$1(AssistInvocationEffect assistInvocationEffect) {
        this.this$0 = assistInvocationEffect;
    }

    public final void onAnimationCancel(Animator animator) {
        this.this$0.progress = 0.0f;
    }

    public final void onAnimationEnd(Animator animator) {
        this.this$0.progress = 0.0f;
    }

    public final void onAnimationRepeat(Animator animator) {
    }

    public final void onAnimationStart(Animator animator) {
    }
}
