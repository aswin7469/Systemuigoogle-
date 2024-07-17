package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SilenceAlertsDisabled extends Gate {
    public final CoroutineDispatcher bgDispatcher;
    public final ColumbusSettings columbusSettings;
    public final SilenceAlertsDisabled$settingsChangeListener$1 settingsChangeListener = new SilenceAlertsDisabled$settingsChangeListener$1(this);

    public SilenceAlertsDisabled(ColumbusSettings columbusSettings2, CoroutineDispatcher coroutineDispatcher) {
        this.columbusSettings = columbusSettings2;
        this.bgDispatcher = coroutineDispatcher;
    }

    public final void onActivate() {
        this.columbusSettings.registerColumbusSettingsChangeListener(this.settingsChangeListener);
        SilenceAlertsDisabled$onActivate$1 silenceAlertsDisabled$onActivate$1 = new SilenceAlertsDisabled$onActivate$1(this, (Continuation) null);
        BuildersKt.launch$default(this.coroutineScope, this.mainDispatcher, (CoroutineStart) null, silenceAlertsDisabled$onActivate$1, 2);
    }

    public final void onDeactivate() {
        this.columbusSettings.listeners.remove(this.settingsChangeListener);
    }
}
