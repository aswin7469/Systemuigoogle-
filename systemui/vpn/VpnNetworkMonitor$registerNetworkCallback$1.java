package com.google.android.systemui.vpn;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
