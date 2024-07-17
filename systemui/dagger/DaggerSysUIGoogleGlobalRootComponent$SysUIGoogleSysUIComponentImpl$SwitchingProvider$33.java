package com.google.android.systemui.dagger;

import android.content.ComponentName;
import com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepository;
import com.android.systemui.qs.pipeline.domain.autoaddable.A11yShortcutAutoAddable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final A11yShortcutAutoAddable create(TileSpec tileSpec, ComponentName componentName) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        return new A11yShortcutAutoAddable((AccessibilityQsShortcutsRepository) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).accessibilityQsShortcutsRepositoryImplProvider.get(), (CoroutineDispatcher) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).bgDispatcherProvider.get(), tileSpec, componentName);
    }
}
