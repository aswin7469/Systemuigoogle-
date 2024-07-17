package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;
import com.google.android.systemui.assist.uihints.TranscriptionView;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class TranscriptionView$TranscriptionAnimator$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ ValueAnimator f$0;

    public /* synthetic */ TranscriptionView$TranscriptionAnimator$$ExternalSyntheticLambda0(ValueAnimator valueAnimator) {
        this.f$0 = valueAnimator;
    }

    public final void accept(Object obj) {
        ((TranscriptionView.TranscriptionSpan) obj).mCurrentFraction = this.f$0.getAnimatedFraction();
    }
}
