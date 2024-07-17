package com.google.android.systemui.columbus.legacy.actions;

import android.app.IActivityManager;
import android.content.Context;
import android.content.Intent;
import com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SnoozeAlarm extends DeskClockAction {
    public final String tag = "Columbus/SnoozeAlarm";

    public SnoozeAlarm(Context context, SilenceAlertsDisabled silenceAlertsDisabled, IActivityManager iActivityManager) {
        super(context, silenceAlertsDisabled, iActivityManager);
    }

    public final Intent createDismissIntent() {
        return new Intent("android.intent.action.SNOOZE_ALARM");
    }

    public final String getAlertAction() {
        return "com.google.android.deskclock.action.ALARM_ALERT";
    }

    public final String getDoneAction() {
        return "com.google.android.deskclock.action.ALARM_DONE";
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }
}
