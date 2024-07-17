package com.google.android.systemui.elmyra.gates;

import android.content.Context;
import android.os.PowerManager;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import com.google.android.systemui.elmyra.UserContentObserver;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class WakeMode extends PowerState {
    public final SecureSettings mSettings;
    public final UserContentObserver mSettingsObserver;
    public boolean mWakeSettingEnabled;

    public WakeMode(Context context, Executor executor, PowerManager powerManager, KeyguardUpdateMonitor keyguardUpdateMonitor, SecureSettings secureSettings) {
        super(executor, powerManager, keyguardUpdateMonitor);
        this.mSettings = secureSettings;
        ((SecureSettingsImpl) secureSettings).getClass();
        this.mSettingsObserver = new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_wake_enabled"), new WakeMode$$ExternalSyntheticLambda0(this), false);
    }

    public final boolean isBlocked() {
        if (this.mWakeSettingEnabled) {
            return false;
        }
        return super.isBlocked();
    }

    public final void onActivate() {
        boolean z = true;
        if (this.mSettings.getIntForUser("assist_gesture_wake_enabled", 1, -2) == 0) {
            z = false;
        }
        this.mWakeSettingEnabled = z;
        this.mSettingsObserver.activate();
    }

    public final void onDeactivate() {
        this.mSettingsObserver.deactivate();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mWakeSettingEnabled -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mWakeSettingEnabled, "]");
    }
}
