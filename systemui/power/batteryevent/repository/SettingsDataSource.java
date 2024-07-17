package com.google.android.systemui.power.batteryevent.repository;

import android.content.Context;
import com.google.android.systemui.power.batteryevent.common.SettingsDataType;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SettingsDataSource {
    public final Context context;
    public int lastDockDefenderByPass;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
