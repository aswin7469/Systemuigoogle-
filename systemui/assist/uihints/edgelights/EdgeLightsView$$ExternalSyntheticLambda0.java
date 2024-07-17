package com.google.android.systemui.assist.uihints.edgelights;

import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class EdgeLightsView$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ EdgeLightsView f$0;

    public /* synthetic */ EdgeLightsView$$ExternalSyntheticLambda0(EdgeLightsView edgeLightsView, int i) {
        this.$r8$classId = i;
        this.f$0 = edgeLightsView;
    }

    public final void accept(Object obj) {
        int i = this.$r8$classId;
        EdgeLightsView edgeLightsView = this.f$0;
        EdgeLightsListener edgeLightsListener = (EdgeLightsListener) obj;
        switch (i) {
            case 0:
                edgeLightsListener.onModeStarted(edgeLightsView.mMode);
                return;
            default:
                edgeLightsListener.onAssistLightsUpdated(edgeLightsView.mMode, edgeLightsView.mAssistLights);
                return;
        }
    }
}
