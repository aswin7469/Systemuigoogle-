package com.google.android.systemui.dreamliner;

import android.content.Intent;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.wm.shell.animation.PhysicsAnimator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class DockGestureController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DockGestureController f$0;

    public /* synthetic */ DockGestureController$$ExternalSyntheticLambda0(DockGestureController dockGestureController, int i) {
        this.$r8$classId = i;
        this.f$0 = dockGestureController;
    }

    public final void run() {
        int i = this.$r8$classId;
        DynamicAnimation.AnonymousClass1 r2 = DynamicAnimation.TRANSLATION_X;
        DockGestureController dockGestureController = this.f$0;
        switch (i) {
            case 0:
                dockGestureController.hideGear();
                return;
            case 1:
                dockGestureController.getClass();
                dockGestureController.sendProtectedBroadcast(new Intent("com.google.android.systemui.dreamliner.PHOTO_EVENT"));
                return;
            case 2:
                PhysicsAnimator physicsAnimator = dockGestureController.mPreviewTargetAnimator;
                physicsAnimator.spring(r2, 0.0f, dockGestureController.mVelocityX, dockGestureController.mTargetSpringConfig);
                physicsAnimator.start();
                return;
            case 3:
                PhysicsAnimator physicsAnimator2 = dockGestureController.mPreviewTargetAnimator;
                physicsAnimator2.spring(r2, (float) dockGestureController.mPhotoPreview.getRight(), dockGestureController.mVelocityX, dockGestureController.mTargetSpringConfig);
                physicsAnimator2.withEndActions(new DockGestureController$$ExternalSyntheticLambda0(dockGestureController, 5));
                physicsAnimator2.start();
                return;
            case 4:
                dockGestureController.hidePhotoPreview(false);
                return;
            case 5:
                dockGestureController.mPhotoPreview.setAlpha(0.0f);
                dockGestureController.mPhotoPreview.setVisibility(4);
                return;
            default:
                dockGestureController.mSettingsGear.setVisibility(4);
                return;
        }
    }
}
