package com.google.android.systemui.elmyra.actions;

import android.content.Intent;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SnoozeAlarm extends DeskClockAction {
    public final Intent createDismissIntent() {
        return new Intent("android.intent.action.SNOOZE_ALARM");
    }

    public final String getAlertAction() {
        return "com.google.android.deskclock.action.ALARM_ALERT";
    }

    public final String getDoneAction() {
        return "com.google.android.deskclock.action.ALARM_DONE";
    }
}
