package com.google.android.systemui.assist.uihints;

import android.view.animation.PathInterpolator;
import com.google.android.systemui.assist.uihints.TranscriptionView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class TranscriptionView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TranscriptionView f$0;

    public /* synthetic */ TranscriptionView$$ExternalSyntheticLambda0(TranscriptionView transcriptionView) {
        this.f$0 = transcriptionView;
    }

    public final void run() {
        TranscriptionView transcriptionView = this.f$0;
        PathInterpolator pathInterpolator = TranscriptionView.INTERPOLATOR_SCROLL;
        transcriptionView.setVisibility(8);
        transcriptionView.setAlpha(1.0f);
        transcriptionView.setTranslationY(0.0f);
        transcriptionView.setTranscription("");
        transcriptionView.mTranscriptionAnimator = new TranscriptionView.TranscriptionAnimator();
        transcriptionView.mHideFuture.set((Object) null);
    }
}
