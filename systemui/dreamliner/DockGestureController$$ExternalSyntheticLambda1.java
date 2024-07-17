package com.google.android.systemui.dreamliner;

import android.content.Intent;
import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class DockGestureController$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ DockGestureController f$0;

    public /* synthetic */ DockGestureController$$ExternalSyntheticLambda1(DockGestureController dockGestureController) {
        this.f$0 = dockGestureController;
    }

    public final void onClick(View view) {
        DockGestureController dockGestureController = this.f$0;
        dockGestureController.hideGear();
        dockGestureController.sendProtectedBroadcast(new Intent("com.google.android.systemui.dreamliner.SETTINGS"));
    }
}
