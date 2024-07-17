package com.google.android.systemui.dagger;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.UserManager;
import android.view.IWindowManager;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.screenshot.ActionIntentExecutor;
import com.android.systemui.screenshot.AssistContentRequester;
import com.android.systemui.screenshot.ImageCaptureImpl;
import com.android.systemui.screenshot.ImageExporter;
import com.android.systemui.screenshot.ImageTileSet;
import com.android.systemui.screenshot.LongScreenshotData;
import com.android.systemui.screenshot.MessageContainerController;
import com.android.systemui.screenshot.ScreenshotController;
import com.android.systemui.screenshot.ScreenshotDetectionController;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;
import com.android.systemui.screenshot.ScreenshotSmartActions;
import com.android.systemui.screenshot.ScrollCaptureClient;
import com.android.systemui.screenshot.ScrollCaptureController;
import com.android.systemui.screenshot.TimeoutHandler;
import com.android.systemui.screenshot.WorkProfileMessageController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$28 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$28(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final ScreenshotController create(int i) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        Context context = switchingProvider.sysUIGoogleGlobalRootComponentImpl.context;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        ScrollCaptureClient scrollCaptureClient = new ScrollCaptureClient((IWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIWindowManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        ImageExporter imageExporter = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.imageExporter();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideMainExecutorProvider.get();
        ImageCaptureImpl imageCaptureImpl = (ImageCaptureImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.imageCaptureImplProvider.get();
        ScrollCaptureController scrollCaptureController = new ScrollCaptureController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.context, (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), new ScrollCaptureClient((IWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4.provideIWindowManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4.context), new ImageTileSet(new Handler()), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.provideUiEventLoggerProvider.get());
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        TimeoutHandler timeoutHandler = new TimeoutHandler(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        UserManager userManager = (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideUserManagerProvider.get();
        MessageContainerController messageContainerController = new MessageContainerController(new WorkProfileMessageController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.context, (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.provideUserManagerProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.providePackageManagerProvider.get()), new ScreenshotDetectionController((IWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.provideIWindowManagerProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.providePackageManagerProvider.get()));
        MessageContainerController messageContainerController2 = messageContainerController;
        return new ScreenshotController(context, (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (ScreenshotSmartActions) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenshotSmartActionsProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider34.get(), scrollCaptureClient, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideUiEventLoggerProvider.get(), imageExporter, imageCaptureImpl, executor, scrollCaptureController, (LongScreenshotData) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.longScreenshotDataProvider.get(), (ActivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideActivityManagerProvider.get(), timeoutHandler, (BroadcastSender) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastSenderProvider.get(), (ScreenshotNotificationSmartActionsProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesScrnshtNotifSmartActionsProvider.get(), (ActionIntentExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.actionIntentExecutorProvider.get(), userManager, (AssistContentRequester) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistContentRequesterProvider.get(), messageContainerController2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenshotSoundControllerImplProvider, i);
    }
}