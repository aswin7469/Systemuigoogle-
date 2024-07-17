package com.google.android.systemui.columbus.legacy.feedback;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AssistInvocationEffect implements FeedbackEffect {
    public Animator animation;
    public final AssistInvocationEffect$animatorListener$1 animatorListener = new AssistInvocationEffect$animatorListener$1(this);
    public final AssistInvocationEffect$animatorUpdateListener$1 animatorUpdateListener = new AssistInvocationEffect$animatorUpdateListener$1(this);
    public final AssistManagerGoogle assistManager;
    public float progress;

    public AssistInvocationEffect(AssistManagerGoogle assistManagerGoogle) {
        this.assistManager = assistManagerGoogle;
    }

    public final void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties) {
        Animator animator;
        Animator animator2;
        if (i == 0) {
            Animator animator3 = this.animation;
            if (!(animator3 == null || !animator3.isRunning() || (animator = this.animation) == null)) {
                animator.cancel();
            }
            this.animation = null;
        } else if (i == 1) {
            Animator animator4 = this.animation;
            if (!(animator4 == null || !animator4.isRunning() || (animator2 = this.animation) == null)) {
                animator2.cancel();
            }
            this.animation = null;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.progress, 1.0f});
            ofFloat.setDuration(200);
            ofFloat.setInterpolator(new DecelerateInterpolator());
            ofFloat.addUpdateListener(this.animatorUpdateListener);
            ofFloat.addListener(this.animatorListener);
            this.animation = ofFloat;
            ofFloat.start();
        }
    }
}
