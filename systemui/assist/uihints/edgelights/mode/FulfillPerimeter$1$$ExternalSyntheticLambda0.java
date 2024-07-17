package com.google.android.systemui.assist.uihints.edgelights.mode;

import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;
import com.google.android.systemui.assist.uihints.edgelights.mode.FulfillPerimeter;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class FulfillPerimeter$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ FulfillPerimeter.AnonymousClass1 f$0;
    public final /* synthetic */ EdgeLightsView f$1;

    public /* synthetic */ FulfillPerimeter$1$$ExternalSyntheticLambda0(FulfillPerimeter.AnonymousClass1 r1, EdgeLightsView edgeLightsView) {
        this.f$0 = r1;
        this.f$1 = edgeLightsView;
    }

    public final void run() {
        this.f$1.commitModeTransition(FulfillPerimeter.this.mNextMode);
    }
}
