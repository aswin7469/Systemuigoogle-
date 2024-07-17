package com.google.android.systemui.columbus.legacy.gates;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.ComponentName;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class CameraVisibility$isCameraTopActivity$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ CameraVisibility this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CameraVisibility$isCameraTopActivity$2(CameraVisibility cameraVisibility, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cameraVisibility;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new CameraVisibility$isCameraTopActivity$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CameraVisibility$isCameraTopActivity$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List tasks = ((IActivityManager) this.this$0.activityManager.get()).getTasks(1);
            boolean z = false;
            if (!tasks.isEmpty()) {
                ComponentName componentName = ((ActivityManager.RunningTaskInfo) tasks.get(0)).topActivity;
                if (componentName != null) {
                    str = componentName.getPackageName();
                } else {
                    str = null;
                }
                z = StringsKt__StringsJVMKt.equals(str, "com.google.android.GoogleCamera", true);
            }
            return Boolean.valueOf(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
