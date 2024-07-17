package com.google.android.systemui.lowlightclock;

import com.android.app.animation.Interpolators;
import com.android.dream.lowlight.util.TruncatedInterpolator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
