package com.google.android.systemui.keyguard.ui.sections;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.viewmodel.AodAlphaViewModel;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11;
import com.google.android.systemui.keyguard.ui.viewmodel.KeyguardAmbientIndicationViewModel;
import dagger.Lazy;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DefaultAmbientIndicationAreaSection extends KeyguardSection {
    public final ActivityStarter activityStarter;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;

    public DefaultAmbientIndicationAreaSection(KeyguardUpdateMonitor keyguardUpdateMonitor2, KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, AodAlphaViewModel aodAlphaViewModel, Lazy lazy, PowerInteractor powerInteractor, ActivityStarter activityStarter2, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11) {
        this.keyguardUpdateMonitor = keyguardUpdateMonitor2;
        this.activityStarter = activityStarter2;
    }

    public final void applyConstraints(ConstraintSet constraintSet) {
        constraintSet.constrainWidth(2131361967, -1);
        if (this.keyguardUpdateMonitor.mAuthController.isUdfpsSupported()) {
            constraintSet.constrainHeight(2131361967, 0);
            constraintSet.connect(2131361967, 3, 2131362923, 4);
            constraintSet.connect(2131361967, 4, 2131362807, 3);
            constraintSet.connect(2131361967, 6, 0, 6);
            constraintSet.connect(2131361967, 7, 0, 7);
            return;
        }
        constraintSet.constrainHeight(2131361967, -2);
        constraintSet.connect(2131361967, 4, 2131362923, 3);
        constraintSet.connect(2131361967, 6, 0, 6);
        constraintSet.connect(2131361967, 7, 0, 7);
    }

    public final void removeViews(ConstraintLayout constraintLayout) {
        View findViewById = constraintLayout.findViewById(2131361967);
        if (findViewById != null) {
            constraintLayout.removeView(findViewById);
        }
    }

    public final void addViews(ConstraintLayout constraintLayout) {
    }

    public final void bindData(ConstraintLayout constraintLayout) {
    }
}
