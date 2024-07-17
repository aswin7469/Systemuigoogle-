package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeControllerImpl;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.IColumbusServiceGestureListener;
import com.google.android.systemui.columbus.IColumbusServiceGestureListener$Stub$Proxy;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Collections;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SettingsAction extends ServiceAction {
    public final ShadeController shadeController;
    public final Set supportedCallerPackages = Collections.singleton("com.android.settings");
    public final String tag = "Columbus/SettingsAction";
    public final UiEventLogger uiEventLogger;

    public SettingsAction(Context context, ShadeController shadeController2, UiEventLogger uiEventLogger2) {
        super(context);
        this.shadeController = shadeController2;
        this.uiEventLogger = uiEventLogger2;
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_ON_SETTINGS);
        ((ShadeControllerImpl) this.shadeController).cancelExpansionAndCollapseShade();
        IColumbusServiceGestureListener iColumbusServiceGestureListener = this.columbusServiceGestureListener;
        if (iColumbusServiceGestureListener != null) {
            try {
                ((IColumbusServiceGestureListener$Stub$Proxy) iColumbusServiceGestureListener).onTrigger();
            } catch (DeadObjectException e) {
                Log.e("Columbus/ServiceAction", "Listener crashed or closed without unregistering", e);
                this.columbusServiceGestureListener = null;
                updateAvailable$1();
            } catch (RemoteException e2) {
                Log.e("Columbus/ServiceAction", "Unable to send trigger, setting listener to null", e2);
                this.columbusServiceGestureListener = null;
                updateAvailable$1();
            }
        }
    }
}
