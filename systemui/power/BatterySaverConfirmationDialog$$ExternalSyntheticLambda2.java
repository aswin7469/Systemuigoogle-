package com.google.android.systemui.power;

import android.view.View;
import android.widget.RadioButton;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BatterySaverConfirmationDialog$$ExternalSyntheticLambda2 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BatterySaverConfirmationDialog f$0;
    public final /* synthetic */ RadioButton f$1;
    public final /* synthetic */ RadioButton f$2;

    public /* synthetic */ BatterySaverConfirmationDialog$$ExternalSyntheticLambda2(BatterySaverConfirmationDialog batterySaverConfirmationDialog, RadioButton radioButton, RadioButton radioButton2, int i) {
        this.$r8$classId = i;
        this.f$0 = batterySaverConfirmationDialog;
        this.f$1 = radioButton;
        this.f$2 = radioButton2;
    }

    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                BatterySaverConfirmationDialog batterySaverConfirmationDialog = this.f$0;
                RadioButton radioButton = this.f$1;
                RadioButton radioButton2 = this.f$2;
                batterySaverConfirmationDialog.mIsStandardMode = true;
                radioButton.setChecked(true);
                radioButton2.setChecked(!batterySaverConfirmationDialog.mIsStandardMode);
                return;
            default:
                BatterySaverConfirmationDialog batterySaverConfirmationDialog2 = this.f$0;
                RadioButton radioButton3 = this.f$1;
                RadioButton radioButton4 = this.f$2;
                batterySaverConfirmationDialog2.mIsStandardMode = false;
                radioButton3.setChecked(false);
                radioButton4.setChecked(!batterySaverConfirmationDialog2.mIsStandardMode);
                return;
        }
    }
}
