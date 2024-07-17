package com.google.android.systemui.smartspace;

import android.os.Bundle;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.SearchResults;
import androidx.appsearch.app.SearchSpec;
import androidx.appsearch.builtintypes.Alarm;
import androidx.appsearch.builtintypes.AlarmInstance;
import androidx.appsearch.platformstorage.GlobalSearchSessionImpl;
import androidx.appsearch.platformstorage.SearchResultsImpl;
import androidx.appsearch.platformstorage.converter.SearchSpecToPlatformConverter;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class AlarmAppSearchController$query$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ AlarmAppSearchController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AlarmAppSearchController$query$2(AlarmAppSearchController alarmAppSearchController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = alarmAppSearchController;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new AlarmAppSearchController$query$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AlarmAppSearchController$query$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: type inference failed for: r7v7, types: [androidx.appsearch.app.SearchSpec$Builder, java.lang.Object] */
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (!((Boolean) this.this$0.isInitialized.getValue()).booleanValue()) {
                Log.w("AlarmAppSearchCtlr", "Session is not initialized yet!");
                return new Object();
            }
            ? obj2 = new Object();
            obj2.mSchemas = new ArrayList();
            obj2.mNamespaces = new ArrayList();
            obj2.mPackageNames = new ArrayList();
            obj2.mEnabledFeatures = new ArraySet(0);
            obj2.mProjectionTypePropertyMasks = new Bundle();
            obj2.mTypePropertyWeights = new Bundle();
            obj2.mResultCountPerPage = 10;
            obj2.mTermMatchType = 2;
            obj2.mSnippetCountPerProperty = 10000;
            obj2.mAdvancedRankingExpression = "";
            obj2.mBuilt = false;
            obj2.resetIfBuilt();
            List asList = Arrays.asList(new String[]{"com.google.android.deskclock"});
            asList.getClass();
            obj2.resetIfBuilt();
            obj2.mPackageNames.addAll(asList);
            obj2.addFilterDocumentClasses(Alarm.class);
            obj2.addFilterDocumentClasses(AlarmInstance.class);
            Preconditions.checkArgumentInRange("resultCountPerPage", 10, 0, 10000);
            obj2.resetIfBuilt();
            obj2.mResultCountPerPage = 10;
            Bundle bundle = new Bundle();
            if (obj2.mTypePropertyWeights.isEmpty()) {
                if (!obj2.mSchemas.isEmpty()) {
                    for (String str : obj2.mProjectionTypePropertyMasks.keySet()) {
                        if (!obj2.mSchemas.contains(str)) {
                            throw new IllegalArgumentException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Projection requested for schema not in schemas filters: ", str));
                        }
                    }
                }
                bundle.putStringArrayList("schema", obj2.mSchemas);
                bundle.putStringArrayList("namespace", obj2.mNamespaces);
                bundle.putStringArrayList("packageName", obj2.mPackageNames);
                bundle.putStringArrayList("enabledFeatures", new ArrayList(obj2.mEnabledFeatures));
                bundle.putBundle("projectionTypeFieldMasks", obj2.mProjectionTypePropertyMasks);
                bundle.putInt("numPerPage", obj2.mResultCountPerPage);
                bundle.putInt("termMatchType", obj2.mTermMatchType);
                bundle.putInt("snippetCount", 0);
                bundle.putInt("snippetCountPerProperty", obj2.mSnippetCountPerProperty);
                bundle.putInt("maxSnippet", 0);
                bundle.putInt("rankingStrategy", 0);
                bundle.putInt("order", 0);
                bundle.putInt("resultGroupingTypeFlags", 0);
                bundle.putInt("resultGroupingLimit", 0);
                bundle.putBundle("typePropertyWeightsField", obj2.mTypePropertyWeights);
                bundle.putString("advancedRankingExpression", obj2.mAdvancedRankingExpression);
                obj2.mBuilt = true;
                SearchSpec searchSpec = new SearchSpec(bundle);
                GlobalSearchSessionImpl globalSearchSessionImpl = this.this$0.searchSession;
                if (globalSearchSessionImpl == null) {
                    globalSearchSessionImpl = null;
                }
                globalSearchSessionImpl.getClass();
                return new SearchResultsImpl(globalSearchSessionImpl.mPlatformSession.search("", SearchSpecToPlatformConverter.toPlatformSearchSpec(searchSpec)), searchSpec, globalSearchSessionImpl.mExecutor);
            }
            throw new IllegalArgumentException("Property weights are only compatible with the RANKING_STRATEGY_RELEVANCE_SCORE and RANKING_STRATEGY_ADVANCED_RANKING_EXPRESSION ranking strategies.");
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /* renamed from: com.google.android.systemui.smartspace.AlarmAppSearchController$query$2$1  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass1 implements SearchResults {
        public final ListenableFuture getNextPageAsync() {
            return new ImmediateFuture(new ArrayList());
        }

        public final void close() {
        }
    }
}
