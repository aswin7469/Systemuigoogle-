package com.google.android.systemui.power;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import com.android.settingslib.fuelgauge.BatterySaverUtils;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class BatterySaverConfirmationDialog$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ BatterySaverConfirmationDialog f$0;

    public /* synthetic */ BatterySaverConfirmationDialog$$ExternalSyntheticLambda2(BatterySaverConfirmationDialog batterySaverConfirmationDialog) {
        this.f$0 = batterySaverConfirmationDialog;
    }

    public final void run() {
        BatterySaverConfirmationDialog batterySaverConfirmationDialog = this.f$0;
        boolean z = batterySaverConfirmationDialog.mIsStandardMode;
        Context context = batterySaverConfirmationDialog.mContext;
        if (!z) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("update_flipendo_mode", 1);
                context.getContentResolver().call("com.google.android.flipendo.api", "update_flipendo_mode_method", (String) null, bundle);
            } catch (Exception e) {
                Log.e("PowerUtils", "applyExtremeSaverMode() failed", e);
            }
        }
        BatterySaverUtils.setPowerSaveMode(context, true, false, 1);
        Settings.Secure.putInt(context.getContentResolver(), "low_power_warning_acknowledged", 1);
        Settings.Secure.putInt(context.getContentResolver(), "extra_low_power_warning_acknowledged", 1);
    }
}
