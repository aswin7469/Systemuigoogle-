package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.gates.Gate;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CameraVisibility$gateListener$1 implements Gate.Listener {
    public final /* synthetic */ CameraVisibility this$0;

    public CameraVisibility$gateListener$1(CameraVisibility cameraVisibility) {
        this.this$0 = cameraVisibility;
    }

    public final void onGateChanged(Gate gate) {
        CameraVisibility cameraVisibility = this.this$0;
        BuildersKt.launch$default(cameraVisibility.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraVisibility$updateCameraIsShowing$1(cameraVisibility, (Continuation) null), 3);
    }
}
