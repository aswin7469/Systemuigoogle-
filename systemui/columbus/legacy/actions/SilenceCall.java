package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SilenceCall extends Action {
    public boolean isPhoneRinging;
    public final SilenceCall$phoneStateListener$1 phoneStateListener = new SilenceCall$phoneStateListener$1(this);
    public final SilenceAlertsDisabled silenceAlertsDisabled;
    public final String tag = "Columbus/SilenceCall";
    public final Lazy telecomManager;
    public final Lazy telephonyListenerManager;
    public final Lazy telephonyManager;

    public SilenceCall(Context context, SilenceAlertsDisabled silenceAlertsDisabled2, Lazy lazy, Lazy lazy2, Lazy lazy3) {
        super(context, (Set) null);
        this.silenceAlertsDisabled = silenceAlertsDisabled2;
        this.telecomManager = lazy;
        this.telephonyManager = lazy2;
        this.telephonyListenerManager = lazy3;
        silenceAlertsDisabled2.registerListener(new SilenceCall$gateListener$1(this));
        updatePhoneStateListener();
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        ((TelecomManager) this.telecomManager.get()).silenceRinger();
    }

    public final String toString() {
        String action = super.toString();
        boolean z = this.isPhoneRinging;
        return action + " [isPhoneRinging -> " + z + "]";
    }

    public final void updatePhoneStateListener() {
        boolean z;
        SilenceAlertsDisabled silenceAlertsDisabled2 = this.silenceAlertsDisabled;
        boolean isBlocking = silenceAlertsDisabled2.isBlocking();
        SilenceCall$phoneStateListener$1 silenceCall$phoneStateListener$1 = this.phoneStateListener;
        Lazy lazy = this.telephonyListenerManager;
        if (isBlocking) {
            ((TelephonyListenerManager) lazy.get()).removeCallStateListener(silenceCall$phoneStateListener$1);
        } else {
            ((TelephonyListenerManager) lazy.get()).addCallStateListener(silenceCall$phoneStateListener$1);
        }
        boolean z2 = false;
        if (((TelephonyManager) this.telephonyManager.get()).getCallState() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.isPhoneRinging = z;
        if (!silenceAlertsDisabled2.isBlocking() && this.isPhoneRinging) {
            z2 = true;
        }
        setAvailable(z2);
    }
}
