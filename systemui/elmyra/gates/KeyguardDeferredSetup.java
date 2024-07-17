package com.google.android.systemui.elmyra.gates;

import android.content.Context;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import com.google.android.systemui.elmyra.UserContentObserver;
import com.google.android.systemui.elmyra.actions.Action;
import com.google.android.systemui.elmyra.gates.Gate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardDeferredSetup extends Gate {
    public boolean mDeferredSetupComplete;
    public final List mExceptions;
    public final KeyguardVisibility mKeyguardGate;
    public final AnonymousClass1 mKeyguardGateListener;
    public final SecureSettings mSecureSettings;
    public final UserContentObserver mSettingsObserver;

    public KeyguardDeferredSetup(Context context, Executor executor, KeyguardVisibility keyguardVisibility, SecureSettings secureSettings, Set set) {
        super(executor);
        AnonymousClass1 r3 = new Gate.Listener() {
            public final void onGateChanged(Gate gate) {
                KeyguardDeferredSetup.this.notifyListener();
            }
        };
        this.mSecureSettings = secureSettings;
        this.mExceptions = new ArrayList(set);
        this.mKeyguardGate = keyguardVisibility;
        keyguardVisibility.mListener = r3;
        ((SecureSettingsImpl) secureSettings).getClass();
        this.mSettingsObserver = new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_setup_complete"), new KeyguardDeferredSetup$$ExternalSyntheticLambda0(this), false);
    }

    public final boolean isBlocked() {
        int i = 0;
        while (true) {
            List list = this.mExceptions;
            if (i < ((ArrayList) list).size()) {
                if (((Action) ((ArrayList) list).get(i)).isAvailable()) {
                    return false;
                }
                i++;
            } else if (this.mDeferredSetupComplete || !this.mKeyguardGate.isBlocking()) {
                return false;
            } else {
                return true;
            }
        }
    }

    public final void onActivate() {
        this.mKeyguardGate.activate();
        boolean z = false;
        if (this.mSecureSettings.getIntForUser("assist_gesture_setup_complete", 0, -2) != 0) {
            z = true;
        }
        this.mDeferredSetupComplete = z;
        this.mSettingsObserver.activate();
    }

    public final void onDeactivate() {
        this.mKeyguardGate.deactivate();
        this.mSettingsObserver.deactivate();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mDeferredSetupComplete -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mDeferredSetupComplete, "]");
    }
}
