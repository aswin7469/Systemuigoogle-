package com.google.android.systemui.dreamliner;

import android.os.ResultReceiver;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$getWpcChallengeResponse$1 extends Lambda implements Function1 {
    final /* synthetic */ byte[] $nonce;
    final /* synthetic */ ResultReceiver $resultReceiver;
    final /* synthetic */ byte $slot;
    final /* synthetic */ WirelessChargerCommander this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$getWpcChallengeResponse$1(byte b, byte[] bArr, ResultReceiver resultReceiver, WirelessChargerCommander wirelessChargerCommander) {
        super(1);
        this.$slot = b;
        this.$nonce = bArr;
        this.$resultReceiver = resultReceiver;
        this.this$0 = wirelessChargerCommander;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x006b A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke(java.lang.Object r11) {
        /*
            r10 = this;
            com.google.android.systemui.dreamliner.WirelessCharger r11 = (com.google.android.systemui.dreamliner.WirelessCharger) r11
            byte r0 = r10.$slot
            byte[] r1 = r10.$nonce
            android.os.ResultReceiver r2 = r10.$resultReceiver
            com.google.android.systemui.dreamliner.WirelessChargerCommander r10 = r10.this$0
            com.google.android.systemui.dreamliner.WirelessChargerImpl r11 = (com.google.android.systemui.dreamliner.WirelessChargerImpl) r11
            boolean r3 = r11.initHALInterface()
            if (r3 != 0) goto L_0x0014
            goto L_0x00c4
        L_0x0014:
            r3 = 0
            r4 = 0
            vendor.google.wireless_charger.IWirelessCharger r11 = r11.mWirelessCharger     // Catch:{ Exception -> 0x0041 }
            vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy r11 = (vendor.google.wireless_charger.IWirelessCharger.Stub.Proxy) r11     // Catch:{ Exception -> 0x0041 }
            vendor.google.wireless_charger.WpcAuthChallengeResponse r11 = r11.getWpcAuthChallengeResponse(r0, r1)     // Catch:{ Exception -> 0x0041 }
            byte r0 = r11.maxProtocolVersion     // Catch:{ Exception -> 0x0041 }
            byte r1 = r11.slotPopulatedMask     // Catch:{ Exception -> 0x003c }
            byte r5 = r11.certificateChainHashLsb     // Catch:{ Exception -> 0x0038 }
            byte[] r6 = r11.signatureR     // Catch:{ Exception -> 0x0036 }
            java.util.ArrayList r6 = com.google.android.systemui.dreamliner.WirelessChargerImpl.convertPrimitiveArrayToArrayList(r6)     // Catch:{ Exception -> 0x0036 }
            byte[] r11 = r11.signatureS     // Catch:{ Exception -> 0x0032 }
            java.util.ArrayList r11 = com.google.android.systemui.dreamliner.WirelessChargerImpl.convertPrimitiveArrayToArrayList(r11)     // Catch:{ Exception -> 0x0032 }
            r7 = r4
            goto L_0x0062
        L_0x0032:
            r11 = move-exception
            goto L_0x0046
        L_0x0034:
            r6 = r3
            goto L_0x0046
        L_0x0036:
            r11 = move-exception
            goto L_0x0034
        L_0x0038:
            r11 = move-exception
            r6 = r3
            r5 = r4
            goto L_0x0046
        L_0x003c:
            r11 = move-exception
            r6 = r3
            r1 = r4
        L_0x003f:
            r5 = r1
            goto L_0x0046
        L_0x0041:
            r11 = move-exception
            r6 = r3
            r0 = r4
            r1 = r0
            goto L_0x003f
        L_0x0046:
            int r7 = com.google.android.systemui.dreamliner.WirelessChargerImpl.mapError(r11)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "get wpc challenge response fail: "
            r8.<init>(r9)
            java.lang.String r11 = r11.getMessage()
            r8.append(r11)
            java.lang.String r11 = r8.toString()
            java.lang.String r8 = "Dreamliner-WLC_HAL"
            android.util.Log.i(r8, r11)
            r11 = r3
        L_0x0062:
            java.lang.String r8 = "GWACR() result: "
            java.lang.String r9 = "WirelessChargerCommander"
            androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r8, r9, r7)
            if (r7 != 0) goto L_0x00c1
            if (r6 == 0) goto L_0x00c1
            if (r11 != 0) goto L_0x0070
            goto L_0x00c1
        L_0x0070:
            java.lang.String r3 = "GWACR() response: mpv="
            java.lang.String r7 = ", pm="
            java.lang.String r8 = ", chl="
            java.lang.StringBuilder r3 = androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(r3, r0, r7, r1, r8)
            r3.append(r5)
            java.lang.String r7 = ", rv="
            r3.append(r7)
            r3.append(r6)
            java.lang.String r7 = ", sv="
            r3.append(r7)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r9, r3)
            r10.getClass()
            android.os.Bundle r10 = new android.os.Bundle
            r10.<init>()
            java.lang.String r3 = "max_protocol_ver"
            r10.putByte(r3, r0)
            java.lang.String r0 = "slot_populated_mask"
            r10.putByte(r0, r1)
            java.lang.String r0 = "cert_lsb"
            r10.putByte(r0, r5)
            byte[] r0 = kotlin.collections.CollectionsKt.toByteArray(r6)
            java.lang.String r1 = "signature_r"
            r10.putByteArray(r1, r0)
            byte[] r11 = kotlin.collections.CollectionsKt.toByteArray(r11)
            java.lang.String r0 = "signature_s"
            r10.putByteArray(r0, r11)
            r2.send(r4, r10)
            goto L_0x00c4
        L_0x00c1:
            r2.send(r7, r3)
        L_0x00c4:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.WirelessChargerCommander$getWpcChallengeResponse$1.invoke(java.lang.Object):java.lang.Object");
    }
}
