package com.google.android.systemui.elmyra.actions;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.elmyra.ElmyraServiceProxy;
import com.google.android.systemui.elmyra.IElmyraService;
import com.google.android.systemui.elmyra.IElmyraServiceGestureListener$Stub$Proxy;
import com.google.android.systemui.elmyra.IElmyraServiceListener;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ServiceAction extends Action implements IBinder.DeathRecipient {
    public final Context mContext;
    public IElmyraService mElmyraService;
    public IElmyraServiceGestureListener$Stub$Proxy mElmyraServiceGestureListener;
    public final ElmyraServiceListener mElmyraServiceListener;
    public final IBinder mToken = new Binder();

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
                this.mElmyraServiceGestureListener.onGestureProgress(i, f);
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
                this.mElmyraServiceGestureListener.onGestureDetected();
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

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class ElmyraServiceListener extends Binder implements IElmyraServiceListener {
        public ElmyraServiceListener() {
            attachInterface(this, "com.google.android.systemui.elmyra.IElmyraServiceListener");
        }

        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.google.android.systemui.elmyra.IElmyraServiceListener");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.google.android.systemui.elmyra.IElmyraServiceListener");
                return true;
            }
            if (i == 1) {
                IBinder readStrongBinder = parcel.readStrongBinder();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                parcel.enforceNoDataAvail();
                setListener(readStrongBinder, readStrongBinder2);
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                triggerAction();
            }
            return true;
        }

        /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Object, com.google.android.systemui.elmyra.IElmyraServiceGestureListener$Stub$Proxy] */
        public final void setListener(IBinder iBinder, IBinder iBinder2) {
            IElmyraServiceGestureListener$Stub$Proxy iElmyraServiceGestureListener$Stub$Proxy;
            if (ServiceAction.this.checkSupportedCaller()) {
                if (iBinder2 != null || ServiceAction.this.mElmyraServiceGestureListener != null) {
                    if (iBinder2 == null) {
                        iElmyraServiceGestureListener$Stub$Proxy = null;
                    } else {
                        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.systemui.elmyra.IElmyraServiceGestureListener");
                        if (queryLocalInterface == null || !(queryLocalInterface instanceof IElmyraServiceGestureListener$Stub$Proxy)) {
                            ? obj = new Object();
                            obj.mRemote = iBinder2;
                            iElmyraServiceGestureListener$Stub$Proxy = obj;
                        } else {
                            iElmyraServiceGestureListener$Stub$Proxy = (IElmyraServiceGestureListener$Stub$Proxy) queryLocalInterface;
                        }
                    }
                    ServiceAction serviceAction = ServiceAction.this;
                    if (iElmyraServiceGestureListener$Stub$Proxy != serviceAction.mElmyraServiceGestureListener) {
                        serviceAction.mElmyraServiceGestureListener = iElmyraServiceGestureListener$Stub$Proxy;
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

        public final IBinder asBinder() {
            return this;
        }
    }

    public void triggerAction() {
    }
}
