package com.google.android.systemui.columbus.fetchers;

import android.net.Uri;
import java.util.function.Function;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ContentFetcher$getIntSecureSettingFlow$1 implements Function {
    public final /* synthetic */ int $defaultSettingValue;
    public final /* synthetic */ String $settingKey = "columbus_launch_profile_id";
    public final /* synthetic */ Uri $settingUri;
    public final /* synthetic */ ContentFetcher this$0;

    public ContentFetcher$getIntSecureSettingFlow$1(ContentFetcher contentFetcher, Uri uri) {
        this.this$0 = contentFetcher;
        this.$settingUri = uri;
        this.$defaultSettingValue = 0;
    }

    public final Object apply(Object obj) {
        Uri uri = (Uri) obj;
        ContentFetcher contentFetcher = this.this$0;
        String str = this.$settingKey;
        Uri uri2 = this.$settingUri;
        int i = this.$defaultSettingValue;
        return FlowKt.stateIn(FlowKt.flowOn(FlowKt.mapLatest(new ContentFetcher$createIntSecureSettingFlow$1(contentFetcher, str, i, (Continuation) null), FlowKt.transformLatest(contentFetcher.userFetcher.currentUserHandle, new ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1((Continuation) null, contentFetcher, uri2))), contentFetcher.bgDispatcher), this.this$0.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Integer.valueOf(this.$defaultSettingValue));
    }
}
