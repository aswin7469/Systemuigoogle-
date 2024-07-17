package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.statusbar.policy.FlashlightControllerImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ToggleFlashlight$turnOffFlashlight$1 implements Runnable {
    public final /* synthetic */ ToggleFlashlight this$0;

    public ToggleFlashlight$turnOffFlashlight$1(ToggleFlashlight toggleFlashlight) {
        this.this$0 = toggleFlashlight;
    }

    public final void run() {
        ((FlashlightControllerImpl) this.this$0.flashlightController).setFlashlight(false);
    }
}
