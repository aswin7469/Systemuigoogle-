package com.google.android.systemui.input;

import android.os.IBinder;
import android.provider.DeviceConfig;
import com.google.input.ITouchContextService$Stub$Proxy;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class TouchContextService$$ExternalSyntheticLambda0 implements IBinder.DeathRecipient {
    public final /* synthetic */ TouchContextService f$0;
    public final /* synthetic */ IBinder f$1;

    public /* synthetic */ TouchContextService$$ExternalSyntheticLambda0(TouchContextService touchContextService, IBinder iBinder) {
        this.f$0 = touchContextService;
        this.f$1 = iBinder;
    }

    public final void binderDied() {
        TouchContextService touchContextService = this.f$0;
        IBinder iBinder = this.f$1;
        synchronized (touchContextService.mServiceLock) {
            try {
                if (((ITouchContextService$Stub$Proxy) touchContextService.mTouchContextService).mRemote == iBinder) {
                    touchContextService.mTouchContextService = null;
                }
            } finally {
                while (true) {
                }
            }
        }
        synchronized (touchContextService.mPropertiesLock) {
            try {
                touchContextService.mActivePropertyNamespaces.clear();
                for (int i = 0; i < touchContextService.mPropertiesChangedListeners.size(); i++) {
                    DeviceConfig.removeOnPropertiesChangedListener((DeviceConfig.OnPropertiesChangedListener) touchContextService.mPropertiesChangedListeners.get(i));
                }
                touchContextService.mPropertiesChangedListeners.clear();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        touchContextService.mDisplayManager.unregisterDisplayListener(touchContextService.mDisplayListener);
        touchContextService.mAudioManager.removeOnModeChangedListener(touchContextService.mAudioModeListener);
    }
}
