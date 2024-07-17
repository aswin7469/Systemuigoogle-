package com.google.android.systemui.smartspace;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class NextClockAlarmController$updateNextAlarm$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NextClockAlarmController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NextClockAlarmController$updateNextAlarm$1(NextClockAlarmController nextClockAlarmController, Continuation continuation) {
        super(continuation);
        this.this$0 = nextClockAlarmController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return NextClockAlarmController.access$updateNextAlarm(this.this$0, this);
    }
}
