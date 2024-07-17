package com.google.android.systemui.assist.uihints;

import com.google.android.systemui.assist.uihints.TranscriptionController;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class TranscriptionController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TranscriptionController f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ TranscriptionController$$ExternalSyntheticLambda2(TranscriptionController transcriptionController, String str, int i) {
        this.$r8$classId = i;
        this.f$0 = transcriptionController;
        this.f$1 = str;
    }

    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                TranscriptionController transcriptionController = this.f$0;
                ((TranscriptionView) transcriptionController.mViewMap.get(TranscriptionController.State.TRANSCRIPTION)).setTranscription(this.f$1);
                return;
            default:
                TranscriptionController transcriptionController2 = this.f$0;
                String str = this.f$1;
                GreetingView greetingView = (GreetingView) transcriptionController2.mViewMap.get(TranscriptionController.State.GREETING);
                greetingView.setPadding(0, 0, 0, 0);
                greetingView.setText(str);
                greetingView.setVisibility(0);
                return;
        }
    }
}
