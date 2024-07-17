package com.google.android.systemui.elmyra.gates;

import android.net.Uri;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
