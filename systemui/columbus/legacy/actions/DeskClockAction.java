package com.google.android.systemui.columbus.legacy.actions;

import android.app.ActivityOptions;
import android.app.IActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class DeskClockAction extends Action {
    public boolean alertFiring;
    public final DeskClockAction$alertReceiver$1 alertReceiver = new DeskClockAction$alertReceiver$1(this);
    public boolean receiverRegistered;
    public final SilenceAlertsDisabled silenceAlertsDisabled;

    public DeskClockAction(Context context, SilenceAlertsDisabled silenceAlertsDisabled2, IActivityManager iActivityManager) {
        super(context, (Set) null);
        this.silenceAlertsDisabled = silenceAlertsDisabled2;
        DeskClockAction$gateListener$1 deskClockAction$gateListener$1 = new DeskClockAction$gateListener$1(this);
        DeskClockAction$userSwitchCallback$1 deskClockAction$userSwitchCallback$1 = new DeskClockAction$userSwitchCallback$1(this);
        silenceAlertsDisabled2.registerListener(deskClockAction$gateListener$1);
        try {
            iActivityManager.registerUserSwitchObserver(deskClockAction$userSwitchCallback$1, "Columbus/DeskClockAct");
        } catch (RemoteException e) {
            Log.e("Columbus/DeskClockAct", "Failed to register user switch observer", e);
        }
        updateBroadcastReceiver();
    }

    public abstract Intent createDismissIntent();

    public abstract String getAlertAction();

    public abstract String getDoneAction();

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        Intent createDismissIntent = createDismissIntent();
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setDisallowEnterPictureInPictureWhileLaunching(true);
        createDismissIntent.setFlags(268435456);
        Context context = this.context;
        String packageName = context.getPackageName();
        createDismissIntent.putExtra("android.intent.extra.REFERRER", Uri.parse("android-app://" + packageName));
        try {
            context.startActivityAsUser(createDismissIntent, makeBasic.toBundle(), UserHandle.CURRENT);
        } catch (ActivityNotFoundException e) {
            Log.e("Columbus/DeskClockAct", "Failed to dismiss alert", e);
        }
        this.alertFiring = false;
        setAvailable(false);
    }

    public final String toString() {
        String action = super.toString();
        boolean z = this.receiverRegistered;
        return action + " [receiverRegistered -> " + z + "]";
    }

    public final void updateBroadcastReceiver() {
        this.alertFiring = false;
        if (this.receiverRegistered) {
            this.context.unregisterReceiver(this.alertReceiver);
            this.receiverRegistered = false;
        }
        if (!this.silenceAlertsDisabled.isBlocking()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(getAlertAction());
            intentFilter.addAction(getDoneAction());
            UserHandle userHandle = UserHandle.CURRENT;
            this.context.registerReceiverAsUser(this.alertReceiver, userHandle, intentFilter, "com.android.systemui.permission.SEND_ALERT_BROADCASTS", (Handler) null, 2);
            this.receiverRegistered = true;
        }
        setAvailable(this.alertFiring);
    }
}
