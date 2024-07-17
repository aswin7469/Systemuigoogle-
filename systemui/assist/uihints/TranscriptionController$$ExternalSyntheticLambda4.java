package com.google.android.systemui.assist.uihints;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import com.google.android.systemui.assist.uihints.TranscriptionController;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class TranscriptionController$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TranscriptionController f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ TranscriptionController$$ExternalSyntheticLambda4(TranscriptionController transcriptionController, List list, int i) {
        this.$r8$classId = i;
        this.f$0 = transcriptionController;
        this.f$1 = list;
    }

    public final void run() {
        int i = this.$r8$classId;
        TranscriptionController.State state = TranscriptionController.State.CHIPS;
        switch (i) {
            case 0:
                TranscriptionController transcriptionController = this.f$0;
                List list = this.f$1;
                ChipsContainer chipsContainer = (ChipsContainer) transcriptionController.mViewMap.get(state);
                chipsContainer.mChips = list;
                chipsContainer.setChipsInternal();
                if (chipsContainer.mAnimator.isRunning()) {
                    Log.w("ChipsContainer", "Already animating in chips view; ignoring");
                    return;
                }
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(ObjectAnimator.ofFloat(chipsContainer, View.SCALE_X, new float[]{0.8f, 1.0f})).with(ObjectAnimator.ofFloat(chipsContainer, View.SCALE_Y, new float[]{0.8f, 1.0f})).with(ObjectAnimator.ofFloat(chipsContainer, View.ALPHA, new float[]{0.0f, 1.0f}));
                animatorSet.setDuration(200);
                chipsContainer.setVisibility(0);
                animatorSet.start();
                return;
            default:
                TranscriptionController transcriptionController2 = this.f$0;
                List list2 = this.f$1;
                ChipsContainer chipsContainer2 = (ChipsContainer) transcriptionController2.mViewMap.get(state);
                chipsContainer2.mChips = list2;
                chipsContainer2.setChipsInternal();
                chipsContainer2.setVisibility(0);
                return;
        }
    }
}
