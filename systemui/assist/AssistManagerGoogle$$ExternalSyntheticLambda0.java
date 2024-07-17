package com.google.android.systemui.assist;

import com.android.systemui.navigationbar.NavigationModeController;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class AssistManagerGoogle$$ExternalSyntheticLambda0 implements NavigationModeController.ModeChangedListener {
    public final /* synthetic */ AssistManagerGoogle f$0;

    public /* synthetic */ AssistManagerGoogle$$ExternalSyntheticLambda0(AssistManagerGoogle assistManagerGoogle) {
        this.f$0 = assistManagerGoogle;
    }

    public final void onNavigationModeChanged(int i) {
        this.f$0.mNavigationMode = i;
    }
}
