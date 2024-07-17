package com.google.android.systemui.elmyra;

import android.content.Context;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SnapshotConfiguration {
    public final int mCompleteGestures;
    public final int mIncompleteGestures;
    public final int mSnapshotDelayAfterGesture;

    public SnapshotConfiguration(Context context) {
        this.mCompleteGestures = context.getResources().getInteger(2131427422);
        this.mIncompleteGestures = context.getResources().getInteger(2131427423);
        this.mSnapshotDelayAfterGesture = context.getResources().getInteger(2131427421);
    }
}
