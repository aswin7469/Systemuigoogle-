package com.google.android.systemui.assist.uihints;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
