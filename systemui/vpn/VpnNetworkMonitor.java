package com.google.android.systemui.vpn;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.Assert;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class VpnNetworkMonitor {
    public static final boolean DEBUG = Log.isLoggable("VpnNetworkMonitor", 3);
    public final BroadcastDispatcher broadcastDispatcher;
    public final VpnNetworkMonitor$broadcastReceiver$1 broadcastReceiver = new VpnNetworkMonitor$broadcastReceiver$1(this);
    public final BroadcastSender broadcastSender;
    public final ConnectivityManager connectivityManager;
    public final Executor executor;
    public boolean isRegistered;
    public final MyNetworkStatusCallback networkCallback = new MyNetworkStatusCallback();
    public final Executor uiExecutor;
    public final UserFileManager userFileManager;
    public final UserTracker userTracker;
    public final VpnNetworkMonitor$userTrackerCallback$1 userTrackerCallback = new VpnNetworkMonitor$userTrackerCallback$1(this);

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class MyNetworkStatusCallback extends ConnectivityManager.NetworkCallback {
        public MyNetworkStatusCallback() {
        }

        public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            VpnNetworkMonitor.access$notifyNetworkChange(VpnNetworkMonitor.this);
        }

        public final void onLost(Network network) {
            VpnNetworkMonitor.access$notifyNetworkChange(VpnNetworkMonitor.this);
        }
    }

    public VpnNetworkMonitor(ConnectivityManager connectivityManager2, BroadcastSender broadcastSender2, BroadcastDispatcher broadcastDispatcher2, Executor executor2, Executor executor3, UserFileManager userFileManager2, UserTracker userTracker2) {
        this.connectivityManager = connectivityManager2;
        this.broadcastSender = broadcastSender2;
        this.broadcastDispatcher = broadcastDispatcher2;
        this.uiExecutor = executor2;
        this.executor = executor3;
        this.userFileManager = userFileManager2;
        this.userTracker = userTracker2;
    }

    public static final void access$notifyNetworkChange(VpnNetworkMonitor vpnNetworkMonitor) {
        vpnNetworkMonitor.getClass();
        if (DEBUG) {
            Log.d("VpnNetworkMonitor", "notifyNetworkChange");
        }
        vpnNetworkMonitor.broadcastSender.sendBroadcast(new Intent("com.google.android.apps.privacy.wildlife.WIFI_STATE_CHANGED").setClassName("com.google.android.apps.privacy.wildlife", "com.google.android.apps.privacy.wildlife.receiver.WildlifeVpnReceiver"));
    }

    public final void registerNetworkCallback() {
        Assert.isMainThread();
        if (!this.isRegistered) {
            this.executor.execute(new VpnNetworkMonitor$registerNetworkCallback$1(this, 0));
            this.isRegistered = true;
        }
    }

    public final void unregisterNetworkCallback() {
        Assert.isMainThread();
        if (this.isRegistered) {
            this.executor.execute(new VpnNetworkMonitor$registerNetworkCallback$1(this, 1));
            this.isRegistered = false;
        }
    }
}
