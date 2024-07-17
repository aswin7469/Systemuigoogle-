package com.google.android.systemui.assist.uihints.edgelights;

import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;
import com.google.android.systemui.assist.uihints.edgelights.mode.AssistantModeChangeEvent;
import com.google.android.systemui.assist.uihints.edgelights.mode.FulfillBottom;
import com.google.android.systemui.assist.uihints.edgelights.mode.FulfillPerimeter;
import com.google.android.systemui.assist.uihints.edgelights.mode.FullListening;
import com.google.android.systemui.assist.uihints.edgelights.mode.Gone;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class EdgeLightsController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ EdgeLightsController f$0;
    public final /* synthetic */ EdgeLightsView.Mode f$1;

    public /* synthetic */ EdgeLightsController$$ExternalSyntheticLambda0(EdgeLightsController edgeLightsController, EdgeLightsView.Mode mode) {
        this.f$0 = edgeLightsController;
        this.f$1 = mode;
    }

    public final void run() {
        AssistantModeChangeEvent assistantModeChangeEvent;
        EdgeLightsController edgeLightsController = this.f$0;
        EdgeLightsView.Mode mode = this.f$1;
        EdgeLightsView edgeLightsView = edgeLightsController.mEdgeLightsView;
        edgeLightsView.mMode.onNewModeRequest(edgeLightsView, mode);
        AssistantModeChangeEvent.Companion.getClass();
        if (mode instanceof Gone) {
            assistantModeChangeEvent = AssistantModeChangeEvent.ASSISTANT_SESSION_MODE_GONE;
        } else if (mode instanceof FullListening) {
            assistantModeChangeEvent = AssistantModeChangeEvent.ASSISTANT_SESSION_MODE_FULL_LISTENING;
        } else if (mode instanceof FulfillBottom) {
            assistantModeChangeEvent = AssistantModeChangeEvent.ASSISTANT_SESSION_MODE_FULFILL_BOTTOM;
        } else if (mode instanceof FulfillPerimeter) {
            assistantModeChangeEvent = AssistantModeChangeEvent.ASSISTANT_SESSION_MODE_FULFILL_PERIMETER;
        } else {
            assistantModeChangeEvent = AssistantModeChangeEvent.ASSISTANT_SESSION_MODE_UNKNOWN;
        }
        edgeLightsController.mAssistLogger.reportAssistantSessionEvent(assistantModeChangeEvent);
        mode.logState();
    }
}
