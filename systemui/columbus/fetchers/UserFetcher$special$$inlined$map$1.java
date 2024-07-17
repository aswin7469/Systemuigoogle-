package com.google.android.systemui.columbus.fetchers;

import com.android.systemui.settings.UserTracker;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UserFetcher$special$$inlined$map$1 implements Flow {
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;
    public final /* synthetic */ UserTracker $userTracker$inlined;

    public UserFetcher$special$$inlined$map$1(FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, UserTracker userTracker) {
        this.$this_unsafeTransform$inlined = flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
        this.$userTracker$inlined = userTracker;
    }

    public final Object collect(final FlowCollector flowCollector, Continuation continuation) {
        final UserTracker userTracker = this.$userTracker$inlined;
        Object collect = this.$this_unsafeTransform$inlined.collect(new FlowCollector() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                /*
                    r4 = this;
                    boolean r0 = r6 instanceof com.google.android.systemui.columbus.fetchers.UserFetcher$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0013
                    r0 = r6
                    com.google.android.systemui.columbus.fetchers.UserFetcher$special$$inlined$map$1$2$1 r0 = (com.google.android.systemui.columbus.fetchers.UserFetcher$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L_0x0013
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L_0x0018
                L_0x0013:
                    com.google.android.systemui.columbus.fetchers.UserFetcher$special$$inlined$map$1$2$1 r0 = new com.google.android.systemui.columbus.fetchers.UserFetcher$special$$inlined$map$1$2$1
                    r0.<init>(r6)
                L_0x0018:
                    java.lang.Object r6 = r0.result
                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x002f
                    if (r2 != r3) goto L_0x0027
                    kotlin.ResultKt.throwOnFailure(r6)
                    goto L_0x0047
                L_0x0027:
                    java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                    java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                    r4.<init>(r5)
                    throw r4
                L_0x002f:
                    kotlin.ResultKt.throwOnFailure(r6)
                    kotlin.Unit r5 = (kotlin.Unit) r5
                    com.android.systemui.settings.UserTracker r5 = r1
                    com.android.systemui.settings.UserTrackerImpl r5 = (com.android.systemui.settings.UserTrackerImpl) r5
                    android.os.UserHandle r5 = r5.getUserHandle()
                    r0.label = r3
                    kotlinx.coroutines.flow.FlowCollector r4 = kotlinx.coroutines.flow.FlowCollector.this
                    java.lang.Object r4 = r4.emit(r5, r0)
                    if (r4 != r1) goto L_0x0047
                    return r1
                L_0x0047:
                    kotlin.Unit r4 = kotlin.Unit.INSTANCE
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.fetchers.UserFetcher$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        if (collect == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return collect;
        }
        return Unit.INSTANCE;
    }
}
