package com.google.android.systemui.power;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import com.android.settingslib.fuelgauge.BatterySaverUtils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BatterySaverConfirmationDialog$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ BatterySaverConfirmationDialog f$0;

    public /* synthetic */ BatterySaverConfirmationDialog$$ExternalSyntheticLambda1(BatterySaverConfirmationDialog batterySaverConfirmationDialog) {
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
            } catch (IllegalArgumentException e) {
                Log.e("PowerUtils", "applyExtremeSaverMode() failed", e);
            }
        }
        BatterySaverUtils.setPowerSaveMode(1, context, true, false);
        Settings.Secure.putInt(context.getContentResolver(), "low_power_warning_acknowledged", 1);
        Settings.Secure.putInt(context.getContentResolver(), "extra_low_power_warning_acknowledged", 1);
    }
}
