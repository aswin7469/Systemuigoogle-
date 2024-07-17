package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import android.os.Binder;
import com.android.systemui.shade.ShadeController;
import com.google.android.systemui.elmyra.feedback.AssistInvocationEffect;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CameraAction extends ServiceAction {
    public final String mCameraPackageName;
    public final ShadeController mShadeController;

    public CameraAction(Context context, Executor executor, ShadeController shadeController, AssistInvocationEffect assistInvocationEffect) {
        super(context, executor, Collections.singletonList(assistInvocationEffect));
        this.mCameraPackageName = context.getResources().getString(2131952606);
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
        this.mShadeController.cancelExpansionAndCollapseShade();
        super.onTrigger(detectionProperties);
    }
}
