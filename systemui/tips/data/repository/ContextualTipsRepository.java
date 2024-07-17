package com.google.android.systemui.tips.data.repository;

import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SettingsProxyExt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ContextualTipsRepository {
    public final StateFlowImpl _assistantStartCount;
    public final StateFlowImpl _powerMenuDismissCount;
    public final ReadonlyStateFlow assistantStartCount;
    public final GlobalSettings globalSettings;
    public final ReadonlyStateFlow powerMenuDismissCount;

    /* JADX WARNING: type inference failed for: r0v5, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function2] */
    public ContextualTipsRepository(CoroutineDispatcher coroutineDispatcher, GlobalSettings globalSettings2) {
        this.globalSettings = globalSettings2;
        new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(0));
        new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(0));
        FlowKt.flowOn(new ContextualTipsRepository$special$$inlined$map$1(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SuspendLambda(2, (Continuation) null), SettingsProxyExt.observerFlow(globalSettings2, "power_button_long_press")), this), coroutineDispatcher);
    }
}
