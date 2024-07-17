package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceTargetEvent;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CardPagerAdapter$onBindViewHolder$1 implements BcSmartspaceDataPlugin.SmartspaceEventNotifier {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CardPagerAdapter this$0;

    public /* synthetic */ CardPagerAdapter$onBindViewHolder$1(CardPagerAdapter cardPagerAdapter, int i) {
        this.$r8$classId = i;
        this.this$0 = cardPagerAdapter;
    }

    public final void notifySmartspaceEvent(SmartspaceTargetEvent smartspaceTargetEvent) {
        int i = this.$r8$classId;
        CardPagerAdapter cardPagerAdapter = this.this$0;
        switch (i) {
            case 0:
                BcSmartspaceDataPlugin bcSmartspaceDataPlugin = cardPagerAdapter.dataProvider;
                Intrinsics.checkNotNull(bcSmartspaceDataPlugin);
                bcSmartspaceDataPlugin.notifySmartspaceEvent(smartspaceTargetEvent);
                return;
            default:
                BcSmartspaceDataPlugin bcSmartspaceDataPlugin2 = cardPagerAdapter.dataProvider;
                Intrinsics.checkNotNull(bcSmartspaceDataPlugin2);
                bcSmartspaceDataPlugin2.notifySmartspaceEvent(smartspaceTargetEvent);
                return;
        }
    }
}
