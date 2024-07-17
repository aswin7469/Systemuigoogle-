package com.google.android.systemui.elmyra.gates;

import android.net.Uri;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class KeyguardDeferredSetup$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ KeyguardDeferredSetup f$0;

    public /* synthetic */ KeyguardDeferredSetup$$ExternalSyntheticLambda0(KeyguardDeferredSetup keyguardDeferredSetup) {
        this.f$0 = keyguardDeferredSetup;
    }

    public final void accept(Object obj) {
        KeyguardDeferredSetup keyguardDeferredSetup = this.f$0;
        Uri uri = (Uri) obj;
        boolean z = false;
        if (keyguardDeferredSetup.mSecureSettings.getIntForUser("assist_gesture_setup_complete", 0, -2) != 0) {
            z = true;
        }
        if (keyguardDeferredSetup.mDeferredSetupComplete != z) {
            keyguardDeferredSetup.mDeferredSetupComplete = z;
            keyguardDeferredSetup.notifyListener();
        }
    }
}
