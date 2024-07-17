package com.google.android.systemui.columbus.fetchers;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class ContentFetcher$createIntSecureSettingFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $defaultSettingValue;
    final /* synthetic */ String $settingKey;
    /* synthetic */ int I$0;
    int label;
    final /* synthetic */ ContentFetcher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContentFetcher$createIntSecureSettingFlow$1(ContentFetcher contentFetcher, String str, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contentFetcher;
        this.$settingKey = str;
        this.$defaultSettingValue = i;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        ContentFetcher$createIntSecureSettingFlow$1 contentFetcher$createIntSecureSettingFlow$1 = new ContentFetcher$createIntSecureSettingFlow$1(this.this$0, this.$settingKey, this.$defaultSettingValue, continuation);
        contentFetcher$createIntSecureSettingFlow$1.I$0 = ((Number) obj).intValue();
        return contentFetcher$createIntSecureSettingFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ContentFetcher$createIntSecureSettingFlow$1) create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            int i2 = this.I$0;
            ContentFetcher contentFetcher = this.this$0;
            String str = this.$settingKey;
            int i3 = this.$defaultSettingValue;
            this.label = 1;
            contentFetcher.getClass();
            obj = BuildersKt.withContext(contentFetcher.bgDispatcher, new ContentFetcher$fetchIntSecureSetting$2(contentFetcher, str, i3, i2, (Continuation) null), this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
