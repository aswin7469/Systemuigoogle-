package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.shade.BaseShadeControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class OpenNotificationShade extends UserAction {
    public final Lazy notificationShadeWindowController;
    public final ShadeController shadeController;
    public final String tag = "Columbus/OpenNotif";
    public final UiEventLogger uiEventLogger;

    public OpenNotificationShade(Context context, Lazy lazy, ShadeController shadeController2, UiEventLogger uiEventLogger2) {
        super(context, (Set) null);
        this.notificationShadeWindowController = lazy;
        this.shadeController = shadeController2;
        this.uiEventLogger = uiEventLogger2;
        setAvailable(true);
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        boolean z = ((NotificationShadeWindowControllerImpl) ((NotificationShadeWindowController) this.notificationShadeWindowController.get())).mCurrentState.shadeOrQsExpanded;
        UiEventLogger uiEventLogger2 = this.uiEventLogger;
        ShadeController shadeController2 = this.shadeController;
        if (z) {
            shadeController2.postAnimateCollapseShade();
            uiEventLogger2.log(ColumbusEvent.COLUMBUS_INVOKED_NOTIFICATION_SHADE_CLOSE);
            return;
        }
        ((BaseShadeControllerImpl) shadeController2).animateExpandShade();
        uiEventLogger2.log(ColumbusEvent.COLUMBUS_INVOKED_NOTIFICATION_SHADE_OPEN);
    }
}
