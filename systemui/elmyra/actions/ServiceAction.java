package com.google.android.systemui.elmyra.actions;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.elmyra.ElmyraServiceProxy;
import com.google.android.systemui.elmyra.IElmyraService;
import com.google.android.systemui.elmyra.IElmyraServiceGestureListener;
import com.google.android.systemui.elmyra.IElmyraServiceGestureListener$Stub$Proxy;
import com.google.android.systemui.elmyra.IElmyraServiceListener;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ServiceAction extends Action implements IBinder.DeathRecipient {
    public final Context mContext;
    public IElmyraService mElmyraService;
    public IElmyraServiceGestureListener mElmyraServiceGestureListener;
    public final ElmyraServiceListener mElmyraServiceListener;
    public final IBinder mToken = new Binder();

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ElmyraServiceConnection implements ServiceConnection {
        public ElmyraServiceConnection() {
        }

        /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Object, com.google.android.systemui.elmyra.IElmyraService$Stub$Proxy] */
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IElmyraService iElmyraService;
            ServiceAction serviceAction = ServiceAction.this;
            int i = ElmyraServiceProxy.AnonymousClass1.$r8$clinit;
            if (iBinder == null) {
                iElmyraService = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.systemui.elmyra.IElmyraService");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IElmyraService)) {
                    ? obj = new Object();
                    obj.mRemote = iBinder;
                    iElmyraService = obj;
                } else {
                    iElmyraService = (IElmyraService) queryLocalInterface;
                }
            }
            serviceAction.mElmyraService = iElmyraService;
            try {
                ServiceAction serviceAction2 = ServiceAction.this;
                serviceAction2.mElmyraService.registerServiceListener(serviceAction2.mToken, serviceAction2.mElmyraServiceListener);
            } catch (RemoteException e) {
                Log.e("Elmyra/ServiceAction", "Error registering listener", e);
            }
            ServiceAction.this.getClass();
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            ServiceAction serviceAction = ServiceAction.this;
            serviceAction.mElmyraService = null;
            serviceAction.getClass();
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ElmyraServiceListener extends IElmyraServiceListener.Stub {
        public ElmyraServiceListener() {
            attachInterface(this, "com.google.android.systemui.elmyra.IElmyraServiceListener");
        }

        /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Object, com.google.android.systemui.elmyra.IElmyraServiceGestureListener$Stub$Proxy] */
        public final void setListener(IBinder iBinder, IBinder iBinder2) {
            IElmyraServiceGestureListener iElmyraServiceGestureListener;
            if (ServiceAction.this.checkSupportedCaller()) {
                if (iBinder2 != null || ServiceAction.this.mElmyraServiceGestureListener != null) {
                    if (iBinder2 == null) {
                        iElmyraServiceGestureListener = null;
                    } else {
                        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.systemui.elmyra.IElmyraServiceGestureListener");
                        if (queryLocalInterface == null || !(queryLocalInterface instanceof IElmyraServiceGestureListener)) {
                            ? obj = new Object();
                            obj.mRemote = iBinder2;
                            iElmyraServiceGestureListener = obj;
                        } else {
                            iElmyraServiceGestureListener = (IElmyraServiceGestureListener) queryLocalInterface;
                        }
                    }
                    ServiceAction serviceAction = ServiceAction.this;
                    if (iElmyraServiceGestureListener != serviceAction.mElmyraServiceGestureListener) {
                        serviceAction.mElmyraServiceGestureListener = iElmyraServiceGestureListener;
                        serviceAction.notifyListener();
                    }
                    if (iBinder == null) {
                        return;
                    }
                    if (iBinder2 != null) {
                        try {
                            iBinder.linkToDeath(ServiceAction.this, 0);
                        } catch (RemoteException e) {
                            Log.e("Elmyra/ServiceAction", "RemoteException during linkToDeath", e);
                        } catch (NoSuchElementException unused) {
                        }
                    } else {
                        iBinder.unlinkToDeath(ServiceAction.this, 0);
                    }
                }
            }
        }

        public final void triggerAction() {
            if (ServiceAction.this.checkSupportedCaller()) {
                ServiceAction.this.triggerAction();
            }
        }
    }

    public ServiceAction(Context context, Executor executor, List list) {
        super(executor, list);
        ElmyraServiceConnection elmyraServiceConnection = new ElmyraServiceConnection();
        this.mElmyraServiceListener = new ElmyraServiceListener();
        this.mContext = context;
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(context, ElmyraServiceProxy.class));
            context.bindService(intent, elmyraServiceConnection, 1);
        } catch (SecurityException e) {
            Log.e("Elmyra/ServiceAction", "Unable to bind to ElmyraServiceProxy", e);
        }
    }

    public final void binderDied() {
        Log.w("Elmyra/ServiceAction", "Binder died");
        this.mElmyraServiceGestureListener = null;
        notifyListener();
    }

    public abstract boolean checkSupportedCaller();

    public final boolean isAvailable() {
        if (this.mElmyraServiceGestureListener != null) {
            return true;
        }
        return false;
    }

    public final void onProgress(int i, float f) {
        if (this.mElmyraServiceGestureListener != null) {
            updateFeedbackEffects(i, f);
            try {
                ((IElmyraServiceGestureListener$Stub$Proxy) this.mElmyraServiceGestureListener).onGestureProgress(i, f);
            } catch (DeadObjectException e) {
                Log.e("Elmyra/ServiceAction", "Listener crashed or closed without unregistering", e);
                this.mElmyraServiceGestureListener = null;
                notifyListener();
            } catch (RemoteException e2) {
                Log.e("Elmyra/ServiceAction", "Unable to send progress, setting listener to null", e2);
                this.mElmyraServiceGestureListener = null;
                notifyListener();
            }
        }
    }

    public void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        if (this.mElmyraServiceGestureListener != null) {
            triggerFeedbackEffects(detectionProperties);
            try {
                ((IElmyraServiceGestureListener$Stub$Proxy) this.mElmyraServiceGestureListener).onGestureDetected();
            } catch (DeadObjectException e) {
                Log.e("Elmyra/ServiceAction", "Listener crashed or closed without unregistering", e);
                this.mElmyraServiceGestureListener = null;
                notifyListener();
            } catch (RemoteException e2) {
                Log.e("Elmyra/ServiceAction", "Unable to send onGestureDetected; removing listener", e2);
                this.mElmyraServiceGestureListener = null;
                notifyListener();
            }
        }
    }

    public void triggerAction() {
    }
}
