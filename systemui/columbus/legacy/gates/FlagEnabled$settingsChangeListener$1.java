package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
