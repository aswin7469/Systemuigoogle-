package com.google.android.systemui.dreamliner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class WirelessChargerCommander$commandReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ WirelessChargerCommander this$0;

    public WirelessChargerCommander$commandReceiver$1(WirelessChargerCommander wirelessChargerCommander) {
        this.this$0 = wirelessChargerCommander;
    }

    public final void onReceive(Context context, Intent intent) {
        String str;
        String str2;
        Intent intent2 = intent;
        if (intent2 != null) {
            str = intent.getAction();
        } else {
            str = null;
        }
        FragmentManagerViewModel$$ExternalSyntheticOutline0.m("onReceive(): ", str, "WirelessChargerCommander");
        if (intent2 != null) {
            str2 = intent.getAction();
        } else {
            str2 = null;
        }
        if (str2 != null) {
            switch (str2.hashCode()) {
                case -2133451883:
                    if (str2.equals("com.google.android.systemui.dreamliner.ACTION_GET_FAN_LEVEL")) {
                        WirelessChargerCommander wirelessChargerCommander = this.this$0;
                        wirelessChargerCommander.getClass();
                        Log.d("WirelessChargerCommander", "GFL()");
                        WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander.wirelessCharger, new WirelessChargerCommander$getFanLevel$1((ResultReceiver) intent2.getParcelableExtra("android.intent.extra.RESULT_RECEIVER"), wirelessChargerCommander));
                        return;
                    }
                    return;
                case -1863595884:
                    if (str2.equals("com.google.android.systemui.dreamliner.ACTION_SET_FEATURES")) {
                        WirelessChargerCommander wirelessChargerCommander2 = this.this$0;
                        wirelessChargerCommander2.getClass();
                        long longExtra = intent2.getLongExtra("charger_id", -1);
                        long longExtra2 = intent2.getLongExtra("charger_feature", -1);
                        ResultReceiver resultReceiver = (ResultReceiver) intent2.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                        if (resultReceiver != null) {
                            Log.d("WirelessChargerCommander", "SF(), c=" + longExtra + ", feature=" + longExtra2);
                            if (longExtra == -1 || longExtra2 == -1) {
                                resultReceiver.send(-1, (Bundle) null);
                                return;
                            }
                            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander2.wirelessCharger, new WirelessChargerCommander$setFeatures$1(longExtra, longExtra2, resultReceiver));
                            return;
                        }
                        return;
                    }
                    return;
                case -1627881412:
                    if (str2.equals("com.google.android.systemui.dreamliner.ACTION_SET_FAN")) {
                        WirelessChargerCommander wirelessChargerCommander3 = this.this$0;
                        wirelessChargerCommander3.getClass();
                        byte byteExtra = intent2.getByteExtra("fan_id", (byte) 0);
                        byte byteExtra2 = intent2.getByteExtra("fan_mode", (byte) 0);
                        int intExtra = intent2.getIntExtra("fan_rpm", -1);
                        StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m("SF(), i=", byteExtra, ", m=", byteExtra2, ", r=");
                        m.append(intExtra);
                        Log.d("WirelessChargerCommander", m.toString());
                        ResultReceiver resultReceiver2 = (ResultReceiver) intent2.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                        if (byteExtra2 == 1 && intExtra == -1) {
                            Log.e("WirelessChargerCommander", "Failed to get r.");
                            return;
                        }
                        WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander3.wirelessCharger, new WirelessChargerCommander$setFan$1(byteExtra, byteExtra2, intExtra, resultReceiver2));
                        return;
                    }
                    return;
                case -1616532553:
                    if (str2.equals("com.google.android.systemui.dreamliner.ACTION_GET_DOCK_INFO")) {
                        WirelessChargerCommander wirelessChargerCommander4 = this.this$0;
                        wirelessChargerCommander4.getClass();
                        Log.d("WirelessChargerCommander", "GI()");
                        ResultReceiver resultReceiver3 = (ResultReceiver) intent2.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                        if (resultReceiver3 != null) {
                            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander4.wirelessCharger, new WirelessChargerCommander$getInformation$1(resultReceiver3));
                            return;
                        }
                        return;
                    }
                    return;
                case -1598391011:
                    if (str2.equals("com.google.android.systemui.dreamliner.ACTION_KEY_EXCHANGE")) {
                        WirelessChargerCommander wirelessChargerCommander5 = this.this$0;
                        wirelessChargerCommander5.getClass();
                        Log.d("WirelessChargerCommander", "KE()");
                        ResultReceiver resultReceiver4 = (ResultReceiver) intent2.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                        if (resultReceiver4 != null) {
                            byte[] byteArrayExtra = intent2.getByteArrayExtra("public_key");
                            if (byteArrayExtra == null || byteArrayExtra.length == 0) {
                                resultReceiver4.send(-1, (Bundle) null);
                                return;
                            }
                            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander5.wirelessCharger, new WirelessChargerCommander$doKeyExchange$1(byteArrayExtra, resultReceiver4, wirelessChargerCommander5));
                            return;
                        }
                        return;
                    }
                    return;
                case -1458969207:
                    if (str2.equals("com.google.android.systemui.dreamliner.ACTION_CHALLENGE")) {
                        WirelessChargerCommander wirelessChargerCommander6 = this.this$0;
                        wirelessChargerCommander6.getClass();
                        Log.d("WirelessChargerCommander", "C()");
                        ResultReceiver resultReceiver5 = (ResultReceiver) intent2.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                        if (resultReceiver5 != null) {
                            byte byteExtra3 = intent2.getByteExtra("challenge_dock_id", (byte) -1);
                            byte[] byteArrayExtra2 = intent2.getByteArrayExtra("challenge_data");
                            if (byteArrayExtra2 == null || byteArrayExtra2.length == 0 || byteExtra3 < 0) {
                                resultReceiver5.send(-1, (Bundle) null);
                                return;
                            }
                            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander6.wirelessCharger, new WirelessChargerCommander$doChallenge$1(byteExtra3, byteArrayExtra2, resultReceiver5, wirelessChargerCommander6));
                            return;
                        }
                        return;
                    }
                    return;
                case -686255721:
                    if (str2.equals("com.google.android.systemui.dreamliner.ACTION_GET_WPC_DIGESTS")) {
                        WirelessChargerCommander wirelessChargerCommander7 = this.this$0;
                        wirelessChargerCommander7.getClass();
                        byte byteExtra4 = intent2.getByteExtra("slot_mask", (byte) -1);
                        Log.d("WirelessChargerCommander", "GWAD(), s=" + byteExtra4);
                        ResultReceiver resultReceiver6 = (ResultReceiver) intent2.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                        if (resultReceiver6 != null) {
                            if (byteExtra4 == -1) {
                                resultReceiver6.send(-1, (Bundle) null);
                                return;
                            }
                            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander7.wirelessCharger, new WirelessChargerCommander$getWpcDigests$1(byteExtra4, resultReceiver6, wirelessChargerCommander7));
                            return;
                        }
                        return;
                    }
                    return;
                case 882378784:
                    if (str2.equals("com.google.android.systemui.dreamliner.ACTION_GET_FEATURES")) {
                        WirelessChargerCommander wirelessChargerCommander8 = this.this$0;
                        wirelessChargerCommander8.getClass();
                        long longExtra3 = intent2.getLongExtra("charger_id", -1);
                        Log.d("WirelessChargerCommander", "GF(), c=" + longExtra3);
                        ResultReceiver resultReceiver7 = (ResultReceiver) intent2.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                        if (resultReceiver7 != null) {
                            if (longExtra3 == -1) {
                                resultReceiver7.send(-1, (Bundle) null);
                                return;
                            }
                            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander8.wirelessCharger, new WirelessChargerCommander$getFeatures$1(longExtra3, resultReceiver7));
                            return;
                        }
                        return;
                    }
                    return;
                case 1954561023:
                    if (str2.equals("com.google.android.systemui.dreamliner.ACTION_GET_WPC_CERTIFICATE")) {
                        WirelessChargerCommander wirelessChargerCommander9 = this.this$0;
                        wirelessChargerCommander9.getClass();
                        byte byteExtra5 = intent2.getByteExtra("slot_number", (byte) -1);
                        short shortExtra = intent2.getShortExtra("cert_offset", -1);
                        short shortExtra2 = intent2.getShortExtra("cert_length", -1);
                        StringBuilder m2 = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m("GWAC(), s=", byteExtra5, ", offset=", shortExtra, ", length=");
                        m2.append(shortExtra2);
                        Log.d("WirelessChargerCommander", m2.toString());
                        ResultReceiver resultReceiver8 = (ResultReceiver) intent2.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                        if (resultReceiver8 != null) {
                            if (byteExtra5 == -1 || shortExtra == -1 || shortExtra2 == -1) {
                                resultReceiver8.send(-1, (Bundle) null);
                                return;
                            }
                            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander9.wirelessCharger, new WirelessChargerCommander$getWpcCertificate$1(byteExtra5, shortExtra, shortExtra2, resultReceiver8, wirelessChargerCommander9));
                            return;
                        }
                        return;
                    }
                    return;
                case 2009307741:
                    if (str2.equals("com.google.android.systemui.dreamliner.ACTION_GET_FAN_INFO")) {
                        WirelessChargerCommander wirelessChargerCommander10 = this.this$0;
                        wirelessChargerCommander10.getClass();
                        byte byteExtra6 = intent2.getByteExtra("fan_id", (byte) -1);
                        Log.d("WirelessChargerCommander", "GFI(), i=" + byteExtra6);
                        ResultReceiver resultReceiver9 = (ResultReceiver) intent2.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                        if (resultReceiver9 != null) {
                            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander10.wirelessCharger, new WirelessChargerCommander$getFanInfo$1(byteExtra6, resultReceiver9));
                            return;
                        }
                        return;
                    }
                    return;
                case 2121889077:
                    if (str2.equals("com.google.android.systemui.dreamliner.ACTION_GET_WPC_CHALLENGE_RESPONSE")) {
                        WirelessChargerCommander wirelessChargerCommander11 = this.this$0;
                        wirelessChargerCommander11.getClass();
                        byte byteExtra7 = intent2.getByteExtra("slot_number", (byte) -1);
                        Log.d("WirelessChargerCommander", "GWACR(), s=" + byteExtra7);
                        ResultReceiver resultReceiver10 = (ResultReceiver) intent2.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                        if (resultReceiver10 != null) {
                            byte[] byteArrayExtra3 = intent2.getByteArrayExtra("wpc_nonce");
                            if (byteArrayExtra3 == null || byteArrayExtra3.length == 0) {
                                resultReceiver10.send(-1, (Bundle) null);
                                return;
                            }
                            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander11.wirelessCharger, new WirelessChargerCommander$getWpcChallengeResponse$1(byteExtra7, byteArrayExtra3, resultReceiver10, wirelessChargerCommander11));
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
