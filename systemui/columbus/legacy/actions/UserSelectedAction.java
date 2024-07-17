package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import com.google.android.systemui.columbus.legacy.PowerManagerWrapper;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class UserSelectedAction extends Action {
    public UserAction currentAction;
    public final UserSelectedAction$keyguardMonitorCallback$1 keyguardMonitorCallback = new UserSelectedAction$keyguardMonitorCallback$1(this);
    public final KeyguardStateController keyguardStateController;
    public final PowerManagerWrapper powerManager;
    public final TakeScreenshot takeScreenshot;
    public final Map userSelectedActions;
    public final UserSelectedAction$wakefulnessLifecycleObserver$1 wakefulnessLifecycleObserver = new UserSelectedAction$wakefulnessLifecycleObserver$1(this);

    public UserSelectedAction(Context context, ColumbusSettings columbusSettings, Map map, TakeScreenshot takeScreenshot2, KeyguardStateController keyguardStateController2, PowerManagerWrapper powerManagerWrapper, WakefulnessLifecycle wakefulnessLifecycle) {
        super(context, (Set) null);
        this.userSelectedActions = map;
        this.takeScreenshot = takeScreenshot2;
        this.keyguardStateController = keyguardStateController2;
        this.powerManager = powerManagerWrapper;
        UserSelectedAction$settingsChangeListener$1 userSelectedAction$settingsChangeListener$1 = new UserSelectedAction$settingsChangeListener$1(this);
        UserAction userAction = (UserAction) map.getOrDefault(columbusSettings.selectedAction(), takeScreenshot2);
        this.currentAction = userAction;
        Log.i("Columbus/SelectedAction", "User Action selected: " + userAction);
        columbusSettings.registerColumbusSettingsChangeListener(userSelectedAction$settingsChangeListener$1);
        UserSelectedAction$sublistener$1 userSelectedAction$sublistener$1 = new UserSelectedAction$sublistener$1(this);
        for (UserAction userAction2 : map.values()) {
            userAction2.listeners.add(userSelectedAction$sublistener$1);
        }
        ((KeyguardStateControllerImpl) this.keyguardStateController).addCallback(this.keyguardMonitorCallback);
        wakefulnessLifecycle.addObserver(this.wakefulnessLifecycleObserver);
        updateAvailable$7();
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.currentAction.getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig();
    }

    public final void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties) {
        this.currentAction.onGestureDetected(i, detectionProperties);
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.currentAction.onTrigger(detectionProperties);
    }

    public final String toString() {
        String action = super.toString();
        UserAction userAction = this.currentAction;
        return action + " [currentAction -> " + userAction + "]";
    }

    public final void updateAvailable$7() {
        Boolean bool;
        UserAction userAction = this.currentAction;
        if (!userAction.isAvailable) {
            setAvailable(false);
            return;
        }
        if (!userAction.availableOnScreenOff()) {
            PowerManager powerManager2 = this.powerManager.powerManager;
            if (powerManager2 != null) {
                bool = Boolean.valueOf(powerManager2.isInteractive());
            } else {
                bool = null;
            }
            if (!Intrinsics.areEqual(bool, Boolean.TRUE)) {
                setAvailable(false);
                return;
            }
        }
        if (this.currentAction.availableOnLockscreen() || !((KeyguardStateControllerImpl) this.keyguardStateController).mShowing) {
            setAvailable(true);
        } else {
            setAvailable(false);
        }
    }

    public final void updateFeedbackEffects(int i, GestureSensor.DetectionProperties detectionProperties) {
        this.currentAction.updateFeedbackEffects(i, detectionProperties);
    }
}
