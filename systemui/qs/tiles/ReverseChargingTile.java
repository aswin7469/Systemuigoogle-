package com.google.android.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IThermalEventListener;
import android.os.IThermalService;
import android.os.Looper;
import android.os.RemoteException;
import android.os.Temperature;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import androidx.compose.foundation.text.ValidatingOffsetMapping$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.Prefs;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ReverseChargingTile extends QSTileImpl implements BatteryController.BatteryStateChangeCallback {
    public static final boolean DEBUG = Log.isLoggable("ReverseChargingTile", 3);
    public final BatteryController mBatteryController;
    public int mBatteryLevel;
    public boolean mListening;
    public boolean mOverHeat;
    public boolean mPowerSave;
    public boolean mReverse;
    public final AnonymousClass2 mSettingsObserver = new ContentObserver(this.mHandler) {
        public final void onChange(boolean z) {
            ReverseChargingTile.this.updateThresholdLevel();
        }
    };
    public final AnonymousClass1 mThermalEventListener = new IThermalEventListener.Stub() {
        public final void notifyThrottling(Temperature temperature) {
            boolean z;
            int status = temperature.getStatus();
            ReverseChargingTile reverseChargingTile = ReverseChargingTile.this;
            if (status >= 5) {
                z = true;
            } else {
                z = false;
            }
            reverseChargingTile.mOverHeat = z;
            if (ReverseChargingTile.DEBUG) {
                ExifInterface$$ExternalSyntheticOutline0.m("notifyThrottling(): status=", status, "ReverseChargingTile");
            }
        }
    };
    public final IThermalService mThermalService;
    public int mThresholdLevel;

    public ReverseChargingTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, BatteryController batteryController, IThermalService iThermalService) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mBatteryController = batteryController;
        batteryController.observe(this.mLifecycle, this);
        this.mThermalService = iThermalService;
    }

    public final Intent getLongClickIntent() {
        Intent intent = new Intent("android.settings.REVERSE_CHARGING_SETTINGS");
        intent.setPackage("com.android.settings");
        return intent;
    }

    public final int getMetricsCategory() {
        return 0;
    }

    public final CharSequence getTileLabel() {
        return this.mContext.getString(2131953662);
    }

    public final void handleClick(View view) {
        if (((QSTile.BooleanState) this.mState).state != 0) {
            this.mReverse = !this.mReverse;
            if (DEBUG) {
                Log.d("ReverseChargingTile", "handleClick(): rtx=" + (this.mReverse ? 1 : 0) + ",this=" + this);
            }
            this.mBatteryController.setReverseState(this.mReverse);
            QSHost qSHost = this.mHost;
            if (!Prefs.getBoolean(qSHost.getUserContext(), "HasSeenReverseBottomSheet")) {
                Intent intent = new Intent("android.settings.REVERSE_CHARGING_BOTTOM_SHEET");
                intent.setPackage("com.android.settings");
                this.mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
                Prefs.putBoolean(qSHost.getUserContext(), "HasSeenReverseBottomSheet", true);
            }
        }
    }

    public final void handleDestroy() {
        super.handleDestroy();
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (this.mListening != z) {
            this.mListening = z;
            AnonymousClass1 r0 = this.mThermalEventListener;
            AnonymousClass2 r1 = this.mSettingsObserver;
            Context context = this.mContext;
            IThermalService iThermalService = this.mThermalService;
            if (z) {
                updateThresholdLevel();
                boolean z2 = false;
                context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("advanced_battery_usage_amount"), false, r1);
                try {
                    iThermalService.registerThermalEventListenerWithType(r0, 3);
                } catch (RemoteException e) {
                    Log.e("ReverseChargingTile", "Could not register thermal event listener, exception: " + e);
                }
                try {
                    Temperature[] currentTemperaturesWithType = iThermalService.getCurrentTemperaturesWithType(3);
                    int length = currentTemperaturesWithType.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        Temperature temperature = currentTemperaturesWithType[i];
                        if (temperature.getStatus() >= 5) {
                            Log.w("ReverseChargingTile", "isOverHeat(): current skin status = " + temperature.getStatus() + ", temperature = " + temperature.getValue());
                            z2 = true;
                            break;
                        }
                        i++;
                    }
                } catch (RemoteException e2) {
                    Log.w("ReverseChargingTile", "isOverHeat(): " + e2);
                }
                this.mOverHeat = z2;
            } else {
                context.getContentResolver().unregisterContentObserver(r1);
                try {
                    iThermalService.unregisterThermalEventListener(r0);
                } catch (RemoteException e3) {
                    Log.e("ReverseChargingTile", "Could not unregister thermal event listener, exception: " + e3);
                }
            }
            if (DEBUG) {
                Log.d("ReverseChargingTile", "handleSetListening(): rtx=" + (this.mReverse ? 1 : 0) + ",level=" + this.mBatteryLevel + ",threshold=" + this.mThresholdLevel + ",listening=" + z);
            }
        }
    }

    public final void handleUpdateState(QSTile.State state, Object obj) {
        int i;
        boolean z;
        int i2;
        String str;
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        boolean z2 = ((BatteryControllerImpl) this.mBatteryController).mWirelessCharging;
        int i3 = 0;
        if (this.mBatteryLevel <= this.mThresholdLevel) {
            i = 1;
        } else {
            i = 0;
        }
        boolean z3 = this.mOverHeat;
        if (z3 || this.mPowerSave || z2 || i != 0 || !this.mReverse) {
            z = false;
        } else {
            z = true;
        }
        booleanState.value = z;
        if (!z3 && !this.mPowerSave && !z2 && i == 0) {
            i3 = this.mReverse ? 2 : 1;
        }
        booleanState.state = i3;
        if (z) {
            i2 = 2131233410;
        } else {
            i2 = 2131233409;
        }
        booleanState.icon = QSTileImpl.ResourceIcon.get(i2);
        CharSequence tileLabel = getTileLabel();
        booleanState.label = tileLabel;
        booleanState.contentDescription = tileLabel;
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        boolean z4 = this.mOverHeat;
        Context context = this.mContext;
        if (z4) {
            str = context.getString(2131953957);
        } else if (this.mPowerSave) {
            str = context.getString(2131953564);
        } else if (z2) {
            str = context.getString(2131954311);
        } else if (i != 0) {
            str = context.getString(2131952989);
        } else {
            str = null;
        }
        booleanState.secondaryLabel = str;
        if (DEBUG) {
            Log.d("ReverseChargingTile", "handleUpdateState(): ps=" + (this.mPowerSave ? 1 : 0) + ",wlc=" + (z2 ? 1 : 0) + ",low=" + i + ",over=" + (this.mOverHeat ? 1 : 0) + ",rtx=" + (this.mReverse ? 1 : 0) + ",this=" + this);
        }
    }

    public final boolean isAvailable() {
        return this.mBatteryController.isReverseSupported();
    }

    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }

    public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
        this.mBatteryLevel = i;
        this.mReverse = this.mBatteryController.isReverseOn();
        if (DEBUG) {
            StringBuilder sb = new StringBuilder("onBatteryLevelChanged(): rtx=");
            sb.append(this.mReverse ? 1 : 0);
            sb.append(",level=");
            sb.append(this.mBatteryLevel);
            sb.append(",threshold=");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, this.mThresholdLevel, "ReverseChargingTile");
        }
        refreshState((Object) null);
    }

    public final void onPowerSaveChanged(boolean z) {
        this.mPowerSave = z;
        refreshState((Object) null);
    }

    public final void onReverseChanged(int i, String str, boolean z) {
        if (DEBUG) {
            StringBuilder m = ValidatingOffsetMapping$$ExternalSyntheticOutline0.m("onReverseChanged(): rtx=", z ? 1 : 0, ",level=", i, ",name=");
            m.append(str);
            m.append(",this=");
            m.append(this);
            Log.d("ReverseChargingTile", m.toString());
        }
        this.mReverse = z;
        refreshState((Object) null);
    }

    public final void updateThresholdLevel() {
        this.mThresholdLevel = Settings.Global.getInt(this.mContext.getContentResolver(), "advanced_battery_usage_amount", 2) * 5;
        if (DEBUG) {
            StringBuilder sb = new StringBuilder("updateThresholdLevel(): rtx=");
            sb.append(this.mReverse ? 1 : 0);
            sb.append(",level=");
            sb.append(this.mBatteryLevel);
            sb.append(",threshold=");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, this.mThresholdLevel, "ReverseChargingTile");
        }
    }
}
