package com.google.android.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorPrivacyManager;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.view.RotationPolicy;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.RotationLockTile$$ExternalSyntheticLambda0;
import com.android.systemui.qs.tiles.RotationLockTile$1;
import com.android.systemui.qs.tiles.RotationLockTile$2;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DevicePostureControllerImpl;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.statusbar.policy.RotationLockControllerImpl;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class RotationLockTileGoogle extends QSTileImpl implements BatteryController.BatteryStateChangeCallback {
    public final boolean mAllowRotationResolver;
    public final BatteryController mBatteryController;
    public final RotationLockTile$2 mCallback;
    public final RotationLockController mController;
    public final DevicePostureController mDevicePostureController;
    public final boolean mIsPerDeviceStateRotationLockEnabled;
    public final SensorPrivacyManager mPrivacyManager;
    public final RotationLockTile$$ExternalSyntheticLambda0 mSensorPrivacyChangedListener = new RotationLockTile$$ExternalSyntheticLambda0(this);
    public final RotationLockTile$1 mSetting;

    public RotationLockTileGoogle(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, RotationLockController rotationLockController, SensorPrivacyManager sensorPrivacyManager, BatteryController batteryController, SecureSettings secureSettings, String[] strArr, DevicePostureController devicePostureController) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        boolean z;
        QSTileImpl.ResourceIcon.get(17302909);
        RotationLockTile$2 rotationLockTile$2 = new RotationLockTile$2(this);
        this.mController = rotationLockController;
        rotationLockController.getClass();
        rotationLockController.observe(this.mLifecycle, rotationLockTile$2);
        this.mPrivacyManager = sensorPrivacyManager;
        this.mBatteryController = batteryController;
        this.mSetting = new RotationLockTile$1(this, secureSettings, this.mHandler, qSHost.getUserContext().getUserId());
        batteryController.observe(this.mLifecycle, this);
        this.mAllowRotationResolver = this.mContext.getResources().getBoolean(17891359);
        this.mDevicePostureController = devicePostureController;
        if (strArr.length == 0) {
            z = true;
        } else {
            z = false;
        }
        this.mIsPerDeviceStateRotationLockEnabled = !z;
    }

    public final Intent getLongClickIntent() {
        return new Intent("android.settings.AUTO_ROTATE_SETTINGS");
    }

    public final int getMetricsCategory() {
        return 123;
    }

    public final CharSequence getTileLabel() {
        return ((QSTile.BooleanState) this.mState).label;
    }

    public final void handleClick(View view) {
        boolean z = ((QSTile.BooleanState) this.mState).value;
        ((RotationLockControllerImpl) this.mController).mRotationPolicy.setRotationLock("RotationLockTile#handleClick", z);
        refreshState(Boolean.valueOf(!z));
    }

    public final void handleDestroy() {
        super.handleDestroy();
        this.mSetting.setListening(false);
        this.mPrivacyManager.removeSensorPrivacyListener(2, this.mSensorPrivacyChangedListener);
    }

    public final void handleInitialize() {
        this.mPrivacyManager.addSensorPrivacyListener(2, this.mSensorPrivacyChangedListener);
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        this.mSetting.setListening(z);
    }

    public final void handleUserSwitch(int i) {
        this.mSetting.setUserId(i);
        handleRefreshState((Object) null);
    }

    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }

    public final void onPowerSaveChanged(boolean z) {
        refreshState((Object) null);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        PackageManager packageManager;
        String rotationResolverPackageName;
        RotationLockControllerImpl rotationLockControllerImpl = (RotationLockControllerImpl) this.mController;
        boolean isRotationLocked = RotationPolicy.isRotationLocked(rotationLockControllerImpl.mRotationPolicy.context);
        boolean z = ((BatteryControllerImpl) this.mBatteryController).mPowerSave;
        int i = 2;
        boolean isSensorPrivacyEnabled = this.mPrivacyManager.isSensorPrivacyEnabled(2);
        Context context = this.mContext;
        boolean z2 = false;
        if (this.mAllowRotationResolver && !z && !isSensorPrivacyEnabled && (rotationResolverPackageName = packageManager.getRotationResolverPackageName()) != null && (packageManager = context.getPackageManager()).checkPermission("android.permission.CAMERA", rotationResolverPackageName) == 0 && rotationLockControllerImpl.mRotationPolicy.secureSettings.getInt(0, "camera_autorotate") == 1) {
            z2 = true;
        }
        booleanState.value = !isRotationLocked;
        booleanState.label = context.getString(2131953657);
        booleanState.icon = QSTileImpl.ResourceIcon.get(2131233460);
        booleanState.contentDescription = context.getString(2131951826);
        String str = "";
        if (!isRotationLocked) {
            if (z2) {
                str = context.getResources().getString(2131953707);
            }
            booleanState.secondaryLabel = str;
            booleanState.icon = QSTileImpl.ResourceIcon.get(2131233461);
        } else {
            booleanState.secondaryLabel = str;
        }
        booleanState.stateDescription = booleanState.secondaryLabel;
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        if (!booleanState.value) {
            i = 1;
        }
        booleanState.state = i;
        if (this.mIsPerDeviceStateRotationLockEnabled) {
            StringBuilder sb = new StringBuilder();
            sb.append(context.getResources().getStringArray(2130903183)[booleanState.state]);
            sb.append(" / ");
            if (((DevicePostureControllerImpl) this.mDevicePostureController).getDevicePosture() == 1) {
                sb.append(context.getString(2131953655));
            } else {
                sb.append(context.getString(2131953656));
            }
            String sb2 = sb.toString();
            booleanState.secondaryLabel = sb2;
            booleanState.stateDescription = sb2;
        }
    }
}
