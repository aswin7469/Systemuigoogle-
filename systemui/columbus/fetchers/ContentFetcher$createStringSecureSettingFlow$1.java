package com.google.android.systemui.columbus.fetchers;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class ContentFetcher$createStringSecureSettingFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String $defaultSettingValue;
    final /* synthetic */ String $settingKey;
    /* synthetic */ int I$0;
    int label;
    final /* synthetic */ ContentFetcher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContentFetcher$createStringSecureSettingFlow$1(ContentFetcher contentFetcher, String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contentFetcher;
        this.$settingKey = str;
        this.$defaultSettingValue = str2;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        ContentFetcher$createStringSecureSettingFlow$1 contentFetcher$createStringSecureSettingFlow$1 = new ContentFetcher$createStringSecureSettingFlow$1(this.this$0, this.$settingKey, this.$defaultSettingValue, continuation);
        contentFetcher$createStringSecureSettingFlow$1.I$0 = ((Number) obj).intValue();
        return contentFetcher$createStringSecureSettingFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ContentFetcher$createStringSecureSettingFlow$1) create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            int i2 = this.I$0;
            ContentFetcher contentFetcher = this.this$0;
            String str = this.$settingKey;
            String str2 = this.$defaultSettingValue;
            this.label = 1;
            contentFetcher.getClass();
            obj = BuildersKt.withContext(contentFetcher.bgDispatcher, new ContentFetcher$fetchStringSecureSetting$2(contentFetcher, str, i2, str2, (Continuation) null), this);
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
