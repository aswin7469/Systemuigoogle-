package com.google.android.systemui.columbus.legacy.actions;

import android.util.Log;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UserSelectedAction$settingsChangeListener$1 implements ColumbusSettings.ColumbusSettingsChangeListener {
    public final /* synthetic */ UserSelectedAction this$0;

    public UserSelectedAction$settingsChangeListener$1(UserSelectedAction userSelectedAction) {
        this.this$0 = userSelectedAction;
    }

    public final void onSelectedActionChange(String str) {
        UserSelectedAction userSelectedAction = this.this$0;
        UserAction userAction = (UserAction) userSelectedAction.userSelectedActions.getOrDefault(str, userSelectedAction.takeScreenshot);
        if (!Intrinsics.areEqual(userAction, userSelectedAction.currentAction)) {
            userSelectedAction.currentAction.onGestureDetected(0, (GestureSensor.DetectionProperties) null);
            userSelectedAction.currentAction = userAction;
            Log.i("Columbus/SelectedAction", "User Action selected: " + userAction);
            userSelectedAction.updateAvailable$7();
        }
    }
}
