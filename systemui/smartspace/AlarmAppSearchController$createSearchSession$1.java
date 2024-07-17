package com.google.android.systemui.smartspace;

import android.content.Context;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class AlarmAppSearchController$createSearchSession$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AlarmAppSearchController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AlarmAppSearchController$createSearchSession$1(AlarmAppSearchController alarmAppSearchController, Continuation continuation) {
        super(continuation);
        this.this$0 = alarmAppSearchController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.createSearchSession((Context) null, this);
    }
}
