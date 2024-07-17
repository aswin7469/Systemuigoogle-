package com.google.android.systemui.power;

import android.os.Bundle;
import android.util.Log;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class SevereLowBatteryDialog$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ SevereLowBatteryDialog f$0;

    public /* synthetic */ SevereLowBatteryDialog$$ExternalSyntheticLambda2(SevereLowBatteryDialog severeLowBatteryDialog) {
        this.f$0 = severeLowBatteryDialog;
    }

    public final void run() {
        try {
            this.f$0.mContext.getContentResolver().call("com.google.android.flipendo.api", "force_enable_flipendo_method", (String) null, (Bundle) null);
        } catch (IllegalArgumentException e) {
            Log.e("PowerUtils", "enableFlipendo() failed", e);
        }
    }
}
