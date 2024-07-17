package com.google.android.systemui.assist.uihints;

import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class TouchInsideHandler$$ExternalSyntheticLambda1 implements NavigationModeController.ModeChangedListener {
    public final /* synthetic */ TouchInsideHandler f$0;

    public /* synthetic */ TouchInsideHandler$$ExternalSyntheticLambda1(TouchInsideHandler touchInsideHandler) {
        this.f$0 = touchInsideHandler;
    }

    public final void onNavigationModeChanged(int i) {
        TouchInsideHandler touchInsideHandler = this.f$0;
        touchInsideHandler.getClass();
        boolean isGesturalMode = QuickStepContract.isGesturalMode(i);
        touchInsideHandler.mInGesturalMode = isGesturalMode;
        if (isGesturalMode) {
            touchInsideHandler.mGuardLocked = false;
            touchInsideHandler.mGuarded = false;
        }
    }
}
