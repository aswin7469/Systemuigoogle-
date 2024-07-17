package com.google.android.systemui.elmyra.actions;

import android.content.Intent;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
