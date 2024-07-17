package com.google.android.systemui.assist.uihints;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class TranscriptionController$$ExternalSyntheticLambda0 {
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
