package com.google.android.systemui.elmyra;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class ElmyraServiceProxy extends Service {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final AnonymousClass1 mBinder = new IElmyraService() {
        public static final /* synthetic */ int $r8$clinit = 0;

        {
            attachInterface(this, "com.google.android.systemui.elmyra.IElmyraService");
        }

        /* renamed from: onTransact$com$google$android$systemui$elmyra$IElmyraService$Stub */
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.google.android.systemui.elmyra.IElmyraService");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.google.android.systemui.elmyra.IElmyraService");
                return true;
            }
            if (i == 1) {
                IBinder readStrongBinder = parcel.readStrongBinder();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                parcel.enforceNoDataAvail();
                ElmyraServiceProxy elmyraServiceProxy = ElmyraServiceProxy.this;
                int i3 = ElmyraServiceProxy.$r8$clinit;
                elmyraServiceProxy.enforceCallingOrSelfPermission("com.google.android.elmyra.permission.CONFIGURE_ASSIST_GESTURE", "Must have com.google.android.elmyra.permission.CONFIGURE_ASSIST_GESTURE permission");
                try {
                    for (int size = ((ArrayList) ElmyraServiceProxy.this.mElmyraServiceListeners).size() - 1; size >= 0; size--) {
                        IElmyraServiceListener iElmyraServiceListener = ((ElmyraServiceListener) ((ArrayList) ElmyraServiceProxy.this.mElmyraServiceListeners).get(size)).mListener;
                        if (iElmyraServiceListener == null) {
                            ElmyraServiceProxy.this.mElmyraServiceListeners.remove(size);
                        } else {
                            iElmyraServiceListener.setListener(readStrongBinder, readStrongBinder2);
                        }
                    }
                } catch (RemoteException e) {
                    Log.e("Elmyra/ElmyraServiceProxy", "Action isn't connected", e);
                }
            } else if (i == 2) {
                ElmyraServiceProxy elmyraServiceProxy2 = ElmyraServiceProxy.this;
                int i4 = ElmyraServiceProxy.$r8$clinit;
                elmyraServiceProxy2.enforceCallingOrSelfPermission("com.google.android.elmyra.permission.CONFIGURE_ASSIST_GESTURE", "Must have com.google.android.elmyra.permission.CONFIGURE_ASSIST_GESTURE permission");
                try {
                    for (int size2 = ((ArrayList) ElmyraServiceProxy.this.mElmyraServiceListeners).size() - 1; size2 >= 0; size2--) {
                        IElmyraServiceListener iElmyraServiceListener2 = ((ElmyraServiceListener) ((ArrayList) ElmyraServiceProxy.this.mElmyraServiceListeners).get(size2)).mListener;
                        if (iElmyraServiceListener2 == null) {
                            ElmyraServiceProxy.this.mElmyraServiceListeners.remove(size2);
                        } else {
                            iElmyraServiceListener2.triggerAction();
                        }
                    }
                } catch (RemoteException e2) {
                    Log.e("Elmyra/ElmyraServiceProxy", "Error launching assistant", e2);
                }
            } else if (i != 3) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                parcel.enforceNoDataAvail();
                registerServiceListener(readStrongBinder3, readStrongBinder4);
            }
            return true;
        }

        /* JADX WARNING: type inference failed for: r2v1, types: [com.google.android.systemui.elmyra.ElmyraServiceProxy$ElmyraServiceListener, android.os.IBinder$DeathRecipient, java.lang.Object] */
        /* JADX WARNING: type inference failed for: r3v3, types: [com.google.android.systemui.elmyra.IElmyraServiceListener$Stub$Proxy, java.lang.Object] */
        public final void registerServiceListener(IBinder iBinder, IBinder iBinder2) {
            IElmyraServiceListener iElmyraServiceListener;
            ElmyraServiceProxy elmyraServiceProxy = ElmyraServiceProxy.this;
            int i = ElmyraServiceProxy.$r8$clinit;
            elmyraServiceProxy.enforceCallingOrSelfPermission("com.google.android.elmyra.permission.CONFIGURE_ASSIST_GESTURE", "Must have com.google.android.elmyra.permission.CONFIGURE_ASSIST_GESTURE permission");
            if (iBinder == null) {
                Log.e("Elmyra/ElmyraServiceProxy", "Binder token must not be null");
            } else if (iBinder2 == null) {
                for (int i2 = 0; i2 < ((ArrayList) ElmyraServiceProxy.this.mElmyraServiceListeners).size(); i2++) {
                    if (iBinder.equals(((ElmyraServiceListener) ((ArrayList) ElmyraServiceProxy.this.mElmyraServiceListeners).get(i2)).mToken)) {
                        ElmyraServiceListener elmyraServiceListener = (ElmyraServiceListener) ((ArrayList) ElmyraServiceProxy.this.mElmyraServiceListeners).get(i2);
                        IBinder iBinder3 = elmyraServiceListener.mToken;
                        if (iBinder3 != null) {
                            iBinder3.unlinkToDeath(elmyraServiceListener, 0);
                        }
                        ElmyraServiceProxy.this.mElmyraServiceListeners.remove(i2);
                        return;
                    }
                }
            } else {
                List list = ElmyraServiceProxy.this.mElmyraServiceListeners;
                IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.systemui.elmyra.IElmyraServiceListener");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IElmyraServiceListener)) {
                    ? obj = new Object();
                    obj.mRemote = iBinder2;
                    iElmyraServiceListener = obj;
                } else {
                    iElmyraServiceListener = (IElmyraServiceListener) queryLocalInterface;
                }
                ? obj2 = new Object();
                obj2.mToken = iBinder;
                obj2.mListener = iElmyraServiceListener;
                try {
                    iBinder.linkToDeath(obj2, 0);
                } catch (RemoteException e) {
                    Log.e("Elmyra/ElmyraServiceProxy", "Unable to linkToDeath", e);
                }
                list.add(obj2);
            }
        }

        public final IBinder asBinder() {
            return this;
        }
    };
    public final List mElmyraServiceListeners = new ArrayList();

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ElmyraServiceListener implements IBinder.DeathRecipient {
        public IElmyraServiceListener mListener;
        public IBinder mToken;

        public final void binderDied() {
            Log.w("Elmyra/ElmyraServiceProxy", "ElmyraServiceListener binder died");
            this.mToken = null;
            this.mListener = null;
        }
    }

    public final IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }
}
