package com.google.android.systemui.elmyra.gates;

import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import com.android.systemui.telephony.TelephonyListenerManager;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class TelephonyActivity extends Gate {
    public boolean mIsCallBlocked;
    public final AnonymousClass1 mPhoneStateListener = new TelephonyCallback.CallStateListener() {
        public final void onCallStateChanged(int i) {
            boolean z;
            TelephonyActivity.this.getClass();
            if (i == 2) {
                z = true;
            } else {
                z = false;
            }
            TelephonyActivity telephonyActivity = TelephonyActivity.this;
            if (z != telephonyActivity.mIsCallBlocked) {
                telephonyActivity.mIsCallBlocked = z;
                telephonyActivity.notifyListener();
            }
        }
    };
    public final TelephonyListenerManager mTelephonyListenerManager;
    public final TelephonyManager mTelephonyManager;

    public TelephonyActivity(Executor executor, TelephonyManager telephonyManager, TelephonyListenerManager telephonyListenerManager) {
        super(executor);
        this.mTelephonyManager = telephonyManager;
        this.mTelephonyListenerManager = telephonyListenerManager;
    }

    public final boolean isBlocked() {
        return this.mIsCallBlocked;
    }

    public final void onActivate() {
        boolean z;
        if (this.mTelephonyManager.getCallState() == 2) {
            z = true;
        } else {
            z = false;
        }
        this.mIsCallBlocked = z;
        this.mTelephonyListenerManager.addCallStateListener(this.mPhoneStateListener);
    }

    public final void onDeactivate() {
        this.mTelephonyListenerManager.removeCallStateListener(this.mPhoneStateListener);
    }
}
