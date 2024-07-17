package com.google.android.systemui.smartspace;

import android.util.Log;
import androidx.appsearch.platformstorage.GlobalSearchSessionImpl;
import java.util.concurrent.Executor;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AlarmAppSearchController implements AppSearchController {
    public static final boolean DEBUG = Log.isLoggable("AlarmAppSearchCtlr", 3);
    public final CoroutineDispatcher bgDispatcher;
    public final StateFlowImpl isInitialized = StateFlowKt.MutableStateFlow(Boolean.FALSE);
    public final Executor mainExecutor;
    public GlobalSearchSessionImpl searchSession;

    public AlarmAppSearchController(Executor executor, CoroutineDispatcher coroutineDispatcher) {
        this.mainExecutor = executor;
        this.bgDispatcher = coroutineDispatcher;
    }

    /* JADX WARNING: type inference failed for: r10v7, types: [com.google.common.util.concurrent.ListenableFuture, androidx.concurrent.futures.ResolvableFuture, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007e A[Catch:{ all -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createSearchSession(android.content.Context r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            java.lang.String r0 = "session created="
            boolean r1 = r10 instanceof com.google.android.systemui.smartspace.AlarmAppSearchController$createSearchSession$1
            if (r1 == 0) goto L_0x0015
            r1 = r10
            com.google.android.systemui.smartspace.AlarmAppSearchController$createSearchSession$1 r1 = (com.google.android.systemui.smartspace.AlarmAppSearchController$createSearchSession$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.google.android.systemui.smartspace.AlarmAppSearchController$createSearchSession$1 r1 = new com.google.android.systemui.smartspace.AlarmAppSearchController$createSearchSession$1
            r1.<init>(r8, r10)
        L_0x001a:
            java.lang.Object r10 = r1.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r2 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r3 = r1.label
            java.lang.String r4 = "AlarmAppSearchCtlr"
            r5 = 1
            if (r3 == 0) goto L_0x003d
            if (r3 != r5) goto L_0x0035
            java.lang.Object r8 = r1.L$1
            com.google.android.systemui.smartspace.AlarmAppSearchController r8 = (com.google.android.systemui.smartspace.AlarmAppSearchController) r8
            java.lang.Object r9 = r1.L$0
            com.google.android.systemui.smartspace.AlarmAppSearchController r9 = (com.google.android.systemui.smartspace.AlarmAppSearchController) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0033 }
            goto L_0x006f
        L_0x0033:
            r8 = move-exception
            goto L_0x0093
        L_0x0035:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r10)
            r9.getClass()     // Catch:{ all -> 0x0033 }
            java.util.concurrent.Executor r10 = androidx.appsearch.platformstorage.PlatformStorage.EXECUTOR     // Catch:{ all -> 0x0033 }
            androidx.appsearch.platformstorage.PlatformStorage$GlobalSearchContext r3 = new androidx.appsearch.platformstorage.PlatformStorage$GlobalSearchContext     // Catch:{ all -> 0x0033 }
            r3.<init>(r9, r10)     // Catch:{ all -> 0x0033 }
            java.lang.Class<android.app.appsearch.AppSearchManager> r10 = android.app.appsearch.AppSearchManager.class
            java.lang.Object r9 = r9.getSystemService(r10)     // Catch:{ all -> 0x0033 }
            android.app.appsearch.AppSearchManager r9 = (android.app.appsearch.AppSearchManager) r9     // Catch:{ all -> 0x0033 }
            androidx.concurrent.futures.ResolvableFuture r10 = new androidx.concurrent.futures.ResolvableFuture     // Catch:{ all -> 0x0033 }
            r10.<init>()     // Catch:{ all -> 0x0033 }
            java.util.concurrent.Executor r6 = r3.mExecutor     // Catch:{ all -> 0x0033 }
            androidx.appsearch.platformstorage.PlatformStorage$$ExternalSyntheticLambda0 r7 = new androidx.appsearch.platformstorage.PlatformStorage$$ExternalSyntheticLambda0     // Catch:{ all -> 0x0033 }
            r7.<init>(r10, r3)     // Catch:{ all -> 0x0033 }
            r9.createGlobalSearchSession(r6, r7)     // Catch:{ all -> 0x0033 }
            r1.L$0 = r8     // Catch:{ all -> 0x0033 }
            r1.L$1 = r8     // Catch:{ all -> 0x0033 }
            r1.label = r5     // Catch:{ all -> 0x0033 }
            java.lang.Object r10 = androidx.concurrent.futures.ListenableFutureKt.await(r10, r1)     // Catch:{ all -> 0x0033 }
            if (r10 != r2) goto L_0x006e
            return r2
        L_0x006e:
            r9 = r8
        L_0x006f:
            androidx.appsearch.platformstorage.GlobalSearchSessionImpl r10 = (androidx.appsearch.platformstorage.GlobalSearchSessionImpl) r10     // Catch:{ all -> 0x0033 }
            r8.searchSession = r10     // Catch:{ all -> 0x0033 }
            kotlinx.coroutines.flow.StateFlowImpl r8 = r9.isInitialized     // Catch:{ all -> 0x0033 }
            java.lang.Boolean r10 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0033 }
            r8.setValue(r10)     // Catch:{ all -> 0x0033 }
            boolean r8 = DEBUG     // Catch:{ all -> 0x0033 }
            if (r8 == 0) goto L_0x0098
            androidx.appsearch.platformstorage.GlobalSearchSessionImpl r8 = r9.searchSession     // Catch:{ all -> 0x0033 }
            if (r8 != 0) goto L_0x0083
            r8 = 0
        L_0x0083:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0033 }
            r9.<init>(r0)     // Catch:{ all -> 0x0033 }
            r9.append(r8)     // Catch:{ all -> 0x0033 }
            java.lang.String r8 = r9.toString()     // Catch:{ all -> 0x0033 }
            android.util.Log.d(r4, r8)     // Catch:{ all -> 0x0033 }
            goto L_0x0098
        L_0x0093:
            java.lang.String r9 = "Failed to create session"
            android.util.Log.e(r4, r9, r8)
        L_0x0098:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.AlarmAppSearchController.createSearchSession(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getNextPageSearchResults(androidx.appsearch.app.SearchResults r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.google.android.systemui.smartspace.AlarmAppSearchController$getNextPageSearchResults$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.google.android.systemui.smartspace.AlarmAppSearchController$getNextPageSearchResults$1 r0 = (com.google.android.systemui.smartspace.AlarmAppSearchController$getNextPageSearchResults$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.systemui.smartspace.AlarmAppSearchController$getNextPageSearchResults$1 r0 = new com.google.android.systemui.smartspace.AlarmAppSearchController$getNextPageSearchResults$1
            r0.<init>(r4, r6)
        L_0x0018:
            java.lang.Object r4 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r6 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L_0x002f
            if (r1 != r2) goto L_0x0027
            kotlin.ResultKt.throwOnFailure(r4)
            goto L_0x003f
        L_0x0027:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x002f:
            kotlin.ResultKt.throwOnFailure(r4)
            com.google.common.util.concurrent.ListenableFuture r4 = r5.getNextPageAsync()
            r0.label = r2
            java.lang.Object r4 = androidx.concurrent.futures.ListenableFutureKt.await(r4, r0)
            if (r4 != r6) goto L_0x003f
            return r6
        L_0x003f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.AlarmAppSearchController.getNextPageSearchResults(androidx.appsearch.app.SearchResults, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object query(kotlin.coroutines.Continuation r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.google.android.systemui.smartspace.AlarmAppSearchController$query$1
            if (r0 == 0) goto L_0x0013
            r0 = r5
            com.google.android.systemui.smartspace.AlarmAppSearchController$query$1 r0 = (com.google.android.systemui.smartspace.AlarmAppSearchController$query$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.systemui.smartspace.AlarmAppSearchController$query$1 r0 = new com.google.android.systemui.smartspace.AlarmAppSearchController$query$1
            r0.<init>(r4, r5)
        L_0x0018:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x002f
            if (r2 != r3) goto L_0x0027
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0043
        L_0x0027:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x002f:
            kotlin.ResultKt.throwOnFailure(r5)
            com.google.android.systemui.smartspace.AlarmAppSearchController$query$2 r5 = new com.google.android.systemui.smartspace.AlarmAppSearchController$query$2
            r2 = 0
            r5.<init>(r4, r2)
            r0.label = r3
            kotlinx.coroutines.CoroutineDispatcher r4 = r4.bgDispatcher
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r4, r5, r0)
            if (r5 != r1) goto L_0x0043
            return r1
        L_0x0043:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.AlarmAppSearchController.query(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final String toString() {
        GlobalSearchSessionImpl globalSearchSessionImpl = this.searchSession;
        if (globalSearchSessionImpl == null) {
            globalSearchSessionImpl = null;
        }
        Object value = this.isInitialized.getValue();
        return "AlarmAppSearchController { searchSession=" + globalSearchSessionImpl + ", isInitialized=" + value + "}";
    }
}
