package com.google.android.systemui.dreams;

import android.os.Bundle;
import android.service.dreams.DreamService;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class LockscreenWallpaperDreamService extends DreamService {
    public final KeyguardInteractor mKeyguardInteractor;
    public final KeyguardViewMediator mKeyguardViewMediator;

    public LockscreenWallpaperDreamService(KeyguardInteractor keyguardInteractor, KeyguardViewMediator keyguardViewMediator) {
        this.mKeyguardInteractor = keyguardInteractor;
        this.mKeyguardViewMediator = keyguardViewMediator;
    }

    public final void onCreate() {
        super.onCreate();
        setWindowless(true);
    }

    public final void onDreamingStarted() {
        super.onDreamingStarted();
        ((KeyguardRepositoryImpl) this.mKeyguardInteractor.repository)._isActiveDreamLockscreenHosted.setValue(Boolean.TRUE);
        Bundle bundle = new Bundle();
        bundle.putBoolean("force_show", true);
        KeyguardViewMediator.AnonymousClass12 r3 = this.mKeyguardViewMediator.mHandler;
        r3.removeMessages(10);
        r3.sendMessageAtFrontOfQueue(r3.obtainMessage(10, bundle));
    }

    public final void onDreamingStopped() {
        super.onDreamingStopped();
        ((KeyguardRepositoryImpl) this.mKeyguardInteractor.repository)._isActiveDreamLockscreenHosted.setValue(Boolean.FALSE);
    }
}
