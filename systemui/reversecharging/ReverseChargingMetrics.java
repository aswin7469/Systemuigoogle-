package com.google.android.systemui.reversecharging;

import android.frameworks.stats.IStats;
import android.frameworks.stats.VendorAtom;
import android.os.IBinder;
import android.os.IInterface;
import android.os.ServiceManager;
import android.util.Log;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import java.util.Optional;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ReverseChargingMetrics {
    public static final boolean DEBUG = Log.isLoggable("ReverseChargingMetrics", 3);

    public static void reportVendorAtom(VendorAtom vendorAtom) {
        try {
            Optional tryConnectingToStatsService = tryConnectingToStatsService();
            if (tryConnectingToStatsService.isPresent()) {
                ((IStats.Stub.Proxy) ((IStats) tryConnectingToStatsService.get())).reportVendorAtom(vendorAtom);
                if (DEBUG) {
                    Log.i("ReverseChargingMetrics", "Report vendor atom OK, " + vendorAtom);
                }
            }
        } catch (Exception e) {
            Log.e("ReverseChargingMetrics", "Failed to log atom to IStats service, " + e);
        }
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Object, android.frameworks.stats.IStats$Stub$Proxy] */
    public static Optional tryConnectingToStatsService() {
        IStats iStats;
        StringBuilder sb = new StringBuilder();
        String str = IStats.DESCRIPTOR;
        String m = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, str, "/default");
        if (!ServiceManager.isDeclared(m)) {
            Log.e("ReverseChargingMetrics", "IStats is not registered");
            return Optional.empty();
        }
        IBinder waitForDeclaredService = ServiceManager.waitForDeclaredService(m);
        int i = IStats.Stub.$r8$clinit;
        if (waitForDeclaredService == null) {
            iStats = null;
        } else {
            IInterface queryLocalInterface = waitForDeclaredService.queryLocalInterface(str);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IStats)) {
                ? obj = new Object();
                obj.mRemote = waitForDeclaredService;
                iStats = obj;
            } else {
                iStats = (IStats) queryLocalInterface;
            }
        }
        return Optional.ofNullable(iStats);
    }
}
