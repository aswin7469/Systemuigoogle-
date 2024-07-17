package com.google.android.systemui.dreamliner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.dock.DockManager;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.google.android.systemui.dreamliner.DockObserver;
import java.util.Optional;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class DockObserver$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ DockObserver$$ExternalSyntheticLambda0(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ResultReceiver resultReceiver = (ResultReceiver) this.f$1;
                DockIndicationController dockIndicationController = ((DockObserver) this.f$0).mIndicationController;
                dockIndicationController.mShowPromoTimes = 0;
                dockIndicationController.mShowPromo = true;
                if (!dockIndicationController.mDozing || !dockIndicationController.mDocking) {
                    resultReceiver.send(1, (Bundle) null);
                    return;
                }
                dockIndicationController.showPromoInner();
                resultReceiver.send(0, (Bundle) null);
                return;
            case 1:
                DockObserver dockObserver = (DockObserver) this.f$0;
                Runnable runnable = (Runnable) this.f$1;
                Optional optional = dockObserver.mWirelessCharger;
                int i = -1;
                if (optional.isEmpty()) {
                    Log.i("DLObserver", "hint is UNKNOWN for null wireless charger HAL");
                    dockObserver.mFanLevel = -1;
                } else {
                    long currentTimeMillis = System.currentTimeMillis();
                    WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) optional.get());
                    wirelessChargerImpl.initHALInterface();
                    Log.d("Dreamliner-WLC_HAL", "command=2");
                    IWirelessCharger iWirelessCharger = wirelessChargerImpl.mWirelessCharger;
                    if (iWirelessCharger != null) {
                        try {
                            i = ((IWirelessCharger.Stub.Proxy) iWirelessCharger).getFanLevel();
                        } catch (Exception e) {
                            Log.i("Dreamliner-WLC_HAL", "command=2 fail: " + e.getMessage());
                        }
                    }
                    dockObserver.mFanLevel = i;
                    Log.d("DLObserver", "command=2, l=" + dockObserver.mFanLevel + ", spending time=" + (System.currentTimeMillis() - currentTimeMillis));
                }
                if (runnable != null) {
                    runnable.run();
                    return;
                }
                return;
            case 2:
                DockObserver dockObserver2 = (DockObserver) this.f$0;
                Context context = (Context) this.f$1;
                ((UserTrackerImpl) dockObserver2.mUserTracker).addCallback(dockObserver2.mUserChangedCallback, dockObserver2.mMainExecutor);
                if (dockObserver2.mDreamlinerGear == null) {
                    Log.e("DLObserver", "initDockGestureController fail. dreamlinerGear is null");
                    return;
                }
                DockObserver.ProtectedBroadcastSender protectedBroadcastSender = dockObserver2.mProtectedBroadcastSender;
                ImageView imageView = dockObserver2.mDreamlinerGear;
                DockGestureController dockGestureController = new DockGestureController(context, protectedBroadcastSender, imageView, dockObserver2.mPhotoPreview, (View) imageView.getParent(), dockObserver2.mIndicationController, dockObserver2.mStatusBarStateController, dockObserver2.mKeyguardStateController);
                dockObserver2.mDockGestureController = dockGestureController;
                ((ConfigurationControllerImpl) dockObserver2.mConfigurationController).addCallback(dockGestureController);
                return;
            case 3:
                DockObserver dockObserver3 = (DockObserver) this.f$0;
                LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("onDockEvent mDockState = "), dockObserver3.mDockState, "DLObserver");
                ((DockManager.DockEventListener) this.f$1).onEvent(dockObserver3.mDockState);
                return;
            default:
                DockObserver.ProtectedBroadcastSender protectedBroadcastSender2 = (DockObserver.ProtectedBroadcastSender) this.f$0;
                Intent intent = (Intent) this.f$1;
                protectedBroadcastSender2.getClass();
                try {
                    protectedBroadcastSender2.mContext.sendBroadcast(intent);
                    return;
                } catch (SecurityException e2) {
                    Log.w("DLObserver", "Send protected broadcast failed. intent= " + intent, e2);
                    return;
                }
        }
    }
}
