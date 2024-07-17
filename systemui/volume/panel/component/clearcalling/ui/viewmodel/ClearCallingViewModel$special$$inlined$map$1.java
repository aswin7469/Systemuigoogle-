package com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ClearCallingViewModel$special$$inlined$map$1 implements Flow {
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;
    public final /* synthetic */ ClearCallingViewModel this$0;

    public ClearCallingViewModel$special$$inlined$map$1(ReadonlyStateFlow readonlyStateFlow, ClearCallingViewModel clearCallingViewModel) {
        this.$this_unsafeTransform$inlined = readonlyStateFlow;
        this.this$0 = clearCallingViewModel;
    }

    public final Object collect(final FlowCollector flowCollector, Continuation continuation) {
        final ClearCallingViewModel clearCallingViewModel = this.this$0;
        Object collect = this.$this_unsafeTransform$inlined.collect(new FlowCollector() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                /*
                    r6 = this;
                    boolean r0 = r8 instanceof com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0013
                    r0 = r8
                    com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1$2$1 r0 = (com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L_0x0013
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L_0x0018
                L_0x0013:
                    com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1$2$1 r0 = new com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1$2$1
                    r0.<init>(r8)
                L_0x0018:
                    java.lang.Object r8 = r0.result
                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x002f
                    if (r2 != r3) goto L_0x0027
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L_0x0065
                L_0x0027:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r7)
                    throw r6
                L_0x002f:
                    kotlin.ResultKt.throwOnFailure(r8)
                    java.lang.Boolean r7 = (java.lang.Boolean) r7
                    com.android.systemui.volume.panel.component.button.ui.viewmodel.ToggleButtonViewModel r8 = new com.android.systemui.volume.panel.component.button.ui.viewmodel.ToggleButtonViewModel
                    java.lang.Boolean r2 = java.lang.Boolean.TRUE
                    boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r2)
                    com.android.systemui.common.shared.model.Icon$Resource r2 = new com.android.systemui.common.shared.model.Icon$Resource
                    com.android.systemui.common.shared.model.ContentDescription$Resource r4 = new com.android.systemui.common.shared.model.ContentDescription$Resource
                    r5 = 2131954173(0x7f1309fd, float:1.9544838E38)
                    r4.<init>(r5)
                    r5 = 2131232511(0x7f0806ff, float:1.8081133E38)
                    r2.<init>(r5, r4)
                    com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel r4 = r1
                    android.content.Context r4 = r4.context
                    r5 = 2131954174(0x7f1309fe, float:1.954484E38)
                    java.lang.String r4 = r4.getString(r5)
                    r8.<init>(r7, r2, r4)
                    r0.label = r3
                    kotlinx.coroutines.flow.FlowCollector r6 = kotlinx.coroutines.flow.FlowCollector.this
                    java.lang.Object r6 = r6.emit(r8, r0)
                    if (r6 != r1) goto L_0x0065
                    return r1
                L_0x0065:
                    kotlin.Unit r6 = kotlin.Unit.INSTANCE
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        if (collect == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return collect;
        }
        return Unit.INSTANCE;
    }
}
