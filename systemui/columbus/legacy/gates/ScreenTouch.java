package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.shared.system.InputChannelCompat$InputEventReceiver;
import com.android.systemui.shared.system.InputMonitorCompat;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import javax.inject.Provider;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ScreenTouch extends TransientGate {
    public final ScreenTouch$gateListener$1 gateListener = new ScreenTouch$gateListener$1(this);
    public final ScreenTouch$inputEventListener$1 inputEventListener = new ScreenTouch$inputEventListener$1(this);
    public InputChannelCompat$InputEventReceiver inputEventReceiver;
    public InputMonitorCompat inputMonitor;
    public final Provider inputMonitorProvider;
    public final PowerState powerState;

    public ScreenTouch(PowerState powerState2, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.powerState = powerState2;
        this.inputMonitorProvider = switchingProvider;
    }

    public final void onActivate() {
        PowerState powerState2 = this.powerState;
        powerState2.registerListener(this.gateListener);
        if (!powerState2.isBlocking()) {
            BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ScreenTouch$startListeningForTouch$1(this, (Continuation) null), 3);
        }
        setBlocking(false);
    }

    public final void onDeactivate() {
        this.powerState.unregisterListener(this.gateListener);
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ScreenTouch$stopListeningForTouch$1(this, (Continuation) null), 3);
    }
}
