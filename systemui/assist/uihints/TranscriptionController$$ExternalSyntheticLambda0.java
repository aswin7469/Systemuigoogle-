package com.google.android.systemui.assist.uihints;

import com.google.common.base.Function;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class TranscriptionController$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ TranscriptionController f$0;

    public /* synthetic */ TranscriptionController$$ExternalSyntheticLambda0(TranscriptionController transcriptionController) {
        this.f$0 = transcriptionController;
    }

    public final Object apply(Object obj) {
        Void voidR = (Void) obj;
        TranscriptionController transcriptionController = this.f$0;
        transcriptionController.mCurrentState = transcriptionController.mQueuedState;
        Runnable runnable = transcriptionController.mQueuedCompletion;
        if (runnable == null) {
            return null;
        }
        runnable.run();
        return null;
    }
}
