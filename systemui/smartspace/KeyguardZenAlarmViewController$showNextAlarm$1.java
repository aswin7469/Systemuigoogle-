package com.google.android.systemui.smartspace;

import android.app.AlarmManager;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardZenAlarmViewController$showNextAlarm$1 implements AlarmManager.OnAlarmListener {
    public final /* synthetic */ KeyguardZenAlarmViewController this$0;

    public KeyguardZenAlarmViewController$showNextAlarm$1(KeyguardZenAlarmViewController keyguardZenAlarmViewController) {
        this.this$0 = keyguardZenAlarmViewController;
    }

    public final void onAlarm() {
        this.this$0.showAlarm();
    }
}
