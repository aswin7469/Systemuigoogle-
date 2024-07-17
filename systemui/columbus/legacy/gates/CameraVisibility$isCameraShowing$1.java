package com.google.android.systemui.columbus.legacy.gates;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class CameraVisibility$isCameraShowing$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CameraVisibility this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CameraVisibility$isCameraShowing$1(CameraVisibility cameraVisibility, Continuation continuation) {
        super(continuation);
        this.this$0 = cameraVisibility;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CameraVisibility.access$isCameraShowing(this.this$0, this);
    }
}
