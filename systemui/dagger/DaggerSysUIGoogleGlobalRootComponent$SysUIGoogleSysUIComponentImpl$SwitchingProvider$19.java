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

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$19 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$19(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object, com.android.systemui.qs.external.PackageManagerAdapter] */
    public final TileLifecycleManager create(Intent intent, UserHandle userHandle) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        Context context2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
        ? obj = new Object();
        obj.mPackageManager = context2.getPackageManager();
        obj.mIPackageManager = AppGlobals.getPackageManager();
        return new TileLifecycleManager((Handler) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), context, (IQSService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tileServicesProvider.get(), obj, (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), intent, userHandle, (ActivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideActivityManagerProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundDelayableExecutorProvider.get());
    }
}
