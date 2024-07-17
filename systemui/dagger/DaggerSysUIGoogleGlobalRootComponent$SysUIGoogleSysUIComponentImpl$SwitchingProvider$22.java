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
import com.android.systemui.screenshot.ImageCapture;
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

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$22 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$22(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final ScreenshotController create(int i) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        Context context = switchingProvider.sysUIGoogleGlobalRootComponentImpl.context;
        Object obj = switchingProvider.wMComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        ScrollCaptureClient scrollCaptureClient = new ScrollCaptureClient((IWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIWindowManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        ImageExporter imageExporter = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).imageExporter();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.sysUIGoogleGlobalRootComponentImpl;
        Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideMainExecutorProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.sysUIGoogleGlobalRootComponentImpl;
        ImageCapture imageCapture = (ImageCapture) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).imageCaptureImplProvider.get();
        ScrollCaptureClient scrollCaptureClient2 = new ScrollCaptureClient((IWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4.provideIWindowManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4.context);
        ScrollCaptureController scrollCaptureController = new ScrollCaptureController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.context, (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideBackgroundExecutorProvider.get(), scrollCaptureClient2, new ImageTileSet(new Handler()), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.provideUiEventLoggerProvider.get());
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3.getClass();
        TimeoutHandler timeoutHandler = new TimeoutHandler(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3.sysUIGoogleGlobalRootComponentImpl.context);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj;
        UserManager userManager = (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideUserManagerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4.sysUIGoogleGlobalRootComponentImpl;
        ActivityManager activityManager = (ActivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideActivityManagerProvider.get();
        LongScreenshotData longScreenshotData = (LongScreenshotData) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).longScreenshotDataProvider.get();
        return new ScreenshotController(context, (FeatureFlags) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).featureFlagsClassicReleaseProvider.get(), (ScreenshotSmartActions) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).screenshotSmartActionsProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$23) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).factoryProvider26.get(), scrollCaptureClient, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideUiEventLoggerProvider.get(), imageExporter, imageCapture, executor, scrollCaptureController, longScreenshotData, activityManager, timeoutHandler, (BroadcastSender) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).broadcastSenderProvider.get(), (ScreenshotNotificationSmartActionsProvider) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).providesScrnshtNotifSmartActionsProvider.get(), (ActionIntentExecutor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).actionIntentExecutorProvider.get(), userManager, (AssistContentRequester) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).assistContentRequesterProvider.get(), new MessageContainerController(new WorkProfileMessageController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.context, (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.provideUserManagerProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.providePackageManagerProvider.get()), new ScreenshotDetectionController((IWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.provideIWindowManagerProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.providePackageManagerProvider.get()), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4.featureFlagsClassicReleaseProvider.get()), ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).screenshotSoundControllerImplProvider, i);
    }
}
