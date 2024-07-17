package com.google.android.systemui.dagger;

import android.content.Context;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.fingerprint.FingerprintInteractiveToAuthProviderGoogle;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SystemUIGoogleModule_ProvideFingerprintInteractiveToAuthProviderFactory implements Provider {
    public static FingerprintInteractiveToAuthProviderGoogle provideFingerprintInteractiveToAuthProvider(Context context, SecureSettings secureSettings, SelectedUserInteractor selectedUserInteractor) {
        return new FingerprintInteractiveToAuthProviderGoogle(context, secureSettings, selectedUserInteractor);
    }
}
