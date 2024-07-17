package com.google.android.systemui.keyguard.domain.interactor;

import com.android.systemui.biometrics.AuthController;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class RefreshRateInteractor {
    public final AlternateBouncerInteractor alternateBouncerInteractor;
    public final AuthController authController;
    public final ChannelFlowTransformLatest requestOverridingMaxRefreshRate;

    public RefreshRateInteractor(BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, KeyguardInteractor keyguardInteractor, AlternateBouncerInteractor alternateBouncerInteractor2, AuthController authController2) {
        this.alternateBouncerInteractor = alternateBouncerInteractor2;
        this.authController = authController2;
        this.requestOverridingMaxRefreshRate = FlowKt.transformLatest(biometricSettingsRepositoryImpl.isFingerprintEnrolledAndEnabled, new RefreshRateInteractor$special$$inlined$flatMapLatest$1((Continuation) null, keyguardInteractor, this));
    }
}
