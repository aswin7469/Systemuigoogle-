package com.google.android.systemui.smartspace;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class AlarmAppSearchController$query$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AlarmAppSearchController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AlarmAppSearchController$query$1(AlarmAppSearchController alarmAppSearchController, Continuation continuation) {
        super(continuation);
        this.this$0 = alarmAppSearchController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.query(this);
    }
}
