package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.statusbar.policy.FlashlightController;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ToggleFlashlight$flashlightListener$1 implements FlashlightController.FlashlightListener {
    public final /* synthetic */ ToggleFlashlight this$0;

    public ToggleFlashlight$flashlightListener$1(ToggleFlashlight toggleFlashlight) {
        this.this$0 = toggleFlashlight;
    }

    public final void onFlashlightAvailabilityChanged(boolean z) {
        ToggleFlashlight toggleFlashlight = this.this$0;
        if (!z) {
            toggleFlashlight.handler.removeCallbacks(toggleFlashlight.turnOffFlashlight);
        }
        toggleFlashlight.updateAvailable$6();
    }

    public final void onFlashlightChanged(boolean z) {
        ToggleFlashlight toggleFlashlight = this.this$0;
        if (!z) {
            toggleFlashlight.handler.removeCallbacks(toggleFlashlight.turnOffFlashlight);
        }
        toggleFlashlight.updateAvailable$6();
    }

    public final void onFlashlightError() {
        ToggleFlashlight toggleFlashlight = this.this$0;
        toggleFlashlight.handler.removeCallbacks(toggleFlashlight.turnOffFlashlight);
        toggleFlashlight.updateAvailable$6();
    }
}
