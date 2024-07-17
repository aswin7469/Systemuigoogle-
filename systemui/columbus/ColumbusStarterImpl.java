package com.google.android.systemui.columbus;

import com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher;
import dagger.Lazy;
import java.io.PrintWriter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusStarterImpl implements ColumbusStarter {
    public final Lazy columbusManager;
    public boolean started;

    /* renamed from: com.google.android.systemui.columbus.ColumbusStarterImpl$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* renamed from: com.google.android.systemui.columbus.ColumbusStarterImpl$1$1  reason: invalid class name */
        /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            /* synthetic */ boolean Z$0;
            int label;

            /* JADX WARNING: type inference failed for: r1v1, types: [kotlin.coroutines.Continuation, kotlin.coroutines.jvm.internal.SuspendLambda, com.google.android.systemui.columbus.ColumbusStarterImpl$1$1] */
            public final Continuation create(Object obj, Continuation continuation) {
                ? suspendLambda = new SuspendLambda(2, continuation);
                suspendLambda.Z$0 = ((Boolean) obj).booleanValue();
                return suspendLambda;
            }

            public final Object invoke(Object obj, Object obj2) {
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                return ((AnonymousClass1) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    return Boolean.valueOf(this.Z$0);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: type inference failed for: r1v1, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function2] */
        public final Object invokeSuspend(Object obj) {
            Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlow = ColumbusSettingsFetcher.this.columbusEnabled;
                ? suspendLambda = new SuspendLambda(2, (Continuation) null);
                this.label = 1;
                if (FlowKt.first(stateFlow, suspendLambda, this) == obj2) {
                    return obj2;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ColumbusStarterImpl columbusStarterImpl = this;
            columbusStarterImpl.started = true;
            columbusStarterImpl.columbusManager.get();
            return Unit.INSTANCE;
        }
    }

    public ColumbusStarterImpl(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Lazy lazy, final ColumbusSettingsFetcher columbusSettingsFetcher) {
        this.columbusManager = lazy;
        BuildersKt.launch$default(coroutineScope, coroutineDispatcher, (CoroutineStart) null, new AnonymousClass1((Continuation) null), 2);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        if (this.started) {
            ((ColumbusManager) this.columbusManager.get()).dump(printWriter, strArr);
        }
    }
}
