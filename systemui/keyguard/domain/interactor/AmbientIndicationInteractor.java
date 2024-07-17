package com.google.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.google.android.systemui.keyguard.data.repository.AmbientIndicationRepository;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AmbientIndicationInteractor {
    public final AmbientIndicationRepository ambientIndicationRepository;
    public final ReadonlyStateFlow ambientMusicState;
    public final KeyguardInteractor keyguardInteractor;
    public final ReadonlyStateFlow reverseChargingMessage;
    public final ReadonlyStateFlow wirelessChargingMessage;

    public AmbientIndicationInteractor(AmbientIndicationRepository ambientIndicationRepository2, KeyguardInteractor keyguardInteractor2) {
        this.ambientMusicState = new ReadonlyStateFlow(ambientIndicationRepository2.ambientMusic);
        this.reverseChargingMessage = new ReadonlyStateFlow(ambientIndicationRepository2.reverseChargingMessage);
        this.wirelessChargingMessage = new ReadonlyStateFlow(ambientIndicationRepository2.wirelessChargingMessage);
    }
}
