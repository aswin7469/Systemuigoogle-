package com.google.android.systemui.columbus.legacy;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
