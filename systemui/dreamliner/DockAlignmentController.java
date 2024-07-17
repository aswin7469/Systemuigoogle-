package com.google.android.systemui.dreamliner;

import android.util.Log;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DockAlignmentController {
    public static final boolean DEBUG = Log.isLoggable("DockAlignmentController", 3);
    public int mAlignmentState = 0;
    public final CopyOnWriteArrayList mDockAlignmentStateChangeListeners = new CopyOnWriteArrayList();
    public final Optional mWirelessCharger;

    public DockAlignmentController(Optional optional) {
        this.mWirelessCharger = optional;
    }
}
