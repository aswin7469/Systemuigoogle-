package com.google.android.systemui.assist.uihints;

import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.keyguard.domain.interactor.KeyguardBottomAreaInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.shared.recents.IOverviewProxy;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class OverlappedElementController {
    public float mAlpha = 1.0f;
    public final KeyguardBottomAreaInteractor mKeyguardBottomAreaInteractor;
    public final OverviewProxyService mOverviewProxyService;

    public OverlappedElementController(OverviewProxyService overviewProxyService, KeyguardBottomAreaInteractor keyguardBottomAreaInteractor, KeyguardInteractor keyguardInteractor) {
        this.mOverviewProxyService = overviewProxyService;
        this.mKeyguardBottomAreaInteractor = keyguardBottomAreaInteractor;
    }

    public final void setAlpha(float f) {
        Parcel obtain;
        float f2 = this.mAlpha;
        if (f2 != f) {
            if (f2 == 1.0f) {
                int i = (f > 1.0f ? 1 : (f == 1.0f ? 0 : -1));
            }
            this.mAlpha = f;
            float f3 = 1.0f - f;
            OverviewProxyService overviewProxyService = this.mOverviewProxyService;
            overviewProxyService.getClass();
            try {
                IOverviewProxy iOverviewProxy = overviewProxyService.mOverviewProxy;
                if (iOverviewProxy != null) {
                    IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                    obtain = Parcel.obtain(proxy.mRemote);
                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                    obtain.writeFloat(f3);
                    proxy.mRemote.transact(15, obtain, (Parcel) null, 1);
                    obtain.recycle();
                } else {
                    Log.e("OverviewProxyService", "Failed to get overview proxy for assistant visibility.");
                }
            } catch (RemoteException e) {
                Log.e("OverviewProxyService", "Failed to call notifyAssistantVisibilityChanged()", e);
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
            this.mKeyguardBottomAreaInteractor.repository._bottomAreaAlpha.setValue(Float.valueOf(f));
        }
    }
}
