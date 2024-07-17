package com.google.android.systemui.power;

import android.content.Intent;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BatteryInfoBroadcast$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ BatteryInfoBroadcast f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ BatteryInfoBroadcast$$ExternalSyntheticLambda0(BatteryInfoBroadcast batteryInfoBroadcast, Intent intent) {
        this.f$0 = batteryInfoBroadcast;
        this.f$1 = intent;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r8 = this;
            com.google.android.systemui.power.BatteryInfoBroadcast r0 = r8.f$0
            android.content.Intent r8 = r8.f$1
            r0.getClass()
            java.lang.String r1 = r8.getAction()
            boolean r2 = r0.mWidgetEnabled
            java.lang.String r3 = "android.intent.action.ACTION_POWER_DISCONNECTED"
            java.lang.String r4 = "android.intent.action.ACTION_POWER_CONNECTED"
            if (r2 != 0) goto L_0x0021
            boolean r2 = r4.equals(r1)
            if (r2 != 0) goto L_0x0021
            boolean r2 = r3.equals(r1)
            if (r2 != 0) goto L_0x0021
            goto L_0x0120
        L_0x0021:
            int r2 = r1.hashCode()
            java.lang.String r5 = "PNW.batteryStatusChanged"
            r6 = 1
            r7 = 0
            switch(r2) {
                case -1886648615: goto L_0x00aa;
                case -1538406691: goto L_0x00a0;
                case -1530327060: goto L_0x0096;
                case -612790895: goto L_0x008b;
                case 545516589: goto L_0x0080;
                case 579327048: goto L_0x0075;
                case 798292259: goto L_0x006b;
                case 1019184907: goto L_0x0063;
                case 1020204554: goto L_0x005b;
                case 1123270207: goto L_0x0051;
                case 1174571750: goto L_0x0045;
                case 1244161670: goto L_0x0039;
                case 1779291251: goto L_0x002e;
                default: goto L_0x002c;
            }
        L_0x002c:
            goto L_0x00b2
        L_0x002e:
            java.lang.String r2 = "android.os.action.POWER_SAVE_MODE_CHANGED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00b2
            r2 = 5
            goto L_0x00b3
        L_0x0039:
            java.lang.String r2 = "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00b2
            r2 = 10
            goto L_0x00b3
        L_0x0045:
            java.lang.String r2 = "android.bluetooth.device.action.ALIAS_CHANGED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00b2
            r2 = 9
            goto L_0x00b3
        L_0x0051:
            java.lang.String r2 = "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00b2
            r2 = 7
            goto L_0x00b3
        L_0x005b:
            boolean r2 = r1.equals(r5)
            if (r2 == 0) goto L_0x00b2
            r2 = r6
            goto L_0x00b3
        L_0x0063:
            boolean r2 = r1.equals(r4)
            if (r2 == 0) goto L_0x00b2
            r2 = 3
            goto L_0x00b3
        L_0x006b:
            java.lang.String r2 = "android.intent.action.BOOT_COMPLETED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00b2
            r2 = r7
            goto L_0x00b3
        L_0x0075:
            java.lang.String r2 = "android.bluetooth.device.action.BATTERY_LEVEL_CHANGED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00b2
            r2 = 8
            goto L_0x00b3
        L_0x0080:
            java.lang.String r2 = "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00b2
            r2 = 11
            goto L_0x00b3
        L_0x008b:
            java.lang.String r2 = "android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00b2
            r2 = 12
            goto L_0x00b3
        L_0x0096:
            java.lang.String r2 = "android.bluetooth.adapter.action.STATE_CHANGED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00b2
            r2 = 6
            goto L_0x00b3
        L_0x00a0:
            java.lang.String r2 = "android.intent.action.BATTERY_CHANGED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00b2
            r2 = 2
            goto L_0x00b3
        L_0x00aa:
            boolean r2 = r1.equals(r3)
            if (r2 == 0) goto L_0x00b2
            r2 = 4
            goto L_0x00b3
        L_0x00b2:
            r2 = -1
        L_0x00b3:
            switch(r2) {
                case 0: goto L_0x0118;
                case 1: goto L_0x0118;
                case 2: goto L_0x00e6;
                case 3: goto L_0x00dd;
                case 4: goto L_0x00d4;
                case 5: goto L_0x00c4;
                case 6: goto L_0x00b7;
                case 7: goto L_0x00b7;
                case 8: goto L_0x00b7;
                case 9: goto L_0x00b7;
                case 10: goto L_0x00b7;
                case 11: goto L_0x00b7;
                case 12: goto L_0x00b7;
                default: goto L_0x00b6;
            }
        L_0x00b6:
            goto L_0x0120
        L_0x00b7:
            java.lang.String r2 = "PNW.bluetoothStatusChanged"
            android.content.Intent r2 = com.google.android.systemui.power.BatteryInfoBroadcast.createIntentForSI(r2)
            r2.putExtra(r1, r8)
            r0.sendBroadcast(r2)
            goto L_0x0120
        L_0x00c4:
            android.os.PowerManager r1 = r0.mPowerManager
            boolean r1 = r1.isPowerSaveMode()
            boolean r2 = r0.mIsPowerSaveMode
            if (r2 == r1) goto L_0x0120
            r0.mIsPowerSaveMode = r1
            r0.sendBatteryChangeIntent(r8, r7)
            goto L_0x0120
        L_0x00d4:
            r0.sendPluggedInStateIntent(r7)
            java.lang.String r8 = "last_phone_disconnected_time"
            r0.recordDateTime(r8)
            goto L_0x0120
        L_0x00dd:
            r0.sendPluggedInStateIntent(r6)
            java.lang.String r8 = "last_phone_connected_time"
            r0.recordDateTime(r8)
            goto L_0x0120
        L_0x00e6:
            int r1 = com.android.settingslib.fuelgauge.BatteryStatus.getBatteryLevel(r8)
            java.lang.String r2 = "status"
            int r2 = r8.getIntExtra(r2, r6)
            java.lang.String r3 = "plugged"
            int r3 = r8.getIntExtra(r3, r7)
            java.lang.String r4 = "android.os.extra.CHARGING_STATUS"
            int r4 = r8.getIntExtra(r4, r6)
            int r5 = r0.mBatteryLevel
            if (r5 != r1) goto L_0x010c
            int r5 = r0.mBatteryStatus
            if (r5 != r2) goto L_0x010c
            int r5 = r0.mBatteryPlugged
            if (r5 != r3) goto L_0x010c
            int r5 = r0.mBatteryChargingStatus
            if (r5 == r4) goto L_0x0120
        L_0x010c:
            r0.mBatteryLevel = r1
            r0.mBatteryStatus = r2
            r0.mBatteryPlugged = r3
            r0.mBatteryChargingStatus = r4
            r0.sendBatteryChangeIntent(r8, r7)
            goto L_0x0120
        L_0x0118:
            android.content.Intent r8 = new android.content.Intent
            r8.<init>(r5)
            r0.sendBatteryChangeIntent(r8, r7)
        L_0x0120:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.BatteryInfoBroadcast$$ExternalSyntheticLambda0.run():void");
    }
}
