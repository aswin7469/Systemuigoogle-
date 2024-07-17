package com.google.android.systemui.columbus.fetchers;

import android.app.backup.BackupManager;
import android.content.Context;
import android.net.Uri;
import android.os.UserHandle;
import android.provider.Settings;
import com.android.systemui.util.BackupManagerProxy;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__LimitKt$drop$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import org.json.JSONArray;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColumbusSettingsFetcher {
    public final String backupPackageName;
    public final StateFlow columbusEnabled;
    public final StateFlow lowSensitivity;
    public final StateFlow selectedAction;
    public final ReadonlyStateFlow selectedApp;
    public final StateFlow selectedShortcut;

    /* renamed from: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$1  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(userFetcher, backupManagerProxy, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ColumbusSettingsFetcher columbusSettingsFetcher = ColumbusSettingsFetcher.this;
                SafeFlow sample = com.android.systemui.util.kotlin.FlowKt.sample(FlowKt.merge(new FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(columbusSettingsFetcher.columbusEnabled), new FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(columbusSettingsFetcher.selectedAction), new FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(columbusSettingsFetcher.selectedApp), new FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(columbusSettingsFetcher.selectedShortcut), new FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(columbusSettingsFetcher.lowSensitivity)), userFetcher.currentUserHandle);
                final BackupManagerProxy backupManagerProxy = backupManagerProxy;
                final ColumbusSettingsFetcher columbusSettingsFetcher2 = ColumbusSettingsFetcher.this;
                AnonymousClass1 r2 = new FlowCollector() {
                    public final Object emit(Object obj, Continuation continuation) {
                        int identifier = ((UserHandle) obj).getIdentifier();
                        String str = columbusSettingsFetcher2.backupPackageName;
                        BackupManagerProxy.this.getClass();
                        BackupManager.dataChangedForUser(identifier, str);
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (sample.collect(r2, this) == coroutineSingletons) {
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

    public ColumbusSettingsFetcher(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, final UserFetcher userFetcher, ContentFetcher contentFetcher, Context context, final BackupManagerProxy backupManagerProxy) {
        contentFetcher.getClass();
        Uri uriFor = Settings.Secure.getUriFor("columbus_enabled");
        Map map = contentFetcher.cachedBooleanFlows;
        this.columbusEnabled = (StateFlow) map.computeIfAbsent(uriFor, new ContentFetcher$getBooleanSecureSettingFlow$1(contentFetcher, "columbus_enabled", uriFor, false));
        this.selectedAction = contentFetcher.getStringSecureSettingFlow("columbus_action", "");
        this.selectedApp = FlowKt.stateIn(new ColumbusSettingsFetcher$special$$inlined$map$1(contentFetcher.getStringSecureSettingFlow("columbus_launch_app", ""), 0), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), (Object) null);
        this.selectedShortcut = contentFetcher.getStringSecureSettingFlow("columbus_launch_app_shortcut", "");
        Uri uriFor2 = Settings.Secure.getUriFor("columbus_launch_profile_id");
        StateFlow stateFlow = (StateFlow) contentFetcher.cachedIntFlows.computeIfAbsent(uriFor2, new ContentFetcher$getIntSecureSettingFlow$1(contentFetcher, uriFor2));
        Uri uriFor3 = Settings.Secure.getUriFor("columbus_low_sensitivity");
        this.lowSensitivity = (StateFlow) map.computeIfAbsent(uriFor3, new ContentFetcher$getBooleanSecureSettingFlow$1(contentFetcher, "columbus_low_sensitivity", uriFor3, false));
        Uri uriFor4 = Settings.Secure.getUriFor("columbus_silence_alerts");
        StateFlow stateFlow2 = (StateFlow) map.computeIfAbsent(uriFor4, new ContentFetcher$getBooleanSecureSettingFlow$1(contentFetcher, "columbus_silence_alerts", uriFor4, true));
        FlowKt.stateIn(new ColumbusSettingsFetcher$special$$inlined$map$1(contentFetcher.getStringSecureSettingFlow("columbus_package_stats", "[]"), 1), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), new JSONArray());
        this.backupPackageName = context.getBasePackageName();
        BuildersKt.launch$default(coroutineScope, coroutineDispatcher, (CoroutineStart) null, new AnonymousClass1((Continuation) null), 2);
    }
}
