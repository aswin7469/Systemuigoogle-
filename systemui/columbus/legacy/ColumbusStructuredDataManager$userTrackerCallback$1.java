package com.google.android.systemui.columbus.legacy;

import android.content.Context;
import com.android.systemui.settings.UserTracker;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColumbusStructuredDataManager$userTrackerCallback$1 implements UserTracker.Callback {
    public final /* synthetic */ ColumbusStructuredDataManager this$0;

    public ColumbusStructuredDataManager$userTrackerCallback$1(ColumbusStructuredDataManager columbusStructuredDataManager) {
        this.this$0 = columbusStructuredDataManager;
    }

    public final void onUserChanged(int i, Context context) {
        ColumbusStructuredDataManager columbusStructuredDataManager = this.this$0;
        synchronized (columbusStructuredDataManager.lock) {
            columbusStructuredDataManager.packageStats = columbusStructuredDataManager.fetchPackageStats();
        }
    }
}
