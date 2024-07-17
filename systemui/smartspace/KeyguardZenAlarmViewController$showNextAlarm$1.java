package com.google.android.systemui.smartspace;

import android.app.AlarmManager;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class KeyguardZenAlarmViewController$showNextAlarm$1 implements AlarmManager.OnAlarmListener {
    public final /* synthetic */ KeyguardZenAlarmViewController this$0;

    public KeyguardZenAlarmViewController$showNextAlarm$1(KeyguardZenAlarmViewController keyguardZenAlarmViewController) {
        this.this$0 = keyguardZenAlarmViewController;
    }

    public final void onAlarm() {
        this.this$0.showAlarm();
    }
}
