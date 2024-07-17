package com.google.android.systemui.assist;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class OpaLayout$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ OpaLayout f$0;

    public /* synthetic */ OpaLayout$$ExternalSyntheticLambda0(OpaLayout opaLayout, int i) {
        this.$r8$classId = i;
        this.f$0 = opaLayout;
    }

    public final void run() {
        int i = this.$r8$classId;
        OpaLayout opaLayout = this.f$0;
        switch (i) {
            case 0:
                if (opaLayout.mCurrentAnimators.isEmpty()) {
                    opaLayout.startDiamondAnimation();
                    return;
                }
                return;
            default:
                opaLayout.getOpaEnabled();
                return;
        }
    }
}
