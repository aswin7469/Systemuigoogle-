package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.statusbar.policy.FlashlightController;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
