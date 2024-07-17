package com.google.android.systemui.qs.tiles;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.SensorPrivacyManager;
import android.os.Handler;
import android.os.Looper;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.view.RotationPolicy;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.RotationLockTile;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DevicePostureControllerImpl;
import com.android.systemui.statusbar.policy.RotationLockControllerImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.wrapper.RotationPolicyWrapperImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class RotationLockTileGoogle extends RotationLockTile {
    public final DevicePostureController mDevicePostureController;
    public final boolean mIsPerDeviceStateRotationLockEnabled;

    public RotationLockTileGoogle(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, RotationLockControllerImpl rotationLockControllerImpl, SensorPrivacyManager sensorPrivacyManager, BatteryController batteryController, SecureSettings secureSettings, String[] strArr, DevicePostureController devicePostureController) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, rotationLockControllerImpl, sensorPrivacyManager, batteryController, secureSettings);
        boolean z;
        this.mDevicePostureController = devicePostureController;
        if (strArr.length == 0) {
            z = true;
        } else {
            z = false;
        }
        this.mIsPerDeviceStateRotationLockEnabled = !z;
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        PackageManager packageManager;
        String rotationResolverPackageName;
        RotationLockControllerImpl rotationLockControllerImpl = this.mController;
        boolean isRotationLocked = RotationPolicy.isRotationLocked(((RotationPolicyWrapperImpl) rotationLockControllerImpl.mRotationPolicy).context);
        boolean z = ((BatteryControllerImpl) this.mBatteryController).mPowerSave;
        int i = 2;
        boolean isSensorPrivacyEnabled = this.mPrivacyManager.isSensorPrivacyEnabled(2);
        Context context = this.mContext;
        boolean z2 = false;
        if (this.mAllowRotationResolver && !z && !isSensorPrivacyEnabled && (rotationResolverPackageName = packageManager.getRotationResolverPackageName()) != null && (packageManager = context.getPackageManager()).checkPermission("android.permission.CAMERA", rotationResolverPackageName) == 0 && ((RotationPolicyWrapperImpl) rotationLockControllerImpl.mRotationPolicy).secureSettings.getInt(0, "camera_autorotate") == 1) {
            z2 = true;
        }
        booleanState.value = !isRotationLocked;
        booleanState.label = context.getString(2131953616);
        booleanState.icon = QSTileImpl.ResourceIcon.get(2131233405);
        booleanState.contentDescription = context.getString(2131951822);
        String str = "";
        if (!isRotationLocked) {
            if (z2) {
                str = context.getResources().getString(2131953666);
            }
            booleanState.secondaryLabel = str;
            booleanState.icon = QSTileImpl.ResourceIcon.get(2131233406);
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
            sb.append(context.getResources().getStringArray(2130903181)[booleanState.state]);
            sb.append(" / ");
            if (((DevicePostureControllerImpl) this.mDevicePostureController).getDevicePosture() == 1) {
                sb.append(context.getString(2131953614));
            } else {
                sb.append(context.getString(2131953615));
            }
            String sb2 = sb.toString();
            booleanState.secondaryLabel = sb2;
            booleanState.stateDescription = sb2;
        }
    }
}
