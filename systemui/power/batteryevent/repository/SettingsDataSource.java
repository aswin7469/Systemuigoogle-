package com.google.android.systemui.power.batteryevent.repository;

import android.content.Context;
import com.google.android.systemui.power.batteryevent.common.SettingsDataType;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SettingsDataSource {
    public final Context context;
    public int lastDockDefenderByPass;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SettingsDataType.values().length];
            try {
                SettingsDataType settingsDataType = SettingsDataType.DOCK_DEFENDER_BYPASS;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SettingsDataSource(Context context2) {
        this.context = context2;
    }
}
