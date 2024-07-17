package com.google.android.systemui.dreamliner;

import android.content.Intent;
import android.view.View;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
