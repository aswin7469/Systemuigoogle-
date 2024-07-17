package com.google.android.systemui.googlebattery;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.util.NoSuchElementException;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class GoogleBatteryManager {
    public static final boolean DEBUG = Log.isLoggable("GoogleBatteryManager", 3);

    public static void destroyHalInterface(IGoogleBattery iGoogleBattery, IBinder.DeathRecipient deathRecipient) {
        if (DEBUG) {
            Log.d("GoogleBatteryManager", "destroyHalInterface");
        }
        if (deathRecipient != null && iGoogleBattery != null) {
            ((IGoogleBattery.Stub.Proxy) iGoogleBattery).asBinder().unlinkToDeath(deathRecipient, 0);
        }
    }

    public static IGoogleBattery initHalInterface(IBinder.DeathRecipient deathRecipient) {
        if (DEBUG) {
            Log.d("GoogleBatteryManager", "initHalInterface");
        }
        try {
            IBinder allowBlocking = Binder.allowBlocking(ServiceManager.waitForDeclaredService("vendor.google.google_battery.IGoogleBattery/default"));
            if (allowBlocking == null) {
                return null;
            }
            IGoogleBattery asInterface = IGoogleBattery.Stub.asInterface(allowBlocking);
            if (!(asInterface == null || deathRecipient == null)) {
                allowBlocking.linkToDeath(deathRecipient, 0);
            }
            return asInterface;
        } catch (RemoteException | SecurityException | NoSuchElementException e) {
            Log.e("GoogleBatteryManager", "failed to get Google Battery HAL: ", e);
            return null;
        }
    }
}
