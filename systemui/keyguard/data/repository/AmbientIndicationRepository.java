package com.google.android.systemui.keyguard.data.repository;

import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AmbientIndicationRepository {
    public final StateFlowImpl ambientMusic = StateFlowKt.MutableStateFlow((Object) null);
    public final StateFlowImpl reverseChargingMessage = StateFlowKt.MutableStateFlow("");
    public final StateFlowImpl wirelessChargingMessage = StateFlowKt.MutableStateFlow("");
}
