package com.google.android.systemui.columbus;

import com.google.android.systemui.columbus.ColumbusManager;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class ColumbusManager$1$1$emit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ColumbusManager.AnonymousClass1.AnonymousClass1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ColumbusManager$1$1$emit$1(ColumbusManager.AnonymousClass1.AnonymousClass1 r1, Continuation continuation) {
        super(continuation);
        this.this$0 = r1;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        this.this$0.emit((Pair) null, (Continuation) this);
        return Unit.INSTANCE;
    }
}
