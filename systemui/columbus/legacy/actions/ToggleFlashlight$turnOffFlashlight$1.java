package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.statusbar.policy.FlashlightControllerImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ToggleFlashlight$turnOffFlashlight$1 implements Runnable {
    public final /* synthetic */ ToggleFlashlight this$0;

    public ToggleFlashlight$turnOffFlashlight$1(ToggleFlashlight toggleFlashlight) {
        this.this$0 = toggleFlashlight;
    }

    public final void run() {
        ((FlashlightControllerImpl) this.this$0.flashlightController).setFlashlight(false);
    }
}
