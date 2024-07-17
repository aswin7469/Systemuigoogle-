package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.OvershootInterpolator;
import com.google.android.systemui.assist.uihints.TranscriptionController;
import java.util.List;
import java.util.Optional;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class TranscriptionController$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TranscriptionController f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ Optional f$2;

    public /* synthetic */ TranscriptionController$$ExternalSyntheticLambda3(TranscriptionController transcriptionController, Object obj, Optional optional, int i) {
        this.$r8$classId = i;
        this.f$0 = transcriptionController;
        this.f$1 = obj;
        this.f$2 = optional;
    }

    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                TranscriptionController transcriptionController = this.f$0;
                Optional optional = this.f$2;
                ((GreetingView) transcriptionController.mViewMap.get(TranscriptionController.State.GREETING)).setGreetingAnimated(((Float) optional.get()).floatValue(), (String) this.f$1);
                return;
            default:
                TranscriptionController transcriptionController2 = this.f$0;
                Optional optional2 = this.f$2;
                ChipsContainer chipsContainer = (ChipsContainer) transcriptionController2.mViewMap.get(TranscriptionController.State.CHIPS);
                float floatValue = ((Float) optional2.get()).floatValue();
                chipsContainer.mChips = (List) this.f$1;
                chipsContainer.setChipsInternal();
                if (chipsContainer.mAnimator.isRunning()) {
                    Log.w("ChipsContainer", "Already animating in chips view; ignoring");
                    return;
                }
                chipsContainer.mAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                chipsContainer.mAnimator.setInterpolator(new OvershootInterpolator(Math.min(10.0f, (floatValue / 1.2f) + 3.0f)));
                chipsContainer.mAnimator.setDuration(400);
                chipsContainer.mAnimator.addUpdateListener(new ChipsContainer$$ExternalSyntheticLambda0(chipsContainer));
                chipsContainer.setVisibility(0);
                chipsContainer.mAnimator.start();
                return;
        }
    }
}
