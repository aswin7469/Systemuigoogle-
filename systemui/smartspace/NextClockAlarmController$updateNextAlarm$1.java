package com.google.android.systemui.smartspace;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
