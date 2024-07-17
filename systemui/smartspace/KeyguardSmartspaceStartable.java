package com.google.android.systemui.smartspace;

import com.android.systemui.CoreStartable;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.InitializationChecker;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardSmartspaceStartable implements CoreStartable {
    public final InitializationChecker initializationChecker;
    public final KeyguardMediaViewController mediaController;
    public final KeyguardZenAlarmViewController zenController;

    public KeyguardSmartspaceStartable(KeyguardZenAlarmViewController keyguardZenAlarmViewController, KeyguardMediaViewController keyguardMediaViewController, InitializationChecker initializationChecker2) {
        this.zenController = keyguardZenAlarmViewController;
        this.mediaController = keyguardMediaViewController;
        this.initializationChecker = initializationChecker2;
    }

    public final void start() {
        if (this.initializationChecker.initializeComponents()) {
            KeyguardZenAlarmViewController keyguardZenAlarmViewController = this.zenController;
            keyguardZenAlarmViewController.getClass();
            keyguardZenAlarmViewController.datePlugin.addOnAttachStateChangeListener(new KeyguardZenAlarmViewController$init$1(keyguardZenAlarmViewController));
            NextClockAlarmController nextClockAlarmController = keyguardZenAlarmViewController.nextClockAlarmController;
            boolean isUserUnlocked$1 = nextClockAlarmController.isUserUnlocked$1();
            UserTracker userTracker = nextClockAlarmController.userTracker;
            if (isUserUnlocked$1) {
                nextClockAlarmController.updateSession(((UserTrackerImpl) userTracker).getUserContext());
            }
            nextClockAlarmController.dumpManager.registerNormalDumpable("NextClockAlarmCtlr", nextClockAlarmController);
            ((UserTrackerImpl) userTracker).addCallback(nextClockAlarmController.userChangedCallback, nextClockAlarmController.mainExecutor);
            BuildersKt.launch$default(keyguardZenAlarmViewController.applicationScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardZenAlarmViewController$updateNextAlarm$1(keyguardZenAlarmViewController, (Continuation) null), 3);
            KeyguardMediaViewController keyguardMediaViewController = this.mediaController;
            keyguardMediaViewController.getClass();
            keyguardMediaViewController.plugin.addOnAttachStateChangeListener(new KeyguardMediaViewController$init$1(keyguardMediaViewController));
        }
    }
}
