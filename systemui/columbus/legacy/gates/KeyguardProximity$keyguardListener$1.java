package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.gates.Gate;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class KeyguardProximity$keyguardListener$1 implements Gate.Listener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardProximity this$0;

    public /* synthetic */ KeyguardProximity$keyguardListener$1(KeyguardProximity keyguardProximity, int i) {
        this.$r8$classId = i;
        this.this$0 = keyguardProximity;
    }

    public final void onGateChanged(Gate gate) {
        switch (this.$r8$classId) {
            case 0:
                KeyguardProximity keyguardProximity = this.this$0;
                keyguardProximity.getClass();
                BuildersKt.launch$default(keyguardProximity.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardProximity$updateProximityListener$1(keyguardProximity, (Continuation) null), 3);
                return;
            default:
                KeyguardProximity keyguardProximity2 = this.this$0;
                BuildersKt.launch$default(keyguardProximity2.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardProximity$updateBlocking$1(keyguardProximity2, (Continuation) null), 3);
                return;
        }
    }
}
