package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.google.android.systemui.elmyra.UserContentObserver;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SilenceCall extends Action {
    public final Context mContext;
    public boolean mIsPhoneRinging;
    public final AnonymousClass1 mPhoneStateListener = new TelephonyCallback.CallStateListener() {
        public final void onCallStateChanged(int i) {
            SilenceCall.this.getClass();
            boolean z = true;
            if (i != 1) {
                z = false;
            }
            SilenceCall silenceCall = SilenceCall.this;
            if (silenceCall.mIsPhoneRinging != z) {
                silenceCall.mIsPhoneRinging = z;
                silenceCall.notifyListener();
            }
        }
    };
    public boolean mSilenceSettingEnabled;
    public final TelecomManager mTelecomManager;
    public final TelephonyListenerManager mTelephonyListenerManager;
    public final TelephonyManager mTelephonyManager;

    public SilenceCall(Context context, Executor executor, TelephonyListenerManager telephonyListenerManager) {
        super(executor, (List) null);
        this.mContext = context;
        this.mTelecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        this.mTelephonyListenerManager = telephonyListenerManager;
        updatePhoneStateListener();
        new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_silence_alerts_enabled"), new SilenceCall$$ExternalSyntheticLambda0(this), true);
    }

    public final boolean isAvailable() {
        if (this.mSilenceSettingEnabled) {
            return this.mIsPhoneRinging;
        }
        return false;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.mTelecomManager.silenceRinger();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mSilenceSettingEnabled -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mSilenceSettingEnabled, "]");
    }

    public final void updatePhoneStateListener() {
        boolean z;
        boolean z2 = true;
        if (Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "assist_gesture_silence_alerts_enabled", 1, -2) != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z != this.mSilenceSettingEnabled) {
            this.mSilenceSettingEnabled = z;
            AnonymousClass1 r3 = this.mPhoneStateListener;
            TelephonyListenerManager telephonyListenerManager = this.mTelephonyListenerManager;
            if (z) {
                telephonyListenerManager.addCallStateListener(r3);
            } else {
                telephonyListenerManager.removeCallStateListener(r3);
            }
            if (this.mTelephonyManager.getCallState() != 1) {
                z2 = false;
            }
            this.mIsPhoneRinging = z2;
            notifyListener();
        }
    }
}
