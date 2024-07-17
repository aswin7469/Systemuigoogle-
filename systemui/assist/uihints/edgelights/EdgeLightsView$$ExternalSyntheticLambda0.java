package com.google.android.systemui.assist.uihints.edgelights;

import com.google.android.systemui.assist.uihints.edgelights.mode.Gone;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
                Gone gone = edgeLightsView.mMode;
                edgeLightsListener.onAssistLightsUpdated();
                return;
        }
    }
}
