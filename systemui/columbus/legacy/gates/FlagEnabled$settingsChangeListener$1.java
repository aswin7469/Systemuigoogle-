package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class FlagEnabled$settingsChangeListener$1 implements ColumbusSettings.ColumbusSettingsChangeListener {
    public final /* synthetic */ FlagEnabled this$0;

    public FlagEnabled$settingsChangeListener$1(FlagEnabled flagEnabled) {
        this.this$0 = flagEnabled;
    }

    public final void onColumbusEnabledChange(boolean z) {
        FlagEnabled flagEnabled = this.this$0;
        BuildersKt.launch$default(flagEnabled.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new FlagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1(flagEnabled, z, (Continuation) null), 3);
    }
}
