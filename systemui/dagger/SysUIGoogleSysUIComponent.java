package com.google.android.systemui.dagger;

import com.android.systemui.SystemUIAppComponentFactoryBase;
import com.android.systemui.dagger.SysUIComponent;
import com.android.systemui.keyguard.CustomizationProvider;
import com.android.systemui.keyguard.KeyguardSliceProvider;
import com.android.systemui.people.PeopleProvider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public interface SysUIGoogleSysUIComponent extends SysUIComponent {
    /* synthetic */ void inject(SystemUIAppComponentFactoryBase systemUIAppComponentFactoryBase);

    void inject(CustomizationProvider customizationProvider);

    /* synthetic */ void inject(KeyguardSliceProvider keyguardSliceProvider);

    /* synthetic */ void inject(PeopleProvider peopleProvider);
}
