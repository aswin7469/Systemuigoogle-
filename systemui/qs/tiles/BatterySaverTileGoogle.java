package com.google.android.systemui.qs.tiles;

import android.content.Context;
import android.widget.Switch;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.BatterySaverTile;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatterySaverTileGoogle extends BatterySaverTile {
    public boolean mExtreme;

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
            i2 = 2131233408;
        } else {
            i2 = 2131233407;
        }
        booleanState.icon = QSTileImpl.ResourceIcon.get(i2);
        Context context = this.mContext;
        String string = context.getString(2131951950);
        booleanState.label = string;
        booleanState.secondaryLabel = "";
        booleanState.contentDescription = string;
        booleanState.value = this.mPowerSave;
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        if (booleanState.state == 2) {
            if (this.mExtreme) {
                i3 = 2131952514;
            } else {
                i3 = 2131953874;
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
