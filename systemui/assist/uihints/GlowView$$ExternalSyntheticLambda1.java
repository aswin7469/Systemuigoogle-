package com.google.android.systemui.assist.uihints;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class GlowView$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ GlowView f$0;

    public /* synthetic */ GlowView$$ExternalSyntheticLambda1(GlowView glowView, int i) {
        this.$r8$classId = i;
        this.f$0 = glowView;
    }

    public final void run() {
        int i = this.$r8$classId;
        GlowView glowView = this.f$0;
        switch (i) {
            case 0:
                int i2 = GlowView.$r8$clinit;
                glowView.updateGlowSizes();
                glowView.post(new GlowView$$ExternalSyntheticLambda1(glowView, 1));
                return;
            default:
                glowView.setGlowsY(glowView.mTranslationY, glowView.mMinimumHeightPx, glowView.mEdgeLights);
                return;
        }
    }
}
