package com.google.android.systemui.keyguard.ui.sections;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.google.android.systemui.keyguard.ui.viewmodel.KeyguardAmbientIndicationViewModel;
import dagger.Lazy;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DefaultAmbientIndicationAreaSection extends KeyguardSection {
    public final ActivityStarter activityStarter;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;

    public DefaultAmbientIndicationAreaSection(KeyguardUpdateMonitor keyguardUpdateMonitor2, KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, KeyguardRootViewModel keyguardRootViewModel, Lazy lazy, PowerInteractor powerInteractor, ActivityStarter activityStarter2) {
        this.keyguardUpdateMonitor = keyguardUpdateMonitor2;
        this.activityStarter = activityStarter2;
    }

    public final void applyConstraints(ConstraintSet constraintSet) {
        constraintSet.constrainWidth(2131361962, -1);
        if (this.keyguardUpdateMonitor.mAuthController.isUdfpsSupported()) {
            constraintSet.constrainHeight(2131361962, 0);
            constraintSet.connect(2131361962, 3, 2131362900, 4);
            constraintSet.connect(2131361962, 4, 2131362786, 3);
            constraintSet.connect(2131361962, 6, 0, 6);
            constraintSet.connect(2131361962, 7, 0, 7);
            return;
        }
        constraintSet.constrainHeight(2131361962, -2);
        constraintSet.connect(2131361962, 4, 2131362900, 3);
        constraintSet.connect(2131361962, 6, 0, 6);
        constraintSet.connect(2131361962, 7, 0, 7);
    }

    public final void removeViews(ConstraintLayout constraintLayout) {
        View findViewById = constraintLayout.findViewById(2131361962);
        if (findViewById != null) {
            constraintLayout.removeView(findViewById);
        }
    }

    public final void bindData(ConstraintLayout constraintLayout) {
    }

    public final void addViews() {
    }
}
