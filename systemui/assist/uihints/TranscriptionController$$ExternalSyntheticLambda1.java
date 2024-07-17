package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.util.Log;
import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
                PendingIntent pendingIntent = transcriptionController.mOnTranscriptionTap;
                TouchInsideHandler touchInsideHandler = transcriptionController.mDefaultOnTap;
                if (pendingIntent == null) {
                    touchInsideHandler.onTouchInside();
                    return;
                }
                try {
                    pendingIntent.send();
                    return;
                } catch (PendingIntent.CanceledException unused) {
                    Log.e("TranscriptionController", "Transcription tap PendingIntent cancelled");
                    touchInsideHandler.onTouchInside();
                    return;
                }
            default:
                PendingIntent pendingIntent2 = transcriptionController.mOnGreetingTap;
                TouchInsideHandler touchInsideHandler2 = transcriptionController.mDefaultOnTap;
                if (pendingIntent2 == null) {
                    touchInsideHandler2.onTouchInside();
                    return;
                }
                try {
                    pendingIntent2.send();
                    return;
                } catch (PendingIntent.CanceledException unused2) {
                    Log.e("TranscriptionController", "Greeting tap PendingIntent cancelled");
                    touchInsideHandler2.onTouchInside();
                    return;
                }
        }
    }
}
