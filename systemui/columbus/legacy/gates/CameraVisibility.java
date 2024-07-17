package com.google.android.systemui.columbus.legacy.gates;

import android.app.IActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.columbus.legacy.actions.Action;
import dagger.Lazy;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CameraVisibility extends Gate {
    public final CameraVisibility$actionListener$1 actionListener = new CameraVisibility$actionListener$1(this);
    public final Lazy activityManager;
    public final CoroutineDispatcher bgDispatcher;
    public boolean cameraShowing;
    public boolean exceptionActive;
    public final List exceptions;
    public final CameraVisibility$gateListener$1 gateListener = new CameraVisibility$gateListener$1(this);
    public final KeyguardVisibility keyguardGate;
    public final PackageManager packageManager;
    public final PowerState powerState;
    public final CameraVisibility$taskStackListener$1 taskStackListener = new CameraVisibility$taskStackListener$1(this);

    public CameraVisibility(Context context, List list, KeyguardVisibility keyguardVisibility, PowerState powerState2, Lazy lazy, CoroutineDispatcher coroutineDispatcher) {
        this.exceptions = list;
        this.keyguardGate = keyguardVisibility;
        this.powerState = powerState2;
        this.activityManager = lazy;
        this.bgDispatcher = coroutineDispatcher;
        this.packageManager = context.getPackageManager();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object access$isCameraShowing(com.google.android.systemui.columbus.legacy.gates.CameraVisibility r5, kotlin.coroutines.Continuation r6) {
        /*
            r5.getClass()
            boolean r0 = r6 instanceof com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraShowing$1
            if (r0 == 0) goto L_0x0016
            r0 = r6
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraShowing$1 r0 = (com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraShowing$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0016
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x001b
        L_0x0016:
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraShowing$1 r0 = new com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraShowing$1
            r0.<init>(r5, r6)
        L_0x001b:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0067
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            java.lang.Object r5 = r0.L$0
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility r5 = (com.google.android.systemui.columbus.legacy.gates.CameraVisibility) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0053
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r6)
            com.google.android.systemui.columbus.legacy.gates.PowerState r6 = r5.powerState
            boolean r6 = r6.isBlocking()
            if (r6 != 0) goto L_0x0069
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.isCameraTopActivity(r0)
            if (r6 != r1) goto L_0x0053
            goto L_0x006b
        L_0x0053:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x0069
            r6 = 0
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r6 = r5.isCameraInForeground(r0)
            if (r6 != r1) goto L_0x0067
            goto L_0x006b
        L_0x0067:
            r1 = r6
            goto L_0x006b
        L_0x0069:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
        L_0x006b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.gates.CameraVisibility.access$isCameraShowing(com.google.android.systemui.columbus.legacy.gates.CameraVisibility, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object isCameraInForeground(kotlin.coroutines.Continuation r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$1 r0 = (com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$1 r0 = new com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$1
            r0.<init>(r5, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ NameNotFoundException -> 0x004d, RemoteException -> 0x0027 }
            goto L_0x0045
        L_0x0027:
            r5 = move-exception
            goto L_0x0046
        L_0x0029:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = r5.bgDispatcher     // Catch:{ NameNotFoundException -> 0x004d, RemoteException -> 0x0027 }
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$2 r2 = new com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$2     // Catch:{ NameNotFoundException -> 0x004d, RemoteException -> 0x0027 }
            r4 = 0
            r2.<init>(r5, r4)     // Catch:{ NameNotFoundException -> 0x004d, RemoteException -> 0x0027 }
            r0.label = r3     // Catch:{ NameNotFoundException -> 0x004d, RemoteException -> 0x0027 }
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)     // Catch:{ NameNotFoundException -> 0x004d, RemoteException -> 0x0027 }
            if (r6 != r1) goto L_0x0045
            return r1
        L_0x0045:
            return r6
        L_0x0046:
            java.lang.String r6 = "Columbus/CameraVis"
            java.lang.String r0 = "Could not check camera foreground status"
            android.util.Log.e(r6, r0, r5)
        L_0x004d:
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.gates.CameraVisibility.isCameraInForeground(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object isCameraTopActivity(kotlin.coroutines.Continuation r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$1 r0 = (com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$1 r0 = new com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$1
            r0.<init>(r5, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ RemoteException -> 0x0027 }
            goto L_0x0045
        L_0x0027:
            r5 = move-exception
            goto L_0x0046
        L_0x0029:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = r5.bgDispatcher     // Catch:{ RemoteException -> 0x0027 }
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$2 r2 = new com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$2     // Catch:{ RemoteException -> 0x0027 }
            r4 = 0
            r2.<init>(r5, r4)     // Catch:{ RemoteException -> 0x0027 }
            r0.label = r3     // Catch:{ RemoteException -> 0x0027 }
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)     // Catch:{ RemoteException -> 0x0027 }
            if (r6 != r1) goto L_0x0045
            return r1
        L_0x0045:
            return r6
        L_0x0046:
            java.lang.String r6 = "Columbus/CameraVis"
            java.lang.String r0 = "unable to check task stack"
            android.util.Log.e(r6, r0, r5)
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.gates.CameraVisibility.isCameraTopActivity(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onActivate() {
        KeyguardVisibility keyguardVisibility = this.keyguardGate;
        CameraVisibility$gateListener$1 cameraVisibility$gateListener$1 = this.gateListener;
        keyguardVisibility.registerListener(cameraVisibility$gateListener$1);
        this.powerState.registerListener(cameraVisibility$gateListener$1);
        try {
            ((IActivityManager) this.activityManager.get()).registerTaskStackListener(this.taskStackListener);
        } catch (RemoteException e) {
            Log.e("Columbus/CameraVis", "Could not register task stack listener", e);
        }
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraVisibility$onActivate$1(this, (Continuation) null), 3);
    }

    public final void onDeactivate() {
        KeyguardVisibility keyguardVisibility = this.keyguardGate;
        CameraVisibility$gateListener$1 cameraVisibility$gateListener$1 = this.gateListener;
        keyguardVisibility.unregisterListener(cameraVisibility$gateListener$1);
        this.powerState.unregisterListener(cameraVisibility$gateListener$1);
        for (Action action : this.exceptions) {
            action.listeners.remove(this.actionListener);
        }
        try {
            ((IActivityManager) this.activityManager.get()).unregisterTaskStackListener(this.taskStackListener);
        } catch (RemoteException e) {
            Log.e("Columbus/CameraVis", "Could not unregister task stack listener", e);
        }
    }

    public final String toString() {
        String gate = super.toString();
        Object runBlocking = BuildersKt.runBlocking(this.mainDispatcher, new CameraVisibility$toString$1(this, (Continuation) null));
        return gate + runBlocking;
    }
}
