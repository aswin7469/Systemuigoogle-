package com.google.android.systemui.dreamliner;

import com.google.android.systemui.dreamliner.DockObserver;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class DockObserver$IsDockPresentCallback$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DockObserver.IsDockPresentCallback f$0;
    public final /* synthetic */ byte f$1;
    public final /* synthetic */ byte f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ DockObserver$IsDockPresentCallback$$ExternalSyntheticLambda0(DockObserver.IsDockPresentCallback isDockPresentCallback, byte b, byte b2, int i) {
        this.f$0 = isDockPresentCallback;
        this.f$1 = b;
        this.f$2 = b2;
        this.f$3 = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r8 = this;
            com.google.android.systemui.dreamliner.DockObserver$IsDockPresentCallback r0 = r8.f$0
            byte r1 = r8.f$1
            byte r2 = r8.f$2
            int r8 = r8.f$3
            com.google.android.systemui.dreamliner.DockObserver r3 = com.google.android.systemui.dreamliner.DockObserver.this
            android.content.Context r0 = r0.mContext
            java.lang.String r4 = "Unable to bind Dreamliner service: "
            monitor-enter(r3)
            com.google.android.systemui.dreamliner.DockObserver$DreamlinerServiceConn r5 = r3.mDreamlinerServiceConn     // Catch:{ all -> 0x0074 }
            if (r5 == 0) goto L_0x0016
            monitor-exit(r3)
            goto L_0x0095
        L_0x0016:
            r3.addInterruptionSuppressor()     // Catch:{ all -> 0x0074 }
            r5 = 1
            com.google.android.systemui.dreamliner.DockObserver.notifyForceEnabledAmbientDisplay(r5)     // Catch:{ all -> 0x0074 }
            com.google.android.systemui.dreamliner.DockObserver$DreamlinerBroadcastReceiver r6 = r3.mDreamlinerReceiver     // Catch:{ all -> 0x0074 }
            r6.registerReceiver(r0)     // Catch:{ all -> 0x0074 }
            android.content.Intent r6 = new android.content.Intent     // Catch:{ all -> 0x0074 }
            java.lang.String r7 = "com.google.android.apps.dreamliner.START"
            r6.<init>(r7)     // Catch:{ all -> 0x0074 }
            java.lang.String r7 = "com.google.android.apps.dreamliner/.DreamlinerControlService"
            android.content.ComponentName r7 = android.content.ComponentName.unflattenFromString(r7)     // Catch:{ all -> 0x0074 }
            r6.setComponent(r7)     // Catch:{ all -> 0x0074 }
            java.lang.String r7 = "type"
            r6.putExtra(r7, r1)     // Catch:{ all -> 0x0074 }
            java.lang.String r1 = "orientation"
            r6.putExtra(r1, r2)     // Catch:{ all -> 0x0074 }
            java.lang.String r1 = "id"
            r6.putExtra(r1, r8)     // Catch:{ all -> 0x0074 }
            java.lang.String r8 = "occluded"
            com.android.systemui.statusbar.policy.KeyguardStateController r1 = r3.mKeyguardStateController     // Catch:{ all -> 0x0074 }
            com.android.systemui.statusbar.policy.KeyguardStateControllerImpl r1 = (com.android.systemui.statusbar.policy.KeyguardStateControllerImpl) r1     // Catch:{ all -> 0x0074 }
            boolean r1 = r1.mOccluded     // Catch:{ all -> 0x0074 }
            r6.putExtra(r8, r1)     // Catch:{ all -> 0x0074 }
            com.google.android.systemui.dreamliner.DockObserver$DreamlinerServiceConn r8 = new com.google.android.systemui.dreamliner.DockObserver$DreamlinerServiceConn     // Catch:{ all -> 0x0074 }
            r8.<init>(r0)     // Catch:{ all -> 0x0074 }
            r3.mDreamlinerServiceConn = r8     // Catch:{ all -> 0x0074 }
            android.os.UserHandle r1 = new android.os.UserHandle     // Catch:{ SecurityException -> 0x0076 }
            com.android.systemui.settings.UserTracker r2 = r3.mUserTracker     // Catch:{ SecurityException -> 0x0076 }
            com.android.systemui.settings.UserTrackerImpl r2 = (com.android.systemui.settings.UserTrackerImpl) r2     // Catch:{ SecurityException -> 0x0076 }
            int r2 = r2.getUserId()     // Catch:{ SecurityException -> 0x0076 }
            r1.<init>(r2)     // Catch:{ SecurityException -> 0x0076 }
            boolean r8 = r0.bindServiceAsUser(r6, r8, r5, r1)     // Catch:{ SecurityException -> 0x0076 }
            if (r8 == 0) goto L_0x0080
            com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda0 r8 = new com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda0     // Catch:{ all -> 0x0074 }
            r1 = 2
            r8.<init>(r1, r3, r0)     // Catch:{ all -> 0x0074 }
            com.android.systemui.util.concurrency.DelayableExecutor r0 = r3.mMainExecutor     // Catch:{ all -> 0x0074 }
            com.android.systemui.util.concurrency.ExecutorImpl r0 = (com.android.systemui.util.concurrency.ExecutorImpl) r0     // Catch:{ all -> 0x0074 }
            r0.execute(r8)     // Catch:{ all -> 0x0074 }
            goto L_0x0094
        L_0x0074:
            r8 = move-exception
            goto L_0x0096
        L_0x0076:
            r8 = move-exception
            java.lang.String r0 = "DLObserver"
            java.lang.String r1 = r8.getMessage()     // Catch:{ all -> 0x0074 }
            android.util.Log.e(r0, r1, r8)     // Catch:{ all -> 0x0074 }
        L_0x0080:
            r8 = 0
            r3.mDreamlinerServiceConn = r8     // Catch:{ all -> 0x0074 }
            java.lang.String r8 = "DLObserver"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
            r0.<init>(r4)     // Catch:{ all -> 0x0074 }
            r0.append(r6)     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0074 }
            android.util.Log.w(r8, r0)     // Catch:{ all -> 0x0074 }
        L_0x0094:
            monitor-exit(r3)
        L_0x0095:
            return
        L_0x0096:
            monitor-exit(r3)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockObserver$IsDockPresentCallback$$ExternalSyntheticLambda0.run():void");
    }
}
