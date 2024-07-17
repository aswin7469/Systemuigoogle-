package com.google.android.systemui.columbus;

import com.google.android.systemui.columbus.ColumbusManager;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class ColumbusManager$2$1$emit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ColumbusManager.AnonymousClass2.AnonymousClass1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ColumbusManager$2$1$emit$1(ColumbusManager.AnonymousClass2.AnonymousClass1 r1, Continuation continuation) {
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
