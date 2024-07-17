package com.google.android.systemui.vpn;

import android.content.Context;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class VpnNetworkMonitor$userTrackerCallback$1 implements UserTracker.Callback {
    public final /* synthetic */ VpnNetworkMonitor this$0;

    public VpnNetworkMonitor$userTrackerCallback$1(VpnNetworkMonitor vpnNetworkMonitor) {
        this.this$0 = vpnNetworkMonitor;
    }

    public final void onUserChanged(int i, Context context) {
        VpnNetworkMonitor vpnNetworkMonitor = this.this$0;
        if (((UserFileManagerImpl) vpnNetworkMonitor.userFileManager).getSharedPreferences$1("network_monitor_index", ((UserTrackerImpl) vpnNetworkMonitor.userTracker).getUserId()).getBoolean("network_monitor_enabled", true)) {
            vpnNetworkMonitor.registerNetworkCallback();
        } else {
            vpnNetworkMonitor.unregisterNetworkCallback();
        }
    }
}
