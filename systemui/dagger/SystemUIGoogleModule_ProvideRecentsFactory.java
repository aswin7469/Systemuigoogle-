package com.google.android.systemui.dagger;

import android.content.Context;
import com.android.systemui.recents.OverviewProxyRecentsImpl;
import com.android.systemui.recents.Recents;
import com.android.systemui.statusbar.CommandQueue;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SystemUIGoogleModule_ProvideRecentsFactory implements Provider {
    public static Recents provideRecents(Context context, OverviewProxyRecentsImpl overviewProxyRecentsImpl, CommandQueue commandQueue) {
        return new Recents(context, overviewProxyRecentsImpl, commandQueue);
    }
}
