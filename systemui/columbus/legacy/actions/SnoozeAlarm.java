package com.google.android.systemui.columbus.legacy.actions;

import android.app.IActivityManager;
import android.content.Context;
import android.content.Intent;
import com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
