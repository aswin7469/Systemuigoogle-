package com.google.android.systemui.assist;

import android.content.Context;
import android.os.UserManager;
import android.util.Log;
import android.view.View;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import dagger.Lazy;
import java.util.ArrayList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class OpaEnabledDispatcher implements OpaEnabledListener {
    public final Lazy mCentralSurfacesLazy;

    public OpaEnabledDispatcher(Lazy lazy) {
        this.mCentralSurfacesLazy = lazy;
    }

    public final void onOpaEnabledReceived(Context context, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5;
        if ((!z4 || !z || !z2) && !UserManager.isDeviceInDemoMode(context)) {
            z5 = false;
        } else {
            z5 = true;
        }
        CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) ((CentralSurfaces) this.mCentralSurfacesLazy.get());
        if (centralSurfacesImpl.getNavigationBarView() != null) {
            ArrayList arrayList = centralSurfacesImpl.getNavigationBarView().getHomeButton().mViews;
            for (int i = 0; i < arrayList.size(); i++) {
                OpaLayout opaLayout = (OpaLayout) ((View) arrayList.get(i));
                opaLayout.getClass();
                Log.i("OpaLayout", "Setting opa enabled to " + z5);
                opaLayout.mOpaEnabled = z5;
                opaLayout.mOpaEnabledNeedsUpdate = false;
                opaLayout.updateOpaLayout();
            }
        }
    }
}
