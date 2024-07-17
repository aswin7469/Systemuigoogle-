package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceTargetEvent;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class WeatherSmartspaceView$$ExternalSyntheticLambda0 implements BcSmartspaceDataPlugin.SmartspaceEventNotifier {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BcSmartspaceDataPlugin f$0;

    public /* synthetic */ WeatherSmartspaceView$$ExternalSyntheticLambda0(BcSmartspaceDataPlugin bcSmartspaceDataPlugin, int i) {
        this.$r8$classId = i;
        this.f$0 = bcSmartspaceDataPlugin;
    }

    public final void notifySmartspaceEvent(SmartspaceTargetEvent smartspaceTargetEvent) {
        int i = this.$r8$classId;
        this.f$0.notifySmartspaceEvent(smartspaceTargetEvent);
    }
}
