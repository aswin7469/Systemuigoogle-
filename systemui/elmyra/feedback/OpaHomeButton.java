package com.google.android.systemui.elmyra.feedback;

import android.view.View;
import android.view.ViewParent;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.navigationbar.NavigationBarView;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class OpaHomeButton extends NavigationBarEffect {
    public final CentralSurfaces mCentralSurfaces;
    public final KeyguardViewMediator mKeyguardViewMediator;

    public OpaHomeButton(KeyguardViewMediator keyguardViewMediator, CentralSurfaces centralSurfaces, NavigationModeController navigationModeController) {
        super(centralSurfaces, navigationModeController);
        this.mCentralSurfaces = centralSurfaces;
        this.mKeyguardViewMediator = keyguardViewMediator;
    }

    public final List findFeedbackEffects(NavigationBarView navigationBarView) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = navigationBarView.getHomeButton().mViews;
        for (int i = 0; i < arrayList2.size(); i++) {
            View view = (View) arrayList2.get(i);
            if (view instanceof FeedbackEffect) {
                arrayList.add((FeedbackEffect) view);
            }
        }
        return arrayList;
    }

    public final boolean isActiveFeedbackEffect(FeedbackEffect feedbackEffect) {
        if (this.mKeyguardViewMediator.isShowingAndNotOccluded()) {
            return false;
        }
        View view = ((CentralSurfacesImpl) this.mCentralSurfaces).getNavigationBarView().mCurrentView;
        for (ViewParent parent = ((View) feedbackEffect).getParent(); parent != null; parent = parent.getParent()) {
            if (parent.equals(view)) {
                return true;
            }
        }
        return false;
    }

    public final boolean validateFeedbackEffects(List list) {
        for (int i = 0; i < list.size(); i++) {
            if (!((View) list.get(i)).isAttachedToWindow()) {
                return false;
            }
        }
        return true;
    }
}
