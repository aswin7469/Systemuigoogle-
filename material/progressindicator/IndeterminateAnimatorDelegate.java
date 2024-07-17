package com.google.android.material.progressindicator;

import com.google.android.material.progressindicator.BaseProgressIndicator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class IndeterminateAnimatorDelegate {
    public IndeterminateDrawable drawable;
    public final int[] segmentColors;
    public final float[] segmentPositions;

    public IndeterminateAnimatorDelegate(int i) {
        this.segmentPositions = new float[(i * 2)];
        this.segmentColors = new int[i];
    }

    public abstract void cancelAnimatorImmediately();

    public abstract void registerAnimatorsCompleteCallback(BaseProgressIndicator.AnonymousClass3 r1);

    public abstract void requestCancelAnimatorAfterCurrentCycle();

    public abstract void startAnimator();

    public abstract void unregisterAnimatorsCompleteCallback();
}
