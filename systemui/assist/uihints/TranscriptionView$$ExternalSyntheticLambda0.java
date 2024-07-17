package com.google.android.systemui.assist.uihints;

import android.view.animation.PathInterpolator;
import com.google.android.systemui.assist.uihints.TranscriptionView;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
