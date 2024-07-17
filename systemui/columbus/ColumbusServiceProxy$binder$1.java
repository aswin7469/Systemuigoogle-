package com.google.android.systemui.columbus;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.columbus.ColumbusServiceProxy;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__ReversedViewsKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColumbusServiceProxy$binder$1 extends Binder implements IColumbusService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ ColumbusServiceProxy this$0;

    public ColumbusServiceProxy$binder$1(ColumbusServiceProxy columbusServiceProxy) {
        this.this$0 = columbusServiceProxy;
        attachInterface(this, "com.google.android.systemui.columbus.IColumbusService");
    }

    /* renamed from: onTransact$com$google$android$systemui$columbus$IColumbusService$Stub */
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.google.android.systemui.columbus.IColumbusService");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.google.android.systemui.columbus.IColumbusService");
            return true;
        }
        if (i == 1) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            parcel.enforceNoDataAvail();
            ColumbusServiceProxy columbusServiceProxy = this.this$0;
            int i3 = ColumbusServiceProxy.$r8$clinit;
            columbusServiceProxy.enforceCallingOrSelfPermission("com.google.android.columbus.permission.CONFIGURE_COLUMBUS_GESTURE", "Must have com.google.android.columbus.permission.CONFIGURE_COLUMBUS_GESTURE permission");
            for (int size = ((ArrayList) this.this$0.columbusServiceListeners).size() - 1; -1 < size; size--) {
                IColumbusServiceListener iColumbusServiceListener = ((ColumbusServiceProxy.ColumbusServiceListener) ((ArrayList) this.this$0.columbusServiceListeners).get(size)).listener;
                if (iColumbusServiceListener == null) {
                    this.this$0.columbusServiceListeners.remove(size);
                } else {
                    try {
                        iColumbusServiceListener.setListener(readStrongBinder, readStrongBinder2);
                    } catch (RemoteException e) {
                        Log.e("Columbus/ColumbusProxy", "Cannot set listener", e);
                        this.this$0.columbusServiceListeners.remove(size);
                    }
                }
            }
        } else if (i != 2) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            IBinder readStrongBinder3 = parcel.readStrongBinder();
            IBinder readStrongBinder4 = parcel.readStrongBinder();
            parcel.enforceNoDataAvail();
            registerServiceListener(readStrongBinder3, readStrongBinder4);
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [android.os.IBinder$DeathRecipient, java.lang.Object, com.google.android.systemui.columbus.ColumbusServiceProxy$ColumbusServiceListener] */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.lang.Object, com.google.android.systemui.columbus.IColumbusServiceListener$Stub$Proxy] */
    public final void registerServiceListener(IBinder iBinder, IBinder iBinder2) {
        IColumbusServiceListener iColumbusServiceListener;
        ColumbusServiceProxy columbusServiceProxy = this.this$0;
        int i = ColumbusServiceProxy.$r8$clinit;
        columbusServiceProxy.enforceCallingOrSelfPermission("com.google.android.columbus.permission.CONFIGURE_COLUMBUS_GESTURE", "Must have com.google.android.columbus.permission.CONFIGURE_COLUMBUS_GESTURE permission");
        if (iBinder == null) {
            Log.e("Columbus/ColumbusProxy", "Binder token must not be null");
        } else if (iBinder2 == null) {
            CollectionsKt__ReversedViewsKt.removeAll(this.this$0.columbusServiceListeners, new ColumbusServiceProxy$binder$1$registerServiceListener$1(iBinder));
        } else {
            List list = this.this$0.columbusServiceListeners;
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.systemui.columbus.IColumbusServiceListener");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IColumbusServiceListener)) {
                ? obj = new Object();
                obj.mRemote = iBinder2;
                iColumbusServiceListener = obj;
            } else {
                iColumbusServiceListener = (IColumbusServiceListener) queryLocalInterface;
            }
            ? obj2 = new Object();
            obj2.token = iBinder;
            obj2.listener = iColumbusServiceListener;
            try {
                iBinder.linkToDeath(obj2, 0);
            } catch (RemoteException e) {
                Log.e("Columbus/ColumbusProxy", "Unable to linkToDeath", e);
            }
            list.add(obj2);
        }
    }

    public final IBinder asBinder() {
        return this;
    }
}
