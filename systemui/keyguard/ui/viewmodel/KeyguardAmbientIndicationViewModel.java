package com.google.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.doze.util.BurnInHelperWrapper;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.google.android.systemui.keyguard.domain.interactor.AmbientIndicationInteractor;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardAmbientIndicationViewModel {
    public final ReadonlyStateFlow ambientIndicationMusicState;
    public final BurnInHelperWrapper burnInHelperWrapper;
    public final Flow indicationAreaTranslationX;
    public final KeyguardInteractor keyguardInteractor;
    public final ReadonlyStateFlow reverseChargingMessage;
    public final ReadonlyStateFlow wirelessChargingMessage;

    public KeyguardAmbientIndicationViewModel(AmbientIndicationInteractor ambientIndicationInteractor, KeyguardInteractor keyguardInteractor2, BurnInHelperWrapper burnInHelperWrapper2) {
        ReadonlyStateFlow readonlyStateFlow = ambientIndicationInteractor.ambientMusicState;
        FlowKt.distinctUntilChanged(new KeyguardAmbientIndicationViewModel$special$$inlined$map$1(keyguardInteractor2.clockPosition));
        ReadonlyStateFlow readonlyStateFlow2 = ambientIndicationInteractor.reverseChargingMessage;
        ReadonlyStateFlow readonlyStateFlow3 = ambientIndicationInteractor.wirelessChargingMessage;
    }
}
