package com.google.android.systemui.columbus.legacy.actions;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.columbus.ColumbusServiceProxy;
import com.google.android.systemui.columbus.ColumbusServiceProxy$binder$1;
import com.google.android.systemui.columbus.IColumbusService;
import com.google.android.systemui.columbus.IColumbusServiceGestureListener;
import com.google.android.systemui.columbus.IColumbusServiceListener;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.EmptySet;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ServiceAction extends Action implements IBinder.DeathRecipient {
    public IColumbusService columbusService;
    public IColumbusServiceGestureListener columbusServiceGestureListener;
    public final ColumbusServiceListener columbusServiceListener;
    public final IBinder token = new Binder();

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ColumbusServiceConnection implements ServiceConnection {
        public final /* synthetic */ ServiceAction this$0;

        public ColumbusServiceConnection(SettingsAction settingsAction) {
            this.this$0 = settingsAction;
        }

        /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.systemui.columbus.IColumbusService$Stub$Proxy, java.lang.Object] */
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IColumbusService iColumbusService;
            ServiceAction serviceAction = this.this$0;
            int i = ColumbusServiceProxy$binder$1.$r8$clinit;
            if (iBinder == null) {
                iColumbusService = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.systemui.columbus.IColumbusService");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IColumbusService)) {
                    ? obj = new Object();
                    obj.mRemote = iBinder;
                    iColumbusService = obj;
                } else {
                    iColumbusService = (IColumbusService) queryLocalInterface;
                }
            }
            serviceAction.columbusService = iColumbusService;
            try {
                ServiceAction serviceAction2 = this.this$0;
                IColumbusService iColumbusService2 = serviceAction2.columbusService;
                if (iColumbusService2 != null) {
                    iColumbusService2.registerServiceListener(serviceAction2.token, serviceAction2.columbusServiceListener);
                }
            } catch (RemoteException e) {
                Log.e("Columbus/ServiceAction", "Error registering listener", e);
            }
            this.this$0.getClass();
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            ServiceAction serviceAction = this.this$0;
            serviceAction.columbusService = null;
            serviceAction.getClass();
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ColumbusServiceListener extends IColumbusServiceListener.Stub {
        public final /* synthetic */ ServiceAction this$0;

        public ColumbusServiceListener(SettingsAction settingsAction) {
            this.this$0 = settingsAction;
            attachInterface(this, "com.google.android.systemui.columbus.IColumbusServiceListener");
        }

        /* JADX WARNING: type inference failed for: r4v2, types: [com.google.android.systemui.columbus.IColumbusServiceGestureListener$Stub$Proxy, java.lang.Object] */
        public final void setListener(IBinder iBinder, IBinder iBinder2) {
            IColumbusServiceGestureListener iColumbusServiceGestureListener;
            Object obj;
            ServiceAction serviceAction = this.this$0;
            String[] packagesForUid = serviceAction.context.getPackageManager().getPackagesForUid(Binder.getCallingUid());
            if (packagesForUid != null) {
                Iterator it = ((SettingsAction) serviceAction).supportedCallerPackages.iterator();
                while (true) {
                    iColumbusServiceGestureListener = null;
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (ArraysKt___ArraysKt.contains((Object[]) packagesForUid, (Object) (String) obj)) {
                        break;
                    }
                }
                if (obj == null) {
                    return;
                }
                if (iBinder2 != null || this.this$0.columbusServiceGestureListener != null) {
                    ServiceAction serviceAction2 = this.this$0;
                    if (iBinder2 != null) {
                        Object queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.systemui.columbus.IColumbusServiceGestureListener");
                        if (queryLocalInterface == null || !(queryLocalInterface instanceof IColumbusServiceGestureListener)) {
                            ? obj2 = new Object();
                            obj2.mRemote = iBinder2;
                            iColumbusServiceGestureListener = obj2;
                        } else {
                            iColumbusServiceGestureListener = (IColumbusServiceGestureListener) queryLocalInterface;
                        }
                    }
                    serviceAction2.columbusServiceGestureListener = iColumbusServiceGestureListener;
                    this.this$0.updateAvailable$1();
                    if (iBinder != null) {
                        try {
                            ServiceAction serviceAction3 = this.this$0;
                            if (iBinder2 == null) {
                                iBinder.unlinkToDeath(serviceAction3, 0);
                            } else {
                                iBinder.linkToDeath(serviceAction3, 0);
                            }
                        } catch (RemoteException e) {
                            Log.e("Columbus/ServiceAction", "RemoteException during linkToDeath", e);
                        } catch (NoSuchElementException e2) {
                            Log.e("Columbus/ServiceAction", "NoSuchElementException during linkToDeath", e2);
                        }
                    }
                }
            }
        }
    }

    public ServiceAction(Context context) {
        super(context, (Set) null);
        EmptySet emptySet = EmptySet.INSTANCE;
        SettingsAction settingsAction = (SettingsAction) this;
        ColumbusServiceConnection columbusServiceConnection = new ColumbusServiceConnection(settingsAction);
        this.columbusServiceListener = new ColumbusServiceListener(settingsAction);
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(context, ColumbusServiceProxy.class));
            context.bindService(intent, columbusServiceConnection, 1);
        } catch (SecurityException e) {
            Log.e("Columbus/ServiceAction", "Unable to bind to ColumbusServiceProxy", e);
        }
        updateAvailable$1();
    }

    public final void binderDied() {
        Log.w("Columbus/ServiceAction", "Binder died");
        this.columbusServiceGestureListener = null;
        updateAvailable$1();
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
