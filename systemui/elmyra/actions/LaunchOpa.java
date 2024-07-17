package com.google.android.systemui.elmyra.actions;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.tuner.TunerService;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.assist.OpaEnabledListener;
import com.google.android.systemui.assist.OpaEnabledReceiver;
import com.google.android.systemui.elmyra.UserContentObserver;
import com.google.android.systemui.elmyra.feedback.AssistInvocationEffect;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.Collections;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LaunchOpa extends Action implements TunerService.Tunable {
    public final AssistManagerGoogle mAssistManager;
    public final Context mContext;
    public boolean mEnableForAnyAssistant;
    public boolean mIsGestureEnabled;
    public boolean mIsOpaEnabled;
    public final KeyguardManager mKeyguardManager;
    public final AnonymousClass1 mOpaEnabledListener;
    public final ShadeController mShadeController;

    public LaunchOpa(Context context, Executor executor, ShadeController shadeController, AssistManagerGoogle assistManagerGoogle, KeyguardManager keyguardManager, TunerService tunerService, AssistInvocationEffect assistInvocationEffect) {
        super(executor, Collections.singletonList(assistInvocationEffect));
        boolean z;
        AnonymousClass1 r4 = new OpaEnabledListener() {
            public final void onOpaEnabledReceived(Context context, boolean z, boolean z2, boolean z3, boolean z4) {
                boolean z5;
                boolean z6 = true;
                LaunchOpa launchOpa = LaunchOpa.this;
                if (z2 || launchOpa.mEnableForAnyAssistant) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (!z || !z5 || !z3) {
                    z6 = false;
                }
                if (launchOpa.mIsOpaEnabled != z6) {
                    launchOpa.mIsOpaEnabled = z6;
                    launchOpa.notifyListener();
                }
            }
        };
        this.mContext = context;
        this.mShadeController = shadeController;
        this.mAssistManager = assistManagerGoogle;
        this.mKeyguardManager = keyguardManager;
        boolean z2 = true;
        if (Settings.Secure.getIntForUser(context.getContentResolver(), "assist_gesture_enabled", 1, -2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mIsGestureEnabled = z;
        new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_enabled"), new LaunchOpa$$ExternalSyntheticLambda0(this), true);
        tunerService.addTunable(this, "assist_gesture_any_assistant");
        this.mEnableForAnyAssistant = Settings.Secure.getInt(context.getContentResolver(), "assist_gesture_any_assistant", 0) != 1 ? false : z2;
        assistManagerGoogle.addOpaEnabledListener(r4);
    }

    public final boolean isAvailable() {
        if (!this.mIsGestureEnabled || !this.mIsOpaEnabled) {
            return false;
        }
        return true;
    }

    public final void launchOpa(long j) {
        int i;
        Bundle bundle = new Bundle();
        if (this.mKeyguardManager.isKeyguardLocked()) {
            i = 14;
        } else {
            i = 13;
        }
        bundle.putInt("triggered_by", i);
        bundle.putLong("latency_id", j);
        bundle.putInt("invocation_type", 2);
        this.mAssistManager.startAssist(bundle);
    }

    public final void onProgress(int i, float f) {
        updateFeedbackEffects(i, f);
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        long j;
        this.mShadeController.cancelExpansionAndCollapseShade();
        triggerFeedbackEffects(detectionProperties);
        if (detectionProperties != null) {
            j = detectionProperties.mActionId;
        } else {
            j = 0;
        }
        launchOpa(j);
    }

    public final void onTuningChanged(String str, String str2) {
        if ("assist_gesture_any_assistant".equals(str)) {
            this.mEnableForAnyAssistant = "1".equals(str2);
            OpaEnabledReceiver opaEnabledReceiver = this.mAssistManager.mOpaEnabledReceiver;
            opaEnabledReceiver.dispatchOpaEnabledState(opaEnabledReceiver.mContext);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mIsGestureEnabled -> ");
        sb.append(this.mIsGestureEnabled);
        sb.append("; mIsOpaEnabled -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mIsOpaEnabled, "]");
    }
}
