package com.google.android.systemui.columbus.legacy.gates;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class VrMode$onActivate$1 extends SuspendLambda implements Function2 {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ VrMode this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VrMode$onActivate$1(VrMode vrMode, Continuation continuation) {
        super(2, continuation);
        this.this$0 = vrMode;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new VrMode$onActivate$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((VrMode$onActivate$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.service.vr.IVrManager} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r7.label
            r2 = 0
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0025
            if (r1 != r4) goto L_0x001d
            java.lang.Object r0 = r7.L$2
            com.google.android.systemui.columbus.legacy.gates.VrMode r0 = (com.google.android.systemui.columbus.legacy.gates.VrMode) r0
            java.lang.Object r1 = r7.L$1
            android.service.vr.IVrManager r1 = (android.service.vr.IVrManager) r1
            java.lang.Object r5 = r7.L$0
            com.google.android.systemui.columbus.legacy.gates.VrMode r5 = (com.google.android.systemui.columbus.legacy.gates.VrMode) r5
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ RemoteException -> 0x001b }
            goto L_0x0052
        L_0x001b:
            r8 = move-exception
            goto L_0x0063
        L_0x001d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0025:
            kotlin.ResultKt.throwOnFailure(r8)
            com.google.android.systemui.columbus.legacy.gates.VrMode r8 = r7.this$0
            r8.inVrMode = r3
            dagger.Lazy r8 = r8.vrManager
            java.lang.Object r8 = r8.get()
            r1 = r8
            android.service.vr.IVrManager r1 = (android.service.vr.IVrManager) r1
            if (r1 == 0) goto L_0x0070
            com.google.android.systemui.columbus.legacy.gates.VrMode r8 = r7.this$0
            kotlinx.coroutines.CoroutineDispatcher r5 = r8.bgDispatcher     // Catch:{ RemoteException -> 0x001b }
            com.google.android.systemui.columbus.legacy.gates.VrMode$onActivate$1$1$1 r6 = new com.google.android.systemui.columbus.legacy.gates.VrMode$onActivate$1$1$1     // Catch:{ RemoteException -> 0x001b }
            r6.<init>(r1, r2)     // Catch:{ RemoteException -> 0x001b }
            r7.L$0 = r8     // Catch:{ RemoteException -> 0x001b }
            r7.L$1 = r1     // Catch:{ RemoteException -> 0x001b }
            r7.L$2 = r8     // Catch:{ RemoteException -> 0x001b }
            r7.label = r4     // Catch:{ RemoteException -> 0x001b }
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r5, r6, r7)     // Catch:{ RemoteException -> 0x001b }
            if (r5 != r0) goto L_0x004f
            return r0
        L_0x004f:
            r0 = r8
            r8 = r5
            r5 = r0
        L_0x0052:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ RemoteException -> 0x001b }
            boolean r8 = r8.booleanValue()     // Catch:{ RemoteException -> 0x001b }
            if (r8 != r4) goto L_0x005b
            r3 = r4
        L_0x005b:
            r0.inVrMode = r3     // Catch:{ RemoteException -> 0x001b }
            com.google.android.systemui.columbus.legacy.gates.VrMode$vrStateCallbacks$1 r8 = r5.vrStateCallbacks     // Catch:{ RemoteException -> 0x001b }
            r1.registerListener(r8)     // Catch:{ RemoteException -> 0x001b }
            goto L_0x0070
        L_0x0063:
            java.lang.String r0 = "Columbus/VrMode"
            java.lang.String r1 = "Could not register IVrManager listener"
            int r8 = android.util.Log.e(r0, r1, r8)
            java.lang.Integer r0 = new java.lang.Integer
            r0.<init>(r8)
        L_0x0070:
            com.google.android.systemui.columbus.legacy.gates.VrMode r7 = r7.this$0
            kotlinx.coroutines.internal.ContextScope r8 = r7.coroutineScope
            com.google.android.systemui.columbus.legacy.gates.VrMode$updateBlocking$1 r0 = new com.google.android.systemui.columbus.legacy.gates.VrMode$updateBlocking$1
            r0.<init>(r7, r2)
            r7 = 3
            kotlinx.coroutines.BuildersKt.launch$default(r8, r2, r2, r0, r7)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.gates.VrMode$onActivate$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
