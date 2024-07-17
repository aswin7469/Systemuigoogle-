package com.google.android.systemui.power;

import com.google.android.systemui.googlebattery.AdaptiveChargingManager;
import com.google.android.systemui.power.AdaptiveChargingNotification;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class AdaptiveChargingNotification$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AdaptiveChargingNotification f$0;
    public final /* synthetic */ AdaptiveChargingManager.AdaptiveChargingStatusReceiver f$1;

    public /* synthetic */ AdaptiveChargingNotification$$ExternalSyntheticLambda0(AdaptiveChargingNotification adaptiveChargingNotification, AdaptiveChargingNotification.AnonymousClass1 r2) {
        this.f$0 = adaptiveChargingNotification;
        this.f$1 = r2;
    }

    public final void run() {
        AdaptiveChargingNotification adaptiveChargingNotification = this.f$0;
        AdaptiveChargingManager.AdaptiveChargingStatusReceiver adaptiveChargingStatusReceiver = this.f$1;
        adaptiveChargingNotification.mAdaptiveChargingManager.getClass();
        AdaptiveChargingManager.queryStatus(adaptiveChargingStatusReceiver);
    }
}
