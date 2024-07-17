package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceTargetEvent;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CardPagerAdapter$onBindViewHolder$1 implements BcSmartspaceDataPlugin.SmartspaceEventNotifier {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CardPagerAdapter this$0;

    public /* synthetic */ CardPagerAdapter$onBindViewHolder$1(CardPagerAdapter cardPagerAdapter, int i) {
        this.$r8$classId = i;
        this.this$0 = cardPagerAdapter;
    }

    public final void notifySmartspaceEvent(SmartspaceTargetEvent smartspaceTargetEvent) {
        switch (this.$r8$classId) {
            case 0:
                BcSmartspaceDataPlugin bcSmartspaceDataPlugin = this.this$0.dataProvider;
                Intrinsics.checkNotNull(bcSmartspaceDataPlugin);
                bcSmartspaceDataPlugin.notifySmartspaceEvent(smartspaceTargetEvent);
                return;
            default:
                BcSmartspaceDataPlugin bcSmartspaceDataPlugin2 = this.this$0.dataProvider;
                Intrinsics.checkNotNull(bcSmartspaceDataPlugin2);
                bcSmartspaceDataPlugin2.notifySmartspaceEvent(smartspaceTargetEvent);
                return;
        }
    }
}
