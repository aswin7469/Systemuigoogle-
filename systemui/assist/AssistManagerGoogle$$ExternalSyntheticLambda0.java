package com.google.android.systemui.assist;

import com.android.systemui.navigationbar.NavigationModeController;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class AssistManagerGoogle$$ExternalSyntheticLambda0 implements NavigationModeController.ModeChangedListener {
    public final /* synthetic */ AssistManagerGoogle f$0;

    public /* synthetic */ AssistManagerGoogle$$ExternalSyntheticLambda0(AssistManagerGoogle assistManagerGoogle) {
        this.f$0 = assistManagerGoogle;
    }

    public final void onNavigationModeChanged(int i) {
        this.f$0.mNavigationMode = i;
    }
}
