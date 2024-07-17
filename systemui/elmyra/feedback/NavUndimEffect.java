package com.google.android.systemui.elmyra.feedback;

import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class NavUndimEffect implements FeedbackEffect {
    public final NavigationBarControllerImpl mNavBarController;

    public NavUndimEffect(NavigationBarControllerImpl navigationBarControllerImpl) {
        this.mNavBarController = navigationBarControllerImpl;
    }

    public final void onProgress(int i, float f) {
        this.mNavBarController.touchAutoDim(0);
    }

    public final void onRelease() {
        this.mNavBarController.touchAutoDim(0);
    }

    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        this.mNavBarController.touchAutoDim(0);
    }
}
