package com.google.android.systemui.power.batteryhealth;

import android.util.Log;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.properties.ObservableProperty;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class HealthService$special$$inlined$observable$1 extends ObservableProperty {
    public final /* synthetic */ HealthService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthService$special$$inlined$observable$1(HealthService healthService) {
        super(0);
        this.this$0 = healthService;
    }

    public final void afterChange(Object obj, Object obj2) {
        int intValue = ((Number) obj2).intValue();
        int intValue2 = ((Number) obj).intValue();
        Log.i("HealthService", "registered listeners num from " + intValue2 + " to " + intValue);
        HealthService healthService = this.this$0;
        if (intValue2 == 0 && intValue == 1) {
            healthService.subscribeJob = BuildersKt.launch$default(healthService.mainScope, (CoroutineContext) null, (CoroutineStart) null, new HealthService$registeredListenerNum$2$1(healthService, (Continuation) null), 3);
        }
        if (intValue == 0) {
            Job job = healthService.subscribeJob;
            if (job == null) {
                job = null;
            }
            job.cancel((CancellationException) null);
        }
    }
}
