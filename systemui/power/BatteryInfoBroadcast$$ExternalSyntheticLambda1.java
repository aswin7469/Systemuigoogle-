package com.google.android.systemui.power;

import android.os.BatteryStatsManager;
import android.util.Log;
import java.util.concurrent.Callable;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BatteryInfoBroadcast$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ BatteryInfoBroadcast f$0;

    public /* synthetic */ BatteryInfoBroadcast$$ExternalSyntheticLambda1(BatteryInfoBroadcast batteryInfoBroadcast) {
        this.f$0 = batteryInfoBroadcast;
    }

    public final Object call() {
        BatteryInfoBroadcast batteryInfoBroadcast = this.f$0;
        batteryInfoBroadcast.getClass();
        try {
            return ((BatteryStatsManager) batteryInfoBroadcast.mContext.getSystemService(BatteryStatsManager.class)).getBatteryUsageStats();
        } catch (RuntimeException e) {
            Log.e("BatteryInfoBroadcast", "getBatteryInfo() from getBatteryUsageStats()", e);
            return null;
        }
    }
}
