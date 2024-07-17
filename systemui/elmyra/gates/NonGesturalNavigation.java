package com.google.android.systemui.elmyra.gates;

import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NonGesturalNavigation extends Gate {
    public boolean mCurrentModeIsGestural;
    public final NavigationModeController mModeController;
    public final AnonymousClass1 mModeListener = new NavigationModeController.ModeChangedListener() {
        public final void onNavigationModeChanged(int i) {
            boolean isGesturalMode = QuickStepContract.isGesturalMode(i);
            NonGesturalNavigation nonGesturalNavigation = NonGesturalNavigation.this;
            nonGesturalNavigation.mCurrentModeIsGestural = isGesturalMode;
            nonGesturalNavigation.notifyListener();
        }
    };

    public NonGesturalNavigation(Executor executor, NavigationModeController navigationModeController) {
        super(executor);
        this.mModeController = navigationModeController;
    }

    public final boolean isBlocked() {
        return !this.mCurrentModeIsGestural;
    }

    public final void onActivate() {
        this.mCurrentModeIsGestural = QuickStepContract.isGesturalMode(this.mModeController.addListener(this.mModeListener));
    }

    public final void onDeactivate() {
        this.mModeController.mListeners.remove(this.mModeListener);
    }
}
