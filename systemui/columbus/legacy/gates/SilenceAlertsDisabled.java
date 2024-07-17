package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
