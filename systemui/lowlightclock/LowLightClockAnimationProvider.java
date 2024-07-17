package com.google.android.systemui.lowlightclock;

import com.android.app.animation.Interpolators;
import com.android.dream.lowlight.util.TruncatedInterpolator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LowLightClockAnimationProvider {
    public final long mAlphaAnimationDurationMillis;
    public final long mAlphaAnimationInStartDelayMillis;
    public final TruncatedInterpolator mTranslationOutInterpolator;
    public final long mYTranslationAnimationInDurationMillis;
    public final int mYTranslationAnimationInStartOffset;

    public LowLightClockAnimationProvider(int i, long j, long j2, long j3) {
        this.mYTranslationAnimationInStartOffset = i;
        this.mYTranslationAnimationInDurationMillis = j;
        this.mAlphaAnimationInStartDelayMillis = j2;
        this.mAlphaAnimationDurationMillis = j3;
        this.mTranslationOutInterpolator = new TruncatedInterpolator(Interpolators.EMPHASIZED, (float) j, (float) j3);
    }
}
