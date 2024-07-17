package com.google.android.systemui.vpn;

import android.content.res.Resources;
import android.util.Log;
import com.android.systemui.CoreStartable;
import dagger.Lazy;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
        boolean z = resources.getBoolean(2131034163);
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
