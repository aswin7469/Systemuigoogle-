package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.os.Handler;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.statusbar.policy.FlashlightController;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ToggleFlashlight extends UserAction {
    public static final long FLASHLIGHT_TIMEOUT = TimeUnit.MINUTES.toMillis(2);
    public final FlashlightController flashlightController;
    public final ToggleFlashlight$flashlightListener$1 flashlightListener;
    public final Handler handler;
    public final String tag = "ToggleFlashlight";
    public final ToggleFlashlight$turnOffFlashlight$1 turnOffFlashlight;
    public final UiEventLogger uiEventLogger;

    public ToggleFlashlight(Context context, FlashlightController flashlightController2, Handler handler2, UiEventLogger uiEventLogger2) {
        super(context, (Set) null);
        this.flashlightController = flashlightController2;
        this.handler = handler2;
        this.uiEventLogger = uiEventLogger2;
        ToggleFlashlight$flashlightListener$1 toggleFlashlight$flashlightListener$1 = new ToggleFlashlight$flashlightListener$1(this);
        this.flashlightListener = toggleFlashlight$flashlightListener$1;
        this.turnOffFlashlight = new ToggleFlashlight$turnOffFlashlight$1(this);
        ((FlashlightControllerImpl) flashlightController2).addCallback(toggleFlashlight$flashlightListener$1);
        updateAvailable$6();
    }

    public final boolean availableOnLockscreen() {
        return true;
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        Handler handler2 = this.handler;
        ToggleFlashlight$turnOffFlashlight$1 toggleFlashlight$turnOffFlashlight$1 = this.turnOffFlashlight;
        handler2.removeCallbacks(toggleFlashlight$turnOffFlashlight$1);
        FlashlightControllerImpl flashlightControllerImpl = (FlashlightControllerImpl) this.flashlightController;
        boolean z = !flashlightControllerImpl.isEnabled();
        flashlightControllerImpl.setFlashlight(z);
        if (z) {
            handler2.postDelayed(toggleFlashlight$turnOffFlashlight$1, FLASHLIGHT_TIMEOUT);
        }
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_FLASHLIGHT_TOGGLE);
    }

    public final void updateAvailable$6() {
        boolean z;
        boolean z2;
        FlashlightControllerImpl flashlightControllerImpl = (FlashlightControllerImpl) this.flashlightController;
        if (flashlightControllerImpl.mHasFlashlight) {
            synchronized (flashlightControllerImpl) {
                z2 = flashlightControllerImpl.mTorchAvailable;
            }
            if (z2) {
                z = true;
                setAvailable(z);
            }
        }
        z = false;
        setAvailable(z);
    }
}
