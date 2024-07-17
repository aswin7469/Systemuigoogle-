package com.google.android.systemui.power;

import android.content.Intent;
import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BatterySaverConfirmationDialog$$ExternalSyntheticLambda3 implements View.OnClickListener {
    public final /* synthetic */ BatterySaverConfirmationDialog f$0;

    public /* synthetic */ BatterySaverConfirmationDialog$$ExternalSyntheticLambda3(BatterySaverConfirmationDialog batterySaverConfirmationDialog) {
        this.f$0 = batterySaverConfirmationDialog;
    }

    public final void onClick(View view) {
        BatterySaverConfirmationDialog batterySaverConfirmationDialog = this.f$0;
        batterySaverConfirmationDialog.getClass();
        batterySaverConfirmationDialog.log(BatteryMetricEvent.SAVER_CONFIRMATION_DIALOG_SETUP);
        batterySaverConfirmationDialog.mConfirmationDialog.dismiss();
        batterySaverConfirmationDialog.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.settings.batterysaver.flipendo.onboarding"), 0);
    }
}
