package com.google.android.systemui.elmyra.feedback;

import com.android.systemui.shared.system.QuickStepContract;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
