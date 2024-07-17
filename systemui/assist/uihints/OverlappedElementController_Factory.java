package com.google.android.systemui.assist.uihints;

import com.android.systemui.keyguard.domain.interactor.KeyguardBottomAreaInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.recents.OverviewProxyService;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class OverlappedElementController_Factory implements Provider {
    public static OverlappedElementController newInstance(OverviewProxyService overviewProxyService, KeyguardBottomAreaInteractor keyguardBottomAreaInteractor, KeyguardInteractor keyguardInteractor) {
        return new OverlappedElementController(overviewProxyService, keyguardBottomAreaInteractor, keyguardInteractor);
    }
}
