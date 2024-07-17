package com.google.android.systemui.vpn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.Assert;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class VpnNetworkMonitor$broadcastReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ VpnNetworkMonitor this$0;

    public VpnNetworkMonitor$broadcastReceiver$1(VpnNetworkMonitor vpnNetworkMonitor) {
        this.this$0 = vpnNetworkMonitor;
    }

    public final void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), "com.google.android.wildlife.action.UPDATE_NETWORK_MONITOR")) {
            boolean booleanExtra = intent.getBooleanExtra("com.google.android.wildlife.extra.UPDATE_NETWORK_MONITOR_STATUS", true);
            VpnNetworkMonitor vpnNetworkMonitor = this.this$0;
            vpnNetworkMonitor.getClass();
            Assert.isMainThread();
            Log.i("VpnNetworkMonitor", "setNetworkMonitorEnabled " + booleanExtra);
            if (booleanExtra) {
                vpnNetworkMonitor.registerNetworkCallback();
            } else {
                vpnNetworkMonitor.unregisterNetworkCallback();
            }
            ((UserFileManagerImpl) vpnNetworkMonitor.userFileManager).getSharedPreferences$1("network_monitor_index", ((UserTrackerImpl) vpnNetworkMonitor.userTracker).getUserId()).edit().putBoolean("network_monitor_enabled", booleanExtra).apply();
        }
    }
}
