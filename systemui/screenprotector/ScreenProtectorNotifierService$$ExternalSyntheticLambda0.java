package com.google.android.systemui.screenprotector;

import android.content.Context;
import android.os.IBinder;
import com.google.input.algos.spd.IScreenProtectorDetectorService;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ScreenProtectorNotifierService$$ExternalSyntheticLambda0 implements IBinder.DeathRecipient {
    public final /* synthetic */ ScreenProtectorNotifierService f$0;
    public final /* synthetic */ IBinder f$1;

    public /* synthetic */ ScreenProtectorNotifierService$$ExternalSyntheticLambda0(ScreenProtectorNotifierService screenProtectorNotifierService, IBinder iBinder) {
        this.f$0 = screenProtectorNotifierService;
        this.f$1 = iBinder;
    }

    public final void binderDied() {
        ScreenProtectorNotifierService screenProtectorNotifierService = this.f$0;
        IBinder iBinder = this.f$1;
        synchronized (screenProtectorNotifierService.mServiceLock) {
            try {
                if (((IScreenProtectorDetectorService.Stub.Proxy) screenProtectorNotifierService.mDetectorService).mRemote == iBinder) {
                    screenProtectorNotifierService.mDetectorService = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        screenProtectorNotifierService.mContext.getContentResolver().unregisterContentObserver(screenProtectorNotifierService.mScreenProtectorModeObserver);
        Context context = screenProtectorNotifierService.mContext;
        ScreenProtectorSharedPreferenceUtils.getSharedPreferences(context).unregisterOnSharedPreferenceChangeListener(screenProtectorNotifierService.mSharedPreferenceChangeListener);
        ScreenProtectorSharedPreferenceUtils.getSharedPreferences(screenProtectorNotifierService.mContext).edit().putInt("notification_response", -1).apply();
    }
}
