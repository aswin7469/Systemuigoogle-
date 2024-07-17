package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
