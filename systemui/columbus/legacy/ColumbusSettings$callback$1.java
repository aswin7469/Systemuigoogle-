package com.google.android.systemui.columbus.legacy;

import android.app.backup.BackupManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.settings.UserTrackerImpl;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class ColumbusSettings$callback$1 extends Lambda implements Function1 {
    final /* synthetic */ ColumbusSettings this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ColumbusSettings$callback$1(ColumbusSettings columbusSettings) {
        super(1);
        this.this$0 = columbusSettings;
    }

    public final Object invoke(Object obj) {
        Uri uri = (Uri) obj;
        if (Intrinsics.areEqual(uri, ColumbusSettings.COLUMBUS_ENABLED_URI)) {
            boolean isColumbusEnabled = this.this$0.isColumbusEnabled();
            for (ColumbusSettings.ColumbusSettingsChangeListener onColumbusEnabledChange : this.this$0.listeners) {
                onColumbusEnabledChange.onColumbusEnabledChange(isColumbusEnabled);
            }
            BackupManager.dataChangedForUser(((UserTrackerImpl) this.this$0.userTracker).getUserId(), this.this$0.backupPackage);
        } else {
            boolean z = false;
            if (Intrinsics.areEqual(uri, ColumbusSettings.COLUMBUS_AP_SENSOR_URI)) {
                ColumbusSettings columbusSettings = this.this$0;
                Settings.Secure.getIntForUser(columbusSettings.contentResolver, "columbus_ap_sensor", 0, ((UserTrackerImpl) columbusSettings.userTracker).getUserId());
                for (ColumbusSettings.ColumbusSettingsChangeListener columbusSettingsChangeListener : this.this$0.listeners) {
                    columbusSettingsChangeListener.getClass();
                }
            } else if (Intrinsics.areEqual(uri, ColumbusSettings.COLUMBUS_ACTION_URI)) {
                String selectedAction = this.this$0.selectedAction();
                for (ColumbusSettings.ColumbusSettingsChangeListener onSelectedActionChange : this.this$0.listeners) {
                    onSelectedActionChange.onSelectedActionChange(selectedAction);
                }
                BackupManager.dataChangedForUser(((UserTrackerImpl) this.this$0.userTracker).getUserId(), this.this$0.backupPackage);
            } else if (Intrinsics.areEqual(uri, ColumbusSettings.COLUMBUS_LAUNCH_APP_URI)) {
                String selectedApp = this.this$0.selectedApp();
                for (ColumbusSettings.ColumbusSettingsChangeListener onSelectedAppChange : this.this$0.listeners) {
                    onSelectedAppChange.onSelectedAppChange(selectedApp);
                }
                BackupManager.dataChangedForUser(((UserTrackerImpl) this.this$0.userTracker).getUserId(), this.this$0.backupPackage);
            } else if (Intrinsics.areEqual(uri, ColumbusSettings.COLUMBUS_LAUNCH_APP_SHORTCUT_URI)) {
                ColumbusSettings columbusSettings2 = this.this$0;
                String stringForUser = Settings.Secure.getStringForUser(columbusSettings2.contentResolver, "columbus_launch_app_shortcut", ((UserTrackerImpl) columbusSettings2.userTracker).getUserId());
                if (stringForUser == null) {
                    stringForUser = "";
                }
                for (ColumbusSettings.ColumbusSettingsChangeListener onSelectedAppShortcutChange : this.this$0.listeners) {
                    onSelectedAppShortcutChange.onSelectedAppShortcutChange(stringForUser);
                }
                BackupManager.dataChangedForUser(((UserTrackerImpl) this.this$0.userTracker).getUserId(), this.this$0.backupPackage);
            } else if (Intrinsics.areEqual(uri, ColumbusSettings.COLUMBUS_LOW_SENSITIVITY_URI)) {
                ColumbusSettings columbusSettings3 = this.this$0;
                if (Settings.Secure.getIntForUser(columbusSettings3.contentResolver, "columbus_low_sensitivity", 0, ((UserTrackerImpl) columbusSettings3.userTracker).getUserId()) != 0) {
                    z = true;
                }
                for (ColumbusSettings.ColumbusSettingsChangeListener onLowSensitivityChange : this.this$0.listeners) {
                    onLowSensitivityChange.onLowSensitivityChange(z);
                }
                BackupManager.dataChangedForUser(((UserTrackerImpl) this.this$0.userTracker).getUserId(), this.this$0.backupPackage);
            } else if (Intrinsics.areEqual(uri, ColumbusSettings.COLUMBUS_SILENCE_ALERTS_URI)) {
                ColumbusSettings columbusSettings4 = this.this$0;
                if (Settings.Secure.getIntForUser(columbusSettings4.contentResolver, "columbus_silence_alerts", 1, ((UserTrackerImpl) columbusSettings4.userTracker).getUserId()) != 0) {
                    z = true;
                }
                for (ColumbusSettings.ColumbusSettingsChangeListener onAlertSilenceEnabledChange : this.this$0.listeners) {
                    onAlertSilenceEnabledChange.onAlertSilenceEnabledChange(z);
                }
            } else {
                Log.w("Columbus/Settings", "Unknown setting change: " + uri);
            }
        }
        return Unit.INSTANCE;
    }
}
