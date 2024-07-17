package com.google.android.systemui.vpn;

import android.content.Context;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class VpnNetworkMonitor$userTrackerCallback$1 implements UserTracker.Callback {
    public final /* synthetic */ VpnNetworkMonitor this$0;

    public VpnNetworkMonitor$userTrackerCallback$1(VpnNetworkMonitor vpnNetworkMonitor) {
        this.this$0 = vpnNetworkMonitor;
    }

    public final void onUserChanged(int i, Context context) {
        VpnNetworkMonitor vpnNetworkMonitor = this.this$0;
        if (((UserFileManagerImpl) vpnNetworkMonitor.userFileManager).getSharedPreferences$1(((UserTrackerImpl) vpnNetworkMonitor.userTracker).getUserId(), "network_monitor_index").getBoolean("network_monitor_enabled", true)) {
            vpnNetworkMonitor.registerNetworkCallback();
        } else {
            vpnNetworkMonitor.unregisterNetworkCallback();
        }
    }
}
