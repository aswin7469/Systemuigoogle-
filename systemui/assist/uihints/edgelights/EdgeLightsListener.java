package com.google.android.systemui.assist.uihints.edgelights;

import com.google.android.systemui.assist.uihints.edgelights.mode.Gone;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public interface EdgeLightsListener {
    void onModeStarted(Gone gone);

    void onAssistLightsUpdated() {
    }
}
