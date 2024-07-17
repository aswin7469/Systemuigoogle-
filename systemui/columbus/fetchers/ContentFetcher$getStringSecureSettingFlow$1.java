package com.google.android.systemui.columbus.fetchers;

import android.net.Uri;
import java.util.function.Function;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ContentFetcher$getStringSecureSettingFlow$1 implements Function {
    public final /* synthetic */ String $defaultSettingValue;
    public final /* synthetic */ String $settingKey;
    public final /* synthetic */ Uri $settingUri;
    public final /* synthetic */ ContentFetcher this$0;

    public ContentFetcher$getStringSecureSettingFlow$1(ContentFetcher contentFetcher, String str, Uri uri, String str2) {
        this.this$0 = contentFetcher;
        this.$settingKey = str;
        this.$settingUri = uri;
        this.$defaultSettingValue = str2;
    }

    public final Object apply(Object obj) {
        Uri uri = (Uri) obj;
        ContentFetcher contentFetcher = this.this$0;
        String str = this.$settingKey;
        Uri uri2 = this.$settingUri;
        String str2 = this.$defaultSettingValue;
        return FlowKt.stateIn(FlowKt.flowOn(FlowKt.mapLatest(new ContentFetcher$createStringSecureSettingFlow$1(contentFetcher, str, str2, (Continuation) null), FlowKt.transformLatest(contentFetcher.userFetcher.currentUserHandle, new ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1((Continuation) null, contentFetcher, uri2))), contentFetcher.bgDispatcher), this.this$0.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), this.$defaultSettingValue);
    }
}
