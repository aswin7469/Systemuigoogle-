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

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
