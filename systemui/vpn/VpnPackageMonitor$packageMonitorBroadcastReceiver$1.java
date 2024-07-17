package com.google.android.systemui.vpn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTrackerImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class VpnPackageMonitor$packageMonitorBroadcastReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ VpnPackageMonitor this$0;

    public /* synthetic */ VpnPackageMonitor$packageMonitorBroadcastReceiver$1(VpnPackageMonitor vpnPackageMonitor, int i) {
        this.$r8$classId = i;
        this.this$0 = vpnPackageMonitor;
    }

    public final void onReceive(Context context, Intent intent) {
        Uri data;
        String schemeSpecificPart;
        switch (this.$r8$classId) {
            case 0:
                if (TextUtils.equals(intent.getAction(), "android.intent.action.PACKAGE_ADDED") && (data = intent.getData()) != null && (schemeSpecificPart = data.getSchemeSpecificPart()) != null) {
                    VpnPackageMonitor vpnPackageMonitor = this.this$0;
                    vpnPackageMonitor.getClass();
                    if (VpnPackageMonitor.DEBUG) {
                        Log.d("VpnPackageMonitor", "notifyPackageAdded");
                    }
                    vpnPackageMonitor.broadcastSender.sendBroadcast(new Intent("com.google.android.settings.action.NOTIFY_PACKAGE_ADDED").setPackage("com.android.settings").putExtra("com.google.android.wildlife.extra.NEW_PACKAGE_NAME", schemeSpecificPart), "com.google.android.wildlife.permission.VPN_APP_EXCLUSION_LAUNCH");
                    return;
                }
                return;
            default:
                if (TextUtils.equals(intent.getAction(), "com.google.android.wildlife.action.UPDATE_PACKAGE_MONITOR")) {
                    boolean booleanExtra = intent.getBooleanExtra("com.google.android.wildlife.extra.UPDATE_PACKAGE_MONITOR_STATUS", true);
                    VpnPackageMonitor vpnPackageMonitor2 = this.this$0;
                    vpnPackageMonitor2.getClass();
                    Log.i("VpnPackageMonitor", "setPackageMonitorEnabled " + booleanExtra);
                    if (booleanExtra) {
                        vpnPackageMonitor2.registerPackageMonitorReceiver();
                    } else if (vpnPackageMonitor2.isRegistered) {
                        vpnPackageMonitor2.context.unregisterReceiver(vpnPackageMonitor2.packageMonitorBroadcastReceiver);
                        vpnPackageMonitor2.isRegistered = false;
                    }
                    ((UserFileManagerImpl) vpnPackageMonitor2.userFileManager).getSharedPreferences$1("package_monitor_index", ((UserTrackerImpl) vpnPackageMonitor2.userTracker).getUserId()).edit().putBoolean("package_monitor_enabled", booleanExtra).apply();
                    return;
                }
                return;
        }
    }
}
