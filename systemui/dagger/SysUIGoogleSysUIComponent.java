package com.google.android.systemui.dagger;

import com.android.systemui.SystemUIAppComponentFactoryBase;
import com.android.systemui.dagger.SysUIComponent;
import com.android.systemui.keyguard.CustomizationProvider;
import com.android.systemui.keyguard.KeyguardSliceProvider;
import com.android.systemui.people.PeopleProvider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public interface SysUIGoogleSysUIComponent extends SysUIComponent {
    /* synthetic */ void inject(SystemUIAppComponentFactoryBase systemUIAppComponentFactoryBase);

    void inject(CustomizationProvider customizationProvider);

    /* synthetic */ void inject(KeyguardSliceProvider keyguardSliceProvider);

    /* synthetic */ void inject(PeopleProvider peopleProvider);
}
