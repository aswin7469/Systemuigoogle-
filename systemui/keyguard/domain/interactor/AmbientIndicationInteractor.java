package com.google.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.google.android.systemui.keyguard.data.repository.AmbientIndicationRepository;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AmbientIndicationInteractor {
    public final AmbientIndicationRepository ambientIndicationRepository;
    public final ReadonlyStateFlow ambientMusicState;
    public final KeyguardInteractor keyguardInteractor;
    public final ReadonlyStateFlow reverseChargingMessage;
    public final ReadonlyStateFlow wirelessChargingMessage;

    public AmbientIndicationInteractor(AmbientIndicationRepository ambientIndicationRepository2, KeyguardInteractor keyguardInteractor2) {
        this.ambientIndicationRepository = ambientIndicationRepository2;
        this.keyguardInteractor = keyguardInteractor2;
        this.ambientMusicState = new ReadonlyStateFlow(ambientIndicationRepository2.ambientMusic);
        this.reverseChargingMessage = new ReadonlyStateFlow(ambientIndicationRepository2.reverseChargingMessage);
        this.wirelessChargingMessage = new ReadonlyStateFlow(ambientIndicationRepository2.wirelessChargingMessage);
    }
}
