package com.google.android.systemui.columbus.legacy.sensors;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class CHREGestureSensor$sendScreenState$1 extends Lambda implements Function0 {
    final /* synthetic */ CHREGestureSensor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CHREGestureSensor$sendScreenState$1(CHREGestureSensor cHREGestureSensor) {
        super(0);
        this.this$0 = cHREGestureSensor;
    }

    public final Object invoke() {
        this.this$0.screenStateUpdated = true;
        return Unit.INSTANCE;
    }
}
