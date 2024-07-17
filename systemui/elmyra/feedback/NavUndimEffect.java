package com.google.android.systemui.elmyra.feedback;

import com.android.systemui.navigationbar.NavigationBarController;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NavUndimEffect implements FeedbackEffect {
    public final NavigationBarController mNavBarController;

    public NavUndimEffect(NavigationBarController navigationBarController) {
        this.mNavBarController = navigationBarController;
    }

    public final void onProgress(int i, float f) {
        ((NavigationBarControllerImpl) this.mNavBarController).touchAutoDim(0);
    }

    public final void onRelease() {
        ((NavigationBarControllerImpl) this.mNavBarController).touchAutoDim(0);
    }

    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        ((NavigationBarControllerImpl) this.mNavBarController).touchAutoDim(0);
    }
}
