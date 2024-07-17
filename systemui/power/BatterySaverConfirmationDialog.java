package com.google.android.systemui.power;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.animation.DialogLaunchAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.phone.SystemUIDialog;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatterySaverConfirmationDialog {
    public final ActivityStarter mActivityStarter;
    public SystemUIDialog mConfirmationDialog;
    public final Context mContext;
    public final DialogLaunchAnimator mDialogLaunchAnimator;
    public boolean mIsStandardMode;
    public final UiEventLogger mUiEventLogger;

    public BatterySaverConfirmationDialog(Context context, ActivityStarter activityStarter, UiEventLogger uiEventLogger, DialogLaunchAnimator dialogLaunchAnimator) {
        this.mContext = context;
        this.mActivityStarter = activityStarter;
        this.mUiEventLogger = uiEventLogger;
        this.mDialogLaunchAnimator = dialogLaunchAnimator;
    }

    public final void log(BatteryMetricEvent batteryMetricEvent) {
        UiEventLogger uiEventLogger = this.mUiEventLogger;
        if (uiEventLogger == null) {
            return;
        }
        if (batteryMetricEvent.ordinal() != 14) {
            uiEventLogger.log(batteryMetricEvent);
        } else {
            uiEventLogger.logWithPosition(batteryMetricEvent, 0, (String) null, this.mIsStandardMode ^ true ? 1 : 0);
        }
    }
}
