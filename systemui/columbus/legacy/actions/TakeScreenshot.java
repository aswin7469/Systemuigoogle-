package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.os.Handler;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.ScreenshotHelper;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Set;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class TakeScreenshot extends UserAction {
    public final Handler handler;
    public final ScreenshotHelper screenshotHelper;
    public final String tag = "Columbus/TakeScreenshot";
    public final UiEventLogger uiEventLogger;

    public TakeScreenshot(Context context, Handler handler2, UiEventLogger uiEventLogger2) {
        super(context, (Set) null);
        this.handler = handler2;
        this.uiEventLogger = uiEventLogger2;
        this.screenshotHelper = new ScreenshotHelper(context);
        setAvailable(true);
    }

    public final boolean availableOnLockscreen() {
        return true;
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.screenshotHelper.takeScreenshot(6, this.handler, (Consumer) null);
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_SCREENSHOT);
    }
}
