package com.google.android.systemui.elmyra.gates;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class CameraVisibility$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ CameraVisibility f$0;

    public /* synthetic */ CameraVisibility$1$$ExternalSyntheticLambda0(CameraVisibility cameraVisibility) {
        this.f$0 = cameraVisibility;
    }

    public final void run() {
        CameraVisibility cameraVisibility = this.f$0;
        boolean isCameraShowing = cameraVisibility.isCameraShowing();
        if (cameraVisibility.mCameraShowing != isCameraShowing) {
            cameraVisibility.mCameraShowing = isCameraShowing;
            cameraVisibility.notifyListener();
        }
    }
}
