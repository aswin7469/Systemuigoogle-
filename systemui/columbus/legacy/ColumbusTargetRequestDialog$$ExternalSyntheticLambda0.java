package com.google.android.systemui.columbus.legacy;

import android.content.DialogInterface;
import android.view.View;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ColumbusTargetRequestDialog$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ColumbusTargetRequestDialog f$0;
    public final /* synthetic */ DialogInterface.OnClickListener f$1;

    public /* synthetic */ ColumbusTargetRequestDialog$$ExternalSyntheticLambda0(ColumbusTargetRequestDialog columbusTargetRequestDialog, DialogInterface.OnClickListener onClickListener, int i) {
        this.$r8$classId = i;
        this.f$0 = columbusTargetRequestDialog;
        this.f$1 = onClickListener;
    }

    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                ColumbusTargetRequestDialog columbusTargetRequestDialog = this.f$0;
                DialogInterface.OnClickListener onClickListener = this.f$1;
                columbusTargetRequestDialog.getClass();
                onClickListener.onClick(columbusTargetRequestDialog, -2);
                columbusTargetRequestDialog.dismiss();
                return;
            default:
                ColumbusTargetRequestDialog columbusTargetRequestDialog2 = this.f$0;
                DialogInterface.OnClickListener onClickListener2 = this.f$1;
                columbusTargetRequestDialog2.getClass();
                onClickListener2.onClick(columbusTargetRequestDialog2, -1);
                columbusTargetRequestDialog2.dismiss();
                return;
        }
    }
}
