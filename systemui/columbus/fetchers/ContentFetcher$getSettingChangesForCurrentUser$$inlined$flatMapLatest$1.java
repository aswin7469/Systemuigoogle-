package com.google.android.systemui.columbus.fetchers;

import android.net.Uri;
import android.os.UserHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Uri $settingUri$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ ContentFetcher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1(Continuation continuation, ContentFetcher contentFetcher, Uri uri) {
        super(3, continuation);
        this.this$0 = contentFetcher;
        this.$settingUri$inlined = uri;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1 contentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1 = new ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0, this.$settingUri$inlined);
        contentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        contentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1.L$1 = obj2;
        return contentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            UserHandle userHandle = (UserHandle) this.L$1;
            ContentFetcher contentFetcher = this.this$0;
            Uri uri = this.$settingUri$inlined;
            int identifier = userHandle.getIdentifier();
            contentFetcher.getClass();
            FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new ContentFetcher$getSettingChangesForCurrentUser$1$1(userHandle, (Continuation) null), FlowKt.flowOn(FlowKt.buffer$default(FlowKt.callbackFlow(new ContentFetcher$getSettingChangesForUser$1(contentFetcher, uri, identifier, (Continuation) null)), -1), contentFetcher.bgDispatcher));
            this.label = 1;
            if (FlowKt.emitAll(this, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, (FlowCollector) this.L$0) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
