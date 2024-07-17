package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import android.os.Binder;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeControllerImpl;
import com.google.android.systemui.elmyra.feedback.AssistInvocationEffect;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CameraAction extends ServiceAction {
    public final String mCameraPackageName;
    public final ShadeController mShadeController;

    public CameraAction(Context context, Executor executor, ShadeController shadeController, AssistInvocationEffect assistInvocationEffect) {
        super(context, executor, Collections.singletonList(assistInvocationEffect));
        this.mCameraPackageName = context.getResources().getString(2131952581);
        this.mShadeController = shadeController;
    }

    public final boolean checkSupportedCaller() {
        String str = this.mCameraPackageName;
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
}
