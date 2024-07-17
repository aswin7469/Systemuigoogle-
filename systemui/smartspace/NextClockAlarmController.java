package com.google.android.systemui.smartspace;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.CallbackController;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class NextClockAlarmController implements CallbackController, Dumpable {
    public static final boolean DEBUG = Log.isLoggable("NextClockAlarmCtlr", 3);
    public final AppSearchController alarmAppSearchController;
    public final CoroutineScope applicationScope;
    public final CoroutineScope backgroundScope;
    public final BroadcastDispatcher broadcastDispatcher;
    public final List changeCallbacks = new ArrayList();
    public Context context;
    public final DumpManager dumpManager;
    public final Executor mainExecutor;
    public long nextAlarm = -1;
    public String nextAlarmDetailInfo = "";
    public final NextClockAlarmController$observerCallback$1 observerCallback = new NextClockAlarmController$observerCallback$1(this);
    public Job updateNextAlarmJob;
    public StandaloneCoroutine updateSessionJob;
    public final NextClockAlarmController$userChangedCallback$1 userChangedCallback = new NextClockAlarmController$userChangedCallback$1(this);
    public final UserTracker userTracker;
    public final NextClockAlarmController$userUnlockReceiver$1 userUnlockReceiver = new NextClockAlarmController$userUnlockReceiver$1(this);

    public NextClockAlarmController(UserTracker userTracker2, BroadcastDispatcher broadcastDispatcher2, DumpManager dumpManager2, AlarmAppSearchController alarmAppSearchController2, Executor executor, CoroutineScope coroutineScope, CoroutineScope coroutineScope2) {
        this.userTracker = userTracker2;
        this.broadcastDispatcher = broadcastDispatcher2;
        this.dumpManager = dumpManager2;
        this.alarmAppSearchController = alarmAppSearchController2;
        this.mainExecutor = executor;
        this.applicationScope = coroutineScope;
        this.backgroundScope = coroutineScope2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object access$updateNextAlarm(com.google.android.systemui.smartspace.NextClockAlarmController r6, kotlin.coroutines.Continuation r7) {
        /*
            r6.getClass()
            boolean r0 = r7 instanceof com.google.android.systemui.smartspace.NextClockAlarmController$updateNextAlarm$1
            if (r0 == 0) goto L_0x0016
            r0 = r7
            com.google.android.systemui.smartspace.NextClockAlarmController$updateNextAlarm$1 r0 = (com.google.android.systemui.smartspace.NextClockAlarmController$updateNextAlarm$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0016
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x001b
        L_0x0016:
            com.google.android.systemui.smartspace.NextClockAlarmController$updateNextAlarm$1 r0 = new com.google.android.systemui.smartspace.NextClockAlarmController$updateNextAlarm$1
            r0.<init>(r6, r7)
        L_0x001b:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r5) goto L_0x003a
            if (r2 != r4) goto L_0x0032
            java.lang.Object r6 = r0.L$0
            com.google.android.systemui.smartspace.NextClockAlarmController r6 = (com.google.android.systemui.smartspace.NextClockAlarmController) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x006b
        L_0x0032:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003a:
            java.lang.Object r6 = r0.L$1
            com.google.android.systemui.smartspace.NextClockAlarmController r6 = (com.google.android.systemui.smartspace.NextClockAlarmController) r6
            java.lang.Object r2 = r0.L$0
            com.google.android.systemui.smartspace.NextClockAlarmController r2 = (com.google.android.systemui.smartspace.NextClockAlarmController) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x005b
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6
            r0.L$1 = r6
            r0.label = r5
            com.google.android.systemui.smartspace.AppSearchController r7 = r6.alarmAppSearchController
            com.google.android.systemui.smartspace.AlarmAppSearchController r7 = (com.google.android.systemui.smartspace.AlarmAppSearchController) r7
            java.lang.Object r7 = r7.query(r0)
            if (r7 != r1) goto L_0x005a
            goto L_0x009e
        L_0x005a:
            r2 = r6
        L_0x005b:
            androidx.appsearch.app.SearchResults r7 = (androidx.appsearch.app.SearchResults) r7
            r0.L$0 = r2
            r0.L$1 = r3
            r0.label = r4
            java.lang.Object r7 = r6.calculateNextClockAlarm(r7, r0)
            if (r7 != r1) goto L_0x006a
            goto L_0x009e
        L_0x006a:
            r6 = r2
        L_0x006b:
            java.lang.Number r7 = (java.lang.Number) r7
            long r0 = r7.longValue()
            long r4 = r6.nextAlarm
            int r7 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r7 == 0) goto L_0x009c
            r6.nextAlarm = r0
            java.util.List r6 = r6.changeCallbacks
            java.util.Iterator r6 = r6.iterator()
        L_0x007f:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x009c
            java.lang.Object r7 = r6.next()
            com.google.android.systemui.smartspace.KeyguardZenAlarmViewController$nextAlarmCallback$1 r7 = (com.google.android.systemui.smartspace.KeyguardZenAlarmViewController$nextAlarmCallback$1) r7
            com.google.android.systemui.smartspace.KeyguardZenAlarmViewController r7 = r7.this$0
            r7.getClass()
            com.google.android.systemui.smartspace.KeyguardZenAlarmViewController$updateNextAlarm$1 r0 = new com.google.android.systemui.smartspace.KeyguardZenAlarmViewController$updateNextAlarm$1
            r0.<init>(r7, r3)
            r1 = 3
            kotlinx.coroutines.CoroutineScope r7 = r7.applicationScope
            kotlinx.coroutines.BuildersKt.launch$default(r7, r3, r3, r0, r1)
            goto L_0x007f
        L_0x009c:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L_0x009e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.NextClockAlarmController.access$updateNextAlarm(com.google.android.systemui.smartspace.NextClockAlarmController, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void addCallback(Object obj) {
        KeyguardZenAlarmViewController$nextAlarmCallback$1 keyguardZenAlarmViewController$nextAlarmCallback$1 = (KeyguardZenAlarmViewController$nextAlarmCallback$1) obj;
        this.changeCallbacks.add(keyguardZenAlarmViewController$nextAlarmCallback$1);
        KeyguardZenAlarmViewController keyguardZenAlarmViewController = keyguardZenAlarmViewController$nextAlarmCallback$1.this$0;
        keyguardZenAlarmViewController.getClass();
        BuildersKt.launch$default(keyguardZenAlarmViewController.applicationScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardZenAlarmViewController$updateNextAlarm$1(keyguardZenAlarmViewController, (Continuation) null), 3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object calculateNextClockAlarm(androidx.appsearch.app.SearchResults r22, kotlin.coroutines.Continuation r23) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            java.lang.String r3 = "NextClockAlarmCtlr"
            boolean r4 = r2 instanceof com.google.android.systemui.smartspace.NextClockAlarmController$calculateNextClockAlarm$1
            if (r4 == 0) goto L_0x001b
            r4 = r2
            com.google.android.systemui.smartspace.NextClockAlarmController$calculateNextClockAlarm$1 r4 = (com.google.android.systemui.smartspace.NextClockAlarmController$calculateNextClockAlarm$1) r4
            int r5 = r4.label
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = r5 & r6
            if (r7 == 0) goto L_0x001b
            int r5 = r5 - r6
            r4.label = r5
            goto L_0x0020
        L_0x001b:
            com.google.android.systemui.smartspace.NextClockAlarmController$calculateNextClockAlarm$1 r4 = new com.google.android.systemui.smartspace.NextClockAlarmController$calculateNextClockAlarm$1
            r4.<init>(r0, r2)
        L_0x0020:
            java.lang.Object r2 = r4.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r5 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r6 = r4.label
            java.lang.String r7 = ""
            r8 = -1
            r10 = 2
            r11 = 1
            if (r6 == 0) goto L_0x0064
            if (r6 == r11) goto L_0x0053
            if (r6 != r10) goto L_0x004b
            java.lang.Object r0 = r4.L$3
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref$ObjectRef) r0
            java.lang.Object r1 = r4.L$2
            kotlin.jvm.internal.Ref$LongRef r1 = (kotlin.jvm.internal.Ref$LongRef) r1
            java.lang.Object r6 = r4.L$1
            androidx.appsearch.app.SearchResults r6 = (androidx.appsearch.app.SearchResults) r6
            java.lang.Object r12 = r4.L$0
            com.google.android.systemui.smartspace.NextClockAlarmController r12 = (com.google.android.systemui.smartspace.NextClockAlarmController) r12
            kotlin.ResultKt.throwOnFailure(r2)
            r13 = r12
            r12 = r0
            r0 = r2
            r2 = r10
            goto L_0x01cc
        L_0x004b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0053:
            java.lang.Object r0 = r4.L$1
            androidx.appsearch.app.SearchResults r0 = (androidx.appsearch.app.SearchResults) r0
            java.lang.Object r1 = r4.L$0
            com.google.android.systemui.smartspace.NextClockAlarmController r1 = (com.google.android.systemui.smartspace.NextClockAlarmController) r1
            kotlin.ResultKt.throwOnFailure(r2)
            r20 = r1
            r1 = r0
            r0 = r20
            goto L_0x0078
        L_0x0064:
            kotlin.ResultKt.throwOnFailure(r2)
            r4.L$0 = r0
            r4.L$1 = r1
            r4.label = r11
            com.google.android.systemui.smartspace.AppSearchController r2 = r0.alarmAppSearchController
            com.google.android.systemui.smartspace.AlarmAppSearchController r2 = (com.google.android.systemui.smartspace.AlarmAppSearchController) r2
            java.lang.Object r2 = r2.getNextPageSearchResults(r1, r4)
            if (r2 != r5) goto L_0x0078
            return r5
        L_0x0078:
            java.util.List r2 = (java.util.List) r2
            kotlin.jvm.internal.Ref$LongRef r6 = new kotlin.jvm.internal.Ref$LongRef
            r6.<init>()
            r6.element = r8
            kotlin.jvm.internal.Ref$ObjectRef r12 = new kotlin.jvm.internal.Ref$ObjectRef
            r12.<init>()
            r0.nextAlarmDetailInfo = r7
            r13 = r0
            r20 = r6
            r6 = r1
            r1 = r20
        L_0x008e:
            boolean r0 = r2.isEmpty()
            r0 = r0 ^ r11
            if (r0 == 0) goto L_0x01d5
            java.util.Iterator r2 = r2.iterator()
        L_0x0099:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x01b6
            java.lang.Object r0 = r2.next()
            androidx.appsearch.app.SearchResult r0 = (androidx.appsearch.app.SearchResult) r0
            androidx.appsearch.app.GenericDocument r14 = r0.mDocument
            if (r14 != 0) goto L_0x00bb
            androidx.appsearch.app.GenericDocument r14 = new androidx.appsearch.app.GenericDocument
            android.os.Bundle r15 = r0.mBundle
            java.lang.String r8 = "document"
            android.os.Bundle r8 = r15.getBundle(r8)
            r8.getClass()
            r14.<init>(r8)
            r0.mDocument = r14
        L_0x00bb:
            androidx.appsearch.app.GenericDocument r0 = r0.mDocument
            java.lang.String r9 = r0.mSchemaType     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r14 = "builtin:Alarm"
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r14)     // Catch:{ Exception -> 0x00d0 }
            if (r9 == 0) goto L_0x00d2
            java.lang.Class<androidx.appsearch.builtintypes.Alarm> r9 = androidx.appsearch.builtintypes.Alarm.class
            java.lang.Object r0 = r0.toDocumentClass(r9)     // Catch:{ Exception -> 0x00d0 }
            androidx.appsearch.builtintypes.Alarm r0 = (androidx.appsearch.builtintypes.Alarm) r0     // Catch:{ Exception -> 0x00d0 }
            goto L_0x00d3
        L_0x00d0:
            r0 = move-exception
            goto L_0x00d5
        L_0x00d2:
            r0 = 0
        L_0x00d3:
            r9 = r0
            goto L_0x00db
        L_0x00d5:
            java.lang.String r9 = "Failed to convert document to Alarm"
            android.util.Log.w(r3, r9, r0)
            r9 = 0
        L_0x00db:
            if (r9 == 0) goto L_0x01b2
            boolean r0 = r9.mEnabled
            if (r0 == 0) goto L_0x01a8
            androidx.appsearch.builtintypes.AlarmInstance r14 = r9.mNextInstance
            if (r14 == 0) goto L_0x00e8
            java.lang.String r0 = r14.mScheduledTime
            goto L_0x00e9
        L_0x00e8:
            r0 = 0
        L_0x00e9:
            if (r0 == 0) goto L_0x011c
            java.text.SimpleDateFormat r15 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x010a }
            java.lang.String r8 = "yyyy-MM-dd'T'HH:mm"
            java.util.Locale r10 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x010a }
            r15.<init>(r8, r10)     // Catch:{ Exception -> 0x010a }
            java.util.Date r0 = r15.parse(r0)     // Catch:{ Exception -> 0x010a }
            if (r0 == 0) goto L_0x011c
            java.util.Calendar r8 = java.util.Calendar.getInstance()     // Catch:{ Exception -> 0x010a }
            r8.setTime(r0)     // Catch:{ Exception -> 0x010a }
            long r16 = r8.getTimeInMillis()     // Catch:{ Exception -> 0x010a }
            r18 = r16
            goto L_0x011e
        L_0x010a:
            r0 = move-exception
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r10 = "Failed to convert date to milliseconds: "
            r8.<init>(r10)
            r8.append(r0)
            java.lang.String r0 = r8.toString()
            android.util.Log.e(r3, r0)
        L_0x011c:
            r18 = -1
        L_0x011e:
            if (r14 == 0) goto L_0x0123
            java.lang.String r0 = r14.mScheduledTime
            goto L_0x0124
        L_0x0123:
            r0 = 0
        L_0x0124:
            int[] r8 = r9.mDaysOfWeek
            if (r8 == 0) goto L_0x015c
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r7)
            int r14 = r8.length
            r15 = 0
            r16 = r15
        L_0x0134:
            if (r15 >= r14) goto L_0x0152
            r17 = r8[r15]
            r22 = r2
            int r2 = r16 + 1
            if (r2 <= r11) goto L_0x0143
            java.lang.String r11 = ", "
            r10.append(r11)
        L_0x0143:
            java.lang.String r11 = java.lang.String.valueOf(r17)
            r10.append(r11)
            int r15 = r15 + 1
            r16 = r2
            r11 = 1
            r2 = r22
            goto L_0x0134
        L_0x0152:
            r22 = r2
            r10.append(r7)
            java.lang.String r8 = r10.toString()
            goto L_0x015f
        L_0x015c:
            r22 = r2
            r8 = 0
        L_0x015f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r10 = "Alarm id="
            r2.<init>(r10)
            java.lang.String r9 = r9.mId
            r2.append(r9)
            java.lang.String r9 = ", nextTime="
            r2.append(r9)
            r2.append(r0)
            java.lang.String r0 = ", nextTimeInMillis="
            r2.append(r0)
            r9 = r18
            r2.append(r9)
            java.lang.String r0 = ", days=["
            r2.append(r0)
            r2.append(r8)
            java.lang.String r0 = "]"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r12.element = r0
            android.util.Log.d(r3, r0)
            long r14 = r1.element
            r17 = 0
            int r0 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r0 < 0) goto L_0x019f
            int r0 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r0 <= 0) goto L_0x01aa
        L_0x019f:
            r1.element = r9
            java.lang.Object r0 = r12.element
            java.lang.String r0 = (java.lang.String) r0
            r13.nextAlarmDetailInfo = r0
            goto L_0x01aa
        L_0x01a8:
            r22 = r2
        L_0x01aa:
            r2 = r22
            r8 = -1
            r10 = 2
            r11 = 1
            goto L_0x0099
        L_0x01b2:
            r8 = -1
            goto L_0x0099
        L_0x01b6:
            com.google.android.systemui.smartspace.AppSearchController r0 = r13.alarmAppSearchController
            r4.L$0 = r13
            r4.L$1 = r6
            r4.L$2 = r1
            r4.L$3 = r12
            r2 = 2
            r4.label = r2
            com.google.android.systemui.smartspace.AlarmAppSearchController r0 = (com.google.android.systemui.smartspace.AlarmAppSearchController) r0
            java.lang.Object r0 = r0.getNextPageSearchResults(r6, r4)
            if (r0 != r5) goto L_0x01cc
            return r5
        L_0x01cc:
            java.util.List r0 = (java.util.List) r0
            r10 = r2
            r8 = -1
            r11 = 1
            r2 = r0
            goto L_0x008e
        L_0x01d5:
            long r0 = r1.element
            java.lang.Long r2 = new java.lang.Long
            r2.<init>(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.NextClockAlarmController.calculateNextClockAlarm(androidx.appsearch.app.SearchResults, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        int userId = ((UserTrackerImpl) this.userTracker).getUserId();
        printWriter.println("  userId=" + userId);
        Context context2 = this.context;
        if (context2 == null) {
            context2 = null;
        }
        printWriter.println("  context=" + context2);
        printWriter.println("  alarmAppSearchController=" + this.alarmAppSearchController);
        long j = this.nextAlarm;
        printWriter.println("  nextClockAlarm=" + j);
        String str = this.nextAlarmDetailInfo;
        printWriter.println("  nextAlarmDetailInfo=" + str);
        int size = ((ArrayList) this.changeCallbacks).size();
        printWriter.println("  callback size=" + size);
    }

    public final boolean isUserUnlocked$1() {
        UserManager userManager = (UserManager) ((UserTrackerImpl) this.userTracker).getUserContext().getSystemService(UserManager.class);
        if (userManager != null && userManager.isUserUnlocked()) {
            return true;
        }
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.userUnlockReceiver, new IntentFilter("android.intent.action.USER_UNLOCKED"), this.mainExecutor, UserHandle.ALL, 0, (String) null, 48);
        return false;
    }

    public final void removeCallback(Object obj) {
        this.changeCallbacks.remove((KeyguardZenAlarmViewController$nextAlarmCallback$1) obj);
    }

    public final void updateSession(Context context2) {
        this.context = context2;
        StandaloneCoroutine standaloneCoroutine = this.updateSessionJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel((CancellationException) null);
        }
        this.updateSessionJob = BuildersKt.launch$default(this.backgroundScope, (CoroutineContext) null, (CoroutineStart) null, new NextClockAlarmController$updateSession$1(this, (Continuation) null), 3);
    }
}
