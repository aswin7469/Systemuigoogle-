package com.google.android.systemui.columbus.legacy.actions;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.columbus.ColumbusServiceProxy$binder$1;
import com.google.android.systemui.columbus.IColumbusService;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ServiceAction$ColumbusServiceConnection implements ServiceConnection {
    public final /* synthetic */ SettingsAction this$0;

    public ServiceAction$ColumbusServiceConnection(SettingsAction settingsAction) {
        this.this$0 = settingsAction;
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.systemui.columbus.IColumbusService$Stub$Proxy, java.lang.Object] */
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IColumbusService iColumbusService;
        SettingsAction settingsAction = this.this$0;
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
        settingsAction.columbusService = iColumbusService;
        try {
            SettingsAction settingsAction2 = this.this$0;
            IColumbusService iColumbusService2 = settingsAction2.columbusService;
            if (iColumbusService2 != null) {
                iColumbusService2.registerServiceListener(settingsAction2.token, settingsAction2.columbusServiceListener);
            }
        } catch (RemoteException e) {
            Log.e("Columbus/ServiceAction", "Error registering listener", e);
        }
        this.this$0.getClass();
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        SettingsAction settingsAction = this.this$0;
        settingsAction.columbusService = null;
        settingsAction.getClass();
    }
}
