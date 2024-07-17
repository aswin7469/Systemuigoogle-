package com.google.android.systemui.dreamliner;

import android.os.ResultReceiver;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$getWpcDigests$1 extends Lambda implements Function1 {
    final /* synthetic */ ResultReceiver $resultReceiver;
    final /* synthetic */ byte $slotMask;
    final /* synthetic */ WirelessChargerCommander this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$getWpcDigests$1(byte b, ResultReceiver resultReceiver, WirelessChargerCommander wirelessChargerCommander) {
        super(1);
        this.$slotMask = b;
        this.$resultReceiver = resultReceiver;
        this.this$0 = wirelessChargerCommander;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0060 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke(java.lang.Object r10) {
        /*
            r9 = this;
            com.google.android.systemui.dreamliner.WirelessCharger r10 = (com.google.android.systemui.dreamliner.WirelessCharger) r10
            byte r0 = r9.$slotMask
            android.os.ResultReceiver r1 = r9.$resultReceiver
            com.google.android.systemui.dreamliner.WirelessChargerCommander r9 = r9.this$0
            com.google.android.systemui.dreamliner.WirelessChargerImpl r10 = (com.google.android.systemui.dreamliner.WirelessChargerImpl) r10
            boolean r2 = r10.initHALInterface()
            if (r2 != 0) goto L_0x0012
            goto L_0x00b8
        L_0x0012:
            r2 = 0
            r3 = 0
            vendor.google.wireless_charger.IWirelessCharger r10 = r10.mWirelessCharger     // Catch:{ Exception -> 0x0038 }
            vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy r10 = (vendor.google.wireless_charger.IWirelessCharger.Stub.Proxy) r10     // Catch:{ Exception -> 0x0038 }
            vendor.google.wireless_charger.WpcAuthDigests r10 = r10.getWpcAuthDigests(r0)     // Catch:{ Exception -> 0x0038 }
            byte r0 = r10.slotPopulatedMask     // Catch:{ Exception -> 0x0038 }
            byte r4 = r10.slotReturnedMask     // Catch:{ Exception -> 0x0034 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Exception -> 0x0031 }
            byte[][] r6 = r10.digests     // Catch:{ Exception -> 0x0031 }
            int r6 = r6.length     // Catch:{ Exception -> 0x0031 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x0031 }
            byte[][] r10 = r10.digests     // Catch:{ Exception -> 0x002f }
            java.util.Collections.addAll(r5, r10)     // Catch:{ Exception -> 0x002f }
            r6 = r3
            goto L_0x0057
        L_0x002f:
            r10 = move-exception
            goto L_0x003c
        L_0x0031:
            r10 = move-exception
            r5 = r2
            goto L_0x003c
        L_0x0034:
            r10 = move-exception
            r5 = r2
            r4 = r3
            goto L_0x003c
        L_0x0038:
            r10 = move-exception
            r5 = r2
            r0 = r3
            r4 = r0
        L_0x003c:
            int r6 = com.google.android.systemui.dreamliner.WirelessChargerImpl.mapError(r10)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "get wpc digests fail: "
            r7.<init>(r8)
            java.lang.String r10 = r10.getMessage()
            r7.append(r10)
            java.lang.String r10 = r7.toString()
            java.lang.String r7 = "Dreamliner-WLC_HAL"
            android.util.Log.i(r7, r10)
        L_0x0057:
            java.lang.String r10 = "GWAD() result: "
            java.lang.String r7 = "WirelessChargerCommander"
            androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r10, r7, r6)
            if (r6 != 0) goto L_0x00b5
            if (r5 != 0) goto L_0x0063
            goto L_0x00b5
        L_0x0063:
            java.lang.String r10 = "GWAD() response: pm="
            java.lang.String r2 = ", rm="
            java.lang.String r6 = ", d="
            java.lang.StringBuilder r10 = androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(r10, r0, r2, r4, r6)
            r10.append(r5)
            java.lang.String r10 = r10.toString()
            android.util.Log.d(r7, r10)
            r9.getClass()
            android.os.Bundle r9 = new android.os.Bundle
            r9.<init>()
            java.lang.String r10 = "slot_populated_mask"
            r9.putByte(r10, r0)
            java.lang.String r10 = "slot_returned_mask"
            r9.putByte(r10, r4)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.Iterator r0 = r5.iterator()
        L_0x0092:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00ac
            java.lang.Object r2 = r0.next()
            byte[] r2 = (byte[]) r2
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>()
            java.lang.String r5 = "wpc_digest"
            r4.putByteArray(r5, r2)
            r10.add(r4)
            goto L_0x0092
        L_0x00ac:
            java.lang.String r0 = "wpc_digests"
            r9.putParcelableArrayList(r0, r10)
            r1.send(r3, r9)
            goto L_0x00b8
        L_0x00b5:
            r1.send(r6, r2)
        L_0x00b8:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.WirelessChargerCommander$getWpcDigests$1.invoke(java.lang.Object):java.lang.Object");
    }
}
