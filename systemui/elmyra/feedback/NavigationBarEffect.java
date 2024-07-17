package com.google.android.systemui.elmyra.feedback;

import com.android.systemui.navigationbar.NavigationBarView;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class NavigationBarEffect implements FeedbackEffect, NavigationModeController.ModeChangedListener {
    public final CentralSurfaces mCentralSurfaces;
    public final List mFeedbackEffects = new ArrayList();
    public int mNavMode;

    public NavigationBarEffect(CentralSurfaces centralSurfaces, NavigationModeController navigationModeController) {
        this.mCentralSurfaces = centralSurfaces;
        this.mNavMode = navigationModeController.addListener(this);
    }

    public abstract List findFeedbackEffects(NavigationBarView navigationBarView);

    public abstract boolean isActiveFeedbackEffect(FeedbackEffect feedbackEffect);

    public final void onNavigationModeChanged(int i) {
        this.mNavMode = i;
        refreshFeedbackEffects();
    }

    public final void onProgress(int i, float f) {
        refreshFeedbackEffects();
        int i2 = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) this.mFeedbackEffects;
            if (i2 < arrayList.size()) {
                FeedbackEffect feedbackEffect = (FeedbackEffect) arrayList.get(i2);
                if (isActiveFeedbackEffect(feedbackEffect)) {
                    feedbackEffect.onProgress(i, f);
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public final void onRelease() {
        refreshFeedbackEffects();
        int i = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) this.mFeedbackEffects;
            if (i < arrayList.size()) {
                ((FeedbackEffect) arrayList.get(i)).onRelease();
                i++;
            } else {
                return;
            }
        }
    }

    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        refreshFeedbackEffects();
        int i = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) this.mFeedbackEffects;
            if (i < arrayList.size()) {
                ((FeedbackEffect) arrayList.get(i)).onResolve(detectionProperties);
                i++;
            } else {
                return;
            }
        }
    }

    public final void refreshFeedbackEffects() {
        NavigationBarView navigationBarView = ((CentralSurfacesImpl) this.mCentralSurfaces).getNavigationBarView();
        if (navigationBarView == null || !(!QuickStepContract.isGesturalMode(this.mNavMode))) {
            reset$1();
            return;
        }
        List list = this.mFeedbackEffects;
        if (!validateFeedbackEffects(list)) {
            list.clear();
        }
        if (list.isEmpty()) {
            list.addAll(findFeedbackEffects(navigationBarView));
        }
    }

    public void reset$1() {
        this.mFeedbackEffects.clear();
    }

    public abstract boolean validateFeedbackEffects(List list);
}
