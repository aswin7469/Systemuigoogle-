package com.google.android.systemui.columbus.fetchers;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ContentFetcher {
    public final CoroutineDispatcher bgDispatcher;
    public final Map cachedBooleanFlows = new LinkedHashMap();
    public final Map cachedIntFlows = new LinkedHashMap();
    public final Map cachedStringFlows = new LinkedHashMap();
    public final ContentResolver contentResolver;
    public final CoroutineScope coroutineScope;
    public final Handler mainHandler;
    public final UserFetcher userFetcher;

    public ContentFetcher(CoroutineScope coroutineScope2, CoroutineDispatcher coroutineDispatcher, Context context, UserFetcher userFetcher2, Handler handler) {
        this.coroutineScope = coroutineScope2;
        this.bgDispatcher = coroutineDispatcher;
        this.userFetcher = userFetcher2;
        this.mainHandler = handler;
        this.contentResolver = context.getContentResolver();
    }

    public final StateFlow getStringSecureSettingFlow(String str, String str2) {
        Uri uriFor = Settings.Secure.getUriFor(str);
        return (StateFlow) this.cachedStringFlows.computeIfAbsent(uriFor, new ContentFetcher$getStringSecureSettingFlow$1(this, str, uriFor, str2));
    }
}
