package com.google.android.systemui.fingerprint;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$getCurrentSettingValue$1 extends Lambda implements Function0 {
    final /* synthetic */ int $currentUserId;
    final /* synthetic */ FingerprintInteractiveToAuthProviderGoogle this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$getCurrentSettingValue$1(FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle, int i) {
        super(0);
        this.this$0 = fingerprintInteractiveToAuthProviderGoogle;
        this.$currentUserId = i;
    }

    public final Object invoke() {
        boolean z;
        FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle = this.this$0;
        if (fingerprintInteractiveToAuthProviderGoogle.secureSettings.getIntForUser("sfps_performant_auth_enabled_v2", fingerprintInteractiveToAuthProviderGoogle.context.getResources().getBoolean(17891807) ? 1 : 0, this.$currentUserId) == 0) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
