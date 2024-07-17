package com.google.android.systemui.dreamliner;

import android.content.Intent;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.wm.shell.animation.PhysicsAnimator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class DockGestureController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DockGestureController f$0;

    public /* synthetic */ DockGestureController$$ExternalSyntheticLambda0(DockGestureController dockGestureController, int i) {
        this.$r8$classId = i;
        this.f$0 = dockGestureController;
    }

    public final void run() {
        DynamicAnimation.AnonymousClass1 r0 = DynamicAnimation.TRANSLATION_X;
        int i = this.$r8$classId;
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
                physicsAnimator.spring(r0, 0.0f, dockGestureController.mVelocityX, dockGestureController.mTargetSpringConfig);
                physicsAnimator.start();
                return;
            case 3:
                PhysicsAnimator physicsAnimator2 = dockGestureController.mPreviewTargetAnimator;
                physicsAnimator2.spring(r0, (float) dockGestureController.mPhotoPreview.getRight(), dockGestureController.mVelocityX, dockGestureController.mTargetSpringConfig);
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
