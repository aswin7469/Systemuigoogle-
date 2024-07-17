package com.google.android.systemui.assist.uihints;

import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class NgaMessageHandler$$ExternalSyntheticLambda0 implements NavigationModeController.ModeChangedListener {
    public final /* synthetic */ NgaMessageHandler f$0;

    public /* synthetic */ NgaMessageHandler$$ExternalSyntheticLambda0(NgaMessageHandler ngaMessageHandler) {
        this.f$0 = ngaMessageHandler;
    }

    public final void onNavigationModeChanged(int i) {
        NgaMessageHandler ngaMessageHandler = this.f$0;
        ngaMessageHandler.getClass();
        ngaMessageHandler.mIsGestureNav = QuickStepContract.isGesturalMode(i);
    }
}
