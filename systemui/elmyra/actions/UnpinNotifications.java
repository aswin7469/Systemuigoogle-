package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.google.android.systemui.elmyra.UserContentObserver;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UnpinNotifications extends Action {
    public final Context mContext;
    public boolean mHasPinnedHeadsUp;
    public final AnonymousClass1 mHeadsUpChangedListener = new OnHeadsUpChangedListener() {
        public final void onHeadsUpPinnedModeChanged(boolean z) {
            UnpinNotifications unpinNotifications = UnpinNotifications.this;
            if (unpinNotifications.mHasPinnedHeadsUp != z) {
                unpinNotifications.mHasPinnedHeadsUp = z;
                unpinNotifications.notifyListener();
            }
        }
    };
    public final Optional mHeadsUpManagerOptional;
    public boolean mSilenceSettingEnabled;

    public UnpinNotifications(Context context, Executor executor, Optional optional) {
        super(executor, (List) null);
        this.mContext = context;
        this.mHeadsUpManagerOptional = optional;
        if (optional.isPresent()) {
            updateHeadsUpListener();
            new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_silence_alerts_enabled"), new UnpinNotifications$$ExternalSyntheticLambda0(this), true);
            return;
        }
        Log.w("Elmyra/UnpinNotifications", "No HeadsUpManager");
    }

    public final boolean isAvailable() {
        if (this.mSilenceSettingEnabled) {
            return this.mHasPinnedHeadsUp;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.util.function.Consumer, java.lang.Object] */
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.mHeadsUpManagerOptional.ifPresent(new Object());
    }

    public final String toString() {
        return super.toString();
    }

    public final void updateHeadsUpListener() {
        Optional optional = this.mHeadsUpManagerOptional;
        if (optional.isPresent()) {
            boolean z = true;
            if (Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "assist_gesture_silence_alerts_enabled", 1, -2) == 0) {
                z = false;
            }
            if (this.mSilenceSettingEnabled != z) {
                this.mSilenceSettingEnabled = z;
                AnonymousClass1 r1 = this.mHeadsUpChangedListener;
                if (z) {
                    this.mHasPinnedHeadsUp = ((BaseHeadsUpManager) ((HeadsUpManager) optional.get())).mHasPinnedNotification;
                    ((BaseHeadsUpManager) ((HeadsUpManager) optional.get())).addListener(r1);
                } else {
                    this.mHasPinnedHeadsUp = false;
                    ((BaseHeadsUpManager) ((HeadsUpManager) optional.get())).mListeners.remove(r1);
                }
                notifyListener();
            }
        }
    }
}
