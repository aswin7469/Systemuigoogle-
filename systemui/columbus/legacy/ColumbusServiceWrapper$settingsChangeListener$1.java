package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusServiceWrapper$settingsChangeListener$1 implements ColumbusSettings.ColumbusSettingsChangeListener {
    public final /* synthetic */ ColumbusServiceWrapper this$0;

    public ColumbusServiceWrapper$settingsChangeListener$1(ColumbusServiceWrapper columbusServiceWrapper) {
        this.this$0 = columbusServiceWrapper;
    }

    public final void onColumbusEnabledChange(boolean z) {
        if (z) {
            ColumbusServiceWrapper columbusServiceWrapper = this.this$0;
            columbusServiceWrapper.columbusSettings.listeners.remove(columbusServiceWrapper.settingsChangeListener);
            columbusServiceWrapper.started = true;
            columbusServiceWrapper.columbusService.get();
        }
    }
}
