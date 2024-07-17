package com.google.android.systemui.keyguard.data.repository;

import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AmbientIndicationRepository {
    public final StateFlowImpl ambientMusic = StateFlowKt.MutableStateFlow((Object) null);
    public final StateFlowImpl reverseChargingMessage = StateFlowKt.MutableStateFlow("");
    public final StateFlowImpl wirelessChargingMessage = StateFlowKt.MutableStateFlow("");
}
