package com.google.android.systemui.elmyra.feedback;

import android.os.PowerManager;
import android.os.SystemClock;
import androidx.compose.runtime.Anchor$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UserActivity implements FeedbackEffect {
    public final KeyguardStateController mKeyguardStateController;
    public int mLastStage = 0;
    public final PowerManager mPowerManager;
    public int mTriggerCount = 0;

    public UserActivity(KeyguardStateController keyguardStateController, PowerManager powerManager) {
        this.mKeyguardStateController = keyguardStateController;
        this.mPowerManager = powerManager;
    }

    public final void onProgress(int i, float f) {
        PowerManager powerManager;
        if (i != this.mLastStage && i == 2 && !((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing && (powerManager = this.mPowerManager) != null) {
            powerManager.userActivity(SystemClock.uptimeMillis(), 0, 0);
            this.mTriggerCount++;
        }
        this.mLastStage = i;
    }

    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        this.mTriggerCount--;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mTriggerCount -> ");
        return Anchor$$ExternalSyntheticOutline0.m(sb, this.mTriggerCount, "]");
    }

    public final void onRelease() {
    }
}
