package com.google.android.systemui.dagger;

import android.content.Context;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.fingerprint.FingerprintInteractiveToAuthProviderGoogle;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class SystemUIGoogleModule_ProvideFingerprintInteractiveToAuthProviderFactory implements Provider {
    public static FingerprintInteractiveToAuthProviderGoogle provideFingerprintInteractiveToAuthProvider(CoroutineDispatcher coroutineDispatcher, Context context, SecureSettings secureSettings, SelectedUserInteractor selectedUserInteractor) {
        return new FingerprintInteractiveToAuthProviderGoogle(coroutineDispatcher, context, secureSettings, selectedUserInteractor);
    }
}
