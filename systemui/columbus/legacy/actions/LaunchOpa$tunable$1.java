package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.tuner.TunerService;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.assist.OpaEnabledReceiver;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LaunchOpa$tunable$1 implements TunerService.Tunable {
    public final /* synthetic */ LaunchOpa this$0;

    public LaunchOpa$tunable$1(LaunchOpa launchOpa) {
        this.this$0 = launchOpa;
    }

    public final void onTuningChanged(String str, String str2) {
        if (Intrinsics.areEqual("assist_gesture_any_assistant", str)) {
            boolean areEqual = Intrinsics.areEqual("1", str2);
            LaunchOpa launchOpa = this.this$0;
            launchOpa.enableForAnyAssistant = areEqual;
            AssistManagerGoogle assistManagerGoogle = launchOpa.assistManager;
            if (assistManagerGoogle != null) {
                OpaEnabledReceiver opaEnabledReceiver = assistManagerGoogle.mOpaEnabledReceiver;
                opaEnabledReceiver.dispatchOpaEnabledState(opaEnabledReceiver.mContext);
            }
        }
    }
}
