package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.util.Log;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.google.android.systemui.assist.OpaEnabledListener;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LaunchOpa$opaEnabledListener$1 implements OpaEnabledListener {
    public final /* synthetic */ LaunchOpa this$0;

    public LaunchOpa$opaEnabledListener$1(LaunchOpa launchOpa) {
        this.this$0 = launchOpa;
    }

    public final void onOpaEnabledReceived(Context context, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5;
        boolean z6 = true;
        LaunchOpa launchOpa = this.this$0;
        if (z2 || launchOpa.enableForAnyAssistant) {
            z5 = true;
        } else {
            z5 = false;
        }
        StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("eligible: ", ", supported: ", ", opa: ", z, z5);
        m.append(z3);
        Log.i("Columbus/LaunchOpa", m.toString());
        if (!z || !z5 || !z3) {
            z6 = false;
        }
        launchOpa.isOpaEnabled = z6;
        launchOpa.updateAvailable$5();
    }
}
