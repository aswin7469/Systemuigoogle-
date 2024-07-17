package com.google.android.systemui.vpn;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserTracker;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class VpnPackageMonitor {
    public static final boolean DEBUG = Log.isLoggable("VpnPackageMonitor", 3);
    public final BroadcastDispatcher broadcastDispatcher;
    public final BroadcastSender broadcastSender;
    public final Context context;
    public boolean isRegistered;
    public final VpnPackageMonitor$packageMonitorBroadcastReceiver$1 packageMonitorBroadcastReceiver = new VpnPackageMonitor$packageMonitorBroadcastReceiver$1(this, 0);
    public final UserFileManager userFileManager;
    public final UserTracker userTracker;
    public final VpnPackageMonitor$packageMonitorBroadcastReceiver$1 wildlifeFeatureBroadcastReceiver = new VpnPackageMonitor$packageMonitorBroadcastReceiver$1(this, 1);

    public VpnPackageMonitor(Context context2, BroadcastSender broadcastSender2, BroadcastDispatcher broadcastDispatcher2, UserFileManager userFileManager2, UserTracker userTracker2) {
        this.context = context2;
        this.broadcastSender = broadcastSender2;
        this.broadcastDispatcher = broadcastDispatcher2;
        this.userFileManager = userFileManager2;
        this.userTracker = userTracker2;
    }

    public final void registerPackageMonitorReceiver() {
        if (!this.isRegistered) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addDataScheme("package");
            this.context.registerReceiver(this.packageMonitorBroadcastReceiver, intentFilter);
            this.isRegistered = true;
        }
    }
}
