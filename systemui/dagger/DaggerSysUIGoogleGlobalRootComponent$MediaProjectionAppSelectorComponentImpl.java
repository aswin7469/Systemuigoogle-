package com.google.android.systemui.dagger;

import android.os.UserHandle;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorResultHandler;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorView;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import dagger.internal.DoubleCheck;
import dagger.internal.SingleCheck;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl {
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider activityTaskManagerLabelLoaderProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider activityTaskManagerThumbnailLoaderProvider;
    public final Provider bindAppIconLoaderProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider bindIconFactoryProvider;
    public final Provider bindRecentTaskLabelLoaderProvider;
    public final Provider bindRecentTaskListProvider;
    public final Provider bindRecentTaskThumbnailLoaderProvider;
    public final String callingPackage;
    public final Provider factoryProvider;
    public final Provider factoryProvider2;
    public final Integer hostUid;
    public final UserHandle hostUserHandle;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider iconLoaderLibAppIconLoaderProvider;
    public final Boolean isFirstStart;
    public final Provider mediaProjectionAppSelectorControllerProvider;
    public final Provider mediaProjectionBlockerEmptyStateProvider;
    public final Provider mediaProjectionRecentsViewControllerProvider;
    public final Provider provideAppSelectorComponentNameProvider;
    public final Provider provideCoroutineScopeProvider;
    public final MediaProjectionAppSelectorResultHandler resultHandler;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider shellRecentTaskListProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;
    public final Provider taskPreviewSizeProvider;
    public final MediaProjectionAppSelectorView view;

    public DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, UserHandle userHandle, Integer num, String str, MediaProjectionAppSelectorView mediaProjectionAppSelectorView, MediaProjectionAppSelectorResultHandler mediaProjectionAppSelectorResultHandler, Boolean bool) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.view = mediaProjectionAppSelectorView;
        this.hostUserHandle = userHandle;
        this.callingPackage = str;
        this.isFirstStart = bool;
        this.hostUid = num;
        this.resultHandler = mediaProjectionAppSelectorResultHandler;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.bindRecentTaskListProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 1, 5));
        this.provideCoroutineScopeProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 2, 5));
        this.provideAppSelectorComponentNameProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 3, 5));
        this.bindRecentTaskThumbnailLoaderProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 4, 5));
        this.mediaProjectionAppSelectorControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 0, 5));
        this.bindIconFactoryProvider = new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 9, 5);
        this.bindAppIconLoaderProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 8, 5));
        this.bindRecentTaskLabelLoaderProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 10, 5));
        this.taskPreviewSizeProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 11, 5));
        this.factoryProvider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 7, 5));
        this.factoryProvider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 6, 5));
        this.mediaProjectionRecentsViewControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 5, 5));
        this.mediaProjectionBlockerEmptyStateProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 12, 5));
    }
}
