package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;
import com.google.android.systemui.assist.uihints.TranscriptionView;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class TranscriptionView$TranscriptionAnimator$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ ValueAnimator f$0;

    public /* synthetic */ TranscriptionView$TranscriptionAnimator$$ExternalSyntheticLambda0(ValueAnimator valueAnimator) {
        this.f$0 = valueAnimator;
    }

    public final void accept(Object obj) {
        ((TranscriptionView.TranscriptionSpan) obj).mCurrentFraction = this.f$0.getAnimatedFraction();
    }
}
