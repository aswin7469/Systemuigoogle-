package com.google.android.systemui.elmyra.feedback;

import com.android.systemui.shared.system.QuickStepContract;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AssistInvocationEffect implements FeedbackEffect {
    public final AssistManagerGoogle mAssistManager;
    public final OpaHomeButton mOpaHomeButton;
    public final OpaLockscreen mOpaLockscreen;

    public AssistInvocationEffect(AssistManagerGoogle assistManagerGoogle, OpaHomeButton opaHomeButton, OpaLockscreen opaLockscreen) {
        this.mAssistManager = assistManagerGoogle;
        this.mOpaHomeButton = opaHomeButton;
        this.mOpaLockscreen = opaLockscreen;
    }

    public final void onProgress(int i, float f) {
        AssistManagerGoogle assistManagerGoogle = this.mAssistManager;
        if (!QuickStepContract.isGesturalMode(assistManagerGoogle.mNavigationMode)) {
            this.mOpaHomeButton.onProgress(i, f);
            this.mOpaLockscreen.getClass();
            return;
        }
        assistManagerGoogle.onInvocationProgress(2, f);
    }

    public final void onRelease() {
        AssistManagerGoogle assistManagerGoogle = this.mAssistManager;
        if (!QuickStepContract.isGesturalMode(assistManagerGoogle.mNavigationMode)) {
            this.mOpaHomeButton.onRelease();
            this.mOpaLockscreen.getClass();
            return;
        }
        assistManagerGoogle.onInvocationProgress(2, 0.0f);
    }

    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        AssistManagerGoogle assistManagerGoogle = this.mAssistManager;
        if (!QuickStepContract.isGesturalMode(assistManagerGoogle.mNavigationMode)) {
            this.mOpaHomeButton.onResolve(detectionProperties);
            this.mOpaLockscreen.getClass();
            return;
        }
        assistManagerGoogle.onInvocationProgress(2, 1.0f);
    }
}
