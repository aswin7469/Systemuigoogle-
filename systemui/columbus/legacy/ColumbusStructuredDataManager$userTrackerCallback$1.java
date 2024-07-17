package com.google.android.systemui.columbus.legacy;

import android.content.Context;
import com.android.systemui.settings.UserTracker;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
