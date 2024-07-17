package com.google.android.systemui.power;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.Utils;
import com.android.systemui.statusbar.phone.SystemUIDialog;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class SevereLowBatteryDialog$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SevereLowBatteryDialog f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ SevereLowBatteryDialog$$ExternalSyntheticLambda0(SevereLowBatteryDialog severeLowBatteryDialog, int i, boolean z) {
        this.f$0 = severeLowBatteryDialog;
        this.f$1 = i;
        this.f$2 = z;
    }

    public final void run() {
        int i;
        int i2;
        int i3;
        SevereLowBatteryDialog severeLowBatteryDialog = this.f$0;
        int i4 = this.f$1;
        boolean z = this.f$2;
        severeLowBatteryDialog.getClass();
        String formatPercentage = Utils.formatPercentage(Math.round((float) ((double) i4)));
        Context context = severeLowBatteryDialog.mContext;
        SystemUIDialog systemUIDialog = new SystemUIDialog(context);
        severeLowBatteryDialog.mSevereBatteryDialog = systemUIDialog;
        if (z) {
            i = 2131953824;
        } else {
            i = 2131953826;
        }
        systemUIDialog.setTitle(i);
        SystemUIDialog systemUIDialog2 = severeLowBatteryDialog.mSevereBatteryDialog;
        if (z) {
            i2 = 2131953823;
        } else {
            i2 = 2131953825;
        }
        systemUIDialog2.setMessage(context.getString(i2, new Object[]{formatPercentage}));
        SystemUIDialog systemUIDialog3 = severeLowBatteryDialog.mSevereBatteryDialog;
        systemUIDialog3.getClass();
        SystemUIDialog.setShowForAllUsers(systemUIDialog3);
        severeLowBatteryDialog.mSevereBatteryDialog.setCanceledOnTouchOutside(false);
        SystemUIDialog systemUIDialog4 = severeLowBatteryDialog.mSevereBatteryDialog;
        if (z) {
            i3 = 2131953822;
        } else {
            i3 = 2131951967;
        }
        systemUIDialog4.setPositiveButton(i3, new SevereLowBatteryDialog$$ExternalSyntheticLambda1(severeLowBatteryDialog, 0));
        severeLowBatteryDialog.mSevereBatteryDialog.setNegativeButton(2131953683, new SevereLowBatteryDialog$$ExternalSyntheticLambda1(severeLowBatteryDialog, 1));
        severeLowBatteryDialog.mSevereBatteryDialog.show();
        BatteryMetricEvent batteryMetricEvent = BatteryMetricEvent.SEVERE_BATTERY_DIALOG;
        UiEventLogger uiEventLogger = severeLowBatteryDialog.mUiEventLogger;
        if (uiEventLogger != null) {
            uiEventLogger.log(batteryMetricEvent);
        }
    }
}
