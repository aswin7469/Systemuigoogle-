package com.google.android.systemui.dagger;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl {
    public final ExpandableNotificationRow expandableNotificationRow;
    public final Provider expandableNotificationRowControllerProvider;
    public final NotificationEntry notificationEntry;
    public final StatusBarNotificationPresenter onExpandClickListener;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    public DaggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, ExpandableNotificationRow expandableNotificationRow2, NotificationEntry notificationEntry2, StatusBarNotificationPresenter statusBarNotificationPresenter) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.expandableNotificationRow = expandableNotificationRow2;
        this.notificationEntry = notificationEntry2;
        this.onExpandClickListener = statusBarNotificationPresenter;
        this.expandableNotificationRowControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 1));
    }
}
