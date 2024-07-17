package com.google.android.systemui.assist.uihints;

import com.android.systemui.keyguard.domain.interactor.KeyguardBottomAreaInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.recents.OverviewProxyService;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class OverlappedElementController_Factory implements Provider {
    public static OverlappedElementController newInstance(OverviewProxyService overviewProxyService, KeyguardBottomAreaInteractor keyguardBottomAreaInteractor, KeyguardInteractor keyguardInteractor) {
        return new OverlappedElementController(overviewProxyService, keyguardBottomAreaInteractor, keyguardInteractor);
    }
}
