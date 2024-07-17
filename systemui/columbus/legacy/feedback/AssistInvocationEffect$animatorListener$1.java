package com.google.android.systemui.columbus.legacy.feedback;

import android.animation.Animator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
