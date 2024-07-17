package com.google.android.systemui.columbus.legacy;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusContentObserver$userTrackerCallback$1 implements UserTracker.Callback {
    public final /* synthetic */ ColumbusContentObserver this$0;

    public ColumbusContentObserver$userTrackerCallback$1(ColumbusContentObserver columbusContentObserver) {
        this.this$0 = columbusContentObserver;
    }

    public final void onUserChanged(int i, Context context) {
        ColumbusContentObserver columbusContentObserver = this.this$0;
        columbusContentObserver.contentResolver.contentResolver.unregisterContentObserver(columbusContentObserver);
        ContentResolverWrapper contentResolverWrapper = columbusContentObserver.contentResolver;
        contentResolverWrapper.contentResolver.registerContentObserver(columbusContentObserver.settingsUri, false, columbusContentObserver, ((UserTrackerImpl) columbusContentObserver.userTracker).getUserId());
        columbusContentObserver.callback.invoke(columbusContentObserver.settingsUri);
    }
}
