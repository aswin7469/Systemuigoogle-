package com.google.android.systemui.dreamliner;

import android.content.Context;
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

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class DockObserver$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DockObserver f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ DockObserver$$ExternalSyntheticLambda1(DockObserver dockObserver, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = dockObserver;
        this.f$1 = obj;
    }

    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                DockObserver dockObserver = this.f$0;
                LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("onDockEvent mDockState = "), dockObserver.mDockState, "DLObserver");
                ((DockManager.DockEventListener) this.f$1).onEvent(dockObserver.mDockState);
                return;
            case 1:
                DockObserver dockObserver2 = this.f$0;
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
            default:
                DockObserver dockObserver3 = this.f$0;
                ResultReceiver resultReceiver = (ResultReceiver) this.f$1;
                DockIndicationController dockIndicationController = dockObserver3.mIndicationController;
                dockIndicationController.mShowPromoTimes = 0;
                dockIndicationController.mShowPromo = true;
                if (!dockIndicationController.mDozing || !dockIndicationController.mDocking) {
                    resultReceiver.send(1, (Bundle) null);
                    return;
                }
                dockIndicationController.showPromoInner();
                resultReceiver.send(0, (Bundle) null);
                return;
        }
    }
}
