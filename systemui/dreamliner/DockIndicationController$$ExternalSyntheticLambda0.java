package com.google.android.systemui.dreamliner;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class DockIndicationController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DockIndicationController f$0;

    public /* synthetic */ DockIndicationController$$ExternalSyntheticLambda0(DockIndicationController dockIndicationController, int i) {
        this.$r8$classId = i;
        this.f$0 = dockIndicationController;
    }

    public final void run() {
        int i = this.$r8$classId;
        DockIndicationController dockIndicationController = this.f$0;
        switch (i) {
            case 0:
                if (dockIndicationController.mDozing && dockIndicationController.mDocking) {
                    dockIndicationController.mDockPromo.startAnimation(dockIndicationController.mHidePromoAnimation);
                    return;
                }
                return;
            default:
                if (dockIndicationController.mDocking && dockIndicationController.mDozing) {
                    dockIndicationController.mTopIndicationView.setAccessibilityLiveRegion(0);
                    return;
                }
                return;
        }
    }
}
