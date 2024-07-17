package com.google.android.systemui.power.batteryevent.domain;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import androidx.compose.animation.graphics.vector.ObjectAnimator$$ExternalSyntheticOutline0;
import androidx.compose.runtime.Anchor$$ExternalSyntheticOutline0;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LifecycleService;
import com.android.systemui.broadcast.BroadcastSender;
import com.google.android.systemui.power.batteryevent.aidl.SurfaceType;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatteryEventService extends LifecycleService {
    public static final Set supportedCallers = SetsKt__SetsKt.setOf("com.android.settings", "com.android.systemui", "com.google.android.settings.intelligence");
    public final BatteryEventService$aidlBatteryEventsCallbackListener$1 aidlBatteryEventsCallbackListener = new BatteryEventService$aidlBatteryEventsCallbackListener$1(this);
    public final CoroutineDispatcher backgroundDispatcher;
    public final ArrayMap batteryEventsBroadcastCache = new SimpleArrayMap(0);
    public final MutexImpl batteryEventsBroadcastCacheMutex = MutexKt.Mutex$default();
    public final ArrayMap batteryEventsCallbackCache = new SimpleArrayMap(0);
    public final MutexImpl batteryEventsCallbackCacheMutex = MutexKt.Mutex$default();
    public final BatteryEventService$binder$1 binder = new BatteryEventService$binder$1(this);
    public final CopyOnWriteArraySet broadcastIntentBatteryEventsListener = new CopyOnWriteArraySet();
    public final BroadcastSender broadcastSender;
    public final BatteryEventStateController eventStateController;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class BatteryEventsBroadcastData {
        public final ComponentName componentName;
        public final String indexKey;
        public final Set subscribedEvents;
        public final int userId;

        public BatteryEventsBroadcastData(ComponentName componentName2, Set set, int i, String str) {
            this.componentName = componentName2;
            this.subscribedEvents = set;
            this.userId = i;
            this.indexKey = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BatteryEventsBroadcastData)) {
                return false;
            }
            BatteryEventsBroadcastData batteryEventsBroadcastData = (BatteryEventsBroadcastData) obj;
            if (Intrinsics.areEqual(this.componentName, batteryEventsBroadcastData.componentName) && Intrinsics.areEqual(this.subscribedEvents, batteryEventsBroadcastData.subscribedEvents) && this.userId == batteryEventsBroadcastData.userId && Intrinsics.areEqual(this.indexKey, batteryEventsBroadcastData.indexKey)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            int hashCode = this.subscribedEvents.hashCode();
            return this.indexKey.hashCode() + ObjectAnimator$$ExternalSyntheticOutline0.m(this.userId, (hashCode + (this.componentName.hashCode() * 31)) * 31, 31);
        }

        public final String toString() {
            return "BatteryEventsBroadcastData(componentName=" + this.componentName + ", subscribedEvents=" + this.subscribedEvents + ", userId=" + this.userId + ", indexKey=" + this.indexKey + ")";
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class BatteryEventsCallbackData {
        public final Set subscribedEvents;
        public final SurfaceType surfaceType;

        public BatteryEventsCallbackData(Set set, SurfaceType surfaceType2) {
            this.subscribedEvents = set;
            this.surfaceType = surfaceType2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BatteryEventsCallbackData)) {
                return false;
            }
            BatteryEventsCallbackData batteryEventsCallbackData = (BatteryEventsCallbackData) obj;
            if (Intrinsics.areEqual(this.subscribedEvents, batteryEventsCallbackData.subscribedEvents) && this.surfaceType == batteryEventsCallbackData.surfaceType) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            int i;
            int hashCode = this.subscribedEvents.hashCode() * 31;
            SurfaceType surfaceType2 = this.surfaceType;
            if (surfaceType2 == null) {
                i = 0;
            } else {
                i = surfaceType2.hashCode();
            }
            return hashCode + i;
        }

        public final String toString() {
            return "BatteryEventsCallbackData(subscribedEvents=" + this.subscribedEvents + ", surfaceType=" + this.surfaceType + ")";
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class CachedBatteryEvents {
        public final int batteryLevel;
        public final Set needNotifiedEvents;
        public final int pluggedType;

        public CachedBatteryEvents(Set set, int i, int i2) {
            this.needNotifiedEvents = set;
            this.batteryLevel = i;
            this.pluggedType = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CachedBatteryEvents)) {
                return false;
            }
            CachedBatteryEvents cachedBatteryEvents = (CachedBatteryEvents) obj;
            if (Intrinsics.areEqual(this.needNotifiedEvents, cachedBatteryEvents.needNotifiedEvents) && this.batteryLevel == cachedBatteryEvents.batteryLevel && this.pluggedType == cachedBatteryEvents.pluggedType) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Integer.hashCode(this.pluggedType) + ObjectAnimator$$ExternalSyntheticOutline0.m(this.batteryLevel, this.needNotifiedEvents.hashCode() * 31, 31);
        }

        public final boolean isEqual(Set set, int i, int i2) {
            Set set2 = this.needNotifiedEvents;
            if (set2.size() == set.size() && set2.containsAll(set) && this.batteryLevel == i && this.pluggedType == i2) {
                return true;
            }
            return false;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("CachedBatteryEvents(needNotifiedEvents=");
            sb.append(this.needNotifiedEvents);
            sb.append(", batteryLevel=");
            sb.append(this.batteryLevel);
            sb.append(", pluggedType=");
            return Anchor$$ExternalSyntheticOutline0.m(sb, this.pluggedType, ")");
        }
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [androidx.collection.SimpleArrayMap, androidx.collection.ArrayMap] */
    /* JADX WARNING: type inference failed for: r1v4, types: [androidx.collection.SimpleArrayMap, androidx.collection.ArrayMap] */
    public BatteryEventService(BatteryEventStateController batteryEventStateController, BroadcastSender broadcastSender2, CoroutineDispatcher coroutineDispatcher) {
        this.eventStateController = batteryEventStateController;
        this.broadcastSender = broadcastSender2;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0067 A[Catch:{ RemoteException -> 0x0030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0068 A[Catch:{ RemoteException -> 0x0030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object access$notifyAidlListenerBatteryEventUpdate(com.google.android.systemui.power.batteryevent.domain.BatteryEventService r6, int r7, com.google.android.systemui.power.batteryevent.common.BatteryEvents r8, kotlin.coroutines.Continuation r9) {
        /*
            r6.getClass()
            boolean r0 = r9 instanceof com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlListenerBatteryEventUpdate$1
            if (r0 == 0) goto L_0x0016
            r0 = r9
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlListenerBatteryEventUpdate$1 r0 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlListenerBatteryEventUpdate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0016
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x001b
        L_0x0016:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlListenerBatteryEventUpdate$1 r0 = new com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlListenerBatteryEventUpdate$1
            r0.<init>(r6, r9)
        L_0x001b:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            long r6 = r0.J$0
            java.lang.Object r8 = r0.L$0
            com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener r8 = (com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener) r8
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ RemoteException -> 0x0030 }
            goto L_0x0063
        L_0x0030:
            r8 = move-exception
            goto L_0x007a
        L_0x0032:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r9)
            long r4 = java.lang.System.currentTimeMillis()
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r9 = r6.aidlBatteryEventsCallbackListener     // Catch:{ RemoteException -> 0x0078 }
            java.lang.Object r9 = r9.getBroadcastCookie(r7)     // Catch:{ RemoteException -> 0x0078 }
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsCallbackData r9 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsCallbackData) r9     // Catch:{ RemoteException -> 0x0078 }
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r2 = r6.aidlBatteryEventsCallbackListener     // Catch:{ RemoteException -> 0x0078 }
            android.os.IInterface r7 = r2.getBroadcastItem(r7)     // Catch:{ RemoteException -> 0x0078 }
            com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener r7 = (com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener) r7     // Catch:{ RemoteException -> 0x0078 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch:{ RemoteException -> 0x0078 }
            r0.L$0 = r7     // Catch:{ RemoteException -> 0x0078 }
            r0.J$0 = r4     // Catch:{ RemoteException -> 0x0078 }
            r0.label = r3     // Catch:{ RemoteException -> 0x0078 }
            java.lang.Object r9 = r6.updateBatteryEventsCallbackCache(r9, r8, r7, r0)     // Catch:{ RemoteException -> 0x0078 }
            if (r9 != r1) goto L_0x0061
            goto L_0x008b
        L_0x0061:
            r8 = r7
            r6 = r4
        L_0x0063:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r9 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.CachedBatteryEvents) r9     // Catch:{ RemoteException -> 0x0030 }
            if (r9 != 0) goto L_0x0068
            goto L_0x0081
        L_0x0068:
            java.util.Set r0 = r9.needNotifiedEvents     // Catch:{ RemoteException -> 0x0030 }
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ RemoteException -> 0x0030 }
            java.util.List r0 = kotlin.collections.CollectionsKt___CollectionsKt.toList(r0)     // Catch:{ RemoteException -> 0x0030 }
            int r1 = r9.batteryLevel     // Catch:{ RemoteException -> 0x0030 }
            int r9 = r9.pluggedType     // Catch:{ RemoteException -> 0x0030 }
            r8.onBatteryEventChanged(r1, r9, r0)     // Catch:{ RemoteException -> 0x0030 }
            goto L_0x0081
        L_0x0078:
            r8 = move-exception
            r6 = r4
        L_0x007a:
            java.lang.String r9 = "BatteryEventService"
            java.lang.String r0 = "unexpected exception"
            android.util.Log.w(r9, r0, r8)
        L_0x0081:
            long r8 = java.lang.System.currentTimeMillis()
            long r8 = r8 - r6
            java.lang.Long r1 = new java.lang.Long
            r1.<init>(r8)
        L_0x008b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.domain.BatteryEventService.access$notifyAidlListenerBatteryEventUpdate(com.google.android.systemui.power.batteryevent.domain.BatteryEventService, int, com.google.android.systemui.power.batteryevent.common.BatteryEvents, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: java.util.Set} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: com.google.android.systemui.power.batteryevent.common.BatteryEvents} */
    /* JADX WARNING: type inference failed for: r5v6, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0060, code lost:
        if (r9.lock((java.lang.Object) null, r0) == r1) goto L_0x0093;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0079 A[Catch:{ all -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007d A[Catch:{ all -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object access$updateBatteryEventsBroadcastCache(com.google.android.systemui.power.batteryevent.domain.BatteryEventService r5, com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsBroadcastData r6, com.google.android.systemui.power.batteryevent.common.BatteryEvents r7, java.util.Set r8, kotlin.coroutines.Continuation r9) {
        /*
            r5.getClass()
            boolean r0 = r9 instanceof com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsBroadcastCache$1
            if (r0 == 0) goto L_0x0016
            r0 = r9
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsBroadcastCache$1 r0 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsBroadcastCache$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0016
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x001b
        L_0x0016:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsBroadcastCache$1 r0 = new com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsBroadcastCache$1
            r0.<init>(r5, r9)
        L_0x001b:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x004b
            if (r2 != r3) goto L_0x0043
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r6 = r0.L$3
            r8 = r6
            java.util.Set r8 = (java.util.Set) r8
            java.lang.Object r6 = r0.L$2
            r7 = r6
            com.google.android.systemui.power.batteryevent.common.BatteryEvents r7 = (com.google.android.systemui.power.batteryevent.common.BatteryEvents) r7
            java.lang.Object r6 = r0.L$1
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData r6 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsBroadcastData) r6
            java.lang.Object r0 = r0.L$0
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService r0 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService) r0
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r5
            r5 = r0
            goto L_0x0063
        L_0x0043:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x004b:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.sync.MutexImpl r9 = r5.batteryEventsBroadcastCacheMutex
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.L$3 = r8
            r0.L$4 = r9
            r0.label = r3
            java.lang.Object r0 = r9.lock(r4, r0)
            if (r0 != r1) goto L_0x0063
            goto L_0x0093
        L_0x0063:
            androidx.collection.ArrayMap r0 = r5.batteryEventsBroadcastCache     // Catch:{ all -> 0x007b }
            java.lang.String r1 = r6.indexKey     // Catch:{ all -> 0x007b }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x007b }
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r0 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.CachedBatteryEvents) r0     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x007d
            int r1 = r7.batteryLevel     // Catch:{ all -> 0x007b }
            int r2 = r7.pluggedType     // Catch:{ all -> 0x007b }
            boolean r0 = r0.isEqual(r8, r1, r2)     // Catch:{ all -> 0x007b }
            if (r0 != r3) goto L_0x007d
            r1 = r4
            goto L_0x008e
        L_0x007b:
            r5 = move-exception
            goto L_0x0094
        L_0x007d:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r0 = new com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents     // Catch:{ all -> 0x007b }
            int r1 = r7.batteryLevel     // Catch:{ all -> 0x007b }
            int r7 = r7.pluggedType     // Catch:{ all -> 0x007b }
            r0.<init>(r8, r1, r7)     // Catch:{ all -> 0x007b }
            androidx.collection.ArrayMap r5 = r5.batteryEventsBroadcastCache     // Catch:{ all -> 0x007b }
            java.lang.String r6 = r6.indexKey     // Catch:{ all -> 0x007b }
            r5.put(r6, r0)     // Catch:{ all -> 0x007b }
            r1 = r0
        L_0x008e:
            kotlinx.coroutines.sync.MutexImpl r9 = (kotlinx.coroutines.sync.MutexImpl) r9
            r9.unlock(r4)
        L_0x0093:
            return r1
        L_0x0094:
            kotlinx.coroutines.sync.MutexImpl r9 = (kotlinx.coroutines.sync.MutexImpl) r9
            r9.unlock(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.domain.BatteryEventService.access$updateBatteryEventsBroadcastCache(com.google.android.systemui.power.batteryevent.domain.BatteryEventService, com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData, com.google.android.systemui.power.batteryevent.common.BatteryEvents, java.util.Set, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final IBinder onBind(Intent intent) {
        super.onBind(intent);
        Log.i("BatteryEventService", "BatteryEventService bound");
        return this.binder;
    }

    public final void onCreate() {
        super.onCreate();
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), this.backgroundDispatcher, (CoroutineStart) null, new BatteryEventService$onCreate$1(this, (Continuation) null), 2);
    }

    public final void onDestroy() {
        super.onDestroy();
        CoroutineScopeKt.cancel(LifecycleOwnerKt.getLifecycleScope(this), ExceptionsKt.CancellationException("BatteryEventService destroyed", (Throwable) null));
        this.broadcastIntentBatteryEventsListener.clear();
        this.aidlBatteryEventsCallbackListener.kill();
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        return 1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: com.google.android.systemui.power.batteryevent.common.BatteryEvents} */
    /* JADX WARNING: type inference failed for: r5v6, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x007a A[SYNTHETIC, Splitter:B:19:0x007a] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object updateBatteryEventsCallbackCache(com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsCallbackData r6, com.google.android.systemui.power.batteryevent.common.BatteryEvents r7, com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener r8, kotlin.coroutines.Continuation r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsCallbackCache$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsCallbackCache$1 r0 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsCallbackCache$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsCallbackCache$1 r0 = new com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsCallbackCache$1
            r0.<init>(r5, r9)
        L_0x0018:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r6 = r0.L$3
            r8 = r6
            com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener r8 = (com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener) r8
            java.lang.Object r6 = r0.L$2
            r7 = r6
            com.google.android.systemui.power.batteryevent.common.BatteryEvents r7 = (com.google.android.systemui.power.batteryevent.common.BatteryEvents) r7
            java.lang.Object r6 = r0.L$1
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsCallbackData r6 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsCallbackData) r6
            java.lang.Object r0 = r0.L$0
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService r0 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService) r0
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r5
            r5 = r0
            goto L_0x0060
        L_0x0040:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.sync.MutexImpl r9 = r5.batteryEventsCallbackCacheMutex
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.L$3 = r8
            r0.L$4 = r9
            r0.label = r3
            java.lang.Object r0 = r9.lock(r4, r0)
            if (r0 != r1) goto L_0x0060
            return r1
        L_0x0060:
            java.util.Set r6 = r6.subscribedEvents     // Catch:{ all -> 0x0082 }
            java.lang.Iterable r6 = (java.lang.Iterable) r6     // Catch:{ all -> 0x0082 }
            java.util.Set r0 = r7.eventTypes     // Catch:{ all -> 0x0082 }
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ all -> 0x0082 }
            java.util.Set r6 = kotlin.collections.CollectionsKt___CollectionsKt.intersect(r6, r0)     // Catch:{ all -> 0x0082 }
            androidx.collection.ArrayMap r0 = r5.batteryEventsCallbackCache     // Catch:{ all -> 0x0082 }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x0082 }
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r0 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.CachedBatteryEvents) r0     // Catch:{ all -> 0x0082 }
            int r1 = r7.pluggedType
            int r7 = r7.batteryLevel
            if (r0 == 0) goto L_0x0084
            boolean r0 = r0.isEqual(r6, r7, r1)     // Catch:{ all -> 0x0082 }
            if (r0 != r3) goto L_0x0084
            r0 = r4
            goto L_0x008e
        L_0x0082:
            r5 = move-exception
            goto L_0x0094
        L_0x0084:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r0 = new com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents     // Catch:{ all -> 0x0082 }
            r0.<init>(r6, r7, r1)     // Catch:{ all -> 0x0082 }
            androidx.collection.ArrayMap r5 = r5.batteryEventsCallbackCache     // Catch:{ all -> 0x0082 }
            r5.put(r8, r0)     // Catch:{ all -> 0x0082 }
        L_0x008e:
            kotlinx.coroutines.sync.MutexImpl r9 = (kotlinx.coroutines.sync.MutexImpl) r9
            r9.unlock(r4)
            return r0
        L_0x0094:
            kotlinx.coroutines.sync.MutexImpl r9 = (kotlinx.coroutines.sync.MutexImpl) r9
            r9.unlock(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.domain.BatteryEventService.updateBatteryEventsCallbackCache(com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsCallbackData, com.google.android.systemui.power.batteryevent.common.BatteryEvents, com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
