package com.google.android.systemui.power;

import android.content.DialogInterface;
import android.os.AsyncTask;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BatterySaverConfirmationDialog$$ExternalSyntheticLambda0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BatterySaverConfirmationDialog f$0;

    public /* synthetic */ BatterySaverConfirmationDialog$$ExternalSyntheticLambda0(BatterySaverConfirmationDialog batterySaverConfirmationDialog, int i) {
        this.$r8$classId = i;
        this.f$0 = batterySaverConfirmationDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.$r8$classId;
        BatterySaverConfirmationDialog batterySaverConfirmationDialog = this.f$0;
        batterySaverConfirmationDialog.getClass();
        switch (i2) {
            case 0:
                batterySaverConfirmationDialog.log(BatteryMetricEvent.SAVER_CONFIRMATION_DIALOG_TURN_ON);
                dialogInterface.dismiss();
                AsyncTask.execute(new BatterySaverConfirmationDialog$$ExternalSyntheticLambda1(batterySaverConfirmationDialog));
                return;
            default:
                batterySaverConfirmationDialog.log(BatteryMetricEvent.SAVER_CONFIRMATION_DIALOG_CANCEL);
                dialogInterface.dismiss();
                return;
        }
    }
}
