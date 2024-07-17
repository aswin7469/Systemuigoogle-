package com.google.android.systemui.vpn;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class VpnNetworkMonitor$registerNetworkCallback$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ VpnNetworkMonitor this$0;

    public /* synthetic */ VpnNetworkMonitor$registerNetworkCallback$1(VpnNetworkMonitor vpnNetworkMonitor, int i) {
        this.$r8$classId = i;
        this.this$0 = vpnNetworkMonitor;
    }

    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                VpnNetworkMonitor vpnNetworkMonitor = this.this$0;
                vpnNetworkMonitor.connectivityManager.registerDefaultNetworkCallback(vpnNetworkMonitor.networkCallback);
                return;
            default:
                VpnNetworkMonitor vpnNetworkMonitor2 = this.this$0;
                vpnNetworkMonitor2.connectivityManager.unregisterNetworkCallback(vpnNetworkMonitor2.networkCallback);
                return;
        }
    }
}
