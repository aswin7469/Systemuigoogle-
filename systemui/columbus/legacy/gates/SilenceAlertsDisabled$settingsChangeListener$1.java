package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SilenceAlertsDisabled$settingsChangeListener$1 implements ColumbusSettings.ColumbusSettingsChangeListener {
    public final /* synthetic */ SilenceAlertsDisabled this$0;

    public SilenceAlertsDisabled$settingsChangeListener$1(SilenceAlertsDisabled silenceAlertsDisabled) {
        this.this$0 = silenceAlertsDisabled;
    }

    public final void onAlertSilenceEnabledChange(boolean z) {
        SilenceAlertsDisabled silenceAlertsDisabled = this.this$0;
        BuildersKt.launch$default(silenceAlertsDisabled.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new SilenceAlertsDisabled$updateSilenceAlertsEnabled$1(silenceAlertsDisabled, z, (Continuation) null), 3);
    }
}
