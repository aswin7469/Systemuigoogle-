package com.google.android.systemui.power;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.phone.SystemUIDialog;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BatterySaverConfirmationDialog {
    public final ActivityStarter mActivityStarter;
    public SystemUIDialog mConfirmationDialog;
    public final Context mContext;
    public final DialogTransitionAnimator mDialogTransitionAnimator;
    public boolean mIsStandardMode;
    public final SystemUIDialog.Factory mSystemUIDialogFactory;
    public final UiEventLogger mUiEventLogger;

    public BatterySaverConfirmationDialog(Context context, ActivityStarter activityStarter, UiEventLogger uiEventLogger, DialogTransitionAnimator dialogTransitionAnimator, SystemUIDialog.Factory factory) {
        this.mContext = context;
        this.mActivityStarter = activityStarter;
        this.mUiEventLogger = uiEventLogger;
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
        this.mSystemUIDialogFactory = factory;
    }

    public final void log(BatteryMetricEvent batteryMetricEvent) {
        UiEventLogger uiEventLogger = this.mUiEventLogger;
        if (uiEventLogger == null) {
            return;
        }
        if (batteryMetricEvent.ordinal() != 17) {
            uiEventLogger.log(batteryMetricEvent);
        } else {
            uiEventLogger.logWithPosition(batteryMetricEvent, 0, (String) null, this.mIsStandardMode ^ true ? 1 : 0);
        }
    }
}
