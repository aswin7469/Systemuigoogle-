package com.google.android.systemui.dreamliner;

import android.util.Log;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DockAlignmentController {
    public static final boolean DEBUG = Log.isLoggable("DockAlignmentController", 3);
    public int mAlignmentState = 0;
    public final CopyOnWriteArrayList mDockAlignmentStateChangeListeners = new CopyOnWriteArrayList();
    public final Optional mWirelessCharger;

    public DockAlignmentController(Optional optional) {
        this.mWirelessCharger = optional;
    }
}
