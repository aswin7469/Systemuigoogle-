package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.gates.Gate;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardProximity$keyguardListener$1 implements Gate.Listener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardProximity this$0;

    public /* synthetic */ KeyguardProximity$keyguardListener$1(KeyguardProximity keyguardProximity, int i) {
        this.$r8$classId = i;
        this.this$0 = keyguardProximity;
    }

    public final void onGateChanged(Gate gate) {
        int i = this.$r8$classId;
        KeyguardProximity keyguardProximity = this.this$0;
        switch (i) {
            case 0:
                keyguardProximity.getClass();
                BuildersKt.launch$default(keyguardProximity.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardProximity$updateProximityListener$1(keyguardProximity, (Continuation) null), 3);
                return;
            default:
                BuildersKt.launch$default(keyguardProximity.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardProximity$updateBlocking$1(keyguardProximity, (Continuation) null), 3);
                return;
        }
    }
}
