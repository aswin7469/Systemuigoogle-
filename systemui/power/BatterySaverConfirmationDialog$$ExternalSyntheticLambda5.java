package com.google.android.systemui.power;

import android.content.Intent;
import android.view.View;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class BatterySaverConfirmationDialog$$ExternalSyntheticLambda5 implements View.OnClickListener {
    public final /* synthetic */ BatterySaverConfirmationDialog f$0;

    public /* synthetic */ BatterySaverConfirmationDialog$$ExternalSyntheticLambda5(BatterySaverConfirmationDialog batterySaverConfirmationDialog) {
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
