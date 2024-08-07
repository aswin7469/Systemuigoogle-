package com.google.android.systemui.power.batteryevent.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public interface IBatteryEventService extends IInterface {
    void registerBatteryEventsCallback(IBatteryEventsListener iBatteryEventsListener, List list, SurfaceType surfaceType);

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class Stub extends Binder implements IBatteryEventService {
        public static final /* synthetic */ int $r8$clinit = 0;

        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class Proxy implements IBatteryEventService {
            public IBinder mRemote;

            public final IBinder asBinder() {
                return this.mRemote;
            }

            public final void registerBatteryEventsCallback(IBatteryEventsListener iBatteryEventsListener, List list, SurfaceType surfaceType) {
                Parcel obtain = Parcel.obtain(this.mRemote);
                try {
                    obtain.writeInterfaceToken("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService");
                    obtain.writeStrongInterface(iBatteryEventsListener);
                    obtain.writeTypedList(list, 0);
                    obtain.writeTypedObject(surfaceType, 0);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener} */
        /* JADX WARNING: type inference failed for: r2v3, types: [java.lang.Object, com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener$Stub$Proxy] */
        /* JADX WARNING: type inference failed for: r2v7, types: [java.lang.Object, com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener$Stub$Proxy] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r7, android.os.Parcel r8, android.os.Parcel r9, int r10) {
            /*
                r6 = this;
                java.lang.String r0 = "com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService"
                r1 = 1
                if (r7 < r1) goto L_0x000d
                r2 = 16777215(0xffffff, float:2.3509886E-38)
                if (r7 > r2) goto L_0x000d
                r8.enforceInterface(r0)
            L_0x000d:
                r2 = 1598968902(0x5f4e5446, float:1.4867585E19)
                if (r7 != r2) goto L_0x0016
                r9.writeString(r0)
                return r1
            L_0x0016:
                java.lang.String r0 = "com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener"
                r2 = 0
                if (r7 == r1) goto L_0x0176
                r3 = 2
                if (r7 == r3) goto L_0x014d
                r0 = 3
                java.lang.String r3 = ", className="
                java.lang.String r4 = "BatteryEventService"
                if (r7 == r0) goto L_0x00d1
                r0 = 4
                if (r7 == r0) goto L_0x0045
                r0 = 5
                if (r7 == r0) goto L_0x0030
                boolean r6 = super.onTransact(r7, r8, r9, r10)
                return r6
            L_0x0030:
                com.google.android.systemui.power.batteryevent.aidl.SurfaceType$CREATOR r6 = com.google.android.systemui.power.batteryevent.aidl.SurfaceType.CREATOR
                java.lang.Object r6 = r8.readTypedObject(r6)
                com.google.android.systemui.power.batteryevent.aidl.SurfaceType r6 = (com.google.android.systemui.power.batteryevent.aidl.SurfaceType) r6
                com.google.android.systemui.power.batteryevent.aidl.SurfaceAction$CREATOR r6 = com.google.android.systemui.power.batteryevent.aidl.SurfaceAction.CREATOR
                java.lang.Object r6 = r8.readTypedObject(r6)
                com.google.android.systemui.power.batteryevent.aidl.SurfaceAction r6 = (com.google.android.systemui.power.batteryevent.aidl.SurfaceAction) r6
                r8.enforceNoDataAvail()
                goto L_0x01a8
            L_0x0045:
                java.lang.String r7 = r8.readString()
                java.lang.String r9 = r8.readString()
                int r10 = r8.readInt()
                r8.enforceNoDataAvail()
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService$binder$1 r6 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService$binder$1) r6
                if (r7 == 0) goto L_0x00b8
                if (r9 != 0) goto L_0x005b
                goto L_0x00b8
            L_0x005b:
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService r8 = r6.this$0
                java.util.concurrent.CopyOnWriteArraySet r8 = r8.broadcastIntentBatteryEventsListener
                java.util.Iterator r8 = r8.iterator()
            L_0x0063:
                boolean r0 = r8.hasNext()
                if (r0 == 0) goto L_0x008d
                java.lang.Object r0 = r8.next()
                r3 = r0
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData r3 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsBroadcastData) r3
                android.content.ComponentName r5 = r3.componentName
                java.lang.String r5 = r5.getPackageName()
                boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r7)
                if (r5 == 0) goto L_0x0063
                android.content.ComponentName r5 = r3.componentName
                java.lang.String r5 = r5.getClassName()
                boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r9)
                if (r5 == 0) goto L_0x0063
                int r3 = r3.userId
                if (r3 != r10) goto L_0x0063
                r2 = r0
            L_0x008d:
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData r2 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsBroadcastData) r2
                if (r2 == 0) goto L_0x00a2
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService r6 = r6.this$0
                java.util.concurrent.CopyOnWriteArraySet r8 = r6.broadcastIntentBatteryEventsListener
                r8.remove(r2)
                androidx.collection.ArrayMap r6 = r6.batteryEventsBroadcastCache
                java.lang.String r8 = r2.indexKey
                java.lang.Object r6 = r6.remove(r8)
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r6 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.CachedBatteryEvents) r6
            L_0x00a2:
                java.lang.String r6 = "unregisterBatteryEventsUpdate:packageName: "
                java.lang.String r8 = ", className: "
                java.lang.String r0 = ", userId: "
                java.lang.StringBuilder r6 = com.android.keyguard.logging.KeyguardUpdateMonitorLogger$logServiceStateIntent$2$$ExternalSyntheticOutline0.m(r6, r7, r8, r9, r0)
                r6.append(r10)
                java.lang.String r6 = r6.toString()
                android.util.Log.i(r4, r6)
                goto L_0x01a8
            L_0x00b8:
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                java.lang.String r8 = "unregisterBatteryEventsUpdate failed. packageName="
                r6.<init>(r8)
                r6.append(r7)
                r6.append(r3)
                r6.append(r9)
                java.lang.String r6 = r6.toString()
                android.util.Log.w(r4, r6)
                goto L_0x01a8
            L_0x00d1:
                java.lang.String r7 = r8.readString()
                java.lang.String r9 = r8.readString()
                com.google.android.systemui.power.batteryevent.aidl.BatteryEventType$CREATOR r10 = com.google.android.systemui.power.batteryevent.aidl.BatteryEventType.CREATOR
                java.util.ArrayList r10 = r8.createTypedArrayList(r10)
                int r0 = r8.readInt()
                r8.enforceNoDataAvail()
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService$binder$1 r6 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService$binder$1) r6
                if (r7 == 0) goto L_0x0135
                if (r9 != 0) goto L_0x00ed
                goto L_0x0135
            L_0x00ed:
                if (r10 == 0) goto L_0x00fc
                boolean r8 = r10.isEmpty()
                if (r8 != r1) goto L_0x00fc
                java.lang.String r6 = "no battery events to subscribe"
                android.util.Log.w(r4, r6)
                goto L_0x01a8
            L_0x00fc:
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData r8 = new com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData
                android.content.ComponentName r2 = new android.content.ComponentName
                r2.<init>(r7, r9)
                if (r10 == 0) goto L_0x010b
                java.util.Set r10 = kotlin.collections.CollectionsKt___CollectionsKt.toSet(r10)
                if (r10 != 0) goto L_0x010d
            L_0x010b:
                kotlin.collections.EmptySet r10 = kotlin.collections.EmptySet.INSTANCE
            L_0x010d:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                r3.append(r7)
                java.lang.String r7 = "/"
                r3.append(r7)
                r3.append(r9)
                java.lang.String r7 = "-"
                r3.append(r7)
                r3.append(r0)
                java.lang.String r7 = r3.toString()
                r8.<init>(r2, r10, r0, r7)
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService r6 = r6.this$0
                java.util.concurrent.CopyOnWriteArraySet r6 = r6.broadcastIntentBatteryEventsListener
                r6.add(r8)
                goto L_0x01a8
            L_0x0135:
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                java.lang.String r8 = "registerBatteryEventsUpdate failed: packageName="
                r6.<init>(r8)
                r6.append(r7)
                r6.append(r3)
                r6.append(r9)
                java.lang.String r6 = r6.toString()
                android.util.Log.w(r4, r6)
                goto L_0x01a8
            L_0x014d:
                android.os.IBinder r7 = r8.readStrongBinder()
                if (r7 != 0) goto L_0x0154
                goto L_0x0169
            L_0x0154:
                android.os.IInterface r9 = r7.queryLocalInterface(r0)
                if (r9 == 0) goto L_0x0162
                boolean r10 = r9 instanceof com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener
                if (r10 == 0) goto L_0x0162
                r2 = r9
                com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener r2 = (com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener) r2
                goto L_0x0169
            L_0x0162:
                com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener$Stub$Proxy r2 = new com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener$Stub$Proxy
                r2.<init>()
                r2.mRemote = r7
            L_0x0169:
                r8.enforceNoDataAvail()
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService$binder$1 r6 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService$binder$1) r6
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService r6 = r6.this$0
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r6 = r6.aidlBatteryEventsCallbackListener
                r6.unregister((com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener) r2)
                goto L_0x01a8
            L_0x0176:
                android.os.IBinder r7 = r8.readStrongBinder()
                if (r7 != 0) goto L_0x017d
                goto L_0x0192
            L_0x017d:
                android.os.IInterface r9 = r7.queryLocalInterface(r0)
                if (r9 == 0) goto L_0x018b
                boolean r10 = r9 instanceof com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener
                if (r10 == 0) goto L_0x018b
                r2 = r9
                com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener r2 = (com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener) r2
                goto L_0x0192
            L_0x018b:
                com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener$Stub$Proxy r2 = new com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener$Stub$Proxy
                r2.<init>()
                r2.mRemote = r7
            L_0x0192:
                com.google.android.systemui.power.batteryevent.aidl.BatteryEventType$CREATOR r7 = com.google.android.systemui.power.batteryevent.aidl.BatteryEventType.CREATOR
                java.util.ArrayList r7 = r8.createTypedArrayList(r7)
                com.google.android.systemui.power.batteryevent.aidl.SurfaceType$CREATOR r9 = com.google.android.systemui.power.batteryevent.aidl.SurfaceType.CREATOR
                java.lang.Object r9 = r8.readTypedObject(r9)
                com.google.android.systemui.power.batteryevent.aidl.SurfaceType r9 = (com.google.android.systemui.power.batteryevent.aidl.SurfaceType) r9
                r8.enforceNoDataAvail()
                com.google.android.systemui.power.batteryevent.domain.BatteryEventService$binder$1 r6 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService$binder$1) r6
                r6.registerBatteryEventsCallback(r2, r7, r9)
            L_0x01a8:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        public final IBinder asBinder() {
            return this;
        }
    }
}
