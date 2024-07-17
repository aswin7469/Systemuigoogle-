package com.google.android.systemui.elmyra.actions;

import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.google.android.systemui.elmyra.UserContentObserver;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class DeskClockAction extends Action {
    public boolean mAlertFiring;
    public final AnonymousClass1 mAlertReceiver = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DeskClockAction.this.getAlertAction())) {
                DeskClockAction.this.mAlertFiring = true;
            } else if (intent.getAction().equals(DeskClockAction.this.getDoneAction())) {
                DeskClockAction.this.mAlertFiring = false;
            }
            DeskClockAction.this.notifyListener();
        }
    };
    public final Context mContext;
    public boolean mReceiverRegistered;

    public DeskClockAction(Context context, Executor executor) {
        super(executor, (List) null);
        this.mContext = context;
        updateBroadcastReceiver();
        new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_silence_alerts_enabled"), new DeskClockAction$$ExternalSyntheticLambda0(this), true);
    }

    public abstract Intent createDismissIntent();

    public abstract String getAlertAction();

    public abstract String getDoneAction();

    public final boolean isAvailable() {
        return this.mAlertFiring;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        Context context = this.mContext;
        try {
            Intent createDismissIntent = createDismissIntent();
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            makeBasic.setDisallowEnterPictureInPictureWhileLaunching(true);
            createDismissIntent.setFlags(268435456);
            createDismissIntent.putExtra("android.intent.extra.REFERRER", Uri.parse("android-app://" + context.getPackageName()));
            context.startActivityAsUser(createDismissIntent, makeBasic.toBundle(), UserHandle.CURRENT);
        } catch (ActivityNotFoundException e) {
            Log.e("Elmyra/DeskClockAction", "Failed to dismiss alert", e);
        }
        this.mAlertFiring = false;
        notifyListener();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mReceiverRegistered -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mReceiverRegistered, "]");
    }

    public final void updateBroadcastReceiver() {
        this.mAlertFiring = false;
        boolean z = this.mReceiverRegistered;
        Context context = this.mContext;
        if (z) {
            context.unregisterReceiver(this.mAlertReceiver);
            this.mReceiverRegistered = false;
        }
        if (Settings.Secure.getIntForUser(context.getContentResolver(), "assist_gesture_silence_alerts_enabled", 1, -2) != 0) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(getAlertAction());
            intentFilter.addAction(getDoneAction());
            UserHandle userHandle = UserHandle.CURRENT;
            this.mContext.registerReceiverAsUser(this.mAlertReceiver, userHandle, intentFilter, "com.android.systemui.permission.SEND_ALERT_BROADCASTS", (Handler) null, 2);
            this.mReceiverRegistered = true;
        }
        notifyListener();
    }
}
