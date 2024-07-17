package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.google.android.systemui.columbus.legacy.actions.Action;
import dagger.Lazy;
import java.util.Set;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SetupWizard extends Gate {
    public final SetupWizard$actionListener$1 actionListener = new SetupWizard$actionListener$1(this);
    public final CoroutineDispatcher bgDispatcher;
    public boolean exceptionActive;
    public final Set exceptions;
    public final Lazy provisionedController;
    public final SetupWizard$provisionedListener$1 provisionedListener = new SetupWizard$provisionedListener$1(this);
    public boolean setupComplete;

    public SetupWizard(Set set, Lazy lazy, CoroutineDispatcher coroutineDispatcher) {
        this.exceptions = set;
        this.provisionedController = lazy;
        this.bgDispatcher = coroutineDispatcher;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object access$isSetupComplete(com.google.android.systemui.columbus.legacy.gates.SetupWizard r8, kotlin.coroutines.Continuation r9) {
        /*
            r8.getClass()
            boolean r0 = r9 instanceof com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$1
            if (r0 == 0) goto L_0x0016
            r0 = r9
            com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$1 r0 = (com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0016
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x001b
        L_0x0016:
            com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$1 r0 = new com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$1
            r0.<init>(r8, r9)
        L_0x001b:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x003e
            if (r2 == r4) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0075
        L_0x002e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0036:
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.Deferred r8 = (kotlinx.coroutines.Deferred) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0062
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r9)
            com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$isDeviceProvisioned$1 r9 = new com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$isDeviceProvisioned$1
            r9.<init>(r8, r5)
            kotlinx.coroutines.internal.ContextScope r2 = r8.coroutineScope
            kotlinx.coroutines.CoroutineDispatcher r6 = r8.bgDispatcher
            kotlinx.coroutines.DeferredCoroutine r9 = kotlinx.coroutines.BuildersKt.async$default(r2, r6, r9, r3)
            com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$isCurrentUserSetup$1 r7 = new com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$isCurrentUserSetup$1
            r7.<init>(r8, r5)
            kotlinx.coroutines.DeferredCoroutine r8 = kotlinx.coroutines.BuildersKt.async$default(r2, r6, r7, r3)
            r0.L$0 = r8
            r0.label = r4
            java.lang.Object r9 = r9.awaitInternal(r0)
            if (r9 != r1) goto L_0x0062
            goto L_0x0079
        L_0x0062:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x0077
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r9 = r8.await(r0)
            if (r9 != r1) goto L_0x0075
            goto L_0x0079
        L_0x0075:
            r1 = r9
            goto L_0x0079
        L_0x0077:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
        L_0x0079:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.gates.SetupWizard.access$isSetupComplete(com.google.android.systemui.columbus.legacy.gates.SetupWizard, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onActivate() {
        ((DeviceProvisionedControllerImpl) ((DeviceProvisionedController) this.provisionedController.get())).addCallback(this.provisionedListener);
        SetupWizard$onActivate$1 setupWizard$onActivate$1 = new SetupWizard$onActivate$1(this, (Continuation) null);
        BuildersKt.launch$default(this.coroutineScope, this.mainDispatcher, (CoroutineStart) null, setupWizard$onActivate$1, 2);
    }

    public final void onDeactivate() {
        for (Action action : this.exceptions) {
            action.listeners.remove(this.actionListener);
        }
        ((DeviceProvisionedControllerImpl) ((DeviceProvisionedController) this.provisionedController.get())).removeCallback(this.provisionedListener);
    }

    public final String toString() {
        String gate = super.toString();
        Object runBlocking = BuildersKt.runBlocking(this.mainDispatcher, new SetupWizard$toString$1(this, (Continuation) null));
        return gate + runBlocking;
    }
}
