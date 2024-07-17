package com.google.android.systemui.elmyra;

import android.app.StatsManager;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class WestworldLogger$$ExternalSyntheticLambda0 implements StatsManager.StatsPullAtomCallback {
    public final /* synthetic */ WestworldLogger f$0;

    public /* synthetic */ WestworldLogger$$ExternalSyntheticLambda0(WestworldLogger westworldLogger) {
        this.f$0 = westworldLogger;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        r1 = r9.mSnapshotController;
        r1.getClass();
        r3 = new com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$SnapshotHeader();
        r3.gestureType = 4;
        r3.identifier = 0;
        r1 = r1.mHandler;
        r1.sendMessage(r1.obtainMessage(1, r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3 = java.lang.System.currentTimeMillis();
        r9.mCountDownLatch.await(50, java.util.concurrent.TimeUnit.MILLISECONDS);
        android.util.Log.d("Elmyra/Logger", "Snapshot took " + java.lang.Long.toString(java.lang.System.currentTimeMillis() - r3) + " milliseconds.");
        r0 = r9.mMutex;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007a, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007d, code lost:
        if (r9.mSnapshot == null) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0081, code lost:
        if (r9.mChassis != null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0084, code lost:
        r2 = new com.google.android.systemui.elmyra.proto.nano.ElmyraAtoms$ElmyraSnapshot();
        r3 = r9.mGestureConfiguration.getSensitivity();
        r4 = r9.mSnapshot;
        r4.sensitivitySetting = r3;
        r2.snapshot = r4;
        r2.chassis = r9.mChassis;
        r11.add(android.util.StatsEvent.newBuilder().setAtomId(r10).writeByteArray(com.google.protobuf.nano.MessageNano.toByteArray(r2.snapshot)).writeByteArray(com.google.protobuf.nano.MessageNano.toByteArray(r2.chassis)).build());
        r9.mSnapshot = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00be, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c0, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c2, code lost:
        r9.mCountDownLatch = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00c4, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        throw r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c8, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ca, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00cc, code lost:
        android.util.Log.d("Elmyra/Logger", r10.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d6, code lost:
        android.util.Log.d("Elmyra/Logger", r10.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        return 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int onPullAtom(int r10, java.util.List r11) {
        /*
            r9 = this;
            com.google.android.systemui.elmyra.WestworldLogger r9 = r9.f$0
            r9.getClass()
            java.lang.String r0 = "Elmyra/Logger"
            java.lang.String r1 = "Receiving pull request from statsd."
            android.util.Log.d(r0, r1)
            java.lang.String r0 = "Snapshot took "
            com.google.android.systemui.elmyra.SnapshotController r1 = r9.mSnapshotController
            r2 = 1
            if (r1 != 0) goto L_0x001c
            java.lang.String r9 = "Elmyra/Logger"
            java.lang.String r10 = "Snapshot Controller is null, returning."
            android.util.Log.d(r9, r10)
            goto L_0x00e8
        L_0x001c:
            java.lang.Object r1 = r9.mMutex
            monitor-enter(r1)
            java.util.concurrent.CountDownLatch r3 = r9.mCountDownLatch     // Catch:{ all -> 0x0026 }
            if (r3 == 0) goto L_0x0029
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            goto L_0x00e8
        L_0x0026:
            r9 = move-exception
            goto L_0x00ec
        L_0x0029:
            java.util.concurrent.CountDownLatch r3 = new java.util.concurrent.CountDownLatch     // Catch:{ all -> 0x0026 }
            r3.<init>(r2)     // Catch:{ all -> 0x0026 }
            r9.mCountDownLatch = r3     // Catch:{ all -> 0x0026 }
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            com.google.android.systemui.elmyra.SnapshotController r1 = r9.mSnapshotController
            r1.getClass()
            com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$SnapshotHeader r3 = new com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$SnapshotHeader
            r3.<init>()
            r4 = 4
            r3.gestureType = r4
            r4 = 0
            r3.identifier = r4
            com.google.android.systemui.elmyra.SnapshotController$1 r1 = r1.mHandler
            android.os.Message r3 = r1.obtainMessage(r2, r3)
            r1.sendMessage(r3)
            r1 = 0
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            java.util.concurrent.CountDownLatch r5 = r9.mCountDownLatch     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            r7 = 50
            r5.await(r7, r6)     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            java.lang.String r5 = "Elmyra/Logger"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            r6.<init>(r0)     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            long r7 = r7 - r3
            java.lang.String r0 = java.lang.Long.toString(r7)     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            r6.append(r0)     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            java.lang.String r0 = " milliseconds."
            r6.append(r0)     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            java.lang.String r0 = r6.toString()     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            android.util.Log.d(r5, r0)     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            java.lang.Object r0 = r9.mMutex     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
            com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Snapshot r3 = r9.mSnapshot     // Catch:{ all -> 0x00c0 }
            if (r3 == 0) goto L_0x00c2
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis r3 = r9.mChassis     // Catch:{ all -> 0x00c0 }
            if (r3 != 0) goto L_0x0084
            goto L_0x00c2
        L_0x0084:
            com.google.android.systemui.elmyra.proto.nano.ElmyraAtoms$ElmyraSnapshot r2 = new com.google.android.systemui.elmyra.proto.nano.ElmyraAtoms$ElmyraSnapshot     // Catch:{ all -> 0x00c0 }
            r2.<init>()     // Catch:{ all -> 0x00c0 }
            com.google.android.systemui.elmyra.sensors.config.GestureConfiguration r3 = r9.mGestureConfiguration     // Catch:{ all -> 0x00c0 }
            float r3 = r3.getSensitivity()     // Catch:{ all -> 0x00c0 }
            com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Snapshot r4 = r9.mSnapshot     // Catch:{ all -> 0x00c0 }
            r4.sensitivitySetting = r3     // Catch:{ all -> 0x00c0 }
            r2.snapshot = r4     // Catch:{ all -> 0x00c0 }
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis r3 = r9.mChassis     // Catch:{ all -> 0x00c0 }
            r2.chassis = r3     // Catch:{ all -> 0x00c0 }
            android.util.StatsEvent$Builder r3 = android.util.StatsEvent.newBuilder()     // Catch:{ all -> 0x00c0 }
            android.util.StatsEvent$Builder r10 = r3.setAtomId(r10)     // Catch:{ all -> 0x00c0 }
            com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Snapshot r3 = r2.snapshot     // Catch:{ all -> 0x00c0 }
            byte[] r3 = com.google.protobuf.nano.MessageNano.toByteArray(r3)     // Catch:{ all -> 0x00c0 }
            android.util.StatsEvent$Builder r10 = r10.writeByteArray(r3)     // Catch:{ all -> 0x00c0 }
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis r2 = r2.chassis     // Catch:{ all -> 0x00c0 }
            byte[] r2 = com.google.protobuf.nano.MessageNano.toByteArray(r2)     // Catch:{ all -> 0x00c0 }
            android.util.StatsEvent$Builder r10 = r10.writeByteArray(r2)     // Catch:{ all -> 0x00c0 }
            android.util.StatsEvent r10 = r10.build()     // Catch:{ all -> 0x00c0 }
            r11.add(r10)     // Catch:{ all -> 0x00c0 }
            r9.mSnapshot = r1     // Catch:{ all -> 0x00c0 }
            monitor-exit(r0)     // Catch:{ all -> 0x00c0 }
            goto L_0x00df
        L_0x00c0:
            r10 = move-exception
            goto L_0x00c6
        L_0x00c2:
            r9.mCountDownLatch = r1     // Catch:{ all -> 0x00c0 }
            monitor-exit(r0)     // Catch:{ all -> 0x00c0 }
            goto L_0x00e8
        L_0x00c6:
            monitor-exit(r0)     // Catch:{ all -> 0x00c0 }
            throw r10     // Catch:{ InterruptedException -> 0x00ca, IllegalMonitorStateException -> 0x00c8 }
        L_0x00c8:
            r10 = move-exception
            goto L_0x00cc
        L_0x00ca:
            r10 = move-exception
            goto L_0x00d6
        L_0x00cc:
            java.lang.String r11 = "Elmyra/Logger"
            java.lang.String r10 = r10.getMessage()
            android.util.Log.d(r11, r10)
            goto L_0x00df
        L_0x00d6:
            java.lang.String r11 = "Elmyra/Logger"
            java.lang.String r10 = r10.getMessage()
            android.util.Log.d(r11, r10)
        L_0x00df:
            java.lang.Object r10 = r9.mMutex
            monitor-enter(r10)
            r9.mCountDownLatch = r1     // Catch:{ all -> 0x00e9 }
            r9.mSnapshot = r1     // Catch:{ all -> 0x00e9 }
            monitor-exit(r10)     // Catch:{ all -> 0x00e9 }
            r2 = 0
        L_0x00e8:
            return r2
        L_0x00e9:
            r9 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x00e9 }
            throw r9
        L_0x00ec:
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.elmyra.WestworldLogger$$ExternalSyntheticLambda0.onPullAtom(int, java.util.List):int");
    }
}
