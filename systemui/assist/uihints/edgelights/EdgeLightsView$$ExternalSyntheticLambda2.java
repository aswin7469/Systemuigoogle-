package com.google.android.systemui.assist.uihints.edgelights;

import com.android.systemui.assist.ui.EdgeLight;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class EdgeLightsView$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ EdgeLightsView f$0;
    public final /* synthetic */ EdgeLight[] f$1;

    public /* synthetic */ EdgeLightsView$$ExternalSyntheticLambda2(EdgeLightsView edgeLightsView, EdgeLight[] edgeLightArr) {
        this.f$0 = edgeLightsView;
        this.f$1 = edgeLightArr;
    }

    public final void run() {
        EdgeLightsView edgeLightsView = this.f$0;
        EdgeLight[] edgeLightArr = this.f$1;
        int i = EdgeLightsView.$r8$clinit;
        edgeLightsView.getClass();
        edgeLightsView.mAssistLights = EdgeLight.copy(edgeLightArr);
        edgeLightsView.mListeners.forEach(new EdgeLightsView$$ExternalSyntheticLambda0(edgeLightsView, 1));
        edgeLightsView.invalidate();
    }
}
