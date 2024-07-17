package com.google.android.systemui.vpn;

import android.content.IntentFilter;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.PendingRemovalStore;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTrackerImpl;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AdaptivePPNService$startMonitorState$1 implements Runnable {
    public final /* synthetic */ AdaptivePPNService this$0;

    public AdaptivePPNService$startMonitorState$1(AdaptivePPNService adaptivePPNService) {
        this.this$0 = adaptivePPNService;
    }

    public final void run() {
        Log.i("AdaptivePPNService", "start monitors");
        AdaptivePPNService adaptivePPNService = this.this$0;
        adaptivePPNService.vpnNetworkMonitor = (VpnNetworkMonitor) adaptivePPNService.vpnNetworkMonitorWrapper.get();
        AdaptivePPNService adaptivePPNService2 = this.this$0;
        adaptivePPNService2.vpnPackageMonitor = (VpnPackageMonitor) adaptivePPNService2.vpnPackageMonitorWrapper.get();
        AdaptivePPNService adaptivePPNService3 = this.this$0;
        VpnNetworkMonitor vpnNetworkMonitor = adaptivePPNService3.vpnNetworkMonitor;
        VpnPackageMonitor vpnPackageMonitor = null;
        if (vpnNetworkMonitor == null) {
            vpnNetworkMonitor = null;
        }
        ((UserTrackerImpl) vpnNetworkMonitor.userTracker).removeCallback(vpnNetworkMonitor.userTrackerCallback);
        vpnNetworkMonitor.unregisterNetworkCallback();
        UserHandle userHandle = new UserHandle(-1);
        BroadcastDispatcher broadcastDispatcher = vpnNetworkMonitor.broadcastDispatcher;
        VpnNetworkMonitor$broadcastReceiver$1 vpnNetworkMonitor$broadcastReceiver$1 = vpnNetworkMonitor.broadcastReceiver;
        PendingRemovalStore pendingRemovalStore = broadcastDispatcher.removalPendingStore;
        int identifier = userHandle.getIdentifier();
        pendingRemovalStore.logger.logTagForRemoval(identifier, vpnNetworkMonitor$broadcastReceiver$1);
        synchronized (pendingRemovalStore.pendingRemoval) {
            pendingRemovalStore.pendingRemoval.add(identifier, vpnNetworkMonitor$broadcastReceiver$1);
        }
        broadcastDispatcher.handler.obtainMessage(2, userHandle.getIdentifier(), 0, vpnNetworkMonitor$broadcastReceiver$1).sendToTarget();
        Log.i("VpnNetworkMonitor", "NetworkMonitor - destroy, enabled is " + ((UserFileManagerImpl) vpnNetworkMonitor.userFileManager).getSharedPreferences$1(((UserTrackerImpl) vpnNetworkMonitor.userTracker).getUserId(), "network_monitor_index").getBoolean("network_monitor_enabled", true));
        VpnPackageMonitor vpnPackageMonitor2 = adaptivePPNService3.vpnPackageMonitor;
        if (vpnPackageMonitor2 == null) {
            vpnPackageMonitor2 = null;
        }
        vpnPackageMonitor2.getClass();
        UserHandle userHandle2 = new UserHandle(-1);
        BroadcastDispatcher broadcastDispatcher2 = vpnPackageMonitor2.broadcastDispatcher;
        VpnPackageMonitor$packageMonitorBroadcastReceiver$1 vpnPackageMonitor$packageMonitorBroadcastReceiver$1 = vpnPackageMonitor2.wildlifeFeatureBroadcastReceiver;
        PendingRemovalStore pendingRemovalStore2 = broadcastDispatcher2.removalPendingStore;
        int identifier2 = userHandle2.getIdentifier();
        pendingRemovalStore2.logger.logTagForRemoval(identifier2, vpnPackageMonitor$packageMonitorBroadcastReceiver$1);
        synchronized (pendingRemovalStore2.pendingRemoval) {
            pendingRemovalStore2.pendingRemoval.add(identifier2, vpnPackageMonitor$packageMonitorBroadcastReceiver$1);
        }
        broadcastDispatcher2.handler.obtainMessage(2, userHandle2.getIdentifier(), 0, vpnPackageMonitor$packageMonitorBroadcastReceiver$1).sendToTarget();
        if (vpnPackageMonitor2.isRegistered) {
            vpnPackageMonitor2.context.unregisterReceiver(vpnPackageMonitor2.packageMonitorBroadcastReceiver);
            vpnPackageMonitor2.isRegistered = false;
        }
        AdaptivePPNService adaptivePPNService4 = this.this$0;
        VpnNetworkMonitor vpnNetworkMonitor2 = adaptivePPNService4.vpnNetworkMonitor;
        if (vpnNetworkMonitor2 == null) {
            vpnNetworkMonitor2 = null;
        }
        vpnNetworkMonitor2.getClass();
        vpnNetworkMonitor2.broadcastDispatcher.registerReceiver(vpnNetworkMonitor2.broadcastReceiver, new IntentFilter("com.google.android.wildlife.action.UPDATE_NETWORK_MONITOR"), vpnNetworkMonitor2.uiExecutor, new UserHandle(-1), 2, "com.google.android.wildlife.permission.UPDATE_NETWORK_MONITOR");
        boolean z = ((UserFileManagerImpl) vpnNetworkMonitor2.userFileManager).getSharedPreferences$1(((UserTrackerImpl) vpnNetworkMonitor2.userTracker).getUserId(), "network_monitor_index").getBoolean("network_monitor_enabled", true);
        Log.i("VpnNetworkMonitor", "NetworkMonitor - create, enabled is " + z);
        if (z) {
            vpnNetworkMonitor2.registerNetworkCallback();
        }
        ((UserTrackerImpl) vpnNetworkMonitor2.userTracker).addCallback(vpnNetworkMonitor2.userTrackerCallback, vpnNetworkMonitor2.uiExecutor);
        VpnPackageMonitor vpnPackageMonitor3 = adaptivePPNService4.vpnPackageMonitor;
        if (vpnPackageMonitor3 != null) {
            vpnPackageMonitor = vpnPackageMonitor3;
        }
        vpnPackageMonitor.getClass();
        vpnPackageMonitor.broadcastDispatcher.registerReceiver(vpnPackageMonitor.wildlifeFeatureBroadcastReceiver, new IntentFilter("com.google.android.wildlife.action.UPDATE_PACKAGE_MONITOR"), (Executor) null, new UserHandle(-1), 2, "com.google.android.wildlife.permission.UPDATE_PACKAGE_MONITOR");
        boolean z2 = ((UserFileManagerImpl) vpnPackageMonitor.userFileManager).getSharedPreferences$1(((UserTrackerImpl) vpnPackageMonitor.userTracker).getUserId(), "package_monitor_index").getBoolean("package_monitor_enabled", true);
        Log.i("VpnPackageMonitor", "isPackageMonitorEnabled is " + z2);
        if (z2) {
            vpnPackageMonitor.registerPackageMonitorReceiver();
        }
    }
}
