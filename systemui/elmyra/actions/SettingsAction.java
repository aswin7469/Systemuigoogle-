package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import android.os.Binder;
import com.android.systemui.shade.ShadeController;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SettingsAction extends ServiceAction {
    public final LaunchOpa mLaunchOpa;
    public final String mSettingsPackageName;
    public final ShadeController mShadeController;

    public SettingsAction(Context context, Executor executor, ShadeController shadeController, LaunchOpa launchOpa) {
        super(context, executor, (List) null);
        this.mSettingsPackageName = context.getResources().getString(2131953858);
        this.mShadeController = shadeController;
        this.mLaunchOpa = launchOpa;
    }

    public final boolean checkSupportedCaller() {
        String str = this.mSettingsPackageName;
        String[] packagesForUid = this.mContext.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid == null) {
            return false;
        }
        return Arrays.asList(packagesForUid).contains(str);
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.mShadeController.cancelExpansionAndCollapseShade();
        super.onTrigger(detectionProperties);
    }

    public final void triggerAction() {
        if (this.mLaunchOpa.isAvailable()) {
            this.mLaunchOpa.launchOpa(0);
        }
    }
}
