package com.google.android.systemui.screenshot;

import android.content.Context;
import android.os.Handler;
import android.provider.DeviceConfig;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;
import com.android.systemui.util.DeviceConfigProxy;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class GoogleScreenshotModule_ProvidesScrnshtNotifSmartActionsProviderFactory implements Provider {
    /* JADX WARNING: type inference failed for: r2v3, types: [com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider, java.lang.Object] */
    public static ScreenshotNotificationSmartActionsProvider providesScrnshtNotifSmartActionsProvider(DeviceConfigProxy deviceConfigProxy, Context context, Executor executor, Handler handler) {
        deviceConfigProxy.getClass();
        if (DeviceConfig.getBoolean("systemui", "enable_screenshot_notification_smart_actions", true)) {
            return new ScreenshotNotificationSmartActionsProviderGoogle(context, executor, handler);
        }
        return new Object();
    }
}
