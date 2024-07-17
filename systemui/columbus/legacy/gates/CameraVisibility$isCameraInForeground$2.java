package com.google.android.systemui.columbus.legacy.gates;

import android.app.ActivityManager;
import android.app.IActivityManager;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class CameraVisibility$isCameraInForeground$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ CameraVisibility this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CameraVisibility$isCameraInForeground$2(CameraVisibility cameraVisibility, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cameraVisibility;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new CameraVisibility$isCameraInForeground$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CameraVisibility$isCameraInForeground$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CameraVisibility cameraVisibility = this.this$0;
            boolean z = false;
            int i = cameraVisibility.packageManager.getApplicationInfoAsUser("com.google.android.GoogleCamera", 0, ((IActivityManager) cameraVisibility.activityManager.get()).getCurrentUser().id).uid;
            Iterator it = ((IActivityManager) this.this$0.activityManager.get()).getRunningAppProcesses().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj2 = null;
                    break;
                }
                obj2 = it.next();
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = (ActivityManager.RunningAppProcessInfo) obj2;
                if (runningAppProcessInfo.uid == i && StringsKt__StringsJVMKt.equals(runningAppProcessInfo.processName, "com.google.android.GoogleCamera", true)) {
                    break;
                }
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo2 = (ActivityManager.RunningAppProcessInfo) obj2;
            if (runningAppProcessInfo2 != null && runningAppProcessInfo2.importance == 100) {
                z = true;
            }
            return Boolean.valueOf(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
