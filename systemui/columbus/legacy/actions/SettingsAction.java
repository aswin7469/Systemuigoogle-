package com.google.android.systemui.columbus.legacy.actions;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.shade.ShadeController;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.ColumbusServiceProxy;
import com.google.android.systemui.columbus.IColumbusService;
import com.google.android.systemui.columbus.IColumbusServiceGestureListener$Stub$Proxy;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Collections;
import java.util.Set;
import kotlin.collections.EmptySet;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SettingsAction extends Action implements IBinder.DeathRecipient {
    public IColumbusService columbusService;
    public IColumbusServiceGestureListener$Stub$Proxy columbusServiceGestureListener;
    public final ServiceAction$ColumbusServiceListener columbusServiceListener;
    public final ShadeController shadeController;
    public final Set supportedCallerPackages;
    public final String tag;
    public final IBinder token = new Binder();
    public final UiEventLogger uiEventLogger;

    public SettingsAction(Context context, ShadeController shadeController2, UiEventLogger uiEventLogger2) {
        super(context, (Set) null);
        EmptySet emptySet = EmptySet.INSTANCE;
        ServiceAction$ColumbusServiceConnection serviceAction$ColumbusServiceConnection = new ServiceAction$ColumbusServiceConnection(this);
        this.columbusServiceListener = new ServiceAction$ColumbusServiceListener(this);
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(context, ColumbusServiceProxy.class));
            context.bindService(intent, serviceAction$ColumbusServiceConnection, 1);
        } catch (SecurityException e) {
            Log.e("Columbus/ServiceAction", "Unable to bind to ColumbusServiceProxy", e);
        }
        updateAvailable$1();
        this.shadeController = shadeController2;
        this.uiEventLogger = uiEventLogger2;
        this.tag = "Columbus/SettingsAction";
        this.supportedCallerPackages = Collections.singleton("com.android.settings");
    }

    public final void binderDied() {
        Log.w("Columbus/ServiceAction", "Binder died");
        this.columbusServiceGestureListener = null;
        updateAvailable$1();
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_ON_SETTINGS);
        this.shadeController.cancelExpansionAndCollapseShade();
        IColumbusServiceGestureListener$Stub$Proxy iColumbusServiceGestureListener$Stub$Proxy = this.columbusServiceGestureListener;
        if (iColumbusServiceGestureListener$Stub$Proxy != null) {
            try {
                iColumbusServiceGestureListener$Stub$Proxy.onTrigger();
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

    public final void updateAvailable$1() {
        boolean z;
        if (this.columbusServiceGestureListener != null) {
            z = true;
        } else {
            z = false;
        }
        setAvailable(z);
    }
}
