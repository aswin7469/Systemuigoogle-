package com.google.android.systemui.smartspace;

import android.app.ActivityManager;
import android.graphics.drawable.Drawable;
import android.text.format.DateFormat;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class KeyguardZenAlarmViewController$showAlarm$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyguardZenAlarmViewController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KeyguardZenAlarmViewController$showAlarm$1(KeyguardZenAlarmViewController keyguardZenAlarmViewController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardZenAlarmViewController;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardZenAlarmViewController$showAlarm$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((KeyguardZenAlarmViewController$showAlarm$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            KeyguardZenAlarmViewController keyguardZenAlarmViewController = this.this$0;
            long j = keyguardZenAlarmViewController.nextClockAlarmController.nextAlarm;
            if (j > 0) {
                keyguardZenAlarmViewController.getClass();
                if (j <= TimeUnit.HOURS.toMillis(12) + System.currentTimeMillis()) {
                    if (DateFormat.is24HourFormat(this.this$0.context, ActivityManager.getCurrentUser())) {
                        str = "HH:mm";
                    } else {
                        str = "h:mm";
                    }
                    String obj2 = DateFormat.format(str, j).toString();
                    KeyguardZenAlarmViewController keyguardZenAlarmViewController2 = this.this$0;
                    for (BcSmartspaceDataPlugin.SmartspaceView nextAlarm : keyguardZenAlarmViewController2.smartspaceViews) {
                        nextAlarm.setNextAlarm(keyguardZenAlarmViewController2.alarmImage, obj2);
                    }
                    return Unit.INSTANCE;
                }
            }
            for (BcSmartspaceDataPlugin.SmartspaceView nextAlarm2 : this.this$0.smartspaceViews) {
                nextAlarm2.setNextAlarm((Drawable) null, (String) null);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
