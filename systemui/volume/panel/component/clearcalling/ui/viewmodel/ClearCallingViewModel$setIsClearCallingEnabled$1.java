package com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class ClearCallingViewModel$setIsClearCallingEnabled$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $state;
    int label;
    final /* synthetic */ ClearCallingViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClearCallingViewModel$setIsClearCallingEnabled$1(ClearCallingViewModel clearCallingViewModel, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = clearCallingViewModel;
        this.$state = z;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new ClearCallingViewModel$setIsClearCallingEnabled$1(this.this$0, this.$state, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((ClearCallingViewModel$setIsClearCallingEnabled$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        kotlin.jdk7.AutoCloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0061, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r0 = r5.label
            if (r0 != 0) goto L_0x006f
            kotlin.ResultKt.throwOnFailure(r6)
            com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel r6 = r5.this$0
            com.google.android.systemui.volume.panel.component.clearcalling.domain.interactor.ClearCallingInteractor r6 = r6.clearCallingInteractor
            boolean r5 = r5.$state
            com.google.android.systemui.volume.panel.component.clearcalling.data.repository.ClearCallingRepository r6 = r6.clearCallingRepository
            com.google.android.settingslib.dcservice.DcServiceClient r0 = r6.dcServiceClient
            com.google.android.settingslib.dcservice.DcServiceClientImpl r0 = (com.google.android.settingslib.dcservice.DcServiceClientImpl) r0
            r0.getClass()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Set Clear Calling state to "
            r1.<init>(r2)
            r1.append(r5)
            java.lang.String r2 = " through DC-Service"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "DcServiceClientImpl"
            android.util.Log.i(r2, r1)
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r3 = "clear_calling_enable_state"
            r1.putBoolean(r3, r5)
            r5 = 0
            android.content.ContentResolver r0 = r0.contentResolver     // Catch:{ Exception -> 0x0062 }
            android.net.Uri r4 = com.google.android.settingslib.dcservice.DcServiceClientImpl.PROXY_AUTHORITY     // Catch:{ Exception -> 0x0062 }
            android.content.ContentProviderClient r0 = r0.acquireUnstableContentProviderClient(r4)     // Catch:{ Exception -> 0x0062 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ Exception -> 0x0062 }
            java.lang.String r4 = "method_set_clear_calling_state"
            android.os.Bundle r1 = r0.call(r4, r5, r1)     // Catch:{ all -> 0x005b }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x005b }
            kotlin.jdk7.AutoCloseableKt.closeFinally(r0, r5)     // Catch:{ Exception -> 0x0062 }
            boolean r0 = r1.getBoolean(r3)     // Catch:{ Exception -> 0x0062 }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r0)     // Catch:{ Exception -> 0x0062 }
            goto L_0x0067
        L_0x005b:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x005d }
        L_0x005d:
            r3 = move-exception
            kotlin.jdk7.AutoCloseableKt.closeFinally(r0, r1)     // Catch:{ Exception -> 0x0062 }
            throw r3     // Catch:{ Exception -> 0x0062 }
        L_0x0062:
            java.lang.String r0 = "setClearCallingEnableState: error happens when calling DcService."
            android.util.Log.w(r2, r0)
        L_0x0067:
            kotlinx.coroutines.flow.StateFlowImpl r6 = r6.mutableClearCallingStateUpdate
            r6.setValue(r5)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x006f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$setIsClearCallingEnabled$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
