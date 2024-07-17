package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.recents.Recents;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
