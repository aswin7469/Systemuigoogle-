package com.google.android.systemui.googlebattery;

import android.content.Context;
import android.os.IBinder;
import android.os.LocaleList;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import java.util.Locale;
import vendor.google.google_battery.ChargingStage;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AdaptiveChargingManager {
    public static final boolean DEBUG = Log.isLoggable("AdaptiveChargingManager", 3);
    Context mContext;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface AdaptiveChargingStatusReceiver {
        void onReceiveStatus(int i, String str);
    }

    public AdaptiveChargingManager(Context context) {
        this.mContext = context;
    }

    public static void queryStatus(final AdaptiveChargingStatusReceiver adaptiveChargingStatusReceiver) {
        AnonymousClass1 r0 = new IBinder.DeathRecipient() {
            public final void binderDied() {
                if (AdaptiveChargingManager.DEBUG) {
                    Log.d("AdaptiveChargingManager", "serviceDied");
                }
                AdaptiveChargingStatusReceiver.this.getClass();
            }
        };
        IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface(r0);
        if (initHalInterface == null) {
            adaptiveChargingStatusReceiver.getClass();
            return;
        }
        try {
            ChargingStage chargingStageAndDeadline = ((IGoogleBattery.Stub.Proxy) initHalInterface).getChargingStageAndDeadline();
            queryStatusReceived(adaptiveChargingStatusReceiver, chargingStageAndDeadline.stage, chargingStageAndDeadline.deadlineSecs);
        } catch (RemoteException | ServiceSpecificException | IllegalArgumentException e) {
            Log.e("AdaptiveChargingManager", "Failed to get Adaptive Chaging status: ", e);
        }
        GoogleBatteryManager.destroyHalInterface(initHalInterface, r0);
        adaptiveChargingStatusReceiver.getClass();
    }

    public static void queryStatusReceived(AdaptiveChargingStatusReceiver adaptiveChargingStatusReceiver, String str, int i) {
        if (DEBUG) {
            Log.d("AdaptiveChargingManager", "getChargingStageDeadlineCallback stage: \"" + str + "\", seconds: " + i);
        }
        adaptiveChargingStatusReceiver.onReceiveStatus(i, str);
    }

    public final String formatTimeToFull(long j) {
        String str;
        Locale locale;
        if (DateFormat.is24HourFormat(this.mContext)) {
            str = "Hm";
        } else {
            str = "hma";
        }
        LocaleList locales = this.mContext.getResources().getConfiguration().getLocales();
        if (locales == null || locales.isEmpty()) {
            locale = Locale.getDefault();
        } else {
            locale = locales.get(0);
        }
        return DateFormat.format(DateFormat.getBestDateTimePattern(locale, str), j).toString();
    }

    public boolean hasAdaptiveChargingFeature() {
        return this.mContext.getPackageManager().hasSystemFeature("com.google.android.feature.ADAPTIVE_CHARGING");
    }

    public final boolean isEnabled() {
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "adaptive_charging_enabled", 1) == 1) {
            return true;
        }
        return false;
    }
}
