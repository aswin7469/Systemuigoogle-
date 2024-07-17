package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.app.contentsuggestions.ClassificationsRequest;
import android.app.contentsuggestions.ContentSuggestionsManager;
import android.app.contentsuggestions.SelectionsRequest;
import android.content.Context;
import android.os.Bundle;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.LogUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ContentSuggestionsServiceWrapperImpl {
    public final Executor asyncExecutor;
    public final Executor callbackExecutor;
    public final ContentSuggestionsManager contentSuggestionsManager;
    public final Executor loggingExecutor;
    public final Map pendingCallbacks = Collections.synchronizedMap(new WeakHashMap());

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class BundleCallbackWrapper implements ContentSuggestionsServiceWrapper$BundleCallback {
        public final Object key;
        public final WeakReference parentRef;

        public BundleCallbackWrapper(ContentSuggestionsServiceWrapper$BundleCallback contentSuggestionsServiceWrapper$BundleCallback, ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl) {
            Object obj = new Object();
            this.key = obj;
            contentSuggestionsServiceWrapperImpl.pendingCallbacks.put(obj, contentSuggestionsServiceWrapper$BundleCallback);
            this.parentRef = new WeakReference(contentSuggestionsServiceWrapperImpl);
        }

        public final void onResult(Bundle bundle) {
            ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = (ContentSuggestionsServiceWrapperImpl) this.parentRef.get();
            if (contentSuggestionsServiceWrapperImpl != null) {
                ContentSuggestionsServiceWrapper$BundleCallback contentSuggestionsServiceWrapper$BundleCallback = (ContentSuggestionsServiceWrapper$BundleCallback) contentSuggestionsServiceWrapperImpl.pendingCallbacks.remove(this.key);
                if (contentSuggestionsServiceWrapper$BundleCallback != null) {
                    contentSuggestionsServiceWrapper$BundleCallback.onResult(bundle);
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.concurrent.Executor, java.lang.Object] */
    public ContentSuggestionsServiceWrapperImpl(Context context, Executor executor, Executor executor2) {
        ? obj = new Object();
        this.asyncExecutor = executor;
        this.loggingExecutor = executor2;
        this.callbackExecutor = obj;
        this.contentSuggestionsManager = (ContentSuggestionsManager) context.getSystemService(ContentSuggestionsManager.class);
    }

    public final void classifyContentSelections(Bundle bundle, ContentSuggestionsServiceClient.AnonymousClass1.AnonymousClass1 r4) {
        try {
            this.contentSuggestionsManager.classifyContentSelections(new ClassificationsRequest.Builder(new ArrayList()).setExtras(bundle).build(), this.callbackExecutor, new ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda0(new BundleCallbackWrapper(r4, this)));
        } catch (Throwable th) {
            LogUtils.e("Failed to classifyContentSelections", th);
        }
    }

    public final void suggestContentSelections(int i, Bundle bundle, ContentSuggestionsServiceWrapper$BundleCallback contentSuggestionsServiceWrapper$BundleCallback) {
        SelectionsRequest build = new SelectionsRequest.Builder(i).setExtras(bundle).build();
        try {
            this.contentSuggestionsManager.suggestContentSelections(build, this.callbackExecutor, new ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda2(new BundleCallbackWrapper(contentSuggestionsServiceWrapper$BundleCallback, this)));
        } catch (Throwable th) {
            LogUtils.e("Failed to suggestContentSelections", th);
        }
    }
}
