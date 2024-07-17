package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceTargetEvent;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class DateSmartspaceView$$ExternalSyntheticLambda0 implements BcSmartspaceDataPlugin.SmartspaceEventNotifier {
    public final /* synthetic */ BcSmartspaceDataPlugin f$0;

    public /* synthetic */ DateSmartspaceView$$ExternalSyntheticLambda0(BcSmartspaceDataPlugin bcSmartspaceDataPlugin) {
        this.f$0 = bcSmartspaceDataPlugin;
    }

    public final void notifySmartspaceEvent(SmartspaceTargetEvent smartspaceTargetEvent) {
        this.f$0.notifySmartspaceEvent(smartspaceTargetEvent);
    }
}
