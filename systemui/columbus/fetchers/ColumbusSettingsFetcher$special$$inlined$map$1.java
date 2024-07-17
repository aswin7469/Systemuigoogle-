package com.google.android.systemui.columbus.fetchers;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusSettingsFetcher$special$$inlined$map$1 implements Flow {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;

    public /* synthetic */ ColumbusSettingsFetcher$special$$inlined$map$1(StateFlow stateFlow, int i) {
        this.$r8$classId = i;
        this.$this_unsafeTransform$inlined = stateFlow;
    }

    public final Object collect(final FlowCollector flowCollector, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                Object collect = this.$this_unsafeTransform$inlined.collect(new FlowCollector() {
                    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
                    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                        /*
                            r4 = this;
                            boolean r0 = r6 instanceof com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L_0x0013
                            r0 = r6
                            com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2$1 r0 = (com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L_0x0013
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L_0x0018
                        L_0x0013:
                            com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2$1 r0 = new com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L_0x0018:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L_0x002f
                            if (r2 != r3) goto L_0x0027
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L_0x0043
                        L_0x0027:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L_0x002f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            java.lang.String r5 = (java.lang.String) r5
                            android.content.ComponentName r5 = android.content.ComponentName.unflattenFromString(r5)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = kotlinx.coroutines.flow.FlowCollector.this
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L_0x0043
                            return r1
                        L_0x0043:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }, continuation);
                if (collect == CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return collect;
                }
                return Unit.INSTANCE;
            default:
                Object collect2 = this.$this_unsafeTransform$inlined.collect(new ColumbusSettingsFetcher$special$$inlined$map$2$2(flowCollector), continuation);
                if (collect2 == CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return collect2;
                }
                return Unit.INSTANCE;
        }
    }
}
