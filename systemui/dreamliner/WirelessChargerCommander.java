package com.google.android.systemui.dreamliner;

import android.content.IntentFilter;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class WirelessChargerCommander {
    public static ExecutorService singleThreadExecutor;
    public final IntentFilter commandIntents;
    public final WirelessChargerCommander$commandReceiver$1 commandReceiver;
    public final WirelessChargerCommander$fanLevelEventListener$1 fanLevelEventListener = new WirelessChargerCommander$fanLevelEventListener$1(this);
    public final AtomicBoolean isFanLevelCallbackRegistered = new AtomicBoolean(false);
    public final Optional wirelessCharger;
    public final CopyOnWriteArrayList wirelessChargerFanLevelChangedCallback = new CopyOnWriteArrayList();

    public WirelessChargerCommander(Optional optional) {
        this.wirelessCharger = optional;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_DOCK_INFO");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_KEY_EXCHANGE");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_CHALLENGE");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_FAN_INFO");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_FAN_LEVEL");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_SET_FAN");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_WPC_DIGESTS");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_WPC_CERTIFICATE");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_WPC_CHALLENGE_RESPONSE");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_FEATURES");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_SET_FEATURES");
        this.commandIntents = intentFilter;
        this.commandReceiver = new WirelessChargerCommander$commandReceiver$1(this);
    }

    public static void asyncIfPresent(Optional optional, Function1 function1) {
        WirelessChargerCommander$asyncIfPresent$1 wirelessChargerCommander$asyncIfPresent$1 = new WirelessChargerCommander$asyncIfPresent$1(optional, function1);
        if (singleThreadExecutor == null) {
            singleThreadExecutor = Executors.newSingleThreadExecutor();
        }
        ExecutorService executorService = singleThreadExecutor;
        if (executorService != null) {
            executorService.execute(wirelessChargerCommander$asyncIfPresent$1);
        }
    }

    public static /* synthetic */ void getCommandReceiver$annotations() {
    }
}
