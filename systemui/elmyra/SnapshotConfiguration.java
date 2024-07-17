package com.google.android.systemui.elmyra;

import android.content.Context;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SnapshotConfiguration {
    public final int mCompleteGestures;
    public final int mIncompleteGestures;
    public final int mSnapshotDelayAfterGesture;

    public SnapshotConfiguration(Context context) {
        this.mCompleteGestures = context.getResources().getInteger(2131427419);
        this.mIncompleteGestures = context.getResources().getInteger(2131427420);
        this.mSnapshotDelayAfterGesture = context.getResources().getInteger(2131427418);
    }
}
