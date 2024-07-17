package com.google.android.systemui.lowlightclock;

import android.content.Context;
import android.content.res.Resources;
import com.android.internal.app.IBatteryStats;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.fuelgauge.BatteryStatus;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ChargingStatusProvider {
    public final IBatteryStats mBatteryInfo;
    public final BatteryState mBatteryState = new Object();
    public LowLightClockDreamService$$ExternalSyntheticLambda0 mCallback;
    public ChargingStatusCallback mChargingStatusCallback;
    public final Context mContext;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final Resources mResources;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class BatteryState {
        public BatteryStatus mBatteryStatus;

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
            r2 = r2.mBatteryStatus;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean isChargingOrFull() {
            /*
                r2 = this;
                boolean r0 = r2.isValid()
                if (r0 == 0) goto L_0x0017
                com.android.settingslib.fuelgauge.BatteryStatus r2 = r2.mBatteryStatus
                int r0 = r2.status
                r1 = 2
                if (r0 == r1) goto L_0x0015
                int r2 = r2.level
                boolean r2 = com.android.settingslib.fuelgauge.BatteryStatus.isCharged(r0, r2)
                if (r2 == 0) goto L_0x0017
            L_0x0015:
                r2 = 1
                goto L_0x0018
            L_0x0017:
                r2 = 0
            L_0x0018:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.lowlightclock.ChargingStatusProvider.BatteryState.isChargingOrFull():boolean");
        }

        public final boolean isValid() {
            if (this.mBatteryStatus != null) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    class ChargingStatusCallback extends KeyguardUpdateMonitorCallback {
        public ChargingStatusCallback() {
        }

        public final void onRefreshBatteryInfo(BatteryStatus batteryStatus) {
            ChargingStatusProvider chargingStatusProvider = ChargingStatusProvider.this;
            chargingStatusProvider.mBatteryState.mBatteryStatus = batteryStatus;
            chargingStatusProvider.reportStatusToCallback();
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.systemui.lowlightclock.ChargingStatusProvider$BatteryState, java.lang.Object] */
    public ChargingStatusProvider(Context context, Resources resources, IBatteryStats iBatteryStats, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        this.mContext = context;
        this.mResources = resources;
        this.mBatteryInfo = iBatteryStats;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0102, code lost:
        if (r6 != false) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0155, code lost:
        if (r6 != false) goto L_0x0104;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x017e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void reportStatusToCallback() {
        /*
            r15 = this;
            com.google.android.systemui.lowlightclock.LowLightClockDreamService$$ExternalSyntheticLambda0 r0 = r15.mCallback
            if (r0 == 0) goto L_0x0196
            com.google.android.systemui.lowlightclock.ChargingStatusProvider$BatteryState r0 = r15.mBatteryState
            boolean r1 = r0.isValid()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0020
            com.android.settingslib.fuelgauge.BatteryStatus r1 = r0.mBatteryStatus
            int r1 = r1.plugged
            boolean r1 = com.android.settingslib.fuelgauge.BatteryStatus.isPluggedIn(r1)
            if (r1 == 0) goto L_0x0020
            boolean r1 = r0.isChargingOrFull()
            if (r1 == 0) goto L_0x0020
            r1 = r2
            goto L_0x0021
        L_0x0020:
            r1 = r3
        L_0x0021:
            r4 = 4
            if (r1 != 0) goto L_0x0043
            boolean r1 = r0.isValid()
            if (r1 == 0) goto L_0x0041
            com.android.settingslib.fuelgauge.BatteryStatus r1 = r0.mBatteryStatus
            int r1 = r1.plugged
            boolean r1 = com.android.settingslib.fuelgauge.BatteryStatus.isPluggedIn(r1)
            if (r1 == 0) goto L_0x0041
            boolean r1 = r0.isValid()
            if (r1 == 0) goto L_0x0041
            com.android.settingslib.fuelgauge.BatteryStatus r1 = r0.mBatteryStatus
            int r1 = r1.chargingStatus
            if (r1 != r4) goto L_0x0041
            goto L_0x0043
        L_0x0041:
            r1 = r3
            goto L_0x0044
        L_0x0043:
            r1 = r2
        L_0x0044:
            com.google.android.systemui.lowlightclock.LowLightClockDreamService$$ExternalSyntheticLambda0 r5 = r15.mCallback
            boolean r6 = r0.isValid()
            if (r6 != 0) goto L_0x004f
            r15 = 0
            goto L_0x0186
        L_0x004f:
            boolean r6 = r0.isValid()
            if (r6 == 0) goto L_0x005d
            com.android.settingslib.fuelgauge.BatteryStatus r6 = r0.mBatteryStatus
            int r6 = r6.chargingStatus
            if (r6 != r4) goto L_0x005d
            r6 = r2
            goto L_0x005e
        L_0x005d:
            r6 = r3
        L_0x005e:
            r7 = 1120403456(0x42c80000, float:100.0)
            android.content.res.Resources r8 = r15.mResources
            if (r6 == 0) goto L_0x0088
            java.text.NumberFormat r15 = java.text.NumberFormat.getPercentInstance()
            boolean r2 = r0.isValid()
            if (r2 == 0) goto L_0x0073
            com.android.settingslib.fuelgauge.BatteryStatus r0 = r0.mBatteryStatus
            int r0 = r0.level
            goto L_0x0074
        L_0x0073:
            r0 = r3
        L_0x0074:
            float r0 = (float) r0
            float r0 = r0 / r7
            double r6 = (double) r0
            java.lang.String r15 = r15.format(r6)
            java.lang.Object[] r15 = new java.lang.Object[]{r15}
            r0 = 2131952845(0x7f1304cd, float:1.9542144E38)
            java.lang.String r15 = r8.getString(r0, r15)
            goto L_0x0186
        L_0x0088:
            boolean r6 = r0.isValid()
            if (r6 == 0) goto L_0x00a3
            com.android.settingslib.fuelgauge.BatteryStatus r6 = r0.mBatteryStatus
            int r9 = r6.status
            int r6 = r6.level
            boolean r6 = com.android.settingslib.fuelgauge.BatteryStatus.isCharged(r9, r6)
            if (r6 == 0) goto L_0x00a3
            r15 = 2131952815(0x7f1304af, float:1.9542083E38)
            java.lang.String r15 = r8.getString(r15)
            goto L_0x0186
        L_0x00a3:
            com.android.internal.app.IBatteryStats r6 = r15.mBatteryInfo
            r9 = -1
            boolean r11 = r0.isValid()     // Catch:{ RemoteException -> 0x00c2 }
            if (r11 == 0) goto L_0x00ca
            com.android.settingslib.fuelgauge.BatteryStatus r11 = r0.mBatteryStatus     // Catch:{ RemoteException -> 0x00c2 }
            int r11 = r11.plugged     // Catch:{ RemoteException -> 0x00c2 }
            boolean r11 = com.android.settingslib.fuelgauge.BatteryStatus.isPluggedIn(r11)     // Catch:{ RemoteException -> 0x00c2 }
            if (r11 == 0) goto L_0x00ca
            boolean r11 = r0.isChargingOrFull()     // Catch:{ RemoteException -> 0x00c2 }
            if (r11 == 0) goto L_0x00ca
            long r9 = r6.computeChargeTimeRemaining()     // Catch:{ RemoteException -> 0x00c2 }
            goto L_0x00ca
        L_0x00c2:
            r6 = move-exception
            java.lang.String r11 = "ChargingStatusProvider"
            java.lang.String r12 = "Error calling IBatteryStats: "
            android.util.Log.e(r11, r12, r6)
        L_0x00ca:
            r11 = 0
            int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r6 <= 0) goto L_0x00d2
            r6 = r2
            goto L_0x00d3
        L_0x00d2:
            r6 = r3
        L_0x00d3:
            boolean r11 = r0.isValid()
            r12 = 2131952843(0x7f1304cb, float:1.954214E38)
            r13 = 2131952833(0x7f1304c1, float:1.954212E38)
            android.content.Context r15 = r15.mContext
            if (r11 == 0) goto L_0x011b
            com.android.settingslib.fuelgauge.BatteryStatus r11 = r0.mBatteryStatus
            int r11 = r11.plugged
            r14 = 2
            if (r11 == r2) goto L_0x00ea
            if (r11 != r14) goto L_0x011b
        L_0x00ea:
            boolean r2 = r0.isChargingOrFull()
            if (r2 == 0) goto L_0x011b
            boolean r2 = r0.isValid()
            if (r2 == 0) goto L_0x00fd
            com.android.settingslib.fuelgauge.BatteryStatus r2 = r0.mBatteryStatus
            int r2 = r2.getChargingSpeed(r15)
            goto L_0x00fe
        L_0x00fd:
            r2 = r3
        L_0x00fe:
            if (r2 == 0) goto L_0x0111
            if (r2 == r14) goto L_0x0106
            if (r6 == 0) goto L_0x0158
        L_0x0104:
            r12 = r13
            goto L_0x0158
        L_0x0106:
            if (r6 == 0) goto L_0x010d
            r2 = 2131952835(0x7f1304c3, float:1.9542124E38)
        L_0x010b:
            r12 = r2
            goto L_0x0158
        L_0x010d:
            r2 = 2131952844(0x7f1304cc, float:1.9542142E38)
            goto L_0x010b
        L_0x0111:
            if (r6 == 0) goto L_0x0117
            r2 = 2131952836(0x7f1304c4, float:1.9542126E38)
            goto L_0x010b
        L_0x0117:
            r2 = 2131952846(0x7f1304ce, float:1.9542146E38)
            goto L_0x010b
        L_0x011b:
            boolean r2 = r0.isValid()
            if (r2 == 0) goto L_0x0137
            com.android.settingslib.fuelgauge.BatteryStatus r2 = r0.mBatteryStatus
            int r2 = r2.plugged
            if (r2 != r4) goto L_0x0137
            boolean r2 = r0.isChargingOrFull()
            if (r2 == 0) goto L_0x0137
            if (r6 == 0) goto L_0x0133
            r2 = 2131952837(0x7f1304c5, float:1.9542128E38)
            goto L_0x010b
        L_0x0133:
            r2 = 2131952849(0x7f1304d1, float:1.9542152E38)
            goto L_0x010b
        L_0x0137:
            boolean r2 = r0.isValid()
            if (r2 == 0) goto L_0x0155
            com.android.settingslib.fuelgauge.BatteryStatus r2 = r0.mBatteryStatus
            int r2 = r2.plugged
            r11 = 8
            if (r2 != r11) goto L_0x0155
            boolean r2 = r0.isChargingOrFull()
            if (r2 == 0) goto L_0x0155
            if (r6 == 0) goto L_0x0151
            r2 = 2131952834(0x7f1304c2, float:1.9542122E38)
            goto L_0x010b
        L_0x0151:
            r2 = 2131952847(0x7f1304cf, float:1.9542148E38)
            goto L_0x010b
        L_0x0155:
            if (r6 == 0) goto L_0x0158
            goto L_0x0104
        L_0x0158:
            java.text.NumberFormat r2 = java.text.NumberFormat.getPercentInstance()
            boolean r11 = r0.isValid()
            if (r11 == 0) goto L_0x0167
            com.android.settingslib.fuelgauge.BatteryStatus r0 = r0.mBatteryStatus
            int r0 = r0.level
            goto L_0x0168
        L_0x0167:
            r0 = r3
        L_0x0168:
            float r0 = (float) r0
            float r0 = r0 / r7
            double r13 = (double) r0
            java.lang.String r0 = r2.format(r13)
            if (r6 == 0) goto L_0x017e
            java.lang.String r15 = android.text.format.Formatter.formatShortElapsedTimeRoundingUpToMinutes(r15, r9)
            java.lang.Object[] r15 = new java.lang.Object[]{r15, r0}
            java.lang.String r15 = r8.getString(r12, r15)
            goto L_0x0186
        L_0x017e:
            java.lang.Object[] r15 = new java.lang.Object[]{r0}
            java.lang.String r15 = r8.getString(r12, r15)
        L_0x0186:
            com.google.android.systemui.lowlightclock.LowLightClockDreamService r0 = r5.f$0
            android.widget.TextView r2 = r0.mChargingStatusTextView
            r2.setText(r15)
            android.widget.TextView r15 = r0.mChargingStatusTextView
            if (r1 == 0) goto L_0x0192
            goto L_0x0193
        L_0x0192:
            r3 = r4
        L_0x0193:
            r15.setVisibility(r3)
        L_0x0196:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.lowlightclock.ChargingStatusProvider.reportStatusToCallback():void");
    }
}
