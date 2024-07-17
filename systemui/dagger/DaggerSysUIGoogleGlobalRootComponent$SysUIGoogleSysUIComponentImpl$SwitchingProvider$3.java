package com.google.android.systemui.dagger;

import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotifLayoutInflaterFactory;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$3 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$3(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final NotifLayoutInflaterFactory provide(ExpandableNotificationRow expandableNotificationRow, int i) {
        Set set;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) this.this$0.wMComponentImpl;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        Object obj = new Object();
        HashSet hashSet = new HashSet();
        if (((FeatureFlagsClassicRelease) ((FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get())).isEnabled(Flags.PRECOMPUTED_TEXT)) {
            hashSet.add(obj);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "Set contributions cannot be null");
        }
        arrayList.addAll(hashSet);
        if (arrayList.isEmpty()) {
            set = Collections.emptySet();
        } else if (arrayList.size() == 1) {
            set = Collections.singleton(arrayList.get(0));
        } else {
            set = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(arrayList);
        }
        return new NotifLayoutInflaterFactory(expandableNotificationRow, i, set);
    }
}
