package com.google.android.systemui.smartspace;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.systemui.settings.UserTrackerImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class NextClockAlarmController$userUnlockReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ NextClockAlarmController this$0;

    public NextClockAlarmController$userUnlockReceiver$1(NextClockAlarmController nextClockAlarmController) {
        this.this$0 = nextClockAlarmController;
    }

    public final void onReceive(Context context, Intent intent) {
        Log.d("NextClockAlarmCtlr", "User unlock received");
        this.this$0.broadcastDispatcher.unregisterReceiver(this);
        NextClockAlarmController nextClockAlarmController = this.this$0;
        nextClockAlarmController.updateSession(((UserTrackerImpl) nextClockAlarmController.userTracker).getUserContext());
    }
}
