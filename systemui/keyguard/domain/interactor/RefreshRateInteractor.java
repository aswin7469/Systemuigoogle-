package com.google.android.systemui.keyguard.domain.interactor;

import com.android.systemui.biometrics.AuthController;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepository;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class RefreshRateInteractor {
    public final AlternateBouncerInteractor alternateBouncerInteractor;
    public final AuthController authController;
    public final ChannelFlowTransformLatest requestOverridingMaxRefreshRate;

    public RefreshRateInteractor(BiometricSettingsRepository biometricSettingsRepository, KeyguardInteractor keyguardInteractor, AlternateBouncerInteractor alternateBouncerInteractor2, AuthController authController2) {
        this.alternateBouncerInteractor = alternateBouncerInteractor2;
        this.authController = authController2;
        this.requestOverridingMaxRefreshRate = FlowKt.transformLatest(((BiometricSettingsRepositoryImpl) biometricSettingsRepository).isFingerprintEnrolledAndEnabled, new RefreshRateInteractor$special$$inlined$flatMapLatest$1((Continuation) null, keyguardInteractor, this));
    }
}
