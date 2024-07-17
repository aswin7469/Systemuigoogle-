package com.google.android.systemui.elmyra.gates;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class CameraVisibility$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CameraVisibility f$0;

    public /* synthetic */ CameraVisibility$1$$ExternalSyntheticLambda0(CameraVisibility cameraVisibility, int i) {
        this.$r8$classId = i;
        this.f$0 = cameraVisibility;
    }

    public final void run() {
        int i = this.$r8$classId;
        CameraVisibility cameraVisibility = this.f$0;
        switch (i) {
            case 0:
                boolean isCameraShowing = cameraVisibility.isCameraShowing();
                if (cameraVisibility.mCameraShowing != isCameraShowing) {
                    cameraVisibility.mCameraShowing = isCameraShowing;
                    cameraVisibility.notifyListener();
                    return;
                }
                return;
            default:
                boolean isCameraShowing2 = cameraVisibility.isCameraShowing();
                if (cameraVisibility.mCameraShowing != isCameraShowing2) {
                    cameraVisibility.mCameraShowing = isCameraShowing2;
                    cameraVisibility.notifyListener();
                    return;
                }
                return;
        }
    }
}
