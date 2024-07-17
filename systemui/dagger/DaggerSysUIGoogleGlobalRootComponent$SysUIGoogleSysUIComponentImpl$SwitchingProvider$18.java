package com.google.android.systemui.dagger;

import android.app.ActivityManager;
import android.app.AppGlobals;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.UserHandle;
import android.service.quicksettings.IQSService;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.qs.external.TileLifecycleManager;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$18 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$18(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object, com.android.systemui.qs.external.PackageManagerAdapter] */
    public final TileLifecycleManager create(Intent intent, UserHandle userHandle) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        Object obj = switchingProvider.wMComponentImpl;
        Context context2 = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).sysUIGoogleGlobalRootComponentImpl.context;
        ? obj2 = new Object();
        obj2.mPackageManager = context2.getPackageManager();
        obj2.mIPackageManager = AppGlobals.getPackageManager();
        return new TileLifecycleManager((Handler) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), context, (IQSService) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).tileServicesProvider.get(), obj2, (BroadcastDispatcher) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).broadcastDispatcherProvider.get(), intent, userHandle, (ActivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideActivityManagerProvider.get(), (DelayableExecutor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).provideBackgroundDelayableExecutorProvider.get());
    }
}
