package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
