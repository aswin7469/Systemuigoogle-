package com.google.android.systemui.dagger;

import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotifLayoutInflaterFactory;
import com.android.systemui.statusbar.notification.row.NotifRemoteViewsFactoryContainerImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$2 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$2(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final NotifLayoutInflaterFactory provide(ExpandableNotificationRow expandableNotificationRow, int i) {
        return new NotifLayoutInflaterFactory(expandableNotificationRow, i, (NotifRemoteViewsFactoryContainerImpl) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) this.this$0.wMComponentImpl).provideNotifRemoteViewsFactoryContainerProvider.get());
    }
}
