package com.google.android.systemui.fingerprint;

import android.content.Context;
import com.android.systemui.biometrics.FingerprintInteractiveToAuthProvider;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class FingerprintInteractiveToAuthProviderGoogle implements FingerprintInteractiveToAuthProvider {
    public final Context context;
    public final ChannelFlowTransformLatest enabledForCurrentUser;
    public final SecureSettings secureSettings;

    public FingerprintInteractiveToAuthProviderGoogle(Context context2, SecureSettings secureSettings2, SelectedUserInteractor selectedUserInteractor) {
        this.context = context2;
        this.secureSettings = secureSettings2;
        this.enabledForCurrentUser = FlowKt.transformLatest(selectedUserInteractor.selectedUser, new FingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1((Continuation) null, this));
    }
}
