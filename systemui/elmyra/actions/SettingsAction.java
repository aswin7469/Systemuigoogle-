package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import android.os.Binder;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeControllerImpl;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SettingsAction extends ServiceAction {
    public final LaunchOpa mLaunchOpa;
    public final String mSettingsPackageName;
    public final ShadeController mShadeController;

    public SettingsAction(Context context, Executor executor, ShadeController shadeController, LaunchOpa launchOpa) {
        super(context, executor, (List) null);
        this.mSettingsPackageName = context.getResources().getString(2131953815);
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
        ((ShadeControllerImpl) this.mShadeController).cancelExpansionAndCollapseShade();
        super.onTrigger(detectionProperties);
    }

    public final void triggerAction() {
        if (this.mLaunchOpa.isAvailable()) {
            this.mLaunchOpa.launchOpa(0);
        }
    }
}
