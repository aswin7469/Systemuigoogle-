package com.google.android.systemui.smartspace;

import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class KeyguardZenAlarmViewController$updateNextAlarm$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyguardZenAlarmViewController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KeyguardZenAlarmViewController$updateNextAlarm$1(KeyguardZenAlarmViewController keyguardZenAlarmViewController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardZenAlarmViewController;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardZenAlarmViewController$updateNextAlarm$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((KeyguardZenAlarmViewController$updateNextAlarm$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            KeyguardZenAlarmViewController keyguardZenAlarmViewController = this.this$0;
            keyguardZenAlarmViewController.alarmManager.cancel(keyguardZenAlarmViewController.showNextAlarm);
            long j = this.this$0.nextClockAlarmController.nextAlarm;
            if (j > 0) {
                long millis = j - TimeUnit.HOURS.toMillis(12);
                if (millis > 0) {
                    KeyguardZenAlarmViewController keyguardZenAlarmViewController2 = this.this$0;
                    keyguardZenAlarmViewController2.alarmManager.setExact(1, millis, "lock_screen_next_alarm", keyguardZenAlarmViewController2.showNextAlarm, keyguardZenAlarmViewController2.handler);
                }
            }
            this.this$0.showAlarm();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
