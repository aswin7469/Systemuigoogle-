package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.recents.Recents;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LaunchOverview extends UserAction {
    public final Recents recents;
    public final String tag = "Columbus/LaunchOverview";
    public final UiEventLogger uiEventLogger;

    public LaunchOverview(Context context, Recents recents2, UiEventLogger uiEventLogger2) {
        super(context, (Set) null);
        this.recents = recents2;
        this.uiEventLogger = uiEventLogger2;
        setAvailable(true);
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.recents.toggleRecentApps();
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_OVERVIEW);
    }
}
