package com.google.android.systemui.assist;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
