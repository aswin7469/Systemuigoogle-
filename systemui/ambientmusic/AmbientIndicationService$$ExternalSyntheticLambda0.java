package com.google.android.systemui.ambientmusic;

import android.app.AlarmManager;
import android.app.PendingIntent;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class AmbientIndicationService$$ExternalSyntheticLambda0 implements AlarmManager.OnAlarmListener {
    public final /* synthetic */ AmbientIndicationContainer f$0;

    public /* synthetic */ AmbientIndicationService$$ExternalSyntheticLambda0(AmbientIndicationContainer ambientIndicationContainer) {
        this.f$0 = ambientIndicationContainer;
    }

    public final void onAlarm() {
        this.f$0.setAmbientMusic((CharSequence) null, (PendingIntent) null, (PendingIntent) null, 0, false, (String) null);
    }
}
