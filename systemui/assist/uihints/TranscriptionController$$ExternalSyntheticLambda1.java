package com.google.android.systemui.assist.uihints;

import android.view.View;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class TranscriptionController$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TranscriptionController f$0;

    public /* synthetic */ TranscriptionController$$ExternalSyntheticLambda1(TranscriptionController transcriptionController, int i) {
        this.$r8$classId = i;
        this.f$0 = transcriptionController;
    }

    public final void onClick(View view) {
        int i = this.$r8$classId;
        TranscriptionController transcriptionController = this.f$0;
        switch (i) {
            case 0:
                transcriptionController.mDefaultOnTap.onTouchInside();
                return;
            default:
                transcriptionController.mDefaultOnTap.onTouchInside();
                return;
        }
    }
}
