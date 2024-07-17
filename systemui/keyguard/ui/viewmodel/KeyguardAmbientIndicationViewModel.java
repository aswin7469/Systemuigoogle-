package com.google.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.domain.interactor.BurnInInteractor;
import com.google.android.systemui.keyguard.domain.interactor.AmbientIndicationInteractor;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class KeyguardAmbientIndicationViewModel {
    public final ReadonlyStateFlow ambientIndicationMusicState;
    public final KeyguardAmbientIndicationViewModel$special$$inlined$map$1 indicationAreaTranslationX;
    public final KeyguardAmbientIndicationViewModel$special$$inlined$map$1 indicationAreaTranslationY;
    public final ReadonlyStateFlow reverseChargingMessage;
    public final ReadonlyStateFlow wirelessChargingMessage;

    public KeyguardAmbientIndicationViewModel(AmbientIndicationInteractor ambientIndicationInteractor, BurnInInteractor burnInInteractor) {
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(burnInInteractor.burnIn(2131165726));
        ReadonlyStateFlow readonlyStateFlow = ambientIndicationInteractor.ambientMusicState;
        new KeyguardAmbientIndicationViewModel$special$$inlined$map$1(distinctUntilChanged, 0);
        ReadonlyStateFlow readonlyStateFlow2 = ambientIndicationInteractor.reverseChargingMessage;
        ReadonlyStateFlow readonlyStateFlow3 = ambientIndicationInteractor.wirelessChargingMessage;
        new KeyguardAmbientIndicationViewModel$special$$inlined$map$1(distinctUntilChanged, 1);
    }
}
