package com.google.android.systemui.assist.uihints;

import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class TouchInsideHandler$$ExternalSyntheticLambda1 implements NavigationModeController.ModeChangedListener {
    public final /* synthetic */ TouchInsideHandler f$0;

    public /* synthetic */ TouchInsideHandler$$ExternalSyntheticLambda1(TouchInsideHandler touchInsideHandler) {
        this.f$0 = touchInsideHandler;
    }

    public final void onNavigationModeChanged(int i) {
        this.f$0.mInGesturalMode = QuickStepContract.isGesturalMode(i);
    }
}
