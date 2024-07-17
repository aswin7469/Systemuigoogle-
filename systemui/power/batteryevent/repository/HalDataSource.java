package com.google.android.systemui.power.batteryevent.repository;

import android.util.Log;
import com.google.android.systemui.googlebattery.GoogleBatteryManager;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HalDataSource {
    public static final Map featureName = MapsKt.mapOf(new Pair(1, "Temp defend"), new Pair(3, "Dwell defend"));
    public final GoogleBatteryManagerWrapperImpl googleBatteryManager;
    public boolean lastDwellDefendStatus;
    public int lastGoogleBatteryDockDefendStatus;
    public boolean lastTempDefendStatus;

    public HalDataSource(GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapperImpl) {
        this.googleBatteryManager = googleBatteryManagerWrapperImpl;
    }

    public final void destroyGoogleBattery(IGoogleBattery iGoogleBattery) {
        HalDataSource$deathRecipient$1 halDataSource$deathRecipient$1 = HalDataSource$deathRecipient$1.INSTANCE;
        try {
            this.googleBatteryManager.getClass();
            GoogleBatteryManager.destroyHalInterface(iGoogleBattery, halDataSource$deathRecipient$1);
        } catch (Exception e) {
            Log.w("GoogleBatteryDataSource", "destroyHalInterface failed: ", e);
        }
    }

    public final String fetchFeatureStatus(IGoogleBattery iGoogleBattery, int i, boolean z) {
        String str;
        if (iGoogleBattery != null) {
            try {
                str = ((IGoogleBattery.Stub.Proxy) iGoogleBattery).getStringProperty(i);
            } catch (Exception e) {
                Map map = featureName;
                Object obj = map.get(Integer.valueOf(i));
                Log.w("GoogleBatteryDataSource", "retry fetchFeatureStatus: " + obj);
                if (z) {
                    HalDataSource$deathRecipient$1 halDataSource$deathRecipient$1 = HalDataSource$deathRecipient$1.INSTANCE;
                    this.googleBatteryManager.getClass();
                    IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface(halDataSource$deathRecipient$1);
                    if (initHalInterface == null) {
                        return "init google battery failed";
                    }
                    Object invoke = new HalDataSource$fetchFeatureStatus$1(this, i).invoke(initHalInterface);
                    destroyGoogleBattery(initHalInterface);
                    return (String) invoke;
                }
                Object obj2 = map.get(Integer.valueOf(i));
                Log.w("GoogleBatteryDataSource", "fetchFeatureStatus: " + obj2 + " failed", e);
                String message = e.getMessage();
                if (message == null) {
                    return "";
                }
                return message;
            }
        } else {
            str = null;
        }
        if (str == null) {
            str = "null googleBattery";
        }
        return str;
    }
}
