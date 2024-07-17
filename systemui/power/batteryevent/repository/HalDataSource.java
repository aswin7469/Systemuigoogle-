package com.google.android.systemui.power.batteryevent.repository;

import com.google.android.systemui.power.batteryevent.common.HalDataType;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class HalDataSource {
    public final GoogleBatteryManagerWrapperImpl googleBatteryManager;
    public int lastGoogleBatteryDockDefendStatus;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HalDataType.values().length];
            try {
                HalDataType halDataType = HalDataType.GOOGLE_BATTERY_DOCK_DEFEND_STATUS;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public HalDataSource(GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapperImpl) {
        this.googleBatteryManager = googleBatteryManagerWrapperImpl;
    }
}
