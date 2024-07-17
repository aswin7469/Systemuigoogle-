package com.google.android.systemui.smartspace;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.systemui.settings.UserTrackerImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
