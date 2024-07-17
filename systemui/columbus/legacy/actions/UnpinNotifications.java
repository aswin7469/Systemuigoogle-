package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.util.Log;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Optional;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class UnpinNotifications extends Action {
    public boolean hasPinnedHeadsUp;
    public final UnpinNotifications$headsUpChangedListener$1 headsUpChangedListener;
    public final HeadsUpManager headsUpManager;
    public final SilenceAlertsDisabled silenceAlertsDisabled;
    public final String tag = "Columbus/UnpinNotif";

    public UnpinNotifications(Context context, SilenceAlertsDisabled silenceAlertsDisabled2, Optional optional) {
        super(context, (Set) null);
        boolean z;
        this.silenceAlertsDisabled = silenceAlertsDisabled2;
        HeadsUpManager headsUpManager2 = (HeadsUpManager) optional.orElse((Object) null);
        this.headsUpManager = headsUpManager2;
        this.headsUpChangedListener = new UnpinNotifications$headsUpChangedListener$1(this);
        UnpinNotifications$gateListener$1 unpinNotifications$gateListener$1 = new UnpinNotifications$gateListener$1(this);
        if (headsUpManager2 == null) {
            Log.w("Columbus/UnpinNotif", "No HeadsUpManager");
        } else {
            silenceAlertsDisabled2.registerListener(unpinNotifications$gateListener$1);
        }
        if (silenceAlertsDisabled2.isBlocking() || !this.hasPinnedHeadsUp) {
            z = false;
        } else {
            z = true;
        }
        setAvailable(z);
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        HeadsUpManager headsUpManager2 = this.headsUpManager;
        if (headsUpManager2 != null) {
            ((BaseHeadsUpManager) headsUpManager2).unpinAll();
        }
    }
}
