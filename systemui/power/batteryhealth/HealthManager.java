package com.google.android.systemui.power.batteryhealth;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Build;
import com.android.systemui.broadcast.BroadcastDispatcher;
import java.time.Duration;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HealthManager {
    public static final boolean healthDebugEnabled = Build.IS_DEBUGGABLE;
    public static final Duration updatePeriod = Duration.ofDays(1);
    public final AlarmManager alarmManager;
    public final CoroutineDispatcher bgDispatcher;
    public final HealthManager$healthDebugReceiver$1 bootCompletedReceiver = new HealthManager$healthDebugReceiver$1(this, 1);
    public final BroadcastDispatcher broadcastDispatcher;
    public final Context context;
    public IGoogleBattery googleBattery;
    public final HealthManager$healthDebugReceiver$1 healthDebugReceiver = new HealthManager$healthDebugReceiver$1(this, 0);
    public final Job initializer;
    public final CoroutineScope mainScope;
    public final boolean periodicUpdateEnabled;

    /* renamed from: com.google.android.systemui.power.batteryhealth.HealthManager$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Object obj3 = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                HealthManager healthManager = HealthManager.this;
                this.label = 1;
                boolean z = HealthManager.healthDebugEnabled;
                healthManager.getClass();
                Object withContext = BuildersKt.withContext(healthManager.bgDispatcher, new HealthManager$runOnBackground$2((Continuation) null, new HealthManager$initHalInterface$2(healthManager, (Continuation) null)), this);
                if (withContext != obj2) {
                    withContext = obj3;
                }
                if (withContext != obj2) {
                    withContext = obj3;
                }
                if (withContext == obj2) {
                    return obj2;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return obj3;
        }
    }

    public HealthManager(Context context2, AlarmManager alarmManager2, BroadcastDispatcher broadcastDispatcher2, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        this.context = context2;
        this.alarmManager = alarmManager2;
        this.broadcastDispatcher = broadcastDispatcher2;
        this.bgDispatcher = coroutineDispatcher;
        this.mainScope = coroutineScope;
        this.periodicUpdateEnabled = context2.getResources().getBoolean(2131034120);
        this.initializer = BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation) null), 3);
    }

    public static /* synthetic */ void getGoogleBattery$annotations() {
    }

    public static /* synthetic */ void getInitializer$annotations() {
    }
}
