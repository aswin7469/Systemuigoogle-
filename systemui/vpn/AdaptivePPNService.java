package com.google.android.systemui.vpn;

import android.content.res.Resources;
import android.util.Log;
import com.android.systemui.CoreStartable;
import dagger.Lazy;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AdaptivePPNService implements CoreStartable {
    public final boolean isVpnFeatureEnabled;
    public final Executor uiExecutor;
    public VpnNetworkMonitor vpnNetworkMonitor;
    public final Lazy vpnNetworkMonitorWrapper;
    public VpnPackageMonitor vpnPackageMonitor;
    public final Lazy vpnPackageMonitorWrapper;

    public AdaptivePPNService(Resources resources, Executor executor, Lazy lazy, Lazy lazy2) {
        this.uiExecutor = executor;
        this.vpnNetworkMonitorWrapper = lazy;
        this.vpnPackageMonitorWrapper = lazy2;
        boolean z = resources.getBoolean(2131034165);
        this.isVpnFeatureEnabled = z;
        Log.i("AdaptivePPNService", "Wildlife feature enabled is " + z);
    }

    public final void start() {
        if (!this.isVpnFeatureEnabled) {
            Log.d("AdaptivePPNService", "System config is off");
            return;
        }
        this.uiExecutor.execute(new AdaptivePPNService$startMonitorState$1(this));
    }
}
