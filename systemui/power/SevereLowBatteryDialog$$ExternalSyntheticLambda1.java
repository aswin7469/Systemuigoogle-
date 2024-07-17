package com.google.android.systemui.power;

import android.content.DialogInterface;
import android.os.AsyncTask;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class SevereLowBatteryDialog$$ExternalSyntheticLambda1 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ SevereLowBatteryDialog f$0;

    public /* synthetic */ SevereLowBatteryDialog$$ExternalSyntheticLambda1(SevereLowBatteryDialog severeLowBatteryDialog, int i) {
        this.$r8$classId = i;
        this.f$0 = severeLowBatteryDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.$r8$classId;
        SevereLowBatteryDialog severeLowBatteryDialog = this.f$0;
        severeLowBatteryDialog.getClass();
        switch (i2) {
            case 0:
                BatteryMetricEvent batteryMetricEvent = BatteryMetricEvent.SEVERE_BATTERY_DIALOG_TURN_ON;
                UiEventLogger uiEventLogger = severeLowBatteryDialog.mUiEventLogger;
                if (uiEventLogger != null) {
                    uiEventLogger.log(batteryMetricEvent);
                }
                severeLowBatteryDialog.mSevereBatteryDialog.dismiss();
                AsyncTask.execute(new SevereLowBatteryDialog$$ExternalSyntheticLambda2(severeLowBatteryDialog));
                return;
            default:
                BatteryMetricEvent batteryMetricEvent2 = BatteryMetricEvent.SEVERE_BATTERY_DIALOG_CANCEL;
                UiEventLogger uiEventLogger2 = severeLowBatteryDialog.mUiEventLogger;
                if (uiEventLogger2 != null) {
                    uiEventLogger2.log(batteryMetricEvent2);
                    return;
                }
                return;
        }
    }
}
