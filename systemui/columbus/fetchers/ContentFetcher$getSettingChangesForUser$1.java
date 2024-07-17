package com.google.android.systemui.columbus.fetchers;

import android.net.Uri;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class ContentFetcher$getSettingChangesForUser$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Uri $settingUri;
    final /* synthetic */ int $userId;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ContentFetcher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContentFetcher$getSettingChangesForUser$1(ContentFetcher contentFetcher, Uri uri, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contentFetcher;
        this.$settingUri = uri;
        this.$userId = i;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        ContentFetcher$getSettingChangesForUser$1 contentFetcher$getSettingChangesForUser$1 = new ContentFetcher$getSettingChangesForUser$1(this.this$0, this.$settingUri, this.$userId, continuation);
        contentFetcher$getSettingChangesForUser$1.L$0 = obj;
        return contentFetcher$getSettingChangesForUser$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ContentFetcher$getSettingChangesForUser$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final ContentFetcher$getSettingChangesForUser$1$contentObserver$1 contentFetcher$getSettingChangesForUser$1$contentObserver$1 = new ContentFetcher$getSettingChangesForUser$1$contentObserver$1(producerScope, this.$userId, this.this$0.mainHandler);
            this.this$0.contentResolver.registerContentObserver(this.$settingUri, false, contentFetcher$getSettingChangesForUser$1$contentObserver$1, this.$userId);
            final ContentFetcher contentFetcher = this.this$0;
            AnonymousClass1 r1 = new Function0() {
                public final Object invoke() {
                    ContentFetcher.this.contentResolver.unregisterContentObserver(contentFetcher$getSettingChangesForUser$1$contentObserver$1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, r1, this) == coroutineSingletons) {
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
