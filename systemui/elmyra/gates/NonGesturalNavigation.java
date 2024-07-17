package com.google.android.systemui.elmyra.gates;

import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
