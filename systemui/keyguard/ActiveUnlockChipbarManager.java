package com.google.android.systemui.keyguard;

import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.CoreStartable;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator;
import com.android.systemui.util.settings.GlobalSettings;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ActiveUnlockChipbarManager implements CoreStartable {
    public final ChipbarCoordinator chipbarCoordinator;
    public final GlobalSettings globalSettings;
    public final KeyguardStateController keyguardStateController;
    public final ActiveUnlockChipbarManager$keyguardStateControllerCallback$1 keyguardStateControllerCallback = new Object();
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final ActiveUnlockChipbarManager$keyguardUpdateMonitorCallback$1 keyguardUpdateMonitorCallback = new ActiveUnlockChipbarManager$keyguardUpdateMonitorCallback$1(this);
    public final SessionTracker sessionTracker;

    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.systemui.keyguard.ActiveUnlockChipbarManager$keyguardStateControllerCallback$1, java.lang.Object] */
    public ActiveUnlockChipbarManager(KeyguardUpdateMonitor keyguardUpdateMonitor2, ChipbarCoordinator chipbarCoordinator2, KeyguardStateController keyguardStateController2, GlobalSettings globalSettings2, SessionTracker sessionTracker2) {
        this.keyguardUpdateMonitor = keyguardUpdateMonitor2;
        this.chipbarCoordinator = chipbarCoordinator2;
        this.keyguardStateController = keyguardStateController2;
        this.globalSettings = globalSettings2;
        this.sessionTracker = sessionTracker2;
    }

    public final void start() {
        ((KeyguardStateControllerImpl) this.keyguardStateController).addCallback(this.keyguardStateControllerCallback);
        this.keyguardUpdateMonitor.registerCallback(this.keyguardUpdateMonitorCallback);
    }
}
