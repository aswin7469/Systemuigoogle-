package com.google.android.systemui.columbus.legacy.actions;

import android.telephony.TelephonyCallback;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SilenceCall$phoneStateListener$1 implements TelephonyCallback.CallStateListener {
    public final /* synthetic */ SilenceCall this$0;

    public SilenceCall$phoneStateListener$1(SilenceCall silenceCall) {
        this.this$0 = silenceCall;
    }

    public final void onCallStateChanged(int i) {
        boolean z;
        SilenceCall silenceCall = this.this$0;
        silenceCall.getClass();
        boolean z2 = false;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        silenceCall.isPhoneRinging = z;
        SilenceCall silenceCall2 = this.this$0;
        if (!silenceCall2.silenceAlertsDisabled.isBlocking() && silenceCall2.isPhoneRinging) {
            z2 = true;
        }
        silenceCall2.setAvailable(z2);
    }
}
