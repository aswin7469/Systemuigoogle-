package com.google.android.systemui.assist.uihints.edgelights;

import com.android.systemui.assist.ui.EdgeLight;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public interface EdgeLightsListener {
    void onModeStarted(EdgeLightsView.Mode mode);

    void onAssistLightsUpdated(EdgeLightsView.Mode mode, EdgeLight[] edgeLightArr) {
    }
}
