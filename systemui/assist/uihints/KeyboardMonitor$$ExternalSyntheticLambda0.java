package com.google.android.systemui.assist.uihints;

import com.android.systemui.statusbar.CommandQueue;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class KeyboardMonitor$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ KeyboardMonitor f$0;

    public /* synthetic */ KeyboardMonitor$$ExternalSyntheticLambda0(KeyboardMonitor keyboardMonitor) {
        this.f$0 = keyboardMonitor;
    }

    public final void accept(Object obj) {
        KeyboardMonitor keyboardMonitor = this.f$0;
        keyboardMonitor.getClass();
        ((CommandQueue) obj).addCallback((CommandQueue.Callbacks) keyboardMonitor);
    }
}
