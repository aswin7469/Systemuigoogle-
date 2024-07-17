package com.google.android.systemui.elmyra.feedback;

import android.content.Context;
import android.view.View;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.navigationbar.NavigationBarView;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SquishyNavigationButtons extends NavigationBarEffect {
    public final KeyguardViewMediator mKeyguardViewMediator;
    public final SquishyViewController mViewController;

    public SquishyNavigationButtons(Context context, KeyguardViewMediator keyguardViewMediator, CentralSurfaces centralSurfaces, NavigationModeController navigationModeController) {
        super(centralSurfaces, navigationModeController);
        this.mViewController = new SquishyViewController(context);
        this.mKeyguardViewMediator = keyguardViewMediator;
    }

    public final List findFeedbackEffects(NavigationBarView navigationBarView) {
        SquishyViewController squishyViewController = this.mViewController;
        squishyViewController.translateViews(0.0f);
        List list = squishyViewController.mLeftViews;
        list.clear();
        List list2 = squishyViewController.mRightViews;
        list2.clear();
        ArrayList arrayList = navigationBarView.getBackButton().mViews;
        for (int i = 0; i < arrayList.size(); i++) {
            list.add((View) arrayList.get(i));
        }
        ArrayList arrayList2 = navigationBarView.getRecentsButton().mViews;
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            list2.add((View) arrayList2.get(i2));
        }
        return Arrays.asList(new FeedbackEffect[]{squishyViewController});
    }

    public final boolean isActiveFeedbackEffect(FeedbackEffect feedbackEffect) {
        return !this.mKeyguardViewMediator.isShowingAndNotOccluded();
    }

    public final void reset$1() {
        super.reset$1();
        SquishyViewController squishyViewController = this.mViewController;
        squishyViewController.translateViews(0.0f);
        squishyViewController.mLeftViews.clear();
        squishyViewController.mRightViews.clear();
    }

    public final boolean validateFeedbackEffects(List list) {
        SquishyViewController squishyViewController;
        List list2;
        boolean z = false;
        int i = 0;
        while (true) {
            squishyViewController = this.mViewController;
            ArrayList arrayList = (ArrayList) squishyViewController.mLeftViews;
            int size = arrayList.size();
            list2 = squishyViewController.mRightViews;
            if (i >= size) {
                int i2 = 0;
                while (true) {
                    ArrayList arrayList2 = (ArrayList) list2;
                    if (i2 >= arrayList2.size()) {
                        z = true;
                        break;
                    } else if (!((View) arrayList2.get(i2)).isAttachedToWindow()) {
                        break;
                    } else {
                        i2++;
                    }
                }
            } else if (!((View) arrayList.get(i)).isAttachedToWindow()) {
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            squishyViewController.translateViews(0.0f);
            squishyViewController.mLeftViews.clear();
            list2.clear();
        }
        return z;
    }
}
