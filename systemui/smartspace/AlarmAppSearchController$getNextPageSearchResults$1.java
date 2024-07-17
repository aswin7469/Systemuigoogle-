package com.google.android.systemui.smartspace;

import androidx.appsearch.app.SearchResults;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class AlarmAppSearchController$getNextPageSearchResults$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AlarmAppSearchController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AlarmAppSearchController$getNextPageSearchResults$1(AlarmAppSearchController alarmAppSearchController, Continuation continuation) {
        super(continuation);
        this.this$0 = alarmAppSearchController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getNextPageSearchResults((SearchResults) null, this);
    }
}
