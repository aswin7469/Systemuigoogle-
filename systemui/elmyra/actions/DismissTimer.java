package com.google.android.systemui.elmyra.actions;

import android.content.Intent;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DismissTimer extends DeskClockAction {
    public final Intent createDismissIntent() {
        return new Intent("android.intent.action.DISMISS_TIMER");
    }

    public final String getAlertAction() {
        return "com.google.android.deskclock.action.TIMER_ALERT";
    }

    public final String getDoneAction() {
        return "com.google.android.deskclock.action.TIMER_DONE";
    }
}
