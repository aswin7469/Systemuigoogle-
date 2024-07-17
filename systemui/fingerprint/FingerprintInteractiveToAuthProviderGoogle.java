package com.google.android.systemui.fingerprint;

import android.content.Context;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class FingerprintInteractiveToAuthProviderGoogle {
    public final Context context;
    public final Flow enabledForCurrentUser;
    public final SecureSettings secureSettings;

    public FingerprintInteractiveToAuthProviderGoogle(CoroutineDispatcher coroutineDispatcher, Context context2, SecureSettings secureSettings2, SelectedUserInteractor selectedUserInteractor) {
        this.context = context2;
        this.secureSettings = secureSettings2;
        this.enabledForCurrentUser = FlowKt.flowOn(FlowKt.transformLatest(selectedUserInteractor.selectedUser, new FingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1((Continuation) null, this)), coroutineDispatcher);
    }
}
