package com.google.android.systemui.columbus.legacy.actions;

import android.telephony.TelephonyCallback;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
