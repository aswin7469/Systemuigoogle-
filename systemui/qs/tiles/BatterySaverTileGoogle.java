package com.google.android.systemui.qs.tiles;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.BatterySaverTile;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BatterySaverTileGoogle extends BatterySaverTile {
    public boolean mExtreme;

    public BatterySaverTileGoogle(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, BatteryController batteryController, SecureSettings secureSettings) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, batteryController, secureSettings);
    }

    public final void handleUpdateState(QSTile.State state, Object obj) {
        int i;
        int i2;
        int i3;
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        if (this.mPluggedIn) {
            i = 0;
        } else if (this.mPowerSave) {
            i = 2;
        } else {
            i = 1;
        }
        booleanState.state = i;
        if (this.mPowerSave) {
            i2 = 2131233463;
        } else {
            i2 = 2131233462;
        }
        booleanState.icon = QSTileImpl.ResourceIcon.get(i2);
        Context context = this.mContext;
        String string = context.getString(2131951959);
        booleanState.label = string;
        booleanState.secondaryLabel = "";
        booleanState.contentDescription = string;
        booleanState.value = this.mPowerSave;
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        if (booleanState.state == 2) {
            if (this.mExtreme) {
                i3 = 2131952539;
            } else {
                i3 = 2131953921;
            }
            booleanState.secondaryLabel = context.getString(i3);
        } else {
            booleanState.secondaryLabel = "";
        }
        booleanState.stateDescription = booleanState.secondaryLabel;
    }

    public final void onExtremeBatterySaverChanged(boolean z) {
        if (this.mExtreme != z) {
            this.mExtreme = z;
            refreshState((Object) null);
        }
    }
}
