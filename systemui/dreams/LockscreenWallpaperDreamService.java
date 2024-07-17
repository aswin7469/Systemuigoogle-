package com.google.android.systemui.dreams;

import android.service.dreams.DreamService;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class LockscreenWallpaperDreamService extends DreamService {
    public final FeatureFlagsClassic mFeatureFlags;

    public LockscreenWallpaperDreamService(KeyguardInteractor keyguardInteractor, KeyguardViewMediator keyguardViewMediator, FeatureFlagsClassic featureFlagsClassic) {
        this.mFeatureFlags = featureFlagsClassic;
    }

    public final void onCreate() {
        super.onCreate();
        setWindowless(true);
    }

    public final void onDreamingStarted() {
        super.onDreamingStarted();
        FeatureFlagsClassic featureFlagsClassic = this.mFeatureFlags;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        featureFlagsClassic.getClass();
    }

    public final void onDreamingStopped() {
        super.onDreamingStopped();
        FeatureFlagsClassic featureFlagsClassic = this.mFeatureFlags;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        featureFlagsClassic.getClass();
    }
}
